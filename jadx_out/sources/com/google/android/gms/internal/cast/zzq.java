package com.google.android.gms.internal.cast;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes4.dex */
final class zzq implements Runnable {
    private final /* synthetic */ zzo zzji;

    zzq(zzo zzoVar) {
        this.zzji = zzoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzji.zzjh.zzjg) {
            ((ViewGroup) this.zzji.zzjh.zzij.getWindow().getDecorView()).removeView(this.zzji.zzjh);
            if (this.zzji.zzjh.zzin != null) {
                this.zzji.zzjh.zzin.onOverlayDismissed();
            }
            this.zzji.zzjh.reset();
        }
    }
}
