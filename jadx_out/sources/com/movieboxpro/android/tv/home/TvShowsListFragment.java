package com.movieboxpro.android.tv.home;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
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
import com.movieboxpro.android.model.tv.TvDetailModel;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: TvShowsListFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0014J>\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003J\u0016\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0013H\u0014J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\b\u0010 \u001a\u00020\u0011H\u0014J\b\u0010!\u001a\u00020\u0011H\u0014J\b\u0010\"\u001a\u00020\u0013H\u0014J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u001bH\u0014J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u001bH\u0014J\b\u0010&\u001a\u00020\u0013H\u0014J\u0012\u0010'\u001a\u00020\u00112\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\u0018\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/movieboxpro/android/tv/home/TvShowsListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/tv/TvDetailModel;", "", "<init>", "()V", "year", "sort", "gener", "rating", "country", "imdbRating", "tomatoMeter", "topId", "listenerVideoList", "Lcom/movieboxpro/android/listener/OnVideoListFocusListener;", "setFocusUpListener", "", "openLowMemoryEmpty", "", "getServiceData", "Lio/reactivex/Observable;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "refreshData", "selected", "initItemLayout", "", "isOpenLoadMore", "onBackPressed", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OnBackPressedEvent;", "onRecyclerViewFocus", "initFocusUpListener", "enableEventBus", "onFocus", "position", "onLastFocus", "isNeedListenerFocusUp", "getBundle", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvShowsListFragment extends BaseListFragment<TvDetailModel, String> {
    private OnVideoListFocusListener listenerVideoList;
    private String year = "0";
    private String sort = "adder";
    private String gener = "0";
    private String rating = "";
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
            Observable<String> observableVideoList = Http.getService().VideoList(API.Tv.TV_LIST, App.getUserData().uid_v2, this.year, this.gener, this.sort, this.rating, "", this.country, this.imdbRating, this.tomatoMeter, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), BuildConfig.VERSION_NAME);
            Intrinsics.checkNotNullExpressionValue(observableVideoList, "VideoList(...)");
            return observableVideoList;
        }
        Observable<String> observableTop_list_movie = Http.getService().Top_list_movie(API.Tv.TOP_LIST_TV, this.topId, this.mCurrentPage, this.mPageSize);
        Intrinsics.checkNotNullExpressionValue(observableTop_list_movie, "Top_list_movie(...)");
        return observableTop_list_movie;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.home.TvShowsListFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TvShowsListFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onItemClick$lambda$1(TvShowsListFragment tvShowsListFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        Object item = adapter.getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.tv.TvDetailModel");
        TvDetailModel tvDetailModel = (TvDetailModel) item;
        Context context = tvShowsListFragment.getContext();
        if (context != null) {
            TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
            String id = tvDetailModel.id;
            Intrinsics.checkNotNullExpressionValue(id, "id");
            String poster = tvDetailModel.poster;
            Intrinsics.checkNotNullExpressionValue(poster, "poster");
            companion.starter(context, id, poster);
        }
    }

    public final void refreshData(String sort, String year, String gener, String rating, String country, String imdbRating, String tomatoMeter) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(gener, "gener");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(imdbRating, "imdbRating");
        Intrinsics.checkNotNullParameter(tomatoMeter, "tomatoMeter");
        this.sort = sort;
        this.year = year;
        this.gener = gener;
        this.rating = rating;
        this.country = country;
        this.topId = "";
        this.imdbRating = imdbRating;
        this.tomatoMeter = tomatoMeter;
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
    protected int initItemLayout() {
        return R.layout.layout_tvlist_item_2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onBackPressed(OnBackPressedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getType() == 3) {
            this.mRecyclerView.smoothScrollToPosition(0);
            OnVideoListFocusListener onVideoListFocusListener = this.listenerVideoList;
            if (onVideoListFocusListener != null) {
                onVideoListFocusListener.onFocusUp();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onRecyclerViewFocus() {
        OnVideoListFocusListener onVideoListFocusListener = this.listenerVideoList;
        if (onVideoListFocusListener != null) {
            onVideoListFocusListener.onFocus();
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void initFocusUpListener() {
        this.mRecyclerView.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.tv.home.TvShowsListFragment$$ExternalSyntheticLambda1
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
            public final boolean onInBorderKeyEvent(int i, View view) {
                return TvShowsListFragment.initFocusUpListener$lambda$2(this.f$0, i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initFocusUpListener$lambda$2(TvShowsListFragment tvShowsListFragment, int i, View view) {
        if (i != 33) {
            return false;
        }
        OnVideoListFocusListener onVideoListFocusListener = tvShowsListFragment.listenerVideoList;
        if (onVideoListFocusListener == null) {
            return true;
        }
        onVideoListFocusListener.onFocusUp();
        return true;
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
    protected void getBundle(Bundle arguments) {
        this.mPageSize = 17;
        this.preLoadNum = 12;
        this.mClass = TvDetailModel.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, TvDetailModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvTitle, item.title);
        ImageView imageView = (ImageView) helper.getView(R.id.tv_item_poster);
        TextView textView = (TextView) helper.getView(R.id.tv_item_season);
        Group group = (Group) helper.getView(R.id.group);
        SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.slv_right_text);
        ImageView imageView2 = (ImageView) helper.getView(R.id.ivStar);
        TextView textView2 = (TextView) helper.getView(R.id.tvImdbRating);
        if (item.imdb_rating == 0.0f) {
            CommonExtKt.gone(imageView2);
            CommonExtKt.gone(textView2);
        } else {
            CommonExtKt.visible(imageView2);
            CommonExtKt.visible(textView2);
            textView2.setText(String.valueOf(item.imdb_rating));
        }
        textView.setText(item.season_episode);
        if (item.update_title != null && !TextUtils.isEmpty(item.update_title)) {
            CommonExtKt.visible(group);
            slantedTextView.setText(item.update_title);
        } else {
            CommonExtKt.gone(group);
        }
        if (imageView != null) {
            if (Intrinsics.areEqual(item.id, "0")) {
                CommonExtKt.gone(textView);
                GlideUtils.load(getContext(), R.drawable.ic_movie_more, imageView);
                return;
            }
            GlideUtils.loadCornerPortraitHolder(getContext(), item.poster, imageView, 8);
        }
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivTomato);
        imageView3.setImageResource(CommonUtils.getTomatoImg(item.tomato_meter));
        TextView textView3 = (TextView) helper.getView(R.id.tvTomatoMeter);
        if (item.tomato_meter == 0) {
            CommonExtKt.gone(textView3);
            CommonExtKt.gone(imageView3);
            return;
        }
        CommonExtKt.visible(textView3);
        CommonExtKt.visible(imageView3);
        CommonExtKt.textShadow$default(textView3, item.tomato_meter + "%", 0, 0, 6, null);
    }
}
