package com.dueeeke.model;

/* JADX INFO: loaded from: classes4.dex */
public class LanguageModel {
    public boolean isSelected;
    public String language;

    public LanguageModel() {
    }

    public LanguageModel(String str) {
        this.language = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
