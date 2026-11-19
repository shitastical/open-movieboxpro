package com.google.android.gms.common.api.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zafz;

    private zaau(zaak zaakVar) {
        this.zafz = zaakVar;
    }

    protected abstract void zaal();

    @Override // java.lang.Runnable
    public void run() {
        this.zafz.zaer.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            zaal();
            return;
        } catch (RuntimeException e) {
            this.zafz.zafv.zab(e);
            return;
        } finally {
            this.zafz.zaer.unlock();
        }
        this.zafz.zaer.unlock();
    }

    /* synthetic */ zaau(zaak zaakVar, zaaj zaajVar) {
        this(zaakVar);
    }
}
