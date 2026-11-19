package cn.bingoogolapple.refreshlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import me.jingbin.web.WebProgress;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BGARefreshViewHolder {
    private static final float PULL_DISTANCE_SCALE = 1.8f;
    private static final float SPRING_DISTANCE_SCALE = 0.4f;
    protected Context mContext;
    protected AnimationDrawable mFooterChrysanthemumAd;
    protected ImageView mFooterChrysanthemumIv;
    protected TextView mFooterStatusTv;
    private boolean mIsLoadingMoreEnabled;
    protected View mLoadMoreFooterView;
    protected View mRefreshHeaderView;
    protected BGARefreshLayout mRefreshLayout;
    private float mPullDistanceScale = PULL_DISTANCE_SCALE;
    private float mSpringDistanceScale = SPRING_DISTANCE_SCALE;
    protected String mLodingMoreText = "加载中...";
    private int mLoadMoreBackgroundColorRes = -1;
    private int mLoadMoreBackgroundDrawableRes = -1;
    protected int mRefreshViewBackgroundColorRes = -1;
    protected int mRefreshViewBackgroundDrawableRes = -1;
    private int mTopAnimDuration = WebProgress.DO_END_PROGRESS_DURATION;

    public boolean canChangeToRefreshingStatus() {
        return false;
    }

    public abstract void changeToIdle();

    public abstract void changeToPullDown();

    public abstract void changeToRefreshing();

    public abstract void changeToReleaseRefresh();

    public abstract View getRefreshHeaderView();

    public abstract void handleScale(float f, int i);

    public abstract void onEndRefreshing();

    public BGARefreshViewHolder(Context context, boolean z) {
        this.mContext = context;
        this.mIsLoadingMoreEnabled = z;
    }

    public void setLoadingMoreText(String str) {
        this.mLodingMoreText = str;
    }

    public void setLoadMoreBackgroundColorRes(int i) {
        this.mLoadMoreBackgroundColorRes = i;
    }

    public void setLoadMoreBackgroundDrawableRes(int i) {
        this.mLoadMoreBackgroundDrawableRes = i;
    }

    public void setRefreshViewBackgroundColorRes(int i) {
        this.mRefreshViewBackgroundColorRes = i;
    }

    public void setRefreshViewBackgroundDrawableRes(int i) {
        this.mRefreshViewBackgroundDrawableRes = i;
    }

    public int getTopAnimDuration() {
        return this.mTopAnimDuration;
    }

    public void setTopAnimDuration(int i) {
        this.mTopAnimDuration = i;
    }

    public View getLoadMoreFooterView() {
        if (!this.mIsLoadingMoreEnabled) {
            return null;
        }
        if (this.mLoadMoreFooterView == null) {
            View viewInflate = View.inflate(this.mContext, R.layout.view_normal_refresh_footer, null);
            this.mLoadMoreFooterView = viewInflate;
            viewInflate.setBackgroundColor(0);
            int i = this.mLoadMoreBackgroundColorRes;
            if (i != -1) {
                this.mLoadMoreFooterView.setBackgroundResource(i);
            }
            int i2 = this.mLoadMoreBackgroundDrawableRes;
            if (i2 != -1) {
                this.mLoadMoreFooterView.setBackgroundResource(i2);
            }
            this.mFooterStatusTv = (TextView) this.mLoadMoreFooterView.findViewById(R.id.tv_normal_refresh_footer_status);
            ImageView imageView = (ImageView) this.mLoadMoreFooterView.findViewById(R.id.iv_normal_refresh_footer_chrysanthemum);
            this.mFooterChrysanthemumIv = imageView;
            this.mFooterChrysanthemumAd = (AnimationDrawable) imageView.getDrawable();
            this.mFooterStatusTv.setText(this.mLodingMoreText);
        }
        return this.mLoadMoreFooterView;
    }

    public float getPaddingTopScale() {
        return this.mPullDistanceScale;
    }

    public void setPullDistanceScale(float f) {
        this.mPullDistanceScale = f;
    }

    public float getSpringDistanceScale() {
        return this.mSpringDistanceScale;
    }

    public void setSpringDistanceScale(float f) {
        if (f < 0.0f) {
            throw new RuntimeException("下拉刷新控件paddingTop的弹簧距离与下拉刷新控件高度的比值springDistanceScale不能小于0");
        }
        this.mSpringDistanceScale = f;
    }

    public void changeToLoadingMore() {
        AnimationDrawable animationDrawable;
        if (!this.mIsLoadingMoreEnabled || (animationDrawable = this.mFooterChrysanthemumAd) == null) {
            return;
        }
        animationDrawable.start();
    }

    public void onEndLoadingMore() {
        AnimationDrawable animationDrawable;
        if (!this.mIsLoadingMoreEnabled || (animationDrawable = this.mFooterChrysanthemumAd) == null) {
            return;
        }
        animationDrawable.stop();
    }

    public int getRefreshHeaderViewHeight() {
        View view = this.mRefreshHeaderView;
        if (view == null) {
            return 0;
        }
        view.measure(0, 0);
        return this.mRefreshHeaderView.getMeasuredHeight();
    }

    public void startChangeWholeHeaderViewPaddingTop(int i) {
        this.mRefreshLayout.startChangeWholeHeaderViewPaddingTop(i);
    }

    public void setRefreshLayout(BGARefreshLayout bGARefreshLayout) {
        this.mRefreshLayout = bGARefreshLayout;
    }
}
