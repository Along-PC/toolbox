package com.dragon.download.listener

abstract class DefaultListener:DownloadListener {
    override fun onPrepare(key: String) {

    }

    override fun onProgressUpdate(key: String, progress: Int, read: Long, count: Long) {

    }

    override fun onDownloadError(key: String, error: Throwable) {

    }

    override fun onDownloadPause(key: String) {

    }

    override fun onCancel(key: String) {

    }

    override fun onDownloadResume(key: String) {

    }
}