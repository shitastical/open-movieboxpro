package cn.bingoogolapple.refreshlayout;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class BGAMeiTuanRefreshViewHolder extends BGARefreshViewHolder {
    private int mChangeToReleaseRefreshAnimResId;
    private BGAMeiTuanRefreshView mMeiTuanRefreshView;
    private int mPullDownImageResId;
    private int mRefreshingAnimResId;

    public BGAMeiTuanRefreshViewHolder(Context context, boolean z) {
        super(context, z);
        this.mPullDownImageResId = -1;
        this.mChangeToReleaseRefreshAnimResId = -1;
        this.mRefreshingAnimResId = -1;
    }

    public void setPullDownImageResource(int i) {
        this.mPullDownImageResId = i;
    }

    public void setChangeToReleaseRefreshAnimResId(int i) {
        this.mChangeToReleaseRefreshAnimResId = i;
    }

    public void setRefreshingAnimResId(int i) {
        this.mRefreshingAnimResId = i;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public View getRefreshHeaderView() {
        if (this.mRefreshHeaderView == null) {
            this.mRefreshHeaderView = View.inflate(this.mContext, R.layout.view_refresh_header_meituan, null);
            this.mRefreshHeaderView.setBackgroundColor(0);
            if (this.mRefreshViewBackgroundColorRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }
            if (this.mRefreshViewBackgroundDrawableRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }
            BGAMeiTuanRefreshView bGAMeiTuanRefreshView = (BGAMeiTuanRefreshView) this.mRefreshHeaderView.findViewById(R.id.meiTuanView);
            this.mMeiTuanRefreshView = bGAMeiTuanRefreshView;
            int i = this.mPullDownImageResId;
            if (i != -1) {
                bGAMeiTuanRefreshView.setPullDownImageResource(i);
                int i2 = this.mChangeToReleaseRefreshAnimResId;
                if (i2 != -1) {
                    this.mMeiTuanRefreshView.setChangeToReleaseRefreshAnimResId(i2);
                    int i3 = this.mRefreshingAnimResId;
                    if (i3 != -1) {
                        this.mMeiTuanRefreshView.setRefreshingAnimResId(i3);
                    } else {
                        throw new RuntimeException("请调用BGAMeiTuanRefreshViewHolder的setRefreshingAnimResId方法设置正在刷新时的动画资源");
                    }
                } else {
                    throw new RuntimeException("请调用BGAMeiTuanRefreshViewHolder的setChangeToReleaseRefreshAnimResId方法设置进入释放刷新状态时的动画资源");
                }
            } else {
                throw new RuntimeException("请调用BGAMeiTuanRefreshViewHolder的setPullDownImageResource方法设置下拉过程中的图片资源");
            }
        }
        return this.mRefreshHeaderView;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void handleScale(float f, int i) {
        if (f <= 1.0f) {
            this.mMeiTuanRefreshView.handleScale(f);
        }
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToIdle() {
        this.mMeiTuanRefreshView.changeToIdle();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToPullDown() {
        this.mMeiTuanRefreshView.changeToPullDown();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToReleaseRefresh() {
        this.mMeiTuanRefreshView.changeToReleaseRefresh();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void changeToRefreshing() {
        this.mMeiTuanRefreshView.changeToRefreshing();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
    public void onEndRefreshing() {
        this.mMeiTuanRefreshView.onEndRefreshing();
    }
}
