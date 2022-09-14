package com.dragon.toolbox.snackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dragon.toolbox.databinding.ActivitySnackBinding
import com.google.android.material.snackbar.Snackbar

class SnackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivitySnackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fbActivitySnack.setOnClickListener {
            Snackbar.make(binding.fbActivitySnack,"gogogo",Snackbar.LENGTH_SHORT).setAction("过年",object:View.OnClickListener{
                override fun onClick(v: View?) {
                    Toast.makeText(this@SnackActivity,"过大年",Toast.LENGTH_LONG).show()
                }
            }).show()
        }

        Glide.with(this).load("http://www.yashijufc.cn/maps/res/world_map/ca4832360e0f3ef6ea2ca0f588b2fb0d/a1903ed6927b43b5700f92cc10ea8271_o/701311494/701311494_hd.jpg").into(binding.imgActivitySnack)
    }
}