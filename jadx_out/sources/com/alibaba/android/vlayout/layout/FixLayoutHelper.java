package com.alibaba.android.vlayout.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.OrientationHelperEx;
import com.alibaba.android.vlayout.VirtualLayoutManager;

/* JADX INFO: loaded from: classes4.dex */
public class FixLayoutHelper extends FixAreaLayoutHelper {
    public static final int BOTTOM_LEFT = 2;
    public static final int BOTTOM_RIGHT = 3;
    private static final String TAG = "FixLayoutHelper";
    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT = 1;
    private boolean isAddFixViewImmediately;
    private boolean isRemoveFixViewImmediately;
    private int mAlignType;
    protected boolean mDoNormalHandle;
    protected View mFixView;
    private FixViewAppearAnimatorListener mFixViewAppearAnimatorListener;
    private FixViewDisappearAnimatorListener mFixViewDisappearAnimatorListener;
    private int mPos;
    private boolean mShouldDrawn;
    private boolean mSketchMeasure;
    protected int mX;
    protected int mY;

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public boolean requireLayoutView() {
        return false;
    }

    @Override // com.alibaba.android.vlayout.layout.MarginLayoutHelper
    public void setMargin(int i, int i2, int i3, int i4) {
    }

    protected boolean shouldBeDraw(LayoutManagerHelper layoutManagerHelper, int i, int i2, int i3) {
        return true;
    }

    public FixLayoutHelper(int i, int i2) {
        this(0, i, i2);
    }

