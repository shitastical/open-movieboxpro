package com.movieboxpro.android.model;

import com.movieboxpro.android.db.entity.Download;

/* JADX INFO: loaded from: classes5.dex */
public class DownloadModel {
    public String add_time;
    public int box_type;
    public int childCount;
    public String count;
    public long dateline;
    public String display;
    public String downloads;
    public int episode;
    public String fid;
    public long fileLength;
    public String fileName;
    public String format;
    public int isChecked;
    public int is_collect;
    public String mbId;
    public String mmfid;
    public String movieId;
    public String path;
    public String poster;
    public String privateid;
    public int progress;
    public String quality;
    public String runtime;
    public int season;
    public String seasonid;
    public String seasonthumbs;
    public String seasontitle;
    public String size;
    public long speed;
    public int statue;
    public String title;
    public int tmfid;
    public String vip_only;

    public DownloadModel() {
        this.vip_only = "0";
        this.statue = 0;
        this.progress = 0;
        this.isChecked = 0;
        this.childCount = 0;
    }

    public DownloadModel(Download download) {
        this.vip_only = "0";
        this.statue = 0;
        this.progress = 0;
        this.isChecked = 0;
        this.childCount = 0;
        this.movieId = download.getMovieId();
        this.mbId = download.getMbId();
        this.title = download.getTitle();
        this.vip_only = download.getVip_only();
        this.display = download.getDisplay();
        this.poster = download.getPoster();
        this.runtime = download.getRuntime();
        this.add_time = download.getAdd_time();
        this.is_collect = download.getIs_collect();
        this.downloads = download.getDownload();
        this.fileName = download.getFileName();
        this.path = download.getPath();
        this.quality = download.getQuality();
        this.fileLength = download.getFileLength();
        this.size = download.getSize();
        this.statue = download.getStatue();
        this.progress = download.getProgress();
        this.speed = download.getSpeed();
        this.format = download.getFormat();
        this.count = download.getCount();
        this.dateline = download.getDateline();
        this.mmfid = download.getMmfid();
        this.season = download.getSeason();
        this.episode = download.getEpisode();
        this.box_type = download.getBox_type();
        this.tmfid = download.getTmfid();
        this.seasonid = download.getSeasonid();
        this.seasontitle = download.getSeasontitle();
        this.seasonthumbs = download.getSeasonthumbs();
        this.privateid = download.getPrivateid();
        this.fid = download.getFid();
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public int getChildCount() {
        return this.childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMbId() {
        return this.mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVip_only() {
        return this.vip_only;
    }

    public void setVip_only(String vip_only) {
        this.vip_only = vip_only;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRuntime() {
        return this.runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getAdd_time() {
        return this.add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getIs_collect() {
        return this.is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public String getDownloads() {
        return this.downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public long getFileLength() {
        return this.fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStatue() {
        return this.statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getSpeed() {
        return this.speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public long getDateline() {
        return this.dateline;
    }

    public void setDateline(long dateline) {
        this.dateline = dateline;
    }

    public String getMmfid() {
        return this.mmfid;
    }

    public void setMmfid(String mmfid) {
        this.mmfid = mmfid;
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

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int box_type) {
        this.box_type = box_type;
    }

    public int getTmfid() {
        return this.tmfid;
    }

    public void setTmfid(int tmfid) {
        this.tmfid = tmfid;
    }

    public String getSeasonid() {
        return this.seasonid;
    }

    public void setSeasonid(String seasonid) {
        this.seasonid = seasonid;
    }

    public String getSeasontitle() {
        return this.seasontitle;
    }

    public void setSeasontitle(String seasontitle) {
        this.seasontitle = seasontitle;
    }

    public String getSeasonthumbs() {
        return this.seasonthumbs;
    }

    public void setSeasonthumbs(String seasonthumbs) {
        this.seasonthumbs = seasonthumbs;
    }

    public String getPrivateid() {
        return this.privateid;
    }

    public void setPrivateid(String privateid) {
        this.privateid = privateid;
    }

    public int getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}
