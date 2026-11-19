package ly.count.android.sdk;

import android.util.Log;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleLog {
    HealthTracker healthTracker;
    LogCallback logListener = null;
    int countWarnings = 0;
    int countErrors = 0;

    public interface LogCallback {
        void LogHappened(String str, LogLevel logLevel);
    }

    public enum LogLevel {
        Verbose,
        Debug,
        Info,
        Warning,
        Error
    }

    void SetListener(LogCallback logCallback) {
        this.logListener = logCallback;
    }

    void trackWarning() {
        HealthTracker healthTracker = this.healthTracker;
        if (healthTracker == null) {
            this.countWarnings++;
        } else {
            healthTracker.logWarning();
        }
    }

    void trackError() {
        HealthTracker healthTracker = this.healthTracker;
        if (healthTracker == null) {
            this.countErrors++;
        } else {
            healthTracker.logError();
        }
    }

    void setHealthChecker(HealthTracker healthTracker) {
        v("[ModuleLog] Setting healthTracker W:" + this.countWarnings + " E:" + this.countErrors);
        this.healthTracker = healthTracker;
        if (healthTracker == null) {
            return;
        }
        for (int i = 0; i < this.countErrors; i++) {
            healthTracker.logError();
        }
        for (int i2 = 0; i2 < this.countWarnings; i2++) {
            healthTracker.logWarning();
        }
        this.countWarnings = 0;
        this.countErrors = 0;
    }

    public void v(String str) {
        if (logEnabled()) {
            if (Countly.sharedInstance().isLoggingEnabled()) {
                Log.v(Countly.TAG, str);
            }
            informListener(str, null, LogLevel.Verbose);
        }
    }

    public void d(String str) {
        if (logEnabled()) {
            if (Countly.sharedInstance().isLoggingEnabled()) {
                Log.d(Countly.TAG, str);
            }
            informListener(str, null, LogLevel.Debug);
        }
    }

    public void i(String str) {
        if (logEnabled()) {
            if (Countly.sharedInstance().isLoggingEnabled()) {
                Log.i(Countly.TAG, str);
            }
            informListener(str, null, LogLevel.Info);
        }
    }

    public void w(String str) {
        w(str, null);
    }

    public void w(String str, Throwable th) {
        trackWarning();
        if (logEnabled()) {
            if (Countly.sharedInstance().isLoggingEnabled()) {
                Log.w(Countly.TAG, str);
            }
            informListener(str, null, LogLevel.Warning);
        }
    }

    public void e(String str) {
        e(str, null);
    }

    public void e(String str, Throwable th) {
        trackError();
        if (logEnabled()) {
            if (Countly.sharedInstance().isLoggingEnabled()) {
                Log.e(Countly.TAG, str, th);
            }
            informListener(str, th, LogLevel.Error);
        }
    }

    public boolean logEnabled() {
        return this.logListener != null || Countly.sharedInstance().isLoggingEnabled();
    }

    private void informListener(String str, Throwable th, LogLevel logLevel) {
        if (str == null) {
            str = "";
        }
        if (th != null) {
            try {
                str = str + Log.getStackTraceString(th);
            } catch (Exception e) {
                Log.e(Countly.TAG, "[ModuleLog] Failed to inform listener [" + e.toString() + "]");
                return;
            }
        }
        LogCallback logCallback = this.logListener;
        if (logCallback != null) {
            logCallback.LogHappened(str, logLevel);
        }
    }
}
