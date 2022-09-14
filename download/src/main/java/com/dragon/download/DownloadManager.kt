package com.dragon.download

import com.dragon.download.core.DownloadPool
import com.dragon.download.core.DownloadRequest
import com.dragon.download.core.DownloadTask
import com.dragon.download.listener.DownloadListener

object DownloadManager  {

    private val downloadPool = DownloadPool()
    private val downloadChain = HashMap<DownloadRequest, DownloadTask>()

    fun download(downloadRequest: DownloadRequest) {
        val downloadTask = DownloadTask(downloadRequest)
        downloadChain.put(downloadRequest, downloadTask)
        downloadPool.download(downloadTask)
    }

    fun pause(downloadRequest: DownloadRequest) {
        downloadChain.get(downloadRequest)?.pause()
    }

    fun resume(downloadRequest: DownloadRequest) {
        downloadChain.get(downloadRequest)?.resume()
    }

    fun cancel(downloadRequest: DownloadRequest) {
        downloadChain.get(downloadRequest)?.cancel()
    }

    fun registerListener(downloadRequest: DownloadRequest, listener: DownloadListener) {
        downloadChain.get(downloadRequest)?.registerDownloadListener(listener)
    }

    private fun onDestory() {
        downloadPool.cancel()
    }

    fun pause(url:String){
        var iterator = downloadChain.iterator()
        while(iterator.hasNext()) {
            val entry=iterator.next()
            if (entry.key.url.equals(url)) {
                entry.value.pause()
                break
            }
        }
    }

    fun resume(url:String){
        var iterator = downloadChain.iterator()
        while(iterator.hasNext()) {
            val entry=iterator.next()
            if (entry.key.url.equals(url)) {
                entry.value.resume()
                break
            }
        }
    }

}