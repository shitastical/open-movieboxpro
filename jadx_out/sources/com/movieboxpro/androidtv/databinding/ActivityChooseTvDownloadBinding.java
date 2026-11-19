package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityChooseTvDownloadBinding extends ViewDataBinding {
    public final ConstraintLayout clDownloadLocation;
    public final ConstraintLayout clQuality;
    public final TvRecyclerView recyclerView;
    public final TextView textView;
    public final TextView tvDeviceName;
    public final TextView tvOn;
    public final TextView tvQuality;
    public final TextView tvSeasonEpisode;
    public final TextView tvSpace;

    protected ActivityChooseTvDownloadBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout clDownloadLocation, ConstraintLayout clQuality, TvRecyclerView recyclerView, TextView textView, TextView tvDeviceName, TextView tvOn, TextView tvQuality, TextView tvSeasonEpisode, TextView tvSpace) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clDownloadLocation = clDownloadLocation;
        this.clQuality = clQuality;
        this.recyclerView = recyclerView;
        this.textView = textView;
        this.tvDeviceName = tvDeviceName;
        this.tvOn = tvOn;
        this.tvQuality = tvQuality;
        this.tvSeasonEpisode = tvSeasonEpisode;
        this.tvSpace = tvSpace;
    }

    public static ActivityChooseTvDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChooseTvDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityChooseTvDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_choose_tv_download, root, attachToRoot, component);
    }

    public static ActivityChooseTvDownloadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChooseTvDownloadBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityChooseTvDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_choose_tv_download, null, false, component);
    }

    public static ActivityChooseTvDownloadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChooseTvDownloadBinding bind(View view, Object component) {
        return (ActivityChooseTvDownloadBinding) bind(component, view, R.layout.activity_choose_tv_download);
    }
}
