package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzav implements ResultCallback<Status> {
    private final long zzgk;
    private final /* synthetic */ RemoteMediaClient.zza zzpt;

    zzav(RemoteMediaClient.zza zzaVar, long j) {
        this.zzpt = zzaVar;
        this.zzgk = j;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (status.isSuccess()) {
            return;
        }
        RemoteMediaClient.this.zzff.zza(this.zzgk, status.getStatusCode());
    }
}
