package com.movieboxpro.android.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.tv.LoginActivity;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.widget.CustomLoadMoreView;
import com.movieboxpro.androidtv.R;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.TwoWayLayoutManager;
import com.owen.tvrecyclerview.widget.GridLayoutManager;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseListFragment<T, P> extends BaseLazyFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private View emptyView;
    private ViewStub emptyViewStub;
    private View errorView;
    private ViewStub errorViewStub;
    private KProgressHUD hud;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;
    protected Class<T> mClass;
    protected List<T> mData;
    private View mEmptyView;
    private TextView mErrorMsg;
    private View mErrorView;
    private FocusBorder mFocusBorder;
    protected Class<P> mPageClass;
    protected TvRecyclerView mRecyclerView;
    private BGARefreshLayout mRefreshLayout;
    protected int mCurrentPage = 1;
    protected int mPageSize = 15;
    protected int preLoadNum = 12;

    private void enableRefresh(boolean b) {
    }

    private void initView() {
    }

    protected List<View> addHeaderView(RecyclerView recyclerView) {
        return null;
    }

    protected void doSomethingWithData(List<T> list) {
    }

    protected boolean enableEventBus() {
        return true;
    }

    protected boolean enableMultiAdapter() {
        return false;
    }

    protected abstract void getBundle(Bundle arguments);

    protected List<T> getData(P model) {
        return null;
    }

    protected int getItemViewType(int position) {
        return -1;
    }

    protected abstract Observable<String> getServiceData();

    protected int gridLayoutSpan() {
        return 6;
    }

    protected void initEmptyView(TextView emptyText, ImageView emptyImageView) {
    }

    protected void initFocusUpListener() {
    }

    protected abstract void initHolder(BaseViewHolder helper, T item);

    protected abstract int initItemLayout();

    protected int initItemType(T model) {
        return 0;
    }

    protected void initRecyclerView(RecyclerView recyclerView) {
    }

    protected float initScaleValue() {
        return 1.2f;
    }

    protected boolean isNeedLazyLoad() {
        return true;
    }

    protected boolean isNeedListenerFocusUp() {
        return false;
    }

    protected boolean isNeedLogin() {
        return false;
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

    protected void onFocus(int position) {
    }

    protected OnItemChildClickListener onItemChildClick() {
        return null;
    }

    protected com.chad.library.adapter.base.listener.OnItemClickListener onItemClick() {
        return null;
    }

    protected void onLastFocus(int position) {
    }

    protected void onLoadComplete() {
    }

    protected void onLoadDataComplete(List data) {
    }

    protected OnItemLongClickListener onLongClick() {
        return null;
    }

    protected void onRecyclerViewFocus() {
    }

    protected void onRequestLoadMoreComplete(List<T> data) {
    }

    protected boolean openLowMemoryEmpty() {
        return false;
    }

    protected void registerItemType(BaseMultiTypeDelegate<T> multiTypeDelegate) {
    }

    protected void test() {
    }

    protected void viewAttachedToWindow(BaseViewHolder holder) {
    }

    protected void viewDetachedFromWindow(BaseViewHolder holder) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate;
        super.onCreateView(inflater, container, savedInstanceState);
        if (gridLayoutSpan() == 6) {
            viewInflate = inflater.inflate(R.layout.fragment_recycler_layout, container, false);
        } else {
            viewInflate = inflater.inflate(R.layout.fragment_recycler3_layout, container, false);
        }
        this.mRecyclerView = (TvRecyclerView) viewInflate.findViewById(R.id.recyclerView);
        this.mRefreshLayout = (BGARefreshLayout) viewInflate.findViewById(R.id.refresh_layout);
        this.emptyViewStub = (ViewStub) viewInflate.findViewById(R.id.emptyViewStub);
        this.errorViewStub = (ViewStub) viewInflate.findViewById(R.id.errorViewStub);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isNeedLazyLoad()) {
            return;
        }
        init();
        this.mRefreshLayout.beginRefreshing();
    }

    private void init() {
        this.mData = new ArrayList();
        getBundle(getArguments());
        if (enableMultiAdapter()) {
            this.mAdapter = new MultiItemAdapter();
        } else {
            this.mAdapter = new CommonBaseAdapter<T>(initItemLayout(), this.mData) { // from class: com.movieboxpro.android.base.BaseListFragment.1
                @Override // com.chad.library.adapter.base.BaseQuickAdapter
                protected void convert(BaseViewHolder baseViewHolder, T t) {
                    BaseListFragment.this.initHolder(baseViewHolder, t);
                }
            };
        }
        this.mAdapter.getLoadMoreModule().setLoadMoreView(new CustomLoadMoreView());
        this.mAdapter.getLoadMoreModule().setPreLoadNumber(this.preLoadNum);
        if (gridLayoutSpan() > 0) {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(TwoWayLayoutManager.Orientation.VERTICAL, gridLayoutSpan(), gridLayoutSpan()));
        }
        List<View> listAddHeaderView = addHeaderView(this.mRecyclerView);
        if (listAddHeaderView != null) {
            Iterator<View> it = listAddHeaderView.iterator();
            while (it.hasNext()) {
                this.mAdapter.addHeaderView(it.next(), 0);
            }
        }
        initRecyclerView(this.mRecyclerView);
        RecyclerView.ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
        initView();
        BGANormalRefreshViewHolder bGANormalRefreshViewHolder = new BGANormalRefreshViewHolder(getContext(), false);
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
        if (onLongClick() != null) {
            this.mAdapter.setOnItemLongClickListener(onLongClick());
        }
        this.mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        if (isOpenLoadMore()) {
            this.mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.base.BaseListFragment.2
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public void onLoadMore() {
                    BaseListFragment.this.loadMore();
                }
            });
        }
        test();
        initTvRecyclerView();
        initFocusUpListener();
    }

    protected Boolean fixViewType(int type) {
        return false;
    }

    protected void loadMore() {
        if (Network.isConnected(getContext())) {
            this.mCurrentPage++;
            if (this.mPageClass == null) {
                loadMoreDataPage();
                return;
            } else {
                loadMoreDataPageModel();
                return;
            }
        }
        this.mCurrentPage--;
        this.mAdapter.getLoadMoreModule().loadMoreFail();
        ToastUtils.showShort("no network");
    }

    private void initTvRecyclerView() {
        this.mFocusBorder = new FocusBorder.Builder().asColor().borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().build(this);
        RecyclerView.ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        if (getContext() != null) {
            this.mRecyclerView.setSpacingWithMargins(DensityUtils.dp2px(getContext(), 20.0f), DensityUtils.dp2px(getContext(), 20.0f));
        }
        this.mRecyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.base.BaseListFragment.3
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                BaseListFragment.this.onLastFocus(position);
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                BaseListFragment.this.onFocus(position);
                BaseListFragment.this.mFocusBorder.onFocus(itemView, FocusBorder.OptionsFactory.get(BaseListFragment.this.initScaleValue(), BaseListFragment.this.initScaleValue(), 0.0f));
            }
        });
        if (isNeedListenerFocusUp()) {
            this.mRecyclerView.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener(this) { // from class: com.movieboxpro.android.base.BaseListFragment.4
                @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
                public boolean onInBorderKeyEvent(int direction, View focused) {
                    if (direction != 33) {
                        return false;
                    }
                    EventBus.getDefault().post(new ListFocusUpEvent());
                    return true;
                }
            });
        }
        this.mRecyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.base.BaseListFragment.5
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    BaseListFragment.this.mFocusBorder.setVisible(false, true);
                } else {
                    BaseListFragment.this.onRecyclerViewFocus();
                }
            }
        });
    }

    @Override // cn.bingoogolapple.refreshlayout.BGARefreshLayout.BGARefreshLayoutDelegate
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        if (isNeedLogin()) {
            if (App.isLogin()) {
                this.mCurrentPage = 1;
                refreshData();
                return;
            } else {
                setNoLoginError();
                this.mRefreshLayout.endRefreshing();
                return;
            }
        }
        this.mCurrentPage = 1;
        refreshData();
    }

    protected void testItems(List<T> data) {
        this.mAdapter.setList(data);
    }

    private void enableLoadMore(boolean b) {
        this.mAdapter.getLoadMoreModule().setEnableLoadMore(b);
    }

    private void loadData() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListFragment.6
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<T> list) {
                super.onNext(list);
                if (list == null || list.size() == 0) {
                    BaseListFragment.this.setEmptyView();
                } else {
                    BaseListFragment.this.doSomethingWithData(list);
                    BaseListFragment.this.mAdapter.setList(list);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListFragment.this.mRefreshLayout.endRefreshing();
                BaseListFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                BaseListFragment.this.mRefreshLayout.endRefreshing();
                BaseListFragment.this.setErrorView(e.getMessage());
            }
        });
    }

    private void loadDataWithModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListFragment.7
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListFragment.this.getData(p);
                if (data == null || data.size() == 0) {
                    BaseListFragment.this.setEmptyView();
                } else {
                    BaseListFragment.this.doSomethingWithData(data);
                    BaseListFragment.this.mAdapter.setList(data);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListFragment.this.mRefreshLayout.endRefreshing();
                BaseListFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                BaseListFragment.this.mRefreshLayout.endRefreshing();
                BaseListFragment.this.setErrorView(e.getMessage());
            }
        });
    }

    private void loadMoreDataPage() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListFragment.8
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<T> list) {
                super.onNext(list);
                if (BaseListFragment.this.mCurrentPage == 1) {
                    if (list == null || list.size() == 0) {
                        BaseListFragment.this.mAdapter.setList(null);
                        BaseListFragment.this.setEmptyView();
                        return;
                    } else {
                        BaseListFragment.this.doSomethingWithData(list);
                        BaseListFragment.this.mAdapter.setList(list);
                        return;
                    }
                }
                BaseListFragment.this.doSomethingWithData(list);
                BaseListFragment.this.mAdapter.addData((Collection) list);
                if (list.size() == 0) {
                    BaseListFragment.this.mAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    BaseListFragment.this.mAdapter.getLoadMoreModule().loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListFragment.this.mCurrentPage == 1) {
                    BaseListFragment.this.mRefreshLayout.endRefreshing();
                }
                BaseListFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                if (BaseListFragment.this.mCurrentPage == 1) {
                    BaseListFragment.this.setErrorView(e.getMessage());
                    BaseListFragment.this.mRefreshLayout.endRefreshing();
                } else {
                    ToastUtils.showShort("service error");
                    BaseListFragment.this.mAdapter.getLoadMoreModule().loadMoreFail();
                    BaseListFragment.this.mCurrentPage--;
                }
            }
        });
    }

    private void loadMoreDataPageModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListFragment.9
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListFragment.this.getData(p);
                if (BaseListFragment.this.mCurrentPage == 1) {
                    if (data == null || data.size() == 0) {
                        BaseListFragment.this.mAdapter.setList(null);
                        BaseListFragment.this.setEmptyView();
                        return;
                    }
                    BaseListFragment.this.doSomethingWithData(data);
                    BaseListFragment.this.mAdapter.setList(data);
                    if (data.size() < BaseListFragment.this.mPageSize) {
                        BaseListFragment.this.mAdapter.getLoadMoreModule().loadMoreEnd(true);
                        return;
                    }
                    return;
                }
                BaseListFragment.this.doSomethingWithData(data);
                BaseListFragment.this.mAdapter.addData((Collection) data);
                if (data.size() == 0) {
                    BaseListFragment.this.mAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    BaseListFragment.this.mAdapter.getLoadMoreModule().loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListFragment.this.mCurrentPage == 1) {
                    BaseListFragment.this.mRefreshLayout.endRefreshing();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                if (BaseListFragment.this.mCurrentPage == 1) {
                    BaseListFragment.this.setErrorView(e.getMessage());
                    BaseListFragment.this.mRefreshLayout.endRefreshing();
                } else {
                    ToastUtils.showShort("service error");
                    BaseListFragment.this.mAdapter.getLoadMoreModule().loadMoreFail();
                    BaseListFragment.this.mCurrentPage--;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorView(String text) {
        if (this.errorView == null) {
            this.errorView = this.errorViewStub.inflate();
        }
        ((TextView) this.errorView.findViewById(R.id.empty_text)).setText(String.format("Load failed:%s", text));
        TextView textView = (TextView) this.errorView.findViewById(R.id.tvTryAgain);
        textView.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.base.BaseListFragment.10
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 1) {
                    return false;
                }
                if (keyCode == 19) {
                    EventBus.getDefault().post(new ListFocusUpEvent());
                    return false;
                }
                if (keyCode == 23) {
                    BaseListFragment.this.startRefresh();
                    return false;
                }
                if (keyCode == 66) {
                    BaseListFragment.this.startRefresh();
                }
                return false;
            }
        });
        textView.setText("Try again");
        ((ImageView) this.errorView.findViewById(R.id.empty_image)).setImageResource(R.drawable.ic_load_error);
        textView.setOnClickListener(new View.OnClickListener(this) { // from class: com.movieboxpro.android.base.BaseListFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
            }
        });
        this.errorView.setVisibility(0);
    }

    private void setNoLoginError() {
        if (this.errorView == null) {
            this.errorView = this.errorViewStub.inflate();
        }
        TextView textView = (TextView) this.errorView.findViewById(R.id.tvTryAgain);
        textView.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.base.BaseListFragment.12
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 1) {
                    return false;
                }
                if (keyCode == 19) {
                    EventBus.getDefault().post(new ListFocusUpEvent());
                    return false;
                }
                if (keyCode == 23) {
                    LoginActivity.INSTANCE.start(BaseListFragment.this.getContext());
                    return false;
                }
                if (keyCode == 66) {
                    LoginActivity.INSTANCE.start(BaseListFragment.this.getContext());
                }
                return false;
            }
        });
        ((ImageView) this.errorView.findViewById(R.id.empty_image)).setImageResource(R.mipmap.ic_no_login);
        ((TextView) this.errorView.findViewById(R.id.empty_text)).setText("Please login first.");
        textView.setText(API.USER.LOGIN);
        textView.setOnClickListener(new View.OnClickListener(this) { // from class: com.movieboxpro.android.base.BaseListFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
            }
        });
        this.errorView.setVisibility(0);
    }

    private void hideErrorView() {
        View view = this.errorView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEmptyView() {
        if (this.emptyView == null) {
            View viewInflate = this.emptyViewStub.inflate();
            this.emptyView = viewInflate;
            TextView textView = (TextView) viewInflate.findViewById(R.id.tvTryAgain);
            textView.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.base.BaseListFragment.14
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == 1) {
                        return true;
                    }
                    if (keyCode == 19) {
                        EventBus.getDefault().post(new ListFocusUpEvent());
                        return false;
                    }
                    if (keyCode == 23) {
                        BaseListFragment.this.startRefresh();
                    }
                    return false;
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListFragment.15
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    BaseListFragment.this.startRefresh();
                }
            });
        }
        this.emptyView.setVisibility(0);
    }

    private void hideEmptyView() {
        View view = this.emptyView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void startRefresh() {
        if (this.mAdapter == null) {
            return;
        }
        this.mRefreshLayout.beginRefreshing();
    }

    public void refreshData() {
        hideEmptyView();
        hideErrorView();
        if (Network.isConnected(getContext())) {
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
        setErrorView("no internet");
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentFirstVisible() {
        if (isNeedLazyLoad()) {
            init();
            this.mRefreshLayout.beginRefreshing();
        }
    }

    protected void route(Class<? extends Activity> claz, Bundle data, View v, String transtion) {
        Intent intent = new Intent(getContext(), claz);
        if (data != null) {
            intent.putExtras(data);
        }
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(getContext(), intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), Pair.create(v, transtion)).toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResumeNoFirst() {
        BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter;
        super.onFragmentResumeNoFirst();
        if (!openLowMemoryEmpty() || (baseQuickAdapter = this.mAdapter) == null || baseQuickAdapter.getData().size() != 0 || this.mRefreshLayout.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
            return;
        }
        startRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLowMemory(LowMemoryEvent event) {
        BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter;
        if (!openLowMemoryEmpty() || this.isVisibleToUser || (baseQuickAdapter = this.mAdapter) == null) {
            return;
        }
        baseQuickAdapter.setList(new ArrayList());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLoadingView() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            KProgressHUD kProgressHUD2 = this.hud;
            if (kProgressHUD2 == null || !kProgressHUD2.isShowing()) {
                this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideLoadingView() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            return;
        }
        this.hud.dismiss();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (!enableEventBus() || EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (enableEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    class MultiItemAdapter extends BaseLoadmoreDelegateMultiAdapter<T, BaseViewHolder> {
        MultiItemAdapter() {
            super(null);
            BaseMultiTypeDelegate<T> baseMultiTypeDelegate = new BaseMultiTypeDelegate<T>() { // from class: com.movieboxpro.android.base.BaseListFragment.MultiItemAdapter.1
                @Override // com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
                public int getItemType(List<? extends T> list, int i) {
                    return BaseListFragment.this.initItemType(list.get(i));
                }
            };
            setMultiTypeDelegate(baseMultiTypeDelegate);
            BaseListFragment.this.registerItemType(baseMultiTypeDelegate);
        }

        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        protected void convert(BaseViewHolder helper, T item) {
            BaseListFragment.this.initHolder(helper, item);
        }
    }
}
