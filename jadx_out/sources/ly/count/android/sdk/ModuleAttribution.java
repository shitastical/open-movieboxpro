package ly.count.android.sdk;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleAttribution extends ModuleBase {
    Attribution attributionInterface;

    ModuleAttribution(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.L.v("[ModuleAttribution] Initialising");
        this.attributionInterface = new Attribution();
    }

    void recordDirectAttributionInternal(String str, String str2) {
        this.L.d("[ModuleAttribution] recordDirectAttributionInternal, campaign id:[" + str + "], user id:[" + str2 + "]");
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleAttribution] recordDirectAttributionInternal, provided campaign type value is not valid. Execution will be aborted.");
            return;
        }
        if (str2 == null || str2.isEmpty()) {
            this.L.e("[ModuleAttribution] recordDirectAttributionInternal, provided campaign data value is not valid. Execution will be aborted.");
            return;
        }
        if (!str.equals("countly") && !str.equals("_special_test")) {
            this.L.w("[ModuleAttribution] recordDirectAttributionInternal, recording direct attribution with a type other than 'countly' is currently not supported. Execution will be aborted.");
            return;
        }
        if (str.equals("_special_test")) {
            reportSpecialTestAttribution(str2);
        }
        if (str.equals("countly")) {
            reportLegacyInstallAttribution(str2);
        }
    }

    void reportLegacyInstallAttribution(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(CmcdConfiguration.KEY_CONTENT_ID)) {
                this.L.e("[ModuleAttribution] recordDirectAttributionInternal, direct attribution can't be recorded because the data does not contain the 'cid' value. Execution will be aborted.");
                return;
            }
            try {
                String string = jSONObject.getString(CmcdConfiguration.KEY_CONTENT_ID);
                if (string.isEmpty()) {
                    this.L.e("[ModuleAttribution] recordDirectAttributionInternal, 'cid' value can't be empty string. Execution will be aborted.");
                    return;
                }
                try {
                    String str2 = null;
                    if (jSONObject.has("cuid")) {
                        String string2 = jSONObject.getString("cuid");
                        if (string2.isEmpty()) {
                            this.L.w("[ModuleAttribution] recordDirectAttributionInternal, 'cuid' value can't be empty string. value will be ignored.");
                        } else {
                            str2 = string2;
                        }
                    }
                    this.requestQueueProvider.sendDirectAttributionLegacy(string, str2);
                } catch (JSONException unused) {
                    this.L.e("[ModuleAttribution] recordDirectAttributionInternal, encountered issue while accessing 'cuid'. Execution will be aborted.");
                }
            } catch (JSONException unused2) {
                this.L.e("[ModuleAttribution] recordDirectAttributionInternal, encountered issue while accessing 'cid'. Execution will be aborted.");
            }
        } catch (JSONException unused3) {
            this.L.e("[ModuleAttribution] recordDirectAttributionInternal, recording direct attribution data is not in the correct format. Execution will be aborted.");
        }
    }

    void reportSpecialTestAttribution(String str) {
        this.requestQueueProvider.sendDirectAttributionTest(str);
    }

    void recordIndirectAttributionInternal(Map<String, String> map) {
        this.L.d("[ModuleAttribution] recordIndirectAttributionInternal, attribution id:[" + map + "]");
        if (map == null || map.isEmpty()) {
            this.L.e("[ModuleAttribution] recordIndirectAttributionInternal, provided id values are not valid. Execution will be aborted.");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key == null || key.isEmpty()) {
                this.L.e("[ModuleAttribution] recordIndirectAttributionInternal, provided key is not valid [" + key + "].");
            } else if (value == null || value.isEmpty()) {
                this.L.e("[ModuleAttribution] recordIndirectAttributionInternal, for the key[" + key + "] the provided value is not valid [" + value + "].");
            } else {
                try {
                    jSONObject.putOpt(key, value);
                } catch (JSONException e) {
                    this.L.e("[ModuleAttribution] recordIndirectAttributionInternal, an issue happened while trying to add a value: " + e.toString());
                }
            }
        }
        if (jSONObject.length() == 0) {
            this.L.e("[ModuleAttribution] recordIndirectAttributionInternal, no valid attribution values were provided");
        } else {
            this.requestQueueProvider.sendIndirectAttribution(jSONObject.toString());
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (countlyConfig.iaAttributionValues != null) {
            if (countlyConfig.iaAttributionValues.isEmpty()) {
                this.L.e("[ModuleAttribution] provided attribution ID for indirect attribution is empty string.");
            } else {
                recordIndirectAttributionInternal(countlyConfig.iaAttributionValues);
            }
        }
        if (countlyConfig.daCampaignData == null && countlyConfig.daCampaignType == null) {
            return;
        }
        if (countlyConfig.daCampaignType == null || countlyConfig.daCampaignType.isEmpty()) {
            this.L.e("[ModuleAttribution] Can't record direct attribution can't be recorded with an invalid campaign id.");
            return;
        }
        if (countlyConfig.daCampaignData != null && countlyConfig.daCampaignData.isEmpty()) {
            this.L.e("[ModuleAttribution] For direct attribution the provided Campaign user ID can't be empty string.");
        }
        recordDirectAttributionInternal(countlyConfig.daCampaignType, countlyConfig.daCampaignData);
    }

    @Override // ly.count.android.sdk.ModuleBase
    public void halt() {
        this.attributionInterface = null;
    }

    public class Attribution {
        public Attribution() {
        }

        public void recordDirectAttribution(String str, String str2) {
            synchronized (ModuleAttribution.this._cly) {
                ModuleAttribution.this.L.i("[Attribution] calling 'recordCampaign'");
                ModuleAttribution.this.recordDirectAttributionInternal(str, str2);
            }
        }

        public void recordIndirectAttribution(Map<String, String> map) {
            synchronized (ModuleAttribution.this._cly) {
                ModuleAttribution.this.L.i("[Attribution] calling 'recordIndirectAttribution'");
                ModuleAttribution.this.recordIndirectAttributionInternal(map);
            }
        }
    }
}
