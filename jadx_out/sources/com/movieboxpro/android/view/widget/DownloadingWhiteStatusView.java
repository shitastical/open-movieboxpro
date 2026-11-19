package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.core.app.NotificationCompat;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DownloadingWhiteStatusViewBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadingWhiteStatusView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0006\u0010\u0010\u001a\u00020\u0007J\u0016\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007J\u0006\u0010\u0014\u001a\u00020\u000fJ\u0006\u0010\u0015\u001a\u00020\u000fJ\u0006\u0010\u0016\u001a\u00020\u000fJ\u0006\u0010\u0017\u001a\u00020\u000fJ\u0006\u0010\u0018\u001a\u00020\u000fJ\u0006\u0010\u0019\u001a\u00020\u000fR\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/view/widget/DownloadingWhiteStatusView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mContext", NotificationCompat.CATEGORY_STATUS, "binding", "Lcom/movieboxpro/androidtv/databinding/DownloadingWhiteStatusViewBinding;", "initView", "", "getDownloadStatus", "setProgress", "max", "progress", "resumeStatus", "setPause", "setWaiting", "setStartLoading", "setFail", "downloadFinish", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadingWhiteStatusView extends FrameLayout {
    private final DownloadingWhiteStatusViewBinding binding;
    private final Context mContext;
    private int status;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DownloadingWhiteStatusView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DownloadingWhiteStatusView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void initView() {
    }

    public /* synthetic */ DownloadingWhiteStatusView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadingWhiteStatusView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        DownloadingWhiteStatusViewBinding downloadingWhiteStatusViewBindingInflate = DownloadingWhiteStatusViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(downloadingWhiteStatusViewBindingInflate, "inflate(...)");
        this.binding = downloadingWhiteStatusViewBindingInflate;
        initView();
    }

    /* JADX INFO: renamed from: getDownloadStatus, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final void setProgress(int max, int progress) {
        this.status = 6;
        DownloadingWhiteStatusViewBinding downloadingWhiteStatusViewBinding = this.binding;
        ImageView ivWaiting = downloadingWhiteStatusViewBinding.ivWaiting;
        Intrinsics.checkNotNullExpressionValue(ivWaiting, "ivWaiting");
        CommonExtKt.gone(ivWaiting);
        ProgressBar loading = downloadingWhiteStatusViewBinding.loading;
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.gone(loading);
        CircleProgressBar circleProgressBar = downloadingWhiteStatusViewBinding.circleProgressBar;
        Intrinsics.checkNotNullExpressionValue(circleProgressBar, "circleProgressBar");
        CommonExtKt.visible(circleProgressBar);
        if (max == 0) {
            downloadingWhiteStatusViewBinding.circleProgressBar.setMax(1);
            downloadingWhiteStatusViewBinding.circleProgressBar.setProgress(0);
        } else {
            downloadingWhiteStatusViewBinding.circleProgressBar.setMax(max);
            downloadingWhiteStatusViewBinding.circleProgressBar.setProgress(progress);
        }
    }

    public final void resumeStatus() {
        this.status = 0;
        DownloadingWhiteStatusViewBinding downloadingWhiteStatusViewBinding = this.binding;
        downloadingWhiteStatusViewBinding.ivWaiting.setImageResource(R.mipmap.ic_download_status_pause);
        ImageView ivWaiting = downloadingWhiteStatusViewBinding.ivWaiting;
        Intrinsics.checkNotNullExpressionValue(ivWaiting, "ivWaiting");
        CommonExtKt.visible(ivWaiting);
        CircleProgressBar circleProgressBar = downloadingWhiteStatusViewBinding.circleProgressBar;
        Intrinsics.checkNotNullExpressionValue(circleProgressBar, "circleProgressBar");
        CommonExtKt.gone(circleProgressBar);
        ProgressBar loading = downloadingWhiteStatusViewBinding.loading;
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.gone(loading);
    }

    public final void setPause() {
        this.status = 2;
        DownloadingWhiteStatusViewBinding downloadingWhiteStatusViewBinding = this.binding;
        downloadingWhiteStatusViewBinding.ivWaiting.setImageResource(R.mipmap.ic_download_status_waiting);
        ImageView ivWaiting = downloadingWhiteStatusViewBinding.ivWaiting;
        Intrinsics.checkNotNullExpressionValue(ivWaiting, "ivWaiting");
        CommonExtKt.visible(ivWaiting);
        CircleProgressBar circleProgressBar = downloadingWhiteStatusViewBinding.circleProgressBar;
        Intrinsics.checkNotNullExpressionValue(circleProgressBar, "circleProgressBar");
        CommonExtKt.gone(circleProgressBar);
        ProgressBar loading = downloadingWhiteStatusViewBinding.loading;
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.gone(loading);
    }

    public final void setWaiting() {
        this.status = 8;
        this.binding.ivWaiting.setImageResource(R.mipmap.ic_download_status_waiting_download);
        ImageView ivWaiting = this.binding.ivWaiting;
        Intrinsics.checkNotNullExpressionValue(ivWaiting, "ivWaiting");
        CommonExtKt.visible(ivWaiting);
        CircleProgressBar circleProgressBar = this.binding.circleProgressBar;
        Intrinsics.checkNotNullExpressionValue(circleProgressBar, "circleProgressBar");
        CommonExtKt.gone(circleProgressBar);
        ProgressBar loading = this.binding.loading;
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.gone(loading);
    }

    public final void setStartLoading() {
        this.status = 4;
        ProgressBar loading = this.binding.loading;
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.visible(loading);
        ImageView ivWaiting = this.binding.ivWaiting;
        Intrinsics.checkNotNullExpressionValue(ivWaiting, "ivWaiting");
        CommonExtKt.gone(ivWaiting);
        CircleProgressBar circleProgressBar = this.binding.circleProgressBar;
        Intrinsics.checkNotNullExpressionValue(circleProgressBar, "circleProgressBar");
        CommonExtKt.gone(circleProgressBar);
    }

    public final void setFail() {
        this.status = 7;
        ProgressBar loading = this.binding.loading;
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.gone(loading);
        this.binding.ivWaiting.setImageResource(R.mipmap.ic_download_status_waiting);
        ImageView ivWaiting = this.binding.ivWaiting;
        Intrinsics.checkNotNullExpressionValue(ivWaiting, "ivWaiting");
        CommonExtKt.visible(ivWaiting);
        CircleProgressBar circleProgressBar = this.binding.circleProgressBar;
        Intrinsics.checkNotNullExpressionValue(circleProgressBar, "circleProgressBar");
        CommonExtKt.gone(circleProgressBar);
    }

    public final void downloadFinish() {
        this.status = 1;
        this.binding.ivWaiting.setImageResource(R.mipmap.ic_download_status_finish);
        ProgressBar loading = this.binding.loading;
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        CommonExtKt.gone(loading);
        ImageView ivWaiting = this.binding.ivWaiting;
        Intrinsics.checkNotNullExpressionValue(ivWaiting, "ivWaiting");
        CommonExtKt.visible(ivWaiting);
        CircleProgressBar circleProgressBar = this.binding.circleProgressBar;
        Intrinsics.checkNotNullExpressionValue(circleProgressBar, "circleProgressBar");
        CommonExtKt.gone(circleProgressBar);
    }
}
