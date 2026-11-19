package com.hjq.gson.factory.element;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.gson.factory.ParseExceptionCallback;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class MapTypeAdapter<K, V> extends TypeAdapter<Map<K, V>> {
    private final boolean mComplexMapKeySerialization;
    private final ObjectConstructor<? extends Map<K, V>> mConstructor;
    private String mFieldName;
    private final TypeAdapter<K> mKeyTypeAdapter;
    private TypeToken<?> mTypeToken;
    private final TypeAdapter<V> mValueTypeAdapter;

    public MapTypeAdapter(Gson gson, Type type, TypeAdapter<K> typeAdapter, Type type2, TypeAdapter<V> typeAdapter2, ObjectConstructor<? extends Map<K, V>> objectConstructor, boolean z) {
        this.mKeyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
        this.mValueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, type2);
        this.mConstructor = objectConstructor;
        this.mComplexMapKeySerialization = z;
    }

    public void setReflectiveType(TypeToken<?> typeToken, String str) {
        this.mTypeToken = typeToken;
        this.mFieldName = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.gson.TypeAdapter
    public Map<K, V> read(JsonReader jsonReader) throws IOException {
        JsonToken jsonToken;
        JsonToken jsonTokenPeek = jsonReader.peek();
        Map<K, V> mapConstruct = this.mConstructor.construct();
        if (jsonTokenPeek == JsonToken.NULL) {
            jsonReader.nextNull();
            return mapConstruct;
        }
        if (jsonTokenPeek == JsonToken.BEGIN_ARRAY) {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                    jsonReader.beginArray();
                    mapConstruct.put(this.mKeyTypeAdapter.read(jsonReader), this.mValueTypeAdapter.read(jsonReader));
                    jsonReader.endArray();
                } else {
                    jsonReader.skipValue();
                    ParseExceptionCallback parseExceptionCallback = GsonFactory.getParseExceptionCallback();
                    if (parseExceptionCallback != null) {
                        parseExceptionCallback.onParseObjectException(this.mTypeToken, this.mFieldName, jsonTokenPeek);
                    }
                }
            }
            jsonReader.endArray();
        } else if (jsonTokenPeek == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                JsonReaderInternalAccess.INSTANCE.promoteNameToValue(jsonReader);
                Object objPeek = null;
                try {
                    K k = this.mKeyTypeAdapter.read(jsonReader);
                    try {
                        objPeek = jsonReader.peek();
                        mapConstruct.put(k, this.mValueTypeAdapter.read(jsonReader));
                    } catch (IllegalArgumentException e) {
                        e = e;
                        jsonToken = objPeek;
                        objPeek = k;
                        e.printStackTrace();
                        ParseExceptionCallback parseExceptionCallback2 = GsonFactory.getParseExceptionCallback();
                        if (parseExceptionCallback2 != null) {
                            parseExceptionCallback2.onParseMapItemException(this.mTypeToken, this.mFieldName, String.valueOf(objPeek), jsonToken);
                        }
                    }
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    jsonToken = 0;
                }
            }
            jsonReader.endObject();
        } else {
            jsonReader.skipValue();
            ParseExceptionCallback parseExceptionCallback3 = GsonFactory.getParseExceptionCallback();
            if (parseExceptionCallback3 != null) {
                parseExceptionCallback3.onParseObjectException(this.mTypeToken, this.mFieldName, jsonTokenPeek);
            }
        }
        return mapConstruct;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
        if (map == null) {
            jsonWriter.nullValue();
            return;
        }
        if (!this.mComplexMapKeySerialization) {
            jsonWriter.beginObject();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                jsonWriter.name(String.valueOf(entry.getKey()));
                this.mValueTypeAdapter.write(jsonWriter, entry.getValue());
            }
            jsonWriter.endObject();
            return;
        }
        ArrayList arrayList = new ArrayList(map.size());
        ArrayList arrayList2 = new ArrayList(map.size());
        int i = 0;
        boolean z = false;
        for (Map.Entry<K, V> entry2 : map.entrySet()) {
            JsonElement jsonTree = this.mKeyTypeAdapter.toJsonTree(entry2.getKey());
            arrayList.add(jsonTree);
            arrayList2.add(entry2.getValue());
            z |= jsonTree.isJsonArray() || jsonTree.isJsonObject();
        }
        if (z) {
            jsonWriter.beginArray();
            int size = arrayList.size();
            while (i < size) {
                jsonWriter.beginArray();
                Streams.write((JsonElement) arrayList.get(i), jsonWriter);
                this.mValueTypeAdapter.write(jsonWriter, (V) arrayList2.get(i));
                jsonWriter.endArray();
                i++;
            }
            jsonWriter.endArray();
            return;
        }
        jsonWriter.beginObject();
        int size2 = arrayList.size();
        while (i < size2) {
            jsonWriter.name(keyToString((JsonElement) arrayList.get(i)));
            this.mValueTypeAdapter.write(jsonWriter, (V) arrayList2.get(i));
            i++;
        }
        jsonWriter.endObject();
    }

    private String keyToString(JsonElement jsonElement) {
        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
                return String.valueOf(asJsonPrimitive.getAsNumber());
            }
            if (asJsonPrimitive.isBoolean()) {
                return Boolean.toString(asJsonPrimitive.getAsBoolean());
            }
            if (asJsonPrimitive.isString()) {
                return asJsonPrimitive.getAsString();
            }
            throw new AssertionError();
        }
        if (jsonElement.isJsonNull()) {
            return "null";
        }
        throw new AssertionError();
    }
}
