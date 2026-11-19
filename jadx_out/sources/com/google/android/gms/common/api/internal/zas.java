package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zas implements zabs {
    private final /* synthetic */ zaq zaet;

    private zas(zaq zaqVar) {
        this.zaet = zaqVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zab(Bundle bundle) {
        this.zaet.zaer.lock();
        try {
            this.zaet.zaa(bundle);
            this.zaet.zaeo = ConnectionResult.RESULT_SUCCESS;
            this.zaet.zav();
        } finally {
            this.zaet.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zac(ConnectionResult connectionResult) {
        this.zaet.zaer.lock();
        try {
            this.zaet.zaeo = connectionResult;
            this.zaet.zav();
        } finally {
            this.zaet.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zab(int i, boolean z) {
        this.zaet.zaer.lock();
        try {
            if (!this.zaet.zaeq && this.zaet.zaep != null && this.zaet.zaep.isSuccess()) {
                this.zaet.zaeq = true;
                this.zaet.zaej.onConnectionSuspended(i);
                return;
            }
            this.zaet.zaeq = false;
            this.zaet.zaa(i, z);
        } finally {
            this.zaet.zaer.unlock();
        }
    }

    /* synthetic */ zas(zaq zaqVar, zat zatVar) {
        this(zaqVar);
    }
}
