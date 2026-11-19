package com.movieboxpro.android.event;

/* JADX INFO: loaded from: classes5.dex */
public class OnBackPressedEvent {
    public static final int ACCOUNT = 6;
    public static final int FAVORITES = 5;
    public static final int FEATURED = 1;
    public static final int MOVIELIST = 4;
    public static final int MOVIES = 2;
    public static final int TVGUIDE = 7;
    public static final int TVSHOWS = 3;
    private int type;

    public OnBackPressedEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
