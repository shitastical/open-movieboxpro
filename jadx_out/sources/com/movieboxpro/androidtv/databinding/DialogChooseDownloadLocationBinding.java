package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogChooseDownloadLocationBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected DialogChooseDownloadLocationBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static DialogChooseDownloadLocationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadLocationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogChooseDownloadLocationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_download_location, root, attachToRoot, component);
    }

    public static DialogChooseDownloadLocationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadLocationBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogChooseDownloadLocationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_download_location, null, false, component);
    }

    public static DialogChooseDownloadLocationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadLocationBinding bind(View view, Object component) {
        return (DialogChooseDownloadLocationBinding) bind(component, view, R.layout.dialog_choose_download_location);
    }
}
