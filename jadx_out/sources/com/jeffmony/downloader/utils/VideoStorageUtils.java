package com.jeffmony.downloader.utils;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes5.dex */
public class VideoStorageUtils {
    public static File getVideoCacheDir(Context context) {
        return new File(context.getExternalFilesDir("Video"), "Download");
    }

    public static void clearVideoCacheDir() throws IOException {
        if (ContextUtils.getApplicationContext() != null) {
            cleanDirectory(getVideoCacheDir(ContextUtils.getApplicationContext()));
        }
    }

    private static void cleanDirectory(File file) throws IOException {
        File[] fileArrListFiles;
        if (file.exists() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                delete(file2);
            }
        }
    }

    public static void delete(File file) throws IOException {
        if (file.isFile() && file.exists()) {
            deleteOrThrow(file);
        } else {
            cleanDirectory(file);
            deleteOrThrow(file);
        }
    }

    private static void deleteOrThrow(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException(String.format("File %s can't be deleted", file.getAbsolutePath()));
        }
    }

    public static long countTotalSize(File file) {
        if (file.isDirectory()) {
            long jCountTotalSize = 0;
            for (File file2 : file.listFiles()) {
                jCountTotalSize += countTotalSize(file2);
            }
            return jCountTotalSize;
        }
        return file.length();
    }

    public static String getSizeStr(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        DecimalFormat decimalFormat = new DecimalFormat("###.00");
        if (j >= IjkMediaMeta.AV_CH_STEREO_RIGHT) {
            stringBuffer.append(decimalFormat.format(j / 1.073741824E9d)).append("GB");
        } else if (j >= 1048576) {
            stringBuffer.append(decimalFormat.format(j / 1048576.0d)).append("MB");
        } else if (j >= 1024) {
            stringBuffer.append(decimalFormat.format(j / 1024.0d)).append("KB");
        } else if (j < 1024) {
            if (j <= 0) {
                stringBuffer.append("0B");
            } else {
                stringBuffer.append((int) j).append("B");
            }
        }
        return stringBuffer.toString();
    }
}
