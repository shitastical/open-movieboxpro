package com.owen.tvrecyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.owen.tvrecyclerview.utils.Loger;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseLayoutManager extends TwoWayLayoutManager {
    private static final String LOGTAG = "BaseLayoutManager";
    protected final Rect mChildFrame;
    private ItemEntries mItemEntries;
    private ItemEntries mItemEntriesToRestore;
    private Lanes mLanes;
    private Lanes mLanesToRestore;
    protected final Lanes.LaneInfo mTempLaneInfo;
    protected final Rect mTempRect;

    private enum UpdateOp {
        ADD,
        REMOVE,
        UPDATE,
        MOVE
    }

    protected ItemEntry cacheChildFrame(View view, Rect rect) {
        return null;
    }

    protected ItemEntry cacheChildLaneAndSpan(View view, TwoWayLayoutManager.Direction direction) {
        return null;
    }

    public abstract int getLaneCount();

    public abstract void getLaneForPosition(Lanes.LaneInfo laneInfo, int i, TwoWayLayoutManager.Direction direction);

    public int getLaneSpanForChild(View view) {
        return 1;
    }

    public int getLaneSpanForPosition(int i) {
        return 1;
    }

    protected abstract void moveLayoutToPosition(int i, int i2, RecyclerView.Recycler recycler, RecyclerView.State state);

    /* JADX INFO: Access modifiers changed from: protected */
    public static class ItemEntry implements Parcelable {
        public static final Parcelable.Creator<ItemEntry> CREATOR = new Parcelable.Creator<ItemEntry>() { // from class: com.owen.tvrecyclerview.BaseLayoutManager.ItemEntry.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ItemEntry createFromParcel(Parcel parcel) {
                return new ItemEntry(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ItemEntry[] newArray(int i) {
                return new ItemEntry[i];
            }
        };
        public int anchorLane;
        private int[] spanMargins;
        public int startLane;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ItemEntry(int i, int i2) {
            this.startLane = i;
            this.anchorLane = i2;
        }

        public ItemEntry(Parcel parcel) {
            this.startLane = parcel.readInt();
            this.anchorLane = parcel.readInt();
            int i = parcel.readInt();
            if (i > 0) {
                this.spanMargins = new int[i];
                for (int i2 = 0; i2 < i; i2++) {
                    this.spanMargins[i2] = parcel.readInt();
                }
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.startLane);
            parcel.writeInt(this.anchorLane);
            int[] iArr = this.spanMargins;
            int length = iArr != null ? iArr.length : 0;
            parcel.writeInt(length);
            for (int i2 = 0; i2 < length; i2++) {
                parcel.writeInt(this.spanMargins[i2]);
            }
        }

        public void setLane(Lanes.LaneInfo laneInfo) {
            this.startLane = laneInfo.startLane;
            this.anchorLane = laneInfo.anchorLane;
        }

        public void invalidateLane() {
            this.startLane = -1;
            this.anchorLane = -1;
            this.spanMargins = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasSpanMargins() {
            return this.spanMargins != null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getSpanMargin(int i) {
            int[] iArr = this.spanMargins;
            if (iArr == null) {
                return 0;
            }
            return iArr[i];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpanMargin(int i, int i2, int i3) {
            if (this.spanMargins == null) {
                this.spanMargins = new int[i3];
            }
            this.spanMargins[i] = i2;
        }
    }

    public BaseLayoutManager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseLayoutManager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChildFrame = new Rect();
        this.mTempRect = new Rect();
        this.mTempLaneInfo = new Lanes.LaneInfo();
    }

    public BaseLayoutManager(TwoWayLayoutManager.Orientation orientation) {
        super(orientation);
        this.mChildFrame = new Rect();
        this.mTempRect = new Rect();
        this.mTempLaneInfo = new Lanes.LaneInfo();
    }

    protected void pushChildFrame(ItemEntry itemEntry, Rect rect, int i, int i2, TwoWayLayoutManager.Direction direction) {
        boolean z = (direction != TwoWayLayoutManager.Direction.END || itemEntry == null || itemEntry.hasSpanMargins()) ? false : true;
        for (int i3 = i; i3 < i + i2; i3++) {
            int iPushChildFrame = this.mLanes.pushChildFrame(rect, i3, (itemEntry == null || direction == TwoWayLayoutManager.Direction.END) ? 0 : itemEntry.getSpanMargin(i3 - i), direction);
            if (i2 > 1 && z) {
                itemEntry.setSpanMargin(i3 - i, iPushChildFrame, i2);
            }
        }
    }

    private void popChildFrame(ItemEntry itemEntry, Rect rect, int i, int i2, TwoWayLayoutManager.Direction direction) {
        for (int i3 = i; i3 < i + i2; i3++) {
            this.mLanes.popChildFrame(rect, i3, (itemEntry == null || direction == TwoWayLayoutManager.Direction.END) ? 0 : itemEntry.getSpanMargin(i3 - i), direction);
        }
    }

    void getDecoratedChildFrame(View view, Rect rect) {
        rect.left = getDecoratedLeft(view);
        rect.top = getDecoratedTop(view);
        rect.right = getDecoratedRight(view);
        rect.bottom = getDecoratedBottom(view);
    }

    public Lanes getLanes() {
        return this.mLanes;
    }

    protected void setItemEntryForPosition(int i, ItemEntry itemEntry) {
        ItemEntries itemEntries = this.mItemEntries;
        if (itemEntries != null) {
            itemEntries.putItemEntry(i, itemEntry);
        }
    }

    protected ItemEntry getItemEntryForPosition(int i) {
        ItemEntries itemEntries = this.mItemEntries;
        if (itemEntries != null) {
            return itemEntries.getItemEntry(i);
        }
        return null;
    }

    protected void clearItemEntries() {
        ItemEntries itemEntries = this.mItemEntries;
        if (itemEntries != null) {
            itemEntries.clear();
        }
    }

    protected void invalidateItemLanesAfter(int i) {
        ItemEntries itemEntries = this.mItemEntries;
        if (itemEntries != null) {
            itemEntries.invalidateItemLanesAfter(i);
        }
    }

    protected void offsetForAddition(int i, int i2) {
        ItemEntries itemEntries = this.mItemEntries;
        if (itemEntries != null) {
            itemEntries.offsetForAddition(i, i2);
        }
    }

    protected void offsetForRemoval(int i, int i2) {
        ItemEntries itemEntries = this.mItemEntries;
        if (itemEntries != null) {
            itemEntries.offsetForRemoval(i, i2);
        }
    }

    private void requestMoveLayout() {
        if (getPendingScrollPosition() != -1) {
            return;
        }
        int firstVisiblePosition = getFirstVisiblePosition();
        View viewFindViewByPosition = findViewByPosition(firstVisiblePosition);
        setPendingScrollPositionWithOffset(firstVisiblePosition, viewFindViewByPosition != null ? getChildStart(viewFindViewByPosition) : 0);
    }

    private boolean canUseLanes(Lanes lanes) {
        if (lanes == null) {
            return false;
        }
        int laneCount = getLaneCount();
        return lanes.getOrientation() == getOrientation() && lanes.getCount() == laneCount && lanes.getLaneSize() == Lanes.calculateLaneSize(this, laneCount);
    }

    private boolean ensureLayoutState() {
        int laneCount = getLaneCount();
        if (laneCount == 0 || getWidth() == 0 || getHeight() == 0 || canUseLanes(this.mLanes)) {
            return false;
        }
        Lanes lanes = this.mLanes;
        this.mLanes = new Lanes(this, laneCount);
        requestMoveLayout();
        if (this.mItemEntries == null) {
            this.mItemEntries = new ItemEntries();
        }
        if (lanes != null && lanes.getOrientation() == this.mLanes.getOrientation() && lanes.getLaneSize() == this.mLanes.getLaneSize()) {
            invalidateItemLanesAfter(0);
            return true;
        }
        this.mItemEntries.clear();
        return true;
    }

    private void handleUpdate(int i, int i2, UpdateOp updateOp) {
        invalidateItemLanesAfter(i);
        int i3 = AnonymousClass1.$SwitchMap$com$owen$tvrecyclerview$BaseLayoutManager$UpdateOp[updateOp.ordinal()];
        if (i3 == 1) {
            offsetForAddition(i, i2);
        } else if (i3 == 2) {
            offsetForRemoval(i, i2);
        } else if (i3 == 3) {
            offsetForRemoval(i, 1);
            offsetForAddition(i2, 1);
        }
        if (i2 + i > getFirstVisiblePosition() && i <= getLastVisiblePosition()) {
            requestLayout();
        }
    }

    /* JADX INFO: renamed from: com.owen.tvrecyclerview.BaseLayoutManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$owen$tvrecyclerview$BaseLayoutManager$UpdateOp;

        static {
            int[] iArr = new int[UpdateOp.values().length];
            $SwitchMap$com$owen$tvrecyclerview$BaseLayoutManager$UpdateOp = iArr;
            try {
                iArr[UpdateOp.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$owen$tvrecyclerview$BaseLayoutManager$UpdateOp[UpdateOp.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$owen$tvrecyclerview$BaseLayoutManager$UpdateOp[UpdateOp.MOVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void offsetChildrenHorizontal(int i) {
        if (!isVertical()) {
            this.mLanes.offset(i);
        }
        super.offsetChildrenHorizontal(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void offsetChildrenVertical(int i) {
        super.offsetChildrenVertical(i);
        if (isVertical()) {
            this.mLanes.offset(i);
        }
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Lanes lanes = this.mLanesToRestore;
        boolean z = lanes != null;
        if (z) {
            this.mLanes = lanes;
            this.mItemEntries = this.mItemEntriesToRestore;
            this.mLanesToRestore = null;
            this.mItemEntriesToRestore = null;
        }
        boolean zEnsureLayoutState = ensureLayoutState();
        if (this.mLanes == null) {
            return;
        }
        int itemCount = state.getItemCount();
        ItemEntries itemEntries = this.mItemEntries;
        if (itemEntries != null) {
            itemEntries.setAdapterSize(itemCount);
        }
        int anchorItemPosition = getAnchorItemPosition(state);
        if (anchorItemPosition > 0 && (zEnsureLayoutState || !z)) {
            moveLayoutToPosition(anchorItemPosition, getPendingScrollOffset(), recycler, state);
        }
        this.mLanes.reset(TwoWayLayoutManager.Direction.START);
        super.onLayoutChildren(recycler, state);
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager
    protected void onLayoutScrapList(RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mLanes.save();
        super.onLayoutScrapList(recycler, state);
        this.mLanes.restore();
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, UpdateOp.ADD);
        super.onItemsAdded(recyclerView, i, i2);
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, UpdateOp.REMOVE);
        super.onItemsRemoved(recyclerView, i, i2);
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, UpdateOp.UPDATE);
        super.onItemsUpdated(recyclerView, i, i2);
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        handleUpdate(i, i2, UpdateOp.MOVE);
        super.onItemsMoved(recyclerView, i, i2, i3);
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        clearItemEntries();
        super.onItemsChanged(recyclerView);
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        LanedSavedState lanedSavedState = new LanedSavedState(super.onSaveInstanceState());
        Lanes lanes = this.mLanes;
        int count = lanes != null ? lanes.getCount() : 0;
        lanedSavedState.lanes = new Rect[count];
        for (int i = 0; i < count; i++) {
            Rect rect = new Rect();
            this.mLanes.getLane(i, rect);
            lanedSavedState.lanes[i] = rect;
        }
        lanedSavedState.orientation = getOrientation();
        Lanes lanes2 = this.mLanes;
        lanedSavedState.laneSize = lanes2 != null ? lanes2.getLaneSize() : 0.0f;
        lanedSavedState.itemEntries = this.mItemEntries;
        return lanedSavedState;
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        LanedSavedState lanedSavedState = (LanedSavedState) parcelable;
        if (lanedSavedState.lanes != null && lanedSavedState.laneSize > 0.0f) {
            this.mLanesToRestore = new Lanes(this, lanedSavedState.orientation, lanedSavedState.lanes, lanedSavedState.laneSize);
            this.mItemEntriesToRestore = lanedSavedState.itemEntries;
        }
        super.onRestoreInstanceState(lanedSavedState.getSuperState());
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager
    protected boolean canAddMoreViews(TwoWayLayoutManager.Direction direction, int i) {
        if (direction == TwoWayLayoutManager.Direction.START) {
            int paddingTop = isVertical() ? getPaddingTop() : getPaddingLeft();
            return this.mLanes.getInnerStart() + (paddingTop > 0 ? paddingTop : 20) > i;
        }
        int paddingBottom = isVertical() ? getPaddingBottom() : getPaddingRight();
        return this.mLanes.getInnerEnd() - (paddingBottom > 0 ? paddingBottom : 20) < i;
    }

    private int getWidthUsed(View view) {
        if (!isVertical()) {
            return 0;
        }
        return ((getWidth() - getPaddingLeft()) - getPaddingRight()) - ((int) (getLanes().getLaneSize() * getLaneSpanForChild(view)));
    }

    private int getHeightUsed(View view) {
        if (isVertical()) {
            return 0;
        }
        return ((getHeight() - getPaddingTop()) - getPaddingBottom()) - ((int) (getLanes().getLaneSize() * getLaneSpanForChild(view)));
    }

    protected void measureChildWithMargins(View view) {
        measureChildWithMargins(view, getWidthUsed(view), getHeightUsed(view));
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager
    protected void measureChild(View view, TwoWayLayoutManager.Direction direction) {
        cacheChildLaneAndSpan(view, direction);
        measureChildWithMargins(view);
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager
    protected void layoutChild(View view, TwoWayLayoutManager.Direction direction) {
        int position = getPosition(view);
        getLaneForChild(this.mTempLaneInfo, view, direction);
        this.mLanes.getChildFrame(this.mChildFrame, getDecoratedMeasuredWidth(view), getDecoratedMeasuredHeight(view), this.mTempLaneInfo, direction);
        ItemEntry itemEntryCacheChildFrame = cacheChildFrame(view, this.mChildFrame);
        layoutDecorated(view, this.mChildFrame.left, this.mChildFrame.top, this.mChildFrame.right, this.mChildFrame.bottom);
        if (!((RecyclerView.LayoutParams) view.getLayoutParams()).isItemRemoved()) {
            pushChildFrame(itemEntryCacheChildFrame, this.mChildFrame, this.mTempLaneInfo.startLane, getLaneSpanForChild(view), direction);
        }
        Loger.i("child position " + position + " childFrame=" + this.mChildFrame);
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager
    protected void detachChild(View view, TwoWayLayoutManager.Direction direction) {
        int position = getPosition(view);
        getLaneForPosition(this.mTempLaneInfo, position, direction);
        getDecoratedChildFrame(view, this.mChildFrame);
        popChildFrame(getItemEntryForPosition(position), this.mChildFrame, this.mTempLaneInfo.startLane, getLaneSpanForChild(view), direction);
    }

    protected void getLaneForChild(Lanes.LaneInfo laneInfo, View view, TwoWayLayoutManager.Direction direction) {
        getLaneForPosition(laneInfo, getPosition(view), direction);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        if (isVertical()) {
            return layoutParams.width == -1;
        }
        return layoutParams.height == -1;
    }

    @Override // com.owen.tvrecyclerview.TwoWayLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (isVertical()) {
            return new RecyclerView.LayoutParams(-1, -2);
        }
        return new RecyclerView.LayoutParams(-2, -1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        RecyclerView.LayoutParams layoutParams2 = new RecyclerView.LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        if (isVertical()) {
            layoutParams2.width = -1;
            layoutParams2.height = layoutParams.height;
        } else {
            layoutParams2.width = layoutParams.width;
            layoutParams2.height = -1;
        }
        return layoutParams2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new RecyclerView.LayoutParams(context, attributeSet);
    }

    protected static class LanedSavedState extends TwoWayLayoutManager.SavedState {
        public static final Parcelable.Creator<LanedSavedState> CREATOR = new Parcelable.Creator<LanedSavedState>() { // from class: com.owen.tvrecyclerview.BaseLayoutManager.LanedSavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LanedSavedState createFromParcel(Parcel parcel) {
                return new LanedSavedState(parcel, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LanedSavedState[] newArray(int i) {
                return new LanedSavedState[i];
            }
        };
        private ItemEntries itemEntries;
        private float laneSize;
        private Rect[] lanes;
        private TwoWayLayoutManager.Orientation orientation;

        /* synthetic */ LanedSavedState(Parcel parcel, AnonymousClass1 anonymousClass1) {
            this(parcel);
        }

        protected LanedSavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private LanedSavedState(Parcel parcel) {
            super(parcel);
            this.orientation = TwoWayLayoutManager.Orientation.values()[parcel.readInt()];
            this.laneSize = parcel.readFloat();
            int i = parcel.readInt();
            if (i > 0) {
                this.lanes = new Rect[i];
                for (int i2 = 0; i2 < i; i2++) {
                    Rect rect = new Rect();
                    rect.readFromParcel(parcel);
                    this.lanes[i2] = rect;
                }
            }
            int i3 = parcel.readInt();
            if (i3 > 0) {
                this.itemEntries = new ItemEntries();
                for (int i4 = 0; i4 < i3; i4++) {
                    this.itemEntries.restoreItemEntry(i4, (ItemEntry) parcel.readParcelable(getClass().getClassLoader()));
                }
            }
        }

        @Override // com.owen.tvrecyclerview.TwoWayLayoutManager.SavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.orientation.ordinal());
            parcel.writeFloat(this.laneSize);
            Rect[] rectArr = this.lanes;
            int length = rectArr != null ? rectArr.length : 0;
            parcel.writeInt(length);
            for (int i2 = 0; i2 < length; i2++) {
                this.lanes[i2].writeToParcel(parcel, 1);
            }
            ItemEntries itemEntries = this.itemEntries;
            int size = itemEntries != null ? itemEntries.size() : 0;
            parcel.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                parcel.writeParcelable(this.itemEntries.getItemEntry(i3), i);
            }
        }
    }
}
