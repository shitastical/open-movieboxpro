package me.jessyan.autosize.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes6.dex */
public class LogUtils {
    private static final String TAG = "AndroidAutoSize";
    private static boolean debug;

    private LogUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean z) {
        debug = z;
    }

    public static void d(String str) {
        if (debug) {
            Log.d(TAG, str);
        }
    }

    public static void w(String str) {
        if (debug) {
            Log.w(TAG, str);
        }
    }

    public static void e(String str) {
        if (debug) {
            Log.e(TAG, str);
        }
    }
}
