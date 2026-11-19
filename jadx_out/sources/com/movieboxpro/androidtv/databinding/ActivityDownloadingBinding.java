package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityDownloadingBinding extends ViewDataBinding {
    public final ImageView ivStartPause;
    public final LinearLayout llConcurrent;
    public final LinearLayout llDelete;
    public final LinearLayout llDone;
    public final LinearLayout llHint;
    public final LinearLayout llSmartDownload;
    public final LinearLayout llStartPauseAll;
    public final TvRecyclerView recyclerView;
    public final TextView tvConcurrent;
    public final TextView tvDelete;
    public final TextView tvEdit;
    public final TextView tvSmartDownload;
    public final TextView tvStartPauseAll;

    protected ActivityDownloadingBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivStartPause, LinearLayout llConcurrent, LinearLayout llDelete, LinearLayout llDone, LinearLayout llHint, LinearLayout llSmartDownload, LinearLayout llStartPauseAll, TvRecyclerView recyclerView, TextView tvConcurrent, TextView tvDelete, TextView tvEdit, TextView tvSmartDownload, TextView tvStartPauseAll) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivStartPause = ivStartPause;
        this.llConcurrent = llConcurrent;
        this.llDelete = llDelete;
        this.llDone = llDone;
        this.llHint = llHint;
        this.llSmartDownload = llSmartDownload;
        this.llStartPauseAll = llStartPauseAll;
        this.recyclerView = recyclerView;
        this.tvConcurrent = tvConcurrent;
        this.tvDelete = tvDelete;
        this.tvEdit = tvEdit;
        this.tvSmartDownload = tvSmartDownload;
        this.tvStartPauseAll = tvStartPauseAll;
    }

    public static ActivityDownloadingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDownloadingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_downloading, root, attachToRoot, component);
    }

    public static ActivityDownloadingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadingBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDownloadingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_downloading, null, false, component);
    }

    public static ActivityDownloadingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadingBinding bind(View view, Object component) {
        return (ActivityDownloadingBinding) bind(component, view, R.layout.activity_downloading);
    }
}
