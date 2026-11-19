package com.gavin.com.library;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ClickInfo {
    public int mBottom;
    public List<DetailInfo> mDetailInfoList;
    public int mGroupId = -1;

    public ClickInfo(int i) {
        this.mBottom = i;
    }

    public ClickInfo(int i, List<DetailInfo> list) {
        this.mBottom = i;
        this.mDetailInfoList = list;
    }

    public static class DetailInfo {
        public int bottom;
        public int id;
        public int left;
        public int right;
        public int top;

        public DetailInfo(int i, int i2, int i3, int i4, int i5) {
            this.id = i;
            this.left = i2;
            this.right = i3;
            this.top = i4;
            this.bottom = i5;
        }
    }
}
