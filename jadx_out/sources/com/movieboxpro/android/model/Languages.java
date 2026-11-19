package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class Languages {
    private boolean expanded;
    private int icon;
    private String name;
    private String province;

    public Languages(String name, String province, boolean isExpand) {
        this.name = name;
        this.province = province;
        this.expanded = isExpand;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
