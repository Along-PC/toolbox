package com.dragon.toolbox.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.FileOutputStream

class WriteWorker(val context: Context,val workerParameters: WorkerParameters):Worker(context,workerParameters) {

    override fun doWork(): Result {
        return try{

            //读取参数
            val inputData = workerParameters.inputData
            val string = inputData.getString("msg")

            val currentTimeMillis = System.currentTimeMillis()
            val s =
                context.getExternalFilesDir("")?.absolutePath + "/" + currentTimeMillis + ".txt"
            val fileOutputStream=FileOutputStream(s)
            fileOutputStream.write("${currentTimeMillis}".toByteArray())
            fileOutputStream.close()
            //传递参数
            val data = Data.Builder().putString("msg", "good").build()
            Result.success(data)
        }catch (e:Exception){
            e.printStackTrace()
            Result.failure()
        }
    }

}