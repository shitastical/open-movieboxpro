package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class InvateDialog extends Dialog {
    public static InvateDialog payDialog;
    private EditText InvateEdit;
    private TextView LoginIn;
    private ConstraintLayout lLayout_pay;
    private onNoOnclickListener noOnclickListener;

    public interface onNoOnclickListener {
        void onNoClick(String text);
    }

    private void initData() {
    }

    public InvateDialog(Context context) {
        super(context);
        payDialog = this;
    }

    public InvateDialog setNoOnclickListener(onNoOnclickListener onNoOnclickListener2) {
        this.noOnclickListener = onNoOnclickListener2;
        return this;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_invate_dialog);
        setCanceledOnTouchOutside(false);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        this.InvateEdit = (EditText) findViewById(R.id.invate_edit);
        this.LoginIn = (TextView) findViewById(R.id.invate_login);
        this.lLayout_pay = (ConstraintLayout) findViewById(com.adorkable.iosdialog.R.id.lLayout_pay);
        this.lLayout_pay.setLayoutParams(new FrameLayout.LayoutParams((int) (((double) ((WindowManager) payDialog.getContext().getSystemService("window")).getDefaultDisplay().getWidth()) * 0.6d), -2));
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * getScreenDendity(context)) + 0.5f);
    }

    public static float getScreenDendity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private void initEvent() {
        this.LoginIn.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.InvateDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (InvateDialog.this.noOnclickListener != null) {
                    if (TextUtils.isEmpty(InvateDialog.this.InvateEdit.getText().toString())) {
                        ToastUtils.showShort("empty invite code");
                    } else {
                        InvateDialog.this.noOnclickListener.onNoClick(InvateDialog.this.InvateEdit.getText().toString());
                    }
                }
            }
        });
    }
}
