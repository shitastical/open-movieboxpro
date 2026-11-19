package com.jeffmony.downloader.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes5.dex */
public class LogUtils {
    private static final int LOG_DEBUG = 2;
    private static final int LOG_ERROR = 5;
    private static final int LOG_INFO = 3;
    private static final int LOG_WARN = 4;
    private static int sLogLevel = 3;

    public static void setLogLevel(int i) {
        sLogLevel = i;
    }

    public static void d(String str, String str2) {
        if (sLogLevel <= 2) {
            Log.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (sLogLevel <= 3) {
            Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (sLogLevel <= 4) {
            Log.w(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (sLogLevel <= 5) {
            Log.e(str, str2);
        }
    }
}
