package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.adorkable.iosdialog.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.utils.CommonExtKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VipExpireDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VipExpireDialog;", "Landroidx/appcompat/app/AppCompatDialog;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Builder", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VipExpireDialog extends AppCompatDialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VipExpireDialog(Context context) {
        super(context, R.style.TopShowExitAnimation);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), android.R.color.transparent));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 48;
            attributes.dimAmount = 0.5f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    /* JADX INFO: compiled from: VipExpireDialog.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0007J\u0006\u0010\u0014\u001a\u00020\u0000J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0007J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\fJ\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VipExpireDialog$Builder;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", TvContractCompat.ProgramColumns.COLUMN_TITLE, "", "secondContent", "negativeText", "positiveText", "isNegativeGone", "", "isCancelable", "actionListener", "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "cancelListener", "setTitle", "setContent", FirebaseAnalytics.Param.CONTENT, "setNegativeGone", "setNegativeText", "text", "setPositiveText", "setActionListener", "setCancelListener", "setCancelable", "cancelable", "create", "Lcom/movieboxpro/android/view/dialog/VipExpireDialog;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private DialogAction.ActionListener cancelListener;
        private Context context;
        private boolean isCancelable;
        private boolean isNegativeGone;
        private String negativeText;
        private String positiveText;
        private String secondContent;
        private String title;

        public Builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.title = "Message";
            this.negativeText = "Cancel";
            this.positiveText = "OK";
            this.isCancelable = true;
        }

        public final Builder setTitle(String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.title = title;
            return this;
        }

        public final Builder setContent(String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            this.secondContent = content;
            return this;
        }

        public final Builder setNegativeGone() {
            this.isNegativeGone = true;
            return this;
        }

        public final Builder setNegativeText(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.negativeText = text;
            return this;
        }

        public final Builder setPositiveText(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.positiveText = text;
            return this;
        }

        public final Builder setActionListener(DialogAction.ActionListener actionListener) {
            Intrinsics.checkNotNullParameter(actionListener, "actionListener");
            this.actionListener = actionListener;
            return this;
        }

        public final Builder setCancelListener(DialogAction.ActionListener actionListener) {
            Intrinsics.checkNotNullParameter(actionListener, "actionListener");
            this.cancelListener = actionListener;
            return this;
        }

        public final Builder setCancelable(boolean cancelable) {
            this.isCancelable = cancelable;
            return this;
        }

        public final VipExpireDialog create() {
            final VipExpireDialog vipExpireDialog = new VipExpireDialog(this.context);
            View viewInflate = LayoutInflater.from(this.context).inflate(com.movieboxpro.androidtv.R.layout.vip_expire_date_layout, (ViewGroup) null);
            vipExpireDialog.setContentView(viewInflate);
            TextView textView = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tvCancel);
            textView.setText(this.negativeText);
            if (this.isNegativeGone) {
                Intrinsics.checkNotNull(textView);
                CommonExtKt.gone(textView);
            } else {
                Intrinsics.checkNotNull(textView);
                CommonExtKt.visible(textView);
            }
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.VipExpireDialog$Builder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    vipExpireDialog.dismiss();
                }
            });
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tvTitle)).setText(this.title);
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tvExpireDate)).setText(this.secondContent);
            TextView textView2 = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tvRenew);
            textView2.setText(this.positiveText);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.VipExpireDialog$Builder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VipExpireDialog.Builder.create$lambda$1(this.f$0, view);
                }
            });
            vipExpireDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.movieboxpro.android.view.dialog.VipExpireDialog$Builder$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    VipExpireDialog.Builder.create$lambda$2(this.f$0, dialogInterface);
                }
            });
            return vipExpireDialog;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void create$lambda$1(Builder builder, View view) {
            DialogAction.ActionListener actionListener = builder.actionListener;
            if (actionListener != null) {
                actionListener.onClick();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void create$lambda$2(Builder builder, DialogInterface dialogInterface) {
            DialogAction.ActionListener actionListener = builder.cancelListener;
            if (actionListener != null) {
                actionListener.onClick();
            }
        }
    }
}
