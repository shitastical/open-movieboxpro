package com.owen.tvrecyclerview;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ItemEntries {
    private static final int MIN_SIZE = 10;
    private int mAdapterSize;
    private BaseLayoutManager.ItemEntry[] mItemEntries;
    private boolean mRestoringItem;

    private int sizeForPosition(int i) {
        int i2;
        int length = this.mItemEntries.length;
        while (length <= i) {
            length *= 2;
        }
        return (this.mRestoringItem || length <= (i2 = this.mAdapterSize)) ? length : i2;
    }

    private void ensureSize(int i) {
        BaseLayoutManager.ItemEntry[] itemEntryArr = this.mItemEntries;
        if (itemEntryArr == null) {
            BaseLayoutManager.ItemEntry[] itemEntryArr2 = new BaseLayoutManager.ItemEntry[Math.max(i, 10) + 1];
            this.mItemEntries = itemEntryArr2;
            Arrays.fill(itemEntryArr2, (Object) null);
        } else if (i >= itemEntryArr.length) {
            BaseLayoutManager.ItemEntry[] itemEntryArr3 = new BaseLayoutManager.ItemEntry[sizeForPosition(i)];
            this.mItemEntries = itemEntryArr3;
            System.arraycopy(itemEntryArr, 0, itemEntryArr3, 0, itemEntryArr.length);
            BaseLayoutManager.ItemEntry[] itemEntryArr4 = this.mItemEntries;
            Arrays.fill(itemEntryArr4, itemEntryArr.length, itemEntryArr4.length, (Object) null);
        }
    }

    public BaseLayoutManager.ItemEntry getItemEntry(int i) {
        BaseLayoutManager.ItemEntry[] itemEntryArr = this.mItemEntries;
        if (itemEntryArr == null || i >= itemEntryArr.length || i < 0) {
            return null;
        }
        return itemEntryArr[i];
    }

    public void putItemEntry(int i, BaseLayoutManager.ItemEntry itemEntry) {
        ensureSize(i);
        this.mItemEntries[i] = itemEntry;
    }

    public void restoreItemEntry(int i, BaseLayoutManager.ItemEntry itemEntry) {
        this.mRestoringItem = true;
        putItemEntry(i, itemEntry);
        this.mRestoringItem = false;
    }

    public int size() {
        BaseLayoutManager.ItemEntry[] itemEntryArr = this.mItemEntries;
        if (itemEntryArr != null) {
            return itemEntryArr.length;
        }
        return 0;
    }

    public void setAdapterSize(int i) {
        this.mAdapterSize = i;
    }

    public void invalidateItemLanesAfter(int i) {
        BaseLayoutManager.ItemEntry[] itemEntryArr = this.mItemEntries;
        if (itemEntryArr == null || i >= itemEntryArr.length) {
            return;
        }
        while (true) {
            BaseLayoutManager.ItemEntry[] itemEntryArr2 = this.mItemEntries;
            if (i >= itemEntryArr2.length) {
                return;
            }
            BaseLayoutManager.ItemEntry itemEntry = itemEntryArr2[i];
            if (itemEntry != null) {
                itemEntry.invalidateLane();
            }
            i++;
        }
    }

    public void clear() {
        BaseLayoutManager.ItemEntry[] itemEntryArr = this.mItemEntries;
        if (itemEntryArr != null) {
            Arrays.fill(itemEntryArr, (Object) null);
        }
    }

    void offsetForRemoval(int i, int i2) {
        BaseLayoutManager.ItemEntry[] itemEntryArr = this.mItemEntries;
        if (itemEntryArr == null || i >= itemEntryArr.length) {
            return;
        }
        int i3 = i + i2;
        ensureSize(i3);
        BaseLayoutManager.ItemEntry[] itemEntryArr2 = this.mItemEntries;
        System.arraycopy(itemEntryArr2, i3, itemEntryArr2, i, (itemEntryArr2.length - i) - i2);
        BaseLayoutManager.ItemEntry[] itemEntryArr3 = this.mItemEntries;
        Arrays.fill(itemEntryArr3, itemEntryArr3.length - i2, itemEntryArr3.length, (Object) null);
    }

    void offsetForAddition(int i, int i2) {
        BaseLayoutManager.ItemEntry[] itemEntryArr = this.mItemEntries;
        if (itemEntryArr == null || i >= itemEntryArr.length) {
            return;
        }
        int i3 = i + i2;
        ensureSize(i3);
        BaseLayoutManager.ItemEntry[] itemEntryArr2 = this.mItemEntries;
        System.arraycopy(itemEntryArr2, i, itemEntryArr2, i3, (itemEntryArr2.length - i) - i2);
        Arrays.fill(this.mItemEntries, i, i3, (Object) null);
    }
}
