package com.owen.tvrecyclerview.widget;

import android.graphics.Rect;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.BaseLayoutManager;
import com.owen.tvrecyclerview.Lanes;
import com.owen.tvrecyclerview.TwoWayLayoutManager;

/* JADX INFO: loaded from: classes5.dex */
public class ItemSpacingOffsets {
    private boolean mAddSpacingAtEnd;
    private final int mHorizontalSpacing;
    private final Lanes.LaneInfo mTempLaneInfo = new Lanes.LaneInfo();
    private final int mVerticalSpacing;

    public ItemSpacingOffsets(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Spacings should be equal or greater than 0");
        }
        this.mVerticalSpacing = i;
        this.mHorizontalSpacing = i2;
    }

    public int getSpacingSize(BaseLayoutManager baseLayoutManager) {
        if (baseLayoutManager == null) {
            return 0;
        }
        if (baseLayoutManager.isVertical()) {
            return this.mHorizontalSpacing;
        }
        return this.mVerticalSpacing;
    }

    public int getVerticalSpacing() {
        return this.mVerticalSpacing;
    }

    public int getHorizontalSpacing() {
        return this.mHorizontalSpacing;
    }

    public boolean isSecondLane(BaseLayoutManager baseLayoutManager, int i, int i2) {
        if (i2 == 0 || i == 0) {
            return false;
        }
        int i3 = i - 1;
        int i4 = -1;
        while (i3 >= 0) {
            baseLayoutManager.getLaneForPosition(this.mTempLaneInfo, i3, TwoWayLayoutManager.Direction.END);
            i4 = this.mTempLaneInfo.startLane;
            if (i4 != i2) {
                break;
            }
            i3--;
        }
        return i4 == 0 && i2 == i4 + baseLayoutManager.getLaneSpanForPosition(i3);
    }

    public static boolean isFirstChildInLane(BaseLayoutManager baseLayoutManager, int i) {
        int count = baseLayoutManager.getLanes().getCount();
        if (i >= count) {
            return false;
        }
        int laneSpanForPosition = 0;
        for (int i2 = 0; i2 < i; i2++) {
            laneSpanForPosition += baseLayoutManager.getLaneSpanForPosition(i2);
            if (laneSpanForPosition >= count) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLastChildInLane(BaseLayoutManager baseLayoutManager, int i, int i2) {
        int count = baseLayoutManager.getLanes().getCount();
        Log.d("ItemSpacingOffsets", "isLastChildInLane...itemPosition=" + i + " , itemCount=" + i2 + " , laneCount=" + count);
        return (i < i2 - count || (baseLayoutManager instanceof SpannableGridLayoutManager) || (baseLayoutManager instanceof StaggeredGridLayoutManager)) ? false : true;
    }

    public void setAddSpacingAtEnd(boolean z) {
        this.mAddSpacingAtEnd = z;
    }

    public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
        double d;
        int i2;
        double d2;
        int i3;
        BaseLayoutManager baseLayoutManager = (BaseLayoutManager) recyclerView.getLayoutManager();
        baseLayoutManager.getLaneForPosition(this.mTempLaneInfo, i, TwoWayLayoutManager.Direction.END);
        int i4 = this.mTempLaneInfo.startLane;
        int laneSpanForPosition = baseLayoutManager.getLaneSpanForPosition(i);
        int count = baseLayoutManager.getLanes().getCount();
        int itemCount = recyclerView.getAdapter().getItemCount();
        boolean zIsVertical = baseLayoutManager.isVertical();
        boolean z = i4 == 0;
        boolean zIsSecondLane = isSecondLane(baseLayoutManager, i, i4);
        int i5 = i4 + laneSpanForPosition;
        boolean z2 = i5 == count;
        boolean z3 = i5 == count - 1;
        int i6 = zIsVertical ? this.mHorizontalSpacing : this.mVerticalSpacing;
        if (z) {
            i2 = 0;
        } else {
            if (!z2 || zIsSecondLane) {
                d = (!zIsSecondLane || z2) ? ((double) i6) * 0.5d : ((double) i6) * 0.35d;
            } else {
                d = ((double) i6) * 0.65d;
            }
            i2 = (int) d;
        }
        if (z2) {
            i3 = 0;
        } else {
            if (!z || z3) {
                d2 = (!z3 || z) ? ((double) i6) * 0.5d : ((double) i6) * 0.35d;
            } else {
                d2 = ((double) i6) * 0.65d;
            }
            i3 = (int) d2;
        }
        boolean zIsFirstChildInLane = isFirstChildInLane(baseLayoutManager, i);
        boolean z4 = !this.mAddSpacingAtEnd && isLastChildInLane(baseLayoutManager, i, itemCount);
        if (zIsVertical) {
            rect.left = i2;
            rect.top = zIsFirstChildInLane ? 0 : this.mVerticalSpacing / 2;
            rect.right = i3;
            rect.bottom = z4 ? 0 : this.mVerticalSpacing / 2;
            return;
        }
        rect.left = zIsFirstChildInLane ? 0 : this.mHorizontalSpacing / 2;
        rect.top = i2;
        rect.right = z4 ? 0 : this.mHorizontalSpacing / 2;
        rect.bottom = i3;
    }
}
