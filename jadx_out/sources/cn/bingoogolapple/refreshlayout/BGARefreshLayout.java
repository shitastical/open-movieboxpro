package cn.bingoogolapple.refreshlayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.refreshlayout.util.BGARefreshScrollingUtil;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes4.dex */
public class BGARefreshLayout extends LinearLayout {
    private static final String TAG = "BGARefreshLayout";
    private AbsListView mAbsListView;
    private View mContentView;
    private RefreshStatus mCurrentRefreshStatus;
    private View mCustomHeaderView;
    private Runnable mDelayHiddenLoadingMoreViewTask;
    private BGARefreshLayoutDelegate mDelegate;
    private Handler mHandler;
    private float mInterceptTouchDownX;
    private float mInterceptTouchDownY;
    private boolean mIsCustomHeaderViewScrollable;
    private boolean mIsInitedContentViewScrollListener;
    private boolean mIsLoadingMore;
    private boolean mIsShowLoadingMoreView;
    private View mLoadMoreFooterView;
    private int mLoadMoreFooterViewHeight;
    private int mMaxWholeHeaderViewPaddingTop;
    private int mMinWholeHeaderViewPaddingTop;
    private View mNormalView;
    private boolean mPullDownRefreshEnable;
    private RecyclerView mRecyclerView;
    private int mRefreshDownY;
    private View mRefreshHeaderView;
    private int mRefreshHeaderViewHeight;
    private BGARefreshScaleDelegate mRefreshScaleDelegate;
    private BGARefreshViewHolder mRefreshViewHolder;
    private ScrollView mScrollView;
    private BGAStickyNavLayout mStickyNavLayout;
    private int mTouchSlop;
    private WebView mWebView;
    private int mWholeHeaderDownY;
    private LinearLayout mWholeHeaderView;
    private int mWholeHeaderViewDownPaddingTop;

    public interface BGARefreshLayoutDelegate {
        boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bGARefreshLayout);

