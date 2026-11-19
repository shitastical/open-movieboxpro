package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcn implements GameManagerClient.GameManagerResult {
    private final Status zzgq;
    private final String zzwt;
    private final long zzwu;
    private final JSONObject zzwv;

    zzcn(Status status, String str, long j, JSONObject jSONObject) {
        this.zzgq = status;
        this.zzwt = str;
        this.zzwu = j;
        this.zzwv = jSONObject;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzgq;
    }

    @Override // com.google.android.gms.cast.games.GameManagerClient.GameManagerResult
    public final String getPlayerId() {
        return this.zzwt;
    }

    @Override // com.google.android.gms.cast.games.GameManagerClient.GameManagerResult
    public final long getRequestId() {
        return this.zzwu;
    }

    @Override // com.google.android.gms.cast.games.GameManagerClient.GameManagerResult
    public final JSONObject getExtraMessageData() {
        return this.zzwv;
    }
}
