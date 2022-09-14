package com.dragon.toolbox.download;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dragon.toolbox.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadActivity extends AppCompatActivity {

    private Button mBtStart;
    private Button mBtWait;
    private Button mBtNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mBtStart = (Button) findViewById(R.id.bt_start);
        mBtWait = (Button) findViewById(R.id.bt_wait);
        mBtNotify = (Button) findViewById(R.id.bt_notify);

        MyThread myThread = new MyThread();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.execute(myThread);
            }
        });

        mBtWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread.toWait();
                Log.e("MyThread", "阻塞时间:" + System.currentTimeMillis());
            }
        });

        mBtNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread.toNotify();
                Log.e("MyThread", "唤醒时间:" + System.currentTimeMillis());
            }
        });
    }

    public class MyThread extends Thread {

        private int index = 10000;

        private Object object = new Object();

        boolean isPause = false;

        public void toWait() {
            isPause = true;
        }

        public void toNotify() {
            isPause = false;
            synchronized (object) {
                object.notify();
            }
        }

        @Override
        public void run() {
            super.run();

            while (index > 0) {

                if (isPause) {
                    synchronized (object) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.e("MyThread", "执行时间:" + System.currentTimeMillis());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("MyThread", "index:" + index--);
            }

        }
    }
}