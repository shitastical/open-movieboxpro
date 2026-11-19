package com.movieboxpro.android.view.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentManager;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.view.fragment.DownloadFragment;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityDownloadBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "Lcom/movieboxpro/androidtv/databinding/ActivityDownloadBinding;", "<init>", "()V", "fragment", "Lcom/movieboxpro/android/view/fragment/DownloadFragment;", "getLayoutResId", "", "initListener", "", "initData", "onBackPressed", "initView", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadActivity extends BaseSimpleActivity<ActivityDownloadBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private DownloadFragment fragment;

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_download;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        FrameLayout frameLayout = getBinding().frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
        CommonExtKt.visible(frameLayout);
        this.fragment = new DownloadFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        DownloadFragment downloadFragment = this.fragment;
        if (downloadFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            downloadFragment = null;
        }
        FragmentUtils.add(supportFragmentManager, downloadFragment, R.id.frameLayout);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        DownloadFragment downloadFragment = this.fragment;
        if (downloadFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            downloadFragment = null;
        }
        if (downloadFragment.exitEditDownload()) {
            return;
        }
        super.onBackPressed();
    }

    /* JADX INFO: compiled from: DownloadActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            if (context != null) {
                context.startActivity(new Intent(context, (Class<?>) DownloadActivity.class));
            }
        }
    }
}
