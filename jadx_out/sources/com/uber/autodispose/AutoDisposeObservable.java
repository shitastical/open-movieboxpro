package com.uber.autodispose;

import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposeObservable<T> extends Observable<T> {
    private final CompletableSource scope;
    private final ObservableSource<T> source;

    AutoDisposeObservable(ObservableSource<T> observableSource, CompletableSource completableSource) {
        this.source = observableSource;
        this.scope = completableSource;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new AutoDisposingObserverImpl(this.scope, observer));
    }
}
