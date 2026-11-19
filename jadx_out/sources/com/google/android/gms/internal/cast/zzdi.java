package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzdi implements Runnable {
    private final /* synthetic */ zzdd zzyy;
    private final /* synthetic */ zzct zzzb;

    zzdi(zzdf zzdfVar, zzdd zzddVar, zzct zzctVar) {
        this.zzyy = zzddVar;
        this.zzzb = zzctVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzyy.zza(this.zzzb);
    }
}
