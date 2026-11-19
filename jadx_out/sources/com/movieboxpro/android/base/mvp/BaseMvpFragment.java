package com.movieboxpro.android.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.viewbinding.ViewBinding;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.base.mvp.BaseContract.BasePresenter;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseMvpFragment<T extends BaseContract.BasePresenter, VB extends ViewBinding> extends BaseLazyFragment implements BaseContract.BaseView {
    protected VB binding;
    private KProgressHUD hud;
    protected T mPresenter;

    protected abstract int bindLayout();

    protected abstract T bindPresenter();

    protected boolean enableEventBus() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
    }

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract void loadData();

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showEmptyState() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showError(String message) {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showErrorPage() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showFinish(String msg) {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        T t = (T) bindPresenter();
        this.mPresenter = t;
        if (t != null) {
            t.attachView(this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(bindLayout(), container, false);
        this.binding = DataBindingUtil.bind(viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        initListener();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentFirstVisible() {
        loadData();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            KProgressHUD kProgressHUD2 = this.hud;
            if (kProgressHUD2 == null || !kProgressHUD2.isShowing()) {
                this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
            }
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            return;
        }
        this.hud.dismiss();
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
        T t = this.mPresenter;
        if (t != null) {
            t.detachView();
        }
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null && kProgressHUD.isShowing()) {
            this.hud.dismiss();
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }
}
