package com.movieboxpro.android.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TrailerItem implements Serializable {
    private String img;
    private String url;
    private String videoId;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
