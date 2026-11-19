package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes4.dex */
final class zze extends AnimatorListenerAdapter {
    private final /* synthetic */ zza zzkj;

    zze(zza zzaVar) {
        this.zzkj = zzaVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        zza zzaVar = this.zzkj;
        zzaVar.zzkd = zzaVar.zzbf();
        this.zzkj.zzkd.start();
    }
}
