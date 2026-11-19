package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogAddWatchPlanBinding extends ViewDataBinding {
    public final ImageView ivWaiting;
    public final ImageView ivWatched;
    public final LinearLayout llWaiting;
    public final LinearLayout llWatched;
    public final TextView tvWaiting;
    public final TextView tvWatched;

    protected DialogAddWatchPlanBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivWaiting, ImageView ivWatched, LinearLayout llWaiting, LinearLayout llWatched, TextView tvWaiting, TextView tvWatched) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivWaiting = ivWaiting;
        this.ivWatched = ivWatched;
        this.llWaiting = llWaiting;
        this.llWatched = llWatched;
        this.tvWaiting = tvWaiting;
        this.tvWatched = tvWatched;
    }

    public static DialogAddWatchPlanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddWatchPlanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogAddWatchPlanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_add_watch_plan, root, attachToRoot, component);
    }

    public static DialogAddWatchPlanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddWatchPlanBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogAddWatchPlanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_add_watch_plan, null, false, component);
    }

    public static DialogAddWatchPlanBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddWatchPlanBinding bind(View view, Object component) {
        return (DialogAddWatchPlanBinding) bind(component, view, R.layout.dialog_add_watch_plan);
    }
}
