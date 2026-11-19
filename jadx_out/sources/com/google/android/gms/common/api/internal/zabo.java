package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zabo implements Runnable {
    private final /* synthetic */ ConnectionResult zajc;
    private final /* synthetic */ GoogleApiManager.zab zajk;

    zabo(GoogleApiManager.zab zabVar, ConnectionResult connectionResult) {
        this.zajk = zabVar;
        this.zajc = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GoogleApiManager.zaa zaaVar = (GoogleApiManager.zaa) GoogleApiManager.this.zaim.get(this.zajk.zaft);
        if (zaaVar == null) {
            return;
        }
        if (!this.zajc.isSuccess()) {
            zaaVar.onConnectionFailed(this.zajc);
            return;
        }
        GoogleApiManager.zab.zaa(this.zajk, true);
        if (this.zajk.zais.requiresSignIn()) {
            this.zajk.zabp();
            return;
        }
        try {
            this.zajk.zais.getRemoteService(null, this.zajk.zais.getScopesForConnectionlessNonSignIn());
        } catch (SecurityException e) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
            zaaVar.onConnectionFailed(new ConnectionResult(10));
        }
    }
}
