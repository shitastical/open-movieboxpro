package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class FavoriteEpisodeItem extends BaseNode {
    private int episode;
    private String id;
    private boolean lastItem;
    private boolean lastSeason;
    private int over;
    private int runtime;
    private int season;
    private int seconds;
    private String thumbs;
    private String title;
    private int viewType;

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return null;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getThumbs() {
        return this.thumbs;
    }

    public void setThumbs(String thumbs) {
        this.thumbs = thumbs;
    }

    public boolean isLastItem() {
        return this.lastItem;
    }

    public void setLastItem(boolean lastItem) {
        this.lastItem = lastItem;
    }

    public boolean isLastSeason() {
        return this.lastSeason;
    }

    public void setLastSeason(boolean lastSeason) {
        this.lastSeason = lastSeason;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getOver() {
        return this.over;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
