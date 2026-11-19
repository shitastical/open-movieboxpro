package com.snowtop.diskpanda.utils.databinding;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;
import com.movieboxpro.android.utils.ActivityDataBindingDelegate;
import com.movieboxpro.android.utils.ActivityViewBindingDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DataBindingActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u0086\b\u001a\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0007*\u00020\u0004H\u0086\b\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\bH\u0086\b\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\tH\u0086\b¨\u0006\n"}, d2 = {"dataBinding", "Lcom/movieboxpro/android/utils/ActivityDataBindingDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "viewBinding", "Lcom/movieboxpro/android/utils/ActivityViewBindingDelegate;", "Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/FragmentActivity;", "Landroid/app/Activity;", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class DataBindingActivityKt {
    public static final /* synthetic */ <T extends ViewDataBinding> ActivityDataBindingDelegate<T> dataBinding(AppCompatActivity appCompatActivity) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return new ActivityDataBindingDelegate<>(ViewDataBinding.class, appCompatActivity);
    }

    public static final /* synthetic */ <T extends ViewBinding> ActivityViewBindingDelegate<T> viewBinding(AppCompatActivity appCompatActivity) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return new ActivityViewBindingDelegate<>(ViewBinding.class, appCompatActivity);
    }

    public static final /* synthetic */ <T extends ViewDataBinding> ActivityDataBindingDelegate<T> dataBinding(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return new ActivityDataBindingDelegate<>(ViewDataBinding.class, fragmentActivity);
    }

    public static final /* synthetic */ <T extends ViewDataBinding> ActivityDataBindingDelegate<T> dataBinding(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return new ActivityDataBindingDelegate<>(ViewDataBinding.class, activity);
    }
}
