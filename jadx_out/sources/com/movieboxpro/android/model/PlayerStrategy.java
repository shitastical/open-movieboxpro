package com.movieboxpro.android.model;

import android.app.Activity;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.model.detail.AbstractVideoBean;
import com.movieboxpro.android.model.detail.MovieBean;
import com.movieboxpro.android.model.detail.TvBean;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.MLog;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class PlayerStrategy implements Serializable {
    private MediaFunction play;

    public void setInstace(MediaFunction model) {
        this.play = model;
    }

    public MediaFunction getInstace() {
        return this.play;
    }

    public int choose() {
        return this.play.ChoosePlayer();
    }

    public BaseMediaModel getDownload(Download downloads) {
        if (downloads != null) {
            downloads.getBox_type();
        }
        this.play = null;
        return null;
    }

    public Download getDownload() {
        Download downloadFindByType;
        int boxType = this.play.getBoxType();
        Download download = null;
        try {
            if (boxType != 1) {
                if (boxType == 2) {
                    TvDetail tvDetail = (TvDetail) TvDetail.class.cast(this.play);
                    if (tvDetail != null) {
                        MLog.d("player", "电视tid" + tvDetail.box_type + "电视剧集" + tvDetail.id + "_" + tvDetail.seasonDetail.id);
                        downloadFindByType = App.getDB().downloadDao().findByType(tvDetail.box_type, tvDetail.id + "_" + tvDetail.seasonDetail.id);
                        download = downloadFindByType;
                    }
                }
            } else if (((MovieDetail) MovieDetail.class.cast(this.play)) != null) {
                downloadFindByType = App.getDB().downloadDao().findByType(this.play.getBoxType(), this.play.getId());
                download = downloadFindByType;
            }
        } catch (Exception unused) {
        }
        return download;
    }

    public AbstractVideoBean switchBean() {
        int boxType = this.play.getBoxType();
        if (boxType == 1) {
            return new MovieBean();
        }
        if (boxType == 2) {
            return new TvBean();
        }
        return new MovieBean();
    }

    public String getId() {
        return this.play.getId();
    }

    public int getSeason() {
        return this.play.getSeason();
    }

    public int getEpisode() {
        return this.play.getEpisode();
    }

    public boolean SaveInDao(int i, Activity activity) {
        return this.play.saveInDao(i, activity);
    }

    public void addDonwload(BaseMediaModel donwload) {
        this.play.addDonwload(donwload);
    }
}
