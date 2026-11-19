package cn.bingoogolapple.refreshlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes4.dex */
public class BGAMeiTuanRefreshView extends RelativeLayout {
    private AnimationDrawable mChangeToReleaseRefreshAd;
    private int mChangeToReleaseRefreshAnimResId;
    private ImageView mPullDownView;
    private AnimationDrawable mRefreshingAd;
    private int mRefreshingAnimResId;
    private ImageView mReleaseRefreshingView;

    public BGAMeiTuanRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mPullDownView = (ImageView) findViewById(R.id.iv_meituan_pull_down);
        this.mReleaseRefreshingView = (ImageView) findViewById(R.id.iv_meituan_release_refreshing);
    }

    public void setPullDownImageResource(int i) {
        this.mPullDownView.setImageResource(i);
    }

    public void setChangeToReleaseRefreshAnimResId(int i) {
        this.mChangeToReleaseRefreshAnimResId = i;
        this.mReleaseRefreshingView.setImageResource(i);
    }

    public void setRefreshingAnimResId(int i) {
        this.mRefreshingAnimResId = i;
    }

    public void handleScale(float f) {
        float f2 = (f * 0.9f) + 0.1f;
        ViewCompat.setScaleX(this.mPullDownView, f2);
        ImageView imageView = this.mPullDownView;
        ViewCompat.setPivotY(imageView, imageView.getHeight());
        ViewCompat.setScaleY(this.mPullDownView, f2);
    }

    public void changeToIdle() {
        stopChangeToReleaseRefreshAd();
        stopRefreshingAd();
        this.mPullDownView.setVisibility(0);
        this.mReleaseRefreshingView.setVisibility(4);
    }

    public void changeToPullDown() {
        this.mPullDownView.setVisibility(0);
        this.mReleaseRefreshingView.setVisibility(4);
    }

    public void changeToReleaseRefresh() {
        this.mReleaseRefreshingView.setImageResource(this.mChangeToReleaseRefreshAnimResId);
        this.mChangeToReleaseRefreshAd = (AnimationDrawable) this.mReleaseRefreshingView.getDrawable();
        this.mReleaseRefreshingView.setVisibility(0);
        this.mPullDownView.setVisibility(4);
        this.mChangeToReleaseRefreshAd.start();
    }

    public void changeToRefreshing() {
        stopChangeToReleaseRefreshAd();
        this.mReleaseRefreshingView.setImageResource(this.mRefreshingAnimResId);
        this.mRefreshingAd = (AnimationDrawable) this.mReleaseRefreshingView.getDrawable();
        this.mReleaseRefreshingView.setVisibility(0);
        this.mPullDownView.setVisibility(4);
        this.mRefreshingAd.start();
    }

    public void onEndRefreshing() {
        stopChangeToReleaseRefreshAd();
        stopRefreshingAd();
    }

    private void stopRefreshingAd() {
        AnimationDrawable animationDrawable = this.mRefreshingAd;
        if (animationDrawable != null) {
            animationDrawable.stop();
            this.mRefreshingAd = null;
        }
    }

    private void stopChangeToReleaseRefreshAd() {
        AnimationDrawable animationDrawable = this.mChangeToReleaseRefreshAd;
        if (animationDrawable != null) {
            animationDrawable.stop();
            this.mChangeToReleaseRefreshAd = null;
        }
    }
}
