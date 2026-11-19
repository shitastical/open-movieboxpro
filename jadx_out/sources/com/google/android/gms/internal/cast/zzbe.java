package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzbe extends Cast.Listener {
    private final /* synthetic */ zzbd zzss;

    zzbe(zzbd zzbdVar) {
        this.zzss = zzbdVar;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onVolumeChanged() {
        this.zzss.zzdk();
    }
}
