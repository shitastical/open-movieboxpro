package com.movieboxpro.android.model.tv;

import android.app.Activity;
import com.google.gson.annotations.SerializedName;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.service.DealWatchNextService;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TvDetail extends BaseMediaModel implements Serializable {
    public String actors;
    public String banner_mini;
    public String content_rating;
    public String history;

    @SerializedName(DealWatchNextService.SEASON)
    public String[] seasonList;
    public String season_episode;
    public String year_year;
    public String[] years;

    @SerializedName(DealWatchNextService.EPISODE)
    public List<SeasonDetail> episodeList = new ArrayList();
    public SeasonDetail seasonDetail = new SeasonDetail();

    public static class History implements Serializable {
        public int episode;
        public int over;
        public int season;
        public int seconds;
    }

    public static class SeasonDetail implements Serializable {
        public long add_time;
        public int code_file;
        public int download;
        public String downloadId;
        public int downloadProgress;
        public long downloadSize;
        public int downloadStatus;
        public int episode;
        public String errorMsg;
        public String fileName;
        public String id;
        public String imdb_id;
        public String imdb_id_status;
        public String imdb_rating;
        public boolean isFFmpeg;
        public String mb_id;
        public String path;
        public HashMap<String, Integer> play_progress;
        public String quality_tag;
        public String realQuality;
        public String released;
        public long released_timestamp;
        public String runtime;
        public int season;
        public long size;
        public int source_file;
        public int srt_status;
        public int state;
        public String synopsis;
        public String thumbs;
        public String tid;
        public String title;
        public long update_time;
        public String url;
        public int view;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public int getBoxType() {
        return 2;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public void startService(String type, Activity activity) {
    }

    public TvDetail() {
    }

    public TvDetail(BaseMediaModel mediaModel) {
        this.id = mediaModel.id;
    }

    public TvDetail(DownloadModel download) {
        this.privateId = download.getPrivateid();
        this.privateId = download.getPrivateid();
        this.id = download.getMovieId();
        this.title = download.getTitle();
        this.poster = download.getPoster();
        this.box_type = download.getBox_type();
        this.seasonDetail.season = download.getSeason();
        this.seasonDetail.episode = download.getEpisode();
        this.seasonDetail.runtime = download.getRuntime();
        this.display = Integer.parseInt(download.getDisplay());
        this.add_time = Long.parseLong(download.getAdd_time());
        this.seasonDetail.download = Integer.parseInt(download.getDownloads());
        this.seasonDetail.id = download.getSeasonid();
        this.seasonDetail.title = download.getSeasontitle();
        this.seasonDetail.thumbs = download.getSeasonthumbs();
        this.fileName = download.getFileName();
        this.path = download.getPath();
        this.qualitys = download.getQuality();
        this.size = download.getSize();
        this.format = download.getFormat();
        this.vip_only = Integer.parseInt(download.getVip_only());
        this.count = download.getCount();
        this.dateline = download.getDateline();
        this.tmfid = download.getTmfid();
        this.fileLength = download.getFileLength();
        this.statue = download.getStatue();
        this.progress = download.getProgress();
        this.speed = download.getSpeed();
        BaseMediaModel.DownloadFile downloadFile = new BaseMediaModel.DownloadFile();
        downloadFile.path = download.getPath();
        downloadFile.real_quality = download.getQuality();
        downloadFile.format = download.getFormat();
        downloadFile.size = download.getSize();
        downloadFile.vip_only = Integer.parseInt(download.getVip_only());
        downloadFile.count = download.getCount();
        downloadFile.dateline = download.getDateline();
        downloadFile.filename = download.getFileName();
        this.list.add(downloadFile);
    }

    public List<SeasonDetail> getEpisodeList() {
        return this.episodeList;
    }

    public void setEpisodeList(List<SeasonDetail> episodeList) {
        this.episodeList = episodeList;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getBanner_mini() {
        return this.banner_mini;
    }

    public void setBanner_mini(String banner_mini) {
        this.banner_mini = banner_mini;
    }

    public void setSeasonList(String[] season) {
        this.seasonList = this.seasonList;
    }

    public String[] getSeasonList() {
        return this.seasonList;
    }

    public String[] getYears() {
        return this.years;
    }

    public void setYears(String[] years) {
        this.years = years;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getYear_year() {
        return this.year_year;
    }

    public void setYear_year(String year_year) {
        this.year_year = year_year;
    }

    public String getContent_rating() {
        return this.content_rating;
    }

    public void setContent_rating(String content_rating) {
        this.content_rating = content_rating;
    }

    public String getSeason_episode() {
        return this.season_episode;
    }

    public void setSeason_episode(String season_episode) {
        this.season_episode = season_episode;
    }

    public SeasonDetail getSeasonDetail() {
        return this.seasonDetail;
    }

    public void setSeasonDetail(SeasonDetail seasonDetail) {
        this.seasonDetail = seasonDetail;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public String getId() {
        return this.id + "_" + this.box_type + "_" + this.seasonDetail.season + "_" + this.seasonDetail.episode;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public String getTid() {
        return this.id;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public int getSeason() {
        return this.seasonDetail.season;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public int getEpisode() {
        return this.seasonDetail.episode;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public Observable<String> getUrl() {
        return Http.getService().Tv_downloadurl(API.Tv.TV_DOWNLOADURL, App.isLogin() ? App.getUserData().uid_v2 : "", this.id, this.seasonDetail.season, this.seasonDetail.episode);
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public Observable<String> getSrt() {
        return Http.getService().Tv_srt_auto(API.Tv.TV_SRT_AUTO, this.id, this.seasonDetail.season, this.seasonDetail.episode, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "");
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public boolean saveInDao(int i, Activity activity) {
        super.saveInDao(i, activity);
        if (this.seasonDetail == null || this.list.get(i).path == null) {
            return false;
        }
        Download download = new Download();
        download.setPrivateid(this.id + "_" + this.seasonDetail.id);
        download.setMovieId(this.id);
        download.setTitle(this.title);
        download.setPoster(this.poster);
        download.setIs_collect(this.is_collect);
        download.setBox_type(this.box_type);
        download.setSeason(this.seasonDetail.season);
        download.setEpisode(this.seasonDetail.episode);
        download.setRuntime(this.seasonDetail.runtime);
        download.setDisplay(String.valueOf(this.display));
        download.setAdd_time(String.valueOf(this.add_time));
        download.setDownload(this.seasonDetail.download + "");
        download.setSeasonid(this.seasonDetail.id);
        download.setSeasontitle(this.seasonDetail.title);
        download.setSeasonthumbs(this.seasonDetail.thumbs);
        download.setFileName(this.list.get(i).filename);
        download.setPath(this.list.get(i).path);
        download.setQuality(this.list.get(i).real_quality);
        download.setSize(this.list.get(i).size);
        download.setFormat(this.list.get(i).format);
        download.setVip_only(this.list.get(i).vip_only + "");
        download.setCount(this.list.get(i).count);
        download.setDateline(this.list.get(i).dateline);
        download.setMmfid(this.list.get(i).tmfid + "");
        download.setFileLength(this.fileLength);
        download.setStatue(1);
        download.setProgress(this.progress);
        download.setSpeed(this.speed);
        download.setFid(String.valueOf(this.list.get(i).fid));
        App.getDB().downloadDao().insert(download);
        startService(Constant.ACTION.MOVIE_DOWNLOAD, activity);
        return true;
    }
}
