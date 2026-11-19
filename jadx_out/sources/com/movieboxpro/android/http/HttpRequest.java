package com.movieboxpro.android.http;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import com.movieboxpro.android.http.v2.ApiV2RequestInterceptor;
import com.movieboxpro.android.utils.RxUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: compiled from: HttpRequest.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 +2\u00020\u0001:\u0001+BK\u0012(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bBE\b\u0016\u0012(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\fJ\r\u0010\u0011\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0014J\u0018\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001J;\u0010\u0015\u001a\u00020\u00002.\u0010\u0018\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001a0\u0019\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001a¢\u0006\u0002\u0010\u001bJ.\u0010\u001c\u001a\u00020\u00002&\u0010\u001d\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005J \u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H 0\u001f\"\u0004\b\u0000\u0010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H 0\"J&\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0$0\u001f\"\u0004\b\u0000\u0010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H 0\"J\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u001fJ\u0006\u0010&\u001a\u00020'J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040)J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040)H\u0002R.\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/movieboxpro/android/http/HttpRequest;", "", "paramMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", ApiV2RequestInterceptor.MODULE, "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "url", "<init>", "(Ljava/util/HashMap;Ljava/lang/String;Landroidx/lifecycle/LifecycleOwner;Ljava/lang/String;)V", "(Ljava/util/HashMap;Ljava/lang/String;Landroidx/lifecycle/LifecycleOwner;)V", "getModule", "()Ljava/lang/String;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "debugUrl", "debugUrl$app_movie_tvRelease", "debugParamsJson", "debugParamsJson$app_movie_tvRelease", "param", "key", "value", "pair", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lcom/movieboxpro/android/http/HttpRequest;", "params", "map", "asBean", "Lcom/uber/autodispose/ObservableSubscribeProxy;", ExifInterface.GPS_DIRECTION_TRUE, "type", "Ljava/lang/Class;", "asList", "", "asMsg", "asMsgSubcribe", "", "asRequest", "Lio/reactivex/Observable;", "getRequest", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HttpRequest {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final LifecycleOwner lifecycleOwner;
    private final String module;
    private HashMap<String, Object> paramMap;
    private final String url;

    @JvmStatic
    public static final HttpRequest post(LifecycleOwner lifecycleOwner, String str) {
        return INSTANCE.post(lifecycleOwner, str);
    }

    @JvmStatic
    public static final HttpRequest post(String str) {
        return INSTANCE.post(str);
    }

    @JvmStatic
    public static final HttpRequest post(HashMap<String, Object> map, LifecycleOwner lifecycleOwner) {
        return INSTANCE.post(map, lifecycleOwner);
    }

    public HttpRequest(HashMap<String, Object> paramMap, String module, LifecycleOwner lifecycleOwner, String url) {
        Intrinsics.checkNotNullParameter(paramMap, "paramMap");
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(url, "url");
        this.paramMap = paramMap;
        this.module = module;
        this.lifecycleOwner = lifecycleOwner;
        this.url = url;
        paramMap.remove(ApiV2RequestInterceptor.MODULE);
    }

    public /* synthetic */ HttpRequest(HashMap map, String str, LifecycleOwner lifecycleOwner, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new HashMap() : map, str, lifecycleOwner, str2);
    }

    public final String getModule() {
        return this.module;
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public /* synthetic */ HttpRequest(HashMap map, String str, LifecycleOwner lifecycleOwner, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new HashMap() : map, str, lifecycleOwner);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpRequest(HashMap<String, Object> paramMap, String module, LifecycleOwner lifecycleOwner) {
        this(paramMap, module, lifecycleOwner, ApiV2EndpointConfig.moduleUrl(module));
        Intrinsics.checkNotNullParameter(paramMap, "paramMap");
        Intrinsics.checkNotNullParameter(module, "module");
    }

    /* JADX INFO: compiled from: HttpRequest.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007J<\u0010\u0004\u001a\u00020\u00052&\u0010\n\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/http/HttpRequest$Companion;", "", "<init>", "()V", "post", "Lcom/movieboxpro/android/http/HttpRequest;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", ApiV2RequestInterceptor.MODULE, "", "params", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final HttpRequest post(LifecycleOwner lifecycleOwner, String module) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            Intrinsics.checkNotNullParameter(module, "module");
            return new HttpRequest(null, module, lifecycleOwner, 1, null);
        }

        @JvmStatic
        public final HttpRequest post(String module) {
            Intrinsics.checkNotNullParameter(module, "module");
            return new HttpRequest(null, module, null, 1, null);
        }

        public static /* synthetic */ HttpRequest post$default(Companion companion, HashMap map, LifecycleOwner lifecycleOwner, int i, Object obj) {
            if ((i & 2) != 0) {
                lifecycleOwner = null;
            }
            return companion.post((HashMap<String, Object>) map, lifecycleOwner);
        }

        @JvmStatic
        public final HttpRequest post(HashMap<String, Object> params, LifecycleOwner lifecycleOwner) {
            String string;
            Intrinsics.checkNotNullParameter(params, "params");
            HashMap map = new HashMap(params);
            Object objRemove = map.remove(ApiV2RequestInterceptor.MODULE);
            String string2 = (objRemove == null || (string = objRemove.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
            if (string2 == null) {
                string2 = "";
            }
            if (string2.length() <= 0) {
                throw new IllegalArgumentException("Movie API module must be a URL path segment".toString());
            }
            return new HttpRequest(map, string2, lifecycleOwner);
        }
    }

    /* JADX INFO: renamed from: debugUrl$app_movie_tvRelease, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final String debugParamsJson$app_movie_tvRelease() {
        String jSONString = JSON.toJSONString(this.paramMap);
        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(...)");
        return jSONString;
    }

    public final HttpRequest param(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.paramMap.put(key, value);
        return this;
    }

    public final HttpRequest param(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.paramMap.put(key, value);
        return this;
    }

    public final HttpRequest params(HashMap<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.paramMap.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public final <T> ObservableSubscribeProxy<T> asBean(Class<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Object objAs = getRequest().compose(RxUtils.rxTranslate2Bean(type)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        return (ObservableSubscribeProxy) objAs;
    }

    public final <T> ObservableSubscribeProxy<List<T>> asList(Class<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Object objAs = getRequest().compose(RxUtils.rxTranslate2List(type)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        return (ObservableSubscribeProxy) objAs;
    }

    public final ObservableSubscribeProxy<String> asMsg() {
        Object objAs = getRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        return (ObservableSubscribeProxy) objAs;
    }

    public final void asMsgSubcribe() {
        ((ObservableSubscribeProxy) getRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.http.HttpRequest.asMsgSubcribe.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
            }
        });
    }

    public final Observable<String> asRequest() {
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("text/plain; charset=utf-8");
        String jSONString = JSON.toJSONString(this.paramMap);
        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(...)");
        Observable<String> observableRequest = Http.getService().request(this.url, companion.create(mediaType, jSONString));
        Intrinsics.checkNotNullExpressionValue(observableRequest, "request(...)");
        return observableRequest;
    }

    private final Observable<String> getRequest() {
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("text/plain; charset=utf-8");
        String jSONString = JSON.toJSONString(this.paramMap);
        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(...)");
        Observable<String> observableRequest = Http.getService().request(this.url, companion.create(mediaType, jSONString));
        Intrinsics.checkNotNullExpressionValue(observableRequest, "request(...)");
        return observableRequest;
    }

    public final HttpRequest param(Pair<String, ? extends Object>... pair) {
        Intrinsics.checkNotNullParameter(pair, "pair");
        for (Pair<String, ? extends Object> pair2 : pair) {
            this.paramMap.put(pair2.getFirst(), pair2.getSecond());
        }
        return this;
    }
}
