package ly.count.android.sdk.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ly.count.android.sdk.Countly;
import ly.count.android.sdk.ModuleLog;
import ly.count.android.sdk.RCData;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class RemoteConfigHelper {
    public static Map<String, RCData> DownloadedValuesIntoMap(JSONObject jSONObject) {
        HashMap map = new HashMap();
        if (jSONObject == null) {
            return map;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                map.put(next, new RCData(jSONObject.get(next), true));
            } catch (Exception unused) {
                Countly.sharedInstance().L.e("[RemoteConfigValueStore] Failed merging new remote config values");
            }
        }
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0023  */
    /* JADX WARN: Code duplicated, block: B:17:0x0030 A[Catch: Exception -> 0x0021, LOOP:0: B:16:0x002e->B:17:0x0030, LOOP_END, TryCatch #0 {Exception -> 0x0021, blocks: (B:4:0x0006, B:6:0x0009, B:8:0x0012, B:9:0x001a, B:13:0x0025, B:15:0x0028, B:17:0x0030, B:18:0x0038), top: B:22:0x0006 }] */
    public static String[] prepareKeysIncludeExclude(String[] strArr, String[] strArr2, ModuleLog moduleLog) {
        JSONArray jSONArray;
        String[] strArr3 = new String[2];
        if (strArr != null) {
            try {
                if (strArr.length > 0) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (String str : strArr) {
                        jSONArray2.put(str);
                    }
                    strArr3[0] = jSONArray2.toString();
                } else if (strArr2 != null && strArr2.length > 0) {
                    jSONArray = new JSONArray();
                    for (String str2 : strArr2) {
                        jSONArray.put(str2);
                    }
                    strArr3[1] = jSONArray.toString();
                }
            } catch (Exception e) {
                moduleLog.e("[ModuleRemoteConfig] prepareKeysIncludeExclude, Failed at preparing keys, [" + e.toString() + "]");
            }
        } else if (strArr2 != null) {
            jSONArray = new JSONArray();
            while (i < r2) {
                jSONArray.put(str2);
            }
            strArr3[1] = jSONArray.toString();
        }
        return strArr3;
    }

    public static Map<String, String[]> convertVariantsJsonToMap(JSONObject jSONObject, ModuleLog moduleLog) {
        HashMap map = new HashMap();
        JSONArray jSONArrayNames = jSONObject.names();
        if (jSONArrayNames == null) {
            return map;
        }
        ArrayList arrayList = new ArrayList(5);
        for (int i = 0; i < jSONArrayNames.length(); i++) {
            try {
                String string = jSONArrayNames.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONArray) {
                    arrayList.clear();
                    JSONArray jSONArray = (JSONArray) obj;
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null && !jSONObjectOptJSONObject.isNull("name")) {
                            arrayList.add(jSONObjectOptJSONObject.optString("name"));
                        }
                    }
                    map.put(string, (String[]) arrayList.toArray(new String[0]));
                }
            } catch (Exception e) {
                moduleLog.e("[ModuleRemoteConfig] convertVariantsJsonToMap, failed parsing:[" + e.toString() + "]");
                return new HashMap();
            }
        }
        return map;
    }
}
