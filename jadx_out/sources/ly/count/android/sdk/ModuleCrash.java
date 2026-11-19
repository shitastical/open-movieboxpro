package ly.count.android.sdk;

import android.content.Context;
import android.util.Base64;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleCrash extends ModuleBase {
    private static final String countlyFolderName = "Countly";
    private static final String countlyNativeCrashFolderName = "CrashDumps";
    CrashFilterCallback crashFilterCallback;
    final Crashes crashesInterface;
    Map<String, Object> customCrashSegments;
    Map<String, String> metricOverride;
    boolean recordAllThreads;

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
    }

    ModuleCrash(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.recordAllThreads = false;
        this.customCrashSegments = null;
        this.metricOverride = null;
        this.L.v("[ModuleCrash] Initialising");
        setCrashFilterCallback(countlyConfig.crashFilterCallback);
        this.recordAllThreads = countlyConfig.recordAllThreadsWithCrash;
        setCustomCrashSegmentsInternal(countlyConfig.customCrashSegment);
        this.metricOverride = countlyConfig.metricOverride;
        this.crashesInterface = new Crashes();
    }

    void checkForNativeCrashDumps(Context context) {
        this.L.d("[ModuleCrash] Checking for native crash dumps");
        File file = new File(context.getCacheDir().getAbsolutePath() + File.separator + "Countly" + File.separator + countlyNativeCrashFolderName);
        if (file.exists()) {
            this.L.d("[ModuleCrash] Native crash folder exists, checking for dumps");
            File[] fileArrListFiles = file.listFiles();
            this.L.d("[ModuleCrash] Crash dump folder contains [" + (fileArrListFiles != null ? fileArrListFiles.length : -1) + "] files");
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    recordNativeException(file2);
                    file2.delete();
                }
                return;
            }
            return;
        }
        this.L.d("[ModuleCrash] Native crash folder does not exist");
    }

    private void recordNativeException(File file) {
        this.L.d("[ModuleCrash] Recording native crash dump: [" + file.getName() + "]");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.crashes)) {
            int length = (int) file.length();
            byte[] bArr = new byte[length];
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                bufferedInputStream.read(bArr, 0, length);
                bufferedInputStream.close();
                sendCrashReportToQueue(Base64.encodeToString(bArr, 2), false, true, null);
            } catch (Exception e) {
                this.L.e("[ModuleCrash] Failed to read dump file bytes");
                e.printStackTrace();
            }
        }
    }

    public void sendCrashReportToQueue(String str, boolean z, boolean z2, Map<String, Object> map) {
        this.L.d("[ModuleCrash] sendCrashReportToQueue");
        HashMap map2 = new HashMap();
        Map<String, Object> map3 = this.customCrashSegments;
        if (map3 != null) {
            map2.putAll(map3);
        }
        if (map != null) {
            Utils.removeUnsupportedDataTypes(map);
            map2.putAll(map);
        }
        Utils.truncateSegmentationValues(map2, this._cly.config_.maxSegmentationValues.intValue(), "[ModuleCrash] sendCrashReportToQueue", this.L);
        if (!z2) {
            str = str.substring(0, Math.min(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH, str.length()));
        }
        this.requestQueueProvider.sendCrashReport(this.deviceInfo.getCrashDataString(this._cly.context_, str, Boolean.valueOf(z), z2, DeviceInfo.getLogs(), map2, this.deviceInfo, this.metricOverride), z);
    }

    void setCustomCrashSegmentsInternal(Map<String, Object> map) {
        this.L.d("[ModuleCrash] Calling setCustomCrashSegmentsInternal");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.crashes)) {
            if (map != null) {
                Utils.removeUnsupportedDataTypes(map);
            }
            this.customCrashSegments = map;
        }
    }

    void enableCrashReporting() {
        this.L.d("[ModuleCrash] Enabling unhandled crash reporting");
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: ly.count.android.sdk.ModuleCrash.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                ModuleCrash.this.L.d("[ModuleCrash] Uncaught crash handler triggered");
                if (ModuleCrash.this.consentProvider.getConsent(Countly.CountlyFeatureNames.crashes)) {
                    StringWriter stringWriter = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(stringWriter);
                    th.printStackTrace(printWriter);
                    if (ModuleCrash.this.recordAllThreads) {
                        ModuleCrash.this.addAllThreadInformationToCrash(printWriter);
                    }
                    String string = stringWriter.toString();
                    if (!ModuleCrash.this.crashFilterCheck(string)) {
                        ModuleCrash.this.sendCrashReportToQueue(string, false, false, null);
                    }
                }
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = defaultUncaughtExceptionHandler;
                if (uncaughtExceptionHandler != null) {
                    uncaughtExceptionHandler.uncaughtException(thread, th);
                }
            }
        });
    }

    void setCrashFilterCallback(CrashFilterCallback crashFilterCallback) {
        this.crashFilterCallback = crashFilterCallback;
    }

    boolean crashFilterCheck(String str) {
        this.L.d("[ModuleCrash] Calling crashFilterCheck");
        CrashFilterCallback crashFilterCallback = this.crashFilterCallback;
        if (crashFilterCallback == null) {
            return false;
        }
        return crashFilterCallback.filterCrash(str);
    }

    void addAllThreadInformationToCrash(PrintWriter printWriter) {
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            StackTraceElement[] value = entry.getValue();
            Thread key = entry.getKey();
            if (value != null && key != null) {
                printWriter.println();
                printWriter.println("Thread " + key.getName());
                for (StackTraceElement stackTraceElement : value) {
                    printWriter.println(stackTraceElement.toString());
                }
            }
        }
    }

    Countly recordExceptionInternal(Throwable th, boolean z, Map<String, Object> map) {
        this.L.i("[ModuleCrash] Logging exception, handled:[" + z + "]");
        if (!this._cly.isInitialized()) {
            throw new IllegalStateException("Countly.sharedInstance().init must be called before recording exceptions");
        }
        if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.crashes)) {
            return this._cly;
        }
        if (th == null) {
            this.L.d("[ModuleCrash] recordException, provided exception was null, returning");
            return this._cly;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        if (this.recordAllThreads) {
            addAllThreadInformationToCrash(printWriter);
        }
        String string = stringWriter.toString();
        if (crashFilterCheck(string)) {
            this.L.d("[ModuleCrash] Crash filter found a match, exception will be ignored, [" + string.substring(0, Math.min(string.length(), 60)) + "]");
        } else {
            sendCrashReportToQueue(string, z, false, map);
        }
        return this._cly;
    }

    Countly addBreadcrumbInternal(String str) {
        if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.crashes)) {
            return this._cly;
        }
        if (str == null || str.isEmpty()) {
            this.L.e("[Crashes] Can't add a null or empty crash breadcrumb");
            return this._cly;
        }
        DeviceInfo.addLog(str, this._cly.config_.maxBreadcrumbCount.intValue(), this._cly.config_.maxValueSize.intValue());
        return this._cly;
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (countlyConfig.enableUnhandledCrashReporting) {
            enableCrashReporting();
        }
        if (countlyConfig.checkForNativeCrashDumps) {
            this._cly.moduleCrash.checkForNativeCrashDumps(countlyConfig.context);
        }
    }

    public class Crashes {
        public Crashes() {
        }

        public Countly addCrashBreadcrumb(String str) {
            Countly countlyAddBreadcrumbInternal;
            synchronized (ModuleCrash.this._cly) {
                ModuleCrash.this.L.i("[Crashes] Adding crash breadcrumb");
                countlyAddBreadcrumbInternal = ModuleCrash.this.addBreadcrumbInternal(str);
            }
            return countlyAddBreadcrumbInternal;
        }

        public Countly recordHandledException(Exception exc) {
            Countly countlyRecordExceptionInternal;
            synchronized (ModuleCrash.this._cly) {
                countlyRecordExceptionInternal = ModuleCrash.this.recordExceptionInternal(exc, true, null);
            }
            return countlyRecordExceptionInternal;
        }

        public Countly recordHandledException(Throwable th) {
            Countly countlyRecordExceptionInternal;
            synchronized (ModuleCrash.this._cly) {
                countlyRecordExceptionInternal = ModuleCrash.this.recordExceptionInternal(th, true, null);
            }
            return countlyRecordExceptionInternal;
        }

        public Countly recordUnhandledException(Exception exc) {
            Countly countlyRecordExceptionInternal;
            synchronized (ModuleCrash.this._cly) {
                countlyRecordExceptionInternal = ModuleCrash.this.recordExceptionInternal(exc, false, null);
            }
            return countlyRecordExceptionInternal;
        }

        public Countly recordUnhandledException(Throwable th) {
            Countly countlyRecordExceptionInternal;
            synchronized (ModuleCrash.this._cly) {
                countlyRecordExceptionInternal = ModuleCrash.this.recordExceptionInternal(th, false, null);
            }
            return countlyRecordExceptionInternal;
        }

        public Countly recordHandledException(Throwable th, Map<String, Object> map) {
            Countly countlyRecordExceptionInternal;
            synchronized (ModuleCrash.this._cly) {
                countlyRecordExceptionInternal = ModuleCrash.this.recordExceptionInternal(th, true, map);
            }
            return countlyRecordExceptionInternal;
        }

        public Countly recordUnhandledException(Throwable th, Map<String, Object> map) {
            Countly countlyRecordExceptionInternal;
            synchronized (ModuleCrash.this._cly) {
                countlyRecordExceptionInternal = ModuleCrash.this.recordExceptionInternal(th, false, map);
            }
            return countlyRecordExceptionInternal;
        }
    }
}
