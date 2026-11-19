package com.movieboxpro.android.model.detail;

import android.text.TextUtils;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.androidtv.BuildConfig;
import io.reactivex.Observable;

/* JADX INFO: loaded from: classes5.dex */
public class MovieBean extends BaseVideoBean {
    public String poster;

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> TV_episode(String id, int season, String year) {
        return null;
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> getDetailInfo(String id, String lang) {
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
        return Http.getService().Movie_detail(API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, lang, str2, str);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> addFavorite(String id, JSONObject jsonObject, boolean isFavorite) {
        return Http.getService().Moviecollect(API.Movie.MOVE_COLLECT, App.isLogin() ? App.getUserData().uid_v2 : "", jsonObject, null, isFavorite ? "del" : DealWatchNextService.ADD_ACTION, "", "");
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> getPath(String id, int season, int episode) {
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
        return Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", id, "", str2, str);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> getFeedBackType(int state) {
        return Http.getService().Movie_feedback_type(API.Common.MOVIE_FEEDBACL_TYPE, state);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> setFeedBack(int state, int box_type, String mid, String sid, int ftid, String text, int season, int episode) {
        return Http.getService().Movie_feedback(API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().uid_v2 : "", box_type, mid + "", state, sid, ftid, text, 0, 0);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> set_srt_auto(String id, int season, int episode) {
        return Http.getService().Movie_srt_auto(API.Movie.MOVIE_SRT_AUTO, id, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "");
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> Playlists_list(String uid) {
        return Http.getService().Playlists_list(API.Common.PLAY_LIST, uid, "mine", 0, 0, BuildConfig.VERSION_NAME);
    }
}
