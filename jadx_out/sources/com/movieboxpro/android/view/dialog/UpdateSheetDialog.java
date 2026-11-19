package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateSheetDialog extends AppCompatDialog {
    private final Context mContext;

    public UpdateSheetDialog(Context context) {
        super(context, R.style.BottomSheetFullScreenDialog);
        this.mContext = context;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawable(ContextCompat.getDrawable(this.mContext, android.R.color.transparent));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.dimAmount = 0.0f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private Context context;
        private String secondContent;
        private String title = "Message";
        private String negativeText = "Cancel";
        private String positiveText = "OK";
        private boolean isNegativeGone = false;
        private boolean isCancelable = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.secondContent = content;
            return this;
        }

        public Builder setNegativeGone() {
            this.isNegativeGone = true;
            return this;
        }

        public Builder setNegativeText(String text) {
            this.negativeText = text;
            return this;
        }

        public Builder setPositiveText(String text) {
            this.positiveText = text;
            return this;
        }

        public Builder setActionListener(DialogAction.ActionListener actionListener) {
            this.actionListener = actionListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.isCancelable = cancelable;
            return this;
        }

        public UpdateSheetDialog create() {
            final UpdateSheetDialog updateSheetDialog = new UpdateSheetDialog(this.context);
            View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.update_dialog_layout, (ViewGroup) null);
            updateSheetDialog.setContentView(viewInflate);
            updateSheetDialog.setCancelable(this.isCancelable);
            updateSheetDialog.setCanceledOnTouchOutside(this.isCancelable);
            ((TextView) viewInflate.findViewById(R.id.tv_title)).setText(this.title);
            TextView textView = (TextView) viewInflate.findViewById(R.id.tv_content);
            if (Build.VERSION.SDK_INT >= 24) {
                textView.setText(Html.fromHtml(this.secondContent, 63));
            } else {
                textView.setText(Html.fromHtml(this.secondContent));
            }
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_negative);
            if (this.isNegativeGone) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
            }
            textView2.setText(this.negativeText);
            TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_positive);
            textView3.setText(this.positiveText);
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.UpdateSheetDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                    }
                    if (Builder.this.isCancelable) {
                        updateSheetDialog.dismiss();
                    }
                }
            });
            textView2.setOnClickListener(new View.OnClickListener(this) { // from class: com.movieboxpro.android.view.dialog.UpdateSheetDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    updateSheetDialog.dismiss();
                }
            });
            return updateSheetDialog;
        }

        private void setDialogSize(final View view, final Context context) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: com.movieboxpro.android.view.dialog.UpdateSheetDialog.Builder.3
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    int height = v.getHeight();
                    int width = v.getWidth();
                    int i = (int) (((double) context.getResources().getDisplayMetrics().heightPixels) * 0.6d);
                    if (height > i) {
                        i = -2;
                    }
                    view.setLayoutParams(new FrameLayout.LayoutParams(width, i));
                }
            });
        }
    }
}
