package com.movieboxpro.android.http.v2.session;

import kotlin.Metadata;

/* JADX INFO: compiled from: SessionKeyCipher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;", "", "encrypt", "", "value", "decrypt", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SessionKeyCipher {
    byte[] decrypt(byte[] value);

    byte[] encrypt(byte[] value);
}
