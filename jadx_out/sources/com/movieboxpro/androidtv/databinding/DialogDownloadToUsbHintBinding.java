package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogDownloadToUsbHintBinding extends ViewDataBinding {
    public final LinearLayout llDownload;

    protected DialogDownloadToUsbHintBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llDownload) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llDownload = llDownload;
    }

    public static DialogDownloadToUsbHintBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDownloadToUsbHintBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDownloadToUsbHintBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_download_to_usb_hint, root, attachToRoot, component);
    }

    public static DialogDownloadToUsbHintBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDownloadToUsbHintBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDownloadToUsbHintBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_download_to_usb_hint, null, false, component);
    }

    public static DialogDownloadToUsbHintBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDownloadToUsbHintBinding bind(View view, Object component) {
        return (DialogDownloadToUsbHintBinding) bind(component, view, R.layout.dialog_download_to_usb_hint);
    }
}
