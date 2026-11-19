package cn.bingoogolapple.refreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.bingoogolapple.refreshlayout.util.BGARefreshScrollingUtil;

/* JADX INFO: loaded from: classes4.dex */
public class BGAStickyNavLayout extends LinearLayout {
    private View mContentView;
    private AbsListView mDirectAbsListView;
    private View mDirectNormalView;
    private RecyclerView mDirectRecyclerView;
    private ScrollView mDirectScrollView;
    private ViewPager mDirectViewPager;
    private WebView mDirectWebView;
    private View mHeaderView;
    private boolean mIsInControl;
    private float mLastDispatchY;
    private float mLastTouchY;
    private AbsListView.OnScrollListener mLvOnScrollListener;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private View mNavView;
    private AbsListView mNestedAbsListView;
    private View mNestedContentView;
    private View mNestedNormalView;
    private RecyclerView mNestedRecyclerView;
    private ScrollView mNestedScrollView;
    private WebView mNestedWebView;
    private OverScroller mOverScroller;
    public BGARefreshLayout mRefreshLayout;
    private RecyclerView.OnScrollListener mRvOnScrollListener;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public BGAStickyNavLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsInControl = true;
        this.mRvOnScrollListener = new RecyclerView.OnScrollListener() { // from class: cn.bingoogolapple.refreshlayout.BGAStickyNavLayout.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                if ((i == 0 || i == 2) && BGAStickyNavLayout.this.mRefreshLayout != null && BGAStickyNavLayout.this.mRefreshLayout.shouldHandleRecyclerViewLoadingMore(recyclerView)) {
                    BGAStickyNavLayout.this.mRefreshLayout.beginLoadingMore();
                }
            }
        };
        this.mLvOnScrollListener = new AbsListView.OnScrollListener() { // from class: cn.bingoogolapple.refreshlayout.BGAStickyNavLayout.3
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if ((i == 0 || i == 2) && BGAStickyNavLayout.this.mRefreshLayout != null && BGAStickyNavLayout.this.mRefreshLayout.shouldHandleAbsListViewLoadingMore(absListView)) {
                    BGAStickyNavLayout.this.mRefreshLayout.beginLoadingMore();
                }
            }
        };
        init(context);
    }

    private void init(Context context) {
        setOrientation(1);
        this.mOverScroller = new OverScroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (1 == i) {
            super.setOrientation(1);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 3) {
            throw new IllegalStateException("BGAStickyNavLayout必须有且只有三个子控件");
        }
        this.mHeaderView = getChildAt(0);
        this.mNavView = getChildAt(1);
        View childAt = getChildAt(2);
        this.mContentView = childAt;
        if (childAt instanceof AbsListView) {
            AbsListView absListView = (AbsListView) childAt;
            this.mDirectAbsListView = absListView;
            absListView.setOnScrollListener(this.mLvOnScrollListener);
            return;
        }
        if (childAt instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) childAt;
            this.mDirectRecyclerView = recyclerView;
            recyclerView.addOnScrollListener(this.mRvOnScrollListener);
        } else {
            if (childAt instanceof ScrollView) {
                this.mDirectScrollView = (ScrollView) childAt;
                return;
            }
            if (childAt instanceof WebView) {
                this.mDirectWebView = (WebView) childAt;
            } else {
                if (childAt instanceof ViewPager) {
                    ViewPager viewPager = (ViewPager) childAt;
                    this.mDirectViewPager = viewPager;
                    viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: cn.bingoogolapple.refreshlayout.BGAStickyNavLayout.1
                        @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
                        public void onPageSelected(int i) {
                            BGAStickyNavLayout.this.regetNestedContentView();
                        }
                    });
                    return;
                }
                this.mDirectNormalView = childAt;
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChild(this.mContentView, i, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2) - getNavViewHeight(), 1073741824));
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mOverScroller.computeScrollOffset()) {
            scrollTo(0, this.mOverScroller.getCurrY());
            invalidate();
        }
    }

    public void fling(int i) {
        this.mOverScroller.fling(0, getScrollY(), 0, i, 0, 0, 0, getHeaderViewHeight());
        invalidate();
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        int headerViewHeight = getHeaderViewHeight();
        if (i2 > headerViewHeight) {
            i2 = headerViewHeight;
        }
        if (i2 != getScrollY()) {
            super.scrollTo(i, i2);
        }
    }

    private int getHeaderViewHeight() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderView.getLayoutParams();
        return this.mHeaderView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int getNavViewHeight() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mNavView.getLayoutParams();
        return this.mNavView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private boolean isHeaderViewCompleteInvisible() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int paddingTop = iArr[1] + getPaddingTop();
        this.mNavView.getLocationOnScreen(iArr);
        return iArr[1] - ((ViewGroup.MarginLayoutParams) this.mNavView.getLayoutParams()).topMargin == paddingTop;
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastDispatchY = y;
        } else if (action == 2) {
            float f = y - this.mLastDispatchY;
            this.mLastDispatchY = y;
            if (isContentViewToTop() && isHeaderViewCompleteInvisible()) {
                if (f >= 0.0f && !this.mIsInControl) {
                    this.mIsInControl = true;
                    return resetDispatchTouchEvent(motionEvent);
                }
                if (f <= 0.0f && this.mIsInControl) {
                    this.mIsInControl = false;
                    return resetDispatchTouchEvent(motionEvent);
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean resetDispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEvent.setAction(3);
        dispatchTouchEvent(motionEvent);
        motionEventObtain.setAction(0);
        return dispatchTouchEvent(motionEventObtain);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastTouchY = y;
        } else if (action == 2 && Math.abs(y - this.mLastTouchY) > this.mTouchSlop && (!isHeaderViewCompleteInvisible() || (isContentViewToTop() && isHeaderViewCompleteInvisible() && this.mIsInControl))) {
            this.mLastTouchY = y;
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        initVelocityTrackerIfNotExists();
        this.mVelocityTracker.addMovement(motionEvent);
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            if (!this.mOverScroller.isFinished()) {
                this.mOverScroller.abortAnimation();
            }
            this.mLastTouchY = y;
        } else if (action == 1) {
            this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int yVelocity = (int) this.mVelocityTracker.getYVelocity();
            if (Math.abs(yVelocity) > this.mMinimumVelocity) {
                fling(-yVelocity);
            }
            recycleVelocityTracker();
        } else if (action == 2) {
            float f = y - this.mLastTouchY;
            this.mLastTouchY = y;
            if (Math.abs(f) > 0.0f) {
                scrollBy(0, (int) (-f));
            }
        } else if (action == 3) {
            recycleVelocityTracker();
            if (!this.mOverScroller.isFinished()) {
                this.mOverScroller.abortAnimation();
            }
        }
        return true;
    }

    public boolean isContentViewToTop() {
        if (this.mDirectNormalView != null || BGARefreshScrollingUtil.isScrollViewOrWebViewToTop(this.mDirectWebView) || BGARefreshScrollingUtil.isScrollViewOrWebViewToTop(this.mDirectScrollView) || BGARefreshScrollingUtil.isAbsListViewToTop(this.mDirectAbsListView) || BGARefreshScrollingUtil.isRecyclerViewToTop(this.mDirectRecyclerView)) {
            return true;
        }
        if (this.mDirectViewPager != null) {
            return isViewPagerContentViewToTop();
        }
        return false;
    }

    private boolean isViewPagerContentViewToTop() {
        if (this.mNestedContentView == null) {
            regetNestedContentView();
        }
        return this.mDirectNormalView != null || BGARefreshScrollingUtil.isScrollViewOrWebViewToTop(this.mNestedWebView) || BGARefreshScrollingUtil.isScrollViewOrWebViewToTop(this.mNestedScrollView) || BGARefreshScrollingUtil.isAbsListViewToTop(this.mNestedAbsListView) || BGARefreshScrollingUtil.isRecyclerViewToTop(this.mNestedRecyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void regetNestedContentView() {
        int currentItem = this.mDirectViewPager.getCurrentItem();
        PagerAdapter adapter = this.mDirectViewPager.getAdapter();
        if ((adapter instanceof FragmentPagerAdapter) || (adapter instanceof FragmentStatePagerAdapter)) {
            View view = ((Fragment) adapter.instantiateItem((ViewGroup) this.mDirectViewPager, currentItem)).getView();
            this.mNestedContentView = view;
            this.mNestedNormalView = null;
            this.mNestedAbsListView = null;
            this.mNestedRecyclerView = null;
            this.mNestedScrollView = null;
            this.mNestedWebView = null;
            if (view instanceof AbsListView) {
                AbsListView absListView = (AbsListView) view;
                this.mNestedAbsListView = absListView;
                absListView.setOnScrollListener(this.mLvOnScrollListener);
                if (isHeaderViewCompleteInvisible()) {
                    return;
                }
                this.mNestedAbsListView.setSelection(0);
                return;
            }
            if (view instanceof RecyclerView) {
                RecyclerView recyclerView = (RecyclerView) view;
                this.mNestedRecyclerView = recyclerView;
                recyclerView.removeOnScrollListener(this.mRvOnScrollListener);
                this.mNestedRecyclerView.addOnScrollListener(this.mRvOnScrollListener);
                if (isHeaderViewCompleteInvisible()) {
                    return;
                }
                this.mNestedRecyclerView.scrollToPosition(0);
                return;
            }
            if (view instanceof ScrollView) {
                this.mNestedScrollView = (ScrollView) view;
                if (isHeaderViewCompleteInvisible()) {
                    return;
                }
                ScrollView scrollView = this.mNestedScrollView;
                scrollView.scrollTo(scrollView.getScrollX(), 0);
                return;
            }
            if (view instanceof WebView) {
                this.mNestedWebView = (WebView) view;
                if (isHeaderViewCompleteInvisible()) {
                    return;
                }
                WebView webView = this.mNestedWebView;
                webView.scrollTo(webView.getScrollX(), 0);
                return;
            }
            this.mNestedNormalView = view;
            return;
        }
        throw new IllegalStateException("BGAStickyNavLayout的第三个子控件为ViewPager时，其adapter必须是FragmentPagerAdapter或者FragmentStatePagerAdapter");
    }

    public void setRefreshLayout(BGARefreshLayout bGARefreshLayout) {
        this.mRefreshLayout = bGARefreshLayout;
    }

    public boolean shouldHandleLoadingMore() {
        if (this.mRefreshLayout == null) {
            return false;
        }
        if (this.mDirectNormalView != null || BGARefreshScrollingUtil.isWebViewToBottom(this.mDirectWebView) || BGARefreshScrollingUtil.isScrollViewToBottom(this.mDirectScrollView)) {
            return true;
        }
        AbsListView absListView = this.mDirectAbsListView;
        if (absListView != null) {
            return this.mRefreshLayout.shouldHandleAbsListViewLoadingMore(absListView);
        }
        RecyclerView recyclerView = this.mDirectRecyclerView;
        if (recyclerView != null) {
            return this.mRefreshLayout.shouldHandleRecyclerViewLoadingMore(recyclerView);
        }
        if (this.mDirectViewPager != null) {
            if (this.mNestedContentView == null) {
                regetNestedContentView();
            }
            if (this.mNestedNormalView != null || BGARefreshScrollingUtil.isWebViewToBottom(this.mNestedWebView) || BGARefreshScrollingUtil.isScrollViewToBottom(this.mNestedScrollView)) {
                return true;
            }
            AbsListView absListView2 = this.mNestedAbsListView;
            if (absListView2 != null) {
                return this.mRefreshLayout.shouldHandleAbsListViewLoadingMore(absListView2);
            }
            RecyclerView recyclerView2 = this.mNestedRecyclerView;
            if (recyclerView2 != null) {
                return this.mRefreshLayout.shouldHandleRecyclerViewLoadingMore(recyclerView2);
            }
        }
        return false;
    }

    public void scrollToBottom() {
        BGARefreshScrollingUtil.scrollToBottom(this.mDirectScrollView);
        BGARefreshScrollingUtil.scrollToBottom(this.mDirectRecyclerView);
        BGARefreshScrollingUtil.scrollToBottom(this.mDirectAbsListView);
        if (this.mDirectViewPager != null) {
            if (this.mNestedContentView == null) {
                regetNestedContentView();
            }
            BGARefreshScrollingUtil.scrollToBottom(this.mNestedScrollView);
            BGARefreshScrollingUtil.scrollToBottom(this.mNestedRecyclerView);
            BGARefreshScrollingUtil.scrollToBottom(this.mNestedAbsListView);
        }
    }
}
