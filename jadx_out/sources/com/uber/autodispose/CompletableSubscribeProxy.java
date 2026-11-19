package com.uber.autodispose;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;

/* JADX INFO: loaded from: classes5.dex */
public interface CompletableSubscribeProxy {
    Disposable subscribe();

    Disposable subscribe(Action action);

    Disposable subscribe(Action action, Consumer<? super Throwable> consumer);

    void subscribe(CompletableObserver completableObserver);

    @CheckReturnValue
    <E extends CompletableObserver> E subscribeWith(E e);

    @CheckReturnValue
    TestObserver<Void> test();

    @CheckReturnValue
    TestObserver<Void> test(boolean z);
}
