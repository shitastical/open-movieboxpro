package com.owen.tab;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
class ValueAnimatorCompat {
    private final Impl mImpl;

    public interface AnimatorListener {
        void onAnimationCancel(ValueAnimatorCompat valueAnimatorCompat);

        void onAnimationEnd(ValueAnimatorCompat valueAnimatorCompat);

        void onAnimationStart(ValueAnimatorCompat valueAnimatorCompat);
    }

    public static class AnimatorListenerAdapter implements AnimatorListener {
        @Override // com.owen.tab.ValueAnimatorCompat.AnimatorListener
        public void onAnimationCancel(ValueAnimatorCompat valueAnimatorCompat) {
        }

        @Override // com.owen.tab.ValueAnimatorCompat.AnimatorListener
        public void onAnimationEnd(ValueAnimatorCompat valueAnimatorCompat) {
        }

        @Override // com.owen.tab.ValueAnimatorCompat.AnimatorListener
        public void onAnimationStart(ValueAnimatorCompat valueAnimatorCompat) {
        }
    }

    public interface AnimatorUpdateListener {
        void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat);
    }

    public interface Creator {
        ValueAnimatorCompat createAnimator();
    }

    public static abstract class Impl {

        public interface AnimatorListenerProxy {
            void onAnimationCancel();

            void onAnimationEnd();

            void onAnimationStart();
        }

        public interface AnimatorUpdateListenerProxy {
            void onAnimationUpdate();
        }

        abstract void cancel();

        abstract void end();

        abstract float getAnimatedFloatValue();

        abstract float getAnimatedFraction();

        abstract int getAnimatedIntValue();

        abstract long getDuration();

        abstract boolean isRunning();

        abstract void setDuration(long j);

        abstract void setFloatValues(float f, float f2);

        abstract void setIntValues(int i, int i2);

        abstract void setInterpolator(Interpolator interpolator);

        abstract void setListener(AnimatorListenerProxy animatorListenerProxy);

        abstract void setStartDelay(long j);

        abstract void setUpdateListener(AnimatorUpdateListenerProxy animatorUpdateListenerProxy);

        abstract void start();
    }

    ValueAnimatorCompat(Impl impl) {
        this.mImpl = impl;
    }

    public void start() {
        this.mImpl.start();
    }

    public boolean isRunning() {
        return this.mImpl.isRunning();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mImpl.setInterpolator(interpolator);
    }

    public void setUpdateListener(final AnimatorUpdateListener animatorUpdateListener) {
        if (animatorUpdateListener != null) {
            this.mImpl.setUpdateListener(new Impl.AnimatorUpdateListenerProxy() { // from class: com.owen.tab.ValueAnimatorCompat.1
                @Override // com.owen.tab.ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy
                public void onAnimationUpdate() {
                    animatorUpdateListener.onAnimationUpdate(ValueAnimatorCompat.this);
                }
            });
        } else {
            this.mImpl.setUpdateListener(null);
        }
    }

    public void setListener(final AnimatorListener animatorListener) {
        if (animatorListener != null) {
            this.mImpl.setListener(new Impl.AnimatorListenerProxy() { // from class: com.owen.tab.ValueAnimatorCompat.2
                @Override // com.owen.tab.ValueAnimatorCompat.Impl.AnimatorListenerProxy
                public void onAnimationStart() {
                    animatorListener.onAnimationStart(ValueAnimatorCompat.this);
                }

                @Override // com.owen.tab.ValueAnimatorCompat.Impl.AnimatorListenerProxy
                public void onAnimationEnd() {
                    animatorListener.onAnimationEnd(ValueAnimatorCompat.this);
                }

                @Override // com.owen.tab.ValueAnimatorCompat.Impl.AnimatorListenerProxy
                public void onAnimationCancel() {
                    animatorListener.onAnimationCancel(ValueAnimatorCompat.this);
                }
            });
        } else {
            this.mImpl.setListener(null);
        }
    }

    public void setIntValues(int i, int i2) {
        this.mImpl.setIntValues(i, i2);
    }

    public int getAnimatedIntValue() {
        return this.mImpl.getAnimatedIntValue();
    }

    public void setFloatValues(float f, float f2) {
        this.mImpl.setFloatValues(f, f2);
    }

    public float getAnimatedFloatValue() {
        return this.mImpl.getAnimatedFloatValue();
    }

    public void setDuration(long j) {
        this.mImpl.setDuration(j);
    }

    public void cancel() {
        this.mImpl.cancel();
    }

    public float getAnimatedFraction() {
        return this.mImpl.getAnimatedFraction();
    }

    public void end() {
        this.mImpl.end();
    }

    public long getDuration() {
        return this.mImpl.getDuration();
    }

    public void setStartDelay(long j) {
        this.mImpl.setStartDelay(j);
    }
}
