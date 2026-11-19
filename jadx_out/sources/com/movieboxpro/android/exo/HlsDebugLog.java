package com.movieboxpro.android.exo;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HlsDebugLog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0010\u0010\t\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/exo/HlsDebugLog;", "", "<init>", "()V", "TAG", "", "i", "", "message", "uri", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HlsDebugLog {
    public static final HlsDebugLog INSTANCE = new HlsDebugLog();
    private static final String TAG = "DEBUG-hls";

    public final void i(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    private HlsDebugLog() {
    }

    public final String uri(Object uri) {
        String string = uri != null ? uri.toString() : null;
        if (string == null) {
            string = "";
        }
        String strSubstringBefore$default = StringsKt.substringBefore$default(string, '?', (String) null, 2, (Object) null);
        String strSubstringAfterLast$default = StringsKt.substringAfterLast$default(strSubstringBefore$default, '/', (String) null, 2, (Object) null);
        if (!StringsKt.isBlank(strSubstringAfterLast$default)) {
            strSubstringBefore$default = strSubstringAfterLast$default;
        }
        return strSubstringBefore$default;
    }
}
