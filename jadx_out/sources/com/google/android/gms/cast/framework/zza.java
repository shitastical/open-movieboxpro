package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public final class zza extends zzg {
    private final AppVisibilityListener zzha;

    public zza(AppVisibilityListener appVisibilityListener) {
        this.zzha = appVisibilityListener;
    }

    @Override // com.google.android.gms.cast.framework.zzf
    public final int zzs() {
        return 12451009;
    }

    @Override // com.google.android.gms.cast.framework.zzf
    public final IObjectWrapper zzt() {
        return ObjectWrapper.wrap(this.zzha);
    }

    @Override // com.google.android.gms.cast.framework.zzf
    public final void onAppEnteredForeground() {
        this.zzha.onAppEnteredForeground();
    }

    @Override // com.google.android.gms.cast.framework.zzf
    public final void onAppEnteredBackground() {
        this.zzha.onAppEnteredBackground();
    }
}
