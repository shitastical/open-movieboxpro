package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.appcompat.R;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: loaded from: classes4.dex */
public final class zzar {
    private final Activity zzij;
    private final int zzrl;
    private final int zzrm;
    private final int zzrn;
    private final int zzro;
    private final SeekBar zzrp;
    private final zzas zzrq;

    public zzar(Activity activity, UIMediaController uIMediaController, SeekBar seekBar) {
        this.zzij = activity;
        this.zzrp = seekBar;
        TypedArray typedArrayObtainStyledAttributes = activity.obtainStyledAttributes(new int[]{R.attr.colorControlActivated});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        this.zzro = resourceId;
        typedArrayObtainStyledAttributes.recycle();
        ColorStateList colorStateListZzdd = null;
        TypedArray typedArrayObtainStyledAttributes2 = activity.obtainStyledAttributes(null, com.google.android.gms.cast.framework.R.styleable.CastExpandedController, com.google.android.gms.cast.framework.R.attr.castExpandedControllerStyle, com.google.android.gms.cast.framework.R.style.CastExpandedController);
        int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(com.google.android.gms.cast.framework.R.styleable.CastExpandedController_castSeekBarProgressDrawable, 0);
        this.zzrl = resourceId2;
        int resourceId3 = typedArrayObtainStyledAttributes2.getResourceId(com.google.android.gms.cast.framework.R.styleable.CastExpandedController_castSeekBarThumbDrawable, 0);
        this.zzrm = resourceId3;
        this.zzrn = typedArrayObtainStyledAttributes2.getResourceId(com.google.android.gms.cast.framework.R.styleable.CastExpandedController_castSeekBarProgressAndThumbColor, resourceId);
        typedArrayObtainStyledAttributes2.recycle();
        Drawable drawable = activity.getResources().getDrawable(resourceId2);
        if (drawable != null) {
            if (resourceId2 == com.google.android.gms.cast.framework.R.drawable.cast_expanded_controller_seekbar_track) {
                colorStateListZzdd = zzdd();
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableWrap = DrawableCompat.wrap(layerDrawable.findDrawableByLayerId(android.R.id.progress));
                DrawableCompat.setTintList(drawableWrap, colorStateListZzdd);
                layerDrawable.setDrawableByLayerId(android.R.id.progress, drawableWrap);
                layerDrawable.findDrawableByLayerId(android.R.id.background).setColorFilter(activity.getResources().getColor(com.google.android.gms.cast.framework.R.color.cast_expanded_controller_seek_bar_progress_background_tint_color), PorterDuff.Mode.SRC_IN);
            }
            seekBar.setProgressDrawable(drawable);
        }
        Drawable drawable2 = activity.getResources().getDrawable(resourceId3);
        if (drawable2 != null) {
            if (resourceId3 == com.google.android.gms.cast.framework.R.drawable.cast_expanded_controller_seekbar_thumb) {
                colorStateListZzdd = colorStateListZzdd == null ? zzdd() : colorStateListZzdd;
                drawable2 = DrawableCompat.wrap(drawable2);
                DrawableCompat.setTintList(drawable2, colorStateListZzdd);
            }
            seekBar.setThumb(drawable2);
        }
        if (PlatformVersion.isAtLeastLollipop()) {
            seekBar.setSplitTrack(false);
        }
        RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(com.google.android.gms.cast.framework.R.id.controllers);
        zzas zzasVar = new zzas(activity, seekBar, uIMediaController.zzdh());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(0, com.google.android.gms.cast.framework.R.id.end_text);
        layoutParams.addRule(1, com.google.android.gms.cast.framework.R.id.start_text);
        layoutParams.addRule(6, com.google.android.gms.cast.framework.R.id.seek_bar);
        layoutParams.addRule(7, com.google.android.gms.cast.framework.R.id.seek_bar);
        layoutParams.addRule(5, com.google.android.gms.cast.framework.R.id.seek_bar);
        layoutParams.addRule(8, com.google.android.gms.cast.framework.R.id.seek_bar);
        zzasVar.setLayoutParams(layoutParams);
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            zzasVar.setPaddingRelative(seekBar.getPaddingStart(), seekBar.getPaddingTop(), seekBar.getPaddingEnd(), seekBar.getPaddingBottom());
        } else {
            zzasVar.setPadding(seekBar.getPaddingLeft(), seekBar.getPaddingTop(), seekBar.getPaddingRight(), seekBar.getPaddingBottom());
        }
        zzasVar.setContentDescription(activity.getResources().getString(com.google.android.gms.cast.framework.R.string.cast_seek_bar));
        zzasVar.setBackgroundColor(0);
        relativeLayout.addView(zzasVar);
        this.zzrq = zzasVar;
        uIMediaController.zza(seekBar, 1000L, zzasVar);
    }

    private final ColorStateList zzdd() {
        int color = this.zzij.getResources().getColor(this.zzrn);
        TypedValue typedValue = new TypedValue();
        this.zzij.getResources().getValue(com.google.android.gms.cast.framework.R.dimen.cast_expanded_controller_seekbar_disabled_alpha, typedValue, true);
        return new ColorStateList(new int[][]{new int[]{android.R.attr.state_enabled}, new int[]{-16842910}}, new int[]{color, Color.argb((int) (typedValue.getFloat() * Color.alpha(color)), Color.red(color), Color.green(color), Color.blue(color))});
    }
}
