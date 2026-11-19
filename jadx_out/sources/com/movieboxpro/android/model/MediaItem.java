package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/model/MediaItem;", "", "id", "", "poster", "quality_tag_new", "imdb_rating", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getPoster", "getQuality_tag_new", "getImdb_rating", "component1", "component2", "component3", "component4", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MediaItem {
    private final String id;
    private final String imdb_rating;
    private final String poster;
    private final String quality_tag_new;

    public MediaItem() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ MediaItem copy$default(MediaItem mediaItem, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaItem.id;
        }
        if ((i & 2) != 0) {
            str2 = mediaItem.poster;
        }
        if ((i & 4) != 0) {
            str3 = mediaItem.quality_tag_new;
        }
        if ((i & 8) != 0) {
            str4 = mediaItem.imdb_rating;
        }
        return mediaItem.copy(str, str2, str3, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPoster() {
        return this.poster;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getImdb_rating() {
        return this.imdb_rating;
    }

    public final MediaItem copy(String id, String poster, String quality_tag_new, String imdb_rating) {
        return new MediaItem(id, poster, quality_tag_new, imdb_rating);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) other;
        return Intrinsics.areEqual(this.id, mediaItem.id) && Intrinsics.areEqual(this.poster, mediaItem.poster) && Intrinsics.areEqual(this.quality_tag_new, mediaItem.quality_tag_new) && Intrinsics.areEqual(this.imdb_rating, mediaItem.imdb_rating);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.poster;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.quality_tag_new;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.imdb_rating;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "MediaItem(id=" + this.id + ", poster=" + this.poster + ", quality_tag_new=" + this.quality_tag_new + ", imdb_rating=" + this.imdb_rating + ")";
    }

    public MediaItem(String str, String str2, String str3, String str4) {
        this.id = str;
        this.poster = str2;
        this.quality_tag_new = str3;
        this.imdb_rating = str4;
    }

    public /* synthetic */ MediaItem(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4);
    }

    public final String getId() {
        return this.id;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    public final String getImdb_rating() {
        return this.imdb_rating;
    }
}
