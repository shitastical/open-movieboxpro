package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerEpisodeListStore.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0015\u001a\u00020\rJ\u0006\u0010\u0016\u001a\u00020\rJ3\u0010\u0017\u001a\u00020\u00182\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0019\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u001aJ\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u0006J(\u0010\u001e\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u00062\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u001c\u0010 \u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u0006H\u0002R0\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR0\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\tR\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListStore;", "", "<init>", "()V", "value", "", "", "seasons", "getSeasons", "()[Ljava/lang/String;", "[Ljava/lang/String;", "years", "getYears", "", "seasonMetaLoaded", "getSeasonMetaLoaded", "()Z", "episodes", "", "", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "hasSeasonMeta", "shouldFetchSeasonMeta", "setSeasonMeta", "", "fromNetwork", "([Ljava/lang/String;[Ljava/lang/String;Z)V", "getEpisodes", DealWatchNextService.SEASON, "year", "putEpisodes", "list", "cacheKey", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayerEpisodeListStore {
    private final Map<String, List<TvDetail.SeasonDetail>> episodes = new LinkedHashMap();
    private boolean seasonMetaLoaded;
    private String[] seasons;
    private String[] years;

    public final void setSeasonMeta(String[] seasons, String[] years) {
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        Intrinsics.checkNotNullParameter(years, "years");
        setSeasonMeta$default(this, seasons, years, false, 4, null);
    }

    public final String[] getSeasons() {
        return this.seasons;
    }

    public final String[] getYears() {
        return this.years;
    }

    public final boolean getSeasonMetaLoaded() {
        return this.seasonMetaLoaded;
    }

    public final boolean hasSeasonMeta() {
        String[] strArr;
        String[] strArr2 = this.seasons;
        return ((strArr2 == null || strArr2.length == 0) && ((strArr = this.years) == null || strArr.length == 0)) ? false : true;
    }

    public final boolean shouldFetchSeasonMeta() {
        return !this.seasonMetaLoaded;
    }

    public static /* synthetic */ void setSeasonMeta$default(PlayerEpisodeListStore playerEpisodeListStore, String[] strArr, String[] strArr2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        playerEpisodeListStore.setSeasonMeta(strArr, strArr2, z);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x001e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:13:0x0020  */
    /* JADX WARN: Code duplicated, block: B:23:0x0032 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:24:0x0034  */
    public final void setSeasonMeta(String[] seasons, String[] years, boolean fromNetwork) {
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        Intrinsics.checkNotNullParameter(years, "years");
        String[] strArr = this.seasons;
        if (strArr == null || strArr.length == 0) {
            if (!(seasons.length == 0)) {
                this.seasons = seasons;
            } else if (strArr == null) {
                this.seasons = seasons;
            }
        } else if (strArr == null) {
            this.seasons = seasons;
        }
        String[] strArr2 = this.years;
        if (strArr2 == null || strArr2.length == 0) {
            if (!(years.length == 0)) {
                this.years = years;
            } else if (strArr2 == null) {
                this.years = years;
            }
        } else if (strArr2 == null) {
            this.years = years;
        }
        if (fromNetwork) {
            this.seasonMetaLoaded = true;
        }
    }

    public final List<TvDetail.SeasonDetail> getEpisodes(String season, String year) {
        return this.episodes.get(cacheKey(season, year));
    }

    public final void putEpisodes(String season, String year, List<? extends TvDetail.SeasonDetail> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.isEmpty()) {
            return;
        }
        String strCacheKey = cacheKey(season, year);
        List<TvDetail.SeasonDetail> list2 = this.episodes.get(strCacheKey);
        if (list2 == null || list2.isEmpty()) {
            this.episodes.put(strCacheKey, new ArrayList(list));
        }
    }

    private final String cacheKey(String season, String year) {
        if (season == null) {
            season = "";
        }
        if (year == null) {
            year = "";
        }
        return season + "|" + year;
    }
}
