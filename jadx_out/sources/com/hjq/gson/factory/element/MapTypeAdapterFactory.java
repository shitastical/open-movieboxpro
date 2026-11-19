package com.hjq.gson.factory.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.hjq.gson.factory.constructor.MainConstructor;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class MapTypeAdapterFactory implements TypeAdapterFactory {
    final boolean mComplexMapKeySerialization;
    private final MainConstructor mMainConstructor;

    public MapTypeAdapterFactory(MainConstructor mainConstructor, boolean z) {
        this.mMainConstructor = mainConstructor;
        this.mComplexMapKeySerialization = z;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        if (!Map.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        Type[] mapKeyAndValueTypes = C$Gson$Types.getMapKeyAndValueTypes(type, C$Gson$Types.getRawType(type));
        MapTypeAdapter mapTypeAdapter = new MapTypeAdapter(gson, mapKeyAndValueTypes[0], getKeyAdapter(gson, mapKeyAndValueTypes[0]), mapKeyAndValueTypes[1], gson.getAdapter(TypeToken.get(mapKeyAndValueTypes[1])), this.mMainConstructor.get(gson, typeToken), this.mComplexMapKeySerialization);
        mapTypeAdapter.setReflectiveType(typeToken, null);
        return mapTypeAdapter;
    }

    private TypeAdapter<?> getKeyAdapter(Gson gson, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return TypeAdapters.BOOLEAN_AS_STRING;
        }
        return gson.getAdapter(TypeToken.get(type));
    }
}
