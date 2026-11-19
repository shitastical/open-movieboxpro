package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzci implements zzec {
    private final /* synthetic */ zzcb zzwo;
    private final /* synthetic */ zzch zzwp;

    zzci(zzch zzchVar, zzcb zzcbVar) {
        this.zzwp = zzchVar;
        this.zzwo = zzcbVar;
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zzd(long j) {
        this.zzwp.setResult(zzch.zzb(new Status(2103)));
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zza(long j, int i, Object obj) {
        try {
            if (obj == null) {
                this.zzwp.setResult(new zzcn(new Status(i, (String) null, (PendingIntent) null), null, j, null));
                return;
            }
            zzcp zzcpVar = (zzcp) obj;
            String str = zzcpVar.zzxh;
            if (i == 0 && str != null) {
                this.zzwp.zzwi.zzwh = str;
            }
            this.zzwp.setResult(new zzcn(new Status(i, zzcpVar.zzxa, (PendingIntent) null), str, zzcpVar.zzxi, zzcpVar.zzxb));
        } catch (ClassCastException unused) {
            this.zzwp.setResult(zzch.zzb(new Status(13)));
        }
    }
}
