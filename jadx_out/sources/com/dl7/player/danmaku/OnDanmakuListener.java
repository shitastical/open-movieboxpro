package com.dl7.player.danmaku;

/* JADX INFO: loaded from: classes4.dex */
public interface OnDanmakuListener<T> {
    boolean isValid();

    void onDataObtain(T t);
}
