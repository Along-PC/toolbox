package com.dragon.toolbox.rx;

public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);

}
