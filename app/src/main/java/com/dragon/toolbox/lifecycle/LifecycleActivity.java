package com.dragon.toolbox.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dragon.toolbox.R;

public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
    }
}