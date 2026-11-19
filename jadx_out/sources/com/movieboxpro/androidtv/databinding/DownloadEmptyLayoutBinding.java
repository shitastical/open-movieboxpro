package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DownloadEmptyLayoutBinding extends ViewDataBinding {
    protected DownloadEmptyLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public static DownloadEmptyLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DownloadEmptyLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DownloadEmptyLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.download_empty_layout, root, attachToRoot, component);
    }

    public static DownloadEmptyLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DownloadEmptyLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (DownloadEmptyLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.download_empty_layout, null, false, component);
    }

    public static DownloadEmptyLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DownloadEmptyLayoutBinding bind(View view, Object component) {
        return (DownloadEmptyLayoutBinding) bind(component, view, R.layout.download_empty_layout);
    }
}
