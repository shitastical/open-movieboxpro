package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.movieboxpro.android.view.activity.DownloadActivity;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogNoEnoughSpaceBinding;
import kotlin.Metadata;

/* JADX INFO: compiled from: NoEnoughSpaceDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/NoEnoughSpaceDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "Lcom/movieboxpro/androidtv/databinding/DialogNoEnoughSpaceBinding;", "<init>", "()V", "onStart", "", "initListener", "initData", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NoEnoughSpaceDialog extends BaseCenterDialog<DialogNoEnoughSpaceBinding> {
    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
    }

    public NoEnoughSpaceDialog() {
        super(R.layout.dialog_no_enough_space);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.5d);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
        getBinding().tvManage.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.NoEnoughSpaceDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoEnoughSpaceDialog.initListener$lambda$1(this.f$0, view);
            }
        });
        getBinding().tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.NoEnoughSpaceDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(NoEnoughSpaceDialog noEnoughSpaceDialog, View view) {
        DownloadActivity.INSTANCE.start(noEnoughSpaceDialog.getContext());
        noEnoughSpaceDialog.dismiss();
    }
}
