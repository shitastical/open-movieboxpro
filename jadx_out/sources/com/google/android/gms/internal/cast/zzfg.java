package com.google.android.gms.internal.cast;

import android.view.Choreographer;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzfg {
    private Runnable zzabp;
    private Choreographer.FrameCallback zzabq;

    public abstract void doFrame(long j);

    final Choreographer.FrameCallback zzfi() {
        if (this.zzabq == null) {
            this.zzabq = new zzfh(this);
        }
        return this.zzabq;
    }

    final Runnable zzfj() {
        if (this.zzabp == null) {
            this.zzabp = new zzfi(this);
        }
        return this.zzabp;
    }
}
