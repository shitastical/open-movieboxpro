package com.movieboxpro.android.event;

/* JADX INFO: loaded from: classes5.dex */
public class MovieMoreEvent {
    private int gener;
    private int sortby;
    private int year;

    public MovieMoreEvent(int sortby, int year, int gener) {
        this.sortby = sortby;
        this.year = year;
        this.gener = gener;
    }
}
