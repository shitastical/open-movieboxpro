package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zzhn implements Runnable {
    final /* synthetic */ zzik zza;

    zzhn(zzik zzikVar) {
        this.zza = zzikVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb.zzb();
    }
}
