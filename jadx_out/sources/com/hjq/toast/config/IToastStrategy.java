package com.hjq.toast.config;

import android.app.Application;
import com.hjq.toast.ToastParams;

/* JADX INFO: loaded from: classes5.dex */
public interface IToastStrategy {
    void cancelToast();

    IToast createToast(ToastParams toastParams);

    void registerStrategy(Application application);

    void showToast(ToastParams toastParams);
}
