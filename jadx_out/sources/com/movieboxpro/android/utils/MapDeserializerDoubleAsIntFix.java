package com.movieboxpro.android.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GsonExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u0007¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/utils/MapDeserializerDoubleAsIntFix;", "Lcom/google/gson/JsonDeserializer;", "", "<init>", "()V", "deserialize", "jsonElement", "Lcom/google/gson/JsonElement;", "type", "Ljava/lang/reflect/Type;", "jsonDeserializationContext", "Lcom/google/gson/JsonDeserializationContext;", "read", "json", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MapDeserializerDoubleAsIntFix implements JsonDeserializer<Object> {
    @Override // com.google.gson.JsonDeserializer
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Intrinsics.checkNotNullParameter(jsonElement, "jsonElement");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(jsonDeserializationContext, "jsonDeserializationContext");
        return read(jsonElement);
    }

    public final Object read(JsonElement json) {
        Intrinsics.checkNotNullParameter(json, "json");
        if (json.isJsonArray()) {
            ArrayList arrayList = new ArrayList();
            Iterator<JsonElement> it = json.getAsJsonArray().iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                JsonElement next = it.next();
                Intrinsics.checkNotNull(next);
                arrayList.add(read(next));
            }
            return arrayList;
        }
        if (json.isJsonObject()) {
            LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                Intrinsics.checkNotNull(entry);
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                Intrinsics.checkNotNull(value);
                linkedTreeMap.put(key, read(value));
            }
            return linkedTreeMap;
        }
        if (!json.isJsonPrimitive()) {
            return null;
        }
        JsonPrimitive asJsonPrimitive = json.getAsJsonPrimitive();
        if (asJsonPrimitive.isBoolean()) {
            return Boolean.valueOf(asJsonPrimitive.getAsBoolean());
        }
        if (asJsonPrimitive.isString()) {
            return asJsonPrimitive.getAsString();
        }
        if (!asJsonPrimitive.isNumber()) {
            return null;
        }
        Number asNumber = asJsonPrimitive.getAsNumber();
        Intrinsics.checkNotNullExpressionValue(asNumber, "getAsNumber(...)");
        if (Math.ceil(asNumber.doubleValue()) == asNumber.longValue()) {
            return Long.valueOf(asNumber.longValue());
        }
        return Double.valueOf(asNumber.doubleValue());
    }
}
