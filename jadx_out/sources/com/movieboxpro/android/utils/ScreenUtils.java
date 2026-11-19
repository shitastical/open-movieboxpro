package com.movieboxpro.android.utils;

import android.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.media3.common.C;
import com.movieboxpro.android.app.App;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.videolan.libvlc.MediaDiscoverer;

/* JADX INFO: loaded from: classes5.dex */
public class ScreenUtils {
    public static final int DEFAULT_DAY_BRIGHTNESS = 80;
    public static final int DEFAULT_NIGHT_BRIGHTNESS = 10;

    public enum BrightType {
        USER,
        SYSTEM,
        DEFAULT
    }

    public enum EScreenDensity {
        XXHDPI,
        XHDPI,
        HDPI,
        MDPI
    }

    public static int convertFactorToProgress(float factor) {
        return (int) (((factor + 1.0f) / 2.0f) * 255.0f);
    }

    public static float convertProgressToFactor(int progress) {
        return ((progress / 255.0f) * 2.0f) - 1.0f;
    }

    public static EScreenDensity getDisply(Context context) {
        int i = context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        if (i <= 160) {
            return EScreenDensity.MDPI;
        }
        if (i <= 240) {
            return EScreenDensity.HDPI;
        }
        if (i < 400) {
            return EScreenDensity.XHDPI;
        }
        return EScreenDensity.XXHDPI;
    }

