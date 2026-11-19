package com.movieboxpro.android.tv.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.internal.ViewUtils;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.player.VideoPlayerActivity;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.ChooseListSortDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityPlayListDetailBinding;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: PlayListDetailActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 :2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0001:B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0014J \u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0002J\u0016\u0010\u001a\u001a\u00020\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0014J\b\u0010\u001f\u001a\u00020\u0016H\u0014J\b\u0010 \u001a\u00020\u0016H\u0014J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u000bH\u0016JB\u0010#\u001a\u00020\u00162\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\b\u0010'\u001a\u0004\u0018\u00010\b2\b\u0010(\u001a\u0004\u0018\u00010\b2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000eH\u0016J\u0010\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020.H\u0016J\u0012\u0010/\u001a\u00020\u00162\b\u00100\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u000203H\u0016J\u0012\u00104\u001a\u00020\u00162\b\u00105\u001a\u0004\u0018\u000106H\u0016J\b\u00107\u001a\u00020\u0016H\u0002J\b\u00108\u001a\u00020\u0002H\u0014J\b\u00109\u001a\u00020\u000eH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListDetailActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/tv/list/PlayListDetailPresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivityPlayListDetailBinding;", "Lcom/movieboxpro/android/tv/list/PlayListDetailContract$View;", "<init>", "()V", "lid", "", "userId", "isCollect", "", "name", "likeNum", "", "filterTitle", "fragment", "Lcom/movieboxpro/android/tv/list/PlayListVideoListFragment;", "allVideo", "", "Lcom/movieboxpro/android/model/movie/MovieListDetailModel$ListBean;", "initListener", "", "requestListToPlay", "sort_by", "sort", "playRandomItem", "data", "", "refreshMovieList", "initView", "initData", "requestData", "doCollectComplete", "isSuccess", "showScreenManage", "list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", NotificationCompat.CATEGORY_MESSAGE, "id", "boxType", "currSeason", "currEpisode", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "playWithOtherPlayer", "url", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "showInfo", "model", "Lcom/movieboxpro/android/model/movie/MovieListDetailModel;", "setNameText", "bindPresenter", "getLayoutResId", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListDetailActivity extends BaseMvpActivity<PlayListDetailPresenter, ActivityPlayListDetailBinding> implements PlayListDetailContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private List<MovieListDetailModel.ListBean> allVideo;
    private PlayListVideoListFragment fragment;
    private boolean isCollect;
    private int likeNum;
    private String lid = "";
    private String userId = "";
    private String name = "";
    private String filterTitle = "Default";

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ActivityPlayListDetailBinding) this.binding).clAvatar.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayListDetailActivity.initListener$lambda$0(this.f$0, view);
            }
        });
        ((ActivityPlayListDetailBinding) this.binding).ivRandomPlay.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayListDetailActivity.initListener$lambda$1(this.f$0, view);
            }
        });
        ((ActivityPlayListDetailBinding) this.binding).ivSort.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayListDetailActivity.initListener$lambda$3(this.f$0, view);
            }
        });
        ((ActivityPlayListDetailBinding) this.binding).ivCollect.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayListDetailActivity.initListener$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(PlayListDetailActivity playListDetailActivity, View view) {
        UserMovieListActivity.INSTANCE.start(playListDetailActivity, playListDetailActivity.userId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(PlayListDetailActivity playListDetailActivity, View view) {
        if (Intrinsics.areEqual("Default", playListDetailActivity.filterTitle)) {
            List<? extends MovieListDetailModel.ListBean> list = playListDetailActivity.allVideo;
            if (list == null) {
                playListDetailActivity.requestListToPlay(playListDetailActivity.lid, "name", "asc");
                return;
            } else {
                Intrinsics.checkNotNull(list);
                playListDetailActivity.playRandomItem(list);
                return;
            }
        }
        PlayListVideoListFragment playListVideoListFragment = playListDetailActivity.fragment;
        if (playListVideoListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            playListVideoListFragment = null;
        }
        List<MovieListDetailModel.ListBean> listData = playListVideoListFragment.getListData();
        playListDetailActivity.allVideo = listData;
        Intrinsics.checkNotNull(listData);
        playListDetailActivity.playRandomItem(listData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(final PlayListDetailActivity playListDetailActivity, View view) {
        ChooseListSortDialog chooseListSortDialogNewInstance = ChooseListSortDialog.INSTANCE.newInstance(playListDetailActivity.filterTitle);
        chooseListSortDialogNewInstance.setListener(new DialogAction.EditActionListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity$$ExternalSyntheticLambda4
            @Override // com.movieboxpro.android.view.dialog.DialogAction.EditActionListener
            public final void onClick(String str) {
                PlayListDetailActivity.initListener$lambda$3$lambda$2(this.f$0, str);
            }
        });
        chooseListSortDialogNewInstance.show(playListDetailActivity.getSupportFragmentManager(), "ChooseListSortDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3$lambda$2(PlayListDetailActivity playListDetailActivity, String str) {
        Intrinsics.checkNotNull(str);
        playListDetailActivity.filterTitle = str;
        playListDetailActivity.refreshMovieList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(PlayListDetailActivity playListDetailActivity, View view) {
        String str;
        if (playListDetailActivity.isLogin()) {
            if (((ActivityPlayListDetailBinding) playListDetailActivity.binding).ivCollect.isSelected()) {
                str = "del";
            } else {
                str = DealWatchNextService.ADD_ACTION;
            }
            PlayListDetailPresenter playListDetailPresenter = (PlayListDetailPresenter) playListDetailActivity.mPresenter;
            String str2 = playListDetailActivity.lid;
            String uid_v2 = App.getUserData().uid_v2;
            Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
            playListDetailPresenter.addCollect(str2, uid_v2, str);
        }
    }

    private final void requestListToPlay(String lid, String sort_by, String sort) {
        ((ObservableSubscribeProxy) HttpRequest.INSTANCE.post("Playlists_get_v2").param("sort_by", sort_by).param("sort", sort).param("lid", lid).asRequest().compose(RxUtils.rxTranslate2Bean(MovieListDetailModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<MovieListDetailModel>() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity.requestListToPlay.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                PlayListDetailActivity.this.showLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onNext(MovieListDetailModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                PlayListDetailActivity.this.hideLoadingView();
                PlayListDetailActivity.this.allVideo = model.getList();
                PlayListDetailActivity playListDetailActivity = PlayListDetailActivity.this;
                List<MovieListDetailModel.ListBean> list = model.getList();
                Intrinsics.checkNotNullExpressionValue(list, "getList(...)");
                playListDetailActivity.playRandomItem(list);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                PlayListDetailActivity.this.hideLoadingView();
                ToastUtils.showShort("Load failed:" + e.getMessage(), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playRandomItem(List<? extends MovieListDetailModel.ListBean> data) {
        MovieListDetailModel.ListBean listBean = data.get(new Random().nextInt(data.size()));
        if (listBean.getBox_type() == 1) {
            ((PlayListDetailPresenter) this.mPresenter).getPlayPath(String.valueOf(listBean.getId()), listBean.getBox_type(), 0, 0);
        } else {
            ((PlayListDetailPresenter) this.mPresenter).getPlayPath(String.valueOf(listBean.getId()), listBean.getBox_type(), 1, 1);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void refreshMovieList() {
        String str = this.filterTitle;
        PlayListVideoListFragment playListVideoListFragment = null;
        switch (str.hashCode()) {
            case -1696925139:
                if (str.equals("Name from A-Z")) {
                    PlayListVideoListFragment playListVideoListFragment2 = this.fragment;
                    if (playListVideoListFragment2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    } else {
                        playListVideoListFragment = playListVideoListFragment2;
                    }
                    playListVideoListFragment.refreshList("name", "asc");
                    ((ActivityPlayListDetailBinding) this.binding).ivSort.setImageResource(R.mipmap.ic_list_sort_select);
                    break;
                }
                break;
            case -1696901139:
                if (str.equals("Name from Z-A")) {
                    PlayListVideoListFragment playListVideoListFragment3 = this.fragment;
                    if (playListVideoListFragment3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    } else {
                        playListVideoListFragment = playListVideoListFragment3;
                    }
                    playListVideoListFragment.refreshList("name", "desc");
                    ((ActivityPlayListDetailBinding) this.binding).ivSort.setImageResource(R.mipmap.ic_list_sort_select);
                    break;
                }
                break;
            case -1085510111:
                if (str.equals("Default")) {
                    PlayListVideoListFragment playListVideoListFragment4 = this.fragment;
                    if (playListVideoListFragment4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    } else {
                        playListVideoListFragment = playListVideoListFragment4;
                    }
                    playListVideoListFragment.refreshList("", "");
                    ((ActivityPlayListDetailBinding) this.binding).ivSort.setImageResource(R.mipmap.ic_movielist_sort);
                    break;
                }
                break;
            case -1065097433:
                if (str.equals("Latest Added")) {
                    PlayListVideoListFragment playListVideoListFragment5 = this.fragment;
                    if (playListVideoListFragment5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    } else {
                        playListVideoListFragment = playListVideoListFragment5;
                    }
                    playListVideoListFragment.refreshList("add_time", "desc");
                    ((ActivityPlayListDetailBinding) this.binding).ivSort.setImageResource(R.mipmap.ic_list_sort_select);
                    break;
                }
                break;
            case 201004019:
                if (str.equals("Earliest Added")) {
                    PlayListVideoListFragment playListVideoListFragment6 = this.fragment;
                    if (playListVideoListFragment6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    } else {
                        playListVideoListFragment = playListVideoListFragment6;
                    }
                    playListVideoListFragment.refreshList("add_time", "asc");
                    ((ActivityPlayListDetailBinding) this.binding).ivSort.setImageResource(R.mipmap.ic_list_sort_select);
                    break;
                }
                break;
            case 1580322230:
                if (str.equals("Latest Released")) {
                    PlayListVideoListFragment playListVideoListFragment7 = this.fragment;
                    if (playListVideoListFragment7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    } else {
                        playListVideoListFragment = playListVideoListFragment7;
                    }
                    playListVideoListFragment.refreshList("released", "desc");
                    ((ActivityPlayListDetailBinding) this.binding).ivSort.setImageResource(R.mipmap.ic_list_sort_select);
                    break;
                }
                break;
            case 1605885290:
                if (str.equals("Earliest Released")) {
                    PlayListVideoListFragment playListVideoListFragment8 = this.fragment;
                    if (playListVideoListFragment8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    } else {
                        playListVideoListFragment = playListVideoListFragment8;
                    }
                    playListVideoListFragment.refreshList("released", "asc");
                    ((ActivityPlayListDetailBinding) this.binding).ivSort.setImageResource(R.mipmap.ic_list_sort_select);
                    break;
                }
                break;
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        String stringExtra = getIntent().getStringExtra("lid");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.lid = stringExtra;
        PlayListDetailPresenter playListDetailPresenter = (PlayListDetailPresenter) this.mPresenter;
        String str = this.lid;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        playListDetailPresenter.requestInfo(str, uid_v2);
        this.fragment = PlayListVideoListFragment.INSTANCE.newInstance(this.lid);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        PlayListVideoListFragment playListVideoListFragment = this.fragment;
        if (playListVideoListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            playListVideoListFragment = null;
        }
        FragmentUtils.add(supportFragmentManager, playListVideoListFragment, R.id.frameLayout);
    }

    @Override // com.movieboxpro.android.tv.list.PlayListDetailContract.View
    public void doCollectComplete(boolean isSuccess) {
        if (isSuccess) {
            this.isCollect = !this.isCollect;
            ((ActivityPlayListDetailBinding) this.binding).ivCollect.setSelected(this.isCollect);
            if (this.isCollect) {
                this.likeNum++;
            } else {
                this.likeNum--;
            }
            setNameText();
        } else {
            ToastUtils.showShort("collect error", new Object[0]);
        }
        EventBus.getDefault().post(new RefreshMovieListEvent());
    }

    @Override // com.movieboxpro.android.tv.list.PlayListDetailContract.View
    public void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id, int boxType, int currSeason, int currEpisode) {
        Intrinsics.checkNotNullParameter(list, "list");
        ScreenManageActivity.Companion.start$default(ScreenManageActivity.INSTANCE, this, list, 100, msg, boxType, id, currSeason, currEpisode, false, false, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    @Override // com.movieboxpro.android.tv.list.PlayListDetailContract.View
    public void goTvPlayer(final TvDetail tvDetail) {
        Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        if (tvDetail.episodeList.size() < 100) {
            List<TvDetail.SeasonDetail> episodeList = tvDetail.episodeList;
            Intrinsics.checkNotNullExpressionValue(episodeList, "episodeList");
            for (TvDetail.SeasonDetail seasonDetail : episodeList) {
                TvSeasonList tvSeasonList = new TvSeasonList();
                tvSeasonList.setEpisode(seasonDetail.episode);
                tvSeasonList.setSeason(seasonDetail.season);
                tvSeasonList.setTid(seasonDetail.tid);
                tvSeasonList.setImdbId(seasonDetail.imdb_id);
                tvSeasonList.setId(seasonDetail.id);
                Integer num = seasonDetail.play_progress.get("over");
                tvSeasonList.setOver(num != null ? num.intValue() : 0);
                Integer num2 = seasonDetail.play_progress.get("seconds");
                tvSeasonList.setSeconds(num2 != null ? num2.intValue() : 0);
                ArrayList arrayList = (ArrayList) objectRef.element;
                if (arrayList != null) {
                    arrayList.add(tvSeasonList);
                }
            }
        } else {
            objectRef.element = null;
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
            ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
            choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity.goTvPlayer.2
                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void playWithOther() {
                    ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = ChoosePlayerQualityFragment.INSTANCE.newInstance(new ArrayList<>(tvDetail.list));
                    final PlayListDetailActivity playListDetailActivity = this;
                    choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity$goTvPlayer$2$playWithOther$1
                        @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                        public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position) {
                            Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                            playListDetailActivity.playWithOtherPlayer(downloadUrl.path);
                        }
                    });
                    FragmentManager supportFragmentManager = this.getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
                    choosePlayerQualityFragmentNewInstance.showAllowingStateLoss(supportFragmentManager, "ChoosePlayerQualityFragment");
                }

                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void play() {
                    PlayListDetailActivity playListDetailActivity = this;
                    TvDetail tvDetail2 = tvDetail;
                    VideoPlayer.start(playListDetailActivity, tvDetail2, tvDetail2.seasonDetail.season, tvDetail.seasonDetail.episode, false, objectRef.element, false, true);
                }
            });
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
            choosePlayerDialog.showAllowingStateLoss(supportFragmentManager, "ChoosePlayerDialog");
            return;
        }
        VideoPlayer.start(this, tvDetail, tvDetail.seasonDetail.season, tvDetail.seasonDetail.episode, false, (ArrayList) objectRef.element, false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playWithOtherPlayer(String url) {
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(url), "video/mp4");
            startActivity(intent);
            Intent.createChooser(intent, "Choose a player to play!");
        } catch (Exception unused) {
            ToastUtils.showShort("No player available", new Object[0]);
        }
    }

    @Override // com.movieboxpro.android.tv.list.PlayListDetailContract.View
    public void goMoviePlayer(final MovieDetail movieDetail) {
        Intrinsics.checkNotNullParameter(movieDetail, "movieDetail");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
            ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
            choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity.goMoviePlayer.1
                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void playWithOther() {
                    ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = ChoosePlayerQualityFragment.INSTANCE.newInstance(new ArrayList<>(movieDetail.list));
                    final PlayListDetailActivity playListDetailActivity = this;
                    choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.tv.list.PlayListDetailActivity$goMoviePlayer$1$playWithOther$1
                        @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                        public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position) {
                            Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                            playListDetailActivity.playWithOtherPlayer(downloadUrl.path);
                        }
                    });
                    FragmentManager supportFragmentManager = this.getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
                    choosePlayerQualityFragmentNewInstance.showAllowingStateLoss(supportFragmentManager, "ChoosePlayerQualityFragment");
                }

                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void play() {
                    PlayListDetailActivity playListDetailActivity = this;
                    MovieDetail movieDetail2 = movieDetail;
                    VideoPlayerActivity.start(playListDetailActivity, movieDetail2, movieDetail2.id);
                }
            });
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
            choosePlayerDialog.showAllowingStateLoss(supportFragmentManager, "ChoosePlayerDialog");
            return;
        }
        VideoPlayerActivity.start(this, movieDetail, movieDetail.id);
    }

    @Override // com.movieboxpro.android.tv.list.PlayListDetailContract.View
    public void showInfo(MovieListDetailModel model) {
        String uid;
        String name;
        ((ActivityPlayListDetailBinding) this.binding).frameLayout.requestFocus();
        if (model != null && model.getIsmine() == 1) {
            ImageView ivCollect = ((ActivityPlayListDetailBinding) this.binding).ivCollect;
            Intrinsics.checkNotNullExpressionValue(ivCollect, "ivCollect");
            CommonExtKt.gone(ivCollect);
        }
        String str = "";
        if (model == null || (uid = model.getUid()) == null) {
            uid = "";
        }
        this.userId = uid;
        if (model != null && (name = model.getName()) != null) {
            str = name;
        }
        this.name = str;
        boolean z = false;
        this.likeNum = model != null ? model.getCollect_num() : 0;
        setNameText();
        ((ActivityPlayListDetailBinding) this.binding).tvDesc.setText(model != null ? model.getDesc() : null);
        GlideUtils.load((Activity) this, model != null ? model.getAvatar() : null, (ImageView) ((ActivityPlayListDetailBinding) this.binding).ivAvatar, R.drawable.ic_default_avatar);
        ((ActivityPlayListDetailBinding) this.binding).tvUsername.setText(model != null ? model.getUsername() : null);
        ((ActivityPlayListDetailBinding) this.binding).tvTime.setText(TimeUtils.formatTime4((model != null ? model.getDateline() : 0L) * ((long) 1000)));
        if (model != null && model.getIs_collect() == 0) {
            z = true;
        }
        this.isCollect = !z;
        ((ActivityPlayListDetailBinding) this.binding).ivCollect.setSelected(this.isCollect);
    }

    private final void setNameText() {
        SpanUtils spanUtilsWith = SpanUtils.with(((ActivityPlayListDetailBinding) this.binding).tvTitle);
        Intrinsics.checkNotNullExpressionValue(spanUtilsWith, "with(...)");
        CommonExtKt.addText(spanUtilsWith, this.name, 17, R.color.white).create();
        ((ActivityPlayListDetailBinding) this.binding).tvLike.setText(this.likeNum + " like");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public PlayListDetailPresenter bindPresenter() {
        return new PlayListDetailPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_play_list_detail;
    }

    /* JADX INFO: compiled from: PlayListDetailActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListDetailActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "lid", "", "username", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String lid, String username) {
            Intent intent = new Intent(context, (Class<?>) PlayListDetailActivity.class);
            intent.putExtra("lid", lid);
            intent.putExtra("username", username);
            if (context != null) {
                context.startActivity(intent);
            }
        }
    }
}
