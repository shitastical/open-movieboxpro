package com.avery.subtitle.format;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.avery.subtitle.exception.FatalParsingException;
import com.avery.subtitle.model.Style;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.Time;
import com.avery.subtitle.model.TimedTextObject;
import com.movieboxpro.android.utils.ShellUtil;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilderFactory;
import org.joda.time.DateTimeConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes4.dex */
public class FormatTTML implements TimedTextFileFormat {
    /* JADX WARN: Code duplicated, block: B:109:0x02ba A[Catch: Exception -> 0x0386, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:111:0x02c8 A[Catch: Exception -> 0x0386, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:112:0x02cb A[Catch: Exception -> 0x0386, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:117:0x02f9 A[Catch: Exception -> 0x0386, TRY_ENTER, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:119:0x0309 A[Catch: Exception -> 0x0386, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x0329 A[Catch: Exception -> 0x0386, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:122:0x0339 A[Catch: Exception -> 0x0386, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x035f  */
    /* JADX WARN: Code duplicated, block: B:128:0x0362 A[Catch: Exception -> 0x0386, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x0372 A[Catch: Exception -> 0x0386, LOOP:3: B:129:0x0366->B:131:0x0372, LOOP_END, TryCatch #0 {Exception -> 0x0386, blocks: (B:3:0x0013, B:5:0x0031, B:6:0x0037, B:8:0x0043, B:9:0x0049, B:11:0x0055, B:12:0x005b, B:13:0x007b, B:16:0x0083, B:18:0x009c, B:19:0x00a2, B:21:0x00aa, B:22:0x00b0, B:24:0x00b6, B:26:0x00c2, B:27:0x00d6, B:29:0x00de, B:30:0x00e8, B:32:0x00f0, B:33:0x00fa, B:35:0x0102, B:36:0x0108, B:38:0x0110, B:39:0x0116, B:42:0x0120, B:44:0x012c, B:47:0x0139, B:49:0x0143, B:50:0x0146, B:51:0x0149, B:53:0x0151, B:55:0x015d, B:56:0x0161, B:58:0x016b, B:59:0x016d, B:61:0x0175, B:67:0x018c, B:69:0x019c, B:70:0x01ad, B:71:0x01dc, B:73:0x01e4, B:75:0x01f0, B:78:0x01fd, B:80:0x0209, B:82:0x0213, B:83:0x0218, B:84:0x021c, B:86:0x0224, B:88:0x0230, B:89:0x0234, B:91:0x0240, B:92:0x0242, B:94:0x024e, B:96:0x0254, B:98:0x0279, B:99:0x0285, B:101:0x028b, B:107:0x02b4, B:109:0x02ba, B:111:0x02c8, B:112:0x02cb, B:113:0x02ec, B:114:0x02f1, B:117:0x02f9, B:119:0x0309, B:123:0x034c, B:120:0x0329, B:122:0x0339, B:124:0x034f, B:128:0x0362, B:129:0x0366, B:131:0x0372, B:132:0x0375, B:102:0x0298, B:104:0x02a0), top: B:140:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:151:0x037e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:155:0x034c A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:112:0x02cb, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:119:0x0309, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:122:0x0339, please report this as an issue */
    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public TimedTextObject parseFile(String str, InputStream inputStream) throws IOException, FatalParsingException {
        boolean z;
        Node namedItem;
        NodeList childNodes;
        int i;
        int i2;
        Style style;
        TimedTextObject timedTextObject = new TimedTextObject();
        timedTextObject.fileName = str;
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
            document.getDocumentElement().normalize();
            Node nodeItem = document.getElementsByTagName("ttm:title").item(0);
            if (nodeItem != null) {
                timedTextObject.title = nodeItem.getTextContent();
            }
            Node nodeItem2 = document.getElementsByTagName("ttm:copyright").item(0);
            if (nodeItem2 != null) {
                timedTextObject.copyrigth = nodeItem2.getTextContent();
            }
            Node nodeItem3 = document.getElementsByTagName("ttm:desc").item(0);
            if (nodeItem3 != null) {
                timedTextObject.description = nodeItem3.getTextContent();
            }
            NodeList elementsByTagName = document.getElementsByTagName("style");
            NodeList elementsByTagName2 = document.getElementsByTagName("p");
            timedTextObject.warnings += "Styling attributes are only recognized inside a style definition, to be referenced later in the captions.\n\n";
            for (int i3 = 0; i3 < elementsByTagName.getLength(); i3++) {
                Style style2 = new Style(Style.defaultID());
                NamedNodeMap attributes = elementsByTagName.item(i3).getAttributes();
                Node namedItem2 = attributes.getNamedItem("id");
                if (namedItem2 != null) {
                    style2.iD = namedItem2.getNodeValue();
                }
                Node namedItem3 = attributes.getNamedItem("xml:id");
                if (namedItem3 != null) {
                    style2.iD = namedItem3.getNodeValue();
                }
                Node namedItem4 = attributes.getNamedItem("style");
                if (namedItem4 != null && timedTextObject.styling.containsKey(namedItem4.getNodeValue())) {
                    style2 = new Style(style2.iD, timedTextObject.styling.get(namedItem4.getNodeValue()));
                }
                Node namedItem5 = attributes.getNamedItem("tts:backgroundColor");
                if (namedItem5 != null) {
                    style2.backgroundColor = parseColor(namedItem5.getNodeValue(), timedTextObject);
                }
                Node namedItem6 = attributes.getNamedItem("tts:color");
                if (namedItem6 != null) {
                    style2.color = parseColor(namedItem6.getNodeValue(), timedTextObject);
                }
                Node namedItem7 = attributes.getNamedItem("tts:fontFamily");
                if (namedItem7 != null) {
                    style2.font = namedItem7.getNodeValue();
                }
                Node namedItem8 = attributes.getNamedItem("tts:fontSize");
                if (namedItem8 != null) {
                    style2.fontSize = namedItem8.getNodeValue();
                }
                Node namedItem9 = attributes.getNamedItem("tts:fontStyle");
                if (namedItem9 != null) {
                    if (!namedItem9.getNodeValue().equalsIgnoreCase("italic") && !namedItem9.getNodeValue().equalsIgnoreCase("oblique")) {
                        if (namedItem9.getNodeValue().equalsIgnoreCase("normal")) {
                            style2.italic = false;
                        }
                    } else {
                        style2.italic = true;
                    }
                }
                Node namedItem10 = attributes.getNamedItem("tts:fontWeight");
                if (namedItem10 != null) {
                    if (!namedItem10.getNodeValue().equalsIgnoreCase("bold")) {
                        if (namedItem10.getNodeValue().equalsIgnoreCase("normal")) {
                            style2.bold = false;
                        }
                    } else {
                        style2.bold = true;
                    }
                }
                Node namedItem11 = attributes.getNamedItem("tts:opacity");
                if (namedItem11 != null) {
                    try {
                        float f = Float.parseFloat(namedItem11.getNodeValue());
                        if (f > 1.0f) {
                            f = 1.0f;
                        } else if (f < 0.0f) {
                            f = 0.0f;
                        }
                        String hexString = Integer.toHexString((int) (f * 255.0f));
                        if (hexString.length() < 2) {
                            hexString = "0" + hexString;
                        }
                        style2.color = style2.color.substring(0, 6) + hexString;
                        style2.backgroundColor = style2.backgroundColor.substring(0, 6) + hexString;
                    } catch (NumberFormatException unused) {
                    }
                }
                Node namedItem12 = attributes.getNamedItem("tts:textAlign");
                if (namedItem12 != null) {
                    if (namedItem12.getNodeValue().equalsIgnoreCase("left") || namedItem12.getNodeValue().equalsIgnoreCase("start")) {
                        style2.textAlign = "bottom-left";
                    } else if (namedItem12.getNodeValue().equalsIgnoreCase("right") || namedItem12.getNodeValue().equalsIgnoreCase("end")) {
                        style2.textAlign = "bottom-right";
                    }
                }
                Node namedItem13 = attributes.getNamedItem("tts:textDecoration");
                if (namedItem13 != null) {
                    if (namedItem13.getNodeValue().equalsIgnoreCase("underline")) {
                        style2.underline = true;
                    } else if (namedItem13.getNodeValue().equalsIgnoreCase("noUnderline")) {
                        style2.underline = false;
                    }
                }
                timedTextObject.styling.put(style2.iD, style2);
            }
            for (int i4 = 0; i4 < elementsByTagName2.getLength(); i4++) {
                Subtitle subtitle = new Subtitle();
                subtitle.content = "";
                Node nodeItem4 = elementsByTagName2.item(i4);
                NamedNodeMap attributes2 = nodeItem4.getAttributes();
                Node namedItem14 = attributes2.getNamedItem("begin");
                subtitle.start = new Time("", "");
                subtitle.end = new Time("", "");
                if (namedItem14 != null) {
                    subtitle.start.mseconds = parseTimeExpression(namedItem14.getNodeValue(), timedTextObject, document);
                }
                Node namedItem15 = attributes2.getNamedItem("end");
                if (namedItem15 != null) {
                    subtitle.end.mseconds = parseTimeExpression(namedItem15.getNodeValue(), timedTextObject, document);
                } else {
                    Node namedItem16 = attributes2.getNamedItem("dur");
                    if (namedItem16 != null) {
                        subtitle.end.mseconds = subtitle.start.mseconds + parseTimeExpression(namedItem16.getNodeValue(), timedTextObject, document);
                    } else {
                        z = false;
                    }
                    namedItem = attributes2.getNamedItem("style");
                    if (namedItem != null) {
                        style = timedTextObject.styling.get(namedItem.getNodeValue());
                        if (style != null) {
                            subtitle.style = style;
                        } else {
                            timedTextObject.warnings += "unrecoginzed style referenced: " + namedItem.getNodeValue() + "\n\n";
                        }
                    }
                    childNodes = nodeItem4.getChildNodes();
                    for (i = 0; i < childNodes.getLength(); i++) {
                        if (childNodes.item(i).getNodeName().equals("#text")) {
                            subtitle.content += childNodes.item(i).getTextContent().trim();
                        } else if (childNodes.item(i).getNodeName().equals("br")) {
                            subtitle.content += "<br />";
                        }
                    }
                    if (subtitle.content.replaceAll("<br />", "").trim().isEmpty()) {
                        z = false;
                    }
                    if (z) {
                        i2 = subtitle.start.mseconds;
                        while (timedTextObject.captions.containsKey(Integer.valueOf(i2))) {
                            i2++;
                        }
                        timedTextObject.captions.put(Integer.valueOf(i2), subtitle);
                    }
                }
                z = true;
                namedItem = attributes2.getNamedItem("style");
                if (namedItem != null) {
                    style = timedTextObject.styling.get(namedItem.getNodeValue());
                    if (style != null) {
                        subtitle.style = style;
                    } else {
                        timedTextObject.warnings += "unrecoginzed style referenced: " + namedItem.getNodeValue() + "\n\n";
                    }
                }
                childNodes = nodeItem4.getChildNodes();
                while (i < childNodes.getLength()) {
                    if (childNodes.item(i).getNodeName().equals("#text")) {
                        subtitle.content += childNodes.item(i).getTextContent().trim();
                    } else if (childNodes.item(i).getNodeName().equals("br")) {
                        subtitle.content += "<br />";
                    }
                }
                if (subtitle.content.replaceAll("<br />", "").trim().isEmpty()) {
                    z = false;
                }
                if (z) {
                    i2 = subtitle.start.mseconds;
                    while (timedTextObject.captions.containsKey(Integer.valueOf(i2))) {
                        i2++;
                    }
                    timedTextObject.captions.put(Integer.valueOf(i2), subtitle);
                }
            }
            timedTextObject.built = true;
            return timedTextObject;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalParsingException("Error during parsing: " + e.getMessage());
        }
    }

    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public String[] toFile(TimedTextObject timedTextObject) {
        String str;
        String str2;
        if (!timedTextObject.built) {
            return null;
        }
        ArrayList arrayList = new ArrayList(timedTextObject.styling.size() + 30 + timedTextObject.captions.size());
        arrayList.add(0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        arrayList.add(1, "<tt xml:lang=\"" + timedTextObject.language + "\" xmlns=\"http://www.w3.org/ns/ttml\" xmlns:tts=\"http://www.w3.org/ns/ttml#styling\">");
        arrayList.add(2, "\t<head>");
        arrayList.add(3, "\t\t<metadata xmlns:ttm=\"http://www.w3.org/ns/ttml#metadata\">");
        if (timedTextObject.title == null || timedTextObject.title.isEmpty()) {
            str = timedTextObject.fileName;
        } else {
            str = timedTextObject.title;
        }
        arrayList.add(4, "\t\t\t<ttm:title>" + str + "</ttm:title>");
        int i = 5;
        if (timedTextObject.copyrigth != null && !timedTextObject.copyrigth.isEmpty()) {
            arrayList.add(5, "\t\t\t<ttm:copyright>" + timedTextObject.copyrigth + "</ttm:copyright>");
            i = 6;
        }
        String str3 = (timedTextObject.author != null && !timedTextObject.author.isEmpty()) ? "Converted by the Online Subtitle Converter developed by J. David Requejo\n\n Original file by: " + timedTextObject.author + ShellUtil.COMMAND_LINE_END : "Converted by the Online Subtitle Converter developed by J. David Requejo\n";
        if (timedTextObject.description != null && !timedTextObject.description.isEmpty()) {
            str3 = str3 + timedTextObject.description + ShellUtil.COMMAND_LINE_END;
        }
        arrayList.add(i, "\t\t\t<ttm:desc>" + str3 + "\t\t\t</ttm:desc>");
        int i2 = i + 2;
        arrayList.add(i + 1, "\t\t</metadata>");
        int i3 = i + 3;
        arrayList.add(i2, "\t\t<styling>");
        Hashtable<String, Style> hashtable = timedTextObject.styling;
        for (Style style : DesugarCollections.bridge_synchronizedCollection(hashtable.values(), hashtable)) {
            String str4 = "\t\t\t<style xml:id=\"" + style.iD + "\"";
            if (style.color != null) {
                str4 = str4 + " tts:color=\"#" + style.color + "\"";
            }
            if (style.backgroundColor != null) {
                str4 = str4 + " tts:backgroundColor=\"#" + style.backgroundColor + "\"";
            }
            if (style.font != null) {
                str4 = str4 + " tts:fontFamily=\"" + style.font + "\"";
            }
            if (style.fontSize != null) {
                str4 = str4 + " tts:fontSize=\"" + style.fontSize + "\"";
            }
            if (style.italic) {
                str4 = str4 + " tts:fontStyle=\"italic\"";
            }
            if (style.bold) {
                str4 = str4 + " tts:fontWeight=\"bold\"";
            }
            String str5 = str4 + " tts:textAlign=\"";
            if (style.textAlign.contains("left")) {
                str2 = str5 + "left\"";
            } else if (style.textAlign.contains("right")) {
                str2 = str5 + "rigth\"";
            } else {
                str2 = str5 + "center\"";
            }
            if (style.underline) {
                str2 = str2 + " tts:textDecoration=\"underline\"";
            }
            arrayList.add(i3, str2 + " />");
            i3++;
        }
        arrayList.add(i3, "\t\t</styling>");
        arrayList.add(i3 + 1, "\t</head>");
        int i4 = i3 + 3;
        arrayList.add(i3 + 2, "\t<body>");
        int i5 = i3 + 4;
        arrayList.add(i4, "\t\t<div>");
        for (Subtitle subtitle : timedTextObject.captions.values()) {
            String str6 = ("\t\t\t<p begin=\"" + subtitle.start.getTime("hh:mm:ss,ms").replace(',', '.') + "\"") + " end=\"" + subtitle.end.getTime("hh:mm:ss,ms").replace(',', '.') + "\"";
            if (subtitle.style != null) {
                str6 = str6 + " style=\"" + subtitle.style.iD + "\"";
            }
            arrayList.add(i5, str6 + " >" + subtitle.content + "</p>\n");
            i5++;
        }
        arrayList.add(i5, "\t\t</div>");
        arrayList.add(i5 + 1, "\t</body>");
        arrayList.add(i5 + 2, "</tt>");
        arrayList.add(i5 + 3, "");
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i6 = 0; i6 < size; i6++) {
            strArr[i6] = (String) arrayList.get(i6);
        }
        return strArr;
    }

    private String parseColor(String str, TimedTextObject timedTextObject) {
        if (str.startsWith("#")) {
            if (str.length() == 7) {
                return str.substring(1) + "ff";
            }
            if (str.length() == 9) {
                return str.substring(1);
            }
            timedTextObject.warnings += "Unrecoginzed format: " + str + "\n\n";
            return "ffffffff";
        }
        if (str.startsWith("rgb")) {
            boolean zStartsWith = str.startsWith("rgba");
            try {
                String[] strArrSplit = str.split("\\(")[1].split(",");
                int i = Integer.parseInt(strArrSplit[0]);
                int i2 = Integer.parseInt(strArrSplit[1]);
                int i3 = Integer.parseInt(strArrSplit[2].substring(0, 2));
                int i4 = zStartsWith ? Integer.parseInt(strArrSplit[3].substring(0, 2)) : 255;
                strArrSplit[0] = Integer.toHexString(i);
                strArrSplit[1] = Integer.toHexString(i2);
                strArrSplit[2] = Integer.toHexString(i3);
                if (zStartsWith) {
                    strArrSplit[2] = Integer.toHexString(i4);
                }
                String str2 = "";
                for (int i5 = 0; i5 < strArrSplit.length; i5++) {
                    if (strArrSplit[i5].length() < 2) {
                        strArrSplit[i5] = "0" + strArrSplit[i5];
                    }
                    str2 = str2 + strArrSplit[i5];
                }
                if (!zStartsWith) {
                    str2 = str2 + "ff";
                }
                return str2;
            } catch (Exception unused) {
                timedTextObject.warnings += "Unrecoginzed color: " + str + "\n\n";
                return "ffffffff";
            }
        }
        String rGBValue = Style.getRGBValue("name", str);
        if (rGBValue != null && !rGBValue.isEmpty()) {
            return rGBValue;
        }
        timedTextObject.warnings += "Unrecoginzed color: " + str + "\n\n";
        return "ffffffff";
    }

    private int parseTimeExpression(String str, TimedTextObject timedTextObject, Document document) {
        Node nodeItem;
        double d;
        int i;
        if (str.contains(":")) {
            String[] strArrSplit = str.split(":");
            if (strArrSplit.length == 3) {
                return (Integer.parseInt(strArrSplit[0]) * DateTimeConstants.MILLIS_PER_HOUR) + (Integer.parseInt(strArrSplit[1]) * DateTimeConstants.MILLIS_PER_MINUTE) + ((int) (Float.parseFloat(strArrSplit[2]) * 1000.0f));
            }
            if (strArrSplit.length != 4) {
                return 0;
            }
            Node nodeItem2 = document.getElementsByTagName("ttp:frameRate").item(0);
            if (nodeItem2 != null) {
                try {
                    i = Integer.parseInt(nodeItem2.getNodeValue());
                } catch (NumberFormatException unused) {
                    i = 25;
                }
            } else {
                i = 25;
            }
            return (Integer.parseInt(strArrSplit[0]) * DateTimeConstants.MILLIS_PER_HOUR) + (Integer.parseInt(strArrSplit[1]) * DateTimeConstants.MILLIS_PER_MINUTE) + (Integer.parseInt(strArrSplit[2]) * 1000) + ((int) ((Float.parseFloat(strArrSplit[3]) * 1000.0f) / i));
        }
        String strSubstring = str.substring(str.length() - 1);
        try {
            double d2 = Double.parseDouble(str.substring(0, str.length() - 1).replace(',', '.').trim());
            if (!strSubstring.equalsIgnoreCase(CmcdData.STREAMING_FORMAT_HLS)) {
                if (strSubstring.equalsIgnoreCase(CmcdData.OBJECT_TYPE_MANIFEST)) {
                    d = 60000.0d;
                } else if (strSubstring.equalsIgnoreCase(CmcdData.STREAMING_FORMAT_SS)) {
                    d2 *= 1000.0d;
                } else if (!strSubstring.equalsIgnoreCase("ms")) {
                    if (strSubstring.equalsIgnoreCase("f")) {
                        Node nodeItem3 = document.getElementsByTagName("ttp:frameRate").item(0);
                        if (nodeItem3 != null) {
                            return (int) ((d2 * 1000.0d) / ((double) Integer.parseInt(nodeItem3.getNodeValue())));
                        }
                        return 0;
                    }
                    if (!strSubstring.equalsIgnoreCase("t") || (nodeItem = document.getElementsByTagName("ttp:tickRate").item(0)) == null) {
                        return 0;
                    }
                    d2 = (d2 * 1000.0d) / ((double) Integer.parseInt(nodeItem.getNodeValue()));
                }
                return (int) d2;
            }
            d = 3600000.0d;
            d2 *= d;
            return (int) d2;
        } catch (NumberFormatException unused2) {
            return 0;
        }
    }
}
