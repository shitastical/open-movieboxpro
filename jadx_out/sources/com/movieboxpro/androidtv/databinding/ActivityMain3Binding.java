package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tab.TvTabLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityMain3Binding extends ViewDataBinding {
    public final LinearLayout container;
    public final FrameLayout content;
    public final FrameLayout flDownload;
    public final ImageView ivChildMode;
    public final ImageView ivDownload;
    public final ImageView ivFavorite;
    public final ImageView ivSearch;
    public final ImageView ivTvGuide;
    public final LinearLayout llTop;
    public final TvTabLayout tabLayout;
    public final TextView tvDownloadNum;

    protected ActivityMain3Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout container, FrameLayout content, FrameLayout flDownload, ImageView ivChildMode, ImageView ivDownload, ImageView ivFavorite, ImageView ivSearch, ImageView ivTvGuide, LinearLayout llTop, TvTabLayout tabLayout, TextView tvDownloadNum) {
        super(_bindingComponent, _root, _localFieldCount);
        this.container = container;
        this.content = content;
        this.flDownload = flDownload;
        this.ivChildMode = ivChildMode;
        this.ivDownload = ivDownload;
        this.ivFavorite = ivFavorite;
        this.ivSearch = ivSearch;
        this.ivTvGuide = ivTvGuide;
        this.llTop = llTop;
        this.tabLayout = tabLayout;
        this.tvDownloadNum = tvDownloadNum;
    }

    public static ActivityMain3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMain3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMain3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main3, root, attachToRoot, component);
    }

    public static ActivityMain3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMain3Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMain3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main3, null, false, component);
    }

    public static ActivityMain3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMain3Binding bind(View view, Object component) {
        return (ActivityMain3Binding) bind(component, view, R.layout.activity_main3);
    }
}
