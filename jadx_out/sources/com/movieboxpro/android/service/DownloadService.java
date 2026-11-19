package com.movieboxpro.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.documentfile.provider.DocumentFile;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeffmony.downloader.VideoDownloadManager;
import com.jeffmony.downloader.listener.IDownloadInfosCallback;
import com.jeffmony.downloader.model.VideoTaskItem;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.dispatcher.DownloadDispatcher;
import com.liulishuo.okdownload.core.exception.PreAllocateException;
import com.liulishuo.okdownload.core.exception.ServerCanceledException;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.db.entity.HlsDownload;
import com.movieboxpro.android.event.DownloadCompleteEvent;
import com.movieboxpro.android.event.FileDeleteEvent;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.M3u8Utils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ReflectExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.utils.download.DownloadUtil;
import com.movieboxpro.android.view.dialog.NoEnoughSpaceDialog;
import com.movieboxpro.android.view.fragment.OtgFragment;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal._UtilCommonKt;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: DownloadService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\b\u0007\u0018\u0000 *2\u00020\u0001:\u0003*+,B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\"\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0016J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0002J\b\u0010%\u001a\u00020\u0010H\u0002J&\u0010&\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010\n2\b\u0010(\u001a\u0004\u0018\u00010\n2\b\u0010)\u001a\u0004\u0018\u00010\nH\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/movieboxpro/android/service/DownloadService;", "Landroid/app/Service;", "<init>", "()V", "listenerList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/service/DownloadService$DownloadListener;", "Lkotlin/collections/ArrayList;", "downloadTasks", "Ljava/util/HashMap;", "", "Lcom/liulishuo/okdownload/DownloadTask;", "Lkotlin/collections/HashMap;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "registerDownloadListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "unRegisterDownloadListener", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "downloadListener", "Lcom/liulishuo/okdownload/core/listener/DownloadListener4WithSpeed;", "onStartCommand", "", "flags", "startId", "downloadHls", "downloadFile", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "getAudioSize", "", "audioUrl", "audioEacUrl", "getDownloadingCount", "buildDownloadTask", "url", "parentPath", "fileName", "Companion", "DownloadBinder", "DownloadListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadService extends Service {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DELETE = "delete";
    public static final String PAUSE = "pause";
    public static final int STATUS = 1;
    public static final String TAG = "DownloadService";
    public static final int TOTAL_SIZE = 2;
    private final ArrayList<DownloadListener> listenerList = new ArrayList<>();
    private final HashMap<String, DownloadTask> downloadTasks = new HashMap<>();
    private final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
    private DownloadListener4WithSpeed downloadListener = new DownloadListener4WithSpeed() { // from class: com.movieboxpro.android.service.DownloadService$downloadListener$1

        /* JADX INFO: compiled from: DownloadService.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[EndCause.values().length];
                try {
                    iArr[EndCause.COMPLETED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[EndCause.ERROR.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[EndCause.PRE_ALLOCATE_FAILED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[EndCause.CANCELED.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[EndCause.SAME_TASK_BUSY.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void blockEnd(DownloadTask task, int blockIndex, BlockInfo info, SpeedCalculator blockSpeed) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(blockSpeed, "blockSpeed");
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectEnd(DownloadTask task, int blockIndex, int responseCode, Map<String, List<String>> responseHeaderFields) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(responseHeaderFields, "responseHeaderFields");
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectStart(DownloadTask task, int blockIndex, Map<String, List<String>> requestHeaderFields) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(requestHeaderFields, "requestHeaderFields");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progressBlock(DownloadTask task, int blockIndex, long currentBlockOffset, SpeedCalculator blockSpeed) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(blockSpeed, "blockSpeed");
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(DownloadTask task) {
            Intrinsics.checkNotNullParameter(task, "task");
            Object tag = task.getTag();
            String str = tag instanceof String ? (String) tag : null;
            BuildersKt__Builders_commonKt.launch$default(this.this$0.coroutineScope, null, null, new DownloadService$downloadListener$1$taskStart$1(str, null), 3, null);
            Iterator it = this.this$0.listenerList.iterator();
            while (it.hasNext()) {
                ((DownloadService.DownloadListener) it.next()).onStart(str);
            }
        }

        /* JADX WARN: Code duplicated, block: B:50:0x018a  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r12v15, types: [T, java.lang.String] */
        /* JADX WARN: Type inference failed for: r12v22, types: [T, java.lang.String] */
        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void taskEnd(DownloadTask task, EndCause cause, Exception realCause, SpeedCalculator taskSpeed) {
            String message;
            T t;
            String message2;
            String message3;
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(cause, "cause");
            Intrinsics.checkNotNullParameter(taskSpeed, "taskSpeed");
            Object tag = task.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.String");
            String str = (String) tag;
            int i = WhenMappings.$EnumSwitchMapping$0[cause.ordinal()];
            if (i == 1) {
                Iterator it = this.this$0.listenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadService.DownloadListener) it.next()).onComplete(str);
                }
                BuildersKt__Builders_commonKt.launch$default(this.this$0.coroutineScope, null, null, new DownloadService$downloadListener$1$taskEnd$2(str, null), 3, null);
                EventBus.getDefault().post(new DownloadCompleteEvent());
                return;
            }
            if (i == 2) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = "";
                if (realCause instanceof ServerCanceledException) {
                    ServerCanceledException serverCanceledException = (ServerCanceledException) realCause;
                    if (serverCanceledException.getResponseCode() != 410) {
                        String message4 = serverCanceledException.getMessage();
                        String str2 = message4 != null ? message4 : "";
                        objectRef.element = str2 + ":" + serverCanceledException.getResponseCode();
                        for (DownloadService.DownloadListener downloadListener : this.this$0.listenerList) {
                            Object tag2 = task.getTag();
                            Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type kotlin.String");
                            downloadListener.onFailed((String) tag2, (String) objectRef.element);
                        }
                    }
                } else if (realCause instanceof SSLException) {
                    if (!Network.isConnected(App.getContext())) {
                        objectRef.element = "No internet";
                        for (DownloadService.DownloadListener downloadListener2 : this.this$0.listenerList) {
                            Object tag3 = task.getTag();
                            Intrinsics.checkNotNull(tag3, "null cannot be cast to non-null type kotlin.String");
                            downloadListener2.onFailed((String) tag3, (String) objectRef.element);
                        }
                    } else {
                        objectRef.element = "Connecting error";
                        for (DownloadService.DownloadListener downloadListener3 : this.this$0.listenerList) {
                            Object tag4 = task.getTag();
                            Intrinsics.checkNotNull(tag4, "null cannot be cast to non-null type kotlin.String");
                            downloadListener3.onFailed((String) tag4, (String) objectRef.element);
                        }
                    }
                } else {
                    for (DownloadService.DownloadListener downloadListener4 : this.this$0.listenerList) {
                        Object tag5 = task.getTag();
                        Intrinsics.checkNotNull(tag5, "null cannot be cast to non-null type kotlin.String");
                        String str3 = (String) tag5;
                        if (realCause == null || (message = realCause.getMessage()) == null) {
                            message = "";
                        }
                        downloadListener4.onFailed(str3, message);
                    }
                }
                BuildersKt__Builders_commonKt.launch$default(this.this$0.coroutineScope, null, null, new DownloadService$downloadListener$1$taskEnd$7(str, objectRef, null), 3, null);
                return;
            }
            if (i == 3) {
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = "";
                if (realCause instanceof PreAllocateException) {
                    objectRef2.element = "Free space less than " + FileUtils.byte2FitMemorySize(((PreAllocateException) realCause).getRequireSpace());
                    for (DownloadService.DownloadListener downloadListener5 : this.this$0.listenerList) {
                        Object tag6 = task.getTag();
                        Intrinsics.checkNotNull(tag6, "null cannot be cast to non-null type kotlin.String");
                        downloadListener5.onFailed((String) tag6, (String) objectRef2.element);
                    }
                } else {
                    if (realCause != null) {
                        String message5 = realCause.getMessage();
                        t = message5;
                        if (message5 == null) {
                            t = "";
                        }
                    } else {
                        t = "";
                    }
                    objectRef2.element = t;
                    for (DownloadService.DownloadListener downloadListener6 : this.this$0.listenerList) {
                        Object tag7 = task.getTag();
                        Intrinsics.checkNotNull(tag7, "null cannot be cast to non-null type kotlin.String");
                        String str4 = (String) tag7;
                        if (realCause == null || (message2 = realCause.getMessage()) == null) {
                            message2 = "";
                        }
                        downloadListener6.onFailed(str4, message2);
                    }
                }
                BuildersKt__Builders_commonKt.launch$default(this.this$0.coroutineScope, null, null, new DownloadService$downloadListener$1$taskEnd$10(str, objectRef2, null), 3, null);
                return;
            }
            if (i != 4) {
                if (i == 5) {
                    BuildersKt__Builders_commonKt.launch$default(this.this$0.coroutineScope, null, null, new DownloadService$downloadListener$1$taskEnd$15(str, null), 3, null);
                    for (DownloadService.DownloadListener downloadListener7 : this.this$0.listenerList) {
                        Object tag8 = task.getTag();
                        Intrinsics.checkNotNull(tag8, "null cannot be cast to non-null type kotlin.String");
                        downloadListener7.onWaiting((String) tag8);
                    }
                    return;
                }
                for (DownloadService.DownloadListener downloadListener8 : this.this$0.listenerList) {
                    Object tag9 = task.getTag();
                    Intrinsics.checkNotNull(tag9, "null cannot be cast to non-null type kotlin.String");
                    String str5 = (String) tag9;
                    if (realCause == null || (message3 = realCause.getMessage()) == null) {
                        message3 = "";
                    }
                    downloadListener8.onFailed(str5, message3);
                }
                return;
            }
            Object tag10 = task.getTag(1);
            if (Intrinsics.areEqual(tag10, DownloadService.PAUSE)) {
                BuildersKt__Builders_commonKt.launch$default(this.this$0.coroutineScope, null, null, new DownloadService$downloadListener$1$taskEnd$11(str, null), 3, null);
                for (DownloadService.DownloadListener downloadListener9 : this.this$0.listenerList) {
                    Object tag11 = task.getTag();
                    Intrinsics.checkNotNull(tag11, "null cannot be cast to non-null type kotlin.String");
                    downloadListener9.onPause((String) tag11);
                }
                return;
            }
            if (Intrinsics.areEqual(tag10, "delete")) {
                for (DownloadService.DownloadListener downloadListener10 : this.this$0.listenerList) {
                    Object tag12 = task.getTag();
                    Intrinsics.checkNotNull(tag12, "null cannot be cast to non-null type kotlin.String");
                    downloadListener10.onDelete((String) tag12);
                }
                return;
            }
            for (DownloadService.DownloadListener downloadListener11 : this.this$0.listenerList) {
                Object tag13 = task.getTag();
                Intrinsics.checkNotNull(tag13, "null cannot be cast to non-null type kotlin.String");
                downloadListener11.onDelete((String) tag13);
            }
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void infoReady(DownloadTask task, BreakpointInfo info, boolean fromBreakpoint, Listener4SpeedAssistExtend.Listener4SpeedModel model) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(model, "model");
            Object tag = task.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.String");
            task.addTag(2, Long.valueOf(info.getTotalLength()));
            BuildersKt__Builders_commonKt.launch$default(this.this$0.coroutineScope, null, null, new DownloadService$downloadListener$1$infoReady$1((String) tag, info, null), 3, null);
            for (DownloadService.DownloadListener downloadListener : this.this$0.listenerList) {
                Object tag2 = task.getTag();
                Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type kotlin.String");
                downloadListener.onProgress((String) tag2, info.getTotalOffset(), info.getTotalLength(), "");
            }
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progress(DownloadTask task, long currentOffset, SpeedCalculator taskSpeed) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(taskSpeed, "taskSpeed");
            for (DownloadService.DownloadListener downloadListener : this.this$0.listenerList) {
                Object tag = task.getTag();
                Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.String");
                String str = (String) tag;
                Object tag2 = task.getTag(2);
                Long l = tag2 instanceof Long ? (Long) tag2 : null;
                long jLongValue = l != null ? l.longValue() : 0L;
                String strSpeed = taskSpeed.speed();
                Intrinsics.checkNotNullExpressionValue(strSpeed, "speed(...)");
                downloadListener.onProgress(str, currentOffset, jLongValue, strSpeed);
            }
            BuildersKt__Builders_commonKt.launch$default(this.this$0.coroutineScope, null, null, new DownloadService$downloadListener$1$progress$2(task, currentOffset, taskSpeed, null), 3, null);
        }
    };

    /* JADX INFO: compiled from: DownloadService.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0005H&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u001c\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/service/DownloadService$DownloadListener;", "", "onStart", "", "tag", "", "onProgress", "downloadSize", "", "totalSize", "speed", "onPause", "onFailed", NotificationCompat.CATEGORY_MESSAGE, "onComplete", "onDelete", "onWaiting", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface DownloadListener {
        void onComplete(String tag);

        void onDelete(String tag);

        void onFailed(String tag, String msg);

        void onPause(String tag);

        void onProgress(String tag, long downloadSize, long totalSize, String speed);

        void onStart(String tag);

        void onWaiting(String tag);
    }

    private final void getDownloadingCount() {
    }

    /* JADX INFO: compiled from: DownloadService.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010\u0014\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/service/DownloadService$Companion;", "", "<init>", "()V", "STATUS", "", "TOTAL_SIZE", "DELETE", "", "PAUSE", "TAG", "startDownload", "", "context", "Landroid/content/Context;", "downloadUrl", "Lcom/movieboxpro/android/model/DownloadUrl;", "baseMediaModel", "Lcom/movieboxpro/android/model/BaseMediaModel;", "start", ReflectExtKt.BIND_NAME, "serviceConnection", "Landroid/content/ServiceConnection;", "unBind", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startDownload(Context context, DownloadUrl downloadUrl, BaseMediaModel baseMediaModel) {
            Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
            Intrinsics.checkNotNullParameter(baseMediaModel, "baseMediaModel");
            if (context != null) {
                Intent intent = new Intent(context, (Class<?>) DownloadService.class);
                intent.putExtra("videoData", baseMediaModel);
                intent.putExtra("urlData", downloadUrl);
                context.startService(intent);
            }
        }

        public final void start(Context context) {
            Intent intent = new Intent(context, (Class<?>) DownloadService.class);
            if (context != null) {
                context.startService(intent);
            }
        }

        public final void bind(Context context, ServiceConnection serviceConnection) {
            Intrinsics.checkNotNullParameter(serviceConnection, "serviceConnection");
            Intent intent = new Intent(context, (Class<?>) DownloadService.class);
            if (context != null) {
                context.bindService(intent, serviceConnection, 1);
            }
        }

        public final void unBind(Context context, ServiceConnection serviceConnection) {
            Intrinsics.checkNotNullParameter(serviceConnection, "serviceConnection");
            if (context != null) {
                context.unbindService(serviceConnection);
            }
        }
    }

    public final void registerDownloadListener(DownloadListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listenerList.add(listener);
    }

    public final void unRegisterDownloadListener(DownloadListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listenerList.remove(listener);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new DownloadBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadDispatcher.setMaxParallelRunningCount(PrefsUtils.getInstance().getInt(Constant.Prefs.DOWNLOAD_CONCURRENT, 1));
        VideoDownloadManager.getInstance().fetchDownloadItems(new IDownloadInfosCallback() { // from class: com.movieboxpro.android.service.DownloadService.onCreate.1
            @Override // com.jeffmony.downloader.listener.IDownloadInfosCallback
            public void onDownloadInfos(List<? extends VideoTaskItem> items) {
            }
        });
        VideoDownloadManager.getInstance().setGlobalDownloadListener(new com.jeffmony.downloader.listener.DownloadListener() { // from class: com.movieboxpro.android.service.DownloadService.onCreate.2
            @Override // com.jeffmony.downloader.listener.DownloadListener, com.jeffmony.downloader.listener.IDownloadListener
            public void onDownloadProgress(VideoTaskItem item) {
                Intrinsics.checkNotNullParameter(item, "item");
                super.onDownloadProgress(item);
                Log.d(DownloadService.TAG, "onDownloadProgress " + item.getDownloadSizeString());
                BuildersKt__Builders_commonKt.launch$default(DownloadService.this.coroutineScope, null, null, new DownloadService$onCreate$2$onDownloadProgress$1(item, null), 3, null);
            }

            @Override // com.jeffmony.downloader.listener.DownloadListener, com.jeffmony.downloader.listener.IDownloadListener
            public void onDownloadPause(VideoTaskItem item) {
                Intrinsics.checkNotNullParameter(item, "item");
                super.onDownloadPause(item);
                BuildersKt__Builders_commonKt.launch$default(DownloadService.this.coroutineScope, null, null, new DownloadService$onCreate$2$onDownloadPause$1(item, null), 3, null);
            }

            @Override // com.jeffmony.downloader.listener.DownloadListener, com.jeffmony.downloader.listener.IDownloadListener
            public void onDownloadSuccess(VideoTaskItem item) {
                Intrinsics.checkNotNullParameter(item, "item");
                super.onDownloadSuccess(item);
                BuildersKt__Builders_commonKt.launch$default(DownloadService.this.coroutineScope, null, null, new DownloadService$onCreate$2$onDownloadSuccess$1(item, null), 3, null);
            }

            @Override // com.jeffmony.downloader.listener.DownloadListener, com.jeffmony.downloader.listener.IDownloadListener
            public void onDownloadError(VideoTaskItem item) {
                Intrinsics.checkNotNullParameter(item, "item");
                super.onDownloadError(item);
                Log.d(DownloadService.TAG, "onDownloadError " + item.getErrorCode());
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:45:0x011f  */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        final DownloadUrl downloadUrl = intent != null ? (DownloadUrl) intent.getParcelableExtra("urlData") : null;
        if (downloadUrl != null && !downloadUrl.getHls()) {
            File file = new File(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH()));
            if (!file.exists()) {
                ToastUtils.showShort("Usb device is unavailable", new Object[0]);
                return 1;
            }
            if (!file.canWrite()) {
                ToastUtils.showShort("Can't write file in the usb device", new Object[0]);
                return 1;
            }
        }
        Serializable serializableExtra = intent != null ? intent.getSerializableExtra("videoData") : null;
        final BaseMediaModel baseMediaModel = serializableExtra instanceof BaseMediaModel ? (BaseMediaModel) serializableExtra : null;
        final Ref.LongRef longRef = new Ref.LongRef();
        if (downloadUrl != null) {
            if (downloadUrl.getHls()) {
                int i = baseMediaModel instanceof MovieDetail ? 1 : 2;
                if (baseMediaModel != null) {
                    String str = baseMediaModel.id;
                    String string = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH());
                    DownloadFileHelper companion = DownloadFileHelper.INSTANCE.getInstance();
                    String tmfid = downloadUrl.getTmfid();
                    downloadHls(companion.addDownloadFile(i, str, (tmfid == null || StringsKt.isBlank(tmfid) || Intrinsics.areEqual(downloadUrl.getTmfid(), "-1")) ? downloadUrl.getMmfid() : downloadUrl.getTmfid(), downloadUrl.getPath(), string, downloadUrl.getFilename(), baseMediaModel.title, baseMediaModel.episodeName, baseMediaModel.poster, baseMediaModel.thumb, downloadUrl.getReal_quality(), downloadUrl.getSize_bytes(), baseMediaModel.imdb_id, baseMediaModel.season, baseMediaModel.episode, 6, true));
                }
            } else {
                Observable observableJust = Observable.just(downloadUrl);
                final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.service.DownloadService$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DownloadService.onStartCommand$lambda$6$lambda$1(downloadUrl, (DownloadUrl) obj);
                    }
                };
                Observable observableCompose = observableJust.map(new Function() { // from class: com.movieboxpro.android.service.DownloadService$$ExternalSyntheticLambda1
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return DownloadService.onStartCommand$lambda$6$lambda$2(function1, obj);
                    }
                }).compose(RxUtils.rxSchedulerHelper());
                Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
                final DownloadUrl downloadUrl2 = downloadUrl;
                RxSubscribersKt.subscribeTo$default(observableCompose, null, null, new Function1() { // from class: com.movieboxpro.android.service.DownloadService$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DownloadService.onStartCommand$lambda$6$lambda$5(downloadUrl2, this, baseMediaModel, downloadUrl, longRef, (Boolean) obj);
                    }
                }, null, 11, null);
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean onStartCommand$lambda$6$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Boolean) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean onStartCommand$lambda$6$lambda$1(DownloadUrl downloadUrl, DownloadUrl it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Boolean.valueOf(FileUtils.getAvailableSpace(new File(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH())).getPath()) > downloadUrl.getSize_bytes());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$6$lambda$5(DownloadUrl downloadUrl, DownloadService downloadService, BaseMediaModel baseMediaModel, DownloadUrl downloadUrl2, Ref.LongRef longRef, Boolean bool) {
        DownloadTask downloadTaskBuild;
        if (bool.booleanValue()) {
            if (OtgFragment.INSTANCE.getOtgRoot() != null) {
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH());
                String path = downloadUrl.getPath();
                downloadTaskBuild = new DownloadTask.Builder(path != null ? path : "", string, downloadUrl.getFilename()).setMinIntervalMillisCallbackProcess(1000).build();
            } else {
                String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH());
                String path2 = downloadUrl.getPath();
                downloadTaskBuild = new DownloadTask.Builder(path2 != null ? path2 : "", string2, downloadUrl.getFilename()).setMinIntervalMillisCallbackProcess(1000).build();
            }
            if (downloadTaskBuild != null) {
                String tmfid = downloadUrl.getTmfid();
                if (tmfid == null || StringsKt.isBlank(tmfid) || Intrinsics.areEqual(downloadUrl.getTmfid(), "-1")) {
                    downloadTaskBuild.setTag(downloadUrl.getMmfid());
                } else {
                    downloadTaskBuild.setTag(downloadUrl.getTmfid());
                }
                downloadTaskBuild.enqueue(downloadService.downloadListener);
                HashMap<String, DownloadTask> map = downloadService.downloadTasks;
                Object tag = downloadTaskBuild.getTag();
                Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.String");
                map.put((String) tag, downloadTaskBuild);
                int i = baseMediaModel instanceof MovieDetail ? 1 : 2;
                if (baseMediaModel != null) {
                    String str = baseMediaModel.id;
                    DownloadFileHelper companion = DownloadFileHelper.INSTANCE.getInstance();
                    String tmfid2 = downloadUrl2.getTmfid();
                    companion.addDownloadFile(i, str, (tmfid2 == null || StringsKt.isBlank(tmfid2) || Intrinsics.areEqual(downloadUrl2.getTmfid(), "-1")) ? downloadUrl2.getMmfid() : downloadUrl2.getTmfid(), downloadUrl2.getPath(), downloadTaskBuild.getParentFile().getPath(), downloadUrl2.getFilename(), baseMediaModel.title, baseMediaModel.episodeName, baseMediaModel.poster, baseMediaModel.thumb, downloadUrl2.getReal_quality(), longRef.element, baseMediaModel.imdb_id, (98304 & 8192) != 0 ? 0 : baseMediaModel.season, (98304 & 16384) != 0 ? 0 : baseMediaModel.episode, (32768 & 98304) != 0 ? 8 : 0, (98304 & 65536) != 0 ? false : false);
                }
                if (OkDownload.with().downloadDispatcher().getCurrentRunningCount() == DownloadDispatcher.getMaxParallelRunningCount()) {
                    for (DownloadListener downloadListener : downloadService.listenerList) {
                        Object tag2 = downloadTaskBuild.getTag();
                        Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type kotlin.String");
                        downloadListener.onWaiting((String) tag2);
                    }
                }
            } else {
                ToastUtils.showShort("Can't download in usb device", new Object[0]);
            }
        } else {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            if (topActivityOrApp instanceof AppCompatActivity) {
                new NoEnoughSpaceDialog().show(((AppCompatActivity) topActivityOrApp).getSupportFragmentManager(), "NoEnoughSpaceDialog");
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.service.DownloadService$downloadHls$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.service.DownloadService$downloadHls$1", f = "DownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DownloadFile $downloadFile;
        final /* synthetic */ HlsDownloadDao $hlsDownloadDao;
        int label;
        final /* synthetic */ DownloadService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HlsDownloadDao hlsDownloadDao, DownloadFile downloadFile, DownloadService downloadService, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$hlsDownloadDao = hlsDownloadDao;
            this.$downloadFile = downloadFile;
            this.this$0 = downloadService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$hlsDownloadDao, this.$downloadFile, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            File file;
            File file2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                HlsDownloadDao hlsDownloadDao = this.$hlsDownloadDao;
                String downloadId = this.$downloadFile.getDownloadId();
                Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
                HlsDownload hlsDownloadFindByDownloadId = hlsDownloadDao.findByDownloadId(downloadId);
                if (hlsDownloadFindByDownloadId != null) {
                    long audioSize = this.this$0.getAudioSize(hlsDownloadFindByDownloadId.getAudioUrl(), hlsDownloadFindByDownloadId.getAudioEacUrl());
                    String string = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, "");
                    Intrinsics.checkNotNull(string);
                    if (string.length() == 0) {
                        file2 = new File(Constant.DIR, Constant.Prefs.M3U8_CACHE_DIR);
                    } else {
                        file2 = new File(string, Constant.Prefs.M3U8_CACHE_DIR);
                    }
                    if (audioSize + this.$downloadFile.getSize() > FileUtils.getAvailableSpace(file2.getPath())) {
                        this.$downloadFile.setStatus(7);
                        this.$downloadFile.setErrorMsg("No enough Space");
                        DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(this.$downloadFile);
                        return Unit.INSTANCE;
                    }
                    VideoDownloadManager.getInstance().startDownload(new VideoTaskItem(hlsDownloadFindByDownloadId.getVideoUrl(), "", "", this.$downloadFile.getDownloadId()));
                    if (hlsDownloadFindByDownloadId.getAudioUrl().length() > 0) {
                        VideoDownloadManager.getInstance().startDownload(new VideoTaskItem(hlsDownloadFindByDownloadId.getAudioUrl(), "", "", this.$downloadFile.getDownloadId()));
                    }
                    if (hlsDownloadFindByDownloadId.getAudioEacUrl().length() > 0) {
                        VideoDownloadManager.getInstance().startDownload(new VideoTaskItem(hlsDownloadFindByDownloadId.getAudioEacUrl(), "", "", this.$downloadFile.getDownloadId()));
                    }
                } else {
                    OkHttpClient okHttpClient = Http.getOkHttpClient();
                    Request.Builder builder = new Request.Builder();
                    String url = this.$downloadFile.getUrl();
                    Intrinsics.checkNotNullExpressionValue(url, "getUrl(...)");
                    Response responseExecute = okHttpClient.newCall(builder.url(url).build()).execute();
                    if (responseExecute.getIsSuccessful()) {
                        ResponseBody responseBodyBody = responseExecute.body();
                        String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                        String url2 = this.$downloadFile.getUrl();
                        Intrinsics.checkNotNullExpressionValue(url2, "getUrl(...)");
                        Uri uri = Uri.parse(url2);
                        Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
                        List<String> pathSegments = uri.getPathSegments();
                        Intrinsics.checkNotNullExpressionValue(pathSegments, "getPathSegments(...)");
                        String str = (String) CollectionsKt.last((List) pathSegments);
                        String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, "");
                        Intrinsics.checkNotNull(string2);
                        if (string2.length() == 0) {
                            file = new File(Constant.DIR, Constant.Prefs.M3U8_CACHE_DIR);
                        } else {
                            file = new File(string2, Constant.Prefs.M3U8_CACHE_DIR);
                        }
                        if (!file.exists()) {
                            FileUtils.createOrExistsDir(file);
                        }
                        File file3 = new File(file, str);
                        if (file3.exists()) {
                            file3.delete();
                        } else {
                            FileUtils.createOrExistsFile(file3);
                        }
                        if (strString == null) {
                            strString = "";
                        }
                        FileUtils.write(file3, strString, false);
                        M3u8Utils m3u8Utils = M3u8Utils.INSTANCE;
                        String path = file3.getPath();
                        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
                        Triple<String, String, String> downloadUrl = m3u8Utils.getDownloadUrl(path);
                        if (downloadUrl != null && downloadUrl.getFirst().length() > 0) {
                            String url3 = this.$downloadFile.getUrl();
                            Intrinsics.checkNotNullExpressionValue(url3, "getUrl(...)");
                            String path2 = file3.getPath();
                            Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                            String downloadId2 = this.$downloadFile.getDownloadId();
                            Intrinsics.checkNotNullExpressionValue(downloadId2, "getDownloadId(...)");
                            HlsDownload hlsDownload = new HlsDownload(0L, url3, path2, downloadId2, downloadUrl.getFirst(), downloadUrl.getSecond(), downloadUrl.getThird(), "", "", "", "", 0L, 0L, 0L, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1046529, null);
                            this.$hlsDownloadDao.insertOrReplace(hlsDownload);
                            long audioSize2 = this.this$0.getAudioSize(downloadUrl.getSecond(), downloadUrl.getThird()) + this.$downloadFile.getSize();
                            this.$downloadFile.setSize(audioSize2);
                            if (audioSize2 > FileUtils.getAvailableSpace(file.getPath())) {
                                this.$downloadFile.setStatus(7);
                                this.$downloadFile.setErrorMsg("No enough Space");
                                DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(this.$downloadFile);
                                return Unit.INSTANCE;
                            }
                            VideoDownloadManager.getInstance().startDownload(new VideoTaskItem(hlsDownload.getVideoUrl(), "", "", this.$downloadFile.getDownloadId().toString()));
                            if (hlsDownload.getAudioUrl().length() > 0) {
                                VideoDownloadManager.getInstance().startDownload(new VideoTaskItem(hlsDownload.getAudioUrl(), "", "", this.$downloadFile.getDownloadId().toString()));
                            }
                            if (hlsDownload.getAudioEacUrl().length() > 0) {
                                VideoDownloadManager.getInstance().startDownload(new VideoTaskItem(hlsDownload.getAudioEacUrl(), "", "", this.$downloadFile.getDownloadId().toString()));
                            }
                            this.$downloadFile.setStatus(4);
                            DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(this.$downloadFile);
                        } else {
                            this.$downloadFile.setStatus(7);
                            this.$downloadFile.setErrorMsg("video url is empty");
                            DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(this.$downloadFile);
                        }
                    } else {
                        this.$downloadFile.setStatus(7);
                        this.$downloadFile.setErrorMsg(String.valueOf(responseExecute.code()));
                        DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(this.$downloadFile);
                    }
                }
            } catch (Exception e) {
                this.$downloadFile.setStatus(7);
                this.$downloadFile.setErrorMsg(e.getMessage());
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(this.$downloadFile);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadHls(DownloadFile downloadFile) {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, Dispatchers.getIO(), null, new AnonymousClass1(App.getDB().hlsDownloadDao(), downloadFile, this, null), 2, null);
    }

    /* JADX INFO: compiled from: DownloadService.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/service/DownloadService$DownloadBinder;", "Landroid/os/Binder;", "<init>", "(Lcom/movieboxpro/android/service/DownloadService;)V", "getService", "Lcom/movieboxpro/android/service/DownloadService;", "pauseDownload", "", "downloadFile", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "deleteDownload", "resumeDownload", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public class DownloadBinder extends Binder {
        public DownloadBinder() {
        }

        /* JADX INFO: renamed from: getService, reason: from getter */
        public final DownloadService getThis$0() {
            return DownloadService.this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r0v22 */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r0v9, types: [com.liulishuo.okdownload.DownloadTask] */
        public final void pauseDownload(DownloadFile downloadFile) {
            DownloadTask downloadTaskBuild = "parse(this)";
            Intrinsics.checkNotNullParameter(downloadFile, "downloadFile");
            if (downloadFile.isHls()) {
                HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
                String downloadId = downloadFile.getDownloadId();
                Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
                HlsDownload hlsDownloadFindByDownloadId = hlsDownloadDao.findByDownloadId(downloadId);
                if (hlsDownloadFindByDownloadId != null) {
                    VideoDownloadManager.getInstance().pauseDownloadTask(CollectionsKt.listOf((Object[]) new String[]{hlsDownloadFindByDownloadId.getVideoUrl(), hlsDownloadFindByDownloadId.getAudioUrl(), hlsDownloadFindByDownloadId.getAudioEacUrl()}));
                }
                if (hlsDownloadFindByDownloadId == null) {
                    DownloadUtil.pauseDownload(downloadFile.getDownloadId());
                }
                DownloadUtil.pauseDownload(downloadFile.getDownloadId());
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(2, downloadFile.getDownloadId());
                return;
            }
            String path = downloadFile.getPath();
            if (path == null) {
                path = "";
            }
            if (!new File(path).exists()) {
                ToastUtils.showShort("Usb device is unavailable", new Object[0]);
            }
            DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(2, downloadFile.getDownloadId());
            DownloadTask downloadTask = (DownloadTask) DownloadService.this.downloadTasks.get(downloadFile.getDownloadId());
            if (downloadTask != null) {
                if (CommonUtils.isInternalDownloadPath(downloadFile.getPath()).booleanValue()) {
                    downloadTask.addTag(1, DownloadService.PAUSE);
                    downloadTask.cancel();
                    return;
                }
                DocumentFile documentFileFromSingleUri = DocumentFile.fromSingleUri(App.getContext(), Uri.parse(downloadFile.getPath()));
                if (documentFileFromSingleUri != null && documentFileFromSingleUri.exists()) {
                    downloadTask.addTag(1, DownloadService.PAUSE);
                    downloadTask.cancel();
                    return;
                } else {
                    ToastUtils.showShort("Usb device is unavailable", new Object[0]);
                    downloadTask.addTag(1, DownloadService.PAUSE);
                    downloadTask.cancel();
                    return;
                }
            }
            String path2 = downloadFile.getPath();
            if (path2 != null && !StringsKt.startsWith$default(path2, FirebaseAnalytics.Param.CONTENT, false, 2, (Object) null)) {
                downloadTaskBuild = DownloadService.this.buildDownloadTask(downloadFile.getUrl(), downloadFile.getPath(), downloadFile.getFileName());
            } else {
                try {
                    Context context = App.getContext();
                    String path3 = downloadFile.getPath();
                    Intrinsics.checkNotNullExpressionValue(path3, "getPath(...)");
                    Uri uri = Uri.parse(path3);
                    Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
                    DocumentFile documentFileFromSingleUri2 = DocumentFile.fromSingleUri(context, uri);
                    if (documentFileFromSingleUri2 != null && documentFileFromSingleUri2.exists()) {
                        String url = downloadFile.getUrl();
                        if (url == null) {
                            url = "";
                        }
                        downloadTaskBuild = new DownloadTask.Builder(url, documentFileFromSingleUri2.getUri()).setMinIntervalMillisCallbackProcess(1000).build();
                    } else {
                        String url2 = downloadFile.getUrl();
                        if (url2 == null) {
                            url2 = "";
                        }
                        String path4 = downloadFile.getPath();
                        Intrinsics.checkNotNullExpressionValue(path4, "getPath(...)");
                        Uri uri2 = Uri.parse(path4);
                        Intrinsics.checkNotNullExpressionValue(uri2, "parse(this)");
                        downloadTaskBuild = new DownloadTask.Builder(url2, uri2).setMinIntervalMillisCallbackProcess(1000).build();
                    }
                } catch (Exception unused) {
                    String url3 = downloadFile.getUrl();
                    if (url3 == null) {
                        url3 = "";
                    }
                    String path5 = downloadFile.getPath();
                    Intrinsics.checkNotNullExpressionValue(path5, "getPath(...)");
                    Uri uri3 = Uri.parse(path5);
                    Intrinsics.checkNotNullExpressionValue(uri3, downloadTaskBuild);
                    downloadTaskBuild = new DownloadTask.Builder(url3, uri3).setMinIntervalMillisCallbackProcess(1000).build();
                }
            }
            downloadTaskBuild.setTag(downloadFile.getDownloadId());
            HashMap map = DownloadService.this.downloadTasks;
            String downloadId2 = downloadFile.getDownloadId();
            map.put(downloadId2 != null ? downloadId2 : "", downloadTaskBuild);
            downloadTaskBuild.addTag(1, DownloadService.PAUSE);
            downloadTaskBuild.cancel();
        }

        public final void deleteDownload(DownloadFile downloadFile) {
            DownloadTask downloadTaskBuildDownloadTask;
            DownloadTask downloadTaskBuild;
            Intrinsics.checkNotNullParameter(downloadFile, "downloadFile");
            DownloadTask downloadTask = null;
            if (downloadFile.isHls()) {
                BuildersKt__Builders_commonKt.launch$default(DownloadService.this.coroutineScope, null, null, new DownloadService$DownloadBinder$deleteDownload$1(downloadFile, null), 3, null);
                DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(downloadFile.getDownloadId());
                return;
            }
            String path = downloadFile.getPath();
            String str = "";
            if (path == null) {
                path = "";
            }
            if (!new File(path).exists()) {
                ToastUtils.showShort("Usb device is unavailable,you can delete the file manually", new Object[0]);
            }
            DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(downloadFile.getDownloadId());
            DownloadTask downloadTask2 = (DownloadTask) DownloadService.this.downloadTasks.get(downloadFile.getDownloadId());
            if (downloadTask2 != null) {
                if (CommonUtils.isInternalDownloadPath(downloadFile.getPath()).booleanValue()) {
                    downloadTask2.addTag(1, "delete");
                    downloadTask2.cancel();
                    File file = downloadTask2.getFile();
                    if (file != null) {
                        file.delete();
                    }
                    OkDownload.with().breakpointStore().remove(downloadTask2.getId());
                    return;
                }
                Context context = App.getContext();
                String path2 = downloadFile.getPath();
                Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                Uri uri = Uri.parse(path2);
                Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
                DocumentFile documentFileFromSingleUri = DocumentFile.fromSingleUri(context, uri);
                if (documentFileFromSingleUri != null && documentFileFromSingleUri.exists()) {
                    downloadTask2.addTag(1, "delete");
                    downloadTask2.cancel();
                    try {
                        Context context2 = App.getContext();
                        String path3 = downloadFile.getPath();
                        Intrinsics.checkNotNullExpressionValue(path3, "getPath(...)");
                        Uri uri2 = Uri.parse(path3);
                        Intrinsics.checkNotNullExpressionValue(uri2, "parse(this)");
                        DocumentFile documentFileFromSingleUri2 = DocumentFile.fromSingleUri(context2, uri2);
                        if (documentFileFromSingleUri2 != null) {
                            documentFileFromSingleUri2.delete();
                        }
                    } catch (Exception unused) {
                    }
                    OkDownload.with().breakpointStore().remove(downloadTask2.getId());
                    return;
                }
                ToastUtils.showShort("Usb device is unavailable", new Object[0]);
                downloadTask2.addTag(1, "delete");
                downloadTask2.cancel();
                OkDownload.with().breakpointStore().remove(downloadTask2.getId());
                return;
            }
            if (CommonUtils.isInternalDownloadPath(downloadFile.getPath()).booleanValue()) {
                downloadTaskBuildDownloadTask = DownloadService.this.buildDownloadTask(downloadFile.getUrl(), downloadFile.getPath(), downloadFile.getFileName());
            } else {
                try {
                    Context context3 = App.getContext();
                    String path4 = downloadFile.getPath();
                    Intrinsics.checkNotNullExpressionValue(path4, "getPath(...)");
                    Uri uri3 = Uri.parse(path4);
                    Intrinsics.checkNotNullExpressionValue(uri3, "parse(this)");
                    DocumentFile documentFileFromSingleUri3 = DocumentFile.fromSingleUri(context3, uri3);
                    if (documentFileFromSingleUri3 != null && documentFileFromSingleUri3.exists()) {
                        String url = downloadFile.getUrl();
                        if (url != null) {
                            str = url;
                        }
                        downloadTaskBuild = new DownloadTask.Builder(str, documentFileFromSingleUri3.getUri()).setMinIntervalMillisCallbackProcess(1000).build();
                    } else {
                        String url2 = downloadFile.getUrl();
                        if (url2 != null) {
                            str = url2;
                        }
                        Intrinsics.checkNotNull(documentFileFromSingleUri3);
                        downloadTaskBuild = new DownloadTask.Builder(str, documentFileFromSingleUri3.getUri()).setMinIntervalMillisCallbackProcess(1000).build();
                    }
                    downloadTask = downloadTaskBuild;
                } catch (Exception unused2) {
                }
                downloadTaskBuildDownloadTask = downloadTask;
            }
            if (downloadTaskBuildDownloadTask != null) {
                downloadTaskBuildDownloadTask.addTag(1, "delete");
                downloadTaskBuildDownloadTask.cancel();
                if (CommonUtils.isInternalDownloadPath(downloadFile.getPath()).booleanValue()) {
                    File file2 = downloadTaskBuildDownloadTask.getFile();
                    if (file2 != null) {
                        file2.delete();
                    }
                } else {
                    try {
                        DocumentFile documentFileFromSingleUri4 = DocumentFile.fromSingleUri(App.getContext(), Uri.parse(downloadFile.getPath()));
                        if (documentFileFromSingleUri4 != null) {
                            Boolean.valueOf(documentFileFromSingleUri4.delete());
                        }
                    } catch (Exception unused3) {
                        Unit unit = Unit.INSTANCE;
                    }
                }
                OkDownload.with().breakpointStore().remove(downloadTaskBuildDownloadTask.getId());
                return;
            }
            EventBus.getDefault().post(new FileDeleteEvent());
        }

        public final void resumeDownload(DownloadFile downloadFile) {
            DownloadTask downloadTaskBuild;
            Intrinsics.checkNotNullParameter(downloadFile, "downloadFile");
            if (downloadFile.isHls()) {
                HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
                String downloadId = downloadFile.getDownloadId();
                Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
                if (hlsDownloadDao.findByDownloadId(downloadId) != null) {
                    DownloadService.this.downloadHls(downloadFile);
                } else {
                    DownloadUtil.doDownload(downloadFile.getDownloadId(), downloadFile.getUrl());
                }
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(4, downloadFile.getDownloadId());
                return;
            }
            String path = downloadFile.getPath();
            if (path == null) {
                path = "";
            }
            String str = "Usb device is unavailable";
            if (new File(path).exists()) {
                DownloadTask downloadTask = (DownloadTask) DownloadService.this.downloadTasks.get(downloadFile.getDownloadId());
                if (downloadTask != null) {
                    if (CommonUtils.isInternalDownloadPath(downloadFile.getPath()).booleanValue()) {
                        if (OkDownload.with().downloadDispatcher().getCurrentRunningCount() == DownloadDispatcher.getMaxParallelRunningCount()) {
                            for (DownloadListener downloadListener : DownloadService.this.listenerList) {
                                Object tag = downloadTask.getTag();
                                Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.String");
                                downloadListener.onWaiting((String) tag);
                            }
                        }
                        downloadTask.removeTag(1);
                        downloadTask.enqueue(DownloadService.this.downloadListener);
                        return;
                    }
                    Context context = App.getContext();
                    String path2 = downloadFile.getPath();
                    Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                    Uri uri = Uri.parse(path2);
                    Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
                    DocumentFile documentFileFromSingleUri = DocumentFile.fromSingleUri(context, uri);
                    if (documentFileFromSingleUri != null && documentFileFromSingleUri.exists()) {
                        if (OkDownload.with().downloadDispatcher().getCurrentRunningCount() == DownloadDispatcher.getMaxParallelRunningCount()) {
                            for (DownloadListener downloadListener2 : DownloadService.this.listenerList) {
                                Object tag2 = downloadTask.getTag();
                                Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type kotlin.String");
                                downloadListener2.onWaiting((String) tag2);
                            }
                        }
                        downloadTask.removeTag(1);
                        downloadTask.enqueue(DownloadService.this.downloadListener);
                        return;
                    }
                    ToastUtils.showShort("Usb device is unavailable", new Object[0]);
                    return;
                }
                String str2 = "Can't resume this task,please remove it and download again";
                if (CommonUtils.isInternalDownloadPath(downloadFile.getPath()).booleanValue()) {
                    downloadTaskBuild = DownloadService.this.buildDownloadTask(downloadFile.getUrl(), downloadFile.getPath(), downloadFile.getFileName());
                } else {
                    downloadTaskBuild = null;
                    try {
                        Context context2 = App.getContext();
                        String path3 = downloadFile.getPath();
                        Intrinsics.checkNotNullExpressionValue(path3, "getPath(...)");
                        Uri uri2 = Uri.parse(path3);
                        Intrinsics.checkNotNullExpressionValue(uri2, "parse(this)");
                        DocumentFile documentFileFromSingleUri2 = DocumentFile.fromSingleUri(context2, uri2);
                        if (documentFileFromSingleUri2 != null && documentFileFromSingleUri2.exists()) {
                            if (documentFileFromSingleUri2.canWrite()) {
                                String url = downloadFile.getUrl();
                                if (url == null) {
                                    url = "";
                                }
                                downloadTaskBuild = new DownloadTask.Builder(url, documentFileFromSingleUri2.getUri()).setMinIntervalMillisCallbackProcess(1000).build();
                            } else if (OtgFragment.INSTANCE.getOtgRoot() == null) {
                                str = "Please grant access permission";
                            }
                            str = "Can't resume this task,please remove it and download again";
                        }
                        str2 = str;
                    } catch (Exception unused) {
                    }
                }
                if (downloadTaskBuild != null) {
                    downloadTaskBuild.removeTag(1);
                    downloadTaskBuild.setTag(downloadFile.getDownloadId());
                    HashMap map = DownloadService.this.downloadTasks;
                    String downloadId2 = downloadFile.getDownloadId();
                    map.put(downloadId2 != null ? downloadId2 : "", downloadTaskBuild);
                    downloadTaskBuild.enqueue(DownloadService.this.downloadListener);
                    if (OkDownload.with().downloadDispatcher().getCurrentRunningCount() == DownloadDispatcher.getMaxParallelRunningCount()) {
                        for (DownloadListener downloadListener3 : DownloadService.this.listenerList) {
                            Object tag3 = downloadTaskBuild.getTag();
                            Intrinsics.checkNotNull(tag3, "null cannot be cast to non-null type kotlin.String");
                            downloadListener3.onWaiting((String) tag3);
                        }
                        return;
                    }
                    return;
                }
                ToastUtils.showShort(str2, new Object[0]);
                return;
            }
            ToastUtils.showShort("Usb device is unavailable", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DownloadTask buildDownloadTask(String url, String parentPath, String fileName) {
        if (url == null) {
            url = "";
        }
        if (parentPath == null) {
            parentPath = "";
        }
        DownloadTask downloadTaskBuild = new DownloadTask.Builder(url, parentPath, fileName).setMinIntervalMillisCallbackProcess(1000).build();
        Intrinsics.checkNotNullExpressionValue(downloadTaskBuild, "build(...)");
        return downloadTaskBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getAudioSize(String audioUrl, String audioEacUrl) {
        Uri uri = Uri.parse(audioUrl);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
        String queryParameter = uri.getQueryParameter("size");
        if (queryParameter == null) {
            queryParameter = "";
        }
        long longOrDefault = _UtilCommonKt.toLongOrDefault(queryParameter, 0L);
        Uri uri2 = Uri.parse(audioEacUrl);
        Intrinsics.checkNotNullExpressionValue(uri2, "parse(this)");
        String queryParameter2 = uri2.getQueryParameter("size");
        return longOrDefault + _UtilCommonKt.toLongOrDefault(queryParameter2 != null ? queryParameter2 : "", 0L);
    }
}
