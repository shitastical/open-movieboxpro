package com.google.android.gms.internal.cast;

import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class zzeh implements CastRemoteDisplayApi {
    private static final zzdw zzbf = new zzdw("CastRemoteDisplayApiImpl");
    private Api<?> zzaax;
    private final zzex zzaay = new zzei(this);
    private VirtualDisplay zzbg;

    public zzeh(Api api) {
        this.zzaax = api;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> startRemoteDisplay(GoogleApiClient googleApiClient, String str) {
        zzbf.d("startRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzej(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> stopRemoteDisplay(GoogleApiClient googleApiClient) {
        zzbf.d("stopRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzek(this, googleApiClient));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc() {
        VirtualDisplay virtualDisplay = this.zzbg;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                zzdw zzdwVar = zzbf;
                int displayId = this.zzbg.getDisplay().getDisplayId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("releasing virtual display: ");
                sb.append(displayId);
                zzdwVar.d(sb.toString(), new Object[0]);
            }
            this.zzbg.release();
            this.zzbg = null;
        }
    }
}
