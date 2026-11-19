package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzcm implements GameManagerClient.GameManagerInstanceResult {
    private final Status zzgq;
    private final GameManagerClient zzwr;

    zzcm(Status status, GameManagerClient gameManagerClient) {
        this.zzgq = status;
        this.zzwr = gameManagerClient;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzgq;
    }

    @Override // com.google.android.gms.cast.games.GameManagerClient.GameManagerInstanceResult
    public final GameManagerClient getGameManagerClient() {
        return this.zzwr;
    }
}
