package com.movieboxpro.android.utils;

import com.movieboxpro.android.utils.tool.SPStaticUtils;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: SPDelegate.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u001a6\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00072\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00030\b\u001a6\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u000b2\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00030\b¨\u0006\f"}, d2 = {"stringSP", "Lkotlin/properties/ReadWriteProperty;", "", "", "key", "defaultValue", "intSP", "", "Lkotlin/Function1;", "Lkotlin/reflect/KProperty;", "boolSP", "", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class SPDelegateKt {
    public static /* synthetic */ ReadWriteProperty stringSP$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return stringSP(str, str2);
    }

    public static final ReadWriteProperty<Object, String> stringSP(final String key, final String defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return new ReadWriteProperty<Object, String>() { // from class: com.movieboxpro.android.utils.SPDelegateKt.stringSP.1
            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
                return getValue(obj, (KProperty<?>) kProperty);
            }

            @Override // kotlin.properties.ReadWriteProperty
            public /* bridge */ /* synthetic */ void setValue(Object obj, KProperty kProperty, String str) {
                setValue2(obj, (KProperty<?>) kProperty, str);
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public String getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(thisRef, "thisRef");
                Intrinsics.checkNotNullParameter(property, "property");
                return SPStaticUtils.getString(key, defaultValue);
            }

            /* JADX INFO: renamed from: setValue, reason: avoid collision after fix types in other method */
            public void setValue2(Object thisRef, KProperty<?> property, String value) {
                Intrinsics.checkNotNullParameter(thisRef, "thisRef");
                Intrinsics.checkNotNullParameter(property, "property");
                Intrinsics.checkNotNullParameter(value, "value");
                SPStaticUtils.put(key, value);
            }
        };
    }

    public static /* synthetic */ ReadWriteProperty intSP$default(int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            function1 = new PropertyReference1Impl() { // from class: com.movieboxpro.android.utils.SPDelegateKt.intSP.1
                @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
                public Object get(Object obj2) {
                    return ((KProperty) obj2).getName();
                }
            };
        }
        return intSP(i, function1);
    }

    public static final ReadWriteProperty<Object, Integer> intSP(final int i, final Function1<? super KProperty<?>, String> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new ReadWriteProperty<Object, Integer>() { // from class: com.movieboxpro.android.utils.SPDelegateKt.intSP.2
            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
                return getValue(obj, (KProperty<?>) kProperty);
            }

            @Override // kotlin.properties.ReadWriteProperty
            public /* bridge */ /* synthetic */ void setValue(Object obj, KProperty kProperty, Integer num) {
                setValue(obj, (KProperty<?>) kProperty, num.intValue());
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public Integer getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(thisRef, "thisRef");
                Intrinsics.checkNotNullParameter(property, "property");
                return Integer.valueOf(SPStaticUtils.getInt(key.invoke(property), i));
            }

            public void setValue(Object thisRef, KProperty<?> property, int value) {
                Intrinsics.checkNotNullParameter(thisRef, "thisRef");
                Intrinsics.checkNotNullParameter(property, "property");
                SPStaticUtils.put(key.invoke(property), value);
            }
        };
    }

    public static /* synthetic */ ReadWriteProperty boolSP$default(boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            function1 = new PropertyReference1Impl() { // from class: com.movieboxpro.android.utils.SPDelegateKt.boolSP.1
                @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
                public Object get(Object obj2) {
                    return ((KProperty) obj2).getName();
                }
            };
        }
        return boolSP(z, function1);
    }

    public static final ReadWriteProperty<Object, Boolean> boolSP(final boolean z, final Function1<? super KProperty<?>, String> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new ReadWriteProperty<Object, Boolean>() { // from class: com.movieboxpro.android.utils.SPDelegateKt.boolSP.2
            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
                return getValue(obj, (KProperty<?>) kProperty);
            }

            @Override // kotlin.properties.ReadWriteProperty
            public /* bridge */ /* synthetic */ void setValue(Object obj, KProperty kProperty, Boolean bool) {
                setValue(obj, (KProperty<?>) kProperty, bool.booleanValue());
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public Boolean getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(thisRef, "thisRef");
                Intrinsics.checkNotNullParameter(property, "property");
                return Boolean.valueOf(SPStaticUtils.getBoolean(key.invoke(property), z));
            }

            public void setValue(Object thisRef, KProperty<?> property, boolean value) {
                Intrinsics.checkNotNullParameter(thisRef, "thisRef");
                Intrinsics.checkNotNullParameter(property, "property");
                SPStaticUtils.put(key.invoke(property), value);
            }
        };
    }
}
