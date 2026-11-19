package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzfd extends zzfg {
    private final /* synthetic */ zzfc zzabn;

    zzfd(zzfc zzfcVar) {
        this.zzabn = zzfcVar;
    }

    @Override // com.google.android.gms.internal.cast.zzfg
    public final void doFrame(long j) {
        zzfc.zza(this.zzabn);
        zzfc zzfcVar = this.zzabn;
        if (zzfcVar.zzb(zzfcVar.animator) || this.zzabn.animator.isStarted() || this.zzabn.zzfg()) {
            return;
        }
        if (this.zzabn.zzabj != null) {
            this.zzabn.zzabj.run();
        }
        this.zzabn.animator.start();
    }
}
