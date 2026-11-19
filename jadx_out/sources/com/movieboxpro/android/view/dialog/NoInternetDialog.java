package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatDialog;
import com.adorkable.iosdialog.R;

/* JADX INFO: loaded from: classes5.dex */
public class NoInternetDialog extends AppCompatDialog {
    private OnButtonClickListener mOnButtonClickListener;

    public interface OnButtonClickListener {
        void onClick();
    }

    public NoInternetDialog(Context context, OnButtonClickListener onButtonClickListener) {
        super(context, R.style.DialogStyle);
        this.mOnButtonClickListener = onButtonClickListener;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        if (window != null) {
            window.setContentView(com.movieboxpro.androidtv.R.layout.dialog_no_internet);
            ((Button) window.findViewById(com.movieboxpro.androidtv.R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.NoInternetDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (NoInternetDialog.this.mOnButtonClickListener != null) {
                        NoInternetDialog.this.mOnButtonClickListener.onClick();
                        NoInternetDialog.this.dismiss();
                    }
                }
            });
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) getContext().getResources().getDisplayMetrics().widthPixels) * 0.7d);
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }
}
