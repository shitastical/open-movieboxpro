package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zacn implements Runnable {
    private final /* synthetic */ zack zaky;
    private final /* synthetic */ Result zakz;

    zacn(zack zackVar, Result result) {
        this.zaky = zackVar;
        this.zakz = result;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            BasePendingResult.zado.set(true);
            this.zaky.zakw.sendMessage(this.zaky.zakw.obtainMessage(0, this.zaky.zakr.onSuccess(this.zakz)));
        } catch (RuntimeException e) {
            this.zaky.zakw.sendMessage(this.zaky.zakw.obtainMessage(1, e));
        } finally {
            BasePendingResult.zado.set(false);
            zack zackVar = this.zaky;
            zack.zab(this.zakz);
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zaky.zadr.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zaky);
            }
        }
    }
}
