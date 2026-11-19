package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbw implements RemoteMediaPlayer.MediaChannelResult {
    private final /* synthetic */ Status zzam;

    zzbw(RemoteMediaPlayer.zzb zzbVar, Status status) {
        this.zzam = status;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult
    public final JSONObject getCustomData() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzam;
    }
}
