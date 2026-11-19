package com.google.android.gms.cast.framework.media.widget;

/* JADX INFO: loaded from: classes4.dex */
final class zzd implements Runnable {
    private final /* synthetic */ zzc zzvd;

    zzd(zzc zzcVar) {
        this.zzvd = zzcVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzvd.zzva.zza(this.zzvd.zzvb, this.zzvd.zzvc);
    }
}
