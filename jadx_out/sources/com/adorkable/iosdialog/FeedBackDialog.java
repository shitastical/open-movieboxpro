package com.adorkable.iosdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes4.dex */
public class FeedBackDialog extends Dialog {
    private TextView Cancel;
    private FeedBackDialog Instance;
    private TextView Send;
    private EditText editText;
    private CallBack mCallBack;
    private RelativeLayout mLayout;

    public interface CallBack {
        void setMessage(String str);
    }

    public FeedBackDialog(Context context) {
        super(context);
        this.Instance = this;
    }

    public FeedBackDialog(Context context, int i) {
        super(context, i);
        this.Instance = this;
    }

    protected FeedBackDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.Instance = this;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.view_feedbackdialog);
        setCanceledOnTouchOutside(false);
        initView();
    }

    public void initView() {
        this.Cancel = (TextView) findViewById(R.id.cancel);
        this.Send = (TextView) findViewById(R.id.send);
        this.editText = (EditText) findViewById(R.id.message);
        this.mLayout = (RelativeLayout) findViewById(R.id.lLayout_feedback);
        this.mLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (((double) ((WindowManager) this.Instance.getContext().getSystemService("window")).getDefaultDisplay().getWidth()) * 0.8d), -2));
        this.Cancel.setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.FeedBackDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FeedBackDialog.this.dismiss();
            }
        });
        this.Send.setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.FeedBackDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FeedBackDialog.this.dismiss();
                if (FeedBackDialog.this.mCallBack != null) {
                    FeedBackDialog.this.mCallBack.setMessage(FeedBackDialog.this.editText.getText().toString().trim());
                }
            }
        });
    }

    public FeedBackDialog setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
        return this;
    }
}
