package com.movieboxpro.android.utils.tool;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
public final class ThreadUtils {
    private static final byte TYPE_CACHED = -2;
    private static final byte TYPE_CPU = -8;
    private static final byte TYPE_IO = -4;
    private static final byte TYPE_SINGLE = -1;
    private static Executor sDeliver;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final Map<Integer, Map<Integer, ExecutorService>> TYPE_PRIORITY_POOLS = new HashMap();
    private static final Map<Task, ExecutorService> TASK_POOL_MAP = new ConcurrentHashMap();
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Timer TIMER = new Timer();

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static Handler getMainHandler() {
        return HANDLER;
    }

    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(final Runnable runnable, long delayMillis) {
        HANDLER.postDelayed(runnable, delayMillis);
    }

    public static ExecutorService getFixedPool(final int size) {
        return getPoolByTypeAndPriority(size);
    }

    public static ExecutorService getFixedPool(final int size, final int priority) {
        return getPoolByTypeAndPriority(size, priority);
    }

    public static ExecutorService getSinglePool() {
        return getPoolByTypeAndPriority(-1);
    }

    public static ExecutorService getSinglePool(final int priority) {
        return getPoolByTypeAndPriority(-1, priority);
    }

    public static ExecutorService getCachedPool() {
        return getPoolByTypeAndPriority(-2);
    }

    public static ExecutorService getCachedPool(final int priority) {
        return getPoolByTypeAndPriority(-2, priority);
    }

    public static ExecutorService getIoPool() {
        return getPoolByTypeAndPriority(-4);
    }

    public static ExecutorService getIoPool(final int priority) {
        return getPoolByTypeAndPriority(-4, priority);
    }

    public static ExecutorService getCpuPool() {
        return getPoolByTypeAndPriority(-8);
    }

    public static ExecutorService getCpuPool(final int priority) {
        return getPoolByTypeAndPriority(-8, priority);
    }

    public static <T> void executeByFixed(final int size, final Task<T> task) {
        execute(getPoolByTypeAndPriority(size), task);
    }

    public static <T> void executeByFixed(final int size, final Task<T> task, final int priority) {
        execute(getPoolByTypeAndPriority(size, priority), task);
    }

