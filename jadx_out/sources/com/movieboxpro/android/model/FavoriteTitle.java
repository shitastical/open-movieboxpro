package com.movieboxpro.android.model;

import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class FavoriteTitle extends BaseNode {
    private boolean waiting;

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return null;
    }

    public FavoriteTitle(boolean waiting) {
        this.waiting = waiting;
    }

    public boolean isWaiting() {
        return this.waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
}
