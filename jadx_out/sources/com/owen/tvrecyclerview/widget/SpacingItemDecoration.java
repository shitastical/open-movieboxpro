package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.R;

/* JADX INFO: loaded from: classes5.dex */
public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    protected int mHorizontalSpacing;
    protected final ItemSpacingOffsets mItemSpacing;
    protected int mVerticalSpacing;

    public SpacingItemDecoration(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SpacingItemDecoration(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView_SpacingItemDecoration, i, 0);
        this.mVerticalSpacing = Math.max(0, typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_SpacingItemDecoration_android_verticalSpacing, 0));
        this.mHorizontalSpacing = Math.max(0, typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_SpacingItemDecoration_android_horizontalSpacing, 0));
        typedArrayObtainStyledAttributes.recycle();
        int i2 = this.mHorizontalSpacing;
        this.mItemSpacing = new ItemSpacingOffsets(i2, i2);
    }

    public SpacingItemDecoration(int i, int i2) {
        this.mItemSpacing = new ItemSpacingOffsets(i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
        this.mItemSpacing.getItemOffsets(rect, i, recyclerView);
        Log.e("SpacingItemDecoration", "itemPosition=" + i + " , outRect.right" + rect.right + " , outRect.left=" + rect.left + " , outRect.top=" + rect.top + " , outRect.bottom=" + rect.bottom);
    }
}
