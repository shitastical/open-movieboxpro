package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.GestureDetectorCompat;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.internal.cast.zzfc;
import com.google.android.gms.internal.cast.zzfl;
import com.google.android.gms.internal.cast.zzfo;

/* JADX INFO: loaded from: classes4.dex */
public final class zza extends ViewGroup {
    private View targetView;
    private final int[] zzjw;
    private final Rect zzjx;
    private final Rect zzjy;
    private final OuterHighlightDrawable zzjz;
    private final InnerZoneDrawable zzka;
    private zzi zzkb;
    private View zzkc;
    private Animator zzkd;
    private final zzj zzke;
    private final GestureDetectorCompat zzkf;
    private GestureDetectorCompat zzkg;
    private zzh zzkh;
    private boolean zzki;

    public zza(Context context) {
        super(context);
        this.zzjw = new int[2];
        this.zzjx = new Rect();
        this.zzjy = new Rect();
        setId(R.id.cast_featurehighlight_view);
        setWillNotDraw(false);
        InnerZoneDrawable innerZoneDrawable = new InnerZoneDrawable(context);
        this.zzka = innerZoneDrawable;
        innerZoneDrawable.setCallback(this);
        OuterHighlightDrawable outerHighlightDrawable = new OuterHighlightDrawable(context);
        this.zzjz = outerHighlightDrawable;
        outerHighlightDrawable.setCallback(this);
        this.zzke = new zzj(this);
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(context, new zzb(this));
        this.zzkf = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
        setVisibility(8);
    }

    public final void zza(zzi zziVar) {
        this.zzkb = (zzi) zzfo.checkNotNull(zziVar);
        addView(zziVar.asView(), 0);
    }

