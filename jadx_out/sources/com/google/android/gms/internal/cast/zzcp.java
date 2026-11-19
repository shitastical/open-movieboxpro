package com.google.android.gms.internal.cast;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class zzcp {
    private static final zzdw zzbf = new zzdw("GameManagerMessage");
    protected final int zzwy;
    protected final int zzwz;
    protected final String zzxa;
    protected final JSONObject zzxb;
    protected final int zzxc;
    protected final int zzxd;
    protected final List<zzcs> zzxe;
    protected final JSONObject zzxf;
    protected final String zzxg;
    protected final String zzxh;
    protected final long zzxi;
    protected final String zzxj;
    protected final zzco zzxk;

    private zzcp(int i, int i2, String str, JSONObject jSONObject, int i3, int i4, List<zzcs> list, JSONObject jSONObject2, String str2, String str3, long j, String str4, zzco zzcoVar) {
        this.zzwy = i;
        this.zzwz = i2;
        this.zzxa = str;
        this.zzxb = jSONObject;
        this.zzxc = i3;
        this.zzxd = i4;
        this.zzxe = list;
        this.zzxf = jSONObject2;
        this.zzxg = str2;
        this.zzxh = str3;
        this.zzxi = j;
        this.zzxj = str4;
        this.zzxk = zzcoVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v4 */
    protected static zzcp zzj(JSONObject jSONObject) {
        int iOptInt = jSONObject.optInt("type", -1);
        int i = "playerId";
        try {
            try {
                if (iOptInt == 1) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("gameManagerConfig");
                    return new zzcp(iOptInt, jSONObject.optInt("statusCode"), jSONObject.optString("errorDescription"), jSONObject.optJSONObject("extraMessageData"), jSONObject.optInt("gameplayState"), jSONObject.optInt("lobbyState"), zza(jSONObject.optJSONArray("players")), jSONObject.optJSONObject("gameData"), jSONObject.optString("gameStatusText"), jSONObject.optString("playerId"), jSONObject.optLong("requestId"), jSONObject.optString("playerToken"), jSONObjectOptJSONObject != null ? new zzco(jSONObjectOptJSONObject) : null);
                }
                if (iOptInt == 2) {
                    return new zzcp(iOptInt, jSONObject.optInt("statusCode"), jSONObject.optString("errorDescription"), jSONObject.optJSONObject("extraMessageData"), jSONObject.optInt("gameplayState"), jSONObject.optInt("lobbyState"), zza(jSONObject.optJSONArray("players")), jSONObject.optJSONObject("gameData"), jSONObject.optString("gameStatusText"), jSONObject.optString("playerId"), -1L, null, null);
                }
                zzbf.w("Unrecognized Game Message type %d", Integer.valueOf(iOptInt));
                return null;
            } catch (JSONException e) {
                e = e;
                i = 0;
                zzbf.zzb(e, "Exception while parsing GameManagerMessage from json", new Object[i]);
            }
        } catch (JSONException e2) {
            e = e2;
            zzbf.zzb(e, "Exception while parsing GameManagerMessage from json", new Object[i]);
        }
    }

    private static List<zzcs> zza(JSONArray jSONArray) {
        zzcs zzcsVar;
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                try {
                    zzcsVar = new zzcs(jSONObjectOptJSONObject);
                } catch (JSONException e) {
                    zzbf.zzb(e, "Exception when attempting to parse PlayerInfoMessageComponent at index %d", Integer.valueOf(i));
                    zzcsVar = null;
                }
                if (zzcsVar != null) {
                    arrayList.add(zzcsVar);
                }
            }
        }
        return arrayList;
    }
}
