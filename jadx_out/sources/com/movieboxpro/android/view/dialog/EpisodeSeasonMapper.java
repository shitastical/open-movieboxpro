package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EpisodeSeasonMapper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ$\u0010\u0012\u001a\u00020\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007J\u001c\u0010\u0016\u001a\u00020\f2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0018J(\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00142\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007J&\u0010\u001a\u001a\u00020\f2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00142\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007J5\u0010\u001b\u001a\u00020\u001c2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00142\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001d\u001a\u00020\u0007¢\u0006\u0002\u0010\u001eJ,\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"2\u0006\u0010#\u001a\u00020\u00052\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/dialog/EpisodeSeasonMapper;", "", "<init>", "()V", "headerTitle", "", DealWatchNextService.SEASON, "", "year", "seasonCountLabel", "seasonCount", "isCurrentEpisode", "", "item", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "playingSeason", "playingEpisode", "isUnavailable", "indexOfEpisode", "episodes", "", DealWatchNextService.EPISODE, "isMarkedWatched", "playProgress", "", "findEpisode", "shouldRestartFromStart", "applyOver", "", "over", "(Ljava/util/List;ILjava/lang/Integer;I)V", "toTvSeasonList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/TvSeasonList;", "Lkotlin/collections/ArrayList;", "tid", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EpisodeSeasonMapper {
    public static final EpisodeSeasonMapper INSTANCE = new EpisodeSeasonMapper();

    private EpisodeSeasonMapper() {
    }

    public final String headerTitle(int season, int year) {
        if (season != 0) {
            return "Season " + season;
        }
        if (year == -1) {
            return "Released";
        }
        return "Year " + year;
    }

    public final String seasonCountLabel(int seasonCount) {
        return seasonCount + " seasons";
    }

    public final boolean isCurrentEpisode(TvDetail.SeasonDetail item, int playingSeason, int playingEpisode) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.episode != playingEpisode) {
            return false;
        }
        return playingSeason == 0 || item.season == 0 || item.season == playingSeason;
    }

    public final boolean isUnavailable(TvDetail.SeasonDetail item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return item.code_file != 1;
    }

    public final boolean isMarkedWatched(Map<String, Integer> playProgress) {
        Integer num;
        return (playProgress == null || (num = playProgress.get("over")) == null || num.intValue() != 1) ? false : true;
    }

    public final TvDetail.SeasonDetail findEpisode(List<? extends TvDetail.SeasonDetail> episodes, int season, int episode) {
        Object next;
        List<? extends TvDetail.SeasonDetail> list = episodes;
        Object obj = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<? extends TvDetail.SeasonDetail> list2 = episodes;
        Iterator<T> it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            TvDetail.SeasonDetail seasonDetail = (TvDetail.SeasonDetail) next;
            if (seasonDetail.season == season && seasonDetail.episode == episode) {
                break;
            }
        }
        TvDetail.SeasonDetail seasonDetail2 = (TvDetail.SeasonDetail) next;
        if (seasonDetail2 != null) {
            return seasonDetail2;
        }
        for (Object obj2 : list2) {
            if (((TvDetail.SeasonDetail) obj2).episode == episode) {
                obj = obj2;
                break;
            }
        }
        return (TvDetail.SeasonDetail) obj;
    }

    public final boolean shouldRestartFromStart(List<? extends TvDetail.SeasonDetail> episodes, int season, int episode) {
        TvDetail.SeasonDetail seasonDetailFindEpisode = findEpisode(episodes, season, episode);
        return isMarkedWatched(seasonDetailFindEpisode != null ? seasonDetailFindEpisode.play_progress : null);
    }

    public final void applyOver(List<? extends TvDetail.SeasonDetail> episodes, int season, Integer episode, int over) {
        List<? extends TvDetail.SeasonDetail> list = episodes;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (TvDetail.SeasonDetail seasonDetail : episodes) {
            if (seasonDetail.season == season && (episode == null || seasonDetail.episode == episode.intValue())) {
                HashMap<String, Integer> map = seasonDetail.play_progress;
                if (map == null) {
                    map = new HashMap<>();
                }
                map.put("over", Integer.valueOf(over));
                seasonDetail.play_progress = map;
            }
        }
    }

    public final ArrayList<TvSeasonList> toTvSeasonList(String tid, List<? extends TvDetail.SeasonDetail> episodes) {
        Intrinsics.checkNotNullParameter(tid, "tid");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        ArrayList<TvSeasonList> arrayList = new ArrayList<>();
        for (TvDetail.SeasonDetail seasonDetail : episodes) {
            TvSeasonList tvSeasonList = new TvSeasonList();
            String str = seasonDetail.tid;
            tvSeasonList.setTid((str == null || str.length() == 0) ? tid : seasonDetail.tid);
            tvSeasonList.setId(seasonDetail.id);
            tvSeasonList.setImdbId(seasonDetail.imdb_id);
            tvSeasonList.setSeason(seasonDetail.season);
            tvSeasonList.setEpisode(seasonDetail.episode);
            HashMap<String, Integer> map = seasonDetail.play_progress;
            if (map != null) {
                Integer num = map.get("over");
                tvSeasonList.setOver(num != null ? num.intValue() : 0);
                Integer num2 = map.get("seconds");
                tvSeasonList.setSeconds(num2 != null ? num2.intValue() : 0);
            }
            arrayList.add(tvSeasonList);
        }
        return arrayList;
    }

    public final int indexOfEpisode(List<? extends TvDetail.SeasonDetail> episodes, int season, int episode) {
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Iterator<? extends TvDetail.SeasonDetail> it = episodes.iterator();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            TvDetail.SeasonDetail next = it.next();
            if (next.season == season && next.episode == episode) {
                break;
            }
            i2++;
        }
        if (i2 >= 0) {
            return i2;
        }
        Iterator<? extends TvDetail.SeasonDetail> it2 = episodes.iterator();
        while (it2.hasNext()) {
            if (it2.next().episode == episode) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
