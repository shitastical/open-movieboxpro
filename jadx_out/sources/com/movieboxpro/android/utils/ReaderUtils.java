package com.movieboxpro.android.utils;

import android.content.Context;
import android.os.Environment;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes5.dex */
public class ReaderUtils {
    private static String getChapterPath(String bookId, String sourceId, int chapter) {
        return Constant.DIR_NOVEL + File.separator + bookId + File.separator + sourceId + File.separator + chapter + ".dat";
    }

    public static File getChapterFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            createFile(file);
        }
        return file;
    }

    public static File createWifiTempFile() {
        File file = new File(Constant.DIR_CACHE + File.separator + System.currentTimeMillis());
        if (!file.exists()) {
            createFile(file);
        }
        return file;
    }

    public static File createWifiTranfesFile(String fileName) {
        File file = new File(Constant.DIR_CACHE + File.separator + fileName + File.separator + "1.txt");
        if (!file.exists()) {
            createFile(file);
        }
        return file;
    }

    public static String getPathOPF(String unzipDir) {
        String strTrim = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(unzipDir + "/META-INF/container.xml"), "UTF-8"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("full-path")) {
                    int iIndexOf = line.indexOf(34, line.indexOf("full-path"));
                    int i = iIndexOf + 1;
                    int iIndexOf2 = line.indexOf(34, i);
                    if (iIndexOf > -1 && iIndexOf2 > iIndexOf) {
                        strTrim = line.substring(i, iIndexOf2).trim();
                        break;
                    }
                }
            }
            bufferedReader.close();
            if (!strTrim.contains("/")) {
                return null;
            }
            int iLastIndexOf = strTrim.lastIndexOf(47);
            return iLastIndexOf > -1 ? strTrim.substring(0, iLastIndexOf) : strTrim;
        } catch (IOException | NullPointerException unused) {
            return "";
        }
    }

    public static boolean checkOPFInRootDirectory(String unzipDir) {
        String strTrim;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(unzipDir + "/META-INF/container.xml"), "UTF-8"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    strTrim = "";
                    break;
                }
                if (line.contains("full-path")) {
                    int iIndexOf = line.indexOf(34, line.indexOf("full-path"));
                    int i = iIndexOf + 1;
                    int iIndexOf2 = line.indexOf(34, i);
                    if (iIndexOf > -1 && iIndexOf2 > iIndexOf) {
                        strTrim = line.substring(i, iIndexOf2).trim();
                        break;
                    }
                }
            }
            bufferedReader.close();
            return !strTrim.contains("/");
        } catch (IOException | NullPointerException unused) {
            return false;
        }
    }

    public static void unzipFile(String inputZip, String destinationDirectory) throws IOException {
        ArrayList<String> arrayList = new ArrayList();
        File file = new File(inputZip);
        File file2 = new File(destinationDirectory);
        createDir(file2.getAbsolutePath());
        ZipFile zipFile = new ZipFile(file, 1);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            String name = zipEntryNextElement.getName();
            File file3 = new File(file2, name);
            if (name.endsWith(Constant.SUFFIX_ZIP)) {
                arrayList.add(file3.getAbsolutePath());
            }
            createDir(file3.getParentFile().getAbsolutePath());
            if (!zipEntryNextElement.isDirectory() && !file3.exists()) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                byte[] bArr = new byte[2048];
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3), 2048);
                while (true) {
                    int i = bufferedInputStream.read(bArr, 0, 2048);
                    if (i == -1) {
                        break;
                    } else {
                        bufferedOutputStream.write(bArr, 0, i);
                    }
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
            }
        }
        zipFile.close();
        for (String str : arrayList) {
            unzipFile(str, destinationDirectory + File.separatorChar + str.substring(0, str.lastIndexOf(Constant.SUFFIX_ZIP)));
        }
    }

    public static byte[] readAssets(String fileName) {
        byte[] bArr = null;
        if (fileName != null) {
            try {
                if (fileName.length() > 0) {
                    try {
                        InputStream inputStreamOpen = App.getContext().getAssets().open("uploader" + fileName);
                        bArr = new byte[inputStreamOpen.available()];
                        inputStreamOpen.read(bArr);
                        inputStreamOpen.close();
                        return bArr;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return bArr;
    }

    public static String createRootPath(Context context) {
        if (isSdCardAvailable()) {
            return context.getExternalCacheDir().getPath();
        }
        return context.getCacheDir().getPath();
    }

    public static boolean isSdCardAvailable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String createDir(String dirPath) {
        try {
            File file = new File(dirPath);
            if (file.getParentFile().exists()) {
                file.mkdir();
                return file.getAbsolutePath();
            }
            createDir(file.getParentFile().getAbsolutePath());
            file.mkdir();
            return dirPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String createFile(File file) {
        try {
            if (file.getParentFile().exists()) {
                file.createNewFile();
                return file.getAbsolutePath();
            }
            createDir(file.getParentFile().getAbsolutePath());
            file.createNewFile();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void writeFile(String filePath, String content, boolean isAppend) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath, isAppend);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String filePathAndName, String fileContent) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePathAndName));
            outputStreamWriter.write(fileContent);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resId)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(f);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
            byte[] bArr = new byte[1000];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0053 A[Catch: IOException -> 0x004f, TRY_LEAVE, TryCatch #4 {IOException -> 0x004f, blocks: (B:30:0x004b, B:34:0x0053), top: B:41:0x004b }] */
    public static void fileChannelCopy(File src, File desc) throws Throwable {
        FileInputStream fileInputStream;
        createFile(desc);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(src);
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(desc);
                        try {
                            FileChannel channel = fileInputStream.getChannel();
                            channel.transferTo(0L, channel.size(), fileOutputStream2.getChannel());
                            fileOutputStream2.close();
                            fileInputStream.close();
                        } catch (IOException e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                    throw th;
                                }
                            } else if (fileInputStream != null) {
                                fileInputStream.close();
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
                e = e4;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public static String formatFileSizeToString(long fileLen) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (fileLen < 1024) {
            return decimalFormat.format(fileLen) + "B";
        }
        if (fileLen < 1048576) {
            return decimalFormat.format(fileLen / 1024.0d) + "K";
        }
        if (fileLen < IjkMediaMeta.AV_CH_STEREO_RIGHT) {
            return decimalFormat.format(fileLen / 1048576.0d) + "M";
        }
        return decimalFormat.format(fileLen / 1.073741824E9d) + "G";
    }

    public static boolean deleteFile(File file) {
        return deleteFileOrDirectory(file);
    }

    private static boolean deleteFileOrDirectory(File file) {
        if (file != null && file.isFile()) {
            return file.delete();
        }
        if (file == null || !file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return file.delete();
        }
        for (File file2 : fileArrListFiles) {
            deleteFileOrDirectory(file2);
        }
        return file.delete();
    }

    public static long getFileSize(String dir) throws Exception {
        long length;
        long j = 0;
        try {
            File[] fileArrListFiles = new File(dir).listFiles();
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    length = getFileSize(fileArrListFiles[i].getAbsolutePath());
                } else {
                    length = fileArrListFiles[i].length();
                }
                j += length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static String getExtensionName(String filename) {
        int iLastIndexOf;
        return (filename == null || filename.length() <= 0 || (iLastIndexOf = filename.lastIndexOf(46)) <= -1 || iLastIndexOf >= filename.length() + (-1)) ? filename : filename.substring(iLastIndexOf + 1);
    }

    public static String getFileOutputString(String path, String charset) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), charset), 8192);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(ShellUtil.COMMAND_LINE_END);
                    sb.append(line);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private synchronized void getAllFiles(File root, String ext) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = root.listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.isDirectory()) {
                    getAllFiles(file, ext);
                } else if (file.getName().endsWith(ext) && file.length() > 50) {
                    arrayList.add(file);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:61:0x0093 A[EDGE_INSN: B:61:0x0093->B:87:0x0094 BREAK  A[LOOP:0: B:33:0x0055->B:111:0x0055]] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public static String getCharset(String str) throws Throwable {
        String str2;
        int i;
        String str3 = "GBK";
        byte[] bArr = new byte[3];
        ?? r3 = 0;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(str));
                try {
                    bufferedInputStream2.mark(0);
                    r3 = -1;
                    r3 = -1;
                    if (bufferedInputStream2.read(bArr, 0, 3) == -1) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return "GBK";
                    }
                    byte b = bArr[0];
                    str2 = "UTF-8";
                    boolean z = true;
                    if (b == -1 && bArr[1] == -2) {
                        str3 = "UTF-16LE";
                    } else if (b == -2 && bArr[1] == -1) {
                        str3 = "UTF-16BE";
                    } else if (b == -17 && bArr[1] == -69 && bArr[2] == -65) {
                        str3 = "UTF-8";
                    } else {
                        z = false;
                    }
                    bufferedInputStream2.mark(0);
                    if (z) {
                        str2 = str3;
                        break;
                    }
                    while (true) {
                        int i2 = bufferedInputStream2.read();
                        if (i2 == -1 || i2 >= 240 || (128 <= i2 && i2 <= 191)) {
                            str2 = str3;
                            break;
                        }
                        if (192 <= i2 && i2 <= 223) {
                            int i3 = bufferedInputStream2.read();
                            if (128 > i3 || i3 > 191) {
                                str2 = str3;
                                break;
                            }
                        } else if (224 <= i2 && i2 <= 239) {
                            int i4 = bufferedInputStream2.read();
                            if (128 <= i4 && i4 <= 191 && 128 <= (i = bufferedInputStream2.read()) && i <= 191) {
                                break;
                            }
                            str2 = str3;
                            break;
                        }
                    }
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e = e3;
                    bufferedInputStream = bufferedInputStream2;
                    e.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    str2 = str3;
                    r3 = bufferedInputStream;
                } catch (Throwable th) {
                    th = th;
                    r3 = bufferedInputStream2;
                    if (r3 != 0) {
                        try {
                            r3.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e6) {
            e = e6;
        }
        return str2;
    }

    public static String getCharset1(String fileName) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
        int i = (bufferedInputStream.read() << 8) + bufferedInputStream.read();
        if (i == 61371) {
            return "UTF-8";
        }
        if (i == 65279) {
            return "UTF-16BE";
        }
        if (i == 65534) {
            return "Unicode";
        }
        return "GBK";
    }

    public static void saveWifiTxt(String src, String desc) {
        byte[] bytes = ShellUtil.COMMAND_LINE_END.getBytes();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(src), getCharset(src)));
            FileOutputStream fileOutputStream = new FileOutputStream(desc, true);
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    fileOutputStream.write(line.getBytes());
                    fileOutputStream.write(bytes);
                } else {
                    bufferedReader.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
