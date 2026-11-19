package com.dl7.player.utils;

import android.content.Context;
import android.content.res.Resources;
import androidx.media3.extractor.metadata.icy.IcyHeaders;

/* JADX INFO: loaded from: classes4.dex */
public final class NavUtils {
    private NavUtils() {
        throw new RuntimeException("NavUtils cannot be initialized!");
    }

    public static int getNavigationBarHeight(Context context) {
        if (!checkDeviceHasNavigationBar(context)) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = false;
        boolean z2 = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
            if (!IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(str)) {
                z = "0".equals(str) ? true : z2;
            }
            return z;
        } catch (Exception unused) {
            return z2;
        }
    }
}
