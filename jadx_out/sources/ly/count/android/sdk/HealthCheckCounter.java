package ly.count.android.sdk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class HealthCheckCounter implements HealthTracker {
    ModuleLog L;
    StorageProvider storageProvider;
    public long countLogWarning = 0;
    public long countLogError = 0;
    public int statusCode = -1;
    public String errorMessage = "";
    final String keyLogError = "LErr";
    final String keyLogWarning = "LWar";
    final String keyStatusCode = "RStatC";
    final String keyErrorMessage = "REMsg";
    final String requestKeyErrorCount = "el";
    final String requestKeyWarningCount = "wl";
    final String requestKeyStatusCode = "sc";
    final String requestKeyRequestError = "em";

    public HealthCheckCounter(StorageProvider storageProvider, ModuleLog moduleLog) {
        this.L = moduleLog;
        this.storageProvider = storageProvider;
        setupInitialCounters(storageProvider.getHealthCheckCounterState());
    }

    void setupInitialCounters(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.countLogWarning = jSONObject.optLong("LWar", 0L);
            this.countLogError = jSONObject.optLong("LErr", 0L);
            this.statusCode = jSONObject.optInt("RStatC", -1);
            this.errorMessage = jSONObject.optString("REMsg", "");
            this.L.d("[HealthCheckCounter] Loaded initial health check state: [" + jSONObject.toString() + "]");
        } catch (Exception e) {
            clearAndSave();
            this.L.w("[HealthCheckCounter] Failed to read initial state, " + e);
        }
    }

    @Override // ly.count.android.sdk.HealthTracker
    public void logWarning() {
        this.countLogWarning++;
    }

    @Override // ly.count.android.sdk.HealthTracker
    public void logError() {
        this.countLogError++;
    }

    @Override // ly.count.android.sdk.HealthTracker
    public void logFailedNetworkRequest(int i, String str) {
        this.statusCode = i;
        if (str.length() > 1000) {
            this.errorMessage = str.substring(0, 1000);
        } else {
            this.errorMessage = str;
        }
        saveState();
    }

    @Override // ly.count.android.sdk.HealthTracker
    public void clearAndSave() {
        clearValues();
        this.storageProvider.setHealthCheckCounterState("");
    }

    @Override // ly.count.android.sdk.HealthTracker
    public void saveState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("LWar", this.countLogWarning);
            jSONObject.put("LErr", this.countLogError);
            jSONObject.put("RStatC", this.statusCode);
            jSONObject.put("REMsg", this.errorMessage);
            this.storageProvider.setHealthCheckCounterState(jSONObject.toString());
        } catch (Exception e) {
            this.L.w("[HealthCheckCounter] Failed to save current state, " + e);
        }
    }

    void clearValues() {
        this.L.v("[HealthCheckCounter] Clearing counters");
        this.countLogWarning = 0L;
        this.countLogError = 0L;
        this.statusCode = -1;
        this.errorMessage = "";
    }

    String createRequestParam() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("&hc=");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("el", this.countLogError);
            jSONObject.put("wl", this.countLogWarning);
            jSONObject.put("sc", this.statusCode);
            jSONObject.put("em", this.errorMessage);
        } catch (JSONException e) {
            this.L.w("[HealthCheckCounter] Failed to create param for hc request, " + e);
        }
        String string = jSONObject.toString();
        try {
            string = URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            Countly.sharedInstance().L.e("[getMetrics] encode failed, [" + e2 + "]");
        }
        sb.append(string);
        return sb.toString();
    }
}
