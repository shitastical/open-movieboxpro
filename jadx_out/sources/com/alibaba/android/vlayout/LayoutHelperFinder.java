package com.alibaba.android.vlayout;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LayoutHelperFinder {
    public abstract LayoutHelper getLayoutHelper(int i);

    protected abstract List<LayoutHelper> getLayoutHelpers();

    protected abstract List<LayoutHelper> reverse();

    abstract void setLayouts(List<LayoutHelper> list);
}
