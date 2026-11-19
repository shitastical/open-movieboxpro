package com.movieboxpro.android.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.google.common.base.Ascii;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes5.dex */
public class FileUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public interface OnReplaceListener {
        boolean onReplace();
    }

    public static long getFsAvailableSize(final String anyPathInFs) {
        if (TextUtils.isEmpty(anyPathInFs)) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(anyPathInFs);
            return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String getFileNameNoExtension(final String filePath) {
        if (filePath == null || TextUtils.isEmpty(filePath)) {
            return "";
        }
        int iLastIndexOf = filePath.lastIndexOf(46);
        int iLastIndexOf2 = filePath.lastIndexOf(File.separator);
        if (iLastIndexOf2 == -1) {
            return iLastIndexOf == -1 ? filePath : filePath.substring(0, iLastIndexOf);
        }
        if (iLastIndexOf == -1 || iLastIndexOf2 > iLastIndexOf) {
            return filePath.substring(iLastIndexOf2 + 1);
        }
        return filePath.substring(iLastIndexOf2 + 1, iLastIndexOf);
    }

    public static long getAvailSpace(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    public static long getAvailableSpace(String path) {
        StatFs statFs = new StatFs(path);
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    public static long getTotalSpace(String path) {
        StatFs statFs = new StatFs(path);
        return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
    }

    public static long getInternalTotalSpace(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        statFs.getAvailableBlocksLong();
        return blockCountLong * blockSizeLong;
    }

    public static boolean deleteAllInDir(final File dir) {
        return deleteFilesInDirWithFilter(dir, new FileFilter() { // from class: com.movieboxpro.android.utils.FileUtils.1
            @Override // java.io.FileFilter
            public boolean accept(File pathname) {
                return true;
            }
        });
    }

    public static boolean deleteFilesInDirWithFilter(final File dir, final FileFilter filter) {
        if (dir == null) {
            return false;
        }
        if (!dir.exists()) {
            return true;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = dir.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file : fileArrListFiles) {
                if (filter.accept(file)) {
                    if (file.isFile()) {
                        if (!file.delete()) {
                            return false;
                        }
                    } else if (file.isDirectory() && !deleteDir(file)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static long getFileSize(File file) {
        long j;
        long length;
        int length2 = 0;
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                int length3 = fileArrListFiles.length;
                int i = 0;
                while (length2 < length3) {
                    File file2 = fileArrListFiles[length2];
                    if (file2.isDirectory()) {
                        j = i;
                        length = getFileSize(file2);
                    } else {
                        j = i;
                        length = file2.length();
                    }
                    i = (int) (j + length);
                    length2++;
                }
                length2 = i;
            } else {
                length2 = (int) (((long) 0) + file.length());
            }
        }
        return length2;
    }

    public static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                for (File file2 : fileArrListFiles) {
                    if (file2.isDirectory()) {
                        deleteFile(file2);
                    } else {
                        file2.delete();
                    }
                }
                return;
            }
            file.delete();
            return;
        }
        file.delete();
    }

    public static void write(File file, String content, boolean append) throws Throwable {
        if (file == null) {
            return;
        }
        FileWriter fileWriter = null;
        try {
            try {
                try {
                    FileWriter fileWriter2 = new FileWriter(file, append);
                    try {
                        fileWriter2.write(content);
                        fileWriter2.flush();
                        fileWriter2.close();
                    } catch (IOException e) {
                        e = e;
                        fileWriter = fileWriter2;
                        e.printStackTrace();
                        if (fileWriter == null) {
                            return;
                        }
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (Throwable th) {
                        th = th;
                        fileWriter = fileWriter2;
                        if (fileWriter != null) {
                            try {
                                fileWriter.flush();
                                fileWriter.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public static boolean deleteDir(final File dir) {
        if (dir == null) {
            return false;
        }
        if (!dir.exists()) {
            return true;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = dir.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file : fileArrListFiles) {
                if (file.isFile()) {
                    if (!file.delete()) {
                        return false;
                    }
                } else if (file.isDirectory() && !deleteDir(file)) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static boolean delete(final File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return !file.exists() || (file.isFile() && file.delete());
    }

    public static String getExtensionName(String filename) {
        int iLastIndexOf;
        if (filename.length() > 0 && (iLastIndexOf = filename.lastIndexOf(46)) > -1 && iLastIndexOf < filename.length() - 1) {
            return filename.substring(iLastIndexOf + 1);
        }
        return filename.toLowerCase();
    }

    public static boolean copyOrMoveFile(final File srcFile, final File destFile, final OnReplaceListener listener, final boolean isMove) {
        if (srcFile != null && destFile != null && !srcFile.equals(destFile) && srcFile.exists() && srcFile.isFile()) {
            if (destFile.exists()) {
                if (listener != null && !listener.onReplace()) {
                    return true;
                }
                if (!destFile.delete()) {
                    return false;
                }
            }
            if (!createOrExistsDir(destFile.getParentFile())) {
                return false;
            }
            try {
                if (!writeFileFromIS(destFile, new FileInputStream(srcFile))) {
                    return false;
                }
                if (isMove) {
                    if (!srcFile.exists()) {
                        return false;
                    }
                    if (srcFile.isFile() && srcFile.delete()) {
                        return false;
                    }
                }
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean writeFileFromIS(final File file, final InputStream is) throws Throwable {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = is.read(bArr, 0, 8192);
                    if (i != -1) {
                        bufferedOutputStream2.write(bArr, 0, i);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                is.close();
                try {
                    bufferedOutputStream2.close();
                    return true;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return true;
                }
            } catch (IOException e3) {
                e = e3;
                bufferedOutputStream = bufferedOutputStream2;
                e.printStackTrace();
                try {
                    is.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    is.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                        throw th;
                    } catch (IOException e7) {
                        e7.printStackTrace();
                        throw th;
                    }
                }
                throw th;
            }
        } catch (IOException e8) {
            e = e8;
        }
    }

    public static boolean writeFileFromString(final File file, final String content, final boolean append) throws Throwable {
        if (file == null || content == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, append));
                try {
                    bufferedWriter2.write(content);
                    try {
                        bufferedWriter2.close();
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return true;
                    }
                } catch (IOException e2) {
                    e = e2;
                    bufferedWriter = bufferedWriter2;
                    e.printStackTrace();
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e = e5;
        }
    }

    public static boolean createOrExistsFile(final File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createOrExistsDir(final File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean isFileExists(String path) {
        if (path == null) {
            return false;
        }
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    public static void forceMkdir(File directory) throws IOException {
        if (directory.exists()) {
            if (directory.isDirectory()) {
                return;
            }
            throw new IOException("File " + directory + " exists and is not a directory. Unable to create directory.");
        }
        if (directory.mkdirs() || directory.isDirectory()) {
            return;
        }
        throw new IOException("Unable to create directory " + directory);
    }

    public static String fileExt(String filename) {
        int iLastIndexOf = filename.lastIndexOf(".");
        if (iLastIndexOf == -1) {
            return null;
        }
        return filename.substring(iLastIndexOf + 1).toLowerCase();
    }

    public static boolean isDir(File dir) {
        return dir != null && dir.exists() && dir.isDirectory();
    }

    public static List<File> listFilesInDir(File dir) {
        return listFilesInDirWithFilter(dir, new FileFilter() { // from class: com.movieboxpro.android.utils.FileUtils.2
            @Override // java.io.FileFilter
            public boolean accept(File pathname) {
                return true;
            }
        }, false);
    }

    public static List<File> listFilesInDirWithFilter(final File dir, final FileFilter filter, final boolean isRecursive) {
        if (!isDir(dir)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = dir.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file : fileArrListFiles) {
                if (filter.accept(file)) {
                    arrayList.add(file);
                }
                if (isRecursive && file.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilter(file, filter, true));
                }
            }
        }
        return arrayList;
    }

    public static String getAvailMemory(Context mContext) {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(mContext, memoryInfo.availMem);
    }

    public static String getRemoteFileName(String fileUrl) {
        int iLastIndexOf = fileUrl.lastIndexOf("/");
        int iIndexOf = fileUrl.indexOf("?");
        if (iIndexOf >= 0) {
            return fileUrl.substring(iLastIndexOf, iIndexOf);
        }
        return fileUrl.substring(iLastIndexOf, fileUrl.length());
    }

    public static String getFileMD5ToString(final File file) {
        return bytes2HexString(getFileMD5(file));
    }

    /* JADX WARN: Code duplicated, block: B:44:0x003e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0048: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:34:0x0048 */
    private static byte[] getFileMD5(final File file) throws Throwable {
        DigestInputStream digestInputStream;
        DigestInputStream digestInputStream2;
        DigestInputStream digestInputStream3 = null;
        try {
            if (file == null) {
                return null;
            }
            try {
                digestInputStream2 = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
                try {
                    while (digestInputStream2.read(new byte[262144]) > 0) {
                    }
                    byte[] bArrDigest = digestInputStream2.getMessageDigest().digest();
                    try {
                        digestInputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return bArrDigest;
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    if (digestInputStream2 != null) {
                        try {
                            digestInputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return null;
                } catch (NoSuchAlgorithmException e4) {
                    e = e4;
                    e.printStackTrace();
                    if (digestInputStream2 != null) {
                        digestInputStream2.close();
                    }
                    return null;
                }
            } catch (IOException e5) {
                e = e5;
                digestInputStream2 = null;
                e.printStackTrace();
                if (digestInputStream2 != null) {
                    digestInputStream2.close();
                }
                return null;
            } catch (NoSuchAlgorithmException e6) {
                e = e6;
                digestInputStream2 = null;
                e.printStackTrace();
                if (digestInputStream2 != null) {
                    digestInputStream2.close();
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (digestInputStream3 != null) {
                    try {
                        digestInputStream3.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            digestInputStream3 = digestInputStream;
        }
    }

    private static String bytes2HexString(final byte[] bytes) {
        int length;
        if (bytes == null || (length = bytes.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (byte b : bytes) {
            int i2 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(b >> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & Ascii.SI];
        }
        return new String(cArr);
    }

    public static String byte2FitMemorySize(final long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        }
        if (byteNum < 1024) {
            return String.format(Locale.getDefault(), "%.2fB", Double.valueOf(byteNum));
        }
        if (byteNum < 1048576) {
            return String.format(Locale.getDefault(), "%.2fKB", Double.valueOf(byteNum / 1024.0d));
        }
        return byteNum < IjkMediaMeta.AV_CH_STEREO_RIGHT ? String.format(Locale.getDefault(), "%.2fMB", Double.valueOf(byteNum / 1048576.0d)) : String.format(Locale.getDefault(), "%.2fGB", Double.valueOf(byteNum / 1.073741824E9d));
    }

    public static String byte2FitMemorySize2(final long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        }
        if (byteNum < 1024) {
            return String.format(Locale.getDefault(), "%.2f B", Double.valueOf(byteNum));
        }
        if (byteNum < 1048576) {
            return String.format(Locale.getDefault(), "%.2f KB", Double.valueOf(byteNum / 1024.0d));
        }
        return byteNum < IjkMediaMeta.AV_CH_STEREO_RIGHT ? String.format(Locale.getDefault(), "%.2f MB", Double.valueOf(byteNum / 1048576.0d)) : String.format(Locale.getDefault(), "%.2f GB", Double.valueOf(byteNum / 1.073741824E9d));
    }

    public static boolean writeFileFromIS(final File file, final InputStream is, final boolean append) throws Throwable {
        if (!createOrExistsFile(file) || is == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file, append));
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = is.read(bArr);
                    if (i != -1) {
                        bufferedOutputStream2.write(bArr, 0, i);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                is.close();
                try {
                    bufferedOutputStream2.close();
                    return true;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return true;
                }
            } catch (IOException e3) {
                e = e3;
                bufferedOutputStream = bufferedOutputStream2;
                e.printStackTrace();
                try {
                    is.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    is.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                        throw th;
                    } catch (IOException e7) {
                        e7.printStackTrace();
                        throw th;
                    }
                }
                throw th;
            }
        } catch (IOException e8) {
            e = e8;
        }
    }
}
