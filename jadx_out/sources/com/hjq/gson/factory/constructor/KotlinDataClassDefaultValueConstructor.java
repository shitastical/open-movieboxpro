package com.hjq.gson.factory.constructor;

import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import j$.util.Map;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;

/* JADX INFO: compiled from: KotlinDataClassDefaultValueConstructor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u0010*\b\b\u0000\u0010\u0001*\u00020\u00022\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003:\u0002\u0010\u0011B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t¢\u0006\u0002\u0010\nJ\u000f\u0010\u000b\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/hjq/gson/factory/constructor/KotlinDataClassDefaultValueConstructor;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lcom/google/gson/internal/ObjectConstructor;", "mainConstructor", "Lcom/hjq/gson/factory/constructor/MainConstructor;", "gson", "Lcom/google/gson/Gson;", "rawType", "Ljava/lang/Class;", "(Lcom/hjq/gson/factory/constructor/MainConstructor;Lcom/google/gson/Gson;Ljava/lang/Class;)V", "construct", "()Ljava/lang/Object;", "getTypeDefaultValue", "type", "Lkotlin/reflect/KType;", "Companion", "IndexedParameterMap", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class KotlinDataClassDefaultValueConstructor<T> implements ObjectConstructor<T> {
    private final Gson gson;
    private final MainConstructor mainConstructor;
    private final Class<?> rawType;
    private static final Object ABSENT_VALUE = new Object();

    public KotlinDataClassDefaultValueConstructor(MainConstructor mainConstructor, Gson gson, Class<?> rawType) {
        Intrinsics.checkNotNullParameter(mainConstructor, "mainConstructor");
        Intrinsics.checkNotNullParameter(gson, "gson");
        Intrinsics.checkNotNullParameter(rawType, "rawType");
        this.mainConstructor = mainConstructor;
        this.gson = gson;
        this.rawType = rawType;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public T construct() {
        T t;
        KFunction primaryConstructor = KClasses.getPrimaryConstructor(JvmClassMappingKt.getKotlinClass(this.rawType));
        if (primaryConstructor == null) {
            return null;
        }
        boolean z = true;
        KCallablesJvm.setAccessible(primaryConstructor, true);
        int size = primaryConstructor.getParameters().size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            objArr[i] = ABSENT_VALUE;
        }
        if (size > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (objArr[i2] == ABSENT_VALUE) {
                    KParameter kParameter = primaryConstructor.getParameters().get(i2);
                    if (kParameter.isOptional()) {
                        z = false;
                    } else if (kParameter.getType().isMarkedNullable()) {
                        objArr[i2] = null;
                    } else {
                        objArr[i2] = getTypeDefaultValue(kParameter.getType());
                    }
                }
                if (i3 >= size) {
                    break;
                }
                i2 = i3;
            }
        }
        if (z) {
            t = (T) primaryConstructor.call(Arrays.copyOf(objArr, size));
        } else {
            t = (T) primaryConstructor.callBy(new IndexedParameterMap(primaryConstructor.getParameters(), objArr));
        }
        if (t != null) {
            return t;
        }
        throw new NullPointerException("null cannot be cast to non-null type T of com.hjq.gson.factory.constructor.KotlinDataClassDefaultValueConstructor");
    }

    private final Object getTypeDefaultValue(KType type) {
        ObjectConstructor<T> objectConstructor;
        KClassifier classifier = type.getClassifier();
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(String.class))) {
            return "";
        }
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return false;
        }
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return 0;
        }
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return 0L;
        }
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            return Float.valueOf(0.0f);
        }
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return Double.valueOf(0.0d);
        }
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Short.TYPE))) {
            return (short) 0;
        }
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Byte.TYPE))) {
            return (byte) 0;
        }
        if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Character.TYPE))) {
            return (char) 0;
        }
        TypeToken<?> typeToken = TypeToken.get(ReflectJvmMapping.getJavaType(type));
        if (typeToken == null || (objectConstructor = this.mainConstructor.get(this.gson, typeToken)) == null) {
            return null;
        }
        return objectConstructor.construct();
    }

    /* JADX INFO: compiled from: KotlinDataClassDefaultValueConstructor.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0002J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u0016R(\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/hjq/gson/factory/constructor/KotlinDataClassDefaultValueConstructor$IndexedParameterMap;", "Lkotlin/collections/AbstractMutableMap;", "Lkotlin/reflect/KParameter;", "", "parameterKeys", "", "parameterValues", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "[Ljava/lang/Object;", "containsKey", "", "key", "get", "put", "value", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class IndexedParameterMap extends AbstractMutableMap<KParameter, Object> implements Map {
        private final List<KParameter> parameterKeys;
        private final Object[] parameterValues;

        @Override // java.util.Map, j$.util.Map
        public /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
            return Map.CC.$default$compute(this, obj, biFunction);
        }

        @Override // java.util.Map, j$.util.Map
        public /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
            return Map.CC.$default$computeIfAbsent(this, obj, function);
        }

        @Override // java.util.Map, j$.util.Map
        public /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
            return Map.CC.$default$computeIfPresent(this, obj, biFunction);
        }

        @Override // java.util.Map, j$.util.Map
        public /* synthetic */ void forEach(BiConsumer biConsumer) {
            Map.CC.$default$forEach(this, biConsumer);
        }

        @Override // java.util.Map, j$.util.Map
        public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
            return Map.CC.$default$merge(this, obj, obj2, biFunction);
        }

        @Override // kotlin.collections.AbstractMutableMap, java.util.AbstractMap, java.util.Map
        public Object put(KParameter key, Object value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return null;
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
            return Map.CC.$default$putIfAbsent(this, obj, obj2);
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public /* synthetic */ Object replace(Object obj, Object obj2) {
            return Map.CC.$default$replace(this, obj, obj2);
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
            return Map.CC.$default$replace(this, obj, obj2, obj3);
        }

        @Override // java.util.Map, j$.util.Map
        public /* synthetic */ void replaceAll(BiFunction biFunction) {
            Map.CC.$default$replaceAll(this, biFunction);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof KParameter) {
                return containsKey((KParameter) obj);
            }
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final /* bridge */ Object get(Object obj) {
            if (obj instanceof KParameter) {
                return get((KParameter) obj);
            }
            return null;
        }

        @Override // java.util.Map, j$.util.Map, j$.util.concurrent.ConcurrentMap
        public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
            return !(obj instanceof KParameter) ? obj2 : getOrDefault((KParameter) obj, obj2);
        }

        public /* bridge */ Object getOrDefault(KParameter kParameter, Object obj) {
            return Map.CC.$default$getOrDefault(this, kParameter, obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final /* bridge */ Object remove(Object obj) {
            if (obj instanceof KParameter) {
                return remove((KParameter) obj);
            }
            return null;
        }

        public /* bridge */ Object remove(KParameter kParameter) {
            return super.remove((Object) kParameter);
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final /* bridge */ boolean remove(Object obj, Object obj2) {
            if (obj instanceof KParameter) {
                return remove((KParameter) obj, obj2);
            }
            return false;
        }

        public /* bridge */ boolean remove(KParameter kParameter, Object obj) {
            return Map.CC.$default$remove(this, kParameter, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public IndexedParameterMap(List<? extends KParameter> parameterKeys, Object[] parameterValues) {
            Intrinsics.checkNotNullParameter(parameterKeys, "parameterKeys");
            Intrinsics.checkNotNullParameter(parameterValues, "parameterValues");
            this.parameterKeys = parameterKeys;
            this.parameterValues = parameterValues;
        }

        @Override // kotlin.collections.AbstractMutableMap
        public Set<java.util.Map.Entry<KParameter, Object>> getEntries() {
            List<KParameter> list = this.parameterKeys;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            for (T t : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList.add(new AbstractMap.SimpleEntry((KParameter) t, this.parameterValues[i]));
                i = i2;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (T t2 : arrayList) {
                if (((AbstractMap.SimpleEntry) t2).getValue() != KotlinDataClassDefaultValueConstructor.ABSENT_VALUE) {
                    linkedHashSet.add(t2);
                }
            }
            return linkedHashSet;
        }

        public boolean containsKey(KParameter key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return this.parameterValues[key.getIndex()] != KotlinDataClassDefaultValueConstructor.ABSENT_VALUE;
        }

        public Object get(KParameter key) {
            Intrinsics.checkNotNullParameter(key, "key");
            Object obj = this.parameterValues[key.getIndex()];
            if (obj != KotlinDataClassDefaultValueConstructor.ABSENT_VALUE) {
                return obj;
            }
            return null;
        }
    }
}
