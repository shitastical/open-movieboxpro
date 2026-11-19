package com.movieboxpro.android.view.dialog;

import android.view.View;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogChoosePlaySpeedBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChoosePlaySpeedDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "Lcom/movieboxpro/androidtv/databinding/DialogChoosePlaySpeedBinding;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog$ChoosePlaySpeedListener;", "setListener", "", "initListener", "initData", "initView", "ChoosePlaySpeedListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChoosePlaySpeedDialog extends BaseCenterDialog<DialogChoosePlaySpeedBinding> {
    private ChoosePlaySpeedListener listener;

    /* JADX INFO: compiled from: ChoosePlaySpeedDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog$ChoosePlaySpeedListener;", "", "onChooseSpeed", "", "speed", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChoosePlaySpeedListener {
        void onChooseSpeed(float speed);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
    }

    public ChoosePlaySpeedDialog() {
        super(R.layout.dialog_choose_play_speed);
    }

    public final void setListener(ChoosePlaySpeedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
        DialogChoosePlaySpeedBinding binding = getBinding();
        binding.tvSpeed05.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlaySpeedDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlaySpeedDialog.initListener$lambda$5$lambda$0(this.f$0, view);
            }
        });
        binding.tvSpeed1.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlaySpeedDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlaySpeedDialog.initListener$lambda$5$lambda$1(this.f$0, view);
            }
        });
        binding.tvSpeed125.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlaySpeedDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlaySpeedDialog.initListener$lambda$5$lambda$2(this.f$0, view);
            }
        });
        binding.tvSpeed15.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlaySpeedDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlaySpeedDialog.initListener$lambda$5$lambda$3(this.f$0, view);
            }
        });
        binding.tvSpeed2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlaySpeedDialog$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlaySpeedDialog.initListener$lambda$5$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5$lambda$0(ChoosePlaySpeedDialog choosePlaySpeedDialog, View view) {
        ChoosePlaySpeedListener choosePlaySpeedListener = choosePlaySpeedDialog.listener;
        if (choosePlaySpeedListener != null) {
            choosePlaySpeedListener.onChooseSpeed(0.5f);
        }
        choosePlaySpeedDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5$lambda$1(ChoosePlaySpeedDialog choosePlaySpeedDialog, View view) {
        ChoosePlaySpeedListener choosePlaySpeedListener = choosePlaySpeedDialog.listener;
        if (choosePlaySpeedListener != null) {
            choosePlaySpeedListener.onChooseSpeed(1.0f);
        }
        choosePlaySpeedDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5$lambda$2(ChoosePlaySpeedDialog choosePlaySpeedDialog, View view) {
        ChoosePlaySpeedListener choosePlaySpeedListener = choosePlaySpeedDialog.listener;
        if (choosePlaySpeedListener != null) {
            choosePlaySpeedListener.onChooseSpeed(1.25f);
        }
        choosePlaySpeedDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5$lambda$3(ChoosePlaySpeedDialog choosePlaySpeedDialog, View view) {
        ChoosePlaySpeedListener choosePlaySpeedListener = choosePlaySpeedDialog.listener;
        if (choosePlaySpeedListener != null) {
            choosePlaySpeedListener.onChooseSpeed(1.5f);
        }
        choosePlaySpeedDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5$lambda$4(ChoosePlaySpeedDialog choosePlaySpeedDialog, View view) {
        ChoosePlaySpeedListener choosePlaySpeedListener = choosePlaySpeedDialog.listener;
        if (choosePlaySpeedListener != null) {
            choosePlaySpeedListener.onChooseSpeed(2.0f);
        }
        choosePlaySpeedDialog.dismiss();
    }
}
