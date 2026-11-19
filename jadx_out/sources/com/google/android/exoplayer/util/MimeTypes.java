package com.google.android.exoplayer.util;

/* JADX INFO: loaded from: classes4.dex */
public final class MimeTypes {
    public static final String APPLICATION_EIA608 = "application/eia-608";
    public static final String APPLICATION_ID3 = "application/id3";
    public static final String APPLICATION_M3U8 = "application/x-mpegURL";
    public static final String APPLICATION_MP4 = "application/mp4";
    public static final String APPLICATION_MP4VTT = "application/x-mp4vtt";
    public static final String APPLICATION_PGS = "application/pgs";
    public static final String APPLICATION_SUBRIP = "application/x-subrip";
    public static final String APPLICATION_TTML = "application/ttml+xml";
    public static final String APPLICATION_TX3G = "application/x-quicktime-tx3g";
    public static final String APPLICATION_VOBSUB = "application/vobsub";
    public static final String APPLICATION_WEBM = "application/webm";
    public static final String AUDIO_AAC = "audio/mp4a-latm";
    public static final String AUDIO_AC3 = "audio/ac3";
    public static final String AUDIO_AMR_NB = "audio/3gpp";
    public static final String AUDIO_AMR_WB = "audio/amr-wb";
    public static final String AUDIO_DTS = "audio/vnd.dts";
    public static final String AUDIO_DTS_EXPRESS = "audio/vnd.dts.hd;profile=lbr";
    public static final String AUDIO_DTS_HD = "audio/vnd.dts.hd";
    public static final String AUDIO_E_AC3 = "audio/eac3";
    public static final String AUDIO_FLAC = "audio/x-flac";
    public static final String AUDIO_MP4 = "audio/mp4";
    public static final String AUDIO_MPEG = "audio/mpeg";
    public static final String AUDIO_MPEG_L1 = "audio/mpeg-L1";
    public static final String AUDIO_MPEG_L2 = "audio/mpeg-L2";
    public static final String AUDIO_OPUS = "audio/opus";
    public static final String AUDIO_RAW = "audio/raw";
    public static final String AUDIO_TRUEHD = "audio/true-hd";
    public static final String AUDIO_UNKNOWN = "audio/x-unknown";
    public static final String AUDIO_VORBIS = "audio/vorbis";
    public static final String AUDIO_WEBM = "audio/webm";
    public static final String BASE_TYPE_APPLICATION = "application";
    public static final String BASE_TYPE_AUDIO = "audio";
    public static final String BASE_TYPE_TEXT = "text";
    public static final String BASE_TYPE_VIDEO = "video";
    public static final String TEXT_UNKNOWN = "text/x-unknown";
    public static final String TEXT_VTT = "text/vtt";
    public static final String VIDEO_H263 = "video/3gpp";
    public static final String VIDEO_H264 = "video/avc";
    public static final String VIDEO_H265 = "video/hevc";
    public static final String VIDEO_MP4 = "video/mp4";
    public static final String VIDEO_MP4V = "video/mp4v-es";
    public static final String VIDEO_MPEG2 = "video/mpeg2";
    public static final String VIDEO_UNKNOWN = "video/x-unknown";
    public static final String VIDEO_VC1 = "video/wvc1";
    public static final String VIDEO_VP8 = "video/x-vnd.on2.vp8";
    public static final String VIDEO_VP9 = "video/x-vnd.on2.vp9";
    public static final String VIDEO_WEBM = "video/webm";

    private MimeTypes() {
    }

    public static boolean isAudio(String str) {
        return getTopLevelType(str).equals("audio");
    }

    public static boolean isVideo(String str) {
        return getTopLevelType(str).equals("video");
    }

    public static boolean isText(String str) {
        return getTopLevelType(str).equals("text");
    }

    public static boolean isApplication(String str) {
        return getTopLevelType(str).equals("application");
    }

    private static String getTopLevelType(String str) {
        int iIndexOf = str.indexOf(47);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Invalid mime type: " + str);
        }
        return str.substring(0, iIndexOf);
    }

    public static String getVideoMediaMimeType(String str) {
        if (str == null) {
            return "video/x-unknown";
        }
        for (String str2 : str.split(",")) {
            String strTrim = str2.trim();
            if (strTrim.startsWith("avc1") || strTrim.startsWith("avc3")) {
                return "video/avc";
            }
            if (strTrim.startsWith("hev1") || strTrim.startsWith("hvc1")) {
                return "video/hevc";
            }
            if (strTrim.startsWith("vp9")) {
                return "video/x-vnd.on2.vp9";
            }
            if (strTrim.startsWith("vp8")) {
                return "video/x-vnd.on2.vp8";
            }
        }
        return "video/x-unknown";
    }

    public static String getAudioMediaMimeType(String str) {
        if (str == null) {
            return "audio/x-unknown";
        }
        for (String str2 : str.split(",")) {
            String strTrim = str2.trim();
            if (strTrim.startsWith("mp4a")) {
                return "audio/mp4a-latm";
            }
            if (strTrim.startsWith("ac-3") || strTrim.startsWith("dac3")) {
                return "audio/ac3";
            }
            if (strTrim.startsWith("ec-3") || strTrim.startsWith("dec3")) {
                return "audio/eac3";
            }
            if (strTrim.startsWith("dtsc")) {
                return "audio/vnd.dts";
            }
            if (strTrim.startsWith("dtsh") || strTrim.startsWith("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (strTrim.startsWith("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (strTrim.startsWith("opus")) {
                return "audio/opus";
            }
            if (strTrim.startsWith("vorbis")) {
                return "audio/vorbis";
            }
        }
        return "audio/x-unknown";
    }
}
