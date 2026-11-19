package com.library.flowlayout;

import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class FlowLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "FlowLayoutManager";
    protected int height;
    private int left;
    private int right;
    private int top;
    private int usedMaxWidth;
    protected int width;
    final FlowLayoutManager self = this;
    private int verticalScrollOffset = 0;
    protected int totalHeight = 0;
    private Row row = new Row();
    private List<Row> lineRows = new ArrayList();
    private SparseArray<Rect> allItemFrames = new SparseArray<>();

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return true;
    }

    public int getTotalHeight() {
        return this.totalHeight;
    }

    public FlowLayoutManager() {
        setAutoMeasureEnabled(true);
    }

    public class Item {
        Rect rect;
        int useHeight;
        View view;

        public void setRect(Rect rect) {
            this.rect = rect;
        }

        public Item(int i, View view, Rect rect) {
            this.useHeight = i;
            this.view = view;
            this.rect = rect;
        }
    }

    public class Row {
        float cuTop;
        float maxHeight;
        List<Item> views = new ArrayList();

        public Row() {
        }

        public void setCuTop(float f) {
            this.cuTop = f;
        }

        public void setMaxHeight(float f) {
            this.maxHeight = f;
        }

        public void addViews(Item item) {
            this.views.add(item);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(TAG, "onLayoutChildren");
        this.totalHeight = 0;
        int i = this.top;
        this.row = new Row();
        this.lineRows.clear();
        this.allItemFrames.clear();
        removeAllViews();
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            this.verticalScrollOffset = 0;
            return;
        }
        if (getChildCount() == 0 && state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        if (getChildCount() == 0) {
            this.width = getWidth();
            this.height = getHeight();
            this.left = getPaddingLeft();
            this.right = getPaddingRight();
            this.top = getPaddingTop();
            this.usedMaxWidth = (this.width - this.left) - this.right;
        }
        int i2 = 0;
        int iMax = 0;
        for (int i3 = 0; i3 < getItemCount(); i3++) {
            Log.d(TAG, "index:" + i3);
            View viewForPosition = recycler.getViewForPosition(i3);
            if (8 != viewForPosition.getVisibility()) {
                measureChildWithMargins(viewForPosition, 0, 0);
                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                int i4 = i2 + decoratedMeasuredWidth;
                if (i4 <= this.usedMaxWidth) {
                    int i5 = this.left + i2;
                    Rect rect = this.allItemFrames.get(i3);
                    if (rect == null) {
                        rect = new Rect();
                    }
                    rect.set(i5, i, decoratedMeasuredWidth + i5, i + decoratedMeasuredHeight);
                    this.allItemFrames.put(i3, rect);
                    iMax = Math.max(iMax, decoratedMeasuredHeight);
                    this.row.addViews(new Item(decoratedMeasuredHeight, viewForPosition, rect));
                    this.row.setCuTop(i);
                    this.row.setMaxHeight(iMax);
                    i2 = i4;
                } else {
                    formatAboveRow();
                    i += iMax;
                    this.totalHeight += iMax;
                    int i6 = this.left;
                    Rect rect2 = this.allItemFrames.get(i3);
                    if (rect2 == null) {
                        rect2 = new Rect();
                    }
                    rect2.set(i6, i, i6 + decoratedMeasuredWidth, i + decoratedMeasuredHeight);
                    this.allItemFrames.put(i3, rect2);
                    this.row.addViews(new Item(decoratedMeasuredHeight, viewForPosition, rect2));
                    this.row.setCuTop(i);
                    this.row.setMaxHeight(decoratedMeasuredHeight);
                    i2 = decoratedMeasuredWidth;
                    iMax = decoratedMeasuredHeight;
                }
                if (i3 == getItemCount() - 1) {
                    formatAboveRow();
                    this.totalHeight += iMax;
                }
            }
        }
        this.totalHeight = Math.max(this.totalHeight, getVerticalSpace());
        Log.d(TAG, "onLayoutChildren totalHeight:" + this.totalHeight);
        fillLayout(recycler, state);
    }

    private void fillLayout(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout() || getItemCount() == 0) {
            return;
        }
        new Rect(getPaddingLeft(), getPaddingTop() + this.verticalScrollOffset, getWidth() - getPaddingRight(), this.verticalScrollOffset + (getHeight() - getPaddingBottom()));
        for (int i = 0; i < this.lineRows.size(); i++) {
            Row row = this.lineRows.get(i);
            float f = row.cuTop;
            float f2 = row.maxHeight;
            List<Item> list = row.views;
            for (int i2 = 0; i2 < list.size(); i2++) {
                View view = list.get(i2).view;
                measureChildWithMargins(view, 0, 0);
                addView(view);
                Rect rect = list.get(i2).rect;
                layoutDecoratedWithMargins(view, rect.left, rect.top - this.verticalScrollOffset, rect.right, rect.bottom - this.verticalScrollOffset);
            }
        }
    }

    private void formatAboveRow() {
        List<Item> list = this.row.views;
        for (int i = 0; i < list.size(); i++) {
            Item item = list.get(i);
            View view = item.view;
            int position = getPosition(view);
            if (this.allItemFrames.get(position).top < this.row.cuTop + ((this.row.maxHeight - list.get(i).useHeight) / 2.0f)) {
                Rect rect = this.allItemFrames.get(position);
                if (rect == null) {
                    rect = new Rect();
                }
                rect.set(this.allItemFrames.get(position).left, (int) (this.row.cuTop + ((this.row.maxHeight - list.get(i).useHeight) / 2.0f)), this.allItemFrames.get(position).right, (int) (this.row.cuTop + ((this.row.maxHeight - list.get(i).useHeight) / 2.0f) + getDecoratedMeasuredHeight(view)));
                this.allItemFrames.put(position, rect);
                item.setRect(rect);
                list.set(i, item);
            }
        }
        this.row.views = list;
        this.lineRows.add(this.row);
        this.row = new Row();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d("TAG", "totalHeight:" + this.totalHeight);
        int i2 = this.verticalScrollOffset;
        if (i2 + i < 0) {
            i = -i2;
        } else if (i2 + i > this.totalHeight - getVerticalSpace()) {
            i = (this.totalHeight - getVerticalSpace()) - this.verticalScrollOffset;
        }
        this.verticalScrollOffset += i;
        offsetChildrenVertical(-i);
        fillLayout(recycler, state);
        return i;
    }

    private int getVerticalSpace() {
        return (this.self.getHeight() - this.self.getPaddingBottom()) - this.self.getPaddingTop();
    }

    public int getHorizontalSpace() {
        return (this.self.getWidth() - this.self.getPaddingLeft()) - this.self.getPaddingRight();
    }
}
