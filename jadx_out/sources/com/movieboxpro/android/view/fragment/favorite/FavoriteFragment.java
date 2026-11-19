package com.movieboxpro.android.view.fragment.favorite;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.OnBackPressedEvent;
import com.movieboxpro.android.event.RefreshFavoriteEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.FocusUpListener;
import com.movieboxpro.android.model.common.Collectlist;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: FavoriteFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0007J>\u0010\u0014\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J\b\u0010\u0016\u001a\u00020\u0013H\u0014J\b\u0010\u0017\u001a\u00020\u0013H\u0014J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\u0012\u0010\u001c\u001a\u00020\u00132\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0012\u0010\u001f\u001a\u00020\u00132\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u001bH\u0014J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u001bH\u0014J\b\u0010%\u001a\u00020&H\u0014J\b\u0010'\u001a\u00020\u0010H\u0014J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020*H\u0007J\u0010\u0010+\u001a\u00020\u00132\u0006\u0010)\u001a\u00020,H\u0007J\u0010\u0010-\u001a\u00020\u00132\u0006\u0010)\u001a\u00020.H\u0007J\u0010\u0010/\u001a\u00020\u00132\u0006\u0010)\u001a\u000200H\u0007J\b\u00101\u001a\u00020\u0010H\u0014J\b\u00102\u001a\u00020\u0010H\u0014J\b\u00103\u001a\u00020\u0010H\u0014J\u0018\u00104\u001a\u00020\u00132\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0002H\u0014J\b\u00108\u001a\u00020\u001bH\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/movieboxpro/android/view/fragment/favorite/FavoriteFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/common/Collectlist;", "", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/FocusUpListener;", "boxType", "sort", "gener", "year", "rating", "country", "quality", "allDefault", "", "isAll", "setListener", "", "setFilter", TvContractCompat.PreviewProgramColumns.COLUMN_GENRE, "onLoadComplete", "initFocusUpListener", "getServiceData", "Lio/reactivex/Observable;", "initItemLayout", "", "getBundle", "arguments", "Landroid/os/Bundle;", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onFocus", "position", "onLastFocus", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "openLowMemoryEmpty", "onLoginIn", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LoginEvent;", "onSignOut", "Lcom/movieboxpro/android/event/LogoutEvent;", "onRefresh", "Lcom/movieboxpro/android/event/RefreshFavoriteEvent;", "onBackPressed", "Lcom/movieboxpro/android/event/OnBackPressedEvent;", "isNeedLogin", "enableEventBus", "isNeedListenerFocusUp", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "gridLayoutSpan", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FavoriteFragment extends BaseListFragment<Collectlist, String> {
    private boolean isAll;
    private FocusUpListener listener;
    private String boxType = "0";
    private String sort = "";
    private String gener = "0";
    private String year = "0";
    private String rating = "";
    private String country = "";
    private String quality = "";
    private boolean allDefault = true;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRecyclerView$lambda$6$lambda$5() {
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int gridLayoutSpan() {
        return 6;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isNeedListenerFocusUp() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isNeedLogin() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean openLowMemoryEmpty() {
        return true;
    }

    public final void setListener(FocusUpListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    public final void setFilter(String boxType, String year, String genre, String sort, String rating, String quality, String country) {
        Intrinsics.checkNotNullParameter(boxType, "boxType");
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(country, "country");
        this.mCurrentPage = 1;
        this.boxType = boxType;
        this.year = year;
        this.gener = genre;
        this.sort = sort;
        this.rating = rating;
        this.quality = quality;
        this.country = country;
        this.isAll = Intrinsics.areEqual(boxType, "0");
        this.allDefault = Intrinsics.areEqual(year, "0") && Intrinsics.areEqual(genre, "0") && Intrinsics.areEqual(sort, "") && StringsKt.isBlank(country) && Intrinsics.areEqual(boxType, "0");
        startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onLoadComplete() {
        if (this.isAll) {
            this.mAdapter.getLoadMoreModule().setEnableLoadMore(this.allDefault);
        } else {
            this.mAdapter.getLoadMoreModule().setEnableLoadMore(false);
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void initFocusUpListener() {
        this.mRecyclerView.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.view.fragment.favorite.FavoriteFragment$$ExternalSyntheticLambda7
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
            public final boolean onInBorderKeyEvent(int i, View view) {
                return FavoriteFragment.initFocusUpListener$lambda$0(this.f$0, i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initFocusUpListener$lambda$0(FavoriteFragment favoriteFragment, int i, View view) {
        if (i != 33) {
            return false;
        }
        FocusUpListener focusUpListener = favoriteFragment.listener;
        if (focusUpListener != null) {
            focusUpListener.onFocusUp();
        }
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        if (this.allDefault) {
            return HttpRequest.INSTANCE.post(API.Movie.MOVE_COLLECT).param("type", "get").param("box_type", this.boxType).param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).param("sort", this.sort).param("year", this.year).param("cat_id", this.gener).param("country", this.country).param(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, this.rating).param("quality", this.quality).asRequest();
        }
        return HttpRequest.INSTANCE.post("Movie_collect_list").param("box_type", this.boxType).param("sort", this.sort).param("year", this.year).param("cat_id", this.gener).param("country", this.country).param(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, this.rating).param("quality", this.quality).asRequest();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_favorite_item;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle arguments) {
        this.mClass = Collectlist.class;
        this.mPageSize = 18;
        this.isAll = Intrinsics.areEqual(this.boxType, "0");
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void initRecyclerView(RecyclerView recyclerView) {
        this.mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.fragment.favorite.FavoriteFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                return FavoriteFragment.initRecyclerView$lambda$6(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
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
    public static final boolean initRecyclerView$lambda$6(final FavoriteFragment favoriteFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        Collectlist collectlist = (Collectlist) favoriteFragment.mAdapter.getItem(i);
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        if (collectlist.box_type == 1) {
            arrayList.add(collectlist.id);
        } else {
            arrayList2.add(collectlist.id);
        }
        new MsgHintDialog.Builder(favoriteFragment.getContext()).setContent("Are you sure to delete it").setPositiveText("Delete").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.favorite.FavoriteFragment$$ExternalSyntheticLambda3
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                FavoriteFragment.initRecyclerView$lambda$6$lambda$4(this.f$0, arrayList, arrayList2);
            }
        }).setCancelActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.favorite.FavoriteFragment$$ExternalSyntheticLambda4
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                FavoriteFragment.initRecyclerView$lambda$6$lambda$5();
            }
        }).create().show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRecyclerView$lambda$6$lambda$4(final FavoriteFragment favoriteFragment, ArrayList arrayList, ArrayList arrayList2) {
        FavoriteFragment favoriteFragment2 = favoriteFragment;
        Object objAs = HttpRequest.INSTANCE.post(favoriteFragment2, API.Movie.MOVE_COLLECT).param("mid", arrayList).param("tid", arrayList2).param("type", "del").asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(favoriteFragment2));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.fragment.favorite.FavoriteFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FavoriteFragment.initRecyclerView$lambda$6$lambda$4$lambda$1(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.favorite.FavoriteFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FavoriteFragment.initRecyclerView$lambda$6$lambda$4$lambda$2(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.favorite.FavoriteFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FavoriteFragment.initRecyclerView$lambda$6$lambda$4$lambda$3(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initRecyclerView$lambda$6$lambda$4$lambda$2(FavoriteFragment favoriteFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        favoriteFragment.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initRecyclerView$lambda$6$lambda$4$lambda$3(FavoriteFragment favoriteFragment, String str) {
        favoriteFragment.hideLoadingView();
        favoriteFragment.startRefresh();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initRecyclerView$lambda$6$lambda$4$lambda$1(FavoriteFragment favoriteFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        favoriteFragment.hideLoadingView();
        ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
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
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.favorite.FavoriteFragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                FavoriteFragment.onItemClick$lambda$9(this.f$0, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onItemClick$lambda$9(FavoriteFragment favoriteFragment, BaseQuickAdapter adapter, View view, int i) {
        Context context;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        Object item = adapter.getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.common.Collectlist");
        Collectlist collectlist = (Collectlist) item;
        if (collectlist.box_type == 1) {
            Context context2 = favoriteFragment.getContext();
            if (context2 != null) {
                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                String id = collectlist.id;
                Intrinsics.checkNotNullExpressionValue(id, "id");
                companion.starter(context2, id, collectlist.poster);
                return;
            }
            return;
        }
        if (collectlist.box_type != 2 || (context = favoriteFragment.getContext()) == null) {
            return;
        }
        TvDetailActivity.Companion companion2 = TvDetailActivity.INSTANCE;
        String id2 = collectlist.id;
        Intrinsics.checkNotNullExpressionValue(id2, "id");
        String poster = collectlist.poster;
        Intrinsics.checkNotNullExpressionValue(poster, "poster");
        companion2.starter(context, id2, poster);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLoginIn(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        startRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSignOut(LogoutEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        startRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onRefresh(RefreshFavoriteEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        startRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onBackPressed(OnBackPressedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getType() == 5) {
            this.mRecyclerView.smoothScrollToPosition(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, Collectlist item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tvUpdateCount);
        if (item.updateCount == 0) {
            CommonExtKt.gone(textView);
        } else {
            CommonExtKt.visible(textView);
            if (item.updateCount > 99) {
                textView.setText("99+");
            } else {
                textView.setText(String.valueOf(item.updateCount));
            }
        }
        helper.setText(R.id.tvTitle, item.title);
        ImageView imageView = (ImageView) helper.getView(R.id.favorite_poster);
        TextView textView2 = (TextView) helper.getView(R.id.favorite_poster_describ);
        SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.slv_right_tri);
        ImageView imageView2 = (ImageView) helper.getView(R.id.favorite_desc);
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivStar);
        TextView textView3 = (TextView) helper.getView(R.id.tvImdbRating);
        if (item.imdb_rating == 0.0f) {
            CommonExtKt.gone(imageView3);
            CommonExtKt.gone(textView3);
        } else {
            CommonExtKt.visible(imageView3);
            CommonExtKt.visible(textView3);
            textView3.setText(String.valueOf(item.imdb_rating));
        }
        ImageView imageView4 = (ImageView) helper.getView(R.id.ivTomato);
        TextView textView4 = (TextView) helper.getView(R.id.tvTomatoMeter);
        imageView4.setImageResource(CommonUtils.getTomatoImg(item.tomato_meter));
        if (item.tomato_meter == 0) {
            CommonExtKt.gone(textView4);
            CommonExtKt.gone(imageView4);
        } else {
            CommonExtKt.visible(textView4);
            CommonExtKt.visible(imageView4);
            CommonExtKt.textShadow$default(textView4, item.tomato_meter + "%", 0, 0, 6, null);
        }
        int i = item.box_type;
        if (i == 1) {
            CommonExtKt.gone(textView2);
            CommonExtKt.gone(slantedTextView);
            CommonExtKt.visible(imageView2);
            GlideUtils.loadCornerPortraitHolder(getContext(), item.poster, imageView, 8);
            GlideUtils.load(getContext(), CommonUtils.getMovieTag(item.quality_tag), imageView2);
            return;
        }
        if (i != 2) {
            return;
        }
        CommonExtKt.visible(textView2);
        CommonExtKt.gone(imageView2);
        GlideUtils.loadCornerPortraitHolder(getContext(), item.poster, imageView, 8);
        String str = item.update_title;
        if (str == null || str.length() == 0) {
            CommonExtKt.gone(slantedTextView);
        } else {
            CommonExtKt.visible(slantedTextView);
            slantedTextView.setText(item.update_title);
        }
        textView2.setText(item.season_episode);
    }
}
