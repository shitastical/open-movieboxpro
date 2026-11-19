package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.JsonUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class zzcr implements PlayerInfo {
    private final int zzep;
    private final String zzwt;
    private final JSONObject zzxq;
    private final boolean zzxr;

    public zzcr(String str, int i, JSONObject jSONObject, boolean z) {
        this.zzwt = str;
        this.zzep = i;
        this.zzxq = jSONObject;
        this.zzxr = z;
    }

    @Override // com.google.android.gms.cast.games.PlayerInfo
    public final int getPlayerState() {
        return this.zzep;
    }

    @Override // com.google.android.gms.cast.games.PlayerInfo
    public final JSONObject getPlayerData() {
        return this.zzxq;
    }

    @Override // com.google.android.gms.cast.games.PlayerInfo
    public final String getPlayerId() {
        return this.zzwt;
    }

    @Override // com.google.android.gms.cast.games.PlayerInfo
    public final boolean isConnected() {
        int i = this.zzep;
        return i == 3 || i == 4 || i == 5 || i == 6;
    }

    @Override // com.google.android.gms.cast.games.PlayerInfo
    public final boolean isControllable() {
        return this.zzxr;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof PlayerInfo)) {
            PlayerInfo playerInfo = (PlayerInfo) obj;
            if (this.zzxr == playerInfo.isControllable() && this.zzep == playerInfo.getPlayerState() && zzdk.zza(this.zzwt, playerInfo.getPlayerId()) && JsonUtils.areJsonValuesEquivalent(this.zzxq, playerInfo.getPlayerData())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzwt, Integer.valueOf(this.zzep), this.zzxq, Boolean.valueOf(this.zzxr));
    }
}
