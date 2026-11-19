package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentLoginExpireBinding extends ViewDataBinding {
    public final TextView tvClose;

    protected FragmentLoginExpireBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvClose) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvClose = tvClose;
    }

    public static FragmentLoginExpireBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginExpireBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentLoginExpireBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_login_expire, root, attachToRoot, component);
    }

    public static FragmentLoginExpireBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginExpireBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentLoginExpireBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_login_expire, null, false, component);
    }

    public static FragmentLoginExpireBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginExpireBinding bind(View view, Object component) {
        return (FragmentLoginExpireBinding) bind(component, view, R.layout.fragment_login_expire);
    }
}
