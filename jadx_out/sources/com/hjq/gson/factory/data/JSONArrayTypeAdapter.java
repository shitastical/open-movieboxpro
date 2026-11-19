package com.hjq.gson.factory.data;

import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes5.dex */
public class JSONArrayTypeAdapter extends TypeAdapter<JSONArray> {
    private static final TypeAdapter<JsonElement> PROXY = TypeAdapters.JSON_ELEMENT;

    @Override // com.google.gson.TypeAdapter
    public JSONArray read(JsonReader jsonReader) throws IOException {
        JsonElement jsonElement = PROXY.read(jsonReader);
        if (!jsonElement.isJsonArray()) {
            return null;
        }
        try {
            return new JSONArray(jsonElement.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, JSONArray jSONArray) throws IOException {
        if (jSONArray == null) {
            jsonWriter.nullValue();
        } else {
            TypeAdapter<JsonElement> typeAdapter = PROXY;
            typeAdapter.write(jsonWriter, typeAdapter.fromJson(jSONArray.toString()));
        }
    }
}
