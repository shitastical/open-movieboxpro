package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentLowQualityHintBinding extends ViewDataBinding {
    public final TextView tvClose;
    public final TextView tvContinue;

    protected FragmentLowQualityHintBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvClose, TextView tvContinue) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvClose = tvClose;
        this.tvContinue = tvContinue;
    }

    public static FragmentLowQualityHintBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLowQualityHintBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentLowQualityHintBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_low_quality_hint, root, attachToRoot, component);
    }

    public static FragmentLowQualityHintBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLowQualityHintBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentLowQualityHintBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_low_quality_hint, null, false, component);
    }

    public static FragmentLowQualityHintBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLowQualityHintBinding bind(View view, Object component) {
        return (FragmentLowQualityHintBinding) bind(component, view, R.layout.fragment_low_quality_hint);
    }
}
