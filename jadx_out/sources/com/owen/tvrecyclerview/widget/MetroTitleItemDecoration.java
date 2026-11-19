package com.owen.tvrecyclerview.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.utils.Loger;

/* JADX INFO: loaded from: classes5.dex */
public class MetroTitleItemDecoration extends RecyclerView.ItemDecoration {
    private Adapter mAdapter;
    private MetroGridLayoutManager.LayoutParams mTempItemLp;
    private View mTempTitleView;
    private final SparseArray<View> mTitleViews = new SparseArray<>();
    private final Rect mTempRect = new Rect();
    private int mTempSectionIndex = -1;

    public interface Adapter {
        View getTitleView(int i, RecyclerView recyclerView);
    }

    public MetroTitleItemDecoration(Adapter adapter) {
        this.mAdapter = adapter;
    }

    private void createAndMeasureTitleView(RecyclerView recyclerView) {
        Adapter adapter = this.mAdapter;
        if (adapter == null) {
            return;
        }
        View titleView = adapter.getTitleView(this.mTempItemLp.sectionIndex, recyclerView);
        this.mTempTitleView = titleView;
        if (titleView != null) {
            ViewGroup.LayoutParams layoutParams = titleView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(-1, -2);
                this.mTempTitleView.setLayoutParams(layoutParams);
            }
            int paddingLeft = recyclerView.getPaddingLeft() + this.mTempItemLp.leftMargin;
            int paddingRight = recyclerView.getPaddingRight() + this.mTempItemLp.rightMargin;
            int paddingTop = recyclerView.getPaddingTop() + this.mTempItemLp.topMargin;
            int paddingBottom = recyclerView.getPaddingBottom() + this.mTempItemLp.bottomMargin;
            int measuredWidth = layoutParams.width < 0 ? (recyclerView.getMeasuredWidth() - paddingLeft) - paddingRight : layoutParams.width;
            int measuredHeight = layoutParams.height < 0 ? (recyclerView.getMeasuredHeight() - paddingTop) - paddingBottom : layoutParams.height;
            this.mTempTitleView.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, layoutParams.width == -2 ? Integer.MIN_VALUE : 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, layoutParams.width != -2 ? 1073741824 : Integer.MIN_VALUE));
            this.mTitleViews.put(this.mTempItemLp.sectionIndex, this.mTempTitleView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        this.mTempItemLp = (MetroGridLayoutManager.LayoutParams) view.getLayoutParams();
        Loger.i("ViewAdapterPosition=" + this.mTempItemLp.getViewAdapterPosition() + " isSectionStart=" + this.mTempItemLp.isSectionStart + " Decorated Top=" + recyclerView.getLayoutManager().getTopDecorationHeight(view));
        if (this.mTempItemLp.isSectionStart) {
            View view2 = this.mTitleViews.get(this.mTempItemLp.sectionIndex);
            this.mTempTitleView = view2;
            if (view2 == null) {
                createAndMeasureTitleView(recyclerView);
            }
            if (this.mTempTitleView != null) {
                boolean zIsVertical = ((MetroGridLayoutManager) recyclerView.getLayoutManager()).isVertical();
                rect.set(zIsVertical ? 0 : this.mTempTitleView.getMeasuredWidth(), zIsVertical ? this.mTempTitleView.getMeasuredHeight() : 0, 0, 0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            MetroGridLayoutManager.LayoutParams layoutParams = (MetroGridLayoutManager.LayoutParams) recyclerView.getChildAt(i).getLayoutParams();
            this.mTempItemLp = layoutParams;
            if (this.mTempSectionIndex != layoutParams.sectionIndex && this.mTempItemLp.isSectionStart) {
                View view = this.mTitleViews.get(this.mTempItemLp.sectionIndex);
                this.mTempTitleView = view;
                if (view != null) {
                    View childAt = recyclerView.getChildAt(i);
                    recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.mTempRect);
                    int left = childAt.getLeft();
                    int i2 = this.mTempRect.top;
                    int measuredWidth = this.mTempTitleView.getMeasuredWidth() + left;
                    int measuredHeight = this.mTempTitleView.getMeasuredHeight() + i2;
                    canvas.save();
                    this.mTempTitleView.layout(left, i2, measuredWidth, measuredHeight);
                    canvas.translate(left, i2);
                    this.mTempTitleView.draw(canvas);
                    canvas.restore();
                    Loger.i("mTitleView.draw ... sectionIndex=" + this.mTempItemLp.sectionIndex);
                }
                this.mTempSectionIndex = this.mTempItemLp.sectionIndex;
            }
        }
        this.mTempSectionIndex = -1;
        this.mTempItemLp = null;
        this.mTempTitleView = null;
    }
}
