package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
final class zzaw implements zzab {
    private final /* synthetic */ zzav zzsk;

    zzaw(zzav zzavVar) {
        this.zzsk = zzavVar;
    }

    @Override // com.google.android.gms.internal.cast.zzab
    public final void zza(Bitmap bitmap) {
        if (bitmap != null) {
            this.zzsk.zzsi.setImageBitmap(bitmap);
        }
    }
}
