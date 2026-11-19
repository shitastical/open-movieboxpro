package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.BaseLayoutManager;
import com.owen.tvrecyclerview.Lanes;
import com.owen.tvrecyclerview.R;
import com.owen.tvrecyclerview.TwoWayLayoutManager;

/* JADX INFO: loaded from: classes5.dex */
public class SpannableGridLayoutManager extends GridLayoutManager {
    private static final int DEFAULT_NUM_COLS = 3;
    private static final int DEFAULT_NUM_ROWS = 3;
    public static final String LOGTAG = "SpannableGridLM";
    private boolean mMeasuring;

    public static class SpannableItemEntry extends BaseLayoutManager.ItemEntry {
        public static final Parcelable.Creator<SpannableItemEntry> CREATOR = new Parcelable.Creator<SpannableItemEntry>() { // from class: com.owen.tvrecyclerview.widget.SpannableGridLayoutManager.SpannableItemEntry.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SpannableItemEntry createFromParcel(Parcel parcel) {
                return new SpannableItemEntry(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SpannableItemEntry[] newArray(int i) {
                return new SpannableItemEntry[i];
            }
        };
        private final int colSpan;
        private final int rowSpan;

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

        public SpannableItemEntry(int i, int i2, int i3, int i4) {
            super(i, i2);
            this.colSpan = i3;
            this.rowSpan = i4;
        }

        public SpannableItemEntry(Parcel parcel) {
            super(parcel);
            this.colSpan = parcel.readInt();
            this.rowSpan = parcel.readInt();
        }

        @Override // com.owen.tvrecyclerview.BaseLayoutManager.ItemEntry, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.colSpan);
            parcel.writeInt(this.rowSpan);
        }
    }

    public SpannableGridLayoutManager(Context context) {
        this(context, null);
    }

