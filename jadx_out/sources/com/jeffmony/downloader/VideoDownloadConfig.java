package com.jeffmony.downloader;

/* JADX INFO: loaded from: classes5.dex */
public class VideoDownloadConfig {
    private String mCacheRoot;
    private int mConcurrentCount;
    private int mConnTimeOut;
    private boolean mIgnoreAllCertErrors;
    private int mReadTimeOut;
    private boolean mShouldM3U8Merged;

    public VideoDownloadConfig(String str, int i, int i2, boolean z, int i3, boolean z2) {
        this.mCacheRoot = str;
        this.mReadTimeOut = i;
        this.mConnTimeOut = i2;
        this.mIgnoreAllCertErrors = z;
        this.mConcurrentCount = i3;
        this.mShouldM3U8Merged = z2;
    }

    public String getCacheRoot() {
        return this.mCacheRoot;
    }

    public int getReadTimeOut() {
        return this.mReadTimeOut;
    }

    public int getConnTimeOut() {
        return this.mConnTimeOut;
    }

    public boolean shouldIgnoreCertErrors() {
        return this.mIgnoreAllCertErrors;
    }

    public void setIgnoreAllCertErrors(boolean z) {
        this.mIgnoreAllCertErrors = z;
    }

    public int getConcurrentCount() {
        return this.mConcurrentCount;
    }

    public void setConcurrentCount(int i) {
        this.mConcurrentCount = i;
    }

    public void setShouldM3U8Merged(boolean z) {
        this.mShouldM3U8Merged = z;
    }

    public boolean shouldM3U8Merged() {
        return this.mShouldM3U8Merged;
    }
}
