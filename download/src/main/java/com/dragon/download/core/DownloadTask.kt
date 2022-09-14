package com.dragon.download.core

import com.dragon.download.exception.CancelException
import com.dragon.download.exception.ExistException
import com.dragon.download.listener.NotificationListener
import com.dragon.download.utils.DownloadProvider
import com.dragon.download.utils.FileUtils
import com.dragon.download.utils.IoUtils
import com.dragon.download.utils.Md5Utils
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class DownloadTask(downloadRequest: DownloadRequest) : DownloadAbstractTask(downloadRequest) {

    init {
        if (downloadRequest.needNotify) {
            runOnUi {
                registerDownloadListener(NotificationListener(DownloadProvider.context,this@DownloadTask))
            }
        }
    }

    fun cancel() {
        this.cancel = true
        synchronized(lock) {
            lock.notify()
        }
    }

    fun pause() {
        isPause = true
        downloadPause()
    }

    fun resume() {
        isPause = false
        synchronized(lock) {
            lock.notify()
        }
        downloadResume()
    }

    override fun run() {
        createFile()
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null
        try {
            downloadPrepare()
            val url = URL(downloadRequest.url)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            //处理下载读取长度为-1 问题
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
            size = httpURLConnection.contentLength.toLong()
            val checkLocalFile = checkLocalFile(httpURLConnection)
            if (checkLocalFile) {
                throw ExistException()
            }
            inputStream = httpURLConnection.inputStream
            outputStream = FileOutputStream(outputPath)
            val byteArray = ByteArray(download_threadhold)
            var length = 0
            while (inputStream.read(byteArray).also { length = it } != -1) {
                if (cancel) {
                    throw CancelException()
                }
                if (isPause) {
                    synchronized(lock) {
                        lock.wait()
                    }
                }
                outputStream.write(byteArray, 0, length)
                currentPosition += length
                val progress = (currentPosition * 1.0f / size * 100).toInt()
                if (progress != currentProgress) {
                    currentProgress = progress
                    downloadUpdate(
                        progress = progress,
                        current = currentPosition,
                        total = size
                    )
                }
            }
            downloadSuccess()
        } catch (e: CancelException) {
            downloadCancel()
        } catch (e: ExistException) {
            downloadSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            downloadError(throwable = e)
        } finally {
            IoUtils.close(inputStream)
            IoUtils.close(outputStream)
        }
    }

    /**
     * 创建输出文件
     */
    private fun createFile() {
        if (downloadRequest.outputDir.isNullOrBlank()) {
            downloadRequest.outputDir = FileUtils.getFilePath(
                DownloadProvider.context,
                "download"
            )
        }
        val fileName = Md5Utils.encode(downloadRequest.url);
        outputPath = downloadRequest.outputDir + "/" + fileName
        val file = File(outputPath)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
    }

    /**
     * 检查本地是否存在已下载文件
     * @param httpURLConnection HttpURLConnection
     */
    private fun checkLocalFile(httpURLConnection: HttpURLConnection
    ): Boolean {
        val file = File(outputPath)
        if (!file.exists()) {
            return false
        }
        val available = httpURLConnection.contentLength.toLong()
        if (file.length() == available) {
            return true
        } else {
            file.delete()
            return false
        }
    }

}