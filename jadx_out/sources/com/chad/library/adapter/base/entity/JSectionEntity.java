package com.chad.library.adapter.base.entity;

/* JADX INFO: loaded from: classes4.dex */
public abstract class JSectionEntity implements SectionEntity {
    @Override // com.chad.library.adapter.base.entity.SectionEntity, com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        if (isHeader()) {
            SectionEntity.Companion companion = SectionEntity.INSTANCE;
            return -99;
        }
        SectionEntity.Companion companion2 = SectionEntity.INSTANCE;
        return -100;
    }
}
