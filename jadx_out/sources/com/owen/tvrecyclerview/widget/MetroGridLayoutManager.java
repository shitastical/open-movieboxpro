package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.BaseLayoutManager;
import com.owen.tvrecyclerview.Lanes;
import com.owen.tvrecyclerview.R;
import com.owen.tvrecyclerview.TwoWayLayoutManager;
import com.owen.tvrecyclerview.utils.Loger;
import com.owen.tvrecyclerview.utils.MathUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MetroGridLayoutManager extends GridLayoutManager {
    private static final int[] DEFAULT_LANE_COUNTS = {2};
    private boolean mIsIntelligentScroll;
    private int[] mLaneCounts;
    private boolean mMeasuring;
    private List<OnSectionSelectedListener> mSectionSelectedListeners;
    private int mSelectedSectionIndex;
    private int smoothTargetPosition;

    public interface OnSectionSelectedListener {
        void onSectionSelected(int i);
    }

    public MetroGridLayoutManager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MetroGridLayoutManager(Context context, AttributeSet attributeSet, int i) {
        int[] iArr;
        super(context, attributeSet, i);
        this.mSelectedSectionIndex = 0;
        this.mIsIntelligentScroll = true;
        this.smoothTargetPosition = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView, i, 0);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.TvRecyclerView_tv_laneCountsStr);
        this.mIsIntelligentScroll = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TvRecyclerView_tv_isIntelligentScroll, true);
        typedArrayObtainStyledAttributes.recycle();
        if (!TextUtils.isEmpty(string)) {
            String[] strArrSplit = string.split(",");
            int length = strArrSplit.length;
            iArr = new int[length];
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = Integer.parseInt(strArrSplit[i2]);
            }
        } else {
            iArr = DEFAULT_LANE_COUNTS;
        }
        initLaneCounts(iArr);
    }

    public MetroGridLayoutManager(TwoWayLayoutManager.Orientation orientation, int... iArr) {
        super(orientation);
        this.mSelectedSectionIndex = 0;
        this.mIsIntelligentScroll = true;
        this.smoothTargetPosition = -1;
        initLaneCounts(iArr);
    }

    private void initLaneCounts(int... iArr) {
        this.mLaneCounts = iArr;
        int iCommonMultiple = MathUtil.commonMultiple(iArr);
        setNumColumns(iCommonMultiple);
        setNumRows(iCommonMultiple);
        Loger.i("multiple=" + iCommonMultiple);
    }

    public void setIntelligentScroll(boolean z) {
        this.mIsIntelligentScroll = z;
    }

    public boolean isIntelligentScroll() {
        return this.mIsIntelligentScroll;
    }

    private int getLaneSpan(LayoutParams layoutParams, boolean z) {
        return z ? layoutParams.getColSpan() : layoutParams.getRowSpan();
    }

    private int getLaneSpan(MetroItemEntry metroItemEntry, boolean z) {
        return z ? metroItemEntry.getColSpan() : metroItemEntry.getRowSpan();
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return super.canScrollHorizontally() && !this.mMeasuring;
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return super.canScrollVertically() && !this.mMeasuring;
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    public int getLaneSpanForChild(View view) {
        return getLaneSpan((LayoutParams) view.getLayoutParams(), isVertical());
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    public int getLaneSpanForPosition(int i) {
        MetroItemEntry metroItemEntry = (MetroItemEntry) getItemEntryForPosition(i);
        if (metroItemEntry == null) {
            View childAt = getChildAt(i - getFirstVisiblePosition());
            if (childAt != null) {
                return getLaneSpanForChild(childAt);
            }
            throw new IllegalStateException("Could not find span for position " + i);
        }
        return getLaneSpan(metroItemEntry, isVertical());
    }

    @Override // com.owen.tvrecyclerview.widget.GridLayoutManager, com.owen.tvrecyclerview.BaseLayoutManager
    public void getLaneForPosition(Lanes.LaneInfo laneInfo, int i, TwoWayLayoutManager.Direction direction) {
        MetroItemEntry metroItemEntry = (MetroItemEntry) getItemEntryForPosition(i);
        if (metroItemEntry != null) {
            laneInfo.set(metroItemEntry.startLane, metroItemEntry.anchorLane);
        } else {
            laneInfo.setUndefined();
        }
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    protected void getLaneForChild(Lanes.LaneInfo laneInfo, View view, TwoWayLayoutManager.Direction direction) {
        super.getLaneForChild(laneInfo, view, direction);
        if (laneInfo.isUndefined()) {
            getLanes().findLane(laneInfo, getLaneSpanForChild(view), direction);
        }
    }

    private int getChildWidth(int i) {
        return (int) (getLanes().getLaneSize() * i);
    }

    private int getChildHeight(int i) {
        return (int) (getLanes().getLaneSize() * i);
    }

    private int getWidthUsed(View view) {
        return ((getWidth() - getPaddingLeft()) - getPaddingRight()) - getChildWidth(((LayoutParams) view.getLayoutParams()).getColSpan());
    }

    private int getHeightUsed(View view) {
        return ((getHeight() - getPaddingTop()) - getPaddingBottom()) - getChildHeight(((LayoutParams) view.getLayoutParams()).getRowSpan());
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    protected void measureChildWithMargins(View view) {
        this.mMeasuring = true;
        measureChildWithMargins(view, getWidthUsed(view), getHeightUsed(view));
        this.mMeasuring = false;
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, com.owen.tvrecyclerview.TwoWayLayoutManager
    protected void layoutChild(View view, TwoWayLayoutManager.Direction direction) {
        super.layoutChild(view, direction);
    }

    @Override // com.owen.tvrecyclerview.widget.GridLayoutManager, com.owen.tvrecyclerview.BaseLayoutManager
    protected void moveLayoutToPosition(int i, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        boolean zIsVertical = isVertical();
        Lanes lanes = getLanes();
        lanes.reset(0);
        for (int i3 = 0; i3 <= i; i3++) {
            MetroItemEntry metroItemEntry = (MetroItemEntry) getItemEntryForPosition(i3);
            if (metroItemEntry == null) {
                metroItemEntry = (MetroItemEntry) cacheChildLaneAndSpan(recycler.getViewForPosition(i3), TwoWayLayoutManager.Direction.END);
            }
            MetroItemEntry metroItemEntry2 = metroItemEntry;
            this.mTempLaneInfo.set(metroItemEntry2.startLane, metroItemEntry2.anchorLane);
            if (this.mTempLaneInfo.isUndefined()) {
                lanes.findLane(this.mTempLaneInfo, getLaneSpanForPosition(i3), TwoWayLayoutManager.Direction.END);
                metroItemEntry2.setLane(this.mTempLaneInfo);
            }
            lanes.getChildFrame(this.mTempRect, getChildWidth(metroItemEntry2.getColSpan()), getChildHeight(metroItemEntry2.getRowSpan()), this.mTempLaneInfo, TwoWayLayoutManager.Direction.END);
            if (i3 != i) {
                pushChildFrame(metroItemEntry2, this.mTempRect, metroItemEntry2.startLane, getLaneSpan(metroItemEntry2, zIsVertical), TwoWayLayoutManager.Direction.END);
            }
        }
        lanes.getLane(this.mTempLaneInfo.startLane, this.mTempRect);
        lanes.reset(TwoWayLayoutManager.Direction.END);
        Rect rect = this.mTempRect;
        lanes.offset(i2 - (zIsVertical ? rect.bottom : rect.right));
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    protected BaseLayoutManager.ItemEntry cacheChildLaneAndSpan(View view, TwoWayLayoutManager.Direction direction) {
        int position = getPosition(view);
        this.mTempLaneInfo.setUndefined();
        MetroItemEntry metroItemEntry = (MetroItemEntry) getItemEntryForPosition(position);
        if (metroItemEntry != null) {
            this.mTempLaneInfo.set(metroItemEntry.startLane, metroItemEntry.anchorLane);
        }
        if (this.mTempLaneInfo.isUndefined()) {
            getLaneForChild(this.mTempLaneInfo, view, direction);
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (metroItemEntry == null) {
            Loger.i("cacheChildLaneAndSpan position=" + position + " direction=" + direction);
            int laneSpan = 0;
            for (int firstVisiblePosition = getFirstVisiblePosition(); firstVisiblePosition < position; firstVisiblePosition++) {
                MetroItemEntry metroItemEntry2 = (MetroItemEntry) getItemEntryForPosition(firstVisiblePosition);
                if (metroItemEntry2 != null && metroItemEntry2.sectionIndex == layoutParams.sectionIndex && (laneSpan = laneSpan + getLaneSpan(metroItemEntry2, isVertical())) > getLaneCount()) {
                    break;
                }
            }
            if (laneSpan + getLaneSpan(layoutParams, isVertical()) <= getLaneCount()) {
                layoutParams.isSectionStart = true;
            } else {
                layoutParams.isSectionStart = false;
            }
            metroItemEntry = new MetroItemEntry(this.mTempLaneInfo.startLane, this.mTempLaneInfo.anchorLane, layoutParams.colSpan, layoutParams.rowSpan, layoutParams.scale, layoutParams.sectionIndex, layoutParams.isSectionStart);
            setItemEntryForPosition(position, metroItemEntry);
        } else {
            metroItemEntry.setLane(this.mTempLaneInfo);
            layoutParams.isSectionStart = metroItemEntry.isSectionStart;
        }
        Loger.i("cacheChildLaneAndSpan position=" + position + " lp.isSectionStart=" + layoutParams.isSectionStart + " TopDecorationHeight=" + getTopDecorationHeight(view));
        return metroItemEntry;
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        boolean z3;
        int width;
        int selectedItemOffsetEnd;
        int paddingLeft;
        int selectedItemOffsetStart;
        int i;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.mSelectedSectionIndex != layoutParams.sectionIndex) {
            TwoWayLayoutManager.Direction direction = this.mSelectedSectionIndex < layoutParams.sectionIndex ? TwoWayLayoutManager.Direction.END : TwoWayLayoutManager.Direction.START;
            boolean z4 = direction == TwoWayLayoutManager.Direction.END ? layoutParams.isSuportIntelligentScrollEnd : layoutParams.isSuportIntelligentScrollStart;
            if (this.mIsIntelligentScroll && z4 && (recyclerView instanceof TvRecyclerView)) {
                getDecoratedBoundsWithMargins(view, this.mTempRect);
                TvRecyclerView tvRecyclerView = (TvRecyclerView) recyclerView;
                if (isVertical()) {
                    if (direction == TwoWayLayoutManager.Direction.END) {
                        paddingLeft = this.mTempRect.top - getPaddingTop();
                        selectedItemOffsetStart = tvRecyclerView.getSelectedItemOffsetStart();
                        i = paddingLeft - selectedItemOffsetStart;
                    } else {
                        width = (tvRecyclerView.getHeight() - this.mTempRect.bottom) - getPaddingBottom();
                        selectedItemOffsetEnd = tvRecyclerView.getSelectedItemOffsetEnd();
                        i = -(width - selectedItemOffsetEnd);
                    }
                } else if (direction == TwoWayLayoutManager.Direction.END) {
                    paddingLeft = this.mTempRect.left - getPaddingLeft();
                    selectedItemOffsetStart = tvRecyclerView.getSelectedItemOffsetStart();
                    i = paddingLeft - selectedItemOffsetStart;
                } else {
                    width = (tvRecyclerView.getWidth() - this.mTempRect.right) - getPaddingRight();
                    selectedItemOffsetEnd = tvRecyclerView.getSelectedItemOffsetEnd();
                    i = -(width - selectedItemOffsetEnd);
                }
                int i2 = (isVertical() || !ViewCompat.canScrollHorizontally(recyclerView, i)) ? 0 : i;
                if (!isVertical() || !ViewCompat.canScrollVertically(recyclerView, i)) {
                    i = 0;
                }
                Loger.d("dx=" + i2 + " dy=" + i);
                tvRecyclerView.smoothScrollBy(i2, i);
                z3 = true;
            } else {
                z3 = false;
            }
            this.mSelectedSectionIndex = layoutParams.sectionIndex;
            notifySectionSelectedChanged();
        } else {
            z3 = false;
        }
        return z3 || super.requestChildRectangleOnScreen(recyclerView, view, rect, z, z2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i) {
        if (i == 0) {
            View viewFindViewByPosition = findViewByPosition(this.smoothTargetPosition);
            if (viewFindViewByPosition != null && !isSmoothScrolling() && !hasFocus()) {
                LayoutParams layoutParams = (LayoutParams) viewFindViewByPosition.getLayoutParams();
                if (this.mSelectedSectionIndex != layoutParams.sectionIndex) {
                    this.mSelectedSectionIndex = layoutParams.sectionIndex;
                    notifySectionSelectedChanged();
                }
            }
            this.smoothTargetPosition = -1;
        }
    }

    public int getSelectedSectionIndex() {
        return this.mSelectedSectionIndex;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void startSmoothScroll(RecyclerView.SmoothScroller smoothScroller) {
        this.smoothTargetPosition = smoothScroller.getTargetPosition();
        super.startSmoothScroll(smoothScroller);
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        super.checkLayoutParams(layoutParams);
        if (!(layoutParams instanceof LayoutParams)) {
            return false;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.scale = getLaneCount() / this.mLaneCounts[layoutParams2.sectionIndex];
        if (isVertical()) {
            layoutParams2.height = (getChildHeight(layoutParams2.getRowSpan()) - layoutParams2.topMargin) - layoutParams2.bottomMargin;
            layoutParams2.width = -1;
            return layoutParams2.rowSpan >= 1 && layoutParams2.colSpan >= 1 && layoutParams2.colSpan <= getLaneCount() && layoutParams2.sectionIndex >= 0 && layoutParams2.sectionIndex < this.mLaneCounts.length;
        }
        layoutParams2.height = -1;
        layoutParams2.width = (getChildHeight(layoutParams2.getColSpan()) - layoutParams2.leftMargin) - layoutParams2.rightMargin;
        return layoutParams2.colSpan >= 1 && layoutParams2.rowSpan >= 1 && layoutParams2.rowSpan <= getLaneCount() && layoutParams2.sectionIndex >= 0 && layoutParams2.sectionIndex < this.mLaneCounts.length;
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2 = new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams3 = (LayoutParams) layoutParams;
            layoutParams2.sectionIndex = Math.max(0, Math.min(layoutParams3.sectionIndex, this.mLaneCounts.length - 1));
            layoutParams2.scale = getLaneCount() / this.mLaneCounts[layoutParams2.sectionIndex];
            if (isVertical()) {
                layoutParams2.colSpan = Math.max(1, Math.min(layoutParams3.colSpan, this.mLaneCounts[layoutParams3.sectionIndex]));
                layoutParams2.rowSpan = Math.max(1, layoutParams3.rowSpan);
                layoutParams2.height = (getChildHeight(layoutParams2.getRowSpan()) - layoutParams2.topMargin) - layoutParams2.bottomMargin;
                layoutParams2.width = -1;
            } else {
                layoutParams2.colSpan = Math.max(1, layoutParams3.colSpan);
                layoutParams2.rowSpan = Math.max(1, Math.min(layoutParams3.rowSpan, this.mLaneCounts[layoutParams3.sectionIndex]));
                layoutParams2.height = -1;
                layoutParams2.width = (getChildHeight(layoutParams2.getColSpan()) - layoutParams2.leftMargin) - layoutParams2.rightMargin;
            }
        }
        return layoutParams2;
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public void addOnSectionSelectedListener(OnSectionSelectedListener onSectionSelectedListener) {
        if (this.mSectionSelectedListeners == null) {
            this.mSectionSelectedListeners = new ArrayList();
        }
        this.mSectionSelectedListeners.add(onSectionSelectedListener);
    }

    public void removeOnSectionSelectedListener(OnSectionSelectedListener onSectionSelectedListener) {
        List<OnSectionSelectedListener> list = this.mSectionSelectedListeners;
        if (list != null) {
            list.remove(onSectionSelectedListener);
        }
    }

    private void notifySectionSelectedChanged() {
        List<OnSectionSelectedListener> list = this.mSectionSelectedListeners;
        if (list != null) {
            Iterator<OnSectionSelectedListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onSectionSelected(this.mSelectedSectionIndex);
            }
        }
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        private static final int DEFAULT_INDEX = 0;
        private static final int DEFAULT_SCALE = 1;
        private static final int DEFAULT_SPAN = 1;
        public int colSpan;
        public boolean isSectionStart;
        public boolean isSuportIntelligentScrollEnd;
        public boolean isSuportIntelligentScrollStart;
        public int rowSpan;
        private int scale;
        public int sectionIndex;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            init(null);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView_SpannableGridViewChild);
            this.colSpan = Math.max(1, typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_SpannableGridViewChild_tv_colSpan, -1));
            this.rowSpan = Math.max(1, typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_SpannableGridViewChild_tv_rowSpan, -1));
            typedArrayObtainStyledAttributes.recycle();
            init(null);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            init(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            init(marginLayoutParams);
        }

        private void init(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams != null && (layoutParams instanceof LayoutParams)) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.scale = layoutParams2.scale;
                this.sectionIndex = layoutParams2.sectionIndex;
                this.isSectionStart = layoutParams2.isSectionStart;
                this.isSuportIntelligentScrollStart = layoutParams2.isSuportIntelligentScrollStart;
                this.isSuportIntelligentScrollEnd = layoutParams2.isSuportIntelligentScrollEnd;
                return;
            }
            this.rowSpan = 1;
            this.colSpan = 1;
            this.scale = 1;
            this.sectionIndex = 0;
            this.isSectionStart = false;
            this.isSuportIntelligentScrollStart = true;
            this.isSuportIntelligentScrollEnd = true;
        }

        public int getRowSpan() {
            return this.rowSpan * this.scale;
        }

        public int getColSpan() {
            return this.colSpan * this.scale;
        }

        public String toString() {
            return "[rowSpan=" + this.rowSpan + " colSpan=" + this.colSpan + " sectionIndex=" + this.sectionIndex + " scale=" + this.scale + "]";
        }
    }

    public static class MetroItemEntry extends BaseLayoutManager.ItemEntry {
        public static final Parcelable.Creator<MetroItemEntry> CREATOR = new Parcelable.Creator<MetroItemEntry>() { // from class: com.owen.tvrecyclerview.widget.MetroGridLayoutManager.MetroItemEntry.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MetroItemEntry createFromParcel(Parcel parcel) {
                return new MetroItemEntry(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MetroItemEntry[] newArray(int i) {
                return new MetroItemEntry[i];
            }
        };
        private final int colSpan;
        private final boolean isSectionStart;
        private final int rowSpan;
        private final int scale;
        private final int sectionIndex;

        @Override // com.owen.tvrecyclerview.BaseLayoutManager.ItemEntry, android.os.Parcelable
        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        @Override // com.owen.tvrecyclerview.BaseLayoutManager.ItemEntry
        public /* bridge */ /* synthetic */ void invalidateLane() {
            super.invalidateLane();
        }

        @Override // com.owen.tvrecyclerview.BaseLayoutManager.ItemEntry
        public /* bridge */ /* synthetic */ void setLane(Lanes.LaneInfo laneInfo) {
            super.setLane(laneInfo);
        }

        public MetroItemEntry(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
            super(i, i2);
            this.colSpan = i3;
            this.rowSpan = i4;
            this.scale = i5;
            this.sectionIndex = i6;
            this.isSectionStart = z;
        }

        public MetroItemEntry(Parcel parcel) {
            super(parcel);
            this.colSpan = parcel.readInt();
            this.rowSpan = parcel.readInt();
            this.scale = parcel.readInt();
            this.sectionIndex = parcel.readInt();
            boolean[] zArr = new boolean[1];
            parcel.readBooleanArray(zArr);
            this.isSectionStart = zArr[0];
        }

        @Override // com.owen.tvrecyclerview.BaseLayoutManager.ItemEntry, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.colSpan);
            parcel.writeInt(this.rowSpan);
            parcel.writeInt(this.scale);
            parcel.writeInt(this.sectionIndex);
            parcel.writeBooleanArray(new boolean[]{this.isSectionStart});
        }

        public int getRowSpan() {
            return this.rowSpan * this.scale;
        }

        public int getColSpan() {
            return this.colSpan * this.scale;
        }
    }
}
