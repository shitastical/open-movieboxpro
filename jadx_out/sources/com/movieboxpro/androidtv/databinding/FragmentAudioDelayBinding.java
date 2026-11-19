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
public abstract class FragmentAudioDelayBinding extends ViewDataBinding {
    public final FrameLayout flDecrease;
    public final FrameLayout flIncrease;
    public final TextView tvDelay;

    protected FragmentAudioDelayBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flDecrease, FrameLayout flIncrease, TextView tvDelay) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flDecrease = flDecrease;
        this.flIncrease = flIncrease;
        this.tvDelay = tvDelay;
    }

    public static FragmentAudioDelayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAudioDelayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentAudioDelayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audio_delay, root, attachToRoot, component);
    }

    public static FragmentAudioDelayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAudioDelayBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentAudioDelayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audio_delay, null, false, component);
    }

    public static FragmentAudioDelayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAudioDelayBinding bind(View view, Object component) {
        return (FragmentAudioDelayBinding) bind(component, view, R.layout.fragment_audio_delay);
    }
}
