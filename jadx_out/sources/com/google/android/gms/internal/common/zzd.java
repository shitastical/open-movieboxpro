package com.google.android.gms.internal.common;

import android.os.Build;
import androidx.media3.common.C;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* JADX INFO: loaded from: classes4.dex */
public final class zzd {
    public static final int zza;

    static {
        zza = Build.VERSION.SDK_INT >= 23 ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 0;
    }
}
