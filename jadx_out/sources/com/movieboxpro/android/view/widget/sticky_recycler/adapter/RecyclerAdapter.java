package com.movieboxpro.android.view.widget.sticky_recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.view.widget.sticky_recycler.bean.Basedata;
import com.movieboxpro.androidtv.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RecyclerAdapter<T extends Basedata> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public T mData;
    public List<T> mDatas;
    private int loadState = 2;
    public final int LOADING = 1;
    public final int LOADING_COMPLETE = 2;
    public final int LOADING_END = 3;

    public RecyclerAdapter(Context mContext, List<T> mDatas) {
        new ArrayList();
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    static class ViewHolde extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolde(View itemView) {
            super(itemView);
            this.txt = (TextView) itemView.findViewById(R.id.txt);
        }
    }

    static class ViewHolderFoot extends RecyclerView.ViewHolder {
        TextView textfoot;

        public ViewHolderFoot(View itemView) {
            super(itemView);
            this.textfoot = (TextView) itemView.findViewById(R.id.list_item_foot);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_sticky_item, parent, false);
            viewInflate.setTag(true);
            return new ViewHolde(viewInflate);
        }
        if (viewType == 0) {
            View viewInflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.layout_sticky_foot, parent, false);
            viewInflate2.setTag(false);
            return new ViewHolderFoot(viewInflate2);
        }
        View viewInflate3 = LayoutInflater.from(this.mContext).inflate(R.layout.layout_sticky_item, parent, false);
        viewInflate3.setTag(false);
        return new ViewHolde(viewInflate3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolde) {
            this.mData = this.mDatas.get(position);
            ViewHolde viewHolde = (ViewHolde) holder;
            viewHolde.txt.setText(this.mData.getName());
            if (this.mData.getType() == 1) {
                viewHolde.txt.setTextColor(this.mContext.getResources().getColor(R.color.colorAccent));
                viewHolde.txt.setBackgroundColor(this.mContext.getResources().getColor(R.color.red));
                viewHolde.txt.setTextSize(20.0f);
                return;
            }
            return;
        }
        int i = this.loadState;
        if (i == 1) {
            ViewHolderFoot viewHolderFoot = (ViewHolderFoot) holder;
            viewHolderFoot.textfoot.setText("正在加载。。");
            viewHolderFoot.textfoot.setVisibility(0);
        } else if (i == 2) {
            ((ViewHolderFoot) holder).textfoot.setVisibility(8);
        } else {
            if (i != 3) {
                return;
            }
            ViewHolderFoot viewHolderFoot2 = (ViewHolderFoot) holder;
            viewHolderFoot2.textfoot.setText("没有更多数据");
            viewHolderFoot2.textfoot.setVisibility(0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return 0;
        }
        return this.mDatas.get(position).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDatas.size() + 1;
    }

    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }

    public boolean isLoadState() {
        return this.loadState == 2;
    }
}
