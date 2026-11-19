package com.movieboxpro.android.http;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* JADX INFO: compiled from: ApiService3.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J>\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0004H'JV\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0004H'JV\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0004H'J$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0001\u0010\u0010\u001a\u00020\u0011H'J\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004H'J\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00142\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004H'J\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004H'J&\u0010\u0016\u001a\u0014\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0017\u0018\u00010\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004H'¨\u0006\u0018À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/http/ApiService3;", "", "translate", "Lio/reactivex/Observable;", "", "url", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, FirebaseAnalytics.Param.CONTENT, "translateMovie", CmcdConfiguration.KEY_SESSION_ID, "lang", "language", "translateTv", "translateSubtitleStream", "Lokhttp3/ResponseBody;", "body", "Lokhttp3/RequestBody;", "download", "downloadCall", "Lretrofit2/Call;", "download2", "downloadString", "Lretrofit2/Response;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ApiService3 {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);

    @Streaming
    @GET
    Observable<ResponseBody> download2(@Url String url);

    @Streaming
    @GET
    Call<ResponseBody> downloadCall(@Url String url);

    @Streaming
    @GET
    Observable<Response<String>> downloadString(@Url String url);

    @FormUrlEncoded
    @POST
    Observable<String> translate(@Url String url, @Query(TypedValues.TransitionType.S_FROM) String from, @Query(TypedValues.TransitionType.S_TO) String to, @Field(FirebaseAnalytics.Param.CONTENT) String content);

    @GET
    Observable<String> translateMovie(@Url String url, @Query(TypedValues.TransitionType.S_FROM) String from, @Query(TypedValues.TransitionType.S_TO) String to, @Query("movie_sid") String sid, @Query("lang") String lang, @Query("language") String language);

    @Streaming
    @POST
    Observable<ResponseBody> translateSubtitleStream(@Url String url, @Body RequestBody body);

    @GET
    Observable<String> translateTv(@Url String url, @Query(TypedValues.TransitionType.S_FROM) String from, @Query(TypedValues.TransitionType.S_TO) String to, @Query("tv_sid") String sid, @Query("lang") String lang, @Query("language") String language);
}
