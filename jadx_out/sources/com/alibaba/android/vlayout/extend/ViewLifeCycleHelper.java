package com.alibaba.android.vlayout.extend;

import android.view.View;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public class ViewLifeCycleHelper {
    private ViewLifeCycleListener mViewLifeCycleListener;
    private HashMap<View, STATUS> mViewStatusMap = new HashMap<>();
    private VirtualLayoutManager mVirtualLayoutManager;
    private int scrHeight;

    public enum STATUS {
        APPEARING,
        APPEARED,
        DISAPPEARING,
        DISAPPEARED
    }

    public ViewLifeCycleHelper(VirtualLayoutManager virtualLayoutManager, ViewLifeCycleListener viewLifeCycleListener) {
        this.mViewLifeCycleListener = viewLifeCycleListener;
        this.mVirtualLayoutManager = virtualLayoutManager;
    }

    public void checkViewStatusInScreen() {
        for (int i = 0; i < this.mVirtualLayoutManager.getChildCount(); i++) {
            View childAt = this.mVirtualLayoutManager.getChildAt(i);
            if (this.scrHeight == 0) {
                this.scrHeight = childAt.getContext().getResources().getDisplayMetrics().heightPixels;
            }
            if (this.mVirtualLayoutManager.getVirtualLayoutDirection() == 1) {
                if (childAt.getTop() <= 0 && childAt.getBottom() >= 0 && isViewReadyDisAppearing(childAt)) {
                    setViewDisappearing(childAt);
                } else if (childAt.getTop() <= this.scrHeight && childAt.getBottom() >= this.scrHeight && isViewReadyAppearing(childAt)) {
                    setViewAppearing(childAt);
                }
            } else if (childAt.getTop() <= 0 && childAt.getBottom() >= 0 && isViewReadyAppearing(childAt)) {
                setViewAppearing(childAt);
            } else if (childAt.getTop() <= this.scrHeight && childAt.getBottom() >= this.scrHeight && isViewReadyDisAppearing(childAt)) {
                setViewDisappearing(childAt);
            }
            if (childAt.getTop() >= 0 && childAt.getBottom() <= this.scrHeight) {
                if (isViewReadyAppearing(childAt)) {
                    setViewAppearing(childAt);
                } else if (isViewReadyAppeared(childAt)) {
                    setViewAppeared(childAt);
                }
            } else if (childAt.getBottom() <= 0 || childAt.getTop() >= this.scrHeight) {
                if (isViewReadyDisAppearing(childAt)) {
                    setViewDisappearing(childAt);
                } else if (isViewReadyDisAppeared(childAt)) {
                    setViewDisappeared(childAt);
                }
            }
        }
    }

    private STATUS getViewStatus(View view) {
        if (!this.mViewStatusMap.containsKey(view)) {
            this.mViewStatusMap.put(view, STATUS.DISAPPEARED);
            return STATUS.DISAPPEARED;
        }
        return this.mViewStatusMap.get(view);
    }

    private void setViewstatus(View view, STATUS status) {
        this.mViewStatusMap.put(view, status);
    }

    private boolean isViewReadyAppearing(View view) {
        return getViewStatus(view) == STATUS.DISAPPEARED;
    }

    private void setViewAppearing(View view) {
        if (getViewStatus(view) == STATUS.APPEARING) {
            return;
        }
        setViewstatus(view, STATUS.APPEARING);
        ViewLifeCycleListener viewLifeCycleListener = this.mViewLifeCycleListener;
        if (viewLifeCycleListener != null) {
            viewLifeCycleListener.onAppearing(view);
        }
    }

    private boolean isViewReadyAppeared(View view) {
        return getViewStatus(view) == STATUS.APPEARING;
    }

    private void setViewAppeared(View view) {
        if (getViewStatus(view) == STATUS.APPEARED) {
            return;
        }
        setViewstatus(view, STATUS.APPEARED);
        ViewLifeCycleListener viewLifeCycleListener = this.mViewLifeCycleListener;
        if (viewLifeCycleListener != null) {
            viewLifeCycleListener.onAppeared(view);
        }
    }

    private boolean isViewReadyDisAppearing(View view) {
        return getViewStatus(view) == STATUS.APPEARED;
    }

    private void setViewDisappearing(View view) {
        if (getViewStatus(view) == STATUS.DISAPPEARING) {
            return;
        }
        setViewstatus(view, STATUS.DISAPPEARING);
        ViewLifeCycleListener viewLifeCycleListener = this.mViewLifeCycleListener;
        if (viewLifeCycleListener != null) {
            viewLifeCycleListener.onDisappearing(view);
        }
    }

    private boolean isViewReadyDisAppeared(View view) {
        return getViewStatus(view) == STATUS.DISAPPEARING;
    }

    private void setViewDisappeared(View view) {
        if (getViewStatus(view) == STATUS.DISAPPEARED) {
            return;
        }
        setViewstatus(view, STATUS.DISAPPEARED);
        ViewLifeCycleListener viewLifeCycleListener = this.mViewLifeCycleListener;
        if (viewLifeCycleListener != null) {
            viewLifeCycleListener.onDisappeared(view);
        }
    }
}
