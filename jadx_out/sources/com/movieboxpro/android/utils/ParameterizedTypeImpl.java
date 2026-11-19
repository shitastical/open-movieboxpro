package com.movieboxpro.android.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GsonExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0016¢\u0006\u0002\u0010\u000bR\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/utils/ParameterizedTypeImpl;", "Ljava/lang/reflect/ParameterizedType;", "clazz", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "getRawType", "Ljava/lang/reflect/Type;", "getOwnerType", "getActualTypeArguments", "", "()[Ljava/lang/reflect/Type;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ParameterizedTypeImpl implements ParameterizedType {
    private final Class<?> clazz;

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return null;
    }

    public ParameterizedTypeImpl(Class<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        this.clazz = clazz;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return List.class;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return new Type[]{this.clazz};
    }
}
