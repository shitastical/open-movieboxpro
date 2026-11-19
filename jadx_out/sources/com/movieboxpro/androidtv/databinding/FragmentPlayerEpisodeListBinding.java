package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.view.widget.LoadingView;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentPlayerEpisodeListBinding extends ViewDataBinding {
    public final LinearLayout llError;
    public final LinearLayout llSeason;
    public final LoadingView progressBar;
    public final TvRecyclerView recyclerView;
    public final TextView tvCurrSeason;
    public final TextView tvError;
    public final TextView tvRetry;
    public final TextView tvSeasons;

    protected FragmentPlayerEpisodeListBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llError, LinearLayout llSeason, LoadingView progressBar, TvRecyclerView recyclerView, TextView tvCurrSeason, TextView tvError, TextView tvRetry, TextView tvSeasons) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llError = llError;
        this.llSeason = llSeason;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
        this.tvCurrSeason = tvCurrSeason;
        this.tvError = tvError;
        this.tvRetry = tvRetry;
        this.tvSeasons = tvSeasons;
    }

    public static FragmentPlayerEpisodeListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPlayerEpisodeListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentPlayerEpisodeListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_player_episode_list, root, attachToRoot, component);
    }

    public static FragmentPlayerEpisodeListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPlayerEpisodeListBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentPlayerEpisodeListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_player_episode_list, null, false, component);
    }

    public static FragmentPlayerEpisodeListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPlayerEpisodeListBinding bind(View view, Object component) {
        return (FragmentPlayerEpisodeListBinding) bind(component, view, R.layout.fragment_player_episode_list);
    }
}
