package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import androidx.core.os.BundleKt;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogTranslateProgressBinding;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TranslateProgressDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0016\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\b\u0010\f\u001a\u00020\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TranslateProgressDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "Lcom/movieboxpro/androidtv/databinding/DialogTranslateProgressBinding;", "<init>", "()V", "initListener", "", "initData", "updateProgress", "progress", "", "max", "initView", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TranslateProgressDialog extends BaseCenterDialog<DialogTranslateProgressBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
    }

    public TranslateProgressDialog() {
        super(R.layout.dialog_translate_progress);
    }

    public final void updateProgress(int progress, int max) {
        getBinding().progressBar.setMax(max);
        if (Build.VERSION.SDK_INT >= 24) {
            getBinding().progressBar.setProgress(progress, true);
        } else {
            getBinding().progressBar.setProgress(progress);
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
        Bundle arguments = getArguments();
        int i = arguments != null ? arguments.getInt("max", 0) : 0;
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCancelable(false);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setCanceledOnTouchOutside(false);
        }
        getBinding().progressBar.setMax(i);
    }

    /* JADX INFO: compiled from: TranslateProgressDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TranslateProgressDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/TranslateProgressDialog;", "max", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TranslateProgressDialog newInstance(int max) {
            TranslateProgressDialog translateProgressDialog = new TranslateProgressDialog();
            translateProgressDialog.setArguments(BundleKt.bundleOf(TuplesKt.to("max", Integer.valueOf(max))));
            return translateProgressDialog;
        }
    }
}
