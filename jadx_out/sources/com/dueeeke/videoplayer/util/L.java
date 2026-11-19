package com.dueeeke.videoplayer.util;

import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class L {
    private static final String TAG = "DKPlayer";
    private static boolean isDebug = false;

    public static void d(String str) {
        if (isDebug) {
            Log.d(TAG, str);
        }
    }

    public static void e(String str) {
        if (isDebug) {
            Log.e(TAG, str);
        }
    }

    public static void i(String str) {
        if (isDebug) {
            Log.i(TAG, str);
        }
    }

    public static void setDebug(boolean z) {
        isDebug = z;
    }
}
