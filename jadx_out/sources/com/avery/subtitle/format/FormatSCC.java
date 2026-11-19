package com.avery.subtitle.format;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.location.LocationRequestCompat;
import androidx.core.text.HtmlCompat;
import androidx.media3.common.PlaybackException;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.ts.TsExtractor;
import com.alibaba.fastjson.asm.Opcodes;
import com.avery.subtitle.exception.FatalParsingException;
import com.avery.subtitle.model.Style;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.Time;
import com.avery.subtitle.model.TimedTextObject;
import com.google.zxing.pdf417.PDF417Common;
import com.kaopiz.kprogresshud.BuildConfig;
import com.movieboxpro.android.utils.ScreenUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import okhttp3.internal.http.HttpStatusCodesKt;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes4.dex */
public class FormatSCC implements TimedTextFileFormat {
    private void decodeXtChar(String str, int i) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:118:0x02cf A[PHI: r0 r13
      0x02cf: PHI (r0v40 boolean) = (r0v31 boolean), (r0v46 boolean) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]
      0x02cf: PHI (r13v13 java.lang.String) = (r13v3 java.lang.String), (r13v15 java.lang.String) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:119:0x02d9 A[PHI: r0 r13
      0x02d9: PHI (r0v39 boolean) = (r0v31 boolean), (r0v46 boolean) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]
      0x02d9: PHI (r13v12 java.lang.String) = (r13v3 java.lang.String), (r13v15 java.lang.String) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:120:0x02de A[PHI: r0 r13
      0x02de: PHI (r0v37 boolean) = (r0v31 boolean), (r0v46 boolean) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]
      0x02de: PHI (r13v9 java.lang.String) = (r13v3 java.lang.String), (r13v15 java.lang.String) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:122:0x02e5 A[PHI: r0 r13
      0x02e5: PHI (r0v36 boolean) = (r0v31 boolean), (r0v46 boolean) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]
      0x02e5: PHI (r13v8 java.lang.String) = (r13v3 java.lang.String), (r13v15 java.lang.String) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:123:0x02ed A[PHI: r0 r13
      0x02ed: PHI (r0v35 boolean) = (r0v31 boolean), (r0v46 boolean) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]
      0x02ed: PHI (r13v7 java.lang.String) = (r13v3 java.lang.String), (r13v15 java.lang.String) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:124:0x02f5 A[PHI: r0 r13
      0x02f5: PHI (r0v34 boolean) = (r0v31 boolean), (r0v46 boolean) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]
      0x02f5: PHI (r13v6 java.lang.String) = (r13v3 java.lang.String), (r13v15 java.lang.String) binds: [B:114:0x02be, B:104:0x029f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:144:0x0363  */
    /* JADX WARN: Code duplicated, block: B:177:0x0405  */
    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public TimedTextObject parseFile(String str, InputStream inputStream) throws Throwable {
        int i;
        FormatSCC formatSCC;
        BufferedReader bufferedReader;
        int i2;
        int i3;
        boolean z;
        Subtitle subtitle;
        FormatSCC formatSCC2 = this;
        TimedTextObject timedTextObject = new TimedTextObject();
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
        timedTextObject.fileName = str;
        timedTextObject.title = str;
        try {
            try {
                try {
                    if (!bufferedReader2.readLine().trim().equalsIgnoreCase("Scenarist_SCC V1.0")) {
                        try {
                            throw new FatalParsingException("The fist line should define the file type: \"Scenarist_SCC V1.0\"");
                        } catch (NullPointerException unused) {
                            i = 1;
                            timedTextObject.warnings += "unexpected end of file at line " + i + ", maybe last caption is not complete.\n\n";
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            timedTextObject.built = true;
                            return timedTextObject;
                        }
                    }
                    formatSCC2.createSCCStyles(timedTextObject);
                    timedTextObject.warnings += "Only data from CC channel 1 will be extracted.\n\n";
                    String line = bufferedReader2.readLine();
                    Subtitle subtitle2 = null;
                    String str2 = null;
                    String string = "";
                    i = 1;
                    boolean z2 = false;
                    boolean z3 = true;
                    boolean z4 = false;
                    boolean z5 = false;
                    try {
                        while (line != null) {
                            try {
                                String strTrim = line.trim();
                                i++;
                                try {
                                    if (strTrim.isEmpty()) {
                                        formatSCC = formatSCC2;
                                        bufferedReader = bufferedReader2;
                                        i2 = i;
                                    } else {
                                        String[] strArrSplit = strTrim.split("\t");
                                        String str3 = str2;
                                        StringBuilder sb = new StringBuilder();
                                        i2 = i;
                                        try {
                                            sb.append(strArrSplit[0]);
                                            sb.append("/29.97");
                                            Time time = new Time("h:m:s:f/fps", sb.toString());
                                            String[] strArrSplit2 = strArrSplit[1].split(" ");
                                            str2 = str3;
                                            int i4 = 0;
                                            while (i4 < strArrSplit2.length) {
                                                int i5 = Integer.parseInt(strArrSplit2[i4], 16);
                                                int i6 = i5 & 32639;
                                                BufferedReader bufferedReader3 = bufferedReader2;
                                                if ((i5 & 24576) != 0) {
                                                    if (z2) {
                                                        byte b = (byte) ((i5 & 32512) >>> 8);
                                                        byte b2 = (byte) (i5 & 127);
                                                        if (z3) {
                                                            string = (string + formatSCC2.decodeChar(b)) + formatSCC2.decodeChar(b2);
                                                        } else {
                                                            subtitle2.content += formatSCC2.decodeChar(b);
                                                            subtitle2.content += formatSCC2.decodeChar(b2);
                                                        }
                                                        strArrSplit2 = strArrSplit2;
                                                        this = formatSCC2;
                                                    } else {
                                                        z3 = z3;
                                                        this = this;
                                                        strArrSplit2 = strArrSplit2;
                                                    }
                                                    z3 = z3;
                                                    i3 = 1;
                                                    try {
                                                        i4 += i3;
                                                        formatSCC2 = this;
                                                        bufferedReader2 = bufferedReader3;
                                                        strArrSplit2 = strArrSplit2;
                                                    } catch (NullPointerException unused2) {
                                                        i = i2;
                                                        timedTextObject.warnings += "unexpected end of file at line " + i + ", maybe last caption is not complete.\n\n";
                                                        if (inputStream != null) {
                                                            inputStream.close();
                                                        }
                                                        timedTextObject.built = true;
                                                        return timedTextObject;
                                                    }
                                                } else {
                                                    z3 = z3;
                                                    if (i6 == 0) {
                                                        try {
                                                            time.mseconds = (int) (((double) time.mseconds) + 33.366700033366705d);
                                                        } catch (NullPointerException unused3) {
                                                            i = i2;
                                                            timedTextObject.warnings += "unexpected end of file at line " + i + ", maybe last caption is not complete.\n\n";
                                                            if (inputStream != null) {
                                                                inputStream.close();
                                                            }
                                                            timedTextObject.built = true;
                                                            return timedTextObject;
                                                        } catch (Throwable th) {
                                                            th = th;
                                                        }
                                                    } else {
                                                        int i7 = i4 + 1;
                                                        if (i7 < strArrSplit2.length && strArrSplit2[i4].equals(strArrSplit2[i7])) {
                                                            i4 = i7;
                                                        }
                                                        if ((i5 & 2048) != 0) {
                                                            this = this;
                                                            strArrSplit2 = strArrSplit2;
                                                            z3 = z3;
                                                            i3 = 1;
                                                            z2 = false;
                                                        } else if ((i5 & 5744) != 5152) {
                                                            if (z2) {
                                                                strArrSplit2 = strArrSplit2;
                                                                if ((i5 & 4160) == 4160) {
                                                                    if (z3 && !string.isEmpty()) {
                                                                        string = string + "<br />";
                                                                    }
                                                                    if (!z3 && !subtitle2.content.isEmpty()) {
                                                                        subtitle2.content += "<br />";
                                                                    }
                                                                    z = (i5 & 1) == 1;
                                                                    if ((i5 & 16) != 16) {
                                                                        switch ((short) ((i5 & 14) >> 1)) {
                                                                            case 0:
                                                                                break;
                                                                            case 1:
                                                                                this = this;
                                                                                z4 = z;
                                                                                str2 = "green";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 2:
                                                                                this = this;
                                                                                z4 = z;
                                                                                str2 = "blue";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 3:
                                                                                this = this;
                                                                                z4 = z;
                                                                                str2 = "cyan";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 4:
                                                                                z4 = z;
                                                                                str2 = "red";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 5:
                                                                                z4 = z;
                                                                                str2 = "yellow";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 6:
                                                                                z4 = z;
                                                                                str2 = "magenta";
                                                                                z3 = z3;
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                this = this;
                                                                                break;
                                                                            case 7:
                                                                                z4 = z;
                                                                                str2 = "white";
                                                                                i3 = 1;
                                                                                z5 = true;
                                                                                break;
                                                                            default:
                                                                                break;
                                                                        }
                                                                    }
                                                                    this = this;
                                                                    z4 = z;
                                                                    str2 = "white";
                                                                    i3 = 1;
                                                                    z5 = false;
                                                                } else {
                                                                    int i8 = i5 & PlaybackException.ERROR_CODE_DRM_UNSPECIFIED;
                                                                    if (i8 == 4384) {
                                                                        z = (i5 & 1) == 1;
                                                                        switch ((short) ((i5 & 14) >> 1)) {
                                                                            case 0:
                                                                                this = this;
                                                                                z4 = z;
                                                                                str2 = "white";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 1:
                                                                                this = this;
                                                                                z4 = z;
                                                                                str2 = "green";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 2:
                                                                                this = this;
                                                                                z4 = z;
                                                                                str2 = "blue";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 3:
                                                                                this = this;
                                                                                z4 = z;
                                                                                str2 = "cyan";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 4:
                                                                                z4 = z;
                                                                                str2 = "red";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 5:
                                                                                z4 = z;
                                                                                str2 = "yellow";
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                break;
                                                                            case 6:
                                                                                z4 = z;
                                                                                str2 = "magenta";
                                                                                z3 = z3;
                                                                                i3 = 1;
                                                                                z5 = false;
                                                                                this = this;
                                                                                break;
                                                                            case 7:
                                                                                z4 = z;
                                                                                i3 = 1;
                                                                                z5 = true;
                                                                                break;
                                                                            default:
                                                                                this = this;
                                                                                z4 = z;
                                                                                break;
                                                                        }
                                                                    } else if ((i5 & 6012) == 5920) {
                                                                        this = this;
                                                                    } else if (i8 == 4400) {
                                                                        int i9 = i5 & 15;
                                                                        if (z3) {
                                                                            StringBuilder sb2 = new StringBuilder();
                                                                            sb2.append(string);
                                                                            this = this;
                                                                            sb2.append(this.decodeSpecialChar(i9));
                                                                            string = sb2.toString();
                                                                        } else {
                                                                            this = this;
                                                                            subtitle2.content += this.decodeSpecialChar(i9);
                                                                        }
                                                                    } else {
                                                                        this = this;
                                                                        if ((i5 & 5728) == 4640) {
                                                                            int i10 = i5 & 287;
                                                                            if (z3) {
                                                                                this.decodeXtChar(string, i10);
                                                                            } else {
                                                                                this.decodeXtChar(subtitle2.content, i10);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            z3 = z3;
                                                            i3 = 1;
                                                        } else if ((i5 & 256) == 0) {
                                                            int i11 = i5 & 15;
                                                            if (i11 != 0) {
                                                                if (i11 != 9) {
                                                                    if (i11 != 12) {
                                                                        if (i11 == 5 || i11 == 6 || i11 == 7) {
                                                                            if (subtitle2 != null) {
                                                                                subtitle2.end = time;
                                                                                String str4 = "" + str2;
                                                                                if (z4) {
                                                                                    str4 = str4 + "U";
                                                                                }
                                                                                if (z5) {
                                                                                    str4 = str4 + "I";
                                                                                }
                                                                                subtitle2.style = timedTextObject.styling.get(str4);
                                                                                timedTextObject.captions.put(Integer.valueOf(subtitle2.start.mseconds), subtitle2);
                                                                            }
                                                                            Subtitle subtitle3 = new Subtitle();
                                                                            subtitle3.start = time;
                                                                            subtitle2 = subtitle3;
                                                                            string = "";
                                                                        } else if (i11 == 14) {
                                                                            string = "";
                                                                        } else if (i11 == 15) {
                                                                            subtitle = new Subtitle();
                                                                            subtitle.start = time;
                                                                            subtitle.content += string;
                                                                            subtitle2 = subtitle;
                                                                        }
                                                                    } else if (subtitle2 != null) {
                                                                        subtitle2.end = time;
                                                                        if (subtitle2.start != null) {
                                                                            for (int i12 = subtitle2.start.mseconds; timedTextObject.captions.containsKey(Integer.valueOf(i12)); i12++) {
                                                                            }
                                                                            timedTextObject.captions.put(Integer.valueOf(subtitle2.start.mseconds), subtitle2);
                                                                            subtitle = new Subtitle();
                                                                            subtitle2 = subtitle;
                                                                        }
                                                                    }
                                                                    z3 = z3;
                                                                    i3 = 1;
                                                                    z2 = true;
                                                                } else {
                                                                    Subtitle subtitle4 = new Subtitle();
                                                                    subtitle4.start = time;
                                                                    subtitle2 = subtitle4;
                                                                }
                                                                i3 = 1;
                                                                z2 = true;
                                                                z3 = false;
                                                            } else {
                                                                this = this;
                                                                strArrSplit2 = strArrSplit2;
                                                                string = "";
                                                                i3 = 1;
                                                                z2 = true;
                                                                z3 = true;
                                                            }
                                                        } else {
                                                            this = this;
                                                            strArrSplit2 = strArrSplit2;
                                                            z3 = z3;
                                                            i3 = 1;
                                                            z2 = false;
                                                        }
                                                        i4 += i3;
                                                        formatSCC2 = this;
                                                        bufferedReader2 = bufferedReader3;
                                                        strArrSplit2 = strArrSplit2;
                                                    }
                                                    this = this;
                                                    strArrSplit2 = strArrSplit2;
                                                    z3 = z3;
                                                    i3 = 1;
                                                    i4 += i3;
                                                    formatSCC2 = this;
                                                    bufferedReader2 = bufferedReader3;
                                                    strArrSplit2 = strArrSplit2;
                                                }
                                                timedTextObject.built = true;
                                                return timedTextObject;
                                            }
                                            formatSCC = formatSCC2;
                                            bufferedReader = bufferedReader2;
                                        } catch (NullPointerException unused4) {
                                        }
                                    }
                                    line = bufferedReader.readLine();
                                    formatSCC2 = formatSCC;
                                    i = i2;
                                    bufferedReader2 = bufferedReader;
                                } catch (NullPointerException unused5) {
                                }
                            } catch (NullPointerException unused6) {
                            }
                        }
                        subtitle2.end = new Time("h:m:s:f/fps", "99:59:59:29/29.97");
                        if (subtitle2.start != null) {
                            for (int i13 = subtitle2.start.mseconds; timedTextObject.captions.containsKey(Integer.valueOf(i13)); i13++) {
                            }
                            timedTextObject.captions.put(Integer.valueOf(subtitle2.start.mseconds), subtitle2);
                        }
                        timedTextObject.cleanUnusedStyles();
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (NullPointerException unused7) {
                        timedTextObject.warnings += "unexpected end of file at line " + i + ", maybe last caption is not complete.\n\n";
                        if (inputStream != null) {
                        }
                        timedTextObject.built = true;
                        return timedTextObject;
                    }
                    timedTextObject.built = true;
                    return timedTextObject;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (NullPointerException unused8) {
            }
        } catch (Throwable th3) {
            th = th3;
        }
        if (inputStream != null) {
            inputStream.close();
        }
        throw th;
    }

    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public String[] toFile(TimedTextObject timedTextObject) {
        String str;
        if (!timedTextObject.built) {
            return null;
        }
        ArrayList arrayList = new ArrayList((timedTextObject.captions.size() * 2) + 20);
        arrayList.add(0, "Scenarist_SCC V1.0\n");
        Subtitle subtitle = new Subtitle();
        subtitle.content = "";
        subtitle.end = new Time("h:mm:ss.cs", "0:00:00.00");
        int i = 1;
        for (Subtitle subtitle2 : timedTextObject.captions.values()) {
            if (subtitle.end.mseconds > subtitle2.start.mseconds) {
                subtitle2.content += "<br />" + subtitle.content;
                Time time = subtitle2.start;
                time.mseconds = (int) (((double) time.mseconds) - 33.366700033366705d);
                String str2 = "" + subtitle2.start.getTime("hh:mm:ss:ff/29.97") + "\t942c 942c ";
                Time time2 = subtitle2.start;
                time2.mseconds = (int) (((double) time2.mseconds) + 33.366700033366705d);
                str = str2 + "94ae 94ae 9420 9420 ";
            } else if (subtitle.end.mseconds < subtitle2.start.mseconds) {
                String str3 = "" + subtitle.end.getTime("hh:mm:ss:ff/29.97") + "\t942c 942c\n\n";
                Time time3 = subtitle2.start;
                time3.mseconds = (int) (((double) time3.mseconds) - 33.366700033366705d);
                str = str3 + subtitle2.start.getTime("hh:mm:ss:ff/29.97") + "\t94ae 94ae 9420 9420 ";
                Time time4 = subtitle2.start;
                time4.mseconds = (int) (((double) time4.mseconds) + 33.366700033366705d);
            } else {
                Time time5 = subtitle2.start;
                time5.mseconds = (int) (((double) time5.mseconds) - 33.366700033366705d);
                str = "" + subtitle2.start.getTime("hh:mm:ss:ff/29.97") + "\t942c 942c 94ae 94ae 9420 9420 ";
                Time time6 = subtitle2.start;
                time6.mseconds = (int) (((double) time6.mseconds) + 33.366700033366705d);
            }
            arrayList.add(i, (str + codeText(subtitle2)) + "8080 8080 942f 942f\n");
            subtitle = subtitle2;
            i++;
        }
        arrayList.add(i, "");
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = (String) arrayList.get(i2);
        }
        return strArr;
    }

    private String codeText(Subtitle subtitle) {
        String[] strArrSplit = subtitle.content.split("<br />");
        if (strArrSplit[0].length() > 32) {
            strArrSplit[0] = strArrSplit[0].substring(0, 32);
        }
        int length = ((32 - strArrSplit[0].length()) / 2) % 4;
        String str = "1340 1340 " + codeChar(strArrSplit[0].toCharArray());
        if (strArrSplit.length <= 1) {
            return str;
        }
        if (strArrSplit[1].length() > 32) {
            strArrSplit[1] = strArrSplit[1].substring(0, 32);
        }
        int length2 = ((32 - strArrSplit[1].length()) / 2) % 4;
        String str2 = (str + "13e0 13e0 ") + codeChar(strArrSplit[1].toCharArray());
        if (strArrSplit.length <= 2) {
            return str2;
        }
        if (strArrSplit[2].length() > 32) {
            strArrSplit[2] = strArrSplit[2].substring(0, 32);
        }
        int length3 = ((32 - strArrSplit[2].length()) / 2) % 4;
        String str3 = (str2 + "9440 9440 ") + codeChar(strArrSplit[2].toCharArray());
        if (strArrSplit.length <= 3) {
            return str3;
        }
        if (strArrSplit[3].length() > 32) {
            strArrSplit[3] = strArrSplit[3].substring(0, 32);
        }
        String str4 = str3 + "94e0 94e0 ";
        int length4 = ((32 - strArrSplit[3].length()) / 2) % 4;
        return str4 + codeChar(strArrSplit[3].toCharArray());
    }

    private String codeChar(char[] cArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < cArr.length) {
            char c = cArr[i];
            if (c == '|') {
                sb.append("7f");
            } else if (c != 65533) {
                switch (c) {
                    case ' ':
                        sb.append("20");
                        break;
                    case '!':
                        sb.append("a1");
                        break;
                    case '\"':
                        sb.append("a2");
                        break;
                    case '#':
                        sb.append("23");
                        break;
                    case '$':
                        sb.append("a4");
                        break;
                    case '%':
                        sb.append("25");
                        break;
                    case '&':
                        sb.append("26");
                        break;
                    case '\'':
                        sb.append("a7");
                        break;
                    case '(':
                        sb.append("a8");
                        break;
                    case ')':
                        sb.append("29");
                        break;
                    default:
                        switch (c) {
                            case '+':
                                sb.append("ab");
                                break;
                            case ',':
                                sb.append("2c");
                                break;
                            case '-':
                                sb.append("ad");
                                break;
                            case '.':
                                sb.append("ae");
                                break;
                            case '/':
                                sb.append("2f");
                                break;
                            case '0':
                                sb.append("b0");
                                break;
                            case '1':
                                sb.append("31");
                                break;
                            case '2':
                                sb.append("32");
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                sb.append("b3");
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                sb.append("34");
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                sb.append("b5");
                                break;
                            case '6':
                                sb.append("b6");
                                break;
                            case '7':
                                sb.append("37");
                                break;
                            case Opcodes.FSTORE /* 56 */:
                                sb.append("38");
                                break;
                            case Opcodes.DSTORE /* 57 */:
                                sb.append("b9");
                                break;
                            case Opcodes.ASTORE /* 58 */:
                                sb.append("ba");
                                break;
                            case ';':
                                sb.append("3b");
                                break;
                            case '<':
                                sb.append("bc");
                                break;
                            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                                sb.append("3d");
                                break;
                            case '>':
                                sb.append("3e");
                                break;
                            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                                sb.append("bf");
                                break;
                            case '@':
                                sb.append("40");
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                                sb.append("c1");
                                break;
                            case 'B':
                                sb.append("c2");
                                break;
                            case 'C':
                                sb.append("43");
                                break;
                            case 'D':
                                sb.append("c4");
                                break;
                            case 'E':
                                sb.append("45");
                                break;
                            case 'F':
                                sb.append("46");
                                break;
                            case TsExtractor.TS_SYNC_BYTE /* 71 */:
                                sb.append("c7");
                                break;
                            case 'H':
                                sb.append("c8");
                                break;
                            case 'I':
                                sb.append("49");
                                break;
                            case 'J':
                                sb.append("4a");
                                break;
                            case MdtaMetadataEntry.TYPE_INDICATOR_8_BIT_UNSIGNED_INT /* 75 */:
                                sb.append("cb");
                                break;
                            case 'L':
                                sb.append("4c");
                                break;
                            case IjkMediaMeta.FF_PROFILE_H264_MAIN /* 77 */:
                                sb.append("cd");
                                break;
                            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
                                sb.append("ce");
                                break;
                            case Opcodes.IASTORE /* 79 */:
                                sb.append("4f");
                                break;
                            case ScreenUtils.DEFAULT_DAY_BRIGHTNESS /* 80 */:
                                sb.append("d0");
                                break;
                            case 'Q':
                                sb.append("51");
                                break;
                            case 'R':
                                sb.append("52");
                                break;
                            case 'S':
                                sb.append("d3");
                                break;
                            case 'T':
                                sb.append("54");
                                break;
                            case 'U':
                                sb.append("d5");
                                break;
                            case 'V':
                                sb.append("d6");
                                break;
                            case Opcodes.POP /* 87 */:
                                sb.append("57");
                                break;
                            case IjkMediaMeta.FF_PROFILE_H264_EXTENDED /* 88 */:
                                sb.append("58");
                                break;
                            case 'Y':
                                sb.append("d9");
                                break;
                            case PDF417Common.MAX_ROWS_IN_BARCODE /* 90 */:
                                sb.append("da");
                                break;
                            case '[':
                                sb.append("5b");
                                break;
                            default:
                                switch (c) {
                                    case 'a':
                                        sb.append("61");
                                        break;
                                    case 'b':
                                        sb.append("62");
                                        break;
                                    case 'c':
                                        sb.append("e3");
                                        break;
                                    case 'd':
                                        sb.append("64");
                                        break;
                                    case 'e':
                                        sb.append("e5");
                                        break;
                                    case 'f':
                                        sb.append("e6");
                                        break;
                                    case HttpStatusCodesKt.HTTP_EARLY_HINTS /* 103 */:
                                        sb.append("67");
                                        break;
                                    case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
                                        sb.append("68");
                                        break;
                                    case 'i':
                                        sb.append("e9");
                                        break;
                                    case 'j':
                                        sb.append("ea");
                                        break;
                                    case 'k':
                                        sb.append("6b");
                                        break;
                                    case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR /* 108 */:
                                        sb.append("ec");
                                        break;
                                    case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY /* 109 */:
                                        sb.append("6d");
                                        break;
                                    case IjkMediaMeta.FF_PROFILE_H264_HIGH_10 /* 110 */:
                                        sb.append("6e");
                                        break;
                                    case 'o':
                                        sb.append("ef");
                                        break;
                                    case 'p':
                                        sb.append("70");
                                        break;
                                    case 'q':
                                        sb.append("f1");
                                        break;
                                    case 'r':
                                        sb.append("f2");
                                        break;
                                    case 's':
                                        sb.append("73");
                                        break;
                                    case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                                        sb.append("f4");
                                        break;
                                    case 'u':
                                        sb.append("75");
                                        break;
                                    case 'v':
                                        sb.append("76");
                                        break;
                                    case 'w':
                                        sb.append("f7");
                                        break;
                                    case BuildConfig.VERSION_CODE /* 120 */:
                                        sb.append("f8");
                                        break;
                                    case 'y':
                                        sb.append("79");
                                        break;
                                    case IjkMediaMeta.FF_PROFILE_H264_HIGH_422 /* 122 */:
                                        sb.append("7a");
                                        break;
                                    default:
                                        sb.append("7f");
                                        break;
                                }
                                break;
                        }
                        break;
                }
            } else {
                sb.append("2a");
            }
            if (i % 2 == 1) {
                sb.append(" ");
            }
            i++;
        }
        if (i % 2 == 1) {
            sb.append("80 ");
        }
        return sb.toString();
    }

    private String decodeChar(byte b) {
        if (b == 0) {
            return "";
        }
        if (b == 42) {
            return "�";
        }
        if (b == 92) {
            return "é";
        }
        switch (b) {
            case 94:
                return "í";
            case 95:
                return "ó";
            case Opcodes.IADD /* 96 */:
                return "ú";
            default:
                switch (b) {
                    case 123:
                        return "ç";
                    case 124:
                        return "�";
                    case 125:
                        return "Ñ";
                    case 126:
                        return "ñ";
                    case 127:
                        return "|";
                    default:
                        return "" + ((char) b);
                }
        }
    }

    private String decodeSpecialChar(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return "�";
            case 7:
                return "♪";
            case 8:
                return "�";
            case 9:
                return " ";
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return "�";
            default:
                return "";
        }
    }

    private void createSCCStyles(TimedTextObject timedTextObject) {
        Style style = new Style("white");
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
    }
}
