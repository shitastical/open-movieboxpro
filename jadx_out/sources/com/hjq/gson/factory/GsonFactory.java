package com.hjq.gson.factory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TypeAdapters;
import com.hjq.gson.factory.constructor.MainConstructor;
import com.hjq.gson.factory.data.BigDecimalTypeAdapter;
import com.hjq.gson.factory.data.BooleanTypeAdapter;
import com.hjq.gson.factory.data.DoubleTypeAdapter;
import com.hjq.gson.factory.data.FloatTypeAdapter;
import com.hjq.gson.factory.data.IntegerTypeAdapter;
import com.hjq.gson.factory.data.JSONArrayTypeAdapter;
import com.hjq.gson.factory.data.JSONObjectTypeAdapter;
import com.hjq.gson.factory.data.LongTypeAdapter;
import com.hjq.gson.factory.data.StringTypeAdapter;
import com.hjq.gson.factory.element.CollectionTypeAdapterFactory;
import com.hjq.gson.factory.element.MapTypeAdapterFactory;
import com.hjq.gson.factory.element.ReflectiveTypeAdapterFactory;
import com.hjq.gson.factory.other.AutoToNumberStrategy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class GsonFactory {
    private static volatile Gson sGson;
    private static ParseExceptionCallback sParseExceptionCallback;
    private static final HashMap<Type, InstanceCreator<?>> INSTANCE_CREATORS = new HashMap<>(0);
    private static final List<TypeAdapterFactory> TYPE_ADAPTER_FACTORIES = new ArrayList();
    private static final List<ReflectionAccessFilter> REFLECTION_ACCESS_FILTERS = new ArrayList();
    private static ToNumberStrategy sObjectToNumberStrategy = new AutoToNumberStrategy();

    private GsonFactory() {
    }

    public static Gson getSingletonGson() {
        if (sGson == null) {
            synchronized (GsonFactory.class) {
                if (sGson == null) {
                    sGson = newGsonBuilder().create();
                }
            }
        }
        return sGson;
    }

    public static void setSingletonGson(Gson gson) {
        sGson = gson;
    }

    public static void setParseExceptionCallback(ParseExceptionCallback parseExceptionCallback) {
        sParseExceptionCallback = parseExceptionCallback;
    }

    public static ParseExceptionCallback getParseExceptionCallback() {
        return sParseExceptionCallback;
    }

    public static void registerTypeAdapterFactory(TypeAdapterFactory typeAdapterFactory) {
        TYPE_ADAPTER_FACTORIES.add(typeAdapterFactory);
    }

    public static void registerInstanceCreator(Type type, InstanceCreator<?> instanceCreator) {
        INSTANCE_CREATORS.put(type, instanceCreator);
    }

    public static void addReflectionAccessFilter(ReflectionAccessFilter reflectionAccessFilter) {
        if (reflectionAccessFilter == null) {
            return;
        }
        REFLECTION_ACCESS_FILTERS.add(0, reflectionAccessFilter);
    }

    public static void setObjectToNumberStrategy(ToNumberStrategy toNumberStrategy) {
        sObjectToNumberStrategy = toNumberStrategy;
    }

    public static GsonBuilder newGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        MainConstructor mainConstructor = new MainConstructor(INSTANCE_CREATORS, true, REFLECTION_ACCESS_FILTERS);
        ToNumberStrategy toNumberStrategy = sObjectToNumberStrategy;
        if (toNumberStrategy != null) {
            gsonBuilder.setObjectToNumberStrategy(toNumberStrategy);
        }
        gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(String.class, new StringTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Boolean.TYPE, Boolean.class, new BooleanTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Integer.TYPE, Integer.class, new IntegerTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Long.TYPE, Long.class, new LongTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Float.TYPE, Float.class, new FloatTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(Double.TYPE, Double.class, new DoubleTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(BigDecimal.class, new BigDecimalTypeAdapter())).registerTypeAdapterFactory(new CollectionTypeAdapterFactory(mainConstructor)).registerTypeAdapterFactory(new ReflectiveTypeAdapterFactory(mainConstructor, FieldNamingPolicy.IDENTITY, Excluder.DEFAULT)).registerTypeAdapterFactory(new MapTypeAdapterFactory(mainConstructor, false)).registerTypeAdapterFactory(TypeAdapters.newFactory(JSONObject.class, new JSONObjectTypeAdapter())).registerTypeAdapterFactory(TypeAdapters.newFactory(JSONArray.class, new JSONArrayTypeAdapter()));
        Iterator<TypeAdapterFactory> it = TYPE_ADAPTER_FACTORIES.iterator();
        while (it.hasNext()) {
            gsonBuilder.registerTypeAdapterFactory(it.next());
        }
        return gsonBuilder;
    }
}
