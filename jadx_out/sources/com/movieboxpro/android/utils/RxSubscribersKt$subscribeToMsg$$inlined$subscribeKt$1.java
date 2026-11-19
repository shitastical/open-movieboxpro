package com.movieboxpro.android.utils;

import com.alibaba.fastjson.asm.Opcodes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RxSubscribers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
public final class RxSubscribersKt$subscribeToMsg$$inlined$subscribeKt$1 implements Function1<String, Unit> {
    final /* synthetic */ Function1 $onSuccess;

    public RxSubscribersKt$subscribeToMsg$$inlined$subscribeKt$1(Function1 function1) {
        this.$onSuccess = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        m1498invoke(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m1498invoke(String str) {
        Function1 function1 = this.$onSuccess;
        Intrinsics.checkNotNull(str);
        function1.invoke(str);
    }
}
