package com.jeffmony.downloader.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class WorkerThreadHandler {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final int KEEP_ALIVE = 1;
    private static final int MAXIMUM_POOL_SIZE;
    private static final ExecutorService S_THREAD_POOL_EXECUTOR;
    private static final BlockingQueue<Runnable> S_THREAD_POOL_WORK_QUEUE;
    private static final String TAG = "WorkerThreadHandler";
    private static Handler sMainHandler;

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = iAvailableProcessors;
        int i = iAvailableProcessors + 1;
        CORE_POOL_SIZE = i;
        int i2 = (iAvailableProcessors * 2) + 1;
        MAXIMUM_POOL_SIZE = i2;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        S_THREAD_POOL_WORK_QUEUE = linkedBlockingQueue;
        S_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(i, i2, 1L, TimeUnit.SECONDS, linkedBlockingQueue, new MediaWorkerThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        sMainHandler = new Handler(Looper.getMainLooper());
    }

    public static Handler getMainHandler() {
        return sMainHandler;
    }

    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0);
    }

    public static void runOnUiThread(Runnable runnable, int i) {
        if (i > 0) {
            sMainHandler.postDelayed(runnable, i);
        } else if (runningOnUiThread()) {
            runnable.run();
        } else {
            sMainHandler.post(runnable);
        }
    }

    private static boolean runningOnUiThread() {
        return sMainHandler.getLooper() == Looper.myLooper();
    }

    private static class MediaWorkerThreadFactory implements ThreadFactory {
        private MediaWorkerThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new MediaWorkerThread(runnable);
        }
    }

    private static class MediaWorkerThread extends Thread {
        public MediaWorkerThread(Runnable runnable) {
            super(runnable, "video_download_worker_pool_thread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            long jCurrentTimeMillis = System.currentTimeMillis();
            super.run();
            LogUtils.i(WorkerThreadHandler.TAG, "MediaWorkerThread execution time: " + (System.currentTimeMillis() - jCurrentTimeMillis));
        }
    }

    public static Future submitCallbackTask(Callable callable) {
        return S_THREAD_POOL_EXECUTOR.submit(callable);
    }

    public static Future submitRunnableTask(Runnable runnable) {
        return S_THREAD_POOL_EXECUTOR.submit(runnable);
    }
}
