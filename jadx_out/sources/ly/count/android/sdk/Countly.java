package ly.count.android.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes6.dex */
public class Countly {
    public static final String CONSENT_BROADCAST = "ly.count.android.sdk.Countly.CONSENT_BROADCAST";
    protected static final String DEFAULT_APP_VERSION = "1.0";
    static int EVENT_QUEUE_SIZE_THRESHOLD = 100;
    public static final String TAG = "Countly";
    private static final long TIMER_DELAY_IN_SECONDS = 60;
    static long applicationStart = System.currentTimeMillis();
    protected static String[] certificatePinCertificates = null;
    static final long lastRegistrationCallDebounceDuration = 60000;
    protected static String[] publicKeyPinCertificates;
    private int activityCount_;
    BaseInfoProvider baseInfoProvider;
    ConnectionQueue connectionQueue_;
    Context context_;
    CountlyStore countlyStore;
    private boolean enableLogging_;
    String[] locationFallback;
    Map<String, String> requestHeaderCustomValues;
    RequestQueueProvider requestQueueProvider;
    private final String DEFAULT_COUNTLY_SDK_VERSION_STRING = "23.8.0";
    private final String DEFAULT_COUNTLY_SDK_NAME = "java-native-android";
    public String COUNTLY_SDK_VERSION_STRING = "23.8.0";
    public String COUNTLY_SDK_NAME = "java-native-android";
    public ModuleLog L = new ModuleLog();
    final int maxKeyLengthDefault = 128;
    final int maxValueSizeDefault = 256;
    final int maxSegmentationValuesDefault = 100;
    final int maxBreadcrumbCountDefault = 100;
    final int maxStackTraceLinesPerThreadDefault = 30;
    final int maxStackTraceLineLengthDefault = 200;
    final int maxStackTraceThreadCountDefault = 30;
    private ScheduledFuture<?> timerFuture = null;
    boolean disableUpdateSessionRequests_ = false;
    boolean sdkIsInitialised = false;
    List<ModuleBase> modules = new ArrayList();
    ModuleCrash moduleCrash = null;
    ModuleEvents moduleEvents = null;
    ModuleViews moduleViews = null;
    ModuleRatings moduleRatings = null;
    ModuleSessions moduleSessions = null;
    ModuleRemoteConfig moduleRemoteConfig = null;
    ModuleAPM moduleAPM = null;
    ModuleConsent moduleConsent = null;
    ModuleDeviceId moduleDeviceId = null;
    ModuleLocation moduleLocation = null;
    ModuleFeedback moduleFeedback = null;
    ModuleRequestQueue moduleRequestQueue = null;
    ModuleAttribution moduleAttribution = null;
    ModuleUserProfile moduleUserProfile = null;
    ModuleConfiguration moduleConfiguration = null;
    ModuleHealthCheck moduleHealthCheck = null;
    boolean isHttpPostForced = false;
    private boolean addMetadataToPushIntents = false;
    private boolean calledAtLeastOnceOnStart = false;
    protected boolean isBeginSessionSent = false;
    protected CountlyConfig config_ = null;
    long lastRegistrationCallTs = 0;
    String lastRegistrationCallID = null;
    CountlyMessagingProvider lastRegistrationCallProvider = null;
    boolean applicationClassProvided = false;
    private final ScheduledExecutorService timerService_ = Executors.newSingleThreadScheduledExecutor();

    public static class CountlyFeatureNames {
        public static final String apm = "apm";
        public static final String attribution = "attribution";
        public static final String clicks = "clicks";
        public static final String crashes = "crashes";
        public static final String events = "events";
        public static final String feedback = "feedback";
        public static final String location = "location";
        public static final String push = "push";
        public static final String remoteConfig = "remote-config";
        public static final String scrolls = "scrolls";
        public static final String sessions = "sessions";
        public static final String starRating = "star-rating";
        public static final String users = "users";
        public static final String views = "views";
    }

    public enum CountlyMessagingMode {
        TEST,
        PRODUCTION
    }

    public enum CountlyMessagingProvider {
        FCM,
        HMS
    }

    public static void applicationOnCreate() {
    }

    private static class SingletonHolder {
        static final Countly instance = new Countly();

        private SingletonHolder() {
        }
    }

    public static Countly sharedInstance() {
        return SingletonHolder.instance;
    }

    Countly() {
        staticInit();
    }

    private void staticInit() {
        this.connectionQueue_ = new ConnectionQueue();
        startTimerService(this.timerService_, this.timerFuture, 60L);
    }

