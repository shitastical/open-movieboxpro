package com.movieboxpro.android.http;

/* JADX INFO: loaded from: classes5.dex */
public class API {
    public static final String ONLINE_PARAMS_URL = "https://www.mini-install.com/api/index/get_param_encryption_id.html?id=43";
    public static String REAL_MOVIE_HOST = "";
    public static String TRANSLATE_URL = "https://srt.shegu.net/api/srttrans2/index/";
    public static String MOVIE_HOST = "https://mbpssl.shegu.net";
    public static String TRANSLATE_V3_URL = MOVIE_HOST + "/api/srttransv3/index";

    public static class Common {
        public static final String ADD_CAPTCHA = "Add_captcha";
        public static final String ADD_QRCODE = "Add_qrcode";
        public static final String CODE_LOGIN_CAPTCHA = "Login_captcha_v2";
        public static final String DELETE_PLAYING_STATUS = "Family_playing_device_delete_v2";
        public static final String DEVICELIST = "Device_list";
        public static final String HOME_LIST = "Home_list_type_v5";
        public static final String LOGIN_QRCODE = "Login_qrcode_v2";
        public static final String MOVIE_FEEDBACK = "Movie_feedback";
        public static final String MOVIE_FEEDBACL_TYPE = "Movie_feedback_type";
        public static final String ORDERLIST = "Orderlist";
        public static final String PLAYING_FEEDBACK = "Family_playing_feedback";
        public static final String PLAY_LIST = "Playlists_list_v4";
        public static final String SCANQRCODE = "Scan_qrcode";
        public static final String SUBTITLE_CHECK_MD5 = "Srt_md5_check";
        public static final String TEST_NETWORK = "Test_network_url_v2";
    }

    public static class Movie {
        public static final String MOVE_COLLECT = "Movie_collect";
        public static final String MOVE_DETAIL = "Movie_detail";
        public static final String MOVE_DOWNLAOD = "Movie_downloadurl_v4";
        public static final String MOVE_LIST = "Movie_list_v2";
        public static final String MOVIE_DOWNLAOD_URL = "Movie_downloadurl2";
        public static final String MOVIE_DOWNLOAD = "Movie_download";
        public static final String MOVIE_PLAY = "Movie_play";
        public static final String MOVIE_PLAY_PROGRESS = "Movie_play_progress";
        public static final String MOVIE_SRTLIST = "Movie_srt_list";
        public static final String MOVIE_SRTLIST_V2 = "Movie_srt_list_v3";
        public static final String MOVIE_SRTSELECT = "Movie_srt_select";
        public static final String MOVIE_SRT_AUTO = "Movie_srt_auto";
        public static final String TOP_LIST_MOVIE = "Top_list_movie";
        public static final String UPLOAD_MOVIE_SRT = "Upload_movie_srt_start";
        public static final String UPLOAD_MOVIE_SRTS = "Upload_movie_srt";
    }

    public static class SETTING {
        public static final String MOVIERECORD = "Movie_play_record";
    }

    public static class Search {
        public static final String AUTOCOMPLATE = "Autocomplate2";
        public static final String MY_SEARCH_RECORD = "My_search_record";
        public static final String SEARCH = "Search5";
        public static final String SEARCH_HOT = "Search_hot";
    }

    public static class Tv {
        public static final String TOP_LIST_TV = "Top_list_tv";
        public static final String TV_COLLECT = "TV_collect";
        public static final String TV_DETAIL = "TV_detail_v2";
        public static final String TV_DOWNLAODURL = "TV_downloadurl_v4";
        public static final String TV_DOWNLAOD_URL = "TV_downloadurl2";
        public static final String TV_DOWNLOAD = "Movie_download";
        public static final String TV_DOWNLOADURL = "TV_downloadurl";
        public static final String TV_EPISODE = "TV_episode";
        public static final String TV_GUIDE = "TV_guide";
        public static final String TV_LIST = "TV_list_v2";
        public static final String TV_PLAY = "TV_play";
        public static final String TV_PLAY_PROGRESS = "TV_play_progress";
        public static final String TV_SRTLIST = "TV_srt_list";
        public static final String TV_SRTLIST_V2 = "TV_srt_list_v3";
        public static final String TV_SRTSELECT = "TV_srt_select";
        public static final String TV_SRT_AUTO = "TV_srt_auto";
        public static final String UPLOAD_TV_SRT = "Upload_tv_srt_start";
        public static final String UPLOAD_TV_SRTS = "Upload_tv_srt";
    }

    public static class USER {
        public static final String BIND_THIRDPART = "Bind_thirdpart";
        public static final String DEVICE = "Device_v3";
        public static final String INVITE_ACTIVE = "Invite_active";
        public static final String LOGIN = "Login";
        public static final String LOGIN_OUT = "Login_out";
        public static final String LOGIN_THIRDPART = "Login_thirdpart";
        public static final String PROFILE = "Profile";
        public static final String RECOMMEND_USERNAME = "Recommend_username";
        public static final String REGISTER = "Register";
        public static final String REGISTER2 = "Register_code";
        public static final String REGISTER_CODE = "Register_is_need_code";
        public static final String USERINFO = "Userinfo";
    }
}
