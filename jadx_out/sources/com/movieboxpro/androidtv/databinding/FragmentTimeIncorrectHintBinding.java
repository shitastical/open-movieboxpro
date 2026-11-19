package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentTimeIncorrectHintBinding extends ViewDataBinding {
    public final TextView tvClose;

    protected FragmentTimeIncorrectHintBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvClose) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvClose = tvClose;
    }

    public static FragmentTimeIncorrectHintBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTimeIncorrectHintBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentTimeIncorrectHintBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_time_incorrect_hint, root, attachToRoot, component);
    }

    public static FragmentTimeIncorrectHintBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTimeIncorrectHintBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentTimeIncorrectHintBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_time_incorrect_hint, null, false, component);
    }

    public static FragmentTimeIncorrectHintBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTimeIncorrectHintBinding bind(View view, Object component) {
        return (FragmentTimeIncorrectHintBinding) bind(component, view, R.layout.fragment_time_incorrect_hint);
    }
}
