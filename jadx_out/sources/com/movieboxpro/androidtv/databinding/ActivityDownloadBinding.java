package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityDownloadBinding extends ViewDataBinding {
    public final FrameLayout frameLayout;

    protected ActivityDownloadBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout frameLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.frameLayout = frameLayout;
    }

    public static ActivityDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_download, root, attachToRoot, component);
    }

    public static ActivityDownloadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_download, null, false, component);
    }

    public static ActivityDownloadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadBinding bind(View view, Object component) {
        return (ActivityDownloadBinding) bind(component, view, R.layout.activity_download);
    }
}
