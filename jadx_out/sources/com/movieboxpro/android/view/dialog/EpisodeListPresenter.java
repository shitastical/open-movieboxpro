package com.movieboxpro.android.view.dialog;

import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.RxUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EpisodeListPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J,\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/view/dialog/EpisodeListPresenter;", "Lcom/movieboxpro/android/view/dialog/EpisodeListContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "store", "Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListStore;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListStore;)V", "viewRef", "Ljava/lang/ref/WeakReference;", "Lcom/movieboxpro/android/view/dialog/EpisodeListContract$View;", "episodesDisposable", "Lio/reactivex/disposables/Disposable;", "episodeLoadToken", "", "view", "getView", "()Lcom/movieboxpro/android/view/dialog/EpisodeListContract$View;", "attachView", "", "detachView", "loadSeasonMeta", "tvId", "", "loadEpisodes", DealWatchNextService.SEASON, "year", "forceNetwork", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EpisodeListPresenter implements EpisodeListContract.Presenter {
    private int episodeLoadToken;
    private Disposable episodesDisposable;
    private final LifecycleOwner lifecycleOwner;
    private final PlayerEpisodeListStore store;
    private WeakReference<EpisodeListContract.View> viewRef;

    public EpisodeListPresenter(LifecycleOwner lifecycleOwner, PlayerEpisodeListStore playerEpisodeListStore) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.lifecycleOwner = lifecycleOwner;
        this.store = playerEpisodeListStore;
    }

    public /* synthetic */ EpisodeListPresenter(LifecycleOwner lifecycleOwner, PlayerEpisodeListStore playerEpisodeListStore, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleOwner, (i & 2) != 0 ? null : playerEpisodeListStore);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EpisodeListContract.View getView() {
        WeakReference<EpisodeListContract.View> weakReference = this.viewRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // com.movieboxpro.android.view.dialog.EpisodeListContract.Presenter
    public void attachView(EpisodeListContract.View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.viewRef = new WeakReference<>(view);
    }

    @Override // com.movieboxpro.android.view.dialog.EpisodeListContract.Presenter
    public void detachView() {
        this.episodeLoadToken++;
        Disposable disposable = this.episodesDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.episodesDisposable = null;
        WeakReference<EpisodeListContract.View> weakReference = this.viewRef;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.viewRef = null;
    }

    @Override // com.movieboxpro.android.view.dialog.EpisodeListContract.Presenter
    public void loadSeasonMeta(String tvId) {
        Intrinsics.checkNotNullParameter(tvId, "tvId");
        PlayerEpisodeListStore playerEpisodeListStore = this.store;
        if (playerEpisodeListStore != null && playerEpisodeListStore.hasSeasonMeta()) {
            EpisodeListContract.View view = getView();
            if (view != null) {
                String[] seasons = playerEpisodeListStore.getSeasons();
                if (seasons == null) {
                    seasons = new String[0];
                }
                String[] years = playerEpisodeListStore.getYears();
                if (years == null) {
                    years = new String[0];
                }
                view.showSeasonMeta(seasons, years);
            }
            if (!playerEpisodeListStore.shouldFetchSeasonMeta()) {
                return;
            }
        }
        ((ObservableSubscribeProxy) Http.getService().Tv_detail(API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", tvId, App.deviceLang, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner))).subscribe(new HttpResponseObserver<TvDetail>() { // from class: com.movieboxpro.android.view.dialog.EpisodeListPresenter.loadSeasonMeta.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(TvDetail model) {
                String[] years2;
                String[] seasons2;
                Intrinsics.checkNotNullParameter(model, "model");
                String[] strArr = model.seasonList;
                if (strArr == null) {
                    strArr = new String[0];
                }
                String[] strArr2 = model.years;
                if (strArr2 == null) {
                    strArr2 = new String[0];
                }
                PlayerEpisodeListStore playerEpisodeListStore2 = EpisodeListPresenter.this.store;
                if (playerEpisodeListStore2 != null) {
                    playerEpisodeListStore2.setSeasonMeta(strArr, strArr2, true);
                }
                EpisodeListContract.View view2 = EpisodeListPresenter.this.getView();
                if (view2 != null) {
                    PlayerEpisodeListStore playerEpisodeListStore3 = EpisodeListPresenter.this.store;
                    if (playerEpisodeListStore3 != null && (seasons2 = playerEpisodeListStore3.getSeasons()) != null) {
                        strArr = seasons2;
                    }
                    PlayerEpisodeListStore playerEpisodeListStore4 = EpisodeListPresenter.this.store;
                    if (playerEpisodeListStore4 != null && (years2 = playerEpisodeListStore4.getYears()) != null) {
                        strArr2 = years2;
                    }
                    view2.showSeasonMeta(strArr, strArr2);
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                String[] seasons2;
                String[] years2;
                String[] seasons3;
                String[] years3;
                Intrinsics.checkNotNullParameter(e, "e");
                PlayerEpisodeListStore playerEpisodeListStore2 = EpisodeListPresenter.this.store;
                if (playerEpisodeListStore2 != null) {
                    PlayerEpisodeListStore playerEpisodeListStore3 = EpisodeListPresenter.this.store;
                    if (playerEpisodeListStore3 == null || (seasons3 = playerEpisodeListStore3.getSeasons()) == null) {
                        seasons3 = new String[0];
                    }
                    PlayerEpisodeListStore playerEpisodeListStore4 = EpisodeListPresenter.this.store;
                    if (playerEpisodeListStore4 == null || (years3 = playerEpisodeListStore4.getYears()) == null) {
                        years3 = new String[0];
                    }
                    playerEpisodeListStore2.setSeasonMeta(seasons3, years3, true);
                }
                PlayerEpisodeListStore playerEpisodeListStore5 = EpisodeListPresenter.this.store;
                if (playerEpisodeListStore5 == null || !playerEpisodeListStore5.hasSeasonMeta()) {
                    EpisodeListContract.View view2 = EpisodeListPresenter.this.getView();
                    if (view2 != null) {
                        view2.showSeasonMetaFailed();
                        return;
                    }
                    return;
                }
                EpisodeListContract.View view3 = EpisodeListPresenter.this.getView();
                if (view3 != null) {
                    PlayerEpisodeListStore playerEpisodeListStore6 = EpisodeListPresenter.this.store;
                    if (playerEpisodeListStore6 == null || (seasons2 = playerEpisodeListStore6.getSeasons()) == null) {
                        seasons2 = new String[0];
                    }
                    PlayerEpisodeListStore playerEpisodeListStore7 = EpisodeListPresenter.this.store;
                    if (playerEpisodeListStore7 == null || (years2 = playerEpisodeListStore7.getYears()) == null) {
                        years2 = new String[0];
                    }
                    view3.showSeasonMeta(seasons2, years2);
                }
            }
        });
    }

    @Override // com.movieboxpro.android.view.dialog.EpisodeListContract.Presenter
    public void loadEpisodes(String tvId, final String season, final String year, boolean forceNetwork) {
        Intrinsics.checkNotNullParameter(tvId, "tvId");
        if (!forceNetwork) {
            PlayerEpisodeListStore playerEpisodeListStore = this.store;
            List<TvDetail.SeasonDetail> episodes = playerEpisodeListStore != null ? playerEpisodeListStore.getEpisodes(season, year) : null;
            if (episodes != null && !episodes.isEmpty()) {
                Disposable disposable = this.episodesDisposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                this.episodeLoadToken++;
                EpisodeListContract.View view = getView();
                if (view != null) {
                    view.showEpisodes(CollectionsKt.toMutableList((Collection) episodes));
                    return;
                }
                return;
            }
        }
        Disposable disposable2 = this.episodesDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        final int i = this.episodeLoadToken + 1;
        this.episodeLoadToken = i;
        ((ObservableSubscribeProxy) Http.getService().TV_episode(API.Tv.TV_EPISODE, tvId, season == null ? "" : season, year == null ? "" : year, App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.view.dialog.EpisodeListPresenter.loadEpisodes.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                EpisodeListContract.View view2;
                Intrinsics.checkNotNullParameter(d, "d");
                EpisodeListPresenter.this.episodesDisposable = d;
                if (i == EpisodeListPresenter.this.episodeLoadToken && (view2 = EpisodeListPresenter.this.getView()) != null) {
                    view2.showLoadingView();
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends TvDetail.SeasonDetail> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (i != EpisodeListPresenter.this.episodeLoadToken) {
                    return;
                }
                PlayerEpisodeListStore playerEpisodeListStore2 = EpisodeListPresenter.this.store;
                if (playerEpisodeListStore2 != null) {
                    playerEpisodeListStore2.putEpisodes(season, year, model);
                }
                EpisodeListContract.View view2 = EpisodeListPresenter.this.getView();
                if (view2 != null) {
                    view2.hideLoadingView();
                }
                EpisodeListContract.View view3 = EpisodeListPresenter.this.getView();
                if (view3 != null) {
                    view3.showEpisodes(CollectionsKt.toMutableList((Collection) model));
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                if (i != EpisodeListPresenter.this.episodeLoadToken) {
                    return;
                }
                EpisodeListContract.View view2 = EpisodeListPresenter.this.getView();
                if (view2 != null) {
                    view2.hideLoadingView();
                }
                EpisodeListContract.View view3 = EpisodeListPresenter.this.getView();
                if (view3 != null) {
                    String message = e.getMessage();
                    if (message == null) {
                        message = "Load failed";
                    }
                    view3.showEpisodeLoadFailed(message);
                }
            }
        });
    }
}
