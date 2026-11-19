package com.movieboxpro.android.exo;

/* JADX INFO: loaded from: classes5.dex */
public interface PlayListener {
    void onBuffer(float percent);

    void onComplete();

    void onError(String msg);

    void onPause();

    void onPlay();

    void onPositionChanged();

    void onStart();

    void onVideoSizeChanged(int width, int height);
}
