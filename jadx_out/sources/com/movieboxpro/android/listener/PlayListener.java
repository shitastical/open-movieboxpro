package com.movieboxpro.android.listener;

/* JADX INFO: loaded from: classes5.dex */
public interface PlayListener {
    void onBuffer(float percent);

    void onComplete();

    void onError();

    void onPause();

    void onPlay();

    void onPositionChanged();

    void onStart();
}
