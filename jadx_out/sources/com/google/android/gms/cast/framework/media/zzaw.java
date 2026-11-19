package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzaw implements RemoteMediaClient.MediaChannelResult {
    private final /* synthetic */ Status zzam;

    zzaw(RemoteMediaClient.zzb zzbVar, Status status) {
        this.zzam = status;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    public final JSONObject getCustomData() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzam;
    }
}
