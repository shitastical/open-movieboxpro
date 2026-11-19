package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.images.WebImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzeg {
    private static final String[] zzaav;
    private static final String zzaaw;
    private static final zzdw zzbf = new zzdw("MetadataUtils");

    public static void zza(List<WebImage> list, JSONArray jSONArray) {
        try {
            list.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    list.add(new WebImage(jSONArray.getJSONObject(i)));
                } catch (IllegalArgumentException unused) {
                }
            }
        } catch (JSONException unused2) {
        }
    }

    public static JSONArray zze(List<WebImage> list) {
        if (list == null && list.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<WebImage> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJson());
        }
        return jSONArray;
    }

    public static String zza(Calendar calendar) {
        if (calendar == null) {
            zzbf.d("Calendar object cannot be null", new Object[0]);
            return null;
        }
        String str = zzaaw;
        if (calendar.get(11) == 0 && calendar.get(12) == 0 && calendar.get(13) == 0) {
            str = "yyyyMMdd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        String str2 = simpleDateFormat.format(calendar.getTime());
        return str2.endsWith("+0000") ? str2.replace("+0000", zzaav[0]) : str2;
    }

    public static Calendar zzv(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            zzbf.d("Input string is empty or null", new Object[0]);
            return null;
        }
        String strZzw = zzw(str);
        if (TextUtils.isEmpty(strZzw)) {
            zzbf.d("Invalid date format", new Object[0]);
            return null;
        }
        String strZzx = zzx(str);
        if (TextUtils.isEmpty(strZzx)) {
            str2 = "yyyyMMdd";
        } else {
            String strValueOf = String.valueOf(strZzw);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 1 + String.valueOf(strZzx).length());
            sb.append(strValueOf);
            sb.append(ExifInterface.GPS_DIRECTION_TRUE);
            sb.append(strZzx);
            strZzw = sb.toString();
            if (strZzx.length() == 6) {
                str2 = "yyyyMMdd'T'HHmmss";
            } else {
                str2 = zzaaw;
            }
        }
        Calendar gregorianCalendar = GregorianCalendar.getInstance();
        try {
            gregorianCalendar.setTime(new SimpleDateFormat(str2).parse(strZzw));
            return gregorianCalendar;
        } catch (ParseException e) {
            zzbf.d("Error parsing string: %s", e.getMessage());
            return null;
        }
    }

    private static String zzw(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbf.d("Input string is empty or null", new Object[0]);
            return null;
        }
        try {
            return str.substring(0, 8);
        } catch (IndexOutOfBoundsException e) {
            zzbf.i("Error extracting the date: %s", e.getMessage());
            return null;
        }
    }

    private static String zzx(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbf.d("string is empty or null", new Object[0]);
            return null;
        }
        int iIndexOf = str.indexOf(84);
        int i = iIndexOf + 1;
        if (iIndexOf != 8) {
            zzbf.d("T delimeter is not found", new Object[0]);
            return null;
        }
        try {
            String strSubstring = str.substring(i);
            if (strSubstring.length() == 6) {
                return strSubstring;
            }
            char cCharAt = strSubstring.charAt(6);
            if (cCharAt != '+' && cCharAt != '-') {
                if (cCharAt == 'Z' && strSubstring.length() == zzaav[0].length() + 6) {
                    return String.valueOf(strSubstring.substring(0, strSubstring.length() - 1)).concat("+0000");
                }
                return null;
            }
            int length = strSubstring.length();
            String[] strArr = zzaav;
            if (length == strArr[1].length() + 6 || length == strArr[2].length() + 6 || length == strArr[3].length() + 6) {
                return strSubstring.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            zzbf.d("Error extracting the time substring: %s", e.getMessage());
            return null;
        }
    }

    static {
        String[] strArr = {"Z", "+hh", "+hhmm", "+hh:mm"};
        zzaav = strArr;
        String strValueOf = String.valueOf(strArr[0]);
        zzaaw = strValueOf.length() != 0 ? "yyyyMMdd'T'HHmmss".concat(strValueOf) : new String("yyyyMMdd'T'HHmmss");
    }
}
