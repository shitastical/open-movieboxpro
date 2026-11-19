package com.movieboxpro.android.service;

import com.jeffmony.downloader.VideoDownloadManager;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.db.entity.HlsDownload;
import com.movieboxpro.android.utils.download.DownloadUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
@DebugMetadata(c = "com.movieboxpro.android.service.DownloadService$DownloadBinder$deleteDownload$1", f = "DownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class DownloadService$DownloadBinder$deleteDownload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DownloadFile $downloadFile;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadService$DownloadBinder$deleteDownload$1(DownloadFile downloadFile, Continuation<? super DownloadService$DownloadBinder$deleteDownload$1> continuation) {
        super(2, continuation);
        this.$downloadFile = downloadFile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadService$DownloadBinder$deleteDownload$1(this.$downloadFile, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DownloadService$DownloadBinder$deleteDownload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
        String downloadId = this.$downloadFile.getDownloadId();
        Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
        HlsDownload hlsDownloadFindByDownloadId = hlsDownloadDao.findByDownloadId(downloadId);
        if (hlsDownloadFindByDownloadId != null) {
            VideoDownloadManager.getInstance().deleteVideoTasks(CollectionsKt.listOf((Object[]) new String[]{hlsDownloadFindByDownloadId.getVideoUrl(), hlsDownloadFindByDownloadId.getAudioUrl(), hlsDownloadFindByDownloadId.getAudioEacUrl()}), true);
            new File(hlsDownloadFindByDownloadId.getM3u8LocalPath()).delete();
            new File(hlsDownloadFindByDownloadId.getPlaybackM3u8LocalPath()).delete();
            App.getDB().hlsDownloadDao().delete(hlsDownloadFindByDownloadId);
        } else {
            androidx.media3.exoplayer.offline.DownloadService.sendRemoveDownload(App.getContext(), MyDownloadService.class, this.$downloadFile.getDownloadId(), false);
            DownloadUtil.pauseDownload(this.$downloadFile.getDownloadId());
        }
        return Unit.INSTANCE;
    }
}
