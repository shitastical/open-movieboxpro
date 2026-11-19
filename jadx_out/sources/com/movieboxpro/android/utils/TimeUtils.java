package com.movieboxpro.android.utils;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

/* JADX INFO: compiled from: TimeUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0007J\u001e\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0007J\u000e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\bJ\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\bH\u0007J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0014J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\bH\u0002J\u0010\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\bH\u0002J\u000e\u0010$\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\bJ\u000e\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u001d\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u000b¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/utils/TimeUtils;", "", "<init>", "()V", "DETAULT_PATTERN", "", "PATTERN_MINUTE_SECOND", "currentTime", "", "getCurrentTime$annotations", "getCurrentTime", "()J", "formatedTime", "getFormatedTime$annotations", "getFormatedTime", "()Ljava/lang/String;", "pattern", "formatTime", "time", "minutesSinceLastBack", "", TtmlNode.ANNOTATION_POSITION_BEFORE, "formatTime2", "ms", "formatTime3", "formatTime4", "chatTime", "convertSeconds", "seconds", "weeOfToday", "getWeeOfToday", "isYesterday", "", "day1", "isToady", "millis", "formatRecentFileTime", "getTime", "second", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TimeUtils {
    public static final String DETAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final TimeUtils INSTANCE = new TimeUtils();
    public static final String PATTERN_MINUTE_SECOND = "HH:mm";

    @JvmStatic
    public static /* synthetic */ void getCurrentTime$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getFormatedTime$annotations() {
    }

    public final String formatTime(String str) {
        return formatTime$default(this, str, null, 2, null);
    }

    private TimeUtils() {
    }

    public static final long getCurrentTime() {
        return LocalDateTime.now().toDateTime().getMillis();
    }

    public static final String getFormatedTime() {
        return getFormatedTime(PATTERN_MINUTE_SECOND);
    }

    @JvmStatic
    public static final String getFormatedTime(String pattern) {
        if (pattern == null) {
            pattern = DETAULT_PATTERN;
        }
        String string = LocalDateTime.now().toDateTime().toString(pattern);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public static /* synthetic */ String formatTime$default(TimeUtils timeUtils, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = DETAULT_PATTERN;
        }
        return timeUtils.formatTime(str, str2);
    }

    public final String formatTime(String time, String pattern) {
        try {
            return formatTime(new SimpleDateFormat(pattern, Locale.CHINA).parse(time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    @JvmStatic
    public static final int minutesSinceLastBack(long before) {
        return Minutes.minutesBetween(new LocalDateTime(before).toDateTime(), LocalDateTime.now().toDateTime()).getMinutes();
    }

    public final String formatTime2(long ms) {
        LocalDateTime.now().toDateTime();
        String string = new LocalDateTime(ms).toDateTime().toString("yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final String formatTime3(long ms) {
        LocalDateTime.now().toDateTime();
        String string = new LocalDateTime(ms).toDateTime().toString("dd/MM/yyyy");
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @JvmStatic
    public static final String formatTime4(long ms) {
        LocalDateTime.now().toDateTime();
        String string = new LocalDateTime(ms).toDateTime().toString("MMM dd, yyyy");
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final String formatTime(long ms) {
        DateTime dateTime = LocalDateTime.now().toDateTime();
        DateTime dateTime2 = new LocalDateTime(ms).toDateTime();
        if (dateTime2.getMillis() > dateTime.getMillis()) {
            String string = dateTime2.toString(DETAULT_PATTERN);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
        long j = 1000;
        if (dateTime2.getMillis() / j == dateTime.getMillis() / j) {
            return "刚刚";
        }
        StringBuilder sb = new StringBuilder();
        DateTime dateTime3 = dateTime2;
        DateTime dateTime4 = dateTime;
        int seconds = Seconds.secondsBetween(dateTime3, dateTime4).getSeconds();
        int minutes = Minutes.minutesBetween(dateTime3, dateTime4).getMinutes();
        int hours = Hours.hoursBetween(dateTime3, dateTime4).getHours();
        int days = Days.daysBetween(dateTime3, dateTime4).getDays();
        if (seconds < 60) {
            sb.append(seconds);
            sb.append("秒前");
            String string2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            return string2;
        }
        if (minutes < 60) {
            sb.append(minutes);
            sb.append("分钟前");
            String string3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
            return string3;
        }
        if (hours < 24) {
            sb.append(hours);
            sb.append("小时前");
            String string4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
            return string4;
        }
        if (days <= 10) {
            sb.append(days);
            sb.append("天前");
            String string5 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
            return string5;
        }
        String string6 = dateTime2.toString(DETAULT_PATTERN);
        Intrinsics.checkNotNullExpressionValue(string6, "toString(...)");
        return string6;
    }

    public final String chatTime(long ms) {
        DateTime dateTime = LocalDateTime.now().toDateTime();
        DateTime dateTime2 = new LocalDateTime(ms).toDateTime();
        if (dateTime2.getMillis() > dateTime.getMillis()) {
            String string = dateTime2.toString(DETAULT_PATTERN);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
        long j = 1000;
        if (dateTime2.getMillis() / j == dateTime.getMillis() / j) {
            return "刚刚";
        }
        StringBuilder sb = new StringBuilder();
        DateTime dateTime3 = dateTime2;
        DateTime dateTime4 = dateTime;
        int seconds = Seconds.secondsBetween(dateTime3, dateTime4).getSeconds();
        int minutes = Minutes.minutesBetween(dateTime3, dateTime4).getMinutes();
        int hours = Hours.hoursBetween(dateTime3, dateTime4).getHours();
        int days = Days.daysBetween(dateTime3, dateTime4).getDays();
        if (seconds < 60) {
            sb.append(seconds);
            sb.append("秒前");
            String string2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            return string2;
        }
        if (minutes < 60) {
            sb.append(minutes);
            sb.append("分钟前");
            String string3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
            return string3;
        }
        if (hours < 24) {
            sb.append(hours);
            sb.append("小时前");
            String string4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
            return string4;
        }
        if (days <= 10) {
            sb.append(days);
            sb.append("天前");
            String string5 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
            return string5;
        }
        String string6 = dateTime2.toString("yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(string6, "toString(...)");
        return string6;
    }

    public final String convertSeconds(int seconds) {
        if (seconds <= 0) {
            return "00:00";
        }
        int i = seconds / 60;
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        String string = sb.toString();
        if (i < 10) {
            string = "0" + i;
        }
        int i2 = seconds % 60;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i2);
        String string2 = sb2.toString();
        if (i2 < 10) {
            string2 = "0" + i2;
        }
        return string + ":" + string2;
    }

    private final long getWeeOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private final boolean isYesterday(long day1) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day1);
        int i = calendar.get(6);
        calendar.setTimeInMillis(getWeeOfToday());
        int i2 = calendar.get(6);
        return i - i2 == 1 || i2 - i == 1;
    }

    private final boolean isToady(long millis) {
        long weeOfToday = getWeeOfToday();
        return millis >= weeOfToday && millis < weeOfToday + ((long) DateTimeConstants.MILLIS_PER_DAY);
    }

    public final String formatRecentFileTime(long millis) {
        if (isToady(millis)) {
            return "TODAY";
        }
        if (isYesterday(millis)) {
            return "YESTERDAY";
        }
        return formatTime2(millis);
    }

    public final String getTime(int second) {
        if (second < 10) {
            return "00:0" + second;
        }
        if (second < 60) {
            return "00:" + second;
        }
        if (second < 3600) {
            int i = second / 60;
            int i2 = second - (i * 60);
            if (i < 10) {
                if (i2 < 10) {
                    return "0" + i + ":0" + i2;
                }
                return "0" + i + ":" + i2;
            }
            if (i2 < 10) {
                return i + ":0" + i2;
            }
            return i + ":" + i2;
        }
        int i3 = second / DateTimeConstants.SECONDS_PER_HOUR;
        int i4 = second - (i3 * DateTimeConstants.SECONDS_PER_HOUR);
        int i5 = i4 / 60;
        int i6 = i4 - (i5 * 60);
        if (i3 < 10) {
            if (i5 < 10) {
                if (i6 < 10) {
                    return "0" + i3 + ":0" + i5 + ":0" + i6;
                }
                return "0" + i3 + ":0" + i5 + ":" + i6;
            }
            if (i6 < 10) {
                return "0" + i3 + ":" + i5 + ":0" + i6;
            }
            return "0" + i3 + ":" + i5 + ":" + i6;
        }
        if (i5 < 10) {
            if (i6 < 10) {
                return i3 + ":0" + i5 + ":0" + i6;
            }
            return i3 + ":0" + i5 + ":" + i6;
        }
        if (i6 < 10) {
            return i3 + ":" + i5 + ":0" + i6;
        }
        return i3 + ":" + i5 + ":" + i6;
    }
}
