package com.movieboxpro.android.base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseLazyFragment extends Fragment {
    private boolean currentVisibleState;
    private boolean isViewCreated;
    protected boolean isVisibleToUser;
    private boolean mIsFirstVisible;

    public BaseLazyFragment(int layoutId) {
        super(layoutId);
        this.mIsFirstVisible = true;
        this.isViewCreated = false;
        this.currentVisibleState = false;
    }

    public BaseLazyFragment() {
        this.mIsFirstVisible = true;
        this.isViewCreated = false;
        this.currentVisibleState = false;
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
        if (parentFragment instanceof BaseLazyFragment) {
            return !((BaseLazyFragment) parentFragment).isSupportVisible();
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
            if ((fragment instanceof BaseLazyFragment) && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                BaseLazyFragment baseLazyFragment = (BaseLazyFragment) fragment;
                if (baseLazyFragment.isViewCreated) {
                    baseLazyFragment.dispatchUserVisibleHint(visible);
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

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.isViewCreated = false;
        this.mIsFirstVisible = true;
        this.isVisibleToUser = false;
    }
}
