package com.dueeeke.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class ExtrModel implements Serializable {
    private String IDMovieImdb;
    private String IDSubtitle;
    private String ISO639;
    private String LanguageName;
    private String MovieName;
    private String SubAddDate;
    private String SubFileName;
    private String SubFormat;
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

    public String getIDSubtitle() {
        return this.IDSubtitle;
    }

    public void setIDSubtitle(String str) {
        this.IDSubtitle = str;
    }

    public String getIDMovieImdb() {
        return this.IDMovieImdb;
    }

    public void setIDMovieImdb(String str) {
        this.IDMovieImdb = str;
    }

    public String getISO639() {
        return this.ISO639;
    }

    public void setISO639(String str) {
        this.ISO639 = str;
    }

    public String getSubFormat() {
        return this.SubFormat;
    }

    public void setSubFormat(String str) {
        this.SubFormat = str;
    }
}
