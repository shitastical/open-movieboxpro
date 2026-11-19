package com.movieboxpro.android.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes5.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";

    /* JADX WARN: Code duplicated, block: B:34:0x0050 A[Catch: IOException -> 0x004c, TRY_LEAVE, TryCatch #7 {IOException -> 0x004c, blocks: (B:30:0x0048, B:34:0x0050), top: B:42:0x0048 }] */
    public static void fileChannelCopy(File s, File t) throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileInputStream = new FileInputStream(s);
                try {
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(t);
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
                e4.printStackTrace();
            }
        } catch (IOException e5) {
            e = e5;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public static String formatFileSizeToString(long fileLen) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
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

    public static boolean deleteFile(File file) throws IOException {
        return file != null && file.delete();
    }

    public static String getFileOutputString(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path), 8192);
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
}
