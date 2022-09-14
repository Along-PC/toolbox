package com.dragon.toolbox.animator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dragon.toolbox.R

class FirstFragment:Fragment(R.layout.fragment_animation_first) {

    val secondFragment = SecondFragment()
    val thirdFragment = ThirdFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.bt_last).setOnClickListener {
            last()
        }
        view.findViewById<View>(R.id.bt_next).setOnClickListener {
            next()
        }
    }


    fun last() {
        childFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.dialog_in,R.anim.dialog_out)
            .remove( secondFragment)
            .commit()
    }

    fun next() {
        childFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.dialog_in,R.anim.dialog_out)
            .add(R.id.frame_animation, secondFragment)
            .commit()
    }
}