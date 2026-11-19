package com.snowtop.diskpanda.utils.databinding;

import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.utils.LifecycleExtKt;
import com.movieboxpro.android.utils.ReflectExtKt;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: DataBindingFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\"\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u00042\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0096\u0002¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0002R\u001e\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "Lkotlin/properties/ReadOnlyProperty;", "Landroidx/fragment/app/Fragment;", "classes", "Ljava/lang/Class;", "fragment", "<init>", "(Ljava/lang/Class;Landroidx/fragment/app/Fragment;)V", "viewBinding", "getViewBinding", "()Landroidx/databinding/ViewDataBinding;", "setViewBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "bindView", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "lifecycle", "Landroidx/lifecycle/LifecycleOwner;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Landroidx/fragment/app/Fragment;Lkotlin/reflect/KProperty;)Landroidx/databinding/ViewDataBinding;", "destroyed", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FragmentBindingDelegate<T extends ViewDataBinding> implements ReadOnlyProperty<Fragment, T> {
    private final Method bindView;
    private LifecycleOwner lifecycle;
    private T viewBinding;

    public FragmentBindingDelegate(Class<T> classes, Fragment fragment) {
        Intrinsics.checkNotNullParameter(classes, "classes");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.bindView = ReflectExtKt.bindMethod(classes);
        this.lifecycle = fragment;
        Lifecycle lifecycle = fragment.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        LifecycleExtKt.addObserver(lifecycle, new Function0() { // from class: com.snowtop.diskpanda.utils.databinding.FragmentBindingDelegate$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FragmentBindingDelegate._init_$lambda$0(this.f$0);
            }
        });
    }

    @Override // kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Object getValue(Fragment fragment, KProperty kProperty) {
        return getValue2(fragment, (KProperty<?>) kProperty);
    }

    public final T getViewBinding() {
        return this.viewBinding;
    }

    public final void setViewBinding(T t) {
        this.viewBinding = t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(FragmentBindingDelegate fragmentBindingDelegate) {
        fragmentBindingDelegate.destroyed();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: getValue, reason: avoid collision after fix types in other method */
    public T getValue2(Fragment thisRef, KProperty<?> property) throws IllegalAccessException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        T t = this.viewBinding;
        if (t != null) {
            return t;
        }
        Object objInvoke = this.bindView.invoke(null, thisRef.getView());
        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type T of com.snowtop.diskpanda.utils.databinding.FragmentBindingDelegate");
        T t2 = (T) objInvoke;
        t2.setLifecycleOwner(this.lifecycle);
        this.viewBinding = t2;
        return t2;
    }

    private final void destroyed() {
        this.viewBinding = null;
    }
}
