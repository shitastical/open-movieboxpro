package com.hjq.gson.factory.constructor;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.ObjectConstructor;

/* JADX INFO: loaded from: classes5.dex */
public final class LinkedTreeMapConstructor implements ObjectConstructor<LinkedTreeMap<?, ?>> {
    private static final LinkedTreeMapConstructor INSTANCE = new LinkedTreeMapConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.internal.ObjectConstructor
    public LinkedTreeMap<?, ?> construct() {
        return new LinkedTreeMap<>();
    }
}
