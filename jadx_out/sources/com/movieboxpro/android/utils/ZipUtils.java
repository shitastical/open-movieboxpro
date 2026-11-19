package com.movieboxpro.android.utils;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes5.dex */
public class ZipUtils {
    private static final int BUFFER_LEN = 8192;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean zipFiles(final Collection<String> srcFiles, final String zipFilePath) throws IOException {
        return zipFiles(srcFiles, zipFilePath, (String) null);
    }

    public static boolean zipFiles(final Collection<String> srcFilePaths, final String zipFilePath, final String comment) throws Throwable {
        if (srcFilePaths == null || zipFilePath == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(zipFilePath));
            try {
                Iterator<String> it = srcFilePaths.iterator();
                while (it.hasNext()) {
                    if (!zipFile(getFileByPath(it.next()), "", zipOutputStream2, comment)) {
                        zipOutputStream2.finish();
                        zipOutputStream2.close();
                        return false;
                    }
                }
                zipOutputStream2.finish();
                zipOutputStream2.close();
                return true;
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean zipFiles(final Collection<File> srcFiles, final File zipFile) throws IOException {
        return zipFiles(srcFiles, zipFile, (String) null);
    }

    public static boolean zipFiles(final Collection<File> srcFiles, final File zipFile, final String comment) throws Throwable {
        if (srcFiles == null || zipFile == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(zipFile));
            try {
                Iterator<File> it = srcFiles.iterator();
                while (it.hasNext()) {
                    if (!zipFile(it.next(), "", zipOutputStream2, comment)) {
                        zipOutputStream2.finish();
                        zipOutputStream2.close();
                        return false;
                    }
                }
                zipOutputStream2.finish();
                zipOutputStream2.close();
                return true;
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean zipFile(final String srcFilePath, final String zipFilePath) throws IOException {
        return zipFile(getFileByPath(srcFilePath), getFileByPath(zipFilePath), (String) null);
    }

    public static boolean zipFile(final String srcFilePath, final String zipFilePath, final String comment) throws IOException {
        return zipFile(getFileByPath(srcFilePath), getFileByPath(zipFilePath), comment);
    }

    public static boolean zipFile(final File srcFile, final File zipFile) throws IOException {
        return zipFile(srcFile, zipFile, (String) null);
    }

    public static boolean zipFile(final File srcFile, final File zipFile, final String comment) throws Throwable {
        if (srcFile == null || zipFile == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(zipFile));
            try {
                boolean zZipFile = zipFile(srcFile, "", zipOutputStream2, comment);
                zipOutputStream2.close();
                return zZipFile;
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean zipFile(final File srcFile, String rootPath, final ZipOutputStream zos, final String comment) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append(rootPath);
        sb.append(isSpace(rootPath) ? "" : File.separator);
        sb.append(srcFile.getName());
        String string = sb.toString();
        if (srcFile.isDirectory()) {
            File[] fileArrListFiles = srcFile.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(string + '/');
                zipEntry.setComment(comment);
                zos.putNextEntry(zipEntry);
                zos.closeEntry();
                return true;
            }
            for (File file : fileArrListFiles) {
                if (!zipFile(file, string, zos, comment)) {
                    return false;
                }
            }
            return true;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(srcFile));
            try {
                ZipEntry zipEntry2 = new ZipEntry(string);
                zipEntry2.setComment(comment);
                zos.putNextEntry(zipEntry2);
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = bufferedInputStream2.read(bArr, 0, 8192);
                    if (i != -1) {
                        zos.write(bArr, 0, i);
                    } else {
                        zos.closeEntry();
                        bufferedInputStream2.close();
                        return true;
                    }
                }
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static List<File> unzipFile(final String zipFilePath, final String destDirPath) throws IOException {
        return unzipFileByKeyword(zipFilePath, destDirPath, (String) null);
    }

    public static List<File> unzipFile(final File zipFile, final File destDir) throws IOException {
        return unzipFileByKeyword(zipFile, destDir, (String) null);
    }

    public static List<File> unzipFileByKeyword(final String zipFilePath, final String destDirPath, final String keyword) throws IOException {
        return unzipFileByKeyword(getFileByPath(zipFilePath), getFileByPath(destDirPath), keyword);
    }

    public static List<File> unzipFileByKeyword(final File zipFile, final File destDir, final String keyword) throws IOException {
        if (zipFile == null || destDir == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile2 = new ZipFile(zipFile);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile2.entries();
        try {
            if (isSpace(keyword)) {
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String name = zipEntryNextElement.getName();
                    if (name.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + name + " is dangerous!");
                    } else if (!unzipChildFile(destDir, arrayList, zipFile2, zipEntryNextElement, name)) {
                        zipFile2.close();
                        return arrayList;
                    }
                }
            } else {
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement2 = enumerationEntries.nextElement();
                    String name2 = zipEntryNextElement2.getName();
                    if (name2.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + name2 + " is dangerous!");
                    } else if (name2.contains(keyword) && !unzipChildFile(destDir, arrayList, zipFile2, zipEntryNextElement2, name2)) {
                        zipFile2.close();
                        return arrayList;
                    }
                }
            }
            zipFile2.close();
            return arrayList;
        } catch (Throwable th) {
            zipFile2.close();
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0056  */
    private static boolean unzipChildFile(final File destDir, final List<File> files, final ZipFile zip, final ZipEntry entry, final String name) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        Throwable th;
        File file = new File(destDir, name);
        files.add(file);
        if (entry.isDirectory()) {
            return createOrExistsDir(file);
        }
        if (!createOrExistsFile(file)) {
            return false;
        }
        try {
            bufferedInputStream = new BufferedInputStream(zip.getInputStream(entry));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i = bufferedInputStream.read(bArr);
                        if (i != -1) {
                            bufferedOutputStream.write(bArr, 0, i);
                        } else {
                            bufferedInputStream.close();
                            bufferedOutputStream.close();
                            return true;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
                th = th;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream = null;
            bufferedInputStream = null;
        }
    }

    public static List<String> getFilesPath(final String zipFilePath) throws IOException {
        return getFilesPath(getFileByPath(zipFilePath));
    }

    public static List<String> getFilesPath(final File zipFile) throws IOException {
        if (zipFile == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile2 = new ZipFile(zipFile);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile2.entries();
        while (enumerationEntries.hasMoreElements()) {
            String name = enumerationEntries.nextElement().getName();
            if (name.contains("../")) {
                Log.e("ZipUtils", "entryName: " + name + " is dangerous!");
                arrayList.add(name);
            } else {
                arrayList.add(name);
            }
        }
        zipFile2.close();
        return arrayList;
    }

    public static List<String> getComments(final String zipFilePath) throws IOException {
        return getComments(getFileByPath(zipFilePath));
    }

    public static List<String> getComments(final File zipFile) throws IOException {
        if (zipFile == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile2 = new ZipFile(zipFile);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile2.entries();
        while (enumerationEntries.hasMoreElements()) {
            arrayList.add(enumerationEntries.nextElement().getComment());
        }
        zipFile2.close();
        return arrayList;
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(final File file) {
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

    private static File getFileByPath(final String filePath) {
        if (isSpace(filePath)) {
            return null;
        }
        return new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
