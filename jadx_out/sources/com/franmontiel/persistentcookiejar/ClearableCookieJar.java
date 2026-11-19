package com.franmontiel.persistentcookiejar;

import okhttp3.CookieJar;

/* JADX INFO: loaded from: classes4.dex */
public interface ClearableCookieJar extends CookieJar {
    void clear();

    void clearSession();
}
