package com.movieboxpro.android.view.activity.videoplayer;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.dl7.player.utils.SRT;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BaseContract;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: TranslateSubtitleContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface TranslateSubtitleContract {

    /* JADX INFO: compiled from: TranslateSubtitleContract.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0006H&¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleContract$View;", "translate", "", "fromLanguage", "", "toLanguage", "toLanguageName", CmcdConfiguration.KEY_SESSION_ID, "boxType", "", "originalCount", "parseContent", FirebaseAnalytics.Param.CONTENT, "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void parseContent(String content);

        void translate(String fromLanguage, String toLanguage, String toLanguageName, String sid, int boxType, int originalCount);
    }

    /* JADX INFO: compiled from: TranslateSubtitleContract.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nH&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\rH&J&\u0010\u0012\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH&J\b\u0010\u0015\u001a\u00020\u0003H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H&¨\u0006\u0019À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showSubtitles", "", "list", "Ljava/util/ArrayList;", "Lcom/dl7/player/utils/SRT;", "Lkotlin/collections/ArrayList;", "showContent", "model", "", "showProgress", "progress", "", "max", "dismissProgress", "onTranslateStarted", "originalCount", "appendTranslatedCues", "", "doneCount", "onTranslateFinished", "onTranslateFailed", "message", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void appendTranslatedCues(List<SRT> list, int doneCount, int originalCount);

        void dismissProgress();

        void onTranslateFailed(String message);

        void onTranslateFinished();

        void onTranslateStarted(int originalCount);

        void showContent(List<? extends SRT> model);

        void showProgress(int progress, int max);

        void showSubtitles(ArrayList<SRT> list);
    }
}