    public SpannableGridLayoutManager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SpannableGridLayoutManager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 3, 3);
    }

    public SpannableGridLayoutManager(TwoWayLayoutManager.Orientation orientation, int i, int i2) {
        super(orientation, i, i2);
    }

    private static int getLaneSpan(LayoutParams layoutParams, boolean z) {
        return z ? layoutParams.colSpan : layoutParams.rowSpan;
    }

    private static int getLaneSpan(SpannableItemEntry spannableItemEntry, boolean z) {
        return z ? spannableItemEntry.colSpan : spannableItemEntry.rowSpan;
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
        SpannableItemEntry spannableItemEntry = (SpannableItemEntry) getItemEntryForPosition(i);
        if (spannableItemEntry == null) {
            View childAt = getChildAt(i - getFirstVisiblePosition());
            if (childAt != null) {
                return getLaneSpanForChild(childAt);
            }
            throw new IllegalStateException("Could not find span for position " + i);
        }
        return getLaneSpan(spannableItemEntry, isVertical());
    }

    @Override // com.owen.tvrecyclerview.widget.GridLayoutManager, com.owen.tvrecyclerview.BaseLayoutManager
    public void getLaneForPosition(Lanes.LaneInfo laneInfo, int i, TwoWayLayoutManager.Direction direction) {
        SpannableItemEntry spannableItemEntry = (SpannableItemEntry) getItemEntryForPosition(i);
        if (spannableItemEntry != null) {
            laneInfo.set(spannableItemEntry.startLane, spannableItemEntry.anchorLane);
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
        return ((int) getLanes().getLaneSize()) * i;
    }

    private int getChildHeight(int i) {
        return ((int) getLanes().getLaneSize()) * i;
    }

    private int getWidthUsed(View view) {
        return ((getWidth() - getPaddingLeft()) - getPaddingRight()) - getChildWidth(((LayoutParams) view.getLayoutParams()).colSpan);
    }

    private int getHeightUsed(View view) {
        return ((getHeight() - getPaddingTop()) - getPaddingBottom()) - getChildHeight(((LayoutParams) view.getLayoutParams()).rowSpan);
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager
    protected void measureChildWithMargins(View view) {
        this.mMeasuring = true;
        measureChildWithMargins(view, getWidthUsed(view), getHeightUsed(view));
        this.mMeasuring = false;
    }

    @Override // com.owen.tvrecyclerview.widget.GridLayoutManager, com.owen.tvrecyclerview.BaseLayoutManager
    protected void moveLayoutToPosition(int i, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        boolean zIsVertical = isVertical();
        Lanes lanes = getLanes();
        lanes.reset(0);
        for (int i3 = 0; i3 <= i; i3++) {
            SpannableItemEntry spannableItemEntry = (SpannableItemEntry) getItemEntryForPosition(i3);
            if (spannableItemEntry == null) {
                spannableItemEntry = (SpannableItemEntry) cacheChildLaneAndSpan(recycler.getViewForPosition(i3), TwoWayLayoutManager.Direction.END);
            }
            SpannableItemEntry spannableItemEntry2 = spannableItemEntry;
            this.mTempLaneInfo.set(spannableItemEntry2.startLane, spannableItemEntry2.anchorLane);
            if (this.mTempLaneInfo.isUndefined()) {
                lanes.findLane(this.mTempLaneInfo, getLaneSpanForPosition(i3), TwoWayLayoutManager.Direction.END);
                spannableItemEntry2.setLane(this.mTempLaneInfo);
            }
            lanes.getChildFrame(this.mTempRect, getChildWidth(spannableItemEntry2.colSpan), getChildHeight(spannableItemEntry2.rowSpan), this.mTempLaneInfo, TwoWayLayoutManager.Direction.END);
            if (i3 != i) {
                pushChildFrame(spannableItemEntry2, this.mTempRect, spannableItemEntry2.startLane, getLaneSpan(spannableItemEntry2, zIsVertical), TwoWayLayoutManager.Direction.END);
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
        SpannableItemEntry spannableItemEntry = (SpannableItemEntry) getItemEntryForPosition(position);
        if (spannableItemEntry != null) {
            this.mTempLaneInfo.set(spannableItemEntry.startLane, spannableItemEntry.anchorLane);
        }
        if (this.mTempLaneInfo.isUndefined()) {
            getLaneForChild(this.mTempLaneInfo, view, direction);
        }
        if (spannableItemEntry == null) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            SpannableItemEntry spannableItemEntry2 = new SpannableItemEntry(this.mTempLaneInfo.startLane, this.mTempLaneInfo.anchorLane, layoutParams.colSpan, layoutParams.rowSpan);
            setItemEntryForPosition(position, spannableItemEntry2);
            return spannableItemEntry2;
        }
        spannableItemEntry.setLane(this.mTempLaneInfo);
        return spannableItemEntry;
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        super.checkLayoutParams(layoutParams);
        if (layoutParams.width != -1 || layoutParams.height != -1 || !(layoutParams instanceof LayoutParams)) {
            return false;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (isVertical()) {
            return layoutParams2.rowSpan >= 1 && layoutParams2.colSpan >= 1 && layoutParams2.colSpan <= getLaneCount();
        }
        return layoutParams2.colSpan >= 1 && layoutParams2.rowSpan >= 1 && layoutParams2.rowSpan <= getLaneCount();
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2 = new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        layoutParams2.width = -1;
        layoutParams2.height = -1;
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams3 = (LayoutParams) layoutParams;
            if (isVertical()) {
                layoutParams2.colSpan = Math.max(1, Math.min(layoutParams3.colSpan, getLaneCount()));
                layoutParams2.rowSpan = Math.max(1, layoutParams3.rowSpan);
            } else {
                layoutParams2.colSpan = Math.max(1, layoutParams3.colSpan);
                layoutParams2.rowSpan = Math.max(1, Math.min(layoutParams3.rowSpan, getLaneCount()));
            }
        }
        return layoutParams2;
    }

    @Override // com.owen.tvrecyclerview.BaseLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        private static final int DEFAULT_SPAN = 1;
        public int colSpan;
        public int rowSpan;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.rowSpan = 1;
            this.colSpan = 1;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView_SpannableGridViewChild);
            this.colSpan = Math.max(1, typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_SpannableGridViewChild_tv_colSpan, -1));
            this.rowSpan = Math.max(1, typedArrayObtainStyledAttributes.getInt(R.styleable.TvRecyclerView_SpannableGridViewChild_tv_rowSpan, -1));
            typedArrayObtainStyledAttributes.recycle();
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
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.rowSpan = layoutParams2.rowSpan;
                this.colSpan = layoutParams2.colSpan;
            } else {
                this.rowSpan = 1;
                this.colSpan = 1;
            }
        }
    }
}
