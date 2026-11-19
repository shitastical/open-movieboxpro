package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.ViewStubProxy;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentDownloadBinding extends ViewDataBinding {
    public final ViewStubProxy emptyViewStub;
    public final FrameLayout flList;
    public final LinearLayout llBottom;
    public final LinearLayout llClearAll;
    public final LinearLayout llDelete;
    public final LinearLayout llDownloading;
    public final LinearLayout llEdit;
    public final LinearLayout llHint;
    public final LinearLayout llSmartDownload;
    public final TvRecyclerView recyclerView;
    public final RecyclerView rvStorage;
    public final TextView tvDelete;
    public final TextView tvDeviceSize;
    public final TextView tvDownloading;
    public final TextView tvEdit;
    public final TextView tvSmartDownload;

    protected FragmentDownloadBinding(Object _bindingComponent, View _root, int _localFieldCount, ViewStubProxy emptyViewStub, FrameLayout flList, LinearLayout llBottom, LinearLayout llClearAll, LinearLayout llDelete, LinearLayout llDownloading, LinearLayout llEdit, LinearLayout llHint, LinearLayout llSmartDownload, TvRecyclerView recyclerView, RecyclerView rvStorage, TextView tvDelete, TextView tvDeviceSize, TextView tvDownloading, TextView tvEdit, TextView tvSmartDownload) {
        super(_bindingComponent, _root, _localFieldCount);
        this.emptyViewStub = emptyViewStub;
        this.flList = flList;
        this.llBottom = llBottom;
        this.llClearAll = llClearAll;
        this.llDelete = llDelete;
        this.llDownloading = llDownloading;
        this.llEdit = llEdit;
        this.llHint = llHint;
        this.llSmartDownload = llSmartDownload;
        this.recyclerView = recyclerView;
        this.rvStorage = rvStorage;
        this.tvDelete = tvDelete;
        this.tvDeviceSize = tvDeviceSize;
        this.tvDownloading = tvDownloading;
        this.tvEdit = tvEdit;
        this.tvSmartDownload = tvSmartDownload;
    }

    public static FragmentDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_download, root, attachToRoot, component);
    }

    public static FragmentDownloadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDownloadBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_download, null, false, component);
    }

    public static FragmentDownloadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDownloadBinding bind(View view, Object component) {
        return (FragmentDownloadBinding) bind(component, view, R.layout.fragment_download);
    }
}
