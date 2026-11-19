package com.movieboxpro.android;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.service.DealWatchNextService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveDataSet.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\u0013\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013J\u0006\u0010\u0017\u001a\u00020\u000bJ\u0013\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00028\u0000¢\u0006\u0002\u0010\u001aJ+\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00028\u00002\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u001cj\b\u0012\u0004\u0012\u00028\u0000`\u001d¢\u0006\u0002\u0010\u001eJ\u001b\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00028\u0000¢\u0006\u0002\u0010\u001fJ\u0014\u0010\u0018\u001a\u00020\u000b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u0013J\u0013\u0010!\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00028\u0000¢\u0006\u0002\u0010\u001aJ\u000e\u0010!\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u001b\u0010\"\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00028\u00002\u0006\u0010#\u001a\u00020\u0010¢\u0006\u0002\u0010$J\u0016\u0010\"\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0010R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/LiveDataSet;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/MutableLiveData;", "", "<init>", "()V", "emptyList", "internalList", "isEmpty", "", "setValue", "", "value", "getValue", "get", "position", "", "(I)Ljava/lang/Object;", "getList", "", "size", "getSize", "()I", "clear", DealWatchNextService.ADD_ACTION, "item", "(Ljava/lang/Object;)V", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Object;Ljava/util/Comparator;)V", "(ILjava/lang/Object;)V", FirebaseAnalytics.Param.ITEMS, "remove", "move", "newIndex", "(Ljava/lang/Object;I)V", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LiveDataSet<T> extends MutableLiveData<List<T>> {
    private final List<T> emptyList;
    private List<T> internalList;
    private final int size;

    public LiveDataSet() {
        ArrayList arrayList = new ArrayList();
        this.emptyList = arrayList;
        this.internalList = arrayList;
        this.size = arrayList.size();
    }

    public final boolean isEmpty() {
        return this.internalList.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(List<T> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.internalList = value;
        super.setValue(value);
    }

    @Override // androidx.lifecycle.LiveData
    public List<T> getValue() {
        return this.internalList;
    }

    public final T get(int position) {
        return this.internalList.get(position);
    }

    public final List<T> getList() {
        return CollectionsKt.toList(this.internalList);
    }

    public final int getSize() {
        return this.size;
    }

    public final void clear() {
        List<T> list = this.internalList;
        list.clear();
        setValue((List) list);
    }

    public final void add(T item) {
        List<T> list = this.internalList;
        list.add(item);
        setValue((List) list);
    }

    public final void add(T item, Comparator<T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<T> it = this.internalList.iterator();
        int i = 0;
        while (it.hasNext() && comparator.compare(item, it.next()) > 0) {
            i++;
        }
        List<T> list = this.internalList;
        list.add(i, item);
        setValue((List) list);
    }

    public final void add(int position, T item) {
        List<T> list = this.internalList;
        list.add(position, item);
        setValue((List) list);
    }

    public final void add(List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        List<T> list = this.internalList;
        ArrayList arrayList = new ArrayList();
        for (T t : items) {
            if (!list.contains(t)) {
                arrayList.add(t);
            }
        }
        list.addAll(arrayList);
        setValue((List) list);
    }

    public final void remove(T item) {
        List<T> list = this.internalList;
        list.remove(item);
        setValue((List) list);
    }

    public final void remove(int position) {
        List<T> list = this.internalList;
        list.remove(position);
        setValue((List) list);
    }

    public final void move(T item, int newIndex) {
        move(this.internalList.indexOf(item), newIndex);
    }

    public final void move(int from, int to) {
        List<T> list = this.internalList;
        T t = list.get(from);
        list.remove(from);
        if (from > to) {
            list.add(to, t);
        } else {
            list.add(to - 1, t);
        }
        setValue((List) list);
    }
}
