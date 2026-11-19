package com.movieboxpro.android.utils;

import com.alibaba.fastjson.asm.Opcodes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: RxSubscribers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
public final class RxSubscribersKt$subscribeToList$$inlined$subscribeKt$1<T> implements Function1<List<? extends T>, Unit> {
    final /* synthetic */ Function1 $onSuccess;

    public RxSubscribersKt$subscribeToList$$inlined$subscribeKt$1(Function1 function1) {
        this.$onSuccess = function1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
        invoke2(obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(List<? extends T> list) {
        Function1 function1 = this.$onSuccess;
        Intrinsics.checkNotNull(list);
        function1.invoke(list);
    }
}
