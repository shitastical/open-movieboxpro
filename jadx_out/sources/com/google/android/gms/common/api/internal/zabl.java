package com.google.android.gms.common.api.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zabl implements Runnable {
    private final /* synthetic */ zabm zajd;

    zabl(zabm zabmVar) {
        this.zajd = zabmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zajd.zaiq.zais.disconnect();
    }
}
