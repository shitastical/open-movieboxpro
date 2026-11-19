package com.jeffmony.downloader.m3u8;

import android.net.Uri;
import android.text.TextUtils;
import com.jeffmony.downloader.utils.VideoDownloadUtils;

/* JADX INFO: loaded from: classes5.dex */
public class M3U8Seg implements Comparable<M3U8Seg> {
    private long mContentLength;
    private float mDuration;
    private boolean mHasDiscontinuity;
    private boolean mHasInitSegment;
    private boolean mHasKey;
    private int mIndex;
    private String mInitSegmentUri;
    private boolean mIsMessyKey;
    private String mKeyIV;
    private String mKeyUri;
    private String mMethod;
    private String mName;
    private int mRetryCount;
    private String mSegmentByteRange;
    private int mSequence;
    private long mTsSize;
    private String mUrl;

    public void initTsAttributes(String str, float f, int i, int i2, boolean z) {
        this.mUrl = str;
        this.mName = str;
        this.mDuration = f;
        this.mIndex = i;
        this.mSequence = i2;
        this.mHasDiscontinuity = z;
        this.mTsSize = 0L;
    }

    public void setKeyConfig(String str, String str2, String str3) {
        this.mHasKey = true;
        this.mMethod = str;
        this.mKeyUri = str2;
        this.mKeyIV = str3;
    }

    public void setInitSegmentInfo(String str, String str2) {
        this.mHasInitSegment = true;
        this.mInitSegmentUri = str;
        this.mSegmentByteRange = str2;
    }

    public int getSequence() {
        return this.mSequence;
    }

    public boolean hasKey() {
        return this.mHasKey;
    }

    public String getMethod() {
        return this.mMethod;
    }

    public String getKeyUri() {
        return this.mKeyUri;
    }

    public String getLocalKeyUri() {
        return "local_" + this.mIndex + ".key";
    }

    public String getKeyIV() {
        return this.mKeyIV;
    }

    public float getDuration() {
        return this.mDuration;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0021  */
    public String getIndexName() {
        String suffixName;
        if (!TextUtils.isEmpty(this.mUrl)) {
            String lastPathSegment = Uri.parse(this.mUrl).getLastPathSegment();
            if (!TextUtils.isEmpty(lastPathSegment)) {
                suffixName = VideoDownloadUtils.getSuffixName(lastPathSegment.toLowerCase());
            } else {
                suffixName = "";
            }
        } else {
            suffixName = "";
        }
        return VideoDownloadUtils.SEGMENT_PREFIX + this.mIndex + suffixName;
    }

    public void setTsSize(long j) {
        this.mTsSize = j;
    }

    public long getTsSize() {
        return this.mTsSize;
    }

    public boolean hasDiscontinuity() {
        return this.mHasDiscontinuity;
    }

    public void setIsMessyKey(boolean z) {
        this.mIsMessyKey = z;
    }

    public boolean isMessyKey() {
        return this.mIsMessyKey;
    }

    public void setContentLength(long j) {
        this.mContentLength = j;
    }

    public long getContentLength() {
        return this.mContentLength;
    }

    public void setRetryCount(int i) {
        this.mRetryCount = i;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    public boolean hasInitSegment() {
        return this.mHasInitSegment;
    }

    public String getInitSegmentUri() {
        return this.mInitSegmentUri;
    }

    public String getSegmentByteRange() {
        return this.mSegmentByteRange;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0021  */
    public String getInitSegmentName() {
        String suffixName;
        if (!TextUtils.isEmpty(this.mInitSegmentUri)) {
            String lastPathSegment = Uri.parse(this.mInitSegmentUri).getLastPathSegment();
            if (!TextUtils.isEmpty(lastPathSegment)) {
                suffixName = VideoDownloadUtils.getSuffixName(lastPathSegment.toLowerCase());
            } else {
                suffixName = "";
            }
        } else {
            suffixName = "";
        }
        return VideoDownloadUtils.INIT_SEGMENT_PREFIX + this.mIndex + suffixName;
    }

    public String toString() {
        return "duration=" + this.mDuration + ", index=" + this.mIndex + ", name=" + this.mName;
    }

    @Override // java.lang.Comparable
    public int compareTo(M3U8Seg m3U8Seg) {
        return this.mName.compareTo(m3U8Seg.mName);
    }
}
