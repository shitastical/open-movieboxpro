package com.google.android.gms.cast.framework.media.widget;

import android.graphics.Bitmap;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzab;

/* JADX INFO: loaded from: classes4.dex */
final class zza implements zzab {
    private final /* synthetic */ ExpandedControllerActivity zzva;

    zza(ExpandedControllerActivity expandedControllerActivity) {
        this.zzva = expandedControllerActivity;
    }

    @Override // com.google.android.gms.internal.cast.zzab
    public final void zza(Bitmap bitmap) {
        if (bitmap != null) {
            if (this.zzva.zzur != null) {
                this.zzva.zzur.setVisibility(8);
                if (!PlatformVersion.isAtLeastM()) {
                    this.zzva.zzur.setTextAppearance(this.zzva.getApplicationContext(), this.zzva.zzui);
                } else {
                    this.zzva.zzur.setTextAppearance(this.zzva.zzui);
                }
                this.zzva.zzur.setTextColor(this.zzva.zzud);
            }
            if (this.zzva.zzuq != null) {
                this.zzva.zzuq.setVisibility(0);
                this.zzva.zzuq.setImageBitmap(bitmap);
            }
        }
    }
}
