package com.movieboxpro.android.view.activity.videoplayer;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.alibaba.fastjson.JSON;
import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: compiled from: TranslateSubtitleRequest.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H\u0007JN\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bj\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005J.\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleRequest;", "", "<init>", "()V", "MODULE", "", "url", "params", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "boxType", "", CmcdConfiguration.KEY_SESSION_ID, "fromLanguage", "toLanguage", "toLanguageName", "requestBody", "Lokhttp3/RequestBody;", "isLongTimeoutUrl", "", "requestUrl", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TranslateSubtitleRequest {
    public static final TranslateSubtitleRequest INSTANCE = new TranslateSubtitleRequest();
    public static final String MODULE = "Srttrans_v4";

    private TranslateSubtitleRequest() {
    }

    @JvmStatic
    public static final String url() {
        return ApiV2EndpointConfig.moduleUrl(MODULE);
    }

    public final HashMap<String, Object> params(int boxType, String sid, String fromLanguage, String toLanguage, String toLanguageName) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(fromLanguage, "fromLanguage");
        Intrinsics.checkNotNullParameter(toLanguage, "toLanguage");
        Intrinsics.checkNotNullParameter(toLanguageName, "toLanguageName");
        HashMap<String, Object> mapHashMapOf = MapsKt.hashMapOf(TuplesKt.to(TypedValues.TransitionType.S_FROM, fromLanguage), TuplesKt.to(TypedValues.TransitionType.S_TO, toLanguage), TuplesKt.to("lang", toLanguage), TuplesKt.to("language", toLanguageName));
        if (boxType == 1) {
            mapHashMapOf.put("movie_sid", sid);
        } else {
            mapHashMapOf.put("tv_sid", sid);
        }
        return mapHashMapOf;
    }

    public final RequestBody requestBody(int boxType, String sid, String fromLanguage, String toLanguage, String toLanguageName) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(fromLanguage, "fromLanguage");
        Intrinsics.checkNotNullParameter(toLanguage, "toLanguage");
        Intrinsics.checkNotNullParameter(toLanguageName, "toLanguageName");
        RequestBody.Companion companion = RequestBody.INSTANCE;
        String jSONString = JSON.toJSONString(params(boxType, sid, fromLanguage, toLanguage, toLanguageName));
        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(...)");
        return companion.create(jSONString, MediaType.INSTANCE.parse("text/plain; charset=utf-8"));
    }

    @JvmStatic
    public static final boolean isLongTimeoutUrl(String requestUrl) {
        Intrinsics.checkNotNullParameter(requestUrl, "requestUrl");
        String str = requestUrl;
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "api/srttrans/index/", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "api/srttransv3", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "/api/Srttrans_v4", false, 2, (Object) null);
    }
}
