package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.JsonUtils;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class zzcq implements GameManagerState {
    private final String zzww;
    private final int zzwx;
    private final int zzxl;
    private final int zzxm;
    private final String zzxn;
    private final JSONObject zzxo;
    private final Map<String, PlayerInfo> zzxp;

    public zzcq(int i, int i2, String str, JSONObject jSONObject, Collection<PlayerInfo> collection, String str2, int i3) {
        this.zzxl = i;
        this.zzxm = i2;
        this.zzxn = str;
        this.zzxo = jSONObject;
        this.zzww = str2;
        this.zzwx = i3;
        this.zzxp = new HashMap(collection.size());
        for (PlayerInfo playerInfo : collection) {
            this.zzxp.put(playerInfo.getPlayerId(), playerInfo);
        }
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final int getLobbyState() {
        return this.zzxl;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final int getGameplayState() {
        return this.zzxm;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final JSONObject getGameData() {
        return this.zzxo;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final CharSequence getGameStatusText() {
        return this.zzxn;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final CharSequence getApplicationName() {
        return this.zzww;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final int getMaxPlayers() {
        return this.zzwx;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final List<PlayerInfo> getPlayersInState(int i) {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo playerInfo : getPlayers()) {
            if (playerInfo.getPlayerState() == i) {
                arrayList.add(playerInfo);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final PlayerInfo getPlayer(String str) {
        if (str == null) {
            return null;
        }
        return this.zzxp.get(str);
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final Collection<PlayerInfo> getPlayers() {
        return DesugarCollections.unmodifiableCollection(this.zzxp.values());
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final List<PlayerInfo> getControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo playerInfo : getPlayers()) {
            if (playerInfo.isControllable()) {
                arrayList.add(playerInfo);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final List<PlayerInfo> getConnectedPlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo playerInfo : getPlayers()) {
            if (playerInfo.isConnected()) {
                arrayList.add(playerInfo);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final List<PlayerInfo> getConnectedControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo playerInfo : getPlayers()) {
            if (playerInfo.isConnected() && playerInfo.isControllable()) {
                arrayList.add(playerInfo);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final boolean hasLobbyStateChanged(GameManagerState gameManagerState) {
        return this.zzxl != gameManagerState.getLobbyState();
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final boolean hasGameplayStateChanged(GameManagerState gameManagerState) {
        return this.zzxm != gameManagerState.getGameplayState();
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final boolean hasGameDataChanged(GameManagerState gameManagerState) {
        return !JsonUtils.areJsonValuesEquivalent(this.zzxo, gameManagerState.getGameData());
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final boolean hasGameStatusTextChanged(GameManagerState gameManagerState) {
        return !zzdk.zza(this.zzxn, gameManagerState.getGameStatusText());
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final boolean hasPlayerChanged(String str, GameManagerState gameManagerState) {
        return !zzdk.zza(getPlayer(str), gameManagerState.getPlayer(str));
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final boolean hasPlayerStateChanged(String str, GameManagerState gameManagerState) {
        PlayerInfo player = getPlayer(str);
        PlayerInfo player2 = gameManagerState.getPlayer(str);
        if (player == null && player2 == null) {
            return false;
        }
        return player == null || player2 == null || player.getPlayerState() != player2.getPlayerState();
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final boolean hasPlayerDataChanged(String str, GameManagerState gameManagerState) {
        PlayerInfo player = getPlayer(str);
        PlayerInfo player2 = gameManagerState.getPlayer(str);
        if (player == null && player2 == null) {
            return false;
        }
        return player == null || player2 == null || !JsonUtils.areJsonValuesEquivalent(player.getPlayerData(), player2.getPlayerData());
    }

    @Override // com.google.android.gms.cast.games.GameManagerState
    public final Collection<String> getListOfChangedPlayers(GameManagerState gameManagerState) {
        HashSet hashSet = new HashSet();
        for (PlayerInfo playerInfo : getPlayers()) {
            PlayerInfo player = gameManagerState.getPlayer(playerInfo.getPlayerId());
            if (player == null || !playerInfo.equals(player)) {
                hashSet.add(playerInfo.getPlayerId());
            }
        }
        for (PlayerInfo playerInfo2 : gameManagerState.getPlayers()) {
            if (getPlayer(playerInfo2.getPlayerId()) == null) {
                hashSet.add(playerInfo2.getPlayerId());
            }
        }
        return hashSet;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof GameManagerState)) {
            GameManagerState gameManagerState = (GameManagerState) obj;
            if (getPlayers().size() != gameManagerState.getPlayers().size()) {
                return false;
            }
            for (PlayerInfo playerInfo : getPlayers()) {
                boolean z = false;
                for (PlayerInfo playerInfo2 : gameManagerState.getPlayers()) {
                    if (zzdk.zza(playerInfo.getPlayerId(), playerInfo2.getPlayerId())) {
                        if (!zzdk.zza(playerInfo, playerInfo2)) {
                            return false;
                        }
                        z = true;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            if (this.zzxl == gameManagerState.getLobbyState() && this.zzxm == gameManagerState.getGameplayState() && this.zzwx == gameManagerState.getMaxPlayers() && zzdk.zza(this.zzww, gameManagerState.getApplicationName()) && zzdk.zza(this.zzxn, gameManagerState.getGameStatusText()) && JsonUtils.areJsonValuesEquivalent(this.zzxo, gameManagerState.getGameData())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzxl), Integer.valueOf(this.zzxm), this.zzxp, this.zzxn, this.zzxo, this.zzww, Integer.valueOf(this.zzwx));
    }
}
