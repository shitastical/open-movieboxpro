package com.movieboxpro.android.http;

import com.movieboxpro.android.view.listener.IViewController;
import retrofit2.Call;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StringCallBack<T> extends BaseCallback<String> {
    private Class<T> claz;

    public abstract boolean onParsed(BaseResponse<T> parsed);

    public StringCallBack(Class<T> claz) {
        this.claz = claz;
    }

    public StringCallBack(Call<String> call, Class<T> claz) {
        this(call, null, claz);
    }

    public StringCallBack(IViewController controller, Class<T> claz) {
        super(null, controller);
        this.claz = claz;
    }

    public StringCallBack(Call<String> call, IViewController controller, Class<T> claz) {
        super(call, controller);
        this.claz = claz;
    }

    @Override // com.movieboxpro.android.http.BaseCallback
    public boolean onResponse(String body) {
        BaseResponse<T> baseResponse = HttpUtils.parse(body, this.claz);
        return baseResponse != null && onParsed(baseResponse);
    }
}
