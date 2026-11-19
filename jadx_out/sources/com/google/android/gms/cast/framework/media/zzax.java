package com.google.android.gms.cast.framework.media;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzec;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzax implements zzec {
    private final /* synthetic */ RemoteMediaClient zzpv;
    private final /* synthetic */ RemoteMediaClient.zzc zzpw;

    zzax(RemoteMediaClient.zzc zzcVar, RemoteMediaClient remoteMediaClient) {
        this.zzpw = zzcVar;
        this.zzpv = remoteMediaClient;
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zzd(long j) {
        try {
            RemoteMediaClient.zzc zzcVar = this.zzpw;
            zzcVar.setResult((RemoteMediaClient.MediaChannelResult) zzcVar.createFailedResult(new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestReplaced", e);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zza(long j, int i, Object obj) {
        try {
            this.zzpw.setResult(new RemoteMediaClient.zzd(new Status(i), obj instanceof JSONObject ? (JSONObject) obj : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestCompleted", e);
        }
    }
}
