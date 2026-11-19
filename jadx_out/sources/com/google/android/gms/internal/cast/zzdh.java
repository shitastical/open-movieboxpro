package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzdh implements Runnable {
    private final /* synthetic */ zzdd zzyy;
    private final /* synthetic */ zzdl zzza;

    zzdh(zzdf zzdfVar, zzdd zzddVar, zzdl zzdlVar) {
        this.zzyy = zzddVar;
        this.zzza = zzdlVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzyy.zza(this.zzza);
    }
}
