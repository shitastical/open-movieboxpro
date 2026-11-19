package com.kingja.loadsir.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes5.dex */
public class TargetContext {
    private int childIndex;
    private Context context;
    private View oldContent;
    private ViewGroup parentView;

    public TargetContext(Context context, ViewGroup viewGroup, View view, int i) {
        this.context = context;
        this.parentView = viewGroup;
        this.oldContent = view;
        this.childIndex = i;
    }

    public Context getContext() {
        return this.context;
    }

    View getOldContent() {
        return this.oldContent;
    }

    int getChildIndex() {
        return this.childIndex;
    }

    ViewGroup getParentView() {
        return this.parentView;
    }
}
