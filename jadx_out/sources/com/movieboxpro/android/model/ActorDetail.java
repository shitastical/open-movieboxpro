package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.androidtv.BuildConfig;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\r\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/model/ActorDetail;", "", "tv", "", "Lcom/movieboxpro/android/model/MediaItem;", BuildConfig.CHANNEL, "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getTv", "()Ljava/util/List;", "getMovie", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActorDetail {
    private final List<MediaItem> movie;
    private final List<MediaItem> tv;

    /* JADX WARN: Multi-variable type inference failed */
    public ActorDetail() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActorDetail copy$default(ActorDetail actorDetail, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = actorDetail.tv;
        }
        if ((i & 2) != 0) {
            list2 = actorDetail.movie;
        }
        return actorDetail.copy(list, list2);
    }

    public final List<MediaItem> component1() {
        return this.tv;
    }

    public final List<MediaItem> component2() {
        return this.movie;
    }

    public final ActorDetail copy(List<MediaItem> tv2, List<MediaItem> movie) {
        return new ActorDetail(tv2, movie);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActorDetail)) {
            return false;
        }
        ActorDetail actorDetail = (ActorDetail) other;
        return Intrinsics.areEqual(this.tv, actorDetail.tv) && Intrinsics.areEqual(this.movie, actorDetail.movie);
    }

    public int hashCode() {
        List<MediaItem> list = this.tv;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<MediaItem> list2 = this.movie;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "ActorDetail(tv=" + this.tv + ", movie=" + this.movie + ")";
    }

    public ActorDetail(List<MediaItem> list, List<MediaItem> list2) {
        this.tv = list;
        this.movie = list2;
    }

    public /* synthetic */ ActorDetail(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2);
    }

    public final List<MediaItem> getTv() {
        return this.tv;
    }

    public final List<MediaItem> getMovie() {
        return this.movie;
    }
}
