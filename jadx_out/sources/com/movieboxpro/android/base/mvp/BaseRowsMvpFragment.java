package com.movieboxpro.android.base.mvp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.leanback.app.RowsSupportFragment;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseContract.BasePresenter;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.androidtv.R;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseRowsMvpFragment<T extends BaseContract.BasePresenter> extends RowsSupportFragment implements BaseContract.BaseView {
    protected KProgressHUD hub;
    protected boolean isVisibleToUser;
    protected T mPresenter;
    private boolean mIsFirstVisible = true;
    private boolean isViewCreated = false;
    private boolean currentVisibleState = false;

    protected abstract T bindPresenter();

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
    }

    protected int initBackgroundColor() {
        return -1;
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
    public void showLoading() {
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

    @Override // androidx.leanback.app.RowsSupportFragment, androidx.leanback.app.BaseRowSupportFragment, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (initBackgroundColor() != -1) {
            view.setBackgroundColor(initBackgroundColor());
        } else if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.DARK_MODE, false)) {
            view.setBackgroundColor(ContextCompat.getColor(App.getContext(), R.color.black));
        } else {
            view.setBackgroundColor(Color.parseColor("#FF1D1D1D"));
        }
        if (getContext() != null) {
            this.hub = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f);
        }
        initData();
        initView();
        initListener();
        loadData();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
        try {
            KProgressHUD kProgressHUD = this.hub;
            if (kProgressHUD != null) {
                kProgressHUD.show();
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
        KProgressHUD kProgressHUD = this.hub;
        if (kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isViewCreated) {
            if (isVisibleToUser && !this.currentVisibleState) {
                dispatchUserVisibleHint(true);
            } else {
                if (isVisibleToUser || !this.currentVisibleState) {
                    return;
                }
                dispatchUserVisibleHint(false);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.isViewCreated = true;
        if (isHidden() || !getUserVisibleHint()) {
            return;
        }
        dispatchUserVisibleHint(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            dispatchUserVisibleHint(false);
        } else {
            dispatchUserVisibleHint(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mIsFirstVisible || isHidden() || this.currentVisibleState || !getUserVisibleHint()) {
            return;
        }
        dispatchUserVisibleHint(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.currentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
    }

    private void dispatchUserVisibleHint(boolean visible) {
        if ((visible && isParentInvisible()) || this.currentVisibleState == visible) {
            return;
        }
        this.currentVisibleState = visible;
        if (visible) {
            if (this.mIsFirstVisible) {
                this.mIsFirstVisible = false;
                onFragmentFirstVisible();
            } else {
                onFragmentResumeNoFirst();
            }
            onFragmentResume();
            dispatchChildVisibleState(true);
            return;
        }
        dispatchChildVisibleState(false);
        onFragmentPause();
    }

    private boolean isParentInvisible() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof BaseRowsMvpFragment) {
            return !((BaseRowsMvpFragment) parentFragment).isSupportVisible();
        }
        return false;
    }

    private boolean isSupportVisible() {
        return this.currentVisibleState;
    }

    private void dispatchChildVisibleState(boolean visible) {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment fragment : fragments) {
            if ((fragment instanceof BaseRowsMvpFragment) && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                BaseRowsMvpFragment baseRowsMvpFragment = (BaseRowsMvpFragment) fragment;
                if (baseRowsMvpFragment.isViewCreated) {
                    baseRowsMvpFragment.dispatchUserVisibleHint(visible);
                }
            }
        }
    }

    public void onFragmentFirstVisible() {
        this.isVisibleToUser = true;
    }

    public void onFragmentResume() {
        this.isVisibleToUser = true;
    }

    public void onFragmentPause() {
        this.isVisibleToUser = false;
    }

    public void onFragmentResumeNoFirst() {
        this.isVisibleToUser = true;
    }

    @Override // androidx.leanback.app.RowsSupportFragment, androidx.leanback.app.BaseRowSupportFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.isViewCreated = false;
        this.mIsFirstVisible = true;
        this.isVisibleToUser = false;
    }
}
