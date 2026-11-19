package com.movieboxpro.android.db.entity;

/* JADX INFO: loaded from: classes5.dex */
public class PlayRecode {
    private int box_type;
    private int episode;
    private int id;
    private String imdb_id;
    private String movieId;
    private String privateid;
    private int quality;
    private int season;
    private int start_time;
    private String title;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrivateid() {
        return this.privateid;
    }

    public void setPrivateid(String privateid) {
        this.privateid = privateid;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int box_type) {
        this.box_type = box_type;
    }

    public String getImdb_id() {
        return this.imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStart_time() {
        return this.start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
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
}
