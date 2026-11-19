package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class TestLoadingModel {
    private int id;
    private boolean isTesting = true;
    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TestLoadingModel(int id) {
        this.id = id;
    }

    public boolean isTesting() {
        return this.isTesting;
    }

    public void setTesting(boolean testing) {
        this.isTesting = testing;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
