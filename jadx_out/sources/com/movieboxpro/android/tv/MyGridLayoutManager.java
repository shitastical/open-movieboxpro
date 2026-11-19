package com.movieboxpro.android.tv;

import android.content.Context;
import android.util.AttributeSet;
import com.owen.tvrecyclerview.Lanes;
import com.owen.tvrecyclerview.TwoWayLayoutManager;
import com.owen.tvrecyclerview.widget.GridLayoutManager;

/* JADX INFO: loaded from: classes5.dex */
public class MyGridLayoutManager extends GridLayoutManager {
    public MyGridLayoutManager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected MyGridLayoutManager(Context context, AttributeSet attrs, int defStyle, int defaultNumColumns, int defaultNumRows) {
        super(context, attrs, defStyle, defaultNumColumns, defaultNumRows);
    }

    public MyGridLayoutManager(TwoWayLayoutManager.Orientation orientation, int numColumns, int numRows) {
        super(orientation, numColumns, numRows);
    }

    public MyGridLayoutManager(TwoWayLayoutManager.Orientation orientation) {
        super(orientation);
    }

    @Override // com.owen.tvrecyclerview.widget.GridLayoutManager, com.owen.tvrecyclerview.BaseLayoutManager
    public void getLaneForPosition(Lanes.LaneInfo outInfo, int position, TwoWayLayoutManager.Direction direction) {
        int laneCount = position % getLaneCount();
        outInfo.set(laneCount, laneCount);
    }
}
