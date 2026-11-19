package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import me.jingbin.web.WebProgress;

/* JADX INFO: loaded from: classes5.dex */
public class V7GridLayoutManager extends androidx.recyclerview.widget.GridLayoutManager {
    private int mExtraLayoutSpace;

    public V7GridLayoutManager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        this.mExtraLayoutSpace = WebProgress.DO_END_PROGRESS_DURATION;
    }

    public V7GridLayoutManager(Context context, int i) {
        super(context, i);
        this.mExtraLayoutSpace = WebProgress.DO_END_PROGRESS_DURATION;
    }

    public V7GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
        this.mExtraLayoutSpace = WebProgress.DO_END_PROGRESS_DURATION;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        if (recyclerView instanceof TvRecyclerView) {
            return recyclerView.requestChildRectangleOnScreen(view, rect, z);
        }
        return super.requestChildRectangleOnScreen(recyclerView, view, rect, z, z2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        return this.mExtraLayoutSpace;
    }

    public void setExtraLayoutSpace(int i) {
        this.mExtraLayoutSpace = i;
    }
}
