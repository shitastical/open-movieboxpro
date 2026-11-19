package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentDownloadVideoQualityBinding extends ViewDataBinding {
    public final LinearLayout linearLayout;
    public final TvRecyclerView recyclerView;
    public final TvRecyclerView rvDevice;
    public final TextView tvNote;
    public final TextView tvPath;

    protected FragmentDownloadVideoQualityBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout linearLayout, TvRecyclerView recyclerView, TvRecyclerView rvDevice, TextView tvNote, TextView tvPath) {
        super(_bindingComponent, _root, _localFieldCount);
        this.linearLayout = linearLayout;
        this.recyclerView = recyclerView;
        this.rvDevice = rvDevice;
        this.tvNote = tvNote;
        this.tvPath = tvPath;
    }

    public static FragmentDownloadVideoQualityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDownloadVideoQualityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDownloadVideoQualityBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_download_video_quality, root, attachToRoot, component);
    }

    public static FragmentDownloadVideoQualityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDownloadVideoQualityBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDownloadVideoQualityBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_download_video_quality, null, false, component);
    }

    public static FragmentDownloadVideoQualityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDownloadVideoQualityBinding bind(View view, Object component) {
        return (FragmentDownloadVideoQualityBinding) bind(component, view, R.layout.fragment_download_video_quality);
    }
}
