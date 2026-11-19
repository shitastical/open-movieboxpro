package com.movieboxpro.android.view.activity;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.livedata.DownloadLiveData;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import me.jessyan.autosize.utils.LogUtils;

/* JADX INFO: compiled from: DownloadingPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\n\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadingPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/DownloadingContract$View;", "Lcom/movieboxpro/android/view/activity/DownloadingContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "getDownloadingFiles", "", "deleteDownloadingFiles", "list", "", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadingPresenter extends BasePresenter<DownloadingContract.View> implements DownloadingContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadingPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.DownloadingContract.Presenter
    public void getDownloadingFiles() {
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingPresenter.getDownloadingFiles$lambda$1((String) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadingPresenter.getDownloadingFiles$lambda$2(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingPresenter.getDownloadingFiles$lambda$3((ApiException) obj);
            }
        }, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingPresenter.getDownloadingFiles$lambda$4(this.f$0, (List) obj);
            }
        }, 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadingFiles$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadingFiles$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        List<DownloadFile> downloadingFiles = DownloadFileHelper.INSTANCE.getInstance().getDownloadingFiles();
        for (DownloadFile downloadFile : downloadingFiles) {
            if (CommonUtils.isFFmpegDownload(downloadFile.getPath())) {
                downloadFile.setFfmpeg(true);
            }
        }
        return downloadingFiles;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getDownloadingFiles$lambda$4(DownloadingPresenter downloadingPresenter, List list) {
        DownloadingContract.View view = downloadingPresenter.getView();
        Intrinsics.checkNotNull(list);
        view.showDownloadingFiles(list);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getDownloadingFiles$lambda$3(ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        LogUtils.d("");
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.view.activity.DownloadingContract.Presenter
    public void deleteDownloadingFiles(List<? extends DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Observable observableJust = Observable.just(list);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingPresenter.deleteDownloadingFiles$lambda$6(this.f$0, (List) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadingPresenter.deleteDownloadingFiles$lambda$7(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingPresenter.deleteDownloadingFiles$lambda$8(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingPresenter.deleteDownloadingFiles$lambda$9(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingPresenter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingPresenter.deleteDownloadingFiles$lambda$10(this.f$0, (Unit) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadingFiles$lambda$7(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadingFiles$lambda$6(DownloadingPresenter downloadingPresenter, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            DownloadFile downloadFile = (DownloadFile) it2.next();
            if (CommonUtils.isFFmpegDownload(downloadFile.getPath())) {
                FileUtils.deleteDir(new File(downloadFile.getPath()));
                DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(downloadFile.getDownloadId());
                DownloadLiveData.INSTANCE.getInstance().cancelDownload(downloadFile.getPath(), downloadFile.getDownloadId());
            } else {
                new File(downloadFile.getPath(), downloadFile.getFileName()).delete();
                downloadingPresenter.getView().deleteDownload(downloadFile);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadingFiles$lambda$9(DownloadingPresenter downloadingPresenter, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadingPresenter.getView().showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadingFiles$lambda$10(DownloadingPresenter downloadingPresenter, Unit unit) {
        downloadingPresenter.getView().hideLoadingView();
        downloadingPresenter.getView().deleteComplete();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDownloadingFiles$lambda$8(DownloadingPresenter downloadingPresenter, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadingPresenter.getView().hideLoadingView();
        return Unit.INSTANCE;
    }
}
