package com.movieboxpro.android.view.widget.sticky_recycler.view;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private boolean mCurrentUIFindStickView;
    private int mItemViewHeight;
    private LinearLayoutManager mLayoutManager;
    private View mStickyItemView;
    private int mStickyItemViewMarginTop;
    private RecyclerView.ViewHolder mViewHolder;
    private int mBindDataPosition = -1;
    private List<Integer> mStickyPositionList = new ArrayList();

    /* JADX WARN: Code duplicated, block: B:24:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:26:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:28:0x00bc  */
    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        View nextStickyView;
        int top;
        int i;
        super.onDrawOver(c, parent, state);
        if (parent.getAdapter().getItemCount() <= 0) {
            return;
        }
        this.mCurrentUIFindStickView = false;
        this.mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int childCount = parent.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = parent.getChildAt(i2);
            if (((Boolean) childAt.getTag()).booleanValue()) {
                this.mCurrentUIFindStickView = true;
                getStickyViewHolder(parent);
                cacheStickyViewPosition(i2);
                if (childAt.getTop() <= 0) {
                    bindDataForStickyView(this.mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());
                } else if (this.mStickyPositionList.size() > 0) {
                    if (this.mStickyPositionList.size() == 1) {
                        bindDataForStickyView(this.mStickyPositionList.get(0).intValue(), parent.getMeasuredWidth());
                    } else {
                        bindDataForStickyView(this.mStickyPositionList.get(this.mStickyPositionList.lastIndexOf(Integer.valueOf(this.mLayoutManager.findFirstVisibleItemPosition() + i2)) - 1).intValue(), parent.getMeasuredWidth());
                    }
                }
                if (childAt.getTop() > 0) {
                    int top2 = childAt.getTop();
                    int i3 = this.mItemViewHeight;
                    if (top2 <= i3) {
                        this.mStickyItemViewMarginTop = i3 - childAt.getTop();
                    } else {
                        this.mStickyItemViewMarginTop = 0;
                        nextStickyView = getNextStickyView(parent);
                        if (nextStickyView != null) {
                            top = nextStickyView.getTop();
                            i = this.mItemViewHeight;
                            if (top <= i) {
                                this.mStickyItemViewMarginTop = i - nextStickyView.getTop();
                            }
                        }
                    }
                } else {
                    this.mStickyItemViewMarginTop = 0;
                    nextStickyView = getNextStickyView(parent);
                    if (nextStickyView != null) {
                        top = nextStickyView.getTop();
                        i = this.mItemViewHeight;
                        if (top <= i) {
                            this.mStickyItemViewMarginTop = i - nextStickyView.getTop();
                        }
                    }
                }
                drawStickyItemView(c);
                break;
            }
        }
        if (this.mCurrentUIFindStickView) {
            return;
        }
        this.mStickyItemViewMarginTop = 0;
        if (this.mLayoutManager.findFirstVisibleItemPosition() + parent.getChildCount() == parent.getAdapter().getItemCount() && this.mStickyPositionList.size() > 0) {
            List<Integer> list = this.mStickyPositionList;
            bindDataForStickyView(list.get(list.size() - 1).intValue(), parent.getMeasuredWidth());
        }
        drawStickyItemView(c);
    }

    private View getNextStickyView(RecyclerView parent) {
        int childCount = parent.getChildCount();
        View view = null;
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = parent.getChildAt(i2);
            if (((Boolean) childAt.getTag()).booleanValue()) {
                i++;
                view = childAt;
            }
            if (i == 2) {
                break;
            }
        }
        return view;
    }

    private void getStickyViewHolder(RecyclerView recyclerView) {
        if (this.mAdapter != null) {
            return;
        }
        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = recyclerView.getAdapter();
        this.mAdapter = adapter;
        RecyclerView.ViewHolder viewHolderOnCreateViewHolder = adapter.onCreateViewHolder(recyclerView, 1);
        this.mViewHolder = viewHolderOnCreateViewHolder;
        this.mStickyItemView = viewHolderOnCreateViewHolder.itemView;
    }

    private void cacheStickyViewPosition(int i) {
        int iFindFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition() + i;
        if (this.mStickyPositionList.contains(Integer.valueOf(iFindFirstVisibleItemPosition))) {
            return;
        }
        this.mStickyPositionList.add(Integer.valueOf(iFindFirstVisibleItemPosition));
    }

    private void bindDataForStickyView(int position, int width) {
        RecyclerView.ViewHolder viewHolder;
        if (this.mBindDataPosition == position || (viewHolder = this.mViewHolder) == null) {
            return;
        }
        this.mBindDataPosition = position;
        this.mAdapter.onBindViewHolder(viewHolder, position);
        measureLayoutStickyItemView(width);
        this.mItemViewHeight = this.mViewHolder.itemView.getBottom() - this.mViewHolder.itemView.getTop();
    }

    private void measureLayoutStickyItemView(int parentWidth) {
        int iMakeMeasureSpec;
        View view = this.mStickyItemView;
        if (view == null || !view.isLayoutRequested()) {
            return;
        }
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(parentWidth, 1073741824);
        ViewGroup.LayoutParams layoutParams = this.mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        this.mStickyItemView.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
        View view2 = this.mStickyItemView;
        view2.layout(0, 0, view2.getMeasuredWidth(), this.mStickyItemView.getMeasuredHeight());
    }

    private void drawStickyItemView(Canvas canvas) {
        if (this.mStickyItemView == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(0.0f, -this.mStickyItemViewMarginTop);
        this.mStickyItemView.draw(canvas);
        canvas.restoreToCount(iSave);
    }
}
