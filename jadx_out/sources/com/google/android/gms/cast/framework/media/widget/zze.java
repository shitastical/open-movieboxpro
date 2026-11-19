package com.google.android.gms.cast.framework.media.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.AbsSeekBar;
import android.widget.SeekBar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzdw;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes4.dex */
public final class zze {
    private static final zzdw zzbf = new zzdw("WidgetUtil");

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static Bitmap zza(Context context, Bitmap bitmap, float f, float f2) {
        zzdw zzdwVar = zzbf;
        zzdwVar.d("Begin blurring bitmap %s, original width = %d, original height = %d.", bitmap, Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()));
        int iRound = Math.round(bitmap.getWidth() * 0.25f);
        int iRound2 = Math.round(bitmap.getHeight() * 0.25f);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, iRound, iRound2, false);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iRound, iRound2, bitmapCreateScaledBitmap.getConfig());
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, allocationCreateFromBitmap.getElement());
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.setRadius(7.5f);
        scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        allocationCreateTyped.copyTo(bitmapCreateBitmap);
        renderScriptCreate.destroy();
        zzdwVar.d("End blurring bitmap %s, original width = %d, original height = %d.", bitmapCreateScaledBitmap, Integer.valueOf(iRound), Integer.valueOf(iRound2));
        return bitmapCreateBitmap;
    }

    public static Drawable zza(Context context, int i, int i2) {
        return zza(context, i, i2, R.attr.colorForeground, 0);
    }

    public static Drawable zzb(Context context, int i, int i2) {
        return zza(context, i, i2, 0, R.color.white);
    }

    private static Drawable zza(Context context, int i, int i2, int i3, int i4) {
        int color;
        ColorStateList colorStateList;
        Drawable drawableWrap = DrawableCompat.wrap(context.getResources().getDrawable(i2).mutate());
        DrawableCompat.setTintMode(drawableWrap, PorterDuff.Mode.SRC_IN);
        if (i != 0) {
            colorStateList = ContextCompat.getColorStateList(context, i);
        } else {
            if (i3 != 0) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i3});
                color = typedArrayObtainStyledAttributes.getColor(0, 0);
                typedArrayObtainStyledAttributes.recycle();
            } else {
                color = ContextCompat.getColor(context, i4);
            }
            colorStateList = new ColorStateList(new int[][]{new int[]{R.attr.state_enabled}, new int[]{-16842910}}, new int[]{color, ColorUtils.setAlphaComponent(color, 128)});
        }
        DrawableCompat.setTintList(drawableWrap, colorStateList);
        return drawableWrap;
    }

    public static Drawable zza(SeekBar seekBar) {
        if (PlatformVersion.isAtLeastJellyBean()) {
            return seekBar.getThumb();
        }
        try {
            Field fieldZzej = zzej();
            if (fieldZzej != null) {
                return (Drawable) fieldZzej.get(seekBar);
            }
            return null;
        } catch (Exception unused) {
            zzbf.w("Failed to get the thumb of the SeekBar. The SeekBar might look incorrect", new Object[0]);
            return null;
        }
    }

    public static boolean zzei() {
        return PlatformVersion.isAtLeastJellyBean() || zzej() != null;
    }

    private static Field zzej() {
        try {
            Field declaredField = AbsSeekBar.class.getDeclaredField("mThumb");
            if (declaredField.getType() != Drawable.class) {
                return null;
            }
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Exception unused) {
            zzbf.w("Failed to get the thumb field of the SeekBar.", new Object[0]);
            return null;
        }
    }
}
