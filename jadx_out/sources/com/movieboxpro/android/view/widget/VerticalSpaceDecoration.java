package com.movieboxpro.android.view.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class VerticalSpaceDecoration extends RecyclerView.ItemDecoration {
    private boolean lastHasSpace;
    private final int mVerticalSpaceHeight;

    public VerticalSpaceDecoration(int mVerticalSpaceHeight) {
        this(mVerticalSpaceHeight, false);
    }

    public VerticalSpaceDecoration(int mVerticalSpaceHeight, boolean lastHasSpace) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        this.lastHasSpace = lastHasSpace;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = this.mVerticalSpaceHeight;
        } else if (this.lastHasSpace) {
            outRect.bottom = this.mVerticalSpaceHeight;
        }
    }
}
