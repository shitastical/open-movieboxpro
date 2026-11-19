package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SingletonHolder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00002\u00020\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u0001¢\u0006\u0002\u0010\u000fR\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/utils/SingletonHolder;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "", "creator", "Lkotlin/Function1;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "instance", "getInstance", "()Ljava/lang/Object;", "setInstance", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "arg", "(Ljava/lang/Object;)Ljava/lang/Object;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class SingletonHolder<T, A> {
    private Function1<? super A, ? extends T> creator;
    private volatile T instance;

    public SingletonHolder(Function1<? super A, ? extends T> creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        this.creator = creator;
    }

    public final T getInstance() {
        return this.instance;
    }

    public final void setInstance(T t) {
        this.instance = t;
    }

    public final T getInstance(A arg) {
        T t = this.instance;
        if (t == null) {
            synchronized (this) {
                t = this.instance;
                if (t == null) {
                    Function1<? super A, ? extends T> function1 = this.creator;
                    Intrinsics.checkNotNull(function1);
                    T tInvoke = function1.invoke(arg);
                    this.instance = tInvoke;
                    this.creator = null;
                    t = tInvoke;
                }
            }
        }
        return t;
    }
}
