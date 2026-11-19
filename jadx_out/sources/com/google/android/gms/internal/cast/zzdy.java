package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzdy implements zzec {
    private final /* synthetic */ zzec zzaap;
    private final /* synthetic */ zzdx zzaaq;

    zzdy(zzdx zzdxVar, zzec zzecVar) {
        this.zzaaq = zzdxVar;
        this.zzaap = zzecVar;
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zza(long j, int i, Object obj) {
        zzdx.zza(this.zzaaq, (Long) null);
        zzec zzecVar = this.zzaap;
        if (zzecVar != null) {
            zzecVar.zza(j, i, obj);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zzd(long j) {
        zzec zzecVar = this.zzaap;
        if (zzecVar != null) {
            zzecVar.zzd(j);
        }
    }
}
