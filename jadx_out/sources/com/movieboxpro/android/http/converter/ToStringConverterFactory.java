package com.movieboxpro.android.http.converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes5.dex */
public class ToStringConverterFactory extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return new Converter<ResponseBody, String>(this) { // from class: com.movieboxpro.android.http.converter.ToStringConverterFactory.1
                @Override // retrofit2.Converter
                public String convert(ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        }
        return null;
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return new Converter<String, RequestBody>(this) { // from class: com.movieboxpro.android.http.converter.ToStringConverterFactory.2
                @Override // retrofit2.Converter
                public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(ToStringConverterFactory.MEDIA_TYPE, value);
                }
            };
        }
        return null;
    }
}
