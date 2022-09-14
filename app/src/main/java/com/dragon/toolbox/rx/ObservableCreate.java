package com.dragon.toolbox.rx;

public class ObservableCreate<T> extends Observable{
    
    ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> observableOnSubscribe) {
        source = observableOnSubscribe;
    }

    @Override
    protected void subscribeActural(Observer observer) {
        CreateEmitter<T> createEmitter = new CreateEmitter<T>(observer);
        source.subscribe(createEmitter);
        observer.onSubscribe();
    }

    static class CreateEmitter<T> implements Emitter<T> {

        Observer<T> mObserver;

        public CreateEmitter(Observer<T> Observer) {
            mObserver = Observer;
        }

        @Override
        public void onNext(T t) {
            mObserver.onNext(t);
        }

        @Override
        public void onError(Throwable throwable) {
            mObserver.onError(throwable);
        }

        @Override
        public void onComplete() {
            mObserver.onComplete();
        }
    }
}
