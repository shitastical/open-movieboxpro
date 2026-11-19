package com.google.android.gms.cast.framework.media;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
final class zzj implements com.google.android.gms.internal.cast.zzab {
    private final /* synthetic */ MediaNotificationService zzmi;
    private final /* synthetic */ MediaNotificationService.zzb zzmj;

    zzj(MediaNotificationService mediaNotificationService, MediaNotificationService.zzb zzbVar) {
        this.zzmi = mediaNotificationService;
        this.zzmj = zzbVar;
    }

    @Override // com.google.android.gms.internal.cast.zzab
    public final void zza(Bitmap bitmap) {
        this.zzmj.zzmq = bitmap;
        this.zzmi.zzmf = this.zzmj;
        this.zzmi.zzbk();
    }
}
