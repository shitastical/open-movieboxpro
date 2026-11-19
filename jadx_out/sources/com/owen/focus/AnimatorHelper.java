package com.owen.focus;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class AnimatorHelper {
    private Map<Object, Animator> mCache = new HashMap();
    private AbsFocusBorder mFocusBorder;

    public AnimatorHelper(AbsFocusBorder absFocusBorder) {
        this.mFocusBorder = absFocusBorder;
    }

    private AnimatorSet getAnimatorSet(Object obj) {
        return new AnimatorSet();
    }

    private ObjectAnimator getAnimatorsOfFloat(Object obj) {
        if (!this.mCache.containsKey(obj)) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "dd", 1.0f);
            this.mCache.put(obj, objectAnimatorOfFloat);
            return objectAnimatorOfFloat;
        }
        return (ObjectAnimator) this.mCache.get(obj);
    }

    private ObjectAnimator getAnimatorsOfInt(Object obj) {
        if (!this.mCache.containsKey(obj)) {
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, "dd", 1);
            this.mCache.put(obj, objectAnimatorOfInt);
            return objectAnimatorOfInt;
        }
        return (ObjectAnimator) this.mCache.get(obj);
    }

    public ObjectAnimator ofFloat(Object obj, Object obj2, String str, float... fArr) {
        ObjectAnimator animatorsOfFloat = getAnimatorsOfFloat(obj);
        animatorsOfFloat.setTarget(obj2);
        animatorsOfFloat.setPropertyName(str);
        animatorsOfFloat.setFloatValues(fArr);
        return animatorsOfFloat;
    }

    public ObjectAnimator ofInt(Object obj, Object obj2, String str, int... iArr) {
        ObjectAnimator animatorsOfInt = getAnimatorsOfInt(obj);
        animatorsOfInt.setTarget(obj2);
        animatorsOfInt.setPropertyName(str);
        animatorsOfInt.setIntValues(iArr);
        return animatorsOfInt;
    }

    AnimatorSet getBorderAnimatorWithScale(View view, AbsFocusBorder.Options options, float f, float f2, int i, int i2, long j, long j2) {
        AnimatorSet animatorSet = getAnimatorSet("BorderAnimator2");
        animatorSet.playTogether(ofFloat("mTranslationXAnimator2", this.mFocusBorder, "translationX", f), ofFloat("mTranslationYAnimator2", this.mFocusBorder, "translationY", f2), ofInt("mWidthAnimator2", this.mFocusBorder, "width", i), ofInt("mHeightAnimator2", this.mFocusBorder, "height", i2), ofFloat("mScaleXAnimator2", view, "scaleX", options.scaleX), ofFloat("mScaleYAnimator2", view, "scaleY", options.scaleY));
        animatorSet.setDuration(j);
        animatorSet.setStartDelay(j2);
        return animatorSet;
    }

    AnimatorSet getBorderAnimator(float f, float f2, int i, int i2, long j) {
        AnimatorSet animatorSet = getAnimatorSet("BorderAnimator");
        animatorSet.playTogether(ofFloat("mTranslationXAnimator", this.mFocusBorder, "translationX", f), ofFloat("mTranslationYAnimator", this.mFocusBorder, "translationY", f2), ofInt("mWidthAnimator", this.mFocusBorder, "width", i), ofInt("mHeightAnimator", this.mFocusBorder, "height", i2));
        animatorSet.setDuration(j);
        return animatorSet;
    }

    Animator getShimmerAndTitleAnimator(AbsFocusBorder.Options options, boolean z) {
        AnimatorSet animatorSet = getAnimatorSet("ShimmerAndTitleAnimator");
        if (!TextUtils.isEmpty(this.mFocusBorder.mTitleView.getText())) {
            animatorSet.playTogether(ofFloat("TitleTranslationYAnimator", this.mFocusBorder.mTitleView, "translationY", this.mFocusBorder.mTitleView.getTranslationY(), 0.0f).setDuration(this.mFocusBorder.mBuilder.mTitleAnimDuration / ((long) (z ? 2 : 1))), ofFloat("TitleAlphaAnimator", this.mFocusBorder.mTitleView, "alpha", this.mFocusBorder.mTitleView.getAlpha(), 1.0f).setDuration(this.mFocusBorder.mBuilder.mTitleAnimDuration / ((long) (z ? 2 : 1))));
        }
        if (this.mFocusBorder.mBuilder.mRunShimmerAnim) {
            animatorSet.playTogether(getShimmerAnimator());
        }
        if (!z) {
            if (this.mFocusBorder.mBuilder.mAnimMode == AbsFocusBorder.Mode.TOGETHER) {
                animatorSet.setStartDelay(300L);
            } else if (this.mFocusBorder.mBuilder.mAnimMode == AbsFocusBorder.Mode.NOLL) {
                animatorSet.setStartDelay(200L);
            } else {
                animatorSet.setStartDelay(100L);
            }
        }
        return animatorSet;
    }

    private ObjectAnimator getShimmerAnimator() {
        ObjectAnimator objectAnimatorOfFloat = ofFloat("ShimmerAnimator", this.mFocusBorder, "shimmerTranslate", -1.0f, 1.0f);
        objectAnimatorOfFloat.end();
        objectAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        objectAnimatorOfFloat.setDuration(this.mFocusBorder.mBuilder.mShimmerDuration);
        objectAnimatorOfFloat.setStartDelay(400L);
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.owen.focus.AnimatorHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimatorHelper.this.mFocusBorder.setShimmerAnimating(true);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                AnimatorHelper.this.mFocusBorder.setShimmerAnimating(false);
            }
        });
        return objectAnimatorOfFloat;
    }

    ObjectAnimator getBreathingLampAnimator() {
        ObjectAnimator objectAnimatorOfFloat = ofFloat("BreathingLampAnimator", this.mFocusBorder.getBorderView(), "alpha", 1.0f, 0.22f, 1.0f);
        objectAnimatorOfFloat.setDuration(this.mFocusBorder.mBuilder.mBreathingDuration);
        objectAnimatorOfFloat.setStartDelay(400L);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimatorOfFloat.setRepeatCount(-1);
        return objectAnimatorOfFloat;
    }
}
