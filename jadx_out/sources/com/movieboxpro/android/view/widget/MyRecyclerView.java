package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class MyRecyclerView extends RecyclerView {
    private View mEmptyView;
    private RecyclerView.AdapterDataObserver mObserver;

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mObserver = new RecyclerView.AdapterDataObserver() { // from class: com.movieboxpro.android.view.widget.MyRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                if (MyRecyclerView.this.getAdapter().getItemCount() == 0) {
                    MyRecyclerView.this.mEmptyView.setVisibility(0);
                    MyRecyclerView.this.setVisibility(8);
                } else {
                    MyRecyclerView.this.mEmptyView.setVisibility(8);
                    MyRecyclerView.this.setVisibility(0);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int positionStart, int itemCount) {
                onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int positionStart, int itemCount) {
                onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                onChanged();
            }
        };
    }

    public void setEmptyView(View view) {
        this.mEmptyView = view;
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(this.mObserver);
    }
}
