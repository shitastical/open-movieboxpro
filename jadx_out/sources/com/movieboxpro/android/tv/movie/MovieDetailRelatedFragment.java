package com.movieboxpro.android.tv.movie;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import com.google.common.net.HttpHeaders;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseRowsMvpFragment;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.Actor;
import com.movieboxpro.android.model.TrailerItem;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.tv.list.PlayListDetailActivity;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.YoutubeTrailerLauncher;
import com.movieboxpro.android.view.activity.ActorDetailActivity;
import com.movieboxpro.android.view.activity.ActorListActivity;
import com.movieboxpro.android.view.dialog.YoutubePlayDialog;
import com.movieboxpro.android.view.tvpresenter.MovieDetailPresenterSelector;
import com.movieboxpro.android.view.tvview.BaseVideoCardView;
import com.movieboxpro.android.view.widget.NoHighlightRowPresenter;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: MovieDetailRelatedFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002,-B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u001a\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\u001c\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0014J\u0010\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u000fH\u0014J\b\u0010\"\u001a\u00020\u000fH\u0014J\b\u0010#\u001a\u00020\u000fH\u0014J\b\u0010$\u001a\u00020\u0002H\u0014J\u0010\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020 H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;", "Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;", "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedPresenter;", "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedContract$View;", "<init>", "()V", "rowAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "enteredFromTop", "", "globalFocusChangeListener", "Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;", "focusListener", "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;", "setFocusListener", "", "initBackgroundColor", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "registerGlobalFocusTracker", "unRegisterGlobalFocusTracker", "isDescendantOf", "child", "parent", "initListener", "getActorList", "id", "", "initData", "initView", "loadData", "bindPresenter", "playTrailer", "item", "Lcom/movieboxpro/android/model/TrailerItem;", "launchYoutubeApp", "context", "Landroid/content/Context;", "videoId", "OnFocusListener", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MovieDetailRelatedFragment extends BaseRowsMvpFragment<MovieDetailRelatedPresenter> implements MovieDetailRelatedContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean enteredFromTop;
    private OnFocusListener focusListener;
    private ViewTreeObserver.OnGlobalFocusChangeListener globalFocusChangeListener;
    private ArrayObjectAdapter rowAdapter;

    /* JADX INFO: compiled from: MovieDetailRelatedFragment.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;", "", "onFocusChanged", "", "focus", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnFocusListener {
        void onFocusChanged(boolean focus);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected int initBackgroundColor() {
        return 0;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void loadData() {
    }

    public MovieDetailRelatedFragment() {
        NoHighlightRowPresenter noHighlightRowPresenter = new NoHighlightRowPresenter();
        noHighlightRowPresenter.setNumRows(1);
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(noHighlightRowPresenter);
        this.rowAdapter = arrayObjectAdapter;
        setAdapter(arrayObjectAdapter);
    }

    public final void setFocusListener(OnFocusListener focusListener) {
        Intrinsics.checkNotNullParameter(focusListener, "focusListener");
        this.focusListener = focusListener;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment, androidx.leanback.app.RowsSupportFragment, androidx.leanback.app.BaseRowSupportFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        registerGlobalFocusTracker();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment, androidx.leanback.app.RowsSupportFragment, androidx.leanback.app.BaseRowSupportFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        unRegisterGlobalFocusTracker();
        super.onDestroyView();
    }

    private final void registerGlobalFocusTracker() {
        Window window;
        View decorView;
        FragmentActivity activity = getActivity();
        if (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null || this.globalFocusChangeListener != null) {
            return;
        }
        this.globalFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailRelatedFragment$$ExternalSyntheticLambda3
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                MovieDetailRelatedFragment.registerGlobalFocusTracker$lambda$0(this.f$0, view, view2);
            }
        };
        decorView.getViewTreeObserver().addOnGlobalFocusChangeListener(this.globalFocusChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerGlobalFocusTracker$lambda$0(MovieDetailRelatedFragment movieDetailRelatedFragment, View view, View view2) {
        View view3 = movieDetailRelatedFragment.getView();
        if (view3 == null) {
            return;
        }
        boolean zIsDescendantOf = movieDetailRelatedFragment.isDescendantOf(view2, view3);
        boolean zIsDescendantOf2 = movieDetailRelatedFragment.isDescendantOf(view, view3);
        if (zIsDescendantOf && !zIsDescendantOf2) {
            FragmentActivity activity = movieDetailRelatedFragment.getActivity();
            boolean zIsDescendantOf3 = movieDetailRelatedFragment.isDescendantOf(view, activity != null ? activity.findViewById(R.id.flTop) : null);
            movieDetailRelatedFragment.enteredFromTop = zIsDescendantOf3;
            OnFocusListener onFocusListener = movieDetailRelatedFragment.focusListener;
            if (onFocusListener != null) {
                onFocusListener.onFocusChanged(zIsDescendantOf3);
                return;
            }
            return;
        }
        if (zIsDescendantOf || !zIsDescendantOf2) {
            return;
        }
        movieDetailRelatedFragment.enteredFromTop = false;
        OnFocusListener onFocusListener2 = movieDetailRelatedFragment.focusListener;
        if (onFocusListener2 != null) {
            onFocusListener2.onFocusChanged(false);
        }
    }

    private final void unRegisterGlobalFocusTracker() {
        FragmentActivity activity;
        Window window;
        View decorView;
        ViewTreeObserver.OnGlobalFocusChangeListener onGlobalFocusChangeListener = this.globalFocusChangeListener;
        if (onGlobalFocusChangeListener == null || (activity = getActivity()) == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        if (decorView.getViewTreeObserver().isAlive()) {
            decorView.getViewTreeObserver().removeOnGlobalFocusChangeListener(onGlobalFocusChangeListener);
        }
        this.globalFocusChangeListener = null;
    }

    private final boolean isDescendantOf(View child, View parent) {
        if (child != null && parent != null) {
            while (child != null) {
                if (child == parent) {
                    return true;
                }
                Object parent2 = child.getParent();
                child = parent2 instanceof View ? (View) parent2 : null;
            }
        }
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initListener() {
        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailRelatedFragment$$ExternalSyntheticLambda1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                MovieDetailRelatedFragment.initListener$lambda$3(this.f$0, viewHolder, obj, viewHolder2, obj2);
            }
        });
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        setOnItemViewSelectedListener(new BaseOnItemViewSelectedListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailRelatedFragment$$ExternalSyntheticLambda2
            @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
            public final void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                MovieDetailRelatedFragment.initListener$lambda$4(objectRef, viewHolder, obj, viewHolder2, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(MovieDetailRelatedFragment movieDetailRelatedFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        String string;
        if (obj instanceof Homelist.Typelist) {
            Context context = movieDetailRelatedFragment.getContext();
            if (context != null) {
                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                Homelist.Typelist typelist = (Homelist.Typelist) obj;
                String id = typelist.id;
                Intrinsics.checkNotNullExpressionValue(id, "id");
                companion.starter(context, id, typelist.poster);
                return;
            }
            return;
        }
        if (obj instanceof MovieListModel.MovieListItem) {
            Context context2 = movieDetailRelatedFragment.getContext();
            if (context2 != null) {
                MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) obj;
                PlayListDetailActivity.INSTANCE.start(context2, movieListItem.getLid(), movieListItem.getUsername());
                return;
            }
            return;
        }
        if (obj instanceof TrailerItem) {
            movieDetailRelatedFragment.playTrailer((TrailerItem) obj);
            return;
        }
        if (obj instanceof Actor) {
            Actor actor = (Actor) obj;
            String actor_id = actor.getActor_id();
            if (actor_id == null || actor_id.length() == 0) {
                Bundle arguments = movieDetailRelatedFragment.getArguments();
                if (arguments == null || (string = arguments.getString("id")) == null) {
                    string = "";
                }
                ActorListActivity.Companion companion2 = ActorListActivity.INSTANCE;
                Context contextRequireContext = movieDetailRelatedFragment.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                companion2.start(contextRequireContext, string, 1);
                return;
            }
            ActorDetailActivity.INSTANCE.start(movieDetailRelatedFragment.requireContext(), actor.getActor_id());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.movieboxpro.android.view.tvview.BaseVideoCardView] */
    public static final void initListener$lambda$4(Ref.ObjectRef objectRef, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        BaseVideoCardView baseVideoCardView = (BaseVideoCardView) objectRef.element;
        if (baseVideoCardView != null) {
            baseVideoCardView.hideTitle();
        }
        if ((viewHolder != null ? viewHolder.view : null) != null) {
            View view = viewHolder.view;
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.movieboxpro.android.view.tvview.BaseVideoCardView<*>");
            ?? r1 = (BaseVideoCardView) view;
            r1.showTitle();
            objectRef.element = r1;
        }
    }

    private final void getActorList(String id) {
        Observable<String> observableMovie_actor = Http.getService().Movie_actor("Movie_actor", id, 1, 10);
        Intrinsics.checkNotNullExpressionValue(observableMovie_actor, "Movie_actor(...)");
        Observable<R> observableCompose = observableMovie_actor.compose(RxUtils.rxTranslate2List(Actor.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, this), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailRelatedFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailRelatedFragment.getActorList$lambda$6(this.f$0, (List) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getActorList$lambda$6(MovieDetailRelatedFragment movieDetailRelatedFragment, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        List list = it;
        if (!list.isEmpty()) {
            HeaderItem headerItem = new HeaderItem("Actors");
            Context context = movieDetailRelatedFragment.getContext();
            ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(context != null ? new MovieDetailPresenterSelector(context) : null);
            arrayObjectAdapter.addAll(0, CollectionsKt.plus((Collection) list, (Iterable) CollectionsKt.listOf(new Actor(null, null, "More", 3, null))));
            if (movieDetailRelatedFragment.rowAdapter.size() >= 1) {
                movieDetailRelatedFragment.rowAdapter.add(1, new ListRow(headerItem, arrayObjectAdapter));
            } else {
                movieDetailRelatedFragment.rowAdapter.add(new ListRow(headerItem, arrayObjectAdapter));
            }
        }
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initData() {
        String string;
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString("id")) == null) {
            string = "";
        }
        getActorList(string);
        Bundle arguments2 = getArguments();
        Serializable serializable = arguments2 != null ? arguments2.getSerializable("movieList") : null;
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.model.movie.MovieDetail.Recommend>");
        ArrayList<MovieDetail.Recommend> arrayList = (ArrayList) serializable;
        Bundle arguments3 = getArguments();
        Serializable serializable2 = arguments3 != null ? arguments3.getSerializable("playList") : null;
        Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.model.movie.MovieListModel.MovieListItem>");
        ArrayList arrayList2 = (ArrayList) serializable2;
        Bundle arguments4 = getArguments();
        Serializable serializable3 = arguments4 != null ? arguments4.getSerializable("trailer") : null;
        ArrayList arrayList3 = serializable3 instanceof ArrayList ? (ArrayList) serializable3 : null;
        ArrayList arrayList4 = arrayList3;
        if (arrayList4 != null && !arrayList4.isEmpty()) {
            Context context = getContext();
            ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(context != null ? new MovieDetailPresenterSelector(context) : null);
            arrayObjectAdapter.addAll(0, arrayList4);
            this.rowAdapter.add(new ListRow(new HeaderItem(arrayList3.size() > 1 ? "Trailers" : HttpHeaders.TRAILER), arrayObjectAdapter));
        }
        if (!arrayList.isEmpty()) {
            ArrayList arrayList5 = new ArrayList();
            for (MovieDetail.Recommend recommend : arrayList) {
                Homelist.Typelist typelist = new Homelist.Typelist();
                typelist.poster = recommend.poster;
                typelist.quality_tag = recommend.quality_tag;
                typelist.title = recommend.title;
                typelist.id = recommend.mid;
                arrayList5.add(typelist);
            }
            Context context2 = getContext();
            ArrayObjectAdapter arrayObjectAdapter2 = new ArrayObjectAdapter(context2 != null ? new MovieDetailPresenterSelector(context2) : null);
            arrayObjectAdapter2.addAll(0, arrayList5);
            this.rowAdapter.add(new ListRow(new HeaderItem("Related Movies"), arrayObjectAdapter2));
        }
        ArrayList arrayList6 = arrayList2;
        if (arrayList6.isEmpty() || PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false)) {
            return;
        }
        Context context3 = getContext();
        ArrayObjectAdapter arrayObjectAdapter3 = new ArrayObjectAdapter(context3 != null ? new MovieDetailPresenterSelector(context3) : null);
        arrayObjectAdapter3.addAll(0, arrayList6);
        this.rowAdapter.add(new ListRow(new HeaderItem("Related MovieLists"), arrayObjectAdapter3));
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initView() {
        Context context = getContext();
        int iDp2px = context != null ? DensityUtils.dp2px(context, 10.0f) : 0;
        getVerticalGridView().setPadding(iDp2px, iDp2px, iDp2px, iDp2px);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    public MovieDetailRelatedPresenter bindPresenter() {
        return new MovieDetailRelatedPresenter(this);
    }

    private final void playTrailer(TrailerItem item) {
        String strResolveVideoId = YoutubeTrailerLauncher.INSTANCE.resolveVideoId(item.getVideoId(), item.getUrl());
        Context context = getContext();
        String str = strResolveVideoId;
        if (str == null || str.length() == 0 || context == null || !launchYoutubeApp(context, strResolveVideoId)) {
            YoutubePlayDialog.Companion companion = YoutubePlayDialog.INSTANCE;
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
            if (strResolveVideoId == null) {
                strResolveVideoId = "";
            }
            companion.showDialog(childFragmentManager, strResolveVideoId);
        }
    }

    private final boolean launchYoutubeApp(Context context, String videoId) {
        for (YoutubeTrailerLauncher.Target target : YoutubeTrailerLauncher.INSTANCE.launchTargets(videoId)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(target.getUri()));
            String packageName = target.getPackageName();
            if (packageName != null && packageName.length() != 0) {
                intent.setPackage(target.getPackageName());
            }
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.addFlags(335544320);
            intent.putExtra("force_fullscreen", true);
            intent.putExtra("finish_on_ended", true);
            intent.putExtra("VIDEO_ID", videoId);
            try {
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException | Exception unused) {
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: MovieDetailRelatedFragment.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;", "id", "", "movies", "", "Lcom/movieboxpro/android/model/movie/MovieDetail$Recommend;", "playList", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "trailer", "Lcom/movieboxpro/android/model/TrailerItem;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MovieDetailRelatedFragment newInstance(String id, List<? extends MovieDetail.Recommend> movies, List<? extends MovieListModel.MovieListItem> playList, List<? extends TrailerItem> trailer) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(movies, "movies");
            Intrinsics.checkNotNullParameter(playList, "playList");
            Intrinsics.checkNotNullParameter(trailer, "trailer");
            MovieDetailRelatedFragment movieDetailRelatedFragment = new MovieDetailRelatedFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("movieList", new ArrayList(movies));
            bundle.putSerializable("playList", new ArrayList(playList));
            bundle.putSerializable("trailer", new ArrayList(trailer));
            bundle.putString("id", id);
            movieDetailRelatedFragment.setArguments(bundle);
            return movieDetailRelatedFragment;
        }
    }
}
