package com.hjq.gson.factory.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.gson.factory.ParseExceptionCallback;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public class CollectionTypeAdapter<E> extends TypeAdapter<Collection<E>> {
    private final TypeAdapter<E> mElementTypeAdapter;
    private String mFieldName;
    private final ObjectConstructor<? extends Collection<E>> mObjectConstructor;
    private TypeToken<?> mTypeToken;

    public CollectionTypeAdapter(Gson gson, Type type, TypeAdapter<E> typeAdapter, ObjectConstructor<? extends Collection<E>> objectConstructor) {
        this.mElementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
        this.mObjectConstructor = objectConstructor;
    }

    public void setReflectiveType(TypeToken<?> typeToken, String str) {
        this.mTypeToken = typeToken;
        this.mFieldName = str;
    }

    @Override // com.google.gson.TypeAdapter
    public Collection<E> read(JsonReader jsonReader) throws IOException {
        JsonToken jsonTokenPeek;
        JsonToken jsonTokenPeek2 = jsonReader.peek();
        Collection<E> collectionConstruct = this.mObjectConstructor.construct();
        if (jsonTokenPeek2 == JsonToken.NULL) {
            jsonReader.nextNull();
            return collectionConstruct;
        }
        if (jsonTokenPeek2 != JsonToken.BEGIN_ARRAY) {
            jsonReader.skipValue();
            ParseExceptionCallback parseExceptionCallback = GsonFactory.getParseExceptionCallback();
            if (parseExceptionCallback != null) {
                parseExceptionCallback.onParseObjectException(this.mTypeToken, this.mFieldName, jsonTokenPeek2);
            }
            return collectionConstruct;
        }
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            try {
                jsonTokenPeek = jsonReader.peek();
                try {
                    collectionConstruct.add(this.mElementTypeAdapter.read(jsonReader));
                } catch (IllegalArgumentException e) {
                    e = e;
                    e.printStackTrace();
                    ParseExceptionCallback parseExceptionCallback2 = GsonFactory.getParseExceptionCallback();
                    if (parseExceptionCallback2 != null) {
                        parseExceptionCallback2.onParseListItemException(this.mTypeToken, this.mFieldName, jsonTokenPeek);
                    }
                }
            } catch (IllegalArgumentException e2) {
                e = e2;
                jsonTokenPeek = null;
            }
        }
        jsonReader.endArray();
        return collectionConstruct;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Collection<E> collection) throws IOException {
        if (collection == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        Iterator<E> it = collection.iterator();
        while (it.hasNext()) {
            this.mElementTypeAdapter.write(jsonWriter, it.next());
        }
        jsonWriter.endArray();
    }
}
