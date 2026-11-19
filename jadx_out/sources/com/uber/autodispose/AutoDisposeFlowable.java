package com.uber.autodispose;

import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposeFlowable<T> extends Flowable<T> {
    private final CompletableSource scope;
    private final Publisher<T> source;

    AutoDisposeFlowable(Publisher<T> publisher, CompletableSource completableSource) {
        this.source = publisher;
        this.scope = completableSource;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new AutoDisposingSubscriberImpl(this.scope, subscriber));
    }
}
