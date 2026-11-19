package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b¢\u0006\u0004\b\f\u0010\rJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u001d\u0010\u001a\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bHÆ\u0003JU\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R%\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/movieboxpro/android/model/OpenSubtitleItem;", "", "subtitle_id", "", "language", "upload_date", "download_count", "", "files", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/OpenSubtitleFile;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/util/ArrayList;)V", "getSubtitle_id", "()Ljava/lang/String;", "getLanguage", "getUpload_date", "getDownload_count", "()J", "getFiles", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class OpenSubtitleItem {
    private final long download_count;
    private final ArrayList<OpenSubtitleFile> files;
    private final String language;
    private final String subtitle_id;
    private final String upload_date;

    public OpenSubtitleItem() {
        this(null, null, null, 0L, null, 31, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ OpenSubtitleItem copy$default(OpenSubtitleItem openSubtitleItem, String str, String str2, String str3, long j, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = openSubtitleItem.subtitle_id;
        }
        if ((i & 2) != 0) {
            str2 = openSubtitleItem.language;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = openSubtitleItem.upload_date;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            j = openSubtitleItem.download_count;
        }
        long j2 = j;
        if ((i & 16) != 0) {
            arrayList = openSubtitleItem.files;
        }
        return openSubtitleItem.copy(str, str4, str5, j2, arrayList);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSubtitle_id() {
        return this.subtitle_id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUpload_date() {
        return this.upload_date;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getDownload_count() {
        return this.download_count;
    }

    public final ArrayList<OpenSubtitleFile> component5() {
        return this.files;
    }

    public final OpenSubtitleItem copy(String subtitle_id, String language, String upload_date, long download_count, ArrayList<OpenSubtitleFile> files) {
        return new OpenSubtitleItem(subtitle_id, language, upload_date, download_count, files);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OpenSubtitleItem)) {
            return false;
        }
        OpenSubtitleItem openSubtitleItem = (OpenSubtitleItem) other;
        return Intrinsics.areEqual(this.subtitle_id, openSubtitleItem.subtitle_id) && Intrinsics.areEqual(this.language, openSubtitleItem.language) && Intrinsics.areEqual(this.upload_date, openSubtitleItem.upload_date) && this.download_count == openSubtitleItem.download_count && Intrinsics.areEqual(this.files, openSubtitleItem.files);
    }

    public int hashCode() {
        String str = this.subtitle_id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.language;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.upload_date;
        int iHashCode3 = (((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.download_count)) * 31;
        ArrayList<OpenSubtitleFile> arrayList = this.files;
        return iHashCode3 + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "OpenSubtitleItem(subtitle_id=" + this.subtitle_id + ", language=" + this.language + ", upload_date=" + this.upload_date + ", download_count=" + this.download_count + ", files=" + this.files + ")";
    }

    public OpenSubtitleItem(String str, String str2, String str3, long j, ArrayList<OpenSubtitleFile> arrayList) {
        this.subtitle_id = str;
        this.language = str2;
        this.upload_date = str3;
        this.download_count = j;
        this.files = arrayList;
    }

    public /* synthetic */ OpenSubtitleItem(String str, String str2, String str3, long j, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? 0L : j, (i & 16) != 0 ? null : arrayList);
    }

    public final String getSubtitle_id() {
        return this.subtitle_id;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getUpload_date() {
        return this.upload_date;
    }

    public final long getDownload_count() {
        return this.download_count;
    }

    public final ArrayList<OpenSubtitleFile> getFiles() {
        return this.files;
    }
}
