package com.dueeeke.utils;

import com.movieboxpro.android.utils.ShellUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes4.dex */
public class StringUtils {
    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean string2File(String str, String str2) throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            try {
                File file = new File(str2);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                BufferedReader bufferedReader2 = new BufferedReader(new StringReader(str));
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    char[] cArr = new char[1024];
                    while (true) {
                        int i = bufferedReader2.read(cArr);
                        if (i != -1) {
                            bufferedWriter.write(cArr, 0, i);
                        } else {
                            bufferedWriter.flush();
                            bufferedReader2.close();
                            bufferedWriter.close();
                            try {
                                bufferedReader2.close();
                                return true;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return true;
                            }
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
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

    public static String generateTime(long j) {
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = i / 60;
        return i3 > 99 ? String.format("%d:%02d", Integer.valueOf(i3), Integer.valueOf(i2)) : String.format("%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2));
    }

    public static String getFormatSize(int i) {
        long j = i;
        if (j >= 0 && j < 1024) {
            return j + "Kb/s";
        }
        if (j >= 1024 && j < 1048576) {
            return Long.toString(j / 1024) + "KB/s";
        }
        if (j >= 1048576 && j < IjkMediaMeta.AV_CH_STEREO_RIGHT) {
            return Long.toString(j / 1048576) + "MB/s";
        }
        return "";
    }

    public static String getCurFormatTime() {
        return new SimpleDateFormat(com.movieboxpro.android.utils.TimeUtils.PATTERN_MINUTE_SECOND).format(new Date(System.currentTimeMillis()));
    }

    public static String formatContent(String str) {
        return str.replaceAll("[ ]*", "").replaceAll("[ ]*", "").replace("<br/>", ShellUtil.COMMAND_LINE_END).replace("</p>", ShellUtil.COMMAND_LINE_END).replace("\n\n", ShellUtil.COMMAND_LINE_END);
    }

    private static String getTwoSpaces() {
        return "\u3000\u3000";
    }
}
