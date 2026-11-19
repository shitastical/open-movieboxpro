package com.alibaba.android.vlayout.layout;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.OrientationHelperEx;
import com.alibaba.android.vlayout.VirtualLayoutManager;

/* JADX INFO: loaded from: classes4.dex */
public class SingleLayoutHelper extends ColumnLayoutHelper {
    private static final String TAG = "SingleLayoutHelper";
    private int mPos = -1;

    public SingleLayoutHelper() {
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

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRangeChange(int i, int i2) {
        this.mPos = i;
    }

    @Override // com.alibaba.android.vlayout.layout.ColumnLayoutHelper, com.alibaba.android.vlayout.layout.AbstractFullFillLayoutHelper, com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void layoutViews(RecyclerView.Recycler recycler, RecyclerView.State state, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int offset;
        int offset2;
        int i6;
        if (isOutOfRange(layoutStateWrapper.getCurrentPosition())) {
            return;
        }
        View next = layoutStateWrapper.next(recycler);
        if (next == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        layoutManagerHelper.addChildView(layoutStateWrapper, next);
        VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) next.getLayoutParams();
        boolean z = layoutManagerHelper.getOrientation() == 1;
        int contentWidth = (((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingLeft()) - layoutManagerHelper.getPaddingRight()) - getHorizontalMargin()) - getHorizontalPadding();
        int contentHeight = (((layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingTop()) - layoutManagerHelper.getPaddingBottom()) - getVerticalMargin()) - getVerticalPadding();
        if (!Float.isNaN(this.mAspectRatio)) {
            if (z) {
                contentHeight = (int) ((contentWidth / this.mAspectRatio) + 0.5f);
            } else {
                contentWidth = (int) ((contentHeight * this.mAspectRatio) + 0.5f);
            }
        }
        if (z) {
            int childMeasureSpec = layoutManagerHelper.getChildMeasureSpec(contentWidth, Float.isNaN(this.mAspectRatio) ? layoutParams.width : contentWidth, !z && Float.isNaN(this.mAspectRatio));
            if (Float.isNaN(layoutParams.mAspectRatio)) {
                i6 = Float.isNaN(this.mAspectRatio) ? layoutParams.height : contentHeight;
            } else {
                i6 = (int) ((contentWidth / layoutParams.mAspectRatio) + 0.5f);
            }
            layoutManagerHelper.measureChildWithMargins(next, childMeasureSpec, layoutManagerHelper.getChildMeasureSpec(contentHeight, i6, z && Float.isNaN(this.mAspectRatio)));
        } else {
            if (Float.isNaN(layoutParams.mAspectRatio)) {
                i = Float.isNaN(this.mAspectRatio) ? layoutParams.width : contentWidth;
            } else {
                i = (int) ((contentHeight * layoutParams.mAspectRatio) + 0.5f);
            }
            layoutManagerHelper.measureChildWithMargins(next, layoutManagerHelper.getChildMeasureSpec(contentWidth, i, !z && Float.isNaN(this.mAspectRatio)), layoutManagerHelper.getChildMeasureSpec(contentHeight, Float.isNaN(this.mAspectRatio) ? layoutParams.height : contentHeight, z && Float.isNaN(this.mAspectRatio)));
        }
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        layoutChunkResult.mConsumed = mainOrientationHelper.getDecoratedMeasurement(next);
        if (z) {
            int decoratedMeasurementInOther = contentWidth - mainOrientationHelper.getDecoratedMeasurementInOther(next);
            int i7 = (decoratedMeasurementInOther >= 0 ? decoratedMeasurementInOther : 0) / 2;
            int paddingLeft = this.mMarginLeft + this.mPaddingLeft + layoutManagerHelper.getPaddingLeft() + i7;
            int contentWidth2 = (((layoutManagerHelper.getContentWidth() - this.mMarginRight) - this.mPaddingRight) - layoutManagerHelper.getPaddingRight()) - i7;
            if (layoutStateWrapper.getLayoutDirection() == -1) {
                offset2 = (layoutStateWrapper.getOffset() - this.mMarginBottom) - this.mPaddingBottom;
                offset = offset2 - layoutChunkResult.mConsumed;
            } else {
                offset = this.mPaddingTop + layoutStateWrapper.getOffset() + this.mMarginTop;
                offset2 = layoutChunkResult.mConsumed + offset;
            }
            i2 = paddingLeft;
            i4 = offset2;
            i3 = contentWidth2;
            i5 = offset;
        } else {
            int decoratedMeasurementInOther2 = contentHeight - mainOrientationHelper.getDecoratedMeasurementInOther(next);
            int i8 = (decoratedMeasurementInOther2 >= 0 ? decoratedMeasurementInOther2 : 0) / 2;
            int paddingTop = layoutManagerHelper.getPaddingTop() + this.mMarginTop + this.mPaddingTop + i8;
            int contentHeight2 = (((layoutManagerHelper.getContentHeight() - (-this.mMarginBottom)) - this.mPaddingBottom) - layoutManagerHelper.getPaddingBottom()) - i8;
            if (layoutStateWrapper.getLayoutDirection() == -1) {
                int offset3 = (layoutStateWrapper.getOffset() - this.mMarginRight) - this.mPaddingRight;
                i3 = offset3;
                i2 = offset3 - layoutChunkResult.mConsumed;
            } else {
                int offset4 = layoutStateWrapper.getOffset() + this.mMarginLeft + this.mPaddingLeft;
                i2 = offset4;
                i3 = layoutChunkResult.mConsumed + offset4;
            }
            i4 = contentHeight2;
            i5 = paddingTop;
        }
        if (z) {
            layoutChunkResult.mConsumed += getVerticalMargin() + getVerticalPadding();
        } else {
            layoutChunkResult.mConsumed += getHorizontalMargin() + getHorizontalPadding();
        }
        layoutChildWithMargin(next, i2, i5, i3, i4, layoutManagerHelper);
    }
}
