package master.flame.danmaku.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import androidx.media3.exoplayer.ExoPlayer;
import java.util.LinkedList;
import master.flame.danmaku.danmaku.model.AbsDanmakuSync;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.util.AndroidUtils;
import master.flame.danmaku.danmaku.util.SystemClock;
import tv.cjump.jni.DeviceUtils;

/* JADX INFO: loaded from: classes6.dex */
public class DrawHandler extends Handler {
    private static final int CLEAR_DANMAKUS_ON_SCREEN = 13;
    private static final int HIDE_DANMAKUS = 9;
    private static final long INDEFINITE_TIME = 10000000;
    private static final int MAX_RECORD_SIZE = 500;
    private static final int NOTIFY_DISP_SIZE_CHANGED = 10;
    private static final int NOTIFY_RENDERING = 11;
    private static final int PAUSE = 7;
    public static final int PREPARE = 5;
    private static final int QUIT = 6;
    public static final int RESUME = 3;
    public static final int SEEK_POS = 4;
    private static final int SHOW_DANMAKUS = 8;
    public static final int START = 1;
    public static final int UPDATE = 2;
    private static final int UPDATE_WHEN_PAUSED = 12;
    public IDrawTask drawTask;
    private Callback mCallback;
    private DanmakuContext mContext;
    private long mCordonTime;
    private long mCordonTime2;
    private IDanmakuViewController mDanmakuView;
    private boolean mDanmakusVisible;
    private long mDesireSeekingTime;
    private AbsDisplayer mDisp;
    private LinkedList<Long> mDrawTimes;
    private long mFrameUpdateRate;
    private boolean mIdleSleep;
    private boolean mInSeekingAction;
    private boolean mInSyncAction;
    private boolean mInWaitingState;
    private long mLastDeltaTime;
    private BaseDanmakuParser mParser;
    private boolean mReady;
    private long mRemainingTime;
    private final IRenderer.RenderingState mRenderingState;
    private UpdateThread mThread;
    private long mThresholdTime;
    private long mTimeBase;
    private final boolean mUpdateInNewThread;
    private long pausedPosition;
    private boolean quitFlag;
    private DanmakuTimer timer;

    public interface Callback {
        void danmakuShown(BaseDanmaku baseDanmaku);

        void drawingFinished();

        void prepared();

        void updateTimer(DanmakuTimer danmakuTimer);
    }

    public DrawHandler(Looper looper, IDanmakuViewController iDanmakuViewController, boolean z) {
        super(looper);
        this.pausedPosition = 0L;
        this.quitFlag = true;
        this.timer = new DanmakuTimer();
        this.mDanmakusVisible = true;
        this.mRenderingState = new IRenderer.RenderingState();
        this.mDrawTimes = new LinkedList<>();
        this.mCordonTime = 30L;
        this.mCordonTime2 = 60L;
        this.mFrameUpdateRate = 16L;
        this.mUpdateInNewThread = Runtime.getRuntime().availableProcessors() > 3;
        this.mIdleSleep = true ^ DeviceUtils.isProblemBoxDevice();
        bindView(iDanmakuViewController);
        if (z) {
            showDanmakus(null);
        } else {
            hideDanmakus(false);
        }
        this.mDanmakusVisible = z;
    }

    private void bindView(IDanmakuViewController iDanmakuViewController) {
        this.mDanmakuView = iDanmakuViewController;
    }

    public void setConfig(DanmakuContext danmakuContext) {
        this.mContext = danmakuContext;
    }

