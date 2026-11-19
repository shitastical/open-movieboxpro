package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/model/TranscodeResponse;", "", "srt_content", "", "srt_name", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getSrt_content", "()Ljava/lang/String;", "getSrt_name", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TranscodeResponse {
    private final String srt_content;
    private final String srt_name;

    /* JADX WARN: Multi-variable type inference failed */
    public TranscodeResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ TranscodeResponse copy$default(TranscodeResponse transcodeResponse, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = transcodeResponse.srt_content;
        }
        if ((i & 2) != 0) {
            str2 = transcodeResponse.srt_name;
        }
        return transcodeResponse.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSrt_content() {
        return this.srt_content;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSrt_name() {
        return this.srt_name;
    }

    public final TranscodeResponse copy(String srt_content, String srt_name) {
        return new TranscodeResponse(srt_content, srt_name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TranscodeResponse)) {
            return false;
        }
        TranscodeResponse transcodeResponse = (TranscodeResponse) other;
        return Intrinsics.areEqual(this.srt_content, transcodeResponse.srt_content) && Intrinsics.areEqual(this.srt_name, transcodeResponse.srt_name);
    }

    public int hashCode() {
        String str = this.srt_content;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.srt_name;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "TranscodeResponse(srt_content=" + this.srt_content + ", srt_name=" + this.srt_name + ")";
    }

    public TranscodeResponse(String str, String str2) {
        this.srt_content = str;
        this.srt_name = str2;
    }

    public /* synthetic */ TranscodeResponse(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    public final String getSrt_content() {
        return this.srt_content;
    }

    public final String getSrt_name() {
        return this.srt_name;
    }
}
