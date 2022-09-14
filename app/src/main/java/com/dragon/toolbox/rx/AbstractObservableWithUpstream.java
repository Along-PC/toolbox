package com.dragon.toolbox.rx;

public abstract class AbstractObservableWithUpstream<T,R> extends Observable<R>{

    protected ObservableSource<T> mSource;

    public AbstractObservableWithUpstream(ObservableSource<T> source) {
        mSource = source;
    }


}
