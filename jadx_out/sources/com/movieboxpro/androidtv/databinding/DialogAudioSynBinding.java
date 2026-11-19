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
public abstract class DialogAudioSynBinding extends ViewDataBinding {
    public final ImageView ivClose;
    public final ImageView ivDelayAdd;
    public final ImageView ivDelaySub;
    public final TextView tvDelay;

    protected DialogAudioSynBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivClose, ImageView ivDelayAdd, ImageView ivDelaySub, TextView tvDelay) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClose = ivClose;
        this.ivDelayAdd = ivDelayAdd;
        this.ivDelaySub = ivDelaySub;
        this.tvDelay = tvDelay;
    }

    public static DialogAudioSynBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAudioSynBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogAudioSynBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_audio_syn, root, attachToRoot, component);
    }

    public static DialogAudioSynBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAudioSynBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogAudioSynBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_audio_syn, null, false, component);
    }

    public static DialogAudioSynBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAudioSynBinding bind(View view, Object component) {
        return (DialogAudioSynBinding) bind(component, view, R.layout.dialog_audio_syn);
    }
}
