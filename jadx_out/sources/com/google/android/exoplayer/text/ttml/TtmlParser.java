package com.google.android.exoplayer.text.ttml;

import android.text.Layout;
import android.util.Log;
import android.util.Pair;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.ParserUtil;
import com.google.android.exoplayer.util.Util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class TtmlParser implements SubtitleParser {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final String TAG = "TtmlParser";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);

    public TtmlParser() {
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = xmlPullParserFactoryNewInstance;
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        return "application/ttml+xml".equals(str);
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public TtmlSubtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            map2.put("", new TtmlRegion());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            TtmlSubtitle ttmlSubtitle = null;
            xmlPullParserNewPullParser.setInput(byteArrayInputStream, null);
            LinkedList linkedList = new LinkedList();
            FrameAndTickRate frameAndTickRates = DEFAULT_FRAME_AND_TICK_RATE;
            int i3 = 0;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) linkedList.peekLast();
                if (i3 == 0) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (eventType == 2) {
                        if ("tt".equals(name)) {
                            frameAndTickRates = parseFrameAndTickRates(xmlPullParserNewPullParser);
                        }
                        if (!isSupportedTag(name)) {
                            Log.i(TAG, "Ignoring unsupported tag: " + xmlPullParserNewPullParser.getName());
                        } else if ("head".equals(name)) {
                            parseHeader(xmlPullParserNewPullParser, map, map2);
                        } else {
                            try {
                                TtmlNode node = parseNode(xmlPullParserNewPullParser, ttmlNode, map2, frameAndTickRates);
                                linkedList.addLast(node);
                                if (ttmlNode != null) {
                                    ttmlNode.addChild(node);
                                }
                            } catch (ParserException e) {
                                Log.w(TAG, "Suppressing parser error", e);
                                i3++;
                            }
                        }
                        i3++;
                    } else if (eventType == 4) {
                        ttmlNode.addChild(TtmlNode.buildTextNode(xmlPullParserNewPullParser.getText()));
                    } else if (eventType == 3) {
                        if (xmlPullParserNewPullParser.getName().equals("tt")) {
                            ttmlSubtitle = new TtmlSubtitle((TtmlNode) linkedList.getLast(), map, map2);
                        }
                        linkedList.removeLast();
                    }
                } else if (eventType == 2) {
                    i3++;
                } else if (eventType == 3) {
                    i3--;
                }
                xmlPullParserNewPullParser.next();
            }
            return ttmlSubtitle;
        } catch (IOException e2) {
            throw new IllegalStateException("Unexpected error when reading input.", e2);
        } catch (XmlPullParserException e3) {
            throw new ParserException("Unable to parse source", e3);
        }
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws ParserException {
        float f;
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int i = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split(" ");
            if (strArrSplit.length != 2) {
                throw new ParserException("frameRateMultiplier doesn't have 2 parts");
            }
            f = Integer.parseInt(strArrSplit[0]) / Integer.parseInt(strArrSplit[1]);
        } else {
            f = 1.0f;
        }
        FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
        int i2 = frameAndTickRate.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i2 = Integer.parseInt(attributeValue3);
        }
        int i3 = frameAndTickRate.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i3 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(i * f, i2, i3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2) throws XmlPullParserException, IOException {
        Pair<String, TtmlRegion> regionAttributes;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = ParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        styleAttributes.chain(map.get(str));
                    }
                }
                if (styleAttributes.getId() != null) {
                    map.put(styleAttributes.getId(), styleAttributes);
                }
            } else if (ParserUtil.isStartTag(xmlPullParser, "region") && (regionAttributes = parseRegionAttributes(xmlPullParser)) != null) {
                map2.put(regionAttributes.first, regionAttributes.second);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "head"));
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0085  */
    private Pair<String, TtmlRegion> parseRegionAttributes(XmlPullParser xmlPullParser) {
        float f;
        float f2;
        float f3;
        String attributeValue = ParserUtil.getAttributeValue(xmlPullParser, "id");
        String attributeValue2 = ParserUtil.getAttributeValue(xmlPullParser, "origin");
        String attributeValue3 = ParserUtil.getAttributeValue(xmlPullParser, "extent");
        if (attributeValue2 == null || attributeValue == null) {
            return null;
        }
        Matcher matcher = PERCENTAGE_COORDINATES.matcher(attributeValue2);
        if (matcher.matches()) {
            try {
                f = Float.parseFloat(matcher.group(1)) / 100.0f;
                f2 = Float.parseFloat(matcher.group(2)) / 100.0f;
            } catch (NumberFormatException e) {
                Log.w(TAG, "Ignoring region with malformed origin: '" + attributeValue2 + "'", e);
                f2 = Float.MIN_VALUE;
                f = Float.MIN_VALUE;
            }
        } else {
            f2 = Float.MIN_VALUE;
            f = Float.MIN_VALUE;
        }
        if (attributeValue3 != null) {
            Matcher matcher2 = PERCENTAGE_COORDINATES.matcher(attributeValue3);
            if (matcher2.matches()) {
                try {
                    f3 = Float.parseFloat(matcher2.group(1)) / 100.0f;
                } catch (NumberFormatException e2) {
                    Log.w(TAG, "Ignoring malformed region extent: '" + attributeValue3 + "'", e2);
                    f3 = Float.MIN_VALUE;
                }
            } else {
                f3 = Float.MIN_VALUE;
            }
        } else {
            f3 = Float.MIN_VALUE;
        }
        if (f != Float.MIN_VALUE) {
            return new Pair<>(attributeValue, new TtmlRegion(f, f2, 0, f3));
        }
        return null;
    }

    private String[] parseStyleIds(String str) {
        return str.split("\\s+");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:63:0x0128  */
    /* JADX WARN: Code duplicated, block: B:6:0x001f  */
    /* JADX WARN: Code duplicated, block: B:87:0x018e  */
    private TtmlStyle parseStyleAttributes(XmlPullParser xmlPullParser, TtmlStyle ttmlStyle) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeValue = xmlPullParser.getAttributeValue(i);
            String attributeName = xmlPullParser.getAttributeName(i);
            attributeName.hashCode();
            switch (attributeName) {
                case "fontStyle":
                    ttmlStyle = createIfNull(ttmlStyle).setItalic("italic".equalsIgnoreCase(attributeValue));
                    break;
                case "fontFamily":
                    ttmlStyle = createIfNull(ttmlStyle).setFontFamily(attributeValue);
                    break;
                case "textAlign":
                    String lowerInvariant = Util.toLowerInvariant(attributeValue);
                    lowerInvariant.hashCode();
                    switch (lowerInvariant) {
                        case "center":
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_CENTER);
                            break;
                        case "end":
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                            break;
                        case "left":
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                            break;
                        case "right":
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                            break;
                        case "start":
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                            break;
                    }
                    break;
                case "textDecoration":
                    String lowerInvariant2 = Util.toLowerInvariant(attributeValue);
                    lowerInvariant2.hashCode();
                    switch (lowerInvariant2) {
                        case "nounderline":
                            ttmlStyle = createIfNull(ttmlStyle).setUnderline(false);
                            break;
                        case "underline":
                            ttmlStyle = createIfNull(ttmlStyle).setUnderline(true);
                            break;
                        case "nolinethrough":
                            ttmlStyle = createIfNull(ttmlStyle).setLinethrough(false);
                            break;
                        case "linethrough":
                            ttmlStyle = createIfNull(ttmlStyle).setLinethrough(true);
                            break;
                    }
                    break;
                case "fontWeight":
                    ttmlStyle = createIfNull(ttmlStyle).setBold("bold".equalsIgnoreCase(attributeValue));
                    break;
                case "id":
                    if ("style".equals(xmlPullParser.getName())) {
                        ttmlStyle = createIfNull(ttmlStyle).setId(attributeValue);
                        break;
                    } else {
                        break;
                    }
                    break;
                case "color":
                    ttmlStyle = createIfNull(ttmlStyle);
                    try {
                        ttmlStyle.setFontColor(TtmlColorParser.parseColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused) {
                        Log.w(TAG, "failed parsing color value: '" + attributeValue + "'");
                        break;
                    }
                    break;
                case "fontSize":
                    try {
                        ttmlStyle = createIfNull(ttmlStyle);
                        parseFontSize(attributeValue, ttmlStyle);
                        break;
                    } catch (ParserException unused2) {
                        Log.w(TAG, "failed parsing fontSize value: '" + attributeValue + "'");
                        break;
                    }
                    break;
                case "backgroundColor":
                    ttmlStyle = createIfNull(ttmlStyle);
                    try {
                        ttmlStyle.setBackgroundColor(TtmlColorParser.parseColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused3) {
                        Log.w(TAG, "failed parsing background value: '" + attributeValue + "'");
                        break;
                    }
                    break;
            }
        }
        return ttmlStyle;
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00a6  */
    private TtmlNode parseNode(XmlPullParser xmlPullParser, TtmlNode ttmlNode, Map<String, TtmlRegion> map, FrameAndTickRate frameAndTickRate) throws ParserException {
        long j;
        int attributeCount = xmlPullParser.getAttributeCount();
        String[] strArr = null;
        TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, null);
        long timeExpression = -1;
        long timeExpression2 = -1;
        String str = "";
        long timeExpression3 = 0;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (ATTR_BEGIN.equals(attributeName)) {
                timeExpression = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if ("end".equals(attributeName)) {
                timeExpression2 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (ATTR_DURATION.equals(attributeName)) {
                timeExpression3 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if ("style".equals(attributeName)) {
                String[] styleIds = parseStyleIds(attributeValue);
                if (styleIds.length > 0) {
                    strArr = styleIds;
                }
            } else {
                if ("region".equals(attributeName)) {
                    if (map.containsKey(attributeValue)) {
                        str = attributeValue;
                    }
                }
            }
        }
        if (ttmlNode != null && ttmlNode.startTimeUs != -1) {
            if (timeExpression != -1) {
                timeExpression += ttmlNode.startTimeUs;
            }
            if (timeExpression2 != -1) {
                timeExpression2 += ttmlNode.startTimeUs;
            }
        }
        if (timeExpression2 != -1) {
            j = timeExpression2;
        } else if (timeExpression3 > 0) {
            j = timeExpression + timeExpression3;
        } else if (ttmlNode == null || ttmlNode.endTimeUs == -1) {
            j = timeExpression2;
        } else {
            j = ttmlNode.endTimeUs;
        }
        return TtmlNode.buildNode(xmlPullParser.getName(), timeExpression, j, styleAttributes, strArr, str);
    }

    private static boolean isSupportedTag(String str) {
        return str.equals("tt") || str.equals("head") || str.equals("body") || str.equals("div") || str.equals("p") || str.equals("span") || str.equals("br") || str.equals("style") || str.equals("styling") || str.equals("layout") || str.equals("region") || str.equals("metadata") || str.equals(TtmlNode.TAG_SMPTE_IMAGE) || str.equals(TtmlNode.TAG_SMPTE_DATA) || str.equals(TtmlNode.TAG_SMPTE_INFORMATION);
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws ParserException {
        Matcher matcher;
        String[] strArrSplit = str.split("\\s+");
        if (strArrSplit.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (strArrSplit.length == 2) {
            matcher = FONT_SIZE.matcher(strArrSplit[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new ParserException("Invalid number of entries for fontSize: " + strArrSplit.length + ".");
        }
        if (matcher.matches()) {
            String strGroup = matcher.group(3);
            strGroup.hashCode();
            switch (strGroup) {
                case "%":
                    ttmlStyle.setFontSizeUnit(3);
                    break;
                case "em":
                    ttmlStyle.setFontSizeUnit(2);
                    break;
                case "px":
                    ttmlStyle.setFontSizeUnit(1);
                    break;
                default:
                    throw new ParserException("Invalid unit for fontSize: '" + strGroup + "'.");
            }
            ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        throw new ParserException("Invalid expression for fontSize: '" + str + "'.");
    }

    private static long parseTimeExpression(String str, FrameAndTickRate frameAndTickRate) throws ParserException {
        double d;
        double d2;
        double d3;
        Matcher matcher = CLOCK_TIME.matcher(str);
        if (matcher.matches()) {
            double d4 = (Long.parseLong(matcher.group(1)) * 3600) + (Long.parseLong(matcher.group(2)) * 60) + Long.parseLong(matcher.group(3));
            String strGroup = matcher.group(4);
            double d5 = d4 + (strGroup != null ? Double.parseDouble(strGroup) : 0.0d);
            String strGroup2 = matcher.group(5);
            double d6 = d5 + (strGroup2 != null ? Long.parseLong(strGroup2) / frameAndTickRate.effectiveFrameRate : 0.0d);
            String strGroup3 = matcher.group(6);
            d = d6 + (strGroup3 != null ? (Long.parseLong(strGroup3) / ((double) frameAndTickRate.subFrameRate)) / ((double) frameAndTickRate.effectiveFrameRate) : 0.0d);
        } else {
            Matcher matcher2 = OFFSET_TIME.matcher(str);
            if (matcher2.matches()) {
                d = Double.parseDouble(matcher2.group(1));
                String strGroup4 = matcher2.group(2);
                if (strGroup4.equals(CmcdData.STREAMING_FORMAT_HLS)) {
                    d3 = 3600.0d;
                } else if (strGroup4.equals(CmcdData.OBJECT_TYPE_MANIFEST)) {
                    d3 = 60.0d;
                } else if (!strGroup4.equals(CmcdData.STREAMING_FORMAT_SS)) {
                    if (strGroup4.equals("ms")) {
                        d2 = 1000.0d;
                    } else if (strGroup4.equals("f")) {
                        d2 = frameAndTickRate.effectiveFrameRate;
                    } else if (strGroup4.equals("t")) {
                        d2 = frameAndTickRate.tickRate;
                    }
                    d /= d2;
                }
                d *= d3;
            } else {
                throw new ParserException("Malformed time expression: " + str);
            }
        }
        return (long) (d * 1000000.0d);
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }
}
