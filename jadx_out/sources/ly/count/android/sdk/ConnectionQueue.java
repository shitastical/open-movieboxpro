package ly.count.android.sdk;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: classes6.dex */
class ConnectionQueue implements RequestQueueProvider {
    protected ModuleLog L;
    BaseInfoProvider baseInfoProvider;
    ConfigurationProvider configProvider;
    private Future<?> connectionProcessorFuture_;
    protected ConsentProvider consentProvider;
    private Context context_;
    private DeviceIdProvider deviceIdProvider_;
    private ExecutorService executor_;
    HealthTracker healthTracker;
    private Map<String, String> requestHeaderCustomValues;
    RequestInfoProvider requestInfoProvider;
    private SSLContext sslContext_;
    StorageProvider storageProvider;
    Map<String, String> metricOverride = null;
    protected ModuleRequestQueue moduleRequestQueue = null;
    protected DeviceInfo deviceInfo = null;

    ConnectionQueue() {
    }

    void setBaseInfoProvider(BaseInfoProvider baseInfoProvider) {
        this.baseInfoProvider = baseInfoProvider;
    }

    void setStorageProvider(StorageProvider storageProvider) {
        this.storageProvider = storageProvider;
    }

    StorageProvider getStorageProvider() {
        return this.storageProvider;
    }

    Context getContext() {
        return this.context_;
    }

    void setContext(Context context) {
        this.context_ = context;
    }

    void setupSSLContext() {
        if (Countly.publicKeyPinCertificates == null && Countly.certificatePinCertificates == null) {
            this.sslContext_ = null;
            return;
        }
        try {
            TrustManager[] trustManagerArr = {new CertificateTrustManager(Countly.publicKeyPinCertificates, Countly.certificatePinCertificates)};
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            this.sslContext_ = sSLContext;
            sSLContext.init(null, trustManagerArr, null);
        } catch (Throwable th) {
            throw new IllegalStateException(th);
        }
    }

    public void setDeviceId(DeviceIdProvider deviceIdProvider) {
        this.deviceIdProvider_ = deviceIdProvider;
    }

    protected void setRequestHeaderCustomValues(Map<String, String> map) {
        this.requestHeaderCustomValues = map;
    }

    protected void setMetricOverride(Map<String, String> map) {
        if (this.L.logEnabled()) {
            if (map != null) {
                this.L.d("[Connection Queue] The following metric overrides are set:");
                for (String str : map.keySet()) {
                    this.L.d("[Connection Queue] key[" + str + "] val[" + map.get(str) + "]");
                }
            } else {
                this.L.d("[Connection Queue] No metric override is provided");
            }
        }
        this.metricOverride = map;
    }

