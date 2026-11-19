package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.hjq.gson.factory.GsonFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GsonExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a$\u0010\u0006\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\f\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b\u001a%\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00070\f\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\f\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u000e2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u0010\"\u00020\tH\u0086\b¢\u0006\u0002\u0010\u0011\u001a.\u0010\u0006\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u000e2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u0010\"\u00020\tH\u0086\b¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u00020\u0014\"\u0006\b\u0000\u0010\u0007\u0018\u0001H\u0086\b\u001a,\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00070\u0016\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b¢\u0006\u0002\u0010\n\u001a;\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\f0\u0016\"\u0004\b\u0000\u0010\u0007*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0019¢\u0006\u0002\u0010\u001a\u001a2\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\f0\u0016\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b¢\u0006\u0002\u0010\n\u001a,\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00070\u0016\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0086\b¢\u0006\u0002\u0010\u001d\u001a2\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\f0\u0016\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0086\b¢\u0006\u0002\u0010\u001d\u001a\u001a\u0010\u001e\u001a\u00020\u001f*\u00020\u00012\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006$"}, d2 = {"GSON", "Lcom/google/gson/Gson;", "getGSON", "()Lcom/google/gson/Gson;", "GSON$delegate", "Lkotlin/Lazy;", "toModel", ExifInterface.GPS_DIRECTION_TRUE, "json", "", "(Lcom/google/gson/Gson;Ljava/lang/String;)Ljava/lang/Object;", "toList", "", "parseList", "Lcom/google/gson/JsonObject;", "name", "", "(Lcom/google/gson/JsonObject;[Ljava/lang/String;)Ljava/util/List;", "(Lcom/google/gson/JsonObject;[Ljava/lang/String;)Ljava/lang/Object;", "genericType", "Ljava/lang/reflect/Type;", "fromJsonObject", "Lkotlin/Result;", "fromJsonArray", "clazz", "Ljava/lang/Class;", "(Lcom/google/gson/Gson;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "inputStream", "Ljava/io/InputStream;", "(Lcom/google/gson/Gson;Ljava/io/InputStream;)Ljava/lang/Object;", "writeToOutputStream", "", "out", "Ljava/io/OutputStream;", "any", "", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class GsonExtensionsKt {
    private static final Lazy GSON$delegate = LazyKt.lazy(new Function0() { // from class: com.movieboxpro.android.utils.GsonExtensionsKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return GsonExtensionsKt.GSON_delegate$lambda$0();
        }
    });

    public static final Gson getGSON() {
        Object value = GSON$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Gson) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Gson GSON_delegate$lambda$0() {
        return GsonFactory.newGsonBuilder().registerTypeAdapter(String.class, new StringJsonDeserializer()).registerTypeAdapter(HashMap.class, new DataTypeAdapter()).disableHtmlEscaping().setPrettyPrinting().create();
    }

    public static final /* synthetic */ <T> T toModel(Gson gson, String str) {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        Gson gson2 = getGSON();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) gson2.fromJson(str, (Class) Object.class);
    }

    public static final /* synthetic */ <T> List<T> toList(Gson gson, String str) {
        Object objM1917constructorimpl;
        Intrinsics.checkNotNullParameter(gson, "<this>");
        Gson gson2 = getGSON();
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Object objFromJson = gson2.fromJson(str, new ParameterizedTypeImpl(Object.class));
            objM1917constructorimpl = Result.m1917constructorimpl(objFromJson instanceof List ? (List) objFromJson : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM1917constructorimpl = Result.m1917constructorimpl(ResultKt.createFailure(th));
        }
        List<T> list = (List) (Result.m1923isFailureimpl(objM1917constructorimpl) ? null : objM1917constructorimpl);
        return list != null ? list : new ArrayList();
    }

    public static final /* synthetic */ <T> List<T> parseList(Gson gson, String str) {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Object objFromJson = gson.fromJson(str, new ParameterizedTypeImpl(Object.class));
        List<T> list = objFromJson instanceof List ? (List) objFromJson : null;
        return list != null ? list : new ArrayList();
    }

    public static final /* synthetic */ <T> Type genericType() {
        Intrinsics.needClassReification();
        Type type = new TypeToken<T>() { // from class: com.movieboxpro.android.utils.GsonExtensionsKt.genericType.1
        }.getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        return type;
    }

    public static final /* synthetic */ <T> Object fromJsonObject(Gson gson, String str) {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.needClassReification();
            Type type = new TypeToken<T>() { // from class: com.movieboxpro.android.utils.GsonExtensionsKt$fromJsonObject$lambda$3$$inlined$genericType$1
            }.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            Object objFromJson = gson.fromJson(str, type);
            Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
            Object obj = objFromJson;
            return Result.m1917constructorimpl(objFromJson);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m1917constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static final <T> Object fromJsonArray(Gson gson, String str, Class<T> clazz) {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        try {
            Result.Companion companion = Result.INSTANCE;
            Object objFromJson = gson.fromJson(str, new ParameterizedTypeImpl(clazz));
            return Result.m1917constructorimpl(objFromJson instanceof List ? (List) objFromJson : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m1917constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static final /* synthetic */ <T> Object fromJsonArray(Gson gson, String str) {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Object objFromJson = gson.fromJson(str, new ParameterizedTypeImpl(Object.class));
            return Result.m1917constructorimpl(objFromJson instanceof List ? (List) objFromJson : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m1917constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static final /* synthetic */ <T> Object fromJsonObject(Gson gson, InputStream inputStream) {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            Intrinsics.needClassReification();
            Type type = new TypeToken<T>() { // from class: com.movieboxpro.android.utils.GsonExtensionsKt$fromJsonObject$lambda$6$$inlined$genericType$1
            }.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            Object objFromJson = gson.fromJson(inputStreamReader, type);
            Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
            Object obj = objFromJson;
            return Result.m1917constructorimpl(objFromJson);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m1917constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static final /* synthetic */ <T> Object fromJsonArray(Gson gson, InputStream inputStream) {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Object objFromJson = gson.fromJson(inputStreamReader, new ParameterizedTypeImpl(Object.class));
            return Result.m1917constructorimpl(objFromJson instanceof List ? (List) objFromJson : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m1917constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static final void writeToOutputStream(Gson gson, OutputStream out, Object any) throws IOException {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(any, "any");
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        jsonWriter.setIndent("  ");
        if (any instanceof List) {
            jsonWriter.beginArray();
            for (Object obj : (Iterable) any) {
                if (obj != null) {
                    gson.toJson(obj, obj.getClass(), jsonWriter);
                }
            }
            jsonWriter.endArray();
        } else {
            gson.toJson(any, any.getClass(), jsonWriter);
        }
        jsonWriter.close();
    }

    public static final /* synthetic */ <T> List<T> toList(JsonObject jsonObject, String... name) {
        Object objM1917constructorimpl;
        Intrinsics.checkNotNullParameter(jsonObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        JsonElement jsonElement = jsonObject;
        for (String str : name) {
            jsonElement = jsonObject.get(str);
            Intrinsics.checkNotNullExpressionValue(jsonElement, "get(...)");
        }
        String json = getGSON().toJson(jsonElement);
        Gson gson = getGSON();
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Object objFromJson = gson.fromJson(json, new ParameterizedTypeImpl(Object.class));
            objM1917constructorimpl = Result.m1917constructorimpl(objFromJson instanceof List ? (List) objFromJson : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM1917constructorimpl = Result.m1917constructorimpl(ResultKt.createFailure(th));
        }
        List<T> list = (List) (Result.m1923isFailureimpl(objM1917constructorimpl) ? null : objM1917constructorimpl);
        return list != null ? list : new ArrayList();
    }

    public static final /* synthetic */ <T> T toModel(JsonObject jsonObject, String... name) {
        Object objM1917constructorimpl;
        Intrinsics.checkNotNullParameter(jsonObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        JsonElement jsonElement = jsonObject;
        for (String str : name) {
            jsonElement = jsonObject.get(str);
            Intrinsics.checkNotNullExpressionValue(jsonElement, "get(...)");
        }
        String json = getGSON().toJson(jsonElement);
        Gson gson = getGSON();
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.needClassReification();
            Type type = new TypeToken<T>() { // from class: com.movieboxpro.android.utils.GsonExtensionsKt$toModel$$inlined$fromJsonObject$1
            }.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            Object objFromJson = gson.fromJson(json, type);
            Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
            Object obj = objFromJson;
            objM1917constructorimpl = (T) Result.m1917constructorimpl(objFromJson);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM1917constructorimpl = Result.m1917constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1923isFailureimpl(objM1917constructorimpl)) {
            objM1917constructorimpl = (T) null;
        }
        Intrinsics.checkNotNull(objM1917constructorimpl);
        return (T) objM1917constructorimpl;
    }
}
