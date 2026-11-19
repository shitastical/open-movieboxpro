package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.view.widget.SquareImageView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class CloseChildModeLayoutBinding extends ViewDataBinding {
    public final SquareImageView ivQrCode;
    public final ProgressBar progressBar;
    public final TextView tvTryAgain;
    public final TextView tvVersionName;

    protected CloseChildModeLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, SquareImageView ivQrCode, ProgressBar progressBar, TextView tvTryAgain, TextView tvVersionName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivQrCode = ivQrCode;
        this.progressBar = progressBar;
        this.tvTryAgain = tvTryAgain;
        this.tvVersionName = tvVersionName;
    }

    public static CloseChildModeLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CloseChildModeLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (CloseChildModeLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.close_child_mode_layout, root, attachToRoot, component);
    }

    public static CloseChildModeLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CloseChildModeLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (CloseChildModeLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.close_child_mode_layout, null, false, component);
    }

    public static CloseChildModeLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CloseChildModeLayoutBinding bind(View view, Object component) {
        return (CloseChildModeLayoutBinding) bind(component, view, R.layout.close_child_mode_layout);
    }
}
