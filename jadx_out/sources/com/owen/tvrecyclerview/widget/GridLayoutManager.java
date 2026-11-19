package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.BaseLayoutManager;
import com.owen.tvrecyclerview.Lanes;
import com.owen.tvrecyclerview.R;
import com.owen.tvrecyclerview.TwoWayLayoutManager;

/* JADX INFO: loaded from: classes5.dex */
public class GridLayoutManager extends BaseLayoutManager {
    private static final int DEFAULT_NUM_COLS = 2;
    private static final int DEFAULT_NUM_ROWS = 2;
    private static final String LOGTAG = "GridLayoutManager";
    private int mNumColumns;
    private int mNumRows;

    public GridLayoutManager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 2, 2);
    }

    protected GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView, i, 0);
        this.mNumColumns = Math.max(1, typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_tv_numColumns, i2));
        this.mNumRows = Math.max(1, typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_tv_numRows, i3));
        typedArrayObtainStyledAttributes.recycle();
    }

    public GridLayoutManager(TwoWayLayoutManager.Orientation orientation, int i, int i2) {
        super(orientation);
        this.mNumColumns = i;
        this.mNumRows = i2;
        if (i < 1) {
            throw new IllegalArgumentException("GridLayoutManager must have at least 1 column");
        }
        if (i2 < 1) {
            throw new IllegalArgumentException("GridLayoutManager must have at least 1 row");
        }
    }

    public GridLayoutManager(TwoWayLayoutManager.Orientation orientation) {
        this(orientation, 2, 2);
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    public int getLaneCount() {
        return isVertical() ? this.mNumColumns : this.mNumRows;
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    public void getLaneForPosition(Lanes.LaneInfo laneInfo, int i, TwoWayLayoutManager.Direction direction) {
        int laneCount = i % getLaneCount();
        laneInfo.set(laneCount, laneCount);
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    protected void moveLayoutToPosition(int i, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Lanes lanes = getLanes();
        lanes.reset(i2);
        getLaneForPosition(this.mTempLaneInfo, i, TwoWayLayoutManager.Direction.END);
        int i3 = this.mTempLaneInfo.startLane;
        if (i3 == 0) {
            return;
        }
        View viewForPosition = recycler.getViewForPosition(i);
        measureChild(viewForPosition, TwoWayLayoutManager.Direction.END);
        int decoratedMeasuredHeight = isVertical() ? getDecoratedMeasuredHeight(viewForPosition) : getDecoratedMeasuredWidth(viewForPosition);
        for (int i4 = i3 - 1; i4 >= 0; i4--) {
            lanes.offset(i4, decoratedMeasuredHeight);
        }
    }

    public int getNumColumns() {
        return this.mNumColumns;
    }

    public void setNumColumns(int i) {
        if (this.mNumColumns == i) {
            return;
        }
        this.mNumColumns = i;
        if (isVertical()) {
            requestLayout();
        }
    }

    public int getNumRows() {
        return this.mNumRows;
    }

    public void setNumRows(int i) {
        if (this.mNumRows == i) {
            return;
        }
        this.mNumRows = i;
        if (isVertical()) {
            return;
        }
        requestLayout();
    }
}
