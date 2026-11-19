package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatDialog;
import com.adorkable.iosdialog.R;

/* JADX INFO: loaded from: classes5.dex */
public class RemindChangeVoiceDialog extends AppCompatDialog {
    private final Context mContext;

    public RemindChangeVoiceDialog(Context context) {
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
        attributes.width = (int) (((double) this.mContext.getResources().getDisplayMetrics().widthPixels) * 0.8d);
        attributes.height = -2;
        window.setAttributes(attributes);
        View viewInflate = LayoutInflater.from(this.mContext).inflate(com.movieboxpro.androidtv.R.layout.remind_change_voice_layout, (ViewGroup) null);
        setContentView(viewInflate);
        ((LinearLayout) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.linearLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.RemindChangeVoiceDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RemindChangeVoiceDialog.this.dismiss();
            }
        });
    }
}
