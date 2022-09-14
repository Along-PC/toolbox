package com.dragon.toolbox.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.dragon.toolbox.R

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
    }

    fun go(view: View) {

        //设置worker执行条件
        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .build()
        //创建一次性任务请求，另有周期执行请求
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(WriteWorker::class.java).build()
        //启动worker
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
        //接收信息
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this) { workInfo ->
                val name = workInfo.state.name
                val finished = workInfo.state.isFinished
                Log.d("tag", "name:${name}")
                Log.d("tag", "finished:${finished}")
                if (finished) {
                    val outputData = workInfo.outputData
                    val msg = outputData.getString("msg")
                    Log.d("tag", "msg:${msg}")
                }
            }
    }
}