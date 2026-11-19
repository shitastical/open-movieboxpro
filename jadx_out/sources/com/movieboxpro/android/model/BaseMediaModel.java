package com.movieboxpro.android.model;

import android.app.Activity;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.CheckUtils;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BaseMediaModel implements MediaFunction, Serializable {
    public long add_time;
    public int audience_score;
    public String[] audio_lang;
    public String banner;
    public int box_type;
    public String cats;
    public int code_file;
    public String collect;
    public String count;
    public long dateline;
    public String description;
    public String director;
    public int display;
    public String download;

    @SerializedName("ingore_episode")
    public int episode;
    public String episodeName;
    public long fileLength;
    public String fileName;
    public String format;
    public String id;
    public String imdb_id;
    public String imdb_link;
    public String imdb_rating;
    public int is_collect;
    public List<MoreLanguage> language;
    public String mb_id;
    public String path;
    public String poster;
    public String privateId;
    public String[] quality;
    public String quality_tag_new;
    public String[] quality_tags;
    public String qualitys;
    public String released;
    public long released_timestamp;

    @SerializedName("ingore_season")
    public int season;
    public int seconds;
    public String size;
    public long speed;
    public int state;
    public String thumb;
    public String title;
    public int tmfid;
    public int tomato_meter;
    public String trailer_url;
    public long update_time;
    public String view;
    public int vip_only;
    public String year;
    public int isChecked = 0;
    public int plan_watched = -1;
    public int statue = 0;
    public int progress = 0;
    public List<DownloadFile> list = new ArrayList();

    public static class DownloadFile implements Serializable {
        public String bitrate;
        public String bitstream;
        public String channel_layout;
        public int colorbit;
        public String count;
        public long dateline;
        public int fid;
        public String filename;
        public String format;
        public int h265;
        public int hdr;
        public boolean hls;
        public String lang;
        public boolean local;
        public String name;
        public int original;
        public String path;
        public String[] path2;
        public String quality;
        public String real_quality;
        public String sample_rate;
        public String size;
        public long size_bytes;
        public String type;
        public int vip_only;
        public int tmfid = -1;
        public int mmfid = -1;
    }

    public static class MoreLanguage implements Serializable {
        public String lang;
        public String title;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public Observable<String> getSrt() {
        return null;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public String getTid() {
        return null;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public Observable<String> getUrl() {
        return null;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public void startService(String type, Activity activity) {
    }

    public boolean isFileExists() {
        return CheckUtils.checkFileExists(getFilePath(), "");
    }

    public int getAudience_score() {
        return this.audience_score;
    }

    public void setAudience_score(int audience_score) {
        this.audience_score = audience_score;
    }

    public String getFilePath() {
        if (getFileName() != null) {
            return Constant.DIR_DOWNLOAD;
        }
        return null;
    }

    private String getFileName() {
        if (this.title == null) {
            return null;
        }
        return this.title + ".MP4";
    }

    public String getBanner() {
        return this.banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public String getId() {
        return "";
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public int getSeason() {
        return this.season;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public int getEpisode() {
        return this.episode;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public int getBoxType() {
        return this.box_type;
    }

    public void setBox_type(int box_type) {
        this.box_type = box_type;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public void addDonwload(BaseMediaModel donwload) {
        int i = donwload.seconds;
        if (i != 0) {
            this.seconds = i;
        }
        this.quality = donwload.quality;
        this.list = donwload.list;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public int ChoosePlayer() {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).path != null && !TextUtils.isEmpty(this.list.get(i).path)) {
                if (i > 4) {
                    return 4;
                }
                return i;
            }
        }
        return 0;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public boolean saveInDao(int i, Activity activity) {
        List<DownloadFile> list = this.list;
        return list != null && list.size() <= i;
    }
}