    public static int getScreenWidth() {
        return App.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return App.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static float dpToPx(float dp) {
        return dp * App.getContext().getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(float dp) {
        return (int) (dpToPx(dp) + 0.5f);
    }

    public static float pxToDp(float px) {
        return px / App.getContext().getResources().getDisplayMetrics().density;
    }

    public static int pxToDpInt(float px) {
        return (int) (pxToDp(px) + 0.5f);
    }

    public static float pxToSp(float pxValue) {
        return pxValue / App.getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public static float spToPx(float spValue) {
        return spValue * App.getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getActionBarSize(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    private int getStatusBarHeight() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return App.getContext().getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static final boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static final boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static void dimBackground(final float from, final float to, Activity context) {
        final Window window = context.getWindow();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(from, to);
        valueAnimatorOfFloat.setDuration(500L);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.movieboxpro.android.utils.ScreenUtils.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha = ((Float) animation.getAnimatedValue()).floatValue();
                window.setAttributes(attributes);
            }
        });
        valueAnimatorOfFloat.start();
    }

    public static boolean isAutoBrightness(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness_mode") == 1;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void disableAutoBrightness(Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), "screen_brightness_mode", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enableAutoBrightness(Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), "screen_brightness_mode", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getSystemBrightness(Context mContext) {
        try {
            return Settings.System.getInt(mContext.getContentResolver(), "screen_brightness");
        } catch (Exception e) {
            e.printStackTrace();
            return 80;
        }
    }

    public static void setSystemBrightness(int brightness, Context mContext) {
        try {
            Settings.System.putInt(mContext.getContentResolver(), "screen_brightness", brightness);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static float getAutoBrightnessFactor(Context mContext) {
        try {
            return Settings.System.getFloat(mContext.getContentResolver(), "screen_auto_brightness_adj");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -0.5f;
        }
    }

    public static void setAutoBrightnessFactor(float factor, Context mContext) {
        try {
            Settings.System.putFloat(mContext.getContentResolver(), "screen_auto_brightness_adj", factor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setActivityBrightness(Activity mActivity, int brightness) {
        Window window = mActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.screenBrightness = brightness / 255.0f;
        window.setAttributes(attributes);
    }

    public static void resetActivityBrightness(Activity mActivity) {
        Window window = mActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.screenBrightness = -1.0f;
        window.setAttributes(attributes);
    }

    public static int getViewMeasuredHeight(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredHeight();
    }

    public static int getViewMeasuredWidth(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredWidth();
    }

    public static int getTextWidth(String str) {
        Paint paint = new Paint();
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int iCeil = 0;
        for (int i = 0; i < length; i++) {
            iCeil += (int) Math.ceil(fArr[i]);
        }
        return iCeil;
    }

    private static void calculateViewMeasure(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public static int getStatusHeigh(Activity context) {
        Rect rect = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static boolean checkIsEmpty(EditText edit) {
        return edit == null || edit.getText().toString().trim().equals("");
    }

    public static String getEditString(EditText edit) {
        if (edit == null) {
            return null;
        }
        return edit.getText().toString().trim();
    }

    public static boolean IsSameStr(EditText edit1, EditText edit2) {
        return getEditString(edit1).equals(getEditString(edit2));
    }

    public static void setDrawableLeft(View view, int drawableId) {
        try {
            Drawable drawable = view.getContext().getResources().getDrawable(drawableId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (view instanceof Button) {
                ((Button) view).setCompoundDrawables(drawable, null, null, null);
            }
            if (view instanceof TextView) {
                ((TextView) view).setCompoundDrawables(drawable, null, null, null);
            }
            if (view instanceof EditText) {
                ((EditText) view).setCompoundDrawables(drawable, null, null, null);
            }
        } catch (Exception unused) {
        }
    }

    public static void setDrawableLeft(View view, int drawableId, int left, int top) {
        try {
            Drawable drawable = view.getContext().getResources().getDrawable(drawableId);
            drawable.setBounds(left, top, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (view instanceof Button) {
                ((Button) view).setCompoundDrawables(drawable, null, null, null);
            }
            if (view instanceof TextView) {
                ((TextView) view).setCompoundDrawables(drawable, null, null, null);
            }
            if (view instanceof EditText) {
                ((EditText) view).setCompoundDrawables(drawable, null, null, null);
            }
        } catch (Exception unused) {
        }
    }

    public static void setDrawableRight(View view, int drawableId) {
        try {
            Drawable drawable = view.getContext().getResources().getDrawable(drawableId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (view instanceof Button) {
                ((Button) view).setCompoundDrawables(null, null, drawable, null);
            }
            if (view instanceof TextView) {
                ((TextView) view).setCompoundDrawables(null, null, drawable, null);
            }
            if (view instanceof EditText) {
                ((EditText) view).setCompoundDrawables(null, null, drawable, null);
            }
        } catch (Exception unused) {
        }
    }

    public static int StatusBarLightMode(Activity activity) {
        if (MIUISetStatusBarLightMode(activity.getWindow(), true)) {
            return 1;
        }
        if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
            return 2;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return 0;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(9216);
        return 3;
    }

    public static void StatusBarLightMode(Activity activity, int type) {
        if (type == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), true);
        } else if (type == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), true);
        } else if (type == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
        }
    }

    public static void StatusBarDarkMode(Activity activity, int type) {
        if (type == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), false);
        } else if (type == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), false);
        } else if (type == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, dark ? i2 | i : (~i) & i2);
                window.setAttributes(attributes);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        if (window == null) {
            return false;
        }
        Class<?> cls = window.getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Class<?> cls3 = Integer.TYPE;
            Method method = cls.getMethod("setExtraFlags", cls3, cls3);
            if (dark) {
                method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
            } else {
                method.invoke(window, 0, Integer.valueOf(i));
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void setImmersionStateMode(Activity activity) {
        StatusBarLightMode(activity);
        if (Build.VERSION.SDK_INT != 21) {
            activity.getWindow().addFlags(C.BUFFER_FLAG_NOT_DEPENDED_ON);
            return;
        }
        if (Build.VERSION.SDK_INT == 21) {
            Window window = activity.getWindow();
            window.clearFlags(201326592);
            window.getDecorView().setSystemUiVisibility(MediaDiscoverer.Event.Started);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
            window.setNavigationBarColor(0);
        }
    }

    public static ViewGroup.LayoutParams setViewMargin(View view, boolean isDp, int left, int right, int top, int bottom) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        }
        marginLayoutParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(marginLayoutParams);
        return marginLayoutParams;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        return (int) ((spValue * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
