package com.movieboxpro.android.tv.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.utils.ActivityDataBindingDelegate;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityFrameLayoutBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: UserMovieListActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/tv/list/UserMovieListActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/ActivityFrameLayoutBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/ActivityFrameLayoutBinding;", "binding$delegate", "Lcom/movieboxpro/android/utils/ActivityDataBindingDelegate;", "initListener", "", "initData", "initView", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UserMovieListActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(UserMovieListActivity.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/ActivityFrameLayoutBinding;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final ActivityDataBindingDelegate binding = new ActivityDataBindingDelegate(ActivityFrameLayoutBinding.class, this);

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
    }

    private final ActivityFrameLayoutBinding getBinding() {
        return (ActivityFrameLayoutBinding) this.binding.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        FrameLayout frameLayout = getBinding().frameLayout;
        String stringExtra = getIntent().getStringExtra("uid");
        if (stringExtra == null) {
            stringExtra = "";
        }
        FragmentUtils.add(getSupportFragmentManager(), UserMovieListFragment.INSTANCE.newInstance(stringExtra), R.id.frameLayout);
    }

    /* JADX INFO: compiled from: UserMovieListActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/tv/list/UserMovieListActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "uid", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String uid) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uid, "uid");
            Intent intent = new Intent(context, (Class<?>) UserMovieListActivity.class);
            intent.putExtra("uid", uid);
            context.startActivity(intent);
        }
    }
}
