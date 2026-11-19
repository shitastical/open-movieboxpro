package com.dl7.player.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes4.dex */
public class SoftInputUtils {
    private SoftInputUtils() {
        throw new AssertionError();
    }

    public static void closeSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            Activity activity = (Activity) context;
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
            }
        }
    }

    public static void setEditFocusable(Context context, View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 0);
    }
}
