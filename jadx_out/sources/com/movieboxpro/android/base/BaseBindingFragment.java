package com.movieboxpro.android.base;

import android.os.Bundle;
import android.view.View;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.view.listener.LoadView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BaseBindingFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH&J\b\u0010\u0010\u001a\u00020\nH&J\b\u0010\u0011\u001a\u00020\nH&J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\b\u0010\u0016\u001a\u00020\nH\u0016J\b\u0010\u0017\u001a\u00020\nH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/base/BaseBindingFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "Lcom/movieboxpro/android/view/listener/LoadView;", "layoutId", "", "<init>", "(I)V", "hud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "initView", "initData", "enableEventBus", "", "showLoadingView", "hideLoadingView", "onStart", "onDestroy", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseBindingFragment extends BaseLazyFragment implements LoadView {
    private KProgressHUD hud;

    protected boolean enableEventBus() {
        return false;
    }

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    public BaseBindingFragment(int i) {
        super(i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        initListener();
    }

    @Override // com.movieboxpro.android.view.listener.LoadView
    public void showLoadingView() {
        if (getContext() != null) {
            KProgressHUD kProgressHUD = this.hud;
            if (kProgressHUD != null) {
                Intrinsics.checkNotNull(kProgressHUD);
                if (kProgressHUD.isShowing()) {
                    return;
                }
            }
            KProgressHUD kProgressHUD2 = this.hud;
            if (kProgressHUD2 != null) {
                Intrinsics.checkNotNull(kProgressHUD2);
                if (kProgressHUD2.isShowing()) {
                    return;
                }
            }
            this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
        }
    }

    @Override // com.movieboxpro.android.view.listener.LoadView
    public void hideLoadingView() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null) {
            Intrinsics.checkNotNull(kProgressHUD);
            kProgressHUD.dismiss();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (!enableEventBus() || EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (enableEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}
