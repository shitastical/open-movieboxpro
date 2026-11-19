package com.alibaba.android.vlayout;

import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public class DelegateAdapter extends VirtualLayoutAdapter<RecyclerView.ViewHolder> {
    private long[] cantorReverse;
    private final List<Pair<AdapterDataObserver, Adapter>> mAdapters;
    private final boolean mHasConsistItemType;
    private int mIndex;
    private final SparseArray<Pair<AdapterDataObserver, Adapter>> mIndexAry;
    private AtomicInteger mIndexGen;
    private SparseArray<Adapter> mItemTypeAry;
    private int mTotal;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void setHasStableIds(boolean z) {
    }

    public DelegateAdapter(VirtualLayoutManager virtualLayoutManager) {
        this(virtualLayoutManager, false, false);
    }

    public DelegateAdapter(VirtualLayoutManager virtualLayoutManager, boolean z) {
        this(virtualLayoutManager, z, false);
    }

    DelegateAdapter(VirtualLayoutManager virtualLayoutManager, boolean z, boolean z2) {
        super(virtualLayoutManager);
        this.mIndex = 0;
        this.mItemTypeAry = new SparseArray<>();
        this.mAdapters = new ArrayList();
        this.mTotal = 0;
        this.mIndexAry = new SparseArray<>();
        this.cantorReverse = new long[2];
        if (z2) {
            this.mIndexGen = new AtomicInteger(0);
        }
        this.mHasConsistItemType = z;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.recyclerview.widget.RecyclerView$ViewHolder] */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.recyclerview.widget.RecyclerView$ViewHolder] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.mHasConsistItemType) {
            Adapter adapter = this.mItemTypeAry.get(i);
            if (adapter != null) {
                return adapter.onCreateViewHolder(viewGroup, i);
            }
            return null;
        }
        Cantor.reverseCantor(i, this.cantorReverse);
        long[] jArr = this.cantorReverse;
        int i2 = (int) jArr[1];
        int i3 = (int) jArr[0];
        Adapter adapterFindAdapterByIndex = findAdapterByIndex(i2);
        if (adapterFindAdapterByIndex == null) {
            return null;
        }
        return adapterFindAdapterByIndex.onCreateViewHolder(viewGroup, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Pair<AdapterDataObserver, Adapter> pairFindAdapterByPosition = findAdapterByPosition(i);
        if (pairFindAdapterByPosition == null) {
            return;
        }
        ((Adapter) pairFindAdapterByPosition.second).onBindViewHolder(viewHolder, i - ((AdapterDataObserver) pairFindAdapterByPosition.first).mStartPosition);
        ((Adapter) pairFindAdapterByPosition.second).onBindViewHolderWithOffset(viewHolder, i - ((AdapterDataObserver) pairFindAdapterByPosition.first).mStartPosition, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        Pair<AdapterDataObserver, Adapter> pairFindAdapterByPosition = findAdapterByPosition(i);
        if (pairFindAdapterByPosition == null) {
            return;
        }
        ((Adapter) pairFindAdapterByPosition.second).onBindViewHolder(viewHolder, i - ((AdapterDataObserver) pairFindAdapterByPosition.first).mStartPosition, list);
        ((Adapter) pairFindAdapterByPosition.second).onBindViewHolderWithOffset(viewHolder, i - ((AdapterDataObserver) pairFindAdapterByPosition.first).mStartPosition, i, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mTotal;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        Pair<AdapterDataObserver, Adapter> pairFindAdapterByPosition = findAdapterByPosition(i);
        if (pairFindAdapterByPosition == null) {
            return -1;
        }
        int itemViewType = ((Adapter) pairFindAdapterByPosition.second).getItemViewType(i - ((AdapterDataObserver) pairFindAdapterByPosition.first).mStartPosition);
        if (itemViewType < 0) {
            return itemViewType;
        }
        if (this.mHasConsistItemType) {
            this.mItemTypeAry.put(itemViewType, (Adapter) pairFindAdapterByPosition.second);
            return itemViewType;
        }
        return (int) Cantor.getCantor(itemViewType, ((AdapterDataObserver) pairFindAdapterByPosition.first).mIndex);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        Pair<AdapterDataObserver, Adapter> pairFindAdapterByPosition = findAdapterByPosition(i);
        if (pairFindAdapterByPosition == null) {
            return -1L;
        }
        long itemId = ((Adapter) pairFindAdapterByPosition.second).getItemId(i - ((AdapterDataObserver) pairFindAdapterByPosition.first).mStartPosition);
        if (itemId < 0) {
            return -1L;
        }
        return Cantor.getCantor(((AdapterDataObserver) pairFindAdapterByPosition.first).mIndex, itemId);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        Pair<AdapterDataObserver, Adapter> pairFindAdapterByPosition;
        super.onViewRecycled(viewHolder);
        int position = viewHolder.getPosition();
        if (position < 0 || (pairFindAdapterByPosition = findAdapterByPosition(position)) == null) {
            return;
        }
        ((Adapter) pairFindAdapterByPosition.second).onViewRecycled(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        Pair<AdapterDataObserver, Adapter> pairFindAdapterByPosition;
        super.onViewAttachedToWindow(viewHolder);
        int position = viewHolder.getPosition();
        if (position < 0 || (pairFindAdapterByPosition = findAdapterByPosition(position)) == null) {
            return;
        }
        ((Adapter) pairFindAdapterByPosition.second).onViewAttachedToWindow(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        Pair<AdapterDataObserver, Adapter> pairFindAdapterByPosition;
        super.onViewDetachedFromWindow(viewHolder);
        int position = viewHolder.getPosition();
        if (position < 0 || (pairFindAdapterByPosition = findAdapterByPosition(position)) == null) {
            return;
        }
        ((Adapter) pairFindAdapterByPosition.second).onViewDetachedFromWindow(viewHolder);
    }

    @Override // com.alibaba.android.vlayout.VirtualLayoutAdapter
    @Deprecated
    public void setLayoutHelpers(List<LayoutHelper> list) {
        throw new UnsupportedOperationException("DelegateAdapter doesn't support setLayoutHelpers directly");
    }

    public void setAdapters(List<Adapter> list) {
        int iIncrementAndGet;
        clear();
        if (list == null) {
            list = Collections.emptyList();
        }
        LinkedList linkedList = new LinkedList();
        this.mTotal = 0;
        boolean z = true;
        for (Adapter adapter : list) {
            int i = this.mTotal;
            AtomicInteger atomicInteger = this.mIndexGen;
            if (atomicInteger == null) {
                iIncrementAndGet = this.mIndex;
                this.mIndex = iIncrementAndGet + 1;
            } else {
                iIncrementAndGet = atomicInteger.incrementAndGet();
            }
            AdapterDataObserver adapterDataObserver = new AdapterDataObserver(i, iIncrementAndGet);
            adapter.registerAdapterDataObserver(adapterDataObserver);
            z = z && adapter.hasStableIds();
            LayoutHelper layoutHelperOnCreateLayoutHelper = adapter.onCreateLayoutHelper();
            layoutHelperOnCreateLayoutHelper.setItemCount(adapter.getItemCount());
            this.mTotal += layoutHelperOnCreateLayoutHelper.getItemCount();
            linkedList.add(layoutHelperOnCreateLayoutHelper);
            Pair<AdapterDataObserver, Adapter> pairCreate = Pair.create(adapterDataObserver, adapter);
            this.mIndexAry.put(adapterDataObserver.mIndex, pairCreate);
            this.mAdapters.add(pairCreate);
        }
        if (!hasObservers()) {
            super.setHasStableIds(z);
        }
        super.setLayoutHelpers(linkedList);
    }

    public void addAdapters(int i, List<Adapter> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        if (i < 0) {
            i = 0;
        }
        if (i > this.mAdapters.size()) {
            i = this.mAdapters.size();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Pair<AdapterDataObserver, Adapter>> it = this.mAdapters.iterator();
        while (it.hasNext()) {
            arrayList.add((Adapter) it.next().second);
        }
        Iterator<Adapter> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(i, it2.next());
            i++;
        }
        setAdapters(arrayList);
    }

    public void addAdapters(List<Adapter> list) {
        addAdapters(this.mAdapters.size(), list);
    }

    public void addAdapter(int i, Adapter adapter) {
        addAdapters(i, Collections.singletonList(adapter));
    }

    public void addAdapter(Adapter adapter) {
        addAdapters(Collections.singletonList(adapter));
    }

    public void removeFirstAdapter() {
        List<Pair<AdapterDataObserver, Adapter>> list = this.mAdapters;
        if (list == null || list.isEmpty()) {
            return;
        }
        removeAdapter((Adapter) this.mAdapters.get(0).second);
    }

    public void removeLastAdapter() {
        List<Pair<AdapterDataObserver, Adapter>> list = this.mAdapters;
        if (list == null || list.isEmpty()) {
            return;
        }
        List<Pair<AdapterDataObserver, Adapter>> list2 = this.mAdapters;
        removeAdapter((Adapter) list2.get(list2.size() - 1).second);
    }

    public void removeAdapter(int i) {
        if (i < 0 || i >= this.mAdapters.size()) {
            return;
        }
        removeAdapter((Adapter) this.mAdapters.get(i).second);
    }

    public void removeAdapter(Adapter adapter) {
        if (adapter == null) {
            return;
        }
        removeAdapters(Collections.singletonList(adapter));
    }

    public void removeAdapters(List<Adapter> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        LinkedList linkedList = new LinkedList(super.getLayoutHelpers());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Adapter adapter = list.get(i);
            Iterator<Pair<AdapterDataObserver, Adapter>> it = this.mAdapters.iterator();
            while (it.hasNext()) {
                Pair<AdapterDataObserver, Adapter> next = it.next();
                Adapter adapter2 = (Adapter) next.second;
                if (adapter2.equals(adapter)) {
                    adapter2.unregisterAdapterDataObserver((RecyclerView.AdapterDataObserver) next.first);
                    int iFindAdapterPositionByIndex = findAdapterPositionByIndex(((AdapterDataObserver) next.first).mIndex);
                    if (iFindAdapterPositionByIndex >= 0 && iFindAdapterPositionByIndex < linkedList.size()) {
                        linkedList.remove(iFindAdapterPositionByIndex);
                    }
                    it.remove();
                    break;
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Pair<AdapterDataObserver, Adapter>> it2 = this.mAdapters.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().second);
        }
        setAdapters(arrayList);
    }

    public void clear() {
        this.mTotal = 0;
        this.mIndex = 0;
        AtomicInteger atomicInteger = this.mIndexGen;
        if (atomicInteger != null) {
            atomicInteger.set(0);
        }
        this.mLayoutManager.setLayoutHelpers(null);
        for (Pair<AdapterDataObserver, Adapter> pair : this.mAdapters) {
            ((Adapter) pair.second).unregisterAdapterDataObserver((RecyclerView.AdapterDataObserver) pair.first);
        }
        this.mItemTypeAry.clear();
        this.mAdapters.clear();
        this.mIndexAry.clear();
    }

    public int getAdaptersCount() {
        List<Pair<AdapterDataObserver, Adapter>> list = this.mAdapters;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int findOffsetPosition(int i) {
        Pair<AdapterDataObserver, Adapter> pairFindAdapterByPosition = findAdapterByPosition(i);
        if (pairFindAdapterByPosition == null) {
            return -1;
        }
        return i - ((AdapterDataObserver) pairFindAdapterByPosition.first).mStartPosition;
    }

    public Pair<AdapterDataObserver, Adapter> findAdapterByPosition(int i) {
        int size = this.mAdapters.size();
        if (size == 0) {
            return null;
        }
        int i2 = size - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) / 2;
            Pair<AdapterDataObserver, Adapter> pair = this.mAdapters.get(i4);
            int itemCount = (((AdapterDataObserver) pair.first).mStartPosition + ((Adapter) pair.second).getItemCount()) - 1;
            if (((AdapterDataObserver) pair.first).mStartPosition > i) {
                i2 = i4 - 1;
            } else if (itemCount < i) {
                i3 = i4 + 1;
            } else if (((AdapterDataObserver) pair.first).mStartPosition <= i && itemCount >= i) {
                return pair;
            }
        }
        return null;
    }

    public int findAdapterPositionByIndex(int i) {
        Pair<AdapterDataObserver, Adapter> pair = this.mIndexAry.get(i);
        if (pair == null) {
            return -1;
        }
        return this.mAdapters.indexOf(pair);
    }

    public Adapter findAdapterByIndex(int i) {
        return (Adapter) this.mIndexAry.get(i).second;
    }

    protected class AdapterDataObserver extends RecyclerView.AdapterDataObserver {
        int mIndex;
        int mStartPosition;

        public AdapterDataObserver(int i, int i2) {
            this.mStartPosition = i;
            this.mIndex = i2;
        }

        public void updateStartPositionAndIndex(int i, int i2) {
            this.mStartPosition = i;
            this.mIndex = i2;
        }

        public int getStartPosition() {
            return this.mStartPosition;
        }

        public int getIndex() {
            return this.mIndex;
        }

        private boolean updateLayoutHelper() {
            int iFindAdapterPositionByIndex;
            int i = this.mIndex;
            if (i < 0 || (iFindAdapterPositionByIndex = DelegateAdapter.this.findAdapterPositionByIndex(i)) < 0) {
                return false;
            }
            Pair pair = (Pair) DelegateAdapter.this.mAdapters.get(iFindAdapterPositionByIndex);
            LinkedList linkedList = new LinkedList(DelegateAdapter.this.getLayoutHelpers());
            LayoutHelper layoutHelper = (LayoutHelper) linkedList.get(iFindAdapterPositionByIndex);
            if (layoutHelper.getItemCount() != ((Adapter) pair.second).getItemCount()) {
                layoutHelper.setItemCount(((Adapter) pair.second).getItemCount());
                DelegateAdapter.this.mTotal = this.mStartPosition + ((Adapter) pair.second).getItemCount();
                for (int i2 = iFindAdapterPositionByIndex + 1; i2 < DelegateAdapter.this.mAdapters.size(); i2++) {
                    Pair pair2 = (Pair) DelegateAdapter.this.mAdapters.get(i2);
                    ((AdapterDataObserver) pair2.first).mStartPosition = DelegateAdapter.this.mTotal;
                    DelegateAdapter.this.mTotal += ((Adapter) pair2.second).getItemCount();
                }
                DelegateAdapter.super.setLayoutHelpers(linkedList);
            }
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            if (updateLayoutHelper()) {
                DelegateAdapter.this.notifyDataSetChanged();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            if (updateLayoutHelper()) {
                DelegateAdapter.this.notifyItemRangeRemoved(this.mStartPosition + i, i2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            if (updateLayoutHelper()) {
                DelegateAdapter.this.notifyItemRangeInserted(this.mStartPosition + i, i2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            if (updateLayoutHelper()) {
                DelegateAdapter delegateAdapter = DelegateAdapter.this;
                int i4 = this.mStartPosition;
                delegateAdapter.notifyItemMoved(i + i4, i4 + i2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            if (updateLayoutHelper()) {
                DelegateAdapter.this.notifyItemRangeChanged(this.mStartPosition + i, i2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, Object obj) {
            if (updateLayoutHelper()) {
                DelegateAdapter.this.notifyItemRangeChanged(this.mStartPosition + i, i2, obj);
            }
        }
    }

    public static Adapter<? extends RecyclerView.ViewHolder> simpleAdapter(View view) {
        return new SimpleViewAdapter(view);
    }

    public static Adapter<? extends RecyclerView.ViewHolder> simpleAdapter(View view, LayoutHelper layoutHelper) {
        return new SimpleViewAdapter(view, layoutHelper);
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View view) {
            super(view);
        }
    }

    static class SimpleViewAdapter extends Adapter<RecyclerView.ViewHolder> {
        private LayoutHelper mLayoutHelper;
        private View mView;

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        }

        public SimpleViewAdapter(View view, LayoutHelper layoutHelper) {
            this.mView = view;
            this.mLayoutHelper = layoutHelper;
        }

        public SimpleViewAdapter(View view) {
            this(view, new SingleLayoutHelper());
        }

        @Override // com.alibaba.android.vlayout.DelegateAdapter.Adapter
        public LayoutHelper onCreateLayoutHelper() {
            return this.mLayoutHelper;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SimpleViewHolder(this.mView);
        }
    }

    public static abstract class Adapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
        protected void onBindViewHolderWithOffset(VH vh, int i, int i2) {
        }

        public abstract LayoutHelper onCreateLayoutHelper();

        protected void onBindViewHolderWithOffset(VH vh, int i, int i2, List<Object> list) {
            onBindViewHolderWithOffset(vh, i, i2);
        }
    }
}
