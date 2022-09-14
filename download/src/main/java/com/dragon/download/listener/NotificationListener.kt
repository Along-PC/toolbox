package com.dragon.download.listener

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.dragon.download.DownloadManager
import com.dragon.download.core.DownloadTask
import com.dragon.download.listener.NotificationListener.DownloadBroadcast.Companion.DOWNLOAD_URL
import com.dragon.download.listener.NotificationListener.DownloadBroadcast.Companion.PAUSE_ACTION
import java.util.*

@SuppressLint("RemoteViewLayout")
class NotificationListener(val context: Context, val task: DownloadTask) : DownloadListener {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private var notification: Notification? = null
    private var notificationBuilder: NotificationCompat.Builder? = null
    private val notifyId = Random().nextInt(10000)

    val TAG = "NotificationListener"


    override fun onPrepare(key: String) {
        val channelId = "channel_id_$key"
        val channelName = "channel_name_$key"
        notificationBuilder = NotificationCompat
            .Builder(context, channelId)
            .setContentTitle("下载管理")
            .setSmallIcon(getAppIcon())
            .setContentIntent(pauseAction())
            .setProgress(100, 0, false)

        notification = notificationBuilder?.build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(notifyId, notification)

    }

    override fun onProgressUpdate(key: String, progress: Int, read: Long, count: Long) {
        val builder = notificationBuilder
        if (builder == null) {
            return
        }
        builder.setProgress(100, progress, false)
        val notification = builder.build()
        notificationManager.notify(notifyId, notification)
    }

    override fun onDownloadError(key: String, error: Throwable) {
        cancelNotify()
    }

    private fun cancelNotify() {
        notificationManager.cancel(notifyId)
    }

    override fun onDownloadSuccess(key: String, saveAddress: String) {
        notificationBuilder?.setContentTitle("下载完成")
        notificationManager.notify(notifyId, notificationBuilder?.build())
        cancelNotify()
    }

    override fun onDownloadPause(key: String) {
        notificationBuilder?.setContentTitle("下载暂停")
        notificationManager.notify(notifyId, notificationBuilder?.build())
    }

    override fun onDownloadResume(key: String) {

    }

    override fun onCancel(key: String) {
        Log.d(TAG, "onCancel() called with: key = $key")
        cancelNotify()
    }

    fun pauseAction():PendingIntent{
        val intent=Intent(context,DownloadBroadcast::class.java)
        intent.action=PAUSE_ACTION
        intent.putExtra(DOWNLOAD_URL,task.downloadRequest.url)
        return PendingIntent.getBroadcast(context,0,intent,0)
    }

    fun getAppIcon(): Int {
        val packageManager = context.applicationContext.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.applicationInfo.icon
    }

    class DownloadBroadcast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            val url = intent.getStringExtra(DOWNLOAD_URL)
            if (action==null || url.isNullOrEmpty()) {
                return
            }
            if (action.equals(PAUSE_ACTION)) {
                DownloadManager.pause(url)
            }
        }

        companion object {
            val PAUSE_ACTION = "dragon_download_pause"
            val RESUME_ACTION = "dragon_download_resume"
            val DOWNLOAD_URL="dragon_download_url"
        }

    }

}