    public FixLayoutHelper(int i, int i2, int i3) {
        this.mPos = -1;
        this.mAlignType = 0;
        this.mX = 0;
        this.mY = 0;
        this.mSketchMeasure = false;
        this.mFixView = null;
        this.mDoNormalHandle = false;
        this.mShouldDrawn = true;
        this.isAddFixViewImmediately = false;
        this.isRemoveFixViewImmediately = true;
        this.mFixViewAppearAnimatorListener = new FixViewAppearAnimatorListener();
        this.mFixViewDisappearAnimatorListener = new FixViewDisappearAnimatorListener();
        this.mAlignType = i;
        this.mX = i2;
        this.mY = i3;
        setItemCount(1);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void setItemCount(int i) {
        if (i > 0) {
            super.setItemCount(1);
        } else {
            super.setItemCount(0);
        }
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setAlignType(int i) {
        this.mAlignType = i;
    }

    public void setSketchMeasure(boolean z) {
        this.mSketchMeasure = z;
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRangeChange(int i, int i2) {
        this.mPos = i;
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void layoutViews(RecyclerView.Recycler recycler, RecyclerView.State state, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper) {
        if (isOutOfRange(layoutStateWrapper.getCurrentPosition())) {
            return;
        }
        if (!this.mShouldDrawn) {
            layoutStateWrapper.skipCurrentPosition();
            return;
        }
        View next = this.mFixView;
        if (next == null) {
            next = layoutStateWrapper.next(recycler);
        } else {
            layoutStateWrapper.skipCurrentPosition();
        }
        if (next == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        boolean zIsPreLayout = state.isPreLayout();
        this.mDoNormalHandle = zIsPreLayout;
        if (zIsPreLayout) {
            layoutManagerHelper.addChildView(layoutStateWrapper, next);
        }
        this.mFixView = next;
        doMeasureAndLayout(next, layoutManagerHelper);
        layoutChunkResult.mConsumed = 0;
        layoutChunkResult.mIgnoreConsumed = true;
        handleStateOnResult(layoutChunkResult, next);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void beforeLayout(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutManagerHelper layoutManagerHelper) {
        super.beforeLayout(recycler, state, layoutManagerHelper);
        View view = this.mFixView;
        if (view != null && layoutManagerHelper.isViewHolderUpdated(view)) {
            layoutManagerHelper.removeChildView(this.mFixView);
            recycler.recycleView(this.mFixView);
            this.mFixView = null;
            this.isAddFixViewImmediately = true;
        }
        this.mDoNormalHandle = false;
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void afterLayout(final RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, int i3, final LayoutManagerHelper layoutManagerHelper) {
        super.afterLayout(recycler, state, i, i2, i3, layoutManagerHelper);
        if (this.mPos < 0) {
            return;
        }
        if (this.mDoNormalHandle && state.isPreLayout()) {
            View view = this.mFixView;
            if (view != null) {
                layoutManagerHelper.removeChildView(view);
                recycler.recycleView(this.mFixView);
                this.isAddFixViewImmediately = false;
            }
            this.mFixView = null;
            return;
        }
        if (shouldBeDraw(layoutManagerHelper, i, i2, i3)) {
            this.mShouldDrawn = true;
            View view2 = this.mFixView;
            if (view2 != null) {
                if (view2.getParent() == null) {
                    addFixViewWithAnimator(layoutManagerHelper, this.mFixView);
                    return;
                } else {
                    layoutManagerHelper.addFixedView(this.mFixView);
                    this.isRemoveFixViewImmediately = false;
                    return;
                }
            }
            Runnable runnable = new Runnable() { // from class: com.alibaba.android.vlayout.layout.FixLayoutHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    FixLayoutHelper fixLayoutHelper = FixLayoutHelper.this;
                    fixLayoutHelper.mFixView = recycler.getViewForPosition(fixLayoutHelper.mPos);
                    FixLayoutHelper fixLayoutHelper2 = FixLayoutHelper.this;
                    fixLayoutHelper2.doMeasureAndLayout(fixLayoutHelper2.mFixView, layoutManagerHelper);
                    if (FixLayoutHelper.this.isAddFixViewImmediately) {
                        layoutManagerHelper.addFixedView(FixLayoutHelper.this.mFixView);
                        FixLayoutHelper.this.isRemoveFixViewImmediately = false;
                    } else {
                        FixLayoutHelper fixLayoutHelper3 = FixLayoutHelper.this;
                        fixLayoutHelper3.addFixViewWithAnimator(layoutManagerHelper, fixLayoutHelper3.mFixView);
                    }
                }
            };
            if (this.mFixViewDisappearAnimatorListener.isAnimating()) {
                this.mFixViewDisappearAnimatorListener.withEndAction(runnable);
                return;
            } else {
                runnable.run();
                return;
            }
        }
        this.mShouldDrawn = false;
        View view3 = this.mFixView;
        if (view3 != null) {
            removeFixViewWithAnimator(recycler, layoutManagerHelper, view3);
            this.mFixView = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFixViewWithAnimator(LayoutManagerHelper layoutManagerHelper, View view) {
        ViewPropertyAnimator viewPropertyAnimatorOnGetFixViewAppearAnimator;
        if (this.mFixViewAnimatorHelper != null && (viewPropertyAnimatorOnGetFixViewAppearAnimator = this.mFixViewAnimatorHelper.onGetFixViewAppearAnimator(view)) != null) {
            view.setVisibility(4);
            layoutManagerHelper.addFixedView(view);
            this.mFixViewAppearAnimatorListener.bindAction(layoutManagerHelper, view);
            viewPropertyAnimatorOnGetFixViewAppearAnimator.setListener(this.mFixViewAppearAnimatorListener).start();
        } else {
            layoutManagerHelper.addFixedView(view);
        }
        this.isRemoveFixViewImmediately = false;
    }

    private void removeFixViewWithAnimator(RecyclerView.Recycler recycler, LayoutManagerHelper layoutManagerHelper, View view) {
        if (!this.isRemoveFixViewImmediately && this.mFixViewAnimatorHelper != null) {
            ViewPropertyAnimator viewPropertyAnimatorOnGetFixViewDisappearAnimator = this.mFixViewAnimatorHelper.onGetFixViewDisappearAnimator(view);
            if (viewPropertyAnimatorOnGetFixViewDisappearAnimator != null) {
                this.mFixViewDisappearAnimatorListener.bindAction(recycler, layoutManagerHelper, view);
                viewPropertyAnimatorOnGetFixViewDisappearAnimator.setListener(this.mFixViewDisappearAnimatorListener).start();
                this.isAddFixViewImmediately = false;
                return;
            } else {
                layoutManagerHelper.removeChildView(view);
                recycler.recycleView(view);
                this.isAddFixViewImmediately = false;
                return;
            }
        }
        layoutManagerHelper.removeChildView(view);
        recycler.recycleView(view);
        this.isAddFixViewImmediately = false;
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public View getFixedView() {
        return this.mFixView;
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void onClear(LayoutManagerHelper layoutManagerHelper) {
        super.onClear(layoutManagerHelper);
        View view = this.mFixView;
        if (view != null) {
            layoutManagerHelper.removeChildView(view);
            layoutManagerHelper.recycleView(this.mFixView);
            this.mFixView.animate().cancel();
            this.mFixView = null;
            this.isAddFixViewImmediately = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doMeasureAndLayout(View view, LayoutManagerHelper layoutManagerHelper) {
        int i;
        int childMeasureSpec;
        int decoratedMeasurement;
        int decoratedMeasurementInOther;
        int i2;
        int i3;
        int i4;
        int i5;
        int contentWidth;
        int contentHeight;
        int measuredWidth;
        int measuredHeight;
        int i6;
        int paddingTop;
        int i7;
        int childMeasureSpec2;
        if (view == null || layoutManagerHelper == null) {
            return;
        }
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        boolean z = layoutManagerHelper.getOrientation() == 1;
        int i8 = -1;
        if (z) {
            int contentWidth2 = (layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingLeft()) - layoutManagerHelper.getPaddingRight();
            if (layoutParams.width >= 0) {
                i7 = layoutParams.width;
            } else {
                i7 = (this.mSketchMeasure && z) ? -1 : -2;
            }
            int childMeasureSpec3 = layoutManagerHelper.getChildMeasureSpec(contentWidth2, i7, false);
            if (!Float.isNaN(layoutParams.mAspectRatio) && layoutParams.mAspectRatio > 0.0f) {
                childMeasureSpec2 = layoutManagerHelper.getChildMeasureSpec((layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingTop()) - layoutManagerHelper.getPaddingBottom(), (int) ((View.MeasureSpec.getSize(childMeasureSpec3) / layoutParams.mAspectRatio) + 0.5f), false);
            } else if (!Float.isNaN(this.mAspectRatio) && this.mAspectRatio > 0.0f) {
                childMeasureSpec2 = layoutManagerHelper.getChildMeasureSpec((layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingTop()) - layoutManagerHelper.getPaddingBottom(), (int) ((View.MeasureSpec.getSize(childMeasureSpec3) / this.mAspectRatio) + 0.5f), false);
            } else {
                int contentHeight2 = (layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingTop()) - layoutManagerHelper.getPaddingBottom();
                if (layoutParams.height >= 0) {
                    i8 = layoutParams.height;
                } else if (!this.mSketchMeasure || z) {
                    i8 = -2;
                }
                childMeasureSpec2 = layoutManagerHelper.getChildMeasureSpec(contentHeight2, i8, false);
            }
            layoutManagerHelper.measureChildWithMargins(view, childMeasureSpec3, childMeasureSpec2);
        } else {
            int contentHeight3 = (layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingTop()) - layoutManagerHelper.getPaddingBottom();
            if (layoutParams.height >= 0) {
                i = layoutParams.height;
            } else {
                i = (!this.mSketchMeasure || z) ? -2 : -1;
            }
            int childMeasureSpec4 = layoutManagerHelper.getChildMeasureSpec(contentHeight3, i, false);
            if (!Float.isNaN(layoutParams.mAspectRatio) && layoutParams.mAspectRatio > 0.0f) {
                childMeasureSpec = layoutManagerHelper.getChildMeasureSpec((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingLeft()) - layoutManagerHelper.getPaddingRight(), (int) ((View.MeasureSpec.getSize(childMeasureSpec4) * layoutParams.mAspectRatio) + 0.5f), false);
            } else if (!Float.isNaN(this.mAspectRatio) && this.mAspectRatio > 0.0f) {
                childMeasureSpec = layoutManagerHelper.getChildMeasureSpec((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingLeft()) - layoutManagerHelper.getPaddingRight(), (int) ((View.MeasureSpec.getSize(childMeasureSpec4) * this.mAspectRatio) + 0.5f), false);
            } else {
                int contentWidth3 = (layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingLeft()) - layoutManagerHelper.getPaddingRight();
                if (layoutParams.width >= 0) {
                    i8 = layoutParams.width;
                } else if (!this.mSketchMeasure || !z) {
                    i8 = -2;
                }
                childMeasureSpec = layoutManagerHelper.getChildMeasureSpec(contentWidth3, i8, false);
            }
            layoutManagerHelper.measureChildWithMargins(view, childMeasureSpec, childMeasureSpec4);
        }
        int i9 = this.mAlignType;
        if (i9 == 1) {
            paddingTop = layoutManagerHelper.getPaddingTop() + this.mY + this.mAdjuster.top;
            contentWidth = ((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingRight()) - this.mX) - this.mAdjuster.right;
            measuredWidth = ((contentWidth - layoutParams.leftMargin) - layoutParams.rightMargin) - view.getMeasuredWidth();
            contentHeight = layoutParams.topMargin + paddingTop + layoutParams.bottomMargin + view.getMeasuredHeight();
        } else {
            if (i9 == 2) {
                measuredWidth = layoutManagerHelper.getPaddingLeft() + this.mX + this.mAdjuster.left;
                contentHeight = ((layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingBottom()) - this.mY) - this.mAdjuster.bottom;
                contentWidth = view.getMeasuredWidth() + layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin;
                measuredHeight = (contentHeight - view.getMeasuredHeight()) - layoutParams.topMargin;
                i6 = layoutParams.bottomMargin;
            } else {
                if (i9 == 3) {
                    contentWidth = ((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingRight()) - this.mX) - this.mAdjuster.right;
                    contentHeight = ((layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingBottom()) - this.mY) - this.mAdjuster.bottom;
                    measuredWidth = ((contentWidth - layoutParams.leftMargin) - layoutParams.rightMargin) - view.getMeasuredWidth();
                    measuredHeight = (contentHeight - view.getMeasuredHeight()) - layoutParams.topMargin;
                    i6 = layoutParams.bottomMargin;
                } else {
                    int paddingLeft = this.mAdjuster.left + layoutManagerHelper.getPaddingLeft() + this.mX;
                    int paddingTop2 = layoutManagerHelper.getPaddingTop() + this.mY + this.mAdjuster.top;
                    if (z) {
                        decoratedMeasurement = mainOrientationHelper.getDecoratedMeasurementInOther(view);
                    } else {
                        decoratedMeasurement = mainOrientationHelper.getDecoratedMeasurement(view);
                    }
                    int i10 = decoratedMeasurement + paddingLeft;
                    if (z) {
                        decoratedMeasurementInOther = mainOrientationHelper.getDecoratedMeasurement(view);
                    } else {
                        decoratedMeasurementInOther = mainOrientationHelper.getDecoratedMeasurementInOther(view);
                    }
                    i2 = paddingTop2;
                    i3 = decoratedMeasurementInOther + paddingTop2;
                    i4 = paddingLeft;
                    i5 = i10;
                }
                layoutChildWithMargin(view, i4, i2, i5, i3, layoutManagerHelper);
            }
            paddingTop = measuredHeight - i6;
        }
        i2 = paddingTop;
        i5 = contentWidth;
        i4 = measuredWidth;
        i3 = contentHeight;
        layoutChildWithMargin(view, i4, i2, i5, i3, layoutManagerHelper);
    }

    private static class FixViewAppearAnimatorListener extends AnimatorListenerAdapter {
        private View mFixView;
        private LayoutManagerHelper mLayoutManagerHelper;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        private FixViewAppearAnimatorListener() {
        }

        public void bindAction(LayoutManagerHelper layoutManagerHelper, View view) {
            this.mLayoutManagerHelper = layoutManagerHelper;
            this.mFixView = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.mFixView.setVisibility(0);
        }
    }

    private static class FixViewDisappearAnimatorListener extends AnimatorListenerAdapter {
        private boolean isAnimating;
        private Runnable mEndAction;
        private View mFixView;
        private LayoutManagerHelper mLayoutManagerHelper;
        private RecyclerView.Recycler mRecycler;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        private FixViewDisappearAnimatorListener() {
        }

        public void bindAction(RecyclerView.Recycler recycler, LayoutManagerHelper layoutManagerHelper, View view) {
            this.isAnimating = true;
            this.mRecycler = recycler;
            this.mLayoutManagerHelper = layoutManagerHelper;
            this.mFixView = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.mLayoutManagerHelper.removeChildView(this.mFixView);
            this.mRecycler.recycleView(this.mFixView);
            this.isAnimating = false;
            Runnable runnable = this.mEndAction;
            if (runnable != null) {
                runnable.run();
                this.mEndAction = null;
            }
        }

        public boolean isAnimating() {
            return this.isAnimating;
        }

        public void withEndAction(Runnable runnable) {
            this.mEndAction = runnable;
        }
    }
}
