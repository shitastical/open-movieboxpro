package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
final class zzay implements zzab {
    private final /* synthetic */ zzax zzsn;

    zzay(zzax zzaxVar) {
        this.zzsn = zzaxVar;
    }

    @Override // com.google.android.gms.internal.cast.zzab
    public final void zza(Bitmap bitmap) {
        if (bitmap != null) {
            if (this.zzsn.zzsm != null) {
                this.zzsn.zzsm.setVisibility(4);
            }
            this.zzsn.zzsi.setVisibility(0);
            this.zzsn.zzsi.setImageBitmap(bitmap);
        }
    }
}
