package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes5.dex */
public class TvSeasonList implements Parcelable {
    public static final Parcelable.Creator<TvSeasonList> CREATOR = new Parcelable.Creator<TvSeasonList>() { // from class: com.movieboxpro.android.model.TvSeasonList.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TvSeasonList createFromParcel(Parcel source) {
            return new TvSeasonList(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TvSeasonList[] newArray(int size) {
            return new TvSeasonList[size];
        }
    };
    private int episode;
    private String id;
    private String imdbId;
    private int over;
    private int season;
    private int seconds;
    private String tid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getImdbId() {
        return this.imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getSeason() {
        return this.season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return this.episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getOver() {
        return this.over;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tid);
        dest.writeInt(this.season);
        dest.writeInt(this.episode);
        dest.writeString(this.id);
        dest.writeString(this.imdbId);
        dest.writeInt(this.over);
        dest.writeInt(this.seconds);
    }

    public TvSeasonList() {
    }

    protected TvSeasonList(Parcel in) {
        this.tid = in.readString();
        this.season = in.readInt();
        this.episode = in.readInt();
        this.id = in.readString();
        this.imdbId = in.readString();
        this.over = in.readInt();
        this.seconds = in.readInt();
    }
}
