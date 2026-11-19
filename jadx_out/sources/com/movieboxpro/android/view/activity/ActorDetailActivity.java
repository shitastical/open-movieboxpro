package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.ActorDetail;
import com.movieboxpro.android.model.MediaItem;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.ActivityDataBindingDelegate;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.widget.LoadingStatusView;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityActorDetailBinding;
import com.owen.tvrecyclerview.widget.V7LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: ActorDetailActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/activity/ActorDetailActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/ActivityActorDetailBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/ActivityActorDetailBinding;", "binding$delegate", "Lcom/movieboxpro/android/utils/ActivityDataBindingDelegate;", "initListener", "", "initData", "initView", "loadDetail", "initList", "actorDetail", "Lcom/movieboxpro/android/model/ActorDetail;", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActorDetailActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(ActorDetailActivity.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/ActivityActorDetailBinding;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final ActivityDataBindingDelegate binding = new ActivityDataBindingDelegate(ActivityActorDetailBinding.class, this);

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
    }

    private final ActivityActorDetailBinding getBinding() {
        return (ActivityActorDetailBinding) this.binding.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        loadDetail();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().loadStatusView.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.ActorDetailActivity$$ExternalSyntheticLambda2
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                this.f$0.loadDetail();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadDetail() {
        String stringExtra = getIntent().getStringExtra("id");
        if (stringExtra == null) {
            stringExtra = "";
        }
        Observable<String> observableActorDetail = Http.getService().ActorDetail("Actor", stringExtra);
        Intrinsics.checkNotNullExpressionValue(observableActorDetail, "ActorDetail(...)");
        Observable<R> observableCompose = observableActorDetail.compose(RxUtils.rxTranslate2Bean(ActorDetail.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, this), new Function1() { // from class: com.movieboxpro.android.view.activity.ActorDetailActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ActorDetailActivity.loadDetail$lambda$1(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.ActorDetailActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ActorDetailActivity.loadDetail$lambda$2(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.ActorDetailActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ActorDetailActivity.loadDetail$lambda$3(this.f$0, (ActorDetail) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit loadDetail$lambda$2(ActorDetailActivity actorDetailActivity, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        actorDetailActivity.getBinding().loadStatusView.showLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit loadDetail$lambda$3(ActorDetailActivity actorDetailActivity, ActorDetail it) {
        Intrinsics.checkNotNullParameter(it, "it");
        actorDetailActivity.getBinding().loadStatusView.hideLoading();
        LinearLayout llContent = actorDetailActivity.getBinding().llContent;
        Intrinsics.checkNotNullExpressionValue(llContent, "llContent");
        CommonExtKt.visible(llContent);
        actorDetailActivity.initList(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit loadDetail$lambda$1(ActorDetailActivity actorDetailActivity, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        LoadingStatusView loadingStatusView = actorDetailActivity.getBinding().loadStatusView;
        String message = it.getMessage();
        if (message == null) {
            message = "";
        }
        loadingStatusView.showError(message);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.movieboxpro.android.view.activity.ActorDetailActivity$initList$movieAdapter$1] */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.movieboxpro.android.view.activity.ActorDetailActivity$initList$tvAdapter$1] */
    private final void initList(ActorDetail actorDetail) {
        final int i = R.layout.layout_movielist_item2;
        List<MediaItem> movie = actorDetail.getMovie();
        if (movie == null) {
            movie = CollectionsKt.emptyList();
        }
        final ArrayList arrayList = new ArrayList(movie);
        final ?? r2 = new CommonBaseAdapter<MediaItem>(i, arrayList) { // from class: com.movieboxpro.android.view.activity.ActorDetailActivity$initList$movieAdapter$1
            {
                ArrayList<MediaItem> arrayList2 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, MediaItem item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                ConstraintLayout constraintLayout = (ConstraintLayout) holder.getView(R.id.clContainer);
                ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = DensityUtils.dp2px(getContext(), 120.0f);
                    constraintLayout.setLayoutParams(layoutParams);
                    GlideUtils.loadWithCorner(getContext(), item.getPoster(), (ImageView) holder.getView(R.id.movie_item_poster), 10);
                    holder.setText(R.id.tvImdbRating, item.getImdb_rating());
                    GlideUtils.load(getContext(), CommonUtils.getMovieTag(item.getQuality_tag_new()), (ImageView) holder.getView(R.id.movie_item_desc));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            }
        };
        ActorDetailActivity actorDetailActivity = this;
        getBinding().rvMovies.setLayoutManager(new V7LinearLayoutManager((Context) actorDetailActivity, 0, false));
        getBinding().rvMovies.setAdapter((RecyclerView.Adapter) r2);
        r2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.ActorDetailActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                ActorDetailActivity.initList$lambda$4(this.f$0, r2, baseQuickAdapter, view, i2);
            }
        });
        final int i2 = R.layout.layout_movielist_item2;
        List<MediaItem> tv2 = actorDetail.getTv();
        if (tv2 == null) {
            tv2 = CollectionsKt.emptyList();
        }
        final ArrayList arrayList2 = new ArrayList(tv2);
        final ?? r6 = new CommonBaseAdapter<MediaItem>(i2, arrayList2) { // from class: com.movieboxpro.android.view.activity.ActorDetailActivity$initList$tvAdapter$1
            {
                ArrayList<MediaItem> arrayList3 = arrayList2;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, MediaItem item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                ConstraintLayout constraintLayout = (ConstraintLayout) holder.getView(R.id.clContainer);
                ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = DensityUtils.dp2px(getContext(), 120.0f);
                    constraintLayout.setLayoutParams(layoutParams);
                    GlideUtils.loadWithCorner(getContext(), item.getPoster(), (ImageView) holder.getView(R.id.movie_item_poster), 10);
                    holder.setText(R.id.tvImdbRating, item.getImdb_rating());
                    GlideUtils.load(getContext(), CommonUtils.getMovieTag(item.getQuality_tag_new()), (ImageView) holder.getView(R.id.movie_item_desc));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            }
        };
        getBinding().rvTvShows.setLayoutManager(new V7LinearLayoutManager((Context) actorDetailActivity, 0, false));
        getBinding().rvTvShows.setAdapter((RecyclerView.Adapter) r6);
        r6.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.ActorDetailActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i3) {
                ActorDetailActivity.initList$lambda$5(this.f$0, r6, baseQuickAdapter, view, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initList$lambda$4(ActorDetailActivity actorDetailActivity, ActorDetailActivity$initList$movieAdapter$1 actorDetailActivity$initList$movieAdapter$1, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
        ActorDetailActivity actorDetailActivity2 = actorDetailActivity;
        String id = actorDetailActivity$initList$movieAdapter$1.getItem(i).getId();
        if (id == null) {
            id = "";
        }
        companion.starter(actorDetailActivity2, id, actorDetailActivity$initList$movieAdapter$1.getItem(i).getPoster());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initList$lambda$5(ActorDetailActivity actorDetailActivity, ActorDetailActivity$initList$tvAdapter$1 actorDetailActivity$initList$tvAdapter$1, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
        ActorDetailActivity actorDetailActivity2 = actorDetailActivity;
        String id = actorDetailActivity$initList$tvAdapter$1.getItem(i).getId();
        if (id == null) {
            id = "";
        }
        String poster = actorDetailActivity$initList$tvAdapter$1.getItem(i).getPoster();
        companion.starter(actorDetailActivity2, id, poster != null ? poster : "");
    }

    /* JADX INFO: compiled from: ActorDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/ActorDetailActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intent intent = new Intent(context, (Class<?>) ActorDetailActivity.class);
            intent.putExtra("id", id);
            if (context != null) {
                context.startActivity(intent);
            }
        }
    }
}
