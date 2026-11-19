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
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentMoviesBinding extends ViewDataBinding {
    public final ConstraintLayout clFilter;
    public final LinearLayout container;
    public final FrameLayout flLoading;
    public final FrameLayout frameLayout;
    public final ImageView imageView6;
    public final LinearLayout llTopFilter;
    public final ProgressBar progressBar;
    public final FrameLayout specialFrameLayout;
    public final TextView textView17;

    protected FragmentMoviesBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout clFilter, LinearLayout container, FrameLayout flLoading, FrameLayout frameLayout, ImageView imageView6, LinearLayout llTopFilter, ProgressBar progressBar, FrameLayout specialFrameLayout, TextView textView17) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clFilter = clFilter;
        this.container = container;
        this.flLoading = flLoading;
        this.frameLayout = frameLayout;
        this.imageView6 = imageView6;
        this.llTopFilter = llTopFilter;
        this.progressBar = progressBar;
        this.specialFrameLayout = specialFrameLayout;
        this.textView17 = textView17;
    }

    public static FragmentMoviesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMoviesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentMoviesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_movies, root, attachToRoot, component);
    }

    public static FragmentMoviesBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMoviesBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentMoviesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_movies, null, false, component);
    }

    public static FragmentMoviesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMoviesBinding bind(View view, Object component) {
        return (FragmentMoviesBinding) bind(component, view, R.layout.fragment_movies);
    }
}
