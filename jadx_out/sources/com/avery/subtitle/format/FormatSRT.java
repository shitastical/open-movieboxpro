package com.avery.subtitle.format;

import android.text.TextUtils;
import android.util.Log;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.Time;
import com.avery.subtitle.model.TimedTextObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTimeConstants;

/* JADX INFO: loaded from: classes4.dex */
public class FormatSRT implements TimedTextFileFormat {
    /* JADX WARN: Code duplicated, block: B:67:0x01a7  */
    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public TimedTextObject parseFile(String str, InputStream inputStream) throws IOException {
        TimedTextObject timedTextObject = new TimedTextObject();
        Subtitle subtitle = new Subtitle();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        timedTextObject.fileName = str;
        String line = bufferedReader.readLine();
        int i = 0;
        while (line != null) {
            try {
                try {
                    int i2 = i + 1;
                    if (line.trim().isEmpty()) {
                        i = i2;
                    } else {
                        int i3 = i + 2;
                        try {
                            String strTrim = bufferedReader.readLine().trim();
                            if (TextUtils.isEmpty(strTrim)) {
                                i = i3;
                                line = strTrim;
                            } else {
                                Matcher matcher = Pattern.compile("\\d+:\\d+:\\d+,\\d+").matcher(strTrim);
                                if (matcher.find()) {
                                    subtitle.start = new Time(parseSubtitleTime(matcher.group()));
                                    if (matcher.find()) {
                                        subtitle.end = new Time(parseSubtitleTime(matcher.group()));
                                    } else {
                                        Log.d("parseTime", "no match:" + strTrim);
                                    }
                                } else {
                                    Matcher matcher2 = Pattern.compile("\\d+:\\d+:\\d+.\\d+").matcher(strTrim);
                                    if (matcher2.find()) {
                                        subtitle.start = new Time(parseSubtitleTime(matcher2.group()));
                                        if (matcher2.find()) {
                                            subtitle.end = new Time(parseSubtitleTime(matcher2.group()));
                                        } else {
                                            Log.d("parseTime", "no match:" + strTrim);
                                        }
                                    } else {
                                        Log.d("parseTime", "no match:" + strTrim);
                                    }
                                }
                                i += 3;
                                String strTrim2 = bufferedReader.readLine().trim();
                                if (strTrim2.isEmpty()) {
                                    do {
                                        strTrim2 = bufferedReader.readLine().trim();
                                        i++;
                                    } while (strTrim2.isEmpty());
                                }
                                StringBuilder sb = new StringBuilder();
                                while (!strTrim2.isEmpty()) {
                                    sb.append(strTrim2);
                                    sb.append("<br />");
                                    strTrim2 = bufferedReader.readLine().trim();
                                    i++;
                                }
                                try {
                                    if (!sb.toString().isEmpty()) {
                                        subtitle.content = sb.toString().substring(0, sb.length() - 6);
                                    } else {
                                        subtitle.content = "";
                                    }
                                } catch (Exception unused) {
                                    subtitle.content = "";
                                }
                                try {
                                    int i4 = subtitle.start.mseconds;
                                    while (timedTextObject.captions.containsKey(Integer.valueOf(i4))) {
                                        i4++;
                                    }
                                    int i5 = subtitle.start.mseconds;
                                    timedTextObject.captions.put(Integer.valueOf(i4), subtitle);
                                    while (!strTrim2.isEmpty()) {
                                        strTrim2 = bufferedReader.readLine().trim();
                                        i++;
                                    }
                                    subtitle = new Subtitle();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception unused2) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("incorrect time format at line ");
                            sb2.append(i3);
                        }
                    }
                    line = bufferedReader.readLine();
                } catch (Throwable th) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.getMessage();
                if (inputStream != null) {
                    inputStream.close();
                }
                timedTextObject.built = true;
                return timedTextObject;
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        timedTextObject.built = true;
        return timedTextObject;
    }

    public static int parseSubtitleTime(String str) {
        int i;
        String[] strArrSplit = str.replace(",", ":").replace(".", ":").split(":");
        if (strArrSplit.length != 4) {
            return 0;
        }
        if (strArrSplit[3].length() == 2) {
            i = Integer.parseInt(strArrSplit[3]) * 10;
        } else {
            i = Integer.parseInt(strArrSplit[3]);
        }
        return (Integer.parseInt(strArrSplit[0]) * DateTimeConstants.MILLIS_PER_HOUR) + (Integer.parseInt(strArrSplit[1]) * DateTimeConstants.MILLIS_PER_MINUTE) + (Integer.parseInt(strArrSplit[2]) * 1000) + i;
    }

    @Override // com.avery.subtitle.format.TimedTextFileFormat
    public String[] toFile(TimedTextObject timedTextObject) {
        if (!timedTextObject.built) {
            return null;
        }
        ArrayList arrayList = new ArrayList(timedTextObject.captions.size() * 5);
        int i = 1;
        int i2 = 0;
        for (Subtitle subtitle : timedTextObject.captions.values()) {
            int i3 = i2 + 1;
            StringBuilder sb = new StringBuilder("");
            int i4 = i + 1;
            sb.append(i);
            arrayList.add(i2, sb.toString());
            if (timedTextObject.offset != 0) {
                subtitle.start.mseconds += timedTextObject.offset;
                subtitle.end.mseconds += timedTextObject.offset;
            }
            int i5 = i2 + 2;
            arrayList.add(i3, subtitle.start.getTime("hh:mm:ss,ms") + " --> " + subtitle.end.getTime("hh:mm:ss,ms"));
            if (timedTextObject.offset != 0) {
                subtitle.start.mseconds -= timedTextObject.offset;
                subtitle.end.mseconds -= timedTextObject.offset;
            }
            String[] strArrCleanTextForSRT = cleanTextForSRT(subtitle);
            for (String str : strArrCleanTextForSRT) {
                arrayList.add(i5, "" + str);
                i5++;
            }
            arrayList.add(i5, "");
            i2 = i5 + 1;
            i = i4;
        }
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i6 = 0; i6 < size; i6++) {
            strArr[i6] = (String) arrayList.get(i6);
        }
        return strArr;
    }

    private String[] cleanTextForSRT(Subtitle subtitle) {
        String[] strArrSplit = subtitle.content.split("<br />");
        for (int i = 0; i < strArrSplit.length; i++) {
            strArrSplit[i] = strArrSplit[i].replaceAll("\\<.*?\\>", "");
        }
        return strArrSplit;
    }
}
