package com.dragon.toolbox.save;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.dragon.toolbox.R;

public class HttpActivity extends AppCompatActivity {

    private static final String TAG = "HttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        Intent intent = getIntent();
        Uri data = intent.getData();
        Log.e(TAG,"data:"+data);
    }
}