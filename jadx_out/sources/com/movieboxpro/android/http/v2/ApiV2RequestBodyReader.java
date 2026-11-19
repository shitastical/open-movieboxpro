package com.movieboxpro.android.http.v2;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;

/* JADX INFO: compiled from: ApiV2RequestBodyReader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\fH\u0002¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2RequestBodyReader;", "", "<init>", "()V", "read", "Lcom/movieboxpro/android/http/v2/ParsedApiBody;", "body", "Lokhttp3/RequestBody;", "readForm", "Lokhttp3/FormBody;", "readJson", "readMultipart", "Lokhttp3/MultipartBody;", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiV2RequestBodyReader {
    private static final Companion Companion = new Companion(null);
    private static final Regex NAME_PARAMETER = new Regex("(?:^|;)\\s*name=\\\"([^\\\"]*)\\\"", RegexOption.IGNORE_CASE);
    private static final Regex FILENAME_PARAMETER = new Regex("(?:^|;)\\s*filename=", RegexOption.IGNORE_CASE);

    public final ParsedApiBody read(RequestBody body) {
        if (body instanceof FormBody) {
            return readForm((FormBody) body);
        }
        if (body instanceof MultipartBody) {
            return readMultipart((MultipartBody) body);
        }
        if (body == null) {
            return new ParsedApiBody(new LinkedHashMap(), CollectionsKt.emptyList(), false);
        }
        return readJson(body);
    }

    private final ParsedApiBody readForm(FormBody body) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = body.size();
        for (int i = 0; i < size; i++) {
            linkedHashMap.put(body.name(i), body.value(i));
        }
        return new ParsedApiBody(linkedHashMap, CollectionsKt.emptyList(), false);
    }

    private final ParsedApiBody readJson(RequestBody body) throws IOException {
        LinkedHashMap linkedHashMap;
        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        String utf8 = buffer.readUtf8();
        if (StringsKt.isBlank(utf8)) {
            linkedHashMap = new LinkedHashMap();
        } else {
            Object object = JSON.parseObject(utf8, (Class<Object>) LinkedHashMap.class);
            Intrinsics.checkNotNull(object, "null cannot be cast to non-null type java.util.LinkedHashMap<kotlin.String, kotlin.Any?>");
            linkedHashMap = (LinkedHashMap) object;
        }
        return new ParsedApiBody(linkedHashMap, CollectionsKt.emptyList(), false);
    }

    private final ParsedApiBody readMultipart(MultipartBody body) throws IOException {
        String str;
        List<String> groupValues;
        String str2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        int size = body.size();
        for (int i = 0; i < size; i++) {
            MultipartBody.Part part = body.part(i);
            Headers headers = part.headers();
            if (headers != null && (str = headers.get("Content-Disposition")) != null) {
                String str3 = str;
                if (FILENAME_PARAMETER.containsMatchIn(str3)) {
                    arrayList.add(part);
                } else {
                    MatchResult matchResultFind$default = Regex.find$default(NAME_PARAMETER, str3, 0, 2, null);
                    if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str2 = groupValues.get(1)) != null) {
                        Buffer buffer = new Buffer();
                        part.body().writeTo(buffer);
                        linkedHashMap.put(str2, buffer.readUtf8());
                    }
                }
            }
        }
        return new ParsedApiBody(linkedHashMap, arrayList, true);
    }

    /* JADX INFO: compiled from: ApiV2RequestBodyReader.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2RequestBodyReader$Companion;", "", "<init>", "()V", "NAME_PARAMETER", "Lkotlin/text/Regex;", "getNAME_PARAMETER", "()Lkotlin/text/Regex;", "FILENAME_PARAMETER", "getFILENAME_PARAMETER", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Regex getNAME_PARAMETER() {
            return ApiV2RequestBodyReader.NAME_PARAMETER;
        }

        public final Regex getFILENAME_PARAMETER() {
            return ApiV2RequestBodyReader.FILENAME_PARAMETER;
        }
    }
}
