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
public class TipDialog extends AppCompatDialog {
    private Context mContext;

    public interface OnPositiveListener {
        void onClick();
    }

    public TipDialog(Context context, int theme) {
        super(context, theme);
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

    public static class Builder {
        private Context context;
        private OnPositiveListener mOnPositiveListener;
        private String msg;
        private String title = "提示";
        private String negativeMsg = "我知道了";
        private boolean cancelable = true;
        private boolean canceledOnTouchOutside = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public TipDialog create() {
            TipDialog tipDialog = new TipDialog(this.context, R.style.DialogStyle);
            View viewInflate = LayoutInflater.from(this.context).inflate(com.movieboxpro.androidtv.R.layout.tip_dialog_layout, (ViewGroup) null);
            tipDialog.addContentView(viewInflate, new ViewGroup.LayoutParams(-1, -2));
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_title)).setText(this.title);
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_content)).setText(this.msg);
            tipDialog.setCancelable(this.cancelable);
            tipDialog.setCanceledOnTouchOutside(this.canceledOnTouchOutside);
            return tipDialog;
        }
    }
}
