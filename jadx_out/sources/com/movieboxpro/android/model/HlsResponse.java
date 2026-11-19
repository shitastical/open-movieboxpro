package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J5\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/model/HlsResponse;", "", "org_file", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "hls_quality_list", "", "Lcom/movieboxpro/android/model/HlsItem;", "quality_list", "<init>", "(Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;Ljava/util/List;Ljava/util/List;)V", "getOrg_file", "()Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "getHls_quality_list", "()Ljava/util/List;", "getQuality_list", "component1", "component2", "component3", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class HlsResponse {
    private final List<HlsItem> hls_quality_list;
    private final BaseMediaModel.DownloadFile org_file;
    private final List<BaseMediaModel.DownloadFile> quality_list;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HlsResponse copy$default(HlsResponse hlsResponse, BaseMediaModel.DownloadFile downloadFile, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            downloadFile = hlsResponse.org_file;
        }
        if ((i & 2) != 0) {
            list = hlsResponse.hls_quality_list;
        }
        if ((i & 4) != 0) {
            list2 = hlsResponse.quality_list;
        }
        return hlsResponse.copy(downloadFile, list, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final BaseMediaModel.DownloadFile getOrg_file() {
        return this.org_file;
    }

    public final List<HlsItem> component2() {
        return this.hls_quality_list;
    }

    public final List<BaseMediaModel.DownloadFile> component3() {
        return this.quality_list;
    }

    public final HlsResponse copy(BaseMediaModel.DownloadFile org_file, List<HlsItem> hls_quality_list, List<? extends BaseMediaModel.DownloadFile> quality_list) {
        Intrinsics.checkNotNullParameter(org_file, "org_file");
        Intrinsics.checkNotNullParameter(quality_list, "quality_list");
        return new HlsResponse(org_file, hls_quality_list, quality_list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HlsResponse)) {
            return false;
        }
        HlsResponse hlsResponse = (HlsResponse) other;
        return Intrinsics.areEqual(this.org_file, hlsResponse.org_file) && Intrinsics.areEqual(this.hls_quality_list, hlsResponse.hls_quality_list) && Intrinsics.areEqual(this.quality_list, hlsResponse.quality_list);
    }

    public int hashCode() {
        int iHashCode = this.org_file.hashCode() * 31;
        List<HlsItem> list = this.hls_quality_list;
        return ((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.quality_list.hashCode();
    }

    public String toString() {
        return "HlsResponse(org_file=" + this.org_file + ", hls_quality_list=" + this.hls_quality_list + ", quality_list=" + this.quality_list + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HlsResponse(BaseMediaModel.DownloadFile org_file, List<HlsItem> list, List<? extends BaseMediaModel.DownloadFile> quality_list) {
        Intrinsics.checkNotNullParameter(org_file, "org_file");
        Intrinsics.checkNotNullParameter(quality_list, "quality_list");
        this.org_file = org_file;
        this.hls_quality_list = list;
        this.quality_list = quality_list;
    }

    public final BaseMediaModel.DownloadFile getOrg_file() {
        return this.org_file;
    }

    public final List<HlsItem> getHls_quality_list() {
        return this.hls_quality_list;
    }

    public final List<BaseMediaModel.DownloadFile> getQuality_list() {
        return this.quality_list;
    }
}
