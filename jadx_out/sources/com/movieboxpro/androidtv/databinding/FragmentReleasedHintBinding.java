package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentReleasedHintBinding extends ViewDataBinding {
    public final TextView tvClose;
    public final TextView tvHint;
    public final TextView tvName;

    protected FragmentReleasedHintBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvClose, TextView tvHint, TextView tvName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvClose = tvClose;
        this.tvHint = tvHint;
        this.tvName = tvName;
    }

    public static FragmentReleasedHintBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentReleasedHintBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentReleasedHintBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_released_hint, root, attachToRoot, component);
    }

    public static FragmentReleasedHintBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentReleasedHintBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentReleasedHintBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_released_hint, null, false, component);
    }

    public static FragmentReleasedHintBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentReleasedHintBinding bind(View view, Object component) {
        return (FragmentReleasedHintBinding) bind(component, view, R.layout.fragment_released_hint);
    }
}
