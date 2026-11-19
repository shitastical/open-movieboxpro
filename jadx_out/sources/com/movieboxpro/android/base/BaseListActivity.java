package com.movieboxpro.android.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.widget.CustomLoadMoreView;
import com.movieboxpro.androidtv.R;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseListActivity<T, P> extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;
    protected Class<T> mClass;
    protected List<T> mData;
    private View mEmptyView;
    private TextView mErrorMsg;
    private View mErrorView;
    protected Class<P> mPageClass;
    protected RecyclerView mRecyclerView;
    private BGARefreshLayout mRefreshLayout;
    protected TextView mTvTitle;
    protected int mCurrentPage = 1;
    protected int mPageSize = 15;

    private void enableRefresh(boolean b) {
    }

    protected List<View> addHeaderView(RecyclerView recyclerView) {
        return null;
    }

    protected void doSomethingWithData(List<T> list) {
    }

    protected List<T> getData(P p) {
        return null;
    }

    protected abstract void getIntentData(Intent intent);

    protected abstract Observable<String> getServiceData();

    protected int gridLayoutSpan() {
        return 2;
    }

    protected void initEmptyView(TextView emptyText, ImageView emptyImageView) {
    }

    protected abstract void initHolder(BaseViewHolder helper, T item);

    protected abstract int initItemLayout();

    protected void initRecyclerView(RecyclerView recyclerView) {
    }

    protected boolean isOpenLoadMore() {
        return true;
    }

    protected boolean isOpenRefresh() {
        return true;
    }

    protected boolean isVerticalLayout() {
        return true;
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshLayout.BGARefreshLayoutDelegate
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }

    protected OnItemChildClickListener onItemChildClick() {
        return null;
    }

    protected com.chad.library.adapter.base.listener.OnItemClickListener onItemClick() {
        return null;
    }

    protected void onLoadDataComplete(List data) {
    }

    protected void onRequestLoadMoreComplete(List<T> data) {
    }

    protected void test() {
    }

    protected void viewAttachedToWindow(BaseViewHolder holder) {
    }

    protected void viewDetachedFromWindow(BaseViewHolder holder) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mRefreshLayout = (BGARefreshLayout) findViewById(R.id.refresh_layout);
        DensityUtils.addMarginTopEqualStatusBarHeight((RelativeLayout) findViewById(R.id.relative_layout));
        findViewById(R.id.ll_back).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BaseListActivity.this.finish();
            }
        });
        this.mTvTitle = (TextView) findViewById(R.id.tv_title);
        init();
    }

    private void init() {
        this.mData = new ArrayList();
        getIntentData(getIntent());
        BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<T, BaseViewHolder>(initItemLayout(), this.mData) { // from class: com.movieboxpro.android.base.BaseListActivity.2
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            protected void convert(BaseViewHolder baseViewHolder, T t) {
                BaseListActivity.this.initHolder(baseViewHolder, t);
            }
        };
        this.mAdapter = baseQuickAdapter;
        baseQuickAdapter.getLoadMoreModule().setLoadMoreView(new CustomLoadMoreView());
        this.mAdapter.getLoadMoreModule().setPreLoadNumber(8);
        if (isVerticalLayout()) {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridLayoutSpan()));
        }
        List<View> listAddHeaderView = addHeaderView(this.mRecyclerView);
        if (listAddHeaderView != null) {
            Iterator<View> it = listAddHeaderView.iterator();
            while (it.hasNext()) {
                this.mAdapter.addHeaderView(it.next(), 0);
            }
        }
        initRecyclerView(this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.mAdapter);
        initView();
        BGANormalRefreshViewHolder bGANormalRefreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        bGANormalRefreshViewHolder.setRefreshingText(getString(R.string.refresh_loading));
        bGANormalRefreshViewHolder.setPullDownRefreshText(getString(R.string.pull_down_refresh_layout));
        bGANormalRefreshViewHolder.setReleaseRefreshText(getString(R.string.release_refresh_text));
        this.mRefreshLayout.setRefreshViewHolder(bGANormalRefreshViewHolder);
        this.mRefreshLayout.setDelegate(this);
        this.mRefreshLayout.setPullDownRefreshEnable(isOpenRefresh());
        if (onItemClick() != null) {
            this.mAdapter.setOnItemClickListener(onItemClick());
        }
        if (onItemChildClick() != null) {
            this.mAdapter.setOnItemChildClickListener(onItemChildClick());
        }
        this.mAdapter.getLoadMoreModule().setPreLoadNumber(8);
        this.mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        this.mRefreshLayout.beginRefreshing();
        if (isOpenLoadMore()) {
            this.mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.base.BaseListActivity.3
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public void onLoadMore() {
                    if (Network.isConnected(BaseListActivity.this)) {
                        BaseListActivity.this.mCurrentPage++;
                        if (BaseListActivity.this.mPageClass == null) {
                            BaseListActivity.this.loadMoreDataPage();
                            return;
                        } else {
                            BaseListActivity.this.loadMoreDataPageModel();
                            return;
                        }
                    }
                    BaseListActivity.this.mCurrentPage--;
                    BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreFail();
                    ToastUtils.showShort("no network");
                }
            });
        }
        test();
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshLayout.BGARefreshLayoutDelegate
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        this.mCurrentPage = 1;
        refreshData();
    }

    protected void testItems(List<T> data) {
        this.mAdapter.setList(data);
    }

    private void initView() {
        View viewInflate = getLayoutInflater().inflate(R.layout.empty_view_layout, (ViewGroup) this.mRecyclerView.getParent(), false);
        this.mEmptyView = viewInflate;
        initEmptyView((TextView) viewInflate.findViewById(R.id.empty_text), (ImageView) this.mEmptyView.findViewById(R.id.empty_image));
        this.mErrorView = getLayoutInflater().inflate(R.layout.net_error_layout, (ViewGroup) this.mRecyclerView.getParent(), false);
    }

    private void enableLoadMore(boolean b) {
        this.mAdapter.getLoadMoreModule().setEnableLoadMore(b);
    }

    private void loadData() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListActivity.4
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<T> list) {
                super.onNext(list);
                if (list == null || list.size() == 0) {
                    BaseListActivity.this.setEmptyView();
                } else {
                    BaseListActivity.this.mAdapter.setList(list);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListActivity.this.mRefreshLayout.endRefreshing();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                BaseListActivity.this.mRefreshLayout.endRefreshing();
                BaseListActivity.this.setErrorView();
            }
        });
    }

    private void loadDataWithModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListActivity.5
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListActivity.this.getData(p);
                if (data == null || data.size() == 0) {
                    BaseListActivity.this.setEmptyView();
                } else {
                    BaseListActivity.this.mAdapter.setList(data);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListActivity.this.mRefreshLayout.endRefreshing();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                BaseListActivity.this.mRefreshLayout.endRefreshing();
                BaseListActivity.this.setErrorView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorView() {
        this.mAdapter.setEmptyView(this.mErrorView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEmptyView() {
        this.mAdapter.setEmptyView(this.mEmptyView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadMoreDataPage() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListActivity.6
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<T> list) {
                super.onNext(list);
                if (BaseListActivity.this.mCurrentPage == 1) {
                    if (list == null || list.size() == 0) {
                        BaseListActivity.this.mAdapter.setEmptyView(BaseListActivity.this.mEmptyView);
                        return;
                    }
                    BaseListActivity.this.mAdapter.setList(list);
                    if (list.size() < BaseListActivity.this.mPageSize) {
                        BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreEnd(true);
                        return;
                    }
                    return;
                }
                BaseListActivity.this.mAdapter.addData((Collection) list);
                if (list.size() < BaseListActivity.this.mPageSize) {
                    BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.mRefreshLayout.endRefreshing();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.setErrorView();
                    BaseListActivity.this.mRefreshLayout.endRefreshing();
                } else {
                    ToastUtils.showShort("service error");
                    BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreFail();
                    BaseListActivity.this.mCurrentPage--;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadMoreDataPageModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListActivity.7
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListActivity.this.getData(p);
                if (BaseListActivity.this.mCurrentPage == 1) {
                    if (data == null || data.size() == 0) {
                        BaseListActivity.this.mAdapter.setEmptyView(BaseListActivity.this.mEmptyView);
                        return;
                    }
                    BaseListActivity.this.mAdapter.setList(data);
                    if (data.size() < BaseListActivity.this.mPageSize) {
                        BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreEnd(true);
                        return;
                    }
                    return;
                }
                BaseListActivity.this.mAdapter.addData((Collection) data);
                if (data.size() < BaseListActivity.this.mPageSize) {
                    BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.mRefreshLayout.endRefreshing();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.setErrorView();
                    BaseListActivity.this.mRefreshLayout.endRefreshing();
                } else {
                    ToastUtils.showShort("service error");
                    BaseListActivity.this.mAdapter.getLoadMoreModule().loadMoreFail();
                    BaseListActivity.this.mCurrentPage--;
                }
            }
        });
    }

    protected void refreshData() {
        if (Network.isConnected(this)) {
            if (isOpenLoadMore()) {
                this.mCurrentPage = 1;
                if (this.mPageClass == null) {
                    loadMoreDataPage();
                    return;
                } else {
                    loadMoreDataPageModel();
                    return;
                }
            }
            if (this.mPageClass == null) {
                loadData();
                return;
            } else {
                loadDataWithModel();
                return;
            }
        }
        this.mRefreshLayout.endRefreshing();
        setErrorView();
    }
}
