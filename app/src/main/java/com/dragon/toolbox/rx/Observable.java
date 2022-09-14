package com.dragon.toolbox.rx;

public abstract class Observable<T> implements ObservableSource<T> {

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActural(observer);
    }

    protected abstract void subscribeActural(Observer observer);

    public static <T> Observable create(ObservableOnSubscribe<T> observableOnSubscribe){
        return new ObservableCreate<T>(observableOnSubscribe);
    }

    public <R> Observable<R> map(Function<T, R> function){
        return new ObservableMap<T,R>(this, function);
    }

}
