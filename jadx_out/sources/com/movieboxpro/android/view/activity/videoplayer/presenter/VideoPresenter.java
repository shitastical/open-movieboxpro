package com.movieboxpro.android.view.activity.videoplayer.presenter;

import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.model.SRTModel;
import com.movieboxpro.android.db.entity.PlayRecode;

/* JADX INFO: loaded from: classes5.dex */
public interface VideoPresenter {
    void _geiPrama();

    void _initAds();

    void _initChromCast();

    void _initController();

    void _initQuality();

    void _initfloat();

    PlayRecode getInDao();

    void getSrt(MediaQualityInfo mediaQualityInfo);

    void get_Movie_select(int duration, int position, String mmfid);

    void get_srt_select(String url, String sid);

    void saveInDao(int position, int quality);

    void setFloatDestroy();

    void setSrts(SRTModel.SubTitles subTitles);
}
