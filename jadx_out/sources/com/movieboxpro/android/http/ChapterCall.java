package com.movieboxpro.android.http;

import retrofit2.Call;

/* JADX INFO: loaded from: classes5.dex */
public class ChapterCall {
    public String bookid;
    public Call<String> call;
    public int chaper;
    public String sourceid;

    public ChapterCall(Call<String> call, String bookid, String sourceid, int chaper) {
        this.call = call;
        this.bookid = bookid;
        this.sourceid = sourceid;
        this.chaper = chaper;
    }
}
