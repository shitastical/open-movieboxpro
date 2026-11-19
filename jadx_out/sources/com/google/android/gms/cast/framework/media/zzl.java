package com.google.android.gms.cast.framework.media;

import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
final class zzl extends TimerTask {
    private final /* synthetic */ MediaQueue zzni;

    zzl(MediaQueue mediaQueue) {
        this.zzni = mediaQueue;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        this.zzni.zzbs();
    }
}
