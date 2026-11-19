package com.hjq.gson.factory.constructor;

import com.google.gson.InstanceCreator;
import com.google.gson.internal.ObjectConstructor;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class InstanceCreatorConstructor<T> implements ObjectConstructor<T> {
    private final InstanceCreator<T> mInstanceCreator;
    private final Type mType;

    public InstanceCreatorConstructor(InstanceCreator<T> instanceCreator, Type type) {
        this.mInstanceCreator = instanceCreator;
        this.mType = type;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public T construct() {
        return this.mInstanceCreator.createInstance(this.mType);
    }
}
