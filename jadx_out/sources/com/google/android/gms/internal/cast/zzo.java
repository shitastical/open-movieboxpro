package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.IntroductoryOverlay;

/* JADX INFO: loaded from: classes4.dex */
final class zzo implements com.google.android.gms.cast.framework.internal.featurehighlight.zzh {
    final /* synthetic */ zzn zzjh;

    zzo(zzn zznVar) {
        this.zzjh = zznVar;
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzh
    public final void zzav() {
        if (this.zzjh.zzjg) {
            IntroductoryOverlay.zza.zzd(this.zzjh.zzij);
            this.zzjh.zzjf.zzc(new zzp(this));
        }
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzh
    public final void dismiss() {
        if (this.zzjh.zzjg) {
            IntroductoryOverlay.zza.zzd(this.zzjh.zzij);
            this.zzjh.zzjf.zzb(new zzq(this));
        }
    }
}
