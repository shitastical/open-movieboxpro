package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public final class zzd extends zzo {
    private final CastStateListener zzih;

    public zzd(CastStateListener castStateListener) {
        this.zzih = castStateListener;
    }

    @Override // com.google.android.gms.cast.framework.zzn
    public final int zzs() {
        return 12451009;
    }

    @Override // com.google.android.gms.cast.framework.zzn
    public final IObjectWrapper zzt() {
        return ObjectWrapper.wrap(this.zzih);
    }

    @Override // com.google.android.gms.cast.framework.zzn
    public final void onCastStateChanged(int i) {
        this.zzih.onCastStateChanged(i);
    }
}
