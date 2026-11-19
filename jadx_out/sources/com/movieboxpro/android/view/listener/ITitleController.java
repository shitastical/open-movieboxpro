package com.movieboxpro.android.view.listener;

import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public interface ITitleController {
    void clearTitleRight();

    void clearTitleRightNew();

    void setTitle(String title);

    void setTitle(String title, View.OnClickListener listener);

    void setTitleBack(boolean isShow);

    void setTitleBackground(int color);

    void setTitleBar(boolean isShow);

    void setTitleLeftIcon(int res, View.OnClickListener listener);

    void setTitleLeftText(String left, View.OnClickListener listener);

    void setTitleRightIcon(int res, View.OnClickListener listener);

    void setTitleRightIcon(int res, boolean hasNew, View.OnClickListener listener);

    void setTitleRightText(String right, View.OnClickListener listener);

    void setTitleRightText(String right, boolean hasBorder, View.OnClickListener listener);
}
