package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DownloadingStatusViewBinding extends ViewDataBinding {
    public final CircleProgressBar circleProgressBar;
    public final ImageView ivWaiting;
    public final ProgressBar loading;

    protected DownloadingStatusViewBinding(Object _bindingComponent, View _root, int _localFieldCount, CircleProgressBar circleProgressBar, ImageView ivWaiting, ProgressBar loading) {
        super(_bindingComponent, _root, _localFieldCount);
        this.circleProgressBar = circleProgressBar;
        this.ivWaiting = ivWaiting;
        this.loading = loading;
    }

    public static DownloadingStatusViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DownloadingStatusViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DownloadingStatusViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.downloading_status_view, root, attachToRoot, component);
    }

    public static DownloadingStatusViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DownloadingStatusViewBinding inflate(LayoutInflater inflater, Object component) {
        return (DownloadingStatusViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.downloading_status_view, null, false, component);
    }

    public static DownloadingStatusViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DownloadingStatusViewBinding bind(View view, Object component) {
        return (DownloadingStatusViewBinding) bind(component, view, R.layout.downloading_status_view);
    }
}
