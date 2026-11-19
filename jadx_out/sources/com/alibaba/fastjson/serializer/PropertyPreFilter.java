package com.alibaba.fastjson.serializer;

/* JADX INFO: loaded from: classes4.dex */
public interface PropertyPreFilter extends SerializeFilter {
    boolean apply(JSONSerializer jSONSerializer, Object obj, String str);
}
