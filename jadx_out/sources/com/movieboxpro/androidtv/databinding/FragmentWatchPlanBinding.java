package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tab.TvTabLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentWatchPlanBinding extends ViewDataBinding {
    public final ConstraintLayout clFilter;
    public final FrameLayout flContainer;
    public final ImageView imageView6;
    public final TvTabLayout tabLayout;
    public final TextView textView17;

    protected FragmentWatchPlanBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout clFilter, FrameLayout flContainer, ImageView imageView6, TvTabLayout tabLayout, TextView textView17) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clFilter = clFilter;
        this.flContainer = flContainer;
        this.imageView6 = imageView6;
        this.tabLayout = tabLayout;
        this.textView17 = textView17;
    }

    public static FragmentWatchPlanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWatchPlanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWatchPlanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_watch_plan, root, attachToRoot, component);
    }

    public static FragmentWatchPlanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWatchPlanBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWatchPlanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_watch_plan, null, false, component);
    }

    public static FragmentWatchPlanBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWatchPlanBinding bind(View view, Object component) {
        return (FragmentWatchPlanBinding) bind(component, view, R.layout.fragment_watch_plan);
    }
}
