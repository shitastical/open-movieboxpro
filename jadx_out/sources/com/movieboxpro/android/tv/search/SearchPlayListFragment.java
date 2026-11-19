package com.movieboxpro.android.tv.search;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.tv.list.PlayListDetailActivity;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchPlayListFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u001a\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchPlayListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "", "<init>", "()V", "keyword", "uid", "getServiceData", "Lio/reactivex/Observable;", "gridLayoutSpan", "", "initItemLayout", "isOpenLoadMore", "", "refreshData", "", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "getBundle", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SearchPlayListFragment extends BaseListFragment<MovieListModel.MovieListItem, String> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String keyword = "";
    private String uid = "";

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int gridLayoutSpan() {
        return 3;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isOpenLoadMore() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        Observable<String> observableSearch = Http.getService().Search(API.Search.SEARCH, "playlists", this.keyword, this.uid, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize));
        Intrinsics.checkNotNullExpressionValue(observableSearch, "Search(...)");
        return observableSearch;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_play_list_item;
    }

    public final void refreshData(String keyword) {
        this.keyword = keyword;
        startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.search.SearchPlayListFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SearchPlayListFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        };
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
    public static final void onItemClick$lambda$1(SearchPlayListFragment searchPlayListFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String lid;
        String username;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) searchPlayListFragment.mAdapter.getItem(i);
        Context context = searchPlayListFragment.getContext();
        if (context != null) {
            PlayListDetailActivity.Companion companion = PlayListDetailActivity.INSTANCE;
            String str = "";
            if (movieListItem == null || (lid = movieListItem.getLid()) == null) {
                lid = "";
            }
            if (movieListItem != null && (username = movieListItem.getUsername()) != null) {
                str = username;
            }
            companion.start(context, lid, str);
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle arguments) {
        this.mClass = MovieListModel.MovieListItem.class;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        this.uid = uid_v2;
        this.keyword = arguments != null ? arguments.getString("keyword") : null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        if (item != null) {
            helper.setText(R.id.tv_num, String.valueOf(item.getCount()));
            List<String> imgArr = item.getImgArr();
            if (imgArr != null && imgArr.size() > 0) {
                Context context = getContext();
                String str = imgArr.get(0);
                View view = helper.getView(R.id.imageView);
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.ImageView");
                GlideUtils.loadWithCorner(context, str, (ImageView) view, DensityUtils.dp2px(App.getContext(), 4.0f));
            }
            helper.setText(R.id.tv_name, item.getName());
        }
    }

    /* JADX INFO: compiled from: SearchPlayListFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchPlayListFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/search/SearchPlayListFragment;", "keyword", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SearchPlayListFragment newInstance(String keyword) {
            SearchPlayListFragment searchPlayListFragment = new SearchPlayListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("keyword", keyword);
            searchPlayListFragment.setArguments(bundle);
            return searchPlayListFragment;
        }
    }
}
