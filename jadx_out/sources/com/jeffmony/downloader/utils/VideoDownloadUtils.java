package com.jeffmony.downloader.utils;

import android.text.TextUtils;
import com.jeffmony.downloader.VideoDownloadConfig;
import com.jeffmony.downloader.model.Video;
import java.io.Closeable;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public class VideoDownloadUtils {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final long DEFAULT_CONTENT_LENGTH = -1;
    public static final String INIT_SEGMENT_PREFIX = "init_video_";
    public static final String LOCAL_M3U8 = "local.m3u8";
    public static final String OUPUT_VIDEO = "merged.mp4";
    public static final String REMOTE_M3U8 = "remote.m3u8";
    public static final String SEGMENT_PREFIX = "video_";
    private static final String TAG = "VideoDownloadUtils";
    public static final String VIDEO_SUFFIX = ".video";
    private static VideoDownloadConfig mDownloadConfig;

    public static void setDownloadConfig(VideoDownloadConfig videoDownloadConfig) {
        mDownloadConfig = videoDownloadConfig;
    }

    public static VideoDownloadConfig getDownloadConfig() {
        return mDownloadConfig;
    }

    public static boolean isNameSupported(String str) {
        return str.endsWith(Video.SUFFIX.SUFFIX_MP4) || str.endsWith(Video.SUFFIX.SUFFIX_MOV) || str.endsWith(Video.SUFFIX.SUFFIX_WEBM) || str.endsWith(Video.SUFFIX.SUFFIX_3GP) || str.endsWith(Video.SUFFIX.SUFFIX_MKV);
    }

    public static String getVideoMime(String str) {
        if (str.endsWith(Video.SUFFIX.SUFFIX_MP4)) {
            return Video.TypeInfo.MP4;
        }
        if (str.endsWith(Video.SUFFIX.SUFFIX_MOV)) {
            return Video.TypeInfo.MOV;
        }
        if (str.endsWith(Video.SUFFIX.SUFFIX_WEBM)) {
            return Video.TypeInfo.WEBM;
        }
        if (str.endsWith(Video.SUFFIX.SUFFIX_3GP)) {
            return Video.TypeInfo.GP3;
        }
        if (str.endsWith(Video.SUFFIX.SUFFIX_MKV)) {
            return Video.TypeInfo.MKV;
        }
        return Video.TypeInfo.OTHER;
    }

    public static int getVideoType(String str) {
        if (str.endsWith(Video.SUFFIX.SUFFIX_MP4) || str.contains(Video.Mime.MIME_TYPE_MP4)) {
            return 3;
        }
        if (str.endsWith(Video.SUFFIX.SUFFIX_MOV) || str.contains(Video.Mime.MIME_TYPE_QUICKTIME)) {
            return 5;
        }
        if (str.endsWith(Video.SUFFIX.SUFFIX_WEBM) || str.contains(Video.Mime.MIME_TYPE_WEBM)) {
            return 4;
        }
        if (str.endsWith(Video.SUFFIX.SUFFIX_3GP) || str.contains(Video.Mime.MIME_TYPE_3GP)) {
            return 6;
        }
        return (str.endsWith(Video.SUFFIX.SUFFIX_MKV) || str.contains(Video.Mime.MIME_TYPE_MKV)) ? 7 : 0;
    }

    public static boolean isM3U8Mimetype(String str) {
        return !TextUtils.isEmpty(str) && (str.contains(Video.Mime.MIME_TYPE_M3U8_1) || str.contains(Video.Mime.MIME_TYPE_M3U8_2) || str.contains(Video.Mime.MIME_TYPE_M3U8_3) || str.contains(Video.Mime.MIME_TYPE_M3U8_4));
    }

    public static String computeMD5(String str) {
        try {
            return bytesToHexString(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static String bytesToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02x", Byte.valueOf(b)));
        }
        return stringBuffer.toString();
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(c);
        return unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeBlockOf == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || unicodeBlockOf == Character.UnicodeBlock.GENERAL_PUNCTUATION || unicodeBlockOf == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || unicodeBlockOf == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static boolean isMessyCode(String str) {
        char[] charArray = Pattern.compile("\\s*|t*|r*|n*").matcher(str).replaceAll("").replaceAll("\\p{P}", "").trim().toCharArray();
        float length = charArray.length;
        float f = 0.0f;
        for (char c : charArray) {
            if (!Character.isLetterOrDigit(c) && !isChinese(c)) {
                f += 1.0f;
            }
        }
        return length > 0.0f && ((double) (f / length)) > 0.4d;
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                LogUtils.w(TAG, "VideoProxyCacheUtils close " + closeable + " failed, exception = " + e);
            }
        }
    }

    public static String getPercent(float f) {
        return new DecimalFormat("###.00").format(f) + "%";
    }

    public static boolean isFloatEqual(float f, float f2) {
        return Math.abs(f - f2) < 0.01f;
    }

    public static String getSuffixName(String str) {
        int iLastIndexOf;
        return (!TextUtils.isEmpty(str) && (iLastIndexOf = str.lastIndexOf(46)) >= 0 && iLastIndexOf < str.length()) ? str.substring(iLastIndexOf) : "";
    }
}
