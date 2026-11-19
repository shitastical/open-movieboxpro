package com.avery.subtitle.format;

import com.avery.subtitle.model.Style;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.Time;
import com.avery.subtitle.model.TimedTextObject;
import com.movieboxpro.android.utils.ShellUtil;
import j$.util.DesugarCollections;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import okio.Utf8;

/* JADX INFO: loaded from: classes4.dex */
public class FormatASS implements TimedTextFileFormat {
    /* JADX WARN: Code duplicated, block: B:95:0x0309  */
    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public TimedTextObject parseFile(String str, InputStream inputStream) throws Throwable {
        String strTrim;
        TimedTextObject timedTextObject = new TimedTextObject();
        timedTextObject.fileName = str;
        new Subtitle();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            try {
                String line = bufferedReader.readLine();
                float f = 100.0f;
                boolean z = false;
                int i = 1;
                while (line != null) {
                    String strTrim2 = line.trim();
                    if (strTrim2.startsWith("[")) {
                        if (strTrim2.equalsIgnoreCase("[Script info]")) {
                            i++;
                            line = bufferedReader.readLine().trim();
                            while (!line.startsWith("[")) {
                                if (line.startsWith("Title:")) {
                                    timedTextObject.title = line.split(":")[1].trim();
                                } else if (line.startsWith("Original Script:")) {
                                    timedTextObject.author = line.split(":")[1].trim();
                                } else if (line.startsWith("Script Type:")) {
                                    if (line.split(":")[1].trim().equalsIgnoreCase("v4.00+")) {
                                        z = true;
                                    } else if (!line.split(":")[1].trim().equalsIgnoreCase("v4.00")) {
                                        timedTextObject.warnings += "Script version is older than 4.00, it may produce parsing errors.";
                                    }
                                } else if (line.startsWith("Timer:")) {
                                    f = Float.parseFloat(line.split(":")[1].trim().replace(',', '.'));
                                }
                                i++;
                                line = bufferedReader.readLine().trim();
                            }
                        } else {
                            if (!strTrim2.equalsIgnoreCase("[v4 Styles]")) {
                                try {
                                    if (!strTrim2.equalsIgnoreCase("[v4 Styles+]") && !strTrim2.equalsIgnoreCase("[v4+ Styles]")) {
                                        if (strTrim2.trim().equalsIgnoreCase("[Events]")) {
                                            int i2 = i + 1;
                                            String strTrim3 = bufferedReader.readLine().trim();
                                            timedTextObject.warnings += "Only dialogue events are considered, all other events are ignored.\n\n";
                                            if (!strTrim3.startsWith("Format:")) {
                                                timedTextObject.warnings += "Format: (format definition) expected at line " + strTrim3 + " for the events section\n\n";
                                                while (!strTrim3.startsWith("Format:")) {
                                                    i2++;
                                                    strTrim3 = bufferedReader.readLine().trim();
                                                }
                                            }
                                            String[] strArrSplit = strTrim3.split(":")[1].trim().split(",");
                                            i = i2 + 1;
                                            strTrim = bufferedReader.readLine().trim();
                                            while (!strTrim.startsWith("[")) {
                                                if (strTrim.startsWith("Dialogue:")) {
                                                    Subtitle dialogueForASS = parseDialogueForASS(strTrim.split(":", 2)[1].trim().split(",", 10), strArrSplit, f, timedTextObject);
                                                    int i3 = dialogueForASS.start.mseconds;
                                                    while (timedTextObject.captions.containsKey(Integer.valueOf(i3))) {
                                                        i3++;
                                                    }
                                                    timedTextObject.captions.put(Integer.valueOf(i3), dialogueForASS);
                                                }
                                                i++;
                                                strTrim = bufferedReader.readLine().trim();
                                            }
                                        } else if (strTrim2.trim().equalsIgnoreCase("[Fonts]") || strTrim2.trim().equalsIgnoreCase("[Graphics]")) {
                                            timedTextObject.warnings += "The section " + strTrim2.trim() + " is not supported for conversion, all information there will be lost.\n\n";
                                            line = bufferedReader.readLine().trim();
                                        } else {
                                            timedTextObject.warnings += "Unrecognized section: " + strTrim2.trim() + " all information there is ignored.";
                                            line = bufferedReader.readLine().trim();
                                        }
                                    }
                                    line = strTrim;
                                } catch (NullPointerException unused) {
                                    timedTextObject.warnings += "unexpected end of file, maybe last caption is not complete.\n\n";
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    timedTextObject.built = true;
                                    return timedTextObject;
                                } catch (Throwable th) {
                                    th = th;
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    throw th;
                                }
                            }
                            if (strTrim2.contains("+") && !z) {
                                timedTextObject.warnings += "ScriptType should be set to v4:00+ in the [Script Info] section.\n\n";
                                z = true;
                            }
                            int i4 = i + 1;
                            String strTrim4 = bufferedReader.readLine().trim();
                            if (!strTrim4.startsWith("Format:")) {
                                timedTextObject.warnings += "Format: (format definition) expected at line " + strTrim4 + " for the styles section\n\n";
                                while (!strTrim4.startsWith("Format:")) {
                                    i4++;
                                    strTrim4 = bufferedReader.readLine().trim();
                                }
                            }
                            String[] strArrSplit2 = strTrim4.split(":")[1].trim().split(",");
                            i = i4 + 1;
                            strTrim = bufferedReader.readLine().trim();
                            while (!strTrim.startsWith("[")) {
                                if (strTrim.startsWith("Style:")) {
                                    Style styleForASS = parseStyleForASS(strTrim.split(":")[1].trim().split(","), strArrSplit2, i, z, timedTextObject.warnings);
                                    timedTextObject.styling.put(styleForASS.iD, styleForASS);
                                }
                                i++;
                                strTrim = bufferedReader.readLine().trim();
                            }
                            line = strTrim;
                        }
                    } else {
                        line = bufferedReader.readLine();
                        i++;
                    }
                }
                timedTextObject.cleanUnusedStyles();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (NullPointerException unused2) {
            }
            timedTextObject.built = true;
            return timedTextObject;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public String[] toFile(TimedTextObject timedTextObject) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (!timedTextObject.built) {
            return null;
        }
        ArrayList arrayList = new ArrayList(timedTextObject.styling.size() + 30 + timedTextObject.captions.size());
        arrayList.add(0, "[Script Info]");
        arrayList.add(1, (timedTextObject.title == null || timedTextObject.title.isEmpty()) ? "Title: " + timedTextObject.fileName : "Title: " + timedTextObject.title);
        arrayList.add(2, (timedTextObject.author == null || timedTextObject.author.isEmpty()) ? "Original Script: Unknown" : "Original Script: " + timedTextObject.author);
        if (timedTextObject.copyrigth == null || timedTextObject.copyrigth.isEmpty()) {
            i = 3;
        } else {
            arrayList.add(3, "; " + timedTextObject.copyrigth);
            i = 4;
        }
        if (timedTextObject.description != null && !timedTextObject.description.isEmpty()) {
            arrayList.add(i, "; " + timedTextObject.description);
            i++;
        }
        int i6 = i + 1;
        arrayList.add(i, "; Converted by the Online Subtitle Converter developed by J. David Requejo");
        if (timedTextObject.useASSInsteadOfSSA) {
            i2 = i + 2;
            arrayList.add(i6, "Script Type: V4.00+");
        } else {
            i2 = i + 2;
            arrayList.add(i6, "Script Type: V4.00");
        }
        arrayList.add(i2, "Collisions: Normal");
        int i7 = i2 + 2;
        arrayList.add(i2 + 1, "Timer: 100,0000");
        if (timedTextObject.useASSInsteadOfSSA) {
            arrayList.add(i7, "WrapStyle: 1");
            i7 = i2 + 3;
        }
        int i8 = i7 + 1;
        arrayList.add(i7, "");
        if (timedTextObject.useASSInsteadOfSSA) {
            i3 = i7 + 2;
            arrayList.add(i8, "[V4+ Styles]");
        } else {
            i3 = i7 + 2;
            arrayList.add(i8, "[V4 Styles]");
        }
        if (timedTextObject.useASSInsteadOfSSA) {
            i4 = i3 + 1;
            arrayList.add(i3, "Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour, BackColour, Bold, Italic, Underline, StrikeOut, ScaleX, ScaleY, Spacing, Angle, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, Encoding");
        } else {
            i4 = i3 + 1;
            arrayList.add(i3, "Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, TertiaryColour, BackColour, Bold, Italic, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, AlphaLevel, Encoding");
        }
        Hashtable<String, Style> hashtable = timedTextObject.styling;
        for (Style style : DesugarCollections.bridge_synchronizedCollection(hashtable.values(), hashtable)) {
            String str = ((((((("Style: " + style.iD + ",") + style.font + ",") + style.fontSize + ",") + getColorsForASS(timedTextObject.useASSInsteadOfSSA, style)) + getOptionsForASS(timedTextObject.useASSInsteadOfSSA, style)) + "1,2,2,") + getAlignForASS(timedTextObject.useASSInsteadOfSSA, style.textAlign)) + ",0,0,0,";
            if (!timedTextObject.useASSInsteadOfSSA) {
                str = str + "0,";
            }
            arrayList.add(i4, str + "0");
            i4++;
        }
        arrayList.add(i4, "");
        int i9 = i4 + 2;
        arrayList.add(i4 + 1, "[Events]");
        if (timedTextObject.useASSInsteadOfSSA) {
            i5 = i4 + 3;
            arrayList.add(i9, "Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
        } else {
            i5 = i4 + 3;
            arrayList.add(i9, "Format: Marked, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
        }
        for (Subtitle subtitle : timedTextObject.captions.values()) {
            if (timedTextObject.offset != 0) {
                subtitle.start.mseconds += timedTextObject.offset;
                subtitle.end.mseconds += timedTextObject.offset;
            }
            String str2 = ("Dialogue: 0," + subtitle.start.getTime("h:mm:ss.cs") + ",") + subtitle.end.getTime("h:mm:ss.cs") + ",";
            if (timedTextObject.offset != 0) {
                subtitle.start.mseconds -= timedTextObject.offset;
                subtitle.end.mseconds -= timedTextObject.offset;
            }
            arrayList.add(i5, ((subtitle.style != null ? str2 + subtitle.style.iD : str2 + "Default") + ",,0000,0000,0000,,") + subtitle.content.replaceAll("<br />", "�N").replaceAll("\\<.*?\\>", "").replace(Utf8.REPLACEMENT_CHARACTER, '\\'));
            i5++;
        }
        arrayList.add(i5, "");
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i10 = 0; i10 < size; i10++) {
            strArr[i10] = (String) arrayList.get(i10);
        }
        return strArr;
    }

    private Style parseStyleForASS(String[] strArr, String[] strArr2, int i, boolean z, String str) {
        String str2;
        String[] strArr3 = strArr;
        String[] strArr4 = strArr2;
        Style style = new Style(Style.defaultID());
        if (strArr3.length == strArr4.length) {
            String str3 = str;
            int i2 = 0;
            while (i2 < strArr4.length) {
                if (strArr4[i2].trim().equalsIgnoreCase("Name")) {
                    style.iD = strArr3[i2].trim();
                } else if (strArr4[i2].trim().equalsIgnoreCase("Fontname")) {
                    style.font = strArr3[i2].trim();
                } else if (strArr4[i2].trim().equalsIgnoreCase("Fontsize")) {
                    style.fontSize = strArr3[i2].trim();
                } else if (strArr4[i2].trim().equalsIgnoreCase("PrimaryColour")) {
                    String strTrim = strArr3[i2].trim();
                    if (z) {
                        if (strTrim.startsWith("&H")) {
                            style.color = Style.getRGBValue("&HAABBGGRR", strTrim);
                        } else {
                            style.color = Style.getRGBValue("decimalCodedAABBGGRR", strTrim);
                        }
                    } else if (strTrim.startsWith("&H")) {
                        style.color = Style.getRGBValue("&HBBGGRR", strTrim);
                    } else {
                        style.color = Style.getRGBValue("decimalCodedBBGGRR", strTrim);
                    }
                } else if (strArr4[i2].trim().equalsIgnoreCase("BackColour")) {
                    String strTrim2 = strArr3[i2].trim();
                    if (z) {
                        if (strTrim2.startsWith("&H")) {
                            style.backgroundColor = Style.getRGBValue("&HAABBGGRR", strTrim2);
                        } else {
                            style.backgroundColor = Style.getRGBValue("decimalCodedAABBGGRR", strTrim2);
                        }
                    } else if (strTrim2.startsWith("&H")) {
                        style.backgroundColor = Style.getRGBValue("&HBBGGRR", strTrim2);
                    } else {
                        style.backgroundColor = Style.getRGBValue("decimalCodedBBGGRR", strTrim2);
                    }
                } else if (strArr4[i2].trim().equalsIgnoreCase("Bold")) {
                    style.bold = Boolean.parseBoolean(strArr3[i2].trim());
                } else if (strArr4[i2].trim().equalsIgnoreCase("Italic")) {
                    style.italic = Boolean.parseBoolean(strArr3[i2].trim());
                } else if (strArr4[i2].trim().equalsIgnoreCase("Underline")) {
                    style.underline = Boolean.parseBoolean(strArr3[i2].trim());
                } else if (strArr4[i2].trim().equalsIgnoreCase("Alignment")) {
                    int i3 = Integer.parseInt(strArr3[i2].trim());
                    if (z) {
                        switch (i3) {
                            case 1:
                                style.textAlign = "bottom-left";
                                continue;
                            case 2:
                                style.textAlign = "bottom-center";
                                continue;
                            case 3:
                                style.textAlign = "bottom-right";
                                continue;
                            case 4:
                                style.textAlign = "mid-left";
                                continue;
                            case 5:
                                style.textAlign = "mid-center";
                                continue;
                            case 6:
                                style.textAlign = "mid-right";
                                continue;
                            case 7:
                                style.textAlign = "top-left";
                                continue;
                            case 8:
                                style.textAlign = "top-center";
                                continue;
                            case 9:
                                style.textAlign = "top-right";
                                continue;
                            default:
                                str2 = str3 + "undefined alignment for style at line " + i + "\n\n";
                                break;
                        }
                        str3 = str2;
                    } else {
                        switch (i3) {
                            case 1:
                                style.textAlign = "mid-left";
                                continue;
                            case 2:
                                style.textAlign = "mid-center";
                                continue;
                            case 3:
                                style.textAlign = "mid-right";
                                continue;
                            case 4:
                            case 8:
                            default:
                                str2 = str3 + "undefined alignment for style at line " + i + "\n\n";
                                break;
                            case 5:
                                style.textAlign = "top-left";
                                continue;
                            case 6:
                                style.textAlign = "top-center";
                                continue;
                            case 7:
                                style.textAlign = "top-right";
                                continue;
                            case 9:
                                style.textAlign = "bottom-left";
                                continue;
                            case 10:
                                style.textAlign = "bottom-center";
                                continue;
                            case 11:
                                style.textAlign = "bottom-right";
                                continue;
                        }
                        str3 = str2;
                    }
                }
                i2++;
                strArr3 = strArr;
                strArr4 = strArr2;
            }
        }
        return style;
    }

    private Subtitle parseDialogueForASS(String[] strArr, String[] strArr2, float f, TimedTextObject timedTextObject) {
        Subtitle subtitle = new Subtitle();
        subtitle.content = strArr[9].replaceAll("\\{.*?\\}", "").replace(ShellUtil.COMMAND_LINE_END, "<br />").replace("\\N", "<br />");
        for (int i = 0; i < strArr2.length; i++) {
            if (strArr2[i].trim().equalsIgnoreCase("Style")) {
                Style style = timedTextObject.styling.get(strArr[i].trim());
                if (style != null) {
                    subtitle.style = style;
                } else {
                    timedTextObject.warnings += "undefined style: " + strArr[i].trim() + "\n\n";
                }
            } else if (strArr2[i].trim().equalsIgnoreCase("Start")) {
                subtitle.start = new Time("h:mm:ss.cs", strArr[i].trim());
            } else if (strArr2[i].trim().equalsIgnoreCase("End")) {
                subtitle.end = new Time("h:mm:ss.cs", strArr[i].trim());
            }
        }
        if (f != 100.0f) {
            Time time = subtitle.start;
            float f2 = f / 100.0f;
            time.mseconds = (int) (time.mseconds / f2);
            Time time2 = subtitle.end;
            time2.mseconds = (int) (time2.mseconds / f2);
        }
        return subtitle;
    }

    private String getColorsForASS(boolean z, Style style) {
        if (z) {
            return Integer.parseInt("00" + style.color.substring(4, 6) + style.color.substring(2, 4) + style.color.substring(0, 2), 16) + ",16777215,0," + Long.parseLong("80" + style.backgroundColor.substring(4, 6) + style.backgroundColor.substring(2, 4) + style.backgroundColor.substring(0, 2), 16) + ",";
        }
        return Long.parseLong(style.color.substring(4, 6) + style.color.substring(2, 4) + style.color.substring(0, 2), 16) + ",16777215,0," + Long.parseLong(style.backgroundColor.substring(4, 6) + style.backgroundColor.substring(2, 4) + style.backgroundColor.substring(0, 2), 16) + ",";
    }

    private String getOptionsForASS(boolean z, Style style) {
        String strConcat;
        String str;
        String str2 = style.bold ? "-1," : "0,";
        if (style.italic) {
            strConcat = str2.concat("-1,");
        } else {
            strConcat = str2.concat("0,");
        }
        if (!z) {
            return strConcat;
        }
        if (style.underline) {
            str = strConcat + "-1,";
        } else {
            str = strConcat + "0,";
        }
        return str + "0,100,100,0,0,";
    }

    private int getAlignForASS(boolean z, String str) {
        if (z) {
            if ("bottom-left".equals(str)) {
                return 1;
            }
            if (!"bottom-center".equals(str)) {
                if ("bottom-right".equals(str)) {
                    return 3;
                }
                if ("mid-left".equals(str)) {
                    return 4;
                }
                if ("mid-center".equals(str)) {
                    return 5;
                }
                if ("mid-right".equals(str)) {
                    return 6;
                }
                if ("top-left".equals(str)) {
                    return 7;
                }
                if ("top-center".equals(str)) {
                    return 8;
                }
                if ("top-right".equals(str)) {
                    return 9;
                }
            }
            return 2;
        }
        if ("bottom-left".equals(str)) {
            return 9;
        }
        if (!"bottom-center".equals(str)) {
            if ("bottom-right".equals(str)) {
                return 11;
            }
            if ("mid-left".equals(str)) {
                return 1;
            }
            if ("mid-center".equals(str)) {
                return 2;
            }
            if ("mid-right".equals(str)) {
                return 3;
            }
            if ("top-left".equals(str)) {
                return 5;
            }
            if ("top-center".equals(str)) {
                return 6;
            }
            if ("top-right".equals(str)) {
                return 7;
            }
        }
        return 10;
    }
}
