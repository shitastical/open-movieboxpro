package master.flame.danmaku.controller;

import android.graphics.Canvas;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.renderer.android.DanmakuRenderer;
import master.flame.danmaku.danmaku.util.SystemClock;

/* JADX INFO: loaded from: classes6.dex */
public class DrawTask implements IDrawTask {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected boolean clearRetainerFlag;
    protected IDanmakus danmakuList;
    protected final DanmakuContext mContext;
    protected final AbsDisplayer mDisp;
    private boolean mIsHidden;
    private long mLastBeginMills;
    private BaseDanmaku mLastDanmaku;
    private long mLastEndMills;
    protected BaseDanmakuParser mParser;
    protected int mPlayState;
    protected boolean mReadyState;
    final IRenderer mRenderer;
    private IDanmakus mRunningDanmakus;
    IDrawTask.TaskListener mTaskListener;
    DanmakuTimer mTimer;
    private IDanmakus danmakus = new Danmakus(4);
    private long mStartRenderTime = 0;
    private final IRenderer.RenderingState mRenderingState = new IRenderer.RenderingState();
    private Danmakus mLiveDanmakus = new Danmakus(4);
    private DanmakuContext.ConfigChangedCallback mConfigChangedCallback = new DanmakuContext.ConfigChangedCallback() { // from class: master.flame.danmaku.controller.DrawTask.1
        @Override // master.flame.danmaku.danmaku.model.android.DanmakuContext.ConfigChangedCallback
        public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
            return DrawTask.this.onDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr);
        }
    };

    protected void onDanmakuRemoved(BaseDanmaku baseDanmaku) {
    }

    public DrawTask(DanmakuTimer danmakuTimer, DanmakuContext danmakuContext, IDrawTask.TaskListener taskListener) {
        if (danmakuContext == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mContext = danmakuContext;
        this.mDisp = danmakuContext.getDisplayer();
        this.mTaskListener = taskListener;
        DanmakuRenderer danmakuRenderer = new DanmakuRenderer(danmakuContext);
        this.mRenderer = danmakuRenderer;
        danmakuRenderer.setOnDanmakuShownListener(new IRenderer.OnDanmakuShownListener() { // from class: master.flame.danmaku.controller.DrawTask.2
            @Override // master.flame.danmaku.danmaku.renderer.IRenderer.OnDanmakuShownListener
            public void onDanmakuShown(BaseDanmaku baseDanmaku) {
                if (DrawTask.this.mTaskListener != null) {
                    DrawTask.this.mTaskListener.onDanmakuShown(baseDanmaku);
                }
            }
        });
        danmakuRenderer.setVerifierEnabled(danmakuContext.isPreventOverlappingEnabled() || danmakuContext.isMaxLinesLimited());
        initTimer(danmakuTimer);
        boolean zIsDuplicateMergingEnabled = danmakuContext.isDuplicateMergingEnabled();
        Boolean boolValueOf = Boolean.valueOf(zIsDuplicateMergingEnabled);
        if (boolValueOf != null) {
            boolValueOf.getClass();
            if (zIsDuplicateMergingEnabled) {
                danmakuContext.mDanmakuFilters.registerFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
            } else {
                danmakuContext.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
            }
        }
    }

    protected void initTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void addDanmaku(BaseDanmaku baseDanmaku) {
        boolean zAddItem;
        boolean zAddItem2;
        IDrawTask.TaskListener taskListener;
        if (this.danmakuList == null) {
            return;
        }
        if (baseDanmaku.isLive) {
            this.mLiveDanmakus.addItem(baseDanmaku);
            removeUnusedLiveDanmakusIn(10);
        }
        baseDanmaku.index = this.danmakuList.size();
        if (this.mLastBeginMills <= baseDanmaku.getActualTime() && baseDanmaku.getActualTime() <= this.mLastEndMills) {
            synchronized (this.danmakus) {
                zAddItem = this.danmakus.addItem(baseDanmaku);
            }
        } else {
            zAddItem = !baseDanmaku.isLive;
        }
        synchronized (this.danmakuList) {
            zAddItem2 = this.danmakuList.addItem(baseDanmaku);
        }
        if (!zAddItem) {
            this.mLastEndMills = 0L;
            this.mLastBeginMills = 0L;
        }
        if (zAddItem2 && (taskListener = this.mTaskListener) != null) {
            taskListener.onDanmakuAdd(baseDanmaku);
        }
        BaseDanmaku baseDanmaku2 = this.mLastDanmaku;
        if (baseDanmaku2 == null || (baseDanmaku != null && baseDanmaku2 != null && baseDanmaku.getActualTime() > this.mLastDanmaku.getActualTime())) {
            this.mLastDanmaku = baseDanmaku;
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        this.mContext.getDisplayer().getCacheStuffer().clearCache(baseDanmaku);
        baseDanmaku.requestFlags |= 2;
        if (z) {
            baseDanmaku.paintWidth = -1.0f;
            baseDanmaku.paintHeight = -1.0f;
            baseDanmaku.requestFlags |= 1;
            baseDanmaku.measureResetFlag++;
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void removeAllDanmakus(boolean z) {
        IDanmakus iDanmakus = this.danmakuList;
        if (iDanmakus != null && !iDanmakus.isEmpty()) {
            synchronized (this.danmakuList) {
                if (!z) {
                    IDanmakus iDanmakusSubnew = this.danmakuList.subnew((this.mTimer.currMillisecond - this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) - 100, this.mTimer.currMillisecond + this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                    if (iDanmakusSubnew != null) {
                        this.danmakus = iDanmakusSubnew;
                    }
                }
                this.danmakuList.clear();
            }
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void removeAllLiveDanmakus() {
        IDanmakus iDanmakus = this.danmakus;
        if (iDanmakus != null && !iDanmakus.isEmpty()) {
            synchronized (this.danmakus) {
                IDanmakuIterator it = this.danmakus.iterator();
                while (it.hasNext()) {
                    BaseDanmaku next = it.next();
                    if (next.isLive) {
                        it.remove();
                        onDanmakuRemoved(next);
                    }
                }
            }
        }
    }

    protected synchronized void removeUnusedLiveDanmakusIn(int i) {
        BaseDanmaku next;
        boolean zIsTimeOut;
        IDanmakus iDanmakus = this.danmakuList;
        if (iDanmakus != null && !iDanmakus.isEmpty() && !this.mLiveDanmakus.isEmpty()) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            IDanmakuIterator it = this.mLiveDanmakus.iterator();
            while (it.hasNext() && (zIsTimeOut = (next = it.next()).isTimeOut())) {
                it.remove();
                this.danmakuList.removeItem(next);
                onDanmakuRemoved(next);
                if (!zIsTimeOut || SystemClock.uptimeMillis() - jUptimeMillis > i) {
                    break;
                }
            }
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public IDanmakus getVisibleDanmakusOnTime(long j) {
        IDanmakus iDanmakusSubnew = this.danmakuList.subnew((j - this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) - 100, j + this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
        Danmakus danmakus = new Danmakus();
        if (iDanmakusSubnew != null && !iDanmakusSubnew.isEmpty()) {
            IDanmakuIterator it = iDanmakusSubnew.iterator();
            while (it.hasNext()) {
                BaseDanmaku next = it.next();
                if (next.isShown() && !next.isOutside()) {
                    danmakus.addItem(next);
                }
            }
        }
        return danmakus;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized IRenderer.RenderingState draw(AbsDisplayer absDisplayer) {
        return drawDanmakus(absDisplayer, this.mTimer);
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void reset() {
        if (this.danmakus != null) {
            this.danmakus = new Danmakus();
        }
        IRenderer iRenderer = this.mRenderer;
        if (iRenderer != null) {
            iRenderer.clear();
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void seek(long j) {
        BaseDanmaku baseDanmakuLast;
        reset();
        this.mContext.mGlobalFlagValues.updateVisibleFlag();
        this.mContext.mGlobalFlagValues.updateFirstShownFlag();
        this.mContext.mGlobalFlagValues.updateSyncOffsetTimeFlag();
        this.mContext.mGlobalFlagValues.updatePrepareFlag();
        this.mRunningDanmakus = new Danmakus(4);
        if (j < 1000) {
            j = 0;
        }
        this.mStartRenderTime = j;
        this.mRenderingState.reset();
        this.mRenderingState.endTime = this.mStartRenderTime;
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        IDanmakus iDanmakus = this.danmakuList;
        if (iDanmakus == null || (baseDanmakuLast = iDanmakus.last()) == null || baseDanmakuLast.isTimeOut()) {
            return;
        }
        this.mLastDanmaku = baseDanmakuLast;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void clearDanmakusOnScreen(long j) {
        reset();
        this.mContext.mGlobalFlagValues.updateVisibleFlag();
        this.mContext.mGlobalFlagValues.updateFirstShownFlag();
        this.mStartRenderTime = j;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void start() {
        this.mContext.registerConfigChangedCallback(this.mConfigChangedCallback);
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void quit() {
        this.mContext.unregisterAllConfigChangedCallbacks();
        IRenderer iRenderer = this.mRenderer;
        if (iRenderer != null) {
            iRenderer.release();
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void prepare() {
        loadDanmakus(this.mParser);
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        IDrawTask.TaskListener taskListener = this.mTaskListener;
        if (taskListener != null) {
            taskListener.ready();
            this.mReadyState = true;
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void onPlayStateChanged(int i) {
        this.mPlayState = i;
    }

    protected void loadDanmakus(BaseDanmakuParser baseDanmakuParser) {
        IDanmakus danmakus = baseDanmakuParser.setConfig(this.mContext).setDisplayer(this.mDisp).setTimer(this.mTimer).getDanmakus();
        this.danmakuList = danmakus;
        if (danmakus != null && !danmakus.isEmpty() && this.danmakuList.first().flags == null) {
            IDanmakuIterator it = this.danmakuList.iterator();
            while (it.hasNext()) {
                BaseDanmaku next = it.next();
                if (next != null) {
                    next.flags = this.mContext.mGlobalFlagValues;
                }
            }
        }
        this.mContext.mGlobalFlagValues.resetAll();
        IDanmakus iDanmakus = this.danmakuList;
        if (iDanmakus != null) {
            this.mLastDanmaku = iDanmakus.last();
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void setParser(BaseDanmakuParser baseDanmakuParser) {
        this.mParser = baseDanmakuParser;
        this.mReadyState = false;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0053  */
    /* JADX WARN: Code duplicated, block: B:19:0x005b  */
    protected IRenderer.RenderingState drawDanmakus(AbsDisplayer absDisplayer, DanmakuTimer danmakuTimer) {
        IDanmakus iDanmakusSub;
        long j;
        long j2;
        IDanmakus iDanmakus;
        if (this.clearRetainerFlag) {
            this.mRenderer.clearRetainer();
            this.clearRetainerFlag = false;
        }
        if (this.danmakuList == null) {
            return null;
        }
        DrawHelper.clearCanvas((Canvas) absDisplayer.getExtraData());
        if (this.mIsHidden) {
            return this.mRenderingState;
        }
        IRenderer.RenderingState renderingState = this.mRenderingState;
        long j3 = (danmakuTimer.currMillisecond - this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) - 100;
        long j4 = danmakuTimer.currMillisecond + this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION;
        IDanmakus iDanmakus2 = this.danmakus;
        if (this.mLastBeginMills <= j3) {
            long j5 = danmakuTimer.currMillisecond;
            long j6 = this.mLastEndMills;
            if (j5 > j6) {
                iDanmakusSub = this.danmakuList.sub(j3, j4);
                if (iDanmakusSub != null) {
                    this.danmakus = iDanmakusSub;
                }
                this.mLastBeginMills = j3;
                this.mLastEndMills = j4;
                j = j3;
                j2 = j4;
                iDanmakus = iDanmakusSub;
            } else {
                iDanmakus = iDanmakus2;
                j2 = j6;
                j = this.mLastBeginMills;
            }
        } else {
            iDanmakusSub = this.danmakuList.sub(j3, j4);
            if (iDanmakusSub != null) {
                this.danmakus = iDanmakusSub;
            }
            this.mLastBeginMills = j3;
            this.mLastEndMills = j4;
            j = j3;
            j2 = j4;
            iDanmakus = iDanmakusSub;
        }
        IDanmakus iDanmakus3 = this.mRunningDanmakus;
        beginTracing(renderingState, iDanmakus3, iDanmakus);
        if (iDanmakus3 != null && !iDanmakus3.isEmpty()) {
            this.mRenderingState.isRunningDanmakus = true;
            this.mRenderer.draw(absDisplayer, iDanmakus3, 0L, this.mRenderingState);
        }
        this.mRenderingState.isRunningDanmakus = false;
        if (iDanmakus != null && !iDanmakus.isEmpty()) {
            this.mRenderer.draw(this.mDisp, iDanmakus, this.mStartRenderTime, renderingState);
            endTracing(renderingState);
            if (renderingState.nothingRendered) {
                BaseDanmaku baseDanmaku = this.mLastDanmaku;
                if (baseDanmaku != null && baseDanmaku.isTimeOut()) {
                    this.mLastDanmaku = null;
                    IDrawTask.TaskListener taskListener = this.mTaskListener;
                    if (taskListener != null) {
                        taskListener.onDanmakusDrawingFinished();
                    }
                }
                if (renderingState.beginTime == -1) {
                    renderingState.beginTime = j;
                }
                if (renderingState.endTime == -1) {
                    renderingState.endTime = j2;
                }
            }
            return renderingState;
        }
        renderingState.nothingRendered = true;
        renderingState.beginTime = j;
        renderingState.endTime = j2;
        return renderingState;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestClear() {
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        this.mIsHidden = false;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestClearRetainer() {
        this.clearRetainerFlag = true;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestSync(long j, long j2, long j3) {
        IDanmakus iDanmakusObtainRunningDanmakus = this.mRenderingState.obtainRunningDanmakus();
        this.mRunningDanmakus = iDanmakusObtainRunningDanmakus;
        IDanmakuIterator it = iDanmakusObtainRunningDanmakus.iterator();
        while (it.hasNext()) {
            BaseDanmaku next = it.next();
            if (next.isOutside()) {
                it.remove();
            } else {
                next.setTimeOffset(next.timeOffset + j3);
                next.isOffset = true;
            }
        }
        this.mStartRenderTime = j2;
    }

    public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        boolean zHandleOnDanmakuConfigChanged = handleOnDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr);
        IDrawTask.TaskListener taskListener = this.mTaskListener;
        if (taskListener != null) {
            taskListener.onDanmakuConfigChanged();
        }
        return zHandleOnDanmakuConfigChanged;
    }

    protected boolean handleOnDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object[] objArr) {
        Boolean bool;
        if (danmakuConfigTag == null || DanmakuContext.DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN.equals(danmakuConfigTag)) {
            return true;
        }
        if (DanmakuContext.DanmakuConfigTag.DUPLICATE_MERGING_ENABLED.equals(danmakuConfigTag)) {
            Boolean bool2 = (Boolean) objArr[0];
            if (bool2 != null) {
                if (bool2.booleanValue()) {
                    this.mContext.mDanmakuFilters.registerFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
                    return true;
                }
                this.mContext.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
                return true;
            }
        } else if (DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
            requestClearRetainer();
        } else {
            if (DanmakuContext.DanmakuConfigTag.MAXIMUN_LINES.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.OVERLAPPING_ENABLE.equals(danmakuConfigTag)) {
                IRenderer iRenderer = this.mRenderer;
                if (iRenderer == null) {
                    return true;
                }
                iRenderer.setVerifierEnabled(this.mContext.isPreventOverlappingEnabled() || this.mContext.isMaxLinesLimited());
                return true;
            }
            if (DanmakuContext.DanmakuConfigTag.ALIGN_BOTTOM.equals(danmakuConfigTag) && (bool = (Boolean) objArr[0]) != null) {
                IRenderer iRenderer2 = this.mRenderer;
                if (iRenderer2 == null) {
                    return true;
                }
                iRenderer2.alignBottom(bool.booleanValue());
                return true;
            }
        }
        return false;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestHide() {
        this.mIsHidden = true;
    }

    private void beginTracing(IRenderer.RenderingState renderingState, IDanmakus iDanmakus, IDanmakus iDanmakus2) {
        renderingState.reset();
        renderingState.timer.update(SystemClock.uptimeMillis());
        renderingState.indexInScreen = 0;
        renderingState.totalSizeInScreen = (iDanmakus != null ? iDanmakus.size() : 0) + (iDanmakus2 != null ? iDanmakus2.size() : 0);
    }

    private void endTracing(IRenderer.RenderingState renderingState) {
        renderingState.nothingRendered = renderingState.totalDanmakuCount == 0;
        if (renderingState.nothingRendered) {
            renderingState.beginTime = -1L;
        }
        BaseDanmaku baseDanmaku = renderingState.lastDanmaku;
        renderingState.lastDanmaku = null;
        renderingState.endTime = baseDanmaku != null ? baseDanmaku.getActualTime() : -1L;
        renderingState.consumingTime = renderingState.timer.update(SystemClock.uptimeMillis());
    }
}
