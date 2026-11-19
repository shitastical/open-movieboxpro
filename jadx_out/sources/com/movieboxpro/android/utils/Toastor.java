package com.movieboxpro.android.utils;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes5.dex */
public class Toastor {
    private Context context;
    private Toast mToast;

    public Toastor(Context context) {
        this.context = context.getApplicationContext();
    }

    public Toast getSingletonToast(int resId) {
        Toast toast = this.mToast;
        if (toast == null) {
            this.mToast = Toast.makeText(this.context, resId, 0);
        } else {
            toast.setText(resId);
        }
        return this.mToast;
    }

    public Toast getSingletonToast(String text) {
        Toast toast = this.mToast;
        if (toast == null) {
            this.mToast = Toast.makeText(this.context, text, 0);
        } else {
            toast.setText(text);
        }
        return this.mToast;
    }

    public Toast getSingleLongToast(int resId) {
        Toast toast = this.mToast;
        if (toast == null) {
            this.mToast = Toast.makeText(this.context, resId, 1);
        } else {
            toast.setText(resId);
        }
        return this.mToast;
    }

    public Toast getSingleLongToast(String text) {
        Toast toast = this.mToast;
        if (toast == null) {
            this.mToast = Toast.makeText(this.context, text, 1);
        } else {
            toast.setText(text);
        }
        return this.mToast;
    }

    public Toast getToast(int resId) {
        return Toast.makeText(this.context, resId, 0);
    }

    public Toast getToast(String text) {
        return Toast.makeText(this.context, text, 0);
    }

    public Toast getLongToast(int resId) {
        return Toast.makeText(this.context, resId, 1);
    }

    public Toast getLongToast(String text) {
        return Toast.makeText(this.context, text, 1);
    }

    public void showSingletonToast(int resId) {
        getSingletonToast(resId).show();
    }

    public void showSingletonToast(String text) {
        getSingletonToast(text).show();
    }

    public void showSingleLongToast(int resId) {
        getSingleLongToast(resId).show();
    }

    public void showSingleLongToast(String text) {
        getSingleLongToast(text).show();
    }

    public void showToast(int resId) {
        getToast(resId).show();
    }

    public void showToast(String text) {
        getToast(text).show();
    }

    public void showLongToast(int resId) {
        getLongToast(resId).show();
    }

    public void showLongToast(String text) {
        getLongToast(text).show();
    }
}
