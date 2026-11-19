package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/model/SkipEnd;", "", "end", "", "total", "<init>", "(II)V", "getEnd", "()I", "getTotal", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SkipEnd {
    private final int end;
    private final int total;

    /* JADX WARN: Illegal instructions before constructor call */
    public SkipEnd() {
        int i = 0;
        this(i, i, 3, null);
    }

    public static /* synthetic */ SkipEnd copy$default(SkipEnd skipEnd, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = skipEnd.end;
        }
        if ((i3 & 2) != 0) {
            i2 = skipEnd.total;
        }
        return skipEnd.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getEnd() {
        return this.end;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getTotal() {
        return this.total;
    }

    public final SkipEnd copy(int end, int total) {
        return new SkipEnd(end, total);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SkipEnd)) {
            return false;
        }
        SkipEnd skipEnd = (SkipEnd) other;
        return this.end == skipEnd.end && this.total == skipEnd.total;
    }

    public int hashCode() {
        return (this.end * 31) + this.total;
    }

    public String toString() {
        return "SkipEnd(end=" + this.end + ", total=" + this.total + ")";
    }

    public SkipEnd(int i, int i2) {
        this.end = i;
        this.total = i2;
    }

    public /* synthetic */ SkipEnd(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final int getEnd() {
        return this.end;
    }

    public final int getTotal() {
        return this.total;
    }
}
