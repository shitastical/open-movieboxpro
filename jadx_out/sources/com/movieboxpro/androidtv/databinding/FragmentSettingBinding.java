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
public abstract class FragmentSettingBinding extends ViewDataBinding {
    public final LinearLayout llAudioDelay;
    public final LinearLayout llAutoPlayNext;
    public final LinearLayout llChildMode;
    public final LinearLayout llDownloadLocation;
    public final LinearLayout llHideMovieList;
    public final LinearLayout llRememberOrg;
    public final LinearLayout llSmartDownload;
    public final LinearLayout llSwitchLanguage;
    public final LinearLayout llTestSpeed;
    public final TvRecyclerView recyclerView;
    public final TextView tvAudioDelay;
    public final TextView tvChildMode;
    public final TextView tvDownloadLocation;
    public final TextView tvHideMovieList;
    public final TextView tvLanguage;
    public final TextView tvPlayNext;
    public final TextView tvRememberOrg;
    public final TextView tvSmartDownload;

    protected FragmentSettingBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llAudioDelay, LinearLayout llAutoPlayNext, LinearLayout llChildMode, LinearLayout llDownloadLocation, LinearLayout llHideMovieList, LinearLayout llRememberOrg, LinearLayout llSmartDownload, LinearLayout llSwitchLanguage, LinearLayout llTestSpeed, TvRecyclerView recyclerView, TextView tvAudioDelay, TextView tvChildMode, TextView tvDownloadLocation, TextView tvHideMovieList, TextView tvLanguage, TextView tvPlayNext, TextView tvRememberOrg, TextView tvSmartDownload) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llAudioDelay = llAudioDelay;
        this.llAutoPlayNext = llAutoPlayNext;
        this.llChildMode = llChildMode;
        this.llDownloadLocation = llDownloadLocation;
        this.llHideMovieList = llHideMovieList;
        this.llRememberOrg = llRememberOrg;
        this.llSmartDownload = llSmartDownload;
        this.llSwitchLanguage = llSwitchLanguage;
        this.llTestSpeed = llTestSpeed;
        this.recyclerView = recyclerView;
        this.tvAudioDelay = tvAudioDelay;
        this.tvChildMode = tvChildMode;
        this.tvDownloadLocation = tvDownloadLocation;
        this.tvHideMovieList = tvHideMovieList;
        this.tvLanguage = tvLanguage;
        this.tvPlayNext = tvPlayNext;
        this.tvRememberOrg = tvRememberOrg;
        this.tvSmartDownload = tvSmartDownload;
    }

    public static FragmentSettingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSettingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSettingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_setting, root, attachToRoot, component);
    }

    public static FragmentSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSettingBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSettingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_setting, null, false, component);
    }

    public static FragmentSettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSettingBinding bind(View view, Object component) {
        return (FragmentSettingBinding) bind(component, view, R.layout.fragment_setting);
    }
}
