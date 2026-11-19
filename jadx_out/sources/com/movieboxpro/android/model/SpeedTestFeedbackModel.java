package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class SpeedTestFeedbackModel {
    private int average;
    private String group;
    private String test_url_id;

    public String getTest_url_id() {
        return this.test_url_id;
    }

    public void setTest_url_id(String test_url_id) {
        this.test_url_id = test_url_id;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getAverage() {
        return this.average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
