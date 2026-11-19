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
public class ChildModeHintDialog extends AppCompatDialog {
    private DialogAction.ActionListener actionListener;
    private Context mContext;

    public void setActionListener(DialogAction.ActionListener listener) {
        this.actionListener = listener;
    }

    public ChildModeHintDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.mContext = context;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDialog();
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(com.movieboxpro.androidtv.R.layout.dialog_child_mode_hint, (ViewGroup) null);
        setContentView(viewInflate, new ViewGroup.LayoutParams(-1, -2));
        TextView textView = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_negative);
        TextView textView2 = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_positive);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChildModeHintDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (ChildModeHintDialog.this.actionListener != null) {
                    ChildModeHintDialog.this.actionListener.onClick();
                }
                ChildModeHintDialog.this.dismiss();
            }
        });
        textView2.requestFocus();
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChildModeHintDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ChildModeHintDialog.this.dismiss();
            }
        });
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((double) this.mContext.getResources().getDisplayMetrics().widthPixels) * 0.8d);
        attributes.height = -2;
        window.setAttributes(attributes);
    }
}
