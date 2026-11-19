package com.uber.autodispose;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposeCompletable extends Completable {
    private final CompletableSource scope;
    private final Completable source;

    AutoDisposeCompletable(Completable completable, CompletableSource completableSource) {
        this.source = completable;
        this.scope = completableSource;
    }

    @Override // io.reactivex.Completable
    protected void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new AutoDisposingCompletableObserverImpl(this.scope, completableObserver));
    }
}
