package com.alibaba.android.vlayout.layout;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.OrientationHelperEx;
import com.alibaba.android.vlayout.Range;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class StaggeredGridLayoutHelper extends BaseLayoutHelper {
    static final int INVALID_LINE = Integer.MIN_VALUE;
    private static final int INVALID_SPAN_ID = Integer.MIN_VALUE;
    private static final String LOOKUP_BUNDLE_KEY = "StaggeredGridLayoutHelper_LazySpanLookup";
    private static final String TAG = "Staggered";
    private int anchorPosition;
    private final Runnable checkForGapsRunnable;
    private int mColLength;
    private int mEachGap;
    private int mHGap;
    private int mLastGap;
    private WeakReference<VirtualLayoutManager> mLayoutManager;
    private boolean mLayoutWithAnchor;
    private LazySpanLookup mLazySpanLookup;
    private int mNumLanes;
    private BitSet mRemainingSpans;
    private Span[] mSpans;
    private int mVGap;
    private List<View> prelayoutViewList;

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onItemsChanged(LayoutManagerHelper layoutManagerHelper) {
    }

    public StaggeredGridLayoutHelper() {
        this(1, 0);
    }

    public StaggeredGridLayoutHelper(int i) {
        this(i, 0);
    }

    public StaggeredGridLayoutHelper(int i, int i2) {
        this.mNumLanes = 0;
        this.mHGap = 0;
        this.mVGap = 0;
        this.mColLength = 0;
        this.mEachGap = 0;
        this.mLastGap = 0;
        this.mRemainingSpans = null;
        this.mLazySpanLookup = new LazySpanLookup();
        this.prelayoutViewList = new ArrayList();
        this.mLayoutManager = null;
        this.checkForGapsRunnable = new Runnable() { // from class: com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper.1
            @Override // java.lang.Runnable
            public void run() {
                StaggeredGridLayoutHelper.this.checkForGaps();
            }
        };
        setLane(i);
        setGap(i2);
    }

    public void setGap(int i) {
        setHGap(i);
        setVGap(i);
    }

    public void setHGap(int i) {
        this.mHGap = i;
    }

    public int getHGap() {
        return this.mHGap;
    }

    public void setVGap(int i) {
        this.mVGap = i;
    }

    public int getVGap() {
        return this.mVGap;
    }

    public void setLane(int i) {
        this.mNumLanes = i;
        ensureLanes();
    }

    public int getLane() {
        return this.mNumLanes;
    }

    public int getColLength() {
        return this.mColLength;
    }

    private void ensureLanes() {
        Span[] spanArr = this.mSpans;
        if (spanArr == null || spanArr.length != this.mNumLanes || this.mRemainingSpans == null) {
            this.mRemainingSpans = new BitSet(this.mNumLanes);
            this.mSpans = new Span[this.mNumLanes];
            for (int i = 0; i < this.mNumLanes; i++) {
                this.mSpans[i] = new Span(i);
            }
        }
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void beforeLayout(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutManagerHelper layoutManagerHelper) {
        int contentHeight;
        int verticalPadding;
        super.beforeLayout(recycler, state, layoutManagerHelper);
        if (layoutManagerHelper.getOrientation() == 1) {
            contentHeight = ((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingLeft()) - layoutManagerHelper.getPaddingRight()) - getHorizontalMargin();
            verticalPadding = getHorizontalPadding();
        } else {
            contentHeight = ((layoutManagerHelper.getContentHeight() - layoutManagerHelper.getPaddingTop()) - layoutManagerHelper.getPaddingBottom()) - getVerticalMargin();
            verticalPadding = getVerticalPadding();
        }
        int i = contentHeight - verticalPadding;
        int i2 = this.mHGap;
        int i3 = this.mNumLanes;
        int i4 = (int) (((double) ((i - (i2 * (i3 - 1))) / i3)) + 0.5d);
        this.mColLength = i4;
        int i5 = i - (i4 * i3);
        if (i3 <= 1) {
            this.mLastGap = 0;
            this.mEachGap = 0;
        } else if (i3 == 2) {
            this.mEachGap = i5;
            this.mLastGap = i5;
        } else {
            int i6 = layoutManagerHelper.getOrientation() == 1 ? this.mHGap : this.mVGap;
            this.mLastGap = i6;
            this.mEachGap = i6;
        }
        WeakReference<VirtualLayoutManager> weakReference = this.mLayoutManager;
        if ((weakReference == null || weakReference.get() == null || this.mLayoutManager.get() != layoutManagerHelper) && (layoutManagerHelper instanceof VirtualLayoutManager)) {
            this.mLayoutManager = new WeakReference<>((VirtualLayoutManager) layoutManagerHelper);
        }
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void afterLayout(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, int i3, LayoutManagerHelper layoutManagerHelper) {
        super.afterLayout(recycler, state, i, i2, i3, layoutManagerHelper);
        this.mLayoutWithAnchor = false;
        if (i > ((Integer) getRange().getUpper()).intValue() || i2 < ((Integer) getRange().getLower()).intValue() || state.isPreLayout() || layoutManagerHelper.getChildCount() <= 0) {
            return;
        }
        ViewCompat.postOnAnimation(layoutManagerHelper.getChildAt(0), this.checkForGapsRunnable);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void layoutViews(RecyclerView.Recycler recycler, RecyclerView.State state, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutChunkResult layoutChunkResult, LayoutManagerHelper layoutManagerHelper) {
        int offset;
        int extra;
        VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper2;
        int i;
        int i2;
        int i3;
        int i4;
        Span nextSpan;
        boolean z;
        int startLine;
        int i5;
        int decoratedMeasurement;
        int decoratedMeasurement2;
        int i6;
        int i7;
        int i8;
        View view;
        Span span;
        int i9;
        OrientationHelperEx orientationHelperEx;
        int iComputeStartSpace;
        if (isOutOfRange(layoutStateWrapper.getCurrentPosition())) {
            return;
        }
        ensureLanes();
        boolean z2 = layoutManagerHelper.getOrientation() == 1;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        OrientationHelperEx secondaryOrientationHelper = layoutManagerHelper.getSecondaryOrientationHelper();
        boolean zIsEnableMarginOverLap = layoutManagerHelper.isEnableMarginOverLap();
        this.mRemainingSpans.set(0, this.mNumLanes, true);
        if (layoutStateWrapper.getLayoutDirection() == 1) {
            offset = layoutStateWrapper.getOffset() + layoutStateWrapper.getAvailable();
            extra = layoutStateWrapper.getExtra() + offset + mainOrientationHelper.getEndPadding();
        } else {
            offset = layoutStateWrapper.getOffset() - layoutStateWrapper.getAvailable();
            extra = (offset - layoutStateWrapper.getExtra()) - mainOrientationHelper.getStartAfterPadding();
        }
        int i10 = offset;
        int i11 = extra;
        updateAllRemainingSpans(layoutStateWrapper.getLayoutDirection(), i11, mainOrientationHelper);
        int offset2 = layoutStateWrapper.getOffset();
        this.prelayoutViewList.clear();
        while (layoutStateWrapper.hasMore(state) && !this.mRemainingSpans.isEmpty() && !isOutOfRange(layoutStateWrapper.getCurrentPosition())) {
            int currentPosition = layoutStateWrapper.getCurrentPosition();
            View next = layoutStateWrapper.next(recycler);
            if (next == null) {
                break;
            }
            VirtualLayoutManager.LayoutParams layoutParams = (VirtualLayoutManager.LayoutParams) next.getLayoutParams();
            int viewPosition = layoutParams.getViewPosition();
            int i12 = i11;
            int span2 = this.mLazySpanLookup.getSpan(viewPosition);
            if (span2 == Integer.MIN_VALUE) {
                nextSpan = getNextSpan(offset2, layoutStateWrapper, layoutManagerHelper);
                this.mLazySpanLookup.setSpan(viewPosition, nextSpan);
            } else {
                nextSpan = this.mSpans[span2];
            }
            Span span3 = nextSpan;
            boolean z3 = viewPosition - ((Integer) getRange().getLower()).intValue() < this.mNumLanes;
            boolean z4 = ((Integer) getRange().getUpper()).intValue() - viewPosition < this.mNumLanes;
            if (layoutStateWrapper.isPreLayout()) {
                this.prelayoutViewList.add(next);
            }
            layoutManagerHelper.addChildView(layoutStateWrapper, next);
            if (z2) {
                int childMeasureSpec = layoutManagerHelper.getChildMeasureSpec(this.mColLength, layoutParams.width, false);
                layoutManagerHelper.measureChildWithMargins(next, childMeasureSpec, layoutManagerHelper.getChildMeasureSpec(mainOrientationHelper.getTotalSpace(), Float.isNaN(layoutParams.mAspectRatio) ? layoutParams.height : (int) ((View.MeasureSpec.getSize(childMeasureSpec) / layoutParams.mAspectRatio) + 0.5f), true));
                z = true;
            } else {
                int childMeasureSpec2 = layoutManagerHelper.getChildMeasureSpec(this.mColLength, layoutParams.height, false);
                int totalSpace = mainOrientationHelper.getTotalSpace();
                int size = Float.isNaN(layoutParams.mAspectRatio) ? layoutParams.width : (int) ((View.MeasureSpec.getSize(childMeasureSpec2) * layoutParams.mAspectRatio) + 0.5f);
                z = true;
                layoutManagerHelper.measureChildWithMargins(next, layoutManagerHelper.getChildMeasureSpec(totalSpace, size, true), childMeasureSpec2);
            }
            if (layoutStateWrapper.getLayoutDirection() == z) {
                decoratedMeasurement2 = span3.getEndLine(offset2, mainOrientationHelper);
                if (z3) {
                    iComputeStartSpace = computeStartSpace(layoutManagerHelper, z2, z, zIsEnableMarginOverLap);
                } else if (this.mLayoutWithAnchor) {
                    if (Math.abs(currentPosition - this.anchorPosition) >= this.mNumLanes) {
                        iComputeStartSpace = z2 ? this.mVGap : this.mHGap;
                    }
                    decoratedMeasurement = mainOrientationHelper.getDecoratedMeasurement(next) + decoratedMeasurement2;
                } else {
                    iComputeStartSpace = z2 ? this.mVGap : this.mHGap;
                }
                decoratedMeasurement2 += iComputeStartSpace;
                decoratedMeasurement = mainOrientationHelper.getDecoratedMeasurement(next) + decoratedMeasurement2;
            } else {
                if (z4) {
                    startLine = span3.getStartLine(offset2, mainOrientationHelper);
                    i5 = (z2 ? this.mMarginBottom : this.mMarginRight) + this.mPaddingRight;
                } else {
                    startLine = span3.getStartLine(offset2, mainOrientationHelper);
                    i5 = z2 ? this.mVGap : this.mHGap;
                }
                int i13 = startLine - i5;
                decoratedMeasurement = i13;
                decoratedMeasurement2 = i13 - mainOrientationHelper.getDecoratedMeasurement(next);
            }
            if (layoutStateWrapper.getLayoutDirection() == 1) {
                span3.appendToSpan(next, mainOrientationHelper);
            } else {
                span3.prependToSpan(next, mainOrientationHelper);
            }
            if (span3.mIndex == this.mNumLanes - 1) {
                int i14 = span3.mIndex;
                int i15 = this.mColLength;
                int i16 = this.mEachGap;
                i6 = ((i14 * (i15 + i16)) - i16) + this.mLastGap;
            } else {
                i6 = span3.mIndex * (this.mColLength + this.mEachGap);
            }
            int startAfterPadding = i6 + secondaryOrientationHelper.getStartAfterPadding();
            if (z2) {
                i7 = this.mMarginLeft;
                i8 = this.mPaddingLeft;
            } else {
                i7 = this.mMarginTop;
                i8 = this.mPaddingTop;
            }
            int i17 = startAfterPadding + i7 + i8;
            int decoratedMeasurementInOther = i17 + mainOrientationHelper.getDecoratedMeasurementInOther(next);
            if (z2) {
                view = next;
                layoutChildWithMargin(next, i17, decoratedMeasurement2, decoratedMeasurementInOther, decoratedMeasurement, layoutManagerHelper);
                i9 = i12;
                span = span3;
                orientationHelperEx = mainOrientationHelper;
            } else {
                view = next;
                int i18 = decoratedMeasurement2;
                span = span3;
                int i19 = decoratedMeasurement;
                i9 = i12;
                orientationHelperEx = mainOrientationHelper;
                layoutChildWithMargin(view, i18, i17, i19, decoratedMeasurementInOther, layoutManagerHelper);
            }
            updateRemainingSpans(span, layoutStateWrapper.getLayoutDirection(), i9, orientationHelperEx);
            recycle(recycler, layoutStateWrapper, span, i10, layoutManagerHelper);
            handleStateOnResult(layoutChunkResult, view);
            i11 = i9;
            mainOrientationHelper = orientationHelperEx;
            zIsEnableMarginOverLap = zIsEnableMarginOverLap;
            offset2 = offset2;
        }
        OrientationHelperEx orientationHelperEx2 = mainOrientationHelper;
        if (isOutOfRange(layoutStateWrapper.getCurrentPosition()) && this.mSpans != null) {
            if (layoutStateWrapper.getLayoutDirection() == -1) {
                int length = this.mSpans.length;
                for (int i20 = 0; i20 < length; i20++) {
                    Span span4 = this.mSpans[i20];
                    if (span4.mCachedStart != Integer.MIN_VALUE) {
                        span4.mLastEdgeStart = span4.mCachedStart;
                    }
                }
            } else {
                int length2 = this.mSpans.length;
                for (int i21 = 0; i21 < length2; i21++) {
                    Span span5 = this.mSpans[i21];
                    if (span5.mCachedEnd != Integer.MIN_VALUE) {
                        span5.mLastEdgeEnd = span5.mCachedEnd;
                    }
                }
            }
        }
        if (layoutStateWrapper.getLayoutDirection() == -1) {
            if (isOutOfRange(layoutStateWrapper.getCurrentPosition())) {
                layoutStateWrapper2 = layoutStateWrapper;
            } else {
                layoutStateWrapper2 = layoutStateWrapper;
                if (layoutStateWrapper2.hasMore(state)) {
                    layoutChunkResult.mConsumed = layoutStateWrapper.getOffset() - getMaxStart(orientationHelperEx2.getStartAfterPadding(), orientationHelperEx2);
                }
            }
            int offset3 = layoutStateWrapper.getOffset() - getMinStart(orientationHelperEx2.getEndAfterPadding(), orientationHelperEx2);
            if (z2) {
                i3 = this.mMarginTop;
                i4 = this.mPaddingTop;
            } else {
                i3 = this.mMarginLeft;
                i4 = this.mPaddingLeft;
            }
            layoutChunkResult.mConsumed = offset3 + i3 + i4;
        } else {
            layoutStateWrapper2 = layoutStateWrapper;
            if (!isOutOfRange(layoutStateWrapper.getCurrentPosition()) && layoutStateWrapper2.hasMore(state)) {
                layoutChunkResult.mConsumed = getMinEnd(orientationHelperEx2.getEndAfterPadding(), orientationHelperEx2) - layoutStateWrapper.getOffset();
            } else {
                int maxEnd = getMaxEnd(orientationHelperEx2.getEndAfterPadding(), orientationHelperEx2) - layoutStateWrapper.getOffset();
                if (z2) {
                    i = this.mMarginBottom;
                    i2 = this.mPaddingBottom;
                } else {
                    i = this.mMarginRight;
                    i2 = this.mPaddingRight;
                }
                layoutChunkResult.mConsumed = maxEnd + i + i2;
            }
        }
        recycleForPreLayout(recycler, layoutStateWrapper2, layoutManagerHelper);
    }

    private void recycleForPreLayout(RecyclerView.Recycler recycler, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutManagerHelper layoutManagerHelper) {
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        for (int size = this.prelayoutViewList.size() - 1; size >= 0; size--) {
            View view = this.prelayoutViewList.get(size);
            if (view != null && mainOrientationHelper.getDecoratedStart(view) > mainOrientationHelper.getEndAfterPadding()) {
                Span spanFindSpan = findSpan(((RecyclerView.LayoutParams) view.getLayoutParams()).getViewPosition(), view, false);
                if (spanFindSpan != null) {
                    spanFindSpan.popEnd(mainOrientationHelper);
                }
                layoutManagerHelper.removeChildView(view);
                recycler.recycleView(view);
            } else {
                Span spanFindSpan2 = findSpan(((RecyclerView.LayoutParams) view.getLayoutParams()).getViewPosition(), view, false);
                if (spanFindSpan2 != null) {
                    spanFindSpan2.popEnd(mainOrientationHelper);
                }
                layoutManagerHelper.removeChildView(view);
                recycler.recycleView(view);
                return;
            }
        }
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onScrollStateChanged(int i, int i2, int i3, LayoutManagerHelper layoutManagerHelper) {
        if (i2 > ((Integer) getRange().getUpper()).intValue() || i3 < ((Integer) getRange().getLower()).intValue() || i != 0) {
            return;
        }
        checkForGaps();
    }

    @Override // com.alibaba.android.vlayout.layout.MarginLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public int computeAlignOffset(int i, boolean z, boolean z2, LayoutManagerHelper layoutManagerHelper) {
        boolean z3 = layoutManagerHelper.getOrientation() == 1;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        View viewFindViewByPosition = layoutManagerHelper.findViewByPosition(((Integer) getRange().getLower()).intValue() + i);
        if (viewFindViewByPosition == null) {
            return 0;
        }
        ensureLanes();
        if (z3) {
            if (z) {
                if (i == getItemCount() - 1) {
                    return this.mMarginBottom + this.mPaddingBottom + (getMaxEnd(mainOrientationHelper.getDecoratedEnd(viewFindViewByPosition), mainOrientationHelper) - mainOrientationHelper.getDecoratedEnd(viewFindViewByPosition));
                }
                if (!z2) {
                    return getMinEnd(mainOrientationHelper.getDecoratedStart(viewFindViewByPosition), mainOrientationHelper) - mainOrientationHelper.getDecoratedEnd(viewFindViewByPosition);
                }
            } else {
                if (i == 0) {
                    return ((-this.mMarginTop) - this.mPaddingTop) - (mainOrientationHelper.getDecoratedStart(viewFindViewByPosition) - getMinStart(mainOrientationHelper.getDecoratedStart(viewFindViewByPosition), mainOrientationHelper));
                }
                if (!z2) {
                    return getMaxStart(mainOrientationHelper.getDecoratedEnd(viewFindViewByPosition), mainOrientationHelper) - mainOrientationHelper.getDecoratedStart(viewFindViewByPosition);
                }
            }
        }
        return 0;
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void onClear(LayoutManagerHelper layoutManagerHelper) {
        super.onClear(layoutManagerHelper);
        this.mLazySpanLookup.clear();
        this.mSpans = null;
        this.mLayoutManager = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkForGaps() {
        VirtualLayoutManager virtualLayoutManager;
        int iIntValue;
        int position;
        int decoratedStart;
        WeakReference<VirtualLayoutManager> weakReference = this.mLayoutManager;
        if (weakReference == null || (virtualLayoutManager = weakReference.get()) == null || virtualLayoutManager.getChildCount() == 0) {
            return;
        }
        Range<Integer> range = getRange();
        if (virtualLayoutManager.getReverseLayout()) {
            virtualLayoutManager.findLastVisibleItemPosition();
            virtualLayoutManager.findFirstVisibleItemPosition();
            iIntValue = ((Integer) range.getUpper()).intValue() - 1;
        } else {
            virtualLayoutManager.findFirstVisibleItemPosition();
            virtualLayoutManager.findLastCompletelyVisibleItemPosition();
            iIntValue = ((Integer) range.getLower()).intValue();
        }
        OrientationHelperEx mainOrientationHelper = virtualLayoutManager.getMainOrientationHelper();
        int childCount = virtualLayoutManager.getChildCount();
        if (virtualLayoutManager.getReverseLayout()) {
            int i = childCount - 1;
            int i2 = i;
            while (true) {
                if (i2 < 0) {
                    decoratedStart = Integer.MIN_VALUE;
                    position = Integer.MIN_VALUE;
                } else {
                    View childAt = virtualLayoutManager.getChildAt(i2);
                    position = virtualLayoutManager.getPosition(childAt);
                    if (position != iIntValue) {
                        i2--;
                    } else if (i2 == i) {
                        decoratedStart = mainOrientationHelper.getDecoratedEnd(childAt);
                    } else {
                        View childAt2 = virtualLayoutManager.getChildAt(i2 + 1);
                        if (virtualLayoutManager.getPosition(childAt2) == position - 1) {
                            decoratedStart = (mainOrientationHelper.getDecoratedStart(childAt2) - virtualLayoutManager.obtainExtraMargin(childAt2, false)) + virtualLayoutManager.obtainExtraMargin(childAt, true);
                        } else {
                            decoratedStart = mainOrientationHelper.getDecoratedEnd(childAt);
                        }
                    }
                }
            }
        } else {
            int i3 = 0;
            while (true) {
                if (i3 >= childCount) {
                    decoratedStart = Integer.MIN_VALUE;
                    position = Integer.MIN_VALUE;
                } else {
                    View childAt3 = virtualLayoutManager.getChildAt(i3);
                    position = virtualLayoutManager.getPosition(childAt3);
                    if (position != iIntValue) {
                        i3++;
                    } else if (i3 == 0) {
                        decoratedStart = mainOrientationHelper.getDecoratedStart(childAt3);
                    } else {
                        View childAt4 = virtualLayoutManager.getChildAt(i3 - 1);
                        int decoratedEnd = (mainOrientationHelper.getDecoratedEnd(childAt4) + virtualLayoutManager.obtainExtraMargin(childAt4, true, false)) - virtualLayoutManager.obtainExtraMargin(childAt3, false, false);
                        if (decoratedEnd == mainOrientationHelper.getDecoratedStart(childAt3)) {
                            decoratedStart = decoratedEnd;
                            position = Integer.MIN_VALUE;
                        } else {
                            int position2 = virtualLayoutManager.getPosition(childAt4);
                            int i4 = iIntValue - 1;
                            if (position2 != i4) {
                                LayoutHelper layoutHelperFindLayoutHelperByPosition = virtualLayoutManager.findLayoutHelperByPosition(i4);
                                if (layoutHelperFindLayoutHelperByPosition != null && (layoutHelperFindLayoutHelperByPosition instanceof StickyLayoutHelper) && layoutHelperFindLayoutHelperByPosition.getFixedView() != null) {
                                    decoratedEnd += layoutHelperFindLayoutHelperByPosition.getFixedView().getMeasuredHeight();
                                }
                            } else {
                                virtualLayoutManager.findLayoutHelperByPosition(position2).getRange();
                            }
                            decoratedStart = decoratedEnd;
                        }
                    }
                }
            }
        }
        if (position == Integer.MIN_VALUE || hasGapsToFix(virtualLayoutManager, position, decoratedStart) == null) {
            return;
        }
        Span[] spanArr = this.mSpans;
        if (spanArr != null) {
            int length = spanArr.length;
            for (int i5 = 0; i5 < length; i5++) {
                this.mSpans[i5].setLine(decoratedStart);
            }
        }
        virtualLayoutManager.requestSimpleAnimationsInNextLayout();
        virtualLayoutManager.requestLayout();
    }

    private View hasGapsToFix(VirtualLayoutManager virtualLayoutManager, int i, int i2) {
        if (virtualLayoutManager.findViewByPosition(i) == null) {
            return null;
        }
        new BitSet(this.mNumLanes).set(0, this.mNumLanes, true);
        Span[] spanArr = this.mSpans;
        if (spanArr != null) {
            int length = spanArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                Span span = this.mSpans[i3];
                if (span.mViews.size() != 0 && checkSpanForGap(span, virtualLayoutManager, i2)) {
                    return (View) (virtualLayoutManager.getReverseLayout() ? span.mViews.get(span.mViews.size() - 1) : span.mViews.get(0));
                }
            }
        }
        return null;
    }

    private boolean checkSpanForGap(Span span, VirtualLayoutManager virtualLayoutManager, int i) {
        OrientationHelperEx mainOrientationHelper = virtualLayoutManager.getMainOrientationHelper();
        if (virtualLayoutManager.getReverseLayout()) {
            return span.getEndLine(mainOrientationHelper) < i;
        }
        return span.getStartLine(mainOrientationHelper) > i;
    }

    private void recycle(RecyclerView.Recycler recycler, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, Span span, int i, LayoutManagerHelper layoutManagerHelper) {
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        if (layoutStateWrapper.getLayoutDirection() == -1) {
            recycleFromEnd(recycler, Math.max(i, getMaxStart(span.getStartLine(mainOrientationHelper), mainOrientationHelper)) + (mainOrientationHelper.getEnd() - mainOrientationHelper.getStartAfterPadding()), layoutManagerHelper);
        } else {
            recycleFromStart(recycler, Math.min(i, getMinEnd(span.getEndLine(mainOrientationHelper), mainOrientationHelper)) - (mainOrientationHelper.getEnd() - mainOrientationHelper.getStartAfterPadding()), layoutManagerHelper);
        }
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i, LayoutManagerHelper layoutManagerHelper) {
        View childAt;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        boolean z = true;
        while (layoutManagerHelper.getChildCount() > 0 && z && (childAt = layoutManagerHelper.getChildAt(0)) != null && mainOrientationHelper.getDecoratedEnd(childAt) < i) {
            Span spanFindSpan = findSpan(((RecyclerView.LayoutParams) childAt.getLayoutParams()).getViewPosition(), childAt, true);
            if (spanFindSpan != null) {
                spanFindSpan.popStart(mainOrientationHelper);
                layoutManagerHelper.removeChildView(childAt);
                recycler.recycleView(childAt);
            } else {
                z = false;
            }
        }
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i, LayoutManagerHelper layoutManagerHelper) {
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        for (int childCount = layoutManagerHelper.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = layoutManagerHelper.getChildAt(childCount);
            if (childAt == null || mainOrientationHelper.getDecoratedStart(childAt) <= i) {
                return;
            }
            Span spanFindSpan = findSpan(((RecyclerView.LayoutParams) childAt.getLayoutParams()).getViewPosition(), childAt, false);
            if (spanFindSpan != null) {
                spanFindSpan.popEnd(mainOrientationHelper);
                layoutManagerHelper.removeChildView(childAt);
                recycler.recycleView(childAt);
            }
        }
    }

    private Span findSpan(int i, View view, boolean z) {
        int span = this.mLazySpanLookup.getSpan(i);
        Span[] spanArr = this.mSpans;
        if (spanArr == null) {
            return null;
        }
        if (span >= 0 && span < spanArr.length) {
            Span span2 = spanArr[span];
            if (z && span2.findStart(view)) {
                return span2;
            }
            if (!z && span2.findEnd(view)) {
                return span2;
            }
        }
        int i2 = 0;
        while (true) {
            Span[] spanArr2 = this.mSpans;
            if (i2 >= spanArr2.length) {
                return null;
            }
            if (i2 != span) {
                Span span3 = spanArr2[i2];
                if (z && span3.findStart(view)) {
                    return span3;
                }
                if (!z && span3.findEnd(view)) {
                    return span3;
                }
            }
            i2++;
        }
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public boolean isRecyclable(int i, int i2, int i3, LayoutManagerHelper layoutManagerHelper, boolean z) {
        View viewFindViewByPosition;
        boolean zIsRecyclable = super.isRecyclable(i, i2, i3, layoutManagerHelper, z);
        if (zIsRecyclable && (viewFindViewByPosition = layoutManagerHelper.findViewByPosition(i)) != null) {
            OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
            int viewPosition = ((RecyclerView.LayoutParams) viewFindViewByPosition.getLayoutParams()).getViewPosition();
            if (layoutManagerHelper.getReverseLayout()) {
                if (z) {
                    Span spanFindSpan = findSpan(viewPosition, viewFindViewByPosition, true);
                    if (spanFindSpan != null) {
                        spanFindSpan.popEnd(mainOrientationHelper);
                    }
                } else {
                    Span spanFindSpan2 = findSpan(viewPosition, viewFindViewByPosition, false);
                    if (spanFindSpan2 != null) {
                        spanFindSpan2.popStart(mainOrientationHelper);
                    }
                }
            } else if (z) {
                Span spanFindSpan3 = findSpan(viewPosition, viewFindViewByPosition, true);
                if (spanFindSpan3 != null) {
                    spanFindSpan3.popStart(mainOrientationHelper);
                }
            } else {
                Span spanFindSpan4 = findSpan(viewPosition, viewFindViewByPosition, false);
                if (spanFindSpan4 != null) {
                    spanFindSpan4.popEnd(mainOrientationHelper);
                }
            }
        }
        return zIsRecyclable;
    }

    private void updateAllRemainingSpans(int i, int i2, OrientationHelperEx orientationHelperEx) {
        for (int i3 = 0; i3 < this.mNumLanes; i3++) {
            if (!this.mSpans[i3].mViews.isEmpty()) {
                updateRemainingSpans(this.mSpans[i3], i, i2, orientationHelperEx);
            }
        }
    }

    private void updateRemainingSpans(Span span, int i, int i2, OrientationHelperEx orientationHelperEx) {
        int deletedSize = span.getDeletedSize();
        if (i == -1) {
            if (span.getStartLine(orientationHelperEx) + deletedSize < i2) {
                this.mRemainingSpans.set(span.mIndex, false);
            }
        } else if (span.getEndLine(orientationHelperEx) - deletedSize > i2) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0035  */
    /* JADX WARN: Code duplicated, block: B:22:0x003b  */
    private Span getNextSpan(int i, VirtualLayoutManager.LayoutStateWrapper layoutStateWrapper, LayoutManagerHelper layoutManagerHelper) {
        int i2;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        int i3 = 0;
        int i4 = -1;
        if (layoutManagerHelper.getOrientation() == 0) {
            if ((layoutStateWrapper.getLayoutDirection() == -1) != layoutManagerHelper.getReverseLayout()) {
                i3 = this.mNumLanes - 1;
                i2 = -1;
            } else {
                i4 = this.mNumLanes;
                i2 = 1;
            }
        } else {
            if (((layoutStateWrapper.getLayoutDirection() == -1) == layoutManagerHelper.getReverseLayout()) == layoutManagerHelper.isDoLayoutRTL()) {
                i3 = this.mNumLanes - 1;
                i2 = -1;
            } else {
                i4 = this.mNumLanes;
                i2 = 1;
            }
        }
        Span span = null;
        if (layoutStateWrapper.getLayoutDirection() == 1) {
            int i5 = Integer.MAX_VALUE;
            while (i3 != i4) {
                Span span2 = this.mSpans[i3];
                int endLine = span2.getEndLine(i, mainOrientationHelper);
                if (endLine < i5) {
                    span = span2;
                    i5 = endLine;
                }
                i3 += i2;
            }
            return span;
        }
        int i6 = Integer.MIN_VALUE;
        while (i3 != i4) {
            Span span3 = this.mSpans[i3];
            int startLine = span3.getStartLine(i, mainOrientationHelper);
            if (startLine > i6) {
                span = span3;
                i6 = startLine;
            }
            i3 += i2;
        }
        return span;
    }

    private int getMaxStart(int i, OrientationHelperEx orientationHelperEx) {
        int startLine = this.mSpans[0].getStartLine(i, orientationHelperEx);
        for (int i2 = 1; i2 < this.mNumLanes; i2++) {
            int startLine2 = this.mSpans[i2].getStartLine(i, orientationHelperEx);
            if (startLine2 > startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    private int getMinStart(int i, OrientationHelperEx orientationHelperEx) {
        int startLine = this.mSpans[0].getStartLine(i, orientationHelperEx);
        for (int i2 = 1; i2 < this.mNumLanes; i2++) {
            int startLine2 = this.mSpans[i2].getStartLine(i, orientationHelperEx);
            if (startLine2 < startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    private int getMaxEnd(int i, OrientationHelperEx orientationHelperEx) {
        int endLine = this.mSpans[0].getEndLine(i, orientationHelperEx);
        for (int i2 = 1; i2 < this.mNumLanes; i2++) {
            int endLine2 = this.mSpans[i2].getEndLine(i, orientationHelperEx);
            if (endLine2 > endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private int getMinEnd(int i, OrientationHelperEx orientationHelperEx) {
        int endLine = this.mSpans[0].getEndLine(i, orientationHelperEx);
        for (int i2 = 1; i2 < this.mNumLanes; i2++) {
            int endLine2 = this.mSpans[i2].getEndLine(i, orientationHelperEx);
            if (endLine2 < endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRefreshLayout(RecyclerView.State state, VirtualLayoutManager.AnchorInfoWrapper anchorInfoWrapper, LayoutManagerHelper layoutManagerHelper) {
        Span[] spanArr;
        super.onRefreshLayout(state, anchorInfoWrapper, layoutManagerHelper);
        ensureLanes();
        if (!isOutOfRange(anchorInfoWrapper.position) || (spanArr = this.mSpans) == null) {
            return;
        }
        int length = spanArr.length;
        for (int i = 0; i < length; i++) {
            this.mSpans[i].clear();
        }
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void checkAnchorInfo(RecyclerView.State state, VirtualLayoutManager.AnchorInfoWrapper anchorInfoWrapper, LayoutManagerHelper layoutManagerHelper) {
        int i;
        super.checkAnchorInfo(state, anchorInfoWrapper, layoutManagerHelper);
        ensureLanes();
        Range<Integer> range = getRange();
        if (anchorInfoWrapper.layoutFromEnd) {
            if (anchorInfoWrapper.position < (((Integer) range.getLower()).intValue() + this.mNumLanes) - 1) {
                anchorInfoWrapper.position = Math.min((((Integer) range.getLower()).intValue() + this.mNumLanes) - 1, ((Integer) range.getUpper()).intValue());
            }
        } else if (anchorInfoWrapper.position > ((Integer) range.getUpper()).intValue() - (this.mNumLanes - 1)) {
            anchorInfoWrapper.position = Math.max(((Integer) range.getLower()).intValue(), ((Integer) range.getUpper()).intValue() - (this.mNumLanes - 1));
        }
        View viewFindViewByPosition = layoutManagerHelper.findViewByPosition(anchorInfoWrapper.position);
        int i2 = layoutManagerHelper.getOrientation() == 1 ? this.mVGap : this.mHGap;
        OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
        int i3 = 0;
        if (viewFindViewByPosition == null) {
            Span[] spanArr = this.mSpans;
            if (spanArr != null) {
                int length = spanArr.length;
                while (i3 < length) {
                    Span span = this.mSpans[i3];
                    span.clear();
                    span.setLine(anchorInfoWrapper.coordinate);
                    i3++;
                }
                return;
            }
            return;
        }
        int i4 = Integer.MIN_VALUE;
        int iMin = anchorInfoWrapper.layoutFromEnd ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Span[] spanArr2 = this.mSpans;
        if (spanArr2 != null) {
            int length2 = spanArr2.length;
            for (int i5 = 0; i5 < length2; i5++) {
                Span span2 = this.mSpans[i5];
                if (!span2.mViews.isEmpty()) {
                    if (anchorInfoWrapper.layoutFromEnd) {
                        iMin = Math.max(iMin, layoutManagerHelper.getPosition((View) span2.mViews.get(span2.mViews.size() - 1)));
                    } else {
                        iMin = Math.min(iMin, layoutManagerHelper.getPosition((View) span2.mViews.get(0)));
                    }
                }
            }
        }
        if (!isOutOfRange(iMin)) {
            boolean z = iMin == ((Integer) range.getLower()).intValue();
            View viewFindViewByPosition2 = layoutManagerHelper.findViewByPosition(iMin);
            if (viewFindViewByPosition2 != null) {
                if (anchorInfoWrapper.layoutFromEnd) {
                    anchorInfoWrapper.position = iMin;
                    int decoratedEnd = mainOrientationHelper.getDecoratedEnd(viewFindViewByPosition);
                    if (decoratedEnd < anchorInfoWrapper.coordinate) {
                        int i6 = anchorInfoWrapper.coordinate - decoratedEnd;
                        if (z) {
                            i2 = 0;
                        }
                        i = i6 + i2;
                        anchorInfoWrapper.coordinate = mainOrientationHelper.getDecoratedEnd(viewFindViewByPosition2) + i;
                    } else {
                        if (z) {
                            i2 = 0;
                        }
                        anchorInfoWrapper.coordinate = mainOrientationHelper.getDecoratedEnd(viewFindViewByPosition2) + i2;
                        i = i2;
                    }
                } else {
                    anchorInfoWrapper.position = iMin;
                    int decoratedStart = mainOrientationHelper.getDecoratedStart(viewFindViewByPosition);
                    if (decoratedStart > anchorInfoWrapper.coordinate) {
                        int i7 = anchorInfoWrapper.coordinate - decoratedStart;
                        if (z) {
                            i2 = 0;
                        }
                        i = i7 - i2;
                        anchorInfoWrapper.coordinate = mainOrientationHelper.getDecoratedStart(viewFindViewByPosition2) + i;
                    } else {
                        if (z) {
                            i2 = 0;
                        }
                        int i8 = -i2;
                        anchorInfoWrapper.coordinate = mainOrientationHelper.getDecoratedStart(viewFindViewByPosition2) + i8;
                        i4 = i8;
                    }
                }
                i4 = i;
            }
        } else {
            this.anchorPosition = anchorInfoWrapper.position;
            this.mLayoutWithAnchor = true;
        }
        Span[] spanArr3 = this.mSpans;
        if (spanArr3 != null) {
            int length3 = spanArr3.length;
            while (i3 < length3) {
                this.mSpans[i3].cacheReferenceLineAndClear(layoutManagerHelper.getReverseLayout() ^ anchorInfoWrapper.layoutFromEnd, i4, mainOrientationHelper);
                i3++;
            }
        }
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onSaveState(Bundle bundle) {
        super.onSaveState(bundle);
        bundle.putIntArray(LOOKUP_BUNDLE_KEY, this.mLazySpanLookup.mData);
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mLazySpanLookup.mData = bundle.getIntArray(LOOKUP_BUNDLE_KEY);
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onOffsetChildrenVertical(int i, LayoutManagerHelper layoutManagerHelper) {
        Span[] spanArr;
        super.onOffsetChildrenVertical(i, layoutManagerHelper);
        if (layoutManagerHelper.getOrientation() != 1 || (spanArr = this.mSpans) == null) {
            return;
        }
        int length = spanArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.mSpans[i2].onOffset(i);
        }
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onOffsetChildrenHorizontal(int i, LayoutManagerHelper layoutManagerHelper) {
        Span[] spanArr;
        super.onOffsetChildrenHorizontal(i, layoutManagerHelper);
        if (layoutManagerHelper.getOrientation() != 0 || (spanArr = this.mSpans) == null) {
            return;
        }
        int length = spanArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.mSpans[i2].onOffset(i);
        }
    }

    static class Span {
        static final int INVALID_OFFSET = Integer.MIN_VALUE;
        int mCachedEnd;
        int mCachedStart;
        int mDeletedSize;
        final int mIndex;
        int mLastEdgeEnd;
        int mLastEdgeStart;
        private ArrayList<View> mViews;

        private Span(int i) {
            this.mViews = new ArrayList<>();
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
            this.mDeletedSize = 0;
            this.mLastEdgeStart = Integer.MIN_VALUE;
            this.mLastEdgeEnd = Integer.MIN_VALUE;
            this.mIndex = i;
        }

        void calculateCachedStart(OrientationHelperEx orientationHelperEx) {
            if (this.mViews.size() == 0) {
                this.mCachedStart = Integer.MIN_VALUE;
            } else {
                this.mCachedStart = orientationHelperEx.getDecoratedStart(this.mViews.get(0));
            }
        }

        int getStartLine(OrientationHelperEx orientationHelperEx) {
            return getStartLine(Integer.MIN_VALUE, orientationHelperEx);
        }

        int getStartLine(int i, OrientationHelperEx orientationHelperEx) {
            int i2 = this.mCachedStart;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (i != Integer.MIN_VALUE && this.mViews.size() == 0) {
                int i3 = this.mLastEdgeEnd;
                return i3 != Integer.MIN_VALUE ? i3 : i;
            }
            calculateCachedStart(orientationHelperEx);
            return this.mCachedStart;
        }

        void calculateCachedEnd(OrientationHelperEx orientationHelperEx) {
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            } else {
                ArrayList<View> arrayList = this.mViews;
                this.mCachedEnd = orientationHelperEx.getDecoratedEnd(arrayList.get(arrayList.size() - 1));
            }
        }

        int getEndLine(OrientationHelperEx orientationHelperEx) {
            return getEndLine(Integer.MIN_VALUE, orientationHelperEx);
        }

        int getEndLine(int i, OrientationHelperEx orientationHelperEx) {
            int i2 = this.mCachedEnd;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (i != Integer.MIN_VALUE && this.mViews.size() == 0) {
                int i3 = this.mLastEdgeStart;
                return i3 != Integer.MIN_VALUE ? i3 : i;
            }
            calculateCachedEnd(orientationHelperEx);
            return this.mCachedEnd;
        }

        void prependToSpan(View view, OrientationHelperEx orientationHelperEx) {
            RecyclerView.LayoutParams layoutParams = getLayoutParams(view);
            this.mViews.add(0, view);
            this.mCachedStart = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize += orientationHelperEx.getDecoratedMeasurement(view);
            }
        }

        void appendToSpan(View view, OrientationHelperEx orientationHelperEx) {
            RecyclerView.LayoutParams layoutParams = getLayoutParams(view);
            this.mViews.add(view);
            this.mCachedEnd = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize += orientationHelperEx.getDecoratedMeasurement(view);
            }
        }

        void cacheReferenceLineAndClear(boolean z, int i, OrientationHelperEx orientationHelperEx) {
            int startLine;
            if (z) {
                startLine = getEndLine(orientationHelperEx);
            } else {
                startLine = getStartLine(orientationHelperEx);
            }
            clear();
            if (startLine == Integer.MIN_VALUE) {
                return;
            }
            if ((!z || startLine >= orientationHelperEx.getEndAfterPadding()) && !z) {
                orientationHelperEx.getStartAfterPadding();
            }
            if (i != Integer.MIN_VALUE) {
                startLine += i;
            }
            this.mCachedEnd = startLine;
            this.mCachedStart = startLine;
            this.mLastEdgeEnd = Integer.MIN_VALUE;
            this.mLastEdgeStart = Integer.MIN_VALUE;
        }

        void clear() {
            this.mViews.clear();
            invalidateCache();
            this.mDeletedSize = 0;
        }

        void invalidateCache() {
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
            this.mLastEdgeEnd = Integer.MIN_VALUE;
            this.mLastEdgeStart = Integer.MIN_VALUE;
        }

        void setLine(int i) {
            this.mCachedStart = i;
            this.mCachedEnd = i;
            this.mLastEdgeEnd = Integer.MIN_VALUE;
            this.mLastEdgeStart = Integer.MIN_VALUE;
        }

        void popEnd(OrientationHelperEx orientationHelperEx) {
            int size = this.mViews.size();
            View viewRemove = this.mViews.remove(size - 1);
            RecyclerView.LayoutParams layoutParams = getLayoutParams(viewRemove);
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= orientationHelperEx.getDecoratedMeasurement(viewRemove);
            }
            if (size == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        boolean findEnd(View view) {
            int size = this.mViews.size();
            return size > 0 && this.mViews.get(size - 1) == view;
        }

        void popStart(OrientationHelperEx orientationHelperEx) {
            View viewRemove = this.mViews.remove(0);
            RecyclerView.LayoutParams layoutParams = getLayoutParams(viewRemove);
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= orientationHelperEx.getDecoratedMeasurement(viewRemove);
            }
            this.mCachedStart = Integer.MIN_VALUE;
        }

        boolean findStart(View view) {
            return this.mViews.size() > 0 && this.mViews.get(0) == view;
        }

        public int getDeletedSize() {
            return this.mDeletedSize;
        }

        RecyclerView.LayoutParams getLayoutParams(View view) {
            return (RecyclerView.LayoutParams) view.getLayoutParams();
        }

        void onOffset(int i) {
            int i2 = this.mLastEdgeStart;
            if (i2 != Integer.MIN_VALUE) {
                this.mLastEdgeStart = i2 + i;
            }
            int i3 = this.mCachedStart;
            if (i3 != Integer.MIN_VALUE) {
                this.mCachedStart = i3 + i;
            }
            int i4 = this.mLastEdgeEnd;
            if (i4 != Integer.MIN_VALUE) {
                this.mLastEdgeEnd = i4 + i;
            }
            int i5 = this.mCachedEnd;
            if (i5 != Integer.MIN_VALUE) {
                this.mCachedEnd = i5 + i;
            }
        }

        int getNormalizedOffset(int i, int i2, int i3, OrientationHelperEx orientationHelperEx) {
            if (this.mViews.size() == 0) {
                return 0;
            }
            if (i < 0) {
                int endLine = getEndLine(0, orientationHelperEx) - i3;
                if (endLine <= 0) {
                    return 0;
                }
                return (-i) > endLine ? -endLine : i;
            }
            int startLine = i2 - getStartLine(0, orientationHelperEx);
            if (startLine <= 0) {
                return 0;
            }
            return startLine < i ? startLine : i;
        }

        boolean isEmpty(int i, int i2, OrientationHelperEx orientationHelperEx) {
            int size = this.mViews.size();
            for (int i3 = 0; i3 < size; i3++) {
                View view = this.mViews.get(i3);
                if (orientationHelperEx.getDecoratedStart(view) < i2 && orientationHelperEx.getDecoratedEnd(view) > i) {
                    return false;
                }
            }
            return true;
        }
    }

    static class LazySpanLookup {
        private static final int MIN_SIZE = 10;
        int[] mData;

        LazySpanLookup() {
        }

        int invalidateAfter(int i) {
            int[] iArr = this.mData;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            Arrays.fill(iArr, i, iArr.length, Integer.MIN_VALUE);
            return this.mData.length;
        }

        int getSpan(int i) {
            int[] iArr = this.mData;
            if (iArr == null || i >= iArr.length || i < 0) {
                return Integer.MIN_VALUE;
            }
            return iArr[i];
        }

        void setSpan(int i, Span span) {
            ensureSize(i);
            this.mData[i] = span.mIndex;
        }

        int sizeForPosition(int i) {
            int length = this.mData.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        void ensureSize(int i) {
            int[] iArr = this.mData;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.mData = iArr2;
                Arrays.fill(iArr2, Integer.MIN_VALUE);
            } else if (i >= iArr.length) {
                int[] iArr3 = new int[sizeForPosition(i)];
                this.mData = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.mData;
                Arrays.fill(iArr4, iArr.length, iArr4.length, Integer.MIN_VALUE);
            }
        }

        void clear() {
            int[] iArr = this.mData;
            if (iArr != null) {
                Arrays.fill(iArr, Integer.MIN_VALUE);
            }
        }

        void offsetForRemoval(int i, int i2) {
            int[] iArr = this.mData;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            ensureSize(i3);
            int[] iArr2 = this.mData;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.mData;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, Integer.MIN_VALUE);
        }

        void offsetForAddition(int i, int i2) {
            int[] iArr = this.mData;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            ensureSize(i3);
            int[] iArr2 = this.mData;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.mData, i, i3, Integer.MIN_VALUE);
        }
    }
}
