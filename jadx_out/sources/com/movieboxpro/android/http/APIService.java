package com.movieboxpro.android.http;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.alibaba.fastjson.JSONObject;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.v2.ApiV2RequestInterceptor;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.service.FileDownloadService;
import io.reactivex.Observable;
import java.util.List;
import ly.count.android.sdk.ModuleDeviceId;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* JADX INFO: loaded from: classes5.dex */
public interface APIService {
    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> ActorDetail(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("actor_id") String actorId);

    @POST("api/{module}")
    Observable<String> Add_captcha(@Path(ApiV2RequestInterceptor.MODULE) String module);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Add_qrcode(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field(ModuleDeviceId.PREF_KEY) String openudid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Advertisement(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("adv_id") String id);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Advertisement_notify(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("id") String id);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Autocomplate(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("keyword") String keyword, @Field("pagelimit") String pagelimit);

    @FormUrlEncoded
    @POST("api/{module}")
    Call<String> Autocomplate2(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("keyword") String keyword, @Field("pagelimit") String pagelimit);

    @POST("api/{module}")
    Observable<String> Cat_list(@Path(ApiV2RequestInterceptor.MODULE) String module);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Device(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("firebase_token") String firebaseToken, @Field("uid") String uid, @Field("device_name") String device_name, @Field("device_model") String device_model, @Field("devicetoken") String devicetoken, @Field("udid") String udid, @Field("open_udid") String open_udid, @Field("appid") String appid, @Field(TvContractCompat.PARAM_START_TIME) String start_time, @Field("receive") String receive, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Device_list(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid);

    @POST("api/{module}")
    Observable<String> Encoding_list(@Path(ApiV2RequestInterceptor.MODULE) String module);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Home_list(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("type") String type, @Field(Constant.Prefs.PRIVATE_MODE) int private_mode, @Field("page") String page, @Field("pagelimit") String pagelimit, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Invite_active(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type, @Field("email") String email, @Field("invite_code") String invite_code, @Field(Scopes.OPEN_ID) String openid);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> MovieRecord(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("type") String type, @Field("mid") String mid, @Field("page") String page, @Field("pagelimit") String pagelimit);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_actor(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("mid") String mid, @Field("page") int page, @Field("pagelimit") int pagelimit);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_detail(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mid") String mid, @Field("lang") String lang, @Field("oss") String oss, @Field("group") String group);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_download(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mid") String mid);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_feedback(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("box_type") int box_type, @Field("mid") String mid, @Field("state") int state, @Field(CmcdConfiguration.KEY_SESSION_ID) String sid, @Field("ftid") int ftid, @Field("text") String text, @Field(DealWatchNextService.SEASON) int season, @Field(DealWatchNextService.EPISODE) int episode);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_feedback2(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("box_type") int box_type, @Field("mid") String mid, @Field("state") int state, @Field(CmcdConfiguration.KEY_SESSION_ID) String sid, @Field("ftid") String ftid, @Field("text") String text, @Field(DealWatchNextService.SEASON) int season, @Field(DealWatchNextService.EPISODE) int episode, @Field("subject") String subject);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_feedback_type(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("state") int state);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_list(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("year") String year, @Field(CmcdConfiguration.KEY_CONTENT_ID) String cid, @Field("orderby") String orderby, @Field("page") String page, @Field("pagelimit") String pagelimit, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_play(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mid") String mid, @Field("seconds") String seconds, @Field("mmfid") String mmfid, @Field("open_udid") String open_udid);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_play_progress2(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mid") String mid, @Field("mp4_id") String mp4_id, @Field("seconds") String seconds, @Field("over") int over, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_srt_auto(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("mid") String mid, @Field("lang") String lang, @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_srt_list(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mid") String mid, @Field("lang") String lang, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_srt_list_v2(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mid") String mid, @Field("fid") String fid, @Field("lang") String lang, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Movie_srt_select(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field(CmcdConfiguration.KEY_SESSION_ID) String sid, @Field("mid") String mid, @Field("type") String type, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Moviecollect(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mids") JSONObject mids, @Field("tids") JSONObject tids, @Field("type") String type, @Field("page") String page, @Field("pagelimit") String pagelimit);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> MySearch_Record(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type, @Field("uid") String uid, @Field("page") String page, @Field("pagelimit") String pagelimit, @Field("id") String id, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Orderlist(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Playlists_collect(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("lid") String lid, @Field("type") String type, @Field("app_version") String appVersion);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Playlists_compilations_detail(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field(CmcdConfiguration.KEY_CONTENT_ID) String cid, @Field("page") int page, @Field("pagelimit") int pagelimit);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Playlists_create(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("name") String name, @Field("desc") String desc, @Field("isshare") String isShare, @Field("lid") String lid, @Field("videos") String videos, @Field("app_version") String appVersion);

    @POST
    Observable<String> Playlists_create(@Url String url, @Body RequestBody param);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Playlists_delete(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type, @Field("uid") String uid, @Field("lid") String lid, @Field("app_version") String appVersion);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Playlists_get(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("lid") String lid, @Field("page") int page, @Field("pagelimit") int pageLimit, @Field("app_version") String appVersion);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Playlists_list(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("type") String type, @Field("page") int page, @Field("pagelimit") int pageLimit, @Field("app_version") String appVersion);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Playlists_video_add(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("lid") String lid, @Field("box_type") int box_type, @Field("mid") String mid);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Recommend_username(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("username") String username, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Register(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("username") String username, @Field("password") String password, @Field("email") String email, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Register2(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("username") String username, @Field("password") String password, @Field("email") String email, @Field("invite_code") String invite_code, @Field("app_version") String app_version);

    @POST("api/{module}")
    Observable<String> Register_NeedCode(@Path(ApiV2RequestInterceptor.MODULE) String module);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Scan_qrcode(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field(ModuleDeviceId.PREF_KEY) String openudid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Search(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type, @Field("keyword") String keyword, @Field("uid") String uid, @Field("page") String page, @Field("pagelimit") String pagelimit);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> SearchFilter(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type, @Field("keyword") String keyword, @Field("uid") String uid, @Field("page") String page, @Field("pagelimit") String pagelimit, @Field("sort") String sort, @Field("year") String year, @Field(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING) String content_rating, @Field("quality") String quality, @Field("genr") String genr, @Field("country") String country, @Field(Constant.Prefs.PRIVATE_MODE) int private_mode);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Search_Hot(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type, @Field("pagelimit") String pagelimit);

    @POST
    @Multipart
    Observable<String> Srt_convert_encoding(@Url String url, @Part List<MultipartBody.Part> parts, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Srt_md5_check(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type, @Field(FileDownloadService.PARAMS_KEY_MD5) String md5);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Srt_vote(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field(CmcdConfiguration.KEY_SESSION_ID) String sid, @Field("type") int type, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Srt_vote_data(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field(CmcdConfiguration.KEY_SESSION_ID) String sid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> TV_downloadurl(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("tid") String tid, @Field(DealWatchNextService.SEASON) String season, @Field(DealWatchNextService.EPISODE) String episode, @Field("oss") String oss, @Field("group") String group);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> TV_episode(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("tid") String tid, @Field(DealWatchNextService.SEASON) String season, @Field("year") String year, @Field("display_all") String display_all);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> TV_episode(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("tid") String tid, @Field(DealWatchNextService.SEASON) String season, @Field("year") String year, @Field("uid") String uid, @Field("display_all") String display_all);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> TV_guide(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("day") String day, @Field("pagelimit") int pagelimit, @Field("page") int page);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> TV_play_progress(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("tid") String tid, @Field("mp4_id") String mp4_id, @Field(DealWatchNextService.SEASON) String season, @Field(DealWatchNextService.EPISODE) String episode, @Field("seconds") String seconds, @Field("over") int over, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> TV_srt_list_v2(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("tid") String tid, @Field("fid") String fid, @Field(DealWatchNextService.SEASON) String season, @Field(DealWatchNextService.EPISODE) String episode, @Field("lang") String lang, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Top_list(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("box_type") int box_type);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Top_list_movie(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("id") String id, @Field("page") int page, @Field("pagelimit") int pageLimit);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Tv_actor(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("tid") String mid, @Field("page") int page, @Field("pagelimit") int pagelimit);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Tv_detail(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("tid") String tid, @Field("lang") String lang, @Field("display_all") String display_all);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Tv_downloadurl(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("tid") String tid, @Field(DealWatchNextService.SEASON) int season, @Field(DealWatchNextService.EPISODE) int episode);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Tv_play(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("tid") String tid, @Field(DealWatchNextService.SEASON) String season, @Field(DealWatchNextService.EPISODE) String episode, @Field("seconds") String seconds, @Field("tmfid") String tmfid, @Field("app_version") String app_version, @Field("open_udid") String open_udid);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Tv_srt_auto(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("tid") String mid, @Field(DealWatchNextService.SEASON) int season, @Field(DealWatchNextService.EPISODE) int episode, @Field("lang") String lang, @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Tv_srt_list(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("tid") String tid, @Field(DealWatchNextService.SEASON) String season, @Field(DealWatchNextService.EPISODE) String episode, @Field("lang") String lang, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Tv_srt_select(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("tid") String tid, @Field(DealWatchNextService.SEASON) String season, @Field(DealWatchNextService.EPISODE) String episode, @Field(CmcdConfiguration.KEY_SESSION_ID) String sid, @Field("type") String type, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> UploadSrt_DELAY_INFO(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field(CmcdConfiguration.KEY_SESSION_ID) String sid, @Field("fid") String fid, @Field("delay") int delaySeconds, @Field("app_version") String appVersion);

    @POST
    @Multipart
    Observable<String> Upload_Request(@Url String url, @Part List<MultipartBody.Part> parts, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("api/{module}")
    Call<String> Userinfo(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("open_udid") String open_udid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Userinfo2(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("open_udid") String open_udid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> VideoList(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("year") String year, @Field(CmcdConfiguration.KEY_CONTENT_ID) String cid, @Field("orderby") String orderby, @Field("rating") String rating, @Field("quality") String quality, @Field("country") String country, @Field("imdb_rating_section") String imdb_rating_section, @Field("tomato_meter_section") String tomato_meter_section, @Field("page") String page, @Field("pagelimit") String pagelimit, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> Year_list(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> bindthirdpart(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("action") String action, @Field("type") String type, @Field("accesstoken") String accesstoken, @Field("name") String name, @Field(Scopes.OPEN_ID) String openid, @Field("nick") String nick, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> checkCodeLogin(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field(ModuleDeviceId.PREF_KEY) String openudid, @Field("code") String code);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> deletePlayingStatus(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("delete_open_udid") String open_udid, @Field("uid") String uid);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url);

    @Streaming
    @GET
    Observable<ResponseBody> downloadObservable(@Url String url);

    @GET
    Observable<String> get(@Url String url);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> getAudioTracks(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mid") String mid, @Field("tid") String tid, @Field(DealWatchNextService.SEASON) int season, @Field(DealWatchNextService.EPISODE) int episode);

    @Streaming
    @GET
    Observable<ResponseBody> getOpenSubtitle(@Url String url);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> getUserInfo(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("open_udid") String open_udid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> login(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("username") String username, @Field("password") String password, @Field("open_udid") String open_udid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> login_out(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("open_udid") String open_udid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> loginthirdpart(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("type") String type, @Field(Scopes.OPEN_ID) String openid, @Field("open_udid") String open_udid);

    @FormUrlEncoded
    @POST("api/{module}")
    Call<String> phoneregister(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("phone") String phone, @Field("appid") String appid, @Field("app_version") String app_version);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> playingFeedback(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("mid") String mid, @Field("open_udid") String openUdid, @Field("box_type") int boxType, @Field(DealWatchNextService.SEASON) int season, @Field(DealWatchNextService.EPISODE) int episode, @Field("device_name") String deviceName, @Field("device_model") String deviceModel);

    @POST
    Observable<String> request(@Url String url, @Body RequestBody param);

    @POST("api/{module}")
    Observable<String> simple(@Path(ApiV2RequestInterceptor.MODULE) String module);

    @POST
    Observable<String> sortFavorite(@Url String url, @Body RequestBody param);

    @Streaming
    @GET
    Observable<Response<String>> strlist(@Url String url);

    @Streaming
    @GET
    Observable<ResponseBody> strlist2(@Url String url);

    @FormUrlEncoded
    @POST
    Observable<String> translate(@Url String url, @Query(TypedValues.TransitionType.S_FROM) String from, @Query(TypedValues.TransitionType.S_TO) String to, @Field(FirebaseAnalytics.Param.CONTENT) String content);

    @POST
    @Multipart
    Call<String> uploadAvatar(@Url String url, @Part List<MultipartBody.Part> parts, @Part MultipartBody.Part avatar);

    @POST
    @Multipart
    Observable<String> uploadAvatar2(@Url String url, @Part List<MultipartBody.Part> parts, @Part MultipartBody.Part avatar);

    @POST
    @Multipart
    Call<String> uploadSubttitle(@Url String url, @Part("data") String data, @Part("appid") String appid, @Part("version") int version, @Part MultipartBody.Part subtitle);

    @FormUrlEncoded
    @POST("api/{module}")
    Observable<String> userprofile(@Path(ApiV2RequestInterceptor.MODULE) String module, @Field("uid") String uid, @Field("type") String type, @Field("newname") String newname, @Field("newpw") String newpw, @Field("oldpw") String oldpw, @Field("nickname") String nickname, @Field("email") String email, @Field("app_version") String app_version);
}
