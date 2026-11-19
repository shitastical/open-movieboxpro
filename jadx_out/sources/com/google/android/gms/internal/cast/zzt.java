package com.google.android.gms.internal.cast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes4.dex */
final class zzt extends AnimatorListenerAdapter {
    private final /* synthetic */ zzs zzjm;

    zzt(zzs zzsVar) {
        this.zzjm = zzsVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.zzjm.zzjl.zzaw();
    }
}
