package cn.bingoogolapple.refreshlayout;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class BGAStickinessRefreshViewHolder extends BGARefreshViewHolder {
    private int mRotateImageResId;
    private int mStickinessColorResId;
    private BGAStickinessRefreshView mStickinessRefreshView;

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToPullDown() {
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToReleaseRefresh() {
    }

    public BGAStickinessRefreshViewHolder(Context context, boolean z) {
        super(context, z);
        this.mRotateImageResId = -1;
        this.mStickinessColorResId = -1;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public View getRefreshHeaderView() {
        if (this.mRefreshHeaderView == null) {
            this.mRefreshHeaderView = View.inflate(this.mContext, R.layout.view_refresh_header_stickiness, null);
            this.mRefreshHeaderView.setBackgroundColor(0);
            if (this.mRefreshViewBackgroundColorRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }
            if (this.mRefreshViewBackgroundDrawableRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }
            BGAStickinessRefreshView bGAStickinessRefreshView = (BGAStickinessRefreshView) this.mRefreshHeaderView.findViewById(R.id.stickinessRefreshView);
            this.mStickinessRefreshView = bGAStickinessRefreshView;
            bGAStickinessRefreshView.setStickinessRefreshViewHolder(this);
            int i = this.mRotateImageResId;
            if (i != -1) {
                this.mStickinessRefreshView.setRotateImage(i);
                int i2 = this.mStickinessColorResId;
                if (i2 != -1) {
                    this.mStickinessRefreshView.setStickinessColor(i2);
                } else {
                    throw new RuntimeException("请调用BGAStickinessRefreshViewHolder的setStickinessColor方法设置黏性颜色资源");
                }
            } else {
                throw new RuntimeException("请调用BGAStickinessRefreshViewHolder的setRotateImage方法设置旋转图片资源");
            }
        }
        return this.mRefreshHeaderView;
    }

    public void setRotateImage(int i) {
        this.mRotateImageResId = i;
    }

    public void setStickinessColor(int i) {
        this.mStickinessColorResId = i;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void handleScale(float f, int i) {
        this.mStickinessRefreshView.setMoveYDistance(i);
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToIdle() {
        this.mStickinessRefreshView.smoothToIdle();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToRefreshing() {
        this.mStickinessRefreshView.startRefreshing();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void onEndRefreshing() {
        this.mStickinessRefreshView.stopRefresh();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public boolean canChangeToRefreshingStatus() {
        return this.mStickinessRefreshView.canChangeToRefreshing();
    }
}
