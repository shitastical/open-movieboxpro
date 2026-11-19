package com.movieboxpro.android.utils;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/utils/CommUtils;", "", "<init>", "()V", "getRealName", "", "name", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CommUtils {
    public static final CommUtils INSTANCE = new CommUtils();

    private CommUtils() {
    }

    @JvmStatic
    public static final String getRealName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = name;
        int length = str.length() - 1;
        if (length < 0) {
            length = -1;
            break;
        }
        while (true) {
            int i = length - 1;
            if (str.charAt(length) == '-') {
                break;
            }
            if (i < 0) {
                length = -1;
                break;
            }
            length = i;
        }
        if (length != -1) {
            String strSubstring = name.substring(0, length);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }
        return "";
    }
}
