package com.dragon.toolbox.rx;

import android.util.Log;

public abstract class BasicObserver<T,R> implements Observer<T> {
    private static final String TAG = "BasicObserver";

    Observer<? super R> actual;

    public BasicObserver(Observer<? super R> observer) {
        actual = observer;
    }

    @Override
    public void onSubscribe() {
        Log.d(TAG, "注册成功");
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "传递完成");
    }
}
