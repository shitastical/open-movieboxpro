package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentAudioTrackBinding extends ViewDataBinding {
    public final FrameLayout flLoading;
    public final ImageView ivAudioSyn;
    public final ImageView ivDelayAdd;
    public final ImageView ivDelaySub;
    public final ImageView ivShowTrack;
    public final LinearLayout llAudioTracks;
    public final LinearLayout llBestAudio;
    public final LinearLayout llContainer;
    public final LinearLayout llDelay;
    public final ProgressBar progressBar;
    public final TvRecyclerView recyclerView;
    public final TextView tvAudioHint;
    public final TextView tvDefaultAudio;
    public final TextView tvDelay;
    public final TextView tvTryAgain;

    protected FragmentAudioTrackBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flLoading, ImageView ivAudioSyn, ImageView ivDelayAdd, ImageView ivDelaySub, ImageView ivShowTrack, LinearLayout llAudioTracks, LinearLayout llBestAudio, LinearLayout llContainer, LinearLayout llDelay, ProgressBar progressBar, TvRecyclerView recyclerView, TextView tvAudioHint, TextView tvDefaultAudio, TextView tvDelay, TextView tvTryAgain) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flLoading = flLoading;
        this.ivAudioSyn = ivAudioSyn;
        this.ivDelayAdd = ivDelayAdd;
        this.ivDelaySub = ivDelaySub;
        this.ivShowTrack = ivShowTrack;
        this.llAudioTracks = llAudioTracks;
        this.llBestAudio = llBestAudio;
        this.llContainer = llContainer;
        this.llDelay = llDelay;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
        this.tvAudioHint = tvAudioHint;
        this.tvDefaultAudio = tvDefaultAudio;
        this.tvDelay = tvDelay;
        this.tvTryAgain = tvTryAgain;
    }

    public static FragmentAudioTrackBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAudioTrackBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentAudioTrackBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audio_track, root, attachToRoot, component);
    }

    public static FragmentAudioTrackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAudioTrackBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentAudioTrackBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audio_track, null, false, component);
    }

    public static FragmentAudioTrackBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAudioTrackBinding bind(View view, Object component) {
        return (FragmentAudioTrackBinding) bind(component, view, R.layout.fragment_audio_track);
    }
}
