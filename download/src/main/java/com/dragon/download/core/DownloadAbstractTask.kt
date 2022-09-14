package com.dragon.download.core

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.OnLifecycleEvent
import com.dragon.download.listener.DownloadListener
import com.dragon.download.listener.NotificationListener
import com.dragon.download.utils.DownloadProvider

abstract class DownloadAbstractTask(val downloadRequest: DownloadRequest) : Runnable {

    protected var outputPath = ""

    protected val handler = Handler(Looper.myLooper()!!)

    val TAG = "DownloadTask"
    protected val lock = java.lang.Object()

    //下载文件总大小
    protected var size: Long = -1

    //当前下载大小
    protected var currentPosition: Long = 0

    //当前进度
    protected var currentProgress = -1

    //是否暂停下载
    internal var isPause = false

    //是否结束下载
    protected var cancel = false

    //单次读取阈值
    protected val download_threadhold = 1024 * 1024 * 3

    //下载监听
    internal val downloadListeners = mutableListOf<DownloadListener>()

    //注册下载监听
    fun registerDownloadListener(downloadListener: DownloadListener) {
        downloadListeners.add(downloadListener)
    }

    internal fun downloadError(throwable: Throwable) {
        runOnUi {
            downloadListeners.forEach {
                it.onDownloadError(key = downloadRequest.url, error = throwable)
            }
        }
    }

    internal fun downloadResume() {
        runOnUi {
            downloadListeners.forEach {
                it.onDownloadResume(key = downloadRequest.url)
            }
        }
    }

    internal fun downloadPrepare() {
        runOnUi {
            downloadListeners.forEach {
                it.onPrepare(key = downloadRequest.url)
            }
        }
    }

    internal fun downloadPause() {
        runOnUi {
            downloadListeners.forEach {
                it.onDownloadPause(key = downloadRequest.url)
            }
        }
    }

    internal fun downloadUpdate(progress: Int, current: Long, total: Long) {
        runOnUi {
            downloadListeners.forEach {
                it.onProgressUpdate(
                    key = downloadRequest.url,
                    progress = progress,
                    read = current,
                    count = total,
                )
            }
        }
    }

    internal fun downloadSuccess() {
        runOnUi {
            downloadListeners.forEach {
                it.onDownloadSuccess(
                    key = downloadRequest.url,
                    saveAddress = outputPath
                )
            }
        }
    }

    internal fun downloadCancel() {
        runOnUi {
            downloadListeners.forEach {
                it.onCancel(key = downloadRequest.url)
            }
        }
    }

    protected fun runOnUi(block: () -> Unit) {
        handler.post {
            block.invoke()
        }
    }
}