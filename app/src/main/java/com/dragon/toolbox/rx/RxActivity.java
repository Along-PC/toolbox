package com.dragon.toolbox.rx;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.dragon.toolbox.R;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    private static final String TAG = "RxActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        Observable observable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Emitter<String> emitter) {
                        emitter.onNext("welcome to cz!");
                    }
                })
                .map(new Function<String,String>(){

                    @Override
                    public String apply(String s) {
                        Log.d(TAG, "apply() called with: s = [ map1 ]");
                        return s+" map1";
                    }
                })
                .map(new Function<String,String>(){

                    @Override
                    public String apply(String s) {
                        Log.d(TAG, "apply() called with: s = [ map2 ]");
                        return s+" map2";
                    }
                })
                .map(new Function<String,String>(){

                    @Override
                    public String apply(String s) {
                        return s+" map3";
                    }
                })
                .map(new Function<String,String>(){

                    @Override
                    public String apply(String s) {
                        Log.d(TAG, "apply() called with: s = [ map4 ]");
                        return s+"map4";
                    }
                });
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                 Log.d(TAG, "注册成功");
            }

            @Override
            public void onNext(String str) {
                Log.d(TAG, "onNext() called with: str = [" + str + "]");
                Log.d(TAG, ("传递内容" + str));
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "传递完成");
            }
        });

        io.reactivex.Observable
                .create(new io.reactivex.ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        Log.d(TAG,"subscribe 当前线程："+Thread.currentThread().getId());
                        e.onNext("长治久安");
                    }
                })
                .map(new io.reactivex.functions.Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.d(TAG,"apply1 当前线程："+Thread.currentThread().getId());
                        return s;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new io.reactivex.functions.Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.d(TAG,"apply2 当前线程："+Thread.currentThread().getId());
                        return s;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new io.reactivex.functions.Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.d(TAG,"apply3 当前线程："+Thread.currentThread().getId());
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept() called with: s = [" + s + "]");
                        Log.d(TAG,"accept 当前线程："+Thread.currentThread().getId());
                    }
                });
    }
}