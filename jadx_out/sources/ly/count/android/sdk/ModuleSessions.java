package ly.count.android.sdk;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleSessions extends ModuleBase {
    boolean manualSessionControlEnabled;
    boolean manualSessionControlHybridModeEnabled;
    Map<String, String> metricOverride;
    long prevSessionDurationStartTime_;
    final Sessions sessionInterface;

    ModuleSessions(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.manualSessionControlEnabled = false;
        this.manualSessionControlHybridModeEnabled = false;
        this.prevSessionDurationStartTime_ = 0L;
        this.metricOverride = null;
        this.L.v("[ModuleSessions] Initialising");
        this.metricOverride = countlyConfig.metricOverride;
        boolean z = countlyConfig.manualSessionControlEnabled;
        this.manualSessionControlEnabled = z;
        if (z) {
            this.L.d("[ModuleSessions] Enabling manual session control");
        }
        boolean z2 = countlyConfig.manualSessionControlHybridModeEnabled;
        this.manualSessionControlHybridModeEnabled = z2;
        if (z2) {
            this.L.d("[ModuleSessions] Enabling manual session control hybrid mode");
        }
        if (countlyConfig.disableUpdateSessionRequests) {
            this.L.d("[ModuleSessions] Disabling periodic session time updates");
            this._cly.disableUpdateSessionRequests_ = countlyConfig.disableUpdateSessionRequests;
        }
        this.sessionInterface = new Sessions();
    }

    void beginSessionInternal() {
        this.L.d("[ModuleSessions] 'beginSessionInternal'");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
            if (sessionIsRunning()) {
                this.L.d("[ModuleSessions] A session is already running, this 'beginSessionInternal' will be ignored");
            }
            String metrics = this.deviceInfo.getMetrics(this._cly.context_, this.metricOverride);
            this.prevSessionDurationStartTime_ = System.nanoTime();
            this.requestQueueProvider.beginSession(this._cly.moduleLocation.locationDisabled, this._cly.moduleLocation.locationCountryCode, this._cly.moduleLocation.locationCity, this._cly.moduleLocation.locationGpsCoordinates, this._cly.moduleLocation.locationIpAddress, metrics);
        }
    }

    void updateSessionInternal() {
        this.L.d("[ModuleSessions] 'updateSessionInternal'");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
            if (!sessionIsRunning()) {
                this.L.d("[ModuleSessions] No session is running, this 'updateSessionInternal' will be ignored");
            }
            if (this._cly.disableUpdateSessionRequests_) {
                return;
            }
            this.requestQueueProvider.updateSession(roundedSecondsSinceLastSessionDurationUpdate());
        }
    }

    void endSessionInternal(String str) {
        this.L.d("[ModuleSessions] 'endSessionInternal'");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
            if (!sessionIsRunning()) {
                this.L.d("[ModuleSessions] No session is running, this 'endSessionInternal' will be ignored");
            }
            this._cly.moduleRequestQueue.sendEventsIfNeeded(true);
            this.requestQueueProvider.endSession(roundedSecondsSinceLastSessionDurationUpdate(), str);
            this.prevSessionDurationStartTime_ = 0L;
            this._cly.moduleViews.resetFirstView();
        }
    }

    public boolean sessionIsRunning() {
        return this.prevSessionDurationStartTime_ > 0;
    }

    int roundedSecondsSinceLastSessionDurationUpdate() {
        long jNanoTime = System.nanoTime();
        long j = jNanoTime - this.prevSessionDurationStartTime_;
        this.prevSessionDurationStartTime_ = jNanoTime;
        return (int) Math.round(j / 1.0E9d);
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onConsentChanged(List<String> list, boolean z, ModuleConsent.ConsentChangeSource consentChangeSource) {
        if (list.contains(Countly.CountlyFeatureNames.sessions)) {
            if (z) {
                if (this.manualSessionControlEnabled || !this._cly.lifecycleStateAtLeastStarted()) {
                    return;
                }
                beginSessionInternal();
                return;
            }
            if (!this._cly.isBeginSessionSent) {
                this._cly.moduleLocation.sendCurrentLocationIfValid();
            }
            if (sessionIsRunning()) {
                endSessionInternal(null);
            } else {
                this._cly.moduleViews.resetFirstView();
            }
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (this.manualSessionControlEnabled || !this._cly.lifecycleStateAtLeastStarted()) {
            return;
        }
        beginSessionInternal();
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        this.prevSessionDurationStartTime_ = 0L;
    }

    public class Sessions {
        public Sessions() {
        }

        public void beginSession() {
            synchronized (ModuleSessions.this._cly) {
                ModuleSessions.this.L.i("[Sessions] Calling 'beginSession', manual session control enabled:[" + ModuleSessions.this.manualSessionControlEnabled + "]");
                if (!ModuleSessions.this.manualSessionControlEnabled) {
                    ModuleSessions.this.L.w("[Sessions] 'beginSession' will be ignored since manual session control is not enabled");
                } else {
                    ModuleSessions.this.beginSessionInternal();
                }
            }
        }

        public void updateSession() {
            synchronized (ModuleSessions.this._cly) {
                ModuleSessions.this.L.i("[Sessions] Calling 'updateSession', manual session control enabled:[" + ModuleSessions.this.manualSessionControlEnabled + "]");
                if (!ModuleSessions.this.manualSessionControlEnabled) {
                    ModuleSessions.this.L.w("[Sessions] 'updateSession' will be ignored since manual session control is not enabled");
                } else if (ModuleSessions.this.manualSessionControlHybridModeEnabled) {
                    ModuleSessions.this.L.w("[Sessions] 'updateSession' will be ignored since manual session control hybrid mode is enabled");
                } else {
                    ModuleSessions.this.updateSessionInternal();
                }
            }
        }

        public void endSession() {
            synchronized (ModuleSessions.this._cly) {
                ModuleSessions.this.L.i("[Sessions] Calling 'endSession', manual session control enabled:[" + ModuleSessions.this.manualSessionControlEnabled + "]");
                if (!ModuleSessions.this.manualSessionControlEnabled) {
                    ModuleSessions.this.L.w("[Sessions] 'endSession' will be ignored since manual session control is not enabled");
                } else {
                    ModuleSessions.this.endSessionInternal(null);
                }
            }
        }
    }
}