    public final void zza(View view, View view2, boolean z, zzh zzhVar) {
        this.targetView = (View) zzfo.checkNotNull(view);
        this.zzkc = null;
        this.zzkh = (zzh) zzfo.checkNotNull(zzhVar);
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(getContext(), new zzc(this, view, true, zzhVar));
        this.zzkg = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
        setVisibility(4);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(View.MeasureSpec.getSize(i), i), resolveSize(View.MeasureSpec.getSize(i2), i2));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.targetView;
        if (view == null) {
            throw new IllegalStateException("Target view must be set before layout");
        }
        if (view.getParent() != null) {
            int[] iArr = this.zzjw;
            View view2 = this.targetView;
            getLocationInWindow(iArr);
            int i5 = iArr[0];
            int i6 = iArr[1];
            view2.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i5;
            iArr[1] = iArr[1] - i6;
        }
        Rect rect = this.zzjx;
        int[] iArr2 = this.zzjw;
        int i7 = iArr2[0];
        rect.set(i7, iArr2[1], this.targetView.getWidth() + i7, this.zzjw[1] + this.targetView.getHeight());
        this.zzjy.set(i, i2, i3, i4);
        this.zzjz.setBounds(this.zzjy);
        this.zzka.setBounds(this.zzjy);
        this.zzke.zza(this.zzjx, this.zzjy);
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        canvas.save();
        this.zzjz.draw(canvas);
        this.zzka.draw(canvas);
        View view = this.targetView;
        if (view != null) {
            if (view.getParent() != null) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.targetView.getWidth(), this.targetView.getHeight(), Bitmap.Config.ARGB_8888);
                this.targetView.draw(new Canvas(bitmapCreateBitmap));
                int color = this.zzjz.getColor();
                int iRed = Color.red(color);
                int iGreen = Color.green(color);
                int iBlue = Color.blue(color);
                for (int i = 0; i < bitmapCreateBitmap.getHeight(); i++) {
                    for (int i2 = 0; i2 < bitmapCreateBitmap.getWidth(); i2++) {
                        int pixel = bitmapCreateBitmap.getPixel(i2, i);
                        if (Color.alpha(pixel) != 0) {
                            bitmapCreateBitmap.setPixel(i2, i, Color.argb(Color.alpha(pixel), iRed, iGreen, iBlue));
                        }
                    }
                }
                canvas.drawBitmap(bitmapCreateBitmap, this.zzjx.left, this.zzjx.top, (Paint) null);
            }
            canvas.restore();
            return;
        }
        throw new IllegalStateException("Neither target view nor drawable was set");
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    @Override // android.view.View
    protected final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.zzjz || drawable == this.zzka || drawable == null;
    }

    public final void zza(Runnable runnable) {
        addOnLayoutChangeListener(new zzd(this, null));
    }

    public final void zzbb() {
        if (this.targetView == null) {
            throw new IllegalStateException("Target view must be set before animation");
        }
        setVisibility(0);
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzkb.asView(), "alpha", 0.0f, 1.0f).setDuration(350L);
        duration.setInterpolator(zzfl.zzfk());
        Animator animatorZzc = this.zzjz.zzc(this.zzjx.exactCenterX() - this.zzjz.getCenterX(), this.zzjx.exactCenterY() - this.zzjz.getCenterY());
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzka, PropertyValuesHolder.ofFloat("scale", 0.0f, 1.0f), PropertyValuesHolder.ofInt("alpha", 0, 255));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzfl.zzfk());
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(350L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, animatorZzc, duration2);
        animatorSet.addListener(new zze(this));
        zza(animatorSet);
    }

    public final void zzb(Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzkb.asView(), "alpha", 0.0f).setDuration(200L);
        duration.setInterpolator(zzfl.zzfl());
        float fExactCenterX = this.zzjx.exactCenterX() - this.zzjz.getCenterX();
        float fExactCenterY = this.zzjx.exactCenterY() - this.zzjz.getCenterY();
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzjz, PropertyValuesHolder.ofFloat("scale", 0.0f), PropertyValuesHolder.ofFloat("translationX", 0.0f, fExactCenterX), PropertyValuesHolder.ofFloat("translationY", 0.0f, fExactCenterY), PropertyValuesHolder.ofInt("alpha", 0));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzfl.zzfl());
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(200L);
        Animator animatorZzbg = this.zzka.zzbg();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, duration2, animatorZzbg);
        animatorSet.addListener(new zzg(this, runnable));
        zza(animatorSet);
    }

    public final void zzc(Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzkb.asView(), "alpha", 0.0f).setDuration(200L);
        duration.setInterpolator(zzfl.zzfl());
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzjz, PropertyValuesHolder.ofFloat("scale", 1.125f), PropertyValuesHolder.ofInt("alpha", 0));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzfl.zzfl());
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(200L);
        Animator animatorZzbg = this.zzka.zzbg();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, duration2, animatorZzbg);
        animatorSet.addListener(new zzf(this, runnable));
        zza(animatorSet);
    }

    public final void zzk(int i) {
        this.zzjz.setColor(i);
    }

    final View zzbc() {
        return this.zzkb.asView();
    }

    final OuterHighlightDrawable zzbd() {
        return this.zzjz;
    }

    final InnerZoneDrawable zzbe() {
        return this.zzka;
    }

    private final void zza(Animator animator) {
        Animator animator2 = this.zzkd;
        if (animator2 != null) {
            animator2.cancel();
        }
        this.zzkd = animator;
        animator.start();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.zzki = this.zzjx.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (this.zzki) {
            GestureDetectorCompat gestureDetectorCompat = this.zzkg;
            if (gestureDetectorCompat != null) {
                gestureDetectorCompat.onTouchEvent(motionEvent);
                if (actionMasked == 1) {
                    motionEvent = MotionEvent.obtain(motionEvent);
                    motionEvent.setAction(3);
                }
            }
            if (this.targetView.getParent() != null) {
                this.targetView.onTouchEvent(motionEvent);
            }
        } else {
            this.zzkf.onTouchEvent(motionEvent);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(float f, float f2) {
        return this.zzjy.contains(Math.round(f), Math.round(f2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Animator zzbf() {
        InnerZoneDrawable innerZoneDrawable = this.zzka;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator duration = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", 1.0f, 1.1f).setDuration(500L);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", 1.1f, 1.0f).setDuration(500L);
        ObjectAnimator duration3 = ObjectAnimator.ofPropertyValuesHolder(innerZoneDrawable, PropertyValuesHolder.ofFloat("pulseScale", 1.1f, 2.0f), PropertyValuesHolder.ofFloat("pulseAlpha", 1.0f, 0.0f)).setDuration(500L);
        animatorSet.play(duration);
        animatorSet.play(duration2).with(duration3).after(duration);
        animatorSet.setInterpolator(zzfl.zzfm());
        animatorSet.setStartDelay(500L);
        zzfc.zza(animatorSet, -1, null);
        return animatorSet;
    }
}
