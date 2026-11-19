package com.movieboxpro.android.model;

/* JADX INFO: loaded from: classes5.dex */
public class BaseResponse<T> {
    private int code;
    private T data;
    private String msg;
    private int super_child_mode;

    public int getSuper_child_mode() {
        return this.super_child_mode;
    }

    public void setSuper_child_mode(int super_child_mode) {
        this.super_child_mode = super_child_mode;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
