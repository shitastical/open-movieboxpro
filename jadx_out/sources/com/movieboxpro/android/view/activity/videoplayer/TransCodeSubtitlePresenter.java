package com.movieboxpro.android.view.activity.videoplayer;

import androidx.lifecycle.LifecycleOwner;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.dl7.player.utils.SRT;
import com.dueeeke.model.EncodeModel;
import com.dueeeke.model.ExtrModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.http.v2.ApiEncryptV2;
import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.ResponseUploadExtraSubtitle;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.HttpHelperKt;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: TransCodeSubtitlePresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J3\u0010\u0013\u001a\u00020\u00142\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000fH\u0016J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\tH\u0016J,\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u001d2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000fH\u0002J$\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u001d2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\tH\u0002J$\u0010!\u001a\u00020\u00142\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00140$J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010\u001a\u001a\u00020\u000fH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitlePresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleContract$View;", "Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "subtitleFile", "Ljava/io/File;", DealWatchNextService.SEASON, "", "Ljava/lang/Integer;", DealWatchNextService.EPISODE, "videoId", "", "extrModel", "Lcom/dueeeke/model/ExtrModel;", "subtitleName", "initValue", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/dueeeke/model/ExtrModel;)V", "firstLoadData", "url", "fileName", "transCode", "code", IDataSource.SCHEME_FILE_TAG, "transCodeWithUrlObservable", "Lio/reactivex/Observable;", "", "Lcom/dl7/player/utils/SRT;", "transCodeObservable", "uploadSubtitle", FirebaseAnalytics.Param.CONTENT, FirebaseAnalytics.Param.SUCCESS, "Lkotlin/Function1;", "buildData", "", "Lokhttp3/MultipartBody$Part;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TransCodeSubtitlePresenter extends BasePresenter<TransCodeSubtitleContract.View> implements TransCodeSubtitleContract.Presenter {
    private Integer episode;
    private ExtrModel extrModel;
    private Integer season;
    private File subtitleFile;
    private String subtitleName;
    private String videoId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransCodeSubtitlePresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.season = 0;
        this.episode = 0;
        this.subtitleName = "";
    }

    public final void initValue(Integer season, Integer episode, String videoId, ExtrModel extrModel) {
        this.season = season;
        this.episode = episode;
        this.videoId = videoId;
        this.extrModel = extrModel;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleContract.Presenter
    public void firstLoadData(String url, String fileName) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Observable<R> observableCompose = Http.getService().Encoding_list("Encoding_list").compose(RxUtils.rxTranslate2Bean(HashMap.class));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.firstLoadData$lambda$1((HashMap) obj);
            }
        };
        ((ObservableSubscribeProxy) Observable.zip(observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TransCodeSubtitlePresenter.firstLoadData$lambda$2(function1, obj);
            }
        }), transCodeWithUrlObservable("UTF-8", url, fileName), new BiFunction() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda10
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return TransCodeSubtitlePresenter.firstLoadData$lambda$3((ArrayList) obj, (List) obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<Pair<? extends ArrayList<EncodeModel>, ? extends List<SRT>>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter.firstLoadData.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TransCodeSubtitlePresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<? extends ArrayList<EncodeModel>, ? extends List<SRT>> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TransCodeSubtitlePresenter.this.getView().showLanguages(model.getFirst(), model.getSecond(), TransCodeSubtitlePresenter.this.subtitleFile);
                TransCodeSubtitlePresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                TransCodeSubtitlePresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("load error:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList firstLoadData$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ArrayList) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList firstLoadData$lambda$1(HashMap hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "hashMap");
        HashMap map = (HashMap) JSON.parseObject(JSON.toJSONString(hashMap.get("list")), HashMap.class);
        ArrayList arrayList = new ArrayList();
        Intrinsics.checkNotNull(map);
        for (Map.Entry entry : map.entrySet()) {
            EncodeModel encodeModel = new EncodeModel();
            encodeModel.setLanguage(entry.getKey().toString());
            encodeModel.setCode(JSON.parseArray(entry.getValue().toString(), String.class));
            arrayList.add(encodeModel);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair firstLoadData$lambda$3(ArrayList t1, List t2) {
        Intrinsics.checkNotNullParameter(t1, "t1");
        Intrinsics.checkNotNullParameter(t2, "t2");
        return new Pair(t1, t2);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleContract.Presenter
    public void transCode(String code, File file) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(file, "file");
        ((ObservableSubscribeProxy) transCodeObservable(code, file).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<SRT>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter.transCode.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TransCodeSubtitlePresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<SRT> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TransCodeSubtitlePresenter.this.getView().showSubtitle(model);
                TransCodeSubtitlePresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                TransCodeSubtitlePresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("load error:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    private final Observable<List<SRT>> transCodeWithUrlObservable(final String code, String url, final String fileName) {
        Observable<ResponseBody> observableDownload = HttpHelperKt.getApiService().download(url);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.transCodeWithUrlObservable$lambda$4(fileName, this, code, (ResponseBody) obj);
            }
        };
        Observable observableFlatMap = observableDownload.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TransCodeSubtitlePresenter.transCodeWithUrlObservable$lambda$5(function1, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        return observableFlatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource transCodeWithUrlObservable$lambda$5(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource transCodeWithUrlObservable$lambda$4(String str, TransCodeSubtitlePresenter transCodeSubtitlePresenter, String str2, ResponseBody it) throws Throwable {
        Intrinsics.checkNotNullParameter(it, "it");
        File file = new File(Constant.DIR_DOWNLOAD, str);
        FileUtils.writeFileFromIS(file, it.byteStream(), false);
        transCodeSubtitlePresenter.subtitleFile = file;
        return transCodeSubtitlePresenter.transCodeObservable(str2, file);
    }

    private final Observable<List<SRT>> transCodeObservable(String code, File file) {
        MultipartBody.Part partCreateFormData;
        try {
            partCreateFormData = MultipartBody.Part.INSTANCE.createFormData("zip_file", URLEncoder.encode(file.getName(), "UTF-8"), RequestBody.INSTANCE.create(MediaType.INSTANCE.parse("application/zip"), file));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            partCreateFormData = null;
        }
        Observable<String> observableSrt_convert_encoding = Http.getService().Srt_convert_encoding(ApiV2EndpointConfig.moduleUrl("Srt_convert_encoding"), buildData(code), partCreateFormData);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.transCodeObservable$lambda$6((String) obj);
            }
        };
        Observable<R> map = observableSrt_convert_encoding.map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TransCodeSubtitlePresenter.transCodeObservable$lambda$7(function1, obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.transCodeObservable$lambda$8(this.f$0, (BaseResponse) obj);
            }
        };
        Observable<List<SRT>> map2 = map.map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TransCodeSubtitlePresenter.transCodeObservable$lambda$9(function2, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseResponse transCodeObservable$lambda$7(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (BaseResponse) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseResponse transCodeObservable$lambda$6(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        MLog.d("JSON", s);
        return (BaseResponse) JSON.parseObject(s, RxUtils.buildType(BaseResponse.class, HashMap.class), new Feature[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List transCodeObservable$lambda$9(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List transCodeObservable$lambda$8(TransCodeSubtitlePresenter transCodeSubtitlePresenter, BaseResponse response) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getCode() == 1) {
            HashMap map = (HashMap) response.getData();
            String str = (String) map.get("srt_content");
            String str2 = (String) map.get("srt_name");
            if (str2 != null) {
                transCodeSubtitlePresenter.subtitleName = str2;
            }
            if (str != null) {
                transCodeSubtitlePresenter.getView().setSubtitleContent(str);
                arrayList = new ArrayList();
                SrtParser.parseSRTContentSrt(str, arrayList);
            } else {
                arrayList = new ArrayList();
            }
        } else {
            arrayList = new ArrayList();
        }
        return arrayList;
    }

    public final void uploadSubtitle(final String content, final Function1<? super String, Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        String str = content;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        Observable observableJust = Observable.just(content);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.uploadSubtitle$lambda$10(this.f$0, content, (String) obj);
            }
        };
        Observable map = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda12
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TransCodeSubtitlePresenter.uploadSubtitle$lambda$11(function1, obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.uploadSubtitle$lambda$12(this.f$0, (File) obj);
            }
        };
        Observable observableFlatMap = map.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda14
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TransCodeSubtitlePresenter.uploadSubtitle$lambda$13(function2, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableFlatMap, mLifecycleOwner), new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.uploadSubtitle$lambda$14(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.uploadSubtitle$lambda$15(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitlePresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitlePresenter.uploadSubtitle$lambda$16(this.f$0, success, (ResponseUploadExtraSubtitle) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final File uploadSubtitle$lambda$11(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (File) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final File uploadSubtitle$lambda$10(TransCodeSubtitlePresenter transCodeSubtitlePresenter, String str, String it) throws Throwable {
        Intrinsics.checkNotNullParameter(it, "it");
        File file = new File(Constant.DIR_TRANS_CODE_SUBTITLE + "/" + transCodeSubtitlePresenter.subtitleName);
        FileUtils.writeFileFromString(file, str, false);
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource uploadSubtitle$lambda$13(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ObservableSource uploadSubtitle$lambda$12(TransCodeSubtitlePresenter transCodeSubtitlePresenter, File it) {
        String subFormat;
        String iso639;
        Intrinsics.checkNotNullParameter(it, "it");
        HttpUploadRequest httpUploadRequest = new HttpUploadRequest(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0);
        Integer num = transCodeSubtitlePresenter.season;
        HttpUploadRequest httpUploadRequestAddBaseParams = httpUploadRequest.addBaseParams((num != null && num.intValue() == 0) ? "Upload_movie_srt_user" : "Upload_tv_srt_user", "text/plain", it, IDataSource.SCHEME_FILE_TAG);
        String str = "";
        HttpUploadRequest httpUploadRequestAddParam = httpUploadRequestAddBaseParams.addParam(CmcdConfiguration.KEY_SESSION_ID, "").addParam("tid", transCodeSubtitlePresenter.videoId).addParam("mid", transCodeSubtitlePresenter.videoId).addParam(DealWatchNextService.SEASON, transCodeSubtitlePresenter.season).addParam(DealWatchNextService.EPISODE, transCodeSubtitlePresenter.episode);
        ExtrModel extrModel = transCodeSubtitlePresenter.extrModel;
        HttpUploadRequest httpUploadRequestAddParam2 = httpUploadRequestAddParam.addParam("language", extrModel != null ? extrModel.getLanguageName() : null);
        ExtrModel extrModel2 = transCodeSubtitlePresenter.extrModel;
        if (extrModel2 == null || (subFormat = extrModel2.getSubFormat()) == null) {
            subFormat = "";
        }
        HttpUploadRequest httpUploadRequestAddParam3 = httpUploadRequestAddParam2.addParam(IjkMediaMeta.IJKM_KEY_FORMAT, subFormat);
        ExtrModel extrModel3 = transCodeSubtitlePresenter.extrModel;
        if (extrModel3 != null && (iso639 = extrModel3.getISO639()) != null) {
            str = iso639;
        }
        ObservableSource observableSourceCompose = httpUploadRequestAddParam3.addParam("lang", str).asRequest().compose(RxUtils.rxTranslate2Bean(ResponseUploadExtraSubtitle.class));
        Intrinsics.checkNotNullExpressionValue(observableSourceCompose, "compose(...)");
        return observableSourceCompose;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit uploadSubtitle$lambda$15(TransCodeSubtitlePresenter transCodeSubtitlePresenter, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        transCodeSubtitlePresenter.getView().showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit uploadSubtitle$lambda$16(TransCodeSubtitlePresenter transCodeSubtitlePresenter, Function1 function1, ResponseUploadExtraSubtitle responseUploadExtraSubtitle) {
        transCodeSubtitlePresenter.getView().hideLoadingView();
        String str = responseUploadExtraSubtitle.getSrt_info().sid;
        if (str == null) {
            str = "";
        }
        function1.invoke(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit uploadSubtitle$lambda$14(TransCodeSubtitlePresenter transCodeSubtitlePresenter, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        transCodeSubtitlePresenter.getView().hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final List<MultipartBody.Part> buildData(String code) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("encoding", code);
        jSONObject.put("app_version", App.versionName);
        jSONObject.put("uid", App.getUserData().uid_v2);
        jSONObject.put("token", App.deviceToken);
        jSONObject.put("platform", "android");
        jSONObject.put(ApiEncryptV2.EXPIRED_DATE, Long.valueOf((TimeUtils.getCurrentTime() / ((long) 1000)) + ((long) 43200)));
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Object> entry : jSONObject.entrySet()) {
            MultipartBody.Part.Companion companion = MultipartBody.Part.INSTANCE;
            String key = entry.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "<get-key>(...)");
            arrayList.add(companion.createFormData(key, entry.getValue().toString()));
        }
        return arrayList;
    }
}
