package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentWebViewDialogBinding extends ViewDataBinding {
    public final ProgressBar progressBar;
    public final WebView webView;

    protected FragmentWebViewDialogBinding(Object _bindingComponent, View _root, int _localFieldCount, ProgressBar progressBar, WebView webView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.progressBar = progressBar;
        this.webView = webView;
    }

    public static FragmentWebViewDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWebViewDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWebViewDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_web_view_dialog, root, attachToRoot, component);
    }

    public static FragmentWebViewDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWebViewDialogBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWebViewDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_web_view_dialog, null, false, component);
    }

    public static FragmentWebViewDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWebViewDialogBinding bind(View view, Object component) {
        return (FragmentWebViewDialogBinding) bind(component, view, R.layout.fragment_web_view_dialog);
    }
}
