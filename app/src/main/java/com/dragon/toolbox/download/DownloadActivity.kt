package com.dragon.toolbox.download

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.DownloadListener
import com.dragon.toolbox.databinding.ActivityDownloadBinding
import com.luck.picture.lib.config.PictureSelectionConfig.listener
import java.io.File
import java.util.concurrent.locks.ReentrantLock

private const val TAG = "DownloadActivity"

class DownloadActivity : AppCompatActivity() {

//    private var request: DownloadRequest? = null

    lateinit var viewBinding: ActivityDownloadBinding

    val url =
        "https://lf3-plat.pglstatp-toutiao.com/obj/union-platform/d60b730fc520fad60c192d974ae4a8ab.zip"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDownloadBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.editActivityDownload.setText(url)
        viewBinding.btActivityDownloadStart.setOnClickListener {
            start(viewBinding)
        }
        viewBinding.btActivityDownloadPause.setOnClickListener {
            pause()
        }
        viewBinding.btActivityDownloadResume.setOnClickListener {
            resume()
        }
        viewBinding.btActivityDownloadCancel.setOnClickListener {
            cancel()
        }

    }

    private fun resume() {
//        val downloadRequest = request
//        if (downloadRequest == null) {
//            return
//        }
//        DownloadManager.resume(downloadRequest)
    }

    private fun cancel() {
//        val downloadRequest = request
//        if (downloadRequest == null) {
//            return
//        }
//        DownloadManager.cancel(downloadRequest)
    }

    private fun pause() {
//        val downloadRequest = request
//        if (downloadRequest == null) {
//            return
//        }
//        DownloadManager.pause(downloadRequest)
    }

    fun start(viewBinding: ActivityDownloadBinding) {
//        Log.d(TAG, "main线程：${Thread.currentThread().id}")
//        val downloadUrl = viewBinding.editActivityDownload.text.toString()
//        val savePath = getExternalFilesDir("")?.absolutePath + File.separator + "download.rar"
//        if (downloadUrl.isNullOrBlank()) {
//            return
//        }
//        val downloadRequest = DownloadRequest.DownloadRequestBuilder(downloadUrl)
//            .setSavePath(savePath)
//            .setNeedNotify(true)
//            .build()
//        request = downloadRequest
//        DownloadManager.download(downloadRequest)
//        DownloadManager.registerListener(downloadRequest, listener)
//        DownloadManager.registerListener(downloadRequest, object : DefaultListener() {
//            override fun onDownloadSuccess(key: String, saveAddress: String) {
//                Log.d(
//                    TAG,
//                    "------------- onDownloadSuccess() called with: key = $key, saveAddress = $saveAddress"
//                )
//            }
//        })
    }

//    val listener = object : DownloadListener {
//        override fun onPrepare(key: String) {
//            Log.d(TAG, "onPrepare() called with: key = $key")
//        }
//
//        override fun onProgressUpdate(key: String, progress: Int, read: Long, count: Long) {
//            Log.d(
//                TAG,
//                "onProgressUpdate() called with: key = $key, progress = $progress, read = $read, count = $count"
//            )
//            viewBinding.tvDownloadProgress.setText("${progress}%")
//        }
//
//        override fun onDownloadError(key: String, error: Throwable) {
//            Log.d(TAG, "onDownloadError() called with: key = $key, error = $error")
//        }
//
//        override fun onDownloadSuccess(key: String, saveAddress: String) {
//            Log.d(
//                TAG,
//                "onDownloadSuccess() called with: key = $key, saveAddress = $saveAddress"
//            )
//        }
//
//        override fun onDownloadPause(key: String) {
//            Log.d(TAG, "onDownloadPause() called with: key = $key")
//        }
//
//        override fun onDownloadResume(key: String) {
//            Log.d(TAG, "onDownloadResume() called with: key = $key")
//        }
//
//        override fun onCancel(key: String) {
//            Log.d(TAG, "onCancel() called with: key = $key")
//        }
//    }

}