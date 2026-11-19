package com.movieboxpro.android.view.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class CustomLoadMoreView extends BaseLoadMoreView {
    @Override // com.chad.library.adapter.base.loadmore.BaseLoadMoreView
    public View getLoadComplete(BaseViewHolder baseViewHolder) {
        return baseViewHolder.getView(R.id.tv_prompt);
    }

    @Override // com.chad.library.adapter.base.loadmore.BaseLoadMoreView
    public View getLoadEndView(BaseViewHolder baseViewHolder) {
        return baseViewHolder.getView(R.id.load_more_load_end_view);
    }

    @Override // com.chad.library.adapter.base.loadmore.BaseLoadMoreView
    public View getLoadFailView(BaseViewHolder baseViewHolder) {
        return baseViewHolder.getView(R.id.load_more_load_fail_view);
    }

    @Override // com.chad.library.adapter.base.loadmore.BaseLoadMoreView
    public View getLoadingView(BaseViewHolder baseViewHolder) {
        return baseViewHolder.getView(R.id.load_more_loading_view);
    }

    @Override // com.chad.library.adapter.base.loadmore.BaseLoadMoreView
    public View getRootView(ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.load_more_view, viewGroup, false);
    }
}
