package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentVideoQualityBinding extends ViewDataBinding {
    public final ProgressBar progressBar;
    public final TvRecyclerView recyclerView;

    protected FragmentVideoQualityBinding(Object _bindingComponent, View _root, int _localFieldCount, ProgressBar progressBar, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
    }

    public static FragmentVideoQualityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVideoQualityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVideoQualityBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_video_quality, root, attachToRoot, component);
    }

    public static FragmentVideoQualityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVideoQualityBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVideoQualityBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_video_quality, null, false, component);
    }

    public static FragmentVideoQualityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVideoQualityBinding bind(View view, Object component) {
        return (FragmentVideoQualityBinding) bind(component, view, R.layout.fragment_video_quality);
    }
}