    public void setParser(BaseDanmakuParser baseDanmakuParser) {
        this.mParser = baseDanmakuParser;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void quit() {
        sendEmptyMessage(6);
    }

    public boolean isStop() {
        return this.quitFlag;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:49:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:52:0x00df  */
    /* JADX WARN: Code duplicated, block: B:55:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:57:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:59:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:62:0x0105  */
    /* JADX WARN: Code duplicated, block: B:65:0x0112  */
    /* JADX WARN: Code duplicated, block: B:79:0x0152  */
    /* JADX WARN: Code duplicated, block: B:80:0x0159  */
    /* JADX WARN: Code duplicated, block: B:83:0x0160  */
    /* JADX WARN: Code duplicated, block: B:85:0x018b  */
    /* JADX WARN: Code duplicated, block: B:89:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:91:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:92:0x01d2  */
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        IDrawTask iDrawTask;
        Long l;
        IDrawTask iDrawTask2;
        Long l2;
        IDrawTask iDrawTask3;
        BaseDanmakuParser baseDanmakuParser;
        IDrawTask iDrawTask4;
        boolean z;
        IDanmakuViewController iDanmakuViewController;
        IDrawTask iDrawTask5;
        int i = message.what;
        switch (i) {
            case 1:
                l2 = (Long) message.obj;
                if (l2 != null) {
                    this.pausedPosition = l2.longValue();
                } else {
                    this.pausedPosition = 0L;
                }
                if (i == 4) {
                    this.quitFlag = true;
                    quitUpdateThread();
                    l = (Long) message.obj;
                    this.mTimeBase -= l.longValue() - this.timer.currMillisecond;
                    this.timer.update(l.longValue());
                    this.mContext.mGlobalFlagValues.updateMeasureFlag();
                    iDrawTask2 = this.drawTask;
                    if (iDrawTask2 != null) {
                        iDrawTask2.seek(l.longValue());
                    }
                    this.pausedPosition = l.longValue();
                }
                removeMessages(7);
                this.quitFlag = false;
                if (this.mReady) {
                    this.mRenderingState.reset();
                    this.mDrawTimes.clear();
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    long j = this.pausedPosition;
                    this.mTimeBase = jUptimeMillis - j;
                    this.timer.update(j);
                    removeMessages(3);
                    sendEmptyMessage(2);
                    this.drawTask.start();
                    notifyRendering();
                    this.mInSeekingAction = false;
                    iDrawTask = this.drawTask;
                    if (iDrawTask != null) {
                        iDrawTask.onPlayStateChanged(1);
                    }
                } else {
                    sendEmptyMessageDelayed(3, 100L);
                }
                break;
            case 2:
                if (this.mUpdateInNewThread) {
                    updateInNewThread();
                } else {
                    updateInCurrentThread();
                }
                break;
            case 3:
                removeMessages(7);
                this.quitFlag = false;
                if (this.mReady) {
                    this.mRenderingState.reset();
                    this.mDrawTimes.clear();
                    long jUptimeMillis2 = SystemClock.uptimeMillis();
                    long j2 = this.pausedPosition;
                    this.mTimeBase = jUptimeMillis2 - j2;
                    this.timer.update(j2);
                    removeMessages(3);
                    sendEmptyMessage(2);
                    this.drawTask.start();
                    notifyRendering();
                    this.mInSeekingAction = false;
                    iDrawTask = this.drawTask;
                    if (iDrawTask != null) {
                        iDrawTask.onPlayStateChanged(1);
                    }
                } else {
                    sendEmptyMessageDelayed(3, 100L);
                }
                break;
            case 4:
                if (i == 4) {
                    this.quitFlag = true;
                    quitUpdateThread();
                    l = (Long) message.obj;
                    this.mTimeBase -= l.longValue() - this.timer.currMillisecond;
                    this.timer.update(l.longValue());
                    this.mContext.mGlobalFlagValues.updateMeasureFlag();
                    iDrawTask2 = this.drawTask;
                    if (iDrawTask2 != null) {
                        iDrawTask2.seek(l.longValue());
                    }
                    this.pausedPosition = l.longValue();
                }
                removeMessages(7);
                this.quitFlag = false;
                if (this.mReady) {
                    this.mRenderingState.reset();
                    this.mDrawTimes.clear();
                    long jUptimeMillis3 = SystemClock.uptimeMillis();
                    long j3 = this.pausedPosition;
                    this.mTimeBase = jUptimeMillis3 - j3;
                    this.timer.update(j3);
                    removeMessages(3);
                    sendEmptyMessage(2);
                    this.drawTask.start();
                    notifyRendering();
                    this.mInSeekingAction = false;
                    iDrawTask = this.drawTask;
                    if (iDrawTask != null) {
                        iDrawTask.onPlayStateChanged(1);
                    }
                } else {
                    sendEmptyMessageDelayed(3, 100L);
                }
                break;
            case 5:
                this.mTimeBase = SystemClock.uptimeMillis();
                if (this.mParser == null || !this.mDanmakuView.isViewReady()) {
                    sendEmptyMessageDelayed(5, 100L);
                } else {
                    prepare(new Runnable() { // from class: master.flame.danmaku.controller.DrawHandler.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DrawHandler.this.pausedPosition = 0L;
                            DrawHandler.this.mReady = true;
                            if (DrawHandler.this.mCallback != null) {
                                DrawHandler.this.mCallback.prepared();
                            }
                        }
                    });
                }
                break;
            case 6:
                if (i == 6) {
                    removeCallbacksAndMessages(null);
                }
                this.quitFlag = true;
                syncTimerIfNeeded();
                this.pausedPosition = this.timer.currMillisecond;
                if (this.mThread != null) {
                    notifyRendering();
                    quitUpdateThread();
                }
                if (i == 6) {
                    iDrawTask3 = this.drawTask;
                    if (iDrawTask3 != null) {
                        iDrawTask3.quit();
                    }
                    baseDanmakuParser = this.mParser;
                    if (baseDanmakuParser != null) {
                        baseDanmakuParser.release();
                    }
                    if (getLooper() != Looper.getMainLooper()) {
                        getLooper().quit();
                    }
                }
                break;
            case 7:
                removeMessages(3);
                removeMessages(2);
                iDrawTask4 = this.drawTask;
                if (iDrawTask4 != null) {
                    iDrawTask4.onPlayStateChanged(2);
                }
                if (i == 6) {
                    removeCallbacksAndMessages(null);
                }
                this.quitFlag = true;
                syncTimerIfNeeded();
                this.pausedPosition = this.timer.currMillisecond;
                if (this.mThread != null) {
                    notifyRendering();
                    quitUpdateThread();
                }
                if (i == 6) {
                    iDrawTask3 = this.drawTask;
                    if (iDrawTask3 != null) {
                        iDrawTask3.quit();
                    }
                    baseDanmakuParser = this.mParser;
                    if (baseDanmakuParser != null) {
                        baseDanmakuParser.release();
                    }
                    if (getLooper() != Looper.getMainLooper()) {
                        getLooper().quit();
                    }
                }
                break;
            case 8:
                this.mDanmakusVisible = true;
                Long l3 = (Long) message.obj;
                IDrawTask iDrawTask6 = this.drawTask;
                if (iDrawTask6 == null) {
                    z = false;
                } else if (l3 == null) {
                    this.timer.update(getCurrentTime());
                    this.drawTask.requestClear();
                    z = false;
                } else {
                    iDrawTask6.start();
                    this.drawTask.seek(l3.longValue());
                    this.drawTask.requestClear();
                    z = true;
                }
                if (this.quitFlag && (iDanmakuViewController = this.mDanmakuView) != null) {
                    iDanmakuViewController.drawDanmakus();
                }
                notifyRendering();
                if (!z) {
                }
                l2 = (Long) message.obj;
                if (l2 != null) {
                    this.pausedPosition = l2.longValue();
                } else {
                    this.pausedPosition = 0L;
                }
                if (i == 4) {
                    this.quitFlag = true;
                    quitUpdateThread();
                    l = (Long) message.obj;
                    this.mTimeBase -= l.longValue() - this.timer.currMillisecond;
                    this.timer.update(l.longValue());
                    this.mContext.mGlobalFlagValues.updateMeasureFlag();
                    iDrawTask2 = this.drawTask;
                    if (iDrawTask2 != null) {
                        iDrawTask2.seek(l.longValue());
                    }
                    this.pausedPosition = l.longValue();
                }
                removeMessages(7);
                this.quitFlag = false;
                if (this.mReady) {
                    this.mRenderingState.reset();
                    this.mDrawTimes.clear();
                    long jUptimeMillis4 = SystemClock.uptimeMillis();
                    long j4 = this.pausedPosition;
                    this.mTimeBase = jUptimeMillis4 - j4;
                    this.timer.update(j4);
                    removeMessages(3);
                    sendEmptyMessage(2);
                    this.drawTask.start();
                    notifyRendering();
                    this.mInSeekingAction = false;
                    iDrawTask = this.drawTask;
                    if (iDrawTask != null) {
                        iDrawTask.onPlayStateChanged(1);
                    }
                } else {
                    sendEmptyMessageDelayed(3, 100L);
                }
                break;
            case 9:
                this.mDanmakusVisible = false;
                IDanmakuViewController iDanmakuViewController2 = this.mDanmakuView;
                if (iDanmakuViewController2 != null) {
                    iDanmakuViewController2.clear();
                }
                IDrawTask iDrawTask7 = this.drawTask;
                if (iDrawTask7 != null) {
                    iDrawTask7.requestClear();
                    this.drawTask.requestHide();
                }
                Boolean bool = (Boolean) message.obj;
                if (bool.booleanValue() && (iDrawTask5 = this.drawTask) != null) {
                    iDrawTask5.quit();
                }
                if (!bool.booleanValue()) {
                }
                removeMessages(3);
                removeMessages(2);
                iDrawTask4 = this.drawTask;
                if (iDrawTask4 != null) {
                    iDrawTask4.onPlayStateChanged(2);
                }
                if (i == 6) {
                    removeCallbacksAndMessages(null);
                }
                this.quitFlag = true;
                syncTimerIfNeeded();
                this.pausedPosition = this.timer.currMillisecond;
                if (this.mThread != null) {
                    notifyRendering();
                    quitUpdateThread();
                }
                if (i == 6) {
                    iDrawTask3 = this.drawTask;
                    if (iDrawTask3 != null) {
                        iDrawTask3.quit();
                    }
                    baseDanmakuParser = this.mParser;
                    if (baseDanmakuParser != null) {
                        baseDanmakuParser.release();
                    }
                    if (getLooper() != Looper.getMainLooper()) {
                        getLooper().quit();
                    }
                }
                break;
            case 10:
                this.mContext.mDanmakuFactory.notifyDispSizeChanged(this.mContext);
                Boolean bool2 = (Boolean) message.obj;
                if (bool2 != null && bool2.booleanValue()) {
                    this.mContext.mGlobalFlagValues.updateMeasureFlag();
                    this.mContext.mGlobalFlagValues.updateVisibleFlag();
                    this.drawTask.requestClearRetainer();
                    break;
                }
                break;
            case 11:
                notifyRendering();
                break;
            case 12:
                if (this.quitFlag && this.mDanmakuView != null) {
                    this.drawTask.requestClear();
                    this.mDanmakuView.drawDanmakus();
                    notifyRendering();
                    break;
                }
                break;
            case 13:
                IDrawTask iDrawTask8 = this.drawTask;
                if (iDrawTask8 != null) {
                    iDrawTask8.clearDanmakusOnScreen(getCurrentTime());
                }
                break;
        }
    }

