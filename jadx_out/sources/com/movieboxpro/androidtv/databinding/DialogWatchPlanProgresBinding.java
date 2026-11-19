package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogWatchPlanProgresBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected DialogWatchPlanProgresBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static DialogWatchPlanProgresBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWatchPlanProgresBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogWatchPlanProgresBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_watch_plan_progres, root, attachToRoot, component);
    }

    public static DialogWatchPlanProgresBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWatchPlanProgresBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogWatchPlanProgresBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_watch_plan_progres, null, false, component);
    }

    public static DialogWatchPlanProgresBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWatchPlanProgresBinding bind(View view, Object component) {
        return (DialogWatchPlanProgresBinding) bind(component, view, R.layout.dialog_watch_plan_progres);
    }
}
