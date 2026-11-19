package com.hjq.gson.factory.other;

import com.google.gson.ToNumberStrategy;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes5.dex */
public class AutoToNumberStrategy implements ToNumberStrategy {
    @Override // com.google.gson.ToNumberStrategy
    public Number readNumber(JsonReader jsonReader) throws IOException {
        BigDecimal bigDecimal = new BigDecimal(jsonReader.nextString());
        if (bigDecimal.scale() > 0) {
            return Double.valueOf(bigDecimal.doubleValue());
        }
        if (bigDecimal.compareTo(BigDecimal.valueOf(2147483647L)) > 0) {
            return Long.valueOf(bigDecimal.longValue());
        }
        return Integer.valueOf(bigDecimal.intValue());
    }
}
