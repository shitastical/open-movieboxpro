package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentChooseLoginWayBinding extends ViewDataBinding {
    public final LinearLayout llCode;
    public final LinearLayout llQr;
    public final TextView tvVersion;

    protected FragmentChooseLoginWayBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llCode, LinearLayout llQr, TextView tvVersion) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llCode = llCode;
        this.llQr = llQr;
        this.tvVersion = tvVersion;
    }

    public static FragmentChooseLoginWayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseLoginWayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentChooseLoginWayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_choose_login_way, root, attachToRoot, component);
    }

    public static FragmentChooseLoginWayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseLoginWayBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentChooseLoginWayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_choose_login_way, null, false, component);
    }

    public static FragmentChooseLoginWayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseLoginWayBinding bind(View view, Object component) {
        return (FragmentChooseLoginWayBinding) bind(component, view, R.layout.fragment_choose_login_way);
    }
}
