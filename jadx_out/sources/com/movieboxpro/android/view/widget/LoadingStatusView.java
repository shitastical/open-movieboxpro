package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: LoadingStatusView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0007J\u0006\u0010\u001a\u001a\u00020\u0014J\u0006\u0010\u001b\u001a\u00020\u0014J\u000e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/widget/LoadingStatusView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "progressView", "Lcom/movieboxpro/android/view/widget/LoadingView;", "tvErrorMsg", "Landroid/widget/TextView;", "tvRetry", "llRetry", "Landroid/widget/LinearLayout;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "setListener", "", "initView", "view", "Landroid/view/View;", "setTextColor", "color", "hideLoading", "showLoading", "showError", NotificationCompat.CATEGORY_MESSAGE, "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoadingStatusView extends FrameLayout {
    private DialogAction.ActionListener listener;
    private LinearLayout llRetry;
    private LoadingView progressView;
    private TextView tvErrorMsg;
    private TextView tvRetry;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoadingStatusView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoadingStatusView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ LoadingStatusView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingStatusView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.loading_status_view, this);
        Intrinsics.checkNotNull(viewInflate);
        initView(viewInflate);
    }

    public final void setListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    private final void initView(View view) {
        View viewFindViewById = view.findViewById(R.id.progressBar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.progressView = (LoadingView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.llRetry);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.llRetry = (LinearLayout) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.tvErrorMsg);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.tvErrorMsg = (TextView) viewFindViewById3;
        View viewFindViewById4 = view.findViewById(R.id.tvRetry);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        TextView textView = (TextView) viewFindViewById4;
        this.tvRetry = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvRetry");
            textView = null;
        }
        textView.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.widget.LoadingStatusView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view2, int i, KeyEvent keyEvent) {
                return LoadingStatusView.initView$lambda$0(this.f$0, view2, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initView$lambda$0(LoadingStatusView loadingStatusView, View view, int i, KeyEvent keyEvent) {
        if ((i == 66 || i == 23) && keyEvent.getAction() == 0) {
            DialogAction.ActionListener actionListener = loadingStatusView.listener;
            if (actionListener == null) {
                return true;
            }
            actionListener.onClick();
            return true;
        }
        if (keyEvent.getAction() == 1) {
            return true;
        }
        if (i != 19) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    public final void setTextColor(int color) {
        TextView textView = this.tvErrorMsg;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvErrorMsg");
            textView = null;
        }
        textView.setTextColor(color);
    }

    public final void hideLoading() {
        setVisibility(8);
    }

    public final void showLoading() {
        setVisibility(0);
        LoadingView loadingView = this.progressView;
        LinearLayout linearLayout = null;
        if (loadingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressView");
            loadingView = null;
        }
        CommonExtKt.visible(loadingView);
        LinearLayout linearLayout2 = this.llRetry;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llRetry");
        } else {
            linearLayout = linearLayout2;
        }
        CommonExtKt.gone(linearLayout);
    }

    public final void showError(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        setVisibility(0);
        LoadingView loadingView = this.progressView;
        LinearLayout linearLayout = null;
        if (loadingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressView");
            loadingView = null;
        }
        CommonExtKt.gone(loadingView);
        TextView textView = this.tvErrorMsg;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvErrorMsg");
            textView = null;
        }
        textView.setText(msg);
        LinearLayout linearLayout2 = this.llRetry;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llRetry");
        } else {
            linearLayout = linearLayout2;
        }
        CommonExtKt.visible(linearLayout);
    }
}
