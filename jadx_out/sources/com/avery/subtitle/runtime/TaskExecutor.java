package com.avery.subtitle.runtime;

/* JADX INFO: loaded from: classes4.dex */
public abstract class TaskExecutor {
    public abstract void executeOnDeskIO(Runnable runnable);

    public abstract boolean isMainThread();

    public abstract void postToMainThread(Runnable runnable);

    public void executeOnMainThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            postToMainThread(runnable);
        }
    }
}
