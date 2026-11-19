package com.movieboxpro.android.view.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.DensityUtils;

/* JADX INFO: loaded from: classes5.dex */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private final int decoration;
    private boolean includeEdge;
    private int orientation;

    public LinearItemDecoration(int orientation, int decoration) {
        this.includeEdge = true;
        this.orientation = orientation;
        this.decoration = DensityUtils.dp2px(App.getContext(), decoration);
    }

    public LinearItemDecoration(int orientation, int decoration, boolean includeEdge) {
        this.includeEdge = true;
        this.orientation = orientation;
        this.decoration = DensityUtils.dp2px(App.getContext(), decoration);
        this.includeEdge = includeEdge;
    }

    public LinearItemDecoration(int decoration, boolean includeEdge) {
        this.includeEdge = true;
        this.orientation = 1;
        this.decoration = DensityUtils.dp2px(App.getContext(), decoration);
        this.includeEdge = includeEdge;
    }

    public LinearItemDecoration(int decoration) {
        this.includeEdge = true;
        this.orientation = 1;
        this.decoration = DensityUtils.dp2px(App.getContext(), decoration);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        state.getItemCount();
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        if (childLayoutPosition == -1 || !(layoutManager instanceof LinearLayoutManager) || (layoutManager instanceof GridLayoutManager)) {
            return;
        }
        if (this.orientation == 1) {
            if (!this.includeEdge) {
                if (childLayoutPosition == 0) {
                    int i = this.decoration;
                    outRect.set(0, i, 0, i);
                    return;
                } else {
                    outRect.set(0, 0, 0, this.decoration);
                    return;
                }
            }
            if (childLayoutPosition == 0) {
                int i2 = this.decoration;
                outRect.set(i2, i2, i2, i2);
                return;
            } else {
                int i3 = this.decoration;
                outRect.set(i3, 0, i3, i3);
                return;
            }
        }
        if (!this.includeEdge) {
            if (childLayoutPosition == 0) {
                int i4 = this.decoration;
                outRect.set(i4, 0, i4, 0);
                return;
            } else {
                outRect.set(0, 0, this.decoration, 0);
                return;
            }
        }
        if (childLayoutPosition == 0) {
            int i5 = this.decoration;
            outRect.set(i5, i5, i5, i5);
        } else {
            int i6 = this.decoration;
            outRect.set(0, i6, i6, i6);
        }
    }
}
