package ly.count.android.sdk;

import android.app.Application;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class CountlyConfig {
    protected CrashFilterCallback crashFilterCallback;
    protected HealthTracker healthTracker;
    Integer maxBreadcrumbCount;
    Integer maxKeyLength;
    Integer maxSegmentationValues;
    Integer maxStackTraceLineLength;
    Integer maxStackTraceLinesPerThread;
    Integer maxValueSize;
    ModuleLog.LogCallback providedLogCallback;
    protected CountlyStore countlyStore = null;
    protected ConsentProvider consentProvider = null;
    protected StorageProvider storageProvider = null;
    protected EventProvider eventProvider = null;
    protected EventQueueProvider eventQueueProvider = null;
    protected RequestQueueProvider requestQueueProvider = null;
    protected DeviceIdProvider deviceIdProvider = null;
    protected ViewIdProvider viewIdProvider = null;
    protected BaseInfoProvider baseInfoProvider = null;
    protected ConfigurationProvider configProvider = null;
    protected SafeIDGenerator safeViewIDGenerator = null;
    protected SafeIDGenerator safeEventIDGenerator = null;
    protected ImmediateRequestGenerator immediateRequestGenerator = null;
    protected MetricProvider metricProviderOverride = null;
    protected DeviceInfo deviceInfo = null;
    protected ModuleBase testModuleListener = null;
    protected boolean checkForNativeCrashDumps = true;
    protected Map<String, Object> providedUserProperties = null;
    protected Context context = null;
    protected String serverURL = null;
    protected String appKey = null;
    protected String deviceID = null;
    protected int starRatingSessionLimit = 5;
    protected StarRatingCallback starRatingCallback = null;
    protected String starRatingTextTitle = null;
    protected String starRatingTextMessage = null;
    protected String starRatingTextDismiss = null;
    protected boolean loggingEnabled = false;
    protected boolean enableUnhandledCrashReporting = false;
    protected boolean enableAutomaticViewTracking = false;
    protected boolean autoTrackingUseShortName = false;
    protected Class[] automaticViewTrackingExceptions = null;
    protected Map<String, Object> globalViewSegmentation = null;
    protected Map<String, String> customNetworkRequestHeaders = null;
    protected boolean pushIntentAddMetadata = false;
    protected boolean enableRemoteConfigAutomaticDownloadTriggers = false;
    protected boolean enableAutoEnrollFlag = false;
    boolean enableRemoteConfigValueCaching = false;
    protected RemoteConfigCallback remoteConfigCallbackLegacy = null;
    protected List<RCDownloadCallback> remoteConfigGlobalCallbackList = new ArrayList(2);
    protected boolean shouldRequireConsent = false;
    protected boolean enableAllConsents = false;
    protected String[] enabledFeatureNames = null;
    protected boolean httpPostForced = false;
    protected boolean temporaryDeviceIdEnabled = false;
    protected String tamperingProtectionSalt = null;
    protected Integer eventQueueSizeThreshold = null;
    protected boolean trackOrientationChange = true;
    protected boolean manualSessionControlEnabled = false;
    protected boolean manualSessionControlHybridModeEnabled = false;
    protected boolean recordAllThreadsWithCrash = false;
    protected boolean disableUpdateSessionRequests = false;
    protected boolean shouldIgnoreAppCrawlers = false;
    protected String[] appCrawlerNames = null;
    protected String[] publicKeyPinningCertificates = null;
    protected String[] certificatePinningCertificates = null;
    protected Map<String, Object> customCrashSegment = null;
    protected Integer sessionUpdateTimerDelay = null;
    protected boolean starRatingDialogIsCancellable = false;
    protected boolean starRatingShownAutomatically = false;
    protected boolean starRatingDisableAskingForEachAppVersion = false;
    protected Application application = null;
    protected boolean recordAppStartTime = false;
    boolean disableLocation = false;
    String locationCountyCode = null;
    String locationCity = null;
    String locationLocation = null;
    String locationIpAddress = null;
    Map<String, String> metricOverride = null;
    Long appStartTimestampOverride = null;
    boolean appLoadedManualTrigger = false;
    boolean manualForegroundBackgroundTrigger = false;
    int maxRequestQueueSize = 1000;
    String daCampaignType = null;
    String daCampaignData = null;
    Map<String, String> iaAttributionValues = null;
    boolean explicitStorageModeEnabled = false;
    boolean serverConfigurationEnabled = false;
    boolean healthCheckEnabled = true;
    int maxStackTraceThreadCount = 30;

    public synchronized CountlyConfig setMaxSegmentationValues(int i) {
        this.maxSegmentationValues = Integer.valueOf(i);
        return this;
    }

    public synchronized CountlyConfig setMaxBreadcrumbCount(int i) {
        this.maxBreadcrumbCount = Integer.valueOf(i);
        return this;
    }

    public CountlyConfig() {
    }

    public CountlyConfig(Context context, String str, String str2) {
        setContext(context);
        setAppKey(str);
        setServerURL(str2);
    }

    public CountlyConfig(Application application, String str, String str2) {
        setAppKey(str);
        setServerURL(str2);
        setApplication(application);
    }

    public synchronized CountlyConfig setContext(Context context) {
        this.context = context;
        return this;
    }

    public synchronized CountlyConfig setServerURL(String str) {
        this.serverURL = str;
        return this;
    }

    public synchronized CountlyConfig setAppKey(String str) {
        this.appKey = str;
        return this;
    }

    public synchronized CountlyConfig setDeviceId(String str) {
        this.deviceID = str;
        return this;
    }

    public synchronized CountlyConfig setIdMode(DeviceIdType deviceIdType) {
        return this;
    }

    public synchronized CountlyConfig setStarRatingSessionLimit(int i) {
        this.starRatingSessionLimit = i;
        return this;
    }

    public synchronized CountlyConfig setStarRatingCallback(StarRatingCallback starRatingCallback) {
        this.starRatingCallback = starRatingCallback;
        return this;
    }

    public synchronized CountlyConfig setStarRatingTextTitle(String str) {
        this.starRatingTextTitle = str;
        return this;
    }

    public synchronized CountlyConfig setStarRatingTextMessage(String str) {
        this.starRatingTextMessage = str;
        return this;
    }

    public synchronized CountlyConfig setStarRatingTextDismiss(String str) {
        this.starRatingTextDismiss = str;
        return this;
    }

    public synchronized CountlyConfig setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        return this;
    }

    public synchronized CountlyConfig enableCrashReporting() {
        this.enableUnhandledCrashReporting = true;
        return this;
    }

    public synchronized CountlyConfig setViewTracking(boolean z) {
        this.enableAutomaticViewTracking = z;
        return this;
    }

    public synchronized CountlyConfig enableAutomaticViewTracking() {
        this.enableAutomaticViewTracking = true;
        return this;
    }

    public synchronized CountlyConfig enableAutomaticViewShortNames() {
        this.autoTrackingUseShortName = true;
        return this;
    }

    public synchronized CountlyConfig setAutoTrackingUseShortName(boolean z) {
        this.autoTrackingUseShortName = z;
        return this;
    }

    public synchronized CountlyConfig setGlobalViewSegmentation(Map<String, Object> map) {
        this.globalViewSegmentation = map;
        return this;
    }

    public synchronized CountlyConfig setAutomaticViewSegmentation(Map<String, Object> map) {
        this.globalViewSegmentation = map;
        return this;
    }

    public synchronized CountlyConfig setAutomaticViewTrackingExclusions(Class[] clsArr) {
        if (clsArr != null) {
            for (Class cls : clsArr) {
                if (cls == null) {
                    throw new IllegalArgumentException("setAutomaticViewTrackingExclusions(...) does not accept 'null' activities");
                }
            }
        }
        this.automaticViewTrackingExceptions = clsArr;
        return this;
    }

    public synchronized CountlyConfig setAutoTrackingExceptions(Class[] clsArr) {
        return setAutomaticViewTrackingExclusions(clsArr);
    }

    public synchronized CountlyConfig addCustomNetworkRequestHeaders(Map<String, String> map) {
        this.customNetworkRequestHeaders = map;
        return this;
    }

    public synchronized CountlyConfig setPushIntentAddMetadata(boolean z) {
        this.pushIntentAddMetadata = z;
        return this;
    }

    public synchronized CountlyConfig setRemoteConfigAutomaticDownload(boolean z, RemoteConfigCallback remoteConfigCallback) {
        this.enableRemoteConfigAutomaticDownloadTriggers = z;
        this.remoteConfigCallbackLegacy = remoteConfigCallback;
        return this;
    }

    public synchronized CountlyConfig enableRemoteConfigAutomaticTriggers() {
        this.enableRemoteConfigAutomaticDownloadTriggers = true;
        return this;
    }

    public synchronized CountlyConfig enrollABOnRCDownload() {
        this.enableAutoEnrollFlag = true;
        return this;
    }

    public synchronized CountlyConfig enableRemoteConfigValueCaching() {
        this.enableRemoteConfigValueCaching = true;
        return this;
    }

    public synchronized CountlyConfig RemoteConfigRegisterGlobalCallback(RCDownloadCallback rCDownloadCallback) {
        this.remoteConfigGlobalCallbackList.add(rCDownloadCallback);
        return this;
    }

    public synchronized CountlyConfig setRequiresConsent(boolean z) {
        this.shouldRequireConsent = z;
        return this;
    }

    public synchronized CountlyConfig setConsentEnabled(String[] strArr) {
        this.enabledFeatureNames = strArr;
        return this;
    }

    public synchronized CountlyConfig giveAllConsents() {
        this.enableAllConsents = true;
        return this;
    }

    public synchronized CountlyConfig setHttpPostForced(boolean z) {
        this.httpPostForced = z;
        return this;
    }

    public synchronized CountlyConfig enableTemporaryDeviceIdMode() {
        this.temporaryDeviceIdEnabled = true;
        return this;
    }

    public synchronized CountlyConfig setCrashFilterCallback(CrashFilterCallback crashFilterCallback) {
        this.crashFilterCallback = crashFilterCallback;
        return this;
    }

    public synchronized CountlyConfig setParameterTamperingProtectionSalt(String str) {
        this.tamperingProtectionSalt = str;
        return this;
    }

    public synchronized CountlyConfig setTrackOrientationChanges(boolean z) {
        this.trackOrientationChange = z;
        return this;
    }

    public synchronized CountlyConfig setRecordAllThreadsWithCrash() {
        this.recordAllThreadsWithCrash = true;
        return this;
    }

    public synchronized CountlyConfig setEnableAttribution(boolean z) {
        return this;
    }

    public synchronized CountlyConfig enablePublicKeyPinning(String[] strArr) {
        this.publicKeyPinningCertificates = strArr;
        return this;
    }

    public synchronized CountlyConfig enableCertificatePinning(String[] strArr) {
        this.certificatePinningCertificates = strArr;
        return this;
    }

    public synchronized CountlyConfig setShouldIgnoreAppCrawlers(boolean z) {
        this.shouldIgnoreAppCrawlers = z;
        return this;
    }

    public synchronized CountlyConfig setAppCrawlerNames(String[] strArr) {
        this.appCrawlerNames = strArr;
        return this;
    }

    public synchronized CountlyConfig setEventQueueSizeToSend(int i) {
        this.eventQueueSizeThreshold = Integer.valueOf(i);
        return this;
    }

    public synchronized CountlyConfig enableManualSessionControl() {
        this.manualSessionControlEnabled = true;
        return this;
    }

    public synchronized CountlyConfig enableManualSessionControlHybridMode() {
        this.manualSessionControlHybridModeEnabled = true;
        return this;
    }

    public synchronized CountlyConfig setCustomCrashSegment(Map<String, Object> map) {
        this.customCrashSegment = map;
        return this;
    }

    protected synchronized CountlyConfig checkForNativeCrashDumps(boolean z) {
        this.checkForNativeCrashDumps = z;
        return this;
    }

    public synchronized CountlyConfig setUpdateSessionTimerDelay(int i) {
        this.sessionUpdateTimerDelay = Integer.valueOf(i);
        return this;
    }

    protected synchronized CountlyConfig setCountlyStore(CountlyStore countlyStore) {
        this.countlyStore = countlyStore;
        return this;
    }

    protected synchronized CountlyConfig setDisableUpdateSessionRequests(boolean z) {
        this.disableUpdateSessionRequests = z;
        return this;
    }

    public synchronized CountlyConfig setIfStarRatingDialogIsCancellable(boolean z) {
        this.starRatingDialogIsCancellable = z;
        return this;
    }

    public synchronized CountlyConfig setIfStarRatingShownAutomatically(boolean z) {
        this.starRatingShownAutomatically = z;
        return this;
    }

    public synchronized CountlyConfig setStarRatingDisableAskingForEachAppVersion(boolean z) {
        this.starRatingDisableAskingForEachAppVersion = z;
        return this;
    }

    public synchronized CountlyConfig setApplication(Application application) {
        this.application = application;
        return this;
    }

    public synchronized CountlyConfig setRecordAppStartTime(boolean z) {
        this.recordAppStartTime = z;
        return this;
    }

    public synchronized CountlyConfig setDisableLocation() {
        this.disableLocation = true;
        return this;
    }

    public synchronized CountlyConfig setLocation(String str, String str2, String str3, String str4) {
        this.locationCountyCode = str;
        this.locationCity = str2;
        this.locationLocation = str3;
        this.locationIpAddress = str4;
        return this;
    }

    public synchronized CountlyConfig setMetricOverride(Map<String, String> map) {
        this.metricOverride = map;
        return this;
    }

    public synchronized CountlyConfig setAppStartTimestampOverride(long j) {
        this.appStartTimestampOverride = Long.valueOf(j);
        return this;
    }

    public synchronized CountlyConfig enableManualAppLoadedTrigger() {
        this.appLoadedManualTrigger = true;
        return this;
    }

    public synchronized CountlyConfig enableManualForegroundBackgroundTriggerAPM() {
        this.manualForegroundBackgroundTrigger = true;
        return this;
    }

    public synchronized CountlyConfig setLogListener(ModuleLog.LogCallback logCallback) {
        this.providedLogCallback = logCallback;
        return this;
    }

    public synchronized CountlyConfig setMaxRequestQueueSize(int i) {
        this.maxRequestQueueSize = i;
        return this;
    }

    public synchronized CountlyConfig setDirectAttribution(String str, String str2) {
        this.daCampaignType = str;
        this.daCampaignData = str2;
        return this;
    }

    public synchronized CountlyConfig setIndirectAttribution(Map<String, String> map) {
        this.iaAttributionValues = map;
        return this;
    }

    public synchronized CountlyConfig setUserProperties(Map<String, Object> map) {
        this.providedUserProperties = map;
        return this;
    }

    public synchronized CountlyConfig enableExplicitStorageMode() {
        this.explicitStorageModeEnabled = true;
        return this;
    }

    public synchronized CountlyConfig enableServerConfiguration() {
        this.serverConfigurationEnabled = true;
        return this;
    }

    protected synchronized CountlyConfig disableHealthCheck() {
        this.healthCheckEnabled = false;
        return this;
    }
}
