package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogChangeVolumeBinding extends ViewDataBinding {
    public final ImageView ivAdd;
    public final ImageView tvSub;
    public final TextView tvVolume;

    protected DialogChangeVolumeBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivAdd, ImageView tvSub, TextView tvVolume) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivAdd = ivAdd;
        this.tvSub = tvSub;
        this.tvVolume = tvVolume;
    }

    public static DialogChangeVolumeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChangeVolumeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogChangeVolumeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_change_volume, root, attachToRoot, component);
    }

    public static DialogChangeVolumeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChangeVolumeBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogChangeVolumeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_change_volume, null, false, component);
    }

    public static DialogChangeVolumeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChangeVolumeBinding bind(View view, Object component) {
        return (DialogChangeVolumeBinding) bind(component, view, R.layout.dialog_change_volume);
    }
}
