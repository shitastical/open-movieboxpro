package com.google.android.gms.common.api.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zaaj implements Runnable {
    private final /* synthetic */ zaak zafz;

    zaaj(zaak zaakVar) {
        this.zafz = zaakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zafz.zaey.cancelAvailabilityErrorNotifications(this.zafz.mContext);
    }
}
