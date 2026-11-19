package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import com.adorkable.iosdialog.R;

/* JADX INFO: loaded from: classes5.dex */
public class MsgHintDialog extends AppCompatDialog {
    private Context mContext;

    public MsgHintDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.mContext = context;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((double) this.mContext.getResources().getDisplayMetrics().widthPixels) * 0.6d);
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private DialogAction.ActionListener cancelListener;
        private Context context;
        private String secondContent;
        private String title = "Note";
        private String negativeText = "Cancel";
        private String positiveText = "OK";
        private boolean isNegativeGone = false;

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

        public Builder setCancelActionListener(DialogAction.ActionListener actionListener) {
            this.cancelListener = actionListener;
            return this;
        }

        public MsgHintDialog create() {
            final MsgHintDialog msgHintDialog = new MsgHintDialog(this.context);
            View viewInflate = LayoutInflater.from(this.context).inflate(com.movieboxpro.androidtv.R.layout.msg_hint_dialog_layout, (ViewGroup) null);
            msgHintDialog.addContentView(viewInflate, new ViewGroup.LayoutParams(-1, -2));
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_title)).setText(this.title);
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_content)).setText(this.secondContent);
            TextView textView = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_negative);
            if (this.isNegativeGone) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
            textView.setText(this.negativeText);
            TextView textView2 = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_positive);
            textView2.setText(this.positiveText);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.MsgHintDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                    }
                    msgHintDialog.dismiss();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.MsgHintDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (Builder.this.cancelListener != null) {
                        Builder.this.cancelListener.onClick();
                    }
                    msgHintDialog.dismiss();
                }
            });
            return msgHintDialog;
        }
    }
}
