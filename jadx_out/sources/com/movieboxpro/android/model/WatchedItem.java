package com.movieboxpro.android.model;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b=\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u0010A\u001a\u00020\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010E\u001a\u00020\tHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u0003HÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\t\u0010T\u001a\u00020\u0003HÆ\u0003J\t\u0010U\u001a\u00020\u0003HÆ\u0003J\t\u0010V\u001a\u00020\u0003HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u001cHÆ\u0003J\u0089\u0002\u0010X\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÆ\u0001J\u0013\u0010Y\u001a\u00020Z2\b\u0010[\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\\\u001a\u00020\u0003HÖ\u0001J\t\u0010]\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\"R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010 \"\u0004\b,\u0010-R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010 \"\u0004\b/\u0010-R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010 \"\u0004\b1\u0010-R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\"R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\"R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\"R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\"R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\"R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\"R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\"R\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010 R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b;\u0010 R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b<\u0010 R\u001a\u0010\u001a\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010 \"\u0004\b>\u0010-R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@¨\u0006^"}, d2 = {"Lcom/movieboxpro/android/model/WatchedItem;", "", "box_type", "", "id", "", "watched_id", TvContractCompat.ProgramColumns.COLUMN_TITLE, "last_play_time", "", "imdb_rating", "tomato_meter", "like_status", "like_total", "dislike_total", "year", "cats", "quality_tag", "released", "poster", "quality_tag_new", "season_episode", "update_title", "runtime", "updateCount", "runtime_seconds", "viewType", "episode_info", "Lcom/movieboxpro/android/model/WatchedEpisode;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;IIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILcom/movieboxpro/android/model/WatchedEpisode;)V", "getBox_type", "()I", "getId", "()Ljava/lang/String;", "getWatched_id", "getTitle", "getLast_play_time", "()J", "setLast_play_time", "(J)V", "getImdb_rating", "getTomato_meter", "getLike_status", "setLike_status", "(I)V", "getLike_total", "setLike_total", "getDislike_total", "setDislike_total", "getYear", "getCats", "getQuality_tag", "getReleased", "getPoster", "getQuality_tag_new", "getSeason_episode", "getUpdate_title", "getRuntime", "getUpdateCount", "getRuntime_seconds", "getViewType", "setViewType", "getEpisode_info", "()Lcom/movieboxpro/android/model/WatchedEpisode;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class WatchedItem {
    private final int box_type;
    private final String cats;
    private int dislike_total;
    private final WatchedEpisode episode_info;
    private final String id;
    private final String imdb_rating;
    private long last_play_time;
    private int like_status;
    private int like_total;
    private final String poster;
    private final String quality_tag;
    private final String quality_tag_new;
    private final String released;
    private final int runtime;
    private final int runtime_seconds;
    private final String season_episode;
    private final String title;
    private final int tomato_meter;
    private final int updateCount;
    private final String update_title;
    private int viewType;
    private final String watched_id;
    private final String year;

    public WatchedItem() {
        this(0, null, null, null, 0L, null, 0, 0, 0, 0, null, null, null, null, null, null, null, null, 0, 0, 0, 0, null, 8388607, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getBox_type() {
        return this.box_type;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getDislike_total() {
        return this.dislike_total;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getCats() {
        return this.cats;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getQuality_tag() {
        return this.quality_tag;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getReleased() {
        return this.released;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getPoster() {
        return this.poster;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getSeason_episode() {
        return this.season_episode;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getUpdate_title() {
        return this.update_title;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final int getRuntime() {
        return this.runtime;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final int getUpdateCount() {
        return this.updateCount;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final int getRuntime_seconds() {
        return this.runtime_seconds;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final int getViewType() {
        return this.viewType;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final WatchedEpisode getEpisode_info() {
        return this.episode_info;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getWatched_id() {
        return this.watched_id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getLast_play_time() {
        return this.last_play_time;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getImdb_rating() {
        return this.imdb_rating;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getTomato_meter() {
        return this.tomato_meter;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getLike_status() {
        return this.like_status;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getLike_total() {
        return this.like_total;
    }

    public final WatchedItem copy(int box_type, String id, String watched_id, String title, long last_play_time, String imdb_rating, int tomato_meter, int like_status, int like_total, int dislike_total, String year, String cats, String quality_tag, String released, String poster, String quality_tag_new, String season_episode, String update_title, int runtime, int updateCount, int runtime_seconds, int viewType, WatchedEpisode episode_info) {
        return new WatchedItem(box_type, id, watched_id, title, last_play_time, imdb_rating, tomato_meter, like_status, like_total, dislike_total, year, cats, quality_tag, released, poster, quality_tag_new, season_episode, update_title, runtime, updateCount, runtime_seconds, viewType, episode_info);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchedItem)) {
            return false;
        }
        WatchedItem watchedItem = (WatchedItem) other;
        return this.box_type == watchedItem.box_type && Intrinsics.areEqual(this.id, watchedItem.id) && Intrinsics.areEqual(this.watched_id, watchedItem.watched_id) && Intrinsics.areEqual(this.title, watchedItem.title) && this.last_play_time == watchedItem.last_play_time && Intrinsics.areEqual(this.imdb_rating, watchedItem.imdb_rating) && this.tomato_meter == watchedItem.tomato_meter && this.like_status == watchedItem.like_status && this.like_total == watchedItem.like_total && this.dislike_total == watchedItem.dislike_total && Intrinsics.areEqual(this.year, watchedItem.year) && Intrinsics.areEqual(this.cats, watchedItem.cats) && Intrinsics.areEqual(this.quality_tag, watchedItem.quality_tag) && Intrinsics.areEqual(this.released, watchedItem.released) && Intrinsics.areEqual(this.poster, watchedItem.poster) && Intrinsics.areEqual(this.quality_tag_new, watchedItem.quality_tag_new) && Intrinsics.areEqual(this.season_episode, watchedItem.season_episode) && Intrinsics.areEqual(this.update_title, watchedItem.update_title) && this.runtime == watchedItem.runtime && this.updateCount == watchedItem.updateCount && this.runtime_seconds == watchedItem.runtime_seconds && this.viewType == watchedItem.viewType && Intrinsics.areEqual(this.episode_info, watchedItem.episode_info);
    }

    public int hashCode() {
        int i = this.box_type * 31;
        String str = this.id;
        int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.watched_id;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.title;
        int iHashCode3 = (((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.last_play_time)) * 31;
        String str4 = this.imdb_rating;
        int iHashCode4 = (((((((((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.tomato_meter) * 31) + this.like_status) * 31) + this.like_total) * 31) + this.dislike_total) * 31;
        String str5 = this.year;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.cats;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.quality_tag;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.released;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.poster;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.quality_tag_new;
        int iHashCode10 = (iHashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.season_episode;
        int iHashCode11 = (iHashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.update_title;
        int iHashCode12 = (((((((((iHashCode11 + (str12 == null ? 0 : str12.hashCode())) * 31) + this.runtime) * 31) + this.updateCount) * 31) + this.runtime_seconds) * 31) + this.viewType) * 31;
        WatchedEpisode watchedEpisode = this.episode_info;
        return iHashCode12 + (watchedEpisode != null ? watchedEpisode.hashCode() : 0);
    }

    public String toString() {
        return "WatchedItem(box_type=" + this.box_type + ", id=" + this.id + ", watched_id=" + this.watched_id + ", title=" + this.title + ", last_play_time=" + this.last_play_time + ", imdb_rating=" + this.imdb_rating + ", tomato_meter=" + this.tomato_meter + ", like_status=" + this.like_status + ", like_total=" + this.like_total + ", dislike_total=" + this.dislike_total + ", year=" + this.year + ", cats=" + this.cats + ", quality_tag=" + this.quality_tag + ", released=" + this.released + ", poster=" + this.poster + ", quality_tag_new=" + this.quality_tag_new + ", season_episode=" + this.season_episode + ", update_title=" + this.update_title + ", runtime=" + this.runtime + ", updateCount=" + this.updateCount + ", runtime_seconds=" + this.runtime_seconds + ", viewType=" + this.viewType + ", episode_info=" + this.episode_info + ")";
    }

    public WatchedItem(int i, String str, String str2, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i6, int i7, int i8, int i9, WatchedEpisode watchedEpisode) {
        this.box_type = i;
        this.id = str;
        this.watched_id = str2;
        this.title = str3;
        this.last_play_time = j;
        this.imdb_rating = str4;
        this.tomato_meter = i2;
        this.like_status = i3;
        this.like_total = i4;
        this.dislike_total = i5;
        this.year = str5;
        this.cats = str6;
        this.quality_tag = str7;
        this.released = str8;
        this.poster = str9;
        this.quality_tag_new = str10;
        this.season_episode = str11;
        this.update_title = str12;
        this.runtime = i6;
        this.updateCount = i7;
        this.runtime_seconds = i8;
        this.viewType = i9;
        this.episode_info = watchedEpisode;
    }

    public /* synthetic */ WatchedItem(int i, String str, String str2, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i6, int i7, int i8, int i9, WatchedEpisode watchedEpisode, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? 0 : i, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : str2, (i10 & 8) != 0 ? null : str3, (i10 & 16) != 0 ? 0L : j, (i10 & 32) != 0 ? null : str4, (i10 & 64) != 0 ? 0 : i2, (i10 & 128) != 0 ? 0 : i3, (i10 & 256) != 0 ? 0 : i4, (i10 & 512) != 0 ? 0 : i5, (i10 & 1024) != 0 ? null : str5, (i10 & 2048) != 0 ? null : str6, (i10 & 4096) != 0 ? null : str7, (i10 & 8192) != 0 ? null : str8, (i10 & 16384) != 0 ? null : str9, (i10 & 32768) != 0 ? null : str10, (i10 & 65536) != 0 ? null : str11, (i10 & 131072) != 0 ? null : str12, (i10 & 262144) != 0 ? 0 : i6, (i10 & 524288) != 0 ? 0 : i7, (i10 & 1048576) != 0 ? 0 : i8, (i10 & 2097152) != 0 ? 0 : i9, (i10 & 4194304) != 0 ? null : watchedEpisode);
    }

    public final int getBox_type() {
        return this.box_type;
    }

    public final String getId() {
        return this.id;
    }

    public final String getWatched_id() {
        return this.watched_id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final long getLast_play_time() {
        return this.last_play_time;
    }

    public final void setLast_play_time(long j) {
        this.last_play_time = j;
    }

    public final String getImdb_rating() {
        return this.imdb_rating;
    }

    public final int getTomato_meter() {
        return this.tomato_meter;
    }

    public final int getLike_status() {
        return this.like_status;
    }

    public final void setLike_status(int i) {
        this.like_status = i;
    }

    public final int getLike_total() {
        return this.like_total;
    }

    public final void setLike_total(int i) {
        this.like_total = i;
    }

    public final int getDislike_total() {
        return this.dislike_total;
    }

    public final void setDislike_total(int i) {
        this.dislike_total = i;
    }

    public final String getYear() {
        return this.year;
    }

    public final String getCats() {
        return this.cats;
    }

    public final String getQuality_tag() {
        return this.quality_tag;
    }

    public final String getReleased() {
        return this.released;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    public final String getSeason_episode() {
        return this.season_episode;
    }

    public final String getUpdate_title() {
        return this.update_title;
    }

    public final int getRuntime() {
        return this.runtime;
    }

    public final int getUpdateCount() {
        return this.updateCount;
    }

    public final int getRuntime_seconds() {
        return this.runtime_seconds;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public final void setViewType(int i) {
        this.viewType = i;
    }

    public final WatchedEpisode getEpisode_info() {
        return this.episode_info;
    }
}
