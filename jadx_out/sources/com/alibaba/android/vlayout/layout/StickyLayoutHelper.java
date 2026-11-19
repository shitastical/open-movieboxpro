package com.alibaba.android.vlayout.layout;

import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.OrientationHelperEx;
import com.alibaba.android.vlayout.VirtualLayoutManager;

/* JADX INFO: loaded from: classes4.dex */
public class StickyLayoutHelper extends FixAreaLayoutHelper {
    private static final String TAG = "StickyStartLayoutHelper";
    private boolean isLastStatusSticking;
    private boolean mDoNormalHandle;
    private View mFixView;
    private int mOffset;
    private int mPos;
    private boolean mStickyStart;
    private StickyListener stickyListener;

    public interface StickyListener {
        void onSticky(int i, View view);

        void onUnSticky(int i, View view);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public boolean requireLayoutView() {
        return false;
    }

    public StickyLayoutHelper() {
        this(true);
    }

    public StickyLayoutHelper(boolean z) {
        this.mPos = -1;
        this.mOffset = 0;
        this.mFixView = null;
        this.mDoNormalHandle = false;
        this.isLastStatusSticking = false;
        this.mStickyStart = z;
        setItemCount(1);
    }

    public void setStickyStart(boolean z) {
        this.mStickyStart = z;
    }

    public void setOffset(int i) {
        this.mOffset = i;
    }

