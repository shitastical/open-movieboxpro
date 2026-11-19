package com.adorkable.iosdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

/* JADX INFO: loaded from: classes4.dex */
public class AlertDialog {
    private Button btn_neg;
    private Button btn_pos;
    private Context context;
    private Dialog dialog;
    private Display display;
    private View img_line;
    private TextView txt_msg;
    private TextView txt_title;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public AlertDialog(Context context) {
        this.context = context;
        this.display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    public AlertDialog builder() {
        Dialog dialog = new Dialog(this.context, R.style.AlertDialogStyle);
        this.dialog = dialog;
        dialog.setContentView(R.layout.view_alertdialog);
        LinearLayout linearLayout = (LinearLayout) this.dialog.findViewById(R.id.lLayout_bg);
        TextView textView = (TextView) this.dialog.findViewById(R.id.txt_title);
        this.txt_title = textView;
        textView.setVisibility(8);
        TextView textView2 = (TextView) this.dialog.findViewById(R.id.txt_msg);
        this.txt_msg = textView2;
        textView2.setVisibility(8);
        Button button = (Button) this.dialog.findViewById(R.id.btn_neg);
        this.btn_neg = button;
        button.setVisibility(8);
        Button button2 = (Button) this.dialog.findViewById(R.id.btn_pos);
        this.btn_pos = button2;
        button2.setVisibility(8);
        View viewFindViewById = this.dialog.findViewById(R.id.img_line);
        this.img_line = viewFindViewById;
        viewFindViewById.setVisibility(8);
        this.btn_neg.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.adorkable.iosdialog.AlertDialog.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AlertDialog.this.btn_neg.setBackgroundColor(ContextCompat.getColor(AlertDialog.this.context, R.color.focus_color));
                } else {
                    AlertDialog.this.btn_neg.setBackgroundColor(ContextCompat.getColor(AlertDialog.this.context, R.color.transparent));
                }
            }
        });
        this.btn_pos.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.adorkable.iosdialog.AlertDialog.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AlertDialog.this.btn_pos.setBackgroundColor(ContextCompat.getColor(AlertDialog.this.context, R.color.focus_color));
                } else {
                    AlertDialog.this.btn_pos.setBackgroundColor(ContextCompat.getColor(AlertDialog.this.context, R.color.transparent));
                }
            }
        });
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (((double) this.display.getWidth()) * 0.85d), -2));
        return this;
    }

    public AlertDialog setTitle(String str) {
        this.showTitle = true;
        if ("".equals(str)) {
            this.txt_title.setText("标题");
        } else {
            this.txt_title.setText(str);
        }
        return this;
    }

    public AlertDialog setMsg(String str) {
        this.showMsg = true;
        if ("".equals(str)) {
            this.txt_msg.setText("内容");
        } else {
            this.txt_msg.setText(str);
        }
        return this;
    }

    public AlertDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertDialog setPositiveButton(String str, final View.OnClickListener onClickListener) {
        this.showPosBtn = true;
        if ("".equals(str)) {
            this.btn_pos.setText("确定");
        } else {
            this.btn_pos.setText(str);
        }
        this.btn_pos.setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.AlertDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onClickListener.onClick(view);
                AlertDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public AlertDialog setNegativeButton(String str, final View.OnClickListener onClickListener) {
        this.showNegBtn = true;
        if ("".equals(str)) {
            this.btn_neg.setText("取消");
        } else {
            this.btn_neg.setText(str);
        }
        this.btn_neg.setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.AlertDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        });
        return this;
    }

    private void setLayout() {
        if (!this.showTitle && !this.showMsg) {
            this.txt_title.setText("提示");
            this.txt_title.setVisibility(0);
        }
        if (this.showTitle) {
            this.txt_title.setVisibility(0);
        }
        if (this.showMsg) {
            this.txt_msg.setVisibility(0);
        }
        if (!this.showPosBtn && !this.showNegBtn) {
            this.btn_pos.setText("确定");
            this.btn_pos.setVisibility(0);
            this.btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
            this.btn_pos.setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.AlertDialog.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AlertDialog.this.dialog.dismiss();
                }
            });
        }
        if (this.showPosBtn && this.showNegBtn) {
            this.btn_pos.setVisibility(0);
            this.btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            this.btn_neg.setVisibility(0);
            this.btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
            this.img_line.setVisibility(0);
        }
        if (this.showPosBtn && !this.showNegBtn) {
            this.btn_pos.setVisibility(0);
            this.btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }
        if (this.showPosBtn || !this.showNegBtn) {
            return;
        }
        this.btn_neg.setVisibility(0);
        this.btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
    }

    public void dismiss() {
        this.dialog.dismiss();
    }

    public void show() {
        setLayout();
        this.dialog.show();
    }
}
