package com.alibaba.android.vlayout.layout;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.Range;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RangeGridLayoutHelper extends BaseLayoutHelper {
    private static boolean DEBUG = false;
    private static final int MAIN_DIR_SPEC = View.MeasureSpec.makeMeasureSpec(0, 0);
    private static final String TAG = "RGLayoutHelper";
    private boolean mLayoutWithAnchor;
    private GridRangeStyle mRangeStyle;
    private int mTotalSize;

    public RangeGridLayoutHelper(int i) {
        this(i, -1, -1);
    }

    public RangeGridLayoutHelper(int i, int i2) {
        this(i, i2, 0);
    }

    public RangeGridLayoutHelper(int i, int i2, int i3) {
        this(i, i2, i3, i3);
    }

    public RangeGridLayoutHelper(int i, int i2, int i3, int i4) {
        this.mTotalSize = 0;
        this.mLayoutWithAnchor = false;
        GridRangeStyle gridRangeStyle = new GridRangeStyle(this);
        this.mRangeStyle = gridRangeStyle;
        gridRangeStyle.setSpanCount(i);
        this.mRangeStyle.setVGap(i3);
        this.mRangeStyle.setHGap(i4);
        setItemCount(i2);
    }

    public void addRangeStyle(int i, int i2, GridRangeStyle gridRangeStyle) {
        this.mRangeStyle.addChildRangeStyle(i, i2, gridRangeStyle);
    }

    public GridRangeStyle getRootRangeStyle() {
        return this.mRangeStyle;
    }

    @Override // com.alibaba.android.vlayout.layout.MarginLayoutHelper
    public void setMargin(int i, int i2, int i3, int i4) {
        super.setMargin(i, i2, i3, i4);
        this.mRangeStyle.setMargin(i, i2, i3, i4);
    }

    @Override // com.alibaba.android.vlayout.layout.MarginLayoutHelper
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        this.mRangeStyle.setPadding(i, i2, i3, i4);
    }

    public void setWeights(float[] fArr) {
        this.mRangeStyle.setWeights(fArr);
    }

    public void setSpanSizeLookup(GridLayoutHelper.SpanSizeLookup spanSizeLookup) {
        this.mRangeStyle.setSpanSizeLookup(spanSizeLookup);
    }

    public void setAutoExpand(boolean z) {
        this.mRangeStyle.setAutoExpand(z);
    }

    public void setIgnoreExtra(boolean z) {
        this.mRangeStyle.setIgnoreExtra(z);
    }

    public void setSpanCount(int i) {
        this.mRangeStyle.setSpanCount(i);
    }

    public int getSpanCount() {
        return this.mRangeStyle.getSpanCount();
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onRangeChange(int i, int i2) {
        this.mRangeStyle.setRange(i, i2);
    }

    public void setGap(int i) {
        setVGap(i);
        setHGap(i);
    }

    public void setVGap(int i) {
        this.mRangeStyle.setVGap(i);
    }

    public void setHGap(int i) {
        this.mRangeStyle.setHGap(i);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void setAspectRatio(float f) {
        this.mRangeStyle.setAspectRatio(f);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public float getAspectRatio() {
        return this.mRangeStyle.getAspectRatio();
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void setBgColor(int i) {
        this.mRangeStyle.setBgColor(i);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void setLayoutViewHelper(BaseLayoutHelper.DefaultLayoutViewHelper defaultLayoutViewHelper) {
        this.mRangeStyle.setLayoutViewHelper(defaultLayoutViewHelper);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void setLayoutViewBindListener(BaseLayoutHelper.LayoutViewBindListener layoutViewBindListener) {
        this.mRangeStyle.setLayoutViewBindListener(layoutViewBindListener);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void setLayoutViewUnBindListener(BaseLayoutHelper.LayoutViewUnBindListener layoutViewUnBindListener) {
        this.mRangeStyle.setLayoutViewUnBindListener(layoutViewUnBindListener);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public boolean requireLayoutView() {
        return this.mRangeStyle.requireLayoutView();
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void beforeLayout(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutManagerHelper layoutManagerHelper) {
        this.mRangeStyle.beforeLayout(recycler, state, layoutManagerHelper);
    }

    /* JADX WARN: Code duplicated, block: B:133:0x036e A[PHI: r2
      0x036e: PHI (r2v77 boolean) = (r2v4 boolean), (r2v4 boolean), (r2v85 boolean) binds: [B:118:0x02f2, B:120:0x02fa, B:131:0x0328] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:144:0x03c3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:145:0x03c4  */
    /* JADX WARN: Code duplicated, block: B:147:0x03e4 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:154:0x0410  */
    /* JADX WARN: Code duplicated, block: B:156:0x0414 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:161:0x0420  */
    /* JADX WARN: Code duplicated, block: B:162:0x0430  */
    /* JADX WARN: Code duplicated, block: B:165:0x0446  */
    /* JADX WARN: Code duplicated, block: B:195:0x04d9  */
    /* JADX WARN: Code duplicated, block: B:198:0x04de  */
    /* JADX WARN: Code duplicated, block: B:200:0x04e8  */
    /* JADX WARN: Code duplicated, block: B:201:0x04ea  */
    /* JADX WARN: Code duplicated, block: B:204:0x04fe  */
    /* JADX WARN: Code duplicated, block: B:206:0x0508 A[LOOP:6: B:205:0x0506->B:206:0x0508, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:208:0x0521  */
    /* JADX WARN: Code duplicated, block: B:210:0x0530  */
    /* JADX WARN: Code duplicated, block: B:211:0x0535  */
    /* JADX WARN: Code duplicated, block: B:215:0x0550  */
    /* JADX WARN: Code duplicated, block: B:216:0x057c  */
    /* JADX WARN: Code duplicated, block: B:219:0x05a7  */
    /* JADX WARN: Code duplicated, block: B:220:0x05a9  */
    /* JADX WARN: Code duplicated, block: B:224:0x05da  */
    /* JADX WARN: Code duplicated, block: B:226:0x05e6  */
    /* JADX WARN: Code duplicated, block: B:228:0x05f4  */
    /* JADX WARN: Code duplicated, block: B:230:0x05fe A[LOOP:8: B:229:0x05fc->B:230:0x05fe, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:232:0x0615  */
    /* JADX WARN: Code duplicated, block: B:234:0x0624  */
    /* JADX WARN: Code duplicated, block: B:235:0x0629  */
    /* JADX WARN: Code duplicated, block: B:239:0x063b  */
    /* JADX WARN: Code duplicated, block: B:240:0x063f  */
    /* JADX WARN: Code duplicated, block: B:241:0x0643  */
    /* JADX WARN: Code duplicated, block: B:245:0x0650  */
    /* JADX WARN: Code duplicated, block: B:246:0x0652  */
    /* JADX WARN: Code duplicated, block: B:249:0x065b  */
    /* JADX WARN: Code duplicated, block: B:250:0x0661  */
    /* JADX WARN: Code duplicated, block: B:252:0x0664 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:253:0x0666  */
    /* JADX WARN: Code duplicated, block: B:254:0x066f  */
    /* JADX WARN: Code duplicated, block: B:256:0x067b  */
    /* JADX WARN: Code duplicated, block: B:258:0x067e  */
    /* JADX WARN: Code duplicated, block: B:260:0x0682  */
    /* JADX WARN: Code duplicated, block: B:261:0x068d  */
    /* JADX WARN: Code duplicated, block: B:263:0x069c  */
    /* JADX WARN: Code duplicated, block: B:265:0x06a1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:266:0x06a3  */
    /* JADX WARN: Code duplicated, block: B:267:0x06ac  */
    /* JADX WARN: Code duplicated, block: B:270:0x06b9  */
    /* JADX WARN: Code duplicated, block: B:271:0x06df  */
    /* JADX WARN: Code duplicated, block: B:272:0x06e4  */
    /* JADX WARN: Code duplicated, block: B:275:0x06f9  */
    /* JADX WARN: Code duplicated, block: B:276:0x06fb  */
    /* JADX WARN: Code duplicated, block: B:279:0x0710  */
    /* JADX WARN: Code duplicated, block: B:281:0x0714 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:282:0x0716 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:283:0x0718  */
    /* JADX WARN: Code duplicated, block: B:285:0x0720  */
    /* JADX WARN: Code duplicated, block: B:286:0x0725  */
    /* JADX WARN: Code duplicated, block: B:289:0x072d  */
    /* JADX WARN: Code duplicated, block: B:290:0x0748  */
    /* JADX WARN: Code duplicated, block: B:292:0x074f  */
    /* JADX WARN: Code duplicated, block: B:294:0x0755  */
    /* JADX WARN: Code duplicated, block: B:295:0x075a  */
    /* JADX WARN: Code duplicated, block: B:298:0x0762  */
    /* JADX WARN: Code duplicated, block: B:299:0x077a A[PHI: r5 r17 r34
      0x077a: PHI (r5v25 int) = (r5v22 int), (r5v27 int), (r5v27 int) binds: [B:309:0x079c, B:297:0x0760, B:298:0x0762] A[DONT_GENERATE, DONT_INLINE]
      0x077a: PHI (r17v19 int) = (r17v16 int), (r17v21 int), (r17v21 int) binds: [B:309:0x079c, B:297:0x0760, B:298:0x0762] A[DONT_GENERATE, DONT_INLINE]
      0x077a: PHI (r34v7 int) = (r34v4 int), (r34v9 int), (r34v9 int) binds: [B:309:0x079c, B:297:0x0760, B:298:0x0762] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:300:0x077e  */
    /* JADX WARN: Code duplicated, block: B:301:0x0783  */
    /* JADX WARN: Code duplicated, block: B:303:0x0789 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:304:0x078b  */
    /* JADX WARN: Code duplicated, block: B:306:0x0791  */
    /* JADX WARN: Code duplicated, block: B:307:0x0796  */
    /* JADX WARN: Code duplicated, block: B:310:0x079e  */
    /* JADX WARN: Code duplicated, block: B:311:0x07bb  */
    /* JADX WARN: Code duplicated, block: B:313:0x07bf  */
    /* JADX WARN: Code duplicated, block: B:314:0x07c4  */
    /* JADX WARN: Code duplicated, block: B:317:0x07cd  */
    /* JADX WARN: Code duplicated, block: B:320:0x07ec  */
    /* JADX WARN: Code duplicated, block: B:324:0x07fd  */
    /* JADX WARN: Code duplicated, block: B:327:0x0805  */
    /* JADX WARN: Code duplicated, block: B:329:0x0809  */
    /* JADX WARN: Code duplicated, block: B:331:0x0811  */
    /* JADX WARN: Code duplicated, block: B:333:0x081d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:334:0x081f  */
    /* JADX WARN: Code duplicated, block: B:335:0x0828  */
    /* JADX WARN: Code duplicated, block: B:338:0x0835  */
    /* JADX WARN: Code duplicated, block: B:339:0x084e  */
    /* JADX WARN: Code duplicated, block: B:341:0x0851  */
    /* JADX WARN: Code duplicated, block: B:343:0x0859  */
    /* JADX WARN: Code duplicated, block: B:345:0x0865 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:346:0x0867  */
    /* JADX WARN: Code duplicated, block: B:347:0x0870  */
    /* JADX WARN: Code duplicated, block: B:350:0x087d  */
    /* JADX WARN: Code duplicated, block: B:351:0x0896  */
    /* JADX WARN: Code duplicated, block: B:354:0x089b  */
    /* JADX WARN: Code duplicated, block: B:357:0x08a3  */
    /* JADX WARN: Code duplicated, block: B:359:0x08f4  */
    /* JADX WARN: Code duplicated, block: B:361:0x08fc A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:362:0x08fe  */
    /* JADX WARN: Code duplicated, block: B:363:0x090d  */
    /* JADX WARN: Code duplicated, block: B:364:0x091c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:365:0x091e  */
    /* JADX WARN: Code duplicated, block: B:366:0x092a  */
    /* JADX WARN: Code duplicated, block: B:370:0x093c  */
    /* JADX WARN: Code duplicated, block: B:372:0x0952 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:373:0x0954  */
    /* JADX WARN: Code duplicated, block: B:375:0x0965 A[LOOP:10: B:374:0x0963->B:375:0x0965, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:376:0x0976  */
    /* JADX WARN: Code duplicated, block: B:378:0x09a2  */
    /* JADX WARN: Code duplicated, block: B:380:0x09a6  */
    /* JADX WARN: Code duplicated, block: B:382:0x09bb A[LOOP:11: B:381:0x09b9->B:382:0x09bb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:383:0x09cc  */
    /* JADX WARN: Code duplicated, block: B:387:0x09fa  */
    /* JADX WARN: Code duplicated, block: B:390:0x0a88  */
    /* JADX WARN: Code duplicated, block: B:394:0x0a93  */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02bd, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x02ed, code lost:
    
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0323, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0151, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0289, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:289:0x072d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:298:0x0762, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:317:0x07cd, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:338:0x0835, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:350:0x087d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:387:0x09fa, please report this as an issue */
    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void layoutViews(androidx.recyclerview.widget.RecyclerView.Recycler r33, androidx.recyclerview.widget.RecyclerView.State r34, com.alibaba.android.vlayout.VirtualLayoutManager.LayoutStateWrapper r35, com.alibaba.android.vlayout.layout.LayoutChunkResult r36, com.alibaba.android.vlayout.LayoutManagerHelper r37) {
        /*
            Method dump skipped, instruction units count: 2768
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.vlayout.layout.RangeGridLayoutHelper.layoutViews(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, com.alibaba.android.vlayout.VirtualLayoutManager$LayoutStateWrapper, com.alibaba.android.vlayout.layout.LayoutChunkResult, com.alibaba.android.vlayout.LayoutManagerHelper):void");
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void afterLayout(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, int i3, LayoutManagerHelper layoutManagerHelper) {
        this.mRangeStyle.afterLayout(recycler, state, i, i2, i3, layoutManagerHelper);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public void adjustLayout(int i, int i2, LayoutManagerHelper layoutManagerHelper) {
        this.mRangeStyle.adjustLayout(i, i2, layoutManagerHelper);
    }

    @Override // com.alibaba.android.vlayout.layout.MarginLayoutHelper, com.alibaba.android.vlayout.LayoutHelper
    public int computeAlignOffset(int i, boolean z, boolean z2, LayoutManagerHelper layoutManagerHelper) {
        boolean z3 = layoutManagerHelper.getOrientation() == 1;
        if (z) {
            if (i == getItemCount() - 1) {
                return GridRangeStyle.computeEndAlignOffset(this.mRangeStyle, z3);
            }
        } else if (i == 0) {
            return GridRangeStyle.computeStartAlignOffset(this.mRangeStyle, z3);
        }
        return super.computeAlignOffset(i, z, z2, layoutManagerHelper);
    }

    @Override // com.alibaba.android.vlayout.layout.BaseLayoutHelper
    public void onClear(LayoutManagerHelper layoutManagerHelper) {
        super.onClear(layoutManagerHelper);
        this.mRangeStyle.onClear(layoutManagerHelper);
        this.mRangeStyle.onInvalidateSpanIndexCache();
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void onItemsChanged(LayoutManagerHelper layoutManagerHelper) {
        super.onItemsChanged(layoutManagerHelper);
        this.mRangeStyle.onInvalidateSpanIndexCache();
    }

    private int getMainDirSpec(GridRangeStyle gridRangeStyle, int i, int i2, int i3, float f) {
        if (!Float.isNaN(f) && f > 0.0f && i3 > 0) {
            return View.MeasureSpec.makeMeasureSpec((int) ((i3 / f) + 0.5f), 1073741824);
        }
        if (!Float.isNaN(gridRangeStyle.mAspectRatio) && gridRangeStyle.mAspectRatio > 0.0f) {
            return View.MeasureSpec.makeMeasureSpec((int) ((i2 / gridRangeStyle.mAspectRatio) + 0.5f), 1073741824);
        }
        if (i < 0) {
            return MAIN_DIR_SPEC;
        }
        return View.MeasureSpec.makeMeasureSpec(i, 1073741824);
    }

    @Override // com.alibaba.android.vlayout.LayoutHelper
    public void checkAnchorInfo(RecyclerView.State state, VirtualLayoutManager.AnchorInfoWrapper anchorInfoWrapper, LayoutManagerHelper layoutManagerHelper) {
        if (state.getItemCount() > 0) {
            GridRangeStyle gridRangeStyleFindRangeStyleByPosition = this.mRangeStyle.findRangeStyleByPosition(anchorInfoWrapper.position);
            int cachedSpanIndex = gridRangeStyleFindRangeStyleByPosition.mSpanSizeLookup.getCachedSpanIndex(anchorInfoWrapper.position, gridRangeStyleFindRangeStyleByPosition.mSpanCount);
            if (!anchorInfoWrapper.layoutFromEnd) {
                while (cachedSpanIndex > 0 && anchorInfoWrapper.position > 0) {
                    anchorInfoWrapper.position--;
                    cachedSpanIndex = gridRangeStyleFindRangeStyleByPosition.mSpanSizeLookup.getCachedSpanIndex(anchorInfoWrapper.position, gridRangeStyleFindRangeStyleByPosition.mSpanCount);
                }
            } else {
                while (cachedSpanIndex < gridRangeStyleFindRangeStyleByPosition.mSpanCount - 1 && anchorInfoWrapper.position < ((Integer) getRange().getUpper()).intValue()) {
                    anchorInfoWrapper.position++;
                    cachedSpanIndex = gridRangeStyleFindRangeStyleByPosition.mSpanSizeLookup.getCachedSpanIndex(anchorInfoWrapper.position, gridRangeStyleFindRangeStyleByPosition.mSpanCount);
                }
            }
            this.mLayoutWithAnchor = true;
        }
    }

    private int getSpanIndex(GridLayoutHelper.SpanSizeLookup spanSizeLookup, int i, RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.isPreLayout()) {
            return spanSizeLookup.getCachedSpanIndex(i2, i);
        }
        int iConvertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
        if (iConvertPreLayoutPositionToPostLayout == -1) {
            return 0;
        }
        return spanSizeLookup.getCachedSpanIndex(iConvertPreLayoutPositionToPostLayout, i);
    }

    private int getSpanSize(GridLayoutHelper.SpanSizeLookup spanSizeLookup, RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.isPreLayout()) {
            return spanSizeLookup.getSpanSize(i);
        }
        int iConvertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (iConvertPreLayoutPositionToPostLayout == -1) {
            return 0;
        }
        return spanSizeLookup.getSpanSize(iConvertPreLayoutPositionToPostLayout);
    }

    private void assignSpans(GridRangeStyle gridRangeStyle, RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, boolean z, LayoutManagerHelper layoutManagerHelper) {
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
            int spanSize = getSpanSize(gridRangeStyle.mSpanSizeLookup, recycler, state, layoutManagerHelper.getPosition(gridRangeStyle.mSet[i3]));
            if (i6 != -1 || spanSize <= 1) {
                gridRangeStyle.mSpanIndices[i3] = i7;
            } else {
                gridRangeStyle.mSpanIndices[i3] = i7 - (spanSize - 1);
            }
            i7 += spanSize * i6;
            i3 += i5;
        }
    }

    public int getBorderStartSpace(LayoutManagerHelper layoutManagerHelper) {
        int familyMarginLeft;
        int familyPaddingLeft;
        GridRangeStyle gridRangeStyleFindRangeStyleByPosition = this.mRangeStyle.findRangeStyleByPosition(((Integer) getRange().getLower()).intValue());
        if (layoutManagerHelper.getOrientation() == 1) {
            familyMarginLeft = gridRangeStyleFindRangeStyleByPosition.getFamilyMarginTop();
            familyPaddingLeft = gridRangeStyleFindRangeStyleByPosition.getFamilyPaddingTop();
        } else {
            familyMarginLeft = gridRangeStyleFindRangeStyleByPosition.getFamilyMarginLeft();
            familyPaddingLeft = gridRangeStyleFindRangeStyleByPosition.getFamilyPaddingLeft();
        }
        return familyMarginLeft + familyPaddingLeft;
    }

    public int getBorderEndSpace(LayoutManagerHelper layoutManagerHelper) {
        int familyMarginRight;
        int familyPaddingRight;
        GridRangeStyle gridRangeStyleFindRangeStyleByPosition = this.mRangeStyle.findRangeStyleByPosition(((Integer) getRange().getUpper()).intValue());
        if (layoutManagerHelper.getOrientation() == 1) {
            familyMarginRight = gridRangeStyleFindRangeStyleByPosition.getFamilyMarginBottom();
            familyPaddingRight = gridRangeStyleFindRangeStyleByPosition.getFamilyPaddingBottom();
        } else {
            familyMarginRight = gridRangeStyleFindRangeStyleByPosition.getFamilyMarginRight();
            familyPaddingRight = gridRangeStyleFindRangeStyleByPosition.getFamilyPaddingRight();
        }
        return familyMarginRight + familyPaddingRight;
    }

    public static class GridRangeStyle extends RangeStyle<GridRangeStyle> {
        private float mAspectRatio;
        private int mHGap;
        private boolean mIgnoreExtra;
        private boolean mIsAutoExpand;
        private View[] mSet;
        private int mSizePerSpan;
        private int[] mSpanCols;
        private int mSpanCount;
        private int[] mSpanIndices;
        private GridLayoutHelper.SpanSizeLookup mSpanSizeLookup;
        private int mVGap;
        private float[] mWeights;

        public GridRangeStyle(RangeGridLayoutHelper rangeGridLayoutHelper) {
            super(rangeGridLayoutHelper);
            this.mAspectRatio = Float.NaN;
            this.mSpanCount = 4;
            this.mSizePerSpan = 0;
            this.mIsAutoExpand = true;
            this.mIgnoreExtra = false;
            GridLayoutHelper.DefaultSpanSizeLookup defaultSpanSizeLookup = new GridLayoutHelper.DefaultSpanSizeLookup();
            this.mSpanSizeLookup = defaultSpanSizeLookup;
            this.mVGap = 0;
            this.mHGap = 0;
            this.mWeights = new float[0];
            defaultSpanSizeLookup.setSpanIndexCacheEnabled(true);
        }

        public GridRangeStyle() {
            this.mAspectRatio = Float.NaN;
            this.mSpanCount = 4;
            this.mSizePerSpan = 0;
            this.mIsAutoExpand = true;
            this.mIgnoreExtra = false;
            GridLayoutHelper.DefaultSpanSizeLookup defaultSpanSizeLookup = new GridLayoutHelper.DefaultSpanSizeLookup();
            this.mSpanSizeLookup = defaultSpanSizeLookup;
            this.mVGap = 0;
            this.mHGap = 0;
            this.mWeights = new float[0];
            defaultSpanSizeLookup.setSpanIndexCacheEnabled(true);
        }

        public GridRangeStyle findRangeStyleByPosition(int i) {
            return findRangeStyle(this, i);
        }

        private GridRangeStyle findRangeStyle(GridRangeStyle gridRangeStyle, int i) {
            for (Map.Entry entry : gridRangeStyle.mChildren.entrySet()) {
                GridRangeStyle gridRangeStyle2 = (GridRangeStyle) entry.getValue();
                Range range = (Range) entry.getKey();
                if (!gridRangeStyle2.isChildrenEmpty()) {
                    return findRangeStyle(gridRangeStyle2, i);
                }
                if (range.contains(Integer.valueOf(i))) {
                    return gridRangeStyle2;
                }
            }
            return gridRangeStyle;
        }

        public GridRangeStyle findSiblingStyleByPosition(int i) {
            if (this.mParent != 0) {
                for (Map.Entry entry : ((GridRangeStyle) this.mParent).mChildren.entrySet()) {
                    if (((Range) entry.getKey()).contains(Integer.valueOf(i))) {
                        GridRangeStyle gridRangeStyle = (GridRangeStyle) entry.getValue();
                        if (!gridRangeStyle.equals(this)) {
                            return gridRangeStyle;
                        }
                    }
                }
            }
            return null;
        }

        public void onInvalidateSpanIndexCache() {
            this.mSpanSizeLookup.invalidateSpanIndexCache();
            Iterator it = this.mChildren.entrySet().iterator();
            while (it.hasNext()) {
                ((GridRangeStyle) ((Map.Entry) it.next()).getValue()).onInvalidateSpanIndexCache();
            }
        }

        public static int computeEndAlignOffset(GridRangeStyle gridRangeStyle, boolean z) {
            int i;
            int i2;
            int i3;
            int i4;
            if (z) {
                i = gridRangeStyle.mMarginBottom;
                i2 = gridRangeStyle.mPaddingBottom;
            } else {
                i = gridRangeStyle.mMarginRight;
                i2 = gridRangeStyle.mPaddingRight;
            }
            int iComputeEndAlignOffset = i + i2;
            int iIntValue = ((Integer) gridRangeStyle.getRange().getUpper()).intValue();
            Iterator it = gridRangeStyle.mChildren.entrySet().iterator();
            while (it.hasNext()) {
                GridRangeStyle gridRangeStyle2 = (GridRangeStyle) ((Map.Entry) it.next()).getValue();
                if (!gridRangeStyle2.isChildrenEmpty()) {
                    iComputeEndAlignOffset += computeEndAlignOffset(gridRangeStyle2, z);
                } else if (((Integer) gridRangeStyle2.mRange.getUpper()).intValue() == iIntValue) {
                    if (z) {
                        i3 = gridRangeStyle2.mMarginBottom;
                        i4 = gridRangeStyle2.mPaddingBottom;
                    } else {
                        i3 = gridRangeStyle2.mMarginRight;
                        i4 = gridRangeStyle2.mPaddingRight;
                    }
                    return iComputeEndAlignOffset + i3 + i4;
                }
            }
            return iComputeEndAlignOffset;
        }

        public static int computeStartAlignOffset(GridRangeStyle gridRangeStyle, boolean z) {
            int i;
            int i2;
            int i3;
            int i4;
            if (z) {
                i = -gridRangeStyle.mMarginTop;
                i2 = gridRangeStyle.mPaddingTop;
            } else {
                i = -gridRangeStyle.mMarginLeft;
                i2 = gridRangeStyle.mPaddingLeft;
            }
            int iComputeStartAlignOffset = i - i2;
            int iIntValue = ((Integer) gridRangeStyle.getRange().getLower()).intValue();
            Iterator it = gridRangeStyle.mChildren.entrySet().iterator();
            while (it.hasNext()) {
                GridRangeStyle gridRangeStyle2 = (GridRangeStyle) ((Map.Entry) it.next()).getValue();
                if (!gridRangeStyle2.isChildrenEmpty()) {
                    iComputeStartAlignOffset += computeStartAlignOffset(gridRangeStyle2, z);
                } else if (((Integer) gridRangeStyle2.mRange.getLower()).intValue() == iIntValue) {
                    if (z) {
                        i3 = -gridRangeStyle2.mMarginTop;
                        i4 = gridRangeStyle2.mPaddingTop;
                    } else {
                        i3 = -gridRangeStyle2.mMarginLeft;
                        i4 = gridRangeStyle2.mPaddingLeft;
                    }
                    return iComputeStartAlignOffset + (i3 - i4);
                }
            }
            return iComputeStartAlignOffset;
        }

        public void setAspectRatio(float f) {
            this.mAspectRatio = f;
        }

        public float getAspectRatio() {
            return this.mAspectRatio;
        }

        @Override // com.alibaba.android.vlayout.layout.RangeStyle
        public void setRange(int i, int i2) {
            super.setRange(i, i2);
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

        public void setWeights(float[] fArr) {
            if (fArr != null) {
                this.mWeights = Arrays.copyOf(fArr, fArr.length);
            } else {
                this.mWeights = new float[0];
            }
        }

        public void setSpanSizeLookup(GridLayoutHelper.SpanSizeLookup spanSizeLookup) {
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

        public int getSpanCount() {
            return this.mSpanCount;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void ensureSpanCount() {
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
    }
}
