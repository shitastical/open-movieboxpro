package com.google.android.exoplayer.dash.mpd;

import android.net.Uri;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.UriUtil;

/* JADX INFO: loaded from: classes4.dex */
public final class RangedUri {
    private final String baseUri;
    private int hashCode;
    public final long length;
    private final String referenceUri;
    public final long start;

    public RangedUri(String str, String str2, long j, long j2) {
        Assertions.checkArgument((str == null && str2 == null) ? false : true);
        this.baseUri = str;
        this.referenceUri = str2;
        this.start = j;
        this.length = j2;
    }

    public Uri getUri() {
        return UriUtil.resolveToUri(this.baseUri, this.referenceUri);
    }

    public String getUriString() {
        return UriUtil.resolve(this.baseUri, this.referenceUri);
    }

    public RangedUri attemptMerge(RangedUri rangedUri) {
        if (rangedUri != null && getUriString().equals(rangedUri.getUriString())) {
            long j = this.length;
            if (j != -1) {
                long j2 = this.start;
                if (j2 + j == rangedUri.start) {
                    String str = this.baseUri;
                    String str2 = this.referenceUri;
                    long j3 = rangedUri.length;
                    return new RangedUri(str, str2, j2, j3 != -1 ? j + j3 : -1L);
                }
            }
            long j4 = rangedUri.length;
            if (j4 != -1) {
                long j5 = rangedUri.start;
                if (j5 + j4 == this.start) {
                    return new RangedUri(this.baseUri, this.referenceUri, j5, j != -1 ? j4 + j : -1L);
                }
            }
        }
        return null;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((((527 + ((int) this.start)) * 31) + ((int) this.length)) * 31) + getUriString().hashCode();
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RangedUri rangedUri = (RangedUri) obj;
        return this.start == rangedUri.start && this.length == rangedUri.length && getUriString().equals(rangedUri.getUriString());
    }
}
