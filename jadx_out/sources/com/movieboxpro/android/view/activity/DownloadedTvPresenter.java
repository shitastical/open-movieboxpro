package com.movieboxpro.android.view.activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.media3.exoplayer.offline.DownloadService;
import com.jeffmony.downloader.VideoDownloadManager;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.entity.ConnectUsbDevice;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.db.entity.HlsDownload;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.service.MyDownloadService;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.download.DownloadUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DownloadedTvPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0016\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0017R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadedTvPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/DownloadedTvContract$View;", "Lcom/movieboxpro/android/view/activity/DownloadedTvContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "id", "", "findDevice", FileDownloadService.PARAMS_KEY_PATH, "getDownloadedTv", "", "deleteDownloadedTv", "list", "", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadedTvPresenter extends BasePresenter<DownloadedTvContract.View> implements DownloadedTvContract.Presenter {
    private String id;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadedTvPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.id = "";
    }

    private final String findDevice(String path) {
        List<ConnectUsbDevice> all = App.getDB().connectUsbDeviceDao().getAll();
        Intrinsics.checkNotNull(all);
        for (ConnectUsbDevice connectUsbDevice : all) {
            if (path != null) {
                String downloadPath = connectUsbDevice.getDownloadPath();
                Intrinsics.checkNotNullExpressionValue(downloadPath, "getDownloadPath(...)");
                if (StringsKt.contains$default((CharSequence) path, (CharSequence) downloadPath, false, 2, (Object) null)) {
                    String productName = connectUsbDevice.getProductName();
                    Intrinsics.checkNotNullExpressionValue(productName, "getProductName(...)");
                    return productName;
                }
            }
        }
        return "Usb Device";
    }

    @Override // com.movieboxpro.android.view.activity.DownloadedTvContract.Presenter
    public void getDownloadedTv(final String id) {
        this.id = id == null ? "" : id;
        Observable observableJust = Observable.just(id);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvPresenter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvPresenter.getDownloadedTv$lambda$3(id, (String) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.DownloadedTvPresenter$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadedTvPresenter.getDownloadedTv$lambda$4(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvPresenter$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvPresenter.getDownloadedTv$lambda$5(this.f$0, (List) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadedTv$lambda$4(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadedTv$lambda$3(String str, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        List<DownloadFile> listFindDownloadedTv = DownloadFileHelper.INSTANCE.getInstance().findDownloadedTv(it);
        List<DownloadFile> list = listFindDownloadedTv;
        for (DownloadFile downloadFile : list) {
            PlayRecode playRecodeFindByEpisode = App.getDB().playRecodeDao().findByEpisode(2, str, downloadFile.getSeason(), downloadFile.getEpisode());
            if (playRecodeFindByEpisode != null && playRecodeFindByEpisode.getStart_time() != 0) {
                downloadFile.setRecordTime(TimeUtils.INSTANCE.getTime(playRecodeFindByEpisode.getStart_time() / 1000));
            }
        }
        for (DownloadFile downloadFile2 : list) {
            String path = downloadFile2.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            if (!StringsKt.startsWith$default(path, "/storage/emulated/0", false, 2, (Object) null) && !downloadFile2.isHls()) {
                UsbConnectReceiver usbConnectReceiver = UsbConnectReceiver.INSTANCE;
                String path2 = downloadFile2.getPath();
                Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                downloadFile2.setDevice("USB-" + usbConnectReceiver.getUsbDisplayName(path2));
            }
        }
        return listFindDownloadedTv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getDownloadedTv$lambda$5(DownloadedTvPresenter downloadedTvPresenter, List list) {
        DownloadedTvContract.View view = downloadedTvPresenter.getView();
        Intrinsics.checkNotNull(list);
        view.showTv(list);
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.view.activity.DownloadedTvContract.Presenter
    public void deleteDownloadedTv(final List<? extends DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Observable observableJust = Observable.just(list);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvPresenter.deleteDownloadedTv$lambda$7((List) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.DownloadedTvPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadedTvPresenter.deleteDownloadedTv$lambda$8(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvPresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvPresenter.deleteDownloadedTv$lambda$9(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvPresenter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvPresenter.deleteDownloadedTv$lambda$10(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvPresenter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvPresenter.deleteDownloadedTv$lambda$11(this.f$0, list, (Unit) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadedTv$lambda$8(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadedTv$lambda$7(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            DownloadFile downloadFile = (DownloadFile) it2.next();
            if (CommonUtils.isFFmpegDownload(downloadFile.getPath())) {
                FileUtils.delete(new File(downloadFile.getPath()));
            } else {
                FileUtils.delete(new File(downloadFile.getPath(), downloadFile.getFileName()));
            }
            DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(downloadFile.getDownloadId());
            HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
            String downloadId = downloadFile.getDownloadId();
            Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
            HlsDownload hlsDownloadFindByDownloadId = hlsDownloadDao.findByDownloadId(downloadId);
            if (hlsDownloadFindByDownloadId != null) {
                VideoDownloadManager.getInstance().deleteVideoTasks(CollectionsKt.listOf((Object[]) new String[]{hlsDownloadFindByDownloadId.getVideoUrl(), hlsDownloadFindByDownloadId.getAudioUrl(), hlsDownloadFindByDownloadId.getAudioEacUrl()}), true);
                new File(hlsDownloadFindByDownloadId.getM3u8LocalPath()).delete();
                new File(hlsDownloadFindByDownloadId.getPlaybackM3u8LocalPath()).delete();
                App.getDB().hlsDownloadDao().delete(hlsDownloadFindByDownloadId);
            } else {
                DownloadService.sendRemoveDownload(App.getContext(), MyDownloadService.class, downloadFile.getDownloadId(), false);
                DownloadUtil.pauseDownload(downloadFile.getDownloadId());
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadedTv$lambda$10(DownloadedTvPresenter downloadedTvPresenter, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadedTvPresenter.getView().showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadedTv$lambda$11(DownloadedTvPresenter downloadedTvPresenter, List list, Unit unit) {
        downloadedTvPresenter.getView().hideLoadingView();
        downloadedTvPresenter.getView().deleteComplete(list);
        downloadedTvPresenter.getDownloadedTv(downloadedTvPresenter.id);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadedTv$lambda$9(DownloadedTvPresenter downloadedTvPresenter, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadedTvPresenter.getView().hideLoadingView();
        return Unit.INSTANCE;
    }
}
