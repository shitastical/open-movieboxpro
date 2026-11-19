package com.movieboxpro.android.view.activity.videoplayer;

import com.dl7.player.utils.SRT;
import com.dueeeke.model.EncodeModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BaseContract;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: compiled from: TransCodeSubtitleContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface TransCodeSubtitleContract {

    /* JADX INFO: compiled from: TransCodeSubtitleContract.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleContract$View;", "firstLoadData", "", "url", "", "fileName", "transCode", "code", IDataSource.SCHEME_FILE_TAG, "Ljava/io/File;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void firstLoadData(String url, String fileName);

        void transCode(String code, File file);
    }

    /* JADX INFO: compiled from: TransCodeSubtitleContract.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J.\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showLanguages", "", "languageModel", "", "Lcom/dueeeke/model/EncodeModel;", "subtitles", "", "Lcom/dl7/player/utils/SRT;", IDataSource.SCHEME_FILE_TAG, "Ljava/io/File;", "showSubtitle", "setSubtitleContent", FirebaseAnalytics.Param.CONTENT, "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void setSubtitleContent(String content);

        void showLanguages(List<EncodeModel> languageModel, List<? extends SRT> subtitles, File file);

        void showSubtitle(List<? extends SRT> subtitles);
    }
}
