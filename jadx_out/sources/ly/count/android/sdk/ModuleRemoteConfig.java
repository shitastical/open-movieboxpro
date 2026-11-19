package ly.count.android.sdk;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ly.count.android.sdk.internal.RemoteConfigHelper;
import ly.count.android.sdk.internal.RemoteConfigValueStore;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleRemoteConfig extends ModuleBase {
    public static final String variantObjectNameKey = "name";
    boolean autoEnrollEnabled;
    boolean automaticDownloadTriggersEnabled;
    List<RCDownloadCallback> downloadCallbacks;
    ImmediateRequestGenerator iRGenerator;
    Map<String, String> metricOverride;
    RemoteConfig remoteConfigInterface;
    boolean remoteConfigValuesShouldBeCached;
    boolean updateRemoteConfigAfterIdChange;
    Map<String, String[]> variantContainer;

    ModuleRemoteConfig(Countly countly, final CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.updateRemoteConfigAfterIdChange = false;
        this.variantContainer = new HashMap();
        this.remoteConfigInterface = null;
        this.remoteConfigValuesShouldBeCached = false;
        this.downloadCallbacks = new ArrayList(2);
        this.metricOverride = null;
        this.L.v("[ModuleRemoteConfig] Initialising");
        this.metricOverride = countlyConfig.metricOverride;
        this.iRGenerator = countlyConfig.immediateRequestGenerator;
        this.L.d("[ModuleRemoteConfig] Setting if remote config Automatic triggers enabled, " + countlyConfig.enableRemoteConfigAutomaticDownloadTriggers + ", caching enabled: " + countlyConfig.enableRemoteConfigValueCaching + ", auto enroll enabled: " + countlyConfig.enableAutoEnrollFlag);
        this.automaticDownloadTriggersEnabled = countlyConfig.enableRemoteConfigAutomaticDownloadTriggers;
        this.remoteConfigValuesShouldBeCached = countlyConfig.enableRemoteConfigValueCaching;
        this.autoEnrollEnabled = countlyConfig.enableAutoEnrollFlag;
        this.downloadCallbacks.addAll(countlyConfig.remoteConfigGlobalCallbackList);
        if (countlyConfig.remoteConfigCallbackLegacy != null) {
            this.downloadCallbacks.add(new RCDownloadCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$$ExternalSyntheticLambda2
                @Override // ly.count.android.sdk.RCDownloadCallback
                public final void callback(RequestResult requestResult, String str, boolean z, Map map) {
                    countlyConfig.remoteConfigCallbackLegacy.callback(str);
                }
            });
        }
        this.remoteConfigInterface = new RemoteConfig();
    }

    void updateRemoteConfigValues(final String[] strArr, final String[] strArr2, boolean z, final RCDownloadCallback rCDownloadCallback) {
        String str;
        String strPrepareRemoteConfigRequest;
        this.L.d("[ModuleRemoteConfig] Updating remote config values, legacyAPI:[" + z + "]");
        String[] strArrPrepareKeysIncludeExclude = RemoteConfigHelper.prepareKeysIncludeExclude(strArr, strArr2, this.L);
        String str2 = strArrPrepareKeysIncludeExclude[0];
        boolean z2 = (str2 == null || str2.length() == 0) && ((str = strArrPrepareKeysIncludeExclude[1]) == null || str.length() == 0);
        try {
            if (this.deviceIdProvider.getDeviceId() == null) {
                this.L.d("[ModuleRemoteConfig] RemoteConfig value update was aborted, deviceID is null");
                NotifyDownloadCallbacks(rCDownloadCallback, RequestResult.Error, "Can't complete call, device ID is null", z2, null);
                return;
            }
            if (!this.deviceIdProvider.isTemporaryIdEnabled() && !this.requestQueueProvider.queueContainsTemporaryIdItems()) {
                String metrics = this.deviceInfo.getMetrics(this._cly.context_, this.metricOverride);
                if (z) {
                    strPrepareRemoteConfigRequest = this.requestQueueProvider.prepareRemoteConfigRequestLegacy(strArrPrepareKeysIncludeExclude[0], strArrPrepareKeysIncludeExclude[1], metrics);
                } else {
                    strPrepareRemoteConfigRequest = this.requestQueueProvider.prepareRemoteConfigRequest(strArrPrepareKeysIncludeExclude[0], strArrPrepareKeysIncludeExclude[1], metrics, this.autoEnrollEnabled);
                }
                String str3 = strPrepareRemoteConfigRequest;
                this.L.d("[ModuleRemoteConfig] RemoteConfig requestData:[" + str3 + "]");
                ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
                final boolean z3 = z2;
                this.iRGenerator.CreateImmediateRequestMaker().doWork(str3, "/o/sdk", connectionProcessorCreateConnectionProcessor, false, connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled(), new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$$ExternalSyntheticLambda3
                    @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
                    public final void callback(JSONObject jSONObject) {
                        this.f$0.m3513xa46dce5c(rCDownloadCallback, z3, strArr2, strArr, jSONObject);
                    }
                }, this.L);
                return;
            }
            this.L.d("[ModuleRemoteConfig] RemoteConfig value update was aborted, temporary device ID mode is set");
            NotifyDownloadCallbacks(rCDownloadCallback, RequestResult.Error, "Can't complete call, temporary device ID is set", z2, null);
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] Encountered internal error while trying to perform a remote config update. " + e.toString());
            NotifyDownloadCallbacks(rCDownloadCallback, RequestResult.Error, "Encountered internal error while trying to perform a remote config update", z2, null);
        }
    }

    /* JADX INFO: renamed from: lambda$updateRemoteConfigValues$1$ly-count-android-sdk-ModuleRemoteConfig, reason: not valid java name */
    /* synthetic */ void m3513xa46dce5c(RCDownloadCallback rCDownloadCallback, boolean z, String[] strArr, String[] strArr2, JSONObject jSONObject) {
        String str;
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleRemoteConfig] Processing remote config received response, received response is null:[");
        sb.append(jSONObject == null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (jSONObject == null) {
            NotifyDownloadCallbacks(rCDownloadCallback, RequestResult.Error, "Encountered problem while trying to reach the server, possibly no internet connection", z, null);
            return;
        }
        Map<String, RCData> mapDownloadedValuesIntoMap = RemoteConfigHelper.DownloadedValuesIntoMap(jSONObject);
        try {
            mergeCheckResponseIntoCurrentValues(strArr == null && strArr2 == null, mapDownloadedValuesIntoMap);
            str = null;
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] updateRemoteConfigValues - execute, Encountered internal issue while trying to download remote config information from the server, [" + e.toString() + "]");
            str = "Encountered internal issue while trying to download remote config information from the server, [" + e.toString() + "]";
        }
        String str2 = str;
        NotifyDownloadCallbacks(rCDownloadCallback, str2 == null ? RequestResult.Success : RequestResult.Error, str2, z, mapDownloadedValuesIntoMap);
    }

    void enrollIntoABTestsForKeysInternal(String[] strArr) {
        this.L.d("[ModuleRemoteConfig] Enrolling user for the given keys:" + strArr);
        if (this.deviceIdProvider.isTemporaryIdEnabled() || this.requestQueueProvider.queueContainsTemporaryIdItems() || this.deviceIdProvider.getDeviceId() == null) {
            this.L.d("[ModuleRemoteConfig] Enrolling user was aborted, temporary device ID mode is set or device ID is null.");
            return;
        }
        String strPrepareEnrollmentParameters = this.requestQueueProvider.prepareEnrollmentParameters(strArr);
        this.L.d("[ModuleRemoteConfig] Enrollment requestData:[" + strPrepareEnrollmentParameters + "]");
        ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
        this.iRGenerator.CreateImmediateRequestMaker().doWork(strPrepareEnrollmentParameters, "/o/sdk", connectionProcessorCreateConnectionProcessor, false, connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled(), new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$$ExternalSyntheticLambda4
            @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
            public final void callback(JSONObject jSONObject) {
                this.f$0.m3509x411df64a(jSONObject);
            }
        }, this.L);
    }

    /* JADX INFO: renamed from: lambda$enrollIntoABTestsForKeysInternal$2$ly-count-android-sdk-ModuleRemoteConfig, reason: not valid java name */
    /* synthetic */ void m3509x411df64a(JSONObject jSONObject) {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleRemoteConfig] Processing received response, received response is null:[");
        sb.append(jSONObject == null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (jSONObject == null) {
            return;
        }
        try {
            if (jSONObject.has("result")) {
                this.L.d("[ModuleRemoteConfig] Assuming that user was enrolled user for the A/B test");
            } else {
                this.L.w("[ModuleRemoteConfig] Encountered a network error while enrolling the user for the A/B test.");
            }
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] Encountered an internal error while trying to enroll the user for A/B test. " + e.toString());
        }
    }

    void exitABTestsForKeysInternal(String[] strArr) {
        this.L.d("[ModuleRemoteConfig] Removing user for the tests with given keys:" + strArr);
        if (this.deviceIdProvider.isTemporaryIdEnabled() || this.requestQueueProvider.queueContainsTemporaryIdItems() || this.deviceIdProvider.getDeviceId() == null) {
            this.L.d("[ModuleRemoteConfig] Removing user from tests was aborted, temporary device ID mode is set or device ID is null.");
            return;
        }
        String strPrepareRemovalParameters = this.requestQueueProvider.prepareRemovalParameters(strArr);
        this.L.d("[ModuleRemoteConfig] Removal requestData:[" + strPrepareRemovalParameters + "]");
        ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
        this.iRGenerator.CreateImmediateRequestMaker().doWork(strPrepareRemovalParameters, "/i", connectionProcessorCreateConnectionProcessor, false, connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled(), new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$$ExternalSyntheticLambda1
            @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
            public final void callback(JSONObject jSONObject) {
                this.f$0.m3510xcd4db403(jSONObject);
            }
        }, this.L);
    }

    /* JADX INFO: renamed from: lambda$exitABTestsForKeysInternal$3$ly-count-android-sdk-ModuleRemoteConfig, reason: not valid java name */
    /* synthetic */ void m3510xcd4db403(JSONObject jSONObject) {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleRemoteConfig] Processing received response, received response is null:[");
        sb.append(jSONObject == null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (jSONObject == null) {
            return;
        }
        try {
            if (jSONObject.has("result") && jSONObject.getString("result").equals("Success")) {
                this.L.d("[ModuleRemoteConfig]  Removed user from the A/B test");
            } else {
                this.L.d("[ModuleRemoteConfig]  Encountered a network error while removing the user from A/B testing.");
            }
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] Encountered an internal error while trying to remove user from A/B testing. " + e.toString());
        }
    }

    void testingFetchVariantInformationInternal(final RCVariantCallback rCVariantCallback) {
        try {
            this.L.d("[ModuleRemoteConfig] Fetching all A/B test variants");
            if (!this.deviceIdProvider.isTemporaryIdEnabled() && !this.requestQueueProvider.queueContainsTemporaryIdItems() && this.deviceIdProvider.getDeviceId() != null) {
                String strPrepareFetchAllVariants = this.requestQueueProvider.prepareFetchAllVariants();
                this.L.d("[ModuleRemoteConfig] Fetching all A/B test variants requestData:[" + strPrepareFetchAllVariants + "]");
                ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
                this.iRGenerator.CreateImmediateRequestMaker().doWork(strPrepareFetchAllVariants, "/o/sdk", connectionProcessorCreateConnectionProcessor, false, connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled(), new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$$ExternalSyntheticLambda5
                    @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
                    public final void callback(JSONObject jSONObject) {
                        this.f$0.m3512x499313a(rCVariantCallback, jSONObject);
                    }
                }, this.L);
                return;
            }
            this.L.d("[ModuleRemoteConfig] Fetching all A/B test variants was aborted, temporary device ID mode is set or device ID is null.");
            rCVariantCallback.callback(RequestResult.Error, "Temporary device ID mode is set or device ID is null.");
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] Encountered internal error while trying to fetch all A/B test variants. " + e.toString());
            rCVariantCallback.callback(RequestResult.Error, "Encountered internal error while trying to fetch all A/B test variants.");
        }
    }

    /* JADX INFO: renamed from: lambda$testingFetchVariantInformationInternal$4$ly-count-android-sdk-ModuleRemoteConfig, reason: not valid java name */
    /* synthetic */ void m3512x499313a(RCVariantCallback rCVariantCallback, JSONObject jSONObject) {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleRemoteConfig] Processing Fetching all A/B test variants received response, received response is null:[");
        sb.append(jSONObject == null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (jSONObject == null) {
            rCVariantCallback.callback(RequestResult.NetworkIssue, "Encountered problem while trying to reach the server, possibly no internet connection");
        } else {
            this.variantContainer = RemoteConfigHelper.convertVariantsJsonToMap(jSONObject, this.L);
            rCVariantCallback.callback(RequestResult.Success, null);
        }
    }

    void testingEnrollIntoVariantInternal(String str, String str2, final RCVariantCallback rCVariantCallback) {
        try {
            this.L.d("[ModuleRemoteConfig] Enrolling A/B test variants, Key/Variant pairs:[" + str + "][" + str2 + "]");
            if (!this.deviceIdProvider.isTemporaryIdEnabled() && !this.requestQueueProvider.queueContainsTemporaryIdItems() && this.deviceIdProvider.getDeviceId() != null) {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    String strPrepareEnrollVariant = this.requestQueueProvider.prepareEnrollVariant(str, str2);
                    this.L.d("[ModuleRemoteConfig] Enrolling A/B test variants requestData:[" + strPrepareEnrollVariant + "]");
                    ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
                    this.iRGenerator.CreateImmediateRequestMaker().doWork(strPrepareEnrollVariant, "/i", connectionProcessorCreateConnectionProcessor, false, connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled(), new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$$ExternalSyntheticLambda0
                        @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
                        public final void callback(JSONObject jSONObject) {
                            this.f$0.m3511x7563a839(rCVariantCallback, jSONObject);
                        }
                    }, this.L);
                    return;
                }
                this.L.w("[ModuleRemoteConfig] Enrolling A/B test variants, Key/Variant pair is invalid. Aborting.");
                rCVariantCallback.callback(RequestResult.Error, "Provided key/variant pair is invalid.");
                return;
            }
            this.L.d("[ModuleRemoteConfig] Enrolling A/B test variants was aborted, temporary device ID mode is set or device ID is null.");
            rCVariantCallback.callback(RequestResult.Error, "Temporary device ID mode is set or device ID is null.");
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] Encountered internal error while trying to enroll A/B test variants. " + e.toString());
            rCVariantCallback.callback(RequestResult.Error, "Encountered internal error while trying to enroll A/B test variants.");
        }
    }

    /* JADX INFO: renamed from: lambda$testingEnrollIntoVariantInternal$5$ly-count-android-sdk-ModuleRemoteConfig, reason: not valid java name */
    /* synthetic */ void m3511x7563a839(RCVariantCallback rCVariantCallback, JSONObject jSONObject) {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleRemoteConfig] Processing Fetching all A/B test variants received response, received response is null:[");
        sb.append(jSONObject == null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (jSONObject == null) {
            rCVariantCallback.callback(RequestResult.NetworkIssue, "Encountered problem while trying to reach the server, possibly no internet connection");
            return;
        }
        try {
            if (isResponseValid(jSONObject)) {
                RCAutomaticDownloadTrigger(true);
                rCVariantCallback.callback(RequestResult.Success, null);
            } else {
                rCVariantCallback.callback(RequestResult.NetworkIssue, "Bad response from the server:" + jSONObject.toString());
            }
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] testingEnrollIntoVariantInternal - execute, Encountered internal issue while trying to enroll to the variant, [" + e.toString() + "]");
            rCVariantCallback.callback(RequestResult.Error, "Encountered internal error while trying to take care of the A/B test variant enrolment.");
        }
    }

    void mergeCheckResponseIntoCurrentValues(boolean z, Map<String, RCData> map) {
        RemoteConfigValueStore remoteConfigValueStoreLoadConfig = loadConfig();
        remoteConfigValueStoreLoadConfig.mergeValues(map, z);
        this.L.d("[ModuleRemoteConfig] Finished remote config processing, starting saving");
        saveConfig(remoteConfigValueStoreLoadConfig);
        this.L.d("[ModuleRemoteConfig] Finished remote config saving");
    }

    boolean isResponseValid(JSONObject jSONObject) {
        try {
            return jSONObject.get("result").equals("Success");
        } catch (JSONException e) {
            this.L.e("[ModuleRemoteConfig] isResponseValid, encountered issue, " + e);
            return false;
        }
    }

    RCData getRCValue(String str) {
        try {
            return loadConfig().getValue(str);
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] getValue, Call failed:[" + e.toString() + "]");
            return new RCData(null, true);
        }
    }

    Object getRCValueLegacy(String str) {
        try {
            return loadConfig().getValueLegacy(str);
        } catch (Exception e) {
            this.L.e("[ModuleRemoteConfig] getValueLegacy, Call failed:[" + e.toString() + "]");
            return null;
        }
    }

    void saveConfig(RemoteConfigValueStore remoteConfigValueStore) {
        this.storageProvider.setRemoteConfigValues(remoteConfigValueStore.dataToString());
    }

    RemoteConfigValueStore loadConfig() {
        return RemoteConfigValueStore.dataFromString(this.storageProvider.getRemoteConfigValues(), this.remoteConfigValuesShouldBeCached);
    }

    void clearValueStoreInternal() {
        this.storageProvider.setRemoteConfigValues("");
    }

    Map<String, Object> getAllRemoteConfigValuesInternalLegacy() {
        try {
            return loadConfig().getAllValuesLegacy();
        } catch (Exception e) {
            Countly.sharedInstance().L.e("[ModuleRemoteConfig] getAllRemoteConfigValuesInternal, Call failed:[" + e.toString() + "]");
            return new HashMap();
        }
    }

    Map<String, RCData> getAllRemoteConfigValuesInternal() {
        try {
            return loadConfig().getAllValues();
        } catch (Exception e) {
            Countly.sharedInstance().L.e("[ModuleRemoteConfig] getAllRemoteConfigValuesInternal, Call failed:[" + e.toString() + "]");
            return new HashMap();
        }
    }

    Map<String, String[]> testingGetAllVariantsInternal() {
        return this.variantContainer;
    }

    String[] testingGetVariantsForKeyInternal(String str) {
        if (this.variantContainer.containsKey(str)) {
            return this.variantContainer.get(str);
        }
        return null;
    }

    void clearAndDownloadAfterIdChange(boolean z) {
        this.L.v("[RemoteConfig] Clearing remote config values and preparing to download after ID update, " + z);
        if (z) {
            CacheOrClearRCValuesIfNeeded();
        }
        if (this.automaticDownloadTriggersEnabled && this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
            this.updateRemoteConfigAfterIdChange = true;
        }
    }

    void CacheOrClearRCValuesIfNeeded() {
        this.L.v("[RemoteConfig] CacheOrClearRCValuesIfNeeded, cacheclearing values");
        RemoteConfigValueStore remoteConfigValueStoreLoadConfig = loadConfig();
        remoteConfigValueStoreLoadConfig.cacheClearValues();
        saveConfig(remoteConfigValueStoreLoadConfig);
    }

    void NotifyDownloadCallbacks(RCDownloadCallback rCDownloadCallback, RequestResult requestResult, String str, boolean z, Map<String, RCData> map) {
        Iterator<RCDownloadCallback> it = this.downloadCallbacks.iterator();
        while (it.hasNext()) {
            it.next().callback(requestResult, str, z, map);
        }
        if (rCDownloadCallback != null) {
            rCDownloadCallback.callback(requestResult, str, z, map);
        }
    }

    void RCAutomaticDownloadTrigger(boolean z) {
        if (z) {
            CacheOrClearRCValuesIfNeeded();
        }
        if (this.automaticDownloadTriggersEnabled && this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
            this.L.d("[RemoteConfig] Automatically updating remote config values");
            updateRemoteConfigValues(null, null, false, null);
        } else {
            this.L.v("[RemoteConfig] Automatic RC update trigger skipped");
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onConsentChanged(List<String> list, boolean z, ModuleConsent.ConsentChangeSource consentChangeSource) {
        if (list.contains(Countly.CountlyFeatureNames.remoteConfig) && consentChangeSource == ModuleConsent.ConsentChangeSource.ChangeConsentCall) {
            if (z) {
                RCAutomaticDownloadTrigger(false);
            } else {
                this.L.d("[RemoteConfig] removing remote-config consent. Clearing stored values");
                clearValueStoreInternal();
            }
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void deviceIdChanged() {
        this.L.v("[RemoteConfig] Device ID changed will update values: [" + this.updateRemoteConfigAfterIdChange + "]");
        if (this.updateRemoteConfigAfterIdChange) {
            this.updateRemoteConfigAfterIdChange = false;
            RCAutomaticDownloadTrigger(true);
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    public void initFinished(CountlyConfig countlyConfig) {
        if (this.deviceIdProvider.isTemporaryIdEnabled()) {
            return;
        }
        RCAutomaticDownloadTrigger(false);
    }

    @Override // ly.count.android.sdk.ModuleBase
    public void halt() {
        this.remoteConfigInterface = null;
    }

    public class RemoteConfig {
        static /* synthetic */ void lambda$downloadAllKeys$5(RequestResult requestResult, String str, boolean z, Map map) {
        }

        static /* synthetic */ void lambda$downloadOmittingKeys$3(RequestResult requestResult, String str, boolean z, Map map) {
        }

        static /* synthetic */ void lambda$downloadSpecificKeys$4(RequestResult requestResult, String str, boolean z, Map map) {
        }

        static /* synthetic */ void lambda$testingDownloadVariantInformation$6(RequestResult requestResult, String str) {
        }

        static /* synthetic */ void lambda$testingEnrollIntoVariant$7(RequestResult requestResult, String str) {
        }

        public RemoteConfig() {
        }

        public void clearStoredValues() {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Calling 'clearStoredValues'");
                ModuleRemoteConfig.this.clearValueStoreInternal();
            }
        }

        public Map<String, Object> getAllValues() {
            Map<String, Object> allRemoteConfigValuesInternalLegacy;
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Calling 'getAllValues'");
                allRemoteConfigValuesInternalLegacy = ModuleRemoteConfig.this.getAllRemoteConfigValuesInternalLegacy();
            }
            return allRemoteConfigValuesInternalLegacy;
        }

        public Object getValueForKey(String str) {
            Object rCValueLegacy;
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Calling remoteConfigValueForKey, " + str);
                rCValueLegacy = ModuleRemoteConfig.this.getRCValueLegacy(str);
            }
            return rCValueLegacy;
        }

        public void updateExceptKeys(String[] strArr, final RemoteConfigCallback remoteConfigCallback) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Manually calling to updateRemoteConfig with exclude keys");
                if (!ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    if (remoteConfigCallback != null) {
                        remoteConfigCallback.callback("No consent given");
                    }
                } else {
                    if (strArr == null) {
                        ModuleRemoteConfig.this.L.w("[RemoteConfig] updateRemoteConfigExceptKeys passed 'keys to ignore' array is null");
                    }
                    ModuleRemoteConfig.this.updateRemoteConfigValues(null, strArr, true, new RCDownloadCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$RemoteConfig$$ExternalSyntheticLambda2
                        @Override // ly.count.android.sdk.RCDownloadCallback
                        public final void callback(RequestResult requestResult, String str, boolean z, Map map) {
                            ModuleRemoteConfig.RemoteConfig.lambda$updateExceptKeys$0(remoteConfigCallback, requestResult, str, z, map);
                        }
                    });
                }
            }
        }

        static /* synthetic */ void lambda$updateExceptKeys$0(RemoteConfigCallback remoteConfigCallback, RequestResult requestResult, String str, boolean z, Map map) {
            if (remoteConfigCallback != null) {
                remoteConfigCallback.callback(str);
            }
        }

        public void updateForKeysOnly(String[] strArr, final RemoteConfigCallback remoteConfigCallback) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Manually calling to updateRemoteConfig with include keys");
                if (!ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    if (remoteConfigCallback != null) {
                        remoteConfigCallback.callback("No consent given");
                    }
                } else {
                    if (strArr == null) {
                        ModuleRemoteConfig.this.L.w("[RemoteConfig] updateRemoteConfigExceptKeys passed 'keys to include' array is null");
                    }
                    ModuleRemoteConfig.this.updateRemoteConfigValues(strArr, null, true, new RCDownloadCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$RemoteConfig$$ExternalSyntheticLambda0
                        @Override // ly.count.android.sdk.RCDownloadCallback
                        public final void callback(RequestResult requestResult, String str, boolean z, Map map) {
                            ModuleRemoteConfig.RemoteConfig.lambda$updateForKeysOnly$1(remoteConfigCallback, requestResult, str, z, map);
                        }
                    });
                }
            }
        }

        static /* synthetic */ void lambda$updateForKeysOnly$1(RemoteConfigCallback remoteConfigCallback, RequestResult requestResult, String str, boolean z, Map map) {
            if (remoteConfigCallback != null) {
                remoteConfigCallback.callback(str);
            }
        }

        public void update(final RemoteConfigCallback remoteConfigCallback) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Manually calling to updateRemoteConfig");
                if (ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    ModuleRemoteConfig.this.updateRemoteConfigValues(null, null, true, new RCDownloadCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$RemoteConfig$$ExternalSyntheticLambda5
                        @Override // ly.count.android.sdk.RCDownloadCallback
                        public final void callback(RequestResult requestResult, String str, boolean z, Map map) {
                            ModuleRemoteConfig.RemoteConfig.lambda$update$2(remoteConfigCallback, requestResult, str, z, map);
                        }
                    });
                } else {
                    if (remoteConfigCallback != null) {
                        remoteConfigCallback.callback("No consent given");
                    }
                }
            }
        }

        static /* synthetic */ void lambda$update$2(RemoteConfigCallback remoteConfigCallback, RequestResult requestResult, String str, boolean z, Map map) {
            if (remoteConfigCallback != null) {
                remoteConfigCallback.callback(str);
            }
        }

        public void downloadOmittingKeys(String[] strArr, RCDownloadCallback rCDownloadCallback) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Manually calling to updateRemoteConfig with exclude keys");
                if (!ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    if (rCDownloadCallback != null) {
                        rCDownloadCallback.callback(RequestResult.Error, null, false, null);
                    }
                    return;
                }
                if (strArr == null) {
                    ModuleRemoteConfig.this.L.w("[RemoteConfig] updateRemoteConfigExceptKeys passed 'keys to ignore' array is null");
                }
                if (rCDownloadCallback == null) {
                    rCDownloadCallback = new RCDownloadCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$RemoteConfig$$ExternalSyntheticLambda3
                        @Override // ly.count.android.sdk.RCDownloadCallback
                        public final void callback(RequestResult requestResult, String str, boolean z, Map map) {
                            ModuleRemoteConfig.RemoteConfig.lambda$downloadOmittingKeys$3(requestResult, str, z, map);
                        }
                    };
                }
                ModuleRemoteConfig.this.updateRemoteConfigValues(null, strArr, false, rCDownloadCallback);
            }
        }

        public void downloadSpecificKeys(String[] strArr, RCDownloadCallback rCDownloadCallback) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Manually calling to updateRemoteConfig with include keys");
                if (!ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    if (rCDownloadCallback != null) {
                        rCDownloadCallback.callback(RequestResult.Error, null, false, null);
                    }
                    return;
                }
                if (strArr == null) {
                    ModuleRemoteConfig.this.L.w("[RemoteConfig] updateRemoteConfigExceptKeys passed 'keys to include' array is null");
                }
                if (rCDownloadCallback == null) {
                    rCDownloadCallback = new RCDownloadCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$RemoteConfig$$ExternalSyntheticLambda6
                        @Override // ly.count.android.sdk.RCDownloadCallback
                        public final void callback(RequestResult requestResult, String str, boolean z, Map map) {
                            ModuleRemoteConfig.RemoteConfig.lambda$downloadSpecificKeys$4(requestResult, str, z, map);
                        }
                    };
                }
                ModuleRemoteConfig.this.updateRemoteConfigValues(strArr, null, false, rCDownloadCallback);
            }
        }

        public void downloadAllKeys(RCDownloadCallback rCDownloadCallback) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Manually calling to update Remote Config v2");
                if (!ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    if (rCDownloadCallback != null) {
                        rCDownloadCallback.callback(RequestResult.Error, null, true, null);
                    }
                } else {
                    if (rCDownloadCallback == null) {
                        rCDownloadCallback = new RCDownloadCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$RemoteConfig$$ExternalSyntheticLambda4
                            @Override // ly.count.android.sdk.RCDownloadCallback
                            public final void callback(RequestResult requestResult, String str, boolean z, Map map) {
                                ModuleRemoteConfig.RemoteConfig.lambda$downloadAllKeys$5(requestResult, str, z, map);
                            }
                        };
                    }
                    ModuleRemoteConfig.this.updateRemoteConfigValues(null, null, false, rCDownloadCallback);
                }
            }
        }

        public Map<String, RCData> getValues() {
            Map<String, RCData> allRemoteConfigValuesInternal;
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Getting all Remote config values v2");
                allRemoteConfigValuesInternal = ModuleRemoteConfig.this.getAllRemoteConfigValuesInternal();
            }
            return allRemoteConfigValuesInternal;
        }

        public RCData getValue(String str) {
            RCData rCValue;
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Getting Remote config values for key:[" + str + "] v2");
                rCValue = ModuleRemoteConfig.this.getRCValue(str);
            }
            return rCValue;
        }

        public void enrollIntoABTestsForKeys(String[] strArr) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Enrolling user into A/B tests.");
                if (strArr != null && strArr.length != 0) {
                    if (ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                        ModuleRemoteConfig.this.enrollIntoABTestsForKeysInternal(strArr);
                        return;
                    }
                    return;
                }
                ModuleRemoteConfig.this.L.w("[RemoteConfig] A key should be provided to enroll the user.");
            }
        }

        public void exitABTestsForKeys(String[] strArr) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Removing user from A/B tests.");
                if (strArr == null) {
                    strArr = new String[0];
                }
                if (ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    ModuleRemoteConfig.this.exitABTestsForKeysInternal(strArr);
                }
            }
        }

        public void registerDownloadCallback(RCDownloadCallback rCDownloadCallback) {
            ModuleRemoteConfig.this.downloadCallbacks.add(rCDownloadCallback);
        }

        public void removeDownloadCallback(RCDownloadCallback rCDownloadCallback) {
            ModuleRemoteConfig.this.downloadCallbacks.remove(rCDownloadCallback);
        }

        public void clearAll() {
            clearStoredValues();
        }

        public Map<String, String[]> testingGetAllVariants() {
            Map<String, String[]> mapTestingGetAllVariantsInternal;
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Calling 'testingGetAllVariants'");
                mapTestingGetAllVariantsInternal = ModuleRemoteConfig.this.testingGetAllVariantsInternal();
            }
            return mapTestingGetAllVariantsInternal;
        }

        public String[] testingGetVariantsForKey(String str) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Calling 'testingGetVariantsForKey'");
                if (str == null) {
                    ModuleRemoteConfig.this.L.i("[RemoteConfig] testingGetVariantsForKey, provided variant key can not be null");
                    return null;
                }
                return ModuleRemoteConfig.this.testingGetVariantsForKeyInternal(str);
            }
        }

        public void testingDownloadVariantInformation(RCVariantCallback rCVariantCallback) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Calling 'testingFetchVariantInformation'");
                if (ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    if (rCVariantCallback == null) {
                        rCVariantCallback = new RCVariantCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$RemoteConfig$$ExternalSyntheticLambda1
                            @Override // ly.count.android.sdk.RCVariantCallback
                            public final void callback(RequestResult requestResult, String str) {
                                ModuleRemoteConfig.RemoteConfig.lambda$testingDownloadVariantInformation$6(requestResult, str);
                            }
                        };
                    }
                    ModuleRemoteConfig.this.testingFetchVariantInformationInternal(rCVariantCallback);
                }
            }
        }

        public void testingEnrollIntoVariant(String str, String str2, RCVariantCallback rCVariantCallback) {
            synchronized (ModuleRemoteConfig.this._cly) {
                ModuleRemoteConfig.this.L.i("[RemoteConfig] Calling 'testingEnrollIntoVariant'");
                if (ModuleRemoteConfig.this.consentProvider.getConsent(Countly.CountlyFeatureNames.remoteConfig)) {
                    if (str != null && str2 != null) {
                        if (rCVariantCallback == null) {
                            rCVariantCallback = new RCVariantCallback() { // from class: ly.count.android.sdk.ModuleRemoteConfig$RemoteConfig$$ExternalSyntheticLambda7
                                @Override // ly.count.android.sdk.RCVariantCallback
                                public final void callback(RequestResult requestResult, String str3) {
                                    ModuleRemoteConfig.RemoteConfig.lambda$testingEnrollIntoVariant$7(requestResult, str3);
                                }
                            };
                        }
                        ModuleRemoteConfig.this.testingEnrollIntoVariantInternal(str, str2, rCVariantCallback);
                        return;
                    }
                    ModuleRemoteConfig.this.L.w("[RemoteConfig] testEnrollIntoVariant, passed key or variant is null. Aborting.");
                }
            }
        }
    }
}
