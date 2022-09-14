package com.dragon.toolbox.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.dragon.toolbox.BaseApplication

fun String.logv(){
    Log.v("tag_map",this)
}

fun String.logd(){
    Log.d("tag_map",this)
}

fun String.logi(){
    Log.i("tag_map",this)
}

fun String.logw(){
    Log.w("tag_map",this)
}

fun String.loge(){
    Log.e("tag_map",this)
}

fun String.toast(context: Context= BaseApplication.mContext, duration:Int=Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}