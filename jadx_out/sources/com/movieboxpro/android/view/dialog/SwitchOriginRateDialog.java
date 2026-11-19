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
public class SwitchOriginRateDialog extends AppCompatDialog {
    private Context mContext;

    public SwitchOriginRateDialog(Context context) {
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
    }

    @Override // android.app.Dialog
    public void show() {
        if (getWindow() != null) {
            getWindow().setFlags(8, 8);
        }
        super.show();
        fullScreenImmersive(getWindow().getDecorView());
        getWindow().clearFlags(8);
    }

    private void fullScreenImmersive(View view) {
        view.setSystemUiVisibility(5894);
    }

    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setActionListener(DialogAction.ActionListener actionListener) {
            this.actionListener = actionListener;
            return this;
        }

        public SwitchOriginRateDialog create() {
            final SwitchOriginRateDialog switchOriginRateDialog = new SwitchOriginRateDialog(this.context);
            View viewInflate = LayoutInflater.from(this.context).inflate(com.movieboxpro.androidtv.R.layout.switch_origin_rate_layout, (ViewGroup) null);
            switchOriginRateDialog.addContentView(viewInflate, new ViewGroup.LayoutParams(-1, -2));
            TextView textView = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_cancel);
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_keep)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.SwitchOriginRateDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                    }
                    switchOriginRateDialog.dismiss();
                }
            });
            textView.setOnClickListener(new View.OnClickListener(this) { // from class: com.movieboxpro.android.view.dialog.SwitchOriginRateDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    switchOriginRateDialog.dismiss();
                }
            });
            return switchOriginRateDialog;
        }
    }
}
