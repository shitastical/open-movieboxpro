package com.movieboxpro.android.view.activity.videoplayer.floatmanager;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.dueeeke.videoplayer.StageState;
import com.dueeeke.videoplayer.controller.GestureVideoController;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class FloatController extends GestureVideoController implements View.OnClickListener {
    private float downX;
    private float downY;
    private ImageView playButton;
    private ImageView playClose;
    private ImageView playSkip;
    private ProgressBar proLoading;

    public FloatController(Context context) {
        super(context);
    }

    public FloatController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    protected int getLayoutId() {
        return R.layout.layout_float_controller;
    }

    @Override // com.dueeeke.videoplayer.controller.GestureVideoController, com.dueeeke.videoplayer.controller.BaseVideoController
    protected void initView() {
        super.initView();
        setOnClickListener(this);
        this.playClose = (ImageView) this.mControllerView.findViewById(R.id.btn_close);
        this.playSkip = (ImageView) this.mControllerView.findViewById(R.id.btn_skip);
        this.proLoading = (ProgressBar) this.mControllerView.findViewById(R.id.loading);
        this.playButton = (ImageView) this.mControllerView.findViewById(R.id.start_play);
        this.playSkip.setClickable(true);
        this.playButton.setOnClickListener(this);
        this.playClose.setOnClickListener(this);
        this.playSkip.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_close) {
            PIPManager.getInstance().stopFloatWindow();
            PIPManager.getInstance().reset();
            return;
        }
        if (id == R.id.start_play) {
            doPauseResume();
            return;
        }
        if (id != R.id.btn_skip || PIPManager.getInstance().getAction() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtras(PIPManager.getInstance().getInfo());
        intent.setAction(PIPManager.getInstance().getAction());
        getContext().startActivity(intent);
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case -1:
                this.proLoading.setVisibility(8);
                this.playButton.setVisibility(8);
                this.playClose.setVisibility(8);
                this.playSkip.setVisibility(8);
                break;
            case 0:
                this.playButton.setSelected(false);
                this.playButton.setVisibility(0);
                this.playSkip.setSelected(false);
                this.playSkip.setVisibility(0);
                this.playClose.setSelected(false);
                this.playClose.setVisibility(0);
                this.proLoading.setVisibility(8);
                break;
            case 1:
                this.playButton.setVisibility(8);
                this.playSkip.setVisibility(8);
                this.playClose.setVisibility(8);
                this.proLoading.setVisibility(0);
                break;
            case 2:
                this.playButton.setVisibility(8);
                this.playSkip.setVisibility(8);
                this.playClose.setVisibility(8);
                this.proLoading.setVisibility(8);
                break;
            case 3:
                this.playButton.setSelected(true);
                this.playButton.setVisibility(8);
                this.playSkip.setSelected(true);
                this.playSkip.setVisibility(8);
                this.playClose.setSelected(true);
                this.playClose.setVisibility(8);
                this.proLoading.setVisibility(8);
                hide(StageState.PlayState);
                break;
            case 4:
                this.playButton.setSelected(false);
                this.playButton.setVisibility(0);
                this.playSkip.setSelected(false);
                this.playSkip.setVisibility(0);
                this.playClose.setSelected(false);
                this.playClose.setVisibility(0);
                this.proLoading.setVisibility(8);
                show(0);
                break;
            case 5:
                show(0);
                removeCallbacks(this.mShowProgress);
                break;
            case 6:
                this.playButton.setVisibility(8);
                this.playSkip.setVisibility(8);
                this.playClose.setVisibility(8);
                this.proLoading.setVisibility(0);
                break;
            case 7:
                this.playButton.setVisibility(8);
                this.playSkip.setVisibility(8);
                this.proLoading.setVisibility(8);
                break;
        }
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    public void show(StageState stageState) {
        show(this.mDefaultTimeout);
    }

    private void show(int timeout) {
        if (this.mCurrentPlayState == 6) {
            return;
        }
        if (!this.mShowing) {
            this.playButton.setVisibility(0);
            this.playSkip.setVisibility(0);
            this.playClose.setVisibility(0);
        }
        this.mShowing = true;
        removeCallbacks(this.mFadeOut);
        if (timeout != 0) {
            postDelayed(this.mFadeOut, timeout);
        }
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    public void hide(StageState stageState) {
        if (this.mCurrentPlayState != 6 && this.mShowing) {
            this.playButton.setVisibility(8);
            this.playSkip.setVisibility(8);
            this.playClose.setVisibility(8);
            this.mShowing = false;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.downX = ev.getX();
            this.downY = ev.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2) {
            float fAbs = Math.abs(ev.getX() - this.downX);
            float fAbs2 = Math.abs(ev.getY() - this.downY);
            if (fAbs > ViewConfiguration.get(getContext()).getScaledTouchSlop() || fAbs2 > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
