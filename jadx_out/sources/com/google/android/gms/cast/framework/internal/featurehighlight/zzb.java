package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes4.dex */
final class zzb extends GestureDetector.SimpleOnGestureListener {
    private final /* synthetic */ zza zzkj;

    zzb(zza zzaVar) {
        this.zzkj = zzaVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.zzkj.zza(x, y) && this.zzkj.zzjz.zzb(x, y)) {
            return true;
        }
        this.zzkj.zzkh.dismiss();
        return true;
    }
}
