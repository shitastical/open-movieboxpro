package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzck extends zzcj<GameManagerClient.GameManagerInstanceResult> {
    final /* synthetic */ zzcb zzwi;
    private GameManagerClient zzwr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzck(zzcb zzcbVar, GameManagerClient gameManagerClient) {
        super(zzcbVar);
        this.zzwi = zzcbVar;
        this.zzwr = gameManagerClient;
        this.zzwq = new zzcl(this, zzcbVar);
    }

    public static GameManagerClient.GameManagerInstanceResult zzc(Status status) {
        return new zzcm(status, null);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return zzc(status);
    }
}
