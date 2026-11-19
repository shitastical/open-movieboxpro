package com.alibaba.android.vlayout.layout;

import android.util.SparseIntArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class GridLayoutHelper extends BaseLayoutHelper {
    private static boolean DEBUG = false;
    private static final int MAIN_DIR_SPEC = View.MeasureSpec.makeMeasureSpec(0, 0);
    private static final String TAG = "GridLayoutHelper";
    private int mHGap;
    private boolean mIgnoreExtra;
    private boolean mIsAutoExpand;
    private boolean mLayoutWithAnchor;
    private View[] mSet;
    private int mSizePerSpan;
    private int[] mSpanCols;
    private int mSpanCount;
    private int[] mSpanIndices;
    private SpanSizeLookup mSpanSizeLookup;
    private int mTotalSize;
    private int mVGap;
    private float[] mWeights;

    public GridLayoutHelper(int i) {
        this(i, -1, -1);
    }

    public GridLayoutHelper(int i, int i2) {
        this(i, i2, 0);
    }

    public GridLayoutHelper(int i, int i2, int i3) {
        this(i, i2, i3, i3);
    }

    public GridLayoutHelper(int i, int i2, int i3, int i4) {
        this.mSpanCount = 4;
        this.mSizePerSpan = 0;
        this.mTotalSize = 0;
        this.mIsAutoExpand = true;
        this.mIgnoreExtra = false;
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mVGap = 0;
        this.mHGap = 0;
        this.mWeights = new float[0];
        this.mLayoutWithAnchor = false;
        setSpanCount(i);
        this.mSpanSizeLookup.setSpanIndexCacheEnabled(true);
        setItemCount(i2);
        setVGap(i3);
        setHGap(i4);
    }

    public void setWeights(float[] fArr) {
        if (fArr != null) {
            this.mWeights = Arrays.copyOf(fArr, fArr.length);
        } else {
            this.mWeights = new float[0];
        }
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        if (spanSizeLookup != null) {
            spanSizeLookup.setStartPosition(this.mSpanSizeLookup.getStartPosition());
            this.mSpanSizeLookup = spanSizeLookup;
        }
    }

    public void setAutoExpand(boolean z) {
        this.mIsAutoExpand = z;
    }

    public void setIgnoreExtra(boolean z) {
        this.mIgnoreExtra = z;
    }

    public void setSpanCount(int i) {
        if (i == this.mSpanCount) {
            return;
        }
        if (i < 1) {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
        }
        this.mSpanCount = i;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        ensureSpanCount();
    }

    public int getVGap() {
        return this.mVGap;
    }

    public int getHGap() {
        return this.mHGap;
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRangeChange(int i, int i2) {
        this.mSpanSizeLookup.setStartPosition(i);
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void setGap(int i) {
        setVGap(i);
        setHGap(i);
    }

    public void setVGap(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mVGap = i;
    }

    public void setHGap(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mHGap = i;
    }

    /* JADX WARN: Code duplicated, block: B:108:0x025b  */
    /* JADX WARN: Code duplicated, block: B:114:0x028c  */
    /* JADX WARN: Code duplicated, block: B:117:0x0291 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:118:0x0292  */
    /* JADX WARN: Code duplicated, block: B:126:0x02cc A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:127:0x02ce A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:132:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:133:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:156:0x0341  */
    /* JADX WARN: Code duplicated, block: B:166:0x035d  */
    /* JADX WARN: Code duplicated, block: B:169:0x0362  */
    /* JADX WARN: Code duplicated, block: B:171:0x0368  */
    /* JADX WARN: Code duplicated, block: B:172:0x036a  */
    /* JADX WARN: Code duplicated, block: B:175:0x0378  */
    /* JADX WARN: Code duplicated, block: B:177:0x0380 A[LOOP:5: B:176:0x037e->B:177:0x0380, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:179:0x0396  */
    /* JADX WARN: Code duplicated, block: B:181:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:182:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:186:0x03be  */
    /* JADX WARN: Code duplicated, block: B:187:0x03d0  */
    /* JADX WARN: Code duplicated, block: B:190:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:194:0x0403  */
    /* JADX WARN: Code duplicated, block: B:196:0x040d  */
    /* JADX WARN: Code duplicated, block: B:198:0x0417  */
    /* JADX WARN: Code duplicated, block: B:200:0x041f A[LOOP:7: B:199:0x041d->B:200:0x041f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:202:0x043a  */
    /* JADX WARN: Code duplicated, block: B:204:0x0447  */
    /* JADX WARN: Code duplicated, block: B:205:0x044a  */
    /* JADX WARN: Code duplicated, block: B:209:0x045c  */
    /* JADX WARN: Code duplicated, block: B:210:0x0460  */
    /* JADX WARN: Code duplicated, block: B:211:0x0464  */
    /* JADX WARN: Code duplicated, block: B:214:0x046f  */
    /* JADX WARN: Code duplicated, block: B:215:0x047c  */
    /* JADX WARN: Code duplicated, block: B:217:0x0482  */
    /* JADX WARN: Code duplicated, block: B:218:0x048c  */
    /* JADX WARN: Code duplicated, block: B:221:0x049b  */
    /* JADX WARN: Code duplicated, block: B:222:0x049d  */
    /* JADX WARN: Code duplicated, block: B:231:0x04ae  */
    /* JADX WARN: Code duplicated, block: B:232:0x04b1  */
    /* JADX WARN: Code duplicated, block: B:235:0x04b8  */
    /* JADX WARN: Code duplicated, block: B:237:0x04bf  */
    /* JADX WARN: Code duplicated, block: B:242:0x04ce  */
    /* JADX WARN: Code duplicated, block: B:244:0x04d6  */
    /* JADX WARN: Code duplicated, block: B:249:0x04e5  */
    /* JADX WARN: Code duplicated, block: B:252:0x04ee  */
    /* JADX WARN: Code duplicated, block: B:254:0x04f5  */
    /* JADX WARN: Code duplicated, block: B:259:0x0504  */
    /* JADX WARN: Code duplicated, block: B:261:0x0511  */
    /* JADX WARN: Code duplicated, block: B:266:0x0520  */
    /* JADX WARN: Code duplicated, block: B:270:0x0529  */
    /* JADX WARN: Code duplicated, block: B:272:0x053b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:273:0x053d  */
    /* JADX WARN: Code duplicated, block: B:275:0x054a A[LOOP:9: B:274:0x0548->B:275:0x054a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:276:0x0555  */
    /* JADX WARN: Code duplicated, block: B:278:0x0573 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:279:0x0575  */
    /* JADX WARN: Code duplicated, block: B:281:0x0582 A[LOOP:10: B:280:0x0580->B:281:0x0582, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:282:0x058d  */
    /* JADX WARN: Code duplicated, block: B:286:0x05b1  */
    /* JADX WARN: Code duplicated, block: B:287:0x0601  */
    /* JADX WARN: Code duplicated, block: B:294:0x0628  */
    /* JADX WARN: Code duplicated, block: B:314:0x03ee A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:96:0x022a A[PHI: r25
      0x022a: PHI (r25v3 int) = (r25v2 int), (r25v6 int) binds: [B:95:0x0228, B:92:0x0215] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x019f, code lost:
    
        r14 = r14 + r5;
        r29.mSet[r11] = r6;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x011e, code lost:
    
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x014c, code lost:
    
        r11 = true;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:286:0x05b1, please report this as an issue */
    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void layoutViews(androidx.recyclerview.widget.RecyclerView.Recycler r30, androidx.recyclerview.widget.RecyclerView.State r31, com.alibaba.android.vlayout.VirtualLayoutManager.LayoutStateWrapper r32, com.alibaba.android.vlayout.layout.LayoutChunkResult r33, com.alibaba.android.vlayout.LayoutManagerHelper r34) {
        /*
            Method dump skipped, instruction units count: 1622
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.vlayout.layout.GridLayoutHelper.layoutViews(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, com.alibaba.android.vlayout.VirtualLayoutManager$LayoutStateWrapper, com.alibaba.android.vlayout.layout.LayoutChunkResult, com.alibaba.android.vlayout.LayoutManagerHelper):void");
    }

    @Override // com.alibaba.android.vlayout.layout.MarginLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public int computeAlignOffset(int i, boolean z, boolean z2, LayoutManagerHelper layoutManagerHelper) {
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z3 = layoutManagerHelper.getOrientation() == 1;
        if (z) {
            if (i == getItemCount() - 1) {
                if (z3) {
                    i4 = this.mMarginBottom;
                    i5 = this.mPaddingBottom;
                } else {
                    i4 = this.mMarginRight;
                    i5 = this.mPaddingRight;
                }
                return i4 + i5;
            }
        } else if (i == 0) {
            if (z3) {
                i2 = -this.mMarginTop;
                i3 = this.mPaddingTop;
            } else {
                i2 = -this.mMarginLeft;
                i3 = this.mPaddingLeft;
            }
            return i2 - i3;
        }
        return super.computeAlignOffset(i, z, z2, layoutManagerHelper);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void onClear(LayoutManagerHelper layoutManagerHelper) {
        super.onClear(layoutManagerHelper);
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onItemsChanged(LayoutManagerHelper layoutManagerHelper) {
        super.onItemsChanged(layoutManagerHelper);
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    private int getMainDirSpec(int i, int i2, int i3, float f) {
        if (!Float.isNaN(f) && f > 0.0f && i3 > 0) {
            return View.MeasureSpec.makeMeasureSpec((int) ((i3 / f) + 0.5f), 1073741824);
        }
        if (!Float.isNaN(this.mAspectRatio) && this.mAspectRatio > 0.0f) {
            return View.MeasureSpec.makeMeasureSpec((int) ((i2 / this.mAspectRatio) + 0.5f), 1073741824);
        }
        if (i < 0) {
            return MAIN_DIR_SPEC;
        }
        return View.MeasureSpec.makeMeasureSpec(i, 1073741824);
    }

    private void ensureSpanCount() {
        View[] viewArr = this.mSet;
        if (viewArr == null || viewArr.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
        int[] iArr = this.mSpanIndices;
        if (iArr == null || iArr.length != this.mSpanCount) {
            this.mSpanIndices = new int[this.mSpanCount];
        }
        int[] iArr2 = this.mSpanCols;
        if (iArr2 == null || iArr2.length != this.mSpanCount) {
            this.mSpanCols = new int[this.mSpanCount];
        }
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void checkAnchorInfo(RecyclerView.State state, VirtualLayoutManager.AnchorInfoWrapper anchorInfoWrapper, LayoutManagerHelper layoutManagerHelper) {
        if (state.getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        int cachedSpanIndex = this.mSpanSizeLookup.getCachedSpanIndex(anchorInfoWrapper.position, this.mSpanCount);
        if (anchorInfoWrapper.layoutFromEnd) {
            while (cachedSpanIndex < this.mSpanCount - 1 && anchorInfoWrapper.position < ((Integer) getRange().getUpper()).intValue()) {
                anchorInfoWrapper.position++;
                cachedSpanIndex = this.mSpanSizeLookup.getCachedSpanIndex(anchorInfoWrapper.position, this.mSpanCount);
            }
        } else {
            while (cachedSpanIndex > 0 && anchorInfoWrapper.position > 0) {
                anchorInfoWrapper.position--;
                cachedSpanIndex = this.mSpanSizeLookup.getCachedSpanIndex(anchorInfoWrapper.position, this.mSpanCount);
            }
        }
        this.mLayoutWithAnchor = true;
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanIndex(i, this.mSpanCount);
        }
        int iConvertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (iConvertPreLayoutPositionToPostLayout == -1) {
            return 0;
        }
        return this.mSpanSizeLookup.getCachedSpanIndex(iConvertPreLayoutPositionToPostLayout, this.mSpanCount);
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanSize(i);
        }
        int iConvertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (iConvertPreLayoutPositionToPostLayout == -1) {
            return 0;
        }
        return this.mSpanSizeLookup.getSpanSize(iConvertPreLayoutPositionToPostLayout);
    }

    private void assignSpans(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, boolean z, LayoutManagerHelper layoutManagerHelper) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if (z) {
            i4 = i;
            i3 = 0;
            i5 = 1;
        } else {
            i3 = i - 1;
            i4 = -1;
            i5 = -1;
        }
        if (layoutManagerHelper.getOrientation() == 1 && layoutManagerHelper.isDoLayoutRTL()) {
            i7 = i2 - 1;
            i6 = -1;
        } else {
            i6 = 1;
        }
        while (i3 != i4) {
            int spanSize = getSpanSize(recycler, state, layoutManagerHelper.getPosition(this.mSet[i3]));
            if (i6 == -1 && spanSize > 1) {
                this.mSpanIndices[i3] = i7 - (spanSize - 1);
            } else {
                this.mSpanIndices[i3] = i7;
            }
            i7 += spanSize * i6;
            i3 += i5;
        }
    }

    static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        @Override // com.alibaba.android.vlayout.layout.GridLayoutHelper.SpanSizeLookup
        public int getSpanSize(int i) {
            return 1;
        }

        DefaultSpanSizeLookup() {
        }

        @Override // com.alibaba.android.vlayout.layout.GridLayoutHelper.SpanSizeLookup
        public int getSpanIndex(int i, int i2) {
            return (i - this.mStartPosition) % i2;
        }
    }

    public static abstract class SpanSizeLookup {
        final SparseIntArray mSpanIndexCache = new SparseIntArray();
        private boolean mCacheSpanIndices = false;
        int mStartPosition = 0;

        public abstract int getSpanSize(int i);

        public void setSpanIndexCacheEnabled(boolean z) {
            this.mCacheSpanIndices = z;
        }

        public void setStartPosition(int i) {
            this.mStartPosition = i;
        }

        public int getStartPosition() {
            return this.mStartPosition;
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        int getCachedSpanIndex(int i, int i2) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(i, i2);
            }
            int i3 = this.mSpanIndexCache.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int spanIndex = getSpanIndex(i, i2);
            this.mSpanIndexCache.put(i, spanIndex);
            return spanIndex;
        }

        public int getSpanIndex(int i, int i2) {
            int i3;
            int iFindReferenceIndexFromCache;
            int spanSize = getSpanSize(i);
            if (spanSize == i2) {
                return 0;
            }
            int i4 = this.mStartPosition;
            if (!this.mCacheSpanIndices || this.mSpanIndexCache.size() <= 0 || (iFindReferenceIndexFromCache = findReferenceIndexFromCache(i)) < 0) {
                i3 = 0;
            } else {
                int spanSize2 = this.mSpanIndexCache.get(iFindReferenceIndexFromCache) + getSpanSize(iFindReferenceIndexFromCache);
                int i5 = iFindReferenceIndexFromCache + 1;
                i3 = spanSize2;
                i4 = i5;
            }
            while (i4 < i) {
                int spanSize3 = getSpanSize(i4);
                i3 += spanSize3;
                if (i3 == i2) {
                    i3 = 0;
                } else if (i3 > i2) {
                    i3 = spanSize3;
                }
                i4++;
            }
            if (spanSize + i3 <= i2) {
                return i3;
            }
            return 0;
        }

        int findReferenceIndexFromCache(int i) {
            int size = this.mSpanIndexCache.size() - 1;
            int i2 = 0;
            while (i2 <= size) {
                int i3 = (i2 + size) >>> 1;
                if (this.mSpanIndexCache.keyAt(i3) < i) {
                    i2 = i3 + 1;
                } else {
                    size = i3 - 1;
                }
            }
            int i4 = i2 - 1;
            if (i4 < 0 || i4 >= this.mSpanIndexCache.size()) {
                return -1;
            }
            return this.mSpanIndexCache.keyAt(i4);
        }

        public int getSpanGroupIndex(int i, int i2) {
            int spanSize = getSpanSize(i);
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int spanSize2 = getSpanSize(i5);
                i3 += spanSize2;
                if (i3 == i2) {
                    i4++;
                    i3 = 0;
                } else if (i3 > i2) {
                    i4++;
                    i3 = spanSize2;
                }
            }
            return i3 + spanSize > i2 ? i4 + 1 : i4;
        }
    }
}
