package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class BottomSheetMsgDialog extends BottomSheetDialog {
    private BottomSheetBehavior<View> mBottomSheetBehavior;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback;
    private final Context mContext;

    private void initDialog() {
    }

    public BottomSheetMsgDialog(Context context) {
        super(context, R.style.TransparentDialog);
        this.mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.movieboxpro.android.view.dialog.BottomSheetMsgDialog.1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View bottomSheet, float slideOffset) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == 1) {
                    BottomSheetMsgDialog.this.mBottomSheetBehavior.setState(4);
                }
            }
        };
        this.mContext = context;
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
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

        public BottomSheetMsgDialog create() {
            final BottomSheetMsgDialog bottomSheetMsgDialog = new BottomSheetMsgDialog(this.context);
            View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.bottom_msg_dialog_layout, (ViewGroup) null);
            bottomSheetMsgDialog.setContentView(viewInflate);
            bottomSheetMsgDialog.setCancelable(this.isCancelable);
            bottomSheetMsgDialog.setCanceledOnTouchOutside(this.isCancelable);
            ((TextView) viewInflate.findViewById(R.id.tv_title)).setText(this.title);
            ((TextView) viewInflate.findViewById(R.id.tv_content)).setText(this.secondContent);
            TextView textView = (TextView) viewInflate.findViewById(R.id.tv_negative);
            if (this.isNegativeGone) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
            textView.setText(this.negativeText);
            TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_positive);
            textView2.setText(this.positiveText);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.BottomSheetMsgDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                    }
                    if (Builder.this.isCancelable) {
                        bottomSheetMsgDialog.dismiss();
                    }
                }
            });
            textView.setOnClickListener(new View.OnClickListener(this) { // from class: com.movieboxpro.android.view.dialog.BottomSheetMsgDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    bottomSheetMsgDialog.dismiss();
                }
            });
            return bottomSheetMsgDialog;
        }
    }
}
