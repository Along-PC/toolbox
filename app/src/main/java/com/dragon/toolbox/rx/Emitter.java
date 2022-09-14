package com.dragon.toolbox.rx;

public interface Emitter<T> {

    void onNext(T t);

    void onError(Throwable throwable);

    void onComplete();
}


