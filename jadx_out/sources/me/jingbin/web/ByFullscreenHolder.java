package me.jingbin.web;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes6.dex */
public class ByFullscreenHolder extends FrameLayout {
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public ByFullscreenHolder(Context context) {
        super(context);
        setBackgroundColor(context.getResources().getColor(android.R.color.black));
    }
}
