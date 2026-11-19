package com.movieboxpro.android.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class MemoryUtils {
    public static final String MEMFREE = "MemFree";
    public static final String MEMTOTAL = "MemTotal";
    private static final String MEM_INFO_PATH = "/proc/meminfo";
    private static final String TAG = "MemoryUtils";

    public static String getTotalMemory(Context context, String memtotal) {
        return getMemInfoIype(context, MEMTOTAL);
    }

    public static String getMemoryFree(Context context, String memfree) {
        return getMemInfoIype(context, MEMFREE);
    }

    public static String getMemInfoIype(Context context, String type) {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(MEM_INFO_PATH), 4096);
            do {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
            } while (!line.contains(type));
            bufferedReader.close();
            return Formatter.formatFileSize(context, Integer.valueOf(line.split("\\s+")[1]).intValue() * 1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getInternalToatalSpace(Context context) {
        String path = Environment.getDataDirectory().getPath();
        Log.d(TAG, "root path is " + path);
        StatFs statFs = new StatFs(path);
        long blockSize = (long) statFs.getBlockSize();
        long blockCount = statFs.getBlockCount();
        statFs.getAvailableBlocks();
        return Formatter.formatFileSize(context, blockCount * blockSize);
    }

    public static String getAvailSpace(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        return Formatter.formatFileSize(context, ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
    }
}
