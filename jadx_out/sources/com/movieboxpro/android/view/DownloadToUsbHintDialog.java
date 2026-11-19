package com.movieboxpro.android.view;

import android.view.View;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.view.dialog.BaseCenterDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogDownloadToUsbHintBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadToUsbHintDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/DownloadToUsbHintDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "Lcom/movieboxpro/androidtv/databinding/DialogDownloadToUsbHintBinding;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "setListener", "", "initListener", "initData", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadToUsbHintDialog extends BaseCenterDialog<DialogDownloadToUsbHintBinding> {
    private DialogAction.ActionListener listener;

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
    }

    public DownloadToUsbHintDialog() {
        super(R.layout.dialog_download_to_usb_hint);
    }

    public final void setListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
        getBinding().llDownload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.DownloadToUsbHintDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadToUsbHintDialog.initListener$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(DownloadToUsbHintDialog downloadToUsbHintDialog, View view) {
        DialogAction.ActionListener actionListener = downloadToUsbHintDialog.listener;
        if (actionListener != null) {
            actionListener.onClick();
        }
    }
}
