package com.kunminx.architecture.ui.callback;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class ProtectedUnPeekLiveDataV3<T> extends LiveData<T> {
    protected boolean isAllowNullValue;
    private boolean isCleaning;
    private boolean isDelaying;
    private TimerTask mTask;
    private boolean hasHandled = true;
    protected int DELAY_TO_CLEAR_EVENT = 1000;
    private Timer mTimer = new Timer();
    protected boolean isAllowToClear = true;

    @Override // androidx.lifecycle.LiveData
    public void observe(LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        super.observe(lifecycleOwner, new Observer() { // from class: com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveDataV3$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m711xffe2dd14(observer, obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$observe$0$com-kunminx-architecture-ui-callback-ProtectedUnPeekLiveDataV3, reason: not valid java name */
    /* synthetic */ void m711xffe2dd14(Observer observer, Object obj) {
        if (this.isCleaning) {
            this.hasHandled = true;
            this.isDelaying = false;
            this.isCleaning = false;
        } else if (!this.hasHandled) {
            this.hasHandled = true;
            this.isDelaying = true;
            observer.onChanged(obj);
        } else if (this.isDelaying) {
            observer.onChanged(obj);
        }
    }

    @Override // androidx.lifecycle.LiveData
    public void observeForever(Observer<? super T> observer) {
        throw new IllegalArgumentException("Do not use observeForever for communication between pages to avoid lifecycle security issues");
    }

    @Override // androidx.lifecycle.LiveData
    protected void setValue(T t) {
        if (this.isCleaning || this.isAllowNullValue || t != null) {
            this.hasHandled = false;
            this.isDelaying = false;
            super.setValue(t);
            TimerTask timerTask = this.mTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.mTimer.purge();
            }
            if (t != null) {
                TimerTask timerTask2 = new TimerTask() { // from class: com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveDataV3.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        ProtectedUnPeekLiveDataV3.this.clear();
                    }
                };
                this.mTask = timerTask2;
                this.mTimer.schedule(timerTask2, this.DELAY_TO_CLEAR_EVENT);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clear() {
        if (this.isAllowToClear) {
            this.isCleaning = true;
            super.postValue(null);
        } else {
            this.hasHandled = true;
            this.isDelaying = false;
        }
    }
}
