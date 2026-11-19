package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.AppVisibilityListener;

/* JADX INFO: loaded from: classes4.dex */
final class zzi implements AppVisibilityListener {
    private final /* synthetic */ MediaNotificationService zzmi;

    zzi(MediaNotificationService mediaNotificationService) {
        this.zzmi = mediaNotificationService;
    }

    @Override // com.google.android.gms.cast.framework.AppVisibilityListener
    public final void onAppEnteredForeground() {
        this.zzmi.stopForeground(true);
    }

    @Override // com.google.android.gms.cast.framework.AppVisibilityListener
    public final void onAppEnteredBackground() {
        if (this.zzmi.zzbv != null) {
            MediaNotificationService mediaNotificationService = this.zzmi;
            mediaNotificationService.startForeground(1, mediaNotificationService.zzbv);
        } else {
            this.zzmi.stopForeground(true);
        }
    }
}
