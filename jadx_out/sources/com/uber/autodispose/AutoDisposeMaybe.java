package com.uber.autodispose;

import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposeMaybe<T> extends Maybe<T> {
    private final CompletableSource scope;
    private final MaybeSource<T> source;

    AutoDisposeMaybe(MaybeSource<T> maybeSource, CompletableSource completableSource) {
        this.source = maybeSource;
        this.scope = completableSource;
    }

    @Override // io.reactivex.Maybe
    protected void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new AutoDisposingMaybeObserverImpl(this.scope, maybeObserver));
    }
}
