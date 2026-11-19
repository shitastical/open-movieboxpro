package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentReleasedHintBinding;
import com.owen.focus.FocusBorder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReleasedHintDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\fH\u0002J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\fH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReleasedHintDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentReleasedHintBinding;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "favorite", "", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "onStart", "", "setClickListener", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initView", "focusScale", "v", "scale", "", "initListener", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ReleasedHintDialog extends BaseTransparentDialogFragment<FragmentReleasedHintBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean favorite;
    private View.OnClickListener listener;
    private FocusBorder mFocusBorder;

    public ReleasedHintDialog() {
        super(R.layout.fragment_released_hint);
    }

    /* JADX INFO: compiled from: ReleasedHintDialog.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReleasedHintDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ReleasedHintDialog;", "favorite", "", "boxType", "", "unknown", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ ReleasedHintDialog newInstance$default(Companion companion, boolean z, int i, boolean z2, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                z2 = false;
            }
            return companion.newInstance(z, i, z2);
        }

        public final ReleasedHintDialog newInstance(boolean favorite, int boxType, boolean unknown) {
            ReleasedHintDialog releasedHintDialog = new ReleasedHintDialog();
            Bundle bundle = new Bundle();
            bundle.putBoolean("favorite", favorite);
            bundle.putInt("boxType", boxType);
            bundle.putBoolean("unknown", unknown);
            releasedHintDialog.setArguments(bundle);
            return releasedHintDialog;
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            getResources().getDisplayMetrics();
            attributes.width = -1;
            attributes.height = -1;
            attributes.gravity = 17;
            attributes.dimAmount = 0.9f;
            window.setAttributes(attributes);
        }
    }

    public final void setClickListener(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    private final void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        Bundle arguments = getArguments();
        this.favorite = arguments != null ? arguments.getBoolean("favorite", false) : false;
        Bundle arguments2 = getArguments();
        int i = arguments2 != null ? arguments2.getInt("boxType") : 1;
        if (this.favorite) {
            getBinding().tvClose.setText("Done");
            if (i == 1) {
                getBinding().tvHint.setText("You have added the movie to your favorite,\nand will be notified when  update.");
            } else {
                getBinding().tvHint.setText("You have added the TV show to your favorite,\nand will be notified when  update.");
            }
        } else {
            getBinding().tvClose.setText("Add Favorite");
            if (i == 1) {
                getBinding().tvHint.setText("You can add the movie to favorite,\nyou will get notice when it update.");
            } else {
                getBinding().tvHint.setText("You can add the TV show to favorite,\nyou will get notice when it update.");
            }
        }
        Bundle arguments3 = getArguments();
        if (arguments3 == null || !arguments3.getBoolean("unknown", false)) {
            return;
        }
        getBinding().tvName.setText("Unknown");
    }

    private final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    private final void initListener() {
        getBinding().tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ReleasedHintDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReleasedHintDialog.initListener$lambda$1(this.f$0, view);
            }
        });
        getBinding().tvClose.requestFocus();
        getBinding().tvClose.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.ReleasedHintDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ReleasedHintDialog.initListener$lambda$2(this.f$0, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(ReleasedHintDialog releasedHintDialog, View view) {
        View.OnClickListener onClickListener;
        if (!releasedHintDialog.favorite && (onClickListener = releasedHintDialog.listener) != null) {
            onClickListener.onClick(view);
        }
        releasedHintDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(ReleasedHintDialog releasedHintDialog, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            releasedHintDialog.focusScale(view, 1.1f);
        }
    }
}
