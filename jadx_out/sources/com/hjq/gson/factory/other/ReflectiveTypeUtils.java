package com.hjq.gson.factory.other;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.hjq.gson.factory.constructor.MainConstructor;
import com.hjq.gson.factory.element.CollectionTypeAdapter;
import com.hjq.gson.factory.element.MapTypeAdapter;
import com.hjq.gson.factory.element.ReflectiveTypeAdapter;
import com.hjq.gson.factory.element.TypeAdapterRuntimeTypeWrapper;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes5.dex */
public class ReflectiveTypeUtils {
    private static final ArrayList<Class<?>> TYPE_TOKENS;

    static {
        ArrayList<Class<?>> arrayList = new ArrayList<>();
        TYPE_TOKENS = arrayList;
        arrayList.add(String.class);
        arrayList.add(Integer.class);
        arrayList.add(Boolean.class);
        arrayList.add(Byte.class);
        arrayList.add(Short.class);
        arrayList.add(Long.class);
        arrayList.add(Double.class);
        arrayList.add(Float.class);
        arrayList.add(Number.class);
        arrayList.add(AtomicInteger.class);
        arrayList.add(AtomicBoolean.class);
        arrayList.add(AtomicLong.class);
        arrayList.add(AtomicLongArray.class);
        arrayList.add(AtomicIntegerArray.class);
        arrayList.add(Character.class);
        arrayList.add(StringBuilder.class);
        arrayList.add(StringBuffer.class);
        arrayList.add(BigDecimal.class);
        arrayList.add(BigInteger.class);
        arrayList.add(URL.class);
        arrayList.add(URI.class);
        arrayList.add(UUID.class);
        arrayList.add(Currency.class);
        arrayList.add(Locale.class);
        arrayList.add(InetAddress.class);
        arrayList.add(BitSet.class);
        arrayList.add(Date.class);
        arrayList.add(GregorianCalendar.class);
        arrayList.add(Calendar.class);
        arrayList.add(Time.class);
        arrayList.add(java.sql.Date.class);
        arrayList.add(Timestamp.class);
        arrayList.add(Class.class);
    }

    public static boolean containsClass(Class<?> cls) {
        return TYPE_TOKENS.contains(cls);
    }

    public static ReflectiveFieldBound createBoundField(Gson gson, MainConstructor mainConstructor, Field field, String str, TypeToken<?> typeToken, boolean z, boolean z2) {
        return new ReflectiveFieldBound(str, z, z2, gson, mainConstructor, field, typeToken, str) { // from class: com.hjq.gson.factory.other.ReflectiveTypeUtils.1
            final TypeAdapter<?> typeAdapter;
            final /* synthetic */ Field val$field;
            final /* synthetic */ String val$fieldName;
            final /* synthetic */ TypeToken val$fieldType;
            final /* synthetic */ Gson val$gson;
            final /* synthetic */ MainConstructor val$mainConstructor;

            {
                this.val$gson = gson;
                this.val$mainConstructor = mainConstructor;
                this.val$field = field;
                this.val$fieldType = typeToken;
                this.val$fieldName = str;
                this.typeAdapter = ReflectiveTypeUtils.getFieldAdapter(gson, mainConstructor, field, typeToken, str);
            }

            @Override // com.hjq.gson.factory.other.ReflectiveFieldBound
            public void write(JsonWriter jsonWriter, Object obj) throws IllegalAccessException, IOException {
                new TypeAdapterRuntimeTypeWrapper(this.val$gson, this.typeAdapter, this.val$fieldType.getType()).write(jsonWriter, this.val$field.get(obj));
            }

            @Override // com.hjq.gson.factory.other.ReflectiveFieldBound
            public void read(JsonReader jsonReader, Object obj) throws IllegalAccessException, IOException {
                Object obj2 = this.typeAdapter.read(jsonReader);
                if (obj2 == null) {
                    return;
                }
                this.val$field.set(obj, obj2);
            }

            @Override // com.hjq.gson.factory.other.ReflectiveFieldBound
            public boolean writeField(Object obj) throws IllegalAccessException, IOException {
                return isSerialized() && this.val$field.get(obj) != obj;
            }
        };
    }

    public static TypeAdapter<?> getFieldAdapter(Gson gson, MainConstructor mainConstructor, Field field, TypeToken<?> typeToken, String str) {
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> typeAdapter = jsonAdapter != null ? getTypeAdapter(mainConstructor, gson, typeToken, jsonAdapter) : null;
        if (typeAdapter == null) {
            typeAdapter = gson.getAdapter(typeToken);
        }
        if (typeAdapter instanceof CollectionTypeAdapter) {
            ((CollectionTypeAdapter) typeAdapter).setReflectiveType(TypeToken.get((Class) field.getDeclaringClass()), str);
        }
        if (typeAdapter instanceof ReflectiveTypeAdapter) {
            ((ReflectiveTypeAdapter) typeAdapter).setReflectiveType(TypeToken.get((Class) field.getDeclaringClass()), str);
        }
        if (typeAdapter instanceof MapTypeAdapter) {
            ((MapTypeAdapter) typeAdapter).setReflectiveType(TypeToken.get((Class) field.getDeclaringClass()), str);
        }
        return typeAdapter;
    }

    public static TypeAdapter<?> getTypeAdapter(MainConstructor mainConstructor, Gson gson, TypeToken<?> typeToken, JsonAdapter jsonAdapter) {
        TypeAdapter<?> typeAdapterCreate;
        Class<?> clsValue = jsonAdapter.value();
        if (TypeAdapter.class.isAssignableFrom(clsValue)) {
            typeAdapterCreate = (TypeAdapter) mainConstructor.get(gson, TypeToken.get((Class) clsValue)).construct();
        } else if (TypeAdapterFactory.class.isAssignableFrom(clsValue)) {
            typeAdapterCreate = ((TypeAdapterFactory) mainConstructor.get(gson, TypeToken.get((Class) clsValue)).construct()).create(gson, typeToken);
        } else {
            throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
        }
        return typeAdapterCreate != null ? typeAdapterCreate.nullSafe() : typeAdapterCreate;
    }

    public static List<String> getFieldNames(FieldNamingStrategy fieldNamingStrategy, Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            return Collections.singletonList(fieldNamingStrategy.translateName(field));
        }
        String strValue = serializedName.value();
        String[] strArrAlternate = serializedName.alternate();
        if (strArrAlternate.length == 0) {
            return Collections.singletonList(strValue);
        }
        ArrayList arrayList = new ArrayList(strArrAlternate.length + 1);
        arrayList.add(strValue);
        Collections.addAll(arrayList, strArrAlternate);
        return arrayList;
    }
}
