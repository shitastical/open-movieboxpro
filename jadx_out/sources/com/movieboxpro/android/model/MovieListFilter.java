package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class MovieListFilter {
    private boolean select;
    private String serviceText;
    private String title;

    public MovieListFilter(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServiceText() {
        return this.serviceText;
    }

    public void setServiceText(String serviceText) {
        this.serviceText = serviceText;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
