package com.owen.tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public final class TabItem extends View {
    final int mCustomLayout;
    final Drawable mIcon;
    final CharSequence mText;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabItem);
        this.mText = typedArrayObtainStyledAttributes.getText(R.styleable.TabItem_android_text);
        this.mIcon = typedArrayObtainStyledAttributes.getDrawable(R.styleable.TabItem_android_icon);
        this.mCustomLayout = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabItem_android_layout, 0);
        typedArrayObtainStyledAttributes.recycle();
    }
}
