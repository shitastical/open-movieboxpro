package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.jingbin.web.WebProgress;

/* JADX INFO: loaded from: classes5.dex */
public class V7LinearLayoutManager extends LinearLayoutManager {
    private int mExtraLayoutSpace;

    public V7LinearLayoutManager(Context context) {
        super(context);
        this.mExtraLayoutSpace = WebProgress.DO_END_PROGRESS_DURATION;
    }

    public V7LinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.mExtraLayoutSpace = WebProgress.DO_END_PROGRESS_DURATION;
    }

    public V7LinearLayoutManager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
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
