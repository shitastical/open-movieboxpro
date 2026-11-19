package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class SelectableModel {
    private String content;
    private boolean select;

    public SelectableModel(String content) {
        this.content = content;
    }

    public SelectableModel(String content, boolean select) {
        this.content = content;
        this.select = select;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
