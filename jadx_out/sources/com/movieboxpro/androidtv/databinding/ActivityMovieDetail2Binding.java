package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.view.widget.DownloadingStatusView;
import com.movieboxpro.android.view.widget.SlowScrollView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityMovieDetail2Binding extends ViewDataBinding {
    public final ConstraintLayout backgroundLayout;
    public final DownloadingStatusView downloadStatusView;
    public final FrameLayout flDownload;
    public final FrameLayout flTop;
    public final FrameLayout frameLayout;
    public final ImageView ivAdd;
    public final ImageView ivBackground;
    public final ImageView ivWatchPlan;
    public final LinearLayout llLoading;
    public final LinearLayout llMovieInfo;
    public final ConstraintLayout llMovieName;
    public final LinearLayout llResume;
    public final LayoutDetailMetaRowBinding metaInfoRow;
    public final ProgressBar progressBar;
    public final FrameLayout rlPlay;
    public final SlowScrollView scrollView;
    public final TextView tvLanguage;
    public final TextView tvLoading;
    public final TextView tvMovieDesc;
    public final TextView tvMovieDirector;
    public final TextView tvMovieName;
    public final TextView tvMovieStars;
    public final TextView tvPlay;

    protected ActivityMovieDetail2Binding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout backgroundLayout, DownloadingStatusView downloadStatusView, FrameLayout flDownload, FrameLayout flTop, FrameLayout frameLayout, ImageView ivAdd, ImageView ivBackground, ImageView ivWatchPlan, LinearLayout llLoading, LinearLayout llMovieInfo, ConstraintLayout llMovieName, LinearLayout llResume, LayoutDetailMetaRowBinding metaInfoRow, ProgressBar progressBar, FrameLayout rlPlay, SlowScrollView scrollView, TextView tvLanguage, TextView tvLoading, TextView tvMovieDesc, TextView tvMovieDirector, TextView tvMovieName, TextView tvMovieStars, TextView tvPlay) {
        super(_bindingComponent, _root, _localFieldCount);
        this.backgroundLayout = backgroundLayout;
        this.downloadStatusView = downloadStatusView;
        this.flDownload = flDownload;
        this.flTop = flTop;
        this.frameLayout = frameLayout;
        this.ivAdd = ivAdd;
        this.ivBackground = ivBackground;
        this.ivWatchPlan = ivWatchPlan;
        this.llLoading = llLoading;
        this.llMovieInfo = llMovieInfo;
        this.llMovieName = llMovieName;
        this.llResume = llResume;
        this.metaInfoRow = metaInfoRow;
        this.progressBar = progressBar;
        this.rlPlay = rlPlay;
        this.scrollView = scrollView;
        this.tvLanguage = tvLanguage;
        this.tvLoading = tvLoading;
        this.tvMovieDesc = tvMovieDesc;
        this.tvMovieDirector = tvMovieDirector;
        this.tvMovieName = tvMovieName;
        this.tvMovieStars = tvMovieStars;
        this.tvPlay = tvPlay;
    }

    public static ActivityMovieDetail2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMovieDetail2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMovieDetail2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_movie_detail2, root, attachToRoot, component);
    }

    public static ActivityMovieDetail2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMovieDetail2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMovieDetail2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_movie_detail2, null, false, component);
    }

    public static ActivityMovieDetail2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMovieDetail2Binding bind(View view, Object component) {
        return (ActivityMovieDetail2Binding) bind(component, view, R.layout.activity_movie_detail2);
    }
}
