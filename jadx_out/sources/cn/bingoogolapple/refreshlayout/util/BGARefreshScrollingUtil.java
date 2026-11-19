package cn.bingoogolapple.refreshlayout.util;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import androidx.core.view.ViewCompat;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.bingoogolapple.refreshlayout.BGAStickyNavLayout;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes4.dex */
public class BGARefreshScrollingUtil {
    private BGARefreshScrollingUtil() {
    }

    public static boolean isScrollViewOrWebViewToTop(View view) {
        return view != null && view.getScrollY() == 0;
    }

    public static boolean isAbsListViewToTop(AbsListView absListView) {
        if (absListView != null) {
            int top = absListView.getChildCount() > 0 ? absListView.getChildAt(0).getTop() - absListView.getPaddingTop() : 0;
            if (absListView.getFirstVisiblePosition() == 0 && top == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRecyclerViewToTop(RecyclerView recyclerView) {
        int top;
        if (recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null || layoutManager.getItemCount() == 0) {
                return true;
            }
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (recyclerView.getChildCount() > 0) {
                    View childAt = recyclerView.getChildAt(0);
                    if (childAt != null && childAt.getMeasuredHeight() >= recyclerView.getMeasuredHeight()) {
                        return !ViewCompat.canScrollVertically(recyclerView, -1);
                    }
                    View childAt2 = recyclerView.getChildAt(0);
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt2.getLayoutParams();
                    top = ((childAt2.getTop() - layoutParams.topMargin) - getRecyclerViewItemTopInset(layoutParams)) - recyclerView.getPaddingTop();
                } else {
                    top = 0;
                }
                if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() < 1 && top == 0) {
                    return true;
                }
            } else if ((layoutManager instanceof StaggeredGridLayoutManager) && ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(null)[0] < 1) {
                return true;
            }
        }
        return false;
    }

    private static int getRecyclerViewItemTopInset(RecyclerView.LayoutParams layoutParams) {
        try {
            Field declaredField = RecyclerView.LayoutParams.class.getDeclaredField("mDecorInsets");
            declaredField.setAccessible(true);
            return ((Rect) declaredField.get(layoutParams)).top;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isStickyNavLayoutToTop(BGAStickyNavLayout bGAStickyNavLayout) {
        return isScrollViewOrWebViewToTop(bGAStickyNavLayout) && bGAStickyNavLayout.isContentViewToTop();
    }

    public static boolean isWebViewToBottom(WebView webView) {
        return webView != null && ((float) webView.getContentHeight()) * webView.getScale() == ((float) (webView.getScrollY() + webView.getMeasuredHeight()));
    }

    public static boolean isScrollViewToBottom(ScrollView scrollView) {
        return scrollView != null && ((scrollView.getScrollY() + scrollView.getMeasuredHeight()) - scrollView.getPaddingTop()) - scrollView.getPaddingBottom() == scrollView.getChildAt(0).getMeasuredHeight();
    }

    public static boolean isAbsListViewToBottom(AbsListView absListView) {
        if (absListView == null || absListView.getAdapter() == null || absListView.getChildCount() <= 0 || absListView.getLastVisiblePosition() != ((ListAdapter) absListView.getAdapter()).getCount() - 1) {
            return false;
        }
        View childAt = absListView.getChildAt(absListView.getChildCount() - 1);
        BGAStickyNavLayout stickyNavLayout = getStickyNavLayout(absListView);
        if (stickyNavLayout == null) {
            return childAt.getBottom() <= absListView.getMeasuredHeight();
        }
        int[] iArr = new int[2];
        childAt.getLocationOnScreen(iArr);
        int measuredHeight = iArr[1] + childAt.getMeasuredHeight();
        stickyNavLayout.getLocationOnScreen(iArr);
        return measuredHeight + absListView.getPaddingBottom() <= iArr[1] + stickyNavLayout.getMeasuredHeight();
    }

    public static boolean isRecyclerViewToBottom(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager;
        View childAt;
        if (recyclerView != null && (layoutManager = recyclerView.getLayoutManager()) != null && layoutManager.getItemCount() != 0) {
            if (layoutManager instanceof LinearLayoutManager) {
                View childAt2 = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
                if (childAt2 != null && childAt2.getMeasuredHeight() >= recyclerView.getMeasuredHeight()) {
                    return !ViewCompat.canScrollVertically(recyclerView, 1);
                }
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.getItemCount() - 1) {
                    BGAStickyNavLayout stickyNavLayout = getStickyNavLayout(recyclerView);
                    if (stickyNavLayout == null || (childAt = linearLayoutManager.getChildAt(linearLayoutManager.findLastCompletelyVisibleItemPosition())) == null) {
                        return true;
                    }
                    int[] iArr = new int[2];
                    childAt.getLocationOnScreen(iArr);
                    int measuredHeight = iArr[1] + childAt.getMeasuredHeight();
                    stickyNavLayout.getLocationOnScreen(iArr);
                    return measuredHeight <= iArr[1] + stickyNavLayout.getMeasuredHeight();
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int[] iArrFindLastCompletelyVisibleItemPositions = staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(null);
                int itemCount = staggeredGridLayoutManager.getItemCount() - 1;
                for (int i : iArrFindLastCompletelyVisibleItemPositions) {
                    if (i == itemCount) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void scrollToBottom(final ScrollView scrollView) {
        if (scrollView != null) {
            scrollView.post(new Runnable() { // from class: cn.bingoogolapple.refreshlayout.util.BGARefreshScrollingUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    scrollView.fullScroll(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
                }
            });
        }
    }

    public static void scrollToBottom(final AbsListView absListView) {
        if (absListView == null || absListView.getAdapter() == null || ((ListAdapter) absListView.getAdapter()).getCount() <= 0) {
            return;
        }
        absListView.post(new Runnable() { // from class: cn.bingoogolapple.refreshlayout.util.BGARefreshScrollingUtil.2
            @Override // java.lang.Runnable
            public void run() {
                AbsListView absListView2 = absListView;
                absListView2.setSelection(((ListAdapter) absListView2.getAdapter()).getCount() - 1);
            }
        });
    }

    public static void scrollToBottom(final RecyclerView recyclerView) {
        if (recyclerView == null || recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() <= 0) {
            return;
        }
        recyclerView.post(new Runnable() { // from class: cn.bingoogolapple.refreshlayout.util.BGARefreshScrollingUtil.3
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView2 = recyclerView;
                recyclerView2.smoothScrollToPosition(recyclerView2.getAdapter().getItemCount() - 1);
            }
        });
    }

    public static BGAStickyNavLayout getStickyNavLayout(View view) {
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof BGAStickyNavLayout) {
                return (BGAStickyNavLayout) parent;
            }
        }
        return null;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
