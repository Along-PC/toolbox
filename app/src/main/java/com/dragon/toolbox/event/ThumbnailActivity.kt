package com.dragon.toolbox.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragon.toolbox.R
import com.dragon.toolbox.databinding.ActivityThumbnailBinding

class ThumbnailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityThumbnailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thumbnailAdapter=ThumbnailAdapter(this)
        val customLayoutManager=CustomLayoutManager(this,RecyclerView.HORIZONTAL,false)
//        val customLayoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.recyclerActivityThumbnail.layoutManager=customLayoutManager
        binding.recyclerActivityThumbnail.adapter=thumbnailAdapter

        binding.tvActivityThumbnail1.setOnClickListener {
            "button1".toast()
            customLayoutManager.maxHeight=900
            thumbnailAdapter.notifyDataSetChanged()
        }

        binding.tvActivityThumbnail2.setOnClickListener {
            "button2".toast()
            customLayoutManager.maxHeight=2000
            thumbnailAdapter.notifyDataSetChanged()
        }

        binding.tvActivityThumbnail3.setOnClickListener {
            "button3".toast()
            customLayoutManager.maxHeight=2500
            thumbnailAdapter.notifyDataSetChanged()
        }

        binding.tvActivityThumbnail4.setOnClickListener {
            "button4".toast()
            customLayoutManager.maxHeight=3000
            thumbnailAdapter.notifyDataSetChanged()
        }

    }

    fun String.toast(){
        Toast.makeText(this@ThumbnailActivity,this,Toast.LENGTH_SHORT).show()
    }
}