    public boolean isStickyNow() {
        return (this.mDoNormalHandle || this.mFixView == null) ? false : true;
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void setItemCount(int i) {
        if (i > 0) {
            super.setItemCount(1);
        } else {
            super.setItemCount(0);
        }
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRangeChange(int i, int i2) {
        this.mPos = i;
    }

    /* JADX WARN: Code duplicated, block: B:68:0x01d5  */
    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void layoutViews(RecyclerView.Recycler recycler, RecyclerView.State state, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper) {
        int paddingTop;
        int decoratedMeasurementInOther;
        int offset;
        int offset2;
        int startAfterPadding;
        int paddingLeft;
        int decoratedMeasurementInOther2;
        int endAfterPadding;
        int offset3;
        if (isOutOfRange(layoutStateWrapper.getCurrentPosition())) {
            return;
        }
        View next = this.mFixView;
        if (next == null) {
            next = layoutStateWrapper.next(recycler);
        } else {
            layoutStateWrapper.skipCurrentPosition();
        }
        View view = next;
        if (view == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        doMeasure(view, layoutManagerHelper);
        boolean z = layoutManagerHelper.getOrientation() == 1;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        layoutChunkResult.mConsumed = mainOrientationHelper.getDecoratedMeasurement(view);
        this.mDoNormalHandle = true;
        int available = (layoutStateWrapper.getAvailable() - layoutChunkResult.mConsumed) + layoutStateWrapper.getExtra();
        if (layoutManagerHelper.getOrientation() == 1) {
            if (layoutManagerHelper.isDoLayoutRTL()) {
                decoratedMeasurementInOther2 = (layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingRight()) - this.mMarginRight;
                paddingLeft = decoratedMeasurementInOther2 - mainOrientationHelper.getDecoratedMeasurementInOther(view);
            } else {
                paddingLeft = this.mMarginLeft + layoutManagerHelper.getPaddingLeft();
                decoratedMeasurementInOther2 = mainOrientationHelper.getDecoratedMeasurementInOther(view) + paddingLeft;
            }
            if (layoutStateWrapper.getLayoutDirection() == -1) {
                endAfterPadding = layoutStateWrapper.getOffset() - this.mMarginBottom;
                offset3 = layoutStateWrapper.getOffset() - layoutChunkResult.mConsumed;
            } else if (this.mStickyStart) {
                offset3 = this.mMarginTop + layoutStateWrapper.getOffset();
                endAfterPadding = layoutStateWrapper.getOffset() + layoutChunkResult.mConsumed;
            } else {
                endAfterPadding = ((mainOrientationHelper.getEndAfterPadding() - this.mMarginBottom) - this.mOffset) - this.mAdjuster.bottom;
                offset3 = endAfterPadding - layoutChunkResult.mConsumed;
            }
            if (layoutManagerHelper.getReverseLayout() || !this.mStickyStart) {
                if ((available >= this.mOffset + this.mAdjuster.bottom || layoutStateWrapper.getItemDirection() != 1) && endAfterPadding <= this.mMarginBottom + this.mOffset + this.mAdjuster.bottom) {
                    offset2 = decoratedMeasurementInOther2;
                    startAfterPadding = paddingLeft;
                    decoratedMeasurementInOther = endAfterPadding;
                    paddingTop = offset3;
                } else {
                    this.mDoNormalHandle = false;
                    this.mFixView = view;
                    int endAfterPadding2 = ((mainOrientationHelper.getEndAfterPadding() - this.mMarginBottom) - this.mOffset) - this.mAdjuster.bottom;
                    offset2 = decoratedMeasurementInOther2;
                    startAfterPadding = paddingLeft;
                    decoratedMeasurementInOther = endAfterPadding2;
                    paddingTop = endAfterPadding2 - layoutChunkResult.mConsumed;
                }
            } else if ((available < this.mOffset + this.mAdjuster.top && layoutStateWrapper.getItemDirection() == -1) || offset3 < this.mMarginTop + this.mOffset + this.mAdjuster.top) {
                this.mDoNormalHandle = false;
                this.mFixView = view;
                int startAfterPadding2 = mainOrientationHelper.getStartAfterPadding() + this.mMarginTop + this.mOffset + this.mAdjuster.top;
                offset2 = decoratedMeasurementInOther2;
                startAfterPadding = paddingLeft;
                paddingTop = startAfterPadding2;
                decoratedMeasurementInOther = layoutChunkResult.mConsumed + startAfterPadding2;
            } else {
                if (VirtualLayoutManager.sDebuggable) {
                    Log.i("Sticky", "remainingSpace: " + available + "    offset: " + this.mOffset);
                }
                offset2 = decoratedMeasurementInOther2;
                startAfterPadding = paddingLeft;
                decoratedMeasurementInOther = endAfterPadding;
                paddingTop = offset3;
            }
        } else {
            paddingTop = layoutManagerHelper.getPaddingTop();
            decoratedMeasurementInOther = mainOrientationHelper.getDecoratedMeasurementInOther(view) + paddingTop + this.mMarginTop;
            if (layoutStateWrapper.getLayoutDirection() == -1) {
                offset2 = layoutStateWrapper.getOffset() - this.mMarginRight;
                offset = layoutStateWrapper.getOffset() - layoutChunkResult.mConsumed;
            } else {
                offset = this.mMarginLeft + layoutStateWrapper.getOffset();
                offset2 = layoutStateWrapper.getOffset() + layoutChunkResult.mConsumed;
            }
            if (layoutManagerHelper.getReverseLayout() || !this.mStickyStart) {
                if (available < this.mOffset + this.mAdjuster.right) {
                    this.mDoNormalHandle = false;
                    this.mFixView = view;
                    int endAfterPadding3 = (mainOrientationHelper.getEndAfterPadding() - this.mOffset) - this.mAdjuster.right;
                    offset2 = endAfterPadding3;
                    startAfterPadding = endAfterPadding3 - layoutChunkResult.mConsumed;
                } else {
                    startAfterPadding = offset;
                }
            } else if (available < this.mOffset + this.mAdjuster.left) {
                this.mDoNormalHandle = false;
                this.mFixView = view;
                startAfterPadding = mainOrientationHelper.getStartAfterPadding() + this.mOffset + this.mAdjuster.left;
                offset2 = layoutChunkResult.mConsumed;
            } else {
                startAfterPadding = offset;
            }
        }
        layoutChildWithMargin(view, startAfterPadding, paddingTop, offset2, decoratedMeasurementInOther, layoutManagerHelper);
        layoutChunkResult.mConsumed += z ? getVerticalMargin() : getHorizontalMargin();
        if (state.isPreLayout()) {
            this.mDoNormalHandle = true;
        }
        if (this.mDoNormalHandle) {
            layoutManagerHelper.addChildView(layoutStateWrapper, view);
            handleStateOnResult(layoutChunkResult, view);
            this.mFixView = null;
        }
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void beforeLayout(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutManagerHelper layoutManagerHelper) {
        super.beforeLayout(recycler, state, layoutManagerHelper);
        View view = this.mFixView;
        if (view != null && layoutManagerHelper.isViewHolderUpdated(view)) {
            layoutManagerHelper.removeChildView(this.mFixView);
            recycler.recycleView(this.mFixView);
            this.mFixView = null;
        }
        this.mDoNormalHandle = false;
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void afterLayout(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, int i3, LayoutManagerHelper layoutManagerHelper) {
        int i4;
        super.afterLayout(recycler, state, i, i2, i3, layoutManagerHelper);
        if (this.mPos < 0) {
            return;
        }
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        if (!this.mDoNormalHandle && (i4 = this.mPos) >= i && i4 <= i2) {
            fixLayoutStateFromAbnormal2Normal(mainOrientationHelper, recycler, i, i2, layoutManagerHelper);
        }
        if (this.mDoNormalHandle || state.isPreLayout()) {
            state.isPreLayout();
            View view = this.mFixView;
            if (view == null) {
                return;
            } else {
                layoutManagerHelper.removeChildView(view);
            }
        }
        View view2 = this.mFixView;
        if (!this.mDoNormalHandle && view2 != null) {
            if (view2.getParent() == null) {
                layoutManagerHelper.addFixedView(this.mFixView);
            } else {
                fixLayoutStateInCase1(mainOrientationHelper, recycler, i, i2, layoutManagerHelper);
            }
        } else {
            fixLayoutStateInCase2(mainOrientationHelper, recycler, i, i2, layoutManagerHelper);
        }
        if (this.stickyListener != null) {
            if (this.isLastStatusSticking && !isStickyNow()) {
                this.stickyListener.onUnSticky(this.mPos, view2);
                this.isLastStatusSticking = false;
            } else {
                if (this.isLastStatusSticking || !isStickyNow()) {
                    return;
                }
                this.stickyListener.onSticky(this.mPos, this.mFixView);
                this.isLastStatusSticking = true;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0072  */
    /* JADX WARN: Code duplicated, block: B:38:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:47:? A[RETURN, SYNTHETIC] */
    private void fixLayoutStateFromAbnormal2Normal(OrientationHelperEx orientationHelperEx, RecyclerView.Recycler recycler, int i, int i2, LayoutManagerHelper layoutManagerHelper) {
        int paddingTop;
        int paddingBottom;
        if (VirtualLayoutManager.sDebuggable) {
            Log.i(TAG, "abnormal pos: " + this.mPos + " start: " + i + " end: " + i2);
        }
        if (this.mFixView != null) {
            if (this.mStickyStart) {
                for (int childCount = layoutManagerHelper.getChildCount() - 1; childCount >= 0; childCount--) {
                    View childAt = layoutManagerHelper.getChildAt(childCount);
                    int position = layoutManagerHelper.getPosition(childAt);
                    if (position < this.mPos) {
                        int decoratedEnd = orientationHelperEx.getDecoratedEnd(childAt);
                        LayoutHelper layoutHelperFindLayoutHelperByPosition = layoutManagerHelper.findLayoutHelperByPosition(position);
                        if (layoutHelperFindLayoutHelperByPosition instanceof RangeGridLayoutHelper) {
                            paddingBottom = ((RangeGridLayoutHelper) layoutHelperFindLayoutHelperByPosition).getBorderEndSpace(layoutManagerHelper);
                        } else {
                            if (layoutHelperFindLayoutHelperByPosition instanceof MarginLayoutHelper) {
                                MarginLayoutHelper marginLayoutHelper = (MarginLayoutHelper) layoutHelperFindLayoutHelperByPosition;
                                decoratedEnd += marginLayoutHelper.getMarginBottom();
                                paddingBottom = marginLayoutHelper.getPaddingBottom();
                            }
                            if (decoratedEnd >= this.mOffset + this.mAdjuster.top) {
                                this.mDoNormalHandle = true;
                                return;
                            }
                            return;
                        }
                        decoratedEnd += paddingBottom;
                        if (decoratedEnd >= this.mOffset + this.mAdjuster.top) {
                            this.mDoNormalHandle = true;
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            for (int i3 = 0; i3 < layoutManagerHelper.getChildCount(); i3++) {
                View childAt2 = layoutManagerHelper.getChildAt(i3);
                int position2 = layoutManagerHelper.getPosition(childAt2);
                if (position2 > this.mPos) {
                    int decoratedStart = orientationHelperEx.getDecoratedStart(childAt2);
                    LayoutHelper layoutHelperFindLayoutHelperByPosition2 = layoutManagerHelper.findLayoutHelperByPosition(position2);
                    if (layoutHelperFindLayoutHelperByPosition2 instanceof RangeGridLayoutHelper) {
                        paddingTop = ((RangeGridLayoutHelper) layoutHelperFindLayoutHelperByPosition2).getBorderStartSpace(layoutManagerHelper);
                    } else {
                        if (layoutHelperFindLayoutHelperByPosition2 instanceof MarginLayoutHelper) {
                            MarginLayoutHelper marginLayoutHelper2 = (MarginLayoutHelper) layoutHelperFindLayoutHelperByPosition2;
                            decoratedStart -= marginLayoutHelper2.getMarginTop();
                            paddingTop = marginLayoutHelper2.getPaddingTop();
                        }
                        if (decoratedStart >= this.mOffset + this.mAdjuster.bottom) {
                            this.mDoNormalHandle = true;
                            return;
                        }
                        return;
                    }
                    decoratedStart -= paddingTop;
                    if (decoratedStart >= this.mOffset + this.mAdjuster.bottom) {
                        this.mDoNormalHandle = true;
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void fixLayoutStateInCase1(OrientationHelperEx orientationHelperEx, RecyclerView.Recycler recycler, int i, int i2, LayoutManagerHelper layoutManagerHelper) {
        int i3;
        int startAfterPadding;
        int i4;
        int i5;
        int i6;
        int paddingLeft;
        int decoratedMeasurementInOther;
        View childAt;
        int paddingTop;
        int endAfterPadding;
        int decoratedEnd;
        int childCount;
        int paddingBottom;
        boolean z = this.mStickyStart;
        if ((z && i2 >= this.mPos) || (!z && i <= this.mPos)) {
            int decoratedMeasurement = orientationHelperEx.getDecoratedMeasurement(this.mFixView);
            int decoratedEnd2 = 0;
            boolean z2 = layoutManagerHelper.getOrientation() == 1;
            FixAreaAdjuster fixAreaAdjuster = this.mAdjuster;
            int i7 = z2 ? fixAreaAdjuster.top : fixAreaAdjuster.left;
            FixAreaAdjuster fixAreaAdjuster2 = this.mAdjuster;
            int i8 = z2 ? fixAreaAdjuster2.bottom : fixAreaAdjuster2.right;
            int i9 = -1;
            if (z2) {
                if (layoutManagerHelper.isDoLayoutRTL()) {
                    decoratedMeasurementInOther = layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingRight();
                    paddingLeft = decoratedMeasurementInOther - orientationHelperEx.getDecoratedMeasurementInOther(this.mFixView);
                } else {
                    paddingLeft = layoutManagerHelper.getPaddingLeft();
                    decoratedMeasurementInOther = orientationHelperEx.getDecoratedMeasurementInOther(this.mFixView) + paddingLeft;
                }
                if (this.mStickyStart) {
                    childCount = layoutManagerHelper.getChildCount() - 1;
                    childAt = null;
                    while (true) {
                        if (childCount < 0) {
                            startAfterPadding = 0;
                            endAfterPadding = 0;
                        } else {
                            childAt = layoutManagerHelper.getChildAt(childCount);
                            int position = layoutManagerHelper.getPosition(childAt);
                            if (position < this.mPos) {
                                decoratedEnd = orientationHelperEx.getDecoratedEnd(childAt);
                                LayoutHelper layoutHelperFindLayoutHelperByPosition = layoutManagerHelper.findLayoutHelperByPosition(position);
                                if (layoutHelperFindLayoutHelperByPosition instanceof RangeGridLayoutHelper) {
                                    paddingBottom = ((RangeGridLayoutHelper) layoutHelperFindLayoutHelperByPosition).getBorderEndSpace(layoutManagerHelper);
                                } else {
                                    if (layoutHelperFindLayoutHelperByPosition instanceof MarginLayoutHelper) {
                                        MarginLayoutHelper marginLayoutHelper = (MarginLayoutHelper) layoutHelperFindLayoutHelperByPosition;
                                        decoratedEnd += marginLayoutHelper.getMarginBottom();
                                        paddingBottom = marginLayoutHelper.getPaddingBottom();
                                    }
                                    endAfterPadding = decoratedEnd + decoratedMeasurement;
                                    this.mDoNormalHandle = true;
                                    startAfterPadding = decoratedEnd;
                                    i9 = childCount;
                                }
                                decoratedEnd += paddingBottom;
                                endAfterPadding = decoratedEnd + decoratedMeasurement;
                                this.mDoNormalHandle = true;
                                startAfterPadding = decoratedEnd;
                                i9 = childCount;
                            } else {
                                childCount--;
                            }
                        }
                    }
                } else {
                    childAt = null;
                    int i10 = 0;
                    while (true) {
                        if (i10 < layoutManagerHelper.getChildCount()) {
                            childAt = layoutManagerHelper.getChildAt(i10);
                            int position2 = layoutManagerHelper.getPosition(childAt);
                            if (position2 > this.mPos) {
                                int decoratedStart = orientationHelperEx.getDecoratedStart(childAt);
                                LayoutHelper layoutHelperFindLayoutHelperByPosition2 = layoutManagerHelper.findLayoutHelperByPosition(position2);
                                if (layoutHelperFindLayoutHelperByPosition2 instanceof RangeGridLayoutHelper) {
                                    paddingTop = ((RangeGridLayoutHelper) layoutHelperFindLayoutHelperByPosition2).getBorderStartSpace(layoutManagerHelper);
                                } else {
                                    if (layoutHelperFindLayoutHelperByPosition2 instanceof MarginLayoutHelper) {
                                        MarginLayoutHelper marginLayoutHelper2 = (MarginLayoutHelper) layoutHelperFindLayoutHelperByPosition2;
                                        decoratedStart -= marginLayoutHelper2.getMarginTop();
                                        paddingTop = marginLayoutHelper2.getPaddingTop();
                                    }
                                    endAfterPadding = decoratedStart;
                                    decoratedEnd = endAfterPadding - decoratedMeasurement;
                                    childCount = i10 + 1;
                                    this.mDoNormalHandle = true;
                                    startAfterPadding = decoratedEnd;
                                    i9 = childCount;
                                }
                                decoratedStart -= paddingTop;
                                endAfterPadding = decoratedStart;
                                decoratedEnd = endAfterPadding - decoratedMeasurement;
                                childCount = i10 + 1;
                                this.mDoNormalHandle = true;
                                startAfterPadding = decoratedEnd;
                                i9 = childCount;
                            } else {
                                i10++;
                            }
                        } else {
                            startAfterPadding = 0;
                            endAfterPadding = 0;
                        }
                    }
                }
                if (childAt == null || i9 < 0) {
                    this.mDoNormalHandle = false;
                }
                if (layoutManagerHelper.getReverseLayout() || !this.mStickyStart) {
                    if (endAfterPadding > (orientationHelperEx.getEndAfterPadding() - this.mOffset) - i8) {
                        this.mDoNormalHandle = false;
                    }
                } else if (startAfterPadding < orientationHelperEx.getStartAfterPadding() + this.mOffset + i7) {
                    this.mDoNormalHandle = false;
                }
                if (!this.mDoNormalHandle) {
                    if (layoutManagerHelper.getReverseLayout() || !this.mStickyStart) {
                        endAfterPadding = (orientationHelperEx.getEndAfterPadding() - this.mOffset) - i8;
                        startAfterPadding = endAfterPadding - decoratedMeasurement;
                    } else {
                        startAfterPadding = orientationHelperEx.getStartAfterPadding() + this.mOffset + i7;
                        endAfterPadding = startAfterPadding + decoratedMeasurement;
                    }
                }
                i3 = decoratedMeasurementInOther;
                i5 = paddingLeft;
                i4 = endAfterPadding;
            } else {
                int paddingTop2 = layoutManagerHelper.getPaddingTop();
                int decoratedMeasurementInOther2 = orientationHelperEx.getDecoratedMeasurementInOther(this.mFixView) + paddingTop2;
                if (this.mDoNormalHandle) {
                    if (!this.mStickyStart) {
                        int i11 = 0;
                        while (true) {
                            if (i11 >= layoutManagerHelper.getChildCount()) {
                                i6 = 0;
                                break;
                            }
                            View childAt2 = layoutManagerHelper.getChildAt(i11);
                            if (layoutManagerHelper.getPosition(childAt2) > this.mPos) {
                                int decoratedStart2 = orientationHelperEx.getDecoratedStart(childAt2);
                                decoratedEnd2 = decoratedStart2 - decoratedMeasurement;
                                i6 = decoratedStart2;
                                break;
                            }
                            i11++;
                        }
                    } else {
                        int childCount2 = layoutManagerHelper.getChildCount() - 1;
                        while (true) {
                            if (childCount2 < 0) {
                                i6 = 0;
                                break;
                            }
                            View childAt3 = layoutManagerHelper.getChildAt(childCount2);
                            if (layoutManagerHelper.getPosition(childAt3) < this.mPos) {
                                decoratedEnd2 = orientationHelperEx.getDecoratedEnd(childAt3);
                                i6 = decoratedEnd2 + decoratedMeasurement;
                                break;
                            }
                            childCount2--;
                        }
                    }
                    startAfterPadding = paddingTop2;
                    i5 = decoratedEnd2;
                    i4 = decoratedMeasurementInOther2;
                    i3 = i6;
                } else if (layoutManagerHelper.getReverseLayout() || !this.mStickyStart) {
                    int endAfterPadding2 = (orientationHelperEx.getEndAfterPadding() - this.mOffset) - i8;
                    i3 = endAfterPadding2;
                    startAfterPadding = paddingTop2;
                    i4 = decoratedMeasurementInOther2;
                    i5 = endAfterPadding2 - decoratedMeasurement;
                } else {
                    int startAfterPadding2 = orientationHelperEx.getStartAfterPadding() + this.mOffset + i7;
                    i3 = decoratedMeasurement + startAfterPadding2;
                    startAfterPadding = paddingTop2;
                    i4 = decoratedMeasurementInOther2;
                    i5 = startAfterPadding2;
                }
            }
            layoutChildWithMargin(this.mFixView, i5, startAfterPadding, i3, i4, layoutManagerHelper);
            if (!this.mDoNormalHandle) {
                layoutManagerHelper.showView(this.mFixView);
                layoutManagerHelper.addFixedView(this.mFixView);
                return;
            } else {
                if (i9 >= 0) {
                    if (this.mFixView.getParent() == null) {
                        layoutManagerHelper.addChildView(this.mFixView, i9);
                    }
                    this.mFixView = null;
                    return;
                }
                return;
            }
        }
        layoutManagerHelper.removeChildView(this.mFixView);
        layoutManagerHelper.recycleView(this.mFixView);
        this.mFixView = null;
    }

    private void fixLayoutStateInCase2(OrientationHelperEx orientationHelperEx, RecyclerView.Recycler recycler, int i, int i2, LayoutManagerHelper layoutManagerHelper) {
        boolean z;
        boolean z2;
        int i3;
        int i4;
        int i5;
        int i6;
        int decoratedStart;
        int i7;
        int i8;
        int paddingLeft;
        int decoratedMeasurementInOther;
        int i9;
        int i10;
        View childAt;
        int paddingTop;
        int decoratedEnd;
        int paddingBottom;
        View viewFindViewByPosition = this.mFixView;
        if (viewFindViewByPosition == null) {
            viewFindViewByPosition = layoutManagerHelper.findViewByPosition(this.mPos);
        }
        boolean z3 = layoutManagerHelper.getOrientation() == 1;
        FixAreaAdjuster fixAreaAdjuster = this.mAdjuster;
        int i11 = z3 ? fixAreaAdjuster.top : fixAreaAdjuster.left;
        FixAreaAdjuster fixAreaAdjuster2 = this.mAdjuster;
        int i12 = z3 ? fixAreaAdjuster2.bottom : fixAreaAdjuster2.right;
        boolean z4 = this.mStickyStart;
        if ((!z4 || i2 < this.mPos) && (z4 || i > this.mPos)) {
            z = false;
        } else if (viewFindViewByPosition == null) {
            z = this.mOffset + (z4 ? i11 : i12) >= 0;
            View viewForPosition = recycler.getViewForPosition(this.mPos);
            this.mFixView = viewForPosition;
            doMeasure(viewForPosition, layoutManagerHelper);
        } else {
            if (z4 && orientationHelperEx.getDecoratedStart(viewFindViewByPosition) >= orientationHelperEx.getStartAfterPadding() + this.mOffset + i11) {
                this.mFixView = viewFindViewByPosition;
            } else if (!this.mStickyStart && orientationHelperEx.getDecoratedEnd(viewFindViewByPosition) <= (orientationHelperEx.getEndAfterPadding() - this.mOffset) - i12) {
                this.mFixView = viewFindViewByPosition;
            } else {
                this.mFixView = viewFindViewByPosition;
                z = false;
            }
            z = true;
        }
        View view = this.mFixView;
        if (view != null) {
            if (((RecyclerView.LayoutParams) view.getLayoutParams()).isItemRemoved()) {
                return;
            }
            int decoratedMeasurement = orientationHelperEx.getDecoratedMeasurement(this.mFixView);
            int i13 = -1;
            if (z3) {
                if (layoutManagerHelper.isDoLayoutRTL()) {
                    decoratedMeasurementInOther = layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingRight();
                    paddingLeft = decoratedMeasurementInOther - orientationHelperEx.getDecoratedMeasurementInOther(this.mFixView);
                } else {
                    paddingLeft = layoutManagerHelper.getPaddingLeft();
                    decoratedMeasurementInOther = orientationHelperEx.getDecoratedMeasurementInOther(this.mFixView) + paddingLeft;
                }
                if (z) {
                    if (!this.mStickyStart) {
                        childAt = null;
                        int i14 = 0;
                        while (true) {
                            if (i14 >= layoutManagerHelper.getChildCount()) {
                                i9 = 0;
                                decoratedEnd = 0;
                                break;
                            }
                            childAt = layoutManagerHelper.getChildAt(i14);
                            int position = layoutManagerHelper.getPosition(childAt);
                            if (position > this.mPos) {
                                int decoratedStart2 = orientationHelperEx.getDecoratedStart(childAt);
                                LayoutHelper layoutHelperFindLayoutHelperByPosition = layoutManagerHelper.findLayoutHelperByPosition(position);
                                if (layoutHelperFindLayoutHelperByPosition instanceof RangeGridLayoutHelper) {
                                    paddingTop = ((RangeGridLayoutHelper) layoutHelperFindLayoutHelperByPosition).getBorderStartSpace(layoutManagerHelper);
                                } else {
                                    if (layoutHelperFindLayoutHelperByPosition instanceof MarginLayoutHelper) {
                                        MarginLayoutHelper marginLayoutHelper = (MarginLayoutHelper) layoutHelperFindLayoutHelperByPosition;
                                        decoratedStart2 -= marginLayoutHelper.getMarginTop();
                                        paddingTop = marginLayoutHelper.getPaddingTop();
                                    }
                                    decoratedEnd = decoratedStart2 - decoratedMeasurement;
                                    int i15 = decoratedStart2;
                                    i13 = i14;
                                    i9 = i15;
                                    break;
                                }
                                decoratedStart2 -= paddingTop;
                                decoratedEnd = decoratedStart2 - decoratedMeasurement;
                                int i16 = decoratedStart2;
                                i13 = i14;
                                i9 = i16;
                                break;
                            }
                            i14++;
                        }
                    } else {
                        int childCount = layoutManagerHelper.getChildCount() - 1;
                        childAt = null;
                        while (true) {
                            if (childCount < 0) {
                                i9 = 0;
                                decoratedEnd = 0;
                                break;
                            }
                            childAt = layoutManagerHelper.getChildAt(childCount);
                            int position2 = layoutManagerHelper.getPosition(childAt);
                            if (position2 < this.mPos) {
                                decoratedEnd = orientationHelperEx.getDecoratedEnd(childAt);
                                LayoutHelper layoutHelperFindLayoutHelperByPosition2 = layoutManagerHelper.findLayoutHelperByPosition(position2);
                                if (layoutHelperFindLayoutHelperByPosition2 instanceof RangeGridLayoutHelper) {
                                    paddingBottom = ((RangeGridLayoutHelper) layoutHelperFindLayoutHelperByPosition2).getBorderEndSpace(layoutManagerHelper);
                                } else {
                                    if (layoutHelperFindLayoutHelperByPosition2 instanceof MarginLayoutHelper) {
                                        MarginLayoutHelper marginLayoutHelper2 = (MarginLayoutHelper) layoutHelperFindLayoutHelperByPosition2;
                                        decoratedEnd += marginLayoutHelper2.getMarginBottom();
                                        paddingBottom = marginLayoutHelper2.getPaddingBottom();
                                    }
                                    i9 = decoratedEnd + decoratedMeasurement;
                                    i13 = childCount + 1;
                                    break;
                                }
                                decoratedEnd += paddingBottom;
                                i9 = decoratedEnd + decoratedMeasurement;
                                i13 = childCount + 1;
                                break;
                            }
                            childCount--;
                        }
                    }
                    if (childAt == null || i13 < 0) {
                        z = false;
                    }
                    if (layoutManagerHelper.getReverseLayout() || !this.mStickyStart ? i9 <= (orientationHelperEx.getEndAfterPadding() - this.mOffset) - i12 : decoratedEnd >= orientationHelperEx.getStartAfterPadding() + this.mOffset + i11) {
                        i10 = decoratedEnd;
                        z2 = z;
                    } else {
                        i10 = decoratedEnd;
                        z2 = false;
                    }
                } else {
                    z2 = z;
                    i9 = 0;
                    i10 = 0;
                }
                if (z2) {
                    i3 = i9;
                    i5 = i10;
                } else if (layoutManagerHelper.getReverseLayout() || !this.mStickyStart) {
                    int endAfterPadding = (orientationHelperEx.getEndAfterPadding() - this.mOffset) - i12;
                    i3 = endAfterPadding;
                    i5 = endAfterPadding - decoratedMeasurement;
                } else {
                    int startAfterPadding = orientationHelperEx.getStartAfterPadding() + this.mOffset + i11;
                    i5 = startAfterPadding;
                    i3 = decoratedMeasurement + startAfterPadding;
                }
                i4 = decoratedMeasurementInOther;
                i6 = paddingLeft;
            } else {
                int paddingTop2 = layoutManagerHelper.getPaddingTop();
                int decoratedMeasurementInOther2 = orientationHelperEx.getDecoratedMeasurementInOther(this.mFixView) + paddingTop2;
                if (z) {
                    if (!this.mStickyStart) {
                        int i17 = 0;
                        while (true) {
                            if (i17 >= layoutManagerHelper.getChildCount()) {
                                decoratedStart = 0;
                                i7 = 0;
                                break;
                            }
                            View childAt2 = layoutManagerHelper.getChildAt(i17);
                            if (layoutManagerHelper.getPosition(childAt2) > this.mPos) {
                                decoratedStart = orientationHelperEx.getDecoratedStart(childAt2);
                                i7 = decoratedStart - decoratedMeasurement;
                                break;
                            }
                            i17++;
                        }
                    } else {
                        int childCount2 = layoutManagerHelper.getChildCount() - 1;
                        while (true) {
                            if (childCount2 < 0) {
                                i8 = 0;
                                i7 = 0;
                                break;
                            }
                            View childAt3 = layoutManagerHelper.getChildAt(childCount2);
                            if (layoutManagerHelper.getPosition(childAt3) < this.mPos) {
                                int decoratedEnd2 = orientationHelperEx.getDecoratedEnd(childAt3);
                                i8 = decoratedEnd2 + decoratedMeasurement;
                                i7 = decoratedEnd2;
                                break;
                            }
                            childCount2--;
                        }
                        decoratedStart = i8;
                    }
                    z2 = z;
                    i5 = paddingTop2;
                    i3 = decoratedMeasurementInOther2;
                    i6 = i7;
                    i4 = decoratedStart;
                } else if (layoutManagerHelper.getReverseLayout() || !this.mStickyStart) {
                    int endAfterPadding2 = (orientationHelperEx.getEndAfterPadding() - this.mOffset) - i12;
                    int i18 = endAfterPadding2 - decoratedMeasurement;
                    z2 = z;
                    i3 = decoratedMeasurementInOther2;
                    i4 = endAfterPadding2;
                    i5 = paddingTop2;
                    i6 = i18;
                } else {
                    int startAfterPadding2 = orientationHelperEx.getStartAfterPadding() + this.mOffset + i11;
                    i5 = paddingTop2;
                    i3 = decoratedMeasurementInOther2;
                    i4 = decoratedMeasurement + startAfterPadding2;
                    i6 = startAfterPadding2;
                    z2 = z;
                }
            }
            layoutChildWithMargin(this.mFixView, i6, i5, i4, i3, layoutManagerHelper);
            if (!z2) {
                layoutManagerHelper.addFixedView(this.mFixView);
            } else if (i13 >= 0) {
                if (this.mFixView.getParent() == null) {
                    layoutManagerHelper.addChildView(this.mFixView, i13);
                }
                this.mFixView = null;
            }
            z = z2;
        }
        this.mDoNormalHandle = z;
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
            layoutManagerHelper.recycleView(view);
            layoutManagerHelper.removeChildView(this.mFixView);
            this.mFixView = null;
        }
    }

    private void doMeasure(View view, LayoutManagerHelper layoutManagerHelper) {
        int childMeasureSpec;
        int childMeasureSpec2;
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        boolean z = layoutManagerHelper.getOrientation() == 1;
        int contentWidth = ((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingLeft()) - layoutManagerHelper.getPaddingRight()) - getHorizontalMargin();
        int contentHeight = ((layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingTop()) - layoutManagerHelper.getPaddingBottom()) - getVerticalMargin();
        float f = layoutParams.mAspectRatio;
        if (z) {
            int childMeasureSpec3 = layoutManagerHelper.getChildMeasureSpec(contentWidth, layoutParams.width, false);
            if (!Float.isNaN(f) && f > 0.0f) {
                childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec((int) ((contentWidth / f) + 0.5f), 1073741824);
            } else if (!Float.isNaN(this.mAspectRatio) && this.mAspectRatio > 0.0f) {
                childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec((int) (((double) (contentWidth / this.mAspectRatio)) + 0.5d), 1073741824);
            } else {
                childMeasureSpec2 = layoutManagerHelper.getChildMeasureSpec(contentHeight, layoutParams.height, true);
            }
            layoutManagerHelper.measureChildWithMargins(view, childMeasureSpec3, childMeasureSpec2);
            return;
        }
        int childMeasureSpec4 = layoutManagerHelper.getChildMeasureSpec(contentHeight, layoutParams.height, false);
        if (!Float.isNaN(f) && f > 0.0f) {
            childMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (((double) (contentHeight * f)) + 0.5d), 1073741824);
        } else if (!Float.isNaN(this.mAspectRatio) && this.mAspectRatio > 0.0f) {
            childMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (((double) (contentHeight * this.mAspectRatio)) + 0.5d), 1073741824);
        } else {
            childMeasureSpec = layoutManagerHelper.getChildMeasureSpec(contentWidth, layoutParams.width, true);
        }
        layoutManagerHelper.measureChildWithMargins(view, childMeasureSpec, childMeasureSpec4);
    }

    public void setStickyListener(StickyListener stickyListener) {
        this.stickyListener = stickyListener;
    }
}
