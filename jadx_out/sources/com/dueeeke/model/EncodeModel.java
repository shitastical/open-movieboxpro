package com.dueeeke.model;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class EncodeModel implements Comparable<EncodeModel> {
    private List<String> code;
    private String language;

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public List<String> getCode() {
        return this.code;
    }

    public void setCode(List<String> list) {
        this.code = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(EncodeModel encodeModel) {
        if (this.language.length() <= 0 || encodeModel.getLanguage().length() <= 0) {
            return 0;
        }
        return this.language.charAt(0) > encodeModel.getLanguage().charAt(0) ? 1 : -1;
    }
}
