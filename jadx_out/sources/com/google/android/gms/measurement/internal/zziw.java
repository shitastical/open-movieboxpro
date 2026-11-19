package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zziw implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zziz zzb;

    zziw(zziz zzizVar, long j) {
        this.zzb = zzizVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzt.zzd().zzf(this.zza);
        this.zzb.zza = null;
    }
}
