package com.adorkable.iosdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes4.dex */
public class PayDialog extends Dialog {
    public static PayDialog payDialog;
    private ImageView close;
    private LinearLayout lLayout_pay;
    private onNoOnclickListener noOnclickListener;
    private TextView payStone;
    private TextView stoneNum;
    private int stones;

    public interface onNoOnclickListener {
        void onNoClick();
    }

    private void initData() {
    }

    public PayDialog(Context context) {
        super(context);
        payDialog = this;
    }

    public PayDialog(Context context, int i) {
        super(context, R.style.Dialog);
        this.stones = i;
        payDialog = this;
    }

    public void setNoOnclickListener(String str, onNoOnclickListener onnoonclicklistener) {
        if (str != null) {
            dismiss();
        }
        this.noOnclickListener = onnoonclicklistener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.view_paydialog);
        setCanceledOnTouchOutside(false);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        this.payStone = (TextView) findViewById(R.id.pay_paystone);
        this.close = (ImageView) findViewById(R.id.pay_close);
        TextView textView = (TextView) findViewById(R.id.pay_dialog_item2);
        this.stoneNum = textView;
        textView.setText("" + this.stones);
        this.lLayout_pay = (LinearLayout) findViewById(R.id.lLayout_pay);
        this.lLayout_pay.setLayoutParams(new FrameLayout.LayoutParams((int) (((double) ((WindowManager) payDialog.getContext().getSystemService("window")).getDefaultDisplay().getWidth()) * 0.6d), -2));
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.PayDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PayDialog.this.dismiss();
            }
        });
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * getScreenDendity(context)) + 0.5f);
    }

    public static float getScreenDendity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private void initEvent() {
        this.payStone.setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.PayDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PayDialog.this.noOnclickListener != null) {
                    PayDialog.this.noOnclickListener.onNoClick();
                }
            }
        });
    }
}
