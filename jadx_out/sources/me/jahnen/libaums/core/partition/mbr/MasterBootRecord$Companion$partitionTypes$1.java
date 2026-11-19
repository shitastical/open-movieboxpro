package me.jahnen.libaums.core.partition.mbr;

import j$.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;

/* JADX INFO: compiled from: MasterBootRecord.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"me/jahnen/libaums/core/partition/mbr/MasterBootRecord$Companion$partitionTypes$1", "Ljava/util/HashMap;", "", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MasterBootRecord$Companion$partitionTypes$1 extends HashMap<Integer, Integer> implements Map {
    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
    public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return Map.CC.$default$putIfAbsent(this, obj, obj2);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
    public /* synthetic */ Object replace(Object obj, Object obj2) {
        return Map.CC.$default$replace(this, obj, obj2);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
    public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    MasterBootRecord$Companion$partitionTypes$1() {
        put(11, 2);
        put(12, 2);
        put(27, 2);
        put(28, 2);
        put(1, 0);
        put(4, 1);
        put(6, 1);
        put(14, 1);
        put(131, 3);
        put(7, 6);
        put(175, 4);
    }

    public /* bridge */ boolean containsKey(Integer num) {
        return super.containsKey((Object) num);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof Integer) {
            return containsKey((Integer) obj);
        }
        return false;
    }

    public /* bridge */ boolean containsValue(Integer num) {
        return super.containsValue((Object) num);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof Integer) {
            return containsValue((Integer) obj);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<java.util.Map.Entry<Integer, Integer>> entrySet() {
        return getEntries();
    }

    public /* bridge */ Integer get(Integer num) {
        return (Integer) super.get((Object) num);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Integer get(Object obj) {
        if (obj instanceof Integer) {
            return get((Integer) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        if (obj instanceof Integer) {
            return get((Integer) obj);
        }
        return null;
    }

    public /* bridge */ Set<java.util.Map.Entry<Integer, Integer>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<Integer> getKeys() {
        return super.keySet();
    }

    public /* bridge */ Integer getOrDefault(Integer num, Integer num2) {
        return (Integer) Map.CC.$default$getOrDefault(this, num, num2);
    }

    public final /* bridge */ Integer getOrDefault(Object obj, Integer num) {
        return !(obj instanceof Integer) ? num : getOrDefault((Integer) obj, num);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map, j$.util.concurrent.ConcurrentMap
    public final /* bridge */ /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj instanceof Integer) ? obj2 : getOrDefault((Integer) obj, (Integer) obj2);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<Integer> getValues() {
        return super.values();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Integer> keySet() {
        return getKeys();
    }

    public /* bridge */ Integer remove(Integer num) {
        return (Integer) super.remove((Object) num);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Integer remove(Object obj) {
        if (obj instanceof Integer) {
            return remove((Integer) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object obj) {
        if (obj instanceof Integer) {
            return remove((Integer) obj);
        }
        return null;
    }

    public /* bridge */ boolean remove(Integer num, Integer num2) {
        return Map.CC.$default$remove(this, num, num2);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
    public final /* bridge */ boolean remove(Object obj, Object obj2) {
        if ((obj instanceof Integer) && (obj2 instanceof Integer)) {
            return remove((Integer) obj, (Integer) obj2);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<Integer> values() {
        return getValues();
    }
}
