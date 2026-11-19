package com.owen.tab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
class ValueAnimatorCompatImplHoneycombMr1 extends ValueAnimatorCompat.Impl {
    final ValueAnimator mValueAnimator = new ValueAnimator();

    ValueAnimatorCompatImplHoneycombMr1() {
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void start() {
        this.mValueAnimator.start();
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public boolean isRunning() {
        return this.mValueAnimator.isRunning();
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setInterpolator(Interpolator interpolator) {
        this.mValueAnimator.setInterpolator(interpolator);
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setUpdateListener(final ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorUpdateListenerProxy) {
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.owen.tab.ValueAnimatorCompatImplHoneycombMr1.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatorUpdateListenerProxy.onAnimationUpdate();
            }
        });
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setListener(final ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy) {
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.owen.tab.ValueAnimatorCompatImplHoneycombMr1.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                animatorListenerProxy.onAnimationStart();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animatorListenerProxy.onAnimationEnd();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                animatorListenerProxy.onAnimationCancel();
            }
        });
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setIntValues(int i, int i2) {
        this.mValueAnimator.setIntValues(i, i2);
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public int getAnimatedIntValue() {
        return ((Integer) this.mValueAnimator.getAnimatedValue()).intValue();
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setFloatValues(float f, float f2) {
        this.mValueAnimator.setFloatValues(f, f2);
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public float getAnimatedFloatValue() {
        return ((Float) this.mValueAnimator.getAnimatedValue()).floatValue();
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void setDuration(long j) {
        this.mValueAnimator.setDuration(j);
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void cancel() {
        this.mValueAnimator.cancel();
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public float getAnimatedFraction() {
        return this.mValueAnimator.getAnimatedFraction();
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public void end() {
        this.mValueAnimator.end();
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    public long getDuration() {
        return this.mValueAnimator.getDuration();
    }

    @Override // com.owen.tab.ValueAnimatorCompat.Impl
    void setStartDelay(long j) {
        this.mValueAnimator.setStartDelay(j);
    }
}
