package com.dragon.toolbox.rx;

public class ObservableMap<T,R> extends AbstractObservableWithUpstream<T,R>{

    private final Function<T, R> mFunction;

    public ObservableMap(ObservableSource<T> observableSource, Function<T,R> function) {
        super(observableSource);
        mFunction = function;
    }

    @Override
    protected void subscribeActural(Observer observer) {
        mSource.subscribe(new MapObserver<T,R>(observer,mFunction));
    }

    static class MapObserver<T,R> extends BasicObserver<T,R>{

        private final Function<? super T,? super R> mapper;

        public MapObserver(Observer<? super R> observer, Function<? super T,? super R> function) {
            super(observer);
            mapper = function;
        }

        @Override
        public void onNext(T t) {
            R apply = (R) mapper.apply(t);
            actual.onNext(apply);
        }
    }
}
