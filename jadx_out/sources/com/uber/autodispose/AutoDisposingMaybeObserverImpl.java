package com.uber.autodispose;

import com.uber.autodispose.observers.AutoDisposingMaybeObserver;
import io.reactivex.CompletableSource;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposingMaybeObserverImpl<T> implements AutoDisposingMaybeObserver<T> {
    private final MaybeObserver<? super T> delegate;
    private final CompletableSource scope;
    final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
    final AtomicReference<Disposable> scopeDisposable = new AtomicReference<>();

    AutoDisposingMaybeObserverImpl(CompletableSource completableSource, MaybeObserver<? super T> maybeObserver) {
        this.scope = completableSource;
        this.delegate = maybeObserver;
    }

    @Override // com.uber.autodispose.observers.AutoDisposingMaybeObserver
    public MaybeObserver<? super T> delegateObserver() {
        return this.delegate;
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        DisposableCompletableObserver disposableCompletableObserver = new DisposableCompletableObserver() { // from class: com.uber.autodispose.AutoDisposingMaybeObserverImpl.1
            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                AutoDisposingMaybeObserverImpl.this.scopeDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposingMaybeObserverImpl.this.onError(th);
            }

            @Override // io.reactivex.CompletableObserver
            public void onComplete() {
                AutoDisposingMaybeObserverImpl.this.scopeDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposableHelper.dispose(AutoDisposingMaybeObserverImpl.this.mainDisposable);
            }
        };
        if (AutoDisposeEndConsumerHelper.setOnce(this.scopeDisposable, disposableCompletableObserver, getClass())) {
            this.delegate.onSubscribe(this);
            this.scope.subscribe(disposableCompletableObserver);
            AutoDisposeEndConsumerHelper.setOnce(this.mainDisposable, disposable, getClass());
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.mainDisposable.get() == AutoDisposableHelper.DISPOSED;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        AutoDisposableHelper.dispose(this.scopeDisposable);
        AutoDisposableHelper.dispose(this.mainDisposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.scopeDisposable);
        this.delegate.onSuccess(t);
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.scopeDisposable);
        this.delegate.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.scopeDisposable);
        this.delegate.onComplete();
    }
}
