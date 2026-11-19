package com.movieboxpro.android.service;

import com.jeffmony.downloader.model.VideoTaskItem;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.db.entity.HlsDownload;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
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
@DebugMetadata(c = "com.movieboxpro.android.service.DownloadService$onCreate$2$onDownloadProgress$1", f = "DownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class DownloadService$onCreate$2$onDownloadProgress$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoTaskItem $item;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadService$onCreate$2$onDownloadProgress$1(VideoTaskItem videoTaskItem, Continuation<? super DownloadService$onCreate$2$onDownloadProgress$1> continuation) {
        super(2, continuation);
        this.$item = videoTaskItem;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadService$onCreate$2$onDownloadProgress$1(this.$item, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DownloadService$onCreate$2$onDownloadProgress$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DownloadFile downloadFileFindDownloadFileByDownloadId = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByDownloadId(this.$item.getGroupName());
        if (downloadFileFindDownloadFileByDownloadId != null) {
            HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
            String downloadId = downloadFileFindDownloadFileByDownloadId.getDownloadId();
            Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
            HlsDownload hlsDownloadFindByDownloadId = hlsDownloadDao.findByDownloadId(downloadId);
            if (hlsDownloadFindByDownloadId != null) {
                int i = (hlsDownloadFindByDownloadId.getVideoUrl().length() <= 0 || hlsDownloadFindByDownloadId.getVideoPercent() == 100.0f) ? 0 : 1;
                if (hlsDownloadFindByDownloadId.getAudioUrl().length() > 0 && hlsDownloadFindByDownloadId.getAudioPercent() != 100.0f) {
                    i++;
                }
                if (hlsDownloadFindByDownloadId.getAudioEacUrl().length() > 0 && hlsDownloadFindByDownloadId.getAudioEacSpeed() != 100.0f) {
                    i++;
                }
                if (Intrinsics.areEqual(this.$item.getUrl(), hlsDownloadFindByDownloadId.getVideoUrl())) {
                    hlsDownloadFindByDownloadId.setVideoSize(this.$item.getDownloadSize());
                    hlsDownloadFindByDownloadId.setVideoPercent(this.$item.getPercent());
                    hlsDownloadFindByDownloadId.setVideoSpeed(this.$item.getSpeed());
                } else if (Intrinsics.areEqual(this.$item.getUrl(), hlsDownloadFindByDownloadId.getAudioUrl())) {
                    hlsDownloadFindByDownloadId.setAudioSize(this.$item.getDownloadSize());
                    hlsDownloadFindByDownloadId.setAudioPercent(this.$item.getPercent());
                    hlsDownloadFindByDownloadId.setAudioSpeed(this.$item.getSpeed());
                } else if (Intrinsics.areEqual(this.$item.getUrl(), hlsDownloadFindByDownloadId.getAudioEacUrl())) {
                    hlsDownloadFindByDownloadId.setAudioEacSize(this.$item.getDownloadSize());
                    hlsDownloadFindByDownloadId.setAudioEacPercent(this.$item.getPercent());
                    hlsDownloadFindByDownloadId.setAudioEacSpeed(this.$item.getSpeed());
                }
                long videoSpeed = (long) (((hlsDownloadFindByDownloadId.getVideoSpeed() + hlsDownloadFindByDownloadId.getAudioSpeed()) + hlsDownloadFindByDownloadId.getAudioEacSpeed()) / i);
                App.getDB().hlsDownloadDao().insertOrReplace(hlsDownloadFindByDownloadId);
                downloadFileFindDownloadFileByDownloadId.setStatus(6);
                downloadFileFindDownloadFileByDownloadId.setDownloadSize(hlsDownloadFindByDownloadId.getVideoSize() + hlsDownloadFindByDownloadId.getAudioSize() + hlsDownloadFindByDownloadId.getAudioEacSize());
                downloadFileFindDownloadFileByDownloadId.setSize(0L);
                downloadFileFindDownloadFileByDownloadId.setPercent((((hlsDownloadFindByDownloadId.getVideoPercent() + hlsDownloadFindByDownloadId.getAudioPercent()) + hlsDownloadFindByDownloadId.getAudioEacPercent()) / 3) / 100.0f);
                downloadFileFindDownloadFileByDownloadId.setSpeed(FileUtils.byte2FitMemorySize(videoSpeed) + "/s");
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(downloadFileFindDownloadFileByDownloadId);
            }
        }
        return Unit.INSTANCE;
    }
}
