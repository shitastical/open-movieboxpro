package com.google.android.gms.common.api.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zat implements Runnable {
    private final /* synthetic */ zaq zaet;

    zat(zaq zaqVar) {
        this.zaet = zaqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaet.zaer.lock();
        try {
            this.zaet.zav();
        } finally {
            this.zaet.zaer.unlock();
        }
    }
}
