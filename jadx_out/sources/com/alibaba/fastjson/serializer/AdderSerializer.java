package com.alibaba.fastjson.serializer;

import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes4.dex */
public class AdderSerializer implements ObjectSerializer {
    public static final AdderSerializer instance = new AdderSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (Trace$$ExternalSyntheticApiModelOutline0.m495m(obj)) {
            serializeWriter.writeFieldValue('{', "value", Trace$$ExternalSyntheticApiModelOutline0.m487m(obj).longValue());
            serializeWriter.write(125);
        } else if (Chip$$ExternalSyntheticApiModelOutline0.m550m(obj)) {
            serializeWriter.writeFieldValue('{', "value", Chip$$ExternalSyntheticApiModelOutline0.m546m(obj).doubleValue());
            serializeWriter.write(125);
        }
    }
}
