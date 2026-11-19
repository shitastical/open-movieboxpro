package com.uber.autodispose;

import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes5.dex */
public interface ParallelFlowableSubscribeProxy<T> {
    void subscribe(Subscriber<? super T>[] subscriberArr);
}
