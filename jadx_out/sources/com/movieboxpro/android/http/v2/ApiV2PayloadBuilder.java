package com.movieboxpro.android.http.v2;

import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.tvprovider.media.tv.TvContractCompat;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApiV2PayloadBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJP\u0010\t\u001a\"\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nj\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\f2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J>\u0010\u0012\u001a\u00020\u0013*\"\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nj\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\f2\u0006\u0010\u0014\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2PayloadBuilder;", "", "environment", "Lcom/movieboxpro/android/http/v2/ApiV2Environment;", "clockSeconds", "Lkotlin/Function0;", "", "<init>", "(Lcom/movieboxpro/android/http/v2/ApiV2Environment;Lkotlin/jvm/functions/Function0;)V", "build", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "business", "", ApiV2RequestInterceptor.MODULE, "snapshot", "Lcom/movieboxpro/android/http/v2/ApiV2EnvironmentSnapshot;", "addIfAbsent", "", "key", "value", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiV2PayloadBuilder {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long EXPIRY_SECONDS = 43200;
    private final Function0<Long> clockSeconds;
    private final ApiV2Environment environment;

    /* JADX WARN: Multi-variable type inference failed */
    public ApiV2PayloadBuilder() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ApiV2PayloadBuilder(ApiV2Environment environment, Function0<Long> clockSeconds) {
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(clockSeconds, "clockSeconds");
        this.environment = environment;
        this.clockSeconds = clockSeconds;
    }

    public /* synthetic */ ApiV2PayloadBuilder(TvApiV2Environment tvApiV2Environment, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TvApiV2Environment.INSTANCE : tvApiV2Environment, (i & 2) != 0 ? new Function0() { // from class: com.movieboxpro.android.http.v2.ApiV2PayloadBuilder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(ApiV2PayloadBuilder._init_$lambda$0());
            }
        } : function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long _init_$lambda$0() {
        return System.currentTimeMillis() / 1000;
    }

    public static /* synthetic */ LinkedHashMap build$default(ApiV2PayloadBuilder apiV2PayloadBuilder, Map map, String str, ApiV2EnvironmentSnapshot apiV2EnvironmentSnapshot, int i, Object obj) {
        if ((i & 4) != 0) {
            apiV2EnvironmentSnapshot = apiV2PayloadBuilder.environment.snapshot();
        }
        return apiV2PayloadBuilder.build(map, str, apiV2EnvironmentSnapshot);
    }

    public final LinkedHashMap<String, Object> build(Map<String, ? extends Object> business, String module, ApiV2EnvironmentSnapshot snapshot) {
        String uidV2;
        Intrinsics.checkNotNullParameter(business, "business");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(business);
        linkedHashMap.remove(ApiV2RequestInterceptor.MODULE);
        addIfAbsent(linkedHashMap, "app_version", snapshot.getAppVersion());
        addIfAbsent(linkedHashMap, TvContractCompat.PARAM_CHANNEL, snapshot.getChannel());
        addIfAbsent(linkedHashMap, "platform", ApiV2RequestInterceptor.PLATFORM);
        addIfAbsent(linkedHashMap, "lang", snapshot.getLang());
        addIfAbsent(linkedHashMap, "open_udid", snapshot.getOpenUdid());
        addIfAbsent(linkedHashMap, "device_model", snapshot.getDeviceModel());
        addIfAbsent(linkedHashMap, "device_name", snapshot.getDeviceName());
        addIfAbsent(linkedHashMap, "platform_version", snapshot.getPlatformVersion());
        addIfAbsent(linkedHashMap, "appid", snapshot.getPackageName());
        addIfAbsent(linkedHashMap, "childmode", snapshot.getChildMode() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : "0");
        if (snapshot.getLoggedIn() && (uidV2 = snapshot.getUidV2()) != null && uidV2.length() != 0) {
            addIfAbsent(linkedHashMap, "uid", snapshot.getUidV2());
        }
        addIfAbsent(linkedHashMap, ApiEncryptV2.EXPIRED_DATE, Long.valueOf(this.clockSeconds.invoke().longValue() + 43200));
        return linkedHashMap;
    }

    private final void addIfAbsent(LinkedHashMap<String, Object> linkedHashMap, String str, Object obj) {
        if (linkedHashMap.containsKey(str) || obj == null) {
            return;
        }
        linkedHashMap.put(str, obj);
    }

    /* JADX INFO: compiled from: ApiV2PayloadBuilder.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2PayloadBuilder$Companion;", "", "<init>", "()V", "EXPIRY_SECONDS", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
