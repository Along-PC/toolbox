package com.dragon.toolbox.rx;

public interface ObservableOnSubscribe<T> {
    void subscribe(Emitter<T> emitter);
}
