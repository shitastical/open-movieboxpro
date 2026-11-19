package com.movieboxpro.android.view.activity.videoplayer.floatmanager;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class FloatView extends FrameLayout {
    private boolean firstTouch;
    private int floatX;
    private int floatY;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;

    public FloatView(Context context, int x, int y) {
        super(context);
        this.firstTouch = true;
        this.floatX = x;
        this.floatY = y;
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.shape_float_window_background);
        int iDp2px = PlayerUtils.dp2px(getContext(), 1.0f);
        setPadding(iDp2px, iDp2px, iDp2px, iDp2px);
        initWindow();
    }

    private void initWindow() {
        this.mWindowManager = PlayerUtils.getWindowManager(getContext().getApplicationContext());
        this.mParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mParams.type = 2038;
        } else {
            this.mParams.type = 2003;
        }
        this.mParams.format = -3;
        this.mParams.flags = 8;
        this.mParams.windowAnimations = R.style.FloatWindowAnimation;
        this.mParams.gravity = 8388659;
        int iDp2px = PlayerUtils.dp2px(getContext(), 250.0f);
        this.mParams.width = iDp2px;
        this.mParams.height = (iDp2px * 9) / 16;
        this.mParams.x = this.floatX;
        this.mParams.y = this.floatY;
    }

    public boolean addToWindow() {
        if (this.mWindowManager == null || isAttachedToWindow()) {
            return false;
        }
        this.mWindowManager.addView(this, this.mParams);
        return true;
    }

    public boolean removeFromWindow() {
        if (this.mWindowManager == null || !isAttachedToWindow()) {
            return false;
        }
        this.mWindowManager.removeViewImmediate(this);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return ev.getAction() != 0;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        int action = event.getAction();
        if (action == 1) {
            this.firstTouch = true;
        } else if (action == 2) {
            if (this.firstTouch) {
                this.floatX = (int) event.getX();
                this.floatY = (int) (((double) event.getY()) + PlayerUtils.getStatusBarHeight(getContext()));
                this.firstTouch = false;
            }
            this.mParams.x = rawX - this.floatX;
            this.mParams.y = rawY - this.floatY;
            this.mWindowManager.updateViewLayout(this, this.mParams);
        }
        return super.onTouchEvent(event);
    }
}
