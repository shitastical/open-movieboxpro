package com.movieboxpro.android.utils;

import java.util.Random;

/* JADX INFO: loaded from: classes5.dex */
public class StringUtils {
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";

    public static String formatContent(String str) {
        return getTwoSpaces() + str.replaceAll("[ ]*", "").replaceAll("[ ]*", "").replace("<br/>", ShellUtil.COMMAND_LINE_END).replace("</p>", ShellUtil.COMMAND_LINE_END).replace("\n\n", ShellUtil.COMMAND_LINE_END).replace(ShellUtil.COMMAND_LINE_END, ShellUtil.COMMAND_LINE_END + getTwoSpaces());
    }

    public static String formatContent2(String str) {
        return str.replaceAll("[ ]*", "").replaceAll("[ ]*", "").replace("<br/>", ShellUtil.COMMAND_LINE_END).replace("</p>", ShellUtil.COMMAND_LINE_END).replace("\n\n", ShellUtil.COMMAND_LINE_END).replace(ShellUtil.COMMAND_LINE_END, ShellUtil.COMMAND_LINE_END);
    }

    private static String getTwoSpaces() {
        return "\u3000\u3000";
    }

    public static String getRandomString(final int sizeOfRandomString) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; i++) {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(36)));
        }
        return sb.toString();
    }

    public static String getPrintSize(long size) {
        if (size < 1024) {
            return size + "B";
        }
        long j = size / 1024;
        if (j < 1024) {
            return j + "KB";
        }
        long j2 = j / 1024;
        if (j2 < 1024) {
            long j3 = j2 * 100;
            return (j3 / 100) + "." + (j3 % 100) + "MB";
        }
        long j4 = (j2 * 100) / 1024;
        return (j4 / 100) + "." + (j4 % 100) + "GB";
    }
}
