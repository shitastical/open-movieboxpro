package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivitySuperChildModeBinding extends ViewDataBinding {
    public final FrameLayout flContainer;

    protected ActivitySuperChildModeBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flContainer) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flContainer = flContainer;
    }

    public static ActivitySuperChildModeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySuperChildModeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_super_child_mode, root, attachToRoot, component);
    }

    public static ActivitySuperChildModeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySuperChildModeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_super_child_mode, null, false, component);
    }

    public static ActivitySuperChildModeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySuperChildModeBinding bind(View view, Object component) {
        return (ActivitySuperChildModeBinding) bind(component, view, R.layout.activity_super_child_mode);
    }
}
