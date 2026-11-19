package master.flame.danmaku.controller;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.ICacheManager;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDrawingCache;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.DrawingCache;
import master.flame.danmaku.danmaku.model.android.DrawingCachePoolManager;
import master.flame.danmaku.danmaku.model.objectpool.Pool;
import master.flame.danmaku.danmaku.model.objectpool.Pools;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.util.DanmakuUtils;
import master.flame.danmaku.danmaku.util.SystemClock;
import tv.cjump.jni.NativeBitmapFactory;

/* JADX INFO: loaded from: classes6.dex */
public class CacheManagingDrawTask extends DrawTask {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_CACHE_SCREEN_SIZE = 3;
    private CacheManager mCacheManager;
    private DanmakuTimer mCacheTimer;
    private final Object mDrawingNotify;
    private int mMaxCacheSize;
    private int mRemaininCacheCount;

    public CacheManagingDrawTask(DanmakuTimer danmakuTimer, DanmakuContext danmakuContext, IDrawTask.TaskListener taskListener, int i) {
        super(danmakuTimer, danmakuContext, taskListener);
        this.mMaxCacheSize = 2;
        this.mDrawingNotify = new Object();
        NativeBitmapFactory.loadLibs();
        this.mMaxCacheSize = i;
        if (NativeBitmapFactory.isInNativeAlloc()) {
            this.mMaxCacheSize = i * 2;
        }
        this.mCacheManager = new CacheManager(i, 3);
        this.mRenderer.setCacheManager(this.mCacheManager);
    }

