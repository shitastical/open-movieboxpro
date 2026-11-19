package com.movieboxpro.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.movieboxpro.android.app.App;

/* JADX INFO: loaded from: classes5.dex */
public class DensityUtils {
    private static final int KEY_OFFSET = -123;
    private static final String TAG_OFFSET = "TAG_OFFSET";

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(1, dpVal, context.getResources().getDisplayMetrics());
    }

    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(1, dpVal, App.getContext().getResources().getDisplayMetrics());
    }

    public static int px2dp(Context var0, float var1) {
        return (int) ((var1 / var0.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(Context var0, float var1) {
        return (int) ((var1 * var0.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int px2sp(Context var0, float var1) {
        return (int) ((var1 / var0.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static void addMarginTopEqualStatusBarHeight(View view) {
        view.setTag(TAG_OFFSET);
        Object tag = view.getTag(KEY_OFFSET);
        if (tag == null || !((Boolean) tag).booleanValue()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setTag(KEY_OFFSET, true);
        }
    }

    public static void setMarginPx(View view, int left, int top, int right, int bottom) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMargins(left, top, right, bottom);
        view.requestLayout();
    }

    public static int getScreenWidth() {
        return App.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return App.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static void addMarginPx(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin + left, marginLayoutParams.topMargin + top, marginLayoutParams.rightMargin + right, marginLayoutParams.bottomMargin + bottom);
        view.requestLayout();
    }

    public static int getStatusBarHeight() {
        Resources system = Resources.getSystem();
        return system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static WindowManager getWindowManager(Context context) {
        return (WindowManager) context.getSystemService("window");
    }
}
