package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityTvDetail2Binding extends ViewDataBinding {
    public final ConstraintLayout backgroundLayout;
    public final FrameLayout flTop;
    public final ImageView ivAdd;
    public final ImageView ivBackground;
    public final ImageView ivDownload;
    public final ImageView ivWatchPlan;
    public final ImageView ivWatchPlanProgress;
    public final LinearLayout llLoading;
    public final LinearLayout llResume;
    public final LinearLayout llTvInfo;
    public final ConstraintLayout llTvName;
    public final LayoutDetailMetaRowBinding metaInfoRow;
    public final ProgressBar progressBar;
    public final TvRecyclerView recyclerView;
    public final FrameLayout rlPlay;
    public final TvRecyclerView rvActors;
    public final ScrollView scrollView;
    public final TextView tvActors;
    public final TextView tvDesc;
    public final TextView tvDirector;
    public final TextView tvGenre;
    public final TextView tvLanguage;
    public final TextView tvLoading;
    public final TextView tvName;
    public final TextView tvPlay;
    public final TextView tvSeason;
    public final TextView tvStars;

    protected ActivityTvDetail2Binding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout backgroundLayout, FrameLayout flTop, ImageView ivAdd, ImageView ivBackground, ImageView ivDownload, ImageView ivWatchPlan, ImageView ivWatchPlanProgress, LinearLayout llLoading, LinearLayout llResume, LinearLayout llTvInfo, ConstraintLayout llTvName, LayoutDetailMetaRowBinding metaInfoRow, ProgressBar progressBar, TvRecyclerView recyclerView, FrameLayout rlPlay, TvRecyclerView rvActors, ScrollView scrollView, TextView tvActors, TextView tvDesc, TextView tvDirector, TextView tvGenre, TextView tvLanguage, TextView tvLoading, TextView tvName, TextView tvPlay, TextView tvSeason, TextView tvStars) {
        super(_bindingComponent, _root, _localFieldCount);
        this.backgroundLayout = backgroundLayout;
        this.flTop = flTop;
        this.ivAdd = ivAdd;
        this.ivBackground = ivBackground;
        this.ivDownload = ivDownload;
        this.ivWatchPlan = ivWatchPlan;
        this.ivWatchPlanProgress = ivWatchPlanProgress;
        this.llLoading = llLoading;
        this.llResume = llResume;
        this.llTvInfo = llTvInfo;
        this.llTvName = llTvName;
        this.metaInfoRow = metaInfoRow;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
        this.rlPlay = rlPlay;
        this.rvActors = rvActors;
        this.scrollView = scrollView;
        this.tvActors = tvActors;
        this.tvDesc = tvDesc;
        this.tvDirector = tvDirector;
        this.tvGenre = tvGenre;
        this.tvLanguage = tvLanguage;
        this.tvLoading = tvLoading;
        this.tvName = tvName;
        this.tvPlay = tvPlay;
        this.tvSeason = tvSeason;
        this.tvStars = tvStars;
    }

    public static ActivityTvDetail2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvDetail2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTvDetail2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_tv_detail2, root, attachToRoot, component);
    }

    public static ActivityTvDetail2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvDetail2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTvDetail2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_tv_detail2, null, false, component);
    }

    public static ActivityTvDetail2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvDetail2Binding bind(View view, Object component) {
        return (ActivityTvDetail2Binding) bind(component, view, R.layout.activity_tv_detail2);
    }
}