    @Override // master.flame.danmaku.controller.DrawTask
    protected void initTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
        DanmakuTimer danmakuTimer2 = new DanmakuTimer();
        this.mCacheTimer = danmakuTimer2;
        danmakuTimer2.update(danmakuTimer.currMillisecond);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void addDanmaku(BaseDanmaku baseDanmaku) {
        super.addDanmaku(baseDanmaku);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager == null) {
            return;
        }
        cacheManager.addDanmaku(baseDanmaku);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager == null) {
            super.invalidateDanmaku(baseDanmaku, z);
        } else {
            cacheManager.invalidateDanmaku(baseDanmaku, z);
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void removeAllDanmakus(boolean z) {
        super.removeAllDanmakus(z);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.requestClearAll();
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask
    protected void onDanmakuRemoved(BaseDanmaku baseDanmaku) {
        super.onDanmakuRemoved(baseDanmaku);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            int i = this.mRemaininCacheCount + 1;
            this.mRemaininCacheCount = i;
            if (i > 5) {
                cacheManager.requestClearTimeout();
                this.mRemaininCacheCount = 0;
                return;
            }
            return;
        }
        IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
        if (drawingCache != null) {
            if (drawingCache.hasReferences()) {
                drawingCache.decreaseReference();
            } else {
                drawingCache.destroy();
            }
            baseDanmaku.cache = null;
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public IRenderer.RenderingState draw(AbsDisplayer absDisplayer) {
        IRenderer.RenderingState renderingStateDraw = super.draw(absDisplayer);
        synchronized (this.mDrawingNotify) {
            this.mDrawingNotify.notify();
        }
        if (renderingStateDraw != null && this.mCacheManager != null && renderingStateDraw.totalDanmakuCount - renderingStateDraw.lastTotalDanmakuCount < -20) {
            this.mCacheManager.requestClearTimeout();
            this.mCacheManager.requestBuild(-this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
        }
        return renderingStateDraw;
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void seek(long j) {
        super.seek(j);
        if (this.mCacheManager == null) {
            start();
        }
        this.mCacheManager.seek(j);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void start() {
        super.start();
        NativeBitmapFactory.loadLibs();
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager == null) {
            CacheManager cacheManager2 = new CacheManager(this.mMaxCacheSize, 3);
            this.mCacheManager = cacheManager2;
            cacheManager2.begin();
            this.mRenderer.setCacheManager(this.mCacheManager);
            return;
        }
        cacheManager.resume();
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void quit() {
        super.quit();
        reset();
        this.mRenderer.setCacheManager(null);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.end();
            this.mCacheManager = null;
        }
        NativeBitmapFactory.releaseLibs();
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void prepare() {
        loadDanmakus(this.mParser);
        this.mCacheManager.begin();
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void onPlayStateChanged(int i) {
        super.onPlayStateChanged(i);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.onPlayStateChanged(i);
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void requestSync(long j, long j2, long j3) {
        super.requestSync(j, j2, j3);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.seek(j2);
        }
    }

    public class CacheManager implements ICacheManager {
        public static final byte RESULT_FAILED = 1;
        public static final byte RESULT_FAILED_OVERSIZE = 2;
        public static final byte RESULT_SUCCESS = 0;
        private static final String TAG = "CacheManager";
        Pool<DrawingCache> mCachePool;
        DrawingCachePoolManager mCachePoolManager;
        Danmakus mCaches = new Danmakus();
        private boolean mEndFlag;
        private CacheHandler mHandler;
        private int mMaxSize;
        private int mRealSize;
        private int mScreenSize;
        public HandlerThread mThread;

        public CacheManager(int i, int i2) {
            DrawingCachePoolManager drawingCachePoolManager = new DrawingCachePoolManager();
            this.mCachePoolManager = drawingCachePoolManager;
            this.mCachePool = Pools.finitePool(drawingCachePoolManager, 800);
            this.mEndFlag = false;
            this.mRealSize = 0;
            this.mMaxSize = i;
            this.mScreenSize = i2;
        }

        public void seek(long j) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.requestCancelCaching();
            this.mHandler.removeMessages(3);
            this.mHandler.obtainMessage(5, Long.valueOf(j)).sendToTarget();
        }

        @Override // master.flame.danmaku.danmaku.model.ICacheManager
        public void addDanmaku(BaseDanmaku baseDanmaku) {
            if (this.mHandler != null) {
                if (baseDanmaku.isLive) {
                    if (baseDanmaku.forceBuildCacheInSameThread) {
                        if (baseDanmaku.isTimeOut()) {
                            return;
                        }
                        this.mHandler.createCache(baseDanmaku);
                        return;
                    }
                    this.mHandler.obtainMessage(18, baseDanmaku).sendToTarget();
                    return;
                }
                this.mHandler.obtainMessage(2, baseDanmaku).sendToTarget();
            }
        }

        public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.requestCancelCaching();
                this.mHandler.obtainMessage(17, baseDanmaku).sendToTarget();
            }
        }

        public void begin() {
            this.mEndFlag = false;
            if (this.mThread == null) {
                HandlerThread handlerThread = new HandlerThread("DFM Cache-Building Thread");
                this.mThread = handlerThread;
                handlerThread.start();
            }
            if (this.mHandler == null) {
                this.mHandler = new CacheHandler(this.mThread.getLooper());
            }
            this.mHandler.begin();
        }

        public void end() {
            this.mEndFlag = true;
            synchronized (CacheManagingDrawTask.this.mDrawingNotify) {
                CacheManagingDrawTask.this.mDrawingNotify.notifyAll();
            }
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.pause();
                this.mHandler = null;
            }
            HandlerThread handlerThread = this.mThread;
            if (handlerThread != null) {
                try {
                    handlerThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.mThread.quit();
                this.mThread = null;
            }
        }

        public void resume() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.resume();
            } else {
                begin();
            }
        }

        public void onPlayStateChanged(int i) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.onPlayStateChanged(i == 1);
            }
        }

        public float getPoolPercent() {
            int i = this.mMaxSize;
            if (i == 0) {
                return 0.0f;
            }
            return this.mRealSize / i;
        }

        public boolean isPoolFull() {
            return this.mRealSize + 5120 >= this.mMaxSize;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void evictAll() {
            Danmakus danmakus = this.mCaches;
            if (danmakus != null) {
                IDanmakuIterator it = danmakus.iterator();
                while (it.hasNext()) {
                    entryRemoved(true, it.next(), null);
                }
                this.mCaches.clear();
            }
            this.mRealSize = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void evictAllNotInScreen() {
            evictAllNotInScreen(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void evictAllNotInScreen(boolean z) {
            Danmakus danmakus = this.mCaches;
            if (danmakus != null) {
                IDanmakuIterator it = danmakus.iterator();
                while (it.hasNext()) {
                    BaseDanmaku next = it.next();
                    IDrawingCache<?> iDrawingCache = next.cache;
                    boolean z2 = iDrawingCache != null && iDrawingCache.hasReferences();
                    if (z && z2) {
                        if (iDrawingCache.get() != null) {
                            this.mRealSize -= iDrawingCache.size();
                            iDrawingCache.destroy();
                        }
                        entryRemoved(true, next, null);
                        it.remove();
                    } else if (next.isOutside()) {
                        entryRemoved(true, next, null);
                        it.remove();
                    }
                }
            }
            this.mRealSize = 0;
        }

        protected void entryRemoved(boolean z, BaseDanmaku baseDanmaku, BaseDanmaku baseDanmaku2) {
            IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
            if (drawingCache != null) {
                long jClearCache = clearCache(baseDanmaku);
                if (baseDanmaku.isTimeOut()) {
                    CacheManagingDrawTask.this.mContext.getDisplayer().getCacheStuffer().releaseResource(baseDanmaku);
                }
                if (jClearCache <= 0) {
                    return;
                }
                this.mRealSize = (int) (((long) this.mRealSize) - jClearCache);
                this.mCachePool.release((DrawingCache) drawingCache);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long clearCache(BaseDanmaku baseDanmaku) {
            IDrawingCache<?> iDrawingCache = baseDanmaku.cache;
            if (iDrawingCache == null) {
                return 0L;
            }
            if (iDrawingCache.hasReferences()) {
                iDrawingCache.decreaseReference();
                baseDanmaku.cache = null;
                return 0L;
            }
            long jSizeOf = sizeOf(baseDanmaku);
            iDrawingCache.destroy();
            baseDanmaku.cache = null;
            return jSizeOf;
        }

        protected int sizeOf(BaseDanmaku baseDanmaku) {
            if (baseDanmaku.cache == null || baseDanmaku.cache.hasReferences()) {
                return 0;
            }
            return baseDanmaku.cache.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCachePool() {
            while (true) {
                DrawingCache drawingCache = (DrawingCache) this.mCachePool.acquire();
                if (drawingCache == null) {
                    return;
                } else {
                    drawingCache.destroy();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean push(BaseDanmaku baseDanmaku, int i, boolean z) {
            while (this.mRealSize + i > this.mMaxSize && this.mCaches.size() > 0) {
                BaseDanmaku baseDanmakuFirst = this.mCaches.first();
                if (!baseDanmakuFirst.isTimeOut()) {
                    if (z) {
                        break;
                    }
                    return false;
                }
                entryRemoved(false, baseDanmakuFirst, baseDanmaku);
                this.mCaches.removeItem(baseDanmakuFirst);
            }
            this.mCaches.addItem(baseDanmaku);
            this.mRealSize += i;
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimeOutCaches() {
            clearTimeOutCaches(CacheManagingDrawTask.this.mTimer.currMillisecond);
        }

        private void clearTimeOutCaches(long j) {
            IDanmakuIterator it = this.mCaches.iterator();
            while (it.hasNext() && !this.mEndFlag) {
                BaseDanmaku next = it.next();
                if (!next.isTimeOut()) {
                    return;
                }
                synchronized (CacheManagingDrawTask.this.mDrawingNotify) {
                    try {
                        CacheManagingDrawTask.this.mDrawingNotify.wait(30L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                entryRemoved(false, next, null);
                it.remove();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BaseDanmaku findReuseableCache(BaseDanmaku baseDanmaku, boolean z, int i) {
            IDanmakuIterator it = this.mCaches.iterator();
            int i2 = 0;
            int slopPixel = !z ? CacheManagingDrawTask.this.mDisp.getSlopPixel() * 2 : 0;
            while (it.hasNext()) {
                int i3 = i2 + 1;
                if (i2 >= i) {
                    return null;
                }
                BaseDanmaku next = it.next();
                IDrawingCache<?> drawingCache = next.getDrawingCache();
                if (drawingCache != null && drawingCache.get() != null) {
                    if (next.paintWidth == baseDanmaku.paintWidth && next.paintHeight == baseDanmaku.paintHeight && next.underlineColor == baseDanmaku.underlineColor && next.borderColor == baseDanmaku.borderColor && next.textColor == baseDanmaku.textColor && next.text.equals(baseDanmaku.text) && next.tag == baseDanmaku.tag) {
                        return next;
                    }
                    if (z) {
                        continue;
                    } else {
                        if (!next.isTimeOut()) {
                            return null;
                        }
                        if (drawingCache.hasReferences()) {
                            continue;
                        } else {
                            float fWidth = drawingCache.width() - baseDanmaku.paintWidth;
                            float fHeight = drawingCache.height() - baseDanmaku.paintHeight;
                            if (fWidth >= 0.0f) {
                                float f = slopPixel;
                                if (fWidth <= f && fHeight >= 0.0f && fHeight <= f) {
                                    return next;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                i2 = i3;
            }
            return null;
        }

        public class CacheHandler extends Handler {
            public static final int ADD_DANMAKKU = 2;
            public static final int BIND_CACHE = 18;
            public static final int BUILD_CACHES = 3;
            public static final int CLEAR_ALL_CACHES = 7;
            public static final int CLEAR_OUTSIDE_CACHES = 8;
            public static final int CLEAR_OUTSIDE_CACHES_AND_RESET = 9;
            public static final int CLEAR_TIMEOUT_CACHES = 4;
            public static final int DISABLE_CANCEL_FLAG = 19;
            public static final int DISPATCH_ACTIONS = 16;
            private static final int PREPARE = 1;
            public static final int QUIT = 6;
            public static final int REBUILD_CACHE = 17;
            public static final int SEEK = 5;
            private boolean mCancelFlag;
            private boolean mIsPlayerPause;
            private boolean mPause;
            private boolean mSeekedFlag;

            public CacheHandler(Looper looper) {
                super(looper);
            }

            public void requestCancelCaching() {
                this.mCancelFlag = true;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                switch (i) {
                    case 1:
                        CacheManager.this.evictAllNotInScreen();
                        for (int i2 = 0; i2 < 300; i2++) {
                            CacheManager.this.mCachePool.release(new DrawingCache());
                        }
                        break;
                    case 2:
                        addDanmakuAndBuildCache((BaseDanmaku) message.obj);
                        return;
                    case 3:
                        removeMessages(3);
                        boolean z = !(CacheManagingDrawTask.this.mTaskListener == null || CacheManagingDrawTask.this.mReadyState) || this.mSeekedFlag;
                        prepareCaches(z);
                        if (z) {
                            this.mSeekedFlag = false;
                        }
                        if (CacheManagingDrawTask.this.mTaskListener == null || CacheManagingDrawTask.this.mReadyState) {
                            return;
                        }
                        CacheManagingDrawTask.this.mTaskListener.ready();
                        CacheManagingDrawTask.this.mReadyState = true;
                        return;
                    case 4:
                        CacheManager.this.clearTimeOutCaches();
                        return;
                    case 5:
                        Long l = (Long) message.obj;
                        if (l != null) {
                            long jLongValue = l.longValue();
                            long j = CacheManagingDrawTask.this.mCacheTimer.currMillisecond;
                            CacheManagingDrawTask.this.mCacheTimer.update(jLongValue);
                            this.mSeekedFlag = true;
                            long firstCacheTime = CacheManager.this.getFirstCacheTime();
                            if (jLongValue > j || firstCacheTime - jLongValue > CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) {
                                CacheManager.this.evictAllNotInScreen();
                            } else {
                                CacheManager.this.clearTimeOutCaches();
                            }
                            prepareCaches(true);
                            resume();
                            return;
                        }
                        return;
                    case 6:
                        removeCallbacksAndMessages(null);
                        this.mPause = true;
                        CacheManager.this.evictAll();
                        CacheManager.this.clearCachePool();
                        getLooper().quit();
                        return;
                    case 7:
                        CacheManager.this.evictAll();
                        CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond - CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                        this.mSeekedFlag = true;
                        return;
                    case 8:
                        CacheManager.this.evictAllNotInScreen(true);
                        CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                        return;
                    case 9:
                        CacheManager.this.evictAllNotInScreen(true);
                        CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                        CacheManagingDrawTask.this.requestClear();
                        return;
                    default:
                        switch (i) {
                            case 17:
                                BaseDanmaku baseDanmaku = (BaseDanmaku) message.obj;
                                if (baseDanmaku != null) {
                                    IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
                                    if ((baseDanmaku.requestFlags & 1) == 0 && drawingCache != null && drawingCache.get() != null && !drawingCache.hasReferences()) {
                                        baseDanmaku.cache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, (DrawingCache) baseDanmaku.cache);
                                        CacheManager.this.push(baseDanmaku, 0, true);
                                    } else if (baseDanmaku.isLive) {
                                        CacheManager.this.clearCache(baseDanmaku);
                                        createCache(baseDanmaku);
                                    } else {
                                        if (drawingCache != null && drawingCache.hasReferences()) {
                                            drawingCache.destroy();
                                        }
                                        CacheManager.this.entryRemoved(true, baseDanmaku, null);
                                        addDanmakuAndBuildCache(baseDanmaku);
                                    }
                                }
                                break;
                            case 18:
                                BaseDanmaku baseDanmaku2 = (BaseDanmaku) message.obj;
                                if (!baseDanmaku2.isTimeOut()) {
                                    createCache(baseDanmaku2);
                                    if (baseDanmaku2.cache != null) {
                                        CacheManager.this.push(baseDanmaku2, baseDanmaku2.cache.size(), true);
                                    }
                                }
                                break;
                            case 19:
                                this.mCancelFlag = false;
                                break;
                        }
                        return;
                }
                long jDispatchAction = dispatchAction();
                if (jDispatchAction <= 0) {
                    jDispatchAction = CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION / 2;
                }
                sendEmptyMessageDelayed(16, jDispatchAction);
            }

            private long dispatchAction() {
                if (CacheManagingDrawTask.this.mCacheTimer.currMillisecond <= CacheManagingDrawTask.this.mTimer.currMillisecond - CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) {
                    CacheManager.this.evictAllNotInScreen();
                    CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                    sendEmptyMessage(3);
                    return 0L;
                }
                float poolPercent = CacheManager.this.getPoolPercent();
                BaseDanmaku baseDanmakuFirst = CacheManager.this.mCaches.first();
                long actualTime = baseDanmakuFirst != null ? baseDanmakuFirst.getActualTime() - CacheManagingDrawTask.this.mTimer.currMillisecond : 0L;
                long j = CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION * 2;
                if (poolPercent < 0.6f && actualTime > CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) {
                    CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                    removeMessages(3);
                    sendEmptyMessage(3);
                    return 0L;
                }
                if (poolPercent > 0.4f && actualTime < (-j)) {
                    removeMessages(4);
                    sendEmptyMessage(4);
                    return 0L;
                }
                if (poolPercent >= 0.9f) {
                    return 0L;
                }
                long j2 = CacheManagingDrawTask.this.mCacheTimer.currMillisecond - CacheManagingDrawTask.this.mTimer.currMillisecond;
                if (baseDanmakuFirst != null && baseDanmakuFirst.isTimeOut() && j2 < (-CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION)) {
                    CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                    sendEmptyMessage(8);
                    sendEmptyMessage(3);
                    return 0L;
                }
                if (j2 > j) {
                    return 0L;
                }
                removeMessages(3);
                sendEmptyMessage(3);
                return 0L;
            }

            private void releaseDanmakuCache(BaseDanmaku baseDanmaku, DrawingCache drawingCache) {
                if (drawingCache == null) {
                    drawingCache = (DrawingCache) baseDanmaku.cache;
                }
                baseDanmaku.cache = null;
                if (drawingCache == null) {
                    return;
                }
                drawingCache.destroy();
                CacheManager.this.mCachePool.release(drawingCache);
            }

            private void preMeasure() {
                IDanmakus iDanmakusSubnew;
                try {
                    long j = CacheManagingDrawTask.this.mTimer.currMillisecond;
                    iDanmakusSubnew = CacheManagingDrawTask.this.danmakuList.subnew(j - CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION, (CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION * 2) + j);
                } catch (Exception unused) {
                    iDanmakusSubnew = null;
                }
                if (iDanmakusSubnew == null || iDanmakusSubnew.isEmpty()) {
                    return;
                }
                IDanmakuIterator it = iDanmakusSubnew.iterator();
                while (it.hasNext() && !this.mPause) {
                    BaseDanmaku next = it.next();
                    if (!next.hasPassedFilter()) {
                        CacheManagingDrawTask.this.mContext.mDanmakuFilters.filter(next, 0, 0, null, true, CacheManagingDrawTask.this.mContext);
                    }
                    if (!next.isFiltered()) {
                        if (!next.isMeasured()) {
                            next.measure(CacheManagingDrawTask.this.mDisp, true);
                        }
                        if (!next.isPrepared()) {
                            next.prepare(CacheManagingDrawTask.this.mDisp, true);
                        }
                    }
                }
            }

            /* JADX WARN: Code duplicated, block: B:102:0x0197 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:103:0x0197 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:110:0x0121 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:111:0x0121 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:81:0x0178 A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:82:0x017a  */
            private long prepareCaches(boolean z) {
                CacheHandler cacheHandler;
                long jUptimeMillis;
                preMeasure();
                long j = CacheManagingDrawTask.this.mCacheTimer.currMillisecond;
                long j2 = (CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION * ((long) CacheManager.this.mScreenSize)) + j;
                if (j2 < CacheManagingDrawTask.this.mTimer.currMillisecond) {
                    return 0L;
                }
                long jUptimeMillis2 = SystemClock.uptimeMillis();
                IDanmakus iDanmakusSubnew = null;
                int i = 0;
                boolean z2 = false;
                do {
                    try {
                        iDanmakusSubnew = CacheManagingDrawTask.this.danmakuList.subnew(j, j2);
                    } catch (Exception unused) {
                        SystemClock.sleep(10L);
                        z2 = true;
                    }
                    i++;
                    if (i >= 3 || iDanmakusSubnew != null) {
                        break;
                    }
                } while (z2);
                if (iDanmakusSubnew == null) {
                    CacheManagingDrawTask.this.mCacheTimer.update(j2);
                    return 0L;
                }
                BaseDanmaku baseDanmakuFirst = iDanmakusSubnew.first();
                BaseDanmaku baseDanmakuLast = iDanmakusSubnew.last();
                if (baseDanmakuFirst == null || baseDanmakuLast == null) {
                    CacheManagingDrawTask.this.mCacheTimer.update(j2);
                    return 0L;
                }
                long actualTime = baseDanmakuFirst.getActualTime() - CacheManagingDrawTask.this.mTimer.currMillisecond;
                long j3 = 30;
                if (actualTime < 0) {
                    cacheHandler = this;
                } else {
                    cacheHandler = this;
                    j3 = ((actualTime * 10) / CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) + 30;
                }
                long jMin = z ? 0L : Math.min(100L, j3);
                IDanmakuIterator it = iDanmakusSubnew.iterator();
                int size = iDanmakusSubnew.size();
                int i2 = 0;
                int i3 = 0;
                BaseDanmaku baseDanmaku = null;
                while (!cacheHandler.mPause && !cacheHandler.mCancelFlag && it.hasNext()) {
                    BaseDanmaku next = it.next();
                    BaseDanmaku baseDanmaku2 = baseDanmakuLast;
                    if (baseDanmakuLast.getActualTime() >= CacheManagingDrawTask.this.mTimer.currMillisecond) {
                        IDrawingCache<?> drawingCache = next.getDrawingCache();
                        if ((drawingCache == null || drawingCache.get() == null) && (z || (!next.isTimeOut() && next.isOutside()))) {
                            if (!next.hasPassedFilter()) {
                                CacheManagingDrawTask.this.mContext.mDanmakuFilters.filter(next, i2, size, null, true, CacheManagingDrawTask.this.mContext);
                            }
                            if (next.priority != 0 || !next.isFiltered()) {
                                if (next.getType() == 1) {
                                    int actualTime2 = (int) ((next.getActualTime() - j) / CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                                    i3 = i3;
                                    if (i3 == actualTime2) {
                                        i2++;
                                    } else {
                                        i3 = actualTime2;
                                        i2 = 0;
                                    }
                                }
                                if (!z && !cacheHandler.mIsPlayerPause) {
                                    try {
                                        synchronized (CacheManagingDrawTask.this.mDrawingNotify) {
                                            CacheManagingDrawTask.this.mDrawingNotify.wait(jMin);
                                        }
                                        if (cacheHandler.buildCache(next, false) != 1) {
                                            if (z) {
                                                continue;
                                            } else {
                                                jUptimeMillis = SystemClock.uptimeMillis() - jUptimeMillis2;
                                                DanmakuFactory danmakuFactory = CacheManagingDrawTask.this.mContext.mDanmakuFactory;
                                                if (jUptimeMillis >= ((long) CacheManager.this.mScreenSize) * DanmakuFactory.COMMON_DANMAKU_DURATION) {
                                                }
                                            }
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                } else if (cacheHandler.buildCache(next, false) != 1) {
                                    if (z) {
                                        jUptimeMillis = SystemClock.uptimeMillis() - jUptimeMillis2;
                                        DanmakuFactory danmakuFactory2 = CacheManagingDrawTask.this.mContext.mDanmakuFactory;
                                        if (jUptimeMillis >= ((long) CacheManager.this.mScreenSize) * DanmakuFactory.COMMON_DANMAKU_DURATION) {
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                        baseDanmaku = next;
                        baseDanmakuLast = baseDanmaku2;
                    }
                    baseDanmaku = next;
                }
                long jUptimeMillis3 = SystemClock.uptimeMillis() - jUptimeMillis2;
                if (baseDanmaku != null) {
                    CacheManagingDrawTask.this.mCacheTimer.update(baseDanmaku.getActualTime());
                } else {
                    CacheManagingDrawTask.this.mCacheTimer.update(j2);
                }
                return jUptimeMillis3;
            }

            public boolean createCache(BaseDanmaku baseDanmaku) {
                DrawingCache drawingCacheBuildDanmakuDrawingCache;
                if (!baseDanmaku.isMeasured()) {
                    baseDanmaku.measure(CacheManagingDrawTask.this.mDisp, true);
                }
                try {
                    drawingCacheBuildDanmakuDrawingCache = (DrawingCache) CacheManager.this.mCachePool.acquire();
                    try {
                        drawingCacheBuildDanmakuDrawingCache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, drawingCacheBuildDanmakuDrawingCache);
                        baseDanmaku.cache = drawingCacheBuildDanmakuDrawingCache;
                        return true;
                    } catch (Exception unused) {
                        if (drawingCacheBuildDanmakuDrawingCache != null) {
                            CacheManager.this.mCachePool.release(drawingCacheBuildDanmakuDrawingCache);
                        }
                        baseDanmaku.cache = null;
                        return false;
                    } catch (OutOfMemoryError unused2) {
                        if (drawingCacheBuildDanmakuDrawingCache != null) {
                            CacheManager.this.mCachePool.release(drawingCacheBuildDanmakuDrawingCache);
                        }
                        baseDanmaku.cache = null;
                        return false;
                    }
                } catch (Exception unused3) {
                    drawingCacheBuildDanmakuDrawingCache = null;
                } catch (OutOfMemoryError unused4) {
                    drawingCacheBuildDanmakuDrawingCache = null;
                }
            }

            private byte buildCache(BaseDanmaku baseDanmaku, boolean z) {
                if (!baseDanmaku.isMeasured()) {
                    baseDanmaku.measure(CacheManagingDrawTask.this.mDisp, true);
                }
                DrawingCache drawingCache = null;
                try {
                    BaseDanmaku baseDanmakuFindReuseableCache = CacheManager.this.findReuseableCache(baseDanmaku, true, 20);
                    DrawingCache drawingCache2 = baseDanmakuFindReuseableCache != null ? (DrawingCache) baseDanmakuFindReuseableCache.cache : null;
                    try {
                        if (drawingCache2 == null) {
                            BaseDanmaku baseDanmakuFindReuseableCache2 = CacheManager.this.findReuseableCache(baseDanmaku, false, 50);
                            if (baseDanmakuFindReuseableCache2 != null) {
                                drawingCache2 = (DrawingCache) baseDanmakuFindReuseableCache2.cache;
                            }
                            if (drawingCache2 != null) {
                                baseDanmakuFindReuseableCache2.cache = null;
                                baseDanmaku.cache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, drawingCache2);
                                CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, 0, z);
                                return (byte) 0;
                            }
                            if (!z) {
                                if (CacheManager.this.mRealSize + DanmakuUtils.getCacheSize((int) baseDanmaku.paintWidth, (int) baseDanmaku.paintHeight) > CacheManager.this.mMaxSize) {
                                    return (byte) 1;
                                }
                            }
                            DrawingCache drawingCacheBuildDanmakuDrawingCache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, (DrawingCache) CacheManager.this.mCachePool.acquire());
                            baseDanmaku.cache = drawingCacheBuildDanmakuDrawingCache;
                            boolean zPush = CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, CacheManager.this.sizeOf(baseDanmaku), z);
                            if (!zPush) {
                                releaseDanmakuCache(baseDanmaku, drawingCacheBuildDanmakuDrawingCache);
                            }
                            return !zPush ? (byte) 1 : (byte) 0;
                        }
                        drawingCache2.increaseReference();
                        baseDanmaku.cache = drawingCache2;
                        CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, 0, z);
                        return (byte) 0;
                    } catch (Exception unused) {
                        drawingCache = drawingCache2;
                        releaseDanmakuCache(baseDanmaku, drawingCache);
                        return (byte) 1;
                    } catch (OutOfMemoryError unused2) {
                        drawingCache = drawingCache2;
                        releaseDanmakuCache(baseDanmaku, drawingCache);
                        return (byte) 1;
                    }
                } catch (Exception unused3) {
                } catch (OutOfMemoryError unused4) {
                }
            }

            private final void addDanmakuAndBuildCache(BaseDanmaku baseDanmaku) {
                if (baseDanmaku.isTimeOut()) {
                    return;
                }
                if (baseDanmaku.getActualTime() <= CacheManagingDrawTask.this.mCacheTimer.currMillisecond + CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION || baseDanmaku.isLive) {
                    if (baseDanmaku.priority == 0 && baseDanmaku.isFiltered()) {
                        return;
                    }
                    IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
                    if (drawingCache == null || drawingCache.get() == null) {
                        buildCache(baseDanmaku, true);
                    }
                }
            }

            public void begin() {
                sendEmptyMessage(1);
                sendEmptyMessageDelayed(4, CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
            }

            public void pause() {
                this.mPause = true;
                removeCallbacksAndMessages(null);
                sendEmptyMessage(6);
            }

            public void resume() {
                sendEmptyMessage(19);
                this.mPause = false;
                removeMessages(16);
                sendEmptyMessage(16);
                sendEmptyMessageDelayed(4, CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
            }

            public boolean isPause() {
                return this.mPause;
            }

            public void requestBuildCacheAndDraw(long j) {
                removeMessages(3);
                this.mSeekedFlag = true;
                sendEmptyMessage(19);
                CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond + j);
                sendEmptyMessage(3);
            }

            public void onPlayStateChanged(boolean z) {
                this.mIsPlayerPause = !z;
            }
        }

        public long getFirstCacheTime() {
            BaseDanmaku baseDanmakuFirst;
            Danmakus danmakus = this.mCaches;
            if (danmakus == null || danmakus.size() <= 0 || (baseDanmakuFirst = this.mCaches.first()) == null) {
                return 0L;
            }
            return baseDanmakuFirst.getActualTime();
        }

        public void requestBuild(long j) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.requestBuildCacheAndDraw(j);
            }
        }

        public void requestClearAll() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(3);
            this.mHandler.removeMessages(19);
            this.mHandler.requestCancelCaching();
            this.mHandler.removeMessages(7);
            this.mHandler.sendEmptyMessage(7);
        }

        public void requestClearUnused() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(9);
            this.mHandler.sendEmptyMessage(9);
        }

        public void requestClearTimeout() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(4);
            this.mHandler.sendEmptyMessage(4);
        }

        public void post(Runnable runnable) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.post(runnable);
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask
    public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        CacheManager cacheManager;
        Object obj;
        CacheManager cacheManager2;
        if (!super.handleOnDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr)) {
            if (DanmakuContext.DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
                this.mDisp.resetSlopPixel(this.mContext.scaleTextSize);
                requestClear();
            } else if (danmakuConfigTag.isVisibilityRelatedTag()) {
                if (objArr != null && objArr.length > 0 && (obj = objArr[0]) != null && ((!(obj instanceof Boolean) || ((Boolean) obj).booleanValue()) && (cacheManager2 = this.mCacheManager) != null)) {
                    cacheManager2.requestBuild(0L);
                }
                requestClear();
            } else if (DanmakuContext.DanmakuConfigTag.TRANSPARENCY.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.DANMAKU_STYLE.equals(danmakuConfigTag)) {
                if (DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag)) {
                    this.mDisp.resetSlopPixel(this.mContext.scaleTextSize);
                }
                CacheManager cacheManager3 = this.mCacheManager;
                if (cacheManager3 != null) {
                    cacheManager3.requestClearAll();
                    this.mCacheManager.requestBuild(-this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                }
            } else {
                CacheManager cacheManager4 = this.mCacheManager;
                if (cacheManager4 != null) {
                    cacheManager4.requestClearUnused();
                    this.mCacheManager.requestBuild(0L);
                }
            }
        }
        if (this.mTaskListener == null || (cacheManager = this.mCacheManager) == null) {
            return true;
        }
        cacheManager.post(new Runnable() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.1
            @Override // java.lang.Runnable
            public void run() {
                CacheManagingDrawTask.this.mTaskListener.onDanmakuConfigChanged();
            }
        });
        return true;
    }
}
