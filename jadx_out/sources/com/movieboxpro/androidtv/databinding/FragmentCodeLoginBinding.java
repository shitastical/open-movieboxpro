package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentCodeLoginBinding extends ViewDataBinding {
    public final ProgressBar progressBar;
    public final TextView tvCode;
    public final TextView tvTryAgain;

    protected FragmentCodeLoginBinding(Object _bindingComponent, View _root, int _localFieldCount, ProgressBar progressBar, TextView tvCode, TextView tvTryAgain) {
        super(_bindingComponent, _root, _localFieldCount);
        this.progressBar = progressBar;
        this.tvCode = tvCode;
        this.tvTryAgain = tvTryAgain;
    }

    public static FragmentCodeLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCodeLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCodeLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_code_login, root, attachToRoot, component);
    }

    public static FragmentCodeLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCodeLoginBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCodeLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_code_login, null, false, component);
    }

    public static FragmentCodeLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCodeLoginBinding bind(View view, Object component) {
        return (FragmentCodeLoginBinding) bind(component, view, R.layout.fragment_code_login);
    }
}
