package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentTestNetSpeedBinding extends ViewDataBinding {
    public final FrameLayout flLoading;
    public final TvRecyclerView rvLoadingOne;
    public final TvRecyclerView rvLoadingTwo;
    public final TvRecyclerView rvTestOne;
    public final TvRecyclerView rvTestTwo;
    public final TextView tvTitle;

    protected FragmentTestNetSpeedBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flLoading, TvRecyclerView rvLoadingOne, TvRecyclerView rvLoadingTwo, TvRecyclerView rvTestOne, TvRecyclerView rvTestTwo, TextView tvTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flLoading = flLoading;
        this.rvLoadingOne = rvLoadingOne;
        this.rvLoadingTwo = rvLoadingTwo;
        this.rvTestOne = rvTestOne;
        this.rvTestTwo = rvTestTwo;
        this.tvTitle = tvTitle;
    }

    public static FragmentTestNetSpeedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTestNetSpeedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentTestNetSpeedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_test_net_speed, root, attachToRoot, component);
    }

    public static FragmentTestNetSpeedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTestNetSpeedBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentTestNetSpeedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_test_net_speed, null, false, component);
    }

    public static FragmentTestNetSpeedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTestNetSpeedBinding bind(View view, Object component) {
        return (FragmentTestNetSpeedBinding) bind(component, view, R.layout.fragment_test_net_speed);
    }
}
