package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.owen.tvrecyclerview.BaseLayoutManager;
import com.owen.tvrecyclerview.R;
import com.owen.tvrecyclerview.TwoWayLayoutManager;
import com.owen.tvrecyclerview.utils.Loger;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class TvRecyclerView extends RecyclerView implements View.OnClickListener, View.OnFocusChangeListener {
    private static final int DEFAULT_LOAD_MORE_BEFOREHAND_COUNT = 4;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class, Integer.TYPE};
    private final IRecyclerViewDataObserver mDataObserver;
    private boolean mHasFocusWithPrevious;
    private boolean mHasMoreData;
    public int mHorizontalSpacingWithMargins;
    private boolean mIsMemoryFocus;
    private boolean mIsMenu;
    private int mLoadMoreBeforehandCount;
    private boolean mLoadingMore;
    private int mOldHorizontalSpacingWithMargins;
    private int mOldVerticalSpacingWithMargins;
    private OnInBorderKeyEventListener mOnInBorderKeyEventListener;
    private OnItemListener mOnItemListener;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean mOptimizeLayout;
    private Point mScrollPoint;
    protected int mScrollX;
    protected int mScrollY;
    private boolean mSelectedItemCentered;
    private int mSelectedItemOffsetEnd;
    private int mSelectedItemOffsetStart;
    private int mSelectedPosition;
    private boolean mShouldReverseLayout;
    private final Rect mTempRect;
    public int mVerticalSpacingWithMargins;

    public interface OnInBorderKeyEventListener {
        boolean onInBorderKeyEvent(int i, View view);
    }

    public interface OnItemListener {
        void onItemClick(TvRecyclerView tvRecyclerView, View view, int i);

        void onItemPreSelected(TvRecyclerView tvRecyclerView, View view, int i);

        void onItemSelected(TvRecyclerView tvRecyclerView, View view, int i);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    @Override // android.view.View
    public boolean isInEditMode() {
        return true;
    }

    public TvRecyclerView(Context context) {
        this(context, null);
    }

    public TvRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TvRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVerticalSpacingWithMargins = 0;
        this.mHorizontalSpacingWithMargins = 0;
        this.mOldVerticalSpacingWithMargins = 0;
        this.mOldHorizontalSpacingWithMargins = 0;
        this.mHasMoreData = true;
        this.mLoadingMore = false;
        this.mSelectedPosition = -1;
        this.mHasFocusWithPrevious = false;
        this.mTempRect = new Rect();
        this.mDataObserver = new IRecyclerViewDataObserver();
        this.mShouldReverseLayout = true;
        this.mScrollPoint = new Point();
        init(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView, i, 0);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.TvRecyclerView_tv_layoutManager);
        if (!TextUtils.isEmpty(string)) {
            createLayoutManager(context, string, attributeSet, i);
        }
        this.mSelectedItemCentered = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TvRecyclerView_tv_selectedItemIsCentered, false);
        this.mIsMenu = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TvRecyclerView_tv_isMenu, false);
        this.mIsMemoryFocus = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TvRecyclerView_tv_isMemoryFocus, true);
        this.mLoadMoreBeforehandCount = typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_tv_loadMoreBeforehandCount, 4);
        this.mSelectedItemOffsetStart = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvRecyclerView_tv_selectedItemOffsetStart, 0);
        this.mSelectedItemOffsetEnd = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvRecyclerView_tv_selectedItemOffsetEnd, 0);
        this.mOptimizeLayout = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TvRecyclerView_tv_optimizeLayout, false);
        setSpacingWithMargins(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvRecyclerView_tv_verticalSpacingWithMargins, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvRecyclerView_tv_horizontalSpacingWithMargins, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    private void init(Context context) {
        setChildrenDrawingOrderEnabled(true);
        setWillNotDraw(true);
        setHasFixedSize(true);
        setOverScrollMode(2);
        setClipChildren(false);
        setClipToPadding(false);
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (getItemAnimator() != null) {
            ((SimpleItemAnimator) getItemAnimator()).setSupportsChangeAnimations(false);
        }
    }

    private void createLayoutManager(Context context, String str, AttributeSet attributeSet, int i) {
        ClassLoader classLoader;
        Object[] objArr;
        Constructor constructor;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.length() != 0) {
                String fullClassName = getFullClassName(context, strTrim);
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> clsAsSubclass = classLoader.loadClass(fullClassName).asSubclass(RecyclerView.LayoutManager.class);
                    try {
                        constructor = clsAsSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i)};
                    } catch (NoSuchMethodException e) {
                        objArr = null;
                        try {
                            constructor = clsAsSubclass.getConstructor(null);
                        } catch (NoSuchMethodException e2) {
                            e2.initCause(e);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, e2);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((RecyclerView.LayoutManager) constructor.newInstance(objArr));
                } catch (ClassCastException e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, e3);
                } catch (ClassNotFoundException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, e4);
                } catch (IllegalAccessException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, e5);
                } catch (InstantiationException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e6);
                } catch (InvocationTargetException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e7);
                }
            }
        }
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return TvRecyclerView.class.getPackage().getName() + '.' + str;
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }

    public void setSelectedPosition(int i) {
        this.mSelectedPosition = i;
    }

    public void setMemoryFocus(boolean z) {
        this.mIsMemoryFocus = z;
    }

    public boolean isMemoryFocus() {
        return this.mIsMemoryFocus;
    }

    public void setMenu(boolean z) {
        this.mIsMenu = z;
    }

    public boolean isMenu() {
        return this.mIsMenu;
    }

    public void setLoadMoreBeforehandCount(int i) {
        this.mLoadMoreBeforehandCount = i;
    }

    public int getLoadMoreBeforehandCount() {
        return this.mLoadMoreBeforehandCount;
    }

    public boolean hasMoreData() {
        return this.mHasMoreData;
    }

    public void setHasMoreData(boolean z) {
        this.mHasMoreData = z;
    }

    public void finishLoadMoreWithNoMore() {
        this.mLoadingMore = false;
        setHasMoreData(false);
    }

    public void finishLoadMore() {
        this.mLoadingMore = false;
    }

    public boolean isLoadingMore() {
        return this.mLoadingMore;
    }

    public void setSelectedItemOffset(int i, int i2) {
        this.mSelectedItemOffsetStart = i;
        this.mSelectedItemOffsetEnd = i2;
    }

    public int getSelectedItemOffsetStart() {
        return this.mSelectedItemOffsetStart;
    }

    public int getSelectedItemOffsetEnd() {
        return this.mSelectedItemOffsetEnd;
    }

    public void setSelectedItemAtCentered(boolean z) {
        this.mSelectedItemCentered = z;
    }

    public boolean isSelectedItemCentered() {
        return this.mSelectedItemCentered;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean zHasFocus;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mHasFocusWithPrevious = this.mHasFocusWithPrevious || hasFocus();
        Loger.i("onLayout...start hasFocus()=" + this.mHasFocusWithPrevious + " changed=" + z + " ,mShouldReverseLayout=" + this.mShouldReverseLayout);
        if (!this.mOptimizeLayout || z || this.mShouldReverseLayout) {
            super.onLayout(z, i, i2, i3, i4);
            this.mShouldReverseLayout = false;
            zHasFocus = hasFocus();
            if (!zHasFocus) {
                int i5 = this.mSelectedPosition;
                if (i5 < 0) {
                    this.mSelectedPosition = getFirstVisibleAndFocusablePosition();
                } else if (i5 >= getItemCount()) {
                    this.mSelectedPosition = getLastVisibleAndFocusablePosition();
                }
                if (this.mHasFocusWithPrevious && getPreserveFocusAfterLayout()) {
                    requestDefaultFocus();
                } else if (this.mIsMenu) {
                    setItemActivated(this.mSelectedPosition);
                }
            }
        } else {
            zHasFocus = hasFocus();
        }
        this.mHasFocusWithPrevious = false;
        Loger.i("onLayout...end layoutAfterFocus=" + zHasFocus + ". used time " + ((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0f) + CmcdData.STREAMING_FORMAT_SS);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onMeasure(int i, int i2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Loger.i("onMeasure...start");
        super.onMeasure(i, i2);
        Loger.i("onMeasure...end. used time " + ((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0f) + CmcdData.STREAMING_FORMAT_SS);
    }

    public int getItemCount() {
        if (getAdapter() != null) {
            return getAdapter().getItemCount();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void swapAdapter(RecyclerView.Adapter adapter, boolean z) {
        if (adapter == null) {
            return;
        }
        resetAdapter(adapter, false);
        super.swapAdapter(adapter, z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter == null) {
            return;
        }
        resetAdapter(adapter, false);
        super.setAdapter(adapter);
    }

    public void setAdapter(RecyclerView.Adapter adapter, boolean z) {
        if (adapter == null) {
            return;
        }
        resetAdapter(adapter, z);
        super.setAdapter(adapter);
    }

    private void resetAdapter(RecyclerView.Adapter adapter, boolean z) {
        RecyclerView.Adapter adapter2 = getAdapter();
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.mDataObserver);
            this.mShouldReverseLayout = true;
        }
        adapter.registerAdapterDataObserver(this.mDataObserver);
        View childAt = getChildAt(0);
        if (childAt != null && adapter2 != null && (getLayoutManager() instanceof BaseLayoutManager)) {
            this.mHasFocusWithPrevious = hasFocus();
            int decoratedTop = (getLayoutManager().canScrollVertically() ? getLayoutManager().getDecoratedTop(childAt) : getLayoutManager().getDecoratedLeft(childAt)) - (getLayoutManager().canScrollVertically() ? getPaddingTop() : getPaddingLeft());
            scrollBy(decoratedTop, decoratedTop);
        } else {
            this.mSelectedPosition = getFirstVisibleAndFocusablePosition();
        }
        if (z) {
            this.mSelectedPosition = -1;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnItemListener onItemListener = this.mOnItemListener;
        if (onItemListener == null || this == view) {
            return;
        }
        onItemListener.onItemClick(this, view, getChildAdapterPosition(view));
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(final View view, boolean z) {
        if (view != null) {
            int childAdapterPosition = getChildAdapterPosition(view);
            final boolean z2 = view instanceof RecyclerView;
            if (!z2) {
                view.setSelected(z);
            }
            if (z) {
                this.mSelectedPosition = childAdapterPosition;
                if (z2) {
                    return;
                }
                if (this.mIsMenu && view.isActivated()) {
                    view.setActivated(false);
                }
                OnItemListener onItemListener = this.mOnItemListener;
                if (onItemListener != null) {
                    onItemListener.onItemSelected(this, view, childAdapterPosition);
                    return;
                }
                return;
            }
            view.postDelayed(new Runnable() { // from class: com.owen.tvrecyclerview.widget.TvRecyclerView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (TvRecyclerView.this.hasFocus()) {
                        return;
                    }
                    if (TvRecyclerView.this.mIsMenu && !z2) {
                        view.setActivated(true);
                    }
                    if (TvRecyclerView.this.getOnFocusChangeListener() != null) {
                        TvRecyclerView.this.getOnFocusChangeListener().onFocusChange(TvRecyclerView.this, false);
                    }
                }
            }, 6L);
            OnItemListener onItemListener2 = this.mOnItemListener;
            if (onItemListener2 == null || z2) {
                return;
            }
            onItemListener2.onItemPreSelected(this, view, childAdapterPosition);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int firstVisibleAndFocusablePosition = this.mSelectedPosition;
        if (firstVisibleAndFocusablePosition == -1 || !this.mIsMemoryFocus) {
            firstVisibleAndFocusablePosition = getFirstVisibleAndFocusablePosition();
        }
        View viewFindViewByPosition = getLayoutManager() != null ? getLayoutManager().findViewByPosition(firstVisibleAndFocusablePosition) : null;
        if (viewFindViewByPosition != null) {
            if (getOnFocusChangeListener() != null) {
                getOnFocusChangeListener().onFocusChange(this, true);
            }
            return viewFindViewByPosition.requestFocus(i, rect);
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (!hasFocus() && isFocusable()) {
            arrayList.add(this);
        } else {
            super.addFocusables(arrayList, i, i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int i) {
        if (i == 0) {
            setScrollValue(0, 0);
            if (this.mOnLoadMoreListener != null && !this.mLoadingMore && this.mHasMoreData && getLastVisiblePosition() >= getAdapter().getItemCount() - (this.mLoadMoreBeforehandCount + 1)) {
                this.mLoadingMore = true;
                this.mOnLoadMoreListener.onLoadMore();
            }
        }
        super.onScrollStateChanged(i);
    }

    void setScrollValue(int i, int i2) {
        if (i != 0 || i2 != 0) {
            this.mScrollPoint.set(i, i2);
            setTag(this.mScrollPoint);
        } else {
            setTag(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getFreeHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getFreeWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        int freeHeight;
        int iHeight;
        if (view == null || getLayoutManager() == null) {
            return false;
        }
        if (this.mSelectedItemCentered) {
            getDecoratedBoundsWithMargins(view, this.mTempRect);
            if (getLayoutManager().canScrollHorizontally()) {
                freeHeight = getFreeWidth();
                iHeight = this.mTempRect.width();
            } else {
                freeHeight = getFreeHeight();
                iHeight = this.mTempRect.height();
            }
            int i = (freeHeight - iHeight) / 2;
            this.mSelectedItemOffsetStart = i;
            this.mSelectedItemOffsetEnd = i;
        }
        int[] childRectangleOnScreenScrollAmount2 = getChildRectangleOnScreenScrollAmount2(view, rect, this.mSelectedItemOffsetStart, this.mSelectedItemOffsetEnd);
        int i2 = childRectangleOnScreenScrollAmount2[0];
        int i3 = childRectangleOnScreenScrollAmount2[1];
        Loger.i("dx=" + i2 + " dy=" + i3);
        smoothScrollBy(i2, i3);
        if (i2 != 0 || i3 != 0) {
            return true;
        }
        postInvalidate();
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void smoothScrollBy(int i, int i2, Interpolator interpolator) {
        setScrollValue(i, i2);
        super.smoothScrollBy(i, i2, interpolator);
    }

    private int[] getChildRectangleOnScreenScrollAmount2(View view, Rect rect, int i, int i2) {
        int i3;
        int iComputeScrollOffset;
        int i4 = 0;
        if (getLayoutManager() != null) {
            getDecoratedBoundsWithMargins(view, this.mTempRect);
            if (getLayoutManager().canScrollHorizontally()) {
                int paddingRight = (this.mTempRect.right + getPaddingRight()) - getWidth();
                int paddingLeft = this.mTempRect.left - getPaddingLeft();
                Loger.i("zsq left=" + paddingLeft + " right=" + paddingRight);
                iComputeScrollOffset = computeScrollOffset(paddingLeft, paddingRight, i, i2);
            } else {
                iComputeScrollOffset = 0;
            }
            if (getLayoutManager().canScrollVertically()) {
                int paddingBottom = (this.mTempRect.bottom + getPaddingBottom()) - getHeight();
                int paddingTop = this.mTempRect.top - getPaddingTop();
                Loger.i("zsq top=" + paddingTop + " bottom=" + paddingBottom);
                int iComputeScrollOffset2 = computeScrollOffset(paddingTop, paddingBottom, i, i2);
                i4 = iComputeScrollOffset;
                i3 = iComputeScrollOffset2;
            } else {
                i4 = iComputeScrollOffset;
                i3 = 0;
            }
        } else {
            i3 = 0;
        }
        Loger.i("zsq dx=" + i4 + " dy=" + i3);
        return new int[]{i4, i3};
    }

    private int computeScrollOffset(int i, int i2, int i3, int i4) {
        Loger.i("zsq start=" + i + " end=" + i2 + " offsetStart=" + i3 + " offsetEnd=" + i4);
        StringBuilder sb = new StringBuilder("zsq canScrollHorizontally( -1)=");
        sb.append(canScrollHorizontally(-1));
        sb.append(" canScrollVertically( -1)=");
        sb.append(canScrollVertically(-1));
        Loger.i(sb.toString());
        if (i2 > 0) {
            return getLastVisiblePosition() != getItemCount() - 1 ? i2 + i4 : i2;
        }
        if (i < 0) {
            return getFirstVisiblePosition() != 0 ? i - i3 : i;
        }
        if (i > 0 && i < i3 && (canScrollHorizontally(-1) || canScrollVertically(-1))) {
            return i - i3;
        }
        if (Math.abs(i2) <= 0 || Math.abs(i2) >= i4) {
            return 0;
        }
        if (canScrollHorizontally(1) || canScrollVertically(1)) {
            return i4 - Math.abs(i2);
        }
        return 0;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        if (getLayoutManager() != null && (getLayoutManager() instanceof TwoWayLayoutManager)) {
            TwoWayLayoutManager twoWayLayoutManager = (TwoWayLayoutManager) getLayoutManager();
            return twoWayLayoutManager.canScrollHorizontally() && (i <= 0 ? !twoWayLayoutManager.cannotScrollForward(i) : !twoWayLayoutManager.cannotScrollBackward(i));
        }
        return super.canScrollHorizontally(i);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        if (getLayoutManager() != null && (getLayoutManager() instanceof TwoWayLayoutManager)) {
            TwoWayLayoutManager twoWayLayoutManager = (TwoWayLayoutManager) getLayoutManager();
            return twoWayLayoutManager.canScrollVertically() && (i <= 0 ? !twoWayLayoutManager.cannotScrollForward(i) : !twoWayLayoutManager.cannotScrollBackward(i));
        }
        return super.canScrollVertically(i);
    }

    public void setSpacingWithMargins(int i, int i2) {
        int i3 = this.mVerticalSpacingWithMargins;
        if (i3 == i && this.mHorizontalSpacingWithMargins == i2) {
            return;
        }
        this.mOldVerticalSpacingWithMargins = i3;
        this.mOldHorizontalSpacingWithMargins = this.mHorizontalSpacingWithMargins;
        this.mVerticalSpacingWithMargins = i;
        this.mHorizontalSpacingWithMargins = i2;
        adjustPadding();
    }

    private void adjustPadding() {
        int i = this.mVerticalSpacingWithMargins;
        if (i >= 0 || this.mHorizontalSpacingWithMargins >= 0) {
            int i2 = i / 2;
            int i3 = this.mHorizontalSpacingWithMargins / 2;
            int i4 = this.mOldVerticalSpacingWithMargins / 2;
            int i5 = this.mOldHorizontalSpacingWithMargins / 2;
            setPadding((getPaddingLeft() + i5) - i3, (getPaddingTop() + i4) - i2, (getPaddingRight() + i5) - i3, (getPaddingBottom() + i4) - i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        int i;
        boolean zCheckLayoutParams = super.checkLayoutParams(layoutParams);
        if (zCheckLayoutParams && ((i = this.mVerticalSpacingWithMargins) >= 0 || this.mHorizontalSpacingWithMargins >= 0)) {
            int i2 = i / 2;
            int i3 = this.mHorizontalSpacingWithMargins / 2;
            ((RecyclerView.LayoutParams) layoutParams).setMargins(i3, i2, i3, i2);
        }
        return zCheckLayoutParams;
    }

    public int getFirstVisibleAndFocusablePosition() {
        for (int firstVisiblePosition = getFirstVisiblePosition(); firstVisiblePosition < getChildCount(); firstVisiblePosition++) {
            View viewFindViewByPosition = getLayoutManager().findViewByPosition(firstVisiblePosition);
            if (viewFindViewByPosition != null && viewFindViewByPosition.isFocusable()) {
                return firstVisiblePosition;
            }
        }
        return -1;
    }

    public int getFirstVisiblePosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        }
        return getChildAdapterPosition(getChildAt(0));
    }

    public int getLastVisibleAndFocusablePosition() {
        int firstVisiblePosition = getFirstVisiblePosition();
        for (int lastVisiblePosition = getLastVisiblePosition(); lastVisiblePosition >= firstVisiblePosition; lastVisiblePosition--) {
            View viewFindViewByPosition = getLayoutManager().findViewByPosition(lastVisiblePosition);
            if (viewFindViewByPosition != null && viewFindViewByPosition.isFocusable()) {
                return lastVisiblePosition;
            }
        }
        return -1;
    }

    public int getLastVisiblePosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        if (getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        }
        return getChildAdapterPosition(getChildAt(childCount - 1));
    }

    public void requestDefaultFocus() {
        Loger.i("zqq requestDefaultFocus");
        if (this.mIsMenu || this.mIsMemoryFocus) {
            if (this.mSelectedPosition < 0) {
                this.mSelectedPosition = getFirstVisibleAndFocusablePosition();
            }
            setSelection(this.mSelectedPosition);
            return;
        }
        setSelection(getFirstVisibleAndFocusablePosition());
    }

    public void setSelection(final int i) {
        post(new Runnable() { // from class: com.owen.tvrecyclerview.widget.TvRecyclerView.2
            @Override // java.lang.Runnable
            public void run() {
                int i2;
                if (TvRecyclerView.this.getAdapter() == null || (i2 = i) < 0 || i2 >= TvRecyclerView.this.getItemCount()) {
                    return;
                }
                TvRecyclerView.this.mSelectedPosition = i;
                View viewFindViewByPosition = TvRecyclerView.this.getLayoutManager() != null ? TvRecyclerView.this.getLayoutManager().findViewByPosition(i) : null;
                if (viewFindViewByPosition != null) {
                    if (!TvRecyclerView.this.hasFocus()) {
                        TvRecyclerView.this.onFocusChanged(true, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, null);
                    }
                    viewFindViewByPosition.requestFocus();
                } else {
                    TvRecyclerView tvRecyclerView = TvRecyclerView.this;
                    TvSmoothScroller tvSmoothScroller = tvRecyclerView.new TvSmoothScroller(tvRecyclerView.getContext(), true, false, TvRecyclerView.this.mSelectedItemOffsetStart);
                    tvSmoothScroller.setTargetPosition(i);
                    TvRecyclerView.this.getLayoutManager().startSmoothScroll(tvSmoothScroller);
                }
            }
        });
    }

    public void setSelectionWithSmooth(int i) {
        if (getAdapter() == null || i < 0 || i >= getItemCount()) {
            return;
        }
        this.mSelectedPosition = i;
        TvSmoothScroller tvSmoothScroller = new TvSmoothScroller(getContext(), true, true, this.mSelectedItemOffsetStart);
        tvSmoothScroller.setTargetPosition(i);
        getLayoutManager().startSmoothScroll(tvSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void scrollToPosition(int i) {
        scrollToPositionWithOffset(i, this.mSelectedItemOffsetStart);
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        scrollToPositionWithOffset(i, i2, false);
    }

    public void scrollToPositionWithOffset(int i, int i2, boolean z) {
        scrollToPosition(i, z, false, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void smoothScrollToPosition(int i) {
        smoothScrollToPositionWithOffset(i, this.mSelectedItemOffsetStart);
    }

    public void smoothScrollToPositionWithOffset(int i, int i2) {
        smoothScrollToPositionWithOffset(i, i2, false);
    }

    public void smoothScrollToPositionWithOffset(int i, int i2, boolean z) {
        scrollToPosition(i, z, true, i2);
    }

    private void scrollToPosition(int i, boolean z, boolean z2, int i2) {
        this.mSelectedPosition = i;
        TvSmoothScroller tvSmoothScroller = new TvSmoothScroller(getContext(), z, z2, i2);
        tvSmoothScroller.setTargetPosition(i);
        getLayoutManager().startSmoothScroll(tvSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        View focusedChild = getFocusedChild();
        if (focusedChild != null) {
            int iIndexOfChild = indexOfChild(focusedChild);
            int i3 = i - 1;
            if (i2 == i3) {
                return iIndexOfChild > i2 ? i2 : iIndexOfChild;
            }
            if (iIndexOfChild == i2) {
                return i3;
            }
        }
        return i2;
    }

    public boolean isScrolling() {
        return getScrollState() != 0;
    }

    private View findNextFocus(int i) {
        return FocusFinder.getInstance().findNextFocus(this, getFocusedChild(), i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View view, int i) {
        return super.dispatchUnhandledMove(view, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i) {
        View viewFindNextFocus = findNextFocus(i);
        if (!hasInBorder(i, view, viewFindNextFocus)) {
            return viewFindNextFocus;
        }
        OnInBorderKeyEventListener onInBorderKeyEventListener = this.mOnInBorderKeyEventListener;
        if (onInBorderKeyEventListener == null || !onInBorderKeyEventListener.onInBorderKeyEvent(i, view)) {
            return super.focusSearch(view, i);
        }
        return null;
    }

    private boolean hasInBorder(int i, View view, View view2) {
        if (i == 17) {
            if (view2 != null) {
                return false;
            }
            return !canScrollHorizontally(-1);
        }
        if (i == 33) {
            if (view2 != null) {
                return false;
            }
            return !canScrollVertically(-1);
        }
        if (i == 66) {
            boolean zCanScrollHorizontally = canScrollHorizontally(1);
            if (view2 == null || zCanScrollHorizontally) {
                return !zCanScrollHorizontally;
            }
            if (view == null) {
                return false;
            }
            int iAbs = Math.abs(((getWidth() - view.getRight()) - ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin) - getPaddingRight());
            Loger.i("zsq offset=" + iAbs);
            return iAbs <= 2;
        }
        if (i != 130) {
            return false;
        }
        boolean zCanScrollVertically = canScrollVertically(1);
        if (view2 == null || zCanScrollVertically) {
            return !zCanScrollVertically;
        }
        if (view == null) {
            return false;
        }
        int iAbs2 = Math.abs(((getHeight() - view.getBottom()) - ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin) - getPaddingBottom());
        Loger.i("zsq offset=" + iAbs2);
        return iAbs2 <= 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onChildAttachedToWindow(View view) {
        if (view.isClickable() && !ViewCompat.hasOnClickListeners(view)) {
            view.setOnClickListener(this);
        }
        if (view.isFocusable() && view.getOnFocusChangeListener() == null) {
            view.setOnFocusChangeListener(this);
        }
    }

    public void setItemActivated(int i) {
        int i2 = this.mSelectedPosition;
        if (i != i2) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForLayoutPosition = findViewHolderForLayoutPosition(i2);
            if (viewHolderFindViewHolderForLayoutPosition != null && viewHolderFindViewHolderForLayoutPosition.itemView.isActivated()) {
                viewHolderFindViewHolderForLayoutPosition.itemView.setActivated(false);
            }
            this.mSelectedPosition = i;
        }
        RecyclerView.ViewHolder viewHolderFindViewHolderForLayoutPosition2 = findViewHolderForLayoutPosition(i);
        if (viewHolderFindViewHolderForLayoutPosition2 == null || viewHolderFindViewHolderForLayoutPosition2.itemView.isActivated()) {
            return;
        }
        viewHolderFindViewHolderForLayoutPosition2.itemView.setActivated(true);
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.mOnItemListener = onItemListener;
    }

    public void setOnInBorderKeyEventListener(OnInBorderKeyEventListener onInBorderKeyEventListener) {
        this.mOnInBorderKeyEventListener = onInBorderKeyEventListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected Parcelable onSaveInstanceState() {
        RecyclerView.SavedState savedState = (RecyclerView.SavedState) super.onSaveInstanceState();
        ISavedState iSavedState = new ISavedState(savedState != null ? savedState.getSuperState() : null);
        iSavedState.mISuperState = savedState;
        iSavedState.mSelectedPosition = this.mSelectedPosition;
        iSavedState.mVerticalSpacingWithMargins = this.mVerticalSpacingWithMargins;
        iSavedState.mHorizontalSpacingWithMargins = this.mHorizontalSpacingWithMargins;
        iSavedState.mOldVerticalSpacingWithMargins = this.mOldVerticalSpacingWithMargins;
        iSavedState.mOldHorizontalSpacingWithMargins = this.mOldHorizontalSpacingWithMargins;
        iSavedState.mSelectedItemOffsetStart = this.mSelectedItemOffsetStart;
        iSavedState.mSelectedItemOffsetEnd = this.mSelectedItemOffsetEnd;
        iSavedState.mSelectedItemCentered = this.mSelectedItemCentered;
        iSavedState.mIsMenu = this.mIsMenu;
        iSavedState.mHasMoreData = this.mHasMoreData;
        iSavedState.mIsSelectFirstVisiblePosition = this.mIsMemoryFocus;
        return iSavedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null) {
            if (parcelable instanceof ISavedState) {
                ISavedState iSavedState = (ISavedState) parcelable;
                this.mSelectedPosition = iSavedState.mSelectedPosition;
                this.mVerticalSpacingWithMargins = iSavedState.mVerticalSpacingWithMargins;
                this.mHorizontalSpacingWithMargins = iSavedState.mHorizontalSpacingWithMargins;
                this.mOldVerticalSpacingWithMargins = iSavedState.mOldVerticalSpacingWithMargins;
                this.mOldHorizontalSpacingWithMargins = iSavedState.mOldHorizontalSpacingWithMargins;
                this.mSelectedItemOffsetStart = iSavedState.mSelectedItemOffsetStart;
                this.mSelectedItemOffsetEnd = iSavedState.mSelectedItemOffsetEnd;
                this.mSelectedItemCentered = iSavedState.mSelectedItemCentered;
                this.mIsMenu = iSavedState.mIsMenu;
                this.mHasMoreData = iSavedState.mHasMoreData;
                this.mIsMemoryFocus = iSavedState.mIsSelectFirstVisiblePosition;
                try {
                    super.onRestoreInstanceState(iSavedState.mISuperState);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            super.onRestoreInstanceState(parcelable);
        }
    }

    protected static class ISavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<ISavedState> CREATOR = new Parcelable.Creator<ISavedState>() { // from class: com.owen.tvrecyclerview.widget.TvRecyclerView.ISavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ISavedState createFromParcel(Parcel parcel) {
                return new ISavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ISavedState[] newArray(int i) {
                return new ISavedState[i];
            }
        };
        private boolean mHasMoreData;
        private int mHorizontalSpacingWithMargins;
        private Parcelable mISuperState;
        private boolean mIsMenu;
        private boolean mIsSelectFirstVisiblePosition;
        private int mOldHorizontalSpacingWithMargins;
        private int mOldVerticalSpacingWithMargins;
        private boolean mSelectedItemCentered;
        private int mSelectedItemOffsetEnd;
        private int mSelectedItemOffsetStart;
        private int mSelectedPosition;
        private int mVerticalSpacingWithMargins;

        protected ISavedState(Parcelable parcelable) {
            super(parcelable);
        }

        protected ISavedState(Parcel parcel) {
            super(parcel);
            this.mISuperState = parcel.readParcelable(RecyclerView.class.getClassLoader());
            this.mSelectedPosition = parcel.readInt();
            this.mVerticalSpacingWithMargins = parcel.readInt();
            this.mHorizontalSpacingWithMargins = parcel.readInt();
            this.mOldVerticalSpacingWithMargins = parcel.readInt();
            this.mOldHorizontalSpacingWithMargins = parcel.readInt();
            this.mSelectedItemOffsetStart = parcel.readInt();
            this.mSelectedItemOffsetEnd = parcel.readInt();
            boolean[] zArr = new boolean[4];
            parcel.readBooleanArray(zArr);
            this.mSelectedItemCentered = zArr[0];
            this.mIsMenu = zArr[1];
            this.mHasMoreData = zArr[2];
            this.mIsSelectFirstVisiblePosition = zArr[3];
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.mISuperState, 0);
            parcel.writeInt(this.mSelectedPosition);
            parcel.writeInt(this.mVerticalSpacingWithMargins);
            parcel.writeInt(this.mHorizontalSpacingWithMargins);
            parcel.writeInt(this.mOldVerticalSpacingWithMargins);
            parcel.writeInt(this.mOldHorizontalSpacingWithMargins);
            parcel.writeInt(this.mSelectedItemOffsetStart);
            parcel.writeInt(this.mSelectedItemOffsetEnd);
            parcel.writeBooleanArray(new boolean[]{this.mSelectedItemCentered, this.mIsMenu, this.mHasMoreData, this.mIsSelectFirstVisiblePosition});
        }
    }

    private class IRecyclerViewDataObserver extends RecyclerView.AdapterDataObserver {
        private IRecyclerViewDataObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            Loger.i("RecyclerView Data Changed!!!");
            TvRecyclerView.this.mShouldReverseLayout = true;
        }
    }

    private class TvSmoothScroller extends LinearSmoothScroller {
        private boolean mIsSmooth;
        private int mOffset;
        private boolean mRequestFocus;

        public TvSmoothScroller(Context context, boolean z, boolean z2, int i) {
            super(context);
            this.mRequestFocus = z;
            this.mIsSmooth = z2;
            this.mOffset = i;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected int calculateTimeForScrolling(int i) {
            return this.mIsSmooth ? super.calculateTimeForScrolling(i) : (int) Math.ceil(Math.abs(i) * (4.0f / TvRecyclerView.this.getContext().getResources().getDisplayMetrics().densityDpi));
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int freeHeight;
            int iHeight;
            if (TvRecyclerView.this.mSelectedItemCentered && getLayoutManager() != null) {
                TvRecyclerView tvRecyclerView = TvRecyclerView.this;
                tvRecyclerView.getDecoratedBoundsWithMargins(view, tvRecyclerView.mTempRect);
                if (getLayoutManager().canScrollHorizontally()) {
                    freeHeight = TvRecyclerView.this.getFreeWidth();
                    iHeight = TvRecyclerView.this.mTempRect.width();
                } else {
                    freeHeight = TvRecyclerView.this.getFreeHeight();
                    iHeight = TvRecyclerView.this.mTempRect.height();
                }
                this.mOffset = (freeHeight - iHeight) / 2;
            }
            super.onTargetFound(view, state, action);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            Loger.i("zsq viewStart=" + i + " boxStart=" + i3 + " mOffset=" + this.mOffset);
            int i6 = (i3 - i) + this.mOffset;
            StringBuilder sb = new StringBuilder("zsq dt=");
            sb.append(i6);
            Loger.i(sb.toString());
            return i6;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void onStop() {
            Loger.i("zzssqq SmoothScroller onStop");
            if (this.mRequestFocus) {
                final int targetPosition = getTargetPosition();
                TvRecyclerView.this.postDelayed(new Runnable() { // from class: com.owen.tvrecyclerview.widget.TvRecyclerView.TvSmoothScroller.1
                    @Override // java.lang.Runnable
                    public void run() {
                        View viewFindViewByPosition = TvRecyclerView.this.getLayoutManager().findViewByPosition(targetPosition);
                        if (viewFindViewByPosition != null) {
                            Loger.i("zzssqq position=" + targetPosition);
                            if (!TvRecyclerView.this.hasFocus()) {
                                TvRecyclerView.this.onFocusChanged(true, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, null);
                            }
                            viewFindViewByPosition.requestFocus();
                            return;
                        }
                        Loger.i("zzssqq itemView is null position=" + targetPosition);
                    }
                }, this.mIsSmooth ? 400L : 100L);
            }
            super.onStop();
        }
    }
}
