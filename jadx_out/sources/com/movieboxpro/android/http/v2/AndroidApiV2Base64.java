package com.movieboxpro.android.http.v2;

import android.util.Base64;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApiV2Base64.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\n \u0006*\u0004\u0018\u00010\b0\b2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/http/v2/AndroidApiV2Base64;", "Lcom/movieboxpro/android/http/v2/ApiV2Base64;", "<init>", "()V", "encode", "", "kotlin.jvm.PlatformType", "bytes", "", "decode", "value", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AndroidApiV2Base64 implements ApiV2Base64 {
    public static final AndroidApiV2Base64 INSTANCE = new AndroidApiV2Base64();

    private AndroidApiV2Base64() {
    }

    @Override // com.movieboxpro.android.http.v2.ApiV2Base64
    public String encode(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return Base64.encodeToString(bytes, 2);
    }

    @Override // com.movieboxpro.android.http.v2.ApiV2Base64
    public byte[] decode(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return Base64.decode(value, 2);
    }
}