    private void quitUpdateThread() {
        UpdateThread updateThread = this.mThread;
        if (updateThread != null) {
            this.mThread = null;
            synchronized (this.drawTask) {
                this.drawTask.notifyAll();
            }
            updateThread.quit();
            try {
                updateThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateInCurrentThread() {
        if (this.quitFlag) {
            return;
        }
        long jSyncTimer = syncTimer(SystemClock.uptimeMillis());
        if (jSyncTimer < 0) {
            removeMessages(2);
            sendEmptyMessageDelayed(2, 60 - jSyncTimer);
            return;
        }
        long jDrawDanmakus = this.mDanmakuView.drawDanmakus();
        removeMessages(2);
        if (jDrawDanmakus > this.mCordonTime2) {
            this.timer.add(jDrawDanmakus);
            this.mDrawTimes.clear();
        }
        if (!this.mDanmakusVisible) {
            waitRendering(INDEFINITE_TIME);
            return;
        }
        if (this.mRenderingState.nothingRendered && this.mIdleSleep) {
            long j = this.mRenderingState.endTime - this.timer.currMillisecond;
            if (j > 500) {
                waitRendering(j - 10);
                return;
            }
        }
        long j2 = this.mFrameUpdateRate;
        if (jDrawDanmakus < j2) {
            sendEmptyMessageDelayed(2, j2 - jDrawDanmakus);
        } else {
            sendEmptyMessage(2);
        }
    }

    private void updateInNewThread() {
        if (this.mThread != null) {
            return;
        }
        UpdateThread updateThread = new UpdateThread("DFM Update") { // from class: master.flame.danmaku.controller.DrawHandler.2
            @Override // master.flame.danmaku.controller.UpdateThread, java.lang.Thread, java.lang.Runnable
            public void run() {
                long jUptimeMillis = SystemClock.uptimeMillis();
                while (!isQuited() && !DrawHandler.this.quitFlag) {
                    long jUptimeMillis2 = SystemClock.uptimeMillis();
                    if (DrawHandler.this.mFrameUpdateRate - (SystemClock.uptimeMillis() - jUptimeMillis) <= 1) {
                        long jSyncTimer = DrawHandler.this.syncTimer(jUptimeMillis2);
                        if (jSyncTimer >= 0) {
                            long jDrawDanmakus = DrawHandler.this.mDanmakuView.drawDanmakus();
                            if (jDrawDanmakus > DrawHandler.this.mCordonTime2) {
                                DrawHandler.this.timer.add(jDrawDanmakus);
                                DrawHandler.this.mDrawTimes.clear();
                            }
                            if (!DrawHandler.this.mDanmakusVisible) {
                                DrawHandler.this.waitRendering(DrawHandler.INDEFINITE_TIME);
                            } else if (DrawHandler.this.mRenderingState.nothingRendered && DrawHandler.this.mIdleSleep) {
                                long j = DrawHandler.this.mRenderingState.endTime - DrawHandler.this.timer.currMillisecond;
                                if (j > 500) {
                                    DrawHandler.this.notifyRendering();
                                    DrawHandler.this.waitRendering(j - 10);
                                }
                            }
                        } else {
                            SystemClock.sleep(60 - jSyncTimer);
                        }
                        jUptimeMillis = jUptimeMillis2;
                    } else {
                        SystemClock.sleep(1L);
                    }
                }
            }
        };
        this.mThread = updateThread;
        updateThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long syncTimer(long j) {
        long j2 = 0;
        if (!this.mInSeekingAction && !this.mInSyncAction) {
            this.mInSyncAction = true;
            long j3 = j - this.mTimeBase;
            if (!this.mDanmakusVisible || this.mRenderingState.nothingRendered || this.mInWaitingState) {
                this.timer.update(j3);
                this.mRemainingTime = 0L;
            } else {
                long j4 = j3 - this.timer.currMillisecond;
                long jMax = Math.max(this.mFrameUpdateRate, getAverageRenderingTime());
                if (j4 <= ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS) {
                    long j5 = this.mRenderingState.consumingTime;
                    long j6 = this.mCordonTime;
                    if (j5 <= j6 && jMax <= j6) {
                        long j7 = this.mFrameUpdateRate;
                        long jMin = Math.min(this.mCordonTime, Math.max(j7, jMax + (j4 / j7)));
                        long j8 = this.mLastDeltaTime;
                        long j9 = jMin - j8;
                        if (j9 > 3 && j9 < 8 && j8 >= this.mFrameUpdateRate && j8 <= this.mCordonTime) {
                            jMin = j8;
                        }
                        long j10 = j4 - jMin;
                        this.mLastDeltaTime = jMin;
                        j4 = jMin;
                        j2 = j10;
                    }
                }
                this.mRemainingTime = j2;
                this.timer.add(j4);
                j2 = j4;
            }
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.updateTimer(this.timer);
            }
            this.mInSyncAction = false;
        }
        return j2;
    }

    private void syncTimerIfNeeded() {
        if (this.mInWaitingState) {
            syncTimer(SystemClock.uptimeMillis());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRenderingConfigs() {
        long jMax = Math.max(33L, (long) (16 * 2.5f));
        this.mCordonTime = jMax;
        this.mCordonTime2 = (long) (jMax * 2.5f);
        long jMax2 = Math.max(16L, 15L);
        this.mFrameUpdateRate = jMax2;
        this.mThresholdTime = jMax2 + 3;
    }

    private void prepare(final Runnable runnable) {
        if (this.drawTask == null) {
            this.drawTask = createDrawTask(this.mDanmakuView.isDanmakuDrawingCacheEnabled(), this.timer, this.mDanmakuView.getContext(), this.mDanmakuView.getWidth(), this.mDanmakuView.getHeight(), this.mDanmakuView.isHardwareAccelerated(), new IDrawTask.TaskListener() { // from class: master.flame.danmaku.controller.DrawHandler.3
                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void ready() {
                    DrawHandler.this.initRenderingConfigs();
                    runnable.run();
                }

                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void onDanmakuAdd(BaseDanmaku baseDanmaku) {
                    if (baseDanmaku.isTimeOut()) {
                        return;
                    }
                    long actualTime = baseDanmaku.getActualTime() - DrawHandler.this.timer.currMillisecond;
                    if (actualTime <= 0) {
                        if (DrawHandler.this.mInWaitingState) {
                            DrawHandler.this.notifyRendering();
                            return;
                        }
                        return;
                    }
                    DrawHandler.this.sendEmptyMessageDelayed(11, actualTime);
                }

                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void onDanmakuShown(BaseDanmaku baseDanmaku) {
                    if (DrawHandler.this.mCallback != null) {
                        DrawHandler.this.mCallback.danmakuShown(baseDanmaku);
                    }
                }

                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void onDanmakusDrawingFinished() {
                    if (DrawHandler.this.mCallback != null) {
                        DrawHandler.this.mCallback.drawingFinished();
                    }
                }

                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void onDanmakuConfigChanged() {
                    DrawHandler.this.redrawIfNeeded();
                }
            });
        } else {
            runnable.run();
        }
    }

    public boolean isPrepared() {
        return this.mReady;
    }

    private IDrawTask createDrawTask(boolean z, DanmakuTimer danmakuTimer, Context context, int i, int i2, boolean z2, IDrawTask.TaskListener taskListener) {
        AbsDisplayer displayer = this.mContext.getDisplayer();
        this.mDisp = displayer;
        displayer.setSize(i, i2);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.mDisp.setDensities(displayMetrics.density, displayMetrics.densityDpi, displayMetrics.scaledDensity);
        this.mDisp.resetSlopPixel(this.mContext.scaleTextSize);
        this.mDisp.setHardwareAccelerated(z2);
        IDrawTask cacheManagingDrawTask = z ? new CacheManagingDrawTask(danmakuTimer, this.mContext, taskListener, (AndroidUtils.getMemoryClass(context) * 1048576) / 3) : new DrawTask(danmakuTimer, this.mContext, taskListener);
        cacheManagingDrawTask.setParser(this.mParser);
        cacheManagingDrawTask.prepare();
        obtainMessage(10, false).sendToTarget();
        return cacheManagingDrawTask;
    }

    public void seekTo(Long l) {
        this.mInSeekingAction = true;
        this.mDesireSeekingTime = l.longValue();
        removeMessages(2);
        removeMessages(3);
        removeMessages(4);
        obtainMessage(4, l).sendToTarget();
    }

    public void addDanmaku(BaseDanmaku baseDanmaku) {
        if (this.drawTask != null) {
            baseDanmaku.flags = this.mContext.mGlobalFlagValues;
            baseDanmaku.setTimer(this.timer);
            this.drawTask.addDanmaku(baseDanmaku);
            obtainMessage(11).sendToTarget();
        }
    }

    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        IDrawTask iDrawTask = this.drawTask;
        if (iDrawTask != null && baseDanmaku != null) {
            iDrawTask.invalidateDanmaku(baseDanmaku, z);
        }
        redrawIfNeeded();
    }

    public void resume() {
        removeMessages(7);
        sendEmptyMessage(3);
    }

    public void prepare() {
        sendEmptyMessage(5);
    }

    public void pause() {
        removeMessages(3);
        syncTimerIfNeeded();
        sendEmptyMessage(7);
    }

    public void showDanmakus(Long l) {
        if (this.mDanmakusVisible) {
            return;
        }
        this.mDanmakusVisible = true;
        removeMessages(8);
        removeMessages(9);
        obtainMessage(8, l).sendToTarget();
    }

    public long hideDanmakus(boolean z) {
        if (!this.mDanmakusVisible) {
            return this.timer.currMillisecond;
        }
        this.mDanmakusVisible = false;
        removeMessages(8);
        removeMessages(9);
        obtainMessage(9, Boolean.valueOf(z)).sendToTarget();
        return this.timer.currMillisecond;
    }

    public boolean getVisibility() {
        return this.mDanmakusVisible;
    }

    public IRenderer.RenderingState draw(Canvas canvas) {
        AbsDanmakuSync absDanmakuSync;
        boolean zIsSyncPlayingState;
        if (this.drawTask == null) {
            return this.mRenderingState;
        }
        if (!this.mInWaitingState && (absDanmakuSync = this.mContext.danmakuSync) != null && ((zIsSyncPlayingState = absDanmakuSync.isSyncPlayingState()) || this.quitFlag)) {
            int syncState = absDanmakuSync.getSyncState();
            if (syncState == 2) {
                long j = this.timer.currMillisecond;
                long uptimeMillis = absDanmakuSync.getUptimeMillis();
                long j2 = uptimeMillis - j;
                if (Math.abs(j2) > absDanmakuSync.getThresholdTimeMills()) {
                    if (zIsSyncPlayingState && this.quitFlag) {
                        resume();
                    }
                    this.drawTask.requestSync(j, uptimeMillis, j2);
                    this.timer.update(uptimeMillis);
                    this.mTimeBase = SystemClock.uptimeMillis() - uptimeMillis;
                    this.mRemainingTime = 0L;
                }
            } else if (syncState == 1 && zIsSyncPlayingState && !this.quitFlag) {
                pause();
            }
        }
        this.mDisp.setExtraData(canvas);
        this.mRenderingState.set(this.drawTask.draw(this.mDisp));
        recordRenderingTime();
        return this.mRenderingState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void redrawIfNeeded() {
        if (this.quitFlag && this.mDanmakusVisible) {
            obtainMessage(12).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRendering() {
        if (this.mInWaitingState) {
            IDrawTask iDrawTask = this.drawTask;
            if (iDrawTask != null) {
                iDrawTask.requestClear();
            }
            if (this.mUpdateInNewThread) {
                synchronized (this) {
                    this.mDrawTimes.clear();
                }
                synchronized (this.drawTask) {
                    this.drawTask.notifyAll();
                }
            } else {
                this.mDrawTimes.clear();
                removeMessages(2);
                sendEmptyMessage(2);
            }
            this.mInWaitingState = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitRendering(long j) {
        this.mRenderingState.sysTime = SystemClock.uptimeMillis();
        this.mInWaitingState = true;
        if (!this.mUpdateInNewThread) {
            if (j == INDEFINITE_TIME) {
                removeMessages(11);
                removeMessages(2);
                return;
            } else {
                removeMessages(11);
                removeMessages(2);
                sendEmptyMessageDelayed(11, j);
                return;
            }
        }
        if (this.mThread == null) {
            return;
        }
        try {
            synchronized (this.drawTask) {
                try {
                    if (j == INDEFINITE_TIME) {
                        this.drawTask.wait();
                    } else {
                        this.drawTask.wait(j);
                    }
                    sendEmptyMessage(11);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized long getAverageRenderingTime() {
        int size = this.mDrawTimes.size();
        if (size <= 0) {
            return 0L;
        }
        Long lPeekFirst = this.mDrawTimes.peekFirst();
        Long lPeekLast = this.mDrawTimes.peekLast();
        if (lPeekFirst != null && lPeekLast != null) {
            return (lPeekLast.longValue() - lPeekFirst.longValue()) / ((long) size);
        }
        return 0L;
    }

    private synchronized void recordRenderingTime() {
        this.mDrawTimes.addLast(Long.valueOf(SystemClock.uptimeMillis()));
        if (this.mDrawTimes.size() > 500) {
            this.mDrawTimes.removeFirst();
        }
    }

    public IDisplayer getDisplayer() {
        return this.mDisp;
    }

    public void notifyDispSizeChanged(int i, int i2) {
        AbsDisplayer absDisplayer = this.mDisp;
        if (absDisplayer == null) {
            return;
        }
        if (absDisplayer.getWidth() == i && this.mDisp.getHeight() == i2) {
            return;
        }
        this.mDisp.setSize(i, i2);
        obtainMessage(10, true).sendToTarget();
    }

    public void removeAllDanmakus(boolean z) {
        IDrawTask iDrawTask = this.drawTask;
        if (iDrawTask != null) {
            iDrawTask.removeAllDanmakus(z);
        }
    }

    public void removeAllLiveDanmakus() {
        IDrawTask iDrawTask = this.drawTask;
        if (iDrawTask != null) {
            iDrawTask.removeAllLiveDanmakus();
        }
    }

    public IDanmakus getCurrentVisibleDanmakus() {
        IDrawTask iDrawTask = this.drawTask;
        if (iDrawTask != null) {
            return iDrawTask.getVisibleDanmakusOnTime(getCurrentTime());
        }
        return null;
    }

    public long getCurrentTime() {
        long jUptimeMillis;
        long j;
        if (!this.mReady) {
            return 0L;
        }
        if (this.mInSeekingAction) {
            return this.mDesireSeekingTime;
        }
        if (this.quitFlag || !this.mInWaitingState) {
            jUptimeMillis = this.timer.currMillisecond;
            j = this.mRemainingTime;
        } else {
            jUptimeMillis = SystemClock.uptimeMillis();
            j = this.mTimeBase;
        }
        return jUptimeMillis - j;
    }

    public void clearDanmakusOnScreen() {
        obtainMessage(13).sendToTarget();
    }

    public DanmakuContext getConfig() {
        return this.mContext;
    }
}
