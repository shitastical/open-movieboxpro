package com.alibaba.android.vlayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class VirtualLayoutAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected VirtualLayoutManager mLayoutManager;

    public VirtualLayoutAdapter(VirtualLayoutManager virtualLayoutManager) {
        this.mLayoutManager = virtualLayoutManager;
    }

    public void setLayoutHelpers(List<LayoutHelper> list) {
        this.mLayoutManager.setLayoutHelpers(list);
    }

    public List<LayoutHelper> getLayoutHelpers() {
        return this.mLayoutManager.getLayoutHelpers();
    }
}
