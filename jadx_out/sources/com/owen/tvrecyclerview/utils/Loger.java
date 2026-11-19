package com.owen.tvrecyclerview.utils;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes5.dex */
public class Loger {
    private static final String customTagPrefix = "[TVRV_LOG]";
    public static boolean isDebug = false;

    private Loger() {
    }

    private static String generateTag() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        String className = stackTraceElement.getClassName();
        String str = String.format("%s.%s(L:%d)", className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()));
        if (TextUtils.isEmpty(customTagPrefix)) {
            return str;
        }
        return "[TVRV_LOG]:" + str;
    }

    public static void d(String str) {
        if (isDebug) {
            Log.d(generateTag(), str);
        }
    }

    public static void d(String str, Throwable th) {
        if (isDebug) {
            Log.d(generateTag(), str, th);
        }
    }

    public static void e(String str) {
        if (isDebug) {
            Log.e(generateTag(), str);
        }
    }

    public static void e(String str, Throwable th) {
        if (isDebug) {
            Log.e(generateTag(), str, th);
        }
    }

    public static void i(String str) {
        if (isDebug) {
            Log.i(generateTag(), str);
        }
    }

    public static void i(String str, Throwable th) {
        if (isDebug) {
            Log.i(generateTag(), str, th);
        }
    }

    public static void v(String str) {
        if (isDebug) {
            Log.v(generateTag(), str);
        }
    }

    public static void v(String str, Throwable th) {
        if (isDebug) {
            Log.v(generateTag(), str, th);
        }
    }

    public static void w(String str) {
        if (isDebug) {
            Log.w(generateTag(), str);
        }
    }

    public static void w(String str, Throwable th) {
        if (isDebug) {
            Log.w(generateTag(), str, th);
        }
    }

    public static void w(Throwable th) {
        if (isDebug) {
            Log.w(generateTag(), th);
        }
    }

    public static void wtf(String str) {
        if (isDebug) {
            Log.wtf(generateTag(), str);
        }
    }

    public static void wtf(String str, Throwable th) {
        if (isDebug) {
            Log.wtf(generateTag(), str, th);
        }
    }

    public static void wtf(Throwable th) {
        if (isDebug) {
            Log.wtf(generateTag(), th);
        }
    }
}
