package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.ExoPlayer;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.tv.list.PlayListDetailActivity;
import com.movieboxpro.android.utils.ActivityDataBindingDelegate;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivitySuperChildModeBinding;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: SuperChildModeActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/activity/SuperChildModeActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/ActivitySuperChildModeBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/ActivitySuperChildModeBinding;", "binding$delegate", "Lcom/movieboxpro/android/utils/ActivityDataBindingDelegate;", "initListener", "", "initData", "initView", "mExitTime", "", "onKeyDown", "", "keyCode", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "MovieListFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SuperChildModeActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(SuperChildModeActivity.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/ActivitySuperChildModeBinding;", 0))};

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final ActivityDataBindingDelegate binding = new ActivityDataBindingDelegate(ActivitySuperChildModeBinding.class, this);
    private long mExitTime;

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
    }

    private final ActivitySuperChildModeBinding getBinding() {
        return (ActivitySuperChildModeBinding) this.binding.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        FrameLayout frameLayout = getBinding().flContainer;
        FragmentUtils.add(getSupportFragmentManager(), new MovieListFragment(), R.id.flContainer);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (keyCode == 4) {
            if (System.currentTimeMillis() - this.mExitTime > ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS) {
                ToastUtils.showShort("Press again to quit", new Object[0]);
                this.mExitTime = System.currentTimeMillis();
                return true;
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /* JADX INFO: compiled from: SuperChildModeActivity.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u001a\u0010\u0010\u001a\u00020\u00112\u0010\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010\u0007H\u0014J\b\u0010\u0014\u001a\u00020\nH\u0014J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0002H\u0014¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/view/activity/SuperChildModeActivity$MovieListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "Lcom/movieboxpro/android/model/movie/MovieListModel;", "<init>", "()V", "getData", "", "model", "gridLayoutSpan", "", "getServiceData", "Lio/reactivex/Observable;", "", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onLoadDataComplete", "", "data", "", "initItemLayout", "getBundle", "arguments", "Landroid/os/Bundle;", "initScaleValue", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MovieListFragment extends BaseListFragment<MovieListModel.MovieListItem, MovieListModel> {
        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            return 3;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected float initScaleValue() {
            return 1.1f;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<MovieListModel.MovieListItem> getData(MovieListModel model) {
            Intrinsics.checkNotNullParameter(model, "model");
            List<MovieListModel.MovieListItem> list = model.getList();
            return list == null ? new ArrayList() : list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.INSTANCE.post("Playlists_super_child_list").param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.SuperChildModeActivity$MovieListFragment$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    SuperChildModeActivity.MovieListFragment.onItemClick$lambda$0(this.f$0, baseQuickAdapter, view, i);
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
        public static final void onItemClick$lambda$0(MovieListFragment movieListFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) movieListFragment.mAdapter.getItem(i);
            PlayListDetailActivity.INSTANCE.start(movieListFragment.getContext(), movieListItem.getLid(), movieListItem.getUsername());
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void onLoadDataComplete(List<Object> data) {
            if (this.mCurrentPage == 1) {
                this.mRecyclerView.requestFocus();
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_super_movie_list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            this.mClass = MovieListModel.MovieListItem.class;
            this.mPageClass = MovieListModel.class;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            CommonExtKt.textShadow$default((TextView) helper.getView(R.id.tv_num), String.valueOf(item.getCount()), 0, 0, 6, null);
            List<String> imgArr = item.getImgArr();
            if (imgArr != null && imgArr.size() > 0) {
                GlideUtils.loadCornerLandHolder(getContext(), imgArr.get(0), (ImageView) helper.getView(R.id.imageView), 8);
            }
            helper.setText(R.id.tv_name, item.getName());
        }
    }
}
