package com.kingja.loadsir;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.kingja.loadsir.core.TargetContext;

/* JADX INFO: loaded from: classes5.dex */
public class LoadSirUtil {
    /* JADX WARN: Code duplicated, block: B:26:0x004e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x0050  */
    /* JADX WARN: Code duplicated, block: B:30:0x0059  */
    public static TargetContext getTargetContext(Object obj) {
        ViewGroup viewGroup;
        Context context;
        View childAt;
        int i;
        if (obj instanceof Activity) {
            Activity activity = (Activity) obj;
            viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
            context = activity;
        } else if (obj instanceof View) {
            View view = (View) obj;
            viewGroup = (ViewGroup) view.getParent();
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("The target must be within Activity, Fragment, View.");
        }
        int childCount = viewGroup == null ? 0 : viewGroup.getChildCount();
        if (obj instanceof View) {
            childAt = (View) obj;
            i = 0;
            while (true) {
                if (i < childCount) {
                    if (viewGroup.getChildAt(i) == childAt) {
                        break;
                    }
                    i++;
                }
            }
            if (childAt != null) {
                throw new IllegalArgumentException(String.format("enexpected error when register LoadSir in %s", obj.getClass().getSimpleName()));
            }
            if (viewGroup != null) {
                viewGroup.removeView(childAt);
            }
            return new TargetContext(context, viewGroup, childAt, i);
        }
        childAt = viewGroup != null ? viewGroup.getChildAt(0) : null;
        i = 0;
        if (childAt != null) {
            throw new IllegalArgumentException(String.format("enexpected error when register LoadSir in %s", obj.getClass().getSimpleName()));
        }
        if (viewGroup != null) {
            viewGroup.removeView(childAt);
        }
        return new TargetContext(context, viewGroup, childAt, i);
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
