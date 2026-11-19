package com.movieboxpro.android.event;

import com.movieboxpro.android.model.movie.MovelList;

/* JADX INFO: loaded from: classes5.dex */
public class DownLoadEvent {
    public MovelList.MovieDownload download;
    public String id;
    public boolean isPaused;

    public DownLoadEvent(String id, boolean isPaused, MovelList.MovieDownload download) {
        this.id = id;
        this.isPaused = isPaused;
        this.download = download;
    }

    public String toString() {
        return this.id + this.isPaused;
    }
}
