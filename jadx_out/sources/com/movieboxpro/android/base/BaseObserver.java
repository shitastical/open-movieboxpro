package com.movieboxpro.android.base;

import com.movieboxpro.android.http.ApiException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    public abstract void onError(ApiException e);

    @Override // io.reactivex.Observer
    public void onNext(T t) {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable d) {
    }

    @Override // io.reactivex.Observer
    public final void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(ApiException.handleException(e));
        }
    }
}
