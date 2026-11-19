package com.avery.subtitle.format;

import com.avery.subtitle.exception.FatalParsingException;
import com.avery.subtitle.model.Style;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.Time;
import com.avery.subtitle.model.TimedTextObject;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.videolan.libvlc.MediaPlayer;

/* JADX INFO: loaded from: classes4.dex */
public class FormatSTL implements TimedTextFileFormat {
    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public TimedTextObject parseFile(String str, InputStream inputStream) throws IOException, FatalParsingException {
        String str2;
        int i;
        String str3;
        InputStream inputStream2 = inputStream;
        String str4 = "";
        String str5 = ":";
        TimedTextObject timedTextObject = new TimedTextObject();
        timedTextObject.fileName = str;
        byte[] bArr = new byte[1024];
        byte[] bArr2 = new byte[128];
        try {
            createSTLStyles(timedTextObject);
            if (inputStream2.read(bArr) < 1024) {
                throw new FatalParsingException("The file must contain at least a GSI block");
            }
            int i2 = Integer.parseInt(new String(new byte[]{bArr[6], bArr[7]}));
            int i3 = Integer.parseInt(new String(new byte[]{bArr[12], bArr[13]}));
            byte[] bArr3 = new byte[32];
            System.arraycopy(bArr, 16, bArr3, 0, 32);
            String str6 = new String(bArr3);
            byte[] bArr4 = new byte[32];
            System.arraycopy(bArr, 48, bArr4, 0, 32);
            String str7 = new String(bArr4);
            String str8 = "h:m:s:f/fps";
            int i4 = Integer.parseInt(new String(new byte[]{bArr[238], bArr[239], bArr[240], bArr[241], bArr[242]}));
            int i5 = Integer.parseInt(new String(new byte[]{bArr[243], bArr[244], bArr[245], bArr[246], bArr[247]}));
            timedTextObject.title = (str6.trim() + " " + str7.trim()).trim();
            if (i3 > 4 || i3 < 0) {
                timedTextObject.warnings += "Invalid Character Code table number, corrupt data? will try to parse anyways assuming it is latin.\n\n";
            } else if (i3 != 0) {
                timedTextObject.warnings += "Only latin alphabet supported for import from STL, other languages may produce unexpected results.\n\n";
            }
            Subtitle subtitle = null;
            int i6 = 0;
            boolean z = false;
            int i7 = 0;
            while (i6 < i4) {
                if (inputStream2.read(bArr2) < 128) {
                    timedTextObject.warnings += "Unexpected end of file, " + i6 + " blocks read, expecting " + i4 + " blocks in total.\n\n";
                    break;
                }
                if (!z) {
                    subtitle = new Subtitle();
                }
                if (bArr2[1] + (bArr2[2] * 256) != i7) {
                    timedTextObject.warnings += "Unexpected subtitle number at TTI block " + i6 + ". Parsing proceeds...\n\n";
                }
                z = bArr2[3] != -1;
                StringBuilder sb = new StringBuilder();
                sb.append(str4);
                sb.append((int) bArr2[5]);
                sb.append(str5);
                int i8 = i4;
                sb.append((int) bArr2[6]);
                sb.append(str5);
                sb.append((int) bArr2[7]);
                sb.append(str5);
                sb.append((int) bArr2[8]);
                String string = sb.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str4);
                sb2.append((int) bArr2[9]);
                sb2.append(str5);
                sb2.append((int) bArr2[10]);
                sb2.append(str5);
                sb2.append((int) bArr2[11]);
                sb2.append(str5);
                String str9 = str4;
                sb2.append((int) bArr2[12]);
                String string2 = sb2.toString();
                byte b = bArr2[14];
                if (bArr2[15] == 0) {
                    str2 = str5;
                    byte[] bArr5 = new byte[112];
                    i = i5;
                    System.arraycopy(bArr2, 16, bArr5, 0, 112);
                    if (!z) {
                        str3 = str8;
                        subtitle.start = new Time(str3, string + "/" + i2);
                        subtitle.end = new Time(str3, string2 + "/" + i2);
                        parseTextForSTL(subtitle, bArr5, b, timedTextObject);
                    } else {
                        parseTextForSTL(subtitle, bArr5, b, timedTextObject);
                        str3 = str8;
                    }
                } else {
                    str2 = str5;
                    i = i5;
                    str3 = str8;
                }
                if (!z) {
                    i7++;
                }
                i6++;
                inputStream2 = inputStream;
                str8 = str3;
                i4 = i8;
                str4 = str9;
                i5 = i;
                str5 = str2;
            }
            int i9 = i5;
            if (i7 != i9) {
                timedTextObject.warnings += "Number of parsed subtitles (" + i7 + ") different from expected number of subtitles (" + i9 + ").\n\n";
            }
            if (inputStream != null) {
                inputStream.close();
            }
            timedTextObject.cleanUnusedStyles();
            timedTextObject.built = true;
            return timedTextObject;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalParsingException("Format error in the file, migth be due to corrupt data.\n" + e.getMessage());
        }
    }

    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public byte[] toFile(TimedTextObject timedTextObject) {
        char c;
        if (!timedTextObject.built) {
            return null;
        }
        byte[] bArr = new byte[1024];
        byte[] bArr2 = new byte[128];
        byte[] bArr3 = new byte[(timedTextObject.captions.size() * 128) + 1024];
        byte[] bytes = "850STL25.0110000".getBytes();
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        byte[] bytes2 = (timedTextObject.title != null ? timedTextObject.title : timedTextObject.fileName).getBytes();
        for (int i = 0; i < 208; i++) {
            if (i < bytes2.length) {
                bArr[i + 16] = bytes2[i];
            } else {
                bArr[i + 16] = 32;
            }
        }
        String str = new SimpleDateFormat("yyMMdd").format(new Date());
        String str2 = str + str + "00";
        String str3 = "" + timedTextObject.captions.size();
        while (true) {
            c = 5;
            if (str3.length() >= 5) {
                break;
            }
            str3 = "0" + str3;
        }
        byte[] bytes3 = (((str2 + str3 + str3 + "0013216100000000") + timedTextObject.captions.get(timedTextObject.captions.firstKey()).start.getTime("hhmmssff/25")) + "11OOO").getBytes();
        System.arraycopy(bytes3, 0, bArr, 224, bytes3.length);
        for (int i2 = MediaPlayer.Event.ESDeleted; i2 < 1024; i2++) {
            bArr[i2] = 32;
        }
        System.arraycopy(bArr, 0, bArr3, 0, 1024);
        int i3 = 0;
        for (Subtitle subtitle : timedTextObject.captions.values()) {
            bArr2[0] = 0;
            bArr2[1] = (byte) (i3 % 256);
            bArr2[2] = (byte) (i3 / 256);
            bArr2[3] = -1;
            bArr2[4] = 0;
            String[] strArrSplit = subtitle.start.getTime("h:m:s:f/25").split(":");
            bArr2[c] = Byte.parseByte(strArrSplit[0]);
            bArr2[6] = Byte.parseByte(strArrSplit[1]);
            bArr2[7] = Byte.parseByte(strArrSplit[2]);
            bArr2[8] = Byte.parseByte(strArrSplit[3]);
            String[] strArrSplit2 = subtitle.end.getTime("h:m:s:f/25").split(":");
            bArr2[9] = Byte.parseByte(strArrSplit2[0]);
            bArr2[10] = Byte.parseByte(strArrSplit2[1]);
            bArr2[11] = Byte.parseByte(strArrSplit2[2]);
            bArr2[12] = Byte.parseByte(strArrSplit2[3]);
            bArr2[13] = Ascii.DC2;
            if (subtitle.style != null) {
                if (subtitle.style.textAlign.contains("left")) {
                    bArr2[14] = 1;
                } else if (subtitle.style.textAlign.contains("right")) {
                    bArr2[14] = 3;
                }
            } else {
                bArr2[14] = 2;
            }
            bArr2[15] = 0;
            String[] strArrSplit3 = subtitle.content.split("<br />");
            for (int i4 = 0; i4 < strArrSplit3.length; i4++) {
                strArrSplit3[i4] = strArrSplit3[i4].replaceAll("\\<.*?\\>", "");
            }
            int i5 = 16;
            if (subtitle.style != null) {
                Style style = subtitle.style;
                if (style.italic) {
                    bArr2[16] = -128;
                } else {
                    bArr2[16] = -127;
                }
                if (style.underline) {
                    bArr2[17] = -126;
                } else {
                    bArr2[17] = -125;
                }
                String strSubstring = style.color.substring(0, 6);
                i5 = 19;
                if (strSubstring.equalsIgnoreCase("000000")) {
                    bArr2[18] = 0;
                } else if (strSubstring.equalsIgnoreCase("0000ff")) {
                    bArr2[18] = 4;
                } else if (strSubstring.equalsIgnoreCase("00ffff")) {
                    bArr2[18] = 6;
                } else if (strSubstring.equalsIgnoreCase("00ff00")) {
                    bArr2[18] = 2;
                } else if (strSubstring.equalsIgnoreCase("ff0000")) {
                    bArr2[18] = 1;
                } else if (strSubstring.equalsIgnoreCase("ffff00")) {
                    bArr2[18] = 3;
                } else if (strSubstring.equalsIgnoreCase("ff00ff")) {
                    bArr2[18] = 5;
                } else {
                    bArr2[18] = 7;
                }
            }
            int i6 = 0;
            while (i6 < strArrSplit3.length) {
                char[] charArray = strArrSplit3[i6].toCharArray();
                for (int i7 = 0; i7 < charArray.length && i5 <= 126; i7++) {
                    char c2 = charArray[i7];
                    if (c2 >= ' ' && c2 <= 127) {
                        bArr2[i5] = (byte) c2;
                        i5++;
                    }
                }
                i6++;
                if (i6 < strArrSplit3.length) {
                    bArr2[i5] = -118;
                    i5++;
                }
            }
            while (i5 < 128) {
                bArr2[i5] = -113;
                i5++;
            }
            System.arraycopy(bArr2, 0, bArr3, (i3 * 128) + 1024, bArr2.length);
            bArr2 = new byte[128];
            i3++;
            c = 5;
        }
        return bArr3;
    }

    private void parseTextForSTL(Subtitle subtitle, byte[] bArr, int i, TimedTextObject timedTextObject) {
        String str;
        Style style;
        String str2 = "white";
        String str3 = "";
        int length = 0;
        boolean z = false;
        boolean z2 = false;
        while (length < bArr.length) {
            byte b = bArr[length];
            if (b >= 0) {
                if (b < 32) {
                    int i2 = length + 1;
                    if (i2 < bArr.length && b == bArr[i2]) {
                        length = i2;
                    }
                    switch (bArr[length]) {
                        case 0:
                            str2 = "black";
                            break;
                        case 1:
                            str2 = "red";
                            break;
                        case 2:
                            str2 = "green";
                            break;
                        case 3:
                            str2 = "yellow";
                            break;
                        case 4:
                            str2 = "blue";
                            break;
                        case 5:
                            str2 = "magenta";
                            break;
                        case 6:
                            str2 = "cyan";
                            break;
                        case 7:
                            str2 = "white";
                            break;
                    }
                } else {
                    str3 = str3 + new String(new byte[]{b});
                }
                length++;
            } else if (b <= -113) {
                int i3 = length + 1;
                if (i3 < bArr.length && b == bArr[i3]) {
                    length = i3;
                }
                byte b2 = bArr[length];
                if (b2 == -118) {
                    subtitle.content += str3 + "<br />";
                } else if (b2 != -113) {
                    switch (b2) {
                        case -128:
                            z2 = true;
                            break;
                        case -127:
                            z2 = false;
                            break;
                        case -126:
                            z = true;
                            break;
                        case -125:
                            z = false;
                            break;
                    }
                } else {
                    subtitle.content += str3;
                    if (z) {
                        str2 = str2 + "U";
                    }
                    if (z2) {
                        str2 = str2 + "I";
                    }
                    Style style2 = timedTextObject.styling.get(str2);
                    if (i == 1) {
                        str = str2 + "L";
                        if (timedTextObject.styling.get(str) == null) {
                            style = new Style(str, style2);
                            style.textAlign = "bottom-left";
                            timedTextObject.styling.put(str, style);
                            str2 = str;
                            style2 = style;
                        } else {
                            style2 = timedTextObject.styling.get(str);
                            str2 = str;
                        }
                    } else if (i == 3) {
                        str = str2 + "R";
                        if (timedTextObject.styling.get(str) == null) {
                            style = new Style(str, style2);
                            style.textAlign = "bottom-rigth";
                            timedTextObject.styling.put(str, style);
                            str2 = str;
                            style2 = style;
                        } else {
                            style2 = timedTextObject.styling.get(str);
                            str2 = str;
                        }
                    }
                    subtitle.style = style2;
                    int i4 = subtitle.start.mseconds;
                    while (timedTextObject.captions.containsKey(Integer.valueOf(i4))) {
                        i4++;
                    }
                    timedTextObject.captions.put(Integer.valueOf(i4), subtitle);
                    length = bArr.length;
                }
                str3 = "";
            }
            length++;
        }
    }

    private void createSTLStyles(TimedTextObject timedTextObject) {
        Style style = new Style("white");
        style.color = Style.getRGBValue("name", "white");
        timedTextObject.styling.put(style.iD, style);
        Style style2 = new Style("whiteU", style);
        style2.underline = true;
        timedTextObject.styling.put(style2.iD, style2);
        Style style3 = new Style("whiteUI", style2);
        style3.italic = true;
        timedTextObject.styling.put(style3.iD, style3);
        Style style4 = new Style("whiteI", style3);
        style4.underline = false;
        timedTextObject.styling.put(style4.iD, style4);
        Style style5 = new Style("green");
        style5.color = Style.getRGBValue("name", "green");
        timedTextObject.styling.put(style5.iD, style5);
        Style style6 = new Style("greenU", style5);
        style6.underline = true;
        timedTextObject.styling.put(style6.iD, style6);
        Style style7 = new Style("greenUI", style6);
        style7.italic = true;
        timedTextObject.styling.put(style7.iD, style7);
        Style style8 = new Style("greenI", style7);
        style8.underline = false;
        timedTextObject.styling.put(style8.iD, style8);
        Style style9 = new Style("blue");
        style9.color = Style.getRGBValue("name", "blue");
        timedTextObject.styling.put(style9.iD, style9);
        Style style10 = new Style("blueU", style9);
        style10.underline = true;
        timedTextObject.styling.put(style10.iD, style10);
        Style style11 = new Style("blueUI", style10);
        style11.italic = true;
        timedTextObject.styling.put(style11.iD, style11);
        Style style12 = new Style("blueI", style11);
        style12.underline = false;
        timedTextObject.styling.put(style12.iD, style12);
        Style style13 = new Style("cyan");
        style13.color = Style.getRGBValue("name", "cyan");
        timedTextObject.styling.put(style13.iD, style13);
        Style style14 = new Style("cyanU", style13);
        style14.underline = true;
        timedTextObject.styling.put(style14.iD, style14);
        Style style15 = new Style("cyanUI", style14);
        style15.italic = true;
        timedTextObject.styling.put(style15.iD, style15);
        Style style16 = new Style("cyanI", style15);
        style16.underline = false;
        timedTextObject.styling.put(style16.iD, style16);
        Style style17 = new Style("red");
        style17.color = Style.getRGBValue("name", "red");
        timedTextObject.styling.put(style17.iD, style17);
        Style style18 = new Style("redU", style17);
        style18.underline = true;
        timedTextObject.styling.put(style18.iD, style18);
        Style style19 = new Style("redUI", style18);
        style19.italic = true;
        timedTextObject.styling.put(style19.iD, style19);
        Style style20 = new Style("redI", style19);
        style20.underline = false;
        timedTextObject.styling.put(style20.iD, style20);
        Style style21 = new Style("yellow");
        style21.color = Style.getRGBValue("name", "yellow");
        timedTextObject.styling.put(style21.iD, style21);
        Style style22 = new Style("yellowU", style21);
        style22.underline = true;
        timedTextObject.styling.put(style22.iD, style22);
        Style style23 = new Style("yellowUI", style22);
        style23.italic = true;
        timedTextObject.styling.put(style23.iD, style23);
        Style style24 = new Style("yellowI", style23);
        style24.underline = false;
        timedTextObject.styling.put(style24.iD, style24);
        Style style25 = new Style("magenta");
        style25.color = Style.getRGBValue("name", "magenta");
        timedTextObject.styling.put(style25.iD, style25);
        Style style26 = new Style("magentaU", style25);
        style26.underline = true;
        timedTextObject.styling.put(style26.iD, style26);
        Style style27 = new Style("magentaUI", style26);
        style27.italic = true;
        timedTextObject.styling.put(style27.iD, style27);
        Style style28 = new Style("magentaI", style27);
        style28.underline = false;
        timedTextObject.styling.put(style28.iD, style28);
        Style style29 = new Style("black");
        style29.color = Style.getRGBValue("name", "black");
        timedTextObject.styling.put(style29.iD, style29);
        Style style30 = new Style("blackU", style29);
        style30.underline = true;
        timedTextObject.styling.put(style30.iD, style30);
        Style style31 = new Style("blackUI", style30);
        style31.italic = true;
        timedTextObject.styling.put(style31.iD, style31);
        Style style32 = new Style("blackI", style31);
        style32.underline = false;
        timedTextObject.styling.put(style32.iD, style32);
    }
}
