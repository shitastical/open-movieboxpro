package com.movieboxpro.android.service;

import com.jeffmony.downloader.model.VideoTaskItem;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.utils.DownloadFileHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.service.DownloadService$onCreate$2$onDownloadPause$1", f = "DownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class DownloadService$onCreate$2$onDownloadPause$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoTaskItem $item;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadService$onCreate$2$onDownloadPause$1(VideoTaskItem videoTaskItem, Continuation<? super DownloadService$onCreate$2$onDownloadPause$1> continuation) {
        super(2, continuation);
        this.$item = videoTaskItem;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadService$onCreate$2$onDownloadPause$1(this.$item, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DownloadService$onCreate$2$onDownloadPause$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            DownloadFile downloadFileFindDownloadFileByDownloadId = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByDownloadId(this.$item.getGroupName());
            if (downloadFileFindDownloadFileByDownloadId != null && downloadFileFindDownloadFileByDownloadId.getStatus() != 2) {
                downloadFileFindDownloadFileByDownloadId.setStatus(2);
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(downloadFileFindDownloadFileByDownloadId);
            }
        } catch (Exception unused) {
        }
        return Unit.INSTANCE;
    }
}
