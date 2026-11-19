package com.hjq.gson.factory.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class BooleanTypeAdapter extends TypeAdapter<Boolean> {

    /* JADX INFO: renamed from: com.hjq.gson.factory.data.BooleanTypeAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Boolean read(JsonReader jsonReader) throws IOException {
        JsonToken jsonTokenPeek = jsonReader.peek();
        int i = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonTokenPeek.ordinal()];
        if (i == 1) {
            return Boolean.valueOf(jsonReader.nextBoolean());
        }
        if (i != 2) {
            if (i == 3) {
                return Boolean.valueOf(jsonReader.nextInt() != 0);
            }
            if (i == 4) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.skipValue();
            throw new IllegalArgumentException("The current parser is of type Boolean, but the data is of type " + jsonTokenPeek);
        }
        String strNextString = jsonReader.nextString();
        if (String.valueOf(true).equalsIgnoreCase(strNextString)) {
            return true;
        }
        if (String.valueOf(false).equalsIgnoreCase(strNextString)) {
            return false;
        }
        throw new IllegalArgumentException("Data parsing failed, unable to convert " + strNextString + " to boolean type");
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
        jsonWriter.value(bool);
    }
}
