package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class DrawerItem {
    public int icon;
    public String next;
    public String text;
    public String title;

    public DrawerItem(int icon, String title) {
        this(icon, title, null);
    }

    public DrawerItem(int icon, String title, String text) {
        this(icon, title, text, null);
    }

    public DrawerItem(int icon, String title, String text, String next) {
        this.icon = icon;
        this.title = title;
        this.text = text;
        this.next = next;
    }
}
