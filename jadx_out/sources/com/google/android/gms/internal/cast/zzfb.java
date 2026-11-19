package com.google.android.gms.internal.cast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.collection.SimpleArrayMap;

/* JADX INFO: loaded from: classes4.dex */
public class zzfb extends AnimatorListenerAdapter {
    private SimpleArrayMap<Animator, Boolean> zzabi = new SimpleArrayMap<>();

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.zzabi.put(animator, false);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.zzabi.put(animator, true);
    }

    protected final boolean zzb(Animator animator) {
        return this.zzabi.containsKey(animator) && this.zzabi.get(animator).booleanValue();
    }
}
