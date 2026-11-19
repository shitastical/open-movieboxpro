package com.movieboxpro.android.view.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.DensityUtils;

/* JADX INFO: loaded from: classes5.dex */
public class LayoutManagerItemDecoration extends RecyclerView.ItemDecoration {
    private final int decoration;
    private int orientation;

    public LayoutManagerItemDecoration(int orientation, int decoration) {
        this.orientation = orientation;
        this.decoration = DensityUtils.dp2px(App.getContext(), decoration);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int itemCount = state.getItemCount() - 1;
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        if (childLayoutPosition == -1 || !(layoutManager instanceof LinearLayoutManager) || (layoutManager instanceof GridLayoutManager)) {
            return;
        }
        if (this.orientation != 1) {
            if (childLayoutPosition == itemCount) {
                outRect.set(0, 0, 0, 0);
                return;
            } else {
                outRect.set(0, 0, this.decoration, 0);
                return;
            }
        }
        outRect.set(0, 0, 0, this.decoration);
        if (childLayoutPosition == 0) {
            int i = this.decoration;
            outRect.set(0, i, 0, i);
        }
    }
}
