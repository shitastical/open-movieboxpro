package com.google.android.gms.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzy implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;
    private final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzcl;

    zzy(CastRemoteDisplayLocalService castRemoteDisplayLocalService, CastRemoteDisplayLocalService.NotificationSettings notificationSettings) {
        this.zzci = castRemoteDisplayLocalService;
        this.zzcl = notificationSettings;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzci.zza(this.zzcl);
    }
}
