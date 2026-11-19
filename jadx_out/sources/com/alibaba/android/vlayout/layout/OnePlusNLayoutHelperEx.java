package com.alibaba.android.vlayout.layout;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.OrientationHelperEx;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class OnePlusNLayoutHelperEx extends AbstractFullFillLayoutHelper {
    private static final String TAG = "OnePlusNLayoutHelper";
    private Rect mAreaRect;
    private View[] mChildrenViews;
    private float[] mColWeights;
    private float mRowWeight;

    public OnePlusNLayoutHelperEx() {
        this.mAreaRect = new Rect();
        this.mColWeights = new float[0];
        this.mRowWeight = Float.NaN;
        setItemCount(0);
    }

    public OnePlusNLayoutHelperEx(int i) {
        this(i, 0, 0, 0, 0);
    }

    public OnePlusNLayoutHelperEx(int i, int i2, int i3, int i4, int i5) {
        this.mAreaRect = new Rect();
        this.mColWeights = new float[0];
        this.mRowWeight = Float.NaN;
        setItemCount(i);
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRangeChange(int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 4) {
            throw new IllegalArgumentException("pls use OnePlusNLayoutHelper instead of OnePlusNLayoutHelperEx which childcount <= 5");
        }
        if (i3 > 6) {
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
        if (isOutOfRange(layoutStateWrapper.getCurrentPosition())) {
            return;
        }
        View[] viewArr = this.mChildrenViews;
        if (viewArr == null || viewArr.length != getItemCount()) {
            this.mChildrenViews = new View[getItemCount()];
        }
        int allChildren = getAllChildren(this.mChildrenViews, recycler, layoutStateWrapper, layoutChunkResult, layoutManagerHelper);
        if (allChildren != getItemCount()) {
            Log.w(TAG, "The real number of children is not match with range of LayoutHelper");
        }
        int iHandSeven = 0;
        boolean z = layoutManagerHelper.getOrientation() == 1;
        int contentWidth = layoutManagerHelper.getContentWidth();
        int contentHeight = layoutManagerHelper.getContentHeight();
        int paddingLeft = layoutManagerHelper.getPaddingLeft() + layoutManagerHelper.getPaddingRight() + getHorizontalMargin() + getHorizontalPadding();
        int paddingTop = layoutManagerHelper.getPaddingTop() + layoutManagerHelper.getPaddingBottom() + getVerticalMargin() + getVerticalPadding();
        if (allChildren == 5) {
            iHandSeven = handleFive(layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft, paddingTop);
        } else if (allChildren == 6) {
            iHandSeven = handSix(layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft, paddingTop);
        } else if (allChildren == 7) {
            iHandSeven = handSeven(layoutStateWrapper, layoutChunkResult, layoutManagerHelper, z, contentWidth, contentHeight, paddingLeft, paddingTop);
        }
        layoutChunkResult.mConsumed = iHandSeven;
        Arrays.fill(this.mChildrenViews, (Object) null);
    }

    private float getViewMainWeight(int i) {
        float[] fArr = this.mColWeights;
        if (fArr.length > i) {
            return fArr[i];
        }
        return Float.NaN;
    }

    @Override // com.alibaba.android.vlayout.layout.AbstractFullFillLayoutHelper, com.alibaba.android.vlayout.layout.MarginLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public int computeAlignOffset(int i, boolean z, boolean z2, LayoutManagerHelper layoutManagerHelper) {
        if (getItemCount() == 3) {
            if (i == 1 && z) {
                Log.w(TAG, "Should not happen after adjust anchor");
                return 0;
            }
        } else if (getItemCount() == 4 && i == 1 && z) {
            return 0;
        }
        if (layoutManagerHelper.getOrientation() == 1) {
            if (z) {
                return this.mMarginBottom + this.mPaddingBottom;
            }
            return (-this.mMarginTop) - this.mPaddingTop;
        }
        if (z) {
            return this.mMarginRight + this.mPaddingRight;
        }
        return (-this.mMarginLeft) - this.mPaddingLeft;
    }

    private int handleFive(VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        OnePlusNLayoutHelperEx onePlusNLayoutHelperEx;
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
            layoutParams5.rightMargin = layoutParams3.rightMargin;
            if (!Float.isNaN(this.mAspectRatio)) {
                layoutParams.height = (int) ((i - i3) / this.mAspectRatio);
            }
            int i7 = ((((((i - i3) - layoutParams.leftMargin) - layoutParams.rightMargin) - layoutParams2.leftMargin) - layoutParams2.rightMargin) - layoutParams3.leftMargin) - layoutParams3.rightMargin;
            int i8 = (int) ((Float.isNaN(viewMainWeight) ? i7 / 3.0f : (i7 * viewMainWeight) / 100.0f) + 0.5f);
            int i9 = Float.isNaN(viewMainWeight2) ? (i7 - i8) / 2 : (int) (((i7 * viewMainWeight2) / 100.0f) + 0.5f);
            int i10 = Float.isNaN(viewMainWeight3) ? i9 : (int) (((i7 * viewMainWeight3) / 100.0f) + 0.5f);
            int i11 = Float.isNaN(viewMainWeight4) ? i9 : (int) (((i7 * viewMainWeight4) / 100.0f) + 0.5f);
            int i12 = Float.isNaN(viewMainWeight5) ? i9 : (int) (((i7 * viewMainWeight5) / 100.0f) + 0.5f);
            layoutManagerHelper.measureChildWithMargins(view, View.MeasureSpec.makeMeasureSpec(i8 + layoutParams.leftMargin + layoutParams.rightMargin, 1073741824), layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentHeight(), layoutParams.height, true));
            int measuredHeight = view.getMeasuredHeight();
            int i13 = (int) ((Float.isNaN(this.mRowWeight) ? ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) / 2.0f : (((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) * this.mRowWeight) / 100.0f) + 0.5f);
            int i14 = ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) - i13;
            layoutManagerHelper.measureChildWithMargins(view2, View.MeasureSpec.makeMeasureSpec(i9 + layoutParams2.leftMargin + layoutParams2.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams2.topMargin + i13 + layoutParams2.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view3, View.MeasureSpec.makeMeasureSpec(i10 + layoutParams3.leftMargin + layoutParams3.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams3.topMargin + i14 + layoutParams3.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view4, View.MeasureSpec.makeMeasureSpec(i11 + layoutParams4.leftMargin + layoutParams4.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams4.topMargin + i14 + layoutParams4.bottomMargin, 1073741824));
            View view6 = view5;
            layoutManagerHelper.measureChildWithMargins(view6, View.MeasureSpec.makeMeasureSpec(i12 + layoutParams5.leftMargin + layoutParams5.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams5.topMargin + i14 + layoutParams5.bottomMargin, 1073741824));
            int iMax = Math.max(measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin, i13 + layoutParams2.topMargin + layoutParams2.bottomMargin + Math.max(layoutParams3.topMargin + i14 + layoutParams3.bottomMargin, i14 + layoutParams4.topMargin + layoutParams4.bottomMargin)) + getVerticalMargin() + getVerticalPadding();
            onePlusNLayoutHelperEx = this;
            onePlusNLayoutHelperEx.calculateRect((iMax - getVerticalMargin()) - getVerticalPadding(), onePlusNLayoutHelperEx.mAreaRect, layoutStateWrapper, layoutManagerHelper);
            int decoratedMeasurementInOther = onePlusNLayoutHelperEx.mAreaRect.left + mainOrientationHelper.getDecoratedMeasurementInOther(view);
            layoutChildWithMargin(view, onePlusNLayoutHelperEx.mAreaRect.left, onePlusNLayoutHelperEx.mAreaRect.top, decoratedMeasurementInOther, onePlusNLayoutHelperEx.mAreaRect.bottom, layoutManagerHelper);
            int decoratedMeasurementInOther2 = decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view2);
            layoutChildWithMargin(view2, decoratedMeasurementInOther, onePlusNLayoutHelperEx.mAreaRect.top, decoratedMeasurementInOther2, onePlusNLayoutHelperEx.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view2), layoutManagerHelper);
            layoutChildWithMargin(view3, decoratedMeasurementInOther2, onePlusNLayoutHelperEx.mAreaRect.top, decoratedMeasurementInOther2 + mainOrientationHelper.getDecoratedMeasurementInOther(view3), onePlusNLayoutHelperEx.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view3), layoutManagerHelper);
            int decoratedMeasurementInOther3 = decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view4);
            layoutChildWithMargin(view4, decoratedMeasurementInOther, onePlusNLayoutHelperEx.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view4), decoratedMeasurementInOther3, onePlusNLayoutHelperEx.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view6, decoratedMeasurementInOther3, onePlusNLayoutHelperEx.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view6), decoratedMeasurementInOther3 + mainOrientationHelper.getDecoratedMeasurementInOther(view6), onePlusNLayoutHelperEx.mAreaRect.bottom, layoutManagerHelper);
            i5 = iMax;
        } else {
            onePlusNLayoutHelperEx = this;
            i5 = 0;
        }
        onePlusNLayoutHelperEx.handleStateOnResult(layoutChunkResult, onePlusNLayoutHelperEx.mChildrenViews);
        return i5;
    }

    private int handSix(VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        OnePlusNLayoutHelperEx onePlusNLayoutHelperEx;
        int i5;
        int i6;
        int i7;
        VirtualLayoutManager.LayoutParams layoutParams;
        float f;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        View view = this.mChildrenViews[0];
        VirtualLayoutManager.LayoutParams layoutParams2 = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        View view2 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[5] : this.mChildrenViews[1];
        VirtualLayoutManager.LayoutParams layoutParams3 = (VirtualLayoutManager.LayoutParams) view2.getLayoutParams();
        View view3 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[4] : this.mChildrenViews[2];
        VirtualLayoutManager.LayoutParams layoutParams4 = (VirtualLayoutManager.LayoutParams) view3.getLayoutParams();
        View view4 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[3] : this.mChildrenViews[3];
        VirtualLayoutManager.LayoutParams layoutParams5 = (VirtualLayoutManager.LayoutParams) view4.getLayoutParams();
        boolean reverseLayout = layoutManagerHelper.getReverseLayout();
        View[] viewArr = this.mChildrenViews;
        View view5 = reverseLayout ? viewArr[2] : viewArr[4];
        VirtualLayoutManager.LayoutParams layoutParams6 = (VirtualLayoutManager.LayoutParams) view5.getLayoutParams();
        boolean reverseLayout2 = layoutManagerHelper.getReverseLayout();
        View[] viewArr2 = this.mChildrenViews;
        View view6 = reverseLayout2 ? viewArr2[1] : viewArr2[5];
        VirtualLayoutManager.LayoutParams layoutParams7 = (VirtualLayoutManager.LayoutParams) view6.getLayoutParams();
        float viewMainWeight = getViewMainWeight(0);
        float viewMainWeight2 = getViewMainWeight(1);
        float viewMainWeight3 = getViewMainWeight(2);
        float viewMainWeight4 = getViewMainWeight(3);
        float viewMainWeight5 = getViewMainWeight(4);
        View view7 = view6;
        float viewMainWeight6 = getViewMainWeight(5);
        if (z) {
            View view8 = view5;
            layoutParams3.topMargin = layoutParams2.topMargin;
            int i8 = layoutParams2.bottomMargin;
            layoutParams5.bottomMargin = i8;
            layoutParams4.bottomMargin = i8;
            layoutParams4.leftMargin = layoutParams3.leftMargin;
            layoutParams5.rightMargin = layoutParams3.rightMargin;
            layoutParams6.rightMargin = layoutParams3.rightMargin;
            if (!Float.isNaN(this.mAspectRatio)) {
                layoutParams2.height = (int) ((i - i3) / this.mAspectRatio);
            }
            int i9 = i - i3;
            int i10 = (((i9 - layoutParams2.leftMargin) - layoutParams2.rightMargin) - layoutParams3.leftMargin) - layoutParams3.rightMargin;
            int i11 = (int) ((Float.isNaN(viewMainWeight) ? i10 / 2.0f : (i10 * viewMainWeight) / 100.0f) + 0.5f);
            int i12 = Float.isNaN(viewMainWeight2) ? i10 - i11 : (int) (((i10 * viewMainWeight2) / 100.0f) + 0.5f);
            if (Float.isNaN(viewMainWeight3)) {
                i6 = i12;
                i7 = i6;
            } else {
                i6 = i12;
                i7 = (int) (((double) ((i10 * viewMainWeight3) / 100.0f)) + 0.5d);
            }
            int i13 = (int) ((Float.isNaN(viewMainWeight4) ? ((((((i9 - layoutParams5.leftMargin) - layoutParams5.rightMargin) - layoutParams6.leftMargin) - layoutParams6.rightMargin) - layoutParams7.leftMargin) - layoutParams7.rightMargin) / 3.0f : (i10 * viewMainWeight4) / 100.0f) + 0.5f);
            int i14 = Float.isNaN(viewMainWeight5) ? i13 : (int) (((i10 * viewMainWeight5) / 100.0f) + 0.5f);
            int i15 = Float.isNaN(viewMainWeight6) ? i13 : (int) (((i10 * viewMainWeight6) / 100.0f) + 0.5f);
            layoutManagerHelper.measureChildWithMargins(view, View.MeasureSpec.makeMeasureSpec(i11 + layoutParams2.leftMargin + layoutParams2.rightMargin, 1073741824), layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentHeight(), layoutParams2.height, true));
            int measuredHeight = view.getMeasuredHeight();
            if (Float.isNaN(this.mRowWeight)) {
                layoutParams = layoutParams4;
                f = ((measuredHeight - layoutParams3.bottomMargin) - layoutParams.topMargin) / 2.0f;
            } else {
                layoutParams = layoutParams4;
                f = (((measuredHeight - layoutParams3.bottomMargin) - layoutParams.topMargin) * this.mRowWeight) / 100.0f;
            }
            int i16 = (int) (f + 0.5f);
            int i17 = ((measuredHeight - layoutParams3.bottomMargin) - layoutParams.topMargin) - i16;
            layoutManagerHelper.measureChildWithMargins(view2, View.MeasureSpec.makeMeasureSpec(i6 + layoutParams3.leftMargin + layoutParams3.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams3.topMargin + i16 + layoutParams3.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view3, View.MeasureSpec.makeMeasureSpec(i7 + layoutParams.leftMargin + layoutParams.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams.topMargin + i17 + layoutParams.bottomMargin, 1073741824));
            View view9 = view4;
            layoutManagerHelper.measureChildWithMargins(view9, View.MeasureSpec.makeMeasureSpec(i13 + layoutParams5.leftMargin + layoutParams5.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams5.topMargin + i17 + layoutParams5.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view8, View.MeasureSpec.makeMeasureSpec(i14 + layoutParams6.leftMargin + layoutParams6.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams6.topMargin + i17 + layoutParams6.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view7, View.MeasureSpec.makeMeasureSpec(i15 + layoutParams7.leftMargin + layoutParams7.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams7.topMargin + i17 + layoutParams7.bottomMargin, 1073741824));
            int iMax = Math.max(measuredHeight + layoutParams2.topMargin + layoutParams2.bottomMargin, (i16 + layoutParams3.topMargin + layoutParams3.bottomMargin) * 2) + Math.max(layoutParams5.topMargin + i17 + layoutParams5.bottomMargin, Math.max(layoutParams6.topMargin + i17 + layoutParams6.bottomMargin, i17 + layoutParams7.topMargin + layoutParams7.bottomMargin)) + getVerticalMargin() + getVerticalPadding();
            onePlusNLayoutHelperEx = this;
            onePlusNLayoutHelperEx.calculateRect((iMax - getVerticalMargin()) - getVerticalPadding(), onePlusNLayoutHelperEx.mAreaRect, layoutStateWrapper, layoutManagerHelper);
            int decoratedMeasurementInOther = onePlusNLayoutHelperEx.mAreaRect.left + mainOrientationHelper.getDecoratedMeasurementInOther(view);
            layoutChildWithMargin(view, onePlusNLayoutHelperEx.mAreaRect.left, onePlusNLayoutHelperEx.mAreaRect.top, decoratedMeasurementInOther, onePlusNLayoutHelperEx.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view9), layoutManagerHelper);
            layoutChildWithMargin(view2, decoratedMeasurementInOther, onePlusNLayoutHelperEx.mAreaRect.top, decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view2), onePlusNLayoutHelperEx.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view2), layoutManagerHelper);
            layoutChildWithMargin(view3, decoratedMeasurementInOther, onePlusNLayoutHelperEx.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view3), decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view3), onePlusNLayoutHelperEx.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view9), layoutManagerHelper);
            int decoratedMeasurementInOther2 = onePlusNLayoutHelperEx.mAreaRect.left + mainOrientationHelper.getDecoratedMeasurementInOther(view9);
            layoutChildWithMargin(view9, onePlusNLayoutHelperEx.mAreaRect.left, onePlusNLayoutHelperEx.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view9), decoratedMeasurementInOther2, onePlusNLayoutHelperEx.mAreaRect.bottom, layoutManagerHelper);
            int decoratedMeasurementInOther3 = decoratedMeasurementInOther2 + mainOrientationHelper.getDecoratedMeasurementInOther(view8);
            layoutChildWithMargin(view8, decoratedMeasurementInOther2, onePlusNLayoutHelperEx.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view8), decoratedMeasurementInOther3, onePlusNLayoutHelperEx.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view7, decoratedMeasurementInOther3, onePlusNLayoutHelperEx.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view7), decoratedMeasurementInOther3 + mainOrientationHelper.getDecoratedMeasurementInOther(view7), onePlusNLayoutHelperEx.mAreaRect.bottom, layoutManagerHelper);
            i5 = iMax;
        } else {
            onePlusNLayoutHelperEx = this;
            i5 = 0;
        }
        onePlusNLayoutHelperEx.handleStateOnResult(layoutChunkResult, onePlusNLayoutHelperEx.mChildrenViews);
        return i5;
    }

    private int handSeven(VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper, boolean z, int i, int i2, int i3, int i4) {
        int i5;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        View view = this.mChildrenViews[0];
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) view.getLayoutParams();
        View view2 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[6] : this.mChildrenViews[1];
        VirtualLayoutManager.LayoutParams layoutParams2 = (VirtualLayoutManager.LayoutParams) view2.getLayoutParams();
        View view3 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[5] : this.mChildrenViews[2];
        VirtualLayoutManager.LayoutParams layoutParams3 = (VirtualLayoutManager.LayoutParams) view3.getLayoutParams();
        View view4 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[4] : this.mChildrenViews[3];
        VirtualLayoutManager.LayoutParams layoutParams4 = (VirtualLayoutManager.LayoutParams) view4.getLayoutParams();
        boolean reverseLayout = layoutManagerHelper.getReverseLayout();
        View[] viewArr = this.mChildrenViews;
        View view5 = reverseLayout ? viewArr[3] : viewArr[4];
        VirtualLayoutManager.LayoutParams layoutParams5 = (VirtualLayoutManager.LayoutParams) view5.getLayoutParams();
        boolean reverseLayout2 = layoutManagerHelper.getReverseLayout();
        View[] viewArr2 = this.mChildrenViews;
        View view6 = reverseLayout2 ? viewArr2[2] : viewArr2[5];
        VirtualLayoutManager.LayoutParams layoutParams6 = (VirtualLayoutManager.LayoutParams) view6.getLayoutParams();
        View view7 = layoutManagerHelper.getReverseLayout() ? this.mChildrenViews[1] : this.mChildrenViews[6];
        VirtualLayoutManager.LayoutParams layoutParams7 = (VirtualLayoutManager.LayoutParams) view7.getLayoutParams();
        View view8 = view7;
        float viewMainWeight = getViewMainWeight(0);
        float viewMainWeight2 = getViewMainWeight(1);
        float viewMainWeight3 = getViewMainWeight(2);
        float viewMainWeight4 = getViewMainWeight(3);
        float viewMainWeight5 = getViewMainWeight(4);
        View view9 = view6;
        float viewMainWeight6 = getViewMainWeight(5);
        float viewMainWeight7 = getViewMainWeight(6);
        if (z) {
            View view10 = view5;
            if (!Float.isNaN(this.mAspectRatio)) {
                layoutParams.height = (int) ((i - i3) / this.mAspectRatio);
            }
            int i6 = ((((((i - i3) - layoutParams.leftMargin) - layoutParams.rightMargin) - layoutParams2.leftMargin) - layoutParams2.rightMargin) - layoutParams3.leftMargin) - layoutParams3.rightMargin;
            int i7 = (int) ((Float.isNaN(viewMainWeight) ? i6 / 3.0f : (i6 * viewMainWeight) / 100.0f) + 0.5f);
            int i8 = Float.isNaN(viewMainWeight2) ? (i6 - i7) / 2 : (int) (((i6 * viewMainWeight2) / 100.0f) + 0.5f);
            int i9 = Float.isNaN(viewMainWeight3) ? i8 : (int) (((double) ((i6 * viewMainWeight3) / 100.0f)) + 0.5d);
            int i10 = Float.isNaN(viewMainWeight4) ? i8 : (int) (((i6 * viewMainWeight4) / 100.0f) + 0.5f);
            int i11 = Float.isNaN(viewMainWeight5) ? i8 : (int) (((i6 * viewMainWeight5) / 100.0f) + 0.5f);
            int i12 = Float.isNaN(viewMainWeight6) ? i8 : (int) (((i6 * viewMainWeight6) / 100.0f) + 0.5f);
            int i13 = Float.isNaN(viewMainWeight6) ? i8 : (int) (((i6 * viewMainWeight7) / 100.0f) + 0.5f);
            layoutManagerHelper.measureChildWithMargins(view, View.MeasureSpec.makeMeasureSpec(i7 + layoutParams.leftMargin + layoutParams.rightMargin, 1073741824), layoutManagerHelper.getChildMeasureSpec(layoutManagerHelper.getContentHeight(), layoutParams.height, true));
            int measuredHeight = view.getMeasuredHeight();
            int i14 = (int) ((Float.isNaN(this.mRowWeight) ? ((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) / 3.0f : (((measuredHeight - layoutParams2.bottomMargin) - layoutParams3.topMargin) * this.mRowWeight) / 100.0f) + 0.5f);
            layoutManagerHelper.measureChildWithMargins(view2, View.MeasureSpec.makeMeasureSpec(i8 + layoutParams2.leftMargin + layoutParams2.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams2.topMargin + i14 + layoutParams2.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view3, View.MeasureSpec.makeMeasureSpec(i9 + layoutParams3.leftMargin + layoutParams3.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams3.topMargin + i14 + layoutParams3.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view4, View.MeasureSpec.makeMeasureSpec(i10 + layoutParams4.leftMargin + layoutParams4.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams4.topMargin + i14 + layoutParams4.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view10, View.MeasureSpec.makeMeasureSpec(i11 + layoutParams5.leftMargin + layoutParams5.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams5.topMargin + i14 + layoutParams5.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view9, View.MeasureSpec.makeMeasureSpec(i12 + layoutParams6.leftMargin + layoutParams6.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams6.topMargin + i14 + layoutParams6.bottomMargin, 1073741824));
            layoutManagerHelper.measureChildWithMargins(view8, View.MeasureSpec.makeMeasureSpec(i13 + layoutParams7.leftMargin + layoutParams7.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams7.topMargin + i14 + layoutParams7.bottomMargin, 1073741824));
            int iMax = Math.max(measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin, Math.max(layoutParams2.topMargin + i14 + layoutParams2.bottomMargin, layoutParams3.topMargin + i14 + layoutParams3.bottomMargin) + Math.max(layoutParams4.topMargin + i14 + layoutParams4.bottomMargin, layoutParams5.topMargin + i14 + layoutParams5.bottomMargin) + Math.max(layoutParams6.topMargin + i14 + layoutParams6.bottomMargin, i14 + layoutParams7.topMargin + layoutParams7.bottomMargin)) + getVerticalMargin() + getVerticalPadding();
            calculateRect((iMax - getVerticalMargin()) - getVerticalPadding(), this.mAreaRect, layoutStateWrapper, layoutManagerHelper);
            int decoratedMeasurementInOther = this.mAreaRect.left + mainOrientationHelper.getDecoratedMeasurementInOther(view);
            layoutChildWithMargin(view, this.mAreaRect.left, this.mAreaRect.top, decoratedMeasurementInOther, this.mAreaRect.bottom, layoutManagerHelper);
            int decoratedMeasurementInOther2 = decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view2);
            layoutChildWithMargin(view2, decoratedMeasurementInOther, this.mAreaRect.top, decoratedMeasurementInOther2, this.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view2), layoutManagerHelper);
            layoutChildWithMargin(view3, decoratedMeasurementInOther2, this.mAreaRect.top, decoratedMeasurementInOther2 + mainOrientationHelper.getDecoratedMeasurementInOther(view3), this.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view3), layoutManagerHelper);
            View view11 = view4;
            int decoratedMeasurementInOther3 = decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view11);
            layoutChildWithMargin(view11, decoratedMeasurementInOther, this.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view2), decoratedMeasurementInOther3, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view9), layoutManagerHelper);
            layoutChildWithMargin(view10, decoratedMeasurementInOther3, this.mAreaRect.top + mainOrientationHelper.getDecoratedMeasurement(view2), decoratedMeasurementInOther3 + mainOrientationHelper.getDecoratedMeasurementInOther(view10), this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view8), layoutManagerHelper);
            int decoratedMeasurementInOther4 = decoratedMeasurementInOther + mainOrientationHelper.getDecoratedMeasurementInOther(view9);
            layoutChildWithMargin(view9, decoratedMeasurementInOther, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view9), decoratedMeasurementInOther4, this.mAreaRect.bottom, layoutManagerHelper);
            layoutChildWithMargin(view8, decoratedMeasurementInOther4, this.mAreaRect.bottom - mainOrientationHelper.getDecoratedMeasurement(view8), decoratedMeasurementInOther4 + mainOrientationHelper.getDecoratedMeasurementInOther(view8), this.mAreaRect.bottom, layoutManagerHelper);
            i5 = iMax;
        } else {
            i5 = 0;
        }
        handleStateOnResult(layoutChunkResult, this.mChildrenViews);
        return i5;
    }
}
