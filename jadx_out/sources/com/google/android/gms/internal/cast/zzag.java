package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzag extends zzak {
    private final /* synthetic */ zzac zzra;

    private zzag(zzac zzacVar) {
        this.zzra = zzacVar;
    }

    @Override // com.google.android.gms.internal.cast.zzaj
    public final int zzs() {
        return 12451009;
    }

    @Override // com.google.android.gms.internal.cast.zzaj
    public final void zza(long j, long j2) {
        this.zzra.publishProgress(Long.valueOf(j), Long.valueOf(j2));
    }
}
