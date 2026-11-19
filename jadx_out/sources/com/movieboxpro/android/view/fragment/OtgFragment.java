package com.movieboxpro.android.view.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.OtgAccessEvent;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ToastUtils;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: OtgFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\"\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u0005H\u0002¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/fragment/OtgFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "", "resultCode", "intent", "Landroid/content/Intent;", "exit", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OtgFragment extends Fragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Uri otgRoot;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            try {
                startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 85);
            } catch (ActivityNotFoundException unused) {
                ToastUtils.showShort("Can't request access permission", new Object[0]);
                exit();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent != null && requestCode == 85) {
            otgRoot = intent.getData();
            PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, String.valueOf(otgRoot));
            Bundle arguments = getArguments();
            int i = arguments != null ? arguments.getInt("productId", 0) : 0;
            PrefsUtils.getInstance().putInt(Constant.Prefs.DOWNLOAD_DEVICE_ID, i);
            EventBus.getDefault().post(new OtgAccessEvent(i));
        } else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        exit();
    }

    private final void exit() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    /* JADX INFO: compiled from: OtgFragment.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/fragment/OtgFragment$Companion;", "", "<init>", "()V", "otgRoot", "Landroid/net/Uri;", "getOtgRoot", "()Landroid/net/Uri;", "setOtgRoot", "(Landroid/net/Uri;)V", "requestOtgRoot", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "productId", "", "productName", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Uri getOtgRoot() {
            return OtgFragment.otgRoot;
        }

        public final void setOtgRoot(Uri uri) {
            OtgFragment.otgRoot = uri;
        }

        public final void requestOtgRoot(FragmentActivity activity, int productId, String productName) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(productName, "productName");
            Bundle bundleBundleOf = BundleKt.bundleOf(TuplesKt.to("productId", Integer.valueOf(productId)), TuplesKt.to("productName", productName));
            OtgFragment otgFragment = new OtgFragment();
            otgFragment.setArguments(bundleBundleOf);
            activity.getSupportFragmentManager().beginTransaction().add(otgFragment, "OtgFragment").commitAllowingStateLoss();
        }
    }
}
