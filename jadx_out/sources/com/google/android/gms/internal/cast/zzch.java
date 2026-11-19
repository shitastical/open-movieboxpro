package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzch extends zzcj<GameManagerClient.GameManagerResult> {
    final /* synthetic */ zzcb zzwi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzch(zzcb zzcbVar) {
        super(zzcbVar);
        this.zzwi = zzcbVar;
        this.zzwq = new zzci(this, zzcbVar);
    }

    public static GameManagerClient.GameManagerResult zzb(Status status) {
        return new zzcn(status, null, -1L, null);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return zzb(status);
    }
}
