package com.movieboxpro.android.model.common;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Homelist {
    public String adImgUrl;
    public int box_type;
    public int code;
    public int ismore;
    public String name;
    public String type;
    public int viewType = 0;
    public List<Typelist> list = new ArrayList();

    public String getAdImgUrl() {
        return this.adImgUrl;
    }

    public void setAdImgUrl(String adImgUrl) {
        this.adImgUrl = adImgUrl;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsmore() {
        return this.ismore;
    }

    public void setIsmore(int ismore) {
        this.ismore = ismore;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int box_type) {
        this.box_type = box_type;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public List<Typelist> getList() {
        return this.list;
    }

    public void setList(List<Typelist> list) {
        this.list = list;
    }

    public static class History {
        private int episode;
        private int over;
        private int runtime;
        private int season;
        private int seconds;

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

        public int getEpisode() {
            return this.episode;
        }

        public void setEpisode(int episode) {
            this.episode = episode;
        }

        public int getSeason() {
            return this.season;
        }

        public void setSeason(int season) {
            this.season = season;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }
    }

    public static class Typelist {
        public String banner_mini;
        public int box_type;
        private String content_rating;
        private String count;
        private History history;
        public String id;
        private float imdb_rating;
        private List<String> imgArr;
        private String lid;
        private String name;
        public String poster;
        public String poster_2;
        public String quality_tag;
        private int runtime;
        public String season_episode;
        private int seconds;
        public String title;
        private int tomato_meter;
        private String type;
        public String update_title;
        private String url;
        private String username;
        public int viewType = 0;

        public int getTomato_meter() {
            return this.tomato_meter;
        }

        public void setTomato_meter(int tomato_meter) {
            this.tomato_meter = tomato_meter;
        }

        public String getContent_rating() {
            return this.content_rating;
        }

        public void setContent_rating(String content_rating) {
            this.content_rating = content_rating;
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

        public History getHistory() {
            return this.history;
        }

        public void setHistory(History history) {
            this.history = history;
        }

        public String getLid() {
            return this.lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public List<String> getImgArr() {
            return this.imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }

        public String getCount() {
            return this.count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getImdb_rating() {
            return this.imdb_rating;
        }

        public void setImdb_rating(float imdb_rating) {
            this.imdb_rating = imdb_rating;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getBanner_mini() {
            return this.banner_mini;
        }

        public void setBanner_mini(String banner_mini) {
            this.banner_mini = banner_mini;
        }

        public String getSeason_episode() {
            return this.season_episode;
        }

        public void setSeason_episode(String season_episode) {
            this.season_episode = season_episode;
        }

        public String getUpdate_title() {
            return this.update_title;
        }

        public void setUpdate_title(String update_title) {
            this.update_title = update_title;
        }

        public String getQuality_tag() {
            return this.quality_tag;
        }

        public void setQuality_tag(String quality_tag) {
            this.quality_tag = quality_tag;
        }

        public int getBox_type() {
            return this.box_type;
        }

        public void setBox_type(int box_type) {
            this.box_type = box_type;
        }

        public String getPoster_2() {
            return this.poster_2;
        }

        public void setPoster_2(String poster_2) {
            this.poster_2 = poster_2;
        }

        public int getViewType() {
            return this.viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }
    }
}
