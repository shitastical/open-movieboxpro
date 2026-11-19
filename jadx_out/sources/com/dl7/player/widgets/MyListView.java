package com.dl7.player.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* JADX INFO: loaded from: classes4.dex */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        int selectedItemPosition = getSelectedItemPosition();
        super.onFocusChanged(z, i, rect);
        if (z) {
            View childAt = getChildAt(selectedItemPosition - getFirstVisiblePosition());
            setSelectionFromTop(selectedItemPosition, childAt == null ? 0 : childAt.getTop());
        }
    }
}
