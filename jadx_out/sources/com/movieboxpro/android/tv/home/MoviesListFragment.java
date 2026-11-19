package com.movieboxpro.android.tv.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.OnBackPressedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.listener.OnVideoListFocusListener;
import com.movieboxpro.android.model.movie.MovieDetailModel;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MoviesListFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0012H\u0014JF\u0010\u0018\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J\u0016\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J\b\u0010 \u001a\u00020\u0012H\u0014J\b\u0010!\u001a\u00020\u001eH\u0014J\b\u0010\"\u001a\u00020\u0014H\u0014J\b\u0010#\u001a\u00020\u0014H\u0014J\b\u0010$\u001a\u00020\u0014H\u0014J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0007J\u0012\u0010(\u001a\u00020\u00122\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\u0018\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/movieboxpro/android/tv/home/MoviesListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieDetailModel;", "", "<init>", "()V", "year", "gener", "sort", "rating", "quality", "country", "imdbRating", "tomatoMeter", "topId", "listenerVideoList", "Lcom/movieboxpro/android/listener/OnVideoListFocusListener;", "setFocusUpListener", "", "openLowMemoryEmpty", "", "getServiceData", "Lio/reactivex/Observable;", "onRecyclerViewFocus", "refreshData", "selected", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onFocus", "position", "", "onLastFocus", "initFocusUpListener", "initItemLayout", "isOpenLoadMore", "isNeedListenerFocusUp", "enableEventBus", "onBackPressed", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OnBackPressedEvent;", "getBundle", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MoviesListFragment extends BaseListFragment<MovieDetailModel, String> {
    private OnVideoListFocusListener listenerVideoList;
    private String year = "";
    private String gener = "0";
    private String sort = "released";
    private String rating = "";
    private String quality = "";
    private String country = "";
    private String imdbRating = "";
    private String tomatoMeter = "";
    private String topId = "";

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isNeedListenerFocusUp() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isOpenLoadMore() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean openLowMemoryEmpty() {
        return true;
    }

    public final void setFocusUpListener(OnVideoListFocusListener listenerVideoList) {
        Intrinsics.checkNotNullParameter(listenerVideoList, "listenerVideoList");
        this.listenerVideoList = listenerVideoList;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        if (this.topId.length() == 0) {
            Observable<String> observableVideoList = Http.getService().VideoList(API.Movie.MOVE_LIST, App.getUserData().uid_v2, this.year, this.gener, this.sort, this.rating, this.quality, this.country, this.imdbRating, this.tomatoMeter, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), BuildConfig.VERSION_NAME);
            Intrinsics.checkNotNullExpressionValue(observableVideoList, "VideoList(...)");
            return observableVideoList;
        }
        Observable<String> observableTop_list_movie = Http.getService().Top_list_movie(API.Movie.TOP_LIST_MOVIE, this.topId, this.mCurrentPage, this.mPageSize);
        Intrinsics.checkNotNullExpressionValue(observableTop_list_movie, "Top_list_movie(...)");
        return observableTop_list_movie;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onRecyclerViewFocus() {
        OnVideoListFocusListener onVideoListFocusListener = this.listenerVideoList;
        if (onVideoListFocusListener != null) {
            onVideoListFocusListener.onFocus();
        }
    }

    public final void refreshData(String sort, String year, String gener, String rating, String quality, String country, String imdbRating, String tomatoMeter) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(gener, "gener");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(imdbRating, "imdbRating");
        Intrinsics.checkNotNullParameter(tomatoMeter, "tomatoMeter");
        this.sort = sort;
        this.year = year;
        this.gener = gener;
        this.rating = rating;
        this.quality = quality;
        this.country = country;
        this.imdbRating = imdbRating;
        this.tomatoMeter = tomatoMeter;
        this.topId = "";
        startRefresh();
    }

    public final void refreshData(boolean selected, String topId) {
        Intrinsics.checkNotNullParameter(topId, "topId");
        if (selected) {
            this.topId = topId;
        } else {
            this.topId = "";
        }
        startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.home.MoviesListFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MoviesListFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onItemClick$lambda$1(MoviesListFragment moviesListFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        Object item = adapter.getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.movie.MovieDetailModel");
        MovieDetailModel movieDetailModel = (MovieDetailModel) item;
        Context context = moviesListFragment.getContext();
        if (context != null) {
            MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
            String id = movieDetailModel.id;
            Intrinsics.checkNotNullExpressionValue(id, "id");
            companion.starter(context, id, movieDetailModel.poster);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onFocus(int position) {
        View viewByPosition = this.mAdapter.getViewByPosition(position, R.id.tvTitle);
        if (viewByPosition != null) {
            CommonExtKt.visible(viewByPosition);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onLastFocus(int position) {
        View viewByPosition = this.mAdapter.getViewByPosition(position, R.id.tvTitle);
        if (viewByPosition != null) {
            CommonExtKt.invisible(viewByPosition);
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void initFocusUpListener() {
        this.mRecyclerView.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.tv.home.MoviesListFragment$$ExternalSyntheticLambda0
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
            public final boolean onInBorderKeyEvent(int i, View view) {
                return MoviesListFragment.initFocusUpListener$lambda$2(this.f$0, i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initFocusUpListener$lambda$2(MoviesListFragment moviesListFragment, int i, View view) {
        if (i != 33) {
            return false;
        }
        OnVideoListFocusListener onVideoListFocusListener = moviesListFragment.listenerVideoList;
        if (onVideoListFocusListener == null) {
            return true;
        }
        onVideoListFocusListener.onFocusUp();
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.layout_movielist_item2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onBackPressed(OnBackPressedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getType() == 2) {
            this.mRecyclerView.smoothScrollToPosition(0);
            OnVideoListFocusListener onVideoListFocusListener = this.listenerVideoList;
            if (onVideoListFocusListener != null) {
                onVideoListFocusListener.onFocusUp();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle arguments) {
        this.mPageSize = 17;
        this.preLoadNum = 12;
        this.mClass = MovieDetailModel.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, MovieDetailModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvTitle, item.title);
        ImageView imageView = (ImageView) helper.getView(R.id.movie_item_poster);
        ImageView imageView2 = (ImageView) helper.getView(R.id.movie_item_desc);
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivStar);
        TextView textView = (TextView) helper.getView(R.id.tvImdbRating);
        if (item.imdb_rating == 0.0f) {
            CommonExtKt.gone(imageView3);
            CommonExtKt.gone(textView);
        } else {
            CommonExtKt.visible(imageView3);
            CommonExtKt.visible(textView);
            textView.setText(String.valueOf(item.imdb_rating));
        }
        GlideUtils.load(getContext(), CommonUtils.getMovieTag(item.quality_tag), imageView2);
        GlideUtils.loadCornerPortraitHolder(getContext(), item.poster, imageView, 8);
        ImageView imageView4 = (ImageView) helper.getView(R.id.ivTomato);
        TextView textView2 = (TextView) helper.getView(R.id.tvTomatoMeter);
        imageView4.setImageResource(CommonUtils.getTomatoImg(item.tomato_meter));
        if (item.tomato_meter == 0) {
            CommonExtKt.gone(textView2);
            CommonExtKt.gone(imageView4);
            return;
        }
        CommonExtKt.visible(textView2);
        CommonExtKt.visible(imageView4);
        CommonExtKt.textShadow$default(textView2, item.tomato_meter + "%", 0, 0, 6, null);
    }
}
