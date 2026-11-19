package com.movieboxpro.android.tv.list;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseRowsMvpFragment;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.event.OnBackPressedEvent;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.listener.RefreshListener;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.tv.CompilationsDetailListActivity;
import com.movieboxpro.android.tv.MoreCompilationsActivity;
import com.movieboxpro.android.tv.MorePlayListActivity;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.view.tvpresenter.PlayListPresenterSelector;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: PlayListFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\u000bJ\b\u0010\r\u001a\u00020\u000bH\u0014J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0016H\u0007J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0018H\u0007J\b\u0010\u0019\u001a\u00020\u000bH\u0016J\b\u0010\u001a\u001a\u00020\u000bH\u0014J\b\u0010\u001b\u001a\u00020\u000bH\u0014J\b\u0010\u001c\u001a\u00020\u000bH\u0014J\b\u0010\u001d\u001a\u00020\u000bH\u0016J\b\u0010\u001e\u001a\u00020\u000bH\u0016J\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!H\u0016J\u0018\u0010\"\u001a\u00020\u000b2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$H\u0016J\b\u0010&\u001a\u00020\u0002H\u0014J\b\u0010'\u001a\u00020\u000bH\u0016J\b\u0010(\u001a\u00020\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListFragment;", "Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;", "Lcom/movieboxpro/android/tv/list/PlayListPresenter;", "Lcom/movieboxpro/android/tv/list/PlayListContract$View;", "<init>", "()V", "rowAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "refreshListener", "Lcom/movieboxpro/android/listener/RefreshListener;", "setRefreshListener", "", "refreshData", "initListener", "onRefresh", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/RefreshMovieListEvent;", "onLoginIn", "Lcom/movieboxpro/android/event/LoginEvent;", "onSignOut", "Lcom/movieboxpro/android/event/LogoutEvent;", "onBackPressed", "Lcom/movieboxpro/android/event/OnBackPressedEvent;", "onLowMemory", "Lcom/movieboxpro/android/event/LowMemoryEvent;", "onFragmentResumeNoFirst", "initData", "initView", "loadData", "showLoading", "hideLoading", "loadFailed", NotificationCompat.CATEGORY_MESSAGE, "", "showPlayList", "list", "", "Lcom/movieboxpro/android/model/movie/MovieListModel;", "bindPresenter", "onStart", "onDestroy", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListFragment extends BaseRowsMvpFragment<PlayListPresenter> implements PlayListContract.View {
    private RefreshListener refreshListener;
    private ArrayObjectAdapter rowAdapter;

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initData() {
    }

    public PlayListFragment() {
        ListRowPresenter listRowPresenter = new ListRowPresenter();
        listRowPresenter.setNumRows(1);
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(listRowPresenter);
        this.rowAdapter = arrayObjectAdapter;
        setAdapter(arrayObjectAdapter);
    }

    public final void setRefreshListener(RefreshListener refreshListener) {
        Intrinsics.checkNotNullParameter(refreshListener, "refreshListener");
        this.refreshListener = refreshListener;
    }

    public final void refreshData() {
        PlayListPresenter playListPresenter = (PlayListPresenter) this.mPresenter;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        playListPresenter.requestPlayList(uid_v2);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initListener() {
        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() { // from class: com.movieboxpro.android.tv.list.PlayListFragment$$ExternalSyntheticLambda0
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                PlayListFragment.initListener$lambda$2(this.f$0, viewHolder, obj, viewHolder2, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(PlayListFragment playListFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.movieboxpro.android.model.movie.MovieListModel.MovieListItem");
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) obj;
        int itemType = movieListItem.getItemType();
        if (itemType != 1) {
            if (itemType == 2 || itemType == 3) {
                if (movieListItem.getIsmore() == 1) {
                    MoreCompilationsActivity.INSTANCE.start(playListFragment.getContext(), movieListItem.getType());
                    return;
                } else {
                    CompilationsDetailListActivity.INSTANCE.start(playListFragment.getContext(), movieListItem.getId());
                    return;
                }
            }
            return;
        }
        if (movieListItem.getIsmore() == 1) {
            Context context = playListFragment.getContext();
            if (context != null) {
                MorePlayListActivity.Companion companion = MorePlayListActivity.INSTANCE;
                String type = movieListItem.getType();
                Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                companion.start(context, type);
                return;
            }
            return;
        }
        Context context2 = playListFragment.getContext();
        if (context2 != null) {
            PlayListDetailActivity.INSTANCE.start(context2, movieListItem.getLid(), movieListItem.getUsername());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onRefresh(RefreshMovieListEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        PlayListPresenter playListPresenter = (PlayListPresenter) this.mPresenter;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        playListPresenter.requestPlayList(uid_v2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLoginIn(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        PlayListPresenter playListPresenter = (PlayListPresenter) this.mPresenter;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        playListPresenter.requestPlayList(uid_v2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSignOut(LogoutEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        PlayListPresenter playListPresenter = (PlayListPresenter) this.mPresenter;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        playListPresenter.requestPlayList(uid_v2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onBackPressed(OnBackPressedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getType() == 4) {
            getVerticalGridView().smoothScrollToPosition(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLowMemory(LowMemoryEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.isVisibleToUser) {
            return;
        }
        this.rowAdapter.clear();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    public void onFragmentResumeNoFirst() {
        super.onFragmentResumeNoFirst();
        if (this.rowAdapter.size() != 0 || this.hub.isShowing()) {
            return;
        }
        loadData();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initView() {
        Context context = getContext();
        int iDp2px = context != null ? DensityUtils.dp2px(context, 20.0f) : 0;
        getVerticalGridView().setPadding(iDp2px, iDp2px, iDp2px, iDp2px);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void loadData() {
        PlayListPresenter playListPresenter = (PlayListPresenter) this.mPresenter;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        playListPresenter.requestPlayList(uid_v2);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoading() {
        RefreshListener refreshListener = this.refreshListener;
        if (refreshListener != null) {
            refreshListener.startRefresh();
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
        RefreshListener refreshListener = this.refreshListener;
        if (refreshListener != null) {
            refreshListener.endRefresh();
        }
    }

    @Override // com.movieboxpro.android.tv.list.PlayListContract.View
    public void loadFailed(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        RefreshListener refreshListener = this.refreshListener;
        if (refreshListener != null) {
            refreshListener.showError(msg);
        }
    }

    @Override // com.movieboxpro.android.tv.list.PlayListContract.View
    public void showPlayList(List<? extends MovieListModel> list) {
        this.rowAdapter.clear();
        if (list != null) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                MovieListModel movieListModel = (MovieListModel) obj;
                List<MovieListModel.MovieListItem> list2 = movieListModel.getList();
                if (list2 != null && !list2.isEmpty()) {
                    Context context = getContext();
                    HeaderItem headerItem = null;
                    PlayListPresenterSelector playListPresenterSelector = context != null ? new PlayListPresenterSelector(context) : null;
                    if (movieListModel.getIsmore() == 1) {
                        MovieListModel.MovieListItem movieListItem = new MovieListModel.MovieListItem();
                        movieListItem.setIsmore(1);
                        movieListItem.setType(movieListModel.getType());
                        movieListModel.getList().add(movieListItem);
                    }
                    if (Intrinsics.areEqual(movieListModel.getType(), "mine")) {
                        List<MovieListModel.MovieListItem> list3 = movieListModel.getList();
                        if ((list3 != null ? list3.size() : 0) >= 10) {
                            MovieListModel.MovieListItem movieListItem2 = new MovieListModel.MovieListItem();
                            movieListItem2.setIsmore(1);
                            movieListItem2.setType(movieListModel.getType());
                            movieListModel.getList().add(movieListItem2);
                        }
                    }
                    if (Intrinsics.areEqual(movieListModel.getType(), "compilations")) {
                        List<MovieListModel.MovieListItem> list4 = movieListModel.getList();
                        if (list4 != null) {
                            Iterator<T> it = list4.iterator();
                            while (it.hasNext()) {
                                ((MovieListModel.MovieListItem) it.next()).setItemType(2);
                            }
                        }
                    } else if (Intrinsics.areEqual(movieListModel.getType(), "collection") || Intrinsics.areEqual(movieListModel.getType(), "collection2")) {
                        List<MovieListModel.MovieListItem> list5 = movieListModel.getList();
                        if (list5 != null) {
                            Iterator<T> it2 = list5.iterator();
                            while (it2.hasNext()) {
                                ((MovieListModel.MovieListItem) it2.next()).setItemType(3);
                            }
                        }
                    } else {
                        List<MovieListModel.MovieListItem> list6 = movieListModel.getList();
                        if (list6 != null) {
                            Iterator<T> it3 = list6.iterator();
                            while (it3.hasNext()) {
                                ((MovieListModel.MovieListItem) it3.next()).setItemType(1);
                            }
                        }
                        headerItem = new HeaderItem(movieListModel.getName());
                    }
                    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(playListPresenterSelector);
                    arrayObjectAdapter.addAll(0, movieListModel.getList());
                    if (headerItem != null) {
                        this.rowAdapter.add(new ListRow(headerItem, arrayObjectAdapter));
                    } else {
                        this.rowAdapter.add(new ListRow(arrayObjectAdapter));
                    }
                }
                i = i2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    public PlayListPresenter bindPresenter() {
        return new PlayListPresenter(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
