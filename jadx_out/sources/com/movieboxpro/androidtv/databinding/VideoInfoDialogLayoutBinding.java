package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class VideoInfoDialogLayoutBinding extends ViewDataBinding {
    public final TextView tvDetail;
    public final TextView tvDirector;
    public final TextView tvStars;

    protected VideoInfoDialogLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvDetail, TextView tvDirector, TextView tvStars) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvDetail = tvDetail;
        this.tvDirector = tvDirector;
        this.tvStars = tvStars;
    }

    public static VideoInfoDialogLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoInfoDialogLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (VideoInfoDialogLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.video_info_dialog_layout, root, attachToRoot, component);
    }

    public static VideoInfoDialogLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoInfoDialogLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (VideoInfoDialogLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.video_info_dialog_layout, null, false, component);
    }

    public static VideoInfoDialogLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoInfoDialogLayoutBinding bind(View view, Object component) {
        return (VideoInfoDialogLayoutBinding) bind(component, view, R.layout.video_info_dialog_layout);
    }
}
