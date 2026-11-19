package com.hjq.toast.style;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hjq.toast.config.IToastStyle;

/* JADX INFO: loaded from: classes5.dex */
public class CustomToastStyle implements IToastStyle<View> {
    private final int mGravity;
    private final float mHorizontalMargin;
    private final int mLayoutId;
    private final float mVerticalMargin;
    private final int mXOffset;
    private final int mYOffset;

    public CustomToastStyle(int i) {
        this(i, 17);
    }

    public CustomToastStyle(int i, int i2) {
        this(i, i2, 0, 0);
    }

    public CustomToastStyle(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, 0.0f, 0.0f);
    }

    public CustomToastStyle(int i, int i2, int i3, int i4, float f, float f2) {
        this.mLayoutId = i;
        this.mGravity = i2;
        this.mXOffset = i3;
        this.mYOffset = i4;
        this.mHorizontalMargin = f;
        this.mVerticalMargin = f2;
    }

    @Override // com.hjq.toast.config.IToastStyle
    public View createView(Context context) {
        return LayoutInflater.from(context).inflate(this.mLayoutId, (ViewGroup) null);
    }

    @Override // com.hjq.toast.config.IToastStyle
    public int getGravity() {
        return this.mGravity;
    }

    @Override // com.hjq.toast.config.IToastStyle
    public int getXOffset() {
        return this.mXOffset;
    }

    @Override // com.hjq.toast.config.IToastStyle
    public int getYOffset() {
        return this.mYOffset;
    }

    @Override // com.hjq.toast.config.IToastStyle
    public float getHorizontalMargin() {
        return this.mHorizontalMargin;
    }

    @Override // com.hjq.toast.config.IToastStyle
    public float getVerticalMargin() {
        return this.mVerticalMargin;
    }
}
