package com.dragon.toolbox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dragon.toolbox.scroll.ScrollActivity
import com.jakj.downloader.DownloadManager

class RouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, ScrollActivity::class.java))
    }
}