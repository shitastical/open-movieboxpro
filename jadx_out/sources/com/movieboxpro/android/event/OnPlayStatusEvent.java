package com.movieboxpro.android.event;

/* JADX INFO: loaded from: classes5.dex */
public class OnPlayStatusEvent {
    private int status;

    public OnPlayStatusEvent(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
