package com.uber.autodispose;

import com.uber.autodispose.observers.AutoDisposingSubscriber;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposingSubscriberImpl<T> extends AtomicInteger implements AutoDisposingSubscriber<T> {
    private final Subscriber<? super T> delegate;
    private final CompletableSource scope;
    final AtomicReference<Subscription> mainSubscription = new AtomicReference<>();
    final AtomicReference<Disposable> scopeDisposable = new AtomicReference<>();
    private final AtomicThrowable error = new AtomicThrowable();
    private final AtomicReference<Subscription> ref = new AtomicReference<>();
    private final AtomicLong requested = new AtomicLong();

    AutoDisposingSubscriberImpl(CompletableSource completableSource, Subscriber<? super T> subscriber) {
        this.scope = completableSource;
        this.delegate = subscriber;
    }

    @Override // com.uber.autodispose.observers.AutoDisposingSubscriber
    public Subscriber<? super T> delegateSubscriber() {
        return this.delegate;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        DisposableCompletableObserver disposableCompletableObserver = new DisposableCompletableObserver() { // from class: com.uber.autodispose.AutoDisposingSubscriberImpl.1
            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                AutoDisposingSubscriberImpl.this.scopeDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposingSubscriberImpl.this.onError(th);
            }

            @Override // io.reactivex.CompletableObserver
            public void onComplete() {
                AutoDisposingSubscriberImpl.this.scopeDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoSubscriptionHelper.cancel(AutoDisposingSubscriberImpl.this.mainSubscription);
            }
        };
        if (AutoDisposeEndConsumerHelper.setOnce(this.scopeDisposable, disposableCompletableObserver, getClass())) {
            this.delegate.onSubscribe(this);
            this.scope.subscribe(disposableCompletableObserver);
            if (AutoDisposeEndConsumerHelper.setOnce(this.mainSubscription, subscription, getClass())) {
                AutoSubscriptionHelper.deferredSetOnce(this.ref, this.requested, subscription);
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j) {
        AutoSubscriptionHelper.deferredRequest(this.ref, this.requested, j);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        AutoDisposableHelper.dispose(this.scopeDisposable);
        AutoSubscriptionHelper.cancel(this.mainSubscription);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.mainSubscription.get() == AutoSubscriptionHelper.CANCELLED;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        cancel();
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (isDisposed() || !HalfSerializer.onNext(this.delegate, t, this, this.error)) {
            return;
        }
        this.mainSubscription.lazySet(AutoSubscriptionHelper.CANCELLED);
        AutoDisposableHelper.dispose(this.scopeDisposable);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (isDisposed()) {
            return;
        }
        this.mainSubscription.lazySet(AutoSubscriptionHelper.CANCELLED);
        AutoDisposableHelper.dispose(this.scopeDisposable);
        HalfSerializer.onError(this.delegate, th, this, this.error);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        this.mainSubscription.lazySet(AutoSubscriptionHelper.CANCELLED);
        AutoDisposableHelper.dispose(this.scopeDisposable);
        HalfSerializer.onComplete(this.delegate, this, this.error);
    }
}
