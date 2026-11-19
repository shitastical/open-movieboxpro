package com.movieboxpro.android.tv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.tv.list.PlayListDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MorePlayListActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0002\b\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/tv/MorePlayListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "MorePlayListFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MorePlayListActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: MorePlayListActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/tv/MorePlayListActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "type", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String type) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(type, "type");
            Intent intent = new Intent(context, (Class<?>) MorePlayListActivity.class);
            intent.putExtra("type", type);
            context.startActivity(intent);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_play_list);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        MorePlayListFragment.Companion companion = MorePlayListFragment.INSTANCE;
        String stringExtra = getIntent().getStringExtra("type");
        if (stringExtra == null) {
            stringExtra = "";
        }
        FragmentUtils.add(supportFragmentManager, companion.newInstance(stringExtra), R.id.frameLayout);
    }

    /* JADX INFO: compiled from: MorePlayListActivity.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0014J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/tv/MorePlayListActivity$MorePlayListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "Lcom/movieboxpro/android/model/movie/MovieListModel;", "<init>", "()V", "uid", "", "type", "getServiceData", "Lio/reactivex/Observable;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "gridLayoutSpan", "", "isOpenLoadMore", "", "initItemLayout", "getBundle", "", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MorePlayListFragment extends BaseListFragment<MovieListModel.MovieListItem, MovieListModel> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String type;
        private String uid;

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            return 3;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isOpenLoadMore() {
            return true;
        }

        /* JADX INFO: compiled from: MorePlayListActivity.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/tv/MorePlayListActivity$MorePlayListFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/MorePlayListActivity$MorePlayListFragment;", "type", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final MorePlayListFragment newInstance(String type) {
                Intrinsics.checkNotNullParameter(type, "type");
                MorePlayListFragment morePlayListFragment = new MorePlayListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", type);
                morePlayListFragment.setArguments(bundle);
                return morePlayListFragment;
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            String str;
            String str2;
            APIService service = Http.getService();
            String str3 = this.uid;
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uid");
                str = null;
            } else {
                str = str3;
            }
            String str4 = this.type;
            if (str4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("type");
                str2 = null;
            } else {
                str2 = str4;
            }
            Observable<String> observablePlaylists_list = service.Playlists_list(API.Common.PLAY_LIST, str, str2, this.mCurrentPage, this.mPageSize, BuildConfig.VERSION_NAME);
            Intrinsics.checkNotNullExpressionValue(observablePlaylists_list, "Playlists_list(...)");
            return observablePlaylists_list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.MorePlayListActivity$MorePlayListFragment$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MorePlayListActivity.MorePlayListFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onItemClick$lambda$1(MorePlayListFragment morePlayListFragment, BaseQuickAdapter adapter, View view, int i) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            Object item = adapter.getItem(i);
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.movie.MovieListModel.MovieListItem");
            MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) item;
            Context context = morePlayListFragment.getContext();
            if (context != null) {
                PlayListDetailActivity.INSTANCE.start(context, movieListItem.getLid(), movieListItem.getUsername());
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_play_list_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            String string;
            this.mClass = MovieListModel.MovieListItem.class;
            String uid_v2 = App.getUserData().uid_v2;
            Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
            this.uid = uid_v2;
            if (arguments == null || (string = arguments.getString("type")) == null) {
                string = "";
            }
            this.type = string;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            CommonExtKt.textShadow$default((TextView) helper.getView(R.id.tv_num), String.valueOf(item.getCount()), 0, 0, 6, null);
            List<String> imgArr = item.getImgArr();
            if (imgArr != null && imgArr.size() > 0) {
                Context context = getContext();
                String str = imgArr.get(0);
                View view = helper.getView(R.id.imageView);
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.ImageView");
                GlideUtils.loadWithCorner(context, str, (ImageView) view, DensityUtils.dp2px(getContext(), 4.0f));
            }
            helper.setText(R.id.tv_name, item.getName());
        }
    }
}
