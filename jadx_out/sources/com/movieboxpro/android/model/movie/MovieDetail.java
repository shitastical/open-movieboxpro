package com.movieboxpro.android.model.movie;

import android.app.Activity;
import android.text.TextUtils;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.model.TrailerItem;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MovieDetail extends BaseMediaModel implements Serializable {
    public String actors;
    public String content_rating;
    public List<MovieFile> file = new ArrayList();
    public List<MovieListModel.MovieListItem> playlists;
    public List<Recommend> recommend;
    public String runtime;
    public String source_file;
    public String trailer;
    private List<TrailerItem> trailer_url_arr;
    public String writer;

    public static class MovieFile implements Serializable {
        public String bitstream;
        public String count;
        public long dateline;
        public String filename;
        public String format;
        public String mmfid;
        public String path;
        public String quality;
        public String size;
        public String vip_only;
    }

    public static class Recommend implements Serializable {
        public String mid;
        public String poster;
        public String quality_tag;
        public String title;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public int getBoxType() {
        return 1;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public void startService(String type, Activity activity) {
    }

    public MovieDetail() {
    }

    public MovieDetail(DownloadModel download) {
        this.privateId = download.getPrivateid();
        this.id = download.getMovieId();
        this.mb_id = download.getMbId();
        this.box_type = download.getBox_type();
        this.title = download.getTitle();
        this.display = Integer.parseInt(download.getDisplay());
        this.poster = download.getPoster();
        this.runtime = download.getRuntime();
        this.add_time = Long.parseLong(download.getAdd_time());
        this.is_collect = download.getIs_collect();
        this.download = download.getDownloads();
        this.fileName = download.getFileName();
        this.path = download.getPath();
        this.qualitys = download.getQuality();
        this.size = download.getSize();
        this.format = download.getFormat();
        this.vip_only = Integer.valueOf(download.getVip_only()).intValue();
        this.count = download.getCount();
        this.dateline = download.getDateline();
        this.fileLength = download.getFileLength();
        this.statue = download.getStatue();
        this.progress = download.getProgress();
        this.speed = download.getSpeed();
        BaseMediaModel.DownloadFile downloadFile = new BaseMediaModel.DownloadFile();
        downloadFile.path = download.getPath();
        downloadFile.real_quality = download.getQuality();
        downloadFile.format = download.getFormat();
        downloadFile.size = download.getSize();
        downloadFile.vip_only = Integer.valueOf(download.getVip_only()).intValue();
        downloadFile.count = download.getCount();
        downloadFile.dateline = download.getDateline();
        downloadFile.filename = download.getFileName();
        this.list.add(downloadFile);
    }

    public List<TrailerItem> getTrailer_url_arr() {
        return this.trailer_url_arr;
    }

    public void setTrailer_url_arr(List<TrailerItem> trailer_url_arr) {
        this.trailer_url_arr = trailer_url_arr;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public boolean saveInDao(int i, Activity activity) {
        super.saveInDao(i, activity);
        if (this.list.get(i).path == null) {
            return false;
        }
        Download download = new Download();
        download.setMovieId(this.id);
        download.setMbId(this.mb_id);
        download.setBox_type(this.box_type);
        download.setTitle(this.title);
        download.setDisplay(String.valueOf(this.display));
        download.setPoster(this.poster);
        download.setRuntime(this.runtime);
        download.setAdd_time(String.valueOf(this.add_time));
        download.setIs_collect(this.is_collect);
        download.setDownload(this.download);
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
        download.setPrivateid(this.id);
        App.getDB().downloadDao().insert(download);
        startService(Constant.ACTION.MOVIE_DOWNLOAD, activity);
        return true;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public String getId() {
        return this.id;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public String getTid() {
        return this.id;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public Observable<String> getUrl() {
        String str;
        String str2;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        return Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", this.id, "", str2, str);
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public Observable<String> getSrt() {
        return Http.getService().Movie_srt_auto(API.Movie.MOVIE_SRT_AUTO, this.id, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "");
    }
}
