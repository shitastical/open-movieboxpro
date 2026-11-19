package com.hjq.gson.factory;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;

/* JADX INFO: loaded from: classes5.dex */
public interface ParseExceptionCallback {
    void onParseListItemException(TypeToken<?> typeToken, String str, JsonToken jsonToken);

    void onParseMapItemException(TypeToken<?> typeToken, String str, String str2, JsonToken jsonToken);

    void onParseObjectException(TypeToken<?> typeToken, String str, JsonToken jsonToken);
}
