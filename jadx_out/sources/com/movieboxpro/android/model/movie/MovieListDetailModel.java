package com.movieboxpro.android.model.movie;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MovieListDetailModel {
    private String avatar;
    private int collect_num;
    private int count;
    private long dateline;
    private String desc;
    private int is_collect;
    private int ismine;
    private String lid;
    private List<ListBean> list;
    private String name;
    private int status;
    private String uid;
    private String username;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLid() {
        return this.lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public int getIsmine() {
        return this.ismine;
    }

    public void setIsmine(int ismine) {
        this.ismine = ismine;
    }

    public int getIs_collect() {
        return this.is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCollect_num() {
        return this.collect_num;
    }

    public void setCollect_num(int collect_num) {
        this.collect_num = collect_num;
    }

    public long getDateline() {
        return this.dateline;
    }

    public void setDateline(long dateline) {
        this.dateline = dateline;
    }

    public List<ListBean> getList() {
        return this.list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int box_type;
        private String description;
        private int id;
        private String imdb_rating;

        @SerializedName("3d")
        private int is3d;
        private String mark;
        private String poster;
        private String quality_tag;
        private int runtime;
        private String title;
        private int tomato_meter;
        private String year;

        public int getTomato_meter() {
            return this.tomato_meter;
        }

        public void setTomato_meter(int tomato_meter) {
            this.tomato_meter = tomato_meter;
        }

        public int getBox_type() {
            return this.box_type;
        }

        public void setBox_type(int box_type) {
            this.box_type = box_type;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMark() {
            return this.mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYear() {
            return this.year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public int getRuntime() {
            return this.runtime;
        }

        public void setRuntime(int runtime) {
            this.runtime = runtime;
        }

        public String getImdb_rating() {
            return this.imdb_rating;
        }

        public void setImdb_rating(String imdb_rating) {
            this.imdb_rating = imdb_rating;
        }

        public String getPoster() {
            return this.poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getIs3d() {
            return this.is3d;
        }

        public void setIs3d(int is3d) {
            this.is3d = is3d;
        }

        public String getQuality_tag() {
            return this.quality_tag;
        }

        public void setQuality_tag(String quality_tag) {
            this.quality_tag = quality_tag;
        }
    }
}
