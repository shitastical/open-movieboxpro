package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.hls.HlsTrackMetadataEntry;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import com.google.common.collect.Iterables;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: loaded from: classes.dex */
public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final String ATTR_CLOSED_CAPTIONS_NONE = "CLOSED-CAPTIONS=NONE";
    private static final String ATTR_QUOTED_STRING_VALUE_PATTERN = "\"((?:.|\f)+?)\"";
    private static final String BOOLEAN_FALSE = "NO";
    private static final String BOOLEAN_TRUE = "YES";
    private static final String DATERANGE_CLASS_INTERSTITIALS = "com.apple.hls.interstitial";
    private static final String KEYFORMAT_IDENTITY = "identity";
    private static final String KEYFORMAT_PLAYREADY = "com.microsoft.playready";
    private static final String KEYFORMAT_WIDEVINE_PSSH_BINARY = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";
    private static final String KEYFORMAT_WIDEVINE_PSSH_JSON = "com.widevine";
    private static final String LOG_TAG = "HlsPlaylistParser";
    private static final String METHOD_AES_128 = "AES-128";
    private static final String METHOD_NONE = "NONE";
    private static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    private static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    private static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    private static final String PLAYLIST_HEADER = "#EXTM3U";
    private static final String TAG_BYTERANGE = "#EXT-X-BYTERANGE";
    private static final String TAG_DATERANGE = "#EXT-X-DATERANGE";
    private static final String TAG_DEFINE = "#EXT-X-DEFINE";
    private static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    private static final String TAG_DISCONTINUITY_SEQUENCE = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    private static final String TAG_GAP = "#EXT-X-GAP";
    private static final String TAG_IFRAME = "#EXT-X-I-FRAMES-ONLY";
    private static final String TAG_INDEPENDENT_SEGMENTS = "#EXT-X-INDEPENDENT-SEGMENTS";
    private static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    private static final String TAG_I_FRAME_STREAM_INF = "#EXT-X-I-FRAME-STREAM-INF";
    private static final String TAG_KEY = "#EXT-X-KEY";
    private static final String TAG_MEDIA = "#EXT-X-MEDIA";
    private static final String TAG_MEDIA_DURATION = "#EXTINF";
    private static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    private static final String TAG_PART = "#EXT-X-PART";
    private static final String TAG_PART_INF = "#EXT-X-PART-INF";
    private static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    private static final String TAG_PREFIX = "#EXT";
    private static final String TAG_PRELOAD_HINT = "#EXT-X-PRELOAD-HINT";
    private static final String TAG_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    private static final String TAG_RENDITION_REPORT = "#EXT-X-RENDITION-REPORT";
    private static final String TAG_SERVER_CONTROL = "#EXT-X-SERVER-CONTROL";
    private static final String TAG_SESSION_KEY = "#EXT-X-SESSION-KEY";
    private static final String TAG_SKIP = "#EXT-X-SKIP";
    private static final String TAG_START = "#EXT-X-START";
    private static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    private static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String TAG_VERSION = "#EXT-X-VERSION";
    private static final String TYPE_AUDIO = "AUDIO";
    private static final String TYPE_CLOSED_CAPTIONS = "CLOSED-CAPTIONS";
    private static final String TYPE_MAP = "MAP";
    private static final String TYPE_PART = "PART";
    private static final String TYPE_SUBTITLES = "SUBTITLES";
    private static final String TYPE_VIDEO = "VIDEO";
    private final HlsMultivariantPlaylist multivariantPlaylist;
    private final HlsMediaPlaylist previousMediaPlaylist;
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_VIDEO = Pattern.compile("VIDEO=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_AUDIO = Pattern.compile("AUDIO=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_SUBTITLES = Pattern.compile("SUBTITLES=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_CLOSED_CAPTIONS = Pattern.compile("CLOSED-CAPTIONS=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_CHANNELS = Pattern.compile("CHANNELS=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_VIDEO_RANGE = Pattern.compile("VIDEO-RANGE=(SDR|PQ|HLG)");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_SUPPLEMENTAL_CODECS = Pattern.compile("SUPPLEMENTAL-CODECS=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_ATTR_DURATION = Pattern.compile("DURATION=([\\d\\.]+)\\b");
    private static final Pattern REGEX_ATTR_DURATION_PREFIXED = Pattern.compile("[:,]DURATION=([\\d\\.]+)\\b");
    private static final Pattern REGEX_PART_TARGET_DURATION = Pattern.compile("PART-TARGET=([\\d\\.]+)\\b");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_CAN_SKIP_UNTIL = Pattern.compile("CAN-SKIP-UNTIL=([\\d\\.]+)\\b");
    private static final Pattern REGEX_CAN_SKIP_DATE_RANGES = compileBooleanAttrPattern("CAN-SKIP-DATERANGES");
    private static final Pattern REGEX_SKIPPED_SEGMENTS = Pattern.compile("SKIPPED-SEGMENTS=(\\d+)\\b");
    private static final Pattern REGEX_HOLD_BACK = Pattern.compile("[:|,]HOLD-BACK=([\\d\\.]+)\\b");
    private static final Pattern REGEX_PART_HOLD_BACK = Pattern.compile("PART-HOLD-BACK=([\\d\\.]+)\\b");
    private static final Pattern REGEX_CAN_BLOCK_RELOAD = compileBooleanAttrPattern("CAN-BLOCK-RELOAD");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_MEDIA_TITLE = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern REGEX_LAST_MSN = Pattern.compile("LAST-MSN=(\\d+)\\b");
    private static final Pattern REGEX_LAST_PART = Pattern.compile("LAST-PART=(\\d+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_BYTERANGE_START = Pattern.compile("BYTERANGE-START=(\\d+)\\b");
    private static final Pattern REGEX_BYTERANGE_LENGTH = Pattern.compile("BYTERANGE-LENGTH=(\\d+)\\b");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_KEYFORMATVERSIONS = Pattern.compile("KEYFORMATVERSIONS=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_PRELOAD_HINT_TYPE = Pattern.compile("TYPE=(PART|MAP)");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_CHARACTERISTICS = Pattern.compile("CHARACTERISTICS=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern("DEFAULT");
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern("FORCED");
    private static final Pattern REGEX_INDEPENDENT = compileBooleanAttrPattern("INDEPENDENT");
    private static final Pattern REGEX_GAP = compileBooleanAttrPattern("GAP");
    private static final Pattern REGEX_PRECISE = compileBooleanAttrPattern("PRECISE");
    private static final Pattern REGEX_VALUE = Pattern.compile("VALUE=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_IMPORT = Pattern.compile("IMPORT=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_ID = Pattern.compile("[:,]ID=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_CLASS = Pattern.compile("CLASS=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_START_DATE = Pattern.compile("START-DATE=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_CUE = Pattern.compile("CUE=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_END_DATE = Pattern.compile("END-DATE=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_PLANNED_DURATION = Pattern.compile("PLANNED-DURATION=([\\d\\.]+)\\b");
    private static final Pattern REGEX_END_ON_NEXT = compileBooleanAttrPattern("END-ON-NEXT");
    private static final Pattern REGEX_ASSET_URI = Pattern.compile("X-ASSET-URI=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_ASSET_LIST_URI = Pattern.compile("X-ASSET-LIST=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_RESUME_OFFSET = Pattern.compile("X-RESUME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_PLAYOUT_LIMIT = Pattern.compile("X-PLAYOUT-LIMIT=([\\d\\.]+)\\b");
    private static final Pattern REGEX_SNAP = Pattern.compile("X-SNAP=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_RESTRICT = Pattern.compile("X-RESTRICT=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_CONTENT_MAY_VARY = Pattern.compile("X-CONTENT-MAY-VARY=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_TIMELINE_OCCUPIES = Pattern.compile("X-TIMELINE-OCCUPIES=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_TIMELINE_STYLE = Pattern.compile("X-TIMELINE-STYLE=\"((?:.|\f)+?)\"");
    private static final Pattern REGEX_VARIABLE_REFERENCE = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
    private static final Pattern REGEX_CLIENT_DEFINED_ATTRIBUTE_PREFIX = Pattern.compile("\\b(X-[A-Z0-9-]+)=");

    public static final class DeltaUpdateException extends IOException {
    }

    public HlsPlaylistParser() {
        this(HlsMultivariantPlaylist.EMPTY, null);
    }

    public HlsPlaylistParser(HlsMultivariantPlaylist hlsMultivariantPlaylist, HlsMediaPlaylist hlsMediaPlaylist) {
        this.multivariantPlaylist = hlsMultivariantPlaylist;
        this.previousMediaPlaylist = hlsMediaPlaylist;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (!checkPlaylistHeader(bufferedReader)) {
                throw ParserException.createForMalformedManifest("Input does not start with the #EXTM3U header.", null);
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    String strTrim = line.trim();
                    if (!strTrim.isEmpty()) {
                        if (strTrim.startsWith("#EXT-X-STREAM-INF")) {
                            arrayDeque.add(strTrim);
                            HlsMultivariantPlaylist multivariantPlaylist = parseMultivariantPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                            Util.closeQuietly(bufferedReader);
                            return multivariantPlaylist;
                        }
                        if (!strTrim.startsWith("#EXT-X-TARGETDURATION") && !strTrim.startsWith("#EXT-X-MEDIA-SEQUENCE") && !strTrim.startsWith("#EXTINF") && !strTrim.startsWith("#EXT-X-KEY") && !strTrim.startsWith(TAG_BYTERANGE) && !strTrim.equals("#EXT-X-DISCONTINUITY") && !strTrim.equals(TAG_DISCONTINUITY_SEQUENCE) && !strTrim.equals("#EXT-X-ENDLIST")) {
                            arrayDeque.add(strTrim);
                        }
                        arrayDeque.add(strTrim);
                        HlsMediaPlaylist mediaPlaylist = parseMediaPlaylist(this.multivariantPlaylist, this.previousMediaPlaylist, new LineIterator(arrayDeque, bufferedReader), uri.toString());
                        Util.closeQuietly(bufferedReader);
                        return mediaPlaylist;
                    }
                } else {
                    Util.closeQuietly(bufferedReader);
                    throw ParserException.createForMalformedManifest("Failed to parse the playlist, could not identify any tags.", null);
                }
            }
        } catch (Throwable th) {
            Util.closeQuietly(bufferedReader);
            throw th;
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader bufferedReader) throws IOException {
        int i = bufferedReader.read();
        if (i == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            i = bufferedReader.read();
        }
        int iSkipIgnorableWhitespace = skipIgnorableWhitespace(bufferedReader, true, i);
        for (int i2 = 0; i2 < 7; i2++) {
            if (iSkipIgnorableWhitespace != "#EXTM3U".charAt(i2)) {
                return false;
            }
            iSkipIgnorableWhitespace = bufferedReader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(bufferedReader, false, iSkipIgnorableWhitespace));
    }

    private static int skipIgnorableWhitespace(BufferedReader bufferedReader, boolean z, int i) throws IOException {
        while (i != -1 && Character.isWhitespace(i) && (z || !Util.isLinebreak(i))) {
            i = bufferedReader.read();
        }
        return i;
    }

    private static boolean isDolbyVisionFormat(String str, String str2, String str3, String str4) {
        if (!MimeTypes.isDolbyVisionCodec(str2, str3)) {
            return false;
        }
        if (str3 == null) {
            return true;
        }
        if (str == null || str4 == null) {
            return false;
        }
        return (!str.equals("PQ") || str4.equals("db1p")) && (!str.equals("SDR") || str4.equals("db2g")) && (!str.equals("HLG") || str4.startsWith("db4"));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:166:0x0267 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:41:0x014f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:43:0x0152  */
    /* JADX WARN: Code duplicated, block: B:46:0x0159  */
    /* JADX WARN: Code duplicated, block: B:47:0x016d  */
    /* JADX WARN: Code duplicated, block: B:50:0x0176  */
    /* JADX WARN: Code duplicated, block: B:55:0x0193  */
    /* JADX WARN: Code duplicated, block: B:57:0x019a  */
    /* JADX WARN: Code duplicated, block: B:60:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:61:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:64:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:65:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:67:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:70:0x0233  */
    /* JADX WARN: Code duplicated, block: B:96:0x037b  */
    /* JADX WARN: Instruction removed from duplicated block: B:46:0x0159, please report this as an issue */
    private static HlsMultivariantPlaylist parseMultivariantPlaylist(LineIterator lineIterator, String str) throws IOException {
        ArrayList arrayList;
        String mediaMimeType;
        ArrayList arrayList2;
        int i;
        String str2;
        String mediaMimeType2;
        ArrayList arrayList3;
        String str3;
        String str4;
        String codecsOfType;
        String optionalStringAttr;
        int i2;
        int i3;
        String optionalStringAttr2;
        float f;
        Uri uriResolveToUri;
        ArrayList arrayList4;
        int i4;
        int i5;
        int i6;
        String codecsWithoutType;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        ArrayList arrayList11 = new ArrayList();
        ArrayList arrayList12 = new ArrayList();
        boolean z = false;
        boolean z2 = false;
        while (true) {
            String str5 = "application/x-mpegURL";
            boolean z3 = z;
            if (lineIterator.hasNext()) {
                String next = lineIterator.next();
                if (next.startsWith("#EXT")) {
                    arrayList12.add(next);
                }
                boolean zStartsWith = next.startsWith(TAG_I_FRAME_STREAM_INF);
                ArrayList arrayList13 = arrayList9;
                if (next.startsWith(TAG_DEFINE)) {
                    map2.put(parseStringAttr(next, REGEX_NAME, map2), parseStringAttr(next, REGEX_VALUE, map2));
                } else {
                    if (next.equals(TAG_INDEPENDENT_SEGMENTS)) {
                        arrayList3 = arrayList10;
                        z = true;
                    } else if (next.startsWith(TAG_MEDIA)) {
                        arrayList10.add(next);
                    } else if (next.startsWith(TAG_SESSION_KEY)) {
                        DrmInitData.SchemeData drmSchemeData = parseDrmSchemeData(next, parseOptionalStringAttr(next, REGEX_KEYFORMAT, "identity", map2), map2);
                        if (drmSchemeData != null) {
                            arrayList11.add(new DrmInitData(parseEncryptionScheme(parseStringAttr(next, REGEX_METHOD, map2)), drmSchemeData));
                        }
                    } else if (next.startsWith("#EXT-X-STREAM-INF") || zStartsWith) {
                        boolean zContains = z2 | next.contains(ATTR_CLOSED_CAPTIONS_NONE);
                        int i7 = zStartsWith ? 16384 : 0;
                        int intAttr = parseIntAttr(next, REGEX_BANDWIDTH);
                        int optionalIntAttr = parseOptionalIntAttr(next, REGEX_AVERAGE_BANDWIDTH, -1);
                        String optionalStringAttr3 = parseOptionalStringAttr(next, REGEX_VIDEO_RANGE, map2);
                        String optionalStringAttr4 = parseOptionalStringAttr(next, REGEX_CODECS, map2);
                        String optionalStringAttr5 = parseOptionalStringAttr(next, REGEX_SUPPLEMENTAL_CODECS, map2);
                        if (optionalStringAttr5 != null) {
                            String[] strArrSplit = Util.split(Util.splitAtFirst(optionalStringAttr5, ",")[0], "/");
                            String str6 = strArrSplit[0];
                            arrayList3 = arrayList10;
                            if (strArrSplit.length > 1) {
                                str4 = strArrSplit[1];
                                map = map;
                                str3 = str6;
                            } else {
                                str3 = str6;
                            }
                            codecsOfType = Util.getCodecsOfType(optionalStringAttr4, 2);
                            if (isDolbyVisionFormat(optionalStringAttr3, codecsOfType, str3, str4)) {
                                if (str3 == null) {
                                    str3 = codecsOfType;
                                }
                                codecsWithoutType = Util.getCodecsWithoutType(optionalStringAttr4, 2);
                                if (codecsWithoutType != null) {
                                    optionalStringAttr4 = str3 + "," + codecsWithoutType;
                                } else {
                                    optionalStringAttr4 = str3;
                                }
                            }
                            optionalStringAttr = parseOptionalStringAttr(next, REGEX_RESOLUTION, map2);
                            if (optionalStringAttr != null) {
                                String[] strArrSplit2 = Util.split(optionalStringAttr, "x");
                                i4 = Integer.parseInt(strArrSplit2[0]);
                                i5 = Integer.parseInt(strArrSplit2[1]);
                                if (i4 > 0 || i5 <= 0) {
                                    i5 = -1;
                                    i6 = -1;
                                } else {
                                    i6 = i4;
                                }
                                i3 = i5;
                                i2 = i6;
                            } else {
                                i2 = -1;
                                i3 = -1;
                            }
                            optionalStringAttr2 = parseOptionalStringAttr(next, REGEX_FRAME_RATE, map2);
                            if (optionalStringAttr2 != null) {
                                f = Float.parseFloat(optionalStringAttr2);
                            } else {
                                f = -1.0f;
                            }
                            String optionalStringAttr6 = parseOptionalStringAttr(next, REGEX_VIDEO, map2);
                            String optionalStringAttr7 = parseOptionalStringAttr(next, REGEX_AUDIO, map2);
                            String optionalStringAttr8 = parseOptionalStringAttr(next, REGEX_SUBTITLES, map2);
                            String optionalStringAttr9 = parseOptionalStringAttr(next, REGEX_CLOSED_CAPTIONS, map2);
                            if (zStartsWith) {
                                uriResolveToUri = UriUtil.resolveToUri(str, parseStringAttr(next, REGEX_URI, map2));
                            } else {
                                if (lineIterator.hasNext()) {
                                    throw ParserException.createForMalformedManifest("#EXT-X-STREAM-INF must be followed by another line", null);
                                }
                                uriResolveToUri = UriUtil.resolveToUri(str, replaceVariableReferences(lineIterator.next(), map2));
                            }
                            arrayList5.add(new HlsMultivariantPlaylist.Variant(uriResolveToUri, new Format.Builder().setId(arrayList5.size()).setContainerMimeType("application/x-mpegURL").setCodecs(optionalStringAttr4).setAverageBitrate(optionalIntAttr).setPeakBitrate(intAttr).setWidth(i2).setHeight(i3).setFrameRate(f).setRoleFlags(i7).build(), optionalStringAttr6, optionalStringAttr7, optionalStringAttr8, optionalStringAttr9));
                            map = map;
                            arrayList4 = (ArrayList) map.get(uriResolveToUri);
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                                map.put(uriResolveToUri, arrayList4);
                            }
                            arrayList4.add(new HlsTrackMetadataEntry.VariantInfo(optionalIntAttr, intAttr, optionalStringAttr6, optionalStringAttr7, optionalStringAttr8, optionalStringAttr9));
                            z = z3;
                            z2 = zContains;
                        } else {
                            arrayList3 = arrayList10;
                            str3 = null;
                        }
                        str4 = null;
                        codecsOfType = Util.getCodecsOfType(optionalStringAttr4, 2);
                        if (isDolbyVisionFormat(optionalStringAttr3, codecsOfType, str3, str4)) {
                            if (str3 == null) {
                                str3 = codecsOfType;
                            }
                            codecsWithoutType = Util.getCodecsWithoutType(optionalStringAttr4, 2);
                            if (codecsWithoutType != null) {
                                optionalStringAttr4 = str3 + "," + codecsWithoutType;
                            } else {
                                optionalStringAttr4 = str3;
                            }
                        }
                        optionalStringAttr = parseOptionalStringAttr(next, REGEX_RESOLUTION, map2);
                        if (optionalStringAttr != null) {
                            String[] strArrSplit3 = Util.split(optionalStringAttr, "x");
                            i4 = Integer.parseInt(strArrSplit3[0]);
                            i5 = Integer.parseInt(strArrSplit3[1]);
                            if (i4 > 0) {
                                i5 = -1;
                                i6 = -1;
                            } else {
                                i5 = -1;
                                i6 = -1;
                            }
                            i3 = i5;
                            i2 = i6;
                        } else {
                            i2 = -1;
                            i3 = -1;
                        }
                        optionalStringAttr2 = parseOptionalStringAttr(next, REGEX_FRAME_RATE, map2);
                        if (optionalStringAttr2 != null) {
                            f = Float.parseFloat(optionalStringAttr2);
                        } else {
                            f = -1.0f;
                        }
                        String optionalStringAttr10 = parseOptionalStringAttr(next, REGEX_VIDEO, map2);
                        String optionalStringAttr11 = parseOptionalStringAttr(next, REGEX_AUDIO, map2);
                        String optionalStringAttr12 = parseOptionalStringAttr(next, REGEX_SUBTITLES, map2);
                        String optionalStringAttr13 = parseOptionalStringAttr(next, REGEX_CLOSED_CAPTIONS, map2);
                        if (zStartsWith) {
                            uriResolveToUri = UriUtil.resolveToUri(str, parseStringAttr(next, REGEX_URI, map2));
                        } else {
                            if (lineIterator.hasNext()) {
                                throw ParserException.createForMalformedManifest("#EXT-X-STREAM-INF must be followed by another line", null);
                            }
                            uriResolveToUri = UriUtil.resolveToUri(str, replaceVariableReferences(lineIterator.next(), map2));
                        }
                        arrayList5.add(new HlsMultivariantPlaylist.Variant(uriResolveToUri, new Format.Builder().setId(arrayList5.size()).setContainerMimeType("application/x-mpegURL").setCodecs(optionalStringAttr4).setAverageBitrate(optionalIntAttr).setPeakBitrate(intAttr).setWidth(i2).setHeight(i3).setFrameRate(f).setRoleFlags(i7).build(), optionalStringAttr10, optionalStringAttr11, optionalStringAttr12, optionalStringAttr13));
                        map = map;
                        arrayList4 = (ArrayList) map.get(uriResolveToUri);
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList();
                            map.put(uriResolveToUri, arrayList4);
                        }
                        arrayList4.add(new HlsTrackMetadataEntry.VariantInfo(optionalIntAttr, intAttr, optionalStringAttr10, optionalStringAttr11, optionalStringAttr12, optionalStringAttr13));
                        z = z3;
                        z2 = zContains;
                    }
                    arrayList9 = arrayList13;
                    arrayList11 = arrayList11;
                    arrayList12 = arrayList12;
                    arrayList8 = arrayList8;
                    arrayList7 = arrayList7;
                    arrayList6 = arrayList6;
                    arrayList10 = arrayList3;
                }
                arrayList3 = arrayList10;
                z = z3;
                arrayList9 = arrayList13;
                arrayList11 = arrayList11;
                arrayList12 = arrayList12;
                arrayList8 = arrayList8;
                arrayList7 = arrayList7;
                arrayList6 = arrayList6;
                arrayList10 = arrayList3;
            } else {
                ArrayList arrayList14 = arrayList10;
                ArrayList arrayList15 = arrayList6;
                ArrayList arrayList16 = arrayList7;
                ArrayList arrayList17 = arrayList8;
                ArrayList arrayList18 = arrayList9;
                ArrayList arrayList19 = arrayList12;
                ArrayList arrayList20 = arrayList11;
                ArrayList arrayList21 = new ArrayList();
                HashSet hashSet = new HashSet();
                for (int i8 = 0; i8 < arrayList5.size(); i8++) {
                    HlsMultivariantPlaylist.Variant variant = (HlsMultivariantPlaylist.Variant) arrayList5.get(i8);
                    if (hashSet.add(variant.url)) {
                        Assertions.checkState(variant.format.metadata == null);
                        arrayList21.add(variant.copyWithFormat(variant.format.buildUpon().setMetadata(new Metadata(new HlsTrackMetadataEntry(null, null, (List) Assertions.checkNotNull((ArrayList) map.get(variant.url))))).build()));
                    }
                }
                ArrayList arrayList22 = null;
                Format formatBuild = null;
                int i9 = 0;
                while (i9 < arrayList14.size()) {
                    arrayList14 = arrayList14;
                    String str7 = (String) arrayList14.get(i9);
                    String stringAttr = parseStringAttr(str7, REGEX_GROUP_ID, map2);
                    String stringAttr2 = parseStringAttr(str7, REGEX_NAME, map2);
                    Format.Builder language = new Format.Builder().setId(stringAttr + ":" + stringAttr2).setLabel(stringAttr2).setContainerMimeType(str5).setSelectionFlags(parseSelectionFlags(str7)).setRoleFlags(parseRoleFlags(str7, map2)).setLanguage(parseOptionalStringAttr(str7, REGEX_LANGUAGE, map2));
                    String optionalStringAttr14 = parseOptionalStringAttr(str7, REGEX_URI, map2);
                    Uri uriResolveToUri2 = optionalStringAttr14 == null ? null : UriUtil.resolveToUri(str, optionalStringAttr14);
                    String str8 = str5;
                    Format format = formatBuild;
                    Metadata metadata = new Metadata(new HlsTrackMetadataEntry(stringAttr, stringAttr2, Collections.emptyList()));
                    String stringAttr3 = parseStringAttr(str7, REGEX_TYPE, map2);
                    stringAttr3.hashCode();
                    switch (stringAttr3) {
                        case "SUBTITLES":
                            arrayList = arrayList16;
                            HlsMultivariantPlaylist.Variant variantWithSubtitleGroup = getVariantWithSubtitleGroup(arrayList5, stringAttr);
                            if (variantWithSubtitleGroup != null) {
                                String codecsOfType2 = Util.getCodecsOfType(variantWithSubtitleGroup.format.codecs, 3);
                                language.setCodecs(codecsOfType2);
                                mediaMimeType = MimeTypes.getMediaMimeType(codecsOfType2);
                            } else {
                                mediaMimeType = null;
                            }
                            if (mediaMimeType == null) {
                                mediaMimeType = "text/vtt";
                            }
                            language.setSampleMimeType(mediaMimeType).setMetadata(metadata);
                            if (uriResolveToUri2 != null) {
                                HlsMultivariantPlaylist.Rendition rendition = new HlsMultivariantPlaylist.Rendition(uriResolveToUri2, language.build(), stringAttr, stringAttr2);
                                arrayList2 = arrayList17;
                                arrayList2.add(rendition);
                            } else {
                                arrayList2 = arrayList17;
                                Log.w(LOG_TAG, "EXT-X-MEDIA tag with missing mandatory URI attribute: skipping");
                            }
                            formatBuild = format;
                            break;
                        case "CLOSED-CAPTIONS":
                            arrayList = arrayList16;
                            String stringAttr4 = parseStringAttr(str7, REGEX_INSTREAM_ID, map2);
                            if (stringAttr4.startsWith("CC")) {
                                i = Integer.parseInt(stringAttr4.substring(2));
                                str2 = MimeTypes.APPLICATION_CEA608;
                            } else {
                                i = Integer.parseInt(stringAttr4.substring(7));
                                str2 = MimeTypes.APPLICATION_CEA708;
                            }
                            if (arrayList22 == null) {
                                arrayList22 = new ArrayList();
                            }
                            language.setSampleMimeType(str2).setAccessibilityChannel(i);
                            arrayList22.add(language.build());
                            formatBuild = format;
                            arrayList2 = arrayList17;
                            break;
                        case "AUDIO":
                            arrayList15 = arrayList15;
                            HlsMultivariantPlaylist.Variant variantWithAudioGroup = getVariantWithAudioGroup(arrayList5, stringAttr);
                            if (variantWithAudioGroup != null) {
                                String codecsOfType3 = Util.getCodecsOfType(variantWithAudioGroup.format.codecs, 1);
                                language.setCodecs(codecsOfType3);
                                mediaMimeType2 = MimeTypes.getMediaMimeType(codecsOfType3);
                            } else {
                                mediaMimeType2 = null;
                            }
                            String optionalStringAttr15 = parseOptionalStringAttr(str7, REGEX_CHANNELS, map2);
                            if (optionalStringAttr15 != null) {
                                language.setChannelCount(Integer.parseInt(Util.splitAtFirst(optionalStringAttr15, "/")[0]));
                                if ("audio/eac3".equals(mediaMimeType2) && optionalStringAttr15.endsWith("/JOC")) {
                                    language.setCodecs(MimeTypes.CODEC_E_AC3_JOC);
                                    mediaMimeType2 = MimeTypes.AUDIO_E_AC3_JOC;
                                }
                            }
                            language.setSampleMimeType(mediaMimeType2);
                            if (uriResolveToUri2 == null) {
                                arrayList = arrayList16;
                                if (variantWithAudioGroup != null) {
                                    formatBuild = language.build();
                                    arrayList2 = arrayList17;
                                }
                                break;
                            } else {
                                language.setMetadata(metadata);
                                arrayList = arrayList16;
                                arrayList.add(new HlsMultivariantPlaylist.Rendition(uriResolveToUri2, language.build(), stringAttr, stringAttr2));
                            }
                            arrayList2 = arrayList17;
                            formatBuild = format;
                            break;
                        case "VIDEO":
                            HlsMultivariantPlaylist.Variant variantWithVideoGroup = getVariantWithVideoGroup(arrayList5, stringAttr);
                            if (variantWithVideoGroup != null) {
                                Format format2 = variantWithVideoGroup.format;
                                String codecsOfType4 = Util.getCodecsOfType(format2.codecs, 2);
                                language.setCodecs(codecsOfType4).setSampleMimeType(MimeTypes.getMediaMimeType(codecsOfType4)).setWidth(format2.width).setHeight(format2.height).setFrameRate(format2.frameRate);
                            }
                            if (uriResolveToUri2 != null) {
                                language.setMetadata(metadata);
                                arrayList15.add(new HlsMultivariantPlaylist.Rendition(uriResolveToUri2, language.build(), stringAttr, stringAttr2));
                                break;
                            }
                        default:
                            arrayList2 = arrayList17;
                            arrayList = arrayList16;
                            formatBuild = format;
                            break;
                    }
                    i9++;
                    arrayList17 = arrayList2;
                    arrayList16 = arrayList;
                    str5 = str8;
                }
                return new HlsMultivariantPlaylist(str, arrayList19, arrayList21, arrayList15, arrayList16, arrayList17, arrayList18, formatBuild, z2 ? Collections.emptyList() : arrayList22, z3, map2, arrayList20);
            }
        }
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithAudioGroup(ArrayList<HlsMultivariantPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.audioGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithVideoGroup(ArrayList<HlsMultivariantPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.videoGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithSubtitleGroup(ArrayList<HlsMultivariantPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.subtitleGroupId)) {
                return variant;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:116:0x0335 A[PHI: r80
      0x0335: PHI (r80v11 java.lang.String) = (r80v9 java.lang.String), (r80v1 java.lang.String) binds: [B:122:0x0349, B:114:0x032a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:241:0x06a5 A[PHI: r48
      0x06a5: PHI (r48v10 int) = (r48v6 int), (r48v7 int), (r48v8 int), (r48v11 int) binds: [B:251:0x06c9, B:247:0x06bc, B:243:0x06af, B:240:0x06a3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:310:0x07f5  */
    /* JADX WARN: Code duplicated, block: B:319:0x0813  */
    /* JADX WARN: Code duplicated, block: B:326:0x0840 A[PHI: r71
      0x0840: PHI (r71v12 java.util.regex.Matcher) = 
      (r71v2 java.util.regex.Matcher)
      (r71v3 java.util.regex.Matcher)
      (r71v4 java.util.regex.Matcher)
      (r71v5 java.util.regex.Matcher)
      (r71v6 java.util.regex.Matcher)
      (r71v7 java.util.regex.Matcher)
      (r71v8 java.util.regex.Matcher)
      (r71v9 java.util.regex.Matcher)
      (r71v10 java.util.regex.Matcher)
      (r71v13 java.util.regex.Matcher)
     binds: [B:360:0x08b8, B:356:0x08ab, B:352:0x089e, B:348:0x0891, B:344:0x0884, B:340:0x0877, B:336:0x086a, B:332:0x085b, B:328:0x084b, B:325:0x083e] A[DONT_GENERATE, DONT_INLINE]] */
    private static HlsMediaPlaylist parseMediaPlaylist(HlsMultivariantPlaylist hlsMultivariantPlaylist, HlsMediaPlaylist hlsMediaPlaylist, LineIterator lineIterator, String str) throws IOException {
        ArrayList arrayList;
        int i;
        LinkedHashMap linkedHashMap;
        ArrayList arrayList2;
        String str2;
        ArrayList arrayList3;
        HlsMediaPlaylist.Segment segment;
        String stringAttr;
        ArrayList arrayList4;
        long j;
        long j2;
        HashMap map;
        String str3;
        String str4;
        String str5;
        long j3;
        DrmInitData drmInitData;
        String str6;
        String str7;
        HlsMediaPlaylist.Interstitial.Builder builder;
        Matcher matcher;
        byte b;
        int i2;
        byte b2;
        hlsMultivariantPlaylist = hlsMultivariantPlaylist;
        hlsMediaPlaylist = hlsMediaPlaylist;
        boolean z = hlsMultivariantPlaylist.hasIndependentSegments;
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        HlsMediaPlaylist.ServerControl serverControl = new HlsMediaPlaylist.ServerControl(C.TIME_UNSET, false, C.TIME_UNSET, C.TIME_UNSET, false);
        TreeMap treeMap = new TreeMap();
        boolean z2 = false;
        String str8 = "";
        boolean z3 = z;
        HlsMediaPlaylist.ServerControl serverControl2 = serverControl;
        long jMsToUs = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long longAttr = 0;
        long timeSecondsToUs = 0;
        long j8 = 0;
        String optionalStringAttr = str8;
        int i3 = 0;
        long j9 = C.TIME_UNSET;
        boolean optionalBooleanAttribute = false;
        boolean z4 = false;
        int i4 = 0;
        int intAttr = 1;
        long intAttr2 = C.TIME_UNSET;
        long doubleAttr = C.TIME_UNSET;
        boolean z5 = false;
        DrmInitData playlistProtectionSchemes = null;
        DrmInitData drmInitData2 = null;
        boolean z6 = false;
        String str9 = null;
        long j10 = -1;
        String str10 = null;
        String encryptionScheme = null;
        int i5 = 0;
        boolean z7 = false;
        HlsMediaPlaylist.Segment segment2 = null;
        ArrayList arrayList9 = arrayList6;
        HlsMediaPlaylist.Part part = null;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            if (next.startsWith("#EXT")) {
                arrayList8.add(next);
            }
            if (next.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                String stringAttr2 = parseStringAttr(next, REGEX_PLAYLIST_TYPE, map2);
                if ("VOD".equals(stringAttr2)) {
                    i3 = 1;
                } else if ("EVENT".equals(stringAttr2)) {
                    i3 = 2;
                }
            } else if (next.equals(TAG_IFRAME)) {
                z7 = true;
            } else {
                if (next.startsWith(TAG_START)) {
                    arrayList = arrayList5;
                    long doubleAttr2 = (long) (parseDoubleAttr(next, REGEX_TIME_OFFSET) * 1000000.0d);
                    optionalBooleanAttribute = parseOptionalBooleanAttribute(next, REGEX_PRECISE, z2);
                    j9 = doubleAttr2;
                } else {
                    arrayList = arrayList5;
                    if (next.startsWith(TAG_SERVER_CONTROL)) {
                        serverControl2 = parseServerControl(next);
                    } else if (next.startsWith(TAG_PART_INF)) {
                        doubleAttr = (long) (parseDoubleAttr(next, REGEX_PART_TARGET_DURATION) * 1000000.0d);
                    } else if (next.startsWith("#EXT-X-MAP")) {
                        String stringAttr3 = parseStringAttr(next, REGEX_URI, map2);
                        String optionalStringAttr2 = parseOptionalStringAttr(next, REGEX_ATTR_BYTERANGE, map2);
                        if (optionalStringAttr2 != null) {
                            String[] strArrSplit = Util.split(optionalStringAttr2, "@");
                            j10 = Long.parseLong(strArrSplit[z2 ? 1 : 0]);
                            if (strArrSplit.length > 1) {
                                j5 = Long.parseLong(strArrSplit[1]);
                            }
                        }
                        if (j10 == -1) {
                            j5 = 0;
                        }
                        String str11 = str9;
                        String str12 = str10;
                        if (str11 != null && str12 == null) {
                            throw ParserException.createForMalformedManifest("The encryption IV attribute must be present when an initialization segment is encrypted with METHOD=AES-128.", null);
                        }
                        segment2 = new HlsMediaPlaylist.Segment(stringAttr3, j5, j10, str11, str12);
                        if (j10 != -1) {
                            j5 += j10;
                        }
                        str9 = str11;
                        str10 = str12;
                        arrayList5 = arrayList;
                        j10 = -1;
                    } else {
                        str9 = str9;
                        str10 = str10;
                        if (next.startsWith("#EXT-X-TARGETDURATION")) {
                            intAttr2 = 1000000 * ((long) parseIntAttr(next, REGEX_TARGET_DURATION));
                        } else if (next.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                            longAttr = parseLongAttr(next, REGEX_MEDIA_SEQUENCE);
                            str10 = str10;
                            str9 = str9;
                            j4 = longAttr;
                            arrayList5 = arrayList;
                            z2 = false;
                        } else if (next.startsWith("#EXT-X-VERSION")) {
                            intAttr = parseIntAttr(next, REGEX_VERSION);
                        } else {
                            if (next.startsWith(TAG_DEFINE)) {
                                String optionalStringAttr3 = parseOptionalStringAttr(next, REGEX_IMPORT, map2);
                                if (optionalStringAttr3 != null) {
                                    String str13 = hlsMultivariantPlaylist.variableDefinitions.get(optionalStringAttr3);
                                    if (str13 != null) {
                                        map2.put(optionalStringAttr3, str13);
                                    }
                                } else {
                                    map2.put(parseStringAttr(next, REGEX_NAME, map2), parseStringAttr(next, REGEX_VALUE, map2));
                                }
                                i = i3;
                                map3 = map3;
                                arrayList7 = arrayList7;
                                linkedHashMap = linkedHashMap2;
                                arrayList2 = arrayList9;
                                treeMap = treeMap;
                                str2 = str8;
                                encryptionScheme = encryptionScheme;
                                arrayList3 = arrayList;
                                part = part;
                                arrayList8 = arrayList8;
                                longAttr = longAttr;
                            } else {
                                if (next.startsWith("#EXTINF")) {
                                    timeSecondsToUs = parseTimeSecondsToUs(next, REGEX_MEDIA_DURATION);
                                    optionalStringAttr = parseOptionalStringAttr(next, REGEX_MEDIA_TITLE, str8, map2);
                                } else {
                                    str2 = str8;
                                    if (next.startsWith(TAG_SKIP)) {
                                        int intAttr3 = parseIntAttr(next, REGEX_SKIPPED_SEGMENTS);
                                        Assertions.checkState(hlsMediaPlaylist != null && arrayList.isEmpty());
                                        int i6 = (int) (j4 - ((HlsMediaPlaylist) Util.castNonNull(hlsMediaPlaylist)).mediaSequence);
                                        int i7 = intAttr3 + i6;
                                        if (i6 < 0 || i7 > hlsMediaPlaylist.segments.size()) {
                                            throw new DeltaUpdateException();
                                        }
                                        str10 = str10;
                                        long j11 = j7;
                                        while (i6 < i7) {
                                            HlsMediaPlaylist.Segment segmentCopyWith = hlsMediaPlaylist.segments.get(i6);
                                            LinkedHashMap linkedHashMap3 = linkedHashMap2;
                                            ArrayList arrayList10 = arrayList8;
                                            if (j4 != hlsMediaPlaylist.mediaSequence) {
                                                segmentCopyWith = segmentCopyWith.copyWith(j11, (hlsMediaPlaylist.discontinuitySequence - i4) + segmentCopyWith.relativeDiscontinuitySequence);
                                            }
                                            ArrayList arrayList11 = arrayList;
                                            arrayList11.add(segmentCopyWith);
                                            int i8 = i7;
                                            long j12 = j11 + segmentCopyWith.durationUs;
                                            if (segmentCopyWith.byteRangeLength != -1) {
                                                j5 = segmentCopyWith.byteRangeOffset + segmentCopyWith.byteRangeLength;
                                            }
                                            int i9 = segmentCopyWith.relativeDiscontinuitySequence;
                                            HlsMediaPlaylist.Segment segment3 = segmentCopyWith.initializationSegment;
                                            DrmInitData drmInitData3 = segmentCopyWith.drmInitData;
                                            String str14 = segmentCopyWith.fullSegmentEncryptionKeyUri;
                                            if (segmentCopyWith.encryptionIV != null) {
                                                segment = segment3;
                                                if (!segmentCopyWith.encryptionIV.equals(Long.toHexString(longAttr))) {
                                                }
                                                longAttr++;
                                                i6++;
                                                hlsMediaPlaylist = hlsMediaPlaylist;
                                                i7 = i8;
                                                arrayList = arrayList11;
                                                str9 = str14;
                                                arrayList8 = arrayList10;
                                                i5 = i9;
                                                j6 = j12;
                                                segment2 = segment;
                                                linkedHashMap2 = linkedHashMap3;
                                                drmInitData2 = drmInitData3;
                                                j11 = j6;
                                            } else {
                                                segment = segment3;
                                            }
                                            str10 = segmentCopyWith.encryptionIV;
                                            longAttr++;
                                            i6++;
                                            hlsMediaPlaylist = hlsMediaPlaylist;
                                            i7 = i8;
                                            arrayList = arrayList11;
                                            str9 = str14;
                                            arrayList8 = arrayList10;
                                            i5 = i9;
                                            j6 = j12;
                                            segment2 = segment;
                                            linkedHashMap2 = linkedHashMap3;
                                            drmInitData2 = drmInitData3;
                                            j11 = j6;
                                        }
                                        hlsMultivariantPlaylist = hlsMultivariantPlaylist;
                                        hlsMediaPlaylist = hlsMediaPlaylist;
                                        str8 = str2;
                                        j7 = j11;
                                        str9 = str9;
                                        arrayList5 = arrayList;
                                    } else {
                                        linkedHashMap2 = linkedHashMap2;
                                        arrayList8 = arrayList8;
                                        arrayList5 = arrayList;
                                        if (next.startsWith("#EXT-X-KEY")) {
                                            String stringAttr4 = parseStringAttr(next, REGEX_METHOD, map2);
                                            String optionalStringAttr4 = parseOptionalStringAttr(next, REGEX_KEYFORMAT, "identity", map2);
                                            if ("NONE".equals(stringAttr4)) {
                                                treeMap.clear();
                                                stringAttr = null;
                                                drmInitData2 = null;
                                                str10 = null;
                                            } else {
                                                String optionalStringAttr5 = parseOptionalStringAttr(next, REGEX_IV, map2);
                                                if ("identity".equals(optionalStringAttr4)) {
                                                    if ("AES-128".equals(stringAttr4)) {
                                                        stringAttr = parseStringAttr(next, REGEX_URI, map2);
                                                        str10 = optionalStringAttr5;
                                                    } else {
                                                        str10 = optionalStringAttr5;
                                                        stringAttr = null;
                                                    }
                                                } else {
                                                    String str15 = encryptionScheme;
                                                    encryptionScheme = str15 == null ? parseEncryptionScheme(stringAttr4) : str15;
                                                    DrmInitData.SchemeData drmSchemeData = parseDrmSchemeData(next, optionalStringAttr4, map2);
                                                    if (drmSchemeData != null) {
                                                        treeMap.put(optionalStringAttr4, drmSchemeData);
                                                        str10 = optionalStringAttr5;
                                                        stringAttr = null;
                                                        drmInitData2 = null;
                                                    } else {
                                                        str10 = optionalStringAttr5;
                                                        stringAttr = null;
                                                    }
                                                }
                                            }
                                            hlsMediaPlaylist = hlsMediaPlaylist;
                                            str9 = stringAttr;
                                            linkedHashMap2 = linkedHashMap2;
                                            hlsMultivariantPlaylist = hlsMultivariantPlaylist;
                                        } else {
                                            encryptionScheme = encryptionScheme;
                                            if (next.startsWith(TAG_BYTERANGE)) {
                                                String[] strArrSplit2 = Util.split(parseStringAttr(next, REGEX_BYTERANGE, map2), "@");
                                                j10 = Long.parseLong(strArrSplit2[0]);
                                                if (strArrSplit2.length > 1) {
                                                    j5 = Long.parseLong(strArrSplit2[1]);
                                                }
                                            } else if (next.startsWith(TAG_DISCONTINUITY_SEQUENCE)) {
                                                i4 = Integer.parseInt(next.substring(next.indexOf(58) + 1));
                                                z4 = true;
                                            } else if (next.equals("#EXT-X-DISCONTINUITY")) {
                                                i5++;
                                            } else {
                                                if (next.startsWith(TAG_PROGRAM_DATE_TIME)) {
                                                    if (jMsToUs == 0) {
                                                        jMsToUs = Util.msToUs(Util.parseXsDateTime(next.substring(next.indexOf(58) + 1))) - j7;
                                                    } else {
                                                        i = i3;
                                                        arrayList3 = arrayList5;
                                                        arrayList2 = arrayList9;
                                                    }
                                                } else if (next.equals(TAG_GAP)) {
                                                    z6 = true;
                                                } else if (next.equals(TAG_INDEPENDENT_SEGMENTS)) {
                                                    z3 = true;
                                                } else if (next.equals("#EXT-X-ENDLIST")) {
                                                    z5 = true;
                                                } else {
                                                    if (next.startsWith(TAG_RENDITION_REPORT)) {
                                                        i = i3;
                                                        arrayList4 = arrayList5;
                                                        arrayList2 = arrayList9;
                                                        arrayList7.add(new HlsMediaPlaylist.RenditionReport(Uri.parse(UriUtil.resolve(str, parseStringAttr(next, REGEX_URI, map2))), parseOptionalLongAttr(next, REGEX_LAST_MSN, -1L), parseOptionalIntAttr(next, REGEX_LAST_PART, -1)));
                                                    } else {
                                                        i = i3;
                                                        arrayList4 = arrayList5;
                                                        arrayList2 = arrayList9;
                                                        if (next.startsWith(TAG_PRELOAD_HINT)) {
                                                            if (part == null && TYPE_PART.equals(parseStringAttr(next, REGEX_PRELOAD_HINT_TYPE, map2))) {
                                                                String stringAttr5 = parseStringAttr(next, REGEX_URI, map2);
                                                                long optionalLongAttr = parseOptionalLongAttr(next, REGEX_BYTERANGE_START, -1L);
                                                                long optionalLongAttr2 = parseOptionalLongAttr(next, REGEX_BYTERANGE_LENGTH, -1L);
                                                                long j13 = longAttr;
                                                                String segmentEncryptionIV = getSegmentEncryptionIV(j13, str9, str10);
                                                                if (drmInitData2 == null && !treeMap.isEmpty()) {
                                                                    DrmInitData.SchemeData[] schemeDataArr = (DrmInitData.SchemeData[]) treeMap.values().toArray(new DrmInitData.SchemeData[0]);
                                                                    DrmInitData drmInitData4 = new DrmInitData(encryptionScheme, schemeDataArr);
                                                                    if (playlistProtectionSchemes == null) {
                                                                        playlistProtectionSchemes = getPlaylistProtectionSchemes(encryptionScheme, schemeDataArr);
                                                                    }
                                                                    drmInitData2 = drmInitData4;
                                                                }
                                                                if (optionalLongAttr == -1 || optionalLongAttr2 != -1) {
                                                                    part = new HlsMediaPlaylist.Part(stringAttr5, segment2, 0L, i5, j6, drmInitData2, str9, segmentEncryptionIV, optionalLongAttr != -1 ? optionalLongAttr : 0L, optionalLongAttr2, false, false, true);
                                                                }
                                                                hlsMultivariantPlaylist = hlsMultivariantPlaylist;
                                                                hlsMediaPlaylist = hlsMediaPlaylist;
                                                                encryptionScheme = encryptionScheme;
                                                                longAttr = j13;
                                                                str9 = str9;
                                                                arrayList8 = arrayList8;
                                                                arrayList9 = arrayList2;
                                                                i3 = i;
                                                                arrayList5 = arrayList4;
                                                                linkedHashMap2 = linkedHashMap2;
                                                                str8 = str2;
                                                                str10 = str10;
                                                            }
                                                        } else {
                                                            long j14 = longAttr;
                                                            if (next.startsWith(TAG_PART)) {
                                                                String segmentEncryptionIV2 = getSegmentEncryptionIV(j14, str9, str10);
                                                                String stringAttr6 = parseStringAttr(next, REGEX_URI, map2);
                                                                long doubleAttr3 = (long) (parseDoubleAttr(next, REGEX_ATTR_DURATION) * 1000000.0d);
                                                                HlsMediaPlaylist.Part part2 = part;
                                                                boolean optionalBooleanAttribute2 = parseOptionalBooleanAttribute(next, REGEX_INDEPENDENT, false) | (z3 && arrayList2.isEmpty());
                                                                boolean optionalBooleanAttribute3 = parseOptionalBooleanAttribute(next, REGEX_GAP, false);
                                                                String optionalStringAttr6 = parseOptionalStringAttr(next, REGEX_ATTR_BYTERANGE, map2);
                                                                if (optionalStringAttr6 != null) {
                                                                    String[] strArrSplit3 = Util.split(optionalStringAttr6, "@");
                                                                    j2 = Long.parseLong(strArrSplit3[0]);
                                                                    if (strArrSplit3.length > 1) {
                                                                        j8 = Long.parseLong(strArrSplit3[1]);
                                                                    }
                                                                    j = -1;
                                                                } else {
                                                                    j = -1;
                                                                    j2 = -1;
                                                                }
                                                                if (j2 == j) {
                                                                    j8 = 0;
                                                                }
                                                                if (drmInitData2 == null && !treeMap.isEmpty()) {
                                                                    DrmInitData.SchemeData[] schemeDataArr2 = (DrmInitData.SchemeData[]) treeMap.values().toArray(new DrmInitData.SchemeData[0]);
                                                                    DrmInitData drmInitData5 = new DrmInitData(encryptionScheme, schemeDataArr2);
                                                                    if (playlistProtectionSchemes == null) {
                                                                        playlistProtectionSchemes = getPlaylistProtectionSchemes(encryptionScheme, schemeDataArr2);
                                                                    }
                                                                    drmInitData2 = drmInitData5;
                                                                }
                                                                arrayList2.add(new HlsMediaPlaylist.Part(stringAttr6, segment2, doubleAttr3, i5, j6, drmInitData2, str9, segmentEncryptionIV2, j8, j2, optionalBooleanAttribute3, optionalBooleanAttribute2, false));
                                                                j6 += doubleAttr3;
                                                                if (j2 != j) {
                                                                    j8 += j2;
                                                                }
                                                                hlsMultivariantPlaylist = hlsMultivariantPlaylist;
                                                                hlsMediaPlaylist = hlsMediaPlaylist;
                                                                longAttr = j14;
                                                                arrayList9 = arrayList2;
                                                                str9 = str9;
                                                                arrayList8 = arrayList8;
                                                                i3 = i;
                                                                part = part2;
                                                                arrayList5 = arrayList4;
                                                                encryptionScheme = encryptionScheme;
                                                                str8 = str2;
                                                                str10 = str10;
                                                                linkedHashMap2 = linkedHashMap2;
                                                            } else {
                                                                part = part;
                                                                arrayList2 = arrayList2;
                                                                if (next.startsWith(TAG_DATERANGE) && parseOptionalStringAttr(next, REGEX_CLASS, str2, map2).equals(DATERANGE_CLASS_INTERSTITIALS)) {
                                                                    String stringAttr7 = parseStringAttr(next, REGEX_ID, map2);
                                                                    String optionalStringAttr7 = parseOptionalStringAttr(next, REGEX_ASSET_URI, map2);
                                                                    Uri uri = optionalStringAttr7 != null ? Uri.parse(optionalStringAttr7) : null;
                                                                    String optionalStringAttr8 = parseOptionalStringAttr(next, REGEX_ASSET_LIST_URI, map2);
                                                                    Uri uri2 = optionalStringAttr8 != null ? Uri.parse(optionalStringAttr8) : null;
                                                                    String optionalStringAttr9 = parseOptionalStringAttr(next, REGEX_START_DATE, map2);
                                                                    long jMsToUs2 = optionalStringAttr9 != null ? Util.msToUs(Util.parseXsDateTime(optionalStringAttr9)) : C.TIME_UNSET;
                                                                    String optionalStringAttr10 = parseOptionalStringAttr(next, REGEX_END_DATE, map2);
                                                                    long jMsToUs3 = optionalStringAttr10 != null ? Util.msToUs(Util.parseXsDateTime(optionalStringAttr10)) : C.TIME_UNSET;
                                                                    ArrayList arrayList12 = new ArrayList();
                                                                    str3 = encryptionScheme;
                                                                    String optionalStringAttr11 = parseOptionalStringAttr(next, REGEX_CUE, map2);
                                                                    map = map3;
                                                                    if (optionalStringAttr11 != null) {
                                                                        String[] strArrSplit4 = Util.split(optionalStringAttr11, ",");
                                                                        int length = strArrSplit4.length;
                                                                        int i10 = 0;
                                                                        while (i10 < length) {
                                                                            String[] strArr = strArrSplit4;
                                                                            String strTrim = strArrSplit4[i10].trim();
                                                                            strTrim.hashCode();
                                                                            switch (strTrim.hashCode()) {
                                                                                case 79491:
                                                                                    i2 = length;
                                                                                    if (!strTrim.equals(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_PRE)) {
                                                                                        b2 = -1;
                                                                                    } else {
                                                                                        b2 = 0;
                                                                                    }
                                                                                    break;
                                                                                case 2430593:
                                                                                    i2 = length;
                                                                                    if (!strTrim.equals(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_ONCE)) {
                                                                                        b2 = -1;
                                                                                    } else {
                                                                                        b2 = 1;
                                                                                    }
                                                                                    break;
                                                                                case 2461856:
                                                                                    i2 = length;
                                                                                    if (!strTrim.equals(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST)) {
                                                                                        b2 = -1;
                                                                                    } else {
                                                                                        b2 = 2;
                                                                                    }
                                                                                    break;
                                                                                default:
                                                                                    i2 = length;
                                                                                    b2 = -1;
                                                                                    break;
                                                                            }
                                                                            switch (b2) {
                                                                                case 0:
                                                                                case 1:
                                                                                case 2:
                                                                                    arrayList12.add(strTrim);
                                                                                    break;
                                                                            }
                                                                            i10++;
                                                                            length = i2;
                                                                            strArrSplit4 = strArr;
                                                                        }
                                                                    }
                                                                    double optionalDoubleAttr = parseOptionalDoubleAttr(next, REGEX_ATTR_DURATION_PREFIXED, -1.0d);
                                                                    long j15 = optionalDoubleAttr >= 0.0d ? (long) (optionalDoubleAttr * 1000000.0d) : C.TIME_UNSET;
                                                                    str5 = str9;
                                                                    double optionalDoubleAttr2 = parseOptionalDoubleAttr(next, REGEX_PLANNED_DURATION, -1.0d);
                                                                    long j16 = optionalDoubleAttr2 >= 0.0d ? (long) (optionalDoubleAttr2 * 1000000.0d) : C.TIME_UNSET;
                                                                    str4 = str10;
                                                                    boolean optionalBooleanAttribute4 = parseOptionalBooleanAttribute(next, REGEX_END_ON_NEXT, false);
                                                                    long j17 = j16;
                                                                    double optionalDoubleAttr3 = parseOptionalDoubleAttr(next, REGEX_RESUME_OFFSET, Double.MIN_VALUE);
                                                                    long j18 = optionalDoubleAttr3 != Double.MIN_VALUE ? (long) (optionalDoubleAttr3 * 1000000.0d) : C.TIME_UNSET;
                                                                    double optionalDoubleAttr4 = parseOptionalDoubleAttr(next, REGEX_PLAYOUT_LIMIT, -1.0d);
                                                                    long j19 = optionalDoubleAttr4 >= 0.0d ? (long) (optionalDoubleAttr4 * 1000000.0d) : C.TIME_UNSET;
                                                                    ArrayList arrayList13 = new ArrayList();
                                                                    long j20 = j19;
                                                                    String optionalStringAttr12 = parseOptionalStringAttr(next, REGEX_SNAP, map2);
                                                                    if (optionalStringAttr12 != null) {
                                                                        String[] strArrSplit5 = Util.split(optionalStringAttr12, ",");
                                                                        int length2 = strArrSplit5.length;
                                                                        int i11 = 0;
                                                                        while (i11 < length2) {
                                                                            String[] strArr2 = strArrSplit5;
                                                                            String strTrim2 = strArrSplit5[i11].trim();
                                                                            strTrim2.hashCode();
                                                                            int i12 = length2;
                                                                            if (strTrim2.equals(HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN) || strTrim2.equals(HlsMediaPlaylist.Interstitial.SNAP_TYPE_OUT)) {
                                                                                arrayList13.add(strTrim2);
                                                                            }
                                                                            i11++;
                                                                            length2 = i12;
                                                                            strArrSplit5 = strArr2;
                                                                        }
                                                                    }
                                                                    ArrayList arrayList14 = new ArrayList();
                                                                    String optionalStringAttr13 = parseOptionalStringAttr(next, REGEX_RESTRICT, map2);
                                                                    if (optionalStringAttr13 != null) {
                                                                        String[] strArrSplit6 = Util.split(optionalStringAttr13, ",");
                                                                        int length3 = strArrSplit6.length;
                                                                        int i13 = 0;
                                                                        while (i13 < length3) {
                                                                            String[] strArr3 = strArrSplit6;
                                                                            String strTrim3 = strArrSplit6[i13].trim();
                                                                            strTrim3.hashCode();
                                                                            int i14 = length3;
                                                                            if (strTrim3.equals(HlsMediaPlaylist.Interstitial.NAVIGATION_RESTRICTION_JUMP) || strTrim3.equals(HlsMediaPlaylist.Interstitial.NAVIGATION_RESTRICTION_SKIP)) {
                                                                                arrayList14.add(strTrim3);
                                                                            }
                                                                            i13++;
                                                                            length3 = i14;
                                                                            strArrSplit6 = strArr3;
                                                                        }
                                                                    }
                                                                    String optionalStringAttr14 = parseOptionalStringAttr(next, REGEX_CONTENT_MAY_VARY, map2);
                                                                    Boolean boolValueOf = optionalStringAttr14 != null ? Boolean.valueOf(!optionalStringAttr14.equals(BOOLEAN_FALSE)) : null;
                                                                    String optionalStringAttr15 = parseOptionalStringAttr(next, REGEX_TIMELINE_OCCUPIES, map2);
                                                                    if (optionalStringAttr15 != null) {
                                                                        str6 = HlsMediaPlaylist.Interstitial.TIMELINE_OCCUPIES_RANGE;
                                                                        if (!optionalStringAttr15.equals(HlsMediaPlaylist.Interstitial.TIMELINE_OCCUPIES_RANGE)) {
                                                                            str6 = HlsMediaPlaylist.Interstitial.TIMELINE_OCCUPIES_POINT;
                                                                            if (!optionalStringAttr15.equals(HlsMediaPlaylist.Interstitial.TIMELINE_OCCUPIES_POINT)) {
                                                                                str6 = null;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        str6 = null;
                                                                    }
                                                                    String optionalStringAttr16 = parseOptionalStringAttr(next, REGEX_TIMELINE_STYLE, map2);
                                                                    String str16 = str6;
                                                                    if (optionalStringAttr16 != null) {
                                                                        str7 = HlsMediaPlaylist.Interstitial.TIMELINE_STYLE_PRIMARY;
                                                                        if (!optionalStringAttr16.equals(HlsMediaPlaylist.Interstitial.TIMELINE_STYLE_PRIMARY)) {
                                                                            str7 = HlsMediaPlaylist.Interstitial.TIMELINE_STYLE_HIGHLIGHT;
                                                                            if (!optionalStringAttr16.equals(HlsMediaPlaylist.Interstitial.TIMELINE_STYLE_HIGHLIGHT)) {
                                                                                str7 = null;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        str7 = null;
                                                                    }
                                                                    ArrayList arrayList15 = new ArrayList();
                                                                    String str17 = str7;
                                                                    String strSubstring = next.substring(17);
                                                                    Matcher matcher2 = REGEX_CLIENT_DEFINED_ATTRIBUTE_PREFIX.matcher(strSubstring);
                                                                    while (matcher2.find()) {
                                                                        Boolean bool = boolValueOf;
                                                                        String strGroup = matcher2.group();
                                                                        strGroup.hashCode();
                                                                        switch (strGroup.hashCode()) {
                                                                            case -2136701954:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-SNAP=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 0;
                                                                                }
                                                                                break;
                                                                            case -1843050726:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-CONTENT-MAY-VARY=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 1;
                                                                                }
                                                                                break;
                                                                            case -148960310:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-PLAYOUT-LIMIT=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 2;
                                                                                }
                                                                                break;
                                                                            case -36345757:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-TIMELINE-STYLE=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 3;
                                                                                }
                                                                                break;
                                                                            case 397239341:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-ASSET-LIST=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 4;
                                                                                }
                                                                                break;
                                                                            case 850193465:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-TIMELINE-OCCUPIES=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 5;
                                                                                }
                                                                                break;
                                                                            case 1472528844:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-RESTRICT=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 6;
                                                                                }
                                                                                break;
                                                                            case 1748487807:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-RESUME-OFFSET=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 7;
                                                                                }
                                                                                break;
                                                                            case 1814205923:
                                                                                matcher = matcher2;
                                                                                if (!strGroup.equals("X-ASSET-URI=")) {
                                                                                    b = -1;
                                                                                } else {
                                                                                    b = 8;
                                                                                }
                                                                                break;
                                                                            default:
                                                                                matcher = matcher2;
                                                                                b = -1;
                                                                                break;
                                                                        }
                                                                        switch (b) {
                                                                            case 0:
                                                                            case 1:
                                                                            case 2:
                                                                            case 3:
                                                                            case 4:
                                                                            case 5:
                                                                            case 6:
                                                                            case 7:
                                                                            case 8:
                                                                                break;
                                                                            default:
                                                                                arrayList15.add(parseClientDefinedAttribute(strSubstring, strGroup.substring(0, strGroup.length() - 1), map2));
                                                                                break;
                                                                        }
                                                                        boolValueOf = bool;
                                                                        matcher2 = matcher;
                                                                        arrayList14 = arrayList14;
                                                                    }
                                                                    Boolean bool2 = boolValueOf;
                                                                    ArrayList arrayList16 = arrayList14;
                                                                    linkedHashMap = linkedHashMap2;
                                                                    if (linkedHashMap.containsKey(stringAttr7)) {
                                                                        builder = (HlsMediaPlaylist.Interstitial.Builder) linkedHashMap.get(stringAttr7);
                                                                    } else {
                                                                        builder = new HlsMediaPlaylist.Interstitial.Builder(stringAttr7);
                                                                    }
                                                                    linkedHashMap.put(stringAttr7, builder.setAssetUri(uri).setAssetListUri(uri2).setStartDateUnixUs(jMsToUs2).setEndDateUnixUs(jMsToUs3).setDurationUs(j15).setPlannedDurationUs(j17).setCue(arrayList12).setEndOnNext(optionalBooleanAttribute4).setResumeOffsetUs(j18).setPlayoutLimitUs(j20).setSnapTypes(arrayList13).setRestrictions(arrayList16).setClientDefinedAttributes(arrayList15).setContentMayVary(bool2).setTimelineOccupies(str16).setTimelineStyle(str17));
                                                                    longAttr = j14;
                                                                } else {
                                                                    map = map3;
                                                                    arrayList7 = arrayList7;
                                                                    str3 = encryptionScheme;
                                                                    treeMap = treeMap;
                                                                    str2 = str2;
                                                                    str4 = str10;
                                                                    arrayList2 = arrayList2;
                                                                    str5 = str9;
                                                                    linkedHashMap = linkedHashMap2;
                                                                    longAttr = j14;
                                                                    if (!next.startsWith("#")) {
                                                                        str10 = str4;
                                                                        String segmentEncryptionIV3 = getSegmentEncryptionIV(longAttr, str5, str10);
                                                                        longAttr++;
                                                                        String strReplaceVariableReferences = replaceVariableReferences(next, map2);
                                                                        map3 = map;
                                                                        HlsMediaPlaylist.Segment segment4 = (HlsMediaPlaylist.Segment) map3.get(strReplaceVariableReferences);
                                                                        if (j10 == -1) {
                                                                            j3 = 0;
                                                                        } else {
                                                                            if (z7 && segment2 == null && segment4 == null) {
                                                                                segment4 = new HlsMediaPlaylist.Segment(strReplaceVariableReferences, 0L, j5, null, null);
                                                                                map3.put(strReplaceVariableReferences, segment4);
                                                                            }
                                                                            j3 = j5;
                                                                        }
                                                                        if (drmInitData2 != null || treeMap.isEmpty()) {
                                                                            encryptionScheme = str3;
                                                                            drmInitData = drmInitData2;
                                                                        } else {
                                                                            DrmInitData.SchemeData[] schemeDataArr3 = (DrmInitData.SchemeData[]) treeMap.values().toArray(new DrmInitData.SchemeData[0]);
                                                                            encryptionScheme = str3;
                                                                            drmInitData = new DrmInitData(encryptionScheme, schemeDataArr3);
                                                                            if (playlistProtectionSchemes == null) {
                                                                                playlistProtectionSchemes = getPlaylistProtectionSchemes(encryptionScheme, schemeDataArr3);
                                                                            }
                                                                        }
                                                                        HlsMediaPlaylist.Segment segment5 = new HlsMediaPlaylist.Segment(strReplaceVariableReferences, segment2 != null ? segment2 : segment4, optionalStringAttr, timeSecondsToUs, i5, j7, drmInitData, str5, segmentEncryptionIV3, j3, j10, z6, arrayList2);
                                                                        arrayList3 = arrayList4;
                                                                        arrayList3.add(segment5);
                                                                        j6 = j7 + timeSecondsToUs;
                                                                        arrayList9 = new ArrayList();
                                                                        if (j10 != -1) {
                                                                            j3 += j10;
                                                                        }
                                                                        j5 = j3;
                                                                        linkedHashMap2 = linkedHashMap;
                                                                        drmInitData2 = drmInitData;
                                                                        str9 = str5;
                                                                        timeSecondsToUs = 0;
                                                                        arrayList8 = arrayList8;
                                                                        j7 = j6;
                                                                        arrayList7 = arrayList7;
                                                                        str8 = str2;
                                                                        optionalStringAttr = str8;
                                                                        treeMap = treeMap;
                                                                        part = part;
                                                                        z2 = false;
                                                                        z6 = false;
                                                                        j10 = -1;
                                                                    }
                                                                    arrayList5 = arrayList3;
                                                                    map3 = map3;
                                                                    encryptionScheme = encryptionScheme;
                                                                    i3 = i;
                                                                    hlsMultivariantPlaylist = hlsMultivariantPlaylist;
                                                                    str10 = str10;
                                                                }
                                                                encryptionScheme = str3;
                                                                map3 = map;
                                                                str9 = str5;
                                                                str10 = str4;
                                                                arrayList3 = arrayList4;
                                                            }
                                                        }
                                                    }
                                                    arrayList3 = arrayList4;
                                                }
                                                linkedHashMap = linkedHashMap2;
                                            }
                                        }
                                        str8 = str2;
                                    }
                                }
                                z2 = false;
                            }
                            linkedHashMap2 = linkedHashMap;
                            longAttr = longAttr;
                            str9 = str9;
                            arrayList8 = arrayList8;
                            arrayList9 = arrayList2;
                            arrayList7 = arrayList7;
                            str8 = str2;
                            treeMap = treeMap;
                            part = part;
                            z2 = false;
                            arrayList5 = arrayList3;
                            map3 = map3;
                            encryptionScheme = encryptionScheme;
                            i3 = i;
                            hlsMultivariantPlaylist = hlsMultivariantPlaylist;
                            str10 = str10;
                        }
                        str10 = str10;
                        str9 = str9;
                        arrayList5 = arrayList;
                        z2 = false;
                    }
                }
                arrayList5 = arrayList;
            }
        }
        int i15 = i3;
        HlsMediaPlaylist.Part part3 = part;
        ArrayList arrayList17 = arrayList7;
        LinkedHashMap linkedHashMap4 = linkedHashMap2;
        ArrayList arrayList18 = arrayList8;
        ArrayList arrayList19 = arrayList9;
        ArrayList arrayList20 = arrayList5;
        HashMap map4 = new HashMap();
        int i16 = 0;
        while (i16 < arrayList17.size()) {
            ArrayList arrayList21 = arrayList17;
            HlsMediaPlaylist.RenditionReport renditionReport = (HlsMediaPlaylist.RenditionReport) arrayList21.get(i16);
            long size = renditionReport.lastMediaSequence;
            if (size == -1) {
                size = (j4 + ((long) arrayList20.size())) - (arrayList19.isEmpty() ? 1L : 0L);
            }
            int size2 = renditionReport.lastPartIndex;
            if (size2 == -1 && doubleAttr != C.TIME_UNSET) {
                size2 = (arrayList19.isEmpty() ? ((HlsMediaPlaylist.Segment) Iterables.getLast(arrayList20)).parts : arrayList19).size() - 1;
            }
            map4.put(renditionReport.playlistUri, new HlsMediaPlaylist.RenditionReport(renditionReport.playlistUri, size, size2));
            i16++;
            arrayList17 = arrayList21;
        }
        if (part3 != null) {
            arrayList19.add(part3);
        }
        ArrayList arrayList22 = new ArrayList();
        Iterator it = linkedHashMap4.values().iterator();
        while (it.hasNext()) {
            HlsMediaPlaylist.Interstitial interstitialBuild = ((HlsMediaPlaylist.Interstitial.Builder) it.next()).build();
            if (interstitialBuild != null) {
                arrayList22.add(interstitialBuild);
            }
        }
        return new HlsMediaPlaylist(i15, str, arrayList18, j9, optionalBooleanAttribute, jMsToUs, z4, i4, j4, intAttr, intAttr2, doubleAttr, z3, z5, jMsToUs != 0, playlistProtectionSchemes, arrayList20, arrayList19, serverControl2, map4, arrayList22);
    }

    private static DrmInitData getPlaylistProtectionSchemes(String str, DrmInitData.SchemeData[] schemeDataArr) {
        DrmInitData.SchemeData[] schemeDataArr2 = new DrmInitData.SchemeData[schemeDataArr.length];
        for (int i = 0; i < schemeDataArr.length; i++) {
            schemeDataArr2[i] = schemeDataArr[i].copyWithData(null);
        }
        return new DrmInitData(str, schemeDataArr2);
    }

    private static String getSegmentEncryptionIV(long j, String str, String str2) {
        if (str == null) {
            return null;
        }
        return str2 != null ? str2 : Long.toHexString(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    private static int parseSelectionFlags(String str) {
        boolean optionalBooleanAttribute = parseOptionalBooleanAttribute(str, REGEX_DEFAULT, false);
        ?? r0 = optionalBooleanAttribute;
        if (parseOptionalBooleanAttribute(str, REGEX_FORCED, false)) {
            r0 = (optionalBooleanAttribute ? 1 : 0) | 2;
        }
        return parseOptionalBooleanAttribute(str, REGEX_AUTOSELECT, false) ? r0 | 4 : r0;
    }

    private static int parseRoleFlags(String str, Map<String, String> map) {
        String optionalStringAttr = parseOptionalStringAttr(str, REGEX_CHARACTERISTICS, map);
        if (TextUtils.isEmpty(optionalStringAttr)) {
            return 0;
        }
        String[] strArrSplit = Util.split(optionalStringAttr, ",");
        int i = Util.contains(strArrSplit, "public.accessibility.describes-video") ? 512 : 0;
        if (Util.contains(strArrSplit, "public.accessibility.transcribes-spoken-dialog")) {
            i |= 4096;
        }
        if (Util.contains(strArrSplit, "public.accessibility.describes-music-and-sound")) {
            i |= 1024;
        }
        return Util.contains(strArrSplit, "public.easy-to-read") ? i | 8192 : i;
    }

    private static DrmInitData.SchemeData parseDrmSchemeData(String str, String str2, Map<String, String> map) throws ParserException {
        String optionalStringAttr = parseOptionalStringAttr(str, REGEX_KEYFORMATVERSIONS, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, map);
        if (KEYFORMAT_WIDEVINE_PSSH_BINARY.equals(str2)) {
            String stringAttr = parseStringAttr(str, REGEX_URI, map);
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "video/mp4", Base64.decode(stringAttr.substring(stringAttr.indexOf(44)), 0));
        }
        if (KEYFORMAT_WIDEVINE_PSSH_JSON.equals(str2)) {
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "hls", Util.getUtf8Bytes(str));
        }
        if (!KEYFORMAT_PLAYREADY.equals(str2) || !IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(optionalStringAttr)) {
            return null;
        }
        String stringAttr2 = parseStringAttr(str, REGEX_URI, map);
        return new DrmInitData.SchemeData(C.PLAYREADY_UUID, "video/mp4", PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(stringAttr2.substring(stringAttr2.indexOf(44)), 0)));
    }

    private static HlsMediaPlaylist.ServerControl parseServerControl(String str) {
        double optionalDoubleAttr = parseOptionalDoubleAttr(str, REGEX_CAN_SKIP_UNTIL, -9.223372036854776E18d);
        long j = C.TIME_UNSET;
        long j2 = optionalDoubleAttr == -9.223372036854776E18d ? -9223372036854775807L : (long) (optionalDoubleAttr * 1000000.0d);
        boolean optionalBooleanAttribute = parseOptionalBooleanAttribute(str, REGEX_CAN_SKIP_DATE_RANGES, false);
        double optionalDoubleAttr2 = parseOptionalDoubleAttr(str, REGEX_HOLD_BACK, -9.223372036854776E18d);
        long j3 = optionalDoubleAttr2 == -9.223372036854776E18d ? -9223372036854775807L : (long) (optionalDoubleAttr2 * 1000000.0d);
        double optionalDoubleAttr3 = parseOptionalDoubleAttr(str, REGEX_PART_HOLD_BACK, -9.223372036854776E18d);
        if (optionalDoubleAttr3 != -9.223372036854776E18d) {
            j = (long) (optionalDoubleAttr3 * 1000000.0d);
        }
        return new HlsMediaPlaylist.ServerControl(j2, optionalBooleanAttribute, j3, j, parseOptionalBooleanAttribute(str, REGEX_CAN_BLOCK_RELOAD, false));
    }

    private static String parseEncryptionScheme(String str) {
        if ("SAMPLE-AES-CENC".equals(str) || "SAMPLE-AES-CTR".equals(str)) {
            return C.CENC_TYPE_cenc;
        }
        return C.CENC_TYPE_cbcs;
    }

    private static int parseIntAttr(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static int parseOptionalIntAttr(String str, Pattern pattern, int i) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1))) : i;
    }

    private static long parseLongAttr(String str, Pattern pattern) throws ParserException {
        return Long.parseLong(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static long parseOptionalLongAttr(String str, Pattern pattern, long j) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Long.parseLong((String) Assertions.checkNotNull(matcher.group(1))) : j;
    }

    private static long parseTimeSecondsToUs(String str, Pattern pattern) throws ParserException {
        return new BigDecimal(parseStringAttr(str, pattern, Collections.emptyMap())).multiply(new BigDecimal(1000000L)).longValue();
    }

    private static double parseDoubleAttr(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static String parseStringAttr(String str, Pattern pattern, Map<String, String> map) throws ParserException {
        String optionalStringAttr = parseOptionalStringAttr(str, pattern, map);
        if (optionalStringAttr != null) {
            return optionalStringAttr;
        }
        throw ParserException.createForMalformedManifest("Couldn't match " + pattern.pattern() + " in " + str, null);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern, Map<String, String> map) {
        return parseOptionalStringAttr(str, pattern, null, map);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern, String str2, Map<String, String> map) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str2 = (String) Assertions.checkNotNull(matcher.group(1));
        }
        return (map.isEmpty() || str2 == null) ? str2 : replaceVariableReferences(str2, map);
    }

    private static double parseOptionalDoubleAttr(String str, Pattern pattern, double d) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Double.parseDouble((String) Assertions.checkNotNull(matcher.group(1))) : d;
    }

    private static HlsMediaPlaylist.ClientDefinedAttribute parseClientDefinedAttribute(String str, String str2, Map<String, String> map) throws ParserException {
        String str3 = str2 + "=";
        int iIndexOf = str.indexOf(str3) + str3.length();
        String strSubstring = str.substring(iIndexOf, (str.length() == iIndexOf + 1 ? 1 : 2) + iIndexOf);
        if (strSubstring.startsWith("\"")) {
            return new HlsMediaPlaylist.ClientDefinedAttribute(str2, parseStringAttr(str, Pattern.compile(str2 + "=\"((?:.|\f)+?)\""), map), 0);
        }
        if (strSubstring.equals("0x") || strSubstring.equals("0X")) {
            return new HlsMediaPlaylist.ClientDefinedAttribute(str2, parseStringAttr(str, Pattern.compile(str2 + "=(0[xX][A-F0-9]+)"), map), 1);
        }
        return new HlsMediaPlaylist.ClientDefinedAttribute(str2, parseDoubleAttr(str, Pattern.compile(str2 + "=([\\d\\.]+)\\b")));
    }

    private static String replaceVariableReferences(String str, Map<String, String> map) {
        Matcher matcher = REGEX_VARIABLE_REFERENCE.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            if (map.containsKey(strGroup)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(map.get(strGroup)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static boolean parseOptionalBooleanAttribute(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? BOOLEAN_TRUE.equals(matcher.group(1)) : z;
    }

    private static Pattern compileBooleanAttrPattern(String str) {
        return Pattern.compile(str + "=(NO|YES)");
    }

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        @EnsuresNonNullIf(expression = {"next"}, result = true)
        public boolean hasNext() throws IOException {
            String strTrim;
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = (String) Assertions.checkNotNull(this.extraLines.poll());
                return true;
            }
            do {
                String line = this.reader.readLine();
                this.next = line;
                if (line == null) {
                    return false;
                }
                strTrim = line.trim();
                this.next = strTrim;
            } while (strTrim.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (hasNext()) {
                String str = this.next;
                this.next = null;
                return str;
            }
            throw new NoSuchElementException();
        }
    }
}
