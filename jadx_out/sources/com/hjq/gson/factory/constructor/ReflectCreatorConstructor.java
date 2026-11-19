package com.hjq.gson.factory.constructor;

import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.reflect.ReflectionHelper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes5.dex */
public final class ReflectCreatorConstructor<T> implements ObjectConstructor<T> {
    private final Constructor<? super T> mConstructor;
    private final ObjectConstructor<T> mKotlinDataClassDefaultValueConstructor;

    public ReflectCreatorConstructor(MainConstructor mainConstructor, Gson gson, Class<? super T> cls, Constructor<? super T> constructor) {
        this.mConstructor = constructor;
        this.mKotlinDataClassDefaultValueConstructor = new KotlinDataClassDefaultValueConstructor(mainConstructor, gson, cls);
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public T construct() {
        T tConstruct = this.mKotlinDataClassDefaultValueConstructor.construct();
        if (tConstruct != null) {
            return tConstruct;
        }
        try {
            return this.mConstructor.newInstance(null);
        } catch (IllegalAccessException e) {
            throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.mConstructor) + "' with no args", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.mConstructor) + "' with no args", e3.getCause());
        }
    }
}
