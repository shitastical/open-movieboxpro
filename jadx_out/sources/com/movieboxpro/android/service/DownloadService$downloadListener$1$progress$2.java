package com.movieboxpro.android.service;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.movieboxpro.android.utils.DownloadFileHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.service.DownloadService$downloadListener$1$progress$2", f = "DownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class DownloadService$downloadListener$1$progress$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $currentOffset;
    final /* synthetic */ DownloadTask $task;
    final /* synthetic */ SpeedCalculator $taskSpeed;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadService$downloadListener$1$progress$2(DownloadTask downloadTask, long j, SpeedCalculator speedCalculator, Continuation<? super DownloadService$downloadListener$1$progress$2> continuation) {
        super(2, continuation);
        this.$task = downloadTask;
        this.$currentOffset = j;
        this.$taskSpeed = speedCalculator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadService$downloadListener$1$progress$2(this.$task, this.$currentOffset, this.$taskSpeed, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DownloadService$downloadListener$1$progress$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Object tag = this.$task.getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.String");
        String str = (String) tag;
        DownloadFileHelper companion = DownloadFileHelper.INSTANCE.getInstance();
        long j = this.$currentOffset;
        Object tag2 = this.$task.getTag(2);
        Long l = tag2 instanceof Long ? (Long) tag2 : null;
        long jLongValue = l != null ? l.longValue() : 0L;
        String strSpeed = this.$taskSpeed.speed();
        Intrinsics.checkNotNullExpressionValue(strSpeed, "speed(...)");
        companion.updateDownloadProgress(str, j, jLongValue, strSpeed);
        return Unit.INSTANCE;
    }
}
