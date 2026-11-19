package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u001c\b\u0002\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\r\u0012\u001c\b\u0002\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\r¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003J\u001d\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\rHÆ\u0003J\u001d\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\rHÆ\u0003J\u0085\u0001\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\u001c\b\u0002\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\r2\u001c\b\u0002\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\rHÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\t\u0010*\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R%\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR%\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001b¨\u0006+"}, d2 = {"Lcom/movieboxpro/android/model/SkipTimeResponse;", "", "start", "", "end", "start_is_multi", "end_is_multi", "start_type", "", "end_type", "start_top_list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/SkipStart;", "Lkotlin/collections/ArrayList;", "end_top_list", "Lcom/movieboxpro/android/model/SkipEnd;", "<init>", "(IIIILjava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getStart", "()I", "getEnd", "getStart_is_multi", "getEnd_is_multi", "getStart_type", "()Ljava/lang/String;", "getEnd_type", "getStart_top_list", "()Ljava/util/ArrayList;", "getEnd_top_list", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SkipTimeResponse {
    private final int end;
    private final int end_is_multi;
    private final ArrayList<SkipEnd> end_top_list;
    private final String end_type;
    private final int start;
    private final int start_is_multi;
    private final ArrayList<SkipStart> start_top_list;
    private final String start_type;

    public SkipTimeResponse() {
        this(0, 0, 0, 0, null, null, null, null, 255, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEnd() {
        return this.end;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getStart_is_multi() {
        return this.start_is_multi;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEnd_is_multi() {
        return this.end_is_multi;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStart_type() {
        return this.start_type;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getEnd_type() {
        return this.end_type;
    }

    public final ArrayList<SkipStart> component7() {
        return this.start_top_list;
    }

    public final ArrayList<SkipEnd> component8() {
        return this.end_top_list;
    }

    public final SkipTimeResponse copy(int start, int end, int start_is_multi, int end_is_multi, String start_type, String end_type, ArrayList<SkipStart> start_top_list, ArrayList<SkipEnd> end_top_list) {
        return new SkipTimeResponse(start, end, start_is_multi, end_is_multi, start_type, end_type, start_top_list, end_top_list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SkipTimeResponse)) {
            return false;
        }
        SkipTimeResponse skipTimeResponse = (SkipTimeResponse) other;
        return this.start == skipTimeResponse.start && this.end == skipTimeResponse.end && this.start_is_multi == skipTimeResponse.start_is_multi && this.end_is_multi == skipTimeResponse.end_is_multi && Intrinsics.areEqual(this.start_type, skipTimeResponse.start_type) && Intrinsics.areEqual(this.end_type, skipTimeResponse.end_type) && Intrinsics.areEqual(this.start_top_list, skipTimeResponse.start_top_list) && Intrinsics.areEqual(this.end_top_list, skipTimeResponse.end_top_list);
    }

    public int hashCode() {
        int i = ((((((this.start * 31) + this.end) * 31) + this.start_is_multi) * 31) + this.end_is_multi) * 31;
        String str = this.start_type;
        int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.end_type;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        ArrayList<SkipStart> arrayList = this.start_top_list;
        int iHashCode3 = (iHashCode2 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        ArrayList<SkipEnd> arrayList2 = this.end_top_list;
        return iHashCode3 + (arrayList2 != null ? arrayList2.hashCode() : 0);
    }

    public String toString() {
        return "SkipTimeResponse(start=" + this.start + ", end=" + this.end + ", start_is_multi=" + this.start_is_multi + ", end_is_multi=" + this.end_is_multi + ", start_type=" + this.start_type + ", end_type=" + this.end_type + ", start_top_list=" + this.start_top_list + ", end_top_list=" + this.end_top_list + ")";
    }

    public SkipTimeResponse(int i, int i2, int i3, int i4, String str, String str2, ArrayList<SkipStart> arrayList, ArrayList<SkipEnd> arrayList2) {
        this.start = i;
        this.end = i2;
        this.start_is_multi = i3;
        this.end_is_multi = i4;
        this.start_type = str;
        this.end_type = str2;
        this.start_top_list = arrayList;
        this.end_top_list = arrayList2;
    }

    public /* synthetic */ SkipTimeResponse(int i, int i2, int i3, int i4, String str, String str2, ArrayList arrayList, ArrayList arrayList2, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) == 0 ? i4 : 0, (i5 & 16) != 0 ? null : str, (i5 & 32) != 0 ? null : str2, (i5 & 64) != 0 ? null : arrayList, (i5 & 128) == 0 ? arrayList2 : null);
    }

    public final int getStart() {
        return this.start;
    }

    public final int getEnd() {
        return this.end;
    }

    public final int getStart_is_multi() {
        return this.start_is_multi;
    }

    public final int getEnd_is_multi() {
        return this.end_is_multi;
    }

    public final String getStart_type() {
        return this.start_type;
    }

    public final String getEnd_type() {
        return this.end_type;
    }

    public final ArrayList<SkipStart> getStart_top_list() {
        return this.start_top_list;
    }

    public final ArrayList<SkipEnd> getEnd_top_list() {
        return this.end_top_list;
    }
}
