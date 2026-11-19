package com.google.android.gms.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzv implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzv(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zzci;
        boolean z = castRemoteDisplayLocalService.zzcd;
        StringBuilder sb = new StringBuilder(59);
        sb.append("onCreate after delay. The local service been started: ");
        sb.append(z);
        castRemoteDisplayLocalService.zzb(sb.toString());
        if (this.zzci.zzcd) {
            return;
        }
        this.zzci.zzc("The local service has not been been started, stopping it");
        this.zzci.stopSelf();
    }
}
