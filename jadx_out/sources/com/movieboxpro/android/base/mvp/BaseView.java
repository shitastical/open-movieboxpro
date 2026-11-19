package com.movieboxpro.android.base.mvp;

import com.uber.autodispose.AutoDisposeConverter;

/* JADX INFO: loaded from: classes5.dex */
public interface BaseView {
    <T> AutoDisposeConverter<T> bindAutoDispose();

    void hideLoading();

    void onError(Throwable throwable);

    void showLoading();
}
