package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.view.widget.SquareImageView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentLoginBinding extends ViewDataBinding {
    public final ImageView imageView;
    public final ImageView imageView2;
    public final SquareImageView ivQrCode;
    public final ProgressBar progressBar;
    public final TextView textView;
    public final TextView textView2;
    public final TextView tvTryAgain;
    public final TextView tvVersionName;

    protected FragmentLoginBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView imageView, ImageView imageView2, SquareImageView ivQrCode, ProgressBar progressBar, TextView textView, TextView textView2, TextView tvTryAgain, TextView tvVersionName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView = imageView;
        this.imageView2 = imageView2;
        this.ivQrCode = ivQrCode;
        this.progressBar = progressBar;
        this.textView = textView;
        this.textView2 = textView2;
        this.tvTryAgain = tvTryAgain;
        this.tvVersionName = tvVersionName;
    }

    public static FragmentLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_login, root, attachToRoot, component);
    }

    public static FragmentLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_login, null, false, component);
    }

    public static FragmentLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding bind(View view, Object component) {
        return (FragmentLoginBinding) bind(component, view, R.layout.fragment_login);
    }
}
