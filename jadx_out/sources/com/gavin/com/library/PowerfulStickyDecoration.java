package com.gavin.com.library;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gavin.com.library.cache.CacheUtil;
import com.gavin.com.library.listener.OnGroupClickListener;
import com.gavin.com.library.listener.PowerGroupListener;
import com.gavin.com.library.util.ViewUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class PowerfulStickyDecoration extends BaseDecoration {
    private CacheUtil<Bitmap> mBitmapCache;
    private PowerGroupListener mGroupListener;
    private Paint mGroutPaint;
    private CacheUtil<View> mHeadViewCache;

    private PowerfulStickyDecoration(PowerGroupListener powerGroupListener) {
        this.mBitmapCache = new CacheUtil<>();
        this.mHeadViewCache = new CacheUtil<>();
        this.mGroupListener = powerGroupListener;
        setGroupListener(powerGroupListener);
        this.mGroutPaint = new Paint();
    }

    @Override // com.gavin.com.library.BaseDecoration, androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDrawOver(canvas, recyclerView, state);
        int itemCount = state.getItemCount();
        int childCount = recyclerView.getChildCount();
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            int realPosition = getRealPosition(childAdapterPosition);
            if (isFirstInGroup(realPosition) || isFirstInRecyclerView(realPosition, i)) {
                int bottom = childAt.getBottom();
                int top = !this.mSticky ? childAt.getTop() : Math.max(this.mGroupHeight, childAt.getTop() + recyclerView.getPaddingTop());
                int i2 = (!this.mSticky || childAdapterPosition + 1 >= itemCount || !isLastLineInGroup(recyclerView, realPosition) || bottom >= top) ? top : bottom;
                if (this.mGroupListener.needDrawDecoration(realPosition)) {
                    drawDecoration(canvas, realPosition, paddingLeft, width, i2);
                }
            } else if (this.mGroupListener.needDrawDecoration(realPosition)) {
                drawDivide(canvas, recyclerView, childAt, realPosition, paddingLeft, width);
            }
        }
    }

    private void drawDecoration(Canvas canvas, int i, int i2, int i3, int i4) {
        View groupView;
        Bitmap bitmapCreateBitmap;
        float f = i2;
        canvas.drawRect(f, i4 - this.mGroupHeight, i3, i4, this.mGroutPaint);
        int firstInGroupWithCash = getFirstInGroupWithCash(i);
        if (this.mHeadViewCache.get(firstInGroupWithCash) == null) {
            groupView = getGroupView(firstInGroupWithCash);
            if (groupView == null) {
                return;
            }
            measureAndLayoutView(groupView, i2, i3);
            this.mHeadViewCache.put(firstInGroupWithCash, groupView);
        } else {
            groupView = this.mHeadViewCache.get(firstInGroupWithCash);
        }
        if (this.mBitmapCache.get(firstInGroupWithCash) != null) {
            bitmapCreateBitmap = this.mBitmapCache.get(firstInGroupWithCash);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(groupView.getDrawingCache());
            this.mBitmapCache.put(firstInGroupWithCash, bitmapCreateBitmap);
        }
        canvas.drawBitmap(bitmapCreateBitmap, f, i4 - this.mGroupHeight, (Paint) null);
        if (this.mOnGroupClickListener != null) {
            setClickInfo(groupView, i2, i4, i);
        }
    }

    private void measureAndLayoutView(View view, int i, int i2) {
        view.setDrawingCacheEnabled(true);
        view.setLayoutParams(new ViewGroup.LayoutParams(i2, this.mGroupHeight));
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mGroupHeight, 1073741824));
        view.layout(i, 0 - this.mGroupHeight, i2, 0);
    }

    private void setClickInfo(View view, int i, int i2, int i3) {
        int i4 = i2 - this.mGroupHeight;
        ArrayList arrayList = new ArrayList();
        for (View view2 : ViewUtil.getChildViewWithId(view)) {
            int top = view2.getTop() + i4;
            int bottom = view2.getBottom() + i4;
            arrayList.add(new ClickInfo.DetailInfo(view2.getId(), view2.getLeft() + i, view2.getRight() + i, top, bottom));
        }
        ClickInfo clickInfo = new ClickInfo(i2, arrayList);
        clickInfo.mGroupId = view.getId();
        this.stickyHeaderPosArray.put(Integer.valueOf(i3), clickInfo);
    }

    @Override // com.gavin.com.library.BaseDecoration
    String getGroupName(int i) {
        PowerGroupListener powerGroupListener = this.mGroupListener;
        if (powerGroupListener != null) {
            return powerGroupListener.getGroupName(i);
        }
        return null;
    }

    private View getGroupView(int i) {
        PowerGroupListener powerGroupListener = this.mGroupListener;
        if (powerGroupListener != null) {
            return powerGroupListener.getGroupView(i);
        }
        return null;
    }

    public void setCacheEnable(boolean z) {
        this.mHeadViewCache.isCacheable(z);
        this.mBitmapCache.isCacheable(z);
    }

    public void clearCache() {
        this.mHeadViewCache.clean();
        this.mBitmapCache.clean();
    }

    public void notifyRedraw(RecyclerView recyclerView, View view, int i) {
        view.setDrawingCacheEnabled(false);
        int firstInGroupWithCash = getFirstInGroupWithCash(i);
        this.mBitmapCache.remove(firstInGroupWithCash);
        this.mHeadViewCache.remove(firstInGroupWithCash);
        measureAndLayoutView(view, recyclerView.getPaddingLeft(), recyclerView.getWidth() - recyclerView.getPaddingRight());
        this.mHeadViewCache.put(firstInGroupWithCash, view);
        recyclerView.invalidate();
    }

    public static class Builder {
        PowerfulStickyDecoration mDecoration;

        private Builder(PowerGroupListener powerGroupListener) {
            this.mDecoration = new PowerfulStickyDecoration(powerGroupListener);
        }

        public static Builder init(PowerGroupListener powerGroupListener) {
            return new Builder(powerGroupListener);
        }

        public Builder setGroupHeight(int i) {
            this.mDecoration.mGroupHeight = i;
            return this;
        }

        public Builder setGroupBackground(int i) {
            this.mDecoration.mGroupBackground = i;
            this.mDecoration.mGroutPaint.setColor(this.mDecoration.mGroupBackground);
            return this;
        }

        public Builder setDivideHeight(int i) {
            this.mDecoration.mDivideHeight = i;
            return this;
        }

        public Builder setDivideColor(int i) {
            this.mDecoration.mDivideColor = i;
            this.mDecoration.mDividePaint.setColor(this.mDecoration.mDivideColor);
            return this;
        }

        public Builder setOnClickListener(OnGroupClickListener onGroupClickListener) {
            this.mDecoration.setOnGroupClickListener(onGroupClickListener);
            return this;
        }

        public Builder resetSpan(RecyclerView recyclerView, GridLayoutManager gridLayoutManager) {
            this.mDecoration.resetSpan(recyclerView, gridLayoutManager);
            return this;
        }

        public Builder setCacheEnable(boolean z) {
            this.mDecoration.setCacheEnable(z);
            return this;
        }

        public Builder setHeaderCount(int i) {
            if (i >= 0) {
                this.mDecoration.mHeaderCount = i;
            }
            return this;
        }

        public Builder setSticky(boolean z) {
            this.mDecoration.mSticky = z;
            return this;
        }

        public PowerfulStickyDecoration build() {
            return this.mDecoration;
        }
    }
}
