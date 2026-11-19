package com.movieboxpro.android.db.entity;

/* JADX INFO: loaded from: classes5.dex */
public class TestNetRecode {
    public String country;
    public String description;
    public int display_order;
    public String domain;
    public Long endTime;
    public int id;
    private int ids;
    public float ratio;
    public Long startTime = -1L;
    public String state = "start";
    public String url;

    public int getIds() {
        return this.ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getDisplay_order() {
        return this.display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public float getRatio() {
        return this.ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
