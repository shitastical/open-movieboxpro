package com.uber.autodispose.android.lifecycle;

import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MethodCallsLogger;

/* JADX INFO: loaded from: classes5.dex */
public class LifecycleEventsObservable_ArchLifecycleObserver_LifecycleAdapter implements GeneratedAdapter {
    final LifecycleEventsObservable.ArchLifecycleObserver mReceiver;

    LifecycleEventsObservable_ArchLifecycleObserver_LifecycleAdapter(LifecycleEventsObservable.ArchLifecycleObserver archLifecycleObserver) {
        this.mReceiver = archLifecycleObserver;
    }

    @Override // androidx.lifecycle.GeneratedAdapter
    public void callMethods(LifecycleOwner lifecycleOwner, Lifecycle.Event event, boolean z, MethodCallsLogger methodCallsLogger) {
        boolean z2 = methodCallsLogger != null;
        if (z) {
            if (!z2 || methodCallsLogger.approveCall("onStateChange", 4)) {
                this.mReceiver.onStateChange(lifecycleOwner, event);
            }
        }
    }
}
