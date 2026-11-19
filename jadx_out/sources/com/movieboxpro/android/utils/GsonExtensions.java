package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GsonExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/utils/GsonExtensions;", "", "<init>", "()V", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GsonExtensions {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final Gson getGson() {
        return INSTANCE.getGson();
    }

    @JvmStatic
    public static final <T> List<T> parseArray(String str, Class<T> cls) {
        return INSTANCE.parseArray(str, cls);
    }

    @JvmStatic
    public static final <T> T parseObject(String str, Class<T> cls) {
        return (T) INSTANCE.parseObject(str, cls);
    }

    @JvmStatic
    public static final String toJsonString(Object obj) {
        return INSTANCE.toJsonString(obj);
    }

    /* JADX INFO: compiled from: GsonExtensions.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J,\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\fH\u0007J+\u0010\r\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\fH\u0007¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0007¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/utils/GsonExtensions$Companion;", "", "<init>", "()V", "getGson", "Lcom/google/gson/Gson;", "parseArray", "", ExifInterface.GPS_DIRECTION_TRUE, "json", "", "clazz", "Ljava/lang/Class;", "parseObject", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "toJsonString", "any", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Gson getGson() {
            return GsonExtensionsKt.getGSON();
        }

        @JvmStatic
        public final <T> List<T> parseArray(String json, Class<T> clazz) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            Object objFromJsonArray = GsonExtensionsKt.fromJsonArray(GsonExtensionsKt.getGSON(), json, clazz);
            if (Result.m1923isFailureimpl(objFromJsonArray)) {
                objFromJsonArray = null;
            }
            List<T> list = (List) objFromJsonArray;
            return list == null ? new ArrayList() : list;
        }

        @JvmStatic
        public final <T> T parseObject(String json, Class<T> clazz) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            return (T) GsonExtensionsKt.getGSON().fromJson(json, (Class) clazz);
        }

        @JvmStatic
        public final String toJsonString(Object any) {
            String json = GsonExtensionsKt.getGSON().toJson(any);
            Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
            return json;
        }
    }
}
