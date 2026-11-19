package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes5.dex */
public class SlowScrollView extends ScrollView {
    public SlowScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SlowScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlowScrollView(Context context) {
        super(context);
    }

    @Override // android.widget.ScrollView
    public void fling(int velocityY) {
        super.fling(velocityY / 4);
    }
}
