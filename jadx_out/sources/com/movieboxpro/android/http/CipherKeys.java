package com.movieboxpro.android.http;

/* JADX INFO: loaded from: classes5.dex */
public abstract class CipherKeys {
    private String appKey;
    private String iv;
    private String key;

    protected CipherKeys(String appKey, String key, String iv) {
        this.appKey = appKey;
        this.key = key;
        this.iv = iv;
    }

    public static CipherKeys getDefaultKeys() {
        return getCiperKeys();
    }

    public static CipherKeys getCiperKeys() {
        return new StoneCipherKeys();
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getKey() {
        return this.key;
    }

    public String getIv() {
        return this.iv;
    }
}
