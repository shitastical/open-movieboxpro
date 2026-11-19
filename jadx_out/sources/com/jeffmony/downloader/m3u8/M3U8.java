package com.jeffmony.downloader.m3u8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class M3U8 {
    private boolean mHasEndList;
    private int mInitSequence;
    private float mTargetDuration;
    private List<M3U8Seg> mTsList;
    private String mUrl;
    private int mVersion;

    public M3U8() {
        this("");
    }

    public M3U8(String str) {
        this.mVersion = 3;
        this.mUrl = str;
        this.mInitSequence = 0;
        this.mTsList = new ArrayList();
    }

    public void addTs(M3U8Seg m3U8Seg) {
        this.mTsList.add(m3U8Seg);
    }

    public void setTargetDuration(float f) {
        this.mTargetDuration = f;
    }

    public void setVersion(int i) {
        this.mVersion = i;
    }

    public void setSequence(int i) {
        this.mInitSequence = i;
    }

    public void setHasEndList(boolean z) {
        this.mHasEndList = z;
    }

    public List<M3U8Seg> getTsList() {
        return this.mTsList;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public float getTargetDuration() {
        return this.mTargetDuration;
    }

    public int getInitSequence() {
        return this.mInitSequence;
    }

    public boolean hasEndList() {
        return this.mHasEndList;
    }

    public long getDuration() {
        Iterator<M3U8Seg> it = this.mTsList.iterator();
        long duration = 0;
        while (it.hasNext()) {
            duration = (long) (duration + it.next().getDuration());
        }
        return duration;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof M3U8)) {
            return false;
        }
        M3U8 m3u8 = (M3U8) obj;
        String str = this.mUrl;
        return str != null && str.equals(m3u8.mUrl);
    }
}
