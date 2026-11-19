package com.movieboxpro.android.utils;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.androidtv.R;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: ActivityDataBindingDelegate.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\"\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u00042\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0096\u0002¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/utils/ActivityDataBindingDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "Lkotlin/properties/ReadOnlyProperty;", "Landroid/app/Activity;", "classes", "Ljava/lang/Class;", "act", "<init>", "(Ljava/lang/Class;Landroid/app/Activity;)V", "layoutInflater", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "viewBinding", "Landroidx/databinding/ViewDataBinding;", "lifecycle", "Landroidx/lifecycle/LifecycleOwner;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Landroid/app/Activity;Lkotlin/reflect/KProperty;)Landroidx/databinding/ViewDataBinding;", "destroyed", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActivityDataBindingDelegate<T extends ViewDataBinding> implements ReadOnlyProperty<Activity, T> {
    private final Activity act;
    private final Method layoutInflater;
    private LifecycleOwner lifecycle;
    private T viewBinding;

    /* JADX WARN: Multi-variable type inference failed */
    public ActivityDataBindingDelegate(Class<T> classes, Activity act) {
        Intrinsics.checkNotNullParameter(classes, "classes");
        Intrinsics.checkNotNullParameter(act, "act");
        this.act = act;
        this.layoutInflater = ReflectExtKt.inflateMethod(classes);
        if (act instanceof FragmentActivity) {
            this.lifecycle = (LifecycleOwner) act;
            Lifecycle lifecycle = ((FragmentActivity) act).getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
            LifecycleExtKt.addObserver(lifecycle, new Function0() { // from class: com.movieboxpro.android.utils.ActivityDataBindingDelegate$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ActivityDataBindingDelegate._init_$lambda$0(this.f$0);
                }
            });
            return;
        }
        if (act instanceof AppCompatActivity) {
            this.lifecycle = (LifecycleOwner) act;
            Lifecycle lifecycle2 = ((AppCompatActivity) act).getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
            LifecycleExtKt.addObserver(lifecycle2, new Function0() { // from class: com.movieboxpro.android.utils.ActivityDataBindingDelegate$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ActivityDataBindingDelegate._init_$lambda$1(this.f$0);
                }
            });
        }
    }

    @Override // kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Object getValue(Activity activity, KProperty kProperty) {
        return getValue2(activity, (KProperty<?>) kProperty);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(ActivityDataBindingDelegate activityDataBindingDelegate) {
        activityDataBindingDelegate.destroyed();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$1(ActivityDataBindingDelegate activityDataBindingDelegate) {
        activityDataBindingDelegate.destroyed();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: getValue, reason: avoid collision after fix types in other method */
    public T getValue2(Activity thisRef, KProperty<?> property) throws IllegalAccessException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        T t = this.viewBinding;
        if (t == null) {
            Object objInvoke = this.layoutInflater.invoke(null, thisRef.getLayoutInflater());
            Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type T of com.movieboxpro.android.utils.ActivityDataBindingDelegate");
            t = (T) objInvoke;
            thisRef.setContentView(t.getRoot());
            LinearLayout linearLayout = (LinearLayout) t.getRoot().findViewById(R.id.ll_back);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.utils.ActivityDataBindingDelegate$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDataBindingDelegate.getValue$lambda$5$lambda$3(this.f$0, view);
                    }
                });
            }
            t.setLifecycleOwner(this.lifecycle);
            this.viewBinding = t;
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getValue$lambda$5$lambda$3(ActivityDataBindingDelegate activityDataBindingDelegate, View view) {
        activityDataBindingDelegate.act.finish();
    }

    private final void destroyed() {
        this.viewBinding = null;
    }
}
