package com.dueeeke.utils;

import org.joda.time.DateTimeConstants;

/* JADX INFO: loaded from: classes4.dex */
public class TimeUtils {
    public static String getTime(int i) {
        if (i < 10) {
            return "00:0" + i;
        }
        if (i < 60) {
            return "00:" + i;
        }
        if (i < 3600) {
            int i2 = i / 60;
            int i3 = i - (i2 * 60);
            if (i2 < 10) {
                if (i3 < 10) {
                    return "0" + i2 + ":0" + i3;
                }
                return "0" + i2 + ":" + i3;
            }
            if (i3 < 10) {
                return i2 + ":0" + i3;
            }
            return i2 + ":" + i3;
        }
        int i4 = i / DateTimeConstants.SECONDS_PER_HOUR;
        int i5 = i - (i4 * DateTimeConstants.SECONDS_PER_HOUR);
        int i6 = i5 / 60;
        int i7 = i5 - (i6 * 60);
        if (i4 < 10) {
            if (i6 < 10) {
                if (i7 < 10) {
                    return "0" + i4 + ":0" + i6 + ":0" + i7;
                }
                return "0" + i4 + ":0" + i6 + ":" + i7;
            }
            if (i7 < 10) {
                return "0" + i4 + ":" + i6 + ":0" + i7;
            }
            return "0" + i4 + ":" + i6 + ":" + i7;
        }
        if (i6 < 10) {
            if (i7 < 10) {
                return i4 + ":0" + i6 + ":0" + i7;
            }
            return i4 + ":0" + i6 + ":" + i7;
        }
        if (i7 < 10) {
            return i4 + ":" + i6 + ":0" + i7;
        }
        return i4 + ":" + i6 + ":" + i7;
    }
}
