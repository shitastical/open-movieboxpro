package com.uber.autodispose;

import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposeSingle<T> extends Single<T> {
    private final CompletableSource scope;
    private final SingleSource<T> source;

    AutoDisposeSingle(SingleSource<T> singleSource, CompletableSource completableSource) {
        this.source = singleSource;
        this.scope = completableSource;
    }

    @Override // io.reactivex.Single
    protected void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new AutoDisposingSingleObserverImpl(this.scope, singleObserver));
    }
}
