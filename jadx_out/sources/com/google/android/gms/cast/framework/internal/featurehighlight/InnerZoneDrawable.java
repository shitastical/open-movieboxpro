package com.google.android.gms.cast.framework.internal.featurehighlight;

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
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.internal.cast.zzfl;

/* JADX INFO: loaded from: classes4.dex */
class InnerZoneDrawable extends Drawable {
    private float centerX;
    private float centerY;
    private final Paint zzjn;
    private float zzjo;
    private final Rect zzjx;
    private final Paint zzks;
    private final int zzkt;
    private final int zzku;
    private float zzkv;
    private float zzkw;
    private float zzkx;

    public InnerZoneDrawable(Context context) {
        Paint paint = new Paint();
        this.zzjn = paint;
        Paint paint2 = new Paint();
        this.zzks = paint2;
        this.zzjx = new Rect();
        this.zzkv = 1.0f;
        Resources resources = context.getResources();
        this.zzkt = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zzku = resources.getInteger(R.integer.cast_libraries_material_featurehighlight_pulse_base_alpha);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint.setColor(-1);
        paint2.setColor(-1);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f = this.zzkx;
        if (f > 0.0f) {
            float f2 = this.zzjo * this.zzkw;
            this.zzks.setAlpha((int) (this.zzku * f));
            canvas.drawCircle(this.centerX, this.centerY, f2, this.zzks);
        }
        canvas.drawCircle(this.centerX, this.centerY, this.zzjo * this.zzkv, this.zzjn);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.zzjn.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.zzjn.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setScale(float f) {
        this.zzkv = f;
        invalidateSelf();
    }

    public void setPulseScale(float f) {
        this.zzkw = f;
        invalidateSelf();
    }

    public void setPulseAlpha(float f) {
        this.zzkx = f;
        invalidateSelf();
    }

    public final void zza(Rect rect) {
        this.zzjx.set(rect);
        this.centerX = this.zzjx.exactCenterX();
        this.centerY = this.zzjx.exactCenterY();
        this.zzjo = Math.max(this.zzkt, Math.max(this.zzjx.width() / 2.0f, this.zzjx.height() / 2.0f));
        invalidateSelf();
    }

    public final Animator zzbg() {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("scale", 0.0f), PropertyValuesHolder.ofInt("alpha", 0), PropertyValuesHolder.ofFloat("pulseScale", 0.0f), PropertyValuesHolder.ofFloat("pulseAlpha", 0.0f));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzfl.zzfl());
        return objectAnimatorOfPropertyValuesHolder.setDuration(200L);
    }
}
