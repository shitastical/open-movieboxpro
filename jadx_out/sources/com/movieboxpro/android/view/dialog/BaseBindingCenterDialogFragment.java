package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.adorkable.iosdialog.R;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.view.listener.LoadView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseBindingCenterDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\bH&J\b\u0010\u0012\u001a\u00020\bH&J\b\u0010\u0013\u001a\u00020\bH&J\b\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u0017\u001a\u00020\bH\u0016J\b\u0010\u0018\u001a\u00020\bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "Lcom/movieboxpro/android/view/listener/LoadView;", "<init>", "()V", "hud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityCreated", "onViewCreated", "view", "Landroid/view/View;", "setDialogSize", "mView", "initListener", "initData", "initView", "initDialog", "dialogDimAmount", "", "showLoadingView", "hideLoadingView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseBindingCenterDialogFragment extends AppCompatDialogFragment implements LoadView {
    private KProgressHUD hud;

    protected float dialogDimAmount() {
        return 0.0f;
    }

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDialog();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private final void setDialogSize(final View mView) {
        mView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BaseBindingCenterDialogFragment.setDialogSize$lambda$0(mView, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDialogSize$lambda$0(View view, View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int height = view2.getHeight();
        view2.getWidth();
        int screenHeight = (int) (((double) DensityUtils.getScreenHeight()) * 0.6d);
        if (height > screenHeight) {
            if (height < screenHeight) {
                screenHeight = -2;
            }
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, screenHeight));
        }
    }

    public void initDialog() {
        Window window;
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        Context context = getContext();
        if (context != null) {
            Intrinsics.checkNotNullExpressionValue(context.getResources().getDisplayMetrics(), "getDisplayMetrics(...)");
            attributes.width = -2;
            attributes.height = -2;
            attributes.dimAmount = dialogDimAmount();
            window.setAttributes(attributes);
        }
    }

    @Override // com.movieboxpro.android.view.listener.LoadView
    public void showLoadingView() {
        if (getContext() != null) {
            KProgressHUD kProgressHUD = this.hud;
            if (kProgressHUD != null) {
                Intrinsics.checkNotNull(kProgressHUD);
                if (kProgressHUD.isShowing()) {
                    return;
                }
            }
            KProgressHUD kProgressHUD2 = this.hud;
            if (kProgressHUD2 != null) {
                Intrinsics.checkNotNull(kProgressHUD2);
                if (kProgressHUD2.isShowing()) {
                    return;
                }
            }
            this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
        }
    }

    @Override // com.movieboxpro.android.view.listener.LoadView
    public void hideLoadingView() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null) {
            Intrinsics.checkNotNull(kProgressHUD);
            kProgressHUD.dismiss();
        }
    }
}
