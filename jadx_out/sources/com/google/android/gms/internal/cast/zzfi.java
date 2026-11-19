package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzfi implements Runnable {
    private final /* synthetic */ zzfg zzabr;

    zzfi(zzfg zzfgVar) {
        this.zzabr = zzfgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzabr.doFrame(System.nanoTime());
    }
}
