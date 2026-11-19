package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzdg implements Runnable {
    private final /* synthetic */ zzdd zzyy;
    private final /* synthetic */ int zzyz;

    zzdg(zzdf zzdfVar, zzdd zzddVar, int i) {
        this.zzyy = zzddVar;
        this.zzyz = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzyy.zzak.onApplicationDisconnected(this.zzyz);
    }
}
