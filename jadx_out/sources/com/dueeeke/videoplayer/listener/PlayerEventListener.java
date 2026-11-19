package com.dueeeke.videoplayer.listener;

/* JADX INFO: loaded from: classes4.dex */
public interface PlayerEventListener {
    void onCompletion();

    void onError();

    void onInfo(int i, int i2);

    void onPrepared();

    void onSeekComplete(long j, long j2);

    void onVideoSizeChanged(int i, int i2);
}
