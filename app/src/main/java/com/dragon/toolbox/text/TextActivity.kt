package com.dragon.toolbox.text

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ScaleXSpan
import com.dragon.toolbox.databinding.ActivityTextBinding

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        JumpingBeans
            .with(binding.tvActivityTextOne)
            .appendJumpingDots()
            .build();
//        JumpingBeans.with(binding.tvActivityTextTwo)
//            .makeTextJump(0, binding.tvActivityTextTwo.getText().toString().indexOf(' '))
//            .setIsWave(false)
//            .setLoopDuration(1000)
//            .build();

        val builder = SpannableStringBuilder("hello world!")
        builder.setSpan(ScaleXSpan(2.5f), 3, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvActivityTextTwo.setText(builder)

    }
}