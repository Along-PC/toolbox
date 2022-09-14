package com.dragon.toolbox.blur

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.dragon.toolbox.R
import com.dragon.toolbox.databinding.ActivityBlurBinding

inline fun <reified T : ViewBinding> Activity.inflate() = lazy {
    inflateBinding<T>(layoutInflater).apply {
        setContentView(root)
    }
}

inline fun <reified T : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    T::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as T

class BlurActivity : AppCompatActivity() {

    val viewBinding: ActivityBlurBinding by inflate<ActivityBlurBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding
    }
}