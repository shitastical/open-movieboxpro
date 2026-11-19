package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzcg implements ResultCallback<Status> {
    private final /* synthetic */ zzcb zzwi;
    private final /* synthetic */ long zzwn;

    zzcg(zzcb zzcbVar, long j) {
        this.zzwi = zzcbVar;
        this.zzwn = j;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (status.isSuccess()) {
            return;
        }
        this.zzwi.zza(this.zzwn, status.getStatusCode());
    }
}
