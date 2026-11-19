package com.hjq.gson.factory.constructor;

import com.google.gson.internal.ObjectConstructor;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes5.dex */
public final class ConcurrentMapConstructor implements ObjectConstructor<ConcurrentMap<?, ?>> {
    private static final ConcurrentMapConstructor INSTANCE = new ConcurrentMapConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public ConcurrentMap<?, ?> construct() {
        return new ConcurrentHashMap();
    }
}
