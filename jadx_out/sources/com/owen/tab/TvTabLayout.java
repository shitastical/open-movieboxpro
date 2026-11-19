package com.owen.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.util.Pools;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes5.dex */
public class TvTabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;
    private static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    private static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_BOTTOM = 2;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 3;
    public static final int GRAVITY_TOP = 0;
    private static final int INVALID_WIDTH = -1;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int MOTION_NON_ADJACENT_OFFSET = 24;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private static final Pools.Pool<Tab> sTabPool = new Pools.SynchronizedPool(16);
    private AdapterChangeListener mAdapterChangeListener;
    private int mContentInsetBottom;
    private int mContentInsetStart;
    private OnTabSelectedListener mCurrentVpSelectedListener;
    private int mIndicatorGravity;
    private TabLayoutOnPageChangeListener mPageChangeListener;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private final int mRequestedTabMaxWidth;
    private final int mRequestedTabMinWidth;
    private ValueAnimatorCompat mScrollAnimator;
    private int mScrollMode;
    private final int mScrollableTabMinWidth;
    private OnTabSelectedListener mSelectedListener;
    private final ArrayList<OnTabSelectedListener> mSelectedListeners;
    private Tab mSelectedTab;
    private boolean mSetupViewPagerImplicitly;
    private final int mTabBackgroundResId;
    private int mTabGravity;
    private int mTabMaxWidth;
    private int mTabPaddingBottom;
    private int mTabPaddingEnd;
    private int mTabPaddingStart;
    private int mTabPaddingTop;
    protected final SlidingTabStrip mTabStrip;
    private ColorStateList mTabTextColors;
    private float mTabTextMultiLineSize;
    private boolean mTabTextSelectedCentered;
    private float mTabTextSelectedScaleValue;
    private float mTabTextSize;
    private final Pools.Pool<TabView> mTabViewPool;
    private final ArrayList<Tab> mTabs;
    private ViewPager mViewPager;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener {
        void onTabReselected(Tab tab);

        void onTabSelected(Tab tab);

        void onTabUnselected(Tab tab);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    protected void onTabReselected(Tab tab) {
    }

    public TvTabLayout(Context context) {
        this(context, null);
    }

    public TvTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TvTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTabs = new ArrayList<>();
        this.mTabMaxWidth = LockFreeTaskQueueCore.MAX_CAPACITY_MASK;
        this.mSelectedListeners = new ArrayList<>();
        this.mTabViewPool = new Pools.SimplePool(12);
        setHorizontalScrollBarEnabled(false);
        setDescendantFocusability(393216);
        setWillNotDraw(true);
        setClickable(false);
        setFocusable(true);
        setFocusableInTouchMode(true);
        SlidingTabStrip slidingTabStrip = new SlidingTabStrip(context);
        this.mTabStrip = slidingTabStrip;
        super.addView(slidingTabStrip, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TvTabLayout, i, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.TvTabLayout_tv_tabIndicatorResId);
        if (drawable != null) {
            drawable.setCallback(this);
            slidingTabStrip.setIndicatorDrawable(drawable);
        }
        this.mIndicatorGravity = typedArrayObtainStyledAttributes.getInt(R.styleable.TvTabLayout_tv_tabIndicatorGravity, 2);
        slidingTabStrip.setCircleDotRadius(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabCircleDotRadius, 0));
        slidingTabStrip.setCircleDotColor(typedArrayObtainStyledAttributes.getColor(R.styleable.TvTabLayout_tv_tabCircleDotColor, 0));
        slidingTabStrip.setIndicatorWidth(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabIndicatorWidth, 0));
        slidingTabStrip.setIndicatorHeight(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabIndicatorHeight, 0));
        slidingTabStrip.setIndicatorBackgroundColor(typedArrayObtainStyledAttributes.getColor(R.styleable.TvTabLayout_tv_tabIndicatorBackgroundColor, 0));
        slidingTabStrip.setIndicatorBackgroundHeight(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabIndicatorBackgroundHeight, 0));
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabPadding, 0);
        this.mTabPaddingBottom = dimensionPixelOffset;
        this.mTabPaddingEnd = dimensionPixelOffset;
        this.mTabPaddingTop = dimensionPixelOffset;
        this.mTabPaddingStart = dimensionPixelOffset;
        this.mTabPaddingStart = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabPaddingStart, this.mTabPaddingStart);
        this.mTabPaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabPaddingTop, this.mTabPaddingTop);
        this.mTabPaddingEnd = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabPaddingEnd, this.mTabPaddingEnd);
        this.mTabPaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabPaddingBottom, this.mTabPaddingBottom);
        this.mTabTextSize = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabTexSize, getResources().getDimensionPixelOffset(R.dimen.tablayout_tab_text_size));
        this.mTabTextColors = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.TvTabLayout_tv_tabTextColor);
        this.mRequestedTabMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabMinWidth, -1);
        this.mRequestedTabMaxWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabMaxWidth, -1);
        this.mTabBackgroundResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TvTabLayout_tv_tabBackground, 0);
        this.mContentInsetStart = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabContentStart, 0);
        this.mContentInsetBottom = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.TvTabLayout_tv_tabContentBottom, 0);
        this.mScrollMode = typedArrayObtainStyledAttributes.getInt(R.styleable.TvTabLayout_tv_tabScrollMode, 1);
        this.mTabGravity = typedArrayObtainStyledAttributes.getInt(R.styleable.TvTabLayout_tv_tabGravity, 1);
        this.mTabTextSelectedCentered = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TvTabLayout_tv_tabTextSelectedCentered, false);
        this.mTabTextSelectedScaleValue = typedArrayObtainStyledAttributes.getFloat(R.styleable.TvTabLayout_tv_tabTextSelectedScale, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        Resources resources = getResources();
        this.mTabTextMultiLineSize = resources.getDimensionPixelOffset(R.dimen.tablayout_tab_text_size_2line);
        this.mScrollableTabMinWidth = resources.getDimensionPixelOffset(R.dimen.tablayout_tab_scrollable_min_width);
        applyModeAndGravity();
    }

    public void setTabTextSelectedCentered(boolean z) {
        this.mTabTextSelectedCentered = z;
    }

    public void setTabTextSelectedScaleValue(float f) {
        this.mTabTextSelectedScaleValue = f;
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        View view;
        super.onFocusChanged(z, i, rect);
        Tab tab = this.mSelectedTab;
        if (tab != null && (view = tab.getView()) != null) {
            view.setActivated(!z);
            view.setSelected(z);
        }
        this.mTabStrip.invalidate();
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 21) {
                return selectTab(getSelectedTabPosition() - 1);
            }
            if (keyEvent.getKeyCode() == 22) {
                return selectTab(getSelectedTabPosition() + 1);
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void setScrollPosition(int i, float f, boolean z) {
        setScrollPosition(i, f, z, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollPosition(int i, float f, boolean z, boolean z2) {
        int iRound = Math.round(i + f);
        if (iRound < 0 || iRound >= this.mTabStrip.getChildCount()) {
            return;
        }
        if (z2) {
            this.mTabStrip.setIndicatorPositionFromTabPosition(i, f);
        }
        ValueAnimatorCompat valueAnimatorCompat = this.mScrollAnimator;
        if (valueAnimatorCompat != null && valueAnimatorCompat.isRunning()) {
            this.mScrollAnimator.cancel();
        }
        scrollTo(calculateScrollXForTab(i, f), 0);
        if (z) {
            setSelectedTabView(iRound);
        }
    }

    private float getScrollPosition() {
        return this.mTabStrip.getIndicatorPosition();
    }

    public void addTab(Tab tab) {
        addTab(tab, this.mTabs.isEmpty());
    }

    public void addTab(Tab tab, int i) {
        addTab(tab, i, this.mTabs.isEmpty());
    }

    public void addTab(Tab tab, boolean z) {
        addTab(tab, this.mTabs.size(), z);
    }

    public void addTab(final Tab tab, int i, boolean z) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        configureTab(tab, i);
        addTabView(tab);
        if (z) {
            tab.getView().post(new Runnable() { // from class: com.owen.tab.TvTabLayout.1
                @Override // java.lang.Runnable
                public void run() {
                    tab.select();
                }
            });
        }
    }

    private void addTabFromItemView(TabItem tabItem) {
        Tab tabNewTab = newTab();
        if (tabItem.mText != null) {
            tabNewTab.setText(tabItem.mText);
        }
        if (tabItem.mIcon != null) {
            tabNewTab.setIcon(tabItem.mIcon);
        }
        if (tabItem.mCustomLayout != 0) {
            tabNewTab.setCustomView(tabItem.mCustomLayout);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            tabNewTab.setContentDescription(tabItem.getContentDescription());
        }
        addTab(tabNewTab);
    }

    @Deprecated
    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        OnTabSelectedListener onTabSelectedListener2 = this.mSelectedListener;
        if (onTabSelectedListener2 != null) {
            removeOnTabSelectedListener(onTabSelectedListener2);
        }
        this.mSelectedListener = onTabSelectedListener;
        if (onTabSelectedListener != null) {
            addOnTabSelectedListener(onTabSelectedListener);
        }
    }

    public void addOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        if (this.mSelectedListeners.contains(onTabSelectedListener)) {
            return;
        }
        this.mSelectedListeners.add(onTabSelectedListener);
    }

    public void removeOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.mSelectedListeners.remove(onTabSelectedListener);
    }

    public Tab newTab() {
        Tab tabAcquire = sTabPool.acquire();
        if (tabAcquire == null) {
            tabAcquire = new Tab();
        }
        tabAcquire.mParent = this;
        tabAcquire.mView = createTabView(tabAcquire);
        return tabAcquire;
    }

    public int getTabCount() {
        return this.mTabs.size();
    }

    public Tab getTabAt(int i) {
        if (i < 0 || i >= this.mTabs.size()) {
            return null;
        }
        return this.mTabs.get(i);
    }

    public int getSelectedTabPosition() {
        Tab tab = this.mSelectedTab;
        if (tab != null) {
            return tab.getPosition();
        }
        return -1;
    }

    public void removeTab(Tab tab) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i) {
        Tab tab = this.mSelectedTab;
        int position = tab != null ? tab.getPosition() : 0;
        removeTabViewAt(i);
        Tab tabRemove = this.mTabs.remove(i);
        if (tabRemove != null) {
            tabRemove.reset();
            sTabPool.release(tabRemove);
        }
        int size = this.mTabs.size();
        for (int i2 = i; i2 < size; i2++) {
            this.mTabs.get(i2).setPosition(i2);
        }
        if (position == i) {
            selectTab(this.mTabs.isEmpty() ? null : this.mTabs.get(Math.max(0, i - 1)));
        }
    }

    public void removeAllTabs() {
        for (int childCount = this.mTabStrip.getChildCount() - 1; childCount >= 0; childCount--) {
            removeTabViewAt(childCount);
        }
        Iterator<Tab> it = this.mTabs.iterator();
        while (it.hasNext()) {
            Tab next = it.next();
            it.remove();
            next.reset();
            sTabPool.release(next);
        }
        this.mSelectedTab = null;
    }

    public void setTabScrollMode(int i) {
        if (i != this.mScrollMode) {
            this.mScrollMode = i;
            applyModeAndGravity();
        }
    }

    public int getTabScrollMode() {
        return this.mScrollMode;
    }

    public void setTabGravity(int i) {
        if (this.mTabGravity != i) {
            this.mTabGravity = i;
            applyModeAndGravity();
        }
    }

    public int getTabGravity() {
        return this.mTabGravity;
    }

    public void setTabIndicatorGravity(int i) {
        if (this.mIndicatorGravity != i) {
            this.mIndicatorGravity = i;
            SlidingTabStrip slidingTabStrip = this.mTabStrip;
            if (slidingTabStrip != null) {
                slidingTabStrip.invalidate();
            }
        }
    }

    public int getTabIndicatorGravity() {
        return this.mIndicatorGravity;
    }

    public void setTabTextColor(int i) {
        this.mTabTextColors = ColorStateList.valueOf(i);
        updateAllTabs();
    }

    public void setTabTextColor(ColorStateList colorStateList) {
        if (this.mTabTextColors != colorStateList) {
            this.mTabTextColors = colorStateList;
            updateAllTabs();
        }
    }

    public ColorStateList getTabTextColors() {
        return this.mTabTextColors;
    }

    public void setTabTextColors(int i, int i2, int i3) {
        setTabTextColor(createColorStateList(i, i2, i3));
    }

    public void setTabIndicatorHeight(int i) {
        this.mTabStrip.setIndicatorHeight(i);
    }

    public int getTabIndicatorHeight() {
        return this.mTabStrip.mIndicatorHeight;
    }

    public void setTabIndicatorWidth(int i) {
        this.mTabStrip.setIndicatorWidth(i);
    }

    public int getTabIndicatorWidth() {
        return this.mTabStrip.mIndicatorWidth;
    }

    public void setIndicatorBackgroundColor(int i) {
        this.mTabStrip.setIndicatorBackgroundColor(i);
    }

    public void setIndicatorBackgroundHeight(int i) {
        this.mTabStrip.setIndicatorBackgroundHeight(i);
    }

    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    public void setupWithViewPager(ViewPager viewPager, boolean z) {
        setupWithViewPager(viewPager, z, true);
    }

    private void setupWithViewPager(ViewPager viewPager, boolean z, boolean z2) {
        ViewPager viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.mPageChangeListener;
            if (tabLayoutOnPageChangeListener != null) {
                viewPager2.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
            }
            AdapterChangeListener adapterChangeListener = this.mAdapterChangeListener;
            if (adapterChangeListener != null) {
                this.mViewPager.removeOnAdapterChangeListener(adapterChangeListener);
            }
        }
        OnTabSelectedListener onTabSelectedListener = this.mCurrentVpSelectedListener;
        if (onTabSelectedListener != null) {
            removeOnTabSelectedListener(onTabSelectedListener);
            this.mCurrentVpSelectedListener = null;
        }
        if (viewPager != null) {
            this.mViewPager = viewPager;
            if (this.mPageChangeListener == null) {
                this.mPageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            this.mPageChangeListener.reset();
            viewPager.addOnPageChangeListener(this.mPageChangeListener);
            ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            this.mCurrentVpSelectedListener = viewPagerOnTabSelectedListener;
            addOnTabSelectedListener(viewPagerOnTabSelectedListener);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, z);
            }
            if (this.mAdapterChangeListener == null) {
                this.mAdapterChangeListener = new AdapterChangeListener();
            }
            this.mAdapterChangeListener.setAutoRefresh(z);
            viewPager.addOnAdapterChangeListener(this.mAdapterChangeListener);
            setScrollPosition(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.mViewPager = null;
            setPagerAdapter(null, false);
        }
        this.mSetupViewPagerImplicitly = z2;
    }

    @Deprecated
    public void setTabsFromPagerAdapter(PagerAdapter pagerAdapter) {
        setPagerAdapter(pagerAdapter, false);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mViewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                setupWithViewPager((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mSetupViewPagerImplicitly) {
            setupWithViewPager(null);
            this.mSetupViewPagerImplicitly = false;
        }
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.mTabStrip.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPagerAdapter(PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.mPagerAdapter;
        if (pagerAdapter2 != null && (dataSetObserver = this.mPagerAdapterObserver) != null) {
            pagerAdapter2.unregisterDataSetObserver(dataSetObserver);
        }
        this.mPagerAdapter = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.mPagerAdapterObserver == null) {
                this.mPagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter.registerDataSetObserver(this.mPagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void populateFromPagerAdapter() {
        int currentItem;
        removeAllTabs();
        PagerAdapter pagerAdapter = this.mPagerAdapter;
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            for (int i = 0; i < count; i++) {
                addTab(newTab().setText(this.mPagerAdapter.getPageTitle(i)), false);
            }
            ViewPager viewPager = this.mViewPager;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            final Tab tabAt = getTabAt(currentItem);
            if (tabAt != null && tabAt.getView() != null) {
                tabAt.getView().post(new Runnable() { // from class: com.owen.tab.TvTabLayout.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TvTabLayout.this.selectTab(tabAt);
                    }
                });
            } else {
                selectTab(tabAt);
            }
        }
    }

    private void updateAllTabs() {
        int size = this.mTabs.size();
        for (int i = 0; i < size; i++) {
            this.mTabs.get(i).updateView();
        }
    }

    private TabView createTabView(Tab tab) {
        Pools.Pool<TabView> pool = this.mTabViewPool;
        TabView tabViewAcquire = pool != null ? pool.acquire() : null;
        if (tabViewAcquire == null) {
            tabViewAcquire = new TabView(getContext());
        }
        tabViewAcquire.setTab(tab);
        tabViewAcquire.setFocusable(true);
        tabViewAcquire.setMinimumWidth(getTabMinWidth());
        return tabViewAcquire;
    }

    private void configureTab(Tab tab, int i) {
        tab.setPosition(i);
        this.mTabs.add(i, tab);
        int size = this.mTabs.size();
        while (true) {
            i++;
            if (i >= size) {
                return;
            } else {
                this.mTabs.get(i).setPosition(i);
            }
        }
    }

    private void addTabView(Tab tab) {
        this.mTabStrip.addView(tab.mView, tab.getPosition(), createLayoutParamsForTabs());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        addViewInternal(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i) {
        addViewInternal(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    private void addViewInternal(View view) {
        if (view instanceof TabItem) {
            addTabFromItemView((TabItem) view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public int getFontHeight(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        updateTabViewLayoutParams(layoutParams);
        return layoutParams;
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams layoutParams) {
        if (this.mScrollMode == 1 && this.mTabGravity == 3) {
            layoutParams.height = -1;
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
        int i = this.mTabGravity;
        if (i == 0) {
            layoutParams.gravity = 49;
        } else if (i == 1) {
            layoutParams.gravity = 17;
        } else {
            if (i != 2) {
                return;
            }
            layoutParams.gravity = 81;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int dpToPx(int i) {
        return Math.round(getResources().getDisplayMetrics().density * i);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultHeight = getDefaultHeight() + getPaddingTop() + getPaddingBottom() + this.mContentInsetBottom;
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(defaultHeight, View.MeasureSpec.getSize(i2)), 1073741824);
        } else if (mode == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(defaultHeight, 1073741824);
        }
        int size = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i) != 0) {
            int iDpToPx = this.mRequestedTabMaxWidth;
            if (iDpToPx <= 0) {
                iDpToPx = size - dpToPx(56);
            }
            this.mTabMaxWidth = iDpToPx;
        }
        super.onMeasure(i, i2);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            int i3 = this.mScrollMode;
            if (i3 == 0) {
                if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                    return;
                }
            } else if (i3 != 1 || childAt.getMeasuredWidth() == getMeasuredWidth()) {
                return;
            }
            childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
        }
    }

    private void removeTabViewAt(int i) {
        TabView tabView = (TabView) this.mTabStrip.getChildAt(i);
        this.mTabStrip.removeViewAt(i);
        if (tabView != null) {
            tabView.reset();
            this.mTabViewPool.release(tabView);
        }
        requestLayout();
    }

    private void animateToTab(int i) {
        if (i == -1) {
            return;
        }
        if (getWindowToken() == null || !ViewCompat.isLaidOut(this) || this.mTabStrip.childrenNeedLayout()) {
            setScrollPosition(i, 0.0f, true);
            return;
        }
        if (this.mViewPager == null) {
            int scrollX = getScrollX();
            int iCalculateScrollXForTab = calculateScrollXForTab(i, 0.0f);
            if (scrollX != iCalculateScrollXForTab) {
                if (this.mScrollAnimator == null) {
                    ValueAnimatorCompat valueAnimatorCompatCreateAnimator = ViewUtils.createAnimator();
                    this.mScrollAnimator = valueAnimatorCompatCreateAnimator;
                    valueAnimatorCompatCreateAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                    this.mScrollAnimator.setDuration(300L);
                    this.mScrollAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener() { // from class: com.owen.tab.TvTabLayout.3
                        @Override // com.owen.tab.ValueAnimatorCompat.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat) {
                            TvTabLayout.this.scrollTo(valueAnimatorCompat.getAnimatedIntValue(), 0);
                        }
                    });
                }
                this.mScrollAnimator.setIntValues(scrollX, iCalculateScrollXForTab);
                this.mScrollAnimator.start();
            }
            this.mTabStrip.animateIndicatorToPosition(i, 300);
        }
    }

    private void setSelectedTabView(int i) {
        int childCount = this.mTabStrip.getChildCount();
        if (i < childCount) {
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.mTabStrip.getChildAt(i2);
                boolean z = true;
                childAt.setSelected(i2 == i && hasFocus());
                if (i2 != i || hasFocus()) {
                    z = false;
                }
                childAt.setActivated(z);
                i2++;
            }
        }
    }

    public boolean selectTab(int i) {
        return selectTab(getTabAt(i));
    }

    boolean selectTab(Tab tab) {
        return selectTab(tab, true);
    }

    boolean selectTab(Tab tab, boolean z) {
        if (tab == null) {
            return false;
        }
        Tab tab2 = this.mSelectedTab;
        if (tab2 == tab) {
            dispatchTabReselected(tab);
            animateToTab(tab.getPosition());
        } else {
            int position = tab.getPosition();
            if (z) {
                if ((tab2 == null || tab2.getPosition() == -1) && position != -1) {
                    setScrollPosition(position, 0.0f, true);
                } else {
                    animateToTab(position);
                }
            }
            if (position != -1) {
                setSelectedTabView(position);
            }
            if (tab2 != null) {
                dispatchTabUnselected(tab2);
            }
            this.mSelectedTab = tab;
            dispatchTabSelected(tab);
        }
        return true;
    }

    public Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    private void dispatchTabSelected(Tab tab) {
        onTabSelected(tab);
        for (int size = this.mSelectedListeners.size() - 1; size >= 0; size--) {
            this.mSelectedListeners.get(size).onTabSelected(tab);
        }
    }

    private void dispatchTabUnselected(Tab tab) {
        onTabUnselected(tab);
        for (int size = this.mSelectedListeners.size() - 1; size >= 0; size--) {
            this.mSelectedListeners.get(size).onTabUnselected(tab);
        }
    }

    private void dispatchTabReselected(Tab tab) {
        onTabReselected(tab);
        for (int size = this.mSelectedListeners.size() - 1; size >= 0; size--) {
            this.mSelectedListeners.get(size).onTabReselected(tab);
        }
    }

    protected void onTabSelected(Tab tab) {
        ViewPropertyAnimator viewPropertyAnimatorAnimate = tab.getView().animate();
        if (this.mTabTextSelectedCentered) {
            viewPropertyAnimatorAnimate.scaleX(this.mTabTextSelectedScaleValue).scaleY(this.mTabTextSelectedScaleValue).translationY((getHeight() - tab.getView().getHeight()) / 2).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(700L).start();
            return;
        }
        float f = this.mTabTextSelectedScaleValue;
        if (f > 0.0f) {
            viewPropertyAnimatorAnimate.scaleX(f).scaleY(this.mTabTextSelectedScaleValue).setDuration(500L).start();
        }
    }

    protected void onTabUnselected(Tab tab) {
        ViewPropertyAnimator viewPropertyAnimatorAnimate = tab.getView().animate();
        if (this.mTabTextSelectedCentered) {
            viewPropertyAnimatorAnimate.scaleX(1.0f).scaleY(1.0f).translationY(0.0f).setInterpolator(new DecelerateInterpolator()).setDuration(500L).start();
        } else if (this.mTabTextSelectedScaleValue > 0.0f) {
            viewPropertyAnimatorAnimate.scaleX(1.0f).scaleY(1.0f).setDuration(500L).start();
        }
    }

    private int calculateScrollXForTab(int i, float f) {
        if (this.mScrollMode != 0) {
            return 0;
        }
        View childAt = this.mTabStrip.getChildAt(i);
        int i2 = i + 1;
        View childAt2 = i2 < this.mTabStrip.getChildCount() ? this.mTabStrip.getChildAt(i2) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i3 = (int) ((width + width2) * 0.5f * f);
        return ViewCompat.getLayoutDirection(this) == 0 ? left + i3 : left - i3;
    }

    private void applyModeAndGravity() {
        ViewCompat.setPaddingRelative(this.mTabStrip, this.mScrollMode == 0 ? Math.max(0, this.mContentInsetStart - this.mTabPaddingStart) : 0, 0, 0, this.mContentInsetBottom);
        int i = this.mScrollMode;
        if (i == 0) {
            this.mTabStrip.setGravity(8388611);
        } else if (i == 1) {
            this.mTabStrip.setGravity(1);
        }
        updateTabViews(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTabViews(boolean z) {
        for (int i = 0; i < this.mTabStrip.getChildCount(); i++) {
            View childAt = this.mTabStrip.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    public static final class Tab {
        public static final int INVALID_POSITION = -1;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private TvTabLayout mParent;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;
        private TabView mView;

        private Tab() {
            this.mPosition = -1;
        }

        public Object getTag() {
            return this.mTag;
        }

        public Tab setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public TabView getTabView() {
            return this.mView;
        }

        public View getView() {
            TabView tabView = this.mView;
            return tabView != null ? tabView : this.mCustomView;
        }

        public View getCustomView() {
            return this.mCustomView;
        }

        public Tab setCustomView(View view) {
            this.mCustomView = view;
            updateView();
            return this;
        }

        public Tab setCustomView(int i) {
            return setCustomView(LayoutInflater.from(this.mView.getContext()).inflate(i, (ViewGroup) this.mView, false));
        }

        public Drawable getIcon() {
            return this.mIcon;
        }

        public int getPosition() {
            return this.mPosition;
        }

        void setPosition(int i) {
            this.mPosition = i;
        }

        public CharSequence getText() {
            return this.mText;
        }

        public Tab setIcon(Drawable drawable) {
            this.mIcon = drawable;
            updateView();
            return this;
        }

        public Tab setIcon(int i) {
            TvTabLayout tvTabLayout = this.mParent;
            if (tvTabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return setIcon(tvTabLayout.getResources().getDrawable(i));
        }

        public Tab setText(CharSequence charSequence) {
            this.mText = charSequence;
            updateView();
            return this;
        }

        public Tab setText(int i) {
            TvTabLayout tvTabLayout = this.mParent;
            if (tvTabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return setText(tvTabLayout.getResources().getText(i));
        }

        public void select() {
            TvTabLayout tvTabLayout = this.mParent;
            if (tvTabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tvTabLayout.selectTab(this);
        }

        public boolean isSelected() {
            TvTabLayout tvTabLayout = this.mParent;
            if (tvTabLayout != null) {
                return tvTabLayout.getSelectedTabPosition() == this.mPosition;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public Tab setContentDescription(int i) {
            TvTabLayout tvTabLayout = this.mParent;
            if (tvTabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return setContentDescription(tvTabLayout.getResources().getText(i));
        }

        public Tab setContentDescription(CharSequence charSequence) {
            this.mContentDesc = charSequence;
            updateView();
            return this;
        }

        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateView() {
            TabView tabView = this.mView;
            if (tabView != null) {
                tabView.update();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mParent = null;
            this.mView = null;
            this.mTag = null;
            this.mIcon = null;
            this.mText = null;
            this.mContentDesc = null;
            this.mPosition = -1;
            this.mCustomView = null;
        }
    }

    public class TabView extends LinearLayout implements View.OnLongClickListener {
        private ImageView mCustomIconView;
        private TextView mCustomTextView;
        private View mCustomView;
        private int mDefaultMaxLines;
        private ImageView mIconView;
        private Tab mTab;
        private TextView mTextView;

        public TabView(Context context) {
            super(context);
            this.mDefaultMaxLines = 2;
            if (TvTabLayout.this.mTabBackgroundResId != 0) {
                setBackgroundDrawable(getResources().getDrawable(TvTabLayout.this.mTabBackgroundResId));
            }
            ViewCompat.setPaddingRelative(this, TvTabLayout.this.mTabPaddingStart, TvTabLayout.this.mTabPaddingTop, TvTabLayout.this.mTabPaddingEnd, TvTabLayout.this.mTabPaddingBottom);
            setGravity(17);
            setOrientation(1);
            setClickable(true);
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            Tab tab = this.mTab;
            if (tab == null) {
                return zPerformClick;
            }
            tab.select();
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            isSelected();
            super.setSelected(z);
            TextView textView = this.mTextView;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.mIconView;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.mCustomView;
            if (view != null) {
                view.setSelected(z);
            }
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int tabMaxWidth = TvTabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(TvTabLayout.this.mTabMaxWidth, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.mTextView != null) {
                getResources();
                float f = TvTabLayout.this.mTabTextSize;
                int i3 = this.mDefaultMaxLines;
                ImageView imageView = this.mIconView;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.mTextView;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = TvTabLayout.this.mTabTextMultiLineSize;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.mTextView.getTextSize();
                int lineCount = this.mTextView.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.mTextView);
                if (f != textSize || (maxLines >= 0 && i3 != maxLines)) {
                    if (TvTabLayout.this.mScrollMode != 1 || f <= textSize || lineCount != 1 || ((layout = this.mTextView.getLayout()) != null && approximateLineWidth(layout, 0, f) <= (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        this.mTextView.setTextSize(0, f);
                        this.mTextView.setMaxLines(i3);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTab(Tab tab) {
            if (tab != this.mTab) {
                this.mTab = tab;
                update();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            setTab(null);
            setSelected(false);
        }

        final void update() {
            Tab tab = this.mTab;
            View customView = tab != null ? tab.getCustomView() : null;
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(customView);
                    }
                    addView(customView);
                }
                this.mCustomView = customView;
                TextView textView = this.mTextView;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.mIconView;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.mIconView.setImageDrawable(null);
                }
                TextView textView2 = (TextView) customView.findViewById(android.R.id.text1);
                this.mCustomTextView = textView2;
                if (textView2 != null) {
                    this.mDefaultMaxLines = TextViewCompat.getMaxLines(textView2);
                }
                this.mCustomIconView = (ImageView) customView.findViewById(android.R.id.icon);
            } else {
                View view = this.mCustomView;
                if (view != null) {
                    removeView(view);
                    this.mCustomView = null;
                }
                this.mCustomTextView = null;
                this.mCustomIconView = null;
            }
            boolean z = false;
            if (this.mCustomView == null) {
                if (this.mIconView == null) {
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.tab_layout_tab_icon, (ViewGroup) this, false);
                    addView(imageView2, 0);
                    this.mIconView = imageView2;
                }
                if (this.mTextView == null) {
                    TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tab_layout_tab_text, (ViewGroup) this, false);
                    addView(textView3);
                    this.mTextView = textView3;
                    this.mDefaultMaxLines = TextViewCompat.getMaxLines(textView3);
                }
                if (TvTabLayout.this.mTabTextColors != null) {
                    this.mTextView.setTextColor(TvTabLayout.this.mTabTextColors);
                }
                updateTextAndIcon(this.mTextView, this.mIconView);
            } else {
                TextView textView4 = this.mCustomTextView;
                if (textView4 != null || this.mCustomIconView != null) {
                    updateTextAndIcon(textView4, this.mCustomIconView);
                }
            }
            if (tab != null && tab.isSelected()) {
                z = true;
            }
            setSelected(z);
        }

        private void updateTextAndIcon(TextView textView, ImageView imageView) {
            Tab tab = this.mTab;
            Drawable icon = tab != null ? tab.getIcon() : null;
            Tab tab2 = this.mTab;
            CharSequence text = tab2 != null ? tab2.getText() : null;
            Tab tab3 = this.mTab;
            CharSequence contentDescription = tab3 != null ? tab3.getContentDescription() : null;
            if (imageView != null) {
                if (icon != null) {
                    imageView.setImageDrawable(icon);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
                imageView.setContentDescription(contentDescription);
            }
            boolean zIsEmpty = TextUtils.isEmpty(text);
            if (textView != null) {
                if (!zIsEmpty) {
                    textView.setText(text);
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
                textView.setContentDescription(contentDescription);
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int iDpToPx = (zIsEmpty || imageView.getVisibility() != 0) ? 0 : TvTabLayout.this.dpToPx(8);
                if (iDpToPx != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iDpToPx;
                    imageView.requestLayout();
                }
            }
            if (zIsEmpty && !TextUtils.isEmpty(contentDescription)) {
                setOnLongClickListener(this);
            } else {
                setOnLongClickListener(null);
                setLongClickable(false);
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int[] iArr = new int[2];
            Rect rect = new Rect();
            getLocationOnScreen(iArr);
            getWindowVisibleDisplayFrame(rect);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = iArr[1] + (height / 2);
            int i2 = iArr[0] + (width / 2);
            if (ViewCompat.getLayoutDirection(view) == 0) {
                i2 = context.getResources().getDisplayMetrics().widthPixels - i2;
            }
            Toast toastMakeText = Toast.makeText(context, this.mTab.getContentDescription(), 0);
            if (i < rect.height()) {
                toastMakeText.setGravity(8388661, i2, (iArr[1] + height) - rect.top);
            } else {
                toastMakeText.setGravity(81, 0, height);
            }
            toastMakeText.show();
            return true;
        }

        public Tab getTab() {
            return this.mTab;
        }

        private float approximateLineWidth(Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }
    }

    protected class SlidingTabStrip extends LinearLayout {
        private Paint mCircleDotPaint;
        private int mCircleDotRadius;
        private ValueAnimatorCompat mIndicatorAnimator;
        private int mIndicatorBackgroundBottom;
        private int mIndicatorBackgroundHeight;
        private int mIndicatorBackgroundLeft;
        private Paint mIndicatorBackgroundPaint;
        private int mIndicatorBackgroundRight;
        private int mIndicatorBackgroundTop;
        private int mIndicatorBottom;
        private Drawable mIndicatorDrawable;
        private int mIndicatorHeight;
        private int mIndicatorLeft;
        private int mIndicatorRight;
        private int mIndicatorTop;
        private int mIndicatorWidth;
        private int mSelectedPosition;
        private float mSelectionOffset;

        SlidingTabStrip(Context context) {
            super(context);
            this.mSelectedPosition = -1;
            this.mIndicatorLeft = -1;
            this.mIndicatorRight = -1;
            this.mIndicatorTop = -1;
            this.mIndicatorBottom = -1;
            setWillNotDraw(false);
            Paint paint = new Paint();
            this.mIndicatorBackgroundPaint = paint;
            paint.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.mCircleDotPaint = paint2;
            paint2.setAntiAlias(true);
        }

        void setIndicatorBackgroundColor(int i) {
            if (this.mIndicatorBackgroundPaint.getColor() != i) {
                this.mIndicatorBackgroundPaint.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        public void setIndicatorBackgroundHeight(int i) {
            if (this.mIndicatorBackgroundHeight != i) {
                this.mIndicatorBackgroundHeight = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setIndicatorDrawable(Drawable drawable) {
            if (this.mIndicatorDrawable != drawable) {
                this.mIndicatorDrawable = drawable;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setIndicatorHeight(int i) {
            if (this.mIndicatorHeight != i) {
                this.mIndicatorHeight = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setIndicatorWidth(int i) {
            if (this.mIndicatorWidth != i) {
                this.mIndicatorWidth = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setCircleDotRadius(int i) {
            if (this.mCircleDotRadius != i) {
                this.mCircleDotRadius = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setCircleDotColor(int i) {
            if (this.mCircleDotPaint.getColor() != i) {
                this.mCircleDotPaint.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        boolean childrenNeedLayout() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void setIndicatorPositionFromTabPosition(int i, float f) {
            ValueAnimatorCompat valueAnimatorCompat = this.mIndicatorAnimator;
            if (valueAnimatorCompat != null && valueAnimatorCompat.isRunning()) {
                this.mIndicatorAnimator.cancel();
            }
            this.mSelectedPosition = i;
            this.mSelectionOffset = f;
            updateIndicatorPosition();
        }

        float getIndicatorPosition() {
            return this.mSelectedPosition + this.mSelectionOffset;
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) != 1073741824) {
                return;
            }
            boolean z = true;
            if (TvTabLayout.this.mScrollMode != 1 || TvTabLayout.this.mTabGravity == 3) {
                return;
            }
            int childCount = getChildCount();
            int iMax = 0;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    iMax = Math.max(iMax, childAt.getMeasuredWidth());
                }
            }
            if (iMax <= 0) {
                return;
            }
            if (iMax * childCount <= getMeasuredWidth() - (TvTabLayout.this.dpToPx(16) * 2)) {
                boolean z2 = false;
                for (int i4 = 0; i4 < childCount; i4++) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i4).getLayoutParams();
                    if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                        layoutParams.width = iMax;
                        layoutParams.weight = 0.0f;
                        z2 = true;
                    }
                }
                z = z2;
            } else {
                TvTabLayout.this.mTabGravity = 3;
                TvTabLayout.this.updateTabViews(false);
            }
            if (z) {
                super.onMeasure(i, i2);
            }
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimatorCompat valueAnimatorCompat = this.mIndicatorAnimator;
            if (valueAnimatorCompat != null && valueAnimatorCompat.isRunning()) {
                this.mIndicatorAnimator.cancel();
                animateIndicatorToPosition(this.mSelectedPosition, Math.round((1.0f - this.mIndicatorAnimator.getAnimatedFraction()) * this.mIndicatorAnimator.getDuration()));
                return;
            }
            updateIndicatorPosition();
        }

        private void updateIndicatorPosition() {
            int i;
            int i2;
            View childAt = getChildAt(this.mSelectedPosition);
            if (childAt == null || childAt.getWidth() <= 0) {
                i = -1;
                i2 = -1;
            } else {
                int left = childAt.getLeft();
                int right = childAt.getRight();
                int width = this.mIndicatorWidth > 0 ? (childAt.getWidth() - this.mIndicatorWidth) / 2 : 0;
                if (this.mSelectionOffset > 0.0f && this.mSelectedPosition < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.mSelectedPosition + 1);
                    float left2 = this.mSelectionOffset * childAt2.getLeft();
                    float f = this.mSelectionOffset;
                    left = (int) (left2 + ((1.0f - f) * left));
                    right = (int) ((f * childAt2.getRight()) + ((1.0f - this.mSelectionOffset) * right));
                }
                i = left + width;
                i2 = right - width;
            }
            updateIndicatorTopAndBottom();
            updateIndicatorBackgroundBounds();
            setIndicatorPosition(i, i2);
        }

        private void updateIndicatorTopAndBottom() {
            int i = TvTabLayout.this.mIndicatorGravity;
            if (i == 0) {
                this.mIndicatorTop = 0;
                this.mIndicatorBottom = this.mIndicatorHeight;
            } else if (i == 1) {
                this.mIndicatorTop = (getHeight() - this.mIndicatorHeight) / 2;
                this.mIndicatorBottom = (getHeight() + this.mIndicatorHeight) / 2;
            } else {
                if (i != 2) {
                    return;
                }
                this.mIndicatorTop = getHeight() - this.mIndicatorHeight;
                this.mIndicatorBottom = getHeight();
            }
        }

        private void updateIndicatorBackgroundBounds() {
            int right;
            int width;
            if (this.mIndicatorBackgroundHeight > 0) {
                this.mIndicatorBackgroundLeft = 0;
                this.mIndicatorBackgroundRight = getWidth();
                int i = this.mIndicatorTop;
                int i2 = this.mIndicatorHeight;
                int i3 = this.mIndicatorBackgroundHeight;
                int i4 = i + ((i2 - i3) / 2);
                this.mIndicatorBackgroundTop = i4;
                this.mIndicatorBackgroundBottom = i4 + i3;
                if (this.mCircleDotRadius > 0) {
                    View childAt = getChildAt(0);
                    View childAt2 = getChildAt(getChildCount() - 1);
                    this.mIndicatorBackgroundLeft = (childAt == null ? TvTabLayout.this.mRequestedTabMinWidth : childAt.getWidth()) / 2;
                    if (childAt2 == null) {
                        right = getChildCount() * TvTabLayout.this.mRequestedTabMinWidth;
                        width = TvTabLayout.this.mRequestedTabMinWidth / 2;
                    } else {
                        right = childAt2.getRight();
                        width = childAt2.getWidth() / 2;
                    }
                    this.mIndicatorBackgroundRight = right - width;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIndicatorPosition(int i, int i2) {
            if (i == this.mIndicatorLeft && i2 == this.mIndicatorRight) {
                return;
            }
            this.mIndicatorLeft = i;
            this.mIndicatorRight = i2;
            ViewCompat.postInvalidateOnAnimation(this);
        }

        void animateIndicatorToPosition(final int i, int i2) {
            final int i3;
            final int i4;
            ValueAnimatorCompat valueAnimatorCompat = this.mIndicatorAnimator;
            if (valueAnimatorCompat != null && valueAnimatorCompat.isRunning()) {
                this.mIndicatorAnimator.cancel();
            }
            boolean z = ViewCompat.getLayoutDirection(this) == 1;
            View childAt = getChildAt(i);
            if (childAt == null) {
                updateIndicatorPosition();
                return;
            }
            int width = this.mIndicatorWidth > 0 ? (childAt.getWidth() - this.mIndicatorWidth) / 2 : 0;
            final int left = childAt.getLeft() + width;
            final int right = childAt.getRight() - width;
            if (Math.abs(i - this.mSelectedPosition) > 1) {
                int iDpToPx = TvTabLayout.this.dpToPx(24);
                i3 = (i >= this.mSelectedPosition ? !z : z) ? left - iDpToPx : iDpToPx + right;
                i4 = i3;
            } else {
                i3 = this.mIndicatorLeft;
                i4 = this.mIndicatorRight;
            }
            if (i3 == left && i4 == right) {
                return;
            }
            ValueAnimatorCompat valueAnimatorCompatCreateAnimator = ViewUtils.createAnimator();
            this.mIndicatorAnimator = valueAnimatorCompatCreateAnimator;
            valueAnimatorCompatCreateAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            valueAnimatorCompatCreateAnimator.setDuration(i2);
            valueAnimatorCompatCreateAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimatorCompatCreateAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener() { // from class: com.owen.tab.TvTabLayout.SlidingTabStrip.1
                @Override // com.owen.tab.ValueAnimatorCompat.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat2) {
                    float animatedFraction = valueAnimatorCompat2.getAnimatedFraction();
                    SlidingTabStrip.this.setIndicatorPosition(AnimationUtils.lerp(i3, left, animatedFraction), AnimationUtils.lerp(i4, right, animatedFraction));
                }
            });
            valueAnimatorCompatCreateAnimator.setListener(new ValueAnimatorCompat.AnimatorListenerAdapter() { // from class: com.owen.tab.TvTabLayout.SlidingTabStrip.2
                @Override // com.owen.tab.ValueAnimatorCompat.AnimatorListenerAdapter, com.owen.tab.ValueAnimatorCompat.AnimatorListener
                public void onAnimationEnd(ValueAnimatorCompat valueAnimatorCompat2) {
                    SlidingTabStrip.this.mSelectedPosition = i;
                    SlidingTabStrip.this.mSelectionOffset = 0.0f;
                }
            });
            valueAnimatorCompatCreateAnimator.start();
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            int i;
            int i2;
            canvas.save();
            int childCount = getChildCount();
            if (this.mIndicatorBackgroundHeight > 0) {
                canvas.drawRect(this.mIndicatorBackgroundLeft, this.mIndicatorBackgroundTop, this.mIndicatorBackgroundRight, this.mIndicatorBackgroundBottom, this.mIndicatorBackgroundPaint);
            }
            if (this.mCircleDotRadius > 0) {
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = getChildAt(i3);
                    if (childAt != null) {
                        canvas.drawCircle((childAt.getWidth() / 2) + (childAt.getWidth() * i3), getHeight() - ((this.mIndicatorHeight - this.mIndicatorTop) / 2), this.mCircleDotRadius, this.mCircleDotPaint);
                    }
                }
            }
            Drawable drawable = this.mIndicatorDrawable;
            if (drawable != null && (i = this.mIndicatorLeft) >= 0 && (i2 = this.mIndicatorRight) > i) {
                drawable.setBounds(i, this.mIndicatorTop, i2, this.mIndicatorBottom);
                this.mIndicatorDrawable.draw(canvas);
            }
            canvas.restore();
            super.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        Drawable drawable = this.mTabStrip.mIndicatorDrawable;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
        super.drawableStateChanged();
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mTabStrip.mIndicatorDrawable || super.verifyDrawable(drawable);
    }

    private static ColorStateList createColorStateList(int i, int i2, int i3) {
        return new ColorStateList(new int[][]{new int[]{android.R.attr.state_activated}, new int[]{android.R.attr.state_selected}, new int[]{0}}, new int[]{i2, i3, i});
    }

    private int getDefaultHeight() {
        int i;
        int size = this.mTabs.size();
        for (int i2 = 0; i2 < size; i2++) {
            Tab tab = this.mTabs.get(i2);
            if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                i = DEFAULT_HEIGHT_WITH_TEXT_ICON;
                return Math.max(this.mTabStrip.mIndicatorHeight, dpToPx(i));
            }
        }
        i = 48;
        return Math.max(this.mTabStrip.mIndicatorHeight, dpToPx(i));
    }

    private int getTabMinWidth() {
        int i = this.mRequestedTabMinWidth;
        if (i != -1) {
            return i;
        }
        if (this.mScrollMode == 0) {
            return this.mScrollableTabMinWidth;
        }
        return 0;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTabMaxWidth() {
        return this.mTabMaxWidth;
    }

    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int mPreviousScrollState;
        private int mScrollState;
        private final WeakReference<TvTabLayout> mTabLayoutRef;

        public TabLayoutOnPageChangeListener(TvTabLayout tvTabLayout) {
            this.mTabLayoutRef = new WeakReference<>(tvTabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.mPreviousScrollState = this.mScrollState;
            this.mScrollState = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            TvTabLayout tvTabLayout = this.mTabLayoutRef.get();
            if (tvTabLayout != null) {
                tvTabLayout.setScrollPosition(i, f, this.mScrollState != 2 || this.mPreviousScrollState == 1, true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            TvTabLayout tvTabLayout = this.mTabLayoutRef.get();
            if (tvTabLayout == null || tvTabLayout.getSelectedTabPosition() == i || i >= tvTabLayout.getTabCount()) {
                return;
            }
            int i2 = this.mScrollState;
            tvTabLayout.selectTab(tvTabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.mPreviousScrollState == 0));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mScrollState = 0;
            this.mPreviousScrollState = 0;
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private Handler mHandler = new Handler() { // from class: com.owen.tab.TvTabLayout.ViewPagerOnTabSelectedListener.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 122) {
                    ViewPagerOnTabSelectedListener.this.mViewPager.setCurrentItem(message.arg1);
                }
            }
        };
        private final ViewPager mViewPager;

        @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
        public void onTabReselected(Tab tab) {
        }

        @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
        public void onTabUnselected(Tab tab) {
        }

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.mViewPager = viewPager;
        }

        @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
        public void onTabSelected(Tab tab) {
            this.mHandler.removeMessages(IjkMediaMeta.FF_PROFILE_H264_HIGH_422);
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(IjkMediaMeta.FF_PROFILE_H264_HIGH_422, tab.mPosition, tab.mPosition), 100L);
        }
    }

    private class PagerAdapterObserver extends DataSetObserver {
        private PagerAdapterObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TvTabLayout.this.populateFromPagerAdapter();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TvTabLayout.this.populateFromPagerAdapter();
        }
    }

    private class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
        private boolean mAutoRefresh;

        private AdapterChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            if (TvTabLayout.this.mViewPager == viewPager) {
                TvTabLayout.this.setPagerAdapter(pagerAdapter2, this.mAutoRefresh);
            }
        }

        void setAutoRefresh(boolean z) {
            this.mAutoRefresh = z;
        }
    }
}
