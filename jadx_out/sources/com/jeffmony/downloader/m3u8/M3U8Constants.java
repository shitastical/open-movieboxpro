package com.jeffmony.downloader.m3u8;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public class M3U8Constants {
    public static final String KEYFORMAT_IDENTITY = "identity";
    public static final String METHOD_AES_128 = "AES-128";
    public static final String METHOD_NONE = "NONE";
    public static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    public static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    public static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    public static final String PLAYLIST_HEADER = "#EXTM3U";
    public static final String TAG_ALLOW_CACHE = "EXT-X-ALLOW-CACHE";
    public static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    public static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    public static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    public static final String TAG_KEY = "#EXT-X-KEY";
    public static final String TAG_MEDIA_DURATION = "#EXTINF";
    public static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    public static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    public static final String TAG_PREFIX = "#EXT";
    public static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    public static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    public static final String TAG_VERSION = "#EXT-X-VERSION";
    public static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    public static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    public static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    public static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    public static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(,|$)");
    public static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    public static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    public static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    public static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
}
