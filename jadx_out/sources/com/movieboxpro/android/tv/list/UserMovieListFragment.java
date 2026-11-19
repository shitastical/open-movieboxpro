package com.movieboxpro.android.tv.list;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserMovieListFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/tv/list/UserMovieListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "", "<init>", "()V", "userUid", "isVerticalLayout", "", "gridLayoutSpan", "", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "getServiceData", "Lio/reactivex/Observable;", "initItemLayout", "initScaleValue", "", "getBundle", "", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UserMovieListFragment extends BaseListFragment<MovieListModel.MovieListItem, String> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String userUid = "";

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int gridLayoutSpan() {
        return 3;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected float initScaleValue() {
        return 1.01f;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isVerticalLayout() {
        return false;
    }

    /* JADX INFO: compiled from: UserMovieListFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/tv/list/UserMovieListFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/list/UserMovieListFragment;", "userUid", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UserMovieListFragment newInstance(String userUid) {
            Intrinsics.checkNotNullParameter(userUid, "userUid");
            UserMovieListFragment userMovieListFragment = new UserMovieListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("userUid", userUid);
            userMovieListFragment.setArguments(bundle);
            return userMovieListFragment;
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.list.UserMovieListFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                UserMovieListFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
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
    public static final void onItemClick$lambda$1(UserMovieListFragment userMovieListFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) userMovieListFragment.mAdapter.getItem(i);
        if (movieListItem != null) {
            PlayListDetailActivity.INSTANCE.start(userMovieListFragment.getContext(), movieListItem.getLid(), movieListItem.getUsername());
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        return HttpRequest.INSTANCE.post(API.Common.PLAY_LIST).param("playlist_uid", this.userUid).param("type", "user_playlists").param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).asRequest();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_movie_list_item;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle arguments) {
        String string;
        this.mClass = MovieListModel.MovieListItem.class;
        String str = "";
        if (arguments != null && (string = arguments.getString("userUid", "")) != null) {
            str = string;
        }
        this.userUid = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        CommonExtKt.textShadow$default((TextView) helper.getView(R.id.tvNum), String.valueOf(item.getCount()), 0, 0, 6, null);
        List<String> imgArr = item.getImgArr();
        if (imgArr != null && imgArr.size() > 0) {
            Context context = getContext();
            String str = imgArr.get(0);
            View view = helper.getView(R.id.ivPoster);
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.ImageView");
            GlideUtils.loadWithCorner(context, str, (ImageView) view, DensityUtils.dp2px(getContext(), 4.0f));
        }
        helper.setText(R.id.tvName, item.getName());
    }
}
