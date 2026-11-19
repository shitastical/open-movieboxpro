package com.google.android.gms.internal.cast;

import android.animation.Animator;

/* JADX INFO: loaded from: classes4.dex */
public final class zzfc extends zzfb {
    protected final Animator animator;
    private int zzabl;
    private zzfg zzabm = new zzfd(this);
    private final int zzabk = -1;
    private final Runnable zzabj = null;

    public static void zza(Animator animator, int i, Runnable runnable) {
        animator.addListener(new zzfc(animator, -1, null));
    }

    private zzfc(Animator animator, int i, Runnable runnable) {
        this.animator = animator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (zzb(animator)) {
            return;
        }
        zzfe.zzfh().zza(this.zzabm);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzfg() {
        int i = this.zzabk;
        return i != -1 && this.zzabl >= i;
    }

    static /* synthetic */ int zza(zzfc zzfcVar) {
        int i = zzfcVar.zzabl;
        zzfcVar.zzabl = i + 1;
        return i;
    }
}
