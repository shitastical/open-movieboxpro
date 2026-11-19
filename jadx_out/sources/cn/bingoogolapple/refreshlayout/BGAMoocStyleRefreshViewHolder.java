package cn.bingoogolapple.refreshlayout;

import android.content.Context;
import android.view.View;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes4.dex */
public class BGAMoocStyleRefreshViewHolder extends BGARefreshViewHolder {
    private BGAMoocStyleRefreshView mMoocRefreshView;
    private int mOriginalImageResId;
    private int mUltimateColorResId;

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToIdle() {
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToPullDown() {
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToReleaseRefresh() {
    }

    public BGAMoocStyleRefreshViewHolder(Context context, boolean z) {
        super(context, z);
        this.mUltimateColorResId = -1;
        this.mOriginalImageResId = -1;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public View getRefreshHeaderView() {
        if (this.mRefreshHeaderView == null) {
            this.mRefreshHeaderView = View.inflate(this.mContext, R.layout.view_refresh_header_mooc_style, null);
            this.mRefreshHeaderView.setBackgroundColor(0);
            if (this.mRefreshViewBackgroundColorRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }
            if (this.mRefreshViewBackgroundDrawableRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }
            BGAMoocStyleRefreshView bGAMoocStyleRefreshView = (BGAMoocStyleRefreshView) this.mRefreshHeaderView.findViewById(R.id.moocView);
            this.mMoocRefreshView = bGAMoocStyleRefreshView;
            int i = this.mOriginalImageResId;
            if (i != -1) {
                bGAMoocStyleRefreshView.setOriginalImage(i);
                int i2 = this.mUltimateColorResId;
                if (i2 != -1) {
                    this.mMoocRefreshView.setUltimateColor(i2);
                } else {
                    throw new RuntimeException("请调用BGAMoocStyleRefreshViewHolder的setUltimateColor方法设置最终生成图片的填充颜色资源");
                }
            } else {
                throw new RuntimeException("请调用BGAMoocStyleRefreshViewHolder的setOriginalImage方法设置原始图片资源");
            }
        }
        return this.mRefreshHeaderView;
    }

    public void setOriginalImage(int i) {
        this.mOriginalImageResId = i;
    }

    public void setUltimateColor(int i) {
        this.mUltimateColorResId = i;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void handleScale(float f, int i) {
        float f2 = (f * 0.4f) + 0.6f;
        ViewCompat.setScaleX(this.mMoocRefreshView, f2);
        ViewCompat.setScaleY(this.mMoocRefreshView, f2);
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToRefreshing() {
        this.mMoocRefreshView.startRefreshing();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void onEndRefreshing() {
        this.mMoocRefreshView.stopRefreshing();
    }
}
