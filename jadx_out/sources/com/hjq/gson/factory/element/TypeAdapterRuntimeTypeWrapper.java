package com.hjq.gson.factory.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.SerializationDelegatingTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes5.dex */
public class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final TypeAdapter<T> mDelegate;
    private final Gson mGson;
    private final Type mType;

    public TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.mGson = gson;
        this.mDelegate = typeAdapter;
        this.mType = type;
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        return this.mDelegate.read(jsonReader);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        TypeAdapter<T> adapter;
        Type runtimeTypeIfMoreSpecific = getRuntimeTypeIfMoreSpecific(this.mType, t);
        if (runtimeTypeIfMoreSpecific != this.mType) {
            adapter = this.mGson.getAdapter(TypeToken.get(runtimeTypeIfMoreSpecific));
            if ((adapter instanceof com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter) && !isReflective(this.mDelegate)) {
                adapter = this.mDelegate;
            }
        } else {
            adapter = this.mDelegate;
        }
        adapter.write(jsonWriter, t);
    }

    private static boolean isReflective(TypeAdapter<?> typeAdapter) {
        TypeAdapter<?> serializationDelegate;
        while ((typeAdapter instanceof SerializationDelegatingTypeAdapter) && (serializationDelegate = ((SerializationDelegatingTypeAdapter) typeAdapter).getSerializationDelegate()) != typeAdapter) {
            typeAdapter = serializationDelegate;
        }
        return typeAdapter instanceof com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;
    }

    private static Type getRuntimeTypeIfMoreSpecific(Type type, Object obj) {
        if (obj != null) {
            return ((type instanceof Class) || (type instanceof TypeVariable)) ? obj.getClass() : type;
        }
        return type;
    }
}
