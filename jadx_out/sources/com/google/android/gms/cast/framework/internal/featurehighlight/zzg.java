package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes4.dex */
final class zzg extends AnimatorListenerAdapter {
    private final /* synthetic */ zza zzkj;
    private final /* synthetic */ Runnable zzkp;

    zzg(zza zzaVar, Runnable runnable) {
        this.zzkj = zzaVar;
        this.zzkp = runnable;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.zzkj.setVisibility(8);
        this.zzkj.zzkd = null;
        Runnable runnable = this.zzkp;
        if (runnable != null) {
            runnable.run();
        }
    }
}
