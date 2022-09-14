package com.dragon.download.core

import android.util.Log
import com.dragon.download.utils.IoUtils
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class DownloadPool {

    private val executors = Executors.newFixedThreadPool(3)

    fun download(downloadTask: DownloadTask){
        executors.execute(downloadTask)
    }

    fun cancel() {
        executors.shutdownNow()
    }

}