package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
final class zzfj extends zzfe {
    private Handler handler;

    public zzfj(Looper looper) {
        this.handler = new Handler(looper);
    }

    @Override // com.google.android.gms.internal.cast.zzfe
    public final void zza(zzfg zzfgVar) {
        this.handler.postDelayed(zzfgVar.zzfj(), 0L);
    }
}
