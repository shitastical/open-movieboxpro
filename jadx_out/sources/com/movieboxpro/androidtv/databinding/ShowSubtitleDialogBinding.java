package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ShowSubtitleDialogBinding extends ViewDataBinding {
    public final TextView tvCancel;
    public final TextView tvDownload;
    public final TextView tvSubtitle;

    protected ShowSubtitleDialogBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvCancel, TextView tvDownload, TextView tvSubtitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvCancel = tvCancel;
        this.tvDownload = tvDownload;
        this.tvSubtitle = tvSubtitle;
    }

    public static ShowSubtitleDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ShowSubtitleDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ShowSubtitleDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.show_subtitle_dialog, root, attachToRoot, component);
    }

    public static ShowSubtitleDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ShowSubtitleDialogBinding inflate(LayoutInflater inflater, Object component) {
        return (ShowSubtitleDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.show_subtitle_dialog, null, false, component);
    }

    public static ShowSubtitleDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ShowSubtitleDialogBinding bind(View view, Object component) {
        return (ShowSubtitleDialogBinding) bind(component, view, R.layout.show_subtitle_dialog);
    }
}
