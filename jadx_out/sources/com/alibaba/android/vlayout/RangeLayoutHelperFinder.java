package com.alibaba.android.vlayout;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public class RangeLayoutHelperFinder extends LayoutHelperFinder {
    private List<LayoutHelperItem> mLayoutHelperItems = new LinkedList();
    private List<LayoutHelper> mLayoutHelpers = new LinkedList();
    private List<LayoutHelper> mReverseLayoutHelpers = new LinkedList();
    private LayoutHelperItem[] mSortedLayoutHelpers = null;
    private Comparator<LayoutHelperItem> mLayoutHelperItemComparator = new Comparator<LayoutHelperItem>() { // from class: com.alibaba.android.vlayout.RangeLayoutHelperFinder.1
        @Override // java.util.Comparator
        public int compare(LayoutHelperItem layoutHelperItem, LayoutHelperItem layoutHelperItem2) {
            return layoutHelperItem.getStartPosition() - layoutHelperItem2.getStartPosition();
        }
    };

    @Override // com.alibaba.android.vlayout.LayoutHelperFinder
    protected List<LayoutHelper> reverse() {
        return this.mReverseLayoutHelpers;
    }

    @Override // com.alibaba.android.vlayout.LayoutHelperFinder
    public void setLayouts(List<LayoutHelper> list) {
        this.mLayoutHelpers.clear();
        this.mReverseLayoutHelpers.clear();
        this.mLayoutHelperItems.clear();
        if (list != null) {
            ListIterator<LayoutHelper> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                LayoutHelper next = listIterator.next();
                this.mLayoutHelpers.add(next);
                this.mLayoutHelperItems.add(new LayoutHelperItem(next));
            }
            while (listIterator.hasPrevious()) {
                this.mReverseLayoutHelpers.add(listIterator.previous());
            }
            List<LayoutHelperItem> list2 = this.mLayoutHelperItems;
            LayoutHelperItem[] layoutHelperItemArr = (LayoutHelperItem[]) list2.toArray(new LayoutHelperItem[list2.size()]);
            this.mSortedLayoutHelpers = layoutHelperItemArr;
            Arrays.sort(layoutHelperItemArr, this.mLayoutHelperItemComparator);
        }
    }

    @Override // com.alibaba.android.vlayout.LayoutHelperFinder
    protected List<LayoutHelper> getLayoutHelpers() {
        return this.mLayoutHelpers;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x003c  */
    /* JADX WARN: Code duplicated, block: B:39:? A[RETURN, SYNTHETIC] */
    @Override // com.alibaba.android.vlayout.LayoutHelperFinder
    public LayoutHelper getLayoutHelper(int i) {
        LayoutHelperItem layoutHelperItem;
        LayoutHelperItem[] layoutHelperItemArr = this.mSortedLayoutHelpers;
        if (layoutHelperItemArr == null || layoutHelperItemArr.length == 0) {
            return null;
        }
        int length = layoutHelperItemArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = (i2 + length) / 2;
            layoutHelperItem = this.mSortedLayoutHelpers[i3];
            if (layoutHelperItem.getStartPosition() > i) {
                length = i3 - 1;
            } else if (layoutHelperItem.getEndPosition() < i) {
                i2 = i3 + 1;
            } else if (layoutHelperItem.getStartPosition() <= i && layoutHelperItem.getEndPosition() >= i) {
                if (layoutHelperItem == null) {
                    return null;
                }
                return layoutHelperItem.layoutHelper;
            }
        }
        layoutHelperItem = null;
        if (layoutHelperItem == null) {
            return null;
        }
        return layoutHelperItem.layoutHelper;
    }

    static class LayoutHelperItem {
        LayoutHelper layoutHelper;

        LayoutHelperItem(LayoutHelper layoutHelper) {
            this.layoutHelper = layoutHelper;
        }

        public int getStartPosition() {
            return ((Integer) this.layoutHelper.getRange().getLower()).intValue();
        }

        public int getEndPosition() {
            return ((Integer) this.layoutHelper.getRange().getUpper()).intValue();
        }
    }
}
