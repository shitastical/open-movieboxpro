package com.movieboxpro.android.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.app.App;
import com.movieboxpro.androidtv.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CommonExt.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0019\u0010\u0002\u001a\u00020\u0003\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u0006*\u00020\u0007H\u0086\b\u001a\u0019\u0010\u0002\u001a\u00020\u0003\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u0006*\u00020\u0004H\u0086\b\u001a\n\u0010\t\u001a\u00020\n*\u00020\u0001\u001a\n\u0010\u000b\u001a\u00020\u0003*\u00020\f\u001a\n\u0010\r\u001a\u00020\u0003*\u00020\f\u001a\n\u0010\u000e\u001a\u00020\u0003*\u00020\f\u001a\n\u0010\u000f\u001a\u00020\u0010*\u00020\f\u001a\n\u0010\u0011\u001a\u00020\u0010*\u00020\f\u001a\u0018\u0010\u0012\u001a\u00020\u0010*\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014\u001a&\u0010\u0015\u001a\u00020\u0003*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u0001\u001a\u000e\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0003*\u00020\u001d\u001a\u0012\u0010\u0019\u001a\u00020\u0003*\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0001\u001a\"\u0010\u001e\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0001¨\u0006!"}, d2 = {"dp2Px", "", "showDialogFragment", "", "Landroidx/fragment/app/Fragment;", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "Landroidx/fragment/app/FragmentActivity;", ExifInterface.GPS_DIRECTION_TRUE, "format2", "", "visible", "Landroid/view/View;", "gone", "invisible", "isVisible", "", "isGone", "checkIndexLegal", "list", "", "textShadow", "Landroid/widget/TextView;", "text", "textSize", "textColor", "colorInt", "colorId", "disableRefreshAnimation", "Landroidx/recyclerview/widget/RecyclerView;", "addText", "Lcom/movieboxpro/android/utils/SpanUtils;", "sizeSp", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class CommonExtKt {
    public static final int dp2Px(int i) {
        return DensityUtils.dp2px(App.getContext(), i);
    }

    public static final void showDialogFragment(Fragment fragment, DialogFragment dialogFragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        dialogFragment.show(fragment.getChildFragmentManager(), "DialogFragment");
    }

    public static final void showDialogFragment(FragmentActivity fragmentActivity, DialogFragment dialogFragment) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        dialogFragment.show(fragmentActivity.getSupportFragmentManager(), "DialogFragment");
    }

    public static final /* synthetic */ <T extends DialogFragment> void showDialogFragment(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        DialogFragment dialogFragment = (DialogFragment) DialogFragment.class.newInstance();
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        dialogFragment.show(supportFragmentManager, Reflection.getOrCreateKotlinClass(DialogFragment.class).getSimpleName());
    }

    public static final /* synthetic */ <T extends DialogFragment> void showDialogFragment(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        DialogFragment dialogFragment = (DialogFragment) DialogFragment.class.newInstance();
        FragmentManager childFragmentManager = fragment.getChildFragmentManager();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        dialogFragment.show(childFragmentManager, Reflection.getOrCreateKotlinClass(DialogFragment.class).getSimpleName());
    }

    public static final String format2(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    public static final void visible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }

    public static final void gone(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public static final void invisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(4);
    }

    public static final boolean isVisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 0;
    }

    public static final boolean isGone(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 8;
    }

    public static final boolean checkIndexLegal(int i, List<?> list) {
        return list != null && i >= 0 && i < list.size();
    }

    public static /* synthetic */ void textShadow$default(TextView textView, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 12;
        }
        if ((i3 & 4) != 0) {
            i2 = R.color.white;
        }
        textShadow(textView, str, i, i2);
    }

    public static final void textShadow(TextView textView, String text, int i, int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        SpanUtils.with(textView).append(text).setFontSize(i, true).setForegroundColor(ContextCompat.getColor(App.getContext(), i2)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
    }

    public static final int colorInt(int i) {
        return ContextCompat.getColor(App.getContext(), i);
    }

    public static final void disableRefreshAnimation(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
    }

    public static final void textColor(TextView textView, int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setTextColor(ContextCompat.getColor(App.getContext(), i));
    }

    public static final SpanUtils addText(SpanUtils spanUtils, String text, int i, int i2) {
        Intrinsics.checkNotNullParameter(spanUtils, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        spanUtils.append(text).setFontSize(i, true).setForegroundColor(ContextCompat.getColor(App.getContext(), i2));
        return spanUtils;
    }
}
