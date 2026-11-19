package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityWatchPlanBinding extends ViewDataBinding {
    public final FrameLayout flContainer;

    protected ActivityWatchPlanBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flContainer) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flContainer = flContainer;
    }

    public static ActivityWatchPlanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWatchPlanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityWatchPlanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_watch_plan, root, attachToRoot, component);
    }

    public static ActivityWatchPlanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWatchPlanBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityWatchPlanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_watch_plan, null, false, component);
    }

    public static ActivityWatchPlanBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWatchPlanBinding bind(View view, Object component) {
        return (ActivityWatchPlanBinding) bind(component, view, R.layout.activity_watch_plan);
    }
}
