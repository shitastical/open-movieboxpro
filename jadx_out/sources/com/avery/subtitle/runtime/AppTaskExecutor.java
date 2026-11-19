package com.avery.subtitle.runtime;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public class AppTaskExecutor extends TaskExecutor {
    private static volatile AppTaskExecutor sInstance;
    private TaskExecutor mDefaultTaskExecutor;
    private TaskExecutor mDelegate;
    private static Executor sDeskIO = new Executor() { // from class: com.avery.subtitle.runtime.AppTaskExecutor.1
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            AppTaskExecutor.getInstance().executeOnDeskIO(runnable);
        }
    };
    private static Executor sMainThread = new Executor() { // from class: com.avery.subtitle.runtime.AppTaskExecutor.2
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            AppTaskExecutor.getInstance().executeOnMainThread(runnable);
        }
    };

    public static TaskExecutor getInstance() {
        if (sInstance == null) {
            synchronized (AppTaskExecutor.class) {
                sInstance = new AppTaskExecutor();
            }
        }
        return sInstance;
    }

    private AppTaskExecutor() {
        DefaultTaskExecutor defaultTaskExecutor = new DefaultTaskExecutor();
        this.mDefaultTaskExecutor = defaultTaskExecutor;
        this.mDelegate = defaultTaskExecutor;
    }

    public void setDelegate(TaskExecutor taskExecutor) {
        if (taskExecutor == null) {
            taskExecutor = this.mDefaultTaskExecutor;
        }
        this.mDelegate = taskExecutor;
    }

    @Override // com.avery.subtitle.runtime.TaskExecutor
    public void executeOnDeskIO(Runnable runnable) {
        this.mDelegate.executeOnDeskIO(runnable);
    }

    @Override // com.avery.subtitle.runtime.TaskExecutor
    public void executeOnMainThread(Runnable runnable) {
        this.mDelegate.executeOnMainThread(runnable);
    }

    @Override // com.avery.subtitle.runtime.TaskExecutor
    public void postToMainThread(Runnable runnable) {
        this.mDelegate.postToMainThread(runnable);
    }

    @Override // com.avery.subtitle.runtime.TaskExecutor
    public boolean isMainThread() {
        return this.mDelegate.isMainThread();
    }

    public static Executor deskIO() {
        return sDeskIO;
    }

    public static Executor mainThread() {
        return sMainThread;
    }
}
