package ly.count.android.sdk;

import android.app.Activity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleAPM extends ModuleBase {
    static final String[] reservedKeys = {"response_time", "response_payload_size", "response_code", "request_payload_size", "duration", "slow_rendering_frames", "frozen_frames"};
    int activitiesOpen;
    Apm apmInterface;
    long appStartTimestamp;
    Map<String, Long> codeTraces;
    boolean hasFirstOnResumeHappened;
    long lastScreenSwitchTime;
    boolean manualForegroundBackgroundTriggers;
    boolean manualOverrideInForeground;
    Map<String, Long> networkTraces;
    boolean useManualAppLoadedTrigger;

    ModuleAPM(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.apmInterface = null;
        this.hasFirstOnResumeHappened = false;
        this.lastScreenSwitchTime = -1L;
        this.useManualAppLoadedTrigger = false;
        this.manualForegroundBackgroundTriggers = false;
        this.manualOverrideInForeground = false;
        this.L.v("[ModuleAPM] Initialising");
        this.codeTraces = new HashMap();
        this.networkTraces = new HashMap();
        this.activitiesOpen = 0;
        if (this._cly.lifecycleStateAtLeastStarted()) {
            this.L.d("[ModuleAPM] SDK detects that the app is in the foreground. Increasing the activity counter.");
            this.activitiesOpen++;
        }
        this.useManualAppLoadedTrigger = countlyConfig.appLoadedManualTrigger;
        if (countlyConfig.appStartTimestampOverride != null) {
            this.appStartTimestamp = countlyConfig.appStartTimestampOverride.longValue();
            this.L.d("[ModuleAPM] Using app start timestamp override");
        } else {
            this.appStartTimestamp = Countly.applicationStart;
        }
        if (countlyConfig.appLoadedManualTrigger) {
            this.L.d("[ModuleAPM] Using manual app finished loading trigger for app start");
        }
        boolean z = countlyConfig.manualForegroundBackgroundTrigger;
        this.manualForegroundBackgroundTriggers = z;
        if (z) {
            this.L.d("[ModuleAPM] Using manual foreground/background triggers");
        }
        this.apmInterface = new Apm();
    }

    void startTraceInternal(String str) {
        this.L.d("[ModuleAPM] Calling 'startTraceInternal' with key:[" + str + "]");
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleAPM] Provided a invalid trace key");
        } else {
            this.codeTraces.put(str, Long.valueOf(UtilsTime.currentTimestampMs()));
        }
    }

    void endTraceInternal(String str, Map<String, Integer> map) {
        long jCurrentTimestampMs = UtilsTime.currentTimestampMs();
        Long lValueOf = Long.valueOf(jCurrentTimestampMs);
        this.L.d("[ModuleAPM] Calling 'endTraceInternal' with key:[" + str + "]");
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleAPM] Provided a invalid trace key");
            return;
        }
        if (this.codeTraces.containsKey(str)) {
            Long lRemove = this.codeTraces.remove(str);
            if (lRemove == null) {
                this.L.e("[ModuleAPM] endTraceInternal, retrieved 'startTimestamp' is null, dropping trace");
                return;
            }
            lValueOf.getClass();
            Long lValueOf2 = Long.valueOf(jCurrentTimestampMs - lRemove.longValue());
            if (map != null) {
                removeReservedInvalidKeys(map);
            }
            this.requestQueueProvider.sendAPMCustomTrace(validateAndModifyTraceKey(str), lValueOf2, lRemove, lValueOf, customMetricsToString(map));
            return;
        }
        this.L.w("[ModuleAPM] endTraceInternal, trying to end trace which was not started");
    }

    void cancelTraceInternal(String str) {
        this.L.d("[ModuleAPM] Calling 'cancelTraceInternal' with key:[" + str + "]");
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleAPM] Provided a invalid trace key");
            return;
        }
        if (!this.codeTraces.containsKey(str)) {
            this.L.w("[ModuleAPM] no trace with key [" + str + "] found");
            return;
        }
        this.codeTraces.remove(str);
    }

    void cancelAllTracesInternal() {
        this.L.d("[ModuleAPM] Calling 'cancelAllTracesInternal'");
        this.codeTraces.clear();
    }

    static String customMetricsToString(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        if (map == null) {
            return sb.toString();
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            sb.append(",\"");
            sb.append(key);
            sb.append("\":");
            sb.append(value);
        }
        return sb.toString();
    }

    void removeReservedInvalidKeys(Map<String, Integer> map) {
        if (map == null) {
            return;
        }
        for (String str : reservedKeys) {
            map.remove(str);
        }
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> next = it.next();
            String key = next.getKey();
            Integer value = next.getValue();
            if (key == null || key.isEmpty() || value == null) {
                it.remove();
                this.L.w("[ModuleAPM] custom metrics can't contain null or empty key/value");
            } else if (key.length() > 32) {
                it.remove();
                this.L.w("[ModuleAPM] custom metric key can't be longer than 32 characters, skipping entry, [" + key + "]");
            } else {
                if (key.charAt(0) == '$') {
                    this.L.w("[ModuleAPM] custom metric key can't start with '$', it will be removed server side, [" + key + "]");
                }
                if (key.contains(".")) {
                    this.L.w("[ModuleAPM] custom metric key can't contain '.', those will be removed server side, [" + key + "]");
                }
            }
        }
    }

    String validateAndModifyTraceKey(String str) {
        if (str.charAt(0) == '$') {
            this.L.w("[ModuleAPM] validateAndModifyTraceKey, trace keys can't start with '$', it will be removed server side");
        }
        if (str.length() <= 2048) {
            return str;
        }
        String strSubstring = str.substring(0, 2047);
        this.L.w("[ModuleAPM] validateAndModifyTraceKey, trace keys can't be longer than 2048 characters, it will be trimmed down");
        return strSubstring;
    }

    void startNetworkRequestInternal(String str, String str2) {
        this.L.d("[ModuleAPM] Calling 'startNetworkRequestInternal' with key:[" + str + "]");
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleAPM] Provided a invalid trace key");
            return;
        }
        if (str2 == null || str2.isEmpty()) {
            this.L.e("[ModuleAPM] Provided a invalid uniqueId");
            return;
        }
        this.networkTraces.put(str + "|" + str2, Long.valueOf(UtilsTime.currentTimestampMs()));
    }

    void endNetworkRequestInternal(String str, String str2, int i, int i2, int i3) {
        long jCurrentTimestampMs = UtilsTime.currentTimestampMs();
        this.L.d("[ModuleAPM] Calling 'endNetworkRequestInternal' with key:[" + str + "]");
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleAPM] Provided a invalid trace key");
            return;
        }
        if (str2 == null || str2.isEmpty()) {
            this.L.e("[ModuleAPM] Provided a invalid uniqueId");
            return;
        }
        String str3 = str + "|" + str2;
        if (this.networkTraces.containsKey(str3)) {
            Long lRemove = this.networkTraces.remove(str3);
            if (lRemove == null) {
                this.L.e("[ModuleAPM] endNetworkRequestInternal, retrieved 'startTimestamp' is null");
                return;
            } else {
                recordNetworkRequestInternal(str, i, i2, i3, lRemove.longValue(), jCurrentTimestampMs);
                return;
            }
        }
        this.L.w("[ModuleAPM] endNetworkRequestInternal, trying to end trace which was not started");
    }

    void recordNetworkRequestInternal(String str, int i, int i2, int i3, long j, long j2) {
        int i4;
        long j3;
        long j4;
        int i5 = i;
        this.L.v("[ModuleAPM] Calling 'recordNetworkRequestInternal' with key:[" + str + "]");
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleAPM] Provided a invalid trace key, aborting request");
            return;
        }
        int i6 = 0;
        if (i5 < 100 || i5 >= 600) {
            this.L.e("[ModuleAPM] Invalid response code was provided, setting to '0'");
            i5 = 0;
        }
        if (i2 < 0) {
            this.L.e("[ModuleAPM] Invalid request payload size was provided, setting to '0'");
            i4 = 0;
        } else {
            i4 = i2;
        }
        if (i3 < 0) {
            this.L.e("[ModuleAPM] Invalid response payload size was provided, setting to '0'");
        } else {
            i6 = i3;
        }
        if (j > j2) {
            this.L.e("[ModuleAPM] End timestamp is smaller than start timestamp, switching values");
            j4 = j;
            j3 = j2;
        } else {
            j3 = j;
            j4 = j2;
        }
        this.requestQueueProvider.sendAPMNetworkTrace(validateAndModifyTraceKey(str), Long.valueOf(j4 - j3), i5, i4, i6, Long.valueOf(j3), Long.valueOf(j4));
    }

    void clearNetworkTraces() {
        this.L.v("[ModuleAPM] Calling 'clearNetworkTraces'");
        this.networkTraces.clear();
    }

    void recordAppStart(long j) {
        this.L.d("[ModuleAPM] Calling 'recordAppStart'");
        if (this._cly.config_.recordAppStartTime) {
            long j2 = j - this.appStartTimestamp;
            if (j2 <= 0) {
                this.L.e("[ModuleAPM] Encountered negative app start duration:[" + j2 + "] dropping app start duration request");
                return;
            }
            this.requestQueueProvider.sendAPMAppStart(j2, Long.valueOf(this.appStartTimestamp), Long.valueOf(j));
        }
    }

    void calculateAppRunningTimes(int i, int i2) {
        boolean z = false;
        boolean z2 = i == 1 && i2 == 0;
        if (i == 0 && i2 == 1) {
            z = true;
        }
        this.L.v("[ModuleAPM] calculateAppRunningTimes, toBG[" + z2 + "] toFG[" + z + "]");
        doForegroundBackgroundCalculations(z2, z);
    }

    void doForegroundBackgroundCalculations(boolean z, boolean z2) {
        this.L.d("[ModuleAPM] Calling 'doForegroundBackgroundCalculations', [" + z + "] [" + z2 + "]");
        if (z || z2) {
            long jCurrentTimestampMs = UtilsTime.currentTimestampMs();
            long j = this.lastScreenSwitchTime;
            if (j != -1) {
                long j2 = jCurrentTimestampMs - j;
                if (z2) {
                    this.requestQueueProvider.sendAPMScreenTime(false, j2, Long.valueOf(this.lastScreenSwitchTime), Long.valueOf(jCurrentTimestampMs));
                } else if (z) {
                    this.requestQueueProvider.sendAPMScreenTime(true, j2, Long.valueOf(this.lastScreenSwitchTime), Long.valueOf(jCurrentTimestampMs));
                }
            } else {
                this.L.d("[ModuleAPM] 'doForegroundBackgroundCalculations' last screen switch time was '-1', doing nothing");
            }
            this.lastScreenSwitchTime = jCurrentTimestampMs;
            return;
        }
        this.L.d("[ModuleAPM] Calling 'doForegroundBackgroundCalculations', just changing screens, ignoring request");
    }

    void goToForeground() {
        this.L.d("[ModuleAPM] Calling 'goToForeground'");
        if (this.manualOverrideInForeground) {
            return;
        }
        this.manualOverrideInForeground = true;
        doForegroundBackgroundCalculations(false, true);
    }

    void goToBackground() {
        this.L.d("[ModuleAPM] Calling 'goToBackground'");
        if (this.manualOverrideInForeground) {
            this.manualOverrideInForeground = false;
            doForegroundBackgroundCalculations(true, false);
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        this.codeTraces = null;
        this.networkTraces = null;
    }

    @Override // ly.count.android.sdk.ModuleBase
    void callbackOnActivityResumed(Activity activity) {
        this.L.d("[Apm] Calling 'callbackOnActivityResumed', [" + this.activitiesOpen + "] -> [" + (this.activitiesOpen + 1) + "]");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!this.manualForegroundBackgroundTriggers) {
            int i = this.activitiesOpen;
            calculateAppRunningTimes(i, i + 1);
        }
        this.activitiesOpen++;
        if (this.hasFirstOnResumeHappened) {
            return;
        }
        this.hasFirstOnResumeHappened = true;
        if (this.useManualAppLoadedTrigger) {
            return;
        }
        recordAppStart(jCurrentTimeMillis);
    }

    @Override // ly.count.android.sdk.ModuleBase
    void callbackOnActivityStopped(Activity activity) {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[Apm] Calling 'callbackOnActivityStopped', [");
        sb.append(this.activitiesOpen);
        sb.append("] -> [");
        sb.append(this.activitiesOpen - 1);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (!this.manualForegroundBackgroundTriggers) {
            int i = this.activitiesOpen;
            calculateAppRunningTimes(i, i - 1);
        }
        this.activitiesOpen--;
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onConsentChanged(List<String> list, boolean z, ModuleConsent.ConsentChangeSource consentChangeSource) {
        if (!list.contains(Countly.CountlyFeatureNames.apm) || z) {
            return;
        }
        this._cly.moduleAPM.clearNetworkTraces();
        this._cly.moduleAPM.cancelAllTracesInternal();
    }

    public class Apm {
        public Apm() {
        }

        public void startTrace(String str) {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'startTrace' with key:[" + str + "]");
                ModuleAPM.this.startTraceInternal(str);
            }
        }

        public void endTrace(String str, Map<String, Integer> map) {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'endTrace' with key:[" + str + "]");
                ModuleAPM.this.endTraceInternal(str, map);
            }
        }

        public void cancelTrace(String str) {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'cancelTrace' with key:[" + str + "]");
                ModuleAPM.this.cancelTraceInternal(str);
            }
        }

        public void cancelAllTraces() {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'cancelAllTraces'");
                ModuleAPM.this.cancelAllTracesInternal();
                ModuleAPM.this.clearNetworkTraces();
            }
        }

        public void startNetworkRequest(String str, String str2) {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'startNetworkRequest' with key:[" + str + "], uniqueID:[" + str2 + "]");
                ModuleAPM.this.startNetworkRequestInternal(str, str2);
            }
        }

        public void endNetworkRequest(String str, String str2, int i, int i2, int i3) {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'endNetworkRequest' with key:[" + str + "], uniqueID:[" + str2 + "]");
                ModuleAPM.this.endNetworkRequestInternal(str, str2, i, i2, i3);
            }
        }

        public void recordNetworkTrace(String str, int i, int i2, int i3, long j, long j2) {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'recordNetworkTrace' with key:[" + str + "]");
                ModuleAPM.this.recordNetworkRequestInternal(str, i, i2, i3, j, j2);
            }
        }

        public void setAppIsLoaded() {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'setAppIsLoaded'");
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (!ModuleAPM.this.useManualAppLoadedTrigger) {
                    ModuleAPM.this.L.w("[Apm] trying to record that app has finished loading without enabling manual trigger");
                } else {
                    ModuleAPM.this.recordAppStart(jCurrentTimeMillis);
                }
            }
        }

        public void triggerForeground() {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'triggerForeground'");
                if (!ModuleAPM.this.manualForegroundBackgroundTriggers) {
                    ModuleAPM.this.L.w("[Apm] trying to use manual foreground triggers without enabling them");
                } else {
                    ModuleAPM.this.goToForeground();
                }
            }
        }

        public void triggerBackground() {
            synchronized (ModuleAPM.this._cly) {
                ModuleAPM.this.L.i("[Apm] Calling 'triggerBackground'");
                if (!ModuleAPM.this.manualForegroundBackgroundTriggers) {
                    ModuleAPM.this.L.w("[Apm] trying to use manual background triggers without enabling them");
                } else {
                    ModuleAPM.this.goToBackground();
                }
            }
        }
    }
}
