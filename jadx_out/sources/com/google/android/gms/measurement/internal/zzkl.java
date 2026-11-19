package com.google.android.gms.measurement.internal;

import androidx.media3.exoplayer.ExoPlayer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zzkl {
    final /* synthetic */ zzkp zza;
    private zzkk zzb;

    zzkl(zzkp zzkpVar) {
        this.zza = zzkpVar;
    }

    final void zza(long j) {
        this.zzb = new zzkk(this, this.zza.zzt.zzax().currentTimeMillis(), j);
        this.zza.zzd.postDelayed(this.zzb, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
    }

    final void zzb() {
        this.zza.zzg();
        zzkk zzkkVar = this.zzb;
        if (zzkkVar != null) {
            this.zza.zzd.removeCallbacks(zzkkVar);
        }
        this.zza.zzt.zzm().zzm.zza(false);
        this.zza.zzm(false);
    }
}
