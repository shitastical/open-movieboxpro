package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.androidtv.databinding.DialogConfirmSwitchDownloadPathBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX INFO: compiled from: ConfirmSwitchDownloadPathDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0017J\b\u0010\u0013\u001a\u00020\tH\u0016J\b\u0010\u0014\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ConfirmSwitchDownloadPathDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/DialogConfirmSwitchDownloadPathBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "setListener", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "initData", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConfirmSwitchDownloadPathDialog extends BaseBindingCenterDialogFragment {
    private DialogConfirmSwitchDownloadPathBinding binding;
    private DialogAction.ActionListener listener;

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
    }

    public final void setListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        DialogConfirmSwitchDownloadPathBinding dialogConfirmSwitchDownloadPathBindingInflate = DialogConfirmSwitchDownloadPathBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogConfirmSwitchDownloadPathBindingInflate, "inflate(...)");
        this.binding = dialogConfirmSwitchDownloadPathBindingInflate;
        if (dialogConfirmSwitchDownloadPathBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogConfirmSwitchDownloadPathBindingInflate = null;
        }
        return dialogConfirmSwitchDownloadPathBindingInflate.getRoot();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
        DialogConfirmSwitchDownloadPathBinding dialogConfirmSwitchDownloadPathBinding = this.binding;
        DialogConfirmSwitchDownloadPathBinding dialogConfirmSwitchDownloadPathBinding2 = null;
        if (dialogConfirmSwitchDownloadPathBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogConfirmSwitchDownloadPathBinding = null;
        }
        dialogConfirmSwitchDownloadPathBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ConfirmSwitchDownloadPathDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConfirmSwitchDownloadPathDialog.initListener$lambda$0(this.f$0, view);
            }
        });
        DialogConfirmSwitchDownloadPathBinding dialogConfirmSwitchDownloadPathBinding3 = this.binding;
        if (dialogConfirmSwitchDownloadPathBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogConfirmSwitchDownloadPathBinding2 = dialogConfirmSwitchDownloadPathBinding3;
        }
        dialogConfirmSwitchDownloadPathBinding2.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ConfirmSwitchDownloadPathDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.dismissAllowingStateLoss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(ConfirmSwitchDownloadPathDialog confirmSwitchDownloadPathDialog, View view) {
        confirmSwitchDownloadPathDialog.showLoadingView();
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(confirmSwitchDownloadPathDialog), null, null, new ConfirmSwitchDownloadPathDialog$initListener$1$1(confirmSwitchDownloadPathDialog, null), 3, null);
    }
}
