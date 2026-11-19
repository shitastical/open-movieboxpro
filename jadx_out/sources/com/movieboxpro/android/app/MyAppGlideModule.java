package com.movieboxpro.android.app;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.movieboxpro.android.model.ImageHost;
import com.movieboxpro.android.utils.DomainReplacementInterceptor;
import com.movieboxpro.android.utils.PrefsUtils;
import java.io.InputStream;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes5.dex */
public class MyAppGlideModule extends AppGlideModule {
    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        builder.setDefaultRequestOptions(RequestOptions.formatOf(DecodeFormat.PREFER_RGB_565));
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(Context context, Glide glide, Registry registry) {
        String string = PrefsUtils.getInstance().getString("image_host", "");
        if (TextUtils.isEmpty(string)) {
            registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
            return;
        }
        ImageHost imageHost = (ImageHost) JSONObject.parseObject(string, ImageHost.class);
        if (imageHost.getDefaultHost()) {
            registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
        } else {
            registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient().newBuilder().addInterceptor(new DomainReplacementInterceptor(imageHost.getOriginal(), imageHost.getThumb())).build()));
        }
    }
}
