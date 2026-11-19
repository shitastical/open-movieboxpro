package com.movieboxpro.android.utils;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.movieboxpro.android.service.DealWatchNextService;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Api.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0082\u0001\u0010\u0004\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00072\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010J6\u0010\u0013\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J6\u0010\u0014\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J@\u0010\u0015\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0010JN\u0010\u0019\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00072\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0010J.\u0010\u001c\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00072\u0006\u0010\u0016\u001a\u00020\u0006¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/utils/Api;", "", "<init>", "()V", "getCollectList", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "boxType", "quality", "sort", "country", "year", TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, "cat_id", "page", "", "pageLimit", "waiting", "getWatchedList", "getRecommendList", "getSkipTime", "id", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "getOpenSubtitle", "imdbId", "languages", "getOpenSubtitleUrl", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Api {
    public static final Api INSTANCE = new Api();

    private Api() {
    }

    public final HashMap<String, Object> getCollectList(String boxType, String quality, String sort, String country, String year, String content_rating, String cat_id, int page, int pageLimit, int waiting) {
        Intrinsics.checkNotNullParameter(boxType, "boxType");
        return MapsKt.hashMapOf(new Pair("page", Integer.valueOf(page)), new Pair("pagelimit", Integer.valueOf(pageLimit)), new Pair("box_type", boxType), new Pair("quality", quality), new Pair("sort", sort), new Pair("country", country), new Pair("year", year), new Pair(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, content_rating), new Pair("cat_id", cat_id), new Pair("waiting", Integer.valueOf(waiting)));
    }

    public final HashMap<String, Object> getWatchedList(int page, int pageLimit) {
        return MapsKt.hashMapOf(new Pair("page", Integer.valueOf(page)), new Pair("pagelimit", Integer.valueOf(pageLimit)));
    }

    public final HashMap<String, Object> getRecommendList(int page, int pageLimit) {
        return MapsKt.hashMapOf(new Pair("page", Integer.valueOf(page)), new Pair("pagelimit", Integer.valueOf(pageLimit)));
    }

    public final HashMap<String, Object> getSkipTime(String id, int season, int episode) {
        return MapsKt.hashMapOf(new Pair("tid", id), new Pair(DealWatchNextService.SEASON, Integer.valueOf(season)), new Pair(DealWatchNextService.EPISODE, Integer.valueOf(episode)));
    }

    public final HashMap<String, Object> getOpenSubtitle(String imdbId, int page, String languages, int season, int episode) {
        Intrinsics.checkNotNullParameter(imdbId, "imdbId");
        Intrinsics.checkNotNullParameter(languages, "languages");
        return MapsKt.hashMapOf(new Pair("imdb_id", imdbId), new Pair("page", Integer.valueOf(page)), new Pair(DealWatchNextService.SEASON, Integer.valueOf(season)), new Pair(DealWatchNextService.EPISODE, Integer.valueOf(episode)), new Pair("languages", languages), new Pair("text", ""));
    }

    public final HashMap<String, Object> getOpenSubtitleUrl(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return MapsKt.hashMapOf(new Pair("subtitle_id", id));
    }
}
