package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.widget.FrameLayout;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.utils.ActivityDataBindingDelegate;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.view.fragment.WatchPlanFragment;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityWatchPlanBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: WatchPlanActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/WatchPlanActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/ActivityWatchPlanBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/ActivityWatchPlanBinding;", "binding$delegate", "Lcom/movieboxpro/android/utils/ActivityDataBindingDelegate;", "initListener", "", "initData", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WatchPlanActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(WatchPlanActivity.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/ActivityWatchPlanBinding;", 0))};

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final ActivityDataBindingDelegate binding = new ActivityDataBindingDelegate(ActivityWatchPlanBinding.class, this);

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
    }

    private final ActivityWatchPlanBinding getBinding() {
        return (ActivityWatchPlanBinding) this.binding.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        FrameLayout frameLayout = getBinding().flContainer;
        FragmentUtils.add(getSupportFragmentManager(), new WatchPlanFragment(), R.id.flContainer);
    }
}
