package com.dl7.player.utils;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public class SDCardUtils {
    public static String getState() {
        return Environment.getExternalStorageState();
    }

    public static boolean isAvailable() {
        return getState().equals("mounted");
    }

    public static File getRootDirectory() {
        if (isAvailable()) {
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }

    public static String getRootPath() {
        File rootDirectory = getRootDirectory();
        if (rootDirectory != null) {
            return rootDirectory.getPath();
        }
        return null;
    }

    public static String getSDPath() {
        return (Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : null).toString();
    }

    public static long getFreeSpaceBytes(String str) {
        return new StatFs(str).getAvailableBytes();
    }
}
