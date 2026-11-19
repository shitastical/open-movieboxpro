package com.movieboxpro.android.view.dialog;

import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.exoplayer.offline.DownloadService;
import com.alibaba.fastjson.asm.Opcodes;
import com.jeffmony.downloader.VideoDownloadManager;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.db.entity.HlsDownload;
import com.movieboxpro.android.service.MyDownloadService;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.download.DownloadUtil;
import com.movieboxpro.android.utils.tool.ActivityUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: ConfirmSwitchDownloadPathDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.view.dialog.ConfirmSwitchDownloadPathDialog$initListener$1$1", f = "ConfirmSwitchDownloadPathDialog.kt", i = {}, l = {84, Opcodes.POP, IjkMediaMeta.FF_PROFILE_H264_EXTENDED}, m = "invokeSuspend", n = {}, s = {})
final class ConfirmSwitchDownloadPathDialog$initListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ConfirmSwitchDownloadPathDialog this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ConfirmSwitchDownloadPathDialog$initListener$1$1(ConfirmSwitchDownloadPathDialog confirmSwitchDownloadPathDialog, Continuation<? super ConfirmSwitchDownloadPathDialog$initListener$1$1> continuation) {
        super(2, continuation);
        this.this$0 = confirmSwitchDownloadPathDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ConfirmSwitchDownloadPathDialog$initListener$1$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ConfirmSwitchDownloadPathDialog$initListener$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:50:0x017e A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                List<DownloadFile> hlsDownloadFiles = DownloadFileHelper.INSTANCE.getInstance().getHlsDownloadFiles();
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : hlsDownloadFiles) {
                    if (((DownloadFile) obj2).getStatus() != 1) {
                        arrayList.add(obj2);
                    }
                }
                ArrayList<DownloadFile> arrayList2 = arrayList;
                for (DownloadFile downloadFile : arrayList2) {
                    DownloadService.sendRemoveDownload(App.getContext(), MyDownloadService.class, downloadFile.getDownloadId(), false);
                    DownloadUtil.pauseDownload(downloadFile.getDownloadId());
                }
                DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFiles(arrayList2);
                ArrayList<HlsDownload> arrayList3 = new ArrayList();
                for (DownloadFile downloadFile2 : arrayList2) {
                    HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
                    String id = downloadFile2.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    HlsDownload hlsDownloadFindByDownloadId = hlsDownloadDao.findByDownloadId(id);
                    if (hlsDownloadFindByDownloadId != null) {
                        arrayList3.add(hlsDownloadFindByDownloadId);
                    }
                }
                for (HlsDownload hlsDownload : arrayList3) {
                    VideoDownloadManager.getInstance().deleteVideoTasks(CollectionsKt.listOf((Object[]) new String[]{hlsDownload.getVideoUrl(), hlsDownload.getAudioUrl(), hlsDownload.getAudioEacUrl()}), true);
                    new File(hlsDownload.getM3u8LocalPath()).delete();
                    new File(hlsDownload.getPlaybackM3u8LocalPath()).delete();
                    App.getDB().hlsDownloadDao().delete(hlsDownload);
                }
                DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFiles(arrayList2);
            } catch (Exception unused) {
            }
            this.label = 1;
            if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else if (i == 2) {
                ResultKt.throwOnFailure(obj);
                this.label = 3;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(this.this$0, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            ActivityUtils.finishAllActivities();
            System.exit(0);
            return Unit.INSTANCE;
        }
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            Boxing.boxBoolean(activity.stopService(new Intent(this.this$0.getContext(), (Class<?>) MyDownloadService.class)));
        }
        DialogAction.ActionListener actionListener = this.this$0.listener;
        if (actionListener != null) {
            actionListener.onClick();
        }
        this.label = 2;
        if (DelayKt.delay(1000L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.label = 3;
        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(this.this$0, null), this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        ActivityUtils.finishAllActivities();
        System.exit(0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.dialog.ConfirmSwitchDownloadPathDialog$initListener$1$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: ConfirmSwitchDownloadPathDialog.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.dialog.ConfirmSwitchDownloadPathDialog$initListener$1$1$3", f = "ConfirmSwitchDownloadPathDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ ConfirmSwitchDownloadPathDialog this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(ConfirmSwitchDownloadPathDialog confirmSwitchDownloadPathDialog, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = confirmSwitchDownloadPathDialog;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.hideLoadingView();
            return Unit.INSTANCE;
        }
    }
}
