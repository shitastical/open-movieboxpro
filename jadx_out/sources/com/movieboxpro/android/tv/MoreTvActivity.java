package com.movieboxpro.android.tv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.FragmentManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoreTvActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0002\b\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/tv/MoreTvActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "MoreTvListFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MoreTvActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: MoreTvActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/tv/MoreTvActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "type", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String type) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(type, "type");
            Intent intent = new Intent(context, (Class<?>) MoreTvActivity.class);
            intent.putExtra("type", type);
            context.startActivity(intent);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_tv);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        MoreTvListFragment.Companion companion = MoreTvListFragment.INSTANCE;
        String stringExtra = getIntent().getStringExtra("type");
        if (stringExtra == null) {
            stringExtra = "";
        }
        FragmentUtils.add(supportFragmentManager, companion.newInstance(stringExtra), R.id.frameLayout);
    }

    /* JADX INFO: compiled from: MoreTvActivity.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0014J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u000fH\u0014J\u0012\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u001c\u0010\u0017\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/tv/MoreTvActivity$MoreTvListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "", "<init>", "()V", "uid", "type", "getServiceData", "Lio/reactivex/Observable;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onFocus", "", "position", "", "onLastFocus", "isOpenLoadMore", "", "initItemLayout", "getBundle", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MoreTvListFragment extends BaseListFragment<Homelist.Typelist, String> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String type;
        private String uid;

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isOpenLoadMore() {
            return true;
        }

        /* JADX INFO: compiled from: MoreTvActivity.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/tv/MoreTvActivity$MoreTvListFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/MoreTvActivity$MoreTvListFragment;", "type", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final MoreTvListFragment newInstance(String type) {
                Intrinsics.checkNotNullParameter(type, "type");
                MoreTvListFragment moreTvListFragment = new MoreTvListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", type);
                moreTvListFragment.setArguments(bundle);
                return moreTvListFragment;
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
            Observable<String> observableHome_list = service.Home_list(API.Common.HOME_LIST, str, str2, PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false) ? 1 : 0, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), BuildConfig.VERSION_NAME);
            Intrinsics.checkNotNullExpressionValue(observableHome_list, "Home_list(...)");
            return observableHome_list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.MoreTvActivity$MoreTvListFragment$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MoreTvActivity.MoreTvListFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onItemClick$lambda$1(MoreTvListFragment moreTvListFragment, BaseQuickAdapter adapter, View view, int i) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            Object item = adapter.getItem(i);
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.common.Homelist.Typelist");
            Homelist.Typelist typelist = (Homelist.Typelist) item;
            Context context = moreTvListFragment.getContext();
            if (context != null) {
                TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
                String id = typelist.id;
                Intrinsics.checkNotNullExpressionValue(id, "id");
                String poster = typelist.poster;
                Intrinsics.checkNotNullExpressionValue(poster, "poster");
                companion.starter(context, id, poster);
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
        protected int initItemLayout() {
            return R.layout.layout_tvlist_item_2;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            String string;
            this.mClass = Homelist.Typelist.class;
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
        public void initHolder(BaseViewHolder helper, Homelist.Typelist item) {
            if (helper != null) {
                helper.setText(R.id.tvTitle, item != null ? item.title : null);
            }
            ImageView imageView = helper != null ? (ImageView) helper.getView(R.id.tv_item_poster) : null;
            TextView textView = helper != null ? (TextView) helper.getView(R.id.tv_item_season) : null;
            Group group = helper != null ? (Group) helper.getView(R.id.group) : null;
            SlantedTextView slantedTextView = helper != null ? (SlantedTextView) helper.getView(R.id.slv_right_text) : null;
            if (textView != null) {
                textView.setText(item != null ? item.season_episode : null);
            }
            if ((item != null ? item.update_title : null) != null && !TextUtils.isEmpty(item.update_title)) {
                if (group != null) {
                    CommonExtKt.visible(group);
                }
                if (slantedTextView != null) {
                    slantedTextView.setText(item.update_title);
                }
            } else if (group != null) {
                CommonExtKt.gone(group);
            }
            if (imageView != null) {
                if (Intrinsics.areEqual(item != null ? item.id : null, "0")) {
                    if (textView != null) {
                        CommonExtKt.gone(textView);
                    }
                    GlideUtils.load(getContext(), R.drawable.ic_movie_more, imageView);
                    return;
                }
                GlideUtils.load(getContext(), item != null ? item.poster : null, imageView, R.drawable.ic_default);
            }
        }
    }
}
