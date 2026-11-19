package ly.count.android.sdk;

import android.content.SharedPreferences;
import android.provider.Settings;
import java.util.UUID;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleDeviceId extends ModuleBase implements OpenUDIDProvider, DeviceIdProvider {
    public static final String PREFS_NAME = "openudid_prefs";
    public static final String PREF_KEY = "openudid";
    boolean cleanupTempIdAfterInit;
    ly.count.android.sdk.DeviceId deviceIdInstance;
    DeviceId deviceIdInterface;
    boolean exitTempIdAfterInit;

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
    }

    ModuleDeviceId(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.exitTempIdAfterInit = false;
        this.cleanupTempIdAfterInit = false;
        this.L.v("[ModuleDeviceId] Initialising");
        boolean z = countlyConfig.deviceID != null;
        if (countlyConfig.temporaryDeviceIdEnabled && !z) {
            countlyConfig.deviceID = "CLYTemporaryDeviceID";
        }
        this.deviceIdInstance = new ly.count.android.sdk.DeviceId(countlyConfig.deviceID, countlyConfig.storageProvider, this.L, this);
        countlyConfig.deviceIdProvider = this;
        boolean zIsTemporaryIdModeEnabled = this.deviceIdInstance.isTemporaryIdModeEnabled();
        this.L.d("[ModuleDeviceId] [TemporaryDeviceId] Temp ID should be enabled[" + countlyConfig.temporaryDeviceIdEnabled + "] Currently enabled: [" + zIsTemporaryIdModeEnabled + "]");
        if (zIsTemporaryIdModeEnabled && z) {
            this.L.d("[ModuleDeviceId] [TemporaryDeviceId] Decided we have to exit temporary device ID mode, mode enabled: [" + countlyConfig.temporaryDeviceIdEnabled + "], custom Device ID Set: [" + z + "]");
            this.exitTempIdAfterInit = true;
        } else if (!zIsTemporaryIdModeEnabled) {
            this.cleanupTempIdAfterInit = true;
        }
        this.deviceIdInterface = new DeviceId();
    }

    void replaceTempIDWithRealIDinRQ(String str) {
        String[] requests = this.storageProvider.getRequests();
        String str2 = "&device_id=" + str;
        boolean z = false;
        for (int i = 0; i < requests.length; i++) {
            if (requests[i].contains("&device_id=CLYTemporaryDeviceID")) {
                this.L.d("[ModuleDeviceId] [exitTemporaryIdMode] Found a tag to replace in: [" + requests[i] + "]");
                requests[i] = requests[i].replace("&device_id=CLYTemporaryDeviceID", str2);
                z = true;
            }
        }
        if (z) {
            this.storageProvider.replaceRequests(requests);
        }
    }

    void exitTemporaryIdMode(String str) {
        this.L.d("[ModuleDeviceId] Calling exitTemporaryIdMode");
        if (!this._cly.isInitialized()) {
            throw new IllegalStateException("init must be called before exitTemporaryIdMode");
        }
        this.deviceIdInstance.changeToCustomId(str);
        replaceTempIDWithRealIDinRQ(str);
        this._cly.moduleRemoteConfig.RCAutomaticDownloadTrigger(false);
        this._cly.requestQueue().attemptToSendStoredRequests();
    }

    void changeDeviceIdWithoutMergeInternal(String str) {
        if (isTemporaryIdEnabled() && str.equals("CLYTemporaryDeviceID")) {
            return;
        }
        if (this.deviceIdInstance.getCurrentId().equals(str)) {
            this.L.w("[ModuleDeviceId] changeDeviceIdWithoutMergeInternal, We are attempting to change the device ID to the same ID, request will be ignored");
            return;
        }
        if (isTemporaryIdEnabled() || this.requestQueueProvider.queueContainsTemporaryIdItems()) {
            exitTemporaryIdMode(str);
            return;
        }
        this._cly.moduleRequestQueue.sendEventsIfNeeded(true);
        this._cly.moduleRemoteConfig.clearAndDownloadAfterIdChange(true);
        this._cly.moduleSessions.endSessionInternal(getDeviceId());
        this._cly.moduleConsent.removeConsentAllInternal(ModuleConsent.ConsentChangeSource.DeviceIDChangedNotMerged);
        if (str.equals("CLYTemporaryDeviceID")) {
            this.deviceIdInstance.enterTempIDMode();
        } else {
            this.deviceIdInstance.changeToCustomId(str);
        }
        this._cly.moduleRatings.clearAutomaticStarRatingSessionCountInternal();
    }

    void changeDeviceIdWithMergeInternal(String str) {
        if ("".equals(str)) {
            this.L.e("changeDeviceIdWithMergeInternal, provided device ID can't be empty string");
            return;
        }
        if (isTemporaryIdEnabled() || this.requestQueueProvider.queueContainsTemporaryIdItems()) {
            if (str.equals("CLYTemporaryDeviceID")) {
                this.L.w("[ModuleDeviceId, changeDeviceId] About to enter temporary ID mode when already in it");
                return;
            } else {
                exitTemporaryIdMode(str);
                return;
            }
        }
        this._cly.moduleRemoteConfig.clearAndDownloadAfterIdChange(false);
        this.requestQueueProvider.changeDeviceId(str, this._cly.moduleSessions.roundedSecondsSinceLastSessionDurationUpdate());
    }

    @Override // ly.count.android.sdk.ModuleBase
    public void initFinished(CountlyConfig countlyConfig) {
        if (this.exitTempIdAfterInit) {
            this.L.i("[ModuleDeviceId, initFinished] Exiting temp ID at the end of init");
            exitTemporaryIdMode(countlyConfig.deviceID);
            return;
        }
        if (this.cleanupTempIdAfterInit) {
            this.L.i("[ModuleDeviceId, initFinished] Cleaning up potentially left temp ID requests in queue");
            String deviceId = getDeviceId();
            if (deviceId != null && !deviceId.isEmpty()) {
                replaceTempIDWithRealIDinRQ(deviceId);
                return;
            }
            this.L.w("[ModuleDeviceId, initFinished] Can't cleanup RQ, device ID is either null or empty [" + deviceId + "]");
        }
    }

    @Override // ly.count.android.sdk.OpenUDIDProvider
    public String getOpenUDID() {
        SharedPreferences sharedPreferences = this._cly.context_.getSharedPreferences(PREFS_NAME, 0);
        String string = sharedPreferences.getString(PREF_KEY, null);
        if (string == null) {
            Countly.sharedInstance().L.d("[OpenUDID] Generating openUDID");
            string = Settings.Secure.getString(this._cly.context_.getContentResolver(), "android_id");
            if (string == null || string.equals("9774d56d682e549c") || string.length() < 15) {
                string = UUID.randomUUID().toString();
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(PREF_KEY, string);
            editorEdit.apply();
        }
        Countly.sharedInstance().L.d("[OpenUDID] ID: " + string);
        return string;
    }

    @Override // ly.count.android.sdk.DeviceIdProvider
    public String getDeviceId() {
        return this.deviceIdInstance.getCurrentId();
    }

    @Override // ly.count.android.sdk.DeviceIdProvider
    public boolean isTemporaryIdEnabled() {
        return this.deviceIdInstance.isTemporaryIdModeEnabled();
    }

    @Override // ly.count.android.sdk.DeviceIdProvider
    public ly.count.android.sdk.DeviceId getDeviceIdInstance() {
        return this.deviceIdInstance;
    }

    public class DeviceId {
        public DeviceId() {
        }

        public void changeWithoutMerge(String str) {
            synchronized (ModuleDeviceId.this._cly) {
                ModuleDeviceId.this.L.d("[DeviceId] Calling 'changeDeviceIdWithoutMerge'");
                if (str == null) {
                    ModuleDeviceId.this.L.e("[DeviceId] changeDeviceIdWithoutMerge, provided device ID value was 'null'. Request will be ignored");
                } else {
                    ModuleDeviceId.this.changeDeviceIdWithoutMergeInternal(str);
                }
            }
        }

        public void changeWithMerge(String str) {
            synchronized (ModuleDeviceId.this._cly) {
                ModuleDeviceId.this.L.d("[DeviceId] Calling 'changeDeviceIdWithMerge'");
                if (str == null) {
                    ModuleDeviceId.this.L.e("[DeviceId] changeDeviceIdWithMerge, provided device ID value was 'null'. Request will be ignored");
                } else {
                    ModuleDeviceId.this.changeDeviceIdWithMergeInternal(str);
                }
            }
        }

        public String getID() {
            String deviceId;
            synchronized (ModuleDeviceId.this._cly) {
                ModuleDeviceId.this.L.d("[DeviceId] Calling 'getDeviceID'");
                deviceId = ModuleDeviceId.this.getDeviceId();
            }
            return deviceId;
        }

        public DeviceIdType getType() {
            DeviceIdType type;
            synchronized (ModuleDeviceId.this._cly) {
                ModuleDeviceId.this.L.d("[DeviceId] Calling 'getDeviceIDType'");
                type = ModuleDeviceId.this.deviceIdInstance.getType();
            }
            return type;
        }

        public void enableTemporaryIdMode() {
            synchronized (ModuleDeviceId.this._cly) {
                ModuleDeviceId.this.L.i("[DeviceId] Calling 'enableTemporaryIdMode'");
                ModuleDeviceId.this.changeDeviceIdWithoutMergeInternal("CLYTemporaryDeviceID");
            }
        }
    }
}
