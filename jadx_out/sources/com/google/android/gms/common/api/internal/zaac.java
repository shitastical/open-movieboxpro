package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zaac implements PendingResult.StatusListener {
    private final /* synthetic */ zaz zafq;
    private final /* synthetic */ BasePendingResult zafr;

    zaac(zaz zazVar, BasePendingResult basePendingResult) {
        this.zafq = zazVar;
        this.zafr = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        this.zafq.zafm.remove(this.zafr);
    }
}
