package com.movieboxpro.android.receiver;

import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MethodCallsLogger;

/* JADX INFO: loaded from: classes5.dex */
public class UsbConnectReceiver_LifecycleAdapter implements GeneratedAdapter {
    final UsbConnectReceiver mReceiver;

    UsbConnectReceiver_LifecycleAdapter(UsbConnectReceiver receiver) {
        this.mReceiver = receiver;
    }

    @Override // androidx.lifecycle.GeneratedAdapter
    public void callMethods(LifecycleOwner owner, Lifecycle.Event event, boolean onAny, MethodCallsLogger logger) {
        boolean z = logger != null;
        if (onAny) {
            return;
        }
        if (event == Lifecycle.Event.ON_START) {
            if (!z || logger.approveCall("register", 1)) {
                this.mReceiver.register();
                return;
            }
            return;
        }
        if (event == Lifecycle.Event.ON_RESUME) {
            if (!z || logger.approveCall("onRefresh", 1)) {
                this.mReceiver.onRefresh();
                return;
            }
            return;
        }
        if (event == Lifecycle.Event.ON_STOP) {
            if (!z || logger.approveCall("unregister", 1)) {
                this.mReceiver.unregister();
            }
        }
    }
}
