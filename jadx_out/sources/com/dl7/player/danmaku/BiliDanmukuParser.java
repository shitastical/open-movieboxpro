package com.dl7.player.danmaku;

import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Locale;
import master.flame.danmaku.danmaku.model.AlphaValue;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Duration;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.android.AndroidFileSource;
import master.flame.danmaku.danmaku.util.DanmakuUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/* JADX INFO: loaded from: classes4.dex */
public class BiliDanmukuParser extends BaseDanmakuParser {
    private float mDispScaleX;
    private float mDispScaleY;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPercentageNumber(float f) {
        return f >= 0.0f && f <= 1.0f;
    }

    static {
        System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");
    }

    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
    public Danmakus parse() {
        if (this.mDataSource == null) {
            return null;
        }
        AndroidFileSource androidFileSource = (AndroidFileSource) this.mDataSource;
        try {
            XMLReader xMLReaderCreateXMLReader = XMLReaderFactory.createXMLReader();
            XmlContentHandler xmlContentHandler = new XmlContentHandler();
            xMLReaderCreateXMLReader.setContentHandler(xmlContentHandler);
            xMLReaderCreateXMLReader.parse(new InputSource(androidFileSource.data()));
            return xmlContentHandler.getResult();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (SAXException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public class XmlContentHandler extends DefaultHandler {
        private static final String TRUE_STRING = "true";
        public Danmakus result = null;
        public BaseDanmaku item = null;
        public boolean completed = false;
        public int index = 0;

        public XmlContentHandler() {
        }

        public Danmakus getResult() {
            return this.result;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startDocument() throws SAXException {
            this.result = new Danmakus();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endDocument() throws SAXException {
            this.completed = true;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.length() == 0) {
                str2 = str3;
            }
            if (str2.toLowerCase(Locale.getDefault()).trim().equals("d")) {
                String[] strArrSplit = attributes.getValue("p").split(",");
                if (strArrSplit.length > 0) {
                    long j = (long) (Float.parseFloat(strArrSplit[0]) * 1000.0f);
                    int i = Integer.parseInt(strArrSplit[1]);
                    float f = Float.parseFloat(strArrSplit[2]);
                    int i2 = (int) (Long.parseLong(strArrSplit[3]) | (-16777216));
                    BaseDanmaku baseDanmakuCreateDanmaku = BiliDanmukuParser.this.mContext.mDanmakuFactory.createDanmaku(i, BiliDanmukuParser.this.mContext);
                    this.item = baseDanmakuCreateDanmaku;
                    if (baseDanmakuCreateDanmaku != null) {
                        baseDanmakuCreateDanmaku.setTime(j);
                        this.item.textSize = f * (BiliDanmukuParser.this.mDispDensity - 0.6f);
                        this.item.textColor = i2;
                        BaseDanmaku baseDanmaku = this.item;
                        int i3 = ViewCompat.MEASURED_STATE_MASK;
                        if (i2 <= -16777216) {
                            i3 = -1;
                        }
                        baseDanmaku.textShadowColor = i3;
                    }
                }
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            BaseDanmaku baseDanmaku = this.item;
            if (baseDanmaku != null) {
                if (baseDanmaku.duration != null) {
                    if (str2.length() == 0) {
                        str2 = str3;
                    }
                    if (str2.equalsIgnoreCase("d")) {
                        this.item.setTimer(BiliDanmukuParser.this.mTimer);
                        this.result.addItem(this.item);
                    }
                }
                this.item = null;
            }
        }

        /* JADX WARN: Code duplicated, block: B:47:0x0126  */
        /* JADX WARN: Code duplicated, block: B:50:0x0134  */
        /* JADX WARN: Code duplicated, block: B:53:0x0140  */
        /* JADX WARN: Code duplicated, block: B:56:0x014c  */
        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i, int i2) {
            String[] strArr;
            float f;
            float f2;
            float f3;
            float f4;
            long j;
            long j2;
            String[] strArrSplit;
            BaseDanmaku baseDanmaku = this.item;
            if (baseDanmaku != null) {
                DanmakuUtils.fillText(baseDanmaku, decodeXmlString(new String(cArr, i, i2)));
                BaseDanmaku baseDanmaku2 = this.item;
                int i3 = this.index;
                this.index = i3 + 1;
                baseDanmaku2.index = i3;
                String strTrim = String.valueOf(this.item.text).trim();
                if (this.item.getType() == 7 && strTrim.startsWith("[") && strTrim.endsWith("]")) {
                    try {
                        JSONArray jSONArray = new JSONArray(strTrim);
                        int length = jSONArray.length();
                        strArr = new String[length];
                        for (int i4 = 0; i4 < length; i4++) {
                            try {
                                strArr[i4] = jSONArray.getString(i4);
                            } catch (JSONException e) {
                                e = e;
                                e.printStackTrace();
                                if (strArr != null) {
                                }
                                this.item = null;
                                return;
                            }
                        }
                    } catch (JSONException e2) {
                        e = e2;
                        strArr = null;
                    }
                    if (strArr != null || strArr.length < 5) {
                        this.item = null;
                        return;
                    }
                    this.item.text = strArr[4];
                    float f5 = Float.parseFloat(strArr[0]);
                    float f6 = Float.parseFloat(strArr[1]);
                    String[] strArrSplit2 = strArr[2].split("-");
                    int i5 = (int) (AlphaValue.MAX * Float.parseFloat(strArrSplit2[0]));
                    int i6 = strArrSplit2.length > 1 ? (int) (AlphaValue.MAX * Float.parseFloat(strArrSplit2[1])) : i5;
                    long j3 = (long) (Float.parseFloat(strArr[3]) * 1000.0f);
                    if (strArr.length >= 7) {
                        f = Float.parseFloat(strArr[5]);
                        f2 = Float.parseFloat(strArr[6]);
                    } else {
                        f = 0.0f;
                        f2 = 0.0f;
                    }
                    if (strArr.length >= 11) {
                        f3 = Float.parseFloat(strArr[7]);
                        f4 = Float.parseFloat(strArr[8]);
                        long j4 = !"".equals(strArr[9]) ? Integer.parseInt(strArr[9]) : j3;
                        if ("".equals(strArr[10])) {
                            j = j4;
                        } else {
                            j2 = (long) Float.parseFloat(strArr[10]);
                            j = j4;
                            f3 = f3;
                        }
                        if (BiliDanmukuParser.this.isPercentageNumber(f5)) {
                            f5 *= 682.0f;
                        }
                        float f7 = f5;
                        if (BiliDanmukuParser.this.isPercentageNumber(f6)) {
                            f6 *= 438.0f;
                        }
                        float f8 = f6;
                        if (BiliDanmukuParser.this.isPercentageNumber(f3)) {
                            f3 *= 682.0f;
                        }
                        float f9 = f3;
                        if (BiliDanmukuParser.this.isPercentageNumber(f4)) {
                            f4 *= 438.0f;
                        }
                        this.item.duration = new Duration(j3);
                        this.item.rotationZ = f;
                        this.item.rotationY = f2;
                        BiliDanmukuParser.this.mContext.mDanmakuFactory.fillTranslationData(this.item, f7, f8, f9, f4, j, j2, BiliDanmukuParser.this.mDispScaleX, BiliDanmukuParser.this.mDispScaleY);
                        BiliDanmukuParser.this.mContext.mDanmakuFactory.fillAlphaData(this.item, i5, i6, j3);
                        if (strArr.length >= 12 && !TextUtils.isEmpty(strArr[11]) && TRUE_STRING.equals(strArr[11])) {
                            this.item.textShadowColor = 0;
                        }
                        int length2 = strArr.length;
                        int length3 = strArr.length;
                        if (strArr.length >= 15 || "".equals(strArr[14]) || (strArrSplit = strArr[14].substring(1).split("L")) == null || strArrSplit.length <= 0) {
                            return;
                        }
                        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, strArrSplit.length, 2);
                        for (int i7 = 0; i7 < strArrSplit.length; i7++) {
                            String[] strArrSplit3 = strArrSplit[i7].split(",");
                            fArr[i7][0] = Float.parseFloat(strArrSplit3[0]);
                            fArr[i7][1] = Float.parseFloat(strArrSplit3[1]);
                        }
                        DanmakuFactory danmakuFactory = BiliDanmukuParser.this.mContext.mDanmakuFactory;
                        DanmakuFactory.fillLinePathData(this.item, fArr, BiliDanmukuParser.this.mDispScaleX, BiliDanmukuParser.this.mDispScaleY);
                        return;
                    }
                    f = f;
                    f3 = f5;
                    f4 = f6;
                    j = j3;
                    j2 = 0;
                    if (BiliDanmukuParser.this.isPercentageNumber(f5)) {
                        f5 *= 682.0f;
                    }
                    float f10 = f5;
                    if (BiliDanmukuParser.this.isPercentageNumber(f6)) {
                        f6 *= 438.0f;
                    }
                    float f11 = f6;
                    if (BiliDanmukuParser.this.isPercentageNumber(f3)) {
                        f3 *= 682.0f;
                    }
                    float f12 = f3;
                    if (BiliDanmukuParser.this.isPercentageNumber(f4)) {
                        f4 *= 438.0f;
                    }
                    this.item.duration = new Duration(j3);
                    this.item.rotationZ = f;
                    this.item.rotationY = f2;
                    BiliDanmukuParser.this.mContext.mDanmakuFactory.fillTranslationData(this.item, f10, f11, f12, f4, j, j2, BiliDanmukuParser.this.mDispScaleX, BiliDanmukuParser.this.mDispScaleY);
                    BiliDanmukuParser.this.mContext.mDanmakuFactory.fillAlphaData(this.item, i5, i6, j3);
                    if (strArr.length >= 12) {
                        this.item.textShadowColor = 0;
                    }
                    int length4 = strArr.length;
                    int length5 = strArr.length;
                    if (strArr.length >= 15) {
                    }
                }
            }
        }

        private String decodeXmlString(String str) {
            if (str.contains("&amp;")) {
                str = str.replace("&amp;", "&");
            }
            if (str.contains("&quot;")) {
                str = str.replace("&quot;", "\"");
            }
            if (str.contains("&gt;")) {
                str = str.replace("&gt;", ">");
            }
            return str.contains("&lt;") ? str.replace("&lt;", "<") : str;
        }
    }

    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
    public BaseDanmakuParser setDisplayer(IDisplayer iDisplayer) {
        super.setDisplayer(iDisplayer);
        this.mDispScaleX = this.mDispWidth / 682.0f;
        this.mDispScaleY = this.mDispHeight / 438.0f;
        return this;
    }
}
