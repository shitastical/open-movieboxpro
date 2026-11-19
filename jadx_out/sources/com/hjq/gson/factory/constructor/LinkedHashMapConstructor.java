package com.hjq.gson.factory.constructor;

import com.google.gson.internal.ObjectConstructor;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class LinkedHashMapConstructor implements ObjectConstructor<LinkedHashMap<?, ?>> {
    private static final LinkedHashMapConstructor INSTANCE = new LinkedHashMapConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public LinkedHashMap<?, ?> construct() {
        return new LinkedHashMap<>();
    }
}
