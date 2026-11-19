package ly.count.android.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
class ModuleHealthCheck extends ModuleBase {
    HealthCheckCounter hCounter;
    boolean healthCheckEnabled;
    ImmediateRequestGenerator immediateRequestGenerator;

    ModuleHealthCheck(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.healthCheckEnabled = true;
        this.L.v("[ModuleHealthCheck] Initialising, enabled: " + this.healthCheckEnabled);
        HealthCheckCounter healthCheckCounter = new HealthCheckCounter(countlyConfig.storageProvider, this.L);
        this.hCounter = healthCheckCounter;
        countlyConfig.healthTracker = healthCheckCounter;
        this.immediateRequestGenerator = countlyConfig.immediateRequestGenerator;
        this.healthCheckEnabled = countlyConfig.healthCheckEnabled;
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (this.healthCheckEnabled) {
            sendHealthCheck();
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        this.hCounter = null;
        this.immediateRequestGenerator = null;
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onActivityStopped(int i) {
        this.hCounter.saveState();
    }

    void sendHealthCheck() {
        this.L.v("[ModuleHealthCheck] sendHealthCheck, attempting to send health information");
        StringBuilder sb = new StringBuilder(this.requestQueueProvider.prepareHealthCheckRequest(this.deviceInfo.getMetricsHealthCheck(this._cly.context_, this._cly.config_.metricOverride)));
        ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
        boolean networkingEnabled = connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled();
        sb.append(this.hCounter.createRequestParam());
        this.immediateRequestGenerator.CreateImmediateRequestMaker().doWork(sb.toString(), "/i", connectionProcessorCreateConnectionProcessor, false, networkingEnabled, new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleHealthCheck$$ExternalSyntheticLambda0
            @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
            public final void callback(JSONObject jSONObject) {
                this.f$0.m3508lambda$sendHealthCheck$0$lycountandroidsdkModuleHealthCheck(jSONObject);
            }
        }, this.L);
    }

    /* JADX INFO: renamed from: lambda$sendHealthCheck$0$ly-count-android-sdk-ModuleHealthCheck, reason: not valid java name */
    /* synthetic */ void m3508lambda$sendHealthCheck$0$lycountandroidsdkModuleHealthCheck(JSONObject jSONObject) {
        if (jSONObject == null) {
            this.L.w("[ModuleHealthCheck] No response for sending health check Probably due to lack of connection to the server");
            return;
        }
        this.L.d("[ModuleHealthCheck] Retrieved request response: [" + jSONObject.toString() + "]");
        if (!jSONObject.has("result")) {
            this.L.d("[ModuleHealthCheck] Retrieved request response does not match expected pattern");
        } else {
            this.L.d("[ModuleHealthCheck] sendHealthCheck, SDK health information sent successfully");
            this.hCounter.clearAndSave();
        }
    }
}
