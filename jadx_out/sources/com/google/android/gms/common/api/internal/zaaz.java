package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zaaz extends com.google.android.gms.internal.base.zar {
    private final /* synthetic */ zaaw zagv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zaaz(zaaw zaawVar, Looper looper) {
        super(looper);
        this.zagv = zaawVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.zagv.zaat();
            return;
        }
        if (i != 2) {
            int i2 = message.what;
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i2);
            Log.w("GoogleApiClientImpl", sb.toString());
            return;
        }
        this.zagv.resume();
    }
}
