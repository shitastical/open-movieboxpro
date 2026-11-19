package com.movieboxpro.android.view.widget.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes5.dex */
public class MyScrollerView extends ScrollView {
    public OnScrollListener scrollListener;

    public interface OnScrollListener {
        void onScroll(int scrollY, int oldscrollY);
    }

    public void setScrollListener(OnScrollListener onScrollListener) {
        this.scrollListener = onScrollListener;
    }

    public MyScrollerView(Context context) {
        super(context);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        OnScrollListener onScrollListener = this.scrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(t, oldt);
        }
    }
}
