package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class HistoryModel {
    private String actors;
    private int box_type;
    private String description;
    private HistoryBean history;
    private int id;
    private String poster;
    private int seconds;
    private String title;
    private int year;

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int box_type) {
        this.box_type = box_type;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HistoryBean getHistory() {
        return this.history;
    }

    public void setHistory(HistoryBean history) {
        this.history = history;
    }

    public static class HistoryBean {
        private int episode;
        private int season;
        private int seconds;

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

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }
    }
}
