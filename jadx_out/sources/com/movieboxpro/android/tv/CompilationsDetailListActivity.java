package com.movieboxpro.android.tv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.tv.list.PlayListDetailActivity;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: CompilationsDetailListActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0002\b\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/tv/CompilationsDetailListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "CompilationsDetailListFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CompilationsDetailListActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: CompilationsDetailListActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/tv/CompilationsDetailListActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String id) {
            Intent intent = new Intent(context, (Class<?>) CompilationsDetailListActivity.class);
            intent.putExtra("id", id);
            if (context != null) {
                context.startActivity(intent);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_movie);
        FragmentUtils.add(getSupportFragmentManager(), CompilationsDetailListFragment.INSTANCE.newInstance(getIntent().getStringExtra("id")), R.id.frameLayout);
    }

    /* JADX INFO: compiled from: CompilationsDetailListActivity.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0002H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/tv/CompilationsDetailListActivity$CompilationsDetailListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "", "<init>", "()V", "id", "getServiceData", "Lio/reactivex/Observable;", "initItemLayout", "", "getBundle", "", "arguments", "Landroid/os/Bundle;", "gridLayoutSpan", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "isOpenLoadMore", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initScaleValue", "", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CompilationsDetailListFragment extends BaseListFragment<MovieListModel.MovieListItem, String> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String id = "";

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            return 2;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected float initScaleValue() {
            return 1.08f;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isOpenLoadMore() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            Observable<String> observablePlaylists_compilations_detail = Http.getService().Playlists_compilations_detail("Playlists_compilations_detail", this.id, this.mCurrentPage, this.mPageSize);
            Intrinsics.checkNotNullExpressionValue(observablePlaylists_compilations_detail, "Playlists_compilations_detail(...)");
            return observablePlaylists_compilations_detail;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_compilations_list_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            this.mClass = MovieListModel.MovieListItem.class;
            this.id = arguments != null ? arguments.getString("id") : null;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.CompilationsDetailListActivity$CompilationsDetailListFragment$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    CompilationsDetailListActivity.CompilationsDetailListFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
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
        public static final void onItemClick$lambda$1(CompilationsDetailListFragment compilationsDetailListFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) compilationsDetailListFragment.mAdapter.getItem(i);
            if (movieListItem != null) {
                PlayListDetailActivity.INSTANCE.start(compilationsDetailListFragment.getContext(), movieListItem.getLid(), movieListItem.getUsername());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ImageView imageView = (ImageView) helper.getView(R.id.imageView);
            TextView textView = (TextView) helper.getView(R.id.tvName);
            if (item.getImgArr() != null) {
                List<String> imgArr = item.getImgArr();
                Intrinsics.checkNotNullExpressionValue(imgArr, "getImgArr(...)");
                if (!imgArr.isEmpty()) {
                    GlideUtils.loadWithCorner(getContext(), item.getImgArr().get(0), imageView, DensityUtils.dp2px(App.getContext(), 4.0f));
                }
            }
            textView.setText(item.getName());
            TextView textView2 = (TextView) helper.getView(R.id.tvMovieNames);
            List<String> movieArr = item.getMovieArr();
            SpanUtils spanUtilsWith = SpanUtils.with(textView2);
            List<String> list = movieArr;
            if (list != null && !list.isEmpty()) {
                int i = 0;
                for (Object obj : movieArr) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format("%s.%s", Arrays.copyOf(new Object[]{Integer.valueOf(i2), (String) obj}, 2));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    spanUtilsWith.append(str).append("\n\n");
                    i = i2;
                }
            }
            spanUtilsWith.setBold().setFontSize(16, true).create();
        }

        /* JADX INFO: compiled from: CompilationsDetailListActivity.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/tv/CompilationsDetailListActivity$CompilationsDetailListFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/CompilationsDetailListActivity$CompilationsDetailListFragment;", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final CompilationsDetailListFragment newInstance(String id) {
                CompilationsDetailListFragment compilationsDetailListFragment = new CompilationsDetailListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                compilationsDetailListFragment.setArguments(bundle);
                return compilationsDetailListFragment;
            }
        }
    }
}
