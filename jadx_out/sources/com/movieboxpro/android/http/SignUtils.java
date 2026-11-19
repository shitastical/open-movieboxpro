package com.movieboxpro.android.http;

import com.movieboxpro.android.utils.HexDump;
import com.movieboxpro.android.utils.MD5Util;
import com.movieboxpro.android.utils.MLog;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SignUtils {
    private static final String SECURE_STR = "";
    private static final String TAG = "SignUtils";

    public static String getSignature(List<String> keyValues) {
        if (keyValues == null) {
            return null;
        }
        Collections.sort(keyValues, new Comparator<String>() { // from class: com.movieboxpro.android.http.SignUtils.1
            @Override // java.util.Comparator
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        String str = "";
        for (int i = 0; i < keyValues.size(); i++) {
            str = i < keyValues.size() - 1 ? str + keyValues.get(i) + "&" : str + keyValues.get(i);
        }
        String str2 = str + "";
        MLog.d(TAG, "preSignature: " + str2);
        return HexDump.toHexString(MD5Util.md5(str2)).toUpperCase();
    }
}
