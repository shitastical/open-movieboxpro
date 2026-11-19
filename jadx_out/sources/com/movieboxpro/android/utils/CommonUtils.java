package com.movieboxpro.android.utils;

import android.content.Context;
import android.text.TextUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import ly.count.android.sdk.Countly;

/* JADX INFO: loaded from: classes5.dex */
public class CommonUtils {
    public static void onEvent(String event) {
        Countly.sharedInstance().events().recordEvent(event);
    }

    public static float calPercent(int diliverNum, int queryMailNum) {
        return new BigDecimal(diliverNum).divide(new BigDecimal(queryMailNum), 2, RoundingMode.HALF_UP).floatValue();
    }

    public static Boolean isInternalDownloadPath(String path) {
        if (path == null || path.contains(BuildConfig.APPLICATION_ID)) {
            return true;
        }
        return false;
    }

    public static boolean isFFmpegDownload(String path) {
        if (new File(path).getParentFile() != null) {
            return path.contains("ffmpegDownload");
        }
        return false;
    }

    public static String urlEncode(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String divide(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return new BigDecimal(v1).divide(new BigDecimal(v2), scale, 4).toString();
    }

    public static boolean isChildModeVideo(String contentRating) {
        return TextUtils.isEmpty(contentRating) || "R".equalsIgnoreCase(contentRating) || "NC-17".equalsIgnoreCase(contentRating) || "TV-MA".equalsIgnoreCase(contentRating);
    }

    public static int getTomatoScore(int score) {
        if (score < 60) {
            return R.mipmap.ic_tomato_score_low;
        }
        return R.mipmap.ic_tomato_score_high;
    }

    public static int getTomatoImg(int tomato) {
        if (tomato == 0) {
            return R.mipmap.ic_tomato;
        }
        if (tomato < 60) {
            return R.mipmap.ic_tomato_low;
        }
        if (tomato < 75) {
            return R.mipmap.ic_tomato;
        }
        return R.mipmap.ic_tomato_high;
    }

    public static int getMovieTag(String quaTag) {
        if (TextUtils.isEmpty(quaTag)) {
            return R.drawable.ic_blu_ray;
        }
        quaTag.hashCode();
        switch (quaTag) {
            case "blu-ray":
                return R.drawable.ic_blu_ray;
            case "3d":
                return R.drawable.ic_3d;
            case "4k":
                return R.drawable.ic_4k;
            case "tc":
                return R.drawable.ic_tc;
            case "dvd":
                return R.drawable.ic_dvd;
            case "hdtv":
                return R.drawable.ic_hdtv;
            case "webdl":
                return R.drawable.ic_webdl;
            case "4k_hdr":
                return R.mipmap.ic_4k_hdr_tag;
            case "8k_hdr":
                return R.mipmap.ic_8k_hdr_tag;
            default:
                return R.drawable.ic_blu_ray;
        }
    }

    public static String multiply(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return new BigDecimal(v1).multiply(new BigDecimal(v2)).setScale(scale, 4).toString();
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static String replace(String value, String old, String newValue) {
        return value.replace(old, newValue);
    }

    public static boolean isScreenLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line + ShellUtil.COMMAND_LINE_END);
                    } catch (Throwable th) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    is.close();
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        is.close();
        return sb.toString();
    }
}
