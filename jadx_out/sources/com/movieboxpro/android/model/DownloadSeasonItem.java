package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/model/DownloadSeasonItem;", "", "downloadFile", "Lcom/movieboxpro/android/db/entity/DownloadFile;", DealWatchNextService.SEASON, "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "<init>", "(Lcom/movieboxpro/android/db/entity/DownloadFile;Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;)V", "getDownloadFile", "()Lcom/movieboxpro/android/db/entity/DownloadFile;", "getSeason", "()Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DownloadSeasonItem {
    private final DownloadFile downloadFile;
    private final TvDetail.SeasonDetail season;

    public static /* synthetic */ DownloadSeasonItem copy$default(DownloadSeasonItem downloadSeasonItem, DownloadFile downloadFile, TvDetail.SeasonDetail seasonDetail, int i, Object obj) {
        if ((i & 1) != 0) {
            downloadFile = downloadSeasonItem.downloadFile;
        }
        if ((i & 2) != 0) {
            seasonDetail = downloadSeasonItem.season;
        }
        return downloadSeasonItem.copy(downloadFile, seasonDetail);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DownloadFile getDownloadFile() {
        return this.downloadFile;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final TvDetail.SeasonDetail getSeason() {
        return this.season;
    }

    public final DownloadSeasonItem copy(DownloadFile downloadFile, TvDetail.SeasonDetail season) {
        Intrinsics.checkNotNullParameter(season, "season");
        return new DownloadSeasonItem(downloadFile, season);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadSeasonItem)) {
            return false;
        }
        DownloadSeasonItem downloadSeasonItem = (DownloadSeasonItem) other;
        return Intrinsics.areEqual(this.downloadFile, downloadSeasonItem.downloadFile) && Intrinsics.areEqual(this.season, downloadSeasonItem.season);
    }

    public int hashCode() {
        DownloadFile downloadFile = this.downloadFile;
        return ((downloadFile == null ? 0 : downloadFile.hashCode()) * 31) + this.season.hashCode();
    }

    public String toString() {
        return "DownloadSeasonItem(downloadFile=" + this.downloadFile + ", season=" + this.season + ")";
    }

    public DownloadSeasonItem(DownloadFile downloadFile, TvDetail.SeasonDetail season) {
        Intrinsics.checkNotNullParameter(season, "season");
        this.downloadFile = downloadFile;
        this.season = season;
    }

    public final DownloadFile getDownloadFile() {
        return this.downloadFile;
    }

    public final TvDetail.SeasonDetail getSeason() {
        return this.season;
    }
}
