package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogSmartDownloadSettingBinding extends ViewDataBinding {
    public final FrameLayout flAutoDelete;
    public final FrameLayout flQuality;
    public final FrameLayout flSmartDownload;
    public final TextView tvAutoDelete;
    public final TextView tvQuality;
    public final TextView tvSmartDownload;

    protected DialogSmartDownloadSettingBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flAutoDelete, FrameLayout flQuality, FrameLayout flSmartDownload, TextView tvAutoDelete, TextView tvQuality, TextView tvSmartDownload) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flAutoDelete = flAutoDelete;
        this.flQuality = flQuality;
        this.flSmartDownload = flSmartDownload;
        this.tvAutoDelete = tvAutoDelete;
        this.tvQuality = tvQuality;
        this.tvSmartDownload = tvSmartDownload;
    }

    public static DialogSmartDownloadSettingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSmartDownloadSettingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogSmartDownloadSettingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_smart_download_setting, root, attachToRoot, component);
    }

    public static DialogSmartDownloadSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSmartDownloadSettingBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogSmartDownloadSettingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_smart_download_setting, null, false, component);
    }

    public static DialogSmartDownloadSettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSmartDownloadSettingBinding bind(View view, Object component) {
        return (DialogSmartDownloadSettingBinding) bind(component, view, R.layout.dialog_smart_download_setting);
    }
}
