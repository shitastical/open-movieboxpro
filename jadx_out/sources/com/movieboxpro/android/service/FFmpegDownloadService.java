package com.movieboxpro.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.DownloadCompleteEvent;
import com.movieboxpro.android.livedata.DownloadLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.dialog.NoEnoughSpaceDialog;
import io.microshow.rxffmpeg.RxFFmpegCommandList;
import io.microshow.rxffmpeg.RxFFmpegInvoke;
import io.microshow.rxffmpeg.RxFFmpegProgress;
import io.microshow.rxffmpeg.RxFFmpegSubscriber;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: FFmpegDownloadService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0003\u001b\u001c\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J*\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\nH\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R \u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/service/FFmpegDownloadService;", "Landroid/app/Service;", "<init>", "()V", "downloadQueue", "Ljava/util/ArrayDeque;", "Lkotlin/Pair;", "Lcom/movieboxpro/android/model/DownloadUrl;", "Lcom/movieboxpro/android/model/BaseMediaModel;", "isDownloading", "", "onCreate", "", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "doDownload", "fid", "", "videoData", "downloadUrl", "downloadNext", "onBind", "Landroid/os/IBinder;", "Companion", "DownloadActionListener", "DownloadListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FFmpegDownloadService extends Service {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ArrayDeque<Pair<DownloadUrl, BaseMediaModel>> downloadQueue = new ArrayDeque<>();
    private boolean isDownloading;

    /* JADX INFO: compiled from: FFmpegDownloadService.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/service/FFmpegDownloadService$DownloadActionListener;", "", "cancelDownload", "", FileDownloadService.PARAMS_KEY_PATH, "", "fid", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface DownloadActionListener {
        void cancelDownload(String path, String fid);
    }

    /* JADX INFO: compiled from: FFmpegDownloadService.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/service/FFmpegDownloadService$DownloadListener;", "", "onStartLoading", "", "fid", "", "onStart", "onProgress", "progress", "", "onComplete", "onFailed", NotificationCompat.CATEGORY_MESSAGE, "onPause", "onDelete", "onWaiting", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface DownloadListener {
        void onComplete(String fid);

        void onDelete(String fid);

        void onFailed(String fid, String msg);

        void onPause(String fid);

        void onProgress(String fid, int progress);

        void onStart(String fid);

        void onStartLoading(String fid);

        void onWaiting(String fid);
    }

    /* JADX INFO: compiled from: FFmpegDownloadService.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/service/FFmpegDownloadService$Companion;", "", "<init>", "()V", "startDownload", "", "context", "Landroid/content/Context;", "downloadUrl", "Lcom/movieboxpro/android/model/DownloadUrl;", "baseMediaModel", "Lcom/movieboxpro/android/model/BaseMediaModel;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
                Intent intent = new Intent(context, (Class<?>) FFmpegDownloadService.class);
                intent.putExtra("videoData", baseMediaModel);
                intent.putExtra("urlData", downloadUrl);
                context.startService(intent);
            }
        }
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.service.FFmpegDownloadService$onCreate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FFmpegDownloadService.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007"}, d2 = {"com/movieboxpro/android/service/FFmpegDownloadService$onCreate$1", "Lcom/movieboxpro/android/service/FFmpegDownloadService$DownloadActionListener;", "cancelDownload", "", FileDownloadService.PARAMS_KEY_PATH, "", "fid", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class C01221 implements DownloadActionListener {
        C01221() {
        }

        @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadActionListener
        public void cancelDownload(final String path, final String fid) {
            Observable observableJust = Observable.just(path == null ? "" : path);
            final FFmpegDownloadService fFmpegDownloadService = FFmpegDownloadService.this;
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.service.FFmpegDownloadService$onCreate$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return FFmpegDownloadService.C01221.cancelDownload$lambda$1(fFmpegDownloadService, path, fid, (String) obj);
                }
            };
            Observable observableCompose = observableJust.map(new Function() { // from class: com.movieboxpro.android.service.FFmpegDownloadService$onCreate$1$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return FFmpegDownloadService.C01221.cancelDownload$lambda$2(function1, obj);
                }
            }).compose(RxUtils.rxSchedulerHelper());
            Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
            RxSubscribersKt.subscribeTo$default(observableCompose, null, null, new Function1() { // from class: com.movieboxpro.android.service.FFmpegDownloadService$onCreate$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return FFmpegDownloadService.C01221.cancelDownload$lambda$3(fid, (Unit) obj);
                }
            }, null, 11, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit cancelDownload$lambda$2(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (Unit) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit cancelDownload$lambda$1(FFmpegDownloadService fFmpegDownloadService, String str, String str2, String it) {
            Object next;
            String mmfid;
            Intrinsics.checkNotNullParameter(it, "it");
            Iterator it2 = fFmpegDownloadService.downloadQueue.iterator();
            do {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
                Pair pair = (Pair) next;
                String tmfid = ((DownloadUrl) pair.getFirst()).getTmfid();
                if (tmfid == null || StringsKt.isBlank(tmfid)) {
                    mmfid = ((DownloadUrl) pair.getFirst()).getMmfid();
                } else {
                    mmfid = ((DownloadUrl) pair.getFirst()).getTmfid();
                }
            } while (!Intrinsics.areEqual(mmfid, str2));
            Pair pair2 = (Pair) next;
            if (pair2 != null) {
                fFmpegDownloadService.downloadQueue.remove(pair2);
            } else {
                RxFFmpegInvoke.getInstance().exit();
                if (str == null) {
                    str = "";
                }
                FileUtils.deleteDir(new File(str));
            }
            DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(str2);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit cancelDownload$lambda$3(String str, Unit unit) {
            DownloadLiveData companion = DownloadLiveData.INSTANCE.getInstance();
            if (str == null) {
                str = "";
            }
            companion.onDelete(str);
            return Unit.INSTANCE;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadLiveData.INSTANCE.getInstance().registerActionListener(new C01221());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        final DownloadUrl downloadUrl = intent != null ? (DownloadUrl) intent.getParcelableExtra("urlData") : null;
        Serializable serializableExtra = intent != null ? intent.getSerializableExtra("videoData") : null;
        final BaseMediaModel baseMediaModel = serializableExtra instanceof BaseMediaModel ? (BaseMediaModel) serializableExtra : null;
        if (downloadUrl == null) {
            return 1;
        }
        Observable observableJust = Observable.just(downloadUrl);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.service.FFmpegDownloadService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FFmpegDownloadService.onStartCommand$lambda$4$lambda$0(downloadUrl, (DownloadUrl) obj);
            }
        };
        Observable observableCompose = observableJust.map(new Function() { // from class: com.movieboxpro.android.service.FFmpegDownloadService$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FFmpegDownloadService.onStartCommand$lambda$4$lambda$1(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(observableCompose, null, null, new Function1() { // from class: com.movieboxpro.android.service.FFmpegDownloadService$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FFmpegDownloadService.onStartCommand$lambda$4$lambda$3(baseMediaModel, downloadUrl, this, (Boolean) obj);
            }
        }, null, 11, null);
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean onStartCommand$lambda$4$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Boolean) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean onStartCommand$lambda$4$lambda$0(DownloadUrl downloadUrl, DownloadUrl it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Boolean.valueOf(FileUtils.getAvailableSpace(new File(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH())).getPath()) > downloadUrl.getSize_bytes());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$4$lambda$3(BaseMediaModel baseMediaModel, DownloadUrl downloadUrl, FFmpegDownloadService fFmpegDownloadService, Boolean bool) {
        String mmfid;
        String str;
        int i;
        if (!bool.booleanValue()) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            if (topActivityOrApp instanceof AppCompatActivity) {
                new NoEnoughSpaceDialog().show(((AppCompatActivity) topActivityOrApp).getSupportFragmentManager(), "NoEnoughSpaceDialog");
            }
        } else if (baseMediaModel != null) {
            String tmfid = downloadUrl.getTmfid();
            if (tmfid == null || StringsKt.isBlank(tmfid)) {
                mmfid = downloadUrl.getMmfid();
            } else {
                mmfid = downloadUrl.getTmfid();
            }
            if (baseMediaModel.box_type == 1) {
                str = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH()) + File.separator + "ffmpegDownload" + File.separator + baseMediaModel.id + "_" + baseMediaModel.box_type;
            } else {
                str = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH()) + File.separator + "ffmpegDownload" + File.separator + baseMediaModel.id + "_" + baseMediaModel.box_type + File.separator + ExifInterface.LATITUDE_SOUTH + baseMediaModel.season + File.separator + ExifInterface.LONGITUDE_EAST + baseMediaModel.episode;
            }
            String str2 = str;
            File file = new File(str2);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    ToastUtils.showShort("Create file failed:please make sure the usb device is formatted as FAT32", new Object[0]);
                } else {
                    i = baseMediaModel instanceof MovieDetail ? 1 : 2;
                    String str3 = baseMediaModel.id;
                    if (fFmpegDownloadService.isDownloading) {
                        DownloadFileHelper companion = DownloadFileHelper.INSTANCE.getInstance();
                        String tmfid2 = downloadUrl.getTmfid();
                        String mmfid2 = (tmfid2 == null || StringsKt.isBlank(tmfid2)) ? downloadUrl.getMmfid() : downloadUrl.getTmfid();
                        companion.addDownloadFile(i, str3, mmfid2, downloadUrl.getPath(), str2, mmfid + Video.SUFFIX.SUFFIX_M3U8, baseMediaModel.title, baseMediaModel.episodeName, baseMediaModel.poster, baseMediaModel.thumb, downloadUrl.getReal_quality(), downloadUrl.getSize_bytes(), baseMediaModel.imdb_id, (98304 & 8192) != 0 ? 0 : baseMediaModel.season, (98304 & 16384) != 0 ? 0 : baseMediaModel.episode, (32768 & 98304) != 0 ? 8 : 0, (98304 & 65536) != 0 ? false : false);
                        fFmpegDownloadService.downloadQueue.add(new Pair<>(downloadUrl, baseMediaModel));
                        DownloadLiveData companion2 = DownloadLiveData.INSTANCE.getInstance();
                        if (mmfid == null) {
                            mmfid = "";
                        }
                        companion2.onWaiting(mmfid);
                    } else {
                        fFmpegDownloadService.doDownload(mmfid, baseMediaModel, downloadUrl, false);
                    }
                }
            } else {
                i = baseMediaModel instanceof MovieDetail ? 1 : 2;
                String str4 = baseMediaModel.id;
                if (fFmpegDownloadService.isDownloading) {
                    DownloadFileHelper companion3 = DownloadFileHelper.INSTANCE.getInstance();
                    String tmfid3 = downloadUrl.getTmfid();
                    String mmfid3 = (tmfid3 == null || StringsKt.isBlank(tmfid3)) ? downloadUrl.getMmfid() : downloadUrl.getTmfid();
                    companion3.addDownloadFile(i, str4, mmfid3, downloadUrl.getPath(), str2, mmfid + Video.SUFFIX.SUFFIX_M3U8, baseMediaModel.title, baseMediaModel.episodeName, baseMediaModel.poster, baseMediaModel.thumb, downloadUrl.getReal_quality(), downloadUrl.getSize_bytes(), baseMediaModel.imdb_id, (98304 & 8192) != 0 ? 0 : baseMediaModel.season, (98304 & 16384) != 0 ? 0 : baseMediaModel.episode, (32768 & 98304) != 0 ? 8 : 0, (98304 & 65536) != 0 ? false : false);
                    fFmpegDownloadService.downloadQueue.add(new Pair<>(downloadUrl, baseMediaModel));
                    DownloadLiveData companion4 = DownloadLiveData.INSTANCE.getInstance();
                    if (mmfid == null) {
                        mmfid = "";
                    }
                    companion4.onWaiting(mmfid);
                } else {
                    fFmpegDownloadService.doDownload(mmfid, baseMediaModel, downloadUrl, false);
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doDownload(final String fid, BaseMediaModel videoData, DownloadUrl downloadUrl, boolean downloadNext) {
        String str;
        if (videoData.box_type == 1) {
            str = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH()) + File.separator + "ffmpegDownload" + File.separator + videoData.id + "_" + videoData.box_type;
        } else {
            str = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH()) + File.separator + "ffmpegDownload" + File.separator + videoData.id + "_" + videoData.box_type + File.separator + ExifInterface.LATITUDE_SOUTH + videoData.season + File.separator + ExifInterface.LONGITUDE_EAST + videoData.episode;
        }
        String str2 = str;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        int i = videoData instanceof MovieDetail ? 1 : 2;
        String str3 = videoData.id;
        String str4 = str2 + "/" + fid + Video.SUFFIX.SUFFIX_M3U8;
        String str5 = str2 + "/" + fid + "_%04d.ts";
        if (!downloadNext) {
            DownloadFileHelper companion = DownloadFileHelper.INSTANCE.getInstance();
            String tmfid = downloadUrl.getTmfid();
            String mmfid = (tmfid == null || StringsKt.isBlank(tmfid)) ? downloadUrl.getMmfid() : downloadUrl.getTmfid();
            companion.addDownloadFile(i, str3, mmfid, downloadUrl.getPath(), str2, fid + Video.SUFFIX.SUFFIX_M3U8, videoData.title, videoData.episodeName, videoData.poster, videoData.thumb, downloadUrl.getReal_quality(), downloadUrl.getSize_bytes(), videoData.imdb_id, (98304 & 8192) != 0 ? 0 : videoData.season, (98304 & 16384) != 0 ? 0 : videoData.episode, (32768 & 98304) != 0 ? 8 : 0, (98304 & 65536) != 0 ? false : false);
        }
        DownloadLiveData.INSTANCE.getInstance().onStart(fid == null ? "" : fid);
        DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(4, fid);
        final Ref.LongRef longRef = new Ref.LongRef();
        this.isDownloading = true;
        RxFFmpegCommandList rxFFmpegCommandList = new RxFFmpegCommandList();
        rxFFmpegCommandList.append("-i");
        rxFFmpegCommandList.append(downloadUrl.getPath());
        rxFFmpegCommandList.append("-f");
        rxFFmpegCommandList.append("segment");
        rxFFmpegCommandList.append("-segment_time");
        rxFFmpegCommandList.append("120");
        rxFFmpegCommandList.append("-segment_list");
        rxFFmpegCommandList.append(str4);
        rxFFmpegCommandList.append("-c");
        rxFFmpegCommandList.append("copy");
        rxFFmpegCommandList.append(str5);
        RxFFmpegInvoke.getInstance().runCommandRxJava(rxFFmpegCommandList.build()).subscribe((FlowableSubscriber<? super RxFFmpegProgress>) new RxFFmpegSubscriber() { // from class: com.movieboxpro.android.service.FFmpegDownloadService.doDownload.1
            @Override // io.microshow.rxffmpeg.RxFFmpegInvoke.IFFmpegListener
            public void onError(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                if (Intrinsics.areEqual("转码失败", message)) {
                    DownloadFileHelper companion2 = DownloadFileHelper.INSTANCE.getInstance();
                    String str6 = fid;
                    if (str6 == null) {
                        str6 = "";
                    }
                    companion2.updateDownloadError(str6, "Transcoding failed");
                    DownloadLiveData companion3 = DownloadLiveData.INSTANCE.getInstance();
                    String str7 = fid;
                    companion3.onFailed(str7 != null ? str7 : "", "Transcoding failed");
                } else {
                    DownloadFileHelper companion4 = DownloadFileHelper.INSTANCE.getInstance();
                    String str8 = fid;
                    if (str8 == null) {
                        str8 = "";
                    }
                    companion4.updateDownloadError(str8, message);
                    DownloadLiveData companion5 = DownloadLiveData.INSTANCE.getInstance();
                    String str9 = fid;
                    companion5.onFailed(str9 != null ? str9 : "", message);
                }
                this.isDownloading = false;
            }

            @Override // io.microshow.rxffmpeg.RxFFmpegInvoke.IFFmpegListener
            public void onFinish() {
                Pair pair;
                String mmfid2;
                EventBus.getDefault().post(new DownloadCompleteEvent());
                DownloadLiveData companion2 = DownloadLiveData.INSTANCE.getInstance();
                String str6 = fid;
                if (str6 == null) {
                    str6 = "";
                }
                companion2.onComplete(str6);
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(1, fid);
                this.isDownloading = false;
                if (this.downloadQueue.isEmpty() || (pair = (Pair) this.downloadQueue.poll()) == null) {
                    return;
                }
                FFmpegDownloadService fFmpegDownloadService = this;
                String tmfid2 = ((DownloadUrl) pair.getFirst()).getTmfid();
                if (tmfid2 == null || StringsKt.isBlank(tmfid2)) {
                    mmfid2 = ((DownloadUrl) pair.getFirst()).getMmfid();
                } else {
                    mmfid2 = ((DownloadUrl) pair.getFirst()).getTmfid();
                }
                fFmpegDownloadService.doDownload(mmfid2, (BaseMediaModel) pair.getSecond(), (DownloadUrl) pair.getFirst(), true);
            }

            @Override // io.microshow.rxffmpeg.RxFFmpegInvoke.IFFmpegListener
            public void onProgress(int progress, long progressTime) {
                this.isDownloading = true;
                if (System.currentTimeMillis() - longRef.element > 1000) {
                    DownloadLiveData companion2 = DownloadLiveData.INSTANCE.getInstance();
                    String str6 = fid;
                    if (str6 == null) {
                        str6 = "";
                    }
                    companion2.onProgress(str6, progress);
                    DownloadFileHelper.INSTANCE.getInstance().updateDownloadPercentProgress(fid, progress);
                    longRef.element = System.currentTimeMillis();
                }
            }

            @Override // io.microshow.rxffmpeg.RxFFmpegInvoke.IFFmpegListener
            public void onCancel() {
                this.isDownloading = false;
                DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(fid);
                DownloadLiveData companion2 = DownloadLiveData.INSTANCE.getInstance();
                String str6 = fid;
                if (str6 == null) {
                    str6 = "";
                }
                companion2.onDelete(str6);
            }
        });
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new Binder();
    }
}
