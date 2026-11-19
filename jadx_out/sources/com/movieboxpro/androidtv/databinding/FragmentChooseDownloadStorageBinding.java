package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentChooseDownloadStorageBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected FragmentChooseDownloadStorageBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static FragmentChooseDownloadStorageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseDownloadStorageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentChooseDownloadStorageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_choose_download_storage, root, attachToRoot, component);
    }

    public static FragmentChooseDownloadStorageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseDownloadStorageBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentChooseDownloadStorageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_choose_download_storage, null, false, component);
    }

    public static FragmentChooseDownloadStorageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseDownloadStorageBinding bind(View view, Object component) {
        return (FragmentChooseDownloadStorageBinding) bind(component, view, R.layout.fragment_choose_download_storage);
    }
}
