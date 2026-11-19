package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.R;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzfl;
import com.google.android.gms.internal.cast.zzfn;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes4.dex */
class OuterHighlightDrawable extends Drawable {
    private float centerX;
    private float centerY;
    private final Paint zzjn;
    private float zzjo;
    private float zzkv;
    private final int zzle;
    private final int zzlf;
    private final int zzlg;
    private float zzlh;
    private float zzli;
    private int zzlj;
    private final Rect zzjx = new Rect();
    private final Rect zzky = new Rect();

    public OuterHighlightDrawable(Context context) {
        Paint paint = new Paint();
        this.zzjn = paint;
        this.zzkv = 1.0f;
        this.zzlh = 0.0f;
        this.zzli = 0.0f;
        this.zzlj = IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE;
        if (PlatformVersion.isAtLeastLollipop()) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            setColor(ColorUtils.setAlphaComponent(typedValue.data, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE));
        } else {
            setColor(context.getResources().getColor(com.google.android.gms.cast.framework.R.color.cast_libraries_material_featurehighlight_outer_highlight_default_color));
        }
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Resources resources = context.getResources();
        this.zzle = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_center_threshold);
        this.zzlf = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_center_horizontal_offset);
        this.zzlg = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_outer_padding);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawCircle(this.centerX + this.zzlh, this.centerY + this.zzli, this.zzjo * this.zzkv, this.zzjn);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.zzjn.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.zzjn.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.zzjn.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final int getColor() {
        return this.zzjn.getColor();
    }

    public final void setColor(int i) {
        this.zzjn.setColor(i);
        this.zzlj = this.zzjn.getAlpha();
        invalidateSelf();
    }

    public final void zzb(Rect rect, Rect rect2) {
        float fExactCenterX;
        this.zzjx.set(rect);
        this.zzky.set(rect2);
        float fExactCenterX2 = rect.exactCenterX();
        float fExactCenterY = rect.exactCenterY();
        Rect bounds = getBounds();
        if (Math.min(fExactCenterY - bounds.top, bounds.bottom - fExactCenterY) < this.zzle) {
            this.centerX = fExactCenterX2;
            this.centerY = fExactCenterY;
        } else {
            if (fExactCenterX2 <= bounds.exactCenterX()) {
                fExactCenterX = rect2.exactCenterX() + this.zzlf;
            } else {
                fExactCenterX = rect2.exactCenterX() - this.zzlf;
            }
            this.centerX = fExactCenterX;
            this.centerY = rect2.exactCenterY();
        }
        this.zzjo = this.zzlg + Math.max(zza(this.centerX, this.centerY, rect), zza(this.centerX, this.centerY, rect2));
        invalidateSelf();
    }

    public void setScale(float f) {
        this.zzkv = f;
        invalidateSelf();
    }

    public void setTranslationX(float f) {
        this.zzlh = f;
        invalidateSelf();
    }

    public void setTranslationY(float f) {
        this.zzli = f;
        invalidateSelf();
    }

    public final float getCenterX() {
        return this.centerX;
    }

    public final float getCenterY() {
        return this.centerY;
    }

    public final boolean zzb(float f, float f2) {
        return zzfn.zza(f, f2, this.centerX, this.centerY) < this.zzjo;
    }

    private static float zza(float f, float f2, Rect rect) {
        float f3 = rect.left;
        float f4 = rect.top;
        float f5 = rect.right;
        float f6 = rect.bottom;
        float fZza = zzfn.zza(f, f2, f3, f4);
        float fZza2 = zzfn.zza(f, f2, f5, f4);
        float fZza3 = zzfn.zza(f, f2, f5, f6);
        float fZza4 = zzfn.zza(f, f2, f3, f6);
        if (fZza <= fZza2 || fZza <= fZza3 || fZza <= fZza4) {
            if (fZza2 <= fZza3 || fZza2 <= fZza4) {
                fZza = fZza3 > fZza4 ? fZza3 : fZza4;
            } else {
                fZza = fZza2;
            }
        }
        return (float) Math.ceil(fZza);
    }

    public final Animator zzc(float f, float f2) {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("scale", 0.0f, 1.0f), PropertyValuesHolder.ofFloat("translationX", f, 0.0f), PropertyValuesHolder.ofFloat("translationY", f2, 0.0f), PropertyValuesHolder.ofInt("alpha", 0, this.zzlj));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzfl.zzfk());
        return objectAnimatorOfPropertyValuesHolder.setDuration(350L);
    }
}
