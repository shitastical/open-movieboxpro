package com.hjq.gson.factory.constructor;

import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.UnsafeAllocator;

/* JADX INFO: loaded from: classes5.dex */
public final class ReflectSafeCreatorConstructor<T> implements ObjectConstructor<T> {
    private final ObjectConstructor<T> mKotlinDataClassDefaultValueConstructor;
    private final Class<? super T> mRawType;

    public ReflectSafeCreatorConstructor(MainConstructor mainConstructor, Gson gson, Class<? super T> cls) {
        this.mRawType = cls;
        this.mKotlinDataClassDefaultValueConstructor = new KotlinDataClassDefaultValueConstructor(mainConstructor, gson, cls);
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public T construct() {
        T tConstruct = this.mKotlinDataClassDefaultValueConstructor.construct();
        if (tConstruct != null) {
            return tConstruct;
        }
        try {
            return (T) UnsafeAllocator.INSTANCE.newInstance(this.mRawType);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create instance of " + this.mRawType + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
        }
    }
}
