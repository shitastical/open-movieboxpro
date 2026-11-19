package com.chad.library.adapter.base;

import android.widget.LinearLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* JADX INFO: compiled from: BaseQuickAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
final /* synthetic */ class BaseQuickAdapter$footerLayout$1 extends MutablePropertyReference0Impl {
    BaseQuickAdapter$footerLayout$1(BaseQuickAdapter baseQuickAdapter) {
        super(baseQuickAdapter, BaseQuickAdapter.class, "mFooterLayout", "getMFooterLayout()Landroid/widget/LinearLayout;", 0);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return BaseQuickAdapter.access$getMFooterLayout$p((BaseQuickAdapter) this.receiver);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((BaseQuickAdapter) this.receiver).mFooterLayout = (LinearLayout) obj;
    }
}
