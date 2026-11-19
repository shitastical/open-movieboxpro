package com.movieboxpro.android.model.detail;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observable;

/* JADX INFO: loaded from: classes5.dex */
public interface AbstractVideoBean {
    Observable<String> Playlists_list(String uid);

    Observable<String> TV_episode(String id, int season, String year);

    Observable<String> addFavorite(String id, JSONObject jsonObject, boolean isFavorite);

    Observable<String> getDetailInfo(String id, String lang);

    Observable<String> getFeedBackType(int state);

    Observable<String> getPath(String id, int season, int episode);

    Observable<String> setFeedBack(int state, int box_type, String mid, String sid, int ftid, String text, int season, int episode);

    Observable<String> set_srt_auto(String id, int season, int episode);
}
