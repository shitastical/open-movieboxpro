package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.os.BundleKt;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogChoosePlayerEngineBinding;
import com.owen.focus.FocusBorder;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChoosePlayerEngineDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0018\u0019B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bJ\b\u0010\f\u001a\u00020\nH\u0016J!\u0010\r\u001a\u00020\n2\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f\"\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\nH\u0016J\b\u0010\u0017\u001a\u00020\nH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlayerEngineDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "Lcom/movieboxpro/androidtv/databinding/DialogChoosePlayerEngineBinding;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "choosePlayerEngineListener", "Lcom/movieboxpro/android/view/dialog/ChoosePlayerEngineDialog$ChoosePlayerEngineListener;", "setChoosePlayerEngineListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "initListener", "setViewFocusListener", "args", "", "Landroid/view/View;", "([Landroid/view/View;)V", "focusScale", "v", "scale", "", "initData", "initView", "ChoosePlayerEngineListener", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChoosePlayerEngineDialog extends BaseCenterDialog<DialogChoosePlayerEngineBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ChoosePlayerEngineListener choosePlayerEngineListener;
    private FocusBorder mFocusBorder;

    /* JADX INFO: compiled from: ChoosePlayerEngineDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlayerEngineDialog$ChoosePlayerEngineListener;", "", "onChoosePlayerEngine", "", "engine", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChoosePlayerEngineListener {
        void onChoosePlayerEngine(String engine);
    }

    public ChoosePlayerEngineDialog() {
        super(R.layout.dialog_choose_player_engine);
    }

    public final void setChoosePlayerEngineListener(ChoosePlayerEngineListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.choosePlayerEngineListener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
        LinearLayout llExo = getBinding().llExo;
        Intrinsics.checkNotNullExpressionValue(llExo, "llExo");
        LinearLayout llVlc = getBinding().llVlc;
        Intrinsics.checkNotNullExpressionValue(llVlc, "llVlc");
        LinearLayout llIjk = getBinding().llIjk;
        Intrinsics.checkNotNullExpressionValue(llIjk, "llIjk");
        setViewFocusListener(llExo, llVlc, llIjk);
        getBinding().llExo.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerEngineDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayerEngineDialog.initListener$lambda$0(this.f$0, view);
            }
        });
        getBinding().llVlc.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerEngineDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayerEngineDialog.initListener$lambda$1(this.f$0, view);
            }
        });
        getBinding().llIjk.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerEngineDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayerEngineDialog.initListener$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(ChoosePlayerEngineDialog choosePlayerEngineDialog, View view) {
        ChoosePlayerEngineListener choosePlayerEngineListener = choosePlayerEngineDialog.choosePlayerEngineListener;
        if (choosePlayerEngineListener != null) {
            choosePlayerEngineListener.onChoosePlayerEngine("exo");
        }
        choosePlayerEngineDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(ChoosePlayerEngineDialog choosePlayerEngineDialog, View view) {
        ChoosePlayerEngineListener choosePlayerEngineListener = choosePlayerEngineDialog.choosePlayerEngineListener;
        if (choosePlayerEngineListener != null) {
            choosePlayerEngineListener.onChoosePlayerEngine("vlc");
        }
        choosePlayerEngineDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(ChoosePlayerEngineDialog choosePlayerEngineDialog, View view) {
        ChoosePlayerEngineListener choosePlayerEngineListener = choosePlayerEngineDialog.choosePlayerEngineListener;
        if (choosePlayerEngineListener != null) {
            choosePlayerEngineListener.onChoosePlayerEngine("ijk");
        }
        choosePlayerEngineDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$4$lambda$3(ChoosePlayerEngineDialog choosePlayerEngineDialog, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            choosePlayerEngineDialog.focusScale(view, 1.1f);
        }
        FocusBorder focusBorder = choosePlayerEngineDialog.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(z);
    }

    private final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
        Bundle arguments = getArguments();
        if (arguments != null ? arguments.getBoolean("hls", false) : false) {
            LinearLayout llIjk = getBinding().llIjk;
            Intrinsics.checkNotNullExpressionValue(llIjk, "llIjk");
            CommonExtKt.gone(llIjk);
        }
    }

    /* JADX INFO: compiled from: ChoosePlayerEngineDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlayerEngineDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChoosePlayerEngineDialog;", "hls", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChoosePlayerEngineDialog newInstance(boolean hls) {
            ChoosePlayerEngineDialog choosePlayerEngineDialog = new ChoosePlayerEngineDialog();
            choosePlayerEngineDialog.setArguments(BundleKt.bundleOf(TuplesKt.to("hls", Boolean.valueOf(hls))));
            return choosePlayerEngineDialog;
        }
    }

    private final void setViewFocusListener(View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerEngineDialog$$ExternalSyntheticLambda0
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    ChoosePlayerEngineDialog.setViewFocusListener$lambda$4$lambda$3(this.f$0, view2, z);
                }
            });
        }
    }
}