        void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bGARefreshLayout);
    }

    public interface BGARefreshScaleDelegate {
        void onRefreshScaleChanged(float f, int i);
    }

    public enum RefreshStatus {
        IDLE,
        PULL_DOWN,
        RELEASE_REFRESH,
        REFRESHING
    }

    public BGARefreshLayout(Context context) {
        this(context, null);
    }

    public BGARefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsCustomHeaderViewScrollable = false;
        this.mCurrentRefreshStatus = RefreshStatus.IDLE;
        this.mWholeHeaderDownY = -1;
        this.mIsLoadingMore = false;
        this.mInterceptTouchDownX = -1.0f;
        this.mInterceptTouchDownY = -1.0f;
        this.mWholeHeaderViewDownPaddingTop = 0;
        this.mRefreshDownY = -1;
        this.mIsInitedContentViewScrollListener = false;
        this.mIsShowLoadingMoreView = true;
        this.mPullDownRefreshEnable = true;
        this.mDelayHiddenLoadingMoreViewTask = new Runnable() { // from class: cn.bingoogolapple.refreshlayout.BGARefreshLayout.6
            @Override // java.lang.Runnable
            public void run() {
                BGARefreshLayout.this.mIsLoadingMore = false;
                BGARefreshLayout.this.mRefreshViewHolder.onEndLoadingMore();
                BGARefreshLayout.this.mLoadMoreFooterView.setVisibility(8);
            }
        };
        setOrientation(1);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mHandler = new Handler(Looper.getMainLooper());
        initWholeHeaderView();
    }

    private void initWholeHeaderView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.mWholeHeaderView = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mWholeHeaderView.setOrientation(1);
        addView(this.mWholeHeaderView);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new RuntimeException("BGARefreshLayout必须有且只有一个子控件");
        }
        View childAt = getChildAt(1);
        this.mContentView = childAt;
        if (childAt instanceof AbsListView) {
            this.mAbsListView = (AbsListView) childAt;
            return;
        }
        if (childAt instanceof RecyclerView) {
            this.mRecyclerView = (RecyclerView) childAt;
            return;
        }
        if (childAt instanceof ScrollView) {
            this.mScrollView = (ScrollView) childAt;
            return;
        }
        if (childAt instanceof WebView) {
            this.mWebView = (WebView) childAt;
            return;
        }
        if (childAt instanceof BGAStickyNavLayout) {
            BGAStickyNavLayout bGAStickyNavLayout = (BGAStickyNavLayout) childAt;
            this.mStickyNavLayout = bGAStickyNavLayout;
            bGAStickyNavLayout.setRefreshLayout(this);
        } else {
            this.mNormalView = childAt;
            childAt.setClickable(true);
        }
    }

    public void setRefreshViewHolder(BGARefreshViewHolder bGARefreshViewHolder) {
        this.mRefreshViewHolder = bGARefreshViewHolder;
        bGARefreshViewHolder.setRefreshLayout(this);
        initRefreshHeaderView();
        initLoadMoreFooterView();
    }

    public void startChangeWholeHeaderViewPaddingTop(int i) {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.mWholeHeaderView.getPaddingTop(), this.mWholeHeaderView.getPaddingTop() - i);
        valueAnimatorOfInt.setDuration(this.mRefreshViewHolder.getTopAnimDuration());
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.bingoogolapple.refreshlayout.BGARefreshLayout.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BGARefreshLayout.this.mWholeHeaderView.setPadding(0, ((Integer) valueAnimator.getAnimatedValue()).intValue(), 0, 0);
            }
        });
        valueAnimatorOfInt.start();
    }

    private void initRefreshHeaderView() {
        View refreshHeaderView = this.mRefreshViewHolder.getRefreshHeaderView();
        this.mRefreshHeaderView = refreshHeaderView;
        if (refreshHeaderView != null) {
            refreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            int refreshHeaderViewHeight = this.mRefreshViewHolder.getRefreshHeaderViewHeight();
            this.mRefreshHeaderViewHeight = refreshHeaderViewHeight;
            this.mMinWholeHeaderViewPaddingTop = -refreshHeaderViewHeight;
            this.mMaxWholeHeaderViewPaddingTop = (int) (refreshHeaderViewHeight * this.mRefreshViewHolder.getSpringDistanceScale());
            this.mWholeHeaderView.setPadding(0, this.mMinWholeHeaderViewPaddingTop, 0, 0);
            this.mWholeHeaderView.addView(this.mRefreshHeaderView, 0);
        }
    }

    public void setCustomHeaderView(View view, boolean z) {
        View view2 = this.mCustomHeaderView;
        if (view2 != null && view2.getParent() != null) {
            ((ViewGroup) this.mCustomHeaderView.getParent()).removeView(this.mCustomHeaderView);
        }
        this.mCustomHeaderView = view;
        if (view != null) {
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.mWholeHeaderView.addView(this.mCustomHeaderView);
            this.mIsCustomHeaderViewScrollable = z;
        }
    }

    private void initLoadMoreFooterView() {
        View loadMoreFooterView = this.mRefreshViewHolder.getLoadMoreFooterView();
        this.mLoadMoreFooterView = loadMoreFooterView;
        if (loadMoreFooterView != null) {
            loadMoreFooterView.measure(0, 0);
            this.mLoadMoreFooterViewHeight = this.mLoadMoreFooterView.getMeasuredHeight();
            this.mLoadMoreFooterView.setVisibility(8);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsInitedContentViewScrollListener || this.mLoadMoreFooterView == null) {
            return;
        }
        setRecyclerViewOnScrollListener();
        setAbsListViewOnScrollListener();
        addView(this.mLoadMoreFooterView, getChildCount());
        this.mIsInitedContentViewScrollListener = true;
    }

    private void setRecyclerViewOnScrollListener() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: cn.bingoogolapple.refreshlayout.BGARefreshLayout.2
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                    if (i == 0 || i == 2) {
                        BGARefreshLayout bGARefreshLayout = BGARefreshLayout.this;
                        if (bGARefreshLayout.shouldHandleRecyclerViewLoadingMore(bGARefreshLayout.mRecyclerView)) {
                            BGARefreshLayout.this.beginLoadingMore();
                        }
                    }
                }
            });
        }
    }

    private void setAbsListViewOnScrollListener() {
        if (this.mAbsListView != null) {
            try {
                Field declaredField = AbsListView.class.getDeclaredField("mOnScrollListener");
                declaredField.setAccessible(true);
                final AbsListView.OnScrollListener onScrollListener = (AbsListView.OnScrollListener) declaredField.get(this.mAbsListView);
                this.mAbsListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: cn.bingoogolapple.refreshlayout.BGARefreshLayout.3
                    @Override // android.widget.AbsListView.OnScrollListener
                    public void onScrollStateChanged(AbsListView absListView, int i) {
                        if (i == 0 || i == 2) {
                            BGARefreshLayout bGARefreshLayout = BGARefreshLayout.this;
                            if (bGARefreshLayout.shouldHandleAbsListViewLoadingMore(bGARefreshLayout.mAbsListView)) {
                                BGARefreshLayout.this.beginLoadingMore();
                            }
                        }
                        AbsListView.OnScrollListener onScrollListener2 = onScrollListener;
                        if (onScrollListener2 != null) {
                            onScrollListener2.onScrollStateChanged(absListView, i);
                        }
                    }

                    @Override // android.widget.AbsListView.OnScrollListener
                    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                        AbsListView.OnScrollListener onScrollListener2 = onScrollListener;
                        if (onScrollListener2 != null) {
                            onScrollListener2.onScroll(absListView, i, i2, i3);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean shouldHandleAbsListViewLoadingMore(AbsListView absListView) {
        if (this.mIsLoadingMore || this.mCurrentRefreshStatus == RefreshStatus.REFRESHING || this.mLoadMoreFooterView == null || this.mDelegate == null || absListView == null || absListView.getAdapter() == null || ((ListAdapter) absListView.getAdapter()).getCount() == 0) {
            return false;
        }
        return BGARefreshScrollingUtil.isAbsListViewToBottom(absListView);
    }

    public boolean shouldHandleRecyclerViewLoadingMore(RecyclerView recyclerView) {
        if (this.mIsLoadingMore || this.mCurrentRefreshStatus == RefreshStatus.REFRESHING || this.mLoadMoreFooterView == null || this.mDelegate == null || recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() == 0) {
            return false;
        }
        return BGARefreshScrollingUtil.isRecyclerViewToBottom(recyclerView);
    }

    private boolean shouldHandleLoadingMore() {
        if (!this.mIsLoadingMore && this.mCurrentRefreshStatus != RefreshStatus.REFRESHING && this.mLoadMoreFooterView != null && this.mDelegate != null) {
            if (this.mNormalView != null || BGARefreshScrollingUtil.isWebViewToBottom(this.mWebView) || BGARefreshScrollingUtil.isScrollViewToBottom(this.mScrollView)) {
                return true;
            }
            AbsListView absListView = this.mAbsListView;
            if (absListView != null) {
                return shouldHandleAbsListViewLoadingMore(absListView);
            }
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return shouldHandleRecyclerViewLoadingMore(recyclerView);
            }
            BGAStickyNavLayout bGAStickyNavLayout = this.mStickyNavLayout;
            if (bGAStickyNavLayout != null) {
                return bGAStickyNavLayout.shouldHandleLoadingMore();
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x008a  */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mInterceptTouchDownX = motionEvent.getRawX();
            this.mInterceptTouchDownY = motionEvent.getRawY();
        } else if (action == 1) {
            this.mInterceptTouchDownX = -1.0f;
            this.mInterceptTouchDownY = -1.0f;
        } else if (action != 2) {
            if (action == 3) {
                this.mInterceptTouchDownX = -1.0f;
                this.mInterceptTouchDownY = -1.0f;
            }
        } else if (!this.mIsLoadingMore && this.mCurrentRefreshStatus != RefreshStatus.REFRESHING) {
            if (this.mInterceptTouchDownX == -1.0f) {
                this.mInterceptTouchDownX = (int) motionEvent.getRawX();
            }
            if (this.mInterceptTouchDownY == -1.0f) {
                this.mInterceptTouchDownY = (int) motionEvent.getRawY();
            }
            int rawY = (int) (motionEvent.getRawY() - this.mInterceptTouchDownY);
            if (Math.abs(motionEvent.getRawX() - this.mInterceptTouchDownX) < Math.abs(rawY) && this.mRefreshHeaderView != null && ((rawY > this.mTouchSlop && shouldHandleRefresh()) || ((rawY < (-this.mTouchSlop) && shouldHandleLoadingMore()) || ((rawY < (-this.mTouchSlop) && !isWholeHeaderViewCompleteInvisible()) || (rawY > this.mTouchSlop && shouldInterceptToMoveCustomHeaderViewDown()))))) {
                motionEvent.setAction(3);
                super.onInterceptTouchEvent(motionEvent);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mIsCustomHeaderViewScrollable && !isWholeHeaderViewCompleteInvisible()) {
            super.dispatchTouchEvent(motionEvent);
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean shouldHandleRefresh() {
        if (!this.mPullDownRefreshEnable || this.mIsLoadingMore || this.mCurrentRefreshStatus == RefreshStatus.REFRESHING || this.mRefreshHeaderView == null || this.mDelegate == null) {
            return false;
        }
        return isContentViewToTop();
    }

    private boolean isContentViewToTop() {
        return this.mNormalView != null || BGARefreshScrollingUtil.isScrollViewOrWebViewToTop(this.mWebView) || BGARefreshScrollingUtil.isScrollViewOrWebViewToTop(this.mScrollView) || BGARefreshScrollingUtil.isAbsListViewToTop(this.mAbsListView) || BGARefreshScrollingUtil.isRecyclerViewToTop(this.mRecyclerView) || BGARefreshScrollingUtil.isStickyNavLayoutToTop(this.mStickyNavLayout);
    }

    private boolean shouldInterceptToMoveCustomHeaderViewDown() {
        return isContentViewToTop() && this.mCustomHeaderView != null && this.mIsCustomHeaderViewScrollable && !isCustomHeaderViewCompleteVisible();
    }

    private boolean shouldInterceptToMoveCustomHeaderViewUp() {
        return isContentViewToTop() && this.mCustomHeaderView != null && this.mIsCustomHeaderViewScrollable && !isWholeHeaderViewCompleteInvisible();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001b  */
    /* JADX WARN: Code duplicated, block: B:17:0x0021 A[RETURN] */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mRefreshHeaderView != null) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mWholeHeaderDownY = (int) motionEvent.getY();
                if (this.mCustomHeaderView != null) {
                    this.mWholeHeaderViewDownPaddingTop = this.mWholeHeaderView.getPaddingTop();
                }
                if (this.mCustomHeaderView == null || !this.mIsCustomHeaderViewScrollable) {
                    this.mRefreshDownY = (int) motionEvent.getY();
                }
                if (isWholeHeaderViewCompleteInvisible()) {
                    this.mRefreshDownY = (int) motionEvent.getY();
                    return true;
                }
            } else if (action == 1) {
                if (handleActionUpOrCancel(motionEvent)) {
                    return true;
                }
            } else if (action != 2) {
                if (action == 3) {
                    if (handleActionUpOrCancel(motionEvent)) {
                        return true;
                    }
                }
            } else if (handleActionMove(motionEvent)) {
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean isCustomHeaderViewCompleteVisible() {
        if (this.mCustomHeaderView == null) {
            return true;
        }
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int i = iArr[1];
        this.mCustomHeaderView.getLocationOnScreen(iArr);
        return i <= iArr[1];
    }

    private boolean isWholeHeaderViewCompleteInvisible() {
        if (this.mCustomHeaderView == null || !this.mIsCustomHeaderViewScrollable) {
            return true;
        }
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int i = iArr[1];
        this.mWholeHeaderView.getLocationOnScreen(iArr);
        return iArr[1] + this.mWholeHeaderView.getMeasuredHeight() <= i;
    }

    private boolean handleActionMove(MotionEvent motionEvent) {
        if (this.mCurrentRefreshStatus != RefreshStatus.REFRESHING && !this.mIsLoadingMore) {
            if ((this.mCustomHeaderView == null || !this.mIsCustomHeaderViewScrollable) && this.mRefreshDownY == -1) {
                this.mRefreshDownY = (int) motionEvent.getY();
            }
            if (this.mCustomHeaderView != null && this.mIsCustomHeaderViewScrollable && isCustomHeaderViewCompleteVisible() && this.mRefreshDownY == -1) {
                this.mRefreshDownY = (int) motionEvent.getY();
            }
            int y = (int) ((((int) motionEvent.getY()) - this.mRefreshDownY) / this.mRefreshViewHolder.getPaddingTopScale());
            if (y > 0 && shouldHandleRefresh() && isCustomHeaderViewCompleteVisible()) {
                int i = this.mMinWholeHeaderViewPaddingTop + y;
                if (i > 0 && this.mCurrentRefreshStatus != RefreshStatus.RELEASE_REFRESH) {
                    this.mCurrentRefreshStatus = RefreshStatus.RELEASE_REFRESH;
                    handleRefreshStatusChanged();
                    this.mRefreshViewHolder.handleScale(1.0f, y);
                    BGARefreshScaleDelegate bGARefreshScaleDelegate = this.mRefreshScaleDelegate;
                    if (bGARefreshScaleDelegate != null) {
                        bGARefreshScaleDelegate.onRefreshScaleChanged(1.0f, y);
                    }
                } else if (i < 0) {
                    if (this.mCurrentRefreshStatus != RefreshStatus.PULL_DOWN) {
                        boolean z = this.mCurrentRefreshStatus != RefreshStatus.IDLE;
                        this.mCurrentRefreshStatus = RefreshStatus.PULL_DOWN;
                        if (z) {
                            handleRefreshStatusChanged();
                        }
                    }
                    float f = 1.0f - ((i * 1.0f) / this.mMinWholeHeaderViewPaddingTop);
                    this.mRefreshViewHolder.handleScale(f, y);
                    BGARefreshScaleDelegate bGARefreshScaleDelegate2 = this.mRefreshScaleDelegate;
                    if (bGARefreshScaleDelegate2 != null) {
                        bGARefreshScaleDelegate2.onRefreshScaleChanged(f, y);
                    }
                }
                this.mWholeHeaderView.setPadding(0, Math.min(i, this.mMaxWholeHeaderViewPaddingTop), 0, 0);
                if (this.mRefreshViewHolder.canChangeToRefreshingStatus()) {
                    this.mWholeHeaderDownY = -1;
                    this.mRefreshDownY = -1;
                    beginRefreshing();
                }
                return true;
            }
            if (this.mCustomHeaderView != null && this.mIsCustomHeaderViewScrollable) {
                if (this.mWholeHeaderDownY == -1) {
                    this.mWholeHeaderDownY = (int) motionEvent.getY();
                    if (this.mCustomHeaderView != null) {
                        this.mWholeHeaderViewDownPaddingTop = this.mWholeHeaderView.getPaddingTop();
                    }
                }
                int y2 = ((int) motionEvent.getY()) - this.mWholeHeaderDownY;
                if ((this.mPullDownRefreshEnable && !isWholeHeaderViewCompleteInvisible()) || ((y2 > 0 && shouldInterceptToMoveCustomHeaderViewDown()) || (y2 < 0 && shouldInterceptToMoveCustomHeaderViewUp()))) {
                    int measuredHeight = this.mWholeHeaderViewDownPaddingTop + y2;
                    if (measuredHeight < this.mMinWholeHeaderViewPaddingTop - this.mCustomHeaderView.getMeasuredHeight()) {
                        measuredHeight = this.mMinWholeHeaderViewPaddingTop - this.mCustomHeaderView.getMeasuredHeight();
                    }
                    this.mWholeHeaderView.setPadding(0, measuredHeight, 0, 0);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean handleActionUpOrCancel(MotionEvent motionEvent) {
        View view = this.mCustomHeaderView;
        boolean z = true;
        boolean z2 = (view == null || !(view == null || this.mIsCustomHeaderViewScrollable)) && this.mWholeHeaderView.getPaddingTop() != this.mMinWholeHeaderViewPaddingTop;
        if (this.mCurrentRefreshStatus == RefreshStatus.PULL_DOWN || this.mCurrentRefreshStatus == RefreshStatus.IDLE) {
            View view2 = this.mCustomHeaderView;
            if (view2 == null || (view2 != null && this.mWholeHeaderView.getPaddingTop() < 0 && this.mWholeHeaderView.getPaddingTop() > this.mMinWholeHeaderViewPaddingTop)) {
                hiddenRefreshHeaderView();
            }
            this.mCurrentRefreshStatus = RefreshStatus.IDLE;
            handleRefreshStatusChanged();
        } else if (this.mCurrentRefreshStatus == RefreshStatus.RELEASE_REFRESH) {
            beginRefreshing();
        }
        if (this.mRefreshDownY == -1) {
            this.mRefreshDownY = (int) motionEvent.getY();
        }
        int y = ((int) motionEvent.getY()) - this.mRefreshDownY;
        if (!shouldHandleLoadingMore() || y > 0) {
            z = z2;
        } else {
            beginLoadingMore();
        }
        this.mWholeHeaderDownY = -1;
        this.mRefreshDownY = -1;
        return z;
    }

    /* JADX INFO: renamed from: cn.bingoogolapple.refreshlayout.BGARefreshLayout$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$cn$bingoogolapple$refreshlayout$BGARefreshLayout$RefreshStatus;

        static {
            int[] iArr = new int[RefreshStatus.values().length];
            $SwitchMap$cn$bingoogolapple$refreshlayout$BGARefreshLayout$RefreshStatus = iArr;
            try {
                iArr[RefreshStatus.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cn$bingoogolapple$refreshlayout$BGARefreshLayout$RefreshStatus[RefreshStatus.PULL_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cn$bingoogolapple$refreshlayout$BGARefreshLayout$RefreshStatus[RefreshStatus.RELEASE_REFRESH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$cn$bingoogolapple$refreshlayout$BGARefreshLayout$RefreshStatus[RefreshStatus.REFRESHING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void handleRefreshStatusChanged() {
        int i = AnonymousClass7.$SwitchMap$cn$bingoogolapple$refreshlayout$BGARefreshLayout$RefreshStatus[this.mCurrentRefreshStatus.ordinal()];
        if (i == 1) {
            this.mRefreshViewHolder.changeToIdle();
            return;
        }
        if (i == 2) {
            this.mRefreshViewHolder.changeToPullDown();
        } else if (i == 3) {
            this.mRefreshViewHolder.changeToReleaseRefresh();
        } else {
            if (i != 4) {
                return;
            }
            this.mRefreshViewHolder.changeToRefreshing();
        }
    }

    public void beginRefreshing() {
        if (this.mCurrentRefreshStatus == RefreshStatus.REFRESHING || this.mDelegate == null) {
            return;
        }
        this.mCurrentRefreshStatus = RefreshStatus.REFRESHING;
        changeRefreshHeaderViewToZero();
        handleRefreshStatusChanged();
        this.mDelegate.onBGARefreshLayoutBeginRefreshing(this);
    }

    public void endRefreshing() {
        if (this.mCurrentRefreshStatus == RefreshStatus.REFRESHING) {
            this.mCurrentRefreshStatus = RefreshStatus.IDLE;
            hiddenRefreshHeaderView();
            handleRefreshStatusChanged();
            this.mRefreshViewHolder.onEndRefreshing();
        }
    }

    private void hiddenRefreshHeaderView() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.mWholeHeaderView.getPaddingTop(), this.mMinWholeHeaderViewPaddingTop);
        valueAnimatorOfInt.setDuration(this.mRefreshViewHolder.getTopAnimDuration());
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.bingoogolapple.refreshlayout.BGARefreshLayout.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BGARefreshLayout.this.mWholeHeaderView.setPadding(0, ((Integer) valueAnimator.getAnimatedValue()).intValue(), 0, 0);
            }
        });
        valueAnimatorOfInt.start();
    }

    private void changeRefreshHeaderViewToZero() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.mWholeHeaderView.getPaddingTop(), 0);
        valueAnimatorOfInt.setDuration(this.mRefreshViewHolder.getTopAnimDuration());
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.bingoogolapple.refreshlayout.BGARefreshLayout.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BGARefreshLayout.this.mWholeHeaderView.setPadding(0, ((Integer) valueAnimator.getAnimatedValue()).intValue(), 0, 0);
            }
        });
        valueAnimatorOfInt.start();
    }

    public void beginLoadingMore() {
        BGARefreshLayoutDelegate bGARefreshLayoutDelegate;
        if (this.mIsLoadingMore || this.mLoadMoreFooterView == null || (bGARefreshLayoutDelegate = this.mDelegate) == null || !bGARefreshLayoutDelegate.onBGARefreshLayoutBeginLoadingMore(this)) {
            return;
        }
        this.mIsLoadingMore = true;
        if (this.mIsShowLoadingMoreView) {
            showLoadingMoreView();
        }
    }

    private void showLoadingMoreView() {
        this.mRefreshViewHolder.changeToLoadingMore();
        this.mLoadMoreFooterView.setVisibility(0);
        BGARefreshScrollingUtil.scrollToBottom(this.mScrollView);
        BGARefreshScrollingUtil.scrollToBottom(this.mRecyclerView);
        BGARefreshScrollingUtil.scrollToBottom(this.mAbsListView);
        BGAStickyNavLayout bGAStickyNavLayout = this.mStickyNavLayout;
        if (bGAStickyNavLayout != null) {
            bGAStickyNavLayout.scrollToBottom();
        }
    }

    public void endLoadingMore() {
        if (this.mIsLoadingMore) {
            if (this.mIsShowLoadingMoreView) {
                this.mHandler.postDelayed(this.mDelayHiddenLoadingMoreViewTask, 300L);
            } else {
                this.mIsLoadingMore = false;
            }
        }
    }

    public void setIsShowLoadingMoreView(boolean z) {
        this.mIsShowLoadingMoreView = z;
    }

    public void setPullDownRefreshEnable(boolean z) {
        this.mPullDownRefreshEnable = z;
    }

    public void setDelegate(BGARefreshLayoutDelegate bGARefreshLayoutDelegate) {
        this.mDelegate = bGARefreshLayoutDelegate;
    }

    public void setRefreshScaleDelegate(BGARefreshScaleDelegate bGARefreshScaleDelegate) {
        this.mRefreshScaleDelegate = bGARefreshScaleDelegate;
    }

    public RefreshStatus getCurrentRefreshStatus() {
        return this.mCurrentRefreshStatus;
    }

    public boolean isLoadingMore() {
        return this.mIsLoadingMore;
    }

    public static int dp2px(Context context, int i) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }
}
