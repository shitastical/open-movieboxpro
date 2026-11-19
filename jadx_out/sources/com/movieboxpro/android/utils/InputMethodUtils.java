package com.movieboxpro.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes5.dex */
public class InputMethodUtils {
    public static void toggleSoftInput(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(0, 2);
    }

    public static boolean showSoftInput(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 2);
    }

    public static boolean showSoftInput(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            return ((InputMethodManager) currentFocus.getContext().getSystemService("input_method")).showSoftInput(currentFocus, 2);
        }
        return false;
    }

    public static boolean hideSoftInput(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean hideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            return ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
        return false;
    }

    public static boolean isActive(Context context) {
        return ((InputMethodManager) context.getSystemService("input_method")).isActive();
    }
}
