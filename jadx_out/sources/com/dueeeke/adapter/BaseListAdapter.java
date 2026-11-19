package com.dueeeke.adapter;

import android.content.Context;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public BaseListAdapter(Context context) {
        this.mContext = context;
        this.mDatas = new ArrayList();
    }

    public BaseListAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    public BaseListAdapter(Context context, T[] tArr) {
        this.mContext = context;
        ArrayList arrayList = new ArrayList();
        this.mDatas = arrayList;
        Collections.addAll(arrayList, tArr);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mDatas.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int i) {
        return this.mDatas.get(i);
    }

    public void updateItems(List<T> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    public void addItem(T t) {
        this.mDatas.add(0, t);
        notifyDataSetChanged();
    }

    public void addItem(T t, int i) {
        this.mDatas.add(Math.min(i, this.mDatas.size()), t);
        notifyDataSetChanged();
    }

    public void addItems(List<T> list) {
        this.mDatas.addAll(list);
    }

    public void removeItem(int i) {
        if (i > this.mDatas.size() - 1) {
            return;
        }
        this.mDatas.remove(i);
        notifyDataSetChanged();
    }

    public void removeItem(T t) {
        Iterator<T> it = this.mDatas.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (t.hashCode() == it.next().hashCode()) {
                removeItem(i);
                return;
            }
            i++;
        }
    }

    public void cleanItems() {
        this.mDatas.clear();
        notifyDataSetChanged();
    }
}
