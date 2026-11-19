package com.owen.tab;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
class ValueAnimatorCompatImplEclairMr1 extends ValueAnimatorCompat.Impl {
    private static final int DEFAULT_DURATION = 200;
    private static final int HANDLER_DELAY = 10;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private float mAnimatedFraction;
    private Interpolator mInterpolator;
    private boolean mIsRunning;
    private ValueAnimatorCompat.Impl.AnimatorListenerProxy mListener;
    private long mStartTime;
    private ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy mUpdateListener;
    private final int[] mIntValues = new int[2];
    private final float[] mFloatValues = new float[2];
    private long mDuration = 200;
    private long mStartDelay = 0;
    private final Runnable mRunnable = new Runnable() { // from class: com.owen.tab.ValueAnimatorCompatImplEclairMr1.1
        @Override // java.lang.Runnable
        public void run() {
            ValueAnimatorCompatImplEclairMr1.this.update();
        }
    };

    ValueAnimatorCompatImplEclairMr1() {
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void start() {
        if (this.mIsRunning) {
            return;
        }
        if (this.mInterpolator == null) {
            this.mInterpolator = new AccelerateDecelerateInterpolator();
        }
        this.mStartTime = SystemClock.uptimeMillis();
        this.mIsRunning = true;
        this.mAnimatedFraction = 0.0f;
        ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy = this.mListener;
        if (animatorListenerProxy != null) {
            animatorListenerProxy.onAnimationStart();
        }
        sHandler.postDelayed(this.mRunnable, this.mStartDelay + 10);
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public boolean isRunning() {
        return this.mIsRunning;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setListener(ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy) {
        this.mListener = animatorListenerProxy;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setUpdateListener(ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorUpdateListenerProxy) {
        this.mUpdateListener = animatorUpdateListenerProxy;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setIntValues(int i, int i2) {
        int[] iArr = this.mIntValues;
        iArr[0] = i;
        iArr[1] = i2;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public int getAnimatedIntValue() {
        int[] iArr = this.mIntValues;
        return AnimationUtils.lerp(iArr[0], iArr[1], getAnimatedFraction());
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setFloatValues(float f, float f2) {
        float[] fArr = this.mFloatValues;
        fArr[0] = f;
        fArr[1] = f2;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public float getAnimatedFloatValue() {
        float[] fArr = this.mFloatValues;
        return AnimationUtils.lerp(fArr[0], fArr[1], getAnimatedFraction());
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setDuration(long j) {
        this.mDuration = j;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void cancel() {
        this.mIsRunning = false;
        sHandler.removeCallbacks(this.mRunnable);
        ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy = this.mListener;
        if (animatorListenerProxy != null) {
            animatorListenerProxy.onAnimationCancel();
            this.mListener.onAnimationEnd();
        }
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public float getAnimatedFraction() {
        return this.mAnimatedFraction;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void end() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            sHandler.removeCallbacks(this.mRunnable);
            this.mAnimatedFraction = 1.0f;
            ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorUpdateListenerProxy = this.mUpdateListener;
            if (animatorUpdateListenerProxy != null) {
                animatorUpdateListenerProxy.onAnimationUpdate();
            }
            ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy = this.mListener;
            if (animatorListenerProxy != null) {
                animatorListenerProxy.onAnimationEnd();
            }
        }
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public long getDuration() {
        return this.mDuration;
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    void setStartDelay(long j) {
        this.mStartDelay = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        if (this.mIsRunning) {
            float fConstrain = MathUtils.constrain((SystemClock.uptimeMillis() - this.mStartTime) / this.mDuration, 0.0f, 1.0f);
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null) {
                fConstrain = interpolator.getInterpolation(fConstrain);
            }
            this.mAnimatedFraction = fConstrain;
            ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorUpdateListenerProxy = this.mUpdateListener;
            if (animatorUpdateListenerProxy != null) {
                animatorUpdateListenerProxy.onAnimationUpdate();
            }
            if (SystemClock.uptimeMillis() >= this.mStartTime + this.mDuration) {
                this.mIsRunning = false;
                ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy = this.mListener;
                if (animatorListenerProxy != null) {
                    animatorListenerProxy.onAnimationEnd();
                }
            }
        }
        if (this.mIsRunning) {
            sHandler.postDelayed(this.mRunnable, 10L);
        }
    }
}
