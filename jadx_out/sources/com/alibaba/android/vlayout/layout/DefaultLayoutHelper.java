package com.alibaba.android.vlayout.layout;

import com.alibaba.android.vlayout.LayoutHelper;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultLayoutHelper extends LinearLayoutHelper {
    @Override // com.alibaba.android.vlayout.LayoutHelper
    public boolean isOutOfRange(int i) {
        return false;
    }

    public static LayoutHelper newHelper(int i) {
        DefaultLayoutHelper defaultLayoutHelper = new DefaultLayoutHelper();
        defaultLayoutHelper.setItemCount(i);
        return defaultLayoutHelper;
    }
}
