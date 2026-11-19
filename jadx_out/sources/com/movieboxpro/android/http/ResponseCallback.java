package com.movieboxpro.android.http;

/* JADX INFO: loaded from: classes5.dex */
public interface ResponseCallback<T> {
    void onFailure(int code, String error);

    void onStart();

    void onSuccess(T t);
}
