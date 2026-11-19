package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogDownloadResolutionBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected DialogDownloadResolutionBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static DialogDownloadResolutionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDownloadResolutionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDownloadResolutionBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_download_resolution, root, attachToRoot, component);
    }

    public static DialogDownloadResolutionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDownloadResolutionBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDownloadResolutionBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_download_resolution, null, false, component);
    }

    public static DialogDownloadResolutionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDownloadResolutionBinding bind(View view, Object component) {
        return (DialogDownloadResolutionBinding) bind(component, view, R.layout.dialog_download_resolution);
    }
}
