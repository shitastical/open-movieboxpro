package com.hjq.gson.factory.constructor;

import com.google.gson.JsonIOException;
import com.google.gson.internal.ObjectConstructor;

/* JADX INFO: loaded from: classes5.dex */
public final class ExceptionConstructor<T> implements ObjectConstructor<T> {
    private final String mExceptionMessage;

    public ExceptionConstructor(String str) {
        this.mExceptionMessage = str;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public T construct() {
        throw new JsonIOException(this.mExceptionMessage);
    }
}
