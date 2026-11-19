package com.google.android.gms.internal.cast;

import android.view.Choreographer;

/* JADX INFO: loaded from: classes4.dex */
final class zzfh implements Choreographer.FrameCallback {
    private final /* synthetic */ zzfg zzabr;

    zzfh(zzfg zzfgVar) {
        this.zzabr = zzfgVar;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        this.zzabr.doFrame(j);
    }
}
