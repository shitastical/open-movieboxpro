package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.owen.tvrecyclerview.R;

/* JADX INFO: loaded from: classes5.dex */
public class VLayoutManager extends VirtualLayoutManager {
    public VLayoutManager(Context context) {
        super(context);
    }

    public VLayoutManager(Context context, int i) {
        super(context, i);
    }

    public VLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public VLayoutManager(Context context, AttributeSet attributeSet, int i) {
        super(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView, i, 0);
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_android_orientation, 1);
        typedArrayObtainStyledAttributes.recycle();
        setOrientation(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        if (recyclerView instanceof TvRecyclerView) {
            return recyclerView.requestChildRectangleOnScreen(view, rect, z);
        }
        return super.requestChildRectangleOnScreen(recyclerView, view, rect, z, z2);
    }
}
