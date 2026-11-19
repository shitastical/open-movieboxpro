package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogPreviewSubtitleBinding extends ViewDataBinding {
    public final LinearLayout llBtn;
    public final LinearLayout llContainer;
    public final TvRecyclerView rvSubtitle;
    public final TextView tvOk;
    public final TextView tvTranscode;

    protected DialogPreviewSubtitleBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llBtn, LinearLayout llContainer, TvRecyclerView rvSubtitle, TextView tvOk, TextView tvTranscode) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llBtn = llBtn;
        this.llContainer = llContainer;
        this.rvSubtitle = rvSubtitle;
        this.tvOk = tvOk;
        this.tvTranscode = tvTranscode;
    }

    public static DialogPreviewSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogPreviewSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_preview_subtitle, root, attachToRoot, component);
    }

    public static DialogPreviewSubtitleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewSubtitleBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogPreviewSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_preview_subtitle, null, false, component);
    }

    public static DialogPreviewSubtitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPreviewSubtitleBinding bind(View view, Object component) {
        return (DialogPreviewSubtitleBinding) bind(component, view, R.layout.dialog_preview_subtitle);
    }
}
