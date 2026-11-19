package com.alibaba.android.vlayout.layout;

import com.alibaba.android.vlayout.LayoutManagerHelper;

/* JADX INFO: loaded from: classes4.dex */
public class ScrollFixLayoutHelper extends FixLayoutHelper {
    public static final int SHOW_ALWAYS = 0;
    public static final int SHOW_ON_ENTER = 1;
    public static final int SHOW_ON_LEAVE = 2;
    private static final String TAG = "ScrollFixLayoutHelper";
    private int mShowType;

    public ScrollFixLayoutHelper(int i, int i2) {
        this(0, i, i2);
    }

    public ScrollFixLayoutHelper(int i, int i2, int i3) {
        super(i, i2, i3);
        this.mShowType = 0;
    }

    public void setShowType(int i) {
        this.mShowType = i;
    }

    public int getShowType() {
        return this.mShowType;
    }

    @Override // com.alibaba.android.vlayout.layout.FixLayoutHelper
    protected boolean shouldBeDraw(LayoutManagerHelper layoutManagerHelper, int i, int i2, int i3) {
        int i4 = this.mShowType;
        if (i4 != 1) {
            return i4 != 2 || i >= ((Integer) getRange().getLower()).intValue() + 1;
        }
        return i2 >= ((Integer) getRange().getLower()).intValue() - 1;
    }
}
