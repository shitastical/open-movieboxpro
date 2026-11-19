package com.alibaba.android.vlayout.layout;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.OrientationHelperEx;
import com.alibaba.android.vlayout.Range;
import com.alibaba.android.vlayout.layout.RangeStyle;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RangeStyle<T extends RangeStyle> {
    private static final boolean DEBUG = false;
    private static final String TAG = "RangeStyle";
    private int mBgColor;
    protected BaseLayoutHelper mLayoutHelper;
    private View mLayoutView;
    private BaseLayoutHelper.LayoutViewBindListener mLayoutViewBindListener;
    private BaseLayoutHelper.LayoutViewUnBindListener mLayoutViewUnBindListener;
    protected int mMarginBottom;
    protected int mMarginLeft;
    protected int mMarginRight;
    protected int mMarginTop;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mPaddingTop;
    protected T mParent;
    protected Range<Integer> mRange;
    private int mOriginStartOffset = 0;
    private int mOriginEndOffset = 0;
    protected HashMap<Range<Integer>, T> mChildren = new HashMap<>();
    protected Rect mLayoutRegion = new Rect();

    private boolean isValidScrolled(int i) {
        return (i == Integer.MAX_VALUE || i == Integer.MIN_VALUE) ? false : true;
    }

    public RangeStyle(BaseLayoutHelper baseLayoutHelper) {
        this.mLayoutHelper = baseLayoutHelper;
    }

    public RangeStyle() {
    }

    public void addChildRangeStyle(int i, int i2, T t) {
        if (i > i2 || t == null) {
            return;
        }
        t.setParent(this);
        t.setOriginStartOffset(i);
        t.setOriginEndOffset(i2);
        t.setRange(i, i2);
        this.mChildren.put(t.getRange(), t);
    }

    public void setParent(T t) {
        this.mParent = t;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingRight = i3;
        this.mPaddingTop = i2;
        this.mPaddingBottom = i4;
    }

    public void setMargin(int i, int i2, int i3, int i4) {
        this.mMarginLeft = i;
        this.mMarginTop = i2;
        this.mMarginRight = i3;
        this.mMarginBottom = i4;
    }

    protected int getHorizontalMargin() {
        return this.mMarginLeft + this.mMarginRight;
    }

    protected int getVerticalMargin() {
        return this.mMarginTop + this.mMarginBottom;
    }

    protected int getHorizontalPadding() {
        return this.mPaddingLeft + this.mPaddingRight;
    }

    protected int getVerticalPadding() {
        return this.mPaddingTop + this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getMarginLeft() {
        return this.mMarginLeft;
    }

    public int getMarginRight() {
        return this.mMarginRight;
    }

    public int getMarginTop() {
        return this.mMarginTop;
    }

    public int getMarginBottom() {
        return this.mMarginBottom;
    }

    public void setPaddingLeft(int i) {
        this.mPaddingLeft = i;
    }

    public void setPaddingRight(int i) {
        this.mPaddingRight = i;
    }

    public void setPaddingTop(int i) {
        this.mPaddingTop = i;
    }

    public void setPaddingBottom(int i) {
        this.mPaddingBottom = i;
    }

    public void setMarginLeft(int i) {
        this.mMarginLeft = i;
    }

    public void setMarginRight(int i) {
        this.mMarginRight = i;
    }

    public void setMarginTop(int i) {
        this.mMarginTop = i;
    }

    public void setMarginBottom(int i) {
        this.mMarginBottom = i;
    }

    public int getFamilyHorizontalMargin() {
        T t = this.mParent;
        return (t != null ? t.getFamilyHorizontalMargin() : 0) + getHorizontalMargin();
    }

    public int getFamilyVerticalMargin() {
        T t = this.mParent;
        return (t != null ? t.getFamilyVerticalMargin() : 0) + getVerticalMargin();
    }

    public int getFamilyHorizontalPadding() {
        T t = this.mParent;
        return (t != null ? t.getFamilyHorizontalPadding() : 0) + getHorizontalPadding();
    }

    public int getFamilyVerticalPadding() {
        T t = this.mParent;
        return (t != null ? t.getFamilyVerticalPadding() : 0) + getVerticalPadding();
    }

    public int getFamilyPaddingLeft() {
        T t = this.mParent;
        return (t != null ? t.getFamilyPaddingLeft() : 0) + this.mPaddingLeft;
    }

    public int getFamilyPaddingRight() {
        T t = this.mParent;
        return (t != null ? t.getFamilyPaddingRight() : 0) + this.mPaddingRight;
    }

    public int getFamilyPaddingTop() {
        T t = this.mParent;
        return (t != null ? t.getFamilyPaddingTop() : 0) + this.mPaddingTop;
    }

    public int getFamilyPaddingBottom() {
        T t = this.mParent;
        return (t != null ? t.getFamilyPaddingBottom() : 0) + this.mPaddingBottom;
    }

    public int getFamilyMarginLeft() {
        T t = this.mParent;
        return (t != null ? t.getFamilyMarginLeft() : 0) + this.mMarginLeft;
    }

    public int getFamilyMarginRight() {
        T t = this.mParent;
        return (t != null ? t.getFamilyMarginRight() : 0) + this.mMarginRight;
    }

    public int getFamilyMarginTop() {
        T t = this.mParent;
        return (t != null ? t.getFamilyMarginTop() : 0) + this.mMarginTop;
    }

    public int getFamilyMarginBottom() {
        T t = this.mParent;
        return (t != null ? t.getFamilyMarginBottom() : 0) + this.mMarginBottom;
    }

    public int getAncestorHorizontalMargin() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorHorizontalMargin() + this.mParent.getHorizontalMargin();
        }
        return 0;
    }

    public int getAncestorVerticalMargin() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorVerticalMargin() + this.mParent.getVerticalMargin();
        }
        return 0;
    }

    public int getAncestorHorizontalPadding() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorHorizontalPadding() + this.mParent.getHorizontalPadding();
        }
        return 0;
    }

    public int getAncestorVerticalPadding() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorVerticalPadding() + this.mParent.getVerticalPadding();
        }
        return 0;
    }

    public int getAncestorPaddingLeft() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorPaddingLeft() + this.mParent.getPaddingLeft();
        }
        return 0;
    }

    public int getAncestorPaddingRight() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorPaddingRight() + this.mParent.getPaddingRight();
        }
        return 0;
    }

    public int getAncestorPaddingTop() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorPaddingTop() + this.mParent.getPaddingTop();
        }
        return 0;
    }

    public int getAncestorPaddingBottom() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorPaddingBottom() + this.mParent.getPaddingBottom();
        }
        return 0;
    }

    public int getAncestorMarginLeft() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorMarginLeft() + this.mParent.getMarginLeft();
        }
        return 0;
    }

    public int getAncestorMarginRight() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorMarginRight() + this.mParent.getMarginRight();
        }
        return 0;
    }

    public int getAncestorMarginTop() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorMarginTop() + this.mParent.getMarginTop();
        }
        return 0;
    }

    public int getAncestorMarginBottom() {
        T t = this.mParent;
        if (t != null) {
            return t.getAncestorMarginBottom() + this.mParent.getMarginBottom();
        }
        return 0;
    }

    public int getOriginStartOffset() {
        return this.mOriginStartOffset;
    }

    public int getOriginEndOffset() {
        return this.mOriginEndOffset;
    }

    public void setOriginStartOffset(int i) {
        this.mOriginStartOffset = i;
    }

    public void setOriginEndOffset(int i) {
        this.mOriginEndOffset = i;
    }

    public Range<Integer> getRange() {
        return this.mRange;
    }

    public BaseLayoutHelper getLayoutHelper() {
        BaseLayoutHelper baseLayoutHelper = this.mLayoutHelper;
        if (baseLayoutHelper != null) {
            return baseLayoutHelper;
        }
        T t = this.mParent;
        if (t != null) {
            return t.getLayoutHelper();
        }
        return null;
    }

    public boolean isChildrenEmpty() {
        return this.mChildren.isEmpty();
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isOutOfRange(int i) {
        Range<Integer> range = this.mRange;
        return range == null || !range.contains(Integer.valueOf(i));
    }

    public boolean isFirstPosition(int i) {
        Range<Integer> range = this.mRange;
        return range != null && ((Integer) range.getLower()).intValue() == i;
    }

    public boolean isLastPosition(int i) {
        Range<Integer> range = this.mRange;
        return range != null && ((Integer) range.getUpper()).intValue() == i;
    }

    public void setRange(int i, int i2) {
        this.mRange = Range.create(Integer.valueOf(i), Integer.valueOf(i2));
        if (this.mChildren.isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        Iterator<Map.Entry<Range<Integer>, T>> it = this.mChildren.entrySet().iterator();
        while (it.hasNext()) {
            T value = it.next().getValue();
            int originStartOffset = value.getOriginStartOffset() + i;
            int originEndOffset = value.getOriginEndOffset() + i;
            map.put(Range.create(Integer.valueOf(originStartOffset), Integer.valueOf(originEndOffset)), value);
            value.setRange(originStartOffset, originEndOffset);
        }
        this.mChildren.clear();
        this.mChildren.putAll(map);
    }

    public void beforeLayout(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutManagerHelper layoutManagerHelper) {
        View view;
        if (!isChildrenEmpty()) {
            Iterator<Map.Entry<Range<Integer>, T>> it = this.mChildren.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().beforeLayout(recycler, state, layoutManagerHelper);
            }
        }
        if (requireLayoutView() || (view = this.mLayoutView) == null) {
            return;
        }
        BaseLayoutHelper.LayoutViewUnBindListener layoutViewUnBindListener = this.mLayoutViewUnBindListener;
        if (layoutViewUnBindListener != null) {
            layoutViewUnBindListener.onUnbind(view, getLayoutHelper());
        }
        layoutManagerHelper.removeChildView(this.mLayoutView);
        this.mLayoutView = null;
    }

    public void afterLayout(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, int i3, LayoutManagerHelper layoutManagerHelper) {
        View view;
        if (!isChildrenEmpty()) {
            Iterator<Map.Entry<Range<Integer>, T>> it = this.mChildren.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().afterLayout(recycler, state, i, i2, i3, layoutManagerHelper);
            }
        }
        if (requireLayoutView()) {
            if (isValidScrolled(i3) && (view = this.mLayoutView) != null) {
                this.mLayoutRegion.union(view.getLeft(), this.mLayoutView.getTop(), this.mLayoutView.getRight(), this.mLayoutView.getBottom());
            }
            if (!this.mLayoutRegion.isEmpty()) {
                if (isValidScrolled(i3)) {
                    if (layoutManagerHelper.getOrientation() == 1) {
                        this.mLayoutRegion.offset(0, -i3);
                    } else {
                        this.mLayoutRegion.offset(-i3, 0);
                    }
                }
                unionChildRegion(this);
                int contentWidth = layoutManagerHelper.getContentWidth();
                int contentHeight = layoutManagerHelper.getContentHeight();
                if (layoutManagerHelper.getOrientation() != 1 ? this.mLayoutRegion.intersects((-contentWidth) / 4, 0, contentWidth + (contentWidth / 4), contentHeight) : this.mLayoutRegion.intersects(0, (-contentHeight) / 4, contentWidth, contentHeight + (contentHeight / 4))) {
                    if (this.mLayoutView == null) {
                        View viewGenerateLayoutView = layoutManagerHelper.generateLayoutView();
                        this.mLayoutView = viewGenerateLayoutView;
                        layoutManagerHelper.addBackgroundView(viewGenerateLayoutView, true);
                    }
                    if (layoutManagerHelper.getOrientation() == 1) {
                        this.mLayoutRegion.left = layoutManagerHelper.getPaddingLeft() + getFamilyMarginLeft() + getAncestorPaddingLeft();
                        this.mLayoutRegion.right = ((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingRight()) - getFamilyMarginRight()) - getAncestorPaddingRight();
                    } else {
                        this.mLayoutRegion.top = layoutManagerHelper.getPaddingTop() + getFamilyMarginTop() + getAncestorPaddingTop();
                        this.mLayoutRegion.bottom = ((layoutManagerHelper.getContentWidth() - layoutManagerHelper.getPaddingBottom()) - getFamilyMarginBottom()) - getAncestorPaddingBottom();
                    }
                    bindLayoutView(this.mLayoutView);
                    hideLayoutViews(layoutManagerHelper);
                    return;
                }
                this.mLayoutRegion.set(0, 0, 0, 0);
                View view2 = this.mLayoutView;
                if (view2 != null) {
                    view2.layout(0, 0, 0, 0);
                }
                hideLayoutViews(layoutManagerHelper);
            }
        }
        hideLayoutViews(layoutManagerHelper);
        if (isRoot()) {
            removeChildViews(layoutManagerHelper, this);
        }
    }

    private void unionChildRegion(RangeStyle<T> rangeStyle) {
        if (rangeStyle.isChildrenEmpty()) {
            return;
        }
        Iterator<Map.Entry<Range<Integer>, T>> it = rangeStyle.mChildren.entrySet().iterator();
        while (it.hasNext()) {
            T value = it.next().getValue();
            unionChildRegion(value);
            View view = value.mLayoutView;
            if (view != null) {
                rangeStyle.mLayoutRegion.union(view.getLeft(), value.mLayoutView.getTop(), value.mLayoutView.getRight(), value.mLayoutView.getBottom());
            }
        }
    }

    private void removeChildViews(LayoutManagerHelper layoutManagerHelper, RangeStyle<T> rangeStyle) {
        if (!rangeStyle.isChildrenEmpty()) {
            Iterator<Map.Entry<Range<Integer>, T>> it = rangeStyle.mChildren.entrySet().iterator();
            while (it.hasNext()) {
                removeChildViews(layoutManagerHelper, it.next().getValue());
            }
        }
        View view = rangeStyle.mLayoutView;
        if (view != null) {
            BaseLayoutHelper.LayoutViewUnBindListener layoutViewUnBindListener = rangeStyle.mLayoutViewUnBindListener;
            if (layoutViewUnBindListener != null) {
                layoutViewUnBindListener.onUnbind(view, getLayoutHelper());
            }
            layoutManagerHelper.removeChildView(rangeStyle.mLayoutView);
            rangeStyle.mLayoutView = null;
        }
    }

    public void adjustLayout(int i, int i2, LayoutManagerHelper layoutManagerHelper) {
        if (!isChildrenEmpty()) {
            Iterator<Map.Entry<Range<Integer>, T>> it = this.mChildren.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().adjustLayout(i, i2, layoutManagerHelper);
            }
        }
        if (requireLayoutView()) {
            Rect rect = new Rect();
            OrientationHelperEx mainOrientationHelper = layoutManagerHelper.getMainOrientationHelper();
            for (int i3 = 0; i3 < layoutManagerHelper.getChildCount(); i3++) {
                View childAt = layoutManagerHelper.getChildAt(i3);
                if (getRange().contains(Integer.valueOf(layoutManagerHelper.getPosition(childAt)))) {
                    if (childAt.getVisibility() == 8) {
                        rect.setEmpty();
                    } else {
                        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
                        if (layoutManagerHelper.getOrientation() == 1) {
                            rect.union(layoutManagerHelper.getDecoratedLeft(childAt) - layoutParams.leftMargin, mainOrientationHelper.getDecoratedStart(childAt), layoutManagerHelper.getDecoratedRight(childAt) + layoutParams.rightMargin, mainOrientationHelper.getDecoratedEnd(childAt));
                        } else {
                            rect.union(mainOrientationHelper.getDecoratedStart(childAt), layoutManagerHelper.getDecoratedTop(childAt) - layoutParams.topMargin, mainOrientationHelper.getDecoratedEnd(childAt), layoutManagerHelper.getDecoratedBottom(childAt) + layoutParams.bottomMargin);
                        }
                    }
                }
            }
            if (!rect.isEmpty()) {
                this.mLayoutRegion.set(rect.left - this.mPaddingLeft, rect.top - this.mPaddingTop, rect.right + this.mPaddingRight, rect.bottom + this.mPaddingBottom);
            } else {
                this.mLayoutRegion.setEmpty();
            }
            View view = this.mLayoutView;
            if (view != null) {
                view.layout(this.mLayoutRegion.left, this.mLayoutRegion.top, this.mLayoutRegion.right, this.mLayoutRegion.bottom);
            }
        }
    }

    private void hideLayoutViews(LayoutManagerHelper layoutManagerHelper) {
        if (isRoot()) {
            hideChildLayoutViews(layoutManagerHelper, this);
            View view = this.mLayoutView;
            if (view != null) {
                layoutManagerHelper.hideView(view);
            }
        }
    }

    private void hideChildLayoutViews(LayoutManagerHelper layoutManagerHelper, RangeStyle<T> rangeStyle) {
        Iterator<Map.Entry<Range<Integer>, T>> it = rangeStyle.mChildren.entrySet().iterator();
        while (it.hasNext()) {
            T value = it.next().getValue();
            if (!value.isChildrenEmpty()) {
                hideChildLayoutViews(layoutManagerHelper, value);
            }
            View view = value.mLayoutView;
            if (view != null) {
                layoutManagerHelper.hideView(view);
            }
        }
    }

    public boolean requireLayoutView() {
        boolean z = (this.mBgColor == 0 && this.mLayoutViewBindListener == null) ? false : true;
        return !isChildrenEmpty() ? z | requireChildLayoutView(this) : z;
    }

    private boolean requireChildLayoutView(RangeStyle<T> rangeStyle) {
        boolean zRequireChildLayoutView = (rangeStyle.mBgColor == 0 && rangeStyle.mLayoutViewBindListener == null) ? false : true;
        Iterator<Map.Entry<Range<Integer>, T>> it = rangeStyle.mChildren.entrySet().iterator();
        while (it.hasNext()) {
            T value = it.next().getValue();
            if (!value.isChildrenEmpty()) {
                zRequireChildLayoutView |= requireChildLayoutView(value);
            } else {
                return value.requireLayoutView();
            }
        }
        return zRequireChildLayoutView;
    }

    public void bindLayoutView(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(this.mLayoutRegion.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.mLayoutRegion.height(), 1073741824));
        view.layout(this.mLayoutRegion.left, this.mLayoutRegion.top, this.mLayoutRegion.right, this.mLayoutRegion.bottom);
        view.setBackgroundColor(this.mBgColor);
        BaseLayoutHelper.LayoutViewBindListener layoutViewBindListener = this.mLayoutViewBindListener;
        if (layoutViewBindListener != null) {
            layoutViewBindListener.onBind(view, getLayoutHelper());
        }
        this.mLayoutRegion.set(0, 0, 0, 0);
    }

    public void setLayoutViewHelper(BaseLayoutHelper.DefaultLayoutViewHelper defaultLayoutViewHelper) {
        this.mLayoutViewBindListener = defaultLayoutViewHelper;
        this.mLayoutViewUnBindListener = defaultLayoutViewHelper;
    }

    public void setLayoutViewBindListener(BaseLayoutHelper.LayoutViewBindListener layoutViewBindListener) {
        this.mLayoutViewBindListener = layoutViewBindListener;
    }

    public void setLayoutViewUnBindListener(BaseLayoutHelper.LayoutViewUnBindListener layoutViewUnBindListener) {
        this.mLayoutViewUnBindListener = layoutViewUnBindListener;
    }

    public void setBgColor(int i) {
        this.mBgColor = i;
    }

    public void onClear(LayoutManagerHelper layoutManagerHelper) {
        clearChild(layoutManagerHelper, this);
    }

    private void clearChild(LayoutManagerHelper layoutManagerHelper, RangeStyle<T> rangeStyle) {
        View view = rangeStyle.mLayoutView;
        if (view != null) {
            BaseLayoutHelper.LayoutViewUnBindListener layoutViewUnBindListener = rangeStyle.mLayoutViewUnBindListener;
            if (layoutViewUnBindListener != null) {
                layoutViewUnBindListener.onUnbind(view, getLayoutHelper());
            }
            layoutManagerHelper.removeChildView(rangeStyle.mLayoutView);
            rangeStyle.mLayoutView = null;
        }
        if (rangeStyle.mChildren.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<Range<Integer>, T>> it = rangeStyle.mChildren.entrySet().iterator();
        while (it.hasNext()) {
            clearChild(layoutManagerHelper, it.next().getValue());
        }
    }

    public void onClearChildMap() {
        this.mChildren.clear();
    }

    public void layoutChild(View view, int i, int i2, int i3, int i4, LayoutManagerHelper layoutManagerHelper, boolean z) {
        layoutManagerHelper.layoutChildWithMargins(view, i, i2, i3, i4);
        fillLayoutRegion(i, i2, i3, i4, z);
    }

    protected void fillLayoutRegion(int i, int i2, int i3, int i4, boolean z) {
        if (z) {
            this.mLayoutRegion.union((i - this.mPaddingLeft) - this.mMarginLeft, (i2 - this.mPaddingTop) - this.mMarginTop, this.mPaddingRight + i3 + this.mMarginRight, this.mPaddingBottom + i4 + this.mMarginBottom);
        } else {
            this.mLayoutRegion.union(i - this.mPaddingLeft, i2 - this.mPaddingTop, this.mPaddingRight + i3, this.mPaddingBottom + i4);
        }
        T t = this.mParent;
        if (t != null) {
            int i5 = i - this.mPaddingLeft;
            int i6 = this.mMarginLeft;
            t.fillLayoutRegion(i5 - i6, (i2 - this.mPaddingTop) - i6, this.mPaddingRight + i3 + this.mMarginRight, this.mPaddingBottom + i4 + this.mMarginBottom, z);
        }
    }

    private static class RangeMap<T> {
        private static final int CAPACITY = 64;
        private T[] mCardMap;
        private Class<T> mClass;
        private int lastIndex = -1;
        private int[] mOffsetMap = new int[64];

        public RangeMap(Class<T> cls) {
            this.mCardMap = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mClass, 64));
            this.mClass = cls;
        }

        public void addChild(int i, int i2, T t) {
            int length = this.lastIndex + 1;
            T[] tArr = this.mCardMap;
            if (length < tArr.length) {
                tArr[length] = t;
            } else {
                length = tArr.length;
                T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mClass, length * 2));
                System.arraycopy(this.mCardMap, 0, tArr2, 0, length);
                this.mCardMap = tArr2;
                tArr2[length] = t;
                int[] iArr = this.mOffsetMap;
                int length2 = iArr.length;
                int[] iArr2 = new int[length2 * 2];
                System.arraycopy(iArr, 0, iArr2, 0, length2);
                this.mOffsetMap = iArr2;
            }
            this.lastIndex = length;
            while (i <= i2) {
                this.mOffsetMap[i] = length;
                i++;
            }
        }

        public T getChild(int i) {
            return this.mCardMap[this.mOffsetMap[i]];
        }
    }
}
