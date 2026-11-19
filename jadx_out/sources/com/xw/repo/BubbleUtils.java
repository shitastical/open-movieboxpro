package com.xw.repo;

import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: loaded from: classes5.dex */
class BubbleUtils {
    private static final String KEY_MIUI_MANE = "ro.miui.ui.version.name";
    private static Boolean miui;
    private static Properties sProperties = new Properties();

    BubbleUtils() {
    }

    static boolean isMIUI() throws Throwable {
        Boolean bool = miui;
        if (bool != null) {
            return bool.booleanValue();
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                if (Build.VERSION.SDK_INT < 26) {
                    try {
                        FileInputStream fileInputStream2 = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                        try {
                            sProperties.load(fileInputStream2);
                            fileInputStream2.close();
                        } catch (IOException e) {
                            e = e;
                            fileInputStream = fileInputStream2;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            miui = Boolean.valueOf(sProperties.containsKey(KEY_MIUI_MANE));
                            return miui.booleanValue();
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                    }
                    miui = Boolean.valueOf(sProperties.containsKey(KEY_MIUI_MANE));
                } else {
                    try {
                        miui = Boolean.valueOf(!TextUtils.isEmpty((String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, KEY_MIUI_MANE)));
                    } catch (Exception unused) {
                        miui = false;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return miui.booleanValue();
    }

    static int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, Resources.getSystem().getDisplayMetrics());
    }

    static int sp2px(int i) {
        return (int) TypedValue.applyDimension(2, i, Resources.getSystem().getDisplayMetrics());
    }
}
