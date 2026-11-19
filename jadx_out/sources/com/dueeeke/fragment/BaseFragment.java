package com.dueeeke.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseFragment extends Fragment {
    protected boolean isDataInitiated;
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    public Activity mContext;
    public View view;

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView(View view);

    protected abstract void loadData();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        this.mContext = getActivity();
        initView(this.view);
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initData();
        initListener();
        this.isViewInitiated = true;
        prepareFetchData();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.isVisibleToUser = z;
        if (z) {
            prepareFetchData();
        }
    }

    public void prepareFetchData() {
        prepareFetchData(false);
    }

    public void prepareFetchData(boolean z) {
        if (this.isVisibleToUser && this.isViewInitiated) {
            if (!this.isDataInitiated || z) {
                loadData();
                this.isDataInitiated = true;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void startActivity(Class<?> cls) {
        startActivity(cls, (Bundle) null);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, Bundle bundle, int i) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, i);
    }
}
