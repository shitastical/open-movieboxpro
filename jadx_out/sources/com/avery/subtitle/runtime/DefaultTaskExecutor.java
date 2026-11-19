package com.avery.subtitle.runtime;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultTaskExecutor extends TaskExecutor {
    private Handler mMainHandler;
    private final Object mLock = new Object();
    private ExecutorService mDeskIO = Executors.newFixedThreadPool(3);

    @Override // com.avery.subtitle.runtime.TaskExecutor
    public void executeOnDeskIO(Runnable runnable) {
        this.mDeskIO.execute(runnable);
    }

    @Override // com.avery.subtitle.runtime.TaskExecutor
    public void postToMainThread(Runnable runnable) {
        if (this.mMainHandler == null) {
            synchronized (this.mLock) {
                this.mMainHandler = new Handler(Looper.getMainLooper());
            }
        }
        this.mMainHandler.post(runnable);
    }

    @Override // com.avery.subtitle.runtime.TaskExecutor
    public boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
