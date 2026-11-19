package com.dl7.player.widgets;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.dl7.player.R;

/* JADX INFO: loaded from: classes4.dex */
public class ShareDialog extends DialogFragment {
    private Bitmap mBitmap;
    private OnDialogClickListener mClickListener;
    private OnDialogDismissListener mDismissListener;
    private boolean mIsShareMode = false;

    public interface OnDialogClickListener {
        void onShare(Bitmap bitmap, Uri uri);
    }

    public interface OnDialogDismissListener {
        void onDismiss();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().requestWindowFeature(1);
        Window window = getDialog().getWindow();
        window.addFlags(2);
        window.setWindowAnimations(R.style.AnimateDialog);
        window.setBackgroundDrawable(new ColorDrawable(0));
        View viewInflate = layoutInflater.inflate(R.layout.dialog_share, viewGroup);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_screenshot_photo);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = (getResources().getDisplayMetrics().widthPixels * 7) / 10;
        layoutParams.height = (getResources().getDisplayMetrics().heightPixels * 7) / 10;
        imageView.setLayoutParams(layoutParams);
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        viewInflate.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.dl7.player.widgets.ShareDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ShareDialog.this.dismiss();
            }
        });
        TextView textView = (TextView) viewInflate.findViewById(R.id.btn_share);
        if (this.mIsShareMode) {
            textView.setText("分享");
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.dl7.player.widgets.ShareDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ShareDialog.this.mClickListener != null) {
                    ShareDialog.this.mClickListener.onShare(ShareDialog.this.mBitmap, null);
                }
                ShareDialog.this.dismiss();
            }
        });
        return viewInflate;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        super.dismiss();
        OnDialogDismissListener onDialogDismissListener = this.mDismissListener;
        if (onDialogDismissListener != null) {
            onDialogDismissListener.onDismiss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        OnDialogDismissListener onDialogDismissListener = this.mDismissListener;
        if (onDialogDismissListener != null) {
            onDialogDismissListener.onDismiss();
        }
    }

    public void setScreenshotPhoto(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public void setClickListener(OnDialogClickListener onDialogClickListener) {
        this.mClickListener = onDialogClickListener;
    }

    public void setDismissListener(OnDialogDismissListener onDialogDismissListener) {
        this.mDismissListener = onDialogDismissListener;
    }

    public void setShareMode(boolean z) {
        this.mIsShareMode = z;
    }
}
