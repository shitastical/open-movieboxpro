package com.google.android.exoplayer.hls;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.upstream.UriLoadable;
import j$.util.DesugarCollections;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class HlsPlaylistParser implements UriLoadable.Parser<HlsPlaylist> {
    private static final String AUDIO_TYPE = "AUDIO";
    private static final String BANDWIDTH_ATTR = "BANDWIDTH";
    private static final String BYTERANGE_TAG = "#EXT-X-BYTERANGE";
    private static final String CLOSED_CAPTIONS_TYPE = "CLOSED-CAPTIONS";
    private static final String CODECS_ATTR = "CODECS";
    private static final String DISCONTINUITY_SEQUENCE_TAG = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String DISCONTINUITY_TAG = "#EXT-X-DISCONTINUITY";
    private static final String ENDLIST_TAG = "#EXT-X-ENDLIST";
    private static final String INSTREAM_ID_ATTR = "INSTREAM-ID";
    private static final String IV_ATTR = "IV";
    private static final String KEY_TAG = "#EXT-X-KEY";
    private static final String LANGUAGE_ATTR = "LANGUAGE";
    private static final String MEDIA_DURATION_TAG = "#EXTINF";
    private static final String MEDIA_SEQUENCE_TAG = "#EXT-X-MEDIA-SEQUENCE";
    private static final String MEDIA_TAG = "#EXT-X-MEDIA";
    private static final String METHOD_AES128 = "AES-128";
    private static final String METHOD_ATTR = "METHOD";
    private static final String METHOD_NONE = "NONE";
    private static final String NAME_ATTR = "NAME";
    private static final String RESOLUTION_ATTR = "RESOLUTION";
    private static final String STREAM_INF_TAG = "#EXT-X-STREAM-INF";
    private static final String SUBTITLES_TYPE = "SUBTITLES";
    private static final String TARGET_DURATION_TAG = "#EXT-X-TARGETDURATION";
    private static final String TYPE_ATTR = "TYPE";
    private static final String URI_ATTR = "URI";
    private static final String VERSION_TAG = "#EXT-X-VERSION";
    private static final String VIDEO_TYPE = "VIDEO";
    private static final Pattern BANDWIDTH_ATTR_REGEX = Pattern.compile("BANDWIDTH=(\\d+)\\b");
    private static final Pattern CODECS_ATTR_REGEX = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern RESOLUTION_ATTR_REGEX = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern MEDIA_DURATION_REGEX = Pattern.compile("#EXTINF:([\\d.]+)\\b");
    private static final Pattern MEDIA_SEQUENCE_REGEX = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern TARGET_DURATION_REGEX = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern VERSION_REGEX = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern BYTERANGE_REGEX = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern METHOD_ATTR_REGEX = Pattern.compile("METHOD=(NONE|AES-128)");
    private static final Pattern URI_ATTR_REGEX = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern IV_ATTR_REGEX = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern TYPE_ATTR_REGEX = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern LANGUAGE_ATTR_REGEX = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern NAME_ATTR_REGEX = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern INSTREAM_ID_ATTR_REGEX = Pattern.compile("INSTREAM-ID=\"(.+?)\"");

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer.upstream.UriLoadable.Parser
    public HlsPlaylist parse(String str, InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        LinkedList linkedList = new LinkedList();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    String strTrim = line.trim();
                    if (!strTrim.isEmpty()) {
                        if (strTrim.startsWith("#EXT-X-STREAM-INF")) {
                            linkedList.add(strTrim);
                            HlsMasterPlaylist masterPlaylist = parseMasterPlaylist(new LineIterator(linkedList, bufferedReader), str);
                            bufferedReader.close();
                            return masterPlaylist;
                        }
                        if (!strTrim.startsWith("#EXT-X-TARGETDURATION") && !strTrim.startsWith("#EXT-X-MEDIA-SEQUENCE") && !strTrim.startsWith("#EXTINF") && !strTrim.startsWith("#EXT-X-KEY") && !strTrim.startsWith(BYTERANGE_TAG) && !strTrim.equals("#EXT-X-DISCONTINUITY") && !strTrim.equals(DISCONTINUITY_SEQUENCE_TAG) && !strTrim.equals("#EXT-X-ENDLIST")) {
                            linkedList.add(strTrim);
                        }
                        linkedList.add(strTrim);
                        HlsMediaPlaylist mediaPlaylist = parseMediaPlaylist(new LineIterator(linkedList, bufferedReader), str);
                        bufferedReader.close();
                        return mediaPlaylist;
                    }
                } else {
                    bufferedReader.close();
                    throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                }
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        }
    }

    private static HlsMasterPlaylist parseMasterPlaylist(LineIterator lineIterator, String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        String str2 = null;
        String optionalStringAttr = null;
        while (true) {
            boolean z = false;
            String optionalStringAttr2 = null;
            int i = -1;
            int i2 = -1;
            int intAttr = 0;
            String optionalStringAttr3 = null;
            while (lineIterator.hasNext()) {
                String next = lineIterator.next();
                if (next.startsWith(MEDIA_TAG)) {
                    String stringAttr = HlsParserUtil.parseStringAttr(next, TYPE_ATTR_REGEX, TYPE_ATTR);
                    if (CLOSED_CAPTIONS_TYPE.equals(stringAttr)) {
                        if ("CC1".equals(HlsParserUtil.parseStringAttr(next, INSTREAM_ID_ATTR_REGEX, INSTREAM_ID_ATTR))) {
                            optionalStringAttr = HlsParserUtil.parseOptionalStringAttr(next, LANGUAGE_ATTR_REGEX);
                        }
                    } else if (SUBTITLES_TYPE.equals(stringAttr)) {
                        arrayList3.add(new Variant(HlsParserUtil.parseStringAttr(next, URI_ATTR_REGEX, URI_ATTR), new Format(HlsParserUtil.parseStringAttr(next, NAME_ATTR_REGEX, NAME_ATTR), "application/x-mpegURL", -1, -1, -1.0f, -1, -1, -1, HlsParserUtil.parseOptionalStringAttr(next, LANGUAGE_ATTR_REGEX), optionalStringAttr2)));
                    } else if (AUDIO_TYPE.equals(stringAttr)) {
                        String optionalStringAttr4 = HlsParserUtil.parseOptionalStringAttr(next, LANGUAGE_ATTR_REGEX);
                        String optionalStringAttr5 = HlsParserUtil.parseOptionalStringAttr(next, URI_ATTR_REGEX);
                        if (optionalStringAttr5 != null) {
                            arrayList2.add(new Variant(optionalStringAttr5, new Format(HlsParserUtil.parseStringAttr(next, NAME_ATTR_REGEX, NAME_ATTR), "application/x-mpegURL", -1, -1, -1.0f, -1, -1, -1, optionalStringAttr4, optionalStringAttr2)));
                        } else {
                            str2 = optionalStringAttr4;
                        }
                    }
                } else if (next.startsWith("#EXT-X-STREAM-INF")) {
                    intAttr = HlsParserUtil.parseIntAttr(next, BANDWIDTH_ATTR_REGEX, BANDWIDTH_ATTR);
                    optionalStringAttr2 = HlsParserUtil.parseOptionalStringAttr(next, CODECS_ATTR_REGEX);
                    optionalStringAttr3 = HlsParserUtil.parseOptionalStringAttr(next, NAME_ATTR_REGEX);
                    String optionalStringAttr6 = HlsParserUtil.parseOptionalStringAttr(next, RESOLUTION_ATTR_REGEX);
                    z = true;
                    if (optionalStringAttr6 != null) {
                        String[] strArrSplit = optionalStringAttr6.split("x");
                        int i3 = Integer.parseInt(strArrSplit[0]);
                        if (i3 <= 0) {
                            i3 = -1;
                        }
                        int i4 = Integer.parseInt(strArrSplit[1]);
                        if (i4 <= 0) {
                            i4 = -1;
                        }
                        i2 = i4;
                        i = i3;
                    } else {
                        i = -1;
                        i2 = -1;
                    }
                } else if (!next.startsWith("#") && z) {
                    arrayList.add(new Variant(next, new Format(optionalStringAttr3 == null ? Integer.toString(arrayList.size()) : optionalStringAttr3, "application/x-mpegURL", i, i2, -1.0f, -1, -1, intAttr, null, optionalStringAttr2)));
                }
            }
            return new HlsMasterPlaylist(str, arrayList, arrayList2, arrayList3, str2, optionalStringAttr);
        }
    }

    private static HlsMediaPlaylist parseMediaPlaylist(LineIterator lineIterator, String str) throws IOException {
        String hexString;
        ArrayList arrayList = new ArrayList();
        char c = 0;
        int i = 1;
        long j = -1;
        String optionalStringAttr = null;
        String str2 = null;
        int i2 = 0;
        boolean zEquals = false;
        int i3 = 0;
        int intAttr = 0;
        int intAttr2 = 1;
        boolean z = true;
        int intAttr3 = 0;
        long j2 = 0;
        double doubleAttr = 0.0d;
        long j3 = 0;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            if (next.startsWith("#EXT-X-TARGETDURATION")) {
                intAttr = HlsParserUtil.parseIntAttr(next, TARGET_DURATION_REGEX, "#EXT-X-TARGETDURATION");
            } else if (next.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                intAttr3 = HlsParserUtil.parseIntAttr(next, MEDIA_SEQUENCE_REGEX, "#EXT-X-MEDIA-SEQUENCE");
                i3 = intAttr3;
            } else if (next.startsWith("#EXT-X-VERSION")) {
                intAttr2 = HlsParserUtil.parseIntAttr(next, VERSION_REGEX, "#EXT-X-VERSION");
            } else if (next.startsWith("#EXTINF")) {
                doubleAttr = HlsParserUtil.parseDoubleAttr(next, MEDIA_DURATION_REGEX, "#EXTINF");
            } else if (next.startsWith("#EXT-X-KEY")) {
                zEquals = "AES-128".equals(HlsParserUtil.parseStringAttr(next, METHOD_ATTR_REGEX, METHOD_ATTR));
                if (zEquals) {
                    String stringAttr = HlsParserUtil.parseStringAttr(next, URI_ATTR_REGEX, URI_ATTR);
                    optionalStringAttr = HlsParserUtil.parseOptionalStringAttr(next, IV_ATTR_REGEX);
                    str2 = stringAttr;
                } else {
                    optionalStringAttr = null;
                    str2 = null;
                }
            } else if (next.startsWith(BYTERANGE_TAG)) {
                String[] strArrSplit = HlsParserUtil.parseStringAttr(next, BYTERANGE_REGEX, BYTERANGE_TAG).split("@");
                j = Long.parseLong(strArrSplit[c]);
                if (strArrSplit.length > i) {
                    j2 = Long.parseLong(strArrSplit[i]);
                }
            } else if (next.startsWith(DISCONTINUITY_SEQUENCE_TAG)) {
                i2 = Integer.parseInt(next.substring(next.indexOf(58) + i));
            } else if (next.equals("#EXT-X-DISCONTINUITY")) {
                i2++;
            } else if (!next.startsWith("#")) {
                if (zEquals) {
                    hexString = optionalStringAttr != null ? optionalStringAttr : Integer.toHexString(intAttr3);
                } else {
                    hexString = null;
                }
                int i4 = intAttr3 + 1;
                long j4 = j == -1 ? 0L : j2;
                arrayList.add(new HlsMediaPlaylist.Segment(next, doubleAttr, i2, j3, zEquals, str2, hexString, j4, j));
                j3 += (long) (doubleAttr * 1000000.0d);
                if (j != -1) {
                    j4 += j;
                }
                j2 = j4;
                intAttr3 = i4;
                j = -1;
                c = 0;
                i = 1;
                doubleAttr = 0.0d;
            } else if (next.equals("#EXT-X-ENDLIST")) {
                c = 0;
                i = 1;
                z = false;
            } else {
                c = 0;
                i = 1;
            }
        }
        return new HlsMediaPlaylist(str, i3, intAttr, intAttr2, z, DesugarCollections.unmodifiableList(arrayList));
    }

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        public boolean hasNext() throws IOException {
            String strTrim;
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = this.extraLines.poll();
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
            if (!hasNext()) {
                return null;
            }
            String str = this.next;
            this.next = null;
            return str;
        }
    }
}