    private void startTimerService(ScheduledExecutorService scheduledExecutorService, ScheduledFuture<?> scheduledFuture, long j) {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
        }
        long j2 = j < 1 ? 1L : j;
        this.timerFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() { // from class: ly.count.android.sdk.Countly.1
            @Override // java.lang.Runnable
            public void run() {
                Countly.this.onTimer();
            }
        }, j2, j2, TimeUnit.SECONDS);
    }

    public synchronized Countly init(CountlyConfig countlyConfig) {
        try {
            if (countlyConfig == null) {
                throw new IllegalArgumentException("Can't init SDK with 'null' config");
            }
            if (countlyConfig.loggingEnabled) {
                setLoggingEnabled(true);
            }
            this.L.SetListener(countlyConfig.providedLogCallback);
            if (this.COUNTLY_SDK_NAME.equals("java-native-android") && this.COUNTLY_SDK_VERSION_STRING.equals("23.8.0")) {
                this.L.d("[Init] Initializing Countly [" + this.COUNTLY_SDK_NAME + "] SDK version [" + this.COUNTLY_SDK_VERSION_STRING + "]");
            } else {
                this.L.d("[Init] Initializing Countly [" + this.COUNTLY_SDK_NAME + "] SDK version [" + this.COUNTLY_SDK_VERSION_STRING + "] default name[java-native-android] default version[23.8.0]");
            }
            if (countlyConfig.context == null) {
                if (countlyConfig.application != null) {
                    this.L.d("[Init] No explicit context provided. Using context from the provided application class");
                    countlyConfig.context = countlyConfig.application;
                } else {
                    throw new IllegalArgumentException("valid context is required in Countly init, but was provided 'null'");
                }
            } else {
                this.L.d("[Init] Using explicitly provided context");
            }
            if (!UtilsNetworking.isValidURL(countlyConfig.serverURL)) {
                throw new IllegalArgumentException("valid serverURL is required");
            }
            if (countlyConfig.serverURL.charAt(countlyConfig.serverURL.length() - 1) == '/') {
                this.L.v("[Init] Removing trailing '/' from provided server url");
                countlyConfig.serverURL = countlyConfig.serverURL.substring(0, countlyConfig.serverURL.length() - 1);
            }
            if (countlyConfig.appKey == null || countlyConfig.appKey.length() == 0) {
                throw new IllegalArgumentException("valid appKey is required, but was provided either 'null' or empty String");
            }
            if (countlyConfig.application == null) {
                this.L.w("[Init] Initialising the SDK without providing the application class. Some functionality will not work.");
            }
            this.applicationClassProvided = countlyConfig.application != null;
            if (countlyConfig.deviceID != null && countlyConfig.deviceID.length() == 0) {
                throw new IllegalArgumentException("valid deviceID is required, but was provided as empty String");
            }
            this.L.d("[Init] SDK initialised with the URL:[" + countlyConfig.serverURL + "] and the appKey:[" + countlyConfig.appKey + "]");
            if (this.L.logEnabled()) {
                this.L.i("[Init] Checking init parameters");
                Class<? super Object> superclass = countlyConfig.context.getClass().getSuperclass();
                String str = "[Init] Provided Context [" + countlyConfig.context.getClass().getSimpleName() + "]";
                if (superclass != null) {
                    str = str + ", it's superclass: [" + superclass.getSimpleName() + "]";
                }
                this.L.i(str);
            }
            this.context_ = countlyConfig.context.getApplicationContext();
            if (!this.sdkIsInitialised) {
                this.L.d("[Init] About to init internal systems");
                this.config_ = countlyConfig;
                if (countlyConfig.maxKeyLength != null) {
                    if (countlyConfig.maxKeyLength.intValue() < 1) {
                        countlyConfig.maxKeyLength = 1;
                        this.L.w("[Init] provided 'maxKeyLength' is less than '1'. Setting it to '1'.");
                    }
                    this.L.i("[Init] provided 'maxKeyLength' override:[" + countlyConfig.maxKeyLength + "]");
                } else {
                    countlyConfig.maxKeyLength = 128;
                }
                if (countlyConfig.maxValueSize != null) {
                    if (countlyConfig.maxValueSize.intValue() < 1) {
                        countlyConfig.maxValueSize = 1;
                        this.L.w("[Init] provided 'maxValueSize' is less than '1'. Setting it to '1'.");
                    }
                    this.L.i("[Init] provided 'maxValueSize' override:[" + countlyConfig.maxValueSize + "]");
                } else {
                    countlyConfig.maxValueSize = 256;
                }
                if (countlyConfig.maxSegmentationValues != null) {
                    if (countlyConfig.maxSegmentationValues.intValue() < 1) {
                        countlyConfig.maxSegmentationValues = 1;
                        this.L.w("[Init] provided 'maxSegmentationValues' is less than '1'. Setting it to '1'.");
                    }
                    this.L.i("[Init] provided 'maxSegmentationValues' override:[" + countlyConfig.maxSegmentationValues + "]");
                } else {
                    countlyConfig.maxSegmentationValues = 100;
                }
                if (countlyConfig.maxBreadcrumbCount != null) {
                    if (countlyConfig.maxBreadcrumbCount.intValue() < 1) {
                        countlyConfig.maxBreadcrumbCount = 1;
                        this.L.w("[Init] provided 'maxBreadcrumbCount' is less than '1'. Setting it to '1'.");
                    }
                    this.L.i("[Init] provided 'maxBreadcrumbCount' override:[" + countlyConfig.maxBreadcrumbCount + "]");
                } else {
                    countlyConfig.maxBreadcrumbCount = 100;
                }
                if (countlyConfig.maxStackTraceLinesPerThread != null) {
                    if (countlyConfig.maxStackTraceLinesPerThread.intValue() < 1) {
                        countlyConfig.maxStackTraceLinesPerThread = 1;
                        this.L.w("[Init] provided 'maxStackTraceLinesPerThread' is less than '1'. Setting it to '1'.");
                    }
                    this.L.i("[Init] provided 'maxStackTraceLinesPerThread' override:[" + countlyConfig.maxStackTraceLinesPerThread + "]");
                } else {
                    countlyConfig.maxStackTraceLinesPerThread = 30;
                }
                if (countlyConfig.maxStackTraceLineLength != null) {
                    if (countlyConfig.maxStackTraceLineLength.intValue() < 1) {
                        countlyConfig.maxStackTraceLineLength = 1;
                        this.L.w("[Init] provided 'maxStackTraceLineLength' is less than '1'. Setting it to '1'.");
                    }
                    this.L.i("[Init] provided 'maxStackTraceLineLength' override:[" + countlyConfig.maxStackTraceLineLength + "]");
                } else {
                    countlyConfig.maxStackTraceLineLength = 200;
                }
                if (countlyConfig.sessionUpdateTimerDelay != null) {
                    this.L.d("[Init] Setting custom session update timer delay, [" + countlyConfig.sessionUpdateTimerDelay + "]");
                    startTimerService(this.timerService_, this.timerFuture, (long) countlyConfig.sessionUpdateTimerDelay.intValue());
                }
                if (countlyConfig.explicitStorageModeEnabled) {
                    this.L.i("[Init] Explicit storage mode is being enabled");
                }
                if (countlyConfig.countlyStore != null) {
                    this.countlyStore = countlyConfig.countlyStore;
                } else {
                    CountlyStore countlyStore = new CountlyStore(countlyConfig.context, this.L, countlyConfig.explicitStorageModeEnabled);
                    this.countlyStore = countlyStore;
                    countlyConfig.setCountlyStore(countlyStore);
                }
                if (countlyConfig.maxRequestQueueSize < 1) {
                    this.L.e("[Init] provided request queue size is less than 1. Replacing it with 1.");
                    countlyConfig.maxRequestQueueSize = 1;
                }
                this.L.d("[Init] request queue size set to [" + countlyConfig.maxRequestQueueSize + "]");
                this.countlyStore.setLimits(countlyConfig.maxRequestQueueSize);
                if (countlyConfig.storageProvider == null) {
                    countlyConfig.storageProvider = countlyConfig.countlyStore;
                } else {
                    this.L.d("[Init] Custom event storage provider was provided");
                }
                if (countlyConfig.eventQueueProvider == null) {
                    countlyConfig.eventQueueProvider = this.countlyStore;
                } else {
                    this.L.d("[Init] Custom event queue provider was provided");
                }
                if (countlyConfig.requestQueueProvider == null) {
                    countlyConfig.requestQueueProvider = this.connectionQueue_;
                } else {
                    this.L.d("[Init] Custom request queue provider was provided");
                }
                if (countlyConfig.safeViewIDGenerator == null) {
                    countlyConfig.safeViewIDGenerator = new SafeIDGenerator() { // from class: ly.count.android.sdk.Countly.2
                        @Override // ly.count.android.sdk.SafeIDGenerator
                        public String GenerateValue() {
                            return Utils.safeRandomVal();
                        }
                    };
                }
                if (countlyConfig.safeEventIDGenerator == null) {
                    countlyConfig.safeEventIDGenerator = new SafeIDGenerator() { // from class: ly.count.android.sdk.Countly.3
                        @Override // ly.count.android.sdk.SafeIDGenerator
                        public String GenerateValue() {
                            return Utils.safeRandomVal();
                        }
                    };
                }
                if (countlyConfig.immediateRequestGenerator == null) {
                    countlyConfig.immediateRequestGenerator = new ImmediateRequestGenerator() { // from class: ly.count.android.sdk.Countly.4
                        @Override // ly.count.android.sdk.ImmediateRequestGenerator
                        public ImmediateRequestI CreateImmediateRequestMaker() {
                            return new ImmediateRequestMaker();
                        }
                    };
                }
                if (countlyConfig.metricProviderOverride != null) {
                    this.L.d("[Init] Custom metric provider was provided");
                }
                countlyConfig.deviceInfo = new DeviceInfo(countlyConfig.metricProviderOverride);
                if (countlyConfig.tamperingProtectionSalt != null) {
                    this.L.d("[Init] Parameter tampering protection salt set");
                }
                if (this.connectionQueue_ == null) {
                    this.L.e("[Init] SDK failed to initialize because the connection queue failed to be created");
                    return this;
                }
                if (this.locationFallback != null && countlyConfig.locationCountyCode == null && countlyConfig.locationCity == null && countlyConfig.locationLocation == null && countlyConfig.locationIpAddress == null) {
                    countlyConfig.locationCountyCode = this.locationFallback[0];
                    countlyConfig.locationCity = this.locationFallback[1];
                    countlyConfig.locationLocation = this.locationFallback[2];
                    countlyConfig.locationIpAddress = this.locationFallback[3];
                }
                try {
                    HashMap map = new HashMap();
                    map.put(MigrationHelper.key_from_0_to_1_custom_id_set, Boolean.valueOf(countlyConfig.deviceID != null));
                    new MigrationHelper(countlyConfig.storageProvider, this.L, this.context_).doWork(map);
                    this.moduleHealthCheck = new ModuleHealthCheck(this, countlyConfig);
                    this.moduleConfiguration = new ModuleConfiguration(this, countlyConfig);
                    this.moduleRequestQueue = new ModuleRequestQueue(this, countlyConfig);
                    this.moduleConsent = new ModuleConsent(this, countlyConfig);
                    this.moduleDeviceId = new ModuleDeviceId(this, countlyConfig);
                    this.moduleCrash = new ModuleCrash(this, countlyConfig);
                    this.moduleEvents = new ModuleEvents(this, countlyConfig);
                    this.moduleUserProfile = new ModuleUserProfile(this, countlyConfig);
                    this.moduleViews = new ModuleViews(this, countlyConfig);
                    this.moduleRatings = new ModuleRatings(this, countlyConfig);
                    this.moduleSessions = new ModuleSessions(this, countlyConfig);
                    this.moduleRemoteConfig = new ModuleRemoteConfig(this, countlyConfig);
                    this.moduleAPM = new ModuleAPM(this, countlyConfig);
                    this.moduleLocation = new ModuleLocation(this, countlyConfig);
                    this.moduleFeedback = new ModuleFeedback(this, countlyConfig);
                    this.moduleAttribution = new ModuleAttribution(this, countlyConfig);
                    this.modules.clear();
                    this.modules.add(this.moduleConfiguration);
                    this.modules.add(this.moduleRequestQueue);
                    this.modules.add(this.moduleConsent);
                    this.modules.add(this.moduleDeviceId);
                    this.modules.add(this.moduleCrash);
                    this.modules.add(this.moduleEvents);
                    this.modules.add(this.moduleUserProfile);
                    this.modules.add(this.moduleViews);
                    this.modules.add(this.moduleRatings);
                    this.modules.add(this.moduleSessions);
                    this.modules.add(this.moduleRemoteConfig);
                    this.modules.add(this.moduleAPM);
                    this.modules.add(this.moduleLocation);
                    this.modules.add(this.moduleFeedback);
                    this.modules.add(this.moduleAttribution);
                    this.modules.add(this.moduleHealthCheck);
                    if (countlyConfig.testModuleListener != null) {
                        this.modules.add(countlyConfig.testModuleListener);
                    }
                    this.moduleConfiguration.consentProvider = countlyConfig.consentProvider;
                    this.moduleRequestQueue.consentProvider = countlyConfig.consentProvider;
                    this.moduleHealthCheck.consentProvider = countlyConfig.consentProvider;
                    this.moduleRequestQueue.deviceIdProvider = countlyConfig.deviceIdProvider;
                    this.moduleConsent.eventProvider = countlyConfig.eventProvider;
                    this.moduleConsent.deviceIdProvider = countlyConfig.deviceIdProvider;
                    this.moduleDeviceId.eventProvider = countlyConfig.eventProvider;
                    this.moduleCrash.eventProvider = countlyConfig.eventProvider;
                    this.moduleEvents.viewIdProvider = countlyConfig.viewIdProvider;
                    this.baseInfoProvider = countlyConfig.baseInfoProvider;
                    this.requestQueueProvider = countlyConfig.requestQueueProvider;
                    this.L.setHealthChecker(countlyConfig.healthTracker);
                    this.L.i("[Init] Finished initialising modules");
                    if (countlyConfig.customNetworkRequestHeaders != null) {
                        this.L.i("[Countly] Calling addCustomNetworkRequestHeaders");
                        Map<String, String> map2 = countlyConfig.customNetworkRequestHeaders;
                        this.requestHeaderCustomValues = map2;
                        this.connectionQueue_.setRequestHeaderCustomValues(map2);
                    }
                    if (countlyConfig.httpPostForced) {
                        this.L.d("[Init] Setting HTTP POST to be forced");
                        this.isHttpPostForced = countlyConfig.httpPostForced;
                    }
                    if (countlyConfig.tamperingProtectionSalt != null) {
                        this.L.d("[Init] Enabling tamper protection");
                        ConnectionProcessor.salt = countlyConfig.tamperingProtectionSalt;
                    }
                    if (countlyConfig.pushIntentAddMetadata) {
                        this.L.d("[Init] Enabling push intent metadata");
                        this.addMetadataToPushIntents = countlyConfig.pushIntentAddMetadata;
                    }
                    if (countlyConfig.eventQueueSizeThreshold != null) {
                        this.L.d("[Init] Setting event queue size: [" + countlyConfig.eventQueueSizeThreshold + "]");
                        if (countlyConfig.eventQueueSizeThreshold.intValue() < 1) {
                            this.L.d("[Init] queue size can't be less than zero");
                            countlyConfig.eventQueueSizeThreshold = 1;
                        }
                        EVENT_QUEUE_SIZE_THRESHOLD = countlyConfig.eventQueueSizeThreshold.intValue();
                    }
                    if (countlyConfig.publicKeyPinningCertificates != null) {
                        sharedInstance().L.i("[Init] Enabling public key pinning");
                        publicKeyPinCertificates = countlyConfig.publicKeyPinningCertificates;
                    }
                    if (countlyConfig.certificatePinningCertificates != null) {
                        sharedInstance().L.i("[Init] Enabling certificate pinning");
                        certificatePinCertificates = countlyConfig.certificatePinningCertificates;
                    }
                    this.connectionQueue_.L = this.L;
                    this.connectionQueue_.healthTracker = countlyConfig.healthTracker;
                    this.connectionQueue_.configProvider = countlyConfig.configProvider;
                    this.connectionQueue_.consentProvider = this.moduleConsent;
                    this.connectionQueue_.moduleRequestQueue = this.moduleRequestQueue;
                    this.connectionQueue_.deviceInfo = countlyConfig.deviceInfo;
                    this.connectionQueue_.setStorageProvider(countlyConfig.storageProvider);
                    this.connectionQueue_.setupSSLContext();
                    this.connectionQueue_.setBaseInfoProvider(countlyConfig.baseInfoProvider);
                    this.connectionQueue_.setDeviceId(countlyConfig.deviceIdProvider);
                    this.connectionQueue_.setRequestHeaderCustomValues(this.requestHeaderCustomValues);
                    this.connectionQueue_.setMetricOverride(countlyConfig.metricOverride);
                    this.connectionQueue_.setContext(this.context_);
                    this.connectionQueue_.requestInfoProvider = new RequestInfoProvider() { // from class: ly.count.android.sdk.Countly.5
                        @Override // ly.count.android.sdk.RequestInfoProvider
                        public boolean isHttpPostForced() {
                            return Countly.this.requestQueue().isHttpPostForced();
                        }

                        @Override // ly.count.android.sdk.RequestInfoProvider
                        public boolean isDeviceAppCrawler() {
                            return Countly.this.requestQueue().isDeviceAppCrawler();
                        }

                        @Override // ly.count.android.sdk.RequestInfoProvider
                        public boolean ifShouldIgnoreCrawlers() {
                            return Countly.this.requestQueue().ifShouldIgnoreCrawlers();
                        }
                    };
                    this.sdkIsInitialised = true;
                    if (countlyConfig.application != null) {
                        countlyConfig.application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: ly.count.android.sdk.Countly.6
                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public void onActivityCreated(Activity activity, Bundle bundle) {
                                if (Countly.this.L.logEnabled()) {
                                    Countly.this.L.d("[Countly] onActivityCreated, " + activity.getClass().getSimpleName());
                                }
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public void onActivityStarted(Activity activity) {
                                if (Countly.this.L.logEnabled()) {
                                    Countly.this.L.d("[Countly] onActivityStarted, " + activity.getClass().getSimpleName());
                                }
                                Countly.this.onStartInternal(activity);
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public void onActivityResumed(Activity activity) {
                                if (Countly.this.L.logEnabled()) {
                                    Countly.this.L.d("[Countly] onActivityResumed, " + activity.getClass().getSimpleName());
                                }
                                Iterator<ModuleBase> it = Countly.this.modules.iterator();
                                while (it.hasNext()) {
                                    it.next().callbackOnActivityResumed(activity);
                                }
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public void onActivityPaused(Activity activity) {
                                if (Countly.this.L.logEnabled()) {
                                    Countly.this.L.d("[Countly] onActivityPaused, " + activity.getClass().getSimpleName());
                                }
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public void onActivityStopped(Activity activity) {
                                if (Countly.this.L.logEnabled()) {
                                    Countly.this.L.d("[Countly] onActivityStopped, " + activity.getClass().getSimpleName());
                                }
                                Countly.this.onStopInternal();
                                Iterator<ModuleBase> it = Countly.this.modules.iterator();
                                while (it.hasNext()) {
                                    it.next().callbackOnActivityStopped(activity);
                                }
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                                if (Countly.this.L.logEnabled()) {
                                    Countly.this.L.d("[Countly] onActivitySaveInstanceState, " + activity.getClass().getSimpleName());
                                }
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public void onActivityDestroyed(Activity activity) {
                                if (Countly.this.L.logEnabled()) {
                                    Countly.this.L.d("[Countly] onActivityDestroyed, " + activity.getClass().getSimpleName());
                                }
                            }
                        });
                        countlyConfig.application.registerComponentCallbacks(new ComponentCallbacks() { // from class: ly.count.android.sdk.Countly.7
                            @Override // android.content.ComponentCallbacks
                            public void onConfigurationChanged(Configuration configuration) {
                                Countly.this.L.d("[Countly] ComponentCallbacks, onConfigurationChanged");
                                Countly.this.onConfigurationChangedInternal(configuration);
                            }

                            @Override // android.content.ComponentCallbacks
                            public void onLowMemory() {
                                Countly.this.L.d("[Countly] ComponentCallbacks, onLowMemory");
                            }
                        });
                    } else {
                        this.L.d("[Countly] Global activity listeners not registred due to no Application class");
                    }
                    if (lifecycleStateAtLeastStarted()) {
                        this.L.d("[Countly] SDK detects that the app is in the foreground. Increasing the activity counter.");
                        this.activityCount_++;
                    }
                    this.L.i("[Init] About to call module 'initFinished'");
                    Iterator<ModuleBase> it = this.modules.iterator();
                    while (it.hasNext()) {
                        it.next().initFinished(countlyConfig);
                    }
                    this.L.i("[Init] Finished initialising SDK");
                } catch (Exception unused) {
                    this.L.e("[Init] SDK failed while performing data migration. SDK is not capable to initialize.");
                    return this;
                }
            } else {
                this.L.i("[Init] Getting in the 'else' block");
                this.connectionQueue_.setContext(this.context_);
            }
            return this;
        } catch (Throwable th) {
            throw th;
        }
    }

    public boolean isInitialized() {
        return this.sdkIsInitialised;
    }

    boolean lifecycleStateAtLeastStarted() {
        return ProcessLifecycleOwner.get().getLifecycle().getState().isAtLeast(Lifecycle.State.STARTED);
    }

    public synchronized void halt() {
        this.L.i("Halting Countly!");
        this.sdkIsInitialised = false;
        this.L.SetListener(null);
        if (this.connectionQueue_ != null) {
            CountlyStore countlyStore = this.countlyStore;
            if (countlyStore != null) {
                countlyStore.clear();
            }
            this.connectionQueue_.setContext(null);
            this.connectionQueue_ = null;
        }
        this.activityCount_ = 0;
        Iterator<ModuleBase> it = this.modules.iterator();
        while (it.hasNext()) {
            it.next().halt();
        }
        this.modules.clear();
        this.moduleCrash = null;
        this.moduleViews = null;
        this.moduleEvents = null;
        this.moduleRatings = null;
        this.moduleSessions = null;
        this.moduleRemoteConfig = null;
        this.moduleConsent = null;
        this.moduleAPM = null;
        this.moduleDeviceId = null;
        this.moduleLocation = null;
        this.moduleFeedback = null;
        this.moduleRequestQueue = null;
        this.moduleConfiguration = null;
        this.moduleHealthCheck = null;
        this.COUNTLY_SDK_VERSION_STRING = "23.8.0";
        this.COUNTLY_SDK_NAME = "java-native-android";
        staticInit();
    }

    synchronized void notifyDeviceIdChange() {
        this.L.d("Notifying modules that device ID changed");
        Iterator<ModuleBase> it = this.modules.iterator();
        while (it.hasNext()) {
            it.next().deviceIdChanged();
        }
    }

    void onStartInternal(Activity activity) {
        String simpleName;
        if (this.L.logEnabled()) {
            if (activity == null) {
                simpleName = "NULL ACTIVITY PROVIDED";
            } else {
                simpleName = activity.getClass().getSimpleName();
            }
            this.L.d("Countly onStartInternal called, name:[" + simpleName + "], [" + this.activityCount_ + "] -> [" + (this.activityCount_ + 1) + "] activities now open");
        }
        int i = this.activityCount_ + 1;
        this.activityCount_ = i;
        if (i == 1 && !this.moduleSessions.manualSessionControlEnabled) {
            this.moduleSessions.beginSessionInternal();
        }
        this.config_.deviceInfo.inForeground();
        Iterator<ModuleBase> it = this.modules.iterator();
        while (it.hasNext()) {
            it.next().onActivityStarted(activity, this.activityCount_);
        }
        this.calledAtLeastOnceOnStart = true;
    }

    void onStopInternal() {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("Countly onStopInternal called, [");
        sb.append(this.activityCount_);
        sb.append("] -> [");
        sb.append(this.activityCount_ - 1);
        sb.append("] activities now open");
        moduleLog.d(sb.toString());
        int i = this.activityCount_;
        if (i == 0) {
            this.L.e("must call onStart before onStop");
            return;
        }
        int i2 = i - 1;
        this.activityCount_ = i2;
        if (i2 == 0 && !this.moduleSessions.manualSessionControlEnabled) {
            this.moduleSessions.endSessionInternal(null);
        }
        this.config_.deviceInfo.inBackground();
        Iterator<ModuleBase> it = this.modules.iterator();
        while (it.hasNext()) {
            it.next().onActivityStopped(this.activityCount_);
        }
    }

    public synchronized void onConfigurationChangedInternal(Configuration configuration) {
        this.L.i("Calling [onConfigurationChangedInternal]");
        Iterator<ModuleBase> it = this.modules.iterator();
        while (it.hasNext()) {
            it.next().onConfigurationChanged(configuration);
        }
    }

    public synchronized void onStart(Activity activity) {
        if (!isInitialized()) {
            this.L.e("init must be called before onStart");
        } else if (this.applicationClassProvided) {
            this.L.w("Manual calls to 'onStart' will be ignored since the application class ir provided. SDK will handle these callbacks automatically");
        } else {
            onStartInternal(activity);
        }
    }

    public synchronized void onStop() {
        if (!isInitialized()) {
            this.L.e("init must be called before onStop");
        } else if (this.applicationClassProvided) {
            this.L.w("Manual calls to 'onStart' will be ignored since the application class ir provided. SDK will handle these callbacks automatically");
        } else {
            onStopInternal();
        }
    }

    public synchronized void onConfigurationChanged(Configuration configuration) {
        if (!isInitialized()) {
            this.L.e("init must be called before onConfigurationChanged");
        } else if (this.applicationClassProvided) {
            this.L.w("Manual calls to 'onConfigurationChanged' will be ignored since the application class ir provided. SDK will handle these callbacks automatically");
        } else {
            onConfigurationChangedInternal(configuration);
        }
    }

    synchronized void onTimer() {
        this.L.v("[onTimer] Calling heartbeat, Activity count:[" + this.activityCount_ + "]");
        if (isInitialized()) {
            if (this.activityCount_ > 0 && !this.moduleSessions.manualSessionControlEnabled) {
                this.moduleSessions.updateSessionInternal();
            } else if (this.moduleSessions.manualSessionControlEnabled && this.moduleSessions.manualSessionControlHybridModeEnabled && this.moduleSessions.sessionIsRunning()) {
                this.moduleSessions.updateSessionInternal();
            }
            this.moduleRequestQueue.sendEventsIfNeeded(true);
            this.requestQueueProvider.tick();
        }
    }

    public void onRegistrationId(String str, CountlyMessagingProvider countlyMessagingProvider) {
        CountlyMessagingProvider countlyMessagingProvider2;
        if (this.config_.consentProvider.getConsent(CountlyFeatureNames.push)) {
            if (!isInitialized()) {
                this.L.w("[onRegistrationId] Calling this before the SDK is initialized.");
            }
            long jCurrentTimestampMs = UtilsTime.currentTimestampMs();
            long j = jCurrentTimestampMs - this.lastRegistrationCallTs;
            String str2 = this.lastRegistrationCallID;
            if (str2 != null && str2.equals(str) && (countlyMessagingProvider2 = this.lastRegistrationCallProvider) != null && countlyMessagingProvider2 == countlyMessagingProvider && j < 60000) {
                this.L.w("[onRegistrationId] Calling this with the same values within the debounce interval. elapsedT:[" + j + "] ms");
                return;
            }
            this.lastRegistrationCallTs = jCurrentTimestampMs;
            this.lastRegistrationCallID = str;
            this.lastRegistrationCallProvider = countlyMessagingProvider;
            this.connectionQueue_.tokenSession(str, countlyMessagingProvider);
        }
    }

    public void setLoggingEnabled(boolean z) {
        this.enableLogging_ = z;
        this.L.d("Enabling logging");
    }

    public boolean isLoggingEnabled() {
        return this.enableLogging_;
    }

    public boolean hasBeenCalledOnStart() {
        return this.calledAtLeastOnceOnStart;
    }

    public ModuleCrash.Crashes crashes() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing crashes");
            return null;
        }
        return this.moduleCrash.crashesInterface;
    }

    public ModuleEvents.Events events() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing events");
            return null;
        }
        return this.moduleEvents.eventsInterface;
    }

    public ModuleViews.Views views() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing views");
            return null;
        }
        return this.moduleViews.viewsInterface;
    }

    public ModuleRatings.Ratings ratings() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing ratings");
            return null;
        }
        return this.moduleRatings.ratingsInterface;
    }

    public ModuleSessions.Sessions sessions() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing sessions");
            return null;
        }
        return this.moduleSessions.sessionInterface;
    }

    public ModuleRemoteConfig.RemoteConfig remoteConfig() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing remote config");
            return null;
        }
        return this.moduleRemoteConfig.remoteConfigInterface;
    }

    public ModuleAPM.Apm apm() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing apm");
            return null;
        }
        return this.moduleAPM.apmInterface;
    }

    public ModuleConsent.Consent consent() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing consent");
            return null;
        }
        return this.moduleConsent.consentInterface;
    }

    public ModuleLocation.Location location() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing location");
            return null;
        }
        return this.moduleLocation.locationInterface;
    }

    public ModuleFeedback.Feedback feedback() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing feedback");
            return null;
        }
        return this.moduleFeedback.feedbackInterface;
    }

    public ModuleRequestQueue.RequestQueue requestQueue() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing request queue");
            return null;
        }
        return this.moduleRequestQueue.requestQueueInterface;
    }

    public ModuleAttribution.Attribution attribution() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing attribution");
            return null;
        }
        return this.moduleAttribution.attributionInterface;
    }

    public ModuleDeviceId.DeviceId deviceId() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing deviceId");
            return null;
        }
        return this.moduleDeviceId.deviceIdInterface;
    }

    public ModuleUserProfile.UserProfile userProfile() {
        if (!isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before accessing user profile");
            return null;
        }
        return this.moduleUserProfile.userProfileInterface;
    }

    ConnectionQueue getConnectionQueue() {
        return this.connectionQueue_;
    }

    ExecutorService getTimerService() {
        return this.timerService_;
    }

    long getPrevSessionDurationStartTime() {
        return this.moduleSessions.prevSessionDurationStartTime_;
    }

    void setPrevSessionDurationStartTime(long j) {
        this.moduleSessions.prevSessionDurationStartTime_ = j;
    }

    int getActivityCount() {
        return this.activityCount_;
    }

    synchronized boolean getDisableUpdateSessionRequests() {
        return this.disableUpdateSessionRequests_;
    }
}
