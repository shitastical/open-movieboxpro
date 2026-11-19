package com.alibaba.android.vlayout.layout;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.OrientationHelperEx;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class OnePlusNLayoutHelper extends AbstractFullFillLayoutHelper {
    private static final String TAG = "OnePlusNLayoutHelper";
    private Rect mAreaRect;
    private View[] mChildrenViews;
    private float[] mColWeights;
    private float mRowWeight;

    public OnePlusNLayoutHelper() {
        this.mAreaRect = new Rect();
        this.mColWeights = new float[0];
        this.mRowWeight = Float.NaN;
        setItemCount(0);
    }

    public OnePlusNLayoutHelper(int i) {
        this(i, 0, 0, 0, 0);
    }

    public OnePlusNLayoutHelper(int i, int i2, int i3, int i4, int i5) {
        this.mAreaRect = new Rect();
        this.mColWeights = new float[0];
        this.mRowWeight = Float.NaN;
        setItemCount(i);
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRangeChange(int i, int i2) {
        if (i2 - i > 6) {
            throw new IllegalArgumentException("OnePlusNLayoutHelper only supports maximum 7 children now");
        }
    }

    public void setColWeights(float[] fArr) {
        if (fArr != null) {
            this.mColWeights = Arrays.copyOf(fArr, fArr.length);
        } else {
            this.mColWeights = new float[0];
        }
    }

    public void setRowWeight(float f) {
        this.mRowWeight = f;
    }

    @Override // com.alibaba.android.vlayout.layout.AbstractFullFillLayoutHelper, com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void layoutViews(RecyclerView.Recycler recycler, RecyclerView.State state, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper) {
        int iHandleFive;
        int offset;
        int offset2;
        int decoratedMeasurementInOther;
        int decoratedMeasurementInOther2;
        int i;
        int paddingLeft;
        int offset3;
        int offset4;
        int offset5;
        int offset6;
        int decoratedMeasurementInOther3;
        int decoratedMeasurementInOther4;
        int i2;
        int paddingLeft2;
        int offset7;
        int offset8;
        if (isOutOfRange(layoutStateWrapper.getCurrentPosition())) {
            return;
        }
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        boolean z = layoutManagerHelper.getOrientation() == 1;
        boolean z2 = layoutStateWrapper.getLayoutDirection() == -1;
        int contentWidth = layoutManagerHelper.getContentWidth();
        int contentHeight = layoutManagerHelper.getContentHeight();
        int paddingLeft3 = layoutManagerHelper.getPaddingLeft() + layoutManagerHelper.getPaddingRight() + getHorizontalMargin() + getHorizontalPadding();
        int paddingTop = layoutManagerHelper.getPaddingTop() + layoutManagerHelper.getPaddingBottom() + getVerticalMargin() + getVerticalPadding();
        int currentPosition = layoutStateWrapper.getCurrentPosition();
        if (this.hasHeader && currentPosition == ((Integer) getRange().getLower()).intValue()) {
            View viewNextView = nextView(recycler, layoutStateWrapper, layoutManagerHelper, layoutChunkResult);
            int iHandleHeader = handleHeader(viewNextView, layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft3, paddingTop);
            if (viewNextView != null) {
                if (z) {
                    if (z2) {
                        offset8 = layoutStateWrapper.getOffset();
                        offset7 = offset8 - iHandleHeader;
                    } else {
                        offset7 = (this.mLayoutWithAnchor ? 0 : this.mMarginTop + this.mPaddingTop) + layoutStateWrapper.getOffset();
                        offset8 = offset7 + iHandleHeader;
                    }
                    paddingLeft2 = layoutManagerHelper.getPaddingLeft() + this.mMarginLeft + this.mPaddingLeft;
                    decoratedMeasurementInOther4 = offset8;
                    decoratedMeasurementInOther3 = mainOrientationHelper.getDecoratedMeasurementInOther(viewNextView) + paddingLeft2;
                    i2 = offset7;
                } else {
                    if (z2) {
                        offset6 = layoutStateWrapper.getOffset();
                        offset5 = offset6 - iHandleHeader;
                    } else {
                        offset5 = (this.mLayoutWithAnchor ? 0 : this.mMarginLeft + this.mPaddingLeft) + layoutStateWrapper.getOffset();
                        offset6 = offset5 + iHandleHeader;
                    }
                    int paddingTop2 = layoutManagerHelper.getPaddingTop() + this.mMarginTop + this.mPaddingTop;
                    decoratedMeasurementInOther3 = offset6;
                    decoratedMeasurementInOther4 = mainOrientationHelper.getDecoratedMeasurementInOther(viewNextView) + paddingTop2;
                    i2 = paddingTop2;
                    paddingLeft2 = offset5;
                }
                layoutChildWithMargin(viewNextView, paddingLeft2, i2, decoratedMeasurementInOther3, decoratedMeasurementInOther4, layoutManagerHelper);
            }
            layoutChunkResult.mConsumed = iHandleHeader;
            handleStateOnResult(layoutChunkResult, viewNextView);
            return;
        }
        if (this.hasFooter && currentPosition == ((Integer) getRange().getUpper()).intValue()) {
            View viewNextView2 = nextView(recycler, layoutStateWrapper, layoutManagerHelper, layoutChunkResult);
            int iHandleFooter = handleFooter(viewNextView2, layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft3, paddingTop);
            if (viewNextView2 != null) {
                if (z) {
                    if (z2) {
                        offset4 = layoutStateWrapper.getOffset() - (this.mLayoutWithAnchor ? 0 : this.mMarginBottom + this.mPaddingBottom);
                        offset3 = offset4 - iHandleFooter;
                    } else {
                        offset3 = layoutStateWrapper.getOffset();
                        offset4 = offset3 + iHandleFooter;
                    }
                    paddingLeft = layoutManagerHelper.getPaddingLeft() + this.mMarginLeft + this.mPaddingLeft;
                    decoratedMeasurementInOther2 = offset4;
                    decoratedMeasurementInOther = mainOrientationHelper.getDecoratedMeasurementInOther(viewNextView2) + paddingLeft;
                    i = offset3;
                } else {
                    if (z2) {
                        offset2 = layoutStateWrapper.getOffset() - (this.mLayoutWithAnchor ? 0 : this.mMarginRight + this.mPaddingRight);
                        offset = offset2 - iHandleFooter;
                    } else {
                        offset = layoutStateWrapper.getOffset();
                        offset2 = offset + iHandleFooter;
                    }
                    int paddingTop3 = layoutManagerHelper.getPaddingTop() + this.mMarginTop + this.mPaddingTop;
                    decoratedMeasurementInOther = offset2;
                    decoratedMeasurementInOther2 = mainOrientationHelper.getDecoratedMeasurementInOther(viewNextView2) + paddingTop3;
                    i = paddingTop3;
                    paddingLeft = offset;
                }
                layoutChildWithMargin(viewNextView2, paddingLeft, i, decoratedMeasurementInOther, decoratedMeasurementInOther2, layoutManagerHelper);
            }
            layoutChunkResult.mConsumed = iHandleFooter;
            handleStateOnResult(layoutChunkResult, viewNextView2);
            return;
        }
        int itemCount = (getItemCount() - (this.hasHeader ? 1 : 0)) - (this.hasFooter ? 1 : 0);
        View[] viewArr = this.mChildrenViews;
        if (viewArr == null || viewArr.length != itemCount) {
            this.mChildrenViews = new View[itemCount];
        }
        int allChildren = getAllChildren(this.mChildrenViews, recycler, layoutStateWrapper, layoutChunkResult, layoutManagerHelper);
        if (allChildren == 0 || allChildren < itemCount) {
            return;
        }
        if (itemCount == 1) {
            iHandleFive = handleOne(layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft3, paddingTop);
        } else if (itemCount == 2) {
            iHandleFive = handleTwo(layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft3, paddingTop);
        } else if (itemCount == 3) {
            iHandleFive = handleThree(layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft3, paddingTop);
        } else if (itemCount == 4) {
            iHandleFive = handleFour(layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft3, paddingTop);
        } else {
            iHandleFive = itemCount == 5 ? handleFive(layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft3, paddingTop) : 0;
        }
        layoutChunkResult.mConsumed = iHandleFive;
        Arrays.fill(this.mChildrenViews, (Object) null);
    }

    private float getViewMainWeight(int i) {
        float[] fArr = this.mColWeights;
        if (fArr.length > i) {
            return fArr[i];
        }
        return Float.NaN;
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    protected void onClear(LayoutManagerHelper layoutManagerHelper) {
        super.onClear(layoutManagerHelper);
    }

    @Override // com.alibaba.android.vlayout.layout.AbstractFullFillLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void checkAnchorInfo(RecyclerView.State state, VirtualLayoutManager.AnchorInfoWrapper anchorInfoWrapper, LayoutManagerHelper layoutManagerHelper) {
        super.checkAnchorInfo(state, anchorInfoWrapper, layoutManagerHelper);
        this.mLayoutWithAnchor = true;
    }

    @Override // com.alibaba.android.vlayout.layout.AbstractFullFillLayoutHelper, com.alibaba.android.vlayout.layout.MarginLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public int computeAlignOffset(int i, boolean z, boolean z2, LayoutManagerHelper layoutManagerHelper) {
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z3 = layoutManagerHelper.getOrientation() == 1;
        if (z2) {
            return 0;
        }
        if (z) {
            if (i == getItemCount() - 1) {
                if (z3) {
                    i4 = this.mMarginBottom;
                    i5 = this.mPaddingBottom;
                } else {
                    i4 = this.mMarginRight;
                    i5 = this.mPaddingRight;
                }
                return i4 + i5;
            }
        } else if (i == 0) {
            if (z3) {
                i2 = -this.mMarginTop;
                i3 = this.mPaddingTop;
            } else {
                i2 = -this.mMarginLeft;
                i3 = this.mPaddingLeft;
            }
            return i2 - i3;
        }
        return 0;
    }

    private int handleHeader(View view, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        if (view == null) {
            return 0;
        }
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        layoutManagerHelper.measureChildWithMargins(view, layoutManagerHelper.getChildMeasureSpec(i - i3, z ? -1 : layoutParams.width, !z), layoutManagerHelper.getChildMeasureSpec(i2 - i4, z ? layoutParams.height : 1073741824, z));
        return mainOrientationHelper.getDecoratedMeasurement(view);
    }

    private int handleFooter(View view, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        if (view == null) {
            return 0;
        }
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        layoutManagerHelper.measureChildWithMargins(view, layoutManagerHelper.getChildMeasureSpec(i - i3, z ? -1 : layoutParams.width, !z), layoutManagerHelper.getChildMeasureSpec(i2 - i4, z ? layoutParams.height : 1073741824, z));
        return mainOrientationHelper.getDecoratedMeasurement(view);
    }

    private int handleOne(VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        View view = this.mChildrenViews[0];
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        if (!Float.isNaN(this.mAspectRatio)) {
            if (z) {
                layoutParams.height = (int) ((i - i3) / this.mAspectRatio);
            } else {
                layoutParams.width = (int) ((i2 - i4) * this.mAspectRatio);
            }
        }
        float viewMainWeight = getViewMainWeight(0);
        layoutManagerHelper.measureChildWithMargins(view, layoutManagerHelper.getChildMeasureSpec(Float.isNaN(viewMainWeight) ? i - i3 : (int) ((i - i3) * viewMainWeight), z ? -1 : layoutParams.width, !z), layoutManagerHelper.getChildMeasureSpec(i2 - i4, z ? layoutParams.height : 1073741824, z));
        calculateRect(mainOrientationHelper.getDecoratedMeasurement(view), this.mAreaRect, layoutStateWrapper, layoutManagerHelper);
        layoutChildWithMargin(view, this.mAreaRect.left, this.mAreaRect.top, this.mAreaRect.right, this.mAreaRect.bottom, layoutManagerHelper);
        handleStateOnResult(layoutChunkResult, view);
        return (this.mAreaRect.bottom - this.mAreaRect.top) + (this.hasHeader ? 0 : this.mMarginTop + this.mPaddingTop) + (this.hasFooter ? 0 : this.mMarginBottom + this.mPaddingBottom);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x01d9 A[PHI: r0
      0x01d9: PHI (r0v15 int) = (r0v14 int), (r0v32 int) binds: [B:39:0x01d7, B:20:0x0104] A[DONT_GENERATE, DONT_INLINE]] */
    private int handleTwo(VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        View view = this.mChildrenViews[0];
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        View view2 = this.mChildrenViews[1];
        VirtualLayoutManager.LayoutParams layoutParams2 = (VirtualLayoutManager.LayoutParams) view2.getLayoutParams();
        float viewMainWeight = getViewMainWeight(0);
        float viewMainWeight2 = getViewMainWeight(1);
        if (z) {
            if (!Float.isNaN(this.mAspectRatio)) {
                int i9 = (int) ((i - i3) / this.mAspectRatio);
                layoutParams2.height = i9;
                layoutParams.height = i9;
            }
            layoutParams2.topMargin = layoutParams.topMargin;
            layoutParams2.bottomMargin = layoutParams.bottomMargin;
            int i10 = ((((i - i3) - layoutParams.leftMargin) - layoutParams.rightMargin) - layoutParams2.leftMargin) - layoutParams2.rightMargin;
            int i11 = Float.isNaN(viewMainWeight) ? (int) ((i10 / 2.0f) + 0.5f) : (int) (((i10 * viewMainWeight) / 100.0f) + 0.5f);
            int i12 = Float.isNaN(viewMainWeight2) ? i10 - i11 : (int) (((i10 * viewMainWeight2) / 100.0f) + 0.5f);
            layoutManagerHelper.measureChildWithMargins(view, View.MeasureSpec.makeMeasureSpec(i11 + layoutParams.leftMargin + layoutParams.rightMargin, 1073741824), layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentHeight(), layoutParams.height, true));
            layoutManagerHelper.measureChildWithMargins(view2, View.MeasureSpec.makeMeasureSpec(i12 + layoutParams2.leftMargin + layoutParams2.rightMargin, 1073741824), layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentHeight(), layoutParams2.height, true));
            calculateRect(Math.max(mainOrientationHelper.getDecoratedMeasurement(view), mainOrientationHelper.getDecoratedMeasurement(view2)), this.mAreaRect, layoutStateWrapper, layoutManagerHelper);
            int decoratedMeasurementInOther = this.mAreaRect.left + mainOrientationHelper.getDecoratedMeasurementInOther(view);
            layoutChildWithMargin(view, this.mAreaRect.left, this.mAreaRect.top, decoratedMeasurementInOther, this.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view2, decoratedMeasurementInOther, this.mAreaRect.top, decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view2), this.mAreaRect.bottom, layoutManagerHelper);
            i5 = (this.mAreaRect.bottom - this.mAreaRect.top) + (this.hasHeader ? 0 : this.mMarginTop + this.mPaddingTop);
            if (this.hasFooter) {
                i8 = 0;
            } else {
                i6 = this.mMarginBottom;
                i7 = this.mPaddingBottom;
                i8 = i6 + i7;
            }
        } else {
            if (!Float.isNaN(this.mAspectRatio)) {
                int i13 = (int) ((i2 - i4) * this.mAspectRatio);
                layoutParams2.width = i13;
                layoutParams.width = i13;
            }
            int i14 = ((((i2 - i4) - layoutParams.topMargin) - layoutParams.bottomMargin) - layoutParams2.topMargin) - layoutParams2.bottomMargin;
            int i15 = Float.isNaN(viewMainWeight) ? (int) ((i14 / 2.0f) + 0.5f) : (int) (((i14 * viewMainWeight) / 100.0f) + 0.5f);
            int i16 = Float.isNaN(viewMainWeight2) ? i14 - i15 : (int) (((i14 * viewMainWeight2) / 100.0f) + 0.5f);
            layoutManagerHelper.measureChildWithMargins(view, layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentWidth(), layoutParams.width, true), View.MeasureSpec.makeMeasureSpec(i15 + layoutParams.topMargin + layoutParams.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view2, View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i16 + layoutParams2.topMargin + layoutParams2.bottomMargin, 1073741824));
            calculateRect(Math.max(mainOrientationHelper.getDecoratedMeasurement(view), mainOrientationHelper.getDecoratedMeasurement(view2)), this.mAreaRect, layoutStateWrapper, layoutManagerHelper);
            int decoratedMeasurementInOther2 = this.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurementInOther(view);
            layoutChildWithMargin(view, this.mAreaRect.left, this.mAreaRect.top, this.mAreaRect.right, decoratedMeasurementInOther2, layoutManagerHelper);
            layoutChildWithMargin(view2, this.mAreaRect.left, decoratedMeasurementInOther2, this.mAreaRect.right, decoratedMeasurementInOther2 + mainOrientationHelper.getDecoratedMeasurementInOther(view2), layoutManagerHelper);
            i5 = (this.mAreaRect.right - this.mAreaRect.left) + (this.hasHeader ? 0 : this.mMarginLeft + this.mPaddingRight);
            if (this.hasFooter) {
                i8 = 0;
            } else {
                i6 = this.mMarginRight;
                i7 = this.mPaddingRight;
                i8 = i6 + i7;
            }
        }
        int i17 = i5 + i8;
        handleStateOnResult(layoutChunkResult, this.mChildrenViews);
        return i17;
    }

    private int handleThree(VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        int i5;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        View view = this.mChildrenViews[0];
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        View view2 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[2] : this.mChildrenViews[1];
        View view3 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[1] : this.mChildrenViews[2];
        VirtualLayoutManager.LayoutParams layoutParams2 = (VirtualLayoutManager.LayoutParams) view2.getLayoutParams();
        VirtualLayoutManager.LayoutParams layoutParams3 = (VirtualLayoutManager.LayoutParams) view3.getLayoutParams();
        float viewMainWeight = getViewMainWeight(0);
        float viewMainWeight2 = getViewMainWeight(1);
        float viewMainWeight3 = getViewMainWeight(2);
        if (z) {
            if (!Float.isNaN(this.mAspectRatio)) {
                layoutParams.height = (int) ((i - i3) / this.mAspectRatio);
            }
            layoutParams2.topMargin = layoutParams.topMargin;
            layoutParams3.bottomMargin = layoutParams.bottomMargin;
            layoutParams3.leftMargin = layoutParams2.leftMargin;
            layoutParams3.rightMargin = layoutParams2.rightMargin;
            int i6 = ((((i - i3) - layoutParams.leftMargin) - layoutParams.rightMargin) - layoutParams2.leftMargin) - layoutParams2.rightMargin;
            int i7 = Float.isNaN(viewMainWeight) ? (int) ((i6 / 2.0f) + 0.5f) : (int) (((i6 * viewMainWeight) / 100.0f) + 0.5f);
            int i8 = Float.isNaN(viewMainWeight2) ? i6 - i7 : (int) (((double) ((i6 * viewMainWeight2) / 100.0f)) + 0.5d);
            int i9 = Float.isNaN(viewMainWeight3) ? i8 : (int) (((double) ((i6 * viewMainWeight3) / 100.0f)) + 0.5d);
            layoutManagerHelper.measureChildWithMargins(view, View.MeasureSpec.makeMeasureSpec(i7 + layoutParams.leftMargin + layoutParams.rightMargin, 1073741824), layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentHeight(), layoutParams.height, true));
            int measuredHeight = view.getMeasuredHeight();
            int i10 = (int) ((Float.isNaN(this.mRowWeight) ? ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) / 2.0f : (((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) * this.mRowWeight) / 100.0f) + 0.5f);
            int i11 = ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) - i10;
            layoutManagerHelper.measureChildWithMargins(view2, View.MeasureSpec.makeMeasureSpec(i8 + layoutParams2.leftMargin + layoutParams2.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams2.topMargin + i10 + layoutParams2.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view3, View.MeasureSpec.makeMeasureSpec(i9 + layoutParams3.leftMargin + layoutParams3.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams3.topMargin + i11 + layoutParams3.bottomMargin, 1073741824));
            calculateRect(Math.max(measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin, i10 + layoutParams2.topMargin + layoutParams2.bottomMargin + i11 + layoutParams3.topMargin + layoutParams3.bottomMargin), this.mAreaRect, layoutStateWrapper, layoutManagerHelper);
            int decoratedMeasurementInOther = this.mAreaRect.left + mainOrientationHelper.getDecoratedMeasurementInOther(view);
            layoutChildWithMargin(view, this.mAreaRect.left, this.mAreaRect.top, decoratedMeasurementInOther, this.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view2, decoratedMeasurementInOther, this.mAreaRect.top, decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view2), this.mAreaRect.top + view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin, layoutManagerHelper);
            layoutChildWithMargin(view3, decoratedMeasurementInOther, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view3), decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view3), this.mAreaRect.bottom, layoutManagerHelper);
            i5 = (this.hasFooter ? 0 : this.mMarginBottom + this.mPaddingBottom) + (this.mAreaRect.bottom - this.mAreaRect.top) + (this.hasHeader ? 0 : this.mMarginTop + this.mPaddingTop);
        } else {
            i5 = 0;
        }
        handleStateOnResult(layoutChunkResult, this.mChildrenViews);
        return i5;
    }

    private int handleFour(VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        int i5;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        View view = this.mChildrenViews[0];
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        View view2 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[3] : this.mChildrenViews[1];
        VirtualLayoutManager.LayoutParams layoutParams2 = (VirtualLayoutManager.LayoutParams) view2.getLayoutParams();
        View view3 = this.mChildrenViews[2];
        VirtualLayoutManager.LayoutParams layoutParams3 = (VirtualLayoutManager.LayoutParams) view3.getLayoutParams();
        View view4 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[1] : this.mChildrenViews[3];
        VirtualLayoutManager.LayoutParams layoutParams4 = (VirtualLayoutManager.LayoutParams) view4.getLayoutParams();
        float viewMainWeight = getViewMainWeight(0);
        float viewMainWeight2 = getViewMainWeight(1);
        float viewMainWeight3 = getViewMainWeight(2);
        float viewMainWeight4 = getViewMainWeight(3);
        if (z) {
            layoutParams2.topMargin = layoutParams.topMargin;
            int i6 = layoutParams.bottomMargin;
            layoutParams4.bottomMargin = i6;
            layoutParams3.bottomMargin = i6;
            layoutParams3.leftMargin = layoutParams2.leftMargin;
            layoutParams4.rightMargin = layoutParams2.rightMargin;
            if (!Float.isNaN(this.mAspectRatio)) {
                layoutParams.height = (int) ((i - i3) / this.mAspectRatio);
            }
            int i7 = ((((i - i3) - layoutParams.leftMargin) - layoutParams.rightMargin) - layoutParams2.leftMargin) - layoutParams2.rightMargin;
            int i8 = (int) ((Float.isNaN(viewMainWeight) ? i7 / 2.0f : (i7 * viewMainWeight) / 100.0f) + 0.5f);
            int i9 = Float.isNaN(viewMainWeight2) ? i7 - i8 : (int) (((i7 * viewMainWeight2) / 100.0f) + 0.5f);
            int i10 = Float.isNaN(viewMainWeight3) ? (int) ((((i9 - layoutParams3.rightMargin) - layoutParams4.leftMargin) / 2.0f) + 0.5f) : (int) (((i7 * viewMainWeight3) / 100.0f) + 0.5f);
            int i11 = Float.isNaN(viewMainWeight4) ? ((i9 - layoutParams3.rightMargin) - layoutParams4.leftMargin) - i10 : (int) (((i7 * viewMainWeight4) / 100.0f) + 0.5f);
            layoutManagerHelper.measureChildWithMargins(view, View.MeasureSpec.makeMeasureSpec(i8 + layoutParams.leftMargin + layoutParams.rightMargin, 1073741824), layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentHeight(), layoutParams.height, true));
            int measuredHeight = view.getMeasuredHeight();
            int i12 = (int) ((Float.isNaN(this.mRowWeight) ? ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) / 2.0f : (((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) * this.mRowWeight) / 100.0f) + 0.5f);
            int i13 = ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) - i12;
            layoutManagerHelper.measureChildWithMargins(view2, View.MeasureSpec.makeMeasureSpec(i9 + layoutParams2.leftMargin + layoutParams2.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams2.topMargin + i12 + layoutParams2.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view3, View.MeasureSpec.makeMeasureSpec(i10 + layoutParams3.leftMargin + layoutParams3.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams3.topMargin + i13 + layoutParams3.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view4, View.MeasureSpec.makeMeasureSpec(i11 + layoutParams4.leftMargin + layoutParams4.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams4.topMargin + i13 + layoutParams4.bottomMargin, 1073741824));
            calculateRect(Math.max(measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin, i12 + layoutParams2.topMargin + layoutParams2.bottomMargin + Math.max(layoutParams3.topMargin + i13 + layoutParams3.bottomMargin, i13 + layoutParams4.topMargin + layoutParams4.bottomMargin)), this.mAreaRect, layoutStateWrapper, layoutManagerHelper);
            int decoratedMeasurementInOther = this.mAreaRect.left + mainOrientationHelper.getDecoratedMeasurementInOther(view);
            layoutChildWithMargin(view, this.mAreaRect.left, this.mAreaRect.top, decoratedMeasurementInOther, this.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view2, decoratedMeasurementInOther, this.mAreaRect.top, decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view2), this.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view2), layoutManagerHelper);
            int decoratedMeasurementInOther2 = decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view3);
            layoutChildWithMargin(view3, decoratedMeasurementInOther, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view3), decoratedMeasurementInOther2, this.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view4, decoratedMeasurementInOther2, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view4), decoratedMeasurementInOther2 + mainOrientationHelper.getDecoratedMeasurementInOther(view4), this.mAreaRect.bottom, layoutManagerHelper);
            i5 = (this.hasFooter ? 0 : this.mMarginBottom + this.mPaddingBottom) + (this.mAreaRect.bottom - this.mAreaRect.top) + (this.hasHeader ? 0 : this.mMarginTop + this.mPaddingTop);
        } else {
            i5 = 0;
        }
        handleStateOnResult(layoutChunkResult, this.mChildrenViews);
        return i5;
    }

    private int handleFive(VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        int i5;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        View view = this.mChildrenViews[0];
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        View view2 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[4] : this.mChildrenViews[1];
        VirtualLayoutManager.LayoutParams layoutParams2 = (VirtualLayoutManager.LayoutParams) view2.getLayoutParams();
        View view3 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[3] : this.mChildrenViews[2];
        VirtualLayoutManager.LayoutParams layoutParams3 = (VirtualLayoutManager.LayoutParams) view3.getLayoutParams();
        View view4 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[2] : this.mChildrenViews[3];
        VirtualLayoutManager.LayoutParams layoutParams4 = (VirtualLayoutManager.LayoutParams) view4.getLayoutParams();
        boolean reverseLayout = layoutManagerHelper.getReverseLayout();
        View[] viewArr = this.mChildrenViews;
        View view5 = reverseLayout ? viewArr[1] : viewArr[4];
        VirtualLayoutManager.LayoutParams layoutParams5 = (VirtualLayoutManager.LayoutParams) view5.getLayoutParams();
        float viewMainWeight = getViewMainWeight(0);
        float viewMainWeight2 = getViewMainWeight(1);
        float viewMainWeight3 = getViewMainWeight(2);
        float viewMainWeight4 = getViewMainWeight(3);
        float viewMainWeight5 = getViewMainWeight(4);
        if (z) {
            layoutParams2.topMargin = layoutParams.topMargin;
            int i6 = layoutParams.bottomMargin;
            layoutParams4.bottomMargin = i6;
            layoutParams3.bottomMargin = i6;
            layoutParams3.leftMargin = layoutParams2.leftMargin;
            layoutParams4.rightMargin = layoutParams2.rightMargin;
            layoutParams5.rightMargin = layoutParams2.rightMargin;
            if (!Float.isNaN(this.mAspectRatio)) {
                layoutParams.height = (int) ((i - i3) / this.mAspectRatio);
            }
            int i7 = ((((i - i3) - layoutParams.leftMargin) - layoutParams.rightMargin) - layoutParams2.leftMargin) - layoutParams2.rightMargin;
            int i8 = (int) ((Float.isNaN(viewMainWeight) ? i7 / 2.0f : (i7 * viewMainWeight) / 100.0f) + 0.5f);
            int i9 = Float.isNaN(viewMainWeight2) ? i7 - i8 : (int) (((i7 * viewMainWeight2) / 100.0f) + 0.5f);
            int i10 = Float.isNaN(viewMainWeight3) ? (int) ((((i9 - layoutParams3.rightMargin) - layoutParams4.leftMargin) / 3.0f) + 0.5f) : (int) (((i7 * viewMainWeight3) / 100.0f) + 0.5f);
            int i11 = Float.isNaN(viewMainWeight4) ? (int) ((((i9 - layoutParams3.rightMargin) - layoutParams4.leftMargin) / 3.0f) + 0.5f) : (int) (((i7 * viewMainWeight4) / 100.0f) + 0.5f);
            int i12 = Float.isNaN(viewMainWeight5) ? (((i9 - layoutParams3.rightMargin) - layoutParams4.leftMargin) - i10) - i11 : (int) (((i7 * viewMainWeight5) / 100.0f) + 0.5f);
            layoutManagerHelper.measureChildWithMargins(view, View.MeasureSpec.makeMeasureSpec(i8 + layoutParams.leftMargin + layoutParams.rightMargin, 1073741824), layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentHeight(), layoutParams.height, true));
            int measuredHeight = view.getMeasuredHeight();
            int i13 = (int) ((Float.isNaN(this.mRowWeight) ? ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) / 2.0f : (((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) * this.mRowWeight) / 100.0f) + 0.5f);
            int i14 = ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) - i13;
            layoutManagerHelper.measureChildWithMargins(view2, View.MeasureSpec.makeMeasureSpec(i9 + layoutParams2.leftMargin + layoutParams2.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams2.topMargin + i13 + layoutParams2.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view3, View.MeasureSpec.makeMeasureSpec(i10 + layoutParams3.leftMargin + layoutParams3.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams3.topMargin + i14 + layoutParams3.bottomMargin, 1073741824));
            View view6 = view4;
            layoutManagerHelper.measureChildWithMargins(view6, View.MeasureSpec.makeMeasureSpec(i11 + layoutParams4.leftMargin + layoutParams4.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams4.topMargin + i14 + layoutParams4.bottomMargin, 1073741824));
            View view7 = view5;
            layoutManagerHelper.measureChildWithMargins(view7, View.MeasureSpec.makeMeasureSpec(i12 + layoutParams5.leftMargin + layoutParams5.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams5.topMargin + i14 + layoutParams5.bottomMargin, 1073741824));
            calculateRect(Math.max(measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin, i13 + layoutParams2.topMargin + layoutParams2.bottomMargin + Math.max(layoutParams3.topMargin + i14 + layoutParams3.bottomMargin, i14 + layoutParams4.topMargin + layoutParams4.bottomMargin)), this.mAreaRect, layoutStateWrapper, layoutManagerHelper);
            int decoratedMeasurementInOther = this.mAreaRect.left + mainOrientationHelper.getDecoratedMeasurementInOther(view);
            layoutChildWithMargin(view, this.mAreaRect.left, this.mAreaRect.top, decoratedMeasurementInOther, this.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view2, decoratedMeasurementInOther, this.mAreaRect.top, decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view2), this.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view2), layoutManagerHelper);
            int decoratedMeasurementInOther2 = decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view3);
            layoutChildWithMargin(view3, decoratedMeasurementInOther, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view3), decoratedMeasurementInOther2, this.mAreaRect.bottom, layoutManagerHelper);
            int decoratedMeasurementInOther3 = decoratedMeasurementInOther2 + mainOrientationHelper.getDecoratedMeasurementInOther(view6);
            layoutChildWithMargin(view6, decoratedMeasurementInOther2, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view6), decoratedMeasurementInOther2 + mainOrientationHelper.getDecoratedMeasurementInOther(view6), this.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view7, decoratedMeasurementInOther3, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view7), decoratedMeasurementInOther3 + mainOrientationHelper.getDecoratedMeasurementInOther(view7), this.mAreaRect.bottom, layoutManagerHelper);
            i5 = (this.hasFooter ? 0 : this.mMarginBottom + this.mPaddingBottom) + (this.mAreaRect.bottom - this.mAreaRect.top) + (this.hasHeader ? 0 : this.mMarginTop + this.mPaddingTop);
        } else {
            i5 = 0;
        }
        handleStateOnResult(layoutChunkResult, this.mChildrenViews);
        return i5;
    }
}
