package com.owen.tvrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class TwoWayLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    private static final String LOGTAG = "TwoWayLayoutManager";
    protected boolean mIsVertical;
    private int mLayoutEnd;
    private int mLayoutStart;
    private SavedState mPendingSavedState;
    private int mPendingScrollOffset;
    private int mPendingScrollPosition;
    protected RecyclerView mRecyclerView;

    public enum Direction {
        START,
        END
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    protected abstract boolean canAddMoreViews(Direction direction, int i);

    protected void detachChild(View view, Direction direction) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    protected abstract void layoutChild(View view, Direction direction);

    protected abstract void measureChild(View view, Direction direction);

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    public TwoWayLayoutManager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TwoWayLayoutManager(Context context, AttributeSet attributeSet, int i) {
        int i2;
        this.mIsVertical = true;
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollOffset = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView, i, 0);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i3);
            if (index == R.styleable.TvRecyclerView_android_orientation && (i2 = typedArrayObtainStyledAttributes.getInt(index, -1)) >= 0) {
                setOrientation(Orientation.values()[i2]);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public TwoWayLayoutManager(Orientation orientation) {
        this.mIsVertical = true;
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollOffset = 0;
        this.mIsVertical = orientation == Orientation.VERTICAL;
    }

    public boolean isVertical() {
        return this.mIsVertical;
    }

    private int getTotalSpace() {
        int width;
        int paddingLeft;
        if (this.mIsVertical) {
            width = getHeight() - getPaddingBottom();
            paddingLeft = getPaddingTop();
        } else {
            width = getWidth() - getPaddingRight();
            paddingLeft = getPaddingLeft();
        }
        return width - paddingLeft;
    }

    protected int getStartWithPadding() {
        return this.mIsVertical ? getPaddingTop() : getPaddingLeft();
    }

    protected int getEndWithPadding() {
        int width;
        int paddingRight;
        if (this.mIsVertical) {
            width = getHeight();
            paddingRight = getPaddingBottom();
        } else {
            width = getWidth();
            paddingRight = getPaddingRight();
        }
        return width - paddingRight;
    }

    protected int getChildStart(View view) {
        return this.mIsVertical ? getDecoratedTop(view) : getDecoratedLeft(view);
    }

    protected int getChildEnd(View view) {
        return this.mIsVertical ? getDecoratedBottom(view) : getDecoratedRight(view);
    }

    protected RecyclerView.Adapter getAdapter() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getAdapter();
        }
        return null;
    }

    private void offsetChildren(int i) {
        if (this.mIsVertical) {
            offsetChildrenVertical(i);
        } else {
            offsetChildrenHorizontal(i);
        }
        this.mLayoutStart += i;
        this.mLayoutEnd += i;
    }

    private void recycleChildrenOutOfBounds(Direction direction, RecyclerView.Recycler recycler) {
        if (direction == Direction.END) {
            recycleChildrenFromStart(direction, recycler);
        } else {
            recycleChildrenFromEnd(direction, recycler);
        }
    }

    private void recycleChildrenFromStart(Direction direction, RecyclerView.Recycler recycler) {
        int childCount = getChildCount();
        int startWithPadding = getStartWithPadding();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (getChildEnd(childAt) >= startWithPadding) {
                break;
            }
            i++;
            detachChild(childAt, direction);
        }
        while (true) {
            i--;
            if (i < 0) {
                return;
            }
            View childAt2 = getChildAt(0);
            removeAndRecycleView(childAt2, recycler);
            updateLayoutEdgesFromRemovedChild(childAt2, direction);
        }
    }

    private void recycleChildrenFromEnd(Direction direction, RecyclerView.Recycler recycler) {
        int endWithPadding = getEndWithPadding();
        int i = 0;
        int i2 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (getChildStart(childAt) <= endWithPadding) {
                break;
            }
            i++;
            detachChild(childAt, direction);
            i2 = childCount;
        }
        while (true) {
            i--;
            if (i < 0) {
                return;
            }
            View childAt2 = getChildAt(i2);
            removeAndRecycleViewAt(i2, recycler);
            updateLayoutEdgesFromRemovedChild(childAt2, direction);
        }
    }

    public boolean cannotScrollForward(int i) {
        return getFirstVisiblePosition() == 0 && this.mLayoutStart >= getStartWithPadding() && i <= 0;
    }

    public boolean cannotScrollBackward(int i) {
        return getFirstVisiblePosition() + getChildCount() == getItemCount() && this.mLayoutEnd <= getEndWithPadding() && i >= 0;
    }

    private int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int iMin;
        int childCount = getChildCount();
        if (childCount == 0 || i == 0) {
            return 0;
        }
        int startWithPadding = getStartWithPadding();
        int endWithPadding = getEndWithPadding();
        int firstVisiblePosition = getFirstVisiblePosition();
        int totalSpace = getTotalSpace();
        if (i < 0) {
            iMin = Math.max(-(totalSpace - 1), i);
        } else {
            iMin = Math.min(totalSpace - 1, i);
        }
        boolean z = firstVisiblePosition == 0 && this.mLayoutStart >= startWithPadding && iMin <= 0;
        if ((firstVisiblePosition + childCount == state.getItemCount() && this.mLayoutEnd <= endWithPadding && iMin >= 0) || z) {
            return 0;
        }
        offsetChildren(-iMin);
        Direction direction = iMin > 0 ? Direction.END : Direction.START;
        recycleChildrenOutOfBounds(direction, recycler);
        int iAbs = Math.abs(iMin);
        if (canAddMoreViews(Direction.START, startWithPadding - iAbs) || canAddMoreViews(Direction.END, endWithPadding + iAbs)) {
            fillGap(direction, recycler, state);
        }
        return iMin;
    }

    private void fillGap(Direction direction, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int childCount = getChildCount();
        int extraLayoutSpace = getExtraLayoutSpace(state);
        int firstVisiblePosition = getFirstVisiblePosition();
        if (direction == Direction.END) {
            fillAfter(firstVisiblePosition + childCount, recycler, state, extraLayoutSpace);
            correctTooHigh(childCount, recycler, state);
        } else {
            fillBefore(firstVisiblePosition - 1, recycler, extraLayoutSpace);
            correctTooLow(childCount, recycler, state);
        }
    }

    private void fillBefore(int i, RecyclerView.Recycler recycler) {
        fillBefore(i, recycler, 0);
    }

    private void fillBefore(int i, RecyclerView.Recycler recycler, int i2) {
        int startWithPadding = getStartWithPadding() - i2;
        while (canAddMoreViews(Direction.START, startWithPadding) && i >= 0) {
            makeAndAddView(i, Direction.START, recycler);
            i--;
        }
    }

    private void fillAfter(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        fillAfter(i, recycler, state, 0);
    }

    private void fillAfter(int i, RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        int endWithPadding = getEndWithPadding() + i2;
        int itemCount = state.getItemCount();
        while (canAddMoreViews(Direction.END, endWithPadding) && i < itemCount) {
            makeAndAddView(i, Direction.END, recycler);
            i++;
        }
    }

    private void fillSpecific(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() <= 0) {
            return;
        }
        makeAndAddView(i, Direction.END, recycler);
        int extraLayoutSpace = getExtraLayoutSpace(state);
        int i2 = 0;
        if (state.getTargetScrollPosition() >= i) {
            i2 = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        fillBefore(i - 1, recycler, extraLayoutSpace);
        adjustViewsStartOrEnd();
        fillAfter(i + 1, recycler, state, i2);
        correctTooHigh(getChildCount(), recycler, state);
    }

    private void correctTooHigh(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getLastVisiblePosition() != state.getItemCount() - 1 || i == 0) {
            return;
        }
        int startWithPadding = getStartWithPadding();
        int endWithPadding = getEndWithPadding();
        int firstVisiblePosition = getFirstVisiblePosition();
        int iMin = endWithPadding - this.mLayoutEnd;
        if (iMin > 0) {
            if (firstVisiblePosition > 0 || this.mLayoutStart < startWithPadding) {
                if (firstVisiblePosition == 0) {
                    iMin = Math.min(iMin, startWithPadding - this.mLayoutStart);
                }
                offsetChildren(iMin);
                if (firstVisiblePosition > 0) {
                    fillBefore(firstVisiblePosition - 1, recycler);
                    adjustViewsStartOrEnd();
                }
            }
        }
    }

    private void correctTooLow(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getFirstVisiblePosition() != 0 || i == 0) {
            return;
        }
        int startWithPadding = getStartWithPadding();
        int endWithPadding = getEndWithPadding();
        int itemCount = state.getItemCount();
        int lastVisiblePosition = getLastVisiblePosition();
        int iMin = this.mLayoutStart - startWithPadding;
        if (iMin > 0) {
            int i2 = itemCount - 1;
            if (lastVisiblePosition >= i2 && this.mLayoutEnd <= endWithPadding) {
                if (lastVisiblePosition == i2) {
                    adjustViewsStartOrEnd();
                    return;
                }
                return;
            }
            if (lastVisiblePosition == i2) {
                iMin = Math.min(iMin, this.mLayoutEnd - endWithPadding);
            }
            offsetChildren(-iMin);
            if (lastVisiblePosition < i2) {
                fillAfter(lastVisiblePosition + 1, recycler, state);
                adjustViewsStartOrEnd();
            }
        }
    }

    private void adjustViewsStartOrEnd() {
        if (getChildCount() == 0) {
            return;
        }
        int startWithPadding = this.mLayoutStart - getStartWithPadding();
        if (startWithPadding < 0) {
            startWithPadding = 0;
        }
        if (startWithPadding != 0) {
            offsetChildren(-startWithPadding);
        }
    }

    private static View findNextScrapView(List<RecyclerView.ViewHolder> list, Direction direction, int i) {
        int iAbs;
        int size = list.size();
        int i2 = Integer.MAX_VALUE;
        RecyclerView.ViewHolder viewHolder = null;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView.ViewHolder viewHolder2 = list.get(i3);
            int position = viewHolder2.getPosition() - i;
            if ((position >= 0 || direction != Direction.END) && ((position <= 0 || direction != Direction.START) && (iAbs = Math.abs(position)) < i2)) {
                viewHolder = viewHolder2;
                if (position == 0) {
                    break;
                }
                i2 = iAbs;
            }
        }
        if (viewHolder != null) {
            return viewHolder.itemView;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001d  */
    /* JADX WARN: Code duplicated, block: B:12:0x001f  */
    /* JADX WARN: Code duplicated, block: B:9:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x001d -> B:5:0x000d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x001f -> B:5:0x000d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    private void fillFromScrapList(java.util.List<androidx.recyclerview.widget.RecyclerView.ViewHolder> r4, com.owen.tvrecyclerview.TwoWayLayoutManager.Direction r5) {
        /*
            r3 = this;
            int r0 = r3.getFirstVisiblePosition()
            com.owen.tvrecyclerview.TwoWayLayoutManager$Direction r1 = com.owen.tvrecyclerview.TwoWayLayoutManager.Direction.END
            r2 = 1
            if (r5 != r1) goto Lf
            int r1 = r3.getChildCount()
        Ld:
            int r0 = r0 + r1
            goto L10
        Lf:
            int r0 = r0 - r2
        L10:
            android.view.View r1 = findNextScrapView(r4, r5, r0)
            if (r1 == 0) goto L21
            r3.setupChild(r1, r5)
            com.owen.tvrecyclerview.TwoWayLayoutManager$Direction r1 = com.owen.tvrecyclerview.TwoWayLayoutManager.Direction.END
            if (r5 != r1) goto L1f
            r1 = 1
            goto Ld
        L1f:
            r1 = -1
            goto Ld
        L21:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.owen.tvrecyclerview.TwoWayLayoutManager.fillFromScrapList(java.util.List, com.owen.tvrecyclerview.TwoWayLayoutManager$Direction):void");
    }

    private void setupChild(View view, Direction direction) {
        measureChild(view, direction);
        layoutChild(view, direction);
    }

    private View makeAndAddView(int i, Direction direction, RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(i);
        boolean zIsItemRemoved = ((RecyclerView.LayoutParams) viewForPosition.getLayoutParams()).isItemRemoved();
        if (!zIsItemRemoved) {
            addView(viewForPosition, direction == Direction.END ? -1 : 0);
        }
        setupChild(viewForPosition, direction);
        if (!zIsItemRemoved) {
            updateLayoutEdgesFromNewChild(viewForPosition);
        }
        return viewForPosition;
    }

    private void handleUpdate() {
        int firstVisiblePosition = getFirstVisiblePosition();
        if (findViewByPosition(firstVisiblePosition) != null) {
            setPendingScrollPositionWithOffset(firstVisiblePosition, this.mLayoutStart);
        } else {
            setPendingScrollPositionWithOffset(-1, 0);
        }
    }

    private void updateLayoutEdgesFromNewChild(View view) {
        int childStart = getChildStart(view);
        if (childStart < this.mLayoutStart) {
            this.mLayoutStart = childStart;
        }
        int childEnd = getChildEnd(view);
        if (childEnd > this.mLayoutEnd) {
            this.mLayoutEnd = childEnd;
        }
    }

    private void updateLayoutEdgesFromRemovedChild(View view, Direction direction) {
        int i;
        int childCount = getChildCount();
        if (childCount == 0) {
            resetLayoutEdges();
            return;
        }
        int childStart = getChildStart(view);
        int childEnd = getChildEnd(view);
        if (childStart <= this.mLayoutStart || childEnd >= this.mLayoutEnd) {
            if (direction == Direction.END) {
                this.mLayoutStart = Integer.MAX_VALUE;
                i = 0;
            } else {
                this.mLayoutEnd = Integer.MIN_VALUE;
                i = childCount - 1;
                childEnd = childStart;
            }
            while (i >= 0 && i <= childCount - 1) {
                View childAt = getChildAt(i);
                if (direction == Direction.END) {
                    int childStart2 = getChildStart(childAt);
                    if (childStart2 < this.mLayoutStart) {
                        this.mLayoutStart = childStart2;
                    }
                    if (childStart2 >= childEnd) {
                        return;
                    } else {
                        i++;
                    }
                } else {
                    int childEnd2 = getChildEnd(childAt);
                    if (childEnd2 > this.mLayoutEnd) {
                        this.mLayoutEnd = childEnd2;
                    }
                    if (childEnd2 <= childEnd) {
                        return;
                    } else {
                        i--;
                    }
                }
            }
        }
    }

    private void resetLayoutEdges() {
        int startWithPadding = getStartWithPadding();
        this.mLayoutStart = startWithPadding;
        this.mLayoutEnd = startWithPadding;
    }

    protected int getExtraLayoutSpace(RecyclerView.State state) {
        if (state.hasTargetScrollPosition()) {
            return getTotalSpace();
        }
        return 0;
    }

    private Bundle getPendingItemSelectionState() {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return savedState.itemSelectionState;
        }
        return null;
    }

    protected void setPendingScrollPositionWithOffset(int i, int i2) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollOffset = i2;
    }

    protected int getPendingScrollPosition() {
        SavedState savedState = this.mPendingSavedState;
        if (savedState == null) {
            return this.mPendingScrollPosition;
        }
        return savedState.anchorItemPosition;
    }

    protected int getPendingScrollOffset() {
        if (this.mPendingSavedState != null) {
            return 0;
        }
        if (this.mPendingScrollOffset == 0 && getFirstVisiblePosition() > 0) {
            return this.mLayoutStart;
        }
        return this.mPendingScrollOffset;
    }

    protected int getAnchorItemPosition(RecyclerView.State state) {
        int itemCount = state.getItemCount();
        int pendingScrollPosition = getPendingScrollPosition();
        if (pendingScrollPosition != -1 && (pendingScrollPosition < 0 || pendingScrollPosition >= itemCount)) {
            pendingScrollPosition = -1;
        }
        if (pendingScrollPosition != -1) {
            return pendingScrollPosition;
        }
        if (getChildCount() > 0) {
            return findFirstValidChildPosition(itemCount);
        }
        return 0;
    }

    private int findFirstValidChildPosition(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            int position = getPosition(getChildAt(i2));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedMeasuredWidth(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return super.getDecoratedMeasuredWidth(view) + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedMeasuredHeight(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return super.getDecoratedMeasuredHeight(view) + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedLeft(View view) {
        return super.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedTop(View view) {
        return super.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedRight(View view) {
        return super.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedBottom(View view) {
        return super.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void layoutDecorated(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        super.layoutDecorated(view, i + marginLayoutParams.leftMargin, i2 + marginLayoutParams.topMargin, i3 - marginLayoutParams.rightMargin, i4 - marginLayoutParams.bottomMargin);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mRecyclerView = recyclerView;
        if (this.mLayoutStart == 0) {
            this.mLayoutStart = getStartWithPadding();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        this.mRecyclerView = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        super.onAdapterChanged(adapter, adapter2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int anchorItemPosition = getAnchorItemPosition(state);
        detachAndScrapAttachedViews(recycler);
        fillSpecific(anchorItemPosition, recycler, state);
        onLayoutScrapList(recycler, state);
        setPendingScrollPositionWithOffset(-1, 0);
        this.mPendingSavedState = null;
    }

    protected void onLayoutScrapList(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || state.isPreLayout() || !supportsPredictiveItemAnimations()) {
            return;
        }
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        fillFromScrapList(scrapList, Direction.START);
        fillFromScrapList(scrapList, Direction.END);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        handleUpdate();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        handleUpdate();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        handleUpdate();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        handleUpdate();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        handleUpdate();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mIsVertical) {
            return new RecyclerView.LayoutParams(-1, -2);
        }
        return new RecyclerView.LayoutParams(-2, -1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mIsVertical) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mIsVertical) {
            return scrollBy(i, recycler, state);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return !this.mIsVertical;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mIsVertical;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        scrollToPositionWithOffset(i, 0);
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        setPendingScrollPositionWithOffset(i, i2);
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) { // from class: com.owen.tvrecyclerview.TwoWayLayoutManager.1
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDtToFit(int i2, int i3, int i4, int i5, int i6) {
                return i4 - i2;
            }
        };
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = i < getPosition(getChildAt(0)) ? -1 : 1;
        if (!this.mIsVertical) {
            return new PointF(i2, 0.0f);
        }
        return new PointF(0.0f, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return getChildCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return getChildCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return state.getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return state.getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        super.onMeasure(recycler, state, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(SavedState.EMPTY_STATE);
        int pendingScrollPosition = getPendingScrollPosition();
        if (pendingScrollPosition == -1) {
            pendingScrollPosition = getFirstVisiblePosition();
        }
        savedState.anchorItemPosition = pendingScrollPosition;
        savedState.itemSelectionState = Bundle.EMPTY;
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        this.mPendingSavedState = (SavedState) parcelable;
        requestLayout();
    }

    public Orientation getOrientation() {
        return this.mIsVertical ? Orientation.VERTICAL : Orientation.HORIZONTAL;
    }

    public void setOrientation(Orientation orientation) {
        boolean z = orientation == Orientation.VERTICAL;
        if (this.mIsVertical == z) {
            return;
        }
        this.mIsVertical = z;
        requestLayout();
    }

    public int getFirstVisiblePosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public int getLastVisiblePosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        if (recyclerView instanceof TvRecyclerView) {
            return recyclerView.requestChildRectangleOnScreen(view, rect, z);
        }
        return super.requestChildRectangleOnScreen(recyclerView, view, rect, z, z2);
    }

    protected static class SavedState implements Parcelable {
        private int anchorItemPosition;
        private Bundle itemSelectionState;
        private final Parcelable superState;
        protected static final SavedState EMPTY_STATE = new SavedState();
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.owen.tvrecyclerview.TwoWayLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private SavedState() {
            this.superState = null;
        }

        protected SavedState(Parcelable parcelable) {
            if (parcelable == null) {
                throw new IllegalArgumentException("superState must not be null");
            }
            this.superState = parcelable == EMPTY_STATE ? null : parcelable;
        }

        protected SavedState(Parcel parcel) {
            this.superState = EMPTY_STATE;
            this.anchorItemPosition = parcel.readInt();
            this.itemSelectionState = (Bundle) parcel.readParcelable(getClass().getClassLoader());
        }

        public Parcelable getSuperState() {
            return this.superState;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.anchorItemPosition);
            parcel.writeParcelable(this.itemSelectionState, i);
        }
    }
}
