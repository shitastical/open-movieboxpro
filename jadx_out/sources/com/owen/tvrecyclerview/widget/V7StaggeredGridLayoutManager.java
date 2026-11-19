package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class V7StaggeredGridLayoutManager extends androidx.recyclerview.widget.StaggeredGridLayoutManager {
    public V7StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
    }

    public V7StaggeredGridLayoutManager(int i, int i2) {
        super(i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        if (recyclerView instanceof TvRecyclerView) {
            return recyclerView.requestChildRectangleOnScreen(view, rect, z);
        }
        return super.requestChildRectangleOnScreen(recyclerView, view, rect, z, z2);
    }
}
