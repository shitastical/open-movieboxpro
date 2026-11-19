package com.google.android.gms.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzbu implements ResultCallback<Status> {
    private final long zzgk;
    private final /* synthetic */ RemoteMediaPlayer.zza zzgl;

    zzbu(RemoteMediaPlayer.zza zzaVar, long j) {
        this.zzgl = zzaVar;
        this.zzgk = j;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (status.isSuccess()) {
            return;
        }
        RemoteMediaPlayer.this.zzff.zza(this.zzgk, status.getStatusCode());
    }
}
