package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class LanguageModel {
    private String fullLanguage;
    private String language;
    private boolean select;

    public String getFullLanguage() {
        return this.fullLanguage;
    }

    public void setFullLanguage(String fullLanguage) {
        this.fullLanguage = fullLanguage;
    }

    public LanguageModel(String language, String fullLanguage) {
        this.language = language;
        this.fullLanguage = fullLanguage;
    }

    public LanguageModel(String language, String fullLanguage, boolean select) {
        this.language = language;
        this.select = select;
        this.fullLanguage = fullLanguage;
    }

    public LanguageModel(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
