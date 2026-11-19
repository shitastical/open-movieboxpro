package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.movieboxpro.android.adapter.WatchingAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class WatchingItem extends BaseExpandNode {
    private transient WatchingAdapter adapter;
    private boolean haveWaiting;
    private ArrayList<FavoriteItem> list;
    private int waitingNum;
    private int watchingNum;

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return null;
    }

    public boolean isHaveWaiting() {
        return this.haveWaiting;
    }

    public void setHaveWaiting(boolean haveWaiting) {
        this.haveWaiting = haveWaiting;
    }

    public WatchingAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(WatchingAdapter adapter) {
        this.adapter = adapter;
    }

    public ArrayList<FavoriteItem> getList() {
        return this.list;
    }

    public void setList(ArrayList<FavoriteItem> list) {
        this.list = list;
    }

    public int getWatchingNum() {
        return this.watchingNum;
    }

    public void setWatchingNum(int watchingNum) {
        this.watchingNum = watchingNum;
    }

    public int getWaitingNum() {
        return this.waitingNum;
    }

    public void setWaitingNum(int waitingNum) {
        this.waitingNum = waitingNum;
    }
}
