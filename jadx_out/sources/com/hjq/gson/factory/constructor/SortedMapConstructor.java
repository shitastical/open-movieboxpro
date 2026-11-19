package com.hjq.gson.factory.constructor;

import com.google.gson.internal.ObjectConstructor;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public final class SortedMapConstructor implements ObjectConstructor<SortedMap<?, ?>> {
    private static final SortedMapConstructor INSTANCE = new SortedMapConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public SortedMap<?, ?> construct() {
        return new TreeMap();
    }
}
