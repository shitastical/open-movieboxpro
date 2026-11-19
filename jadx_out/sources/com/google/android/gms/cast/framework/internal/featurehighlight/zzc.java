package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
final class zzc extends GestureDetector.SimpleOnGestureListener {
    private final /* synthetic */ View zzkk;
    private final /* synthetic */ boolean zzkl = true;
    private final /* synthetic */ zzh zzkm;

    zzc(zza zzaVar, View view, boolean z, zzh zzhVar) {
        this.zzkk = view;
        this.zzkm = zzhVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.zzkk.getParent() != null) {
            this.zzkk.performClick();
        }
        if (!this.zzkl) {
            return true;
        }
        this.zzkm.zzav();
        return true;
    }
}
