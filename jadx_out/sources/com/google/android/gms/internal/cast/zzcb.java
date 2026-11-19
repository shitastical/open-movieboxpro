package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class zzcb extends zzcw {
    private static final String NAMESPACE = zzdk.zzq("com.google.cast.games");
    private static final zzdw zzbf = new zzdw("GameManagerChannel");
    private final Cast.CastApi zzhz;
    private final GoogleApiClient zzpb;
    private final Map<String, String> zzvv;
    private final SharedPreferences zzvw;
    private final String zzvx;
    private zzco zzvy;
    private boolean zzvz;
    private GameManagerState zzwa;
    private GameManagerState zzwb;
    private String zzwc;
    private JSONObject zzwd;
    private long zzwe;
    private GameManagerClient.Listener zzwf;
    private final Clock zzwg;
    private String zzwh;

    public zzcb(GoogleApiClient googleApiClient, String str, Cast.CastApi castApi) throws IllegalStateException, IllegalArgumentException {
        super(NAMESPACE, "CastGameManagerChannel", null);
        this.zzvv = new ConcurrentHashMap();
        this.zzvz = false;
        this.zzwe = 0L;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("castSessionId cannot be null.");
        }
        if (googleApiClient == null || !googleApiClient.isConnected() || !googleApiClient.hasConnectedApi(Cast.API)) {
            throw new IllegalArgumentException("googleApiClient needs to be connected and contain the Cast.API API.");
        }
        this.zzwg = DefaultClock.getInstance();
        this.zzvx = str;
        this.zzhz = castApi;
        this.zzpb = googleApiClient;
        Context applicationContext = googleApiClient.getContext().getApplicationContext();
        this.zzvw = applicationContext.getApplicationContext().getSharedPreferences(String.format(Locale.ROOT, "%s.%s", applicationContext.getPackageName(), "game_manager_channel_data"), 0);
        this.zzwb = null;
        this.zzwa = new zzcq(0, 0, "", null, new ArrayList(), "", -1);
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerInstanceResult> zza(GameManagerClient gameManagerClient) throws IllegalArgumentException {
        return this.zzpb.execute(new zzcc(this, gameManagerClient));
    }

    public final synchronized void dispose() throws IllegalStateException {
        if (this.zzvz) {
            return;
        }
        this.zzwa = null;
        this.zzwb = null;
        this.zzwc = null;
        this.zzwd = null;
        this.zzvz = true;
        try {
            this.zzhz.removeMessageReceivedCallbacks(this.zzpb, getNamespace());
        } catch (IOException e) {
            zzbf.w("Exception while detaching game manager channel.", e);
        }
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerResult> zza(String str, int i, JSONObject jSONObject) throws IllegalStateException {
        zzek();
        return this.zzpb.execute(new zzce(this, i, str, jSONObject));
    }

    public final synchronized void sendGameMessage(String str, JSONObject jSONObject) throws IllegalStateException {
        zzek();
        long j = this.zzwe + 1;
        this.zzwe = j;
        JSONObject jSONObjectZza = zza(j, str, 7, jSONObject);
        if (jSONObjectZza == null) {
            return;
        }
        this.zzhz.sendMessage(this.zzpb, getNamespace(), jSONObjectZza.toString());
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerResult> sendGameRequest(String str, JSONObject jSONObject) throws IllegalStateException {
        zzek();
        return this.zzpb.execute(new zzcf(this, str, jSONObject));
    }

    public final synchronized GameManagerState getCurrentState() throws IllegalStateException {
        zzek();
        return this.zzwa;
    }

    public final synchronized String getLastUsedPlayerId() throws IllegalStateException {
        zzek();
        return this.zzwh;
    }

    private final synchronized String zzn(String str) throws IllegalStateException {
        if (str == null) {
            return null;
        }
        return this.zzvv.get(str);
    }

    public final synchronized void setListener(GameManagerClient.Listener listener) {
        this.zzwf = listener;
    }

    @Override // com.google.android.gms.internal.cast.zzdc
    public final void zzo(String str) {
        String str2;
        zzdw zzdwVar = zzbf;
        int i = 0;
        zzdwVar.d("message received: %s", str);
        try {
            zzcp zzcpVarZzj = zzcp.zzj(new JSONObject(str));
            if (zzcpVarZzj == null) {
                zzdwVar.w("Could not parse game manager message from string: %s", str);
                return;
            }
            if ((isInitialized() || zzcpVarZzj.zzxk != null) && !isDisposed()) {
                boolean z = zzcpVarZzj.zzwy == 1;
                if (z && !TextUtils.isEmpty(zzcpVarZzj.zzxj)) {
                    this.zzvv.put(zzcpVarZzj.zzxh, zzcpVarZzj.zzxj);
                    zzel();
                }
                if (zzcpVarZzj.zzwz == 0) {
                    zza(zzcpVarZzj);
                } else {
                    zzdwVar.w("Not updating from game message because the message contains error code: %d", Integer.valueOf(zzcpVarZzj.zzwz));
                }
                int i2 = zzcpVarZzj.zzwz;
                if (i2 != 0) {
                    if (i2 == 1) {
                        i = 2001;
                    } else if (i2 == 2) {
                        i = 2003;
                    } else if (i2 == 3) {
                        i = GameManagerClient.STATUS_INCORRECT_VERSION;
                    } else if (i2 != 4) {
                        StringBuilder sb = new StringBuilder(53);
                        sb.append("Unknown GameManager protocol status code: ");
                        sb.append(i2);
                        zzdwVar.w(sb.toString(), new Object[0]);
                        i = 13;
                    } else {
                        i = GameManagerClient.STATUS_TOO_MANY_PLAYERS;
                    }
                }
                if (z) {
                    zzb(zzcpVarZzj.zzxi, i, zzcpVarZzj);
                }
                if (isInitialized() && i == 0) {
                    if (this.zzwf != null) {
                        GameManagerState gameManagerState = this.zzwb;
                        if (gameManagerState != null && !this.zzwa.equals(gameManagerState)) {
                            this.zzwf.onStateChanged(this.zzwa, this.zzwb);
                        }
                        JSONObject jSONObject = this.zzwd;
                        if (jSONObject != null && (str2 = this.zzwc) != null) {
                            this.zzwf.onGameMessageReceived(str2, jSONObject);
                        }
                    }
                    this.zzwb = null;
                    this.zzwc = null;
                    this.zzwd = null;
                }
            }
        } catch (JSONException e) {
            zzbf.w("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    public final synchronized boolean isDisposed() {
        return this.zzvz;
    }

    private final synchronized boolean isInitialized() {
        return this.zzvy != null;
    }

    @Override // com.google.android.gms.internal.cast.zzdc
    public final void zza(long j, int i) {
        zzb(j, i, null);
    }

    private final synchronized void zzek() throws IllegalStateException {
        if (!isInitialized()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel before it is initialized.");
        }
        if (isDisposed()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel after it has been disposed.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str, int i, JSONObject jSONObject, zzec zzecVar) {
        long j = this.zzwe + 1;
        this.zzwe = j;
        JSONObject jSONObjectZza = zza(j, str, i, jSONObject);
        if (jSONObjectZza == null) {
            zzecVar.zza(-1L, 2001, null);
            zzbf.w("Not sending request because it was invalid.", new Object[0]);
        } else {
            zzed zzedVar = new zzed(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
            zzedVar.zza(j, zzecVar);
            zza(zzedVar);
            this.zzhz.sendMessage(this.zzpb, getNamespace(), jSONObjectZza.toString()).setResultCallback(new zzcg(this, j));
        }
    }

    private final JSONObject zza(long j, String str, int i, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("requestId", j);
            jSONObject2.put("type", i);
            jSONObject2.put("extraMessageData", jSONObject);
            jSONObject2.put("playerId", str);
            jSONObject2.put("playerToken", zzn(str));
            return jSONObject2;
        } catch (JSONException e) {
            zzbf.w("JSONException when trying to create a message: %s", e.getMessage());
            return null;
        }
    }

    private final synchronized void zza(zzcp zzcpVar) {
        boolean z = true;
        if (zzcpVar.zzwy != 1) {
            z = false;
        }
        this.zzwb = this.zzwa;
        if (z && zzcpVar.zzxk != null) {
            this.zzvy = zzcpVar.zzxk;
        }
        if (isInitialized()) {
            ArrayList arrayList = new ArrayList();
            for (zzcs zzcsVar : zzcpVar.zzxe) {
                String playerId = zzcsVar.getPlayerId();
                arrayList.add(new zzcr(playerId, zzcsVar.getPlayerState(), zzcsVar.getPlayerData(), this.zzvv.containsKey(playerId)));
            }
            zzcq zzcqVar = new zzcq(zzcpVar.zzxd, zzcpVar.zzxc, zzcpVar.zzxg, zzcpVar.zzxf, arrayList, this.zzvy.zzeo(), this.zzvy.getMaxPlayers());
            this.zzwa = zzcqVar;
            PlayerInfo player = zzcqVar.getPlayer(zzcpVar.zzxh);
            if (player != null && player.isControllable() && zzcpVar.zzwy == 2) {
                this.zzwc = zzcpVar.zzxh;
                this.zzwd = zzcpVar.zzxb;
            }
        }
    }

    private final void zzb(long j, int i, Object obj) {
        List<zzed> listZzer = zzer();
        synchronized (listZzer) {
            Iterator<zzed> it = listZzer.iterator();
            while (it.hasNext()) {
                if (it.next().zzc(j, i, obj)) {
                    it.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzel() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("castSessionId", this.zzvx);
            jSONObject.put("playerTokenMap", new JSONObject(this.zzvv));
            this.zzvw.edit().putString("save_data", jSONObject.toString()).commit();
        } catch (JSONException e) {
            zzbf.w("Error while saving data: %s", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzem() {
        String string = this.zzvw.getString("save_data", null);
        if (string == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (this.zzvx.equals(jSONObject.getString("castSessionId"))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("playerTokenMap");
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    this.zzvv.put(next, jSONObject2.getString(next));
                }
                this.zzwe = 0L;
            }
        } catch (JSONException e) {
            zzbf.w("Error while loading data: %s", e.getMessage());
        }
    }

    static /* synthetic */ zzco zza(zzcb zzcbVar, zzco zzcoVar) {
        zzcbVar.zzvy = null;
        return null;
    }
}
