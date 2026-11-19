package com.dl7.player.model;

/* JADX INFO: loaded from: classes4.dex */
public class ExtrModel {
    private String LanguageName;
    private String MovieName;
    private String SubAddDate;
    private String SubFileName;
    private String ZipDownloadLink;
    private boolean isSelect;

    public String getMovieName() {
        return this.MovieName;
    }

    public void setMovieName(String str) {
        this.MovieName = str;
    }

    public String getLanguageName() {
        return this.LanguageName;
    }

    public void setLanguageName(String str) {
        this.LanguageName = str;
    }

    public String getSubAddDate() {
        return this.SubAddDate;
    }

    public void setSubAddDate(String str) {
        this.SubAddDate = str;
    }

    public String getSubFileName() {
        return this.SubFileName;
    }

    public void setSubFileName(String str) {
        this.SubFileName = str;
    }

    public String getZipDownloadLink() {
        return this.ZipDownloadLink;
    }

    public void setZipDownloadLink(String str) {
        this.ZipDownloadLink = str;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }
}
