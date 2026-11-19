package com.alibaba.android.vlayout;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.layout.LayoutChunkResult;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class ExposeLinearLayoutManagerEx extends LinearLayoutManager {
    private static final boolean DEBUG = false;
    static final int FLAG_INVALID = 4;
    static final int FLAG_UPDATED = 2;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33f;
    private static final String TAG = "ExposeLLManagerEx";
    public static final int VERTICAL = 1;
    private static Field vhField;
    private static Method vhSetFlags;
    private Object[] emptyArgs;
    private LayoutChunkResult layoutChunkResultCache;
    private final AnchorInfo mAnchorInfo;
    private final ChildHelperWrapper mChildHelperWrapper;
    protected Bundle mCurrentPendingSavedState;
    private int mCurrentPendingScrollPosition;
    private final Method mEnsureLayoutStateMethod;
    private boolean mLastStackFromEnd;
    protected LayoutState mLayoutState;
    private OrientationHelperEx mOrientationHelper;
    private int mPendingScrollPositionOffset;
    private RecyclerView mRecyclerView;
    private boolean mShouldReverseLayoutExpose;
    protected int recycleOffset;

    protected int computeAlignOffset(int i, boolean z, boolean z2) {
        return 0;
    }

    protected int computeAlignOffset(View view, boolean z, boolean z2) {
        return 0;
    }

    public boolean isEnableMarginOverLap() {
        return false;
    }

    public void onAnchorReady(RecyclerView.State state, AnchorInfo anchorInfo) {
    }

    public ExposeLinearLayoutManagerEx(Context context) {
        this(context, 1, false);
    }

    public ExposeLinearLayoutManagerEx(Context context, int i, boolean z) {
        super(context, i, z);
        this.mShouldReverseLayoutExpose = false;
        this.mCurrentPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mCurrentPendingSavedState = null;
        this.emptyArgs = new Object[0];
        this.layoutChunkResultCache = new LayoutChunkResult();
        this.mAnchorInfo = new AnchorInfo();
        setOrientation(i);
        setReverseLayout(z);
        this.mChildHelperWrapper = new ChildHelperWrapper(this);
        try {
            Method declaredMethod = LinearLayoutManager.class.getDeclaredMethod("ensureLayoutState", null);
            this.mEnsureLayoutStateMethod = declaredMethod;
            declaredMethod.setAccessible(true);
            try {
                Method declaredMethod2 = RecyclerView.LayoutManager.class.getDeclaredMethod("setItemPrefetchEnabled", Boolean.TYPE);
                if (declaredMethod2 != null) {
                    declaredMethod2.invoke(this, false);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.mCurrentPendingSavedState != null) {
            return new Bundle(this.mCurrentPendingSavedState);
        }
        Bundle bundle = new Bundle();
        if (getChildCount() > 0) {
            boolean z = this.mLastStackFromEnd ^ this.mShouldReverseLayoutExpose;
            bundle.putBoolean("AnchorLayoutFromEnd", z);
            if (z) {
                View childClosestToEndExpose = getChildClosestToEndExpose();
                bundle.putInt("AnchorOffset", this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(childClosestToEndExpose));
                bundle.putInt("AnchorPosition", getPosition(childClosestToEndExpose));
            } else {
                View childClosestToStartExpose = getChildClosestToStartExpose();
                bundle.putInt("AnchorPosition", getPosition(childClosestToStartExpose));
                bundle.putInt("AnchorOffset", this.mOrientationHelper.getDecoratedStart(childClosestToStartExpose) - this.mOrientationHelper.getStartAfterPadding());
            }
        } else {
            bundle.putInt("AnchorPosition", -1);
        }
        return bundle;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            this.mCurrentPendingSavedState = (Bundle) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void setOrientation(int i) {
        super.setOrientation(i);
        this.mOrientationHelper = null;
    }

    public void setRecycleOffset(int i) {
        this.recycleOffset = i;
    }

    private void myResolveShouldLayoutReverse() {
        if (getOrientation() == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayoutExpose = getReverseLayout();
        } else {
            this.mShouldReverseLayoutExpose = !getReverseLayout();
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = (i < getPosition(getChildAt(0))) != this.mShouldReverseLayoutExpose ? -1 : 1;
        if (getOrientation() == 0) {
            return new PointF(i2, 0.0f);
        }
        return new PointF(0.0f, i2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int iFixLayoutEndGapExpose;
        int i6;
        View viewFindViewByPosition;
        int decoratedStart;
        int endAfterPadding;
        Bundle bundle = this.mCurrentPendingSavedState;
        if (bundle != null && bundle.getInt("AnchorPosition") >= 0) {
            this.mCurrentPendingScrollPosition = this.mCurrentPendingSavedState.getInt("AnchorPosition");
        }
        ensureLayoutStateExpose();
        this.mLayoutState.mRecycle = false;
        myResolveShouldLayoutReverse();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayoutExpose ^ getStackFromEnd();
        updateAnchorInfoForLayoutExpose(state, this.mAnchorInfo);
        int extraLayoutSpace = getExtraLayoutSpace(state);
        if ((state.getTargetScrollPosition() < this.mAnchorInfo.mPosition) == this.mShouldReverseLayoutExpose) {
            i = extraLayoutSpace;
            extraLayoutSpace = 0;
        } else {
            i = 0;
        }
        int startAfterPadding = extraLayoutSpace + this.mOrientationHelper.getStartAfterPadding();
        int endPadding = i + this.mOrientationHelper.getEndPadding();
        if (state.isPreLayout() && (i6 = this.mCurrentPendingScrollPosition) != -1 && this.mPendingScrollPositionOffset != Integer.MIN_VALUE && (viewFindViewByPosition = findViewByPosition(i6)) != null) {
            if (this.mShouldReverseLayoutExpose) {
                endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewFindViewByPosition);
                decoratedStart = this.mPendingScrollPositionOffset;
            } else {
                decoratedStart = this.mOrientationHelper.getDecoratedStart(viewFindViewByPosition) - this.mOrientationHelper.getStartAfterPadding();
                endAfterPadding = this.mPendingScrollPositionOffset;
            }
            int i7 = endAfterPadding - decoratedStart;
            if (i7 > 0) {
                startAfterPadding += i7;
            } else {
                endPadding -= i7;
            }
        }
        onAnchorReady(state, this.mAnchorInfo);
        detachAndScrapAttachedViews(recycler);
        this.mLayoutState.mIsPreLayout = state.isPreLayout();
        this.mLayoutState.mOnRefresLayout = true;
        if (this.mAnchorInfo.mLayoutFromEnd) {
            updateLayoutStateToFillStartExpose(this.mAnchorInfo);
            this.mLayoutState.mExtra = startAfterPadding;
            fill(recycler, this.mLayoutState, state, false);
            i3 = this.mLayoutState.mOffset;
            if (this.mLayoutState.mAvailable > 0) {
                endPadding += this.mLayoutState.mAvailable;
            }
            updateLayoutStateToFillEndExpose(this.mAnchorInfo);
            this.mLayoutState.mExtra = endPadding;
            this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            fill(recycler, this.mLayoutState, state, false);
            i2 = this.mLayoutState.mOffset;
        } else {
            updateLayoutStateToFillEndExpose(this.mAnchorInfo);
            this.mLayoutState.mExtra = endPadding;
            fill(recycler, this.mLayoutState, state, false);
            i2 = this.mLayoutState.mOffset;
            if (this.mLayoutState.mAvailable > 0) {
                startAfterPadding += this.mLayoutState.mAvailable;
            }
            updateLayoutStateToFillStartExpose(this.mAnchorInfo);
            this.mLayoutState.mExtra = startAfterPadding;
            this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            fill(recycler, this.mLayoutState, state, false);
            i3 = this.mLayoutState.mOffset;
        }
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayoutExpose ^ getStackFromEnd()) {
                int iFixLayoutEndGapExpose2 = fixLayoutEndGapExpose(i2, recycler, state, true);
                i4 = i3 + iFixLayoutEndGapExpose2;
                i5 = i2 + iFixLayoutEndGapExpose2;
                iFixLayoutEndGapExpose = fixLayoutStartGapExpose(i4, recycler, state, false);
            } else {
                int iFixLayoutStartGapExpose = fixLayoutStartGapExpose(i3, recycler, state, true);
                i4 = i3 + iFixLayoutStartGapExpose;
                i5 = i2 + iFixLayoutStartGapExpose;
                iFixLayoutEndGapExpose = fixLayoutEndGapExpose(i5, recycler, state, false);
            }
            i3 = i4 + iFixLayoutEndGapExpose;
            i2 = i5 + iFixLayoutEndGapExpose;
        }
        layoutForPredictiveAnimationsExpose(recycler, state, i3, i2);
        if (!state.isPreLayout()) {
            this.mCurrentPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            this.mOrientationHelper.onLayoutComplete();
        }
        this.mLastStackFromEnd = getStackFromEnd();
        this.mCurrentPendingSavedState = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        this.mRecyclerView = null;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public int findFirstVisibleItemPosition() {
        ensureLayoutStateExpose();
        return super.findFirstVisibleItemPosition();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public int findLastVisibleItemPosition() throws Exception {
        ensureLayoutStateExpose();
        try {
            return super.findLastVisibleItemPosition();
        } catch (Exception e) {
            Log.d("LastItem", "itemCount: " + getItemCount());
            Log.d("LastItem", "childCount: " + getChildCount());
            Log.d("LastItem", "child: " + getChildAt(getChildCount() + (-1)));
            Log.d("LastItem", "RV childCount: " + this.mRecyclerView.getChildCount());
            Log.d("LastItem", "RV child: " + this.mRecyclerView.getChildAt(this.mRecyclerView.getChildCount() + (-1)));
            throw e;
        }
    }

    private View myFindReferenceChildClosestToEnd(RecyclerView.State state) {
        boolean z = this.mShouldReverseLayoutExpose;
        int itemCount = state.getItemCount();
        return z ? myFindFirstReferenceChild(itemCount) : myFindLastReferenceChild(itemCount);
    }

    private View myFindReferenceChildClosestToStart(RecyclerView.State state) {
        boolean z = this.mShouldReverseLayoutExpose;
        int itemCount = state.getItemCount();
        return z ? myFindLastReferenceChild(itemCount) : myFindFirstReferenceChild(itemCount);
    }

    private View myFindFirstReferenceChild(int i) {
        return findReferenceChildInternal(0, getChildCount(), i);
    }

    private View myFindLastReferenceChild(int i) {
        return findReferenceChildInternal(getChildCount() - 1, -1, i);
    }

    private View findReferenceChildInternal(int i, int i2, int i3) {
        ensureLayoutStateExpose();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.getDecoratedStart(childAt) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) >= startAfterPadding) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    private void layoutForPredictiveAnimationsExpose(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        if (!state.willRunPredictiveAnimations() || getChildCount() == 0 || state.isPreLayout() || !supportsPredictiveItemAnimations()) {
            return;
        }
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        int size = scrapList.size();
        int position = getPosition(getChildAt(0));
        int i3 = 0;
        int decoratedMeasurement = 0;
        int decoratedMeasurement2 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = scrapList.get(i3);
            if ((viewHolder.getPosition() < position) != this.mShouldReverseLayoutExpose) {
                decoratedMeasurement += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
            } else {
                decoratedMeasurement2 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
            }
            i3++;
        }
        this.mLayoutState.mScrapList = scrapList;
        if (decoratedMeasurement > 0) {
            updateLayoutStateToFillStartExpose(getPosition(getChildClosestToStartExpose()), i);
            this.mLayoutState.mExtra = decoratedMeasurement;
            this.mLayoutState.mAvailable = 0;
            this.mLayoutState.mCurrentPosition += this.mShouldReverseLayoutExpose ? 1 : -1;
            this.mLayoutState.mOnRefresLayout = true;
            fill(recycler, this.mLayoutState, state, false);
        }
        if (decoratedMeasurement2 > 0) {
            updateLayoutStateToFillEndExpose(getPosition(getChildClosestToEndExpose()), i2);
            this.mLayoutState.mExtra = decoratedMeasurement2;
            this.mLayoutState.mAvailable = 0;
            this.mLayoutState.mCurrentPosition += this.mShouldReverseLayoutExpose ? -1 : 1;
            this.mLayoutState.mOnRefresLayout = true;
            fill(recycler, this.mLayoutState, state, false);
        }
        this.mLayoutState.mScrapList = null;
    }

    private void updateAnchorInfoForLayoutExpose(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (updateAnchorFromPendingDataExpose(state, anchorInfo) || updateAnchorFromChildrenExpose(state, anchorInfo)) {
            return;
        }
        anchorInfo.assignCoordinateFromPadding();
        anchorInfo.mPosition = getStackFromEnd() ? state.getItemCount() - 1 : 0;
    }

    private boolean updateAnchorFromChildrenExpose(RecyclerView.State state, AnchorInfo anchorInfo) {
        View viewMyFindReferenceChildClosestToStart;
        int startAfterPadding;
        if (getChildCount() == 0) {
            return false;
        }
        View focusedChild = getFocusedChild();
        if (focusedChild != null && anchorInfo.assignFromViewIfValid(focusedChild, state)) {
            return true;
        }
        if (this.mLastStackFromEnd != getStackFromEnd()) {
            return false;
        }
        if (anchorInfo.mLayoutFromEnd) {
            viewMyFindReferenceChildClosestToStart = myFindReferenceChildClosestToEnd(state);
        } else {
            viewMyFindReferenceChildClosestToStart = myFindReferenceChildClosestToStart(state);
        }
        if (viewMyFindReferenceChildClosestToStart == null) {
            return false;
        }
        anchorInfo.assignFromView(viewMyFindReferenceChildClosestToStart);
        if (!state.isPreLayout() && supportsPredictiveItemAnimations() && (this.mOrientationHelper.getDecoratedStart(viewMyFindReferenceChildClosestToStart) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(viewMyFindReferenceChildClosestToStart) < this.mOrientationHelper.getStartAfterPadding())) {
            if (anchorInfo.mLayoutFromEnd) {
                startAfterPadding = this.mOrientationHelper.getEndAfterPadding();
            } else {
                startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
            }
            anchorInfo.mCoordinate = startAfterPadding;
        }
        return true;
    }

    private boolean updateAnchorFromPendingDataExpose(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i;
        int decoratedStart;
        if (!state.isPreLayout() && (i = this.mCurrentPendingScrollPosition) != -1) {
            if (i < 0 || i >= state.getItemCount()) {
                this.mCurrentPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            } else {
                anchorInfo.mPosition = this.mCurrentPendingScrollPosition;
                Bundle bundle = this.mCurrentPendingSavedState;
                if (bundle != null && bundle.getInt("AnchorPosition") >= 0) {
                    anchorInfo.mLayoutFromEnd = this.mCurrentPendingSavedState.getBoolean("AnchorLayoutFromEnd");
                    if (anchorInfo.mLayoutFromEnd) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mCurrentPendingSavedState.getInt("AnchorOffset");
                    } else {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mCurrentPendingSavedState.getInt("AnchorOffset");
                    }
                    return true;
                }
                if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                    View viewFindViewByPosition = findViewByPosition(this.mCurrentPendingScrollPosition);
                    if (viewFindViewByPosition != null) {
                        if (this.mOrientationHelper.getDecoratedMeasurement(viewFindViewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                            anchorInfo.assignCoordinateFromPadding();
                            return true;
                        }
                        if (this.mOrientationHelper.getDecoratedStart(viewFindViewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                            anchorInfo.mLayoutFromEnd = false;
                            return true;
                        }
                        if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewFindViewByPosition) < 0) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                            anchorInfo.mLayoutFromEnd = true;
                            return true;
                        }
                        if (anchorInfo.mLayoutFromEnd) {
                            decoratedStart = this.mOrientationHelper.getDecoratedEnd(viewFindViewByPosition) + this.mOrientationHelper.getTotalSpaceChange();
                        } else {
                            decoratedStart = this.mOrientationHelper.getDecoratedStart(viewFindViewByPosition);
                        }
                        anchorInfo.mCoordinate = decoratedStart;
                    } else {
                        if (getChildCount() > 0) {
                            anchorInfo.mLayoutFromEnd = (this.mCurrentPendingScrollPosition < getPosition(getChildAt(0))) == this.mShouldReverseLayoutExpose;
                        }
                        anchorInfo.assignCoordinateFromPadding();
                    }
                    return true;
                }
                anchorInfo.mLayoutFromEnd = this.mShouldReverseLayoutExpose;
                if (this.mShouldReverseLayoutExpose) {
                    anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                } else {
                    anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                }
                return true;
            }
        }
        return false;
    }

    private int fixLayoutEndGapExpose(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i;
        if (endAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -scrollInternalBy(-endAfterPadding2, recycler, state);
        int i3 = i + i2;
        if (!z || (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i3) <= 0) {
            return i2;
        }
        this.mOrientationHelper.offsetChildren(endAfterPadding);
        return endAfterPadding + i2;
    }

    private int fixLayoutStartGapExpose(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i - this.mOrientationHelper.getStartAfterPadding();
        if (startAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -scrollInternalBy(startAfterPadding2, recycler, state);
        int i3 = i + i2;
        if (!z || (startAfterPadding = i3 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return i2;
        }
        this.mOrientationHelper.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    private void updateLayoutStateToFillEndExpose(AnchorInfo anchorInfo) {
        updateLayoutStateToFillEndExpose(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }

    private void updateLayoutStateToFillEndExpose(int i, int i2) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - i2;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayoutExpose ? -1 : 1;
        this.mLayoutState.mCurrentPosition = i;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = i2;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillStartExpose(AnchorInfo anchorInfo) {
        updateLayoutStateToFillStartExpose(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }

    private void updateLayoutStateToFillStartExpose(int i, int i2) {
        this.mLayoutState.mAvailable = i2 - this.mOrientationHelper.getStartAfterPadding();
        this.mLayoutState.mCurrentPosition = i;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayoutExpose ? 1 : -1;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = i2;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    protected void ensureLayoutStateExpose() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LayoutState();
        }
        if (this.mOrientationHelper == null) {
            this.mOrientationHelper = OrientationHelperEx.createOrientationHelper(this, getOrientation());
        }
        try {
            this.mEnsureLayoutStateMethod.invoke(this, this.emptyArgs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        this.mCurrentPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        Bundle bundle = this.mCurrentPendingSavedState;
        if (bundle != null) {
            bundle.putInt("AnchorPosition", -1);
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void scrollToPositionWithOffset(int i, int i2) {
        this.mCurrentPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = i2;
        Bundle bundle = this.mCurrentPendingSavedState;
        if (bundle != null) {
            bundle.putInt("AnchorPosition", -1);
        }
        requestLayout();
    }

    protected void updateLayoutStateExpose(int i, int i2, boolean z, RecyclerView.State state) {
        int startAfterPadding;
        this.mLayoutState.mExtra = getExtraLayoutSpace(state);
        this.mLayoutState.mLayoutDirection = i;
        if (i == 1) {
            this.mLayoutState.mExtra += this.mOrientationHelper.getEndPadding();
            View childClosestToEndExpose = getChildClosestToEndExpose();
            this.mLayoutState.mItemDirection = this.mShouldReverseLayoutExpose ? -1 : 1;
            this.mLayoutState.mCurrentPosition = getPosition(childClosestToEndExpose) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEndExpose) + computeAlignOffset(childClosestToEndExpose, true, false);
            startAfterPadding = this.mLayoutState.mOffset - this.mOrientationHelper.getEndAfterPadding();
        } else {
            View childClosestToStartExpose = getChildClosestToStartExpose();
            this.mLayoutState.mExtra += this.mOrientationHelper.getStartAfterPadding();
            this.mLayoutState.mItemDirection = this.mShouldReverseLayoutExpose ? 1 : -1;
            this.mLayoutState.mCurrentPosition = getPosition(childClosestToStartExpose) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStartExpose) + computeAlignOffset(childClosestToStartExpose, false, false);
            startAfterPadding = (-this.mLayoutState.mOffset) + this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mAvailable = i2;
        if (z) {
            this.mLayoutState.mAvailable -= startAfterPadding;
        }
        this.mLayoutState.mScrollingOffset = startAfterPadding;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getOrientation() == 1) {
            return 0;
        }
        return scrollInternalBy(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getOrientation() == 0) {
            return 0;
        }
        return scrollInternalBy(i, recycler, state);
    }

    protected int scrollInternalBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        this.mLayoutState.mRecycle = true;
        ensureLayoutStateExpose();
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        updateLayoutStateExpose(i2, iAbs, true, state);
        int i3 = this.mLayoutState.mScrollingOffset;
        this.mLayoutState.mOnRefresLayout = false;
        int iFill = i3 + fill(recycler, this.mLayoutState, state, false);
        if (iFill < 0) {
            return 0;
        }
        if (iAbs > iFill) {
            i = i2 * iFill;
        }
        this.mOrientationHelper.offsetChildren(-i);
        return i;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mCurrentPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    protected void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                removeAndRecycleViewAt(i, recycler);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                removeAndRecycleViewAt(i3, recycler);
            }
        }
    }

    private void recycleViewsFromStartExpose(RecyclerView.Recycler recycler, int i) {
        if (i < 0) {
            return;
        }
        int childCount = getChildCount();
        if (!this.mShouldReverseLayoutExpose) {
            for (int i2 = 0; i2 < childCount; i2++) {
                if (this.mOrientationHelper.getDecoratedEnd(getChildAt(i2)) + this.recycleOffset > i) {
                    recycleChildren(recycler, 0, i2);
                    return;
                }
            }
            return;
        }
        int i3 = childCount - 1;
        for (int i4 = i3; i4 >= 0; i4--) {
            if (this.mOrientationHelper.getDecoratedEnd(getChildAt(i4)) + this.recycleOffset > i) {
                recycleChildren(recycler, i3, i4);
                return;
            }
        }
    }

    private void recycleViewsFromEndExpose(RecyclerView.Recycler recycler, int i) {
        int childCount = getChildCount();
        if (i < 0) {
            return;
        }
        int end = this.mOrientationHelper.getEnd() - i;
        if (this.mShouldReverseLayoutExpose) {
            for (int i2 = 0; i2 < childCount; i2++) {
                if (this.mOrientationHelper.getDecoratedStart(getChildAt(i2)) - this.recycleOffset < end) {
                    recycleChildren(recycler, 0, i2);
                    return;
                }
            }
            return;
        }
        int i3 = childCount - 1;
        for (int i4 = i3; i4 >= 0; i4--) {
            if (this.mOrientationHelper.getDecoratedStart(getChildAt(i4)) - this.recycleOffset < end) {
                recycleChildren(recycler, i3, i4);
                return;
            }
        }
    }

    private void recycleByLayoutStateExpose(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (layoutState.mRecycle) {
            if (layoutState.mLayoutDirection == -1) {
                recycleViewsFromEndExpose(recycler, layoutState.mScrollingOffset);
            } else {
                recycleViewsFromStartExpose(recycler, layoutState.mScrollingOffset);
            }
        }
    }

    protected int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state, boolean z) {
        int i = layoutState.mAvailable;
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset += layoutState.mAvailable;
            }
            recycleByLayoutStateExpose(recycler, layoutState);
        }
        int i2 = layoutState.mAvailable + layoutState.mExtra + this.recycleOffset;
        while (i2 > 0 && layoutState.hasMore(state)) {
            this.layoutChunkResultCache.resetInternal();
            layoutChunk(recycler, state, layoutState, this.layoutChunkResultCache);
            if (!this.layoutChunkResultCache.mFinished) {
                layoutState.mOffset += this.layoutChunkResultCache.mConsumed * layoutState.mLayoutDirection;
                if (!this.layoutChunkResultCache.mIgnoreConsumed || this.mLayoutState.mScrapList != null || !state.isPreLayout()) {
                    layoutState.mAvailable -= this.layoutChunkResultCache.mConsumed;
                    i2 -= this.layoutChunkResultCache.mConsumed;
                }
                if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
                    layoutState.mScrollingOffset += this.layoutChunkResultCache.mConsumed;
                    if (layoutState.mAvailable < 0) {
                        layoutState.mScrollingOffset += layoutState.mAvailable;
                    }
                    recycleByLayoutStateExpose(recycler, layoutState);
                }
                if (z && this.layoutChunkResultCache.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - layoutState.mAvailable;
    }

    protected void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int decoratedMeasurementInOther;
        int i;
        int paddingLeft;
        int i2;
        View next = layoutState.next(recycler);
        if (next == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) next.getLayoutParams();
        if (layoutState.mScrapList == null) {
            if (this.mShouldReverseLayoutExpose == (layoutState.mLayoutDirection == -1)) {
                addView(next);
            } else {
                addView(next, 0);
            }
        } else {
            if (this.mShouldReverseLayoutExpose == (layoutState.mLayoutDirection == -1)) {
                addDisappearingView(next);
            } else {
                addDisappearingView(next, 0);
            }
        }
        measureChildWithMargins(next, 0, 0);
        layoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(next);
        if (getOrientation() == 1) {
            if (isLayoutRTL()) {
                decoratedMeasurementInOther = getWidth() - getPaddingRight();
                paddingLeft = decoratedMeasurementInOther - this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            } else {
                paddingLeft = getPaddingLeft();
                decoratedMeasurementInOther = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + paddingLeft;
            }
            if (layoutState.mLayoutDirection == -1) {
                i2 = layoutState.mOffset;
                i = layoutState.mOffset - layoutChunkResult.mConsumed;
            } else {
                int i3 = layoutState.mOffset;
                i2 = layoutState.mOffset + layoutChunkResult.mConsumed;
                i = i3;
            }
        } else {
            int paddingTop = getPaddingTop();
            int decoratedMeasurementInOther2 = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + paddingTop;
            if (layoutState.mLayoutDirection == -1) {
                int i4 = layoutState.mOffset;
                paddingLeft = layoutState.mOffset - layoutChunkResult.mConsumed;
                i = paddingTop;
                decoratedMeasurementInOther = i4;
                i2 = decoratedMeasurementInOther2;
            } else {
                int i5 = layoutState.mOffset;
                decoratedMeasurementInOther = layoutState.mOffset + layoutChunkResult.mConsumed;
                i = paddingTop;
                paddingLeft = i5;
                i2 = decoratedMeasurementInOther2;
            }
        }
        layoutDecorated(next, paddingLeft + layoutParams.leftMargin, layoutParams.topMargin + i, decoratedMeasurementInOther - layoutParams.rightMargin, i2 - layoutParams.bottomMargin);
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = next.isFocusable();
    }

    private int convertFocusDirectionToLayoutDirectionExpose(int i) {
        int orientation = getOrientation();
        if (i == 1) {
            return -1;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 17) {
            return orientation == 0 ? -1 : Integer.MIN_VALUE;
        }
        if (i == 33) {
            return orientation == 1 ? -1 : Integer.MIN_VALUE;
        }
        if (i != 66) {
            return (i == 130 && orientation == 1) ? 1 : Integer.MIN_VALUE;
        }
        return orientation == 0 ? 1 : Integer.MIN_VALUE;
    }

    private View getChildClosestToStartExpose() {
        return getChildAt(this.mShouldReverseLayoutExpose ? getChildCount() - 1 : 0);
    }

    private View getChildClosestToEndExpose() {
        return getChildAt(this.mShouldReverseLayoutExpose ? 0 : getChildCount() - 1);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int iConvertFocusDirectionToLayoutDirectionExpose;
        View viewMyFindReferenceChildClosestToEnd;
        View childClosestToEndExpose;
        myResolveShouldLayoutReverse();
        if (getChildCount() == 0 || (iConvertFocusDirectionToLayoutDirectionExpose = convertFocusDirectionToLayoutDirectionExpose(i)) == Integer.MIN_VALUE) {
            return null;
        }
        if (iConvertFocusDirectionToLayoutDirectionExpose == -1) {
            viewMyFindReferenceChildClosestToEnd = myFindReferenceChildClosestToStart(state);
        } else {
            viewMyFindReferenceChildClosestToEnd = myFindReferenceChildClosestToEnd(state);
        }
        if (viewMyFindReferenceChildClosestToEnd == null) {
            return null;
        }
        ensureLayoutStateExpose();
        updateLayoutStateExpose(iConvertFocusDirectionToLayoutDirectionExpose, (int) (this.mOrientationHelper.getTotalSpace() * MAX_SCROLL_FACTOR), false, state);
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mRecycle = false;
        this.mLayoutState.mOnRefresLayout = false;
        fill(recycler, this.mLayoutState, state, true);
        if (iConvertFocusDirectionToLayoutDirectionExpose == -1) {
            childClosestToEndExpose = getChildClosestToStartExpose();
        } else {
            childClosestToEndExpose = getChildClosestToEndExpose();
        }
        if (childClosestToEndExpose == viewMyFindReferenceChildClosestToEnd || !childClosestToEndExpose.isFocusable()) {
            return null;
        }
        return childClosestToEndExpose;
    }

    private void logChildren() {
        Log.d(TAG, "internal representation of views on the screen");
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            Log.d(TAG, "item " + getPosition(childAt) + ", coord:" + this.mOrientationHelper.getDecoratedStart(childAt));
        }
        Log.d(TAG, "==============");
    }

    private void validateChildOrderExpose() {
        Log.d(TAG, "validating child count " + getChildCount());
        if (getChildCount() < 1) {
            return;
        }
        int position = getPosition(getChildAt(0));
        int decoratedStart = this.mOrientationHelper.getDecoratedStart(getChildAt(0));
        if (this.mShouldReverseLayoutExpose) {
            for (int i = 1; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                int position2 = getPosition(childAt);
                int decoratedStart2 = this.mOrientationHelper.getDecoratedStart(childAt);
                if (position2 < position) {
                    logChildren();
                    StringBuilder sb = new StringBuilder("detected invalid position. loc invalid? ");
                    sb.append(decoratedStart2 < decoratedStart);
                    throw new RuntimeException(sb.toString());
                }
                if (decoratedStart2 > decoratedStart) {
                    logChildren();
                    throw new RuntimeException("detected invalid location");
                }
            }
            return;
        }
        for (int i2 = 1; i2 < getChildCount(); i2++) {
            View childAt2 = getChildAt(i2);
            int position3 = getPosition(childAt2);
            int decoratedStart3 = this.mOrientationHelper.getDecoratedStart(childAt2);
            if (position3 < position) {
                logChildren();
                StringBuilder sb2 = new StringBuilder("detected invalid position. loc invalid? ");
                sb2.append(decoratedStart3 < decoratedStart);
                throw new RuntimeException(sb2.toString());
            }
            if (decoratedStart3 < decoratedStart) {
                logChildren();
                throw new RuntimeException("detected invalid location");
            }
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mCurrentPendingSavedState == null && this.mLastStackFromEnd == getStackFromEnd();
    }

    protected void addHiddenView(View view, boolean z) {
        addView(view, z ? 0 : -1);
        this.mChildHelperWrapper.hide(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideView(View view) {
        this.mChildHelperWrapper.hide(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showView(View view) {
        this.mChildHelperWrapper.show(view);
    }

    protected View findHiddenView(int i) {
        return this.mChildHelperWrapper.findHiddenNonRemovedView(i, -1);
    }

    protected boolean isHidden(View view) {
        return this.mChildHelperWrapper.isHidden(view);
    }

    protected static boolean isViewHolderUpdated(RecyclerView.ViewHolder viewHolder) {
        return new ViewHolderWrapper(viewHolder).requireUpdated();
    }

    protected static void attachViewHolder(RecyclerView.LayoutParams layoutParams, RecyclerView.ViewHolder viewHolder) {
        try {
            if (vhField == null) {
                vhField = RecyclerView.LayoutParams.class.getDeclaredField("mViewHolder");
            }
            vhField.setAccessible(true);
            vhField.set(layoutParams, viewHolder);
            if (vhSetFlags == null) {
                Class cls = Integer.TYPE;
                Method declaredMethod = RecyclerView.ViewHolder.class.getDeclaredMethod("setFlags", cls, cls);
                vhSetFlags = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            vhSetFlags.invoke(viewHolder, 4, 4);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    public static class LayoutState {
        static final int INVALID_LAYOUT = Integer.MIN_VALUE;
        public static final int ITEM_DIRECTION_HEAD = -1;
        public static final int ITEM_DIRECTION_TAIL = 1;
        public static final int LAYOUT_END = 1;
        public static final int LAYOUT_START = -1;
        static final int SCOLLING_OFFSET_NaN = Integer.MIN_VALUE;
        static final String TAG = "_ExposeLLayoutManager#LayoutState";
        public int mAvailable;
        public int mCurrentPosition;
        public int mItemDirection;
        public int mLayoutDirection;
        public int mOffset;
        public int mScrollingOffset;
        private Method vhIsRemoved;
        public boolean mOnRefresLayout = false;
        public boolean mRecycle = true;
        public int mExtra = 0;
        public int mFixOffset = 0;
        public boolean mIsPreLayout = false;
        public List<RecyclerView.ViewHolder> mScrapList = null;

        public LayoutState() {
            this.vhIsRemoved = null;
            try {
                Method declaredMethod = RecyclerView.ViewHolder.class.getDeclaredMethod("isRemoved", null);
                this.vhIsRemoved = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        public boolean hasMore(RecyclerView.State state) {
            int i = this.mCurrentPosition;
            return i >= 0 && i < state.getItemCount();
        }

        public View next(RecyclerView.Recycler recycler) {
            if (this.mScrapList != null) {
                return nextFromLimitedList();
            }
            View viewForPosition = recycler.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return viewForPosition;
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0039  */
        /* JADX WARN: Code duplicated, block: B:24:0x004d  */
        /* JADX WARN: Code duplicated, block: B:33:0x0051 A[EDGE_INSN: B:33:0x0051->B:26:0x0051 BREAK  A[LOOP:0: B:3:0x000d->B:25:0x004e], SYNTHETIC] */
        private View nextFromLimitedList() {
            boolean zBooleanValue;
            int position;
            int size = this.mScrapList.size();
            int i = Integer.MAX_VALUE;
            RecyclerView.ViewHolder viewHolder = null;
            for (int i2 = 0; i2 < size; i2++) {
                RecyclerView.ViewHolder viewHolder2 = this.mScrapList.get(i2);
                if (!this.mIsPreLayout) {
                    try {
                        zBooleanValue = ((Boolean) this.vhIsRemoved.invoke(viewHolder2, null)).booleanValue();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        zBooleanValue = false;
                    } catch (InvocationTargetException e2) {
                        e2.printStackTrace();
                        zBooleanValue = false;
                    }
                    if (this.mIsPreLayout || !zBooleanValue) {
                        position = (viewHolder2.getPosition() - this.mCurrentPosition) * this.mItemDirection;
                        if (position < 0 && position < i) {
                            viewHolder = viewHolder2;
                            if (position == 0) {
                                break;
                            }
                            i = position;
                        }
                    }
                } else {
                    position = (viewHolder2.getPosition() - this.mCurrentPosition) * this.mItemDirection;
                    if (position < 0) {
                        continue;
                    } else {
                        viewHolder = viewHolder2;
                        if (position == 0) {
                            break;
                            break;
                        }
                        i = position;
                    }
                }
            }
            if (viewHolder == null) {
                return null;
            }
            this.mCurrentPosition = viewHolder.getPosition() + this.mItemDirection;
            return viewHolder.itemView;
        }

        void log() {
            Log.d(TAG, "avail:" + this.mAvailable + ", ind:" + this.mCurrentPosition + ", dir:" + this.mItemDirection + ", offset:" + this.mOffset + ", layoutDir:" + this.mLayoutDirection);
        }
    }

    protected class AnchorInfo {
        public int mCoordinate;
        public boolean mLayoutFromEnd;
        public int mPosition;

        protected AnchorInfo() {
        }

        void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
        }

        void assignCoordinateFromPadding() {
            this.mCoordinate = this.mLayoutFromEnd ? ExposeLinearLayoutManagerEx.this.mOrientationHelper.getEndAfterPadding() : ExposeLinearLayoutManagerEx.this.mOrientationHelper.getStartAfterPadding();
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + '}';
        }

        public boolean assignFromViewIfValid(View view, RecyclerView.State state) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            if (layoutParams.isItemRemoved() || layoutParams.getViewPosition() < 0 || layoutParams.getViewPosition() >= state.getItemCount()) {
                return false;
            }
            assignFromView(view);
            return true;
        }

        public void assignFromView(View view) {
            if (this.mLayoutFromEnd) {
                this.mCoordinate = ExposeLinearLayoutManagerEx.this.mOrientationHelper.getDecoratedEnd(view) + ExposeLinearLayoutManagerEx.this.computeAlignOffset(view, this.mLayoutFromEnd, true) + ExposeLinearLayoutManagerEx.this.mOrientationHelper.getTotalSpaceChange();
            } else {
                this.mCoordinate = ExposeLinearLayoutManagerEx.this.mOrientationHelper.getDecoratedStart(view) + ExposeLinearLayoutManagerEx.this.computeAlignOffset(view, this.mLayoutFromEnd, true);
            }
            this.mPosition = ExposeLinearLayoutManagerEx.this.getPosition(view);
        }
    }

    static class ViewHolderWrapper {
        private static Method mIsChanged;
        private static Method mIsInvalid;
        private static Method mIsRemoved;
        private static Method mSetFlags;
        private static Method mShouldIgnore;
        private RecyclerView.ViewHolder mHolder;

        static {
            try {
                Method declaredMethod = RecyclerView.ViewHolder.class.getDeclaredMethod("shouldIgnore", null);
                mShouldIgnore = declaredMethod;
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = RecyclerView.ViewHolder.class.getDeclaredMethod("isInvalid", null);
                mIsInvalid = declaredMethod2;
                declaredMethod2.setAccessible(true);
                Method declaredMethod3 = RecyclerView.ViewHolder.class.getDeclaredMethod("isRemoved", null);
                mIsRemoved = declaredMethod3;
                declaredMethod3.setAccessible(true);
                Class cls = Integer.TYPE;
                Method declaredMethod4 = RecyclerView.ViewHolder.class.getDeclaredMethod("setFlags", cls, cls);
                mSetFlags = declaredMethod4;
                declaredMethod4.setAccessible(true);
                try {
                    mIsChanged = RecyclerView.ViewHolder.class.getDeclaredMethod("isChanged", null);
                } catch (NoSuchMethodException unused) {
                    mIsChanged = RecyclerView.ViewHolder.class.getDeclaredMethod("isUpdated", null);
                }
                mIsChanged.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        public static void setFlags(RecyclerView.ViewHolder viewHolder, int i, int i2) {
            try {
                mSetFlags.invoke(viewHolder, Integer.valueOf(i), Integer.valueOf(i2));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }

        public ViewHolderWrapper(RecyclerView.ViewHolder viewHolder) {
            this.mHolder = viewHolder;
        }

        boolean isInvalid() {
            Method method = mIsInvalid;
            if (method == null) {
                return true;
            }
            try {
                return ((Boolean) method.invoke(this.mHolder, null)).booleanValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return true;
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
                return true;
            }
        }

        boolean isRemoved() {
            Method method = mIsRemoved;
            if (method == null) {
                return true;
            }
            try {
                return ((Boolean) method.invoke(this.mHolder, null)).booleanValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return true;
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
                return true;
            }
        }

        boolean isChanged() {
            Method method = mIsChanged;
            if (method == null) {
                return true;
            }
            try {
                return ((Boolean) method.invoke(this.mHolder, null)).booleanValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return true;
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
                return true;
            }
        }

        void setFlags(int i, int i2) {
            try {
                mSetFlags.invoke(this.mHolder, Integer.valueOf(i), Integer.valueOf(i2));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }

        public boolean requireUpdated() {
            return isInvalid() || isRemoved() || isChanged();
        }
    }

    class ChildHelperWrapper {
        private Object[] args = new Object[1];
        private Field mChildHelperField;
        private Method mClearMethod;
        private Method mFindHiddenNonRemovedViewMethod;
        private Method mFindHiddenNonRemovedViewMethod25;
        private Field mHiddenViewField;
        private Method mHideMethod;
        private Object mInnerBucket;
        private Object mInnerChildHelper;
        private List mInnerHiddenView;
        private Method mIsHideMethod;
        private RecyclerView.LayoutManager mLayoutManager;

        void ensureChildHelper() {
            try {
                if (this.mInnerChildHelper == null) {
                    Object obj = this.mChildHelperField.get(this.mLayoutManager);
                    this.mInnerChildHelper = obj;
                    if (obj == null) {
                        return;
                    }
                    Class<?> cls = obj.getClass();
                    Method declaredMethod = cls.getDeclaredMethod("hide", View.class);
                    this.mHideMethod = declaredMethod;
                    declaredMethod.setAccessible(true);
                    try {
                        Class<?> cls2 = Integer.TYPE;
                        Method declaredMethod2 = cls.getDeclaredMethod("findHiddenNonRemovedView", cls2, cls2);
                        this.mFindHiddenNonRemovedViewMethod = declaredMethod2;
                        declaredMethod2.setAccessible(true);
                    } catch (NoSuchMethodException unused) {
                        Method declaredMethod3 = cls.getDeclaredMethod("findHiddenNonRemovedView", Integer.TYPE);
                        this.mFindHiddenNonRemovedViewMethod25 = declaredMethod3;
                        declaredMethod3.setAccessible(true);
                    }
                    Method declaredMethod4 = cls.getDeclaredMethod("isHidden", View.class);
                    this.mIsHideMethod = declaredMethod4;
                    declaredMethod4.setAccessible(true);
                    Field declaredField = cls.getDeclaredField("mBucket");
                    declaredField.setAccessible(true);
                    Object obj2 = declaredField.get(this.mInnerChildHelper);
                    this.mInnerBucket = obj2;
                    Method declaredMethod5 = obj2.getClass().getDeclaredMethod("clear", Integer.TYPE);
                    this.mClearMethod = declaredMethod5;
                    declaredMethod5.setAccessible(true);
                    Field declaredField2 = cls.getDeclaredField("mHiddenViews");
                    this.mHiddenViewField = declaredField2;
                    declaredField2.setAccessible(true);
                    this.mInnerHiddenView = (List) this.mHiddenViewField.get(this.mInnerChildHelper);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ChildHelperWrapper(RecyclerView.LayoutManager layoutManager) {
            this.mLayoutManager = layoutManager;
            try {
                Field declaredField = RecyclerView.LayoutManager.class.getDeclaredField("mChildHelper");
                this.mChildHelperField = declaredField;
                declaredField.setAccessible(true);
                ensureChildHelper();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void hide(View view) {
            try {
                ensureChildHelper();
                if (this.mInnerHiddenView.indexOf(view) < 0) {
                    Object[] objArr = this.args;
                    objArr[0] = view;
                    this.mHideMethod.invoke(this.mInnerChildHelper, objArr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void show(View view) {
            try {
                ensureChildHelper();
                this.args[0] = Integer.valueOf(ExposeLinearLayoutManagerEx.this.mRecyclerView.indexOfChild(view));
                this.mClearMethod.invoke(this.mInnerBucket, this.args);
                List list = this.mInnerHiddenView;
                if (list != null) {
                    list.remove(view);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        View findHiddenNonRemovedView(int i, int i2) {
            try {
                ensureChildHelper();
                Method method = this.mFindHiddenNonRemovedViewMethod;
                if (method != null) {
                    return (View) method.invoke(this.mInnerChildHelper, Integer.valueOf(i), -1);
                }
                Method method2 = this.mFindHiddenNonRemovedViewMethod25;
                if (method2 != null) {
                    return (View) method2.invoke(this.mInnerChildHelper, Integer.valueOf(i));
                }
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
                return null;
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        }

        boolean isHidden(View view) {
            try {
                ensureChildHelper();
                Object[] objArr = this.args;
                objArr[0] = view;
                return ((Boolean) this.mIsHideMethod.invoke(this.mInnerChildHelper, objArr)).booleanValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
                return false;
            } catch (Exception e3) {
                e3.printStackTrace();
                return false;
            }
        }
    }
}
