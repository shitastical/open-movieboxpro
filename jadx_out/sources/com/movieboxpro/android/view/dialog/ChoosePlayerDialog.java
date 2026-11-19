package com.movieboxpro.android.view.dialog;

import android.view.View;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogChoosePlayerBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChoosePlayerDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlayerDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "Lcom/movieboxpro/androidtv/databinding/DialogChoosePlayerBinding;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ChoosePlayerDialog$ChoosePlayerListener;", "setListener", "", "initListener", "initData", "initView", "ChoosePlayerListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChoosePlayerDialog extends BaseCenterDialog<DialogChoosePlayerBinding> {
    private ChoosePlayerListener listener;

    /* JADX INFO: compiled from: ChoosePlayerDialog.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlayerDialog$ChoosePlayerListener;", "", "playWithOther", "", "play", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChoosePlayerListener {
        void play();

        void playWithOther();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
    }

    public ChoosePlayerDialog() {
        super(R.layout.dialog_choose_player);
    }

    public final void setListener(ChoosePlayerListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
        getBinding().llOther.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayerDialog.initListener$lambda$0(this.f$0, view);
            }
        });
        getBinding().llPlay.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayerDialog.initListener$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(ChoosePlayerDialog choosePlayerDialog, View view) {
        ChoosePlayerListener choosePlayerListener = choosePlayerDialog.listener;
        if (choosePlayerListener != null) {
            choosePlayerListener.playWithOther();
        }
        choosePlayerDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(ChoosePlayerDialog choosePlayerDialog, View view) {
        ChoosePlayerListener choosePlayerListener = choosePlayerDialog.listener;
        if (choosePlayerListener != null) {
            choosePlayerListener.play();
        }
        choosePlayerDialog.dismiss();
    }
}
