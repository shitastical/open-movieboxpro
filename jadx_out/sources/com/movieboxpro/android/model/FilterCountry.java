package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class FilterCountry {
    private String country;
    private String icon;
    private int resId;
    private boolean select;

    public FilterCountry() {
    }

    public FilterCountry(String country, int resId) {
        this.country = country;
        this.resId = resId;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
