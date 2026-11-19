package com.movieboxpro.android.http;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BaseResponse<T> {
    public int code;
    public T data;
    public List<T> list;
    public String msg;
    public int success = 0;
}
