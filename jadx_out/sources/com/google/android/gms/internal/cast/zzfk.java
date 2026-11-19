package com.google.android.gms.internal.cast;

import android.view.Choreographer;

/* JADX INFO: loaded from: classes4.dex */
final class zzfk extends zzfe {
    private Choreographer zzabs = Choreographer.getInstance();

    @Override // com.google.android.gms.internal.cast.zzfe
    public final void zza(zzfg zzfgVar) {
        this.zzabs.postFrameCallback(zzfgVar.zzfi());
    }
}
