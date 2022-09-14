package com.dragon.toolbox.animator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dragon.toolbox.R

class AnimationActivity : AppCompatActivity() {

    val firstFragment = FirstFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)


    }

}