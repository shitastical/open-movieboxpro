package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityTvChannelReceiverBinding extends ViewDataBinding {
    public final ProgressBar progressBar;

    protected ActivityTvChannelReceiverBinding(Object _bindingComponent, View _root, int _localFieldCount, ProgressBar progressBar) {
        super(_bindingComponent, _root, _localFieldCount);
        this.progressBar = progressBar;
    }

    public static ActivityTvChannelReceiverBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvChannelReceiverBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTvChannelReceiverBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_tv_channel_receiver, root, attachToRoot, component);
    }

    public static ActivityTvChannelReceiverBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvChannelReceiverBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTvChannelReceiverBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_tv_channel_receiver, null, false, component);
    }

    public static ActivityTvChannelReceiverBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTvChannelReceiverBinding bind(View view, Object component) {
        return (ActivityTvChannelReceiverBinding) bind(component, view, R.layout.activity_tv_channel_receiver);
    }
}
