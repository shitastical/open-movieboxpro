package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.BaseLayoutManager;
import com.owen.tvrecyclerview.Lanes;
import com.owen.tvrecyclerview.TwoWayLayoutManager;

/* JADX INFO: loaded from: classes5.dex */
public class ListLayoutManager extends BaseLayoutManager {
    private static final String LOGTAG = "ListLayoutManager";

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    public int getLaneCount() {
        return 1;
    }

    public ListLayoutManager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListLayoutManager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ListLayoutManager(Context context, TwoWayLayoutManager.Orientation orientation) {
        super(orientation);
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    public void getLaneForPosition(Lanes.LaneInfo laneInfo, int i, TwoWayLayoutManager.Direction direction) {
        laneInfo.set(0, 0);
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    protected void moveLayoutToPosition(int i, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        getLanes().reset(i2);
    }
}
