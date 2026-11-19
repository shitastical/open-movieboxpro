package com.movieboxpro.android.tv.list;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PlayListVideoListFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 &2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001&B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007J\u0014\u0010\u0018\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00020\u00020\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0003H\u0014J\b\u0010\u001e\u001a\u00020\rH\u0014J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0012H\u0014J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0012H\u0014J\u0018\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0002H\u0014R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListVideoListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListDetailModel$ListBean;", "Lcom/movieboxpro/android/model/movie/MovieListDetailModel;", "<init>", "()V", "uid", "", "kotlin.jvm.PlatformType", "lid", "name", "sort", "userMovieList", "", "userUid", "getServiceData", "Lio/reactivex/Observable;", "initItemLayout", "", "getBundle", "", "arguments", "Landroid/os/Bundle;", "refreshList", "getListData", "", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "getData", "p", "isOpenLoadMore", "onFocus", "position", "onLastFocus", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListVideoListFragment extends BaseListFragment<MovieListDetailModel.ListBean, MovieListDetailModel> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean userMovieList;
    private final String uid = App.getUserData().uid_v2;
    private String lid = "";
    private String name = "";
    private String sort = "";
    private String userUid = "";

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isOpenLoadMore() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        if (this.userMovieList) {
            return HttpRequest.INSTANCE.post(API.Common.PLAY_LIST).param("playlist_uid", this.userUid).param("type", "user_playlists").param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).asRequest();
        }
        if (StringsKt.isBlank(this.name)) {
            Observable<String> observablePlaylists_get = Http.getService().Playlists_get("Playlists_get", this.uid, this.lid, this.mCurrentPage, this.mPageSize, BuildConfig.VERSION_NAME);
            Intrinsics.checkNotNullExpressionValue(observablePlaylists_get, "Playlists_get(...)");
            return observablePlaylists_get;
        }
        return HttpRequest.INSTANCE.post("Playlists_get_v2").param("sort_by", this.name).param("sort", this.sort).param("lid", this.lid).asRequest();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.layout_movielist_item2;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle arguments) {
        String string;
        String string2;
        this.mClass = MovieListDetailModel.ListBean.class;
        boolean z = arguments != null ? arguments.getBoolean("userMovieList", false) : false;
        this.userMovieList = z;
        if (!z) {
            this.mPageClass = MovieListDetailModel.class;
        }
        this.mPageSize = 24;
        this.preLoadNum = 24;
        String str = "";
        if (arguments == null || (string = arguments.getString("lid", "")) == null) {
            string = "";
        }
        this.lid = string;
        if (arguments != null && (string2 = arguments.getString("userUid", "")) != null) {
            str = string2;
        }
        this.userUid = str;
    }

    public final void refreshList(String name, String sort) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sort, "sort");
        this.name = name;
        this.sort = sort;
        if (StringsKt.isBlank(name)) {
            this.mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.tv.list.PlayListVideoListFragment$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    this.f$0.loadMore();
                }
            });
        } else {
            this.mAdapter.getLoadMoreModule().setOnLoadMoreListener(null);
        }
        startRefresh();
    }

    public final List<MovieListDetailModel.ListBean> getListData() {
        return this.mAdapter.getData();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.list.PlayListVideoListFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                PlayListVideoListFragment.onItemClick$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onItemClick$lambda$3(PlayListVideoListFragment playListVideoListFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        Object item = adapter.getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.movie.MovieListDetailModel.ListBean");
        MovieListDetailModel.ListBean listBean = (MovieListDetailModel.ListBean) item;
        if (listBean.getBox_type() == 1) {
            Context context = playListVideoListFragment.getContext();
            if (context != null) {
                MovieDetailActivity.INSTANCE.starter(context, String.valueOf(listBean.getId()), listBean.getPoster());
                return;
            }
            return;
        }
        Context context2 = playListVideoListFragment.getContext();
        if (context2 != null) {
            TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
            String strValueOf = String.valueOf(listBean.getId());
            String poster = listBean.getPoster();
            Intrinsics.checkNotNullExpressionValue(poster, "getPoster(...)");
            companion.starter(context2, strValueOf, poster);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public List<MovieListDetailModel.ListBean> getData(MovieListDetailModel p) {
        Intrinsics.checkNotNullParameter(p, "p");
        List<MovieListDetailModel.ListBean> list = p.getList();
        return list == null ? new ArrayList() : list;
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

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:42:0x0103  */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, MovieListDetailModel.ListBean item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvTitle, item.getTitle());
        ImageView imageView = (ImageView) helper.getView(R.id.movie_item_poster);
        ImageView imageView2 = (ImageView) helper.getView(R.id.movie_item_desc);
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivStar);
        TextView textView = (TextView) helper.getView(R.id.tvImdbRating);
        if (Intrinsics.areEqual(item.getImdb_rating(), "0") || item.getImdb_rating() == null) {
            CommonExtKt.gone(imageView3);
            CommonExtKt.gone(textView);
        } else {
            CommonExtKt.visible(imageView3);
            CommonExtKt.visible(textView);
            textView.setText(item.getImdb_rating().toString());
        }
        String quality_tag = item.getQuality_tag();
        if (quality_tag != null) {
            switch (quality_tag) {
                case "blu-ray":
                    GlideUtils.load(getContext(), R.drawable.ic_blu_ray, imageView2);
                    break;
                case "3d":
                    GlideUtils.load(getContext(), R.drawable.ic_3d, imageView2);
                    break;
                case "4k":
                    GlideUtils.load(getContext(), R.drawable.ic_4k, imageView2);
                    break;
                case "tc":
                    GlideUtils.load(getContext(), R.drawable.ic_tc, imageView2);
                    break;
                case "dvd":
                    GlideUtils.load(getContext(), R.drawable.ic_dvd, imageView2);
                    break;
                case "hdtv":
                    GlideUtils.load(getContext(), R.drawable.ic_hdtv, imageView2);
                    break;
                case "webdl":
                    GlideUtils.load(getContext(), R.drawable.ic_webdl, imageView2);
                    break;
                default:
                    CommonExtKt.gone(imageView2);
                    break;
            }
        } else {
            CommonExtKt.gone(imageView2);
        }
        GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), imageView, 8);
        ImageView imageView4 = (ImageView) helper.getView(R.id.ivTomato);
        imageView4.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
        TextView textView2 = (TextView) helper.getView(R.id.tvTomatoMeter);
        if (item.getTomato_meter() == 0) {
            CommonExtKt.gone(textView2);
            CommonExtKt.gone(imageView4);
            return;
        }
        CommonExtKt.visible(textView2);
        CommonExtKt.visible(imageView4);
        textView2.setText(item.getTomato_meter() + "%");
    }

    /* JADX INFO: compiled from: PlayListVideoListFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListVideoListFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/list/PlayListVideoListFragment;", "userUid", "", "userMovieList", "", "lid", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PlayListVideoListFragment newInstance(String userUid, boolean userMovieList) {
            Intrinsics.checkNotNullParameter(userUid, "userUid");
            PlayListVideoListFragment playListVideoListFragment = new PlayListVideoListFragment();
            Bundle bundle = new Bundle();
            playListVideoListFragment.setArguments(bundle);
            bundle.putString("userUid", userUid);
            bundle.putBoolean("userMovieList", userMovieList);
            return playListVideoListFragment;
        }

        public final PlayListVideoListFragment newInstance(String lid) {
            Intrinsics.checkNotNullParameter(lid, "lid");
            PlayListVideoListFragment playListVideoListFragment = new PlayListVideoListFragment();
            Bundle bundle = new Bundle();
            playListVideoListFragment.setArguments(bundle);
            bundle.putString("lid", lid);
            return playListVideoListFragment;
        }
    }
}
