package com.movieboxpro.android.service;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.NotificationUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.offline.Download;
import androidx.media3.exoplayer.offline.DownloadManager;
import androidx.media3.exoplayer.offline.DownloadNotificationHelper;
import androidx.media3.exoplayer.scheduler.PlatformScheduler;
import androidx.media3.exoplayer.scheduler.Requirements;
import androidx.media3.exoplayer.scheduler.Scheduler;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.download.DownloadTracker;
import com.movieboxpro.android.utils.download.DownloadUtil;
import com.movieboxpro.androidtv.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: MyDownloadService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0014J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0015J\u001e\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0012H\u0002J\u0016\u0010\u0015\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0017H\u0002J(\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0014H\u0002J\b\u0010\u001e\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/service/MyDownloadService;", "Landroidx/media3/exoplayer/offline/DownloadService;", "<init>", "()V", "TAG", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "onCreate", "", "getDownloadManager", "Landroidx/media3/exoplayer/offline/DownloadManager;", "getScheduler", "Landroidx/media3/exoplayer/scheduler/Scheduler;", "getForegroundNotification", "Landroid/app/Notification;", "downloads", "", "Landroidx/media3/exoplayer/offline/Download;", "notMetRequirements", "", "setDownloadStatus", "item", "", "sendDownloadBroadcast", "action", "download", "Lcom/movieboxpro/android/db/entity/Download;", "speedTest", "progress", "onDestroy", "TerminalStateNotificationHelper", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MyDownloadService extends androidx.media3.exoplayer.offline.DownloadService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int FOREGROUND_NOTIFICATION_ID = 1;
    private static final int JOB_ID = 1;
    private static volatile boolean isRunning;
    private final String TAG;
    private final CoroutineScope coroutineScope;

    public MyDownloadService() {
        super(1, 1000L, DownloadUtil.DOWNLOAD_NOTIFICATION_CHANNEL_ID, R.string.download_hls_channel_name, 0);
        this.TAG = "MyDownloadService";
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
    }

    @Override // androidx.media3.exoplayer.offline.DownloadService, android.app.Service
    public void onCreate() {
        super.onCreate();
        isRunning = true;
        DownloadUtil.getDownloadTracker(App.getContext(), PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH())).addListener(new DownloadTracker.Listener() { // from class: com.movieboxpro.android.service.MyDownloadService$$ExternalSyntheticLambda0
            @Override // com.movieboxpro.android.utils.download.DownloadTracker.Listener
            public final void onDownloadsChanged(Download download) {
                MyDownloadService.onCreate$lambda$0(this.f$0, download);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(MyDownloadService myDownloadService, Download download) {
        Intrinsics.checkNotNull(download);
        myDownloadService.setDownloadStatus(download);
    }

    @Override // androidx.media3.exoplayer.offline.DownloadService
    protected DownloadManager getDownloadManager() {
        MyDownloadService myDownloadService = this;
        DownloadManager downloadManager = DownloadUtil.getDownloadManager(myDownloadService, PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH()));
        DownloadNotificationHelper downloadNotificationHelper = DownloadUtil.getDownloadNotificationHelper(myDownloadService);
        Intrinsics.checkNotNull(downloadNotificationHelper);
        downloadManager.addListener(new TerminalStateNotificationHelper(myDownloadService, downloadNotificationHelper, 2));
        Intrinsics.checkNotNull(downloadManager);
        return downloadManager;
    }

    @Override // androidx.media3.exoplayer.offline.DownloadService
    protected Scheduler getScheduler() {
        return new PlatformScheduler(this, 1);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.service.MyDownloadService$getForegroundNotification$1, reason: invalid class name */
    /* JADX INFO: compiled from: MyDownloadService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.service.MyDownloadService$getForegroundNotification$1", f = "MyDownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Download> $downloads;
        int label;
        final /* synthetic */ MyDownloadService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<Download> list, MyDownloadService myDownloadService, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$downloads = list;
            this.this$0 = myDownloadService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$downloads, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<Download> list = this.$downloads;
            MyDownloadService myDownloadService = this.this$0;
            for (Download download : list) {
                if (download.state == 2) {
                    myDownloadService.setDownloadStatus(download);
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.media3.exoplayer.offline.DownloadService
    protected Notification getForegroundNotification(List<Download> downloads, int notMetRequirements) {
        Intrinsics.checkNotNullParameter(downloads, "downloads");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, Dispatchers.getDefault(), null, new AnonymousClass1(downloads, this, null), 2, null);
        MyDownloadService myDownloadService = this;
        Notification notificationBuildProgressNotification = DownloadUtil.getDownloadNotificationHelper(myDownloadService).buildProgressNotification(myDownloadService, R.mipmap.ic_download, null, null, downloads, notMetRequirements);
        Intrinsics.checkNotNullExpressionValue(notificationBuildProgressNotification, "buildProgressNotification(...)");
        return notificationBuildProgressNotification;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDownloadStatus(Download item) {
        String str;
        String id = item.request.id;
        Intrinsics.checkNotNullExpressionValue(id, "id");
        int i = item.state;
        if (i == 0) {
            DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(8, id);
            return;
        }
        if (i == 1) {
            DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(2, id);
            return;
        }
        if (i != 2) {
            if (i == 3) {
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(1, id);
                return;
            } else if (i == 4) {
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(7, id);
                return;
            } else {
                if (i != 5) {
                    return;
                }
                DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(id);
                return;
            }
        }
        if (item.getPercentDownloaded() == 0.0f) {
            return;
        }
        DownloadFile downloadFileFindDownloadFileByDownloadId = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByDownloadId(id);
        DownloadFileHelper companion = DownloadFileHelper.INSTANCE.getInstance();
        long bytesDownloaded = item.getBytesDownloaded();
        long bytesDownloaded2 = ((int) item.getPercentDownloaded()) != -1 ? (long) ((item.getBytesDownloaded() / item.getPercentDownloaded()) * 100) : 0L;
        if (item.getBytesDownloaded() == 0 || downloadFileFindDownloadFileByDownloadId == null) {
            str = "";
        } else {
            str = FileUtils.byte2FitMemorySize(item.getBytesDownloaded() - downloadFileFindDownloadFileByDownloadId.getDownloadSize()) + "/s";
        }
        companion.updateDownloadProgress(id, bytesDownloaded, bytesDownloaded2, str);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.service.MyDownloadService$setDownloadStatus$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MyDownloadService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.service.MyDownloadService$setDownloadStatus$1", f = "MyDownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Download> $downloads;
        int label;
        final /* synthetic */ MyDownloadService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01231(List<Download> list, MyDownloadService myDownloadService, Continuation<? super C01231> continuation) {
            super(2, continuation);
            this.$downloads = list;
            this.this$0 = myDownloadService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01231(this.$downloads, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<Download> list = this.$downloads;
            MyDownloadService myDownloadService = this.this$0;
            for (Download download : list) {
                Log.d(myDownloadService.TAG, String.valueOf(download.state));
                String id = download.request.id;
                Intrinsics.checkNotNullExpressionValue(id, "id");
                int i = download.state;
                if (i == 0) {
                    DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(8, id);
                } else if (i == 1) {
                    DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(2, id);
                } else if (i == 2) {
                    DownloadFile downloadFileFindDownloadFileByDownloadId = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByDownloadId(id);
                    DownloadFileHelper.INSTANCE.getInstance().updateDownloadProgress(id, download.getBytesDownloaded(), ((int) download.getPercentDownloaded()) != -1 ? (long) ((download.getBytesDownloaded() / download.getPercentDownloaded()) * 100) : 0L, (download.getBytesDownloaded() == 0 || downloadFileFindDownloadFileByDownloadId == null) ? "" : FileUtils.byte2FitMemorySize(download.getBytesDownloaded() - downloadFileFindDownloadFileByDownloadId.getDownloadSize()) + "/s");
                } else if (i == 3) {
                    DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(1, id);
                } else if (i == 4) {
                    DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(7, id);
                } else if (i == 5) {
                    DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(id);
                }
            }
            return Unit.INSTANCE;
        }
    }

    private final void setDownloadStatus(List<Download> downloads) {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, Dispatchers.getDefault(), null, new C01231(downloads, this, null), 2, null);
    }

    private final void sendDownloadBroadcast(String action, com.movieboxpro.android.db.entity.Download download, String speedTest, int progress) {
        Intent intent = new Intent(action);
        intent.putExtra("speedText", speedTest);
        intent.putExtra("id", download.getId());
        intent.putExtra(TvContractCompat.ProgramColumns.COLUMN_TITLE, download.getTitle());
        intent.putExtra(DealWatchNextService.SEASON, download.getSeason());
        intent.putExtra(DealWatchNextService.EPISODE, download.getEpisode());
        intent.putExtra("boxType", download.getBox_type());
        intent.putExtra("progress", progress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX INFO: compiled from: MyDownloadService.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ(\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/service/MyDownloadService$TerminalStateNotificationHelper;", "Landroidx/media3/exoplayer/offline/DownloadManager$Listener;", "context", "Landroid/content/Context;", "notificationHelper", "Landroidx/media3/exoplayer/offline/DownloadNotificationHelper;", "nextNotificationId", "", "<init>", "(Landroid/content/Context;Landroidx/media3/exoplayer/offline/DownloadNotificationHelper;I)V", "onDownloadChanged", "", "downloadManager", "Landroidx/media3/exoplayer/offline/DownloadManager;", "download", "Landroidx/media3/exoplayer/offline/Download;", "finalException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class TerminalStateNotificationHelper implements DownloadManager.Listener {
        private final Context context;
        private int nextNotificationId;
        private final DownloadNotificationHelper notificationHelper;

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onDownloadRemoved(DownloadManager downloadManager, Download download) {
            DownloadManager.Listener.CC.$default$onDownloadRemoved(this, downloadManager, download);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onDownloadsPausedChanged(DownloadManager downloadManager, boolean z) {
            DownloadManager.Listener.CC.$default$onDownloadsPausedChanged(this, downloadManager, z);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onIdle(DownloadManager downloadManager) {
            DownloadManager.Listener.CC.$default$onIdle(this, downloadManager);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onInitialized(DownloadManager downloadManager) {
            DownloadManager.Listener.CC.$default$onInitialized(this, downloadManager);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onRequirementsStateChanged(DownloadManager downloadManager, Requirements requirements, int i) {
            DownloadManager.Listener.CC.$default$onRequirementsStateChanged(this, downloadManager, requirements, i);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onWaitingForRequirementsChanged(DownloadManager downloadManager, boolean z) {
            DownloadManager.Listener.CC.$default$onWaitingForRequirementsChanged(this, downloadManager, z);
        }

        public TerminalStateNotificationHelper(Context context, DownloadNotificationHelper notificationHelper, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notificationHelper, "notificationHelper");
            this.notificationHelper = notificationHelper;
            this.nextNotificationId = i;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            this.context = applicationContext;
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public void onDownloadChanged(DownloadManager downloadManager, Download download, Exception finalException) {
            Notification notificationBuildDownloadFailedNotification;
            Intrinsics.checkNotNullParameter(downloadManager, "downloadManager");
            Intrinsics.checkNotNullParameter(download, "download");
            if (download.state == 3) {
                notificationBuildDownloadFailedNotification = this.notificationHelper.buildDownloadCompletedNotification(this.context, R.mipmap.ic_white_duihao, null, Util.fromUtf8Bytes(download.request.data));
            } else if (download.state != 4) {
                return;
            } else {
                notificationBuildDownloadFailedNotification = this.notificationHelper.buildDownloadFailedNotification(this.context, R.mipmap.ic_white_duihao, null, Util.fromUtf8Bytes(download.request.data));
            }
            Context context = this.context;
            int i = this.nextNotificationId;
            this.nextNotificationId = i + 1;
            NotificationUtil.setNotification(context, i, notificationBuildDownloadFailedNotification);
        }
    }

    @Override // androidx.media3.exoplayer.offline.DownloadService, android.app.Service
    public void onDestroy() {
        isRunning = false;
        super.onDestroy();
    }

    /* JADX INFO: compiled from: MyDownloadService.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/service/MyDownloadService$Companion;", "", "<init>", "()V", "JOB_ID", "", "FOREGROUND_NOTIFICATION_ID", "value", "", "isRunning", "()Z", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isRunning() {
            return MyDownloadService.isRunning;
        }
    }
}
