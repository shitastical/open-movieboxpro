package com.hjq.gson.factory.constructor;

import com.google.gson.internal.ObjectConstructor;
import java.util.concurrent.ConcurrentSkipListMap;

/* JADX INFO: loaded from: classes5.dex */
public final class ConcurrentSkipListMapConstructor implements ObjectConstructor<ConcurrentSkipListMap<?, ?>> {
    private static final ConcurrentSkipListMapConstructor INSTANCE = new ConcurrentSkipListMapConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public ConcurrentSkipListMap<?, ?> construct() {
        return new ConcurrentSkipListMap<>();
    }
}
