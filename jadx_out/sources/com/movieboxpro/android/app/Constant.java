package com.movieboxpro.android.app;

import android.os.Environment;
import com.movieboxpro.android.utils.HexDump;
import com.movieboxpro.android.utils.MD5Util;
import com.movieboxpro.androidtv.BuildConfig;
import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public class Constant {
    public static final String CHANNEL_KEY = "UMENG_CHANNEL";
    public static final int CHECK_ONLINE_SCREEN_REQUEST_CODE = 100;
    public static final String DIR;
    public static final String[] DIRS;
    public static final String[] DIRS_CLEAR;
    public static final String DIR_CACHE;
    public static final String DIR_CONFIG;
    public static final String DIR_CROP;
    public static final String DIR_DOLOAD_SUBTITLE;
    public static final String DIR_DOWNLOAD;
    public static final String DIR_DOWNLOAD_MOVIE_SUBTITLE;
    public static final String DIR_DOWNLOAD_TEST;
    public static final String DIR_DOWNLOAD_TEST_SPEED;
    public static final String DIR_DOWNLOAD_TV_SUBTITLE;
    public static final String DIR_FONT;
    public static final String DIR_LOG;
    public static final String DIR_NOVEL;
    public static final String DIR_PICTURE;
    public static final String DIR_SUBTITLE;
    public static final String DIR_THUMB;
    public static final String DIR_TRANS_CODE_SUBTITLE;
    public static final String DIR_TXT;
    public static final String DIR_UPLOAD_MOVIE_SUBTITLE;
    public static final String DIR_UPLOAD_SUBTITLE;
    public static final String DIR_UPLOAD_TV_SUBTITLE;
    public static final String DIR_VIDEO;
    public static final String DIR_WEB_DOWNLOAD_SUBTITLE;
    public static final String FFMPEG_DIR_DOWNLOAD;
    public static final String FLURRY_API_KEY = "KKXQCPKN3Z6YTC3K9P33";
    public static final int HTTP_PORT = 8888;
    public static final int MAX_SCREEN_CODE = -88;
    public static final String SELECT_SERVER_URL = "select_server_url";
    public static final String SUFFIX_ZIP = ".zip";

    public static final class ACTION {
        public static final String DOWNLOAD_FILE = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_FILE";
        public static final String DOWNLOAD_FILE_COMPLETE = "com.movieboxpro.androidtv.DOWNLOAD_FILE_COMPLETE";
        public static final String DOWNLOAD_FILE_FAILURE = "com.movieboxpro.androidtv.DOWNLOAD_FILE_FAILURE";
        public static final String DOWNLOAD_FILE_PROGRESS = "com.movieboxpro.androidtv.DOWNLOAD_FILE_PROGRESS";
        public static final String DOWNLOAD_FILE_STARTED = "com.movieboxpro.androidtv.DOWNLOAD_FILE_STARTED";
        public static final String DOWNLOAD_MOVIE_COMPLETE = "com.movieboxpro.androidtv.DOWNLOAD_MOVIE_COMPLETE";
        public static final String DOWNLOAD_MOVIE_DELETE = "com.movieboxpro.androidtv.DOWNLOAD_MOVIE_DELETE";
        public static final String DOWNLOAD_MOVIE_FAILURE = "com.movieboxpro.androidtv.DOWNLOAD_MOVIE_FAILURE";
        public static final String DOWNLOAD_MOVIE_PAUSED = "com.movieboxpro.androidtv.DOWNLOAD_MOVIE_PAUSED";
        public static final String DOWNLOAD_MOVIE_PROGRESS = "com.movieboxpro.androidtv.DOWNLOAD_MOVIE_PROGRESS";
        public static final String DOWNLOAD_MOVIE_STARTED = "com.movieboxpro.androidtv.DOWNLOAD_MOVIE_STARTED";
        public static final String MOVIE_ALLDOWNLOAD = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_ALLMOVIE";
        public static final String MOVIE_DELETE = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_DELETE";
        public static final String MOVIE_DOWNLOAD = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_MOVIE";
        public static final String MOVIE_ERROR = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_ERROR";
        public static final String MOVIE_PAUSED = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_PAUSED";
        public static final String MOVIE_PAUSEDALL = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_PAUSEDALL";
        public static final String MOVIE_STARTALL = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_STARTALL";
        public static final String MOVIE_TV_DELETE = "com.movieboxpro.androidtv.ACTION_DOWNLOAD_TV_DELETE";
    }

    public static final class Bugly {
    }

    public static final class Download {
        public static final String PARAMS_KEY_EPISODE = "params_key_episode";
        public static final String PARAMS_KEY_MOVIE = "params_key_movie";
        public static final String PARAMS_KEY_MOVIE_ID = "params_key_movie_id";
        public static final String PARAMS_KEY_MOVIE_TYPE = "params_key_movie_type";
        public static final String PARAMS_KEY_PATH = "params_key_path";
        public static final String PARAMS_KEY_PROGRESS = "params_key_progress";
        public static final String PARAMS_KEY_REASON = "params_key_reason";
        public static final String PARAMS_KEY_SEASON = "params_key_season";
        public static final String PARAMS_KEY_SIZE = "params_key_size";
        public static final String PARAMS_KEY_TYPE = "params_key_type";
        public static final int STATUS_DELETE = 5;
        public static final int STATUS_DOWNLOADED = 2;
        public static final int STATUS_DOWNLOADING = 1;
        public static final int STATUS_ERROR = 4;
        public static final int STATUS_IDLE = 0;
        public static final int STATUS_PASUED = 3;
    }

    public static final class Prefs {
        public static final String ACCOUNT_LIST = "account_list";
        public static final String AUDIO_DELAY = "audio_delay";
        public static final String AUDIO_SYN_TIME = "audio_syn_time";
        public static final String AUTO_PLAY_NEXT = "auto_play_next";
        public static final String AUTO_SELECT_SUBTITLE = "auto_select_subtitle";
        public static final String CHAPTER_PRICE = "chapter_price";
        public static final String CHILD_MODE = "child_mode";
        public static final String CREATE_CHANNEL = "create_channel";
        public static final String DARK_MODE = "dark_mode";
        public static final float DEFAULT_CHAPTER_PRICE = 0.1f;
        public static final String DOWNLOAD_CONCURRENT = "download_concurrent";
        public static final String DOWNLOAD_DEVICE_ID = "download_device_id";
        public static final String DOWNLOAD_DEVICE_PATH = "download_device_path";
        public static final String DOWNLOAD_PATH = "download_path";
        public static final String ENTER_BACK_TIME = "enter_back_time";
        public static final String FLIP_STYLE = "flipStyle";
        public static final int FLIP_STYLE_NONE = 0;
        public static final int FLIP_STYLE_OVERFLOW = 2;
        public static final int FLIP_STYLE_SCROLL = 3;
        public static final int FLIP_STYLE_SIMULATE = 1;
        public static final String HIDE_DOWNLOAD = "hide_download";
        public static final String HIDE_MOVIE_LIST = "hide_movie_list";
        public static final String HLS_CACHE_DIR = "hlsCache";
        public static final String HW_DECODER = "hw_decoder";
        public static final String IMAGE_HOST = "image_host";
        public static final String ISNIGHT = "isNight";
        public static final String IS_CHOOSE_ORIGIN = "is_choose_origin";
        public static final String IS_FIRST_OPEN = "is_first_open";
        public static final String IS_FIRST_SHORTCUT = "is_first_shorcut";
        public static final String LANGUAGE = "language";
        public static final String LAST_PLAY_QUALITY = "last_play_quality";
        public static final String LAST_SELECT_QUA = "last_select_qua";
        public static final String M3U8_CACHE_DIR = "m3u8Cache";
        public static final String MOVIE_COUNTRY = "movie_country_list";
        public static final String NETWORK_GROUP = "network_group";
        public static final String NETWORK_STATE = "network_state";
        public static final String OPEN_SUBTITLE_BG = "open_subtitle_bg";
        public static final String PLAYER_ENGINE = "player_engine2";
        public static final String PLAY_WITH_OTHER_PLAYER = "play_with_other_player";
        public static final String PREVIEWPROGRAM_IDS = "previewprogram_ids";
        public static final String PRIVATE_MODE = "private_mode";
        public static final String QUICK_SWITCH_ACCOUNT = "quick_switch_account";
        public static final String REMEMBER_SELECTED_ORG = "remember_selected_org";
        public static final String SCREEN_SCALE = "screen_scale";
        public static final String SEARCH_FILTER_ALL = "search_filter_all";
        public static final String SEARCH_FILTER_MOVIE = "search_filter_movie";
        public static final String SEARCH_FILTER_TV = "search_filter_tv";
        public static final String SETTING = "setting";
        public static final String SHOW_ADVERT_FRIST = "show_advert_frist";
        public static final String SHOW_BDYY_FRIST = "show_bdyy_frist";
        public static final String SHOW_FLOAT = "show_float";
        public static final String SHOW_H265 = "show_h265";
        public static final String SHOW_READ_TIPS = "showReadTips";
        public static final String SKIP_OPENING_ENDING = "skip_opening_ending";
        public static final String SUBTITLE_COLOR = "subtitle_color";
        public static final String SUBTITLE_POS = "subtitle_pos";
        public static final String SUPER_CHILD_MODE = "super_child_mode";
        public static final String TVDETAIL_SORT = "tvdetail_sort";
        public static final String TVDETAIL_YEARS = "tvdetail_year";
        public static final String TV_COUNTRY = "tv_country_list";
    }

    public static final class Video {
        public static final String VIDEO_EPISODE = "videoplayer_episode";
        public static final String VIDEO_ID_MOVIE = "videoplayer_id_movie";
        public static final String VIDEO_ID_TV = "videoplayer_id_tv";
        public static final String VIDEO_PARAMS = "videoplayer_params";
        public static final String VIDEO_SEASON = "videoplayer_season";
    }

    static {
        String str = Environment.getExternalStorageDirectory().getPath() + File.separator + "Android/data" + File.separator + BuildConfig.APPLICATION_ID;
        DIR = str;
        String str2 = str + File.separator + "cache";
        DIR_CACHE = str2;
        String str3 = str + File.separator + "log";
        DIR_LOG = str3;
        String str4 = str + File.separator + "config";
        DIR_CONFIG = str4;
        String str5 = str + File.separator + "download";
        DIR_DOWNLOAD = str5;
        String str6 = str + File.separator + "ffmpegDownload";
        FFMPEG_DIR_DOWNLOAD = str6;
        DIR_DOWNLOAD_TEST = str + File.separator + "test";
        String str7 = str + File.separator + "picture";
        DIR_PICTURE = str7;
        String str8 = str + File.separator + "crop";
        DIR_CROP = str8;
        String str9 = str + File.separator + "thumb";
        DIR_THUMB = str9;
        String str10 = str + File.separator + "video";
        DIR_VIDEO = str10;
        DIR_TRANS_CODE_SUBTITLE = str + File.separator + "TransSubtitle";
        String str11 = str + File.separator + "txt";
        DIR_TXT = str11;
        String str12 = str + File.separator + "font";
        DIR_FONT = str12;
        String str13 = str2 + File.separator + HexDump.toHexString(MD5Util.md5(App.packageName));
        DIR_NOVEL = str13;
        String str14 = str + File.separator + "Subtitle";
        DIR_SUBTITLE = str14;
        String str15 = str14 + File.separator + "uploadSubtitles";
        DIR_UPLOAD_SUBTITLE = str15;
        String str16 = str14 + File.separator + "downloadSubtitles";
        DIR_DOLOAD_SUBTITLE = str16;
        DIR_UPLOAD_MOVIE_SUBTITLE = str15 + File.separator + BuildConfig.CHANNEL;
        DIR_UPLOAD_TV_SUBTITLE = str15 + File.separator + "tv";
        DIR_DOWNLOAD_MOVIE_SUBTITLE = str16 + File.separator + BuildConfig.CHANNEL;
        DIR_DOWNLOAD_TV_SUBTITLE = str16 + File.separator + "tv";
        DIR_WEB_DOWNLOAD_SUBTITLE = str5 + File.separator + "webDownload";
        DIR_DOWNLOAD_TEST_SPEED = str + File.separator + "testspeed";
        DIRS = new String[]{str, str2, str3, str4, str5, str7, str8, str9, str10, str11, str13, str12, str6};
        DIRS_CLEAR = new String[]{str2, str3, str4, str7, str8, str9, str10, str11, str13, str6};
    }
}
