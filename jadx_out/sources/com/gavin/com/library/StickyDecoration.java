package com.gavin.com.library;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gavin.com.library.listener.GroupListener;
import com.gavin.com.library.listener.OnGroupClickListener;

/* JADX INFO: loaded from: classes4.dex */
public class StickyDecoration extends BaseDecoration {
    private GroupListener mGroupListener;
    private int mGroupTextColor;
    private Paint mGroutPaint;
    private int mSideMargin;
    private TextPaint mTextPaint;
    private int mTextSize;

    private StickyDecoration(GroupListener groupListener) {
        this.mGroupTextColor = -1;
        this.mSideMargin = 10;
        this.mTextSize = 50;
        this.mGroupListener = groupListener;
        Paint paint = new Paint();
        this.mGroutPaint = paint;
        paint.setColor(this.mGroupBackground);
        TextPaint textPaint = new TextPaint();
        this.mTextPaint = textPaint;
        textPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setColor(this.mGroupTextColor);
        this.mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x006e  */
    @Override // com.gavin.com.library.BaseDecoration, androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        super.onDrawOver(canvas, recyclerView, state);
        int itemCount = state.getItemCount();
        int childCount = recyclerView.getChildCount();
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            int realPosition = getRealPosition(childAdapterPosition);
            if (isFirstInGroup(realPosition) || isFirstInRecyclerView(realPosition, i2)) {
                int top = !this.mSticky ? childAt.getTop() : Math.max(this.mGroupHeight, childAt.getTop() + recyclerView.getPaddingTop());
                if (!this.mSticky || childAdapterPosition + 1 >= itemCount) {
                    i = top;
                } else {
                    int bottom = childAt.getBottom();
                    if (!isLastLineInGroup(recyclerView, realPosition) || bottom >= top) {
                        i = top;
                    } else {
                        i = bottom;
                    }
                }
                drawDecoration(canvas, realPosition, paddingLeft, width, i);
                if (this.mOnGroupClickListener != null) {
                    this.stickyHeaderPosArray.put(Integer.valueOf(realPosition), new ClickInfo(i));
                }
            } else {
                drawDivide(canvas, recyclerView, childAt, realPosition, paddingLeft, width);
            }
        }
    }

    private void drawDecoration(Canvas canvas, int i, int i2, int i3, int i4) {
        String groupName = getGroupName(getFirstInGroupWithCash(i));
        float f = i3;
        float f2 = i4;
        canvas.drawRect(i2, i4 - this.mGroupHeight, f, f2, this.mGroutPaint);
        if (groupName == null) {
            return;
        }
        Paint.FontMetrics fontMetrics = this.mTextPaint.getFontMetrics();
        float f3 = (f2 - ((this.mGroupHeight - (fontMetrics.bottom - fontMetrics.top)) / 2.0f)) - fontMetrics.bottom;
        int iAbs = Math.abs(this.mSideMargin);
        this.mSideMargin = iAbs;
        canvas.drawText(groupName, i2 + iAbs, f3, this.mTextPaint);
    }

    @Override // com.gavin.com.library.BaseDecoration
    String getGroupName(int i) {
        GroupListener groupListener = this.mGroupListener;
        if (groupListener != null) {
            return groupListener.getGroupName(i);
        }
        return null;
    }

    public static class Builder {
        private StickyDecoration mDecoration;

        private Builder(GroupListener groupListener) {
            this.mDecoration = new StickyDecoration(groupListener);
        }

        public static Builder init(GroupListener groupListener) {
            return new Builder(groupListener);
        }

        public Builder setGroupBackground(int i) {
            this.mDecoration.mGroupBackground = i;
            this.mDecoration.mGroutPaint.setColor(this.mDecoration.mGroupBackground);
            return this;
        }

        public Builder setGroupTextSize(int i) {
            this.mDecoration.mTextSize = i;
            this.mDecoration.mTextPaint.setTextSize(this.mDecoration.mTextSize);
            return this;
        }

        public Builder setGroupHeight(int i) {
            this.mDecoration.mGroupHeight = i;
            return this;
        }

        public Builder setGroupTextColor(int i) {
            this.mDecoration.mGroupTextColor = i;
            this.mDecoration.mTextPaint.setColor(this.mDecoration.mGroupTextColor);
            return this;
        }

        public Builder setTextSideMargin(int i) {
            this.mDecoration.mSideMargin = i;
            return this;
        }

        public Builder setDivideHeight(int i) {
            this.mDecoration.mDivideHeight = i;
            return this;
        }

        public Builder setDivideColor(int i) {
            this.mDecoration.mDivideColor = i;
            this.mDecoration.mDividePaint.setColor(i);
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

        public StickyDecoration build() {
            return this.mDecoration;
        }
    }
}
