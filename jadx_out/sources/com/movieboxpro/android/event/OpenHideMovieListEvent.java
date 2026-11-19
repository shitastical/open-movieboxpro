package com.movieboxpro.android.event;

/* JADX INFO: loaded from: classes5.dex */
public class OpenHideMovieListEvent {
    private boolean open;

    public OpenHideMovieListEvent(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
