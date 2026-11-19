package ly.count.android.sdk;

import android.app.Activity;
import android.content.res.Configuration;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
abstract class ModuleBase {
    ModuleLog L;
    final Countly _cly;
    BaseInfoProvider baseInfoProvider;
    ConfigurationProvider configProvider;
    ConsentProvider consentProvider;
    DeviceIdProvider deviceIdProvider;
    DeviceInfo deviceInfo;
    EventProvider eventProvider;
    HealthTracker healthTracker;
    RequestQueueProvider requestQueueProvider;
    StorageProvider storageProvider;
    ViewIdProvider viewIdProvider;

    void callbackOnActivityResumed(Activity activity) {
    }

    void callbackOnActivityStopped(Activity activity) {
    }

    void deviceIdChanged() {
    }

    void initFinished(CountlyConfig countlyConfig) {
    }

    void onActivityStarted(Activity activity, int i) {
    }

    void onActivityStopped(int i) {
    }

    void onConfigurationChanged(Configuration configuration) {
    }

    void onConsentChanged(List<String> list, boolean z, ModuleConsent.ConsentChangeSource consentChangeSource) {
    }

    void sdkConfigurationChanged() {
    }

    ModuleBase(Countly countly, CountlyConfig countlyConfig) {
        this._cly = countly;
        this.L = countly.L;
        this.consentProvider = countlyConfig.consentProvider;
        this.storageProvider = countlyConfig.storageProvider;
        this.eventProvider = countlyConfig.eventProvider;
        this.requestQueueProvider = countlyConfig.requestQueueProvider;
        this.deviceIdProvider = countlyConfig.deviceIdProvider;
        this.baseInfoProvider = countlyConfig.baseInfoProvider;
        this.viewIdProvider = countlyConfig.viewIdProvider;
        this.configProvider = countlyConfig.configProvider;
        this.healthTracker = countlyConfig.healthTracker;
        this.deviceInfo = countlyConfig.deviceInfo;
    }

    void halt() {
        throw new UnsupportedOperationException();
    }
}
