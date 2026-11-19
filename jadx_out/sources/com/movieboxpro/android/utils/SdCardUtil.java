package com.movieboxpro.android.utils;

import android.os.Environment;
import android.os.StatFs;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class SdCardUtil {
    private static final String TAG = "SdCardUtil";

    public boolean isSdCardAvailable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static StatFs getStatFs(String path) {
        return new StatFs(path);
    }

    public static String getDataPath() {
        return Environment.getDataDirectory().getPath();
    }

    public static String getNormalSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static String getSDCardPath() throws Throwable {
        BufferedReader bufferedReader = null;
        bufferedReader = null;
        bufferedReader = null;
        try {
            try {
                try {
                    Process processExec = Runtime.getRuntime().exec("cat /proc/mounts");
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(processExec.getInputStream())));
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                bufferedReader2.close();
                                bufferedReader = line;
                                break;
                            }
                            if (line.contains("sdcard") && line.contains(".android_secure")) {
                                String[] strArrSplit = line.split(" ");
                                if (strArrSplit.length >= 5) {
                                    String strReplace = strArrSplit[1].replace("/.android_secure", "");
                                    try {
                                        bufferedReader2.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return strReplace;
                                }
                            }
                            if (processExec.waitFor() != 0) {
                                processExec.exitValue();
                            }
                        } catch (Exception e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                bufferedReader = bufferedReader;
                            }
                            return Environment.getExternalStorageDirectory().getPath();
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static ArrayList<String> getSDCardPathEx() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("mount").getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (!line.contains("secure") && !line.contains("asec")) {
                    if (line.contains("fat")) {
                        String[] strArrSplit = line.split(" ");
                        if (strArrSplit.length > 1) {
                            arrayList.add("*" + strArrSplit[1]);
                        }
                    } else if (line.contains("fuse")) {
                        String[] strArrSplit2 = line.split(" ");
                        if (strArrSplit2.length > 1) {
                            arrayList.add(strArrSplit2[1]);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static long getAvailableSize(String path) {
        try {
            StatFs statFs = new StatFs(new File(path).getPath());
            return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static SDCardInfo getSDCardInfo() {
        SDCardInfo sDCardInfo = new SDCardInfo();
        if ("mounted".equals(Environment.getExternalStorageState())) {
            sDCardInfo.isExist = true;
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            sDCardInfo.totalBlocks = statFs.getBlockCountLong();
            sDCardInfo.blockByteSize = statFs.getBlockSizeLong();
            sDCardInfo.availableBlocks = statFs.getAvailableBlocksLong();
            sDCardInfo.availableBytes = statFs.getAvailableBytes();
            sDCardInfo.freeBlocks = statFs.getFreeBlocksLong();
            sDCardInfo.freeBytes = statFs.getFreeBytes();
            sDCardInfo.totalBytes = statFs.getTotalBytes();
        }
        return sDCardInfo;
    }

    public static class SDCardInfo {
        public long availableBlocks;
        public long availableBytes;
        public long blockByteSize;
        public long freeBlocks;
        public long freeBytes;
        public boolean isExist;
        public long totalBlocks;
        public long totalBytes;

        public String toString() {
            return "SDCardInfo{isExist=" + this.isExist + ", totalBlocks=" + this.totalBlocks + ", freeBlocks=" + this.freeBlocks + ", availableBlocks=" + this.availableBlocks + ", blockByteSize=" + this.blockByteSize + ", totalBytes=" + this.totalBytes + ", freeBytes=" + this.freeBytes + ", availableBytes=" + this.availableBytes + '}';
        }
    }
}
