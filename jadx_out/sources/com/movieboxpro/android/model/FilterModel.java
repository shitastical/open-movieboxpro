package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class FilterModel {
    private boolean isSelect;
    private String title;

    public FilterModel(String title) {
        this.title = title;
    }

    public FilterModel(String title, boolean isSelect) {
        this.title = title;
        this.isSelect = isSelect;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
    }
}
