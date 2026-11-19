package com.google.android.gms.cast;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzec;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbv implements zzec {
    private final /* synthetic */ RemoteMediaPlayer zzgo;
    private final /* synthetic */ RemoteMediaPlayer.zzb zzgp;

    zzbv(RemoteMediaPlayer.zzb zzbVar, RemoteMediaPlayer remoteMediaPlayer) {
        this.zzgp = zzbVar;
        this.zzgo = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zzd(long j) {
        try {
            RemoteMediaPlayer.zzb zzbVar = this.zzgp;
            zzbVar.setResult((RemoteMediaPlayer.MediaChannelResult) zzbVar.createFailedResult(new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zza(long j, int i, Object obj) {
        try {
            this.zzgp.setResult(new RemoteMediaPlayer.zzc(new Status(i), obj instanceof JSONObject ? (JSONObject) obj : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e);
        }
    }
}
