package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes5.dex */
public class IntegerTypeAdapter extends TypeAdapter<Integer> {

    /* JADX INFO: renamed from: com.hjq.gson.factory.data.IntegerTypeAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Integer read(JsonReader jsonReader) throws IOException {
        JsonToken jsonTokenPeek = jsonReader.peek();
        int i = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonTokenPeek.ordinal()];
        if (i == 1) {
            try {
                return Integer.valueOf(jsonReader.nextInt());
            } catch (NumberFormatException unused) {
                return Integer.valueOf((int) jsonReader.nextDouble());
            }
        }
        if (i == 2) {
            String strNextString = jsonReader.nextString();
            if (strNextString == null || "".equals(strNextString)) {
                return 0;
            }
            try {
                return Integer.valueOf(Integer.parseInt(strNextString));
            } catch (NumberFormatException unused2) {
                return Integer.valueOf((int) new BigDecimal(strNextString).floatValue());
            }
        }
        if (i == 3) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.skipValue();
        throw new IllegalArgumentException("The current parser is of type Integer, but the data is of type " + jsonTokenPeek);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Integer num) throws IOException {
        jsonWriter.value(num);
    }
}
