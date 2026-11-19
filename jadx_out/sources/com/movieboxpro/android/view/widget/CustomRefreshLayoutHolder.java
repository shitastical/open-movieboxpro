package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class CustomRefreshLayoutHolder extends BGARefreshViewHolder {
    private RotateAnimation mDownAnim;
    private ImageView mHeaderArrowIv;
    private AnimationDrawable mHeaderChrysanthemumAd;
    private ImageView mHeaderChrysanthemumIv;
    private TextView mHeaderStatusTv;
    private String mPullDownRefreshText;
    private String mRefreshingText;
    private String mReleaseRefreshText;
    private RotateAnimation mUpAnim;

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToIdle() {
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void handleScale(float scale, int moveYDistance) {
    }

    public CustomRefreshLayoutHolder(Context context, boolean isLoadingMoreEnabled) {
        super(context, isLoadingMoreEnabled);
        this.mPullDownRefreshText = "Pull down to refresh";
        this.mReleaseRefreshText = "Release to refresh";
        this.mRefreshingText = "loading...";
        initAnimation();
    }

    private void initAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mUpAnim = rotateAnimation;
        rotateAnimation.setDuration(150L);
        this.mUpAnim.setFillAfter(true);
        RotateAnimation rotateAnimation2 = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mDownAnim = rotateAnimation2;
        rotateAnimation2.setFillAfter(true);
    }

    public void setPullDownRefreshText(String pullDownRefreshText) {
        this.mPullDownRefreshText = pullDownRefreshText;
    }

    public void setReleaseRefreshText(String releaseRefreshText) {
        this.mReleaseRefreshText = releaseRefreshText;
    }

    public void setRefreshingText(String refreshingText) {
        this.mRefreshingText = refreshingText;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public View getRefreshHeaderView() {
        if (this.mRefreshHeaderView == null) {
            this.mRefreshHeaderView = View.inflate(this.mContext, R.layout.custom_refresh_header_layout, null);
            this.mRefreshHeaderView.setBackgroundColor(0);
            if (this.mRefreshViewBackgroundColorRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }
            if (this.mRefreshViewBackgroundDrawableRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }
            this.mHeaderStatusTv = (TextView) this.mRefreshHeaderView.findViewById(R.id.tv_normal_refresh_header_status);
            this.mHeaderArrowIv = (ImageView) this.mRefreshHeaderView.findViewById(R.id.iv_normal_refresh_header_arrow);
            ImageView imageView = (ImageView) this.mRefreshHeaderView.findViewById(R.id.iv_normal_refresh_header_chrysanthemum);
            this.mHeaderChrysanthemumIv = imageView;
            this.mHeaderChrysanthemumAd = (AnimationDrawable) imageView.getDrawable();
            this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
        }
        return this.mRefreshHeaderView;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToPullDown() {
        this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
        this.mHeaderChrysanthemumIv.setVisibility(4);
        this.mHeaderChrysanthemumAd.stop();
        this.mHeaderArrowIv.setVisibility(0);
        this.mDownAnim.setDuration(150L);
        this.mHeaderArrowIv.startAnimation(this.mDownAnim);
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToReleaseRefresh() {
        this.mHeaderStatusTv.setText(this.mReleaseRefreshText);
        this.mHeaderChrysanthemumIv.setVisibility(4);
        this.mHeaderChrysanthemumAd.stop();
        this.mHeaderArrowIv.setVisibility(0);
        this.mHeaderArrowIv.startAnimation(this.mUpAnim);
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToRefreshing() {
        this.mHeaderStatusTv.setText(this.mRefreshingText);
        this.mHeaderArrowIv.clearAnimation();
        this.mHeaderArrowIv.setVisibility(4);
        this.mHeaderChrysanthemumIv.setVisibility(0);
        this.mHeaderChrysanthemumAd.start();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void onEndRefreshing() {
        this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
        this.mHeaderChrysanthemumIv.setVisibility(4);
        this.mHeaderChrysanthemumAd.stop();
        this.mHeaderArrowIv.setVisibility(0);
        this.mDownAnim.setDuration(0L);
        this.mHeaderArrowIv.startAnimation(this.mDownAnim);
    }
}
