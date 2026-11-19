package com.dl7.player.utils;

import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.TimeUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTimeConstants;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes4.dex */
public final class StringUtils {
    private StringUtils() {
        throw new AssertionError();
    }

    public static String generateTime(long j) {
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        int i4 = i / DateTimeConstants.SECONDS_PER_HOUR;
        return i4 > 0 ? String.format("%d:%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2)) : String.format("%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2));
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
        return new SimpleDateFormat(TimeUtils.PATTERN_MINUTE_SECOND).format(new Date(System.currentTimeMillis()));
    }

    public static String formatContent(String str) {
        return str.replaceAll("[ ]*", "").replaceAll("[ ]*", "").replace("<br/>", ShellUtil.COMMAND_LINE_END).replace("</p>", ShellUtil.COMMAND_LINE_END).replace("\n\n", ShellUtil.COMMAND_LINE_END);
    }

    private static String getTwoSpaces() {
        return "\u3000\u3000";
    }
}
