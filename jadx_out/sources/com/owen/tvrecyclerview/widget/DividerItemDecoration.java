package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.BaseLayoutManager;
import com.owen.tvrecyclerview.R;

/* JADX INFO: loaded from: classes5.dex */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private final Drawable mHorizontalDivider;
    private final ItemSpacingOffsets mItemSpacing;
    private final Drawable mVerticalDivider;

    public DividerItemDecoration(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DividerItemDecoration(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView_DividerItemDecoration, i, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.TvRecyclerView_DividerItemDecoration_android_divider);
        if (drawable != null) {
            this.mHorizontalDivider = drawable;
            this.mVerticalDivider = drawable;
        } else {
            this.mVerticalDivider = typedArrayObtainStyledAttributes.getDrawable(R.styleable.TvRecyclerView_DividerItemDecoration_tv_verticalDivider);
            this.mHorizontalDivider = typedArrayObtainStyledAttributes.getDrawable(R.styleable.TvRecyclerView_DividerItemDecoration_tv_horizontalDivider);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mItemSpacing = createSpacing(this.mVerticalDivider, this.mHorizontalDivider);
    }

    public DividerItemDecoration(Drawable drawable) {
        this(drawable, drawable);
    }

    public DividerItemDecoration(Drawable drawable, Drawable drawable2) {
        this.mVerticalDivider = drawable;
        this.mHorizontalDivider = drawable2;
        this.mItemSpacing = createSpacing(drawable, drawable2);
    }

    private static ItemSpacingOffsets createSpacing(Drawable drawable, Drawable drawable2) {
        ItemSpacingOffsets itemSpacingOffsets = new ItemSpacingOffsets(drawable2 != null ? drawable2.getIntrinsicHeight() : 0, drawable != null ? drawable.getIntrinsicWidth() : 0);
        itemSpacingOffsets.setAddSpacingAtEnd(true);
        return itemSpacingOffsets;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
        BaseLayoutManager baseLayoutManager = (BaseLayoutManager) recyclerView.getLayoutManager();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = recyclerView.getChildAt(i);
            int decoratedLeft = baseLayoutManager.getDecoratedLeft(childAt);
            int decoratedTop = baseLayoutManager.getDecoratedTop(childAt);
            int decoratedRight = baseLayoutManager.getDecoratedRight(childAt);
            int decoratedBottom = baseLayoutManager.getDecoratedBottom(childAt);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int bottom = (decoratedBottom - childAt.getBottom()) - marginLayoutParams.bottomMargin;
            if (bottom > 0 && decoratedBottom < height) {
                int i2 = decoratedBottom - bottom;
                this.mHorizontalDivider.setBounds(decoratedLeft, i2, decoratedRight, this.mHorizontalDivider.getIntrinsicHeight() + i2);
                this.mHorizontalDivider.draw(canvas);
            }
            int right = (decoratedRight - childAt.getRight()) - marginLayoutParams.rightMargin;
            if (right > 0 && decoratedRight < width) {
                int i3 = decoratedRight - right;
                this.mVerticalDivider.setBounds(i3, decoratedTop, this.mVerticalDivider.getIntrinsicWidth() + i3, decoratedBottom);
                this.mVerticalDivider.draw(canvas);
            }
            i++;
            baseLayoutManager = baseLayoutManager;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
        this.mItemSpacing.getItemOffsets(rect, i, recyclerView);
    }
}
