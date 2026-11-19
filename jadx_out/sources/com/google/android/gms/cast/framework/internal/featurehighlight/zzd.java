package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
final class zzd implements View.OnLayoutChangeListener {
    private final /* synthetic */ zza zzkj;
    private final /* synthetic */ Runnable zzkn = null;

    zzd(zza zzaVar, Runnable runnable) {
        this.zzkj = zzaVar;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Runnable runnable = this.zzkn;
        if (runnable != null) {
            runnable.run();
        }
        this.zzkj.zzbb();
        this.zzkj.removeOnLayoutChangeListener(this);
    }
}
