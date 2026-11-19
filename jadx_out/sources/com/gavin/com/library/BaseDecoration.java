package com.gavin.com.library;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gavin.com.library.listener.OnGroupClickListener;
import com.gavin.com.library.listener.PowerGroupListener;
import com.kaopiz.kprogresshud.BuildConfig;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseDecoration extends RecyclerView.ItemDecoration {
    private GestureDetector gestureDetector;
    Paint mDividePaint;
    private boolean mDownInHeader;
    private PowerGroupListener mGroupListener;
    int mHeaderCount;
    protected OnGroupClickListener mOnGroupClickListener;
    int mGroupBackground = Color.parseColor("#48BDFF");
    public int mGroupHeight = BuildConfig.VERSION_CODE;
    int mDivideColor = Color.parseColor("#CCCCCC");
    int mDivideHeight = 0;
    private SparseIntArray firstInGroupCash = new SparseIntArray(100);
    protected boolean mSticky = true;
    protected HashMap<Integer, ClickInfo> stickyHeaderPosArray = new HashMap<>();
    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() { // from class: com.gavin.com.library.BaseDecoration.3
        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return BaseDecoration.this.onTouchEvent(motionEvent);
        }
    };

    abstract String getGroupName(int i);

    protected boolean isFirstInRecyclerView(int i, int i2) {
        return i >= 0 && i2 == 0;
    }

    protected boolean isHeader(int i) {
        return i < 0;
    }

    protected void log(String str) {
    }

    public BaseDecoration() {
        Paint paint = new Paint();
        this.mDividePaint = paint;
        paint.setColor(this.mDivideColor);
    }

    protected void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.mOnGroupClickListener = onGroupClickListener;
    }

    protected void setGroupListener(PowerGroupListener powerGroupListener) {
        this.mGroupListener = powerGroupListener;
    }

    public void setGroupHeight(int i) {
        this.mGroupHeight = i;
    }

    protected int getRealPosition(int i) {
        return i - this.mHeaderCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int realPosition = getRealPosition(recyclerView.getChildAdapterPosition(view));
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            if (isHeader(realPosition)) {
                return;
            }
            if (isFirstLineInGroup(realPosition, spanCount)) {
                PowerGroupListener powerGroupListener = this.mGroupListener;
                if (powerGroupListener == null) {
                    rect.top = this.mGroupHeight;
                    return;
                } else if (powerGroupListener.needDrawDecoration(realPosition)) {
                    rect.top = this.mGroupHeight;
                    return;
                } else {
                    rect.top = 0;
                    return;
                }
            }
            PowerGroupListener powerGroupListener2 = this.mGroupListener;
            if (powerGroupListener2 == null) {
                rect.top = this.mGroupHeight;
                return;
            } else if (powerGroupListener2.needDrawDecoration(realPosition)) {
                rect.top = this.mDivideHeight;
                return;
            } else {
                rect.top = 0;
                return;
            }
        }
        if (isHeader(realPosition)) {
            return;
        }
        if (isFirstInGroup(realPosition)) {
            if (this.mGroupListener.needDrawDecoration(realPosition)) {
                rect.top = this.mDivideHeight;
                return;
            } else {
                rect.top = 0;
                return;
            }
        }
        if (this.mGroupListener.needDrawDecoration(realPosition)) {
            rect.top = this.mDivideHeight;
        } else {
            rect.top = 0;
        }
    }

    protected boolean isFirstInGroup(int i) {
        if (i < 0) {
            return false;
        }
        if (i == 0) {
            return true;
        }
        String groupName = i <= 0 ? null : getGroupName(i - 1);
        String groupName2 = getGroupName(i);
        if (groupName2 == null) {
            return false;
        }
        return !TextUtils.equals(groupName, groupName2);
    }

    public boolean isFirstLineInGroup(int i, int i2) {
        if (i < 0) {
            return false;
        }
        return i == 0 || i - getFirstInGroupWithCash(i) < i2;
    }

    public void resetSpan(RecyclerView recyclerView, GridLayoutManager gridLayoutManager) {
        if (recyclerView == null) {
            throw new NullPointerException("recyclerView not allow null");
        }
        if (gridLayoutManager == null) {
            throw new NullPointerException("gridLayoutManager not allow null");
        }
        final int spanCount = gridLayoutManager.getSpanCount();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.gavin.com.library.BaseDecoration.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                String groupName;
                int realPosition = BaseDecoration.this.getRealPosition(i);
                if (realPosition < 0) {
                    return spanCount;
                }
                String groupName2 = BaseDecoration.this.getGroupName(realPosition);
                try {
                    groupName = BaseDecoration.this.getGroupName(realPosition + 1);
                } catch (Exception unused) {
                    groupName = groupName2;
                }
                if (TextUtils.equals(groupName2, groupName)) {
                    return 1;
                }
                int firstInGroupWithCash = BaseDecoration.this.getFirstInGroupWithCash(realPosition);
                int i2 = spanCount;
                return i2 - ((realPosition - firstInGroupWithCash) % i2);
            }
        });
    }

    public void onEventDown(MotionEvent motionEvent) {
        boolean z = false;
        if (motionEvent == null) {
            this.mDownInHeader = false;
            return;
        }
        if (motionEvent.getY() > 0.0f && motionEvent.getY() < this.mGroupHeight) {
            z = true;
        }
        this.mDownInHeader = z;
    }

    public boolean onEventUp(MotionEvent motionEvent) {
        if (!this.mDownInHeader) {
            return false;
        }
        float y = motionEvent.getY();
        if (y <= 0.0f || y >= this.mGroupHeight) {
            return false;
        }
        return onTouchEvent(motionEvent);
    }

    public int getFirstInGroupWithCash(int i) {
        return getFirstInGroup(i);
    }

    private int getFirstInGroup(int i) {
        if (i <= 0) {
            return 0;
        }
        return isFirstInGroup(i) ? i : getFirstInGroup(i - 1);
    }

    protected boolean isLastLineInGroup(RecyclerView recyclerView, int i) {
        int firstInGroupWithCash;
        String groupName;
        if (i < 0) {
            return true;
        }
        String groupName2 = getGroupName(i);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            firstInGroupWithCash = spanCount - ((i - getFirstInGroupWithCash(i)) % spanCount);
        } else {
            firstInGroupWithCash = 1;
        }
        try {
            groupName = getGroupName(i + firstInGroupWithCash);
        } catch (Exception unused) {
            groupName = groupName2;
        }
        if (groupName == null) {
            return true;
        }
        return !TextUtils.equals(groupName2, groupName);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDrawOver(canvas, recyclerView, state);
        if (this.gestureDetector == null) {
            this.gestureDetector = new GestureDetector(recyclerView.getContext(), this.gestureListener);
            recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.gavin.com.library.BaseDecoration.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return BaseDecoration.this.gestureDetector.onTouchEvent(motionEvent);
                }
            });
        }
        this.stickyHeaderPosArray.clear();
    }

    private void onGroupClick(int i, int i2) {
        OnGroupClickListener onGroupClickListener = this.mOnGroupClickListener;
        if (onGroupClickListener != null) {
            onGroupClickListener.onClick(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (Map.Entry<Integer, ClickInfo> entry : this.stickyHeaderPosArray.entrySet()) {
            ClickInfo clickInfo = this.stickyHeaderPosArray.get(entry.getKey());
            float y = motionEvent.getY();
            float x = motionEvent.getX();
            if (clickInfo.mBottom - this.mGroupHeight <= y && y <= clickInfo.mBottom) {
                if (clickInfo.mDetailInfoList == null || clickInfo.mDetailInfoList.size() == 0) {
                    onGroupClick(entry.getKey().intValue(), clickInfo.mGroupId);
                    return true;
                }
                for (ClickInfo.DetailInfo detailInfo : clickInfo.mDetailInfoList) {
                    if (detailInfo.top <= y && y <= detailInfo.bottom && detailInfo.left <= x && detailInfo.right >= x) {
                        onGroupClick(entry.getKey().intValue(), detailInfo.id);
                        return true;
                    }
                }
                onGroupClick(entry.getKey().intValue(), clickInfo.mGroupId);
                return true;
            }
        }
        return false;
    }

    protected void drawDivide(Canvas canvas, RecyclerView recyclerView, View view, int i, int i2, int i3) {
        if (this.mDivideHeight == 0 || isHeader(i)) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if (isFirstLineInGroup(i, ((GridLayoutManager) layoutManager).getSpanCount())) {
                return;
            }
            float top = view.getTop() + recyclerView.getPaddingTop();
            if (top >= this.mGroupHeight) {
                canvas.drawRect(i2, top - this.mDivideHeight, i3, top, this.mDividePaint);
                return;
            }
            return;
        }
        float top2 = view.getTop();
        if (top2 >= this.mGroupHeight) {
            canvas.drawRect(i2, top2 - this.mDivideHeight, i3, top2, this.mDividePaint);
        }
    }
}
