package com.google.android.exoplayer.dash.mpd;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer.upstream.UriLoadable;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.ParserUtil;
import com.google.android.exoplayer.util.UriUtil;
import com.google.android.exoplayer.util.Util;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MediaPresentationDescriptionParser extends DefaultHandler implements UriLoadable.Parser<MediaPresentationDescription> {
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final String TAG = "MediaPresentationDescriptionParser";
    private final String contentId;
    private final XmlPullParserFactory xmlParserFactory;

    protected void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public MediaPresentationDescriptionParser() {
        this(null);
    }

    public MediaPresentationDescriptionParser(String str) {
        this.contentId = str;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer.upstream.UriLoadable.Parser
    public MediaPresentationDescription parse(String str, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            if (xmlPullParserNewPullParser.next() != 2 || !"MPD".equals(xmlPullParserNewPullParser.getName())) {
                throw new ParserException("inputStream does not contain a valid media presentation description");
            }
            return parseMediaPresentationDescription(xmlPullParserNewPullParser, str);
        } catch (ParseException e) {
            throw new ParserException(e);
        } catch (XmlPullParserException e2) {
            throw new ParserException(e2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0072  */
    protected MediaPresentationDescription parseMediaPresentationDescription(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException, ParseException {
        String strNextText;
        UtcTimingElement utcTiming;
        long dateTime = parseDateTime(xmlPullParser, "availabilityStartTime", -1L);
        long duration = parseDuration(xmlPullParser, "mediaPresentationDuration", -1L);
        long duration2 = parseDuration(xmlPullParser, "minBufferTime", -1L);
        UtcTimingElement utcTimingElement = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "type");
        boolean zEquals = attributeValue != null ? attributeValue.equals("dynamic") : false;
        long duration3 = zEquals ? parseDuration(xmlPullParser, "minimumUpdatePeriod", -1L) : -1L;
        long duration4 = zEquals ? parseDuration(xmlPullParser, "timeShiftBufferDepth", -1L) : -1L;
        ArrayList arrayList = new ArrayList();
        String baseUrl = str;
        String str2 = null;
        long j = duration4;
        long j2 = zEquals ? -1L : 0L;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            xmlPullParser.next();
            UtcTimingElement utcTimingElement2 = utcTimingElement;
            z = true;
            if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (z) {
                    strNextText = str2;
                    baseUrl = baseUrl;
                    j2 = j2;
                    utcTiming = utcTimingElement2;
                } else {
                    baseUrl = parseBaseUrl(xmlPullParser, baseUrl);
                    utcTiming = utcTimingElement2;
                    strNextText = str2;
                    duration3 = duration3;
                }
            } else if (ParserUtil.isStartTag(xmlPullParser, "UTCTiming")) {
                strNextText = str2;
                duration3 = duration3;
                z = z;
                utcTiming = parseUtcTiming(xmlPullParser);
            } else {
                if (ParserUtil.isStartTag(xmlPullParser, HttpHeaders.LOCATION)) {
                    strNextText = xmlPullParser.nextText();
                } else if (!ParserUtil.isStartTag(xmlPullParser, "Period") || z2) {
                    strNextText = str2;
                    baseUrl = baseUrl;
                    j2 = j2;
                } else {
                    strNextText = str2;
                    Pair<Period, Long> period = parsePeriod(xmlPullParser, baseUrl, j2);
                    String str3 = baseUrl;
                    Period period2 = (Period) period.first;
                    long j3 = j2;
                    if (period2.startMs != -1) {
                        long jLongValue = ((Long) period.second).longValue();
                        long j4 = jLongValue == -1 ? -1L : period2.startMs + jLongValue;
                        arrayList.add(period2);
                        j2 = j4;
                        baseUrl = str3;
                    } else {
                        if (!zEquals) {
                            throw new ParserException("Unable to determine start of period " + arrayList.size());
                        }
                        baseUrl = str3;
                        j2 = j3;
                        z2 = true;
                    }
                }
                utcTiming = utcTimingElement2;
            }
            if (ParserUtil.isEndTag(xmlPullParser, "MPD")) {
                if (duration == -1) {
                    if (j2 != -1) {
                        duration = j2;
                    } else if (!zEquals) {
                        throw new ParserException("Unable to determine duration of static manifest.");
                    }
                }
                if (arrayList.isEmpty()) {
                    throw new ParserException("No periods found.");
                }
                return buildMediaPresentationDescription(dateTime, duration, duration2, zEquals, duration3, j, utcTiming, strNextText, arrayList);
            }
            utcTimingElement = utcTiming;
            z = z;
            str2 = strNextText;
            duration3 = duration3;
        }
    }

    protected MediaPresentationDescription buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, UtcTimingElement utcTimingElement, String str, List<Period> list) {
        return new MediaPresentationDescription(j, j2, j3, z, j4, j5, utcTimingElement, str, list);
    }

    protected UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue(null, "schemeIdUri"), xmlPullParser.getAttributeValue(null, "value"));
    }

    protected UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    protected Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        long duration = parseDuration(xmlPullParser, "start", j);
        long duration2 = parseDuration(xmlPullParser, "duration", -1L);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        SegmentBase segmentTemplate = null;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!z) {
                    str = parseBaseUrl(xmlPullParser, str);
                    z = true;
                }
            } else if (ParserUtil.isStartTag(xmlPullParser, "AdaptationSet")) {
                arrayList.add(parseAdaptationSet(xmlPullParser, str, segmentTemplate));
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentTemplate = parseSegmentBase(xmlPullParser, str, null);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentTemplate = parseSegmentList(xmlPullParser, str, null);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                segmentTemplate = parseSegmentTemplate(xmlPullParser, str, null);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "Period"));
        return Pair.create(buildPeriod(attributeValue, duration, arrayList), Long.valueOf(duration2));
    }

    protected Period buildPeriod(String str, long j, List<AdaptationSet> list) {
        return new Period(str, j, list);
    }

    protected AdaptationSet parseAdaptationSet(XmlPullParser xmlPullParser, String str, SegmentBase segmentBase) throws XmlPullParserException, IOException {
        String str2;
        ContentProtectionsBuilder contentProtectionsBuilder;
        String str3;
        String str4;
        int i;
        int i2;
        XmlPullParser xmlPullParser2;
        ArrayList arrayList;
        String str5;
        SegmentBase segmentTemplate;
        XmlPullParser xmlPullParser3 = xmlPullParser;
        int i3 = parseInt(xmlPullParser3, "id", -1);
        int contentType = parseContentType(xmlPullParser);
        String str6 = null;
        String attributeValue = xmlPullParser3.getAttributeValue(null, "mimeType");
        String attributeValue2 = xmlPullParser3.getAttributeValue(null, "codecs");
        int i4 = parseInt(xmlPullParser3, "width", -1);
        int i5 = parseInt(xmlPullParser3, "height", -1);
        float frameRate = parseFrameRate(xmlPullParser3, -1.0f);
        int i6 = parseInt(xmlPullParser3, "audioSamplingRate", -1);
        String str7 = "lang";
        String attributeValue3 = xmlPullParser3.getAttributeValue(null, "lang");
        ContentProtectionsBuilder contentProtectionsBuilder2 = new ContentProtectionsBuilder();
        ArrayList arrayList2 = new ArrayList();
        String baseUrl = str;
        SegmentBase segmentBase2 = segmentBase;
        int iCheckContentTypeConsistency = contentType;
        String strCheckLanguageConsistency = attributeValue3;
        int audioChannelConfiguration = -1;
        boolean z = false;
        while (true) {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser3, "BaseURL")) {
                if (z) {
                    str2 = strCheckLanguageConsistency;
                    str5 = baseUrl;
                    arrayList = arrayList2;
                    contentProtectionsBuilder = contentProtectionsBuilder2;
                    str3 = str7;
                    str4 = str6;
                    xmlPullParser2 = xmlPullParser3;
                    i = i3;
                    i2 = iCheckContentTypeConsistency;
                    baseUrl = str5;
                    iCheckContentTypeConsistency = i2;
                    strCheckLanguageConsistency = str2;
                } else {
                    baseUrl = parseBaseUrl(xmlPullParser3, baseUrl);
                    arrayList = arrayList2;
                    contentProtectionsBuilder = contentProtectionsBuilder2;
                    str3 = str7;
                    str4 = str6;
                    xmlPullParser2 = xmlPullParser3;
                    i = i3;
                    z = true;
                }
            } else if (ParserUtil.isStartTag(xmlPullParser3, "ContentProtection")) {
                ContentProtection contentProtection = parseContentProtection(xmlPullParser);
                if (contentProtection != null) {
                    contentProtectionsBuilder2.addAdaptationSetProtection(contentProtection);
                }
                str2 = strCheckLanguageConsistency;
                str5 = baseUrl;
                arrayList = arrayList2;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str7;
                str4 = str6;
                xmlPullParser2 = xmlPullParser3;
                i = i3;
                i2 = iCheckContentTypeConsistency;
                baseUrl = str5;
                iCheckContentTypeConsistency = i2;
                strCheckLanguageConsistency = str2;
            } else if (ParserUtil.isStartTag(xmlPullParser3, "ContentComponent")) {
                strCheckLanguageConsistency = checkLanguageConsistency(strCheckLanguageConsistency, xmlPullParser3.getAttributeValue(str6, str7));
                iCheckContentTypeConsistency = checkContentTypeConsistency(iCheckContentTypeConsistency, parseContentType(xmlPullParser));
                arrayList = arrayList2;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str7;
                str4 = str6;
                xmlPullParser2 = xmlPullParser3;
                i = i3;
            } else if (ParserUtil.isStartTag(xmlPullParser3, "Representation")) {
                String str8 = strCheckLanguageConsistency;
                i = i3;
                ArrayList arrayList3 = arrayList2;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str7;
                str4 = str6;
                Representation representation = parseRepresentation(xmlPullParser, baseUrl, attributeValue, attributeValue2, i4, i5, frameRate, audioChannelConfiguration, i6, str8, segmentBase2, contentProtectionsBuilder);
                contentProtectionsBuilder.endRepresentation();
                int iCheckContentTypeConsistency2 = checkContentTypeConsistency(iCheckContentTypeConsistency, getContentType(representation));
                arrayList3.add(representation);
                iCheckContentTypeConsistency = iCheckContentTypeConsistency2;
                arrayList = arrayList3;
                strCheckLanguageConsistency = str8;
                baseUrl = baseUrl;
                xmlPullParser2 = xmlPullParser;
            } else {
                str2 = strCheckLanguageConsistency;
                String str9 = baseUrl;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str7;
                str4 = str6;
                i = i3;
                i2 = iCheckContentTypeConsistency;
                xmlPullParser2 = xmlPullParser;
                arrayList = arrayList2;
                if (ParserUtil.isStartTag(xmlPullParser2, "AudioChannelConfiguration")) {
                    audioChannelConfiguration = parseAudioChannelConfiguration(xmlPullParser);
                    iCheckContentTypeConsistency = i2;
                    strCheckLanguageConsistency = str2;
                    baseUrl = str9;
                } else {
                    if (ParserUtil.isStartTag(xmlPullParser2, "SegmentBase")) {
                        str5 = str9;
                        segmentTemplate = parseSegmentBase(xmlPullParser2, str5, (SegmentBase.SingleSegmentBase) segmentBase2);
                    } else {
                        str5 = str9;
                        if (ParserUtil.isStartTag(xmlPullParser2, "SegmentList")) {
                            segmentTemplate = parseSegmentList(xmlPullParser2, str5, (SegmentBase.SegmentList) segmentBase2);
                        } else {
                            if (ParserUtil.isStartTag(xmlPullParser2, "SegmentTemplate")) {
                                segmentTemplate = parseSegmentTemplate(xmlPullParser2, str5, (SegmentBase.SegmentTemplate) segmentBase2);
                            } else if (ParserUtil.isStartTag(xmlPullParser)) {
                                parseAdaptationSetChild(xmlPullParser);
                            }
                            baseUrl = str5;
                            iCheckContentTypeConsistency = i2;
                            strCheckLanguageConsistency = str2;
                        }
                    }
                    segmentBase2 = segmentTemplate;
                    baseUrl = str5;
                    iCheckContentTypeConsistency = i2;
                    strCheckLanguageConsistency = str2;
                }
            }
            if (ParserUtil.isEndTag(xmlPullParser2, "AdaptationSet")) {
                return buildAdaptationSet(i, iCheckContentTypeConsistency, arrayList, contentProtectionsBuilder.build());
            }
            xmlPullParser3 = xmlPullParser2;
            arrayList2 = arrayList;
            i3 = i;
            contentProtectionsBuilder2 = contentProtectionsBuilder;
            str7 = str3;
            str6 = str4;
        }
    }

    protected AdaptationSet buildAdaptationSet(int i, int i2, List<Representation> list, List<ContentProtection> list2) {
        return new AdaptationSet(i, i2, list, list2);
    }

    protected int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if ("audio".equals(attributeValue)) {
            return 1;
        }
        if ("video".equals(attributeValue)) {
            return 0;
        }
        return "text".equals(attributeValue) ? 2 : -1;
    }

    protected int getContentType(Representation representation) {
        String str = representation.format.mimeType;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (MimeTypes.isVideo(str)) {
            return 0;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        if (MimeTypes.isText(str) || "application/ttml+xml".equals(str)) {
            return 2;
        }
        if ("application/mp4".equals(str)) {
            String str2 = representation.format.codecs;
            if ("stpp".equals(str2) || "wvtt".equals(str2)) {
                return 2;
            }
        }
        return -1;
    }

    protected ContentProtection parseContentProtection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "schemeIdUri");
        UUID uuid = null;
        DrmInitData.SchemeInitData schemeInitData = null;
        boolean z = false;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "cenc:pssh") && xmlPullParser.next() == 4) {
                schemeInitData = new DrmInitData.SchemeInitData("video/mp4", Base64.decode(xmlPullParser.getText(), 0));
                uuid = PsshAtomUtil.parseUuid(schemeInitData.data);
                z = true;
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "ContentProtection"));
        if (z && uuid == null) {
            Log.w(TAG, "Skipped unsupported ContentProtection element");
            return null;
        }
        return buildContentProtection(attributeValue, uuid, schemeInitData);
    }

    protected ContentProtection buildContentProtection(String str, UUID uuid, DrmInitData.SchemeInitData schemeInitData) {
        return new ContentProtection(str, uuid, schemeInitData);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00d5 A[LOOP:0: B:3:0x0047->B:34:0x00d5, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:35:0x00bc A[EDGE_INSN: B:35:0x00bc->B:28:0x00bc BREAK  A[LOOP:0: B:3:0x0047->B:34:0x00d5], SYNTHETIC] */
    protected Representation parseRepresentation(XmlPullParser xmlPullParser, String str, String str2, String str3, int i, int i2, float f, int i3, int i4, String str4, SegmentBase segmentBase, ContentProtectionsBuilder contentProtectionsBuilder) throws XmlPullParserException, IOException {
        ContentProtection contentProtection;
        String baseUrl;
        boolean z;
        SegmentBase singleSegmentBase;
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        int i5 = parseInt(xmlPullParser, "bandwidth");
        String string = parseString(xmlPullParser, "mimeType", str2);
        String string2 = parseString(xmlPullParser, "codecs", str3);
        int i6 = parseInt(xmlPullParser, "width", i);
        int i7 = parseInt(xmlPullParser, "height", i2);
        float frameRate = parseFrameRate(xmlPullParser, f);
        int i8 = parseInt(xmlPullParser, "audioSamplingRate", i4);
        String str5 = str;
        int audioChannelConfiguration = i3;
        SegmentBase segmentTemplate = segmentBase;
        boolean z2 = false;
        while (true) {
            xmlPullParser.next();
            if (!ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (ParserUtil.isStartTag(xmlPullParser, "AudioChannelConfiguration")) {
                    audioChannelConfiguration = parseAudioChannelConfiguration(xmlPullParser);
                } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                    segmentTemplate = parseSegmentBase(xmlPullParser, str5, (SegmentBase.SingleSegmentBase) segmentTemplate);
                } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                    segmentTemplate = parseSegmentList(xmlPullParser, str5, (SegmentBase.SegmentList) segmentTemplate);
                } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                    segmentTemplate = parseSegmentTemplate(xmlPullParser, str5, (SegmentBase.SegmentTemplate) segmentTemplate);
                } else {
                    if (ParserUtil.isStartTag(xmlPullParser, "ContentProtection") && (contentProtection = parseContentProtection(xmlPullParser)) != null) {
                        contentProtectionsBuilder.addAdaptationSetProtection(contentProtection);
                    }
                    SegmentBase segmentBase2 = segmentTemplate;
                    baseUrl = str5;
                    z = z2;
                    singleSegmentBase = segmentBase2;
                    if (ParserUtil.isEndTag(xmlPullParser, "Representation")) {
                        break;
                        break;
                    }
                    SegmentBase segmentBase3 = singleSegmentBase;
                    z2 = z;
                    str5 = baseUrl;
                    segmentTemplate = segmentBase3;
                }
            } else {
                if (!z2) {
                    singleSegmentBase = segmentTemplate;
                    baseUrl = parseBaseUrl(xmlPullParser, str5);
                    z = true;
                }
                if (ParserUtil.isEndTag(xmlPullParser, "Representation")) {
                    break;
                }
                SegmentBase segmentBase4 = singleSegmentBase;
                z2 = z;
                str5 = baseUrl;
                segmentTemplate = segmentBase4;
            }
            SegmentBase segmentBase5 = segmentTemplate;
            baseUrl = str5;
            z = z2;
            singleSegmentBase = segmentBase5;
            if (ParserUtil.isEndTag(xmlPullParser, "Representation")) {
                break;
                break;
            }
            SegmentBase segmentBase6 = singleSegmentBase;
            z2 = z;
            str5 = baseUrl;
            segmentTemplate = segmentBase6;
        }
        Format formatBuildFormat = buildFormat(attributeValue, string, i6, i7, frameRate, audioChannelConfiguration, i8, i5, str4, string2);
        String str6 = this.contentId;
        if (singleSegmentBase == null) {
            singleSegmentBase = new SegmentBase.SingleSegmentBase(baseUrl);
        }
        return buildRepresentation(str6, -1, formatBuildFormat, singleSegmentBase);
    }

    protected Format buildFormat(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, String str4) {
        return new Format(str, str2, i, i2, f, i3, i4, i5, str3, str4);
    }

    protected Representation buildRepresentation(String str, int i, Format format, SegmentBase segmentBase) {
        return Representation.newInstance(str, i, format, segmentBase);
    }

    protected SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, String str, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j;
        long j2;
        long j3 = parseLong(xmlPullParser, "timescale", singleSegmentBase != null ? singleSegmentBase.timescale : 1L);
        long j4 = parseLong(xmlPullParser, "presentationTimeOffset", singleSegmentBase != null ? singleSegmentBase.presentationTimeOffset : 0L);
        long j5 = singleSegmentBase != null ? singleSegmentBase.indexStart : 0L;
        long j6 = singleSegmentBase != null ? singleSegmentBase.indexLength : -1L;
        String attributeValue = xmlPullParser.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] strArrSplit = attributeValue.split("-");
            long j7 = Long.parseLong(strArrSplit[0]);
            j = j7;
            j2 = (Long.parseLong(strArrSplit[1]) - j7) + 1;
        } else {
            j = j5;
            j2 = j6;
        }
        RangedUri initialization = singleSegmentBase != null ? singleSegmentBase.initialization : null;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser, str);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentBase"));
        return buildSingleSegmentBase(initialization, j3, j4, str, j, j2);
    }

    protected SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, String str, long j3, long j4) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j, j2, str, j3, j4);
    }

    protected SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, String str, SegmentBase.SegmentList segmentList) throws XmlPullParserException, IOException {
        long j = parseLong(xmlPullParser, "timescale", segmentList != null ? segmentList.timescale : 1L);
        long j2 = parseLong(xmlPullParser, "presentationTimeOffset", segmentList != null ? segmentList.presentationTimeOffset : 0L);
        long j3 = parseLong(xmlPullParser, "duration", segmentList != null ? segmentList.duration : -1L);
        int i = parseInt(xmlPullParser, "startNumber", segmentList != null ? segmentList.startNumber : 1);
        List<RangedUri> arrayList = null;
        RangedUri initialization = null;
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser, str);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                segmentTimeline = parseSegmentTimeline(xmlPullParser);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentURL")) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(parseSegmentUrl(xmlPullParser, str));
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentList"));
        if (segmentList != null) {
            if (initialization == null) {
                initialization = segmentList.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentList.segmentTimeline;
            }
            if (arrayList == null) {
                arrayList = segmentList.mediaSegments;
            }
        }
        return buildSegmentList(initialization, j, j2, i, j3, segmentTimeline, arrayList);
    }

    protected SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentBase.SegmentTimelineElement> list, List<RangedUri> list2) {
        return new SegmentBase.SegmentList(rangedUri, j, j2, i, j3, list, list2);
    }

    protected SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, String str, SegmentBase.SegmentTemplate segmentTemplate) throws XmlPullParserException, IOException {
        long j = parseLong(xmlPullParser, "timescale", segmentTemplate != null ? segmentTemplate.timescale : 1L);
        long j2 = parseLong(xmlPullParser, "presentationTimeOffset", segmentTemplate != null ? segmentTemplate.presentationTimeOffset : 0L);
        long j3 = parseLong(xmlPullParser, "duration", segmentTemplate != null ? segmentTemplate.duration : -1L);
        int i = parseInt(xmlPullParser, "startNumber", segmentTemplate != null ? segmentTemplate.startNumber : 1);
        RangedUri initialization = null;
        UrlTemplate urlTemplate = parseUrlTemplate(xmlPullParser, "media", segmentTemplate != null ? segmentTemplate.mediaTemplate : null);
        UrlTemplate urlTemplate2 = parseUrlTemplate(xmlPullParser, "initialization", segmentTemplate != null ? segmentTemplate.initializationTemplate : null);
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser, str);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                segmentTimeline = parseSegmentTimeline(xmlPullParser);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentTemplate"));
        if (segmentTemplate != null) {
            if (initialization == null) {
                initialization = segmentTemplate.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentTemplate.segmentTimeline;
            }
        }
        return buildSegmentTemplate(initialization, j, j2, i, j3, segmentTimeline, urlTemplate2, urlTemplate, str);
    }

    protected SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentBase.SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2, String str) {
        return new SegmentBase.SegmentTemplate(rangedUri, j, j2, i, j3, list, urlTemplate, urlTemplate2, str);
    }

    protected List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, ExifInterface.LATITUDE_SOUTH)) {
                j = parseLong(xmlPullParser, "t", j);
                long j2 = parseLong(xmlPullParser, "d");
                int i = parseInt(xmlPullParser, "r", 0) + 1;
                for (int i2 = 0; i2 < i; i2++) {
                    arrayList.add(buildSegmentTimelineElement(j, j2));
                    j += j2;
                }
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    protected SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long j, long j2) {
        return new SegmentBase.SegmentTimelineElement(j, j2);
    }

    protected UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    protected RangedUri parseInitialization(XmlPullParser xmlPullParser, String str) {
        return parseRangedUrl(xmlPullParser, str, "sourceURL", "range");
    }

    protected RangedUri parseSegmentUrl(XmlPullParser xmlPullParser, String str) {
        return parseRangedUrl(xmlPullParser, str, "media", "mediaRange");
    }

    protected RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2, String str3) {
        long j;
        String attributeValue = xmlPullParser.getAttributeValue(null, str2);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str3);
        long j2 = -1;
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split("-");
            j = Long.parseLong(strArrSplit[0]);
            if (strArrSplit.length == 2) {
                j2 = (Long.parseLong(strArrSplit[1]) - j) + 1;
            }
        } else {
            j = 0;
        }
        return buildRangedUri(str, attributeValue, j, j2);
    }

    protected RangedUri buildRangedUri(String str, String str2, long j, long j2) {
        return new RangedUri(str, str2, j, j2);
    }

    protected int parseAudioChannelConfiguration(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = "urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString(xmlPullParser, "schemeIdUri", null)) ? parseInt(xmlPullParser, "value") : -1;
        do {
            xmlPullParser.next();
        } while (!ParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int i = Integer.parseInt(matcher.group(1));
        String strGroup = matcher.group(2);
        return !TextUtils.isEmpty(strGroup) ? i / Integer.parseInt(strGroup) : i;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParseException {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Util.parseXsDateTime(attributeValue);
    }

    protected static String parseBaseUrl(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return UriUtil.resolve(str, xmlPullParser.getText());
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str) {
        return parseInt(xmlPullParser, str, -1);
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str) {
        return parseLong(xmlPullParser, str, -1L);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    protected static final class ContentProtectionsBuilder implements Comparator<ContentProtection> {
        private ArrayList<ContentProtection> adaptationSetProtections;
        private ArrayList<ContentProtection> currentRepresentationProtections;
        private ArrayList<ContentProtection> representationProtections;
        private boolean representationProtectionsSet;

        protected ContentProtectionsBuilder() {
        }

        public void addAdaptationSetProtection(ContentProtection contentProtection) {
            if (this.adaptationSetProtections == null) {
                this.adaptationSetProtections = new ArrayList<>();
            }
            maybeAddContentProtection(this.adaptationSetProtections, contentProtection);
        }

        public void addRepresentationProtection(ContentProtection contentProtection) {
            if (this.currentRepresentationProtections == null) {
                this.currentRepresentationProtections = new ArrayList<>();
            }
            maybeAddContentProtection(this.currentRepresentationProtections, contentProtection);
        }

        public void endRepresentation() {
            if (!this.representationProtectionsSet) {
                ArrayList<ContentProtection> arrayList = this.currentRepresentationProtections;
                if (arrayList != null) {
                    Collections.sort(arrayList, this);
                }
                this.representationProtections = this.currentRepresentationProtections;
                this.representationProtectionsSet = true;
            } else {
                ArrayList<ContentProtection> arrayList2 = this.currentRepresentationProtections;
                if (arrayList2 == null) {
                    Assertions.checkState(this.representationProtections == null);
                } else {
                    Collections.sort(arrayList2, this);
                    Assertions.checkState(this.currentRepresentationProtections.equals(this.representationProtections));
                }
            }
            this.currentRepresentationProtections = null;
        }

        public ArrayList<ContentProtection> build() {
            ArrayList<ContentProtection> arrayList = this.adaptationSetProtections;
            if (arrayList == null) {
                return this.representationProtections;
            }
            if (this.representationProtections == null) {
                return arrayList;
            }
            for (int i = 0; i < this.representationProtections.size(); i++) {
                maybeAddContentProtection(this.adaptationSetProtections, this.representationProtections.get(i));
            }
            return this.adaptationSetProtections;
        }

        private void maybeAddContentProtection(List<ContentProtection> list, ContentProtection contentProtection) {
            if (list.contains(contentProtection)) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                Assertions.checkState(!list.get(i).schemeUriId.equals(contentProtection.schemeUriId));
            }
            list.add(contentProtection);
        }

        @Override // java.util.Comparator
        public int compare(ContentProtection contentProtection, ContentProtection contentProtection2) {
            return contentProtection.schemeUriId.compareTo(contentProtection2.schemeUriId);
        }
    }
}
