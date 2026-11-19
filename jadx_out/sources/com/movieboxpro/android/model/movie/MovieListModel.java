package com.movieboxpro.android.model.movie;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MovieListModel {
    private int code;
    private int ismore;
    private List<MovieListItem> list;
    private String name;
    private String type;

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

    public List<MovieListItem> getList() {
        return this.list;
    }

    public void setList(List<MovieListItem> list) {
        this.list = list;
    }

    public static class MovieListItem implements Serializable {
        private int count;
        private String cover;
        private String id;
        private List<String> imgArr;
        private int ismore;
        private int itemType;
        private String lid;
        private List<String> movieArr;
        private String name;
        private String type;
        private String username;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getMovieArr() {
            return this.movieArr;
        }

        public void setMovieArr(List<String> movieArr) {
            this.movieArr = movieArr;
        }

        public String getCover() {
            return this.cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getItemType() {
            return this.itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIsmore() {
            return this.ismore;
        }

        public void setIsmore(int ismore) {
            this.ismore = ismore;
        }

        public String getLid() {
            return this.lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<String> getImgArr() {
            return this.imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }
    }
}
