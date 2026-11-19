package com.hjq.gson.factory.constructor;

import com.google.gson.internal.ObjectConstructor;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ListConstructor implements ObjectConstructor<List<?>> {
    private static final ListConstructor INSTANCE = new ListConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public List<?> construct() {
        return new ArrayList();
    }
}