    boolean checkInternalState() {
        if (this.context_ == null) {
            ModuleLog moduleLog = this.L;
            if (moduleLog != null) {
                moduleLog.e("[Connection Queue] context has not been set");
            }
            return false;
        }
        if (this.baseInfoProvider.getAppKey() == null || this.baseInfoProvider.getAppKey().length() == 0) {
            ModuleLog moduleLog2 = this.L;
            if (moduleLog2 != null) {
                moduleLog2.e("[Connection Queue] app key has not been set");
            }
            return false;
        }
        if (this.storageProvider == null) {
            ModuleLog moduleLog3 = this.L;
            if (moduleLog3 != null) {
                moduleLog3.e("[Connection Queue] countly storage provider has not been set");
            }
            return false;
        }
        if (this.baseInfoProvider.getServerURL() == null || !UtilsNetworking.isValidURL(this.baseInfoProvider.getServerURL())) {
            ModuleLog moduleLog4 = this.L;
            if (moduleLog4 != null) {
                moduleLog4.e("[Connection Queue] server URL is not valid");
            }
            return false;
        }
        if (Countly.publicKeyPinCertificates == null || this.baseInfoProvider.getServerURL().startsWith(IDataSource.SCHEME_HTTPS_TAG)) {
            return true;
        }
        ModuleLog moduleLog5 = this.L;
        if (moduleLog5 != null) {
            moduleLog5.e("[Connection Queue] server must start with https once you specified public keys");
        }
        return false;
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void beginSession(boolean z, String str, String str2, String str3, String str4, String str5) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] beginSession");
            if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
                String str6 = prepareCommonRequestData() + "&begin_session=1&metrics=" + str5;
                String strPrepareLocationData = prepareLocationData(z, str, str2, str3, str4);
                if (!strPrepareLocationData.isEmpty()) {
                    str6 = str6 + strPrepareLocationData;
                }
                Countly.sharedInstance().isBeginSessionSent = true;
                addRequestToQueue(str6, false);
                tick();
            }
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void updateSession(int i) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] updateSession");
            if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions) && i > 0) {
                addRequestToQueue(prepareCommonRequestData() + "&session_duration=" + i, false);
                tick();
            }
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void changeDeviceId(String str, int i) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] changeDeviceId");
            String strPrepareCommonRequestData = prepareCommonRequestData();
            if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
                strPrepareCommonRequestData = strPrepareCommonRequestData + "&session_duration=" + i;
            }
            addRequestToQueue(strPrepareCommonRequestData + "&device_id=" + UtilsNetworking.urlEncodeString(str), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void tokenSession(String str, Countly.CountlyMessagingProvider countlyMessagingProvider) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] tokenSession");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.push)) {
                this.L.d("[Connection Queue] request ignored, 'push' consent not given");
                return;
            }
            final String str2 = prepareCommonRequestData() + "&token_session=1&android_token=" + UtilsNetworking.urlEncodeString(str) + "&token_provider=" + countlyMessagingProvider + "&locale=" + UtilsNetworking.urlEncodeString(this.deviceInfo.mp.getLocale());
            this.L.d("[Connection Queue] Waiting for 10 seconds before adding token request to queue");
            Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() { // from class: ly.count.android.sdk.ConnectionQueue.1
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionQueue.this.L.d("[Connection Queue] Finished waiting 10 seconds adding token request");
                    ConnectionQueue.this.addRequestToQueue(str2, false);
                    ConnectionQueue.this.tick();
                }
            }, 10L, TimeUnit.SECONDS);
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void endSession(int i) {
        endSession(i, null);
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void endSession(int i, String str) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] endSession");
            if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
                String str2 = prepareCommonRequestData() + "&end_session=1";
                if (i > 0) {
                    str2 = str2 + "&session_duration=" + i;
                }
                if (str != null) {
                    str2 = str2 + "&override_id=" + UtilsNetworking.urlEncodeString(str);
                }
                addRequestToQueue(str2, false);
                tick();
            }
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendLocation(boolean z, String str, String str2, String str3, String str4) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendLocation");
            addRequestToQueue(prepareCommonRequestData() + prepareLocationData(z, str, str2, str3, str4), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendUserData(String str) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendUserData");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.users)) {
                this.L.d("[Connection Queue] request ignored, 'user' consent not given");
                return;
            }
            if (str.equals("")) {
                this.L.d("[Connection Queue] No user data to send, skipping");
                return;
            }
            this.moduleRequestQueue.sendEventsIfNeeded(true);
            addRequestToQueue(prepareCommonRequestData() + str, false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendIndirectAttribution(String str) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendIndirectAttribution");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.attribution)) {
                this.L.d("[Connection Queue] request ignored, 'attribution' consent not given");
                return;
            }
            if (str.isEmpty()) {
                this.L.e("[Connection Queue] provided attribution ID is not valid, aborting");
                return;
            }
            addRequestToQueue(prepareCommonRequestData() + ("&aid=" + UtilsNetworking.urlEncodeString(str)), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendDirectAttributionTest(String str) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendDirectAttributionTest");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.attribution)) {
                this.L.d("[Connection Queue] request ignored, 'attribution' consent not given");
                return;
            }
            if (str.isEmpty()) {
                this.L.w("[Connection Queue] sendDirectAttributionTest, attribution not sent, data is empty");
                return;
            }
            addRequestToQueue(prepareCommonRequestData() + ("&attribution_data=" + UtilsNetworking.urlEncodeString(str)), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendDirectAttributionLegacy(String str, String str2) {
        String str3;
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendDirectAttributionLegacy");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.attribution)) {
                this.L.d("[Connection Queue] request ignored, 'attribution' consent not given");
                return;
            }
            if (str.isEmpty()) {
                str3 = "";
            } else {
                str3 = "&campaign_id=" + UtilsNetworking.urlEncodeString(str);
            }
            if (str2 != null && !str2.isEmpty()) {
                str3 = str3 + "&campaign_user=" + UtilsNetworking.urlEncodeString(str2);
            }
            if (str3.length() == 0) {
                this.L.w("[Connection Queue] sendDirectAttributionLegacy, attribution not sent, both campaign ID and user ID are either null or empty");
                return;
            }
            addRequestToQueue(prepareCommonRequestData() + str3, false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendCrashReport(String str, boolean z) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendCrashReport");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.crashes)) {
                this.L.d("[Connection Queue] request ignored, 'crashes' consent not given");
                return;
            }
            addRequestToQueue(prepareCommonRequestData() + "&crash=" + UtilsNetworking.urlEncodeString(str), !z);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendDirectRequest(Map<String, String> map) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendDirectRequest");
            if (!this.consentProvider.anyConsentGiven()) {
                this.L.d("[Connection Queue] request ignored, no consent given");
                return;
            }
            StringBuilder sb = new StringBuilder(prepareCommonRequestData());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(String.format("%s=%s", UtilsNetworking.urlEncodeString(entry.getKey()), UtilsNetworking.urlEncodeString(entry.getValue())));
            }
            addRequestToQueue(sb.toString(), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void recordEvents(String str) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendConsentChanges");
            addRequestToQueue(prepareCommonRequestData() + "&events=" + str, false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendConsentChanges(String str) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendConsentChanges");
            addRequestToQueue(prepareCommonRequestData() + "&consent=" + UtilsNetworking.urlEncodeString(str), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendAPMCustomTrace(String str, Long l, Long l2, Long l3, String str2) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendAPMCustomTrace");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.apm)) {
                this.L.d("[Connection Queue] request ignored, 'apm' consent not given");
                return;
            }
            addRequestToQueue(prepareCommonRequestData() + "&count=1&apm=" + UtilsNetworking.urlEncodeString("{\"type\":\"device\",\"name\":\"" + str + "\", \"apm_metrics\":{\"duration\": " + l + str2 + "}, \"stz\": " + l2 + ", \"etz\": " + l3 + "}"), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendAPMNetworkTrace(String str, Long l, int i, int i2, int i3, Long l2, Long l3) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendAPMNetworkTrace");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.apm)) {
                this.L.d("[Connection Queue] request ignored, 'apm' consent not given");
                return;
            }
            addRequestToQueue(prepareCommonRequestData() + "&count=1&apm=" + UtilsNetworking.urlEncodeString("{\"type\":\"network\",\"name\":\"" + str + "\", \"apm_metrics\":" + ("{\"response_time\": " + l + ", \"response_payload_size\":" + i3 + ", \"response_code\":" + i + ", \"request_payload_size\":" + i2 + "}") + ", \"stz\": " + l2 + ", \"etz\": " + l3 + "}"), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendAPMAppStart(long j, Long l, Long l2) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendAPMAppStart");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.apm)) {
                this.L.d("[Connection Queue] request ignored, consent not given");
                return;
            }
            addRequestToQueue(prepareCommonRequestData() + "&count=1&apm=" + UtilsNetworking.urlEncodeString("{\"type\":\"device\",\"name\":\"app_start\", \"apm_metrics\":{\"duration\": " + j + "}, \"stz\": " + l + ", \"etz\": " + l2 + "}"), false);
            tick();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void sendAPMScreenTime(boolean z, long j, Long l, Long l2) {
        if (checkInternalState()) {
            this.L.d("[Connection Queue] sendAPMScreenTime, recording foreground time: [" + z + "]");
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.apm)) {
                this.L.d("[Connection Queue] request ignored, 'apm' consent not given");
                return;
            }
            addRequestToQueue(prepareCommonRequestData() + "&count=1&apm=" + UtilsNetworking.urlEncodeString("{\"type\":\"device\",\"name\":\"" + (z ? "app_in_foreground" : "app_in_background") + "\", \"apm_metrics\":{\"duration\": " + j + "}, \"stz\": " + l + ", \"etz\": " + l2 + "}"), false);
            tick();
        }
    }

    String prepareCommonRequestData() {
        UtilsTime.Instant currentInstant = UtilsTime.getCurrentInstant();
        return "app_key=" + UtilsNetworking.urlEncodeString(this.baseInfoProvider.getAppKey()) + "&timestamp=" + currentInstant.timestampMs + "&hour=" + currentInstant.hour + "&dow=" + currentInstant.dow + "&tz=" + this.deviceInfo.mp.getTimezoneOffset() + "&sdk_version=" + Countly.sharedInstance().COUNTLY_SDK_VERSION_STRING + "&sdk_name=" + Countly.sharedInstance().COUNTLY_SDK_NAME;
    }

    String prepareCommonRequestDataShort() {
        return "app_key=" + UtilsNetworking.urlEncodeString(this.baseInfoProvider.getAppKey()) + "&timestamp=" + UtilsTime.getCurrentInstant().timestampMs + "&sdk_version=" + Countly.sharedInstance().COUNTLY_SDK_VERSION_STRING + "&sdk_name=" + Countly.sharedInstance().COUNTLY_SDK_NAME;
    }

    private String prepareLocationData(boolean z, String str, String str2, String str3, String str4) {
        String str5;
        if (z || !this.consentProvider.getConsent("location")) {
            return "&location=";
        }
        if (str3 != null && !str3.isEmpty()) {
            str5 = "&location=" + UtilsNetworking.urlEncodeString(str3);
        } else {
            str5 = "";
        }
        if (str2 != null && !str2.isEmpty()) {
            str5 = str5 + "&city=" + UtilsNetworking.urlEncodeString(str2);
        }
        if (str != null && !str.isEmpty()) {
            str5 = str5 + "&country_code=" + UtilsNetworking.urlEncodeString(str);
        }
        String str6 = str5;
        if (str4 == null || str4.isEmpty()) {
            return str6;
        }
        return str6 + "&ip=" + UtilsNetworking.urlEncodeString(str4);
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareRemoteConfigRequestLegacy(String str, String str2, String str3) {
        String str4 = prepareCommonRequestData() + "&method=fetch_remote_config&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
            str4 = str4 + "&metrics=" + str3;
        }
        if (str != null) {
            return str4 + "&keys=" + UtilsNetworking.urlEncodeString(str);
        }
        if (str2 == null) {
            return str4;
        }
        return str4 + "&omit_keys=" + UtilsNetworking.urlEncodeString(str2);
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareRemoteConfigRequest(String str, String str2, String str3, boolean z) {
        String str4 = prepareCommonRequestData() + "&method=rc&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
            str4 = str4 + "&metrics=" + str3;
        }
        if (str != null) {
            str4 = str4 + "&keys=" + UtilsNetworking.urlEncodeString(str);
        } else if (str2 != null) {
            str4 = str4 + "&omit_keys=" + UtilsNetworking.urlEncodeString(str2);
        }
        if (!z) {
            return str4;
        }
        return str4 + "&oi=1";
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareEnrollmentParameters(String[] strArr) {
        return "method=ab&keys=" + UtilsNetworking.encodedArrayBuilder(strArr) + "&app_key=" + UtilsNetworking.urlEncodeString(this.baseInfoProvider.getAppKey()) + "&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareRemovalParameters(String[] strArr) {
        String str = "method=ab_opt_out&app_key=" + UtilsNetworking.urlEncodeString(this.baseInfoProvider.getAppKey()) + "&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
        if (strArr.length <= 0) {
            return str;
        }
        return str + "&keys=" + UtilsNetworking.encodedArrayBuilder(strArr);
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareFetchAllVariants() {
        return "method=ab_fetch_variants&app_key=" + UtilsNetworking.urlEncodeString(this.baseInfoProvider.getAppKey()) + "&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareEnrollVariant(String str, String str2) {
        return "method=ab_enroll_variant&app_key=" + UtilsNetworking.urlEncodeString(this.baseInfoProvider.getAppKey()) + "&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId()) + "&key=" + UtilsNetworking.urlEncodeString(str) + "&variant=" + UtilsNetworking.urlEncodeString(str2);
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareRatingWidgetRequest(String str) {
        return prepareCommonRequestData() + "&widget_id=" + UtilsNetworking.urlEncodeString(str) + "&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareFeedbackListRequest() {
        return prepareCommonRequestData() + "&method=feedback&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareHealthCheckRequest(String str) {
        return (prepareCommonRequestData() + "&metrics=" + str) + "&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public String prepareServerConfigRequest() {
        return prepareCommonRequestDataShort() + "&method=sc&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
    }

    void ensureExecutor() {
        if (this.executor_ == null) {
            this.executor_ = Executors.newSingleThreadExecutor();
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public void tick() {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[Connection Queue] tick, Not empty:[");
        boolean z = true;
        sb.append(!isRequestQueueEmpty());
        sb.append("], Has processor:[");
        sb.append(this.connectionProcessorFuture_ == null);
        sb.append("], Done or null:[");
        Future<?> future = this.connectionProcessorFuture_;
        if (future != null && !future.isDone()) {
            z = false;
        }
        sb.append(z);
        sb.append("]");
        moduleLog.v(sb.toString());
        if (Countly.sharedInstance().isInitialized() && !isRequestQueueEmpty()) {
            Future<?> future2 = this.connectionProcessorFuture_;
            if (future2 == null || future2.isDone()) {
                ensureExecutor();
                this.connectionProcessorFuture_ = this.executor_.submit(createConnectionProcessor());
            }
        }
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public ConnectionProcessor createConnectionProcessor() {
        return new ConnectionProcessor(this.baseInfoProvider.getServerURL(), this.storageProvider, this.deviceIdProvider_, this.configProvider, this.requestInfoProvider, this.sslContext_, this.requestHeaderCustomValues, this.L, this.healthTracker);
    }

    @Override // ly.count.android.sdk.RequestQueueProvider
    public boolean queueContainsTemporaryIdItems() {
        for (String str : this.storageProvider.getRequests()) {
            if (str.contains("&device_id=CLYTemporaryDeviceID")) {
                return true;
            }
        }
        return false;
    }

    void addRequestToQueue(String str, boolean z) {
        this.storageProvider.addRequest(str, z);
    }

    boolean isRequestQueueEmpty() {
        return this.storageProvider.getRequestQueueRaw().length() <= 0;
    }

    ExecutorService getExecutor() {
        return this.executor_;
    }

    void setExecutor(ExecutorService executorService) {
        this.executor_ = executorService;
    }

    Future<?> getConnectionProcessorFuture() {
        return this.connectionProcessorFuture_;
    }

    void setConnectionProcessorFuture(Future<?> future) {
        this.connectionProcessorFuture_ = future;
    }
}
