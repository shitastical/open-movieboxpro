package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class FavoriteItem extends BaseExpandNode {
    private List<FavoriteEpisodeItem> allEpisodes;
    private String banner_mini;
    private int box_type;
    private String cats;
    private String content_rating;
    private int dislike_total;
    private int episode_count;
    private List<FavoriteSeasonItem> episode_progress_list;
    private ArrayList<Integer> episodes;
    private FavoriteTitle favoriteTitle;
    private String id;
    private String imdb_rating;
    private FavoriteEpisodeItem lastStartWatchedItem;
    private int lastWatchedIndex;
    private FavoriteEpisodeItem lastWatchedItem;
    private LastEpisode last_episode;
    private long last_play_time;
    private int like_status;
    private int like_total;
    private long mark_time;
    private int over_episode_count;
    private String poster;
    private String quality_tag_new;
    private String released;
    private int runtime;
    private int runtime_seconds;
    private String season_episode;
    private List<BaseNode> seasons;
    private int seconds;
    private boolean showProgress;
    private String title;
    private int tomato_meter;
    private int totalEpisode;
    private int updateCount;
    private String update_title;
    private int viewType;
    private int waiting;
    private boolean watched;
    private WatchingItem watchingItem;
    private String year;
    private int watchedEpisodeCount = 0;
    private int lastWatchedCount = 0;

    public WatchingItem getWatchingItem() {
        return this.watchingItem;
    }

    public void setWatchingItem(WatchingItem watchingItem) {
        this.watchingItem = watchingItem;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getContent_rating() {
        return this.content_rating;
    }

    public void setContent_rating(String content_rating) {
        this.content_rating = content_rating;
    }

    public int getLastWatchedIndex() {
        return this.lastWatchedIndex;
    }

    public void setLastWatchedIndex(int lastWatchedIndex) {
        this.lastWatchedIndex = lastWatchedIndex;
    }

    public FavoriteEpisodeItem getLastStartWatchedItem() {
        return this.lastStartWatchedItem;
    }

    public void setLastStartWatchedItem(FavoriteEpisodeItem lastStartWatchedItem) {
        this.lastStartWatchedItem = lastStartWatchedItem;
    }

    public FavoriteEpisodeItem getLastWatchedItem() {
        return this.lastWatchedItem;
    }

    public void setLastWatchedItem(FavoriteEpisodeItem lastWatchedItem) {
        this.lastWatchedItem = lastWatchedItem;
    }

    public int getLastWatchedCount() {
        return this.lastWatchedCount;
    }

    public void setLastWatchedCount(int lastWatchedCount) {
        this.lastWatchedCount = lastWatchedCount;
    }

    public List<FavoriteEpisodeItem> getAllEpisodes() {
        return this.allEpisodes;
    }

    public void setAllEpisodes(List<FavoriteEpisodeItem> allEpisodes) {
        this.allEpisodes = allEpisodes;
    }

    public boolean isShowProgress() {
        return this.showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public FavoriteTitle getFavoriteTitle() {
        return this.favoriteTitle;
    }

    public void setFavoriteTitle(FavoriteTitle favoriteTitle) {
        this.favoriteTitle = favoriteTitle;
    }

    public ArrayList<Integer> getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(ArrayList<Integer> episodes) {
        this.episodes = episodes;
    }

    public int getTotalEpisode() {
        return this.totalEpisode;
    }

    public void setTotalEpisode(int totalEpisode) {
        this.totalEpisode = totalEpisode;
    }

    public boolean isWatched() {
        return this.watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public long getMark_time() {
        return this.mark_time;
    }

    public void setMark_time(long mark_time) {
        this.mark_time = mark_time;
    }

    public int getWaiting() {
        return this.waiting;
    }

    public void setWaiting(int waiting) {
        this.waiting = waiting;
    }

    public String getUpdate_title() {
        return this.update_title;
    }

    public void setUpdate_title(String update_title) {
        this.update_title = update_title;
    }

    public int getWatchedEpisodeCount() {
        return this.watchedEpisodeCount;
    }

    public void setWatchedEpisodeCount(int watchedEpisodeCount) {
        this.watchedEpisodeCount = watchedEpisodeCount;
    }

    public List<FavoriteSeasonItem> getEpisode_progress_list() {
        return this.episode_progress_list;
    }

    public void setEpisode_progress_list(List<FavoriteSeasonItem> episode_progress_list) {
        this.episode_progress_list = episode_progress_list;
    }

    public String getBanner_mini() {
        return this.banner_mini;
    }

    public void setBanner_mini(String banner_mini) {
        this.banner_mini = banner_mini;
    }

    public int getEpisode_count() {
        return this.episode_count;
    }

    public void setEpisode_count(int episode_count) {
        this.episode_count = episode_count;
    }

    public int getOver_episode_count() {
        return this.over_episode_count;
    }

    public void setOver_episode_count(int over_episode_count) {
        this.over_episode_count = over_episode_count;
    }

    public String getSeason_episode() {
        return this.season_episode;
    }

    public void setSeason_episode(String season_episode) {
        this.season_episode = season_episode;
    }

    public LastEpisode getLast_episode() {
        return this.last_episode;
    }

    public void setLast_episode(LastEpisode last_episode) {
        this.last_episode = last_episode;
    }

    public int getTomato_meter() {
        return this.tomato_meter;
    }

    public void setTomato_meter(int tomato_meter) {
        this.tomato_meter = tomato_meter;
    }

    public String getReleased() {
        return this.released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getCats() {
        return this.cats;
    }

    public void setCats(String cats) {
        this.cats = cats;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getLike_status() {
        return this.like_status;
    }

    public void setLike_status(int like_status) {
        this.like_status = like_status;
    }

    public int getLike_total() {
        return this.like_total;
    }

    public void setLike_total(int like_total) {
        this.like_total = like_total;
    }

    public int getDislike_total() {
        return this.dislike_total;
    }

    public void setDislike_total(int dislike_total) {
        this.dislike_total = dislike_total;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int box_type) {
        this.box_type = box_type;
    }

    public String getImdb_rating() {
        return this.imdb_rating;
    }

    public void setImdb_rating(String imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuality_tag_new() {
        return this.quality_tag_new;
    }

    public void setQuality_tag_new(String quality_tag_new) {
        this.quality_tag_new = quality_tag_new;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getUpdateCount() {
        return this.updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public int getRuntime_seconds() {
        return this.runtime_seconds;
    }

    public void setRuntime_seconds(int runtime_seconds) {
        this.runtime_seconds = runtime_seconds;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public long getLast_play_time() {
        return this.last_play_time;
    }

    public void setLast_play_time(long last_play_time) {
        this.last_play_time = last_play_time;
    }

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return this.seasons;
    }

    public List<BaseNode> getSeasons() {
        return this.seasons;
    }

    public void setSeasons(List<BaseNode> seasons) {
        this.seasons = seasons;
    }

    public static class LastEpisode {
        private int dislike_total;
        private int episode;
        private String imdb_rating;
        private long last_play_time;
        private int like_status;
        private int like_total;
        private int runtime;
        private int season;
        private int seconds;
        private String thumbs;
        private String title;

        public int getLike_status() {
            return this.like_status;
        }

        public void setLike_status(int like_status) {
            this.like_status = like_status;
        }

        public int getLike_total() {
            return this.like_total;
        }

        public void setLike_total(int like_total) {
            this.like_total = like_total;
        }

        public int getDislike_total() {
            return this.dislike_total;
        }

        public void setDislike_total(int dislike_total) {
            this.dislike_total = dislike_total;
        }

        public long getLast_play_time() {
            return this.last_play_time;
        }

        public void setLast_play_time(long last_play_time) {
            this.last_play_time = last_play_time;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getRuntime() {
            return this.runtime;
        }

        public void setRuntime(int runtime) {
            this.runtime = runtime;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImdb_rating() {
            return this.imdb_rating;
        }

        public void setImdb_rating(String imdb_rating) {
            this.imdb_rating = imdb_rating;
        }

        public int getSeason() {
            return this.season;
        }

        public void setSeason(int season) {
            this.season = season;
        }

        public int getEpisode() {
            return this.episode;
        }

        public void setEpisode(int episode) {
            this.episode = episode;
        }

        public String getThumbs() {
            return this.thumbs;
        }

        public void setThumbs(String thumbs) {
            this.thumbs = thumbs;
        }
    }
}
