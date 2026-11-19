package cn.bingoogolapple.refreshlayout;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes4.dex */
public class BGAStickinessRefreshView extends View {
    private RectF mBottomBound;
    private Point mCenterPoint;
    private int mCurrentBottomHeight;
    private int mCurrentDegree;
    private int mEdge;
    private boolean mIsRefreshing;
    private boolean mIsRotating;
    private int mMaxBottomHeight;
    private Paint mPaint;
    private Path mPath;
    private Drawable mRotateDrawable;
    private Rect mRotateDrawableBound;
    private int mRotateDrawableSize;
    private BGAStickinessRefreshViewHolder mStickinessRefreshViewHolder;
    private RectF mTopBound;
    private int mTopSize;

    public BGAStickinessRefreshView(Context context) {
        this(context, null);
    }

    public BGAStickinessRefreshView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BGAStickinessRefreshView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentBottomHeight = 0;
        this.mIsRotating = false;
        this.mIsRefreshing = false;
        this.mCurrentDegree = 0;
        this.mEdge = 0;
        this.mTopSize = 0;
        initBounds();
        initPaint();
        initSize();
    }

    private void initBounds() {
        this.mTopBound = new RectF();
        this.mBottomBound = new RectF();
        this.mRotateDrawableBound = new Rect();
        this.mCenterPoint = new Point();
    }

    private void initPaint() {
        this.mPaint = new Paint(1);
        this.mPath = new Path();
    }

    private void initSize() {
        this.mEdge = BGARefreshLayout.dp2px(getContext(), 5);
        int iDp2px = BGARefreshLayout.dp2px(getContext(), 30);
        this.mRotateDrawableSize = iDp2px;
        this.mTopSize = (this.mEdge * 2) + iDp2px;
        this.mMaxBottomHeight = (int) (iDp2px * 2.4f);
    }

    public void setStickinessColor(int i) {
        this.mPaint.setColor(getResources().getColor(i));
    }

    public void setRotateImage(int i) {
        this.mRotateDrawable = getResources().getDrawable(i);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mTopSize + getPaddingLeft() + getPaddingRight(), this.mTopSize + getPaddingTop() + getPaddingBottom() + this.mMaxBottomHeight);
        measureDraw();
    }

    private void measureDraw() {
        this.mCenterPoint.x = getMeasuredWidth() / 2;
        this.mCenterPoint.y = getMeasuredHeight() / 2;
        this.mTopBound.left = this.mCenterPoint.x - (this.mTopSize / 2);
        RectF rectF = this.mTopBound;
        rectF.right = rectF.left + this.mTopSize;
        this.mTopBound.bottom = (getMeasuredHeight() - getPaddingBottom()) - this.mCurrentBottomHeight;
        RectF rectF2 = this.mTopBound;
        rectF2.top = rectF2.bottom - this.mTopSize;
        int iMin = (int) (this.mTopSize * Math.min(Math.max(1.0f - ((this.mCurrentBottomHeight * 1.0f) / this.mMaxBottomHeight), 0.2f), 1.0f));
        this.mBottomBound.left = this.mCenterPoint.x - (iMin / 2);
        RectF rectF3 = this.mBottomBound;
        float f = iMin;
        rectF3.right = rectF3.left + f;
        this.mBottomBound.bottom = this.mTopBound.bottom + this.mCurrentBottomHeight;
        RectF rectF4 = this.mBottomBound;
        rectF4.top = rectF4.bottom - f;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.mRotateDrawable == null) {
            return;
        }
        this.mPath.reset();
        this.mTopBound.round(this.mRotateDrawableBound);
        this.mRotateDrawable.setBounds(this.mRotateDrawableBound);
        if (this.mIsRotating) {
            this.mPath.addOval(this.mTopBound, Path.Direction.CW);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.save();
            canvas.rotate(this.mCurrentDegree, this.mRotateDrawable.getBounds().centerX(), this.mRotateDrawable.getBounds().centerY());
            this.mRotateDrawable.draw(canvas);
            canvas.restore();
            return;
        }
        this.mPath.moveTo(this.mTopBound.left, this.mTopBound.top + (this.mTopSize / 2));
        this.mPath.arcTo(this.mTopBound, 180.0f, 180.0f);
        float fPow = this.mTopSize * (((((float) Math.pow(Math.max((this.mCurrentBottomHeight * 1.0f) / this.mMaxBottomHeight, 0.2f), 7.0d)) * 16.0f) + 3.0f) / 32.0f);
        float f = (this.mTopBound.bottom / 2.0f) + (this.mCenterPoint.y / 2);
        this.mPath.cubicTo(this.mTopBound.right - (this.mTopSize / 8), this.mTopBound.bottom, this.mTopBound.right - fPow, f, this.mBottomBound.right, this.mBottomBound.bottom - (this.mBottomBound.height() / 2.0f));
        this.mPath.arcTo(this.mBottomBound, 0.0f, 180.0f);
        this.mPath.cubicTo(this.mTopBound.left + fPow, f, this.mTopBound.left + (this.mTopSize / 8), this.mTopBound.bottom, this.mTopBound.left, this.mTopBound.bottom - (this.mTopSize / 2));
        canvas.drawPath(this.mPath, this.mPaint);
        this.mRotateDrawable.draw(canvas);
    }

    public void setMoveYDistance(int i) {
        int paddingBottom = ((i - this.mTopSize) - getPaddingBottom()) - getPaddingTop();
        if (paddingBottom > 0) {
            this.mCurrentBottomHeight = paddingBottom;
        } else {
            this.mCurrentBottomHeight = 0;
        }
        postInvalidate();
    }

    public boolean canChangeToRefreshing() {
        return ((float) this.mCurrentBottomHeight) >= ((float) this.mMaxBottomHeight) * 0.98f;
    }

    public void startRefreshing() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.mCurrentBottomHeight, 0);
        valueAnimatorOfInt.setDuration(this.mStickinessRefreshViewHolder.getTopAnimDuration());
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.bingoogolapple.refreshlayout.BGAStickinessRefreshView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BGAStickinessRefreshView.this.mCurrentBottomHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                BGAStickinessRefreshView.this.postInvalidate();
            }
        });
        valueAnimatorOfInt.addListener(new Animator.AnimatorListener() { // from class: cn.bingoogolapple.refreshlayout.BGAStickinessRefreshView.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BGAStickinessRefreshView.this.mIsRefreshing = true;
                if (BGAStickinessRefreshView.this.mCurrentBottomHeight != 0) {
                    BGAStickinessRefreshView.this.mStickinessRefreshViewHolder.startChangeWholeHeaderViewPaddingTop(BGAStickinessRefreshView.this.mCurrentBottomHeight);
                } else {
                    BGAStickinessRefreshView.this.mStickinessRefreshViewHolder.startChangeWholeHeaderViewPaddingTop(-(BGAStickinessRefreshView.this.mTopSize + BGAStickinessRefreshView.this.getPaddingTop() + BGAStickinessRefreshView.this.getPaddingBottom()));
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BGAStickinessRefreshView.this.mIsRotating = true;
                BGAStickinessRefreshView.this.startRotating();
            }
        });
        valueAnimatorOfInt.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRotating() {
        ViewCompat.postOnAnimation(this, new Runnable() { // from class: cn.bingoogolapple.refreshlayout.BGAStickinessRefreshView.3
            @Override // java.lang.Runnable
            public void run() {
                BGAStickinessRefreshView.this.mCurrentDegree += 10;
                if (BGAStickinessRefreshView.this.mCurrentDegree > 360) {
                    BGAStickinessRefreshView.this.mCurrentDegree = 0;
                }
                if (BGAStickinessRefreshView.this.mIsRefreshing) {
                    BGAStickinessRefreshView.this.startRotating();
                }
                BGAStickinessRefreshView.this.postInvalidate();
            }
        });
    }

    public void stopRefresh() {
        this.mIsRotating = true;
        this.mIsRefreshing = false;
        postInvalidate();
    }

    public void smoothToIdle() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.mCurrentBottomHeight, 0);
        valueAnimatorOfInt.setDuration(this.mStickinessRefreshViewHolder.getTopAnimDuration());
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.bingoogolapple.refreshlayout.BGAStickinessRefreshView.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BGAStickinessRefreshView.this.mCurrentBottomHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                BGAStickinessRefreshView.this.postInvalidate();
            }
        });
        valueAnimatorOfInt.addListener(new Animator.AnimatorListener() { // from class: cn.bingoogolapple.refreshlayout.BGAStickinessRefreshView.5
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BGAStickinessRefreshView.this.mIsRotating = false;
            }
        });
        valueAnimatorOfInt.start();
    }

    public void setStickinessRefreshViewHolder(BGAStickinessRefreshViewHolder bGAStickinessRefreshViewHolder) {
        this.mStickinessRefreshViewHolder = bGAStickinessRefreshViewHolder;
    }
}
