package com.dragon.toolbox;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class BaseApplication extends Application {

    public static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("init",this.getClass().getSimpleName());
    }
}
