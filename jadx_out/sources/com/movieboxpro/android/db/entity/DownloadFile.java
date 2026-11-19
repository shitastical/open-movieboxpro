package com.movieboxpro.android.db.entity;

import j$.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class DownloadFile {
    public static final int COMPLETE = 1;
    public static final int DOWNLOADING = 6;
    public static final int ERROR = 7;
    public static final int LOADING = 5;
    public static final int OTHER = 3;
    public static final int PAUSE = 2;
    public static final int START = 4;
    public static final int WAITING = 8;
    private int boxType;
    private boolean check;
    private String device;
    private String downloadId = "";
    private long downloadSize;
    private long downloadTime;
    private int episode;
    private String episodeName;
    private int episodeNum;
    private String errorMsg;
    private boolean ffmpeg;
    private String fileName;
    private boolean hls;
    private String id;
    private String imdbId;
    private String mid;
    private String path;
    private float percent;
    private String poster;
    private int progress;
    private String quality;
    private String recordTime;
    private int season;
    private long size;
    private String speed;
    private int status;
    private String thumb;
    private String tid;
    private String url;
    private String videoName;

    public float getPercent() {
        return this.percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public boolean isHls() {
        return this.hls;
    }

    public void setHls(boolean hls) {
        this.hls = hls;
    }

    public String getImdbId() {
        return this.imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isFfmpeg() {
        return this.ffmpeg;
    }

    public void setFfmpeg(boolean ffmpeg) {
        this.ffmpeg = ffmpeg;
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public long getDownloadTime() {
        return this.downloadTime;
    }

    public void setDownloadTime(long downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getQuality() {
        return this.quality;
    }

    public String getEpisodeName() {
        return this.episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getEpisodeNum() {
        return this.episodeNum;
    }

    public void setEpisodeNum(int episodeNum) {
        this.episodeNum = episodeNum;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDownloadId() {
        return this.downloadId;
    }

    public void setDownloadId(String downloadId) {
        this.downloadId = downloadId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDownloadSize() {
        return this.downloadSize;
    }

    public void setDownloadSize(long downloadSize) {
        this.downloadSize = downloadSize;
    }

    public int getBoxType() {
        return this.boxType;
    }

    public void setBoxType(int boxType) {
        this.boxType = boxType;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DownloadFile downloadFile = (DownloadFile) obj;
        if (this.status == downloadFile.status && this.season == downloadFile.season && this.episode == downloadFile.episode && this.size == downloadFile.size && this.downloadSize == downloadFile.downloadSize && this.boxType == downloadFile.boxType && this.downloadTime == downloadFile.downloadTime && this.episodeNum == downloadFile.episodeNum && this.check == downloadFile.check && this.ffmpeg == downloadFile.ffmpeg && this.progress == downloadFile.progress && this.hls == downloadFile.hls && this.downloadId.equals(downloadFile.downloadId) && Objects.equals(this.id, downloadFile.id) && Objects.equals(this.url, downloadFile.url) && Objects.equals(this.path, downloadFile.path) && Objects.equals(this.fileName, downloadFile.fileName) && Objects.equals(this.mid, downloadFile.mid) && Objects.equals(this.tid, downloadFile.tid) && Objects.equals(this.videoName, downloadFile.videoName) && Objects.equals(this.poster, downloadFile.poster) && Objects.equals(this.quality, downloadFile.quality) && Objects.equals(this.thumb, downloadFile.thumb) && Objects.equals(this.episodeName, downloadFile.episodeName) && Objects.equals(this.imdbId, downloadFile.imdbId) && Objects.equals(this.speed, downloadFile.speed) && Objects.equals(this.errorMsg, downloadFile.errorMsg) && Objects.equals(this.recordTime, downloadFile.recordTime)) {
            return Objects.equals(this.device, downloadFile.device);
        }
        return false;
    }
}
