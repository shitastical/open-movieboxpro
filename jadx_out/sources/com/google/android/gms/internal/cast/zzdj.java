package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;

/* JADX INFO: loaded from: classes4.dex */
final class zzdj implements Runnable {
    private final /* synthetic */ String zzae;
    private final /* synthetic */ zzdd zzyy;
    private final /* synthetic */ String zzzc;

    zzdj(zzdf zzdfVar, zzdd zzddVar, String str, String str2) {
        this.zzyy = zzddVar;
        this.zzae = str;
        this.zzzc = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Cast.MessageReceivedCallback messageReceivedCallback;
        synchronized (this.zzyy.zzya) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) this.zzyy.zzya.get(this.zzae);
        }
        if (messageReceivedCallback != null) {
            messageReceivedCallback.onMessageReceived(this.zzyy.zzie, this.zzae, this.zzzc);
        } else {
            zzdd.zzbf.d("Discarded message for unknown namespace '%s'", this.zzae);
        }
    }
}
