package com.movieboxpro.android.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.androidtv.BuildConfig;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public class SystemUtils {
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    public static final String SYS_DEFAULT = "android";
    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_FLYME = "sys_flyme";
    public static final String SYS_MIUI = "sys_miui";

    public static int getVersionCode(Context context) {
        return 126;
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }

    public static String checkSystem(Activity activity) {
        if (App.isDebug) {
            checkBrowsers(activity);
        }
        Context applicationContext = activity.getApplicationContext();
        String versionName = getVersionName(applicationContext);
        int versionCode = getVersionCode(applicationContext);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        int realScreenHeight = getRealScreenHeight(activity);
        float f = displayMetrics.density;
        int i3 = displayMetrics.densityDpi;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("width", Integer.valueOf(i));
            jSONObject.putOpt("height", Integer.valueOf(i2));
            jSONObject.putOpt("real_height", Integer.valueOf(realScreenHeight));
            jSONObject.putOpt("density", Float.valueOf(f));
            jSONObject.putOpt("dpi", Integer.valueOf(i3));
            jSONObject.putOpt("mode", App.isDebug ? "debug" : "release");
            jSONObject.putOpt("test", false);
            jSONObject.putOpt("debug_level", Integer.valueOf(App.DEBUG_LEVEL));
            jSONObject.putOpt("api", Integer.valueOf(Build.VERSION.SDK_INT));
            jSONObject.putOpt("android", Build.VERSION.RELEASE);
            jSONObject.putOpt("os_type", getOSType());
            jSONObject.putOpt("brand", Build.BRAND);
            jSONObject.putOpt("model", Build.MODEL);
            jSONObject.putOpt("display", Build.DISPLAY);
            jSONObject.putOpt("fingerprint", Build.FINGERPRINT);
            jSONObject.putOpt("version_name", versionName);
            jSONObject.putOpt("version_code", Integer.valueOf(versionCode));
            jSONObject.putOpt(TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME, App.packageName);
            jSONObject.putOpt("isEmulator", Boolean.valueOf(isEmulator()));
            jSONObject.putOpt("has_nav", Boolean.valueOf(hasNavBar(applicationContext)));
            jSONObject.putOpt("nav_height", Integer.valueOf(getNavBarHeight(applicationContext)));
            jSONObject.put("sha1", SHA1(activity));
            jSONObject.put(FileDownloadService.PARAMS_KEY_MD5, MD5(activity));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static boolean hasNavBar(Context context) {
        if (context == null) {
            context = App.getContext();
        }
        if (context == null) {
            return false;
        }
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
            if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(str)) {
                return false;
            }
            if ("0".equals(str)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier <= 0 || !hasNavBar(context)) {
            return 0;
        }
        return resources.getDimensionPixelOffset(identifier);
    }

    public static String getMsg2() {
        String str = "app_version:13.4\nappid:" + App.packageName + "\nplatform:android_tv\nAndroid:" + Build.VERSION.RELEASE + "\napi:" + Build.VERSION.SDK_INT + "\ndevice_name:" + Build.MODEL + "\nisEmulator:" + isEmulator() + "\nuser_name:" + App.getUserData().nickname + "\nuid:" + App.getUserData().uid_v2 + "\nis_vip:" + App.getUserData().isvip;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_version", BuildConfig.VERSION_NAME);
            jSONObject.putOpt("appid", App.packageName);
            jSONObject.putOpt("platform", "Android");
            HashMap map = new HashMap();
            map.put("Android", Build.VERSION.RELEASE + "\\\n");
            map.put("api", Build.VERSION.SDK_INT + "\\\n");
            map.put("device_name", Build.MODEL + "\\\n");
            map.put("isEmulator", isEmulator() + "\\\n");
            map.put("app_version", "13.4\\\n");
            map.put("user_name", App.getUserData().name + "\\\n");
            map.put("uid", App.getUserData().uid_v2 + "\\\n");
            map.put("is_vip", App.getUserData().isvip + "\\\n");
            StringBuilder sb = new StringBuilder("\\\n\\\n");
            sb.append(com.alibaba.fastjson.JSONObject.toJSONString(map));
            jSONObject.putOpt("env", sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getRealScreenHeight(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            return displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getMetaData(Context context, String key) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getVersionName(Context context) {
        return BuildConfig.VERSION_NAME;
    }

    public static String getProcessName(Context appContext) {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) appContext.getSystemService("activity")).getRunningAppProcesses();
        String str = null;
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    str = runningAppProcessInfo.processName;
                }
            }
        }
        return str;
    }

    private static String MD5(Context context) {
        try {
            Signature signature = context.getPackageManager().getPackageInfo(App.packageName, 64).signatures[0];
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(signature.toByteArray());
            return HexDump.toHexString(messageDigest.digest()).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String SHA1(Context context) {
        try {
            return HexDump.toHexString(MessageDigest.getInstance("SHA1").digest(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray())).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap frameAtTime;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(filePath);
                frameAtTime = mediaMetadataRetriever.getFrameAtTime(TimeUnit.MILLISECONDS.toMicros(1L));
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (RuntimeException e2) {
                    e2.printStackTrace();
                }
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException e4) {
                    throw new RuntimeException(e4);
                } catch (RuntimeException e5) {
                    e5.printStackTrace();
                }
                frameAtTime = null;
            }
            return frameAtTime;
        } catch (Throwable th) {
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e6) {
                throw new RuntimeException(e6);
            } catch (RuntimeException e7) {
                e7.printStackTrace();
            }
            throw th;
        }
    }

    public static String getOSType() {
        if (Build.MANUFACTURER.equals("HUAWEI")) {
            return SYS_EMUI;
        }
        if (!Build.MANUFACTURER.equals("Xiaomi")) {
            return "android";
        }
        return SYS_MIUI;
    }

    private static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (serviceClass.getName().equals(it.next().service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static String getMIMEType(String ext) {
        if ("epub".equalsIgnoreCase(ext)) {
            return "application/epub+zip";
        }
        String str = "text/plain";
        if (!"txt".equalsIgnoreCase(ext) && !"text".equalsIgnoreCase(ext)) {
            str = "text/html";
            if (!"html".equalsIgnoreCase(ext) && !"htm".equalsIgnoreCase(ext)) {
                return "application/" + ext;
            }
        }
        return str;
    }

    private static void checkBrowsers(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://www.baidu.com"));
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo != null && resolveInfo.activityInfo != null) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                MLog.d("SystemUtils", "checkBrowsers: " + activityInfo.packageName + "/" + activityInfo.name + "/" + activityInfo.targetActivity);
            }
        }
    }

    public static void startBrowser(Context context, String url) {
        MLog.d("SystemUtils", "startBrowser: " + url);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.setFlags(268435456);
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        String str = "com.android.browser";
        String str2 = "com.android.browser.BrowserActivity";
        if (listQueryIntentActivities != null && !listQueryIntentActivities.isEmpty()) {
            String str3 = "com.android.browser";
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                if (resolveInfo != null && resolveInfo.activityInfo != null) {
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    if (activityInfo.packageName != null && activityInfo.name != null) {
                        str3 = activityInfo.packageName;
                        str2 = activityInfo.name;
                        if (str3.equals("com.android.browser") || str3.equals("com.tencent.mtt") || str3.equals("com.UCMobile") || str3.equals("org.mozilla.firefox") || str3.equals("com.microsoft.emmx") || str3.equals("com.android.chrome")) {
                            break;
                        }
                    }
                }
            }
            str = str3;
        }
        intent.setClassName(str, str2);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(Uri.parse(url));
            context.startActivity(Intent.createChooser(intent2, "请选择浏览器"));
        }
    }

    public static void installAPK(File apkFile) {
        installAPK(App.getContext(), apkFile);
    }

    public static void installAPK(Context context, File apkFile) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openFile(Context context, File file) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.fromFile(file));
        context.startActivity(Intent.createChooser(intent, "请选择应用打开"));
    }

    public static String getUniqueId(Context context) {
        String str = Settings.Secure.getString(context.getContentResolver(), "android_id") + Build.SERIAL;
        try {
            return toMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String toMD5(String text) throws NoSuchAlgorithmException {
        byte[] bArrMd5 = MD5Util.md5(text);
        StringBuilder sb = new StringBuilder();
        for (byte b : bArrMd5) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static void sendEmail(Activity context) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:support@movieboxpro.app"));
        intent.putExtra("android.intent.extra.CC", new String[]{"support@movieboxpro.app"});
        intent.putExtra("android.intent.extra.SUBJECT", "Error msg");
        intent.putExtra("android.intent.extra.TEXT", getMsg(context));
        context.startActivity(Intent.createChooser(intent, "Choose"));
    }

    public static String getMsg(Activity context) {
        String str = "\n\napp_version:13.4\nappid:" + App.packageName + "\nplatform:Android\nAndroid:" + Build.VERSION.RELEASE + "\napi:" + Build.VERSION.SDK_INT + "\ndevice_name:" + Build.MODEL + "\nisEmulator:" + isEmulator() + "\nuser_name:" + App.getUserData().nickname + "\nuid:" + App.getUserData().uid_v2 + "\nis_vip:" + App.getUserData().isvip;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_version", BuildConfig.VERSION_NAME);
            jSONObject.putOpt("appid", App.packageName);
            jSONObject.putOpt("platform", "Android");
            HashMap map = new HashMap();
            map.put("Android", Build.VERSION.RELEASE + "\\\n");
            map.put("api", Build.VERSION.SDK_INT + "\\\n");
            map.put("device_name", Build.MODEL + "\\\n");
            map.put("isEmulator", isEmulator() + "\\\n");
            map.put("app_version", "13.4\\\n");
            map.put("user_name", App.getUserData().name + "\\\n");
            map.put("uid", App.getUserData().uid_v2 + "\\\n");
            map.put("is_vip", App.getUserData().isvip + "\\\n");
            StringBuilder sb = new StringBuilder("\\\n\\\n");
            sb.append(com.alibaba.fastjson.JSONObject.toJSONString(map));
            jSONObject.putOpt("env", sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getMsg() {
        String str = "\n\napp_version:13.4\nappid:" + App.packageName + "\nplatform:Android\nAndroid:" + Build.VERSION.RELEASE + "\napi:" + Build.VERSION.SDK_INT + "\ndevice_name:" + Build.MODEL + "\nisEmulator:" + isEmulator() + "\nuser_name:" + App.getUserData().nickname + "\nuid:" + App.getUserData().uid_v2 + "\nis_vip:" + App.getUserData().isvip;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_version", BuildConfig.VERSION_NAME);
            jSONObject.putOpt("appid", App.packageName);
            jSONObject.putOpt("platform", "Android");
            HashMap map = new HashMap();
            map.put("Android", Build.VERSION.RELEASE + "\\\n");
            map.put("api", Build.VERSION.SDK_INT + "\\\n");
            map.put("device_name", Build.MODEL + "\\\n");
            map.put("isEmulator", isEmulator() + "\\\n");
            map.put("app_version", "13.4\\\n");
            map.put("user_name", App.getUserData().name + "\\\n");
            map.put("uid", App.getUserData().uid_v2 + "\\\n");
            map.put("is_vip", App.getUserData().isvip + "\\\n");
            StringBuilder sb = new StringBuilder("\\\n\\\n");
            sb.append(com.alibaba.fastjson.JSONObject.toJSONString(map));
            jSONObject.putOpt("env", sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean isEmail(String email) {
        if (email == null || "".equals(email)) {
            return false;
        }
        return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(email).matches();
    }

    public static boolean isMobileNO(String mobiles) {
        return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(mobiles).matches();
    }
}
