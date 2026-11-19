package com.dueeeke.videoplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dueeeke.videocontroller.R;

/* JADX INFO: loaded from: classes4.dex */
public class StatusView extends LinearLayout {
    private TextView btnAction;
    private float downX;
    private float downY;
    private TextView tvMessage;

    public StatusView(Context context) {
        this(context, null);
    }

    public StatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dkplayer_layout_status_view, this);
        this.tvMessage = (TextView) viewInflate.findViewById(R.id.message);
        this.btnAction = (TextView) viewInflate.findViewById(R.id.status_btn);
        setBackgroundResource(android.R.color.black);
        setClickable(true);
    }

    public void setMessage(String str) {
        TextView textView = this.tvMessage;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setButtonTextAndAction(String str, View.OnClickListener onClickListener) {
        TextView textView = this.btnAction;
        if (textView != null) {
            textView.setText(str);
            this.btnAction.setOnClickListener(onClickListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.downX = motionEvent.getX();
            this.downY = motionEvent.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2) {
            float fAbs = Math.abs(motionEvent.getX() - this.downX);
            float fAbs2 = Math.abs(motionEvent.getY() - this.downY);
            if (fAbs > ViewConfiguration.get(getContext()).getScaledTouchSlop() || fAbs2 > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
