package com.hjq.gson.factory.data;

import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public class JSONObjectTypeAdapter extends TypeAdapter<JSONObject> {
    private static final TypeAdapter<JsonElement> PROXY = TypeAdapters.JSON_ELEMENT;

    @Override // com.google.gson.TypeAdapter
    public JSONObject read(JsonReader jsonReader) throws IOException {
        JsonElement jsonElement = PROXY.read(jsonReader);
        if (!jsonElement.isJsonObject()) {
            return null;
        }
        try {
            return new JSONObject(jsonElement.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, JSONObject jSONObject) throws IOException {
        if (jSONObject == null) {
            jsonWriter.nullValue();
        } else {
            TypeAdapter<JsonElement> typeAdapter = PROXY;
            typeAdapter.write(jsonWriter, typeAdapter.fromJson(jSONObject.toString()));
        }
    }
}
