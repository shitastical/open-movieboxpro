package ly.count.android.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.internal.view.SupportMenu;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.asm.Opcodes;
import com.dl7.player.media.MediaPlayerParams;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTimeConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
class DeviceInfo {
    private static final LinkedList<String> logs = new LinkedList<>();
    private static final int startTime = UtilsTime.currentTimestampSeconds();
    private static long totalMemory = 0;
    private boolean inBackground = true;
    MetricProvider mp;

    public DeviceInfo(MetricProvider metricProvider) {
        this.mp = metricProvider;
        if (metricProvider == null) {
            this.mp = new MetricProvider() { // from class: ly.count.android.sdk.DeviceInfo.1
                @Override // ly.count.android.sdk.MetricProvider
                public String getOS() {
                    return "Android";
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getOSVersion() {
                    return Build.VERSION.RELEASE;
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getDevice() {
                    return Build.MODEL;
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getManufacturer() {
                    return Build.MANUFACTURER;
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getResolution(Context context) {
                    try {
                        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        defaultDisplay.getMetrics(displayMetrics);
                        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
                    } catch (Throwable unused) {
                        Countly.sharedInstance().L.i("[DeviceInfo] Device resolution cannot be determined");
                        return "";
                    }
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getDensity(Context context) {
                    switch (context.getResources().getDisplayMetrics().densityDpi) {
                        case com.kaopiz.kprogresshud.BuildConfig.VERSION_CODE /* 120 */:
                            return "LDPI";
                        case Opcodes.IF_ICMPNE /* 160 */:
                            return "MDPI";
                        case 213:
                            return "TVDPI";
                        case 240:
                            return "HDPI";
                        case 260:
                        case 280:
                        case 300:
                        case 320:
                            return "XHDPI";
                        case 340:
                        case 360:
                        case MediaPlayerParams.STATE_PLAYING_START /* 400 */:
                        case TypedValues.CycleType.TYPE_EASING /* 420 */:
                        case 480:
                            return "XXHDPI";
                        case 560:
                        case 640:
                            return "XXXHDPI";
                        default:
                            return Video.TypeInfo.OTHER;
                    }
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getCarrier(Context context) {
                    String networkOperatorName;
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager == null) {
                        networkOperatorName = "";
                    } else {
                        networkOperatorName = telephonyManager.getNetworkOperatorName();
                    }
                    if (networkOperatorName != null && networkOperatorName.length() != 0) {
                        return networkOperatorName;
                    }
                    Countly.sharedInstance().L.i("[DeviceInfo] No carrier found");
                    return "";
                }

                @Override // ly.count.android.sdk.MetricProvider
                public int getTimezoneOffset() {
                    return TimeZone.getDefault().getOffset(new Date().getTime()) / DateTimeConstants.MILLIS_PER_MINUTE;
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getLocale() {
                    Locale locale = Locale.getDefault();
                    return locale.getLanguage() + "_" + locale.getCountry();
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getAppVersion(Context context) {
                    try {
                        String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                        return str != null ? str : "1.0";
                    } catch (PackageManager.NameNotFoundException unused) {
                        Countly.sharedInstance().L.i("[DeviceInfo] No app version found");
                        return "1.0";
                    }
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getStore(Context context) {
                    String installerPackageName;
                    try {
                        installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                    } catch (Exception unused) {
                        Countly.sharedInstance().L.d("[DeviceInfo, getStore] Can't get Installer package ");
                        installerPackageName = "";
                    }
                    if (installerPackageName != null && installerPackageName.length() != 0) {
                        return installerPackageName;
                    }
                    Countly.sharedInstance().L.d("[DeviceInfo, getStore] No store found");
                    return "";
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getDeviceType(Context context) {
                    if (Utils.isDeviceTv(context)) {
                        return "smarttv";
                    }
                    if (Utils.isDeviceTablet(context)) {
                        return "tablet";
                    }
                    return "mobile";
                }

                @Override // ly.count.android.sdk.MetricProvider
                public long getTotalRAM() throws Throwable {
                    RandomAccessFile randomAccessFile;
                    IOException e;
                    if (DeviceInfo.totalMemory == 0) {
                        try {
                            try {
                                try {
                                    randomAccessFile = new RandomAccessFile("/proc/meminfo", "r");
                                    try {
                                        Matcher matcher = Pattern.compile("(\\d+)").matcher(randomAccessFile.readLine());
                                        String strGroup = "";
                                        while (matcher.find()) {
                                            strGroup = matcher.group(1);
                                        }
                                        try {
                                            if (strGroup != null) {
                                                long unused = DeviceInfo.totalMemory = Long.parseLong(strGroup) / 1024;
                                            } else {
                                                long unused2 = DeviceInfo.totalMemory = 0L;
                                            }
                                        } catch (NumberFormatException unused3) {
                                            long unused4 = DeviceInfo.totalMemory = 0L;
                                        }
                                        randomAccessFile.close();
                                    } catch (IOException e2) {
                                        e = e2;
                                        if (randomAccessFile != null) {
                                            try {
                                                randomAccessFile.close();
                                            } catch (IOException e3) {
                                                e3.printStackTrace();
                                            }
                                        }
                                        e.printStackTrace();
                                        if (randomAccessFile != null) {
                                            randomAccessFile.close();
                                        }
                                        return DeviceInfo.totalMemory;
                                    }
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            } catch (IOException e5) {
                                randomAccessFile = null;
                                e = e5;
                            } catch (Throwable th) {
                                randomAccessFile = null;
                                th = th;
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    return DeviceInfo.totalMemory;
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getRamCurrent(Context context) {
                    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                    ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
                    return Long.toString(getTotalRAM() - (memoryInfo.availMem / 1048576));
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getRamTotal() {
                    return Long.toString(getTotalRAM());
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getCpu() {
                    return Build.SUPPORTED_ABIS[0];
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getOpenGL(Context context) {
                    FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
                    if (systemAvailableFeatures != null && systemAvailableFeatures.length > 0) {
                        for (FeatureInfo featureInfo : systemAvailableFeatures) {
                            if (featureInfo.name == null) {
                                if (featureInfo.reqGlEsVersion == 0) {
                                    return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                                }
                                return Integer.toString((featureInfo.reqGlEsVersion & SupportMenu.CATEGORY_MASK) >> 16);
                            }
                        }
                    }
                    return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getDiskCurrent() {
                    StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
                    return Long.toString(((statFs.getBlockCountLong() * statFs.getBlockSizeLong()) - (statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong())) / 1048576);
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getDiskTotal() {
                    StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
                    return Long.toString((statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / 1048576);
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getBatteryLevel(Context context) {
                    try {
                        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                        if (intentRegisterReceiver != null) {
                            int intExtra = intentRegisterReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
                            int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
                            if (intExtra > -1 && intExtra2 > 0) {
                                return Float.toString((intExtra / intExtra2) * 100.0f);
                            }
                        }
                    } catch (Exception unused) {
                        Countly.sharedInstance().L.i("Can't get battery level");
                    }
                    return null;
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String getOrientation(Context context) {
                    int i = context.getResources().getConfiguration().orientation;
                    if (i == 0) {
                        return "Unknown";
                    }
                    if (i == 1) {
                        return "Portrait";
                    }
                    if (i == 2) {
                        return "Landscape";
                    }
                    if (i != 3) {
                        return null;
                    }
                    return "Square";
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String isRooted() {
                    String[] strArr = {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
                    for (int i = 0; i < 8; i++) {
                        if (new File(strArr[i]).exists()) {
                            return "true";
                        }
                    }
                    return "false";
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String isOnline(Context context) {
                    try {
                        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                        if (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected()) {
                            return "true";
                        }
                        return "false";
                    } catch (Exception e) {
                        Countly.sharedInstance().L.w("isOnline, Got exception determining netwprl connectivity", e);
                        return null;
                    }
                }

                @Override // ly.count.android.sdk.MetricProvider
                public String isMuted(Context context) {
                    try {
                        int ringerMode = ((AudioManager) context.getSystemService("audio")).getRingerMode();
                        if (ringerMode != 0 && ringerMode != 1) {
                            return "false";
                        }
                        return "true";
                    } catch (Throwable unused) {
                        return "false";
                    }
                }
            };
        }
    }

    JSONObject getCommonMetrics(Context context, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        Utils.fillJSONIfValuesNotEmpty(jSONObject, "_device", this.mp.getDevice(), "_os", this.mp.getOS(), "_os_version", this.mp.getOSVersion(), "_resolution", this.mp.getResolution(context), "_app_version", this.mp.getAppVersion(context), "_manufacturer", this.mp.getManufacturer());
        if (map != null) {
            try {
                if (map.containsKey("_device")) {
                    jSONObject.put("_device", map.get("_device"));
                }
                if (map.containsKey("_os")) {
                    jSONObject.put("_os", map.get("_os"));
                }
                if (map.containsKey("_os_version")) {
                    jSONObject.put("_os_version", map.get("_os_version"));
                }
                if (map.containsKey("_resolution")) {
                    jSONObject.put("_resolution", map.get("_resolution"));
                }
                if (map.containsKey("_app_version")) {
                    jSONObject.put("_app_version", map.get("_app_version"));
                }
                if (map.containsKey("_manufacturer")) {
                    jSONObject.put("_manufacturer", map.get("_manufacturer"));
                }
            } catch (Exception e) {
                Countly.sharedInstance().L.e("[DeviceInfo] SDK encountered failure while trying to apply metric override, " + e.toString());
            }
        }
        return jSONObject;
    }

    String getMetrics(Context context, Map<String, String> map) {
        JSONObject commonMetrics = getCommonMetrics(context, null);
        Utils.fillJSONIfValuesNotEmpty(commonMetrics, "_carrier", this.mp.getCarrier(context), "_density", this.mp.getDensity(context), "_locale", this.mp.getLocale(), "_store", this.mp.getStore(context), "_device_type", this.mp.getDeviceType(context));
        if (map != null) {
            for (String str : map.keySet()) {
                if (str == null || str.length() == 0) {
                    Countly.sharedInstance().L.w("Provided metric override key can't be null or empty");
                } else {
                    String str2 = map.get(str);
                    if (str2 == null) {
                        Countly.sharedInstance().L.w("Provided metric override value can't be null, key:[" + str + "]");
                    } else {
                        try {
                            commonMetrics.put(str, str2);
                        } catch (Exception e) {
                            Countly.sharedInstance().L.e("Could not set metric override, [" + e + "]");
                        }
                    }
                }
            }
        }
        String string = commonMetrics.toString();
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            Countly.sharedInstance().L.e("[getMetrics] encode failed, [" + e2 + "]");
            return string;
        }
    }

    String getMetricsHealthCheck(Context context, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        Utils.fillJSONIfValuesNotEmpty(jSONObject, "_app_version", this.mp.getAppVersion(context));
        if (map != null) {
            try {
                if (map.containsKey("_app_version")) {
                    jSONObject.put("_app_version", map.get("_app_version"));
                }
            } catch (Exception e) {
                Countly.sharedInstance().L.e("[DeviceInfo] SDK encountered failure while trying to apply metric override, " + e.toString());
            }
        }
        String string = jSONObject.toString();
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            Countly.sharedInstance().L.e("[getMetrics] encode failed, [" + e2 + "]");
            return string;
        }
    }

    JSONObject getCrashDataStringJSON(Context context, String str, Boolean bool, boolean z, String str2, Map<String, Object> map, Map<String, String> map2) {
        JSONObject commonMetrics = getCommonMetrics(context, map2);
        Utils.fillJSONIfValuesNotEmpty(commonMetrics, "_error", str, "_nonfatal", Boolean.toString(bool.booleanValue()), "_cpu", this.mp.getCpu(), "_opengl", this.mp.getOpenGL(context), "_root", this.mp.isRooted(), "_ram_total", this.mp.getRamTotal(), "_disk_total", this.mp.getDiskTotal());
        if (!z) {
            Utils.fillJSONIfValuesNotEmpty(commonMetrics, "_logs", str2, "_ram_current", this.mp.getRamCurrent(context), "_disk_current", this.mp.getDiskCurrent(), "_bat", this.mp.getBatteryLevel(context), "_run", getRunningTime(), "_orientation", this.mp.getOrientation(context), "_online", this.mp.isOnline(context), "_muted", this.mp.isMuted(context), "_background", isInBackground());
        } else {
            try {
                commonMetrics.put("_native_cpp", true);
            } catch (JSONException unused) {
            }
        }
        try {
            commonMetrics.put("_custom", getCustomSegmentsJson(map));
        } catch (JSONException unused2) {
        }
        return commonMetrics;
    }

    String getCrashDataString(Context context, String str, Boolean bool, boolean z, String str2, Map<String, Object> map, DeviceInfo deviceInfo, Map<String, String> map2) {
        return getCrashDataStringJSON(context, str, bool, z, str2, map, map2).toString();
    }

    void inForeground() {
        this.inBackground = false;
    }

    void inBackground() {
        this.inBackground = true;
    }

    String isInBackground() {
        return Boolean.toString(this.inBackground);
    }

    static void addLog(String str, int i, int i2) {
        int length = str.length();
        if (length > i2) {
            Countly.sharedInstance().L.d("Breadcrumb exceeds character limit: [" + length + "], reducing it to: [" + i2 + "]");
            str = str.substring(0, i2);
        }
        LinkedList<String> linkedList = logs;
        linkedList.add(str);
        if (linkedList.size() > i) {
            Countly.sharedInstance().L.d("Breadcrumb amount limit exceeded, deleting the oldest one");
            linkedList.removeFirst();
        }
    }

    static String getLogs() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = logs.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(ShellUtil.COMMAND_LINE_END);
        }
        logs.clear();
        return sb.toString();
    }

    static JSONObject getCustomSegmentsJson(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            if (str != null) {
                try {
                    jSONObject.put(str, map.get(str));
                } catch (JSONException unused) {
                    Countly.sharedInstance().L.w("[getCustomSegmentsJson] Failed to add custom segmentation to crash");
                }
            }
        }
        return jSONObject;
    }

    static String getRunningTime() {
        return Integer.toString(UtilsTime.currentTimestampSeconds() - startTime);
    }
}
