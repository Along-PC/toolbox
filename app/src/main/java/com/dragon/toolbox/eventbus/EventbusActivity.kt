package com.dragon.toolbox.eventbus

import android.content.BroadcastReceiver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.dragon.toolbox.R

class EventbusActivity : AppCompatActivity() {

    val tag="EventbusActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventbus)
        EventBus.getInstance().register(this)
        log("main:${Thread.currentThread()}")
    }

    fun io(view: View) {
        EventBus.getInstance().post(Msg(code = 0, msg = "success"))
    }

    fun main(view: View) {
        val thread=Thread{
            EventBus.getInstance().post(Msg(code = 1, msg = "fail"))
        }.start()
    }

    @Subscribe(threadMode = ThreadMode.POST)
    fun receiver(msg:Msg){
        val currentThread = Thread.currentThread()
        log("currentThread:${currentThread}")
        log("msg:${msg}")
    }

    fun log(content:String){
        Log.d(tag,content)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getInstance().unregister(this)
    }

    data class Msg(val code:Int,val msg:String)

    val handler=Handler(Looper.myLooper()!!)

    fun runOnUI(block:()->Unit){
        val thread = Thread{
            
        }
        handler.post {
            block.invoke()
        }
    }
}