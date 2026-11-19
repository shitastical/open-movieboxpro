package com.kingja.loadsir.callback;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes5.dex */
public class HintCallback extends Callback {
    private int imgResId;
    private String subTitle;
    private int subTitleStyleRes;
    private String title;
    private int titleStyleRes;

    @Override // com.kingja.loadsir.callback.Callback
    protected int onCreateView() {
        return 0;
    }

    public HintCallback(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.imgResId = builder.imgResId;
        this.subTitleStyleRes = builder.subTitleStyleRes;
        this.titleStyleRes = builder.titleStyleRes;
    }

    @Override // com.kingja.loadsir.callback.Callback
    protected View onBuildView(Context context) {
        return new LinearLayout(context);
    }

    @Override // com.kingja.loadsir.callback.Callback
    protected void onViewCreate(Context context, View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        LinearLayout linearLayout = (LinearLayout) view;
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        if (this.imgResId != -1) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(this.imgResId);
            linearLayout.addView(imageView, layoutParams);
        }
        if (!TextUtils.isEmpty(this.title)) {
            TextView textView = new TextView(context);
            textView.setText(this.title);
            int i = this.titleStyleRes;
            if (i == -1) {
                textView.setTextAppearance(context, R.style.TextAppearance.Large);
            } else {
                textView.setTextAppearance(context, i);
            }
            linearLayout.addView(textView, layoutParams);
        }
        if (TextUtils.isEmpty(this.subTitle)) {
            return;
        }
        TextView textView2 = new TextView(context);
        textView2.setText(this.subTitle);
        int i2 = this.subTitleStyleRes;
        if (i2 == -1) {
            textView2.setTextAppearance(context, R.style.TextAppearance.Small);
        } else {
            textView2.setTextAppearance(context, i2);
        }
        linearLayout.addView(textView2, layoutParams);
    }

    public static class Builder {
        private String subTitle;
        private String title;
        private int imgResId = -1;
        private int subTitleStyleRes = -1;
        private int titleStyleRes = -1;

        public Builder setHintImg(int i) {
            this.imgResId = i;
            return this;
        }

        public Builder setTitle(String str) {
            return setTitle(str, -1);
        }

        public Builder setTitle(String str, int i) {
            this.title = str;
            this.titleStyleRes = i;
            return this;
        }

        public Builder setSubTitle(String str) {
            return setSubTitle(str, -1);
        }

        public Builder setSubTitle(String str, int i) {
            this.subTitle = str;
            this.subTitleStyleRes = i;
            return this;
        }

        public HintCallback build() {
            return new HintCallback(this);
        }
    }
}
