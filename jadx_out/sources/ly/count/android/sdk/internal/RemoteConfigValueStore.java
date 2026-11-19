package ly.count.android.sdk.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ly.count.android.sdk.Countly;
import ly.count.android.sdk.RCData;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class RemoteConfigValueStore {
    public static final int cacheValCached = 0;
    public static final int cacheValFresh = 1;
    public static final String keyCacheFlag = "c";
    public static final String keyValue = "v";
    public JSONObject values;
    public boolean valuesCanBeCached;

    public void cacheClearValues() {
        if (!this.valuesCanBeCached) {
            clearValues();
            return;
        }
        Iterator<String> itKeys = this.values.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObjectOptJSONObject = this.values.optJSONObject(next);
            if (jSONObjectOptJSONObject == null) {
                Object objOpt = this.values.opt(next);
                Countly.sharedInstance().L.w("[RemoteConfigValueStore] cacheClearValues, stored entry was not a JSON object, key:[" + next + "] value:[" + objOpt + "]");
            } else {
                try {
                    jSONObjectOptJSONObject.put(keyCacheFlag, 0);
                    this.values.put(next, jSONObjectOptJSONObject);
                } catch (Exception e) {
                    Countly.sharedInstance().L.e("[RemoteConfigValueStore] cacheClearValues, Failed caching remote config values, " + e);
                }
            }
        }
    }

    public void clearValues() {
        this.values = new JSONObject();
    }

    public void mergeValues(Map<String, RCData> map, boolean z) {
        Countly.sharedInstance().L.v("[RemoteConfigValueStore] mergeValues, stored values C:" + this.values.length() + "provided values C:" + map.size());
        if (z) {
            clearValues();
        }
        for (Map.Entry<String, RCData> entry : map.entrySet()) {
            String key = entry.getKey();
            Object obj = entry.getValue().value;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("v", obj);
                jSONObject.put(keyCacheFlag, 1);
                this.values.put(key, jSONObject);
            } catch (Exception unused) {
                Countly.sharedInstance().L.e("[RemoteConfigValueStore] Failed merging remote config values");
            }
        }
        Countly.sharedInstance().L.v("[RemoteConfigValueStore] merging done:" + this.values.toString());
    }

    private RemoteConfigValueStore(JSONObject jSONObject, boolean z) {
        this.values = jSONObject;
        this.valuesCanBeCached = z;
    }

    public RCData getValue(String str) {
        boolean z = true;
        RCData rCData = new RCData(null, true);
        try {
            JSONObject jSONObjectOptJSONObject = this.values.optJSONObject(str);
            if (jSONObjectOptJSONObject == null) {
                return rCData;
            }
            rCData.value = jSONObjectOptJSONObject.get("v");
            if (jSONObjectOptJSONObject.getInt(keyCacheFlag) == 0) {
                z = false;
            }
            rCData.isCurrentUsersData = z;
            return rCData;
        } catch (Exception e) {
            Countly.sharedInstance().L.e("[RemoteConfigValueStore] Got JSON exception while calling 'getValue': " + e.toString());
            return rCData;
        }
    }

    public Map<String, RCData> getAllValues() {
        HashMap map = new HashMap();
        Iterator<String> itKeys = this.values.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                JSONObject jSONObjectOptJSONObject = this.values.optJSONObject(next);
                if (jSONObjectOptJSONObject != null) {
                    map.put(next, new RCData(jSONObjectOptJSONObject.opt("v"), jSONObjectOptJSONObject.getInt(keyCacheFlag) != 0));
                }
            } catch (Exception e) {
                Countly.sharedInstance().L.e("[RemoteConfigValueStore] Got JSON exception while calling 'getAllValues': " + e.toString());
            }
        }
        return map;
    }

    public Object getValueLegacy(String str) {
        JSONObject jSONObjectOptJSONObject = this.values.optJSONObject(str);
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        return jSONObjectOptJSONObject.opt("v");
    }

    public Map<String, Object> getAllValuesLegacy() {
        HashMap map = new HashMap();
        Iterator<String> itKeys = this.values.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObjectOptJSONObject = this.values.optJSONObject(next);
            if (jSONObjectOptJSONObject == null) {
                Countly.sharedInstance().L.e("[RemoteConfigValueStore] getAllValuesLegacy, inner object seems to be 'null', key:[" + next + "]");
            } else {
                Object objOpt = jSONObjectOptJSONObject.opt("v");
                if (objOpt == null) {
                    Countly.sharedInstance().L.e("[RemoteConfigValueStore] getAllValuesLegacy, inner value seems to be 'null', key:[" + next + "]");
                } else {
                    map.put(next, objOpt);
                }
            }
        }
        return map;
    }

    public static RemoteConfigValueStore dataFromString(String str, boolean z) {
        JSONObject jSONObject;
        if (str == null || str.isEmpty()) {
            return new RemoteConfigValueStore(new JSONObject(), z);
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            Countly.sharedInstance().L.e("[RemoteConfigValueStore] Couldn't decode RemoteConfigValueStore successfully: " + e.toString());
            jSONObject = new JSONObject();
        }
        return new RemoteConfigValueStore(jSONObject, z);
    }

    public String dataToString() {
        return this.values.toString();
    }
}
