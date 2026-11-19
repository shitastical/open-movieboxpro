package master.flame.danmaku.danmaku.renderer.android;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.util.DanmakuUtils;

/* JADX INFO: loaded from: classes6.dex */
public class DanmakusRetainer {
    private IDanmakusRetainer rldrInstance = null;
    private IDanmakusRetainer lrdrInstance = null;
    private IDanmakusRetainer ftdrInstance = null;
    private IDanmakusRetainer fbdrInstance = null;

    public interface IDanmakusRetainer {
        void clear();

        void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier);
    }

    public interface Verifier {
        boolean skipLayout(BaseDanmaku baseDanmaku, float f, int i, boolean z);
    }

    public DanmakusRetainer(boolean z) {
        alignBottom(z);
    }

    public void alignBottom(boolean z) {
        this.rldrInstance = z ? new AlignBottomRetainer() : new AlignTopRetainer();
        this.lrdrInstance = z ? new AlignBottomRetainer() : new AlignTopRetainer();
        if (this.ftdrInstance == null) {
            this.ftdrInstance = new FTDanmakusRetainer();
        }
        if (this.fbdrInstance == null) {
            this.fbdrInstance = new AlignBottomRetainer();
        }
    }

    public void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier) {
        int type = baseDanmaku.getType();
        if (type == 1) {
            this.rldrInstance.fix(baseDanmaku, iDisplayer, verifier);
            return;
        }
        if (type == 4) {
            this.fbdrInstance.fix(baseDanmaku, iDisplayer, verifier);
            return;
        }
        if (type == 5) {
            this.ftdrInstance.fix(baseDanmaku, iDisplayer, verifier);
        } else if (type == 6) {
            this.lrdrInstance.fix(baseDanmaku, iDisplayer, verifier);
        } else {
            if (type != 7) {
                return;
            }
            baseDanmaku.layout(iDisplayer, 0.0f, 0.0f);
        }
    }

    public void clear() {
        IDanmakusRetainer iDanmakusRetainer = this.rldrInstance;
        if (iDanmakusRetainer != null) {
            iDanmakusRetainer.clear();
        }
        IDanmakusRetainer iDanmakusRetainer2 = this.lrdrInstance;
        if (iDanmakusRetainer2 != null) {
            iDanmakusRetainer2.clear();
        }
        IDanmakusRetainer iDanmakusRetainer3 = this.ftdrInstance;
        if (iDanmakusRetainer3 != null) {
            iDanmakusRetainer3.clear();
        }
        IDanmakusRetainer iDanmakusRetainer4 = this.fbdrInstance;
        if (iDanmakusRetainer4 != null) {
            iDanmakusRetainer4.clear();
        }
    }

    public void release() {
        clear();
    }

    private static class AlignTopRetainer implements IDanmakusRetainer {
        protected boolean mCancelFixingFlag;
        protected Danmakus mVisibleDanmakus;

        private AlignTopRetainer() {
            this.mVisibleDanmakus = new Danmakus(1);
            this.mCancelFixingFlag = false;
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier) {
            boolean z;
            int i;
            BaseDanmaku baseDanmaku2;
            BaseDanmaku next;
            boolean z2;
            float top;
            float bottom;
            boolean z3;
            float top2;
            if (baseDanmaku.isOutside()) {
                return;
            }
            boolean zIsShown = baseDanmaku.isShown();
            int i2 = 1;
            int i3 = 0;
            boolean zWillHitInDuration = (zIsShown || this.mVisibleDanmakus.isEmpty()) ? false : true;
            float f = 0.0f;
            BaseDanmaku baseDanmaku3 = null;
            if (zIsShown) {
                z = false;
            } else {
                this.mCancelFixingFlag = false;
                IDanmakuIterator it = this.mVisibleDanmakus.iterator();
                BaseDanmaku baseDanmaku4 = null;
                BaseDanmaku baseDanmaku5 = null;
                BaseDanmaku baseDanmaku6 = null;
                int i4 = 0;
                while (true) {
                    if (this.mCancelFixingFlag || !it.hasNext()) {
                        i = i4;
                        baseDanmaku2 = baseDanmaku4;
                        next = null;
                        z2 = false;
                        break;
                    }
                    i = i4 + 1;
                    next = it.next();
                    if (next == baseDanmaku) {
                        baseDanmaku2 = baseDanmaku4;
                        baseDanmaku6 = null;
                        zWillHitInDuration = false;
                        z2 = false;
                        zIsShown = true;
                        break;
                    }
                    baseDanmaku2 = baseDanmaku4 == null ? next : baseDanmaku4;
                    if (baseDanmaku.paintHeight + next.getTop() > iDisplayer.getHeight()) {
                        next = null;
                        baseDanmaku6 = baseDanmaku6;
                        z2 = true;
                        break;
                    }
                    BaseDanmaku baseDanmaku7 = (baseDanmaku5 != null && baseDanmaku5.getRight() < next.getRight()) ? baseDanmaku5 : next;
                    zWillHitInDuration = DanmakuUtils.willHitInDuration(iDisplayer, next, baseDanmaku, baseDanmaku.getDuration(), baseDanmaku.getTimer().currMillisecond);
                    if (!zWillHitInDuration) {
                        baseDanmaku5 = baseDanmaku7;
                        next = next;
                        z2 = false;
                        break;
                    } else {
                        i4 = i;
                        baseDanmaku4 = baseDanmaku2;
                        baseDanmaku5 = baseDanmaku7;
                        baseDanmaku6 = next;
                    }
                }
                if (next != null) {
                    if (baseDanmaku6 != null) {
                        top2 = baseDanmaku6.getBottom();
                    } else {
                        top2 = next.getTop();
                    }
                    if (next != baseDanmaku) {
                        top = top2;
                        baseDanmaku3 = next;
                        z3 = true;
                        zIsShown = false;
                    } else {
                        top = top2;
                        z3 = true;
                    }
                } else {
                    if (z2 && baseDanmaku5 != null) {
                        bottom = baseDanmaku5.getTop();
                        zWillHitInDuration = zWillHitInDuration;
                        z3 = false;
                        zIsShown = false;
                    } else if (baseDanmaku6 != null) {
                        bottom = baseDanmaku6.getBottom();
                        zIsShown = zIsShown;
                        z3 = true;
                        zWillHitInDuration = false;
                    } else if (baseDanmaku2 != null) {
                        top = baseDanmaku2.getTop();
                        baseDanmaku3 = baseDanmaku2;
                        z3 = true;
                        zIsShown = false;
                    } else {
                        top = 0.0f;
                        z3 = true;
                    }
                    top = bottom;
                }
                boolean zIsOutVerticalEdge = z3 ? isOutVerticalEdge(z2, baseDanmaku, iDisplayer, top, baseDanmaku2, baseDanmaku6) : false;
                if (zIsOutVerticalEdge) {
                    zWillHitInDuration = true;
                    top = 0.0f;
                } else {
                    i2 = baseDanmaku3 != null ? i - 1 : i;
                }
                if (top == 0.0f) {
                    z = zIsOutVerticalEdge;
                    zWillHitInDuration = zWillHitInDuration;
                    i3 = i2;
                    f = top;
                    baseDanmaku3 = baseDanmaku3;
                    zIsShown = false;
                } else {
                    z = zIsOutVerticalEdge;
                    zWillHitInDuration = zWillHitInDuration;
                    i3 = i2;
                    f = top;
                    zIsShown = zIsShown;
                    baseDanmaku3 = baseDanmaku3;
                }
            }
            if (verifier == null || !verifier.skipLayout(baseDanmaku, f, i3, zWillHitInDuration)) {
                if (z) {
                    clear();
                }
                baseDanmaku.layout(iDisplayer, baseDanmaku.getLeft(), f);
                if (zIsShown) {
                    return;
                }
                this.mVisibleDanmakus.removeItem(baseDanmaku3);
                this.mVisibleDanmakus.addItem(baseDanmaku);
            }
        }

        protected boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            if (f >= 0.0f) {
                return (baseDanmaku2 != null && baseDanmaku2.getTop() > 0.0f) || f + baseDanmaku.paintHeight > ((float) iDisplayer.getHeight());
            }
            return true;
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void clear() {
            this.mCancelFixingFlag = true;
            this.mVisibleDanmakus.clear();
        }
    }

    private static class FTDanmakusRetainer extends AlignTopRetainer {
        private FTDanmakusRetainer() {
            super();
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer
        protected boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            return f + baseDanmaku.paintHeight > ((float) iDisplayer.getHeight());
        }
    }

    private static class AlignBottomRetainer extends FTDanmakusRetainer {
        protected Danmakus mVisibleDanmakus;

        private AlignBottomRetainer() {
            super();
            this.mVisibleDanmakus = new Danmakus(2);
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier) {
            int i;
            BaseDanmaku next;
            boolean z;
            BaseDanmaku baseDanmaku2;
            if (baseDanmaku.isOutside()) {
                return;
            }
            boolean zIsShown = baseDanmaku.isShown();
            float top = zIsShown ? baseDanmaku.getTop() : -1.0f;
            int i2 = 1;
            boolean z2 = false;
            boolean zWillHitInDuration = (zIsShown || this.mVisibleDanmakus.isEmpty()) ? false : true;
            if (top < 0.0f) {
                top = iDisplayer.getHeight() - baseDanmaku.paintHeight;
            }
            BaseDanmaku baseDanmaku3 = null;
            if (zIsShown) {
                i2 = 0;
            } else {
                this.mCancelFixingFlag = false;
                IDanmakuIterator it = this.mVisibleDanmakus.iterator();
                float top2 = top;
                BaseDanmaku baseDanmaku4 = null;
                int i3 = 0;
                while (true) {
                    if (this.mCancelFixingFlag || !it.hasNext()) {
                        i = i3;
                        next = baseDanmaku4;
                        z = zWillHitInDuration;
                    } else {
                        i = i3 + 1;
                        next = it.next();
                        if (next == baseDanmaku) {
                            next = baseDanmaku4;
                            baseDanmaku3 = null;
                            z = false;
                        } else {
                            if (baseDanmaku4 != null) {
                                baseDanmaku2 = baseDanmaku4;
                            } else if (next.getBottom() != iDisplayer.getHeight()) {
                                z = zWillHitInDuration;
                            } else {
                                baseDanmaku2 = next;
                            }
                            if (top2 < 0.0f) {
                                next = baseDanmaku2;
                                z = zWillHitInDuration;
                            } else {
                                baseDanmaku3 = next;
                                zWillHitInDuration = DanmakuUtils.willHitInDuration(iDisplayer, next, baseDanmaku, baseDanmaku.getDuration(), baseDanmaku.getTimer().currMillisecond);
                                if (zWillHitInDuration) {
                                    top2 = baseDanmaku3.getTop() - baseDanmaku.paintHeight;
                                    i3 = i;
                                    baseDanmaku4 = baseDanmaku2;
                                } else {
                                    z = zWillHitInDuration;
                                    next = baseDanmaku2;
                                }
                            }
                        }
                    }
                    boolean zIsOutVerticalEdge = isOutVerticalEdge(false, baseDanmaku, iDisplayer, top2, next, null);
                    if (zIsOutVerticalEdge) {
                        z2 = zIsOutVerticalEdge;
                        top = iDisplayer.getHeight() - baseDanmaku.paintHeight;
                        baseDanmaku3 = baseDanmaku3;
                        zWillHitInDuration = true;
                    } else {
                        boolean z3 = top2 >= 0.0f ? false : z;
                        if (baseDanmaku3 != null) {
                            z2 = zIsOutVerticalEdge;
                            top = top2;
                            baseDanmaku3 = baseDanmaku3;
                            boolean z4 = z3;
                            i2 = i - 1;
                            zWillHitInDuration = z4;
                        } else {
                            z2 = zIsOutVerticalEdge;
                            zWillHitInDuration = z3;
                            top = top2;
                            i2 = i;
                            baseDanmaku3 = baseDanmaku3;
                        }
                    }
                }
            }
            if (verifier == null || !verifier.skipLayout(baseDanmaku, top, i2, zWillHitInDuration)) {
                if (z2) {
                    clear();
                }
                baseDanmaku.layout(iDisplayer, baseDanmaku.getLeft(), top);
                if (zIsShown) {
                    return;
                }
                this.mVisibleDanmakus.removeItem(baseDanmaku3);
                this.mVisibleDanmakus.addItem(baseDanmaku);
            }
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.FTDanmakusRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer
        protected boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            if (f >= 0.0f) {
                return (baseDanmaku2 == null || baseDanmaku2.getBottom() == ((float) iDisplayer.getHeight())) ? false : true;
            }
            return true;
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void clear() {
            this.mCancelFixingFlag = true;
            this.mVisibleDanmakus.clear();
        }
    }
}
