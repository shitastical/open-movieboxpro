package com.movieboxpro.android.view.fragment.home;

import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.movieboxpro.android.base.BaseBindingFragment;
import com.movieboxpro.android.listener.RefreshListener;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentHomeBinding;
import com.snowtop.diskpanda.utils.databinding.FragmentBindingDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: HomeFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\rH\u0016J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\rJ\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/HomeFragment;", "Lcom/movieboxpro/android/base/BaseBindingFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentHomeBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/FragmentHomeBinding;", "binding$delegate", "Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;", "featuredFragment", "Lcom/movieboxpro/android/view/fragment/home/FeaturedFragment;", "refreshData", "", "initListener", "isErrorShowing", "", "loadingViewFocus", "initView", "initData", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HomeFragment extends BaseBindingFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(HomeFragment.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentHomeBinding;", 0))};

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentBindingDelegate binding;
    private FeaturedFragment featuredFragment;

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initView() {
    }

    public HomeFragment() {
        super(R.layout.fragment_home);
        this.binding = new FragmentBindingDelegate(FragmentHomeBinding.class, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentHomeBinding getBinding() {
        return (FragmentHomeBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    public final void refreshData() {
        FeaturedFragment featuredFragment = this.featuredFragment;
        if (featuredFragment != null) {
            featuredFragment.refreshData();
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initListener() {
        getBinding().loadingView.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.home.HomeFragment$$ExternalSyntheticLambda0
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                HomeFragment.initListener$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(HomeFragment homeFragment) {
        FeaturedFragment featuredFragment = homeFragment.featuredFragment;
        if (featuredFragment != null) {
            featuredFragment.refreshData();
        }
    }

    public final boolean isErrorShowing() {
        return getBinding().loadingView.getVisibility() == 0;
    }

    public final void loadingViewFocus() {
        getBinding().loadingView.requestFocus();
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initData() {
        FrameLayout frameLayout = getBinding().frameLayout;
        FeaturedFragment featuredFragment = new FeaturedFragment();
        this.featuredFragment = featuredFragment;
        Intrinsics.checkNotNull(featuredFragment);
        featuredFragment.setRefreshListener(new RefreshListener() { // from class: com.movieboxpro.android.view.fragment.home.HomeFragment.initData.1
            @Override // com.movieboxpro.android.listener.RefreshListener
            public void startRefresh() {
                HomeFragment.this.getBinding().loadingView.showLoading();
            }

            @Override // com.movieboxpro.android.listener.RefreshListener
            public void endRefresh() {
                HomeFragment.this.getBinding().loadingView.hideLoading();
            }

            @Override // com.movieboxpro.android.listener.RefreshListener
            public void showError(String msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                HomeFragment.this.getBinding().loadingView.showError(msg);
                HomeFragment.this.loadingViewFocus();
            }
        });
        FragmentManager childFragmentManager = getChildFragmentManager();
        FeaturedFragment featuredFragment2 = this.featuredFragment;
        Intrinsics.checkNotNull(featuredFragment2);
        FragmentUtils.add(childFragmentManager, featuredFragment2, R.id.frameLayout);
    }
}