    public static <T> void executeByFixedWithDelay(final int size, final Task<T> task, final long delay, final TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(size), task, delay, unit);
    }

    public static <T> void executeByFixedWithDelay(final int size, final Task<T> task, final long delay, final TimeUnit unit, final int priority) {
        executeWithDelay(getPoolByTypeAndPriority(size, priority), task, delay, unit);
    }

    public static <T> void executeByFixedAtFixRate(final int size, final Task<T> task, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(size), task, 0L, period, unit);
    }

    public static <T> void executeByFixedAtFixRate(final int size, final Task<T> task, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(size, priority), task, 0L, period, unit);
    }

    public static <T> void executeByFixedAtFixRate(final int size, final Task<T> task, long initialDelay, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(size), task, initialDelay, period, unit);
    }

    public static <T> void executeByFixedAtFixRate(final int size, final Task<T> task, long initialDelay, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(size, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeBySingle(final Task<T> task) {
        execute(getPoolByTypeAndPriority(-1), task);
    }

    public static <T> void executeBySingle(final Task<T> task, final int priority) {
        execute(getPoolByTypeAndPriority(-1, priority), task);
    }

    public static <T> void executeBySingleWithDelay(final Task<T> task, final long delay, final TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(-1), task, delay, unit);
    }

    public static <T> void executeBySingleWithDelay(final Task<T> task, final long delay, final TimeUnit unit, final int priority) {
        executeWithDelay(getPoolByTypeAndPriority(-1, priority), task, delay, unit);
    }

    public static <T> void executeBySingleAtFixRate(final Task<T> task, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, 0L, period, unit);
    }

    public static <T> void executeBySingleAtFixRate(final Task<T> task, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, priority), task, 0L, period, unit);
    }

    public static <T> void executeBySingleAtFixRate(final Task<T> task, long initialDelay, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, initialDelay, period, unit);
    }

    public static <T> void executeBySingleAtFixRate(final Task<T> task, long initialDelay, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeByCached(final Task<T> task) {
        execute(getPoolByTypeAndPriority(-2), task);
    }

    public static <T> void executeByCached(final Task<T> task, final int priority) {
        execute(getPoolByTypeAndPriority(-2, priority), task);
    }

    public static <T> void executeByCachedWithDelay(final Task<T> task, final long delay, final TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(-2), task, delay, unit);
    }

    public static <T> void executeByCachedWithDelay(final Task<T> task, final long delay, final TimeUnit unit, final int priority) {
        executeWithDelay(getPoolByTypeAndPriority(-2, priority), task, delay, unit);
    }

    public static <T> void executeByCachedAtFixRate(final Task<T> task, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, 0L, period, unit);
    }

    public static <T> void executeByCachedAtFixRate(final Task<T> task, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, priority), task, 0L, period, unit);
    }

    public static <T> void executeByCachedAtFixRate(final Task<T> task, long initialDelay, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, initialDelay, period, unit);
    }

    public static <T> void executeByCachedAtFixRate(final Task<T> task, long initialDelay, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeByIo(final Task<T> task) {
        execute(getPoolByTypeAndPriority(-4), task);
    }

    public static <T> void executeByIo(final Task<T> task, final int priority) {
        execute(getPoolByTypeAndPriority(-4, priority), task);
    }

    public static <T> void executeByIoWithDelay(final Task<T> task, final long delay, final TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(-4), task, delay, unit);
    }

    public static <T> void executeByIoWithDelay(final Task<T> task, final long delay, final TimeUnit unit, final int priority) {
        executeWithDelay(getPoolByTypeAndPriority(-4, priority), task, delay, unit);
    }

    public static <T> void executeByIoAtFixRate(final Task<T> task, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, 0L, period, unit);
    }

    public static <T> void executeByIoAtFixRate(final Task<T> task, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, priority), task, 0L, period, unit);
    }

    public static <T> void executeByIoAtFixRate(final Task<T> task, long initialDelay, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, initialDelay, period, unit);
    }

    public static <T> void executeByIoAtFixRate(final Task<T> task, long initialDelay, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeByCpu(final Task<T> task) {
        execute(getPoolByTypeAndPriority(-8), task);
    }

    public static <T> void executeByCpu(final Task<T> task, final int priority) {
        execute(getPoolByTypeAndPriority(-8, priority), task);
    }

    public static <T> void executeByCpuWithDelay(final Task<T> task, final long delay, final TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(-8), task, delay, unit);
    }

    public static <T> void executeByCpuWithDelay(final Task<T> task, final long delay, final TimeUnit unit, final int priority) {
        executeWithDelay(getPoolByTypeAndPriority(-8, priority), task, delay, unit);
    }

    public static <T> void executeByCpuAtFixRate(final Task<T> task, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, 0L, period, unit);
    }

    public static <T> void executeByCpuAtFixRate(final Task<T> task, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, priority), task, 0L, period, unit);
    }

    public static <T> void executeByCpuAtFixRate(final Task<T> task, long initialDelay, final long period, final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, initialDelay, period, unit);
    }

    public static <T> void executeByCpuAtFixRate(final Task<T> task, long initialDelay, final long period, final TimeUnit unit, final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeByCustom(final ExecutorService pool, final Task<T> task) {
        execute(pool, task);
    }

    public static <T> void executeByCustomWithDelay(final ExecutorService pool, final Task<T> task, final long delay, final TimeUnit unit) {
        executeWithDelay(pool, task, delay, unit);
    }

    public static <T> void executeByCustomAtFixRate(final ExecutorService pool, final Task<T> task, final long period, final TimeUnit unit) {
        executeAtFixedRate(pool, task, 0L, period, unit);
    }

    public static <T> void executeByCustomAtFixRate(final ExecutorService pool, final Task<T> task, long initialDelay, final long period, final TimeUnit unit) {
        executeAtFixedRate(pool, task, initialDelay, period, unit);
    }

    public static void cancel(final Task task) {
        if (task == null) {
            return;
        }
        task.cancel();
    }

    public static void cancel(final Task... tasks) {
        if (tasks == null || tasks.length == 0) {
            return;
        }
        for (Task task : tasks) {
            if (task != null) {
                task.cancel();
            }
        }
    }

    public static void cancel(final List<Task> tasks) {
        if (tasks == null || tasks.size() == 0) {
            return;
        }
        for (Task task : tasks) {
            if (task != null) {
                task.cancel();
            }
        }
    }

    public static void cancel(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor4Util) {
            for (Map.Entry<Task, ExecutorService> entry : TASK_POOL_MAP.entrySet()) {
                if (entry.getValue() == executorService) {
                    cancel(entry.getKey());
                }
            }
            return;
        }
        Log.e("ThreadUtils", "The executorService is not ThreadUtils's pool.");
    }

    public static void setDeliver(final Executor deliver) {
        sDeliver = deliver;
    }

    private static <T> void execute(final ExecutorService pool, final Task<T> task) {
        execute(pool, task, 0L, 0L, null);
    }

    private static <T> void executeWithDelay(final ExecutorService pool, final Task<T> task, final long delay, final TimeUnit unit) {
        execute(pool, task, delay, 0L, unit);
    }

    private static <T> void executeAtFixedRate(final ExecutorService pool, final Task<T> task, long delay, final long period, final TimeUnit unit) {
        execute(pool, task, delay, period, unit);
    }

    private static <T> void execute(final ExecutorService pool, final Task<T> task, long delay, final long period, final TimeUnit unit) {
        Map<Task, ExecutorService> map = TASK_POOL_MAP;
        synchronized (map) {
            if (map.get(task) != null) {
                Log.e("ThreadUtils", "Task can only be executed once.");
                return;
            }
            map.put(task, pool);
            if (period != 0) {
                task.setSchedule(true);
                TIMER.scheduleAtFixedRate(new TimerTask() { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        pool.execute(task);
                    }
                }, unit.toMillis(delay), unit.toMillis(period));
            } else if (delay == 0) {
                pool.execute(task);
            } else {
                TIMER.schedule(new TimerTask() { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        pool.execute(task);
                    }
                }, unit.toMillis(delay));
            }
        }
    }

    private static ExecutorService getPoolByTypeAndPriority(final int type) {
        return getPoolByTypeAndPriority(type, 5);
    }

    private static ExecutorService getPoolByTypeAndPriority(final int type, final int priority) {
        ExecutorService executorServiceCreatePool;
        Map<Integer, Map<Integer, ExecutorService>> map = TYPE_PRIORITY_POOLS;
        synchronized (map) {
            Map<Integer, ExecutorService> map2 = map.get(Integer.valueOf(type));
            if (map2 == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                executorServiceCreatePool = ThreadPoolExecutor4Util.createPool(type, priority);
                concurrentHashMap.put(Integer.valueOf(priority), executorServiceCreatePool);
                map.put(Integer.valueOf(type), concurrentHashMap);
            } else {
                executorServiceCreatePool = map2.get(Integer.valueOf(priority));
                if (executorServiceCreatePool == null) {
                    executorServiceCreatePool = ThreadPoolExecutor4Util.createPool(type, priority);
                    map2.put(Integer.valueOf(priority), executorServiceCreatePool);
                }
            }
        }
        return executorServiceCreatePool;
    }

    static final class ThreadPoolExecutor4Util extends ThreadPoolExecutor {
        private final AtomicInteger mSubmittedCount;
        private LinkedBlockingQueue4Util mWorkQueue;

        /* JADX INFO: Access modifiers changed from: private */
        public static ExecutorService createPool(final int type, final int priority) {
            if (type == -8) {
                return new ThreadPoolExecutor4Util(ThreadUtils.CPU_COUNT + 1, (ThreadUtils.CPU_COUNT * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), new UtilsThreadFactory("cpu", priority));
            }
            if (type == -4) {
                return new ThreadPoolExecutor4Util((ThreadUtils.CPU_COUNT * 2) + 1, (ThreadUtils.CPU_COUNT * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("io", priority));
            }
            if (type == -2) {
                return new ThreadPoolExecutor4Util(0, 128, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), new UtilsThreadFactory("cached", priority));
            }
            if (type == -1) {
                return new ThreadPoolExecutor4Util(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("single", priority));
            }
            return new ThreadPoolExecutor4Util(type, type, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("fixed(" + type + ")", priority));
        }

        ThreadPoolExecutor4Util(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, LinkedBlockingQueue4Util workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
            this.mSubmittedCount = new AtomicInteger();
            workQueue.mPool = this;
            this.mWorkQueue = workQueue;
        }

        private int getSubmittedCount() {
            return this.mSubmittedCount.get();
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void afterExecute(Runnable r, Throwable t) {
            this.mSubmittedCount.decrementAndGet();
            super.afterExecute(r, t);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
        public void execute(Runnable command) {
            if (isShutdown()) {
                return;
            }
            this.mSubmittedCount.incrementAndGet();
            try {
                super.execute(command);
            } catch (RejectedExecutionException unused) {
                Log.e("ThreadUtils", "This will not happen!");
                this.mWorkQueue.offer(command);
            } catch (Throwable unused2) {
                this.mSubmittedCount.decrementAndGet();
            }
        }
    }

    private static final class LinkedBlockingQueue4Util extends LinkedBlockingQueue<Runnable> {
        private int mCapacity;
        private volatile ThreadPoolExecutor4Util mPool;

        LinkedBlockingQueue4Util() {
            this.mCapacity = Integer.MAX_VALUE;
        }

        LinkedBlockingQueue4Util(boolean isAddSubThreadFirstThenAddQueue) {
            this.mCapacity = Integer.MAX_VALUE;
            if (isAddSubThreadFirstThenAddQueue) {
                this.mCapacity = 0;
            }
        }

        LinkedBlockingQueue4Util(int capacity) {
            this.mCapacity = capacity;
        }

        @Override // java.util.concurrent.LinkedBlockingQueue, java.util.Queue, java.util.concurrent.BlockingQueue
        public boolean offer(Runnable runnable) {
            if (this.mCapacity > size() || this.mPool == null || this.mPool.getPoolSize() >= this.mPool.getMaximumPoolSize()) {
                return super.offer(runnable);
            }
            return false;
        }
    }

    static final class UtilsThreadFactory extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private static final long serialVersionUID = -9209200509960368598L;
        private final boolean isDaemon;
        private final String namePrefix;
        private final int priority;

        UtilsThreadFactory(String prefix, int priority) {
            this(prefix, priority, false);
        }

        UtilsThreadFactory(String prefix, int priority, boolean isDaemon) {
            this.namePrefix = prefix + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
            this.priority = priority;
            this.isDaemon = isDaemon;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(this, r, this.namePrefix + getAndIncrement()) { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.UtilsThreadFactory.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        super.run();
                    } catch (Throwable th) {
                        Log.e("ThreadUtils", "Request threw uncaught throwable", th);
                    }
                }
            };
            thread.setDaemon(this.isDaemon);
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(this) { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.UtilsThreadFactory.2
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(e);
                }
            });
            thread.setPriority(this.priority);
            return thread;
        }
    }

    public static abstract class SimpleTask<T> extends Task<T> {
        @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
        public void onCancel() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
        public void onFail(Throwable t) {
            Log.e("ThreadUtils", "onFail: ", t);
        }
    }

    public static abstract class Task<T> implements Runnable {
        private static final int CANCELLED = 4;
        private static final int COMPLETING = 3;
        private static final int EXCEPTIONAL = 2;
        private static final int INTERRUPTED = 5;
        private static final int NEW = 0;
        private static final int RUNNING = 1;
        private static final int TIMEOUT = 6;
        private Executor deliver;
        private volatile boolean isSchedule;
        private OnTimeoutListener mTimeoutListener;
        private long mTimeoutMillis;
        private Timer mTimer;
        private volatile Thread runner;
        private final AtomicInteger state = new AtomicInteger(0);

        public interface OnTimeoutListener {
            void onTimeout();
        }

        public abstract T doInBackground() throws Throwable;

        public abstract void onCancel();

        public abstract void onFail(Throwable t);

        public abstract void onSuccess(T result);

        @Override // java.lang.Runnable
        public void run() {
            if (this.isSchedule) {
                if (this.runner == null) {
                    if (!this.state.compareAndSet(0, 1)) {
                        return;
                    }
                    this.runner = Thread.currentThread();
                    if (this.mTimeoutListener != null) {
                        Log.w("ThreadUtils", "Scheduled task doesn't support timeout.");
                    }
                } else if (this.state.get() != 1) {
                    return;
                }
            } else {
                if (!this.state.compareAndSet(0, 1)) {
                    return;
                }
                this.runner = Thread.currentThread();
                if (this.mTimeoutListener != null) {
                    Timer timer = new Timer();
                    this.mTimer = timer;
                    timer.schedule(new TimerTask() { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.Task.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            if (Task.this.isDone() || Task.this.mTimeoutListener == null) {
                                return;
                            }
                            Task.this.timeout();
                            Task.this.mTimeoutListener.onTimeout();
                        }
                    }, this.mTimeoutMillis);
                }
            }
            try {
                final T tDoInBackground = doInBackground();
                if (this.isSchedule) {
                    if (this.state.get() != 1) {
                        return;
                    }
                    getDeliver().execute(new Runnable() { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.Task.2
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            Task.this.onSuccess(tDoInBackground);
                        }
                    });
                } else if (this.state.compareAndSet(1, 3)) {
                    getDeliver().execute(new Runnable() { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.Task.3
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            Task.this.onSuccess(tDoInBackground);
                            Task.this.onDone();
                        }
                    });
                }
            } catch (InterruptedException unused) {
                this.state.compareAndSet(4, 5);
            } catch (Throwable th) {
                if (this.state.compareAndSet(1, 2)) {
                    getDeliver().execute(new Runnable() { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.Task.4
                        @Override // java.lang.Runnable
                        public void run() {
                            Task.this.onFail(th);
                            Task.this.onDone();
                        }
                    });
                }
            }
        }

        public void cancel() {
            cancel(true);
        }

        public void cancel(boolean mayInterruptIfRunning) {
            synchronized (this.state) {
                if (this.state.get() > 1) {
                    return;
                }
                this.state.set(4);
                if (mayInterruptIfRunning && this.runner != null) {
                    this.runner.interrupt();
                }
                getDeliver().execute(new Runnable() { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.Task.5
                    @Override // java.lang.Runnable
                    public void run() {
                        Task.this.onCancel();
                        Task.this.onDone();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void timeout() {
            synchronized (this.state) {
                if (this.state.get() > 1) {
                    return;
                }
                this.state.set(6);
                if (this.runner != null) {
                    this.runner.interrupt();
                }
                onDone();
            }
        }

        public boolean isCanceled() {
            return this.state.get() >= 4;
        }

        public boolean isDone() {
            return this.state.get() > 1;
        }

        public Task<T> setDeliver(Executor deliver) {
            this.deliver = deliver;
            return this;
        }

        public Task<T> setTimeout(final long timeoutMillis, final OnTimeoutListener listener) {
            this.mTimeoutMillis = timeoutMillis;
            this.mTimeoutListener = listener;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSchedule(boolean isSchedule) {
            this.isSchedule = isSchedule;
        }

        private Executor getDeliver() {
            Executor executor = this.deliver;
            return executor == null ? ThreadUtils.getGlobalDeliver() : executor;
        }

        protected void onDone() {
            ThreadUtils.TASK_POOL_MAP.remove(this);
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer = null;
                this.mTimeoutListener = null;
            }
        }
    }

    public static class SyncValue<T> {
        private T mValue;
        private CountDownLatch mLatch = new CountDownLatch(1);
        private AtomicBoolean mFlag = new AtomicBoolean();

        public void setValue(T value) {
            if (this.mFlag.compareAndSet(false, true)) {
                this.mValue = value;
                this.mLatch.countDown();
            }
        }

        public T getValue() {
            if (!this.mFlag.get()) {
                try {
                    this.mLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.mValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Executor getGlobalDeliver() {
        if (sDeliver == null) {
            sDeliver = new Executor() { // from class: com.movieboxpro.android.utils.tool.ThreadUtils.3
                @Override // java.util.concurrent.Executor
                public void execute(Runnable command) {
                    ThreadUtils.runOnUiThread(command);
                }
            };
        }
        return sDeliver;
    }
}
