package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.view.widget.LoadingStatusView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityActorDetailBinding extends ViewDataBinding {
    public final LinearLayout llContent;
    public final LoadingStatusView loadStatusView;
    public final RecyclerView rvMovies;
    public final RecyclerView rvTvShows;

    protected ActivityActorDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llContent, LoadingStatusView loadStatusView, RecyclerView rvMovies, RecyclerView rvTvShows) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llContent = llContent;
        this.loadStatusView = loadStatusView;
        this.rvMovies = rvMovies;
        this.rvTvShows = rvTvShows;
    }

    public static ActivityActorDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityActorDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityActorDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_actor_detail, root, attachToRoot, component);
    }

    public static ActivityActorDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityActorDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityActorDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_actor_detail, null, false, component);
    }

    public static ActivityActorDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityActorDetailBinding bind(View view, Object component) {
        return (ActivityActorDetailBinding) bind(component, view, R.layout.activity_actor_detail);
    }
}
