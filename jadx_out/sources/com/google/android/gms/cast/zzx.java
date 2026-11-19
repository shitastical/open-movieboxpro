package com.google.android.gms.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzx implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;
    private final /* synthetic */ boolean zzco;

    zzx(CastRemoteDisplayLocalService castRemoteDisplayLocalService, boolean z) {
        this.zzci = castRemoteDisplayLocalService;
        this.zzco = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzci.zza(this.zzco);
    }
}
