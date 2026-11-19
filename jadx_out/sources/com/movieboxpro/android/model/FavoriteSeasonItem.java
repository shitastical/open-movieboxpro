package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class FavoriteSeasonItem extends BaseExpandNode {
    private List<FavoriteEpisodeItem> episode_list;
    private List<BaseNode> episodes;
    private String id;
    private FavoriteEpisodeItem lastStartWatchedItem;
    private int lastWatchedIndex;
    private FavoriteEpisodeItem lastWatchedItem;
    private int season;
    private List<Integer> watchList;
    private int watchedCount;

    public FavoriteEpisodeItem getLastWatchedItem() {
        return this.lastWatchedItem;
    }

    public void setLastWatchedItem(FavoriteEpisodeItem lastWatchedItem) {
        this.lastWatchedItem = lastWatchedItem;
    }

    public FavoriteEpisodeItem getLastStartWatchedItem() {
        return this.lastStartWatchedItem;
    }

    public void setLastStartWatchedItem(FavoriteEpisodeItem lastStartWatchedItem) {
        this.lastStartWatchedItem = lastStartWatchedItem;
    }

    public int getLastWatchedIndex() {
        return this.lastWatchedIndex;
    }

    public void setLastWatchedIndex(int lastWatchedIndex) {
        this.lastWatchedIndex = lastWatchedIndex;
    }

    public List<Integer> getWatchList() {
        return this.watchList;
    }

    public void setWatchList(List<Integer> watchList) {
        this.watchList = watchList;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWatchedCount() {
        return this.watchedCount;
    }

    public void setWatchedCount(int watchedCount) {
        this.watchedCount = watchedCount;
    }

    public int getSeason() {
        return this.season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public List<FavoriteEpisodeItem> getEpisode_list() {
        return this.episode_list;
    }

    public void setEpisode_list(List<FavoriteEpisodeItem> episode_list) {
        this.episode_list = episode_list;
    }

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return this.episodes;
    }

    public List<BaseNode> getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(List<BaseNode> episodes) {
        this.episodes = episodes;
    }
}
