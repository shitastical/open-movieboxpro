package com.hjq.gson.factory.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.hjq.gson.factory.constructor.MainConstructor;
import com.hjq.gson.factory.other.ReflectiveTypeUtils;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public class CollectionTypeAdapterFactory implements TypeAdapterFactory {
    private final MainConstructor mMainConstructor;

    public CollectionTypeAdapterFactory(MainConstructor mainConstructor) {
        this.mMainConstructor = mainConstructor;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        if (ReflectiveTypeUtils.containsClass(rawType) || (typeToken.getType() instanceof GenericArrayType) || (((typeToken.getType() instanceof Class) && ((Class) typeToken.getType()).isArray()) || !Collection.class.isAssignableFrom(rawType))) {
            return null;
        }
        Type collectionElementType = C$Gson$Types.getCollectionElementType(type, rawType);
        CollectionTypeAdapter collectionTypeAdapter = new CollectionTypeAdapter(gson, collectionElementType, gson.getAdapter(TypeToken.get(collectionElementType)), this.mMainConstructor.get(gson, typeToken));
        collectionTypeAdapter.setReflectiveType(typeToken, null);
        return collectionTypeAdapter;
    }
}
