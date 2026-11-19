package com.owen.focus;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbsFocusBorder extends FrameLayout implements FocusBorder, ViewTreeObserver.OnGlobalFocusChangeListener {
    private static final long DEFAULT_ANIM_DURATION_TIME = 300;
    private static final long DEFAULT_BREATHING_DURATION_TIME = 3000;
    private static final long DEFAULT_SHIMMER_DURATION_TIME = 1000;
    private static final long DEFAULT_TITLE_ANIM_DURATION_TIME = 400;
    private AnimatorHelper mAnimatorHelper;
    private AnimatorSet mAnimatorSet;
    protected Builder mBuilder;
    protected RectF mFrameRectF;
    private boolean mIsVisible;
    private WeakReference<View> mOldFocusView;
    private FocusBorder.OnFocusCallback mOnFocusCallback;
    private Options mOptions;
    protected RectF mPaddingRectF;
    private boolean mReAnim;
    private RecyclerViewScrollListener mRecyclerViewScrollListener;
    private boolean mShimmerAnimating;
    private Matrix mShimmerGradientMatrix;
    private LinearGradient mShimmerLinearGradient;
    private Paint mShimmerPaint;
    private float mShimmerTranslate;
    protected RectF mTempRectF;
    protected TextView mTitleView;
    private WeakReference<RecyclerView> mWeakRecyclerView;

    public enum Mode {
        TOGETHER,
        SEQUENTIALLY,
        NOLL
    }

    public abstract View getBorderView();

    abstract float getRoundRadius();

    abstract List<Animator> getSequentiallyAnimators(float f, float f2, int i, int i2, Options options);

    abstract List<Animator> getTogetherAnimators(float f, float f2, int i, int i2, Options options);

    @Override // com.owen.focus.FocusBorder
    public View getView() {
        return this;
    }

    @Override // android.view.View
    public boolean isInEditMode() {
        return true;
    }

    protected AbsFocusBorder(Context context, Builder builder) {
        super(context);
        this.mFrameRectF = new RectF();
        this.mPaddingRectF = new RectF();
        this.mTempRectF = new RectF();
        this.mShimmerTranslate = 0.0f;
        this.mShimmerAnimating = false;
        this.mReAnim = false;
        this.mIsVisible = false;
        setWillNotDraw(false);
        this.mBuilder = builder;
        setLayerType(1, null);
        setVisibility(0);
        setClipChildren(false);
        setClipToPadding(false);
        this.mShimmerPaint = new Paint();
        this.mShimmerGradientMatrix = new Matrix();
        initTitleView();
        this.mAnimatorHelper = new AnimatorHelper(this);
    }

    private void initTitleView() {
        if (this.mTitleView == null) {
            TextView textView = new TextView(getContext());
            this.mTitleView = textView;
            textView.setSingleLine();
            this.mTitleView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            this.mTitleView.setSelected(true);
            this.mTitleView.setLayerType(1, null);
            this.mTitleView.setTextSize(this.mBuilder.mTitleTextSize);
            this.mTitleView.setTextColor(this.mBuilder.mTitleTextColor);
            this.mTitleView.setBackgroundResource(this.mBuilder.mTitleBackgroundRes);
            this.mTitleView.setText(" ");
            this.mTitleView.setGravity(17);
            if (this.mBuilder.mTitlePaddingRect != null) {
                this.mTitleView.setPadding(this.mBuilder.mTitlePaddingRect.left, this.mBuilder.mTitlePaddingRect.top, this.mBuilder.mTitlePaddingRect.right, this.mBuilder.mTitlePaddingRect.bottom);
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mBuilder.mTitleWidth, -2);
            layoutParams.gravity = 81;
            if (this.mBuilder.mTitleMarginRect != null) {
                layoutParams.bottomMargin += this.mBuilder.mTitleMarginRect.bottom;
                layoutParams.leftMargin += this.mBuilder.mTitleMarginRect.left;
                layoutParams.rightMargin += this.mBuilder.mTitleMarginRect.right;
            }
            addView(this.mTitleView, layoutParams);
        }
    }

    protected void onDrawShimmer(Canvas canvas) {
        if (this.mShimmerAnimating) {
            canvas.save();
            this.mTempRectF.set(this.mFrameRectF);
            this.mTempRectF.intersect(this.mBuilder.mPaddingOffsetRectF);
            this.mShimmerGradientMatrix.setTranslate(this.mTempRectF.width() * this.mShimmerTranslate, this.mTempRectF.height() * this.mShimmerTranslate);
            this.mShimmerLinearGradient.setLocalMatrix(this.mShimmerGradientMatrix);
            canvas.drawRoundRect(this.mTempRectF, getRoundRadius(), getRoundRadius(), this.mShimmerPaint);
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        this.mFrameRectF.set(this.mPaddingRectF.left, this.mPaddingRectF.top, i - this.mPaddingRectF.right, i2 - this.mPaddingRectF.bottom);
        if (this.mTitleView != null) {
            this.mTitleView.setMaxWidth(((int) ((this.mFrameRectF.width() - this.mBuilder.mPaddingOffsetRectF.left) - this.mBuilder.mPaddingOffsetRectF.right)) - (this.mBuilder.mTitleMarginRect != null ? this.mBuilder.mTitleMarginRect.left + this.mBuilder.mTitleMarginRect.right : 0));
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawShimmer(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mBuilder.mTitleMarginBottomAutoAlignBorder) {
            ((FrameLayout.LayoutParams) this.mTitleView.getLayoutParams()).bottomMargin += (int) (this.mPaddingRectF.bottom + this.mBuilder.mPaddingOffsetRectF.bottom);
        }
        ((FrameLayout.LayoutParams) this.mTitleView.getLayoutParams()).leftMargin += (int) (this.mPaddingRectF.left + this.mBuilder.mPaddingOffsetRectF.left);
        ((FrameLayout.LayoutParams) this.mTitleView.getLayoutParams()).rightMargin += (int) (this.mPaddingRectF.right + this.mBuilder.mPaddingOffsetRectF.right);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        unBoundGlobalFocusListener();
        super.onDetachedFromWindow();
    }

    void setShimmerAnimating(boolean z) {
        this.mShimmerAnimating = z;
        if (z) {
            this.mTempRectF.set(this.mFrameRectF);
            this.mTempRectF.left += this.mBuilder.mPaddingOffsetRectF.left;
            this.mTempRectF.top += this.mBuilder.mPaddingOffsetRectF.top;
            this.mTempRectF.right -= this.mBuilder.mPaddingOffsetRectF.right;
            this.mTempRectF.bottom -= this.mBuilder.mPaddingOffsetRectF.bottom;
            LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, this.mTempRectF.width(), this.mTempRectF.height(), new int[]{ViewCompat.MEASURED_SIZE_MASK, 452984831, this.mBuilder.mShimmerColor, 452984831, ViewCompat.MEASURED_SIZE_MASK}, new float[]{0.0f, 0.2f, 0.5f, 0.8f, 1.0f}, Shader.TileMode.CLAMP);
            this.mShimmerLinearGradient = linearGradient;
            this.mShimmerPaint.setShader(linearGradient);
        }
    }

    protected void setShimmerTranslate(float f) {
        if (!this.mBuilder.mRunShimmerAnim || this.mShimmerTranslate == f) {
            return;
        }
        this.mShimmerTranslate = f;
        ViewCompat.postInvalidateOnAnimation(this);
    }

    protected float getShimmerTranslate() {
        return this.mShimmerTranslate;
    }

    protected void setWidth(int i) {
        if (getLayoutParams().width != i) {
            getLayoutParams().width = i;
            requestLayout();
        }
    }

    protected void setHeight(int i) {
        if (getLayoutParams().height != i) {
            getLayoutParams().height = i;
            requestLayout();
        }
    }

    @Override // com.owen.focus.FocusBorder
    public void setVisible(boolean z) {
        setVisible(z, true);
    }

    @Override // com.owen.focus.FocusBorder
    public void setVisible(boolean z, boolean z2) {
        if (this.mIsVisible != z) {
            this.mIsVisible = z;
            AnimatorSet animatorSet = this.mAnimatorSet;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            WeakReference<View> weakReference = this.mOldFocusView;
            boolean z3 = (weakReference == null || weakReference.get() == null) ? false : true;
            if (z2) {
                ViewPropertyAnimator duration = animate().alpha(z ? 1.0f : 0.0f).setDuration(this.mBuilder.mAnimDuration);
                if (!z && z3) {
                    duration.scaleX(1.0f / this.mOldFocusView.get().getScaleX()).scaleY(1.0f / this.mOldFocusView.get().getScaleY());
                } else {
                    setScaleX(1.0f);
                    setScaleY(1.0f);
                }
                duration.start();
            } else {
                setAlpha(z ? 1.0f : 0.0f);
                setScaleX(1.0f);
                setScaleY(1.0f);
            }
            if (z || !z3) {
                return;
            }
            runFocusScaleAnimation(this.mOldFocusView.get(), 1.0f, 1.0f);
            this.mOldFocusView.clear();
            this.mOldFocusView = null;
        }
    }

    @Override // com.owen.focus.FocusBorder
    public boolean isVisible() {
        return this.mIsVisible;
    }

    private void registerScrollListener(RecyclerView recyclerView) {
        WeakReference<RecyclerView> weakReference = this.mWeakRecyclerView;
        if (weakReference == null || weakReference.get() != recyclerView) {
            if (this.mRecyclerViewScrollListener == null) {
                this.mRecyclerViewScrollListener = new RecyclerViewScrollListener(this);
            }
            WeakReference<RecyclerView> weakReference2 = this.mWeakRecyclerView;
            if (weakReference2 != null && weakReference2.get() != null) {
                this.mWeakRecyclerView.get().removeOnScrollListener(this.mRecyclerViewScrollListener);
                this.mWeakRecyclerView.clear();
            }
            recyclerView.removeOnScrollListener(this.mRecyclerViewScrollListener);
            recyclerView.addOnScrollListener(this.mRecyclerViewScrollListener);
            this.mWeakRecyclerView = new WeakReference<>(recyclerView);
        }
    }

    protected Rect findLocationWithView(View view) {
        return findOffsetDescendantRectToMyCoords(view);
    }

    protected Rect findOffsetDescendantRectToMyCoords(View view) {
        ViewGroup viewGroup = (ViewGroup) getParent();
        Rect rect = new Rect();
        this.mReAnim = false;
        if (view == viewGroup) {
            return rect;
        }
        ViewParent parent = view.getParent();
        View view2 = view;
        Point point = null;
        Point point2 = null;
        while ((parent instanceof View) && parent != viewGroup) {
            rect.offset(view2.getLeft() - view2.getScrollX(), view2.getTop() - view2.getScrollY());
            if ((parent instanceof RecyclerView) && point == null) {
                RecyclerView recyclerView = (RecyclerView) parent;
                registerScrollListener(recyclerView);
                Object tag = recyclerView.getTag();
                if (tag instanceof Point) {
                    point = (Point) tag;
                    rect.offset(-point.x, -point.y);
                }
                if (tag == null && recyclerView.getScrollState() != 0 && (this.mRecyclerViewScrollListener.mScrolledX != 0 || this.mRecyclerViewScrollListener.mScrolledY != 0)) {
                    this.mReAnim = true;
                }
            }
            if ((parent instanceof GridLayout) && point2 == null) {
                GridLayout gridLayout = (GridLayout) parent;
                Object tag2 = gridLayout.getTag();
                if (tag2 instanceof Point) {
                    point2 = (Point) tag2;
                    rect.offset(-point2.x, -point2.y);
                    gridLayout.setTag(null);
                }
            }
            view2 = parent;
            parent = view2.getParent();
        }
        if (parent == viewGroup) {
            rect.offset(view2.getLeft() - view2.getScrollX(), view2.getTop() - view2.getScrollY());
        }
        rect.right = rect.left + view.getMeasuredWidth();
        rect.bottom = rect.top + view.getMeasuredHeight();
        return rect;
    }

    public View getOldFocusView() {
        WeakReference<View> weakReference = this.mOldFocusView;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // com.owen.focus.FocusBorder
    public void onFocus(View view, FocusBorder.Options options) {
        View oldFocusView = getOldFocusView();
        if (oldFocusView != null) {
            runFocusScaleAnimation(oldFocusView, 1.0f, 1.0f);
            this.mOldFocusView.clear();
        }
        if (options == null) {
            options = FocusBorder.OptionsFactory.get();
        }
        if (options instanceof Options) {
            Options options2 = (Options) options;
            restoreFocusBorder(oldFocusView, view, options2);
            setVisible(true);
            runFocusAnimation(view, options2);
            this.mOldFocusView = new WeakReference<>(view);
        }
    }

    private void restoreFocusBorder(View view, View view2, Options options) {
        if (view == null) {
            float f = this.mPaddingRectF.left + this.mPaddingRectF.right + this.mBuilder.mPaddingOffsetRectF.left + this.mBuilder.mPaddingOffsetRectF.right;
            float f2 = this.mPaddingRectF.top + this.mPaddingRectF.bottom + this.mBuilder.mPaddingOffsetRectF.top + this.mBuilder.mPaddingOffsetRectF.bottom;
            Rect rectFindLocationWithView = findLocationWithView(view2);
            rectFindLocationWithView.inset((int) ((-f) / 2.0f), (int) ((-f2) / 2.0f));
            setWidth(rectFindLocationWithView.width());
            setHeight(rectFindLocationWithView.height());
            setTranslationX(rectFindLocationWithView.left);
            setTranslationY(rectFindLocationWithView.top);
        }
    }

    @Override // com.owen.focus.FocusBorder
    public void boundGlobalFocusListener(FocusBorder.OnFocusCallback onFocusCallback) {
        this.mOnFocusCallback = onFocusCallback;
        getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    @Override // com.owen.focus.FocusBorder
    public void unBoundGlobalFocusListener() {
        if (this.mOnFocusCallback != null) {
            this.mOnFocusCallback = null;
            getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
    public void onGlobalFocusChanged(View view, View view2) {
        FocusBorder.OnFocusCallback onFocusCallback = this.mOnFocusCallback;
        Options options = onFocusCallback != null ? (Options) onFocusCallback.onFocus(view, view2) : null;
        if (options != null) {
            onFocus(view2, options);
        }
    }

    private void runFocusAnimation(View view, Options options) {
        this.mOptions = options;
        getBorderView().setAlpha(1.0f);
        this.mTitleView.setAlpha(0.0f);
        this.mTitleView.setText(options.title);
        TextView textView = this.mTitleView;
        textView.setTranslationY(textView.getHeight());
        this.mTitleView.bringToFront();
        runBorderAnimation(view, options, false);
    }

    protected void runBorderAnimation(View view, Options options, boolean z) {
        if (view == null) {
            return;
        }
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        view.clearAnimation();
        AnimatorSet animatorSetCreateBorderAnimation = createBorderAnimation(view, options, z);
        this.mAnimatorSet = animatorSetCreateBorderAnimation;
        animatorSetCreateBorderAnimation.start();
    }

    protected void runFocusScaleAnimation(View view, float f, float f2) {
        if (view == null) {
            return;
        }
        view.animate().scaleX(f).scaleY(f2).setDuration(this.mBuilder.mAnimDuration).start();
    }

    protected AnimatorSet createBorderAnimation(View view, Options options, boolean z) {
        ArrayList arrayList;
        ArrayList arrayList2;
        int i;
        int i2;
        int iWidth;
        int iHeight;
        int i3;
        int i4;
        int i5 = (int) (this.mPaddingRectF.left + this.mPaddingRectF.right + this.mBuilder.mPaddingOffsetRectF.left + this.mBuilder.mPaddingOffsetRectF.right);
        int i6 = (int) (this.mPaddingRectF.top + this.mPaddingRectF.bottom + this.mBuilder.mPaddingOffsetRectF.top + this.mBuilder.mPaddingOffsetRectF.bottom);
        int measuredWidth = (int) (view.getMeasuredWidth() * (options.scaleX - 1.0f));
        int measuredHeight = (int) (view.getMeasuredHeight() * (options.scaleY - 1.0f));
        Rect rectFindLocationWithView = findLocationWithView(this);
        Rect rectFindLocationWithView2 = findLocationWithView(view);
        rectFindLocationWithView2.inset((-i5) / 2, (-i6) / 2);
        int iWidth2 = rectFindLocationWithView2.width();
        int iHeight2 = rectFindLocationWithView2.height();
        int i7 = rectFindLocationWithView2.left - rectFindLocationWithView.left;
        int i8 = rectFindLocationWithView2.top - rectFindLocationWithView.top;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        if (this.mBuilder.mAnimMode == Mode.TOGETHER) {
            rectFindLocationWithView2.inset((-measuredWidth) / 2, (-measuredHeight) / 2);
            int iWidth3 = rectFindLocationWithView2.width();
            int iHeight3 = rectFindLocationWithView2.height();
            int i9 = rectFindLocationWithView2.left - rectFindLocationWithView.left;
            int i10 = rectFindLocationWithView2.top - rectFindLocationWithView.top;
            arrayList = arrayList3;
            arrayList.add(this.mAnimatorHelper.getBorderAnimatorWithScale(view, options, i9, i10, iWidth3, iHeight3, this.mBuilder.mAnimDuration, 0L));
            i = iWidth3;
            i2 = iHeight3;
            i7 = i9;
            i8 = i10;
            arrayList2 = arrayList4;
        } else {
            arrayList = arrayList3;
            if (this.mBuilder.mAnimMode == Mode.SEQUENTIALLY) {
                if (!z) {
                    AnimatorSet borderAnimator = this.mAnimatorHelper.getBorderAnimator(i7, i8, iWidth2, iHeight2, this.mBuilder.mAnimDuration / 2);
                    borderAnimator.setInterpolator(new DecelerateInterpolator());
                    arrayList.add(borderAnimator);
                }
                rectFindLocationWithView2.inset((-measuredWidth) / 2, (-measuredHeight) / 2);
                iWidth = rectFindLocationWithView2.width();
                iHeight = rectFindLocationWithView2.height();
                i3 = rectFindLocationWithView2.left - rectFindLocationWithView.left;
                i4 = rectFindLocationWithView2.top - rectFindLocationWithView.top;
                if (!z) {
                    arrayList4.add(this.mAnimatorHelper.getBorderAnimatorWithScale(view, options, i3, i4, iWidth, iHeight, this.mBuilder.mAnimDuration / 2, 200L));
                    arrayList2 = arrayList4;
                } else {
                    arrayList2 = arrayList4;
                    arrayList.add(this.mAnimatorHelper.getBorderAnimatorWithScale(view, options, i3, i4, iWidth, iHeight, this.mBuilder.mAnimDuration / 2, 0L));
                }
            } else {
                arrayList2 = arrayList4;
                if (!z) {
                    setTranslationX(i7);
                    setTranslationY(i8);
                    setWidth(iWidth2);
                    setHeight(iHeight2);
                }
                if (options.isScale()) {
                    rectFindLocationWithView2.inset((-measuredWidth) / 2, (-measuredHeight) / 2);
                    iWidth = rectFindLocationWithView2.width();
                    iHeight = rectFindLocationWithView2.height();
                    i3 = rectFindLocationWithView2.left - rectFindLocationWithView.left;
                    i4 = rectFindLocationWithView2.top - rectFindLocationWithView.top;
                    arrayList.add(this.mAnimatorHelper.getBorderAnimatorWithScale(view, options, i3, i4, iWidth, iHeight, this.mBuilder.mAnimDuration, 0L));
                } else {
                    i = iWidth2;
                    i2 = iHeight2;
                }
            }
            i = iWidth;
            i2 = iHeight;
            i7 = i3;
            i8 = i4;
        }
        float f = i7;
        float f2 = i8;
        ArrayList arrayList5 = arrayList;
        ArrayList arrayList6 = arrayList2;
        List<Animator> togetherAnimators = getTogetherAnimators(f, f2, i, i2, options);
        if (togetherAnimators != null && !togetherAnimators.isEmpty()) {
            arrayList5.addAll(togetherAnimators);
        }
        if (z) {
            arrayList5.add(this.mAnimatorHelper.getShimmerAndTitleAnimator(options, z));
        } else {
            arrayList6.add(this.mAnimatorHelper.getShimmerAndTitleAnimator(options, z));
        }
        List<Animator> sequentiallyAnimators = getSequentiallyAnimators(f, f2, i, i2, options);
        if (sequentiallyAnimators != null && !sequentiallyAnimators.isEmpty()) {
            arrayList6.addAll(sequentiallyAnimators);
        }
        if (this.mBuilder.mRunBreathingAnim) {
            arrayList6.add(this.mAnimatorHelper.getBreathingLampAnimator());
        }
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.setInterpolator(new DecelerateInterpolator(1.0f));
        this.mAnimatorSet.playTogether(arrayList5);
        this.mAnimatorSet.playSequentially(arrayList6);
        this.mAnimatorSet.setStartDelay(50L);
        return this.mAnimatorSet;
    }

    private static class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        private WeakReference<AbsFocusBorder> mFocusBorder;
        private int mScrolledX = 0;
        private int mScrolledY = 0;

        public RecyclerViewScrollListener(AbsFocusBorder absFocusBorder) {
            this.mFocusBorder = new WeakReference<>(absFocusBorder);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (Math.abs(i) == 1) {
                i = 0;
            }
            this.mScrolledX = i;
            if (Math.abs(i2) == 1) {
                i2 = 0;
            }
            this.mScrolledY = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 0) {
                AbsFocusBorder absFocusBorder = this.mFocusBorder.get();
                View oldFocusView = absFocusBorder != null ? absFocusBorder.getOldFocusView() : null;
                if (oldFocusView != null && !(oldFocusView instanceof RecyclerView) && (absFocusBorder.mReAnim || this.mScrolledX != 0 || this.mScrolledY != 0)) {
                    absFocusBorder.runBorderAnimation(oldFocusView, absFocusBorder.mOptions, true);
                }
                this.mScrolledY = 0;
                this.mScrolledX = 0;
            }
        }
    }

    public static class Options extends FocusBorder.Options {
        protected float scaleX = 1.0f;
        protected float scaleY = 1.0f;
        protected String title;

        Options() {
        }

        private static class OptionsHolder {
            private static final Options INSTANCE = new Options();

            private OptionsHolder() {
            }
        }

        public static Options get(float f, float f2) {
            return get(f, f2, null);
        }

        public static Options get(float f, float f2, String str) {
            OptionsHolder.INSTANCE.scaleX = f;
            OptionsHolder.INSTANCE.scaleY = f2;
            OptionsHolder.INSTANCE.title = str;
            return OptionsHolder.INSTANCE;
        }

        public boolean isScale() {
            return (this.scaleX == 1.0f && this.scaleY == 1.0f) ? false : true;
        }
    }

    public static abstract class Builder {
        protected int mTitleBackgroundRes;
        protected boolean mTitleMarginBottomAutoAlignBorder;
        protected Rect mTitleMarginRect;
        protected Rect mTitlePaddingRect;
        protected int mShimmerColor = 1728053247;
        protected boolean mRunShimmerAnim = true;
        protected boolean mRunBreathingAnim = true;
        protected Mode mAnimMode = Mode.TOGETHER;
        protected long mAnimDuration = AbsFocusBorder.DEFAULT_ANIM_DURATION_TIME;
        protected long mShimmerDuration = 1000;
        protected long mBreathingDuration = 3000;
        protected RectF mPaddingOffsetRectF = new RectF();
        protected float mTitleTextSize = 20.0f;
        protected int mTitleTextColor = 1728053247;
        protected int mTitleWidth = -2;
        protected long mTitleAnimDuration = AbsFocusBorder.DEFAULT_TITLE_ANIM_DURATION_TIME;

        public abstract FocusBorder build(Activity activity);

        public abstract FocusBorder build(ViewGroup viewGroup);

        public Builder titlePadding(int i, int i2, int i3, int i4) {
            this.mTitlePaddingRect = new Rect(i, i2, i3, i4);
            return this;
        }

        public Builder titlePadding(int i) {
            return titlePadding(i, i, i, i);
        }

        public Builder titleMargin(int i, int i2, int i3, int i4) {
            this.mTitleMarginRect = new Rect(i, i2, i3, i4);
            return this;
        }

        public Builder titleMargin(int i) {
            return titleMargin(i, i, i, i);
        }

        public Builder titleMarginBottomAutoAlignBorder() {
            this.mTitleMarginBottomAutoAlignBorder = true;
            return this;
        }

        public Builder titleTextSize(float f) {
            this.mTitleTextSize = f;
            return this;
        }

        public Builder titleTextColor(int i) {
            this.mTitleTextColor = i;
            return this;
        }

        public Builder titleTextColor(int i, Context context) {
            if (Build.VERSION.SDK_INT >= 23) {
                shimmerColor(context.getColor(i));
            } else {
                titleTextColor(context.getResources().getColor(i));
            }
            return this;
        }

        public Builder titleWidth(int i) {
            this.mTitleWidth = i;
            return this;
        }

        public Builder titleBackgroundRes(int i) {
            this.mTitleBackgroundRes = i;
            return this;
        }

        public Builder titleAnimDuration(long j) {
            this.mTitleAnimDuration = j;
            return this;
        }

        public Builder breathingDuration(long j) {
            this.mBreathingDuration = j;
            return this;
        }

        public Builder noBreathing() {
            this.mRunBreathingAnim = false;
            return this;
        }

        public Builder shimmerColor(int i) {
            this.mShimmerColor = i;
            return this;
        }

        public Builder shimmerColorRes(int i, Context context) {
            if (Build.VERSION.SDK_INT >= 23) {
                shimmerColor(context.getColor(i));
            } else {
                shimmerColor(context.getResources().getColor(i));
            }
            return this;
        }

        public Builder shimmerDuration(long j) {
            this.mShimmerDuration = j;
            return this;
        }

        public Builder noShimmer() {
            this.mRunShimmerAnim = false;
            return this;
        }

        public Builder animDuration(long j) {
            this.mAnimDuration = j;
            return this;
        }

        public Builder animMode(Mode mode) {
            this.mAnimMode = mode;
            return this;
        }

        public Builder padding(float f) {
            return padding(f, f, f, f);
        }

        public Builder padding(float f, float f2, float f3, float f4) {
            this.mPaddingOffsetRectF.left = f;
            this.mPaddingOffsetRectF.top = f2;
            this.mPaddingOffsetRectF.right = f3;
            this.mPaddingOffsetRectF.bottom = f4;
            return this;
        }

        public FocusBorder build(Fragment fragment) {
            if (fragment.getActivity() != null) {
                return build(fragment.getActivity());
            }
            return build((ViewGroup) fragment.getView());
        }

        public FocusBorder build(androidx.fragment.app.Fragment fragment) {
            if (fragment.getActivity() != null) {
                return build(fragment.getActivity());
            }
            return build((ViewGroup) fragment.getView());
        }
    }
}
