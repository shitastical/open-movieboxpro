package com.movieboxpro.android.http;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import com.movieboxpro.android.http.v2.ApiV2RequestInterceptor;
import com.movieboxpro.android.utils.RxUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* JADX INFO: compiled from: HttpUploadRequest.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B1\u0012(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005¢\u0006\u0004\b\u0006\u0010\u0007J.\u0010\u000f\u001a\u00020\u00002&\u0010\u0010\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005J\u0018\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001J,\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0004H\u0007J\r\u0010\u0018\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0019J=\u0010\u001a\u001a2\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u00040\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0018\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u00040\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005H\u0000¢\u0006\u0002\b\u001cJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u000eJ\u001a\u0010\u001f\u001a\u00020\u00002\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010!H\u0007J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040#J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040#J\u0018\u0010%\u001a\u0012\u0012\u0004\u0012\u00020'0&j\b\u0012\u0004\u0012\u00020'`(H\u0002R.\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/movieboxpro/android/http/HttpUploadRequest;", "", "data", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "<init>", "(Ljava/util/HashMap;)V", "mediaType", "uploadFile", "Ljava/io/File;", "formName", "moduleName", "mLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "addParams", "param", "addParam", "key", "value", "addBaseParams", ApiV2RequestInterceptor.MODULE, "type", IDataSource.SCHEME_FILE_TAG, "debugModuleUrl", "debugModuleUrl$app_movie_tvRelease", "debugData", "kotlin.jvm.PlatformType", "debugData$app_movie_tvRelease", "bindLifecycle", "lifecycleOwner", "executeUpload", "callback", "Lcom/movieboxpro/android/http/ResponseCallback;", "executeUploadObservable", "Lio/reactivex/Observable;", "asRequest", "buildData", "Ljava/util/ArrayList;", "Lokhttp3/MultipartBody$Part;", "Lkotlin/collections/ArrayList;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HttpUploadRequest {
    private HashMap<String, Object> data;
    private String formName;
    private LifecycleOwner mLifecycleOwner;
    private String mediaType;
    private String moduleName;
    private File uploadFile;

    /* JADX WARN: Multi-variable type inference failed */
    public HttpUploadRequest() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final HttpUploadRequest addBaseParams(String module, File file) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(file, "file");
        return addBaseParams$default(this, module, null, file, null, 10, null);
    }

    public final HttpUploadRequest addBaseParams(String module, String type, File file) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(file, "file");
        return addBaseParams$default(this, module, type, file, null, 8, null);
    }

    public final HttpUploadRequest executeUpload() {
        return executeUpload$default(this, null, 1, null);
    }

    public HttpUploadRequest(HashMap<String, Object> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.mediaType = "";
        this.formName = "";
        this.moduleName = "";
    }

    public /* synthetic */ HttpUploadRequest(HashMap map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new HashMap() : map);
    }

    public final HttpUploadRequest addParams(HashMap<String, Object> param) {
        Intrinsics.checkNotNullParameter(param, "param");
        this.data = param;
        return this;
    }

    public final HttpUploadRequest addParam(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.data.put(key, value);
        return this;
    }

    public static /* synthetic */ HttpUploadRequest addBaseParams$default(HttpUploadRequest httpUploadRequest, String str, String str2, File file, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "text/plain";
        }
        if ((i & 8) != 0) {
            str3 = IDataSource.SCHEME_FILE_TAG;
        }
        return httpUploadRequest.addBaseParams(str, str2, file, str3);
    }

    public final HttpUploadRequest addBaseParams(String module, String type, File file, String formName) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(formName, "formName");
        this.moduleName = module;
        this.mediaType = type;
        this.uploadFile = file;
        this.formName = formName;
        return this;
    }

    public final String debugModuleUrl$app_movie_tvRelease() {
        return ApiV2EndpointConfig.moduleUrl(this.moduleName);
    }

    public final HashMap<String, Object> debugData$app_movie_tvRelease() {
        return new HashMap<>(this.data);
    }

    public final HttpUploadRequest bindLifecycle(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.mLifecycleOwner = lifecycleOwner;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HttpUploadRequest executeUpload$default(HttpUploadRequest httpUploadRequest, ResponseCallback responseCallback, int i, Object obj) {
        if ((i & 1) != 0) {
            responseCallback = null;
        }
        return httpUploadRequest.executeUpload(responseCallback);
    }

    public final HttpUploadRequest executeUpload(final ResponseCallback<String> callback) {
        MultipartBody.Part partCreateFormData;
        if (this.mLifecycleOwner == null) {
            throw new RuntimeException("not bind LifecycleOwner");
        }
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse(this.mediaType);
        File file = this.uploadFile;
        Intrinsics.checkNotNull(file);
        RequestBody requestBodyCreate = companion.create(mediaType, file);
        try {
            MultipartBody.Part.Companion companion2 = MultipartBody.Part.INSTANCE;
            String str = this.formName;
            File file2 = this.uploadFile;
            Intrinsics.checkNotNull(file2);
            partCreateFormData = companion2.createFormData(str, URLEncoder.encode(file2.getName(), "UTF-8"), requestBodyCreate);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            partCreateFormData = null;
        }
        if (partCreateFormData != null) {
            ((ObservableSubscribeProxy) Http.getService().Upload_Request(ApiV2EndpointConfig.moduleUrl(this.moduleName), buildData(), partCreateFormData).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.http.HttpUploadRequest.executeUpload.1
                @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    super.onSubscribe(d);
                    ResponseCallback<String> responseCallback = callback;
                    if (responseCallback != null) {
                        responseCallback.onStart();
                    }
                }

                @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
                public void onNext(String s) {
                    Intrinsics.checkNotNullParameter(s, "s");
                    super.onNext(s);
                    ResponseCallback<String> responseCallback = callback;
                    if (responseCallback != null) {
                        responseCallback.onSuccess(s);
                    }
                }

                @Override // com.movieboxpro.android.base.BaseObserver
                public void onError(ApiException e2) {
                    Intrinsics.checkNotNullParameter(e2, "e");
                    ResponseCallback<String> responseCallback = callback;
                    if (responseCallback != null) {
                        responseCallback.onFailure(e2.getCode(), e2.getMessage());
                    }
                }
            });
        }
        return this;
    }

    public final Observable<String> executeUploadObservable() {
        MultipartBody.Part partCreateFormData;
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse(this.mediaType);
        File file = this.uploadFile;
        Intrinsics.checkNotNull(file);
        RequestBody requestBodyCreate = companion.create(mediaType, file);
        try {
            MultipartBody.Part.Companion companion2 = MultipartBody.Part.INSTANCE;
            String str = this.formName;
            File file2 = this.uploadFile;
            Intrinsics.checkNotNull(file2);
            partCreateFormData = companion2.createFormData(str, URLEncoder.encode(file2.getName(), "UTF-8"), requestBodyCreate);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            partCreateFormData = null;
        }
        Observable observableCompose = Http.getService().Upload_Request(ApiV2EndpointConfig.moduleUrl(this.moduleName), buildData(), partCreateFormData).compose(RxUtils.rxTranslateMsg());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return observableCompose;
    }

    public final Observable<String> asRequest() {
        MultipartBody.Part partCreateFormData;
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse(this.mediaType);
        File file = this.uploadFile;
        Intrinsics.checkNotNull(file);
        RequestBody requestBodyCreate = companion.create(mediaType, file);
        try {
            MultipartBody.Part.Companion companion2 = MultipartBody.Part.INSTANCE;
            String str = this.formName;
            File file2 = this.uploadFile;
            Intrinsics.checkNotNull(file2);
            partCreateFormData = companion2.createFormData(str, URLEncoder.encode(file2.getName(), "UTF-8"), requestBodyCreate);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            partCreateFormData = null;
        }
        Observable<String> observableUpload_Request = Http.getService().Upload_Request(ApiV2EndpointConfig.moduleUrl(this.moduleName), buildData(), partCreateFormData);
        Intrinsics.checkNotNullExpressionValue(observableUpload_Request, "Upload_Request(...)");
        return observableUpload_Request;
    }

    private final ArrayList<MultipartBody.Part> buildData() {
        ArrayList<MultipartBody.Part> arrayList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : this.data.entrySet()) {
            arrayList.add(MultipartBody.Part.INSTANCE.createFormData(entry.getKey(), String.valueOf(entry.getValue())));
        }
        return arrayList;
    }
}
