package com.hjq.gson.factory.element;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.gson.factory.ParseExceptionCallback;
import com.hjq.gson.factory.other.ReflectiveFieldBound;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ReflectiveTypeAdapter<T> extends TypeAdapter<T> {
    private final Map<String, ReflectiveFieldBound> mBoundFields;
    private final ObjectConstructor<T> mConstructor;
    private String mFieldName;
    private TypeToken<?> mTypeToken;

    public ReflectiveTypeAdapter(ObjectConstructor<T> objectConstructor, Map<String, ReflectiveFieldBound> map) {
        this.mConstructor = objectConstructor;
        this.mBoundFields = map;
    }

    public void setReflectiveType(TypeToken<?> typeToken, String str) {
        this.mTypeToken = typeToken;
        this.mFieldName = str;
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        JsonToken jsonTokenPeek = jsonReader.peek();
        if (jsonTokenPeek == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        if (jsonTokenPeek != JsonToken.BEGIN_OBJECT) {
            jsonReader.skipValue();
            ParseExceptionCallback parseExceptionCallback = GsonFactory.getParseExceptionCallback();
            if (parseExceptionCallback != null) {
                parseExceptionCallback.onParseObjectException(this.mTypeToken, this.mFieldName, jsonTokenPeek);
            }
            return null;
        }
        T tConstruct = this.mConstructor.construct();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            ReflectiveFieldBound reflectiveFieldBound = this.mBoundFields.get(jsonReader.nextName());
            if (reflectiveFieldBound == null || !reflectiveFieldBound.isDeserialized()) {
                jsonReader.skipValue();
            } else {
                JsonToken jsonTokenPeek2 = jsonReader.peek();
                try {
                    reflectiveFieldBound.read(jsonReader, tConstruct);
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                    ParseExceptionCallback parseExceptionCallback2 = GsonFactory.getParseExceptionCallback();
                    if (parseExceptionCallback2 != null) {
                        parseExceptionCallback2.onParseObjectException(TypeToken.get((Class) tConstruct.getClass()), reflectiveFieldBound.getFieldName(), jsonTokenPeek2);
                    }
                } catch (IllegalStateException e3) {
                    throw new JsonSyntaxException(e3);
                }
            }
        }
        jsonReader.endObject();
        return tConstruct;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        if (t == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        for (ReflectiveFieldBound reflectiveFieldBound : this.mBoundFields.values()) {
            try {
                if (reflectiveFieldBound.writeField(t)) {
                    jsonWriter.name(reflectiveFieldBound.getFieldName());
                    reflectiveFieldBound.write(jsonWriter, t);
                }
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
        jsonWriter.endObject();
    }
}
