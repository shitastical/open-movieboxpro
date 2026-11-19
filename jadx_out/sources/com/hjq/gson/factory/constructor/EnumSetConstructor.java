package com.hjq.gson.factory.constructor;

import com.google.gson.JsonIOException;
import com.google.gson.internal.ObjectConstructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes5.dex */
public final class EnumSetConstructor<T> implements ObjectConstructor<T> {
    private final Type mType;

    public EnumSetConstructor(Type type) {
        this.mType = type;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public T construct() {
        Type type = this.mType;
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.mType);
        }
        throw new JsonIOException("Invalid EnumSet type: " + this.mType.toString());
    }
}
