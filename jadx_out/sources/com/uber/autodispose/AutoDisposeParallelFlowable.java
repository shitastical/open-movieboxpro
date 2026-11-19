package com.uber.autodispose;

import io.reactivex.CompletableSource;
import io.reactivex.parallel.ParallelFlowable;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposeParallelFlowable<T> extends ParallelFlowable<T> {
    private final CompletableSource scope;
    private final ParallelFlowable<T> source;

    AutoDisposeParallelFlowable(ParallelFlowable<T> parallelFlowable, CompletableSource completableSource) {
        this.source = parallelFlowable;
        this.scope = completableSource;
    }

    @Override // io.reactivex.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscriberArr) {
        if (validate(subscriberArr)) {
            Subscriber<? super T>[] subscriberArr2 = new Subscriber[subscriberArr.length];
            for (int i = 0; i < subscriberArr.length; i++) {
                subscriberArr2[i] = new AutoDisposingSubscriberImpl(this.scope, subscriberArr[i]);
            }
            this.source.subscribe(subscriberArr2);
        }
    }

    @Override // io.reactivex.parallel.ParallelFlowable
    public int parallelism() {
        return this.source.parallelism();
    }
}
