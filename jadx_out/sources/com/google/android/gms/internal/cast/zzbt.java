package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzbt extends UIController {
    private boolean zzri = true;

    public abstract void zzg(long j);

    public boolean zzee() {
        return this.zzri;
    }

    public void zzk(boolean z) {
        this.zzri = z;
    }
}
