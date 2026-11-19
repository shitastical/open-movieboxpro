package com.jeffmony.downloader.utils;

import android.content.Context;

/* JADX INFO: loaded from: classes5.dex */
public class ContextUtils {
    private static Context sApplicationContext;

    public static void initApplicationContext(Context context) {
        if (context == null) {
            throw new RuntimeException("Global application context set error");
        }
        sApplicationContext = context.getApplicationContext();
    }

    public static Context getApplicationContext() {
        return sApplicationContext;
    }
}
