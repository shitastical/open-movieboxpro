package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.media3.ui.PlayerView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogYoutubePlayBinding extends ViewDataBinding {
    public final FrameLayout frameLayout;
    public final ImageView ivPlayPause;
    public final AppCompatSeekBar playerSeekBar;
    public final PlayerView playerView;
    public final ProgressBar progressBar;

    protected DialogYoutubePlayBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout frameLayout, ImageView ivPlayPause, AppCompatSeekBar playerSeekBar, PlayerView playerView, ProgressBar progressBar) {
        super(_bindingComponent, _root, _localFieldCount);
        this.frameLayout = frameLayout;
        this.ivPlayPause = ivPlayPause;
        this.playerSeekBar = playerSeekBar;
        this.playerView = playerView;
        this.progressBar = progressBar;
    }

    public static DialogYoutubePlayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYoutubePlayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogYoutubePlayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_youtube_play, root, attachToRoot, component);
    }

    public static DialogYoutubePlayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYoutubePlayBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogYoutubePlayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_youtube_play, null, false, component);
    }

    public static DialogYoutubePlayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYoutubePlayBinding bind(View view, Object component) {
        return (DialogYoutubePlayBinding) bind(component, view, R.layout.dialog_youtube_play);
    }
}
