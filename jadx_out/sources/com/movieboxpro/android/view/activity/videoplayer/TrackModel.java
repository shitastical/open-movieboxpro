package com.movieboxpro.android.view.activity.videoplayer;

/* JADX INFO: loaded from: classes5.dex */
public class TrackModel {
    private String TrackInfo;
    private int TrackModel;
    private String TrackName;
    private String TrackType;
    private boolean isSelect;

    public String getTrackName() {
        return this.TrackName;
    }

    public void setTrackName(String trackName) {
        this.TrackName = trackName;
    }

    public int getTrackModel() {
        return this.TrackModel;
    }

    public void setTrackModel(int trackModel) {
        this.TrackModel = trackModel;
    }

    public String getTrackType() {
        return this.TrackType;
    }

    public void setTrackType(String trackType) {
        this.TrackType = trackType;
    }

    public String getTrackInfo() {
        return this.TrackInfo;
    }

    public void setTrackInfo(String trackInfo) {
        this.TrackInfo = trackInfo;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
    }
}
