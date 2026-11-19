package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.view.widget.CustomProgressView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class WatchProgressTopLayoutBinding extends ViewDataBinding {
    public final ImageView imageView;
    public final ImageView ivMarkSeason;
    public final ImageView ivPoster;
    public final LinearLayout linearLayout;
    public final CustomProgressView progressView;
    public final TextView tvAddWaiting;
    public final TextView tvMarkWatched;
    public final TextView tvName;
    public final TextView tvProgress;
    public final TextView tvSeason;

    protected WatchProgressTopLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView imageView, ImageView ivMarkSeason, ImageView ivPoster, LinearLayout linearLayout, CustomProgressView progressView, TextView tvAddWaiting, TextView tvMarkWatched, TextView tvName, TextView tvProgress, TextView tvSeason) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView = imageView;
        this.ivMarkSeason = ivMarkSeason;
        this.ivPoster = ivPoster;
        this.linearLayout = linearLayout;
        this.progressView = progressView;
        this.tvAddWaiting = tvAddWaiting;
        this.tvMarkWatched = tvMarkWatched;
        this.tvName = tvName;
        this.tvProgress = tvProgress;
        this.tvSeason = tvSeason;
    }

    public static WatchProgressTopLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchProgressTopLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (WatchProgressTopLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.watch_progress_top_layout, root, attachToRoot, component);
    }

    public static WatchProgressTopLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchProgressTopLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (WatchProgressTopLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.watch_progress_top_layout, null, false, component);
    }

    public static WatchProgressTopLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchProgressTopLayoutBinding bind(View view, Object component) {
        return (WatchProgressTopLayoutBinding) bind(component, view, R.layout.watch_progress_top_layout);
    }
}
