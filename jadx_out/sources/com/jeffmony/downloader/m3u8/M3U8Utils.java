package com.jeffmony.downloader.m3u8;

import android.text.TextUtils;
import com.jeffmony.downloader.utils.HttpUtils;
import com.jeffmony.downloader.utils.LogUtils;
import com.jeffmony.downloader.utils.VideoDownloadUtils;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: classes5.dex */
public class M3U8Utils {
    private static final String TAG = "M3U8Utils";

    public static M3U8 parseNetworkM3U8Info(String str, Map<String, String> map, int i) throws Throwable {
        M3U8 m3u8;
        int i2;
        String stringAttr;
        String str2 = str;
        BufferedReader bufferedReader = null;
        String optionalStringAttr = null;
        try {
            try {
                HttpURLConnection connection = HttpUtils.getConnection(str2, map, VideoDownloadUtils.getDownloadConfig().shouldIgnoreCertErrors());
                int responseCode = connection.getResponseCode();
                LogUtils.i(TAG, "parseNetworkM3U8Info responseCode=" + responseCode);
                if (responseCode != 503 || i >= 100) {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    try {
                        M3U8 m3u9 = new M3U8(str2);
                        String m3U8AbsoluteUrl = null;
                        String optionalStringAttr2 = null;
                        String str3 = null;
                        String optionalStringAttr3 = null;
                        int i3 = 0;
                        boolean z = false;
                        boolean z2 = false;
                        float f = 0.0f;
                        int i4 = 0;
                        boolean z3 = false;
                        int i5 = 0;
                        boolean z4 = false;
                        boolean z5 = false;
                        int i6 = 0;
                        while (true) {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                M3U8 m3u10 = m3u9;
                                m3u10.setTargetDuration(i3);
                                m3u10.setVersion(i6);
                                m3u10.setSequence(i4);
                                m3u10.setHasEndList(z2);
                                VideoDownloadUtils.close(bufferedReader2);
                                return m3u10;
                            }
                            z2 = z2;
                            String strTrim = line.trim();
                            if (TextUtils.isEmpty(strTrim)) {
                                m3u8 = m3u9;
                                i2 = i3;
                            } else {
                                i2 = i3;
                                StringBuilder sb = new StringBuilder();
                                m3u8 = m3u9;
                                sb.append("line = ");
                                sb.append(strTrim);
                                LogUtils.i(TAG, sb.toString());
                                if (strTrim.startsWith(M3U8Constants.TAG_PREFIX)) {
                                    if (strTrim.startsWith(M3U8Constants.TAG_MEDIA_DURATION)) {
                                        String stringAttr2 = parseStringAttr(strTrim, M3U8Constants.REGEX_MEDIA_DURATION);
                                        if (!TextUtils.isEmpty(stringAttr2)) {
                                            f = Float.parseFloat(stringAttr2);
                                        }
                                    } else if (strTrim.startsWith(M3U8Constants.TAG_TARGET_DURATION)) {
                                        String stringAttr3 = parseStringAttr(strTrim, M3U8Constants.REGEX_TARGET_DURATION);
                                        i3 = !TextUtils.isEmpty(stringAttr3) ? Integer.parseInt(stringAttr3) : i2;
                                        m3u9 = m3u8;
                                    } else if (strTrim.startsWith(M3U8Constants.TAG_VERSION)) {
                                        String stringAttr4 = parseStringAttr(strTrim, M3U8Constants.REGEX_VERSION);
                                        if (!TextUtils.isEmpty(stringAttr4)) {
                                            i6 = Integer.parseInt(stringAttr4);
                                        }
                                    } else if (strTrim.startsWith(M3U8Constants.TAG_MEDIA_SEQUENCE)) {
                                        String stringAttr5 = parseStringAttr(strTrim, M3U8Constants.REGEX_MEDIA_SEQUENCE);
                                        if (!TextUtils.isEmpty(stringAttr5)) {
                                            i4 = Integer.parseInt(stringAttr5);
                                        }
                                    } else if (strTrim.startsWith(M3U8Constants.TAG_STREAM_INF)) {
                                        z2 = z2;
                                        i3 = i2;
                                        m3u9 = m3u8;
                                        z = true;
                                    } else if (strTrim.startsWith(M3U8Constants.TAG_DISCONTINUITY)) {
                                        z2 = z2;
                                        i3 = i2;
                                        m3u9 = m3u8;
                                        z3 = true;
                                    } else if (strTrim.startsWith(M3U8Constants.TAG_ENDLIST)) {
                                        i3 = i2;
                                        m3u9 = m3u8;
                                        z2 = true;
                                    } else if (strTrim.startsWith(M3U8Constants.TAG_KEY)) {
                                        optionalStringAttr = parseOptionalStringAttr(strTrim, M3U8Constants.REGEX_METHOD);
                                        String optionalStringAttr4 = parseOptionalStringAttr(strTrim, M3U8Constants.REGEX_KEYFORMAT);
                                        if (!"NONE".equals(optionalStringAttr)) {
                                            optionalStringAttr2 = parseOptionalStringAttr(strTrim, M3U8Constants.REGEX_IV);
                                            if ((M3U8Constants.KEYFORMAT_IDENTITY.equals(optionalStringAttr4) || optionalStringAttr4 == null) && "AES-128".equals(optionalStringAttr) && (stringAttr = parseStringAttr(strTrim, M3U8Constants.REGEX_URI)) != null) {
                                                m3U8AbsoluteUrl = getM3U8AbsoluteUrl(str2, stringAttr);
                                            }
                                        }
                                        z2 = z2;
                                        i3 = i2;
                                        m3u9 = m3u8;
                                        z4 = true;
                                    } else if (strTrim.startsWith(M3U8Constants.TAG_INIT_SEGMENT)) {
                                        String stringAttr6 = parseStringAttr(strTrim, M3U8Constants.REGEX_URI);
                                        if (!TextUtils.isEmpty(stringAttr6)) {
                                            String m3U8AbsoluteUrl2 = getM3U8AbsoluteUrl(str2, stringAttr6);
                                            optionalStringAttr3 = parseOptionalStringAttr(strTrim, M3U8Constants.REGEX_ATTR_BYTERANGE);
                                            z5 = true;
                                            str3 = m3U8AbsoluteUrl2;
                                        }
                                    }
                                } else {
                                    if (z) {
                                        M3U8 networkM3U8Info = parseNetworkM3U8Info(getM3U8AbsoluteUrl(str2, strTrim), map, i);
                                        VideoDownloadUtils.close(bufferedReader2);
                                        return networkM3U8Info;
                                    }
                                    if (Math.abs(f) >= 0.001f) {
                                        M3U8Seg m3U8Seg = new M3U8Seg();
                                        int i7 = i4 + 1;
                                        m3U8Seg.initTsAttributes(getM3U8AbsoluteUrl(str2, strTrim), f, i5, i4, z3);
                                        if (z4) {
                                            m3U8Seg.setKeyConfig(optionalStringAttr, m3U8AbsoluteUrl, optionalStringAttr2);
                                        }
                                        if (z5) {
                                            m3U8Seg.setInitSegmentInfo(str3, optionalStringAttr3);
                                        }
                                        m3u8.addTs(m3U8Seg);
                                        i5++;
                                        z2 = z2;
                                        m3u9 = m3u8;
                                        i4 = i7;
                                        i3 = i2;
                                        optionalStringAttr = null;
                                        m3U8AbsoluteUrl = null;
                                        optionalStringAttr2 = null;
                                        str3 = null;
                                        optionalStringAttr3 = null;
                                        z = false;
                                        f = 0.0f;
                                        z3 = false;
                                        z4 = false;
                                        z5 = false;
                                        str2 = str;
                                    }
                                }
                            }
                            i3 = i2;
                            m3u9 = m3u8;
                        }
                    } catch (IOException e) {
                        e = e;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        VideoDownloadUtils.close(bufferedReader);
                        throw th;
                    }
                } else {
                    try {
                        M3U8 networkM3U8Info2 = parseNetworkM3U8Info(str2, map, i + 1);
                        VideoDownloadUtils.close(null);
                        return networkM3U8Info2;
                    } catch (IOException e2) {
                        e = e2;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
        e.printStackTrace();
        throw e;
    }

    public static M3U8 parseLocalM3U8File(File file) throws Throwable {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader2;
        String line;
        BufferedReader bufferedReader2;
        String str;
        String str2;
        String str3;
        try {
            InputStreamReader inputStreamReader3 = new InputStreamReader(new FileInputStream(file));
            try {
                bufferedReader = new BufferedReader(inputStreamReader3);
                try {
                    M3U8 m3u8 = new M3U8();
                    String optionalStringAttr = null;
                    String stringAttr = null;
                    int i = 0;
                    int i2 = 0;
                    String str4 = null;
                    String stringAttr2 = null;
                    float f = 0.0f;
                    String optionalStringAttr2 = null;
                    int i3 = 0;
                    boolean z = false;
                    int i4 = 0;
                    while (true) {
                        boolean z2 = false;
                        boolean z3 = false;
                        while (true) {
                            line = bufferedReader.readLine();
                            if (line != null) {
                                str = str4;
                                str2 = stringAttr2;
                                StringBuilder sb = new StringBuilder();
                                str3 = optionalStringAttr2;
                                sb.append("line = ");
                                sb.append(line);
                                LogUtils.i(TAG, sb.toString());
                                if (line.startsWith(M3U8Constants.TAG_PREFIX)) {
                                    try {
                                        if (line.startsWith(M3U8Constants.TAG_MEDIA_DURATION)) {
                                            String stringAttr3 = parseStringAttr(line, M3U8Constants.REGEX_MEDIA_DURATION);
                                            if (!TextUtils.isEmpty(stringAttr3)) {
                                                f = Float.parseFloat(stringAttr3);
                                            }
                                        } else if (line.startsWith(M3U8Constants.TAG_TARGET_DURATION)) {
                                            String stringAttr4 = parseStringAttr(line, M3U8Constants.REGEX_TARGET_DURATION);
                                            if (!TextUtils.isEmpty(stringAttr4)) {
                                                i = Integer.parseInt(stringAttr4);
                                            }
                                        } else if (line.startsWith(M3U8Constants.TAG_VERSION)) {
                                            String stringAttr5 = parseStringAttr(line, M3U8Constants.REGEX_VERSION);
                                            if (!TextUtils.isEmpty(stringAttr5)) {
                                                i2 = Integer.parseInt(stringAttr5);
                                            }
                                        } else if (line.startsWith(M3U8Constants.TAG_MEDIA_SEQUENCE)) {
                                            String stringAttr6 = parseStringAttr(line, M3U8Constants.REGEX_MEDIA_SEQUENCE);
                                            if (!TextUtils.isEmpty(stringAttr6)) {
                                                i3 = Integer.parseInt(stringAttr6);
                                            }
                                        } else if (line.startsWith(M3U8Constants.TAG_DISCONTINUITY)) {
                                            str4 = str;
                                            stringAttr2 = str2;
                                            optionalStringAttr2 = str3;
                                            z = true;
                                        } else if (line.startsWith(M3U8Constants.TAG_KEY)) {
                                            optionalStringAttr2 = parseOptionalStringAttr(line, M3U8Constants.REGEX_METHOD);
                                            String optionalStringAttr3 = parseOptionalStringAttr(line, M3U8Constants.REGEX_KEYFORMAT);
                                            if ("NONE".equals(optionalStringAttr2)) {
                                                str4 = str;
                                            } else {
                                                String optionalStringAttr4 = parseOptionalStringAttr(line, M3U8Constants.REGEX_IV);
                                                if ((M3U8Constants.KEYFORMAT_IDENTITY.equals(optionalStringAttr3) || optionalStringAttr3 == null) && "AES-128".equals(optionalStringAttr2)) {
                                                    stringAttr2 = parseStringAttr(line, M3U8Constants.REGEX_URI);
                                                    str4 = optionalStringAttr4;
                                                } else {
                                                    str4 = optionalStringAttr4;
                                                }
                                                z2 = true;
                                            }
                                            stringAttr2 = str2;
                                            z2 = true;
                                        } else if (line.startsWith(M3U8Constants.TAG_INIT_SEGMENT)) {
                                            stringAttr = parseStringAttr(line, M3U8Constants.REGEX_URI);
                                            if (!TextUtils.isEmpty(stringAttr)) {
                                                optionalStringAttr = parseOptionalStringAttr(line, M3U8Constants.REGEX_ATTR_BYTERANGE);
                                                str4 = str;
                                                stringAttr2 = str2;
                                                optionalStringAttr2 = str3;
                                                z3 = true;
                                            }
                                        }
                                        str4 = str;
                                        stringAttr2 = str2;
                                        optionalStringAttr2 = str3;
                                    } catch (IOException e) {
                                        e = e;
                                        inputStreamReader = inputStreamReader3;
                                    } catch (Throwable th) {
                                        th = th;
                                        inputStreamReader = inputStreamReader3;
                                        VideoDownloadUtils.close(inputStreamReader);
                                        VideoDownloadUtils.close(bufferedReader);
                                        throw th;
                                    }
                                }
                            } else {
                                inputStreamReader2 = inputStreamReader3;
                                bufferedReader2 = bufferedReader;
                                m3u8.setTargetDuration(i);
                                m3u8.setVersion(i2);
                                m3u8.setSequence(i3);
                                VideoDownloadUtils.close(inputStreamReader2);
                                VideoDownloadUtils.close(bufferedReader2);
                                return m3u8;
                            }
                            inputStreamReader = inputStreamReader2;
                            try {
                                e.printStackTrace();
                                throw e;
                            } catch (Throwable th2) {
                                th = th2;
                                VideoDownloadUtils.close(inputStreamReader);
                                VideoDownloadUtils.close(bufferedReader);
                                throw th;
                            }
                        }
                        M3U8Seg m3U8Seg = new M3U8Seg();
                        int i5 = i3 + 1;
                        bufferedReader2 = bufferedReader;
                        inputStreamReader2 = inputStreamReader3;
                        int i6 = i2;
                        try {
                            m3U8Seg.initTsAttributes(line, f, i4, i3, z);
                            if (z2) {
                                m3U8Seg.setKeyConfig(str3, str2, str);
                            }
                            if (z3) {
                                m3U8Seg.setInitSegmentInfo(stringAttr, optionalStringAttr);
                            }
                            m3u8.addTs(m3U8Seg);
                            i4++;
                            bufferedReader = bufferedReader2;
                            inputStreamReader3 = inputStreamReader2;
                            i2 = i6;
                            i3 = i5;
                            optionalStringAttr = null;
                            stringAttr = null;
                            str4 = null;
                            stringAttr2 = null;
                            f = 0.0f;
                            optionalStringAttr2 = null;
                            z = false;
                        } catch (IOException e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedReader = bufferedReader2;
                            inputStreamReader = inputStreamReader2;
                            VideoDownloadUtils.close(inputStreamReader);
                            VideoDownloadUtils.close(bufferedReader);
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    inputStreamReader2 = inputStreamReader3;
                } catch (Throwable th4) {
                    th = th4;
                    inputStreamReader2 = inputStreamReader3;
                }
            } catch (IOException e4) {
                e = e4;
                inputStreamReader = inputStreamReader3;
                bufferedReader = null;
            } catch (Throwable th5) {
                th = th5;
                inputStreamReader = inputStreamReader3;
                bufferedReader = null;
                VideoDownloadUtils.close(inputStreamReader);
                VideoDownloadUtils.close(bufferedReader);
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            inputStreamReader = null;
        } catch (Throwable th6) {
            th = th6;
            inputStreamReader = null;
        }
    }

    public static String parseStringAttr(String str, Pattern pattern) {
        if (pattern == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        return null;
    }

    public static String parseOptionalStringAttr(String str, Pattern pattern) {
        if (pattern == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static void createRemoteM3U8(File file, M3U8 m3u8) throws IOException {
        File file2 = new File(file, VideoDownloadUtils.REMOTE_M3U8);
        if (file2.exists()) {
            return;
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2, false));
        bufferedWriter.write("#EXTM3U\n");
        bufferedWriter.write("#EXT-X-VERSION:" + m3u8.getVersion() + ShellUtil.COMMAND_LINE_END);
        bufferedWriter.write("#EXT-X-MEDIA-SEQUENCE:" + m3u8.getInitSequence() + ShellUtil.COMMAND_LINE_END);
        bufferedWriter.write("#EXT-X-TARGETDURATION:" + m3u8.getTargetDuration() + ShellUtil.COMMAND_LINE_END);
        for (M3U8Seg m3U8Seg : m3u8.getTsList()) {
            if (m3U8Seg.hasInitSegment()) {
                bufferedWriter.write("#EXT-X-MAP:" + (m3U8Seg.getSegmentByteRange() != null ? "URI=\"" + m3U8Seg.getInitSegmentUri() + "\",BYTERANGE=\"" + m3U8Seg.getSegmentByteRange() + "\"" : "URI=\"" + m3U8Seg.getInitSegmentUri() + "\"") + ShellUtil.COMMAND_LINE_END);
            }
            if (m3U8Seg.hasKey() && m3U8Seg.getMethod() != null) {
                String str = "METHOD=" + m3U8Seg.getMethod();
                if (m3U8Seg.getKeyUri() != null) {
                    String keyUri = m3U8Seg.getKeyUri();
                    str = str + ",URI=\"" + keyUri + "\"";
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(keyUri).openStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            sb.append(line);
                        }
                    }
                    m3U8Seg.setIsMessyKey(VideoDownloadUtils.isMessyCode(sb.toString()));
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(file, m3U8Seg.getLocalKeyUri()));
                    fileOutputStream.write(sb.toString().getBytes());
                    bufferedReader.close();
                    fileOutputStream.close();
                    if (m3U8Seg.getKeyIV() != null) {
                        str = str + ",IV=" + m3U8Seg.getKeyIV();
                    }
                }
                bufferedWriter.write("#EXT-X-KEY:" + str + ShellUtil.COMMAND_LINE_END);
            }
            if (m3U8Seg.hasDiscontinuity()) {
                bufferedWriter.write("#EXT-X-DISCONTINUITY\n");
            }
            bufferedWriter.write("#EXTINF:" + m3U8Seg.getDuration() + ",\n");
            bufferedWriter.write(m3U8Seg.getUrl());
            bufferedWriter.newLine();
        }
        bufferedWriter.write(M3U8Constants.TAG_ENDLIST);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static String getM3U8AbsoluteUrl(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        if (str.startsWith("file://") || str.startsWith("/")) {
            return str;
        }
        String baseUrl = getBaseUrl(str);
        String hostUrl = getHostUrl(str);
        if (str2.startsWith("//")) {
            return getSchema(str) + ":" + str2;
        }
        if (str2.startsWith("/")) {
            String longestCommonPrefixStr = getLongestCommonPrefixStr(getPathStr(str), str2);
            if (hostUrl.endsWith("/")) {
                hostUrl = hostUrl.substring(0, hostUrl.length() - 1);
            }
            return hostUrl + longestCommonPrefixStr + str2.substring(longestCommonPrefixStr.length());
        }
        if (str2.startsWith(IDataSource.SCHEME_HTTP_TAG)) {
            return str2;
        }
        return baseUrl + str2;
    }

    private static String getSchema(String str) {
        int iIndexOf;
        return (TextUtils.isEmpty(str) || (iIndexOf = str.indexOf("://")) == -1) ? "" : str.substring(0, iIndexOf);
    }

    public static String getBaseUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf("/");
        return iLastIndexOf != -1 ? str.substring(0, iLastIndexOf + 1) : str;
    }

    public static String getHostUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            URL url = new URL(str);
            String host = url.getHost();
            if (host == null) {
                return str;
            }
            int iIndexOf = str.indexOf(host);
            if (iIndexOf != -1) {
                int port = url.getPort();
                if (port != -1) {
                    str = str.substring(0, iIndexOf + host.length()) + ":" + port + "/";
                } else {
                    str = str.substring(0, iIndexOf + host.length()) + "/";
                }
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public static String getPathStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String hostUrl = getHostUrl(str);
        return TextUtils.isEmpty(hostUrl) ? str : str.substring(hostUrl.length() - 1);
    }

    public static String getLongestCommonPrefixStr(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        if (TextUtils.equals(str, str2)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int i = 0;
        while (i < charArray.length && i < charArray2.length && charArray[i] == charArray2[i]) {
            i++;
        }
        return str.substring(0, i);
    }
}
