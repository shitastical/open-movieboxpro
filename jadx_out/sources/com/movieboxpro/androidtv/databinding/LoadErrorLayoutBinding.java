package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class LoadErrorLayoutBinding extends ViewDataBinding {
    public final TextView tvMsg;
    public final TextView tvRefresh;

    protected LoadErrorLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvMsg, TextView tvRefresh) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvMsg = tvMsg;
        this.tvRefresh = tvRefresh;
    }

    public static LoadErrorLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoadErrorLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LoadErrorLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.load_error_layout, root, attachToRoot, component);
    }

    public static LoadErrorLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoadErrorLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (LoadErrorLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.load_error_layout, null, false, component);
    }

    public static LoadErrorLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoadErrorLayoutBinding bind(View view, Object component) {
        return (LoadErrorLayoutBinding) bind(component, view, R.layout.load_error_layout);
    }
}
