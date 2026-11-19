package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;

/* JADX INFO: loaded from: classes4.dex */
final class zzcd implements Cast.MessageReceivedCallback {
    private final /* synthetic */ zzcc zzwj;

    zzcd(zzcc zzccVar) {
        this.zzwj = zzccVar;
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public final void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzwj.zzwi.zzo(str2);
    }
}
