package com.movieboxpro.android.tv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class TitleDelegateAdapter extends DelegateAdapter.Adapter<ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper = new SingleLayoutHelper();
    private String mTitleText;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    public TitleDelegateAdapter(Context context, String titleText) {
        this.mContext = context;
        this.mTitleText = titleText;
    }

    @Override // com.alibaba.android.vlayout.DelegateAdapter.Adapter
    public LayoutHelper onCreateLayoutHelper() {
        return this.mLayoutHelper;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.feature_title_layout, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alibaba.android.vlayout.DelegateAdapter.Adapter
    public void onBindViewHolderWithOffset(ViewHolder holder, int position, int offsetTotal) {
        ((TextView) holder.itemView.findViewById(R.id.textView)).setText(this.mTitleText);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
