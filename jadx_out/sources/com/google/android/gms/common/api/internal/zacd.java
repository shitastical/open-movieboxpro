package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zacd implements Runnable {
    private final /* synthetic */ zace zakl;

    zacd(zace zaceVar) {
        this.zakl = zaceVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zakl.zakn.zag(new ConnectionResult(4));
    }
}
