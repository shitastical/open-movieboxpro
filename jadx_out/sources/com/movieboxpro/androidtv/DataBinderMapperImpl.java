package com.movieboxpro.androidtv;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.databinding.ActivityActorDetailBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityChooseTvDownloadBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityDownloadBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityDownloadedTvBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityDownloadingBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityFrameLayoutBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityListLayoutBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityMain3BindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityMovieDetail2BindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityPlayListDetailBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityScreenManageBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivitySearchVideoBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivitySuperChildModeBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityTransCodeBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityTranslateSubtitleBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityTvChannelReceiverBindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityTvDetail2BindingImpl;
import com.movieboxpro.androidtv.databinding.ActivityWatchPlanBindingImpl;
import com.movieboxpro.androidtv.databinding.CloseChildModeLayoutBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogAddToBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogAddWatchPlanBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogAudioSynBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogChangeVolumeBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogCheckDownloadPathBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogChooseDownloadLanguageBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogChooseDownloadLocationBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogChoosePlaySpeedBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogChoosePlayerBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogChoosePlayerEngineBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogChooseSeasonBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogConfirmSwitchDownloadPathBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogDownloadResolutionBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogDownloadToUsbHintBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogNoEnoughSpaceBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogOpenSubtitleBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogPreviewSubtitleBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogSmartDownloadSettingBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogSubtitleColorBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogTranslateProgressBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogWatchPlanProgresBindingImpl;
import com.movieboxpro.androidtv.databinding.DialogYoutubePlayBindingImpl;
import com.movieboxpro.androidtv.databinding.DownloadEmptyLayoutBindingImpl;
import com.movieboxpro.androidtv.databinding.DownloadingStatusViewBindingImpl;
import com.movieboxpro.androidtv.databinding.DownloadingWhiteStatusViewBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentAudioDelayBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentAudioTrackBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentChooseDownloadStorageBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentChooseLoginWayBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentChoosePlayBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentChooseSeasonBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentCodeLoginBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentDownloadBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentDownloadVideoQualityBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentFilterBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentFilterFavoriteBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentHomeBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentLoginBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentLoginExpireBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentLowQualityHintBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentMoviesBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentOpenSubtitleBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentPlayerEpisodeListBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentReleasedHintBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentSearchVideoResultBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentSettingBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentSpecialFilterBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentSubtitleBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentSwitchLanguageBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentTestNetSpeedBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentTimeIncorrectHintBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentTvGuideBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentUserInfoBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentVideoQualityBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentVipChargeGuideBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentWatchPlanBindingImpl;
import com.movieboxpro.androidtv.databinding.FragmentWebViewDialogBindingImpl;
import com.movieboxpro.androidtv.databinding.GridListDialogBindingImpl;
import com.movieboxpro.androidtv.databinding.LayoutDetailMetaRowBindingImpl;
import com.movieboxpro.androidtv.databinding.LoadErrorLayoutBindingImpl;
import com.movieboxpro.androidtv.databinding.ShowSubtitleDialogBindingImpl;
import com.movieboxpro.androidtv.databinding.SimpleListDialogBindingImpl;
import com.movieboxpro.androidtv.databinding.UserAvatarViewBindingImpl;
import com.movieboxpro.androidtv.databinding.VerticalListDialogBindingImpl;
import com.movieboxpro.androidtv.databinding.VideoInfoDialogLayoutBindingImpl;
import com.movieboxpro.androidtv.databinding.WatchProgressTopLayoutBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYACTORDETAIL = 1;
    private static final int LAYOUT_ACTIVITYCHOOSETVDOWNLOAD = 2;
    private static final int LAYOUT_ACTIVITYDOWNLOAD = 3;
    private static final int LAYOUT_ACTIVITYDOWNLOADEDTV = 4;
    private static final int LAYOUT_ACTIVITYDOWNLOADING = 5;
    private static final int LAYOUT_ACTIVITYFRAMELAYOUT = 6;
    private static final int LAYOUT_ACTIVITYLISTLAYOUT = 7;
    private static final int LAYOUT_ACTIVITYMAIN3 = 8;
    private static final int LAYOUT_ACTIVITYMOVIEDETAIL2 = 9;
    private static final int LAYOUT_ACTIVITYPLAYLISTDETAIL = 10;
    private static final int LAYOUT_ACTIVITYSCREENMANAGE = 11;
    private static final int LAYOUT_ACTIVITYSEARCHVIDEO = 12;
    private static final int LAYOUT_ACTIVITYSUPERCHILDMODE = 13;
    private static final int LAYOUT_ACTIVITYTRANSCODE = 14;
    private static final int LAYOUT_ACTIVITYTRANSLATESUBTITLE = 15;
    private static final int LAYOUT_ACTIVITYTVCHANNELRECEIVER = 16;
    private static final int LAYOUT_ACTIVITYTVDETAIL2 = 17;
    private static final int LAYOUT_ACTIVITYWATCHPLAN = 18;
    private static final int LAYOUT_CLOSECHILDMODELAYOUT = 19;
    private static final int LAYOUT_DIALOGADDTO = 20;
    private static final int LAYOUT_DIALOGADDWATCHPLAN = 21;
    private static final int LAYOUT_DIALOGAUDIOSYN = 22;
    private static final int LAYOUT_DIALOGCHANGEVOLUME = 23;
    private static final int LAYOUT_DIALOGCHECKDOWNLOADPATH = 24;
    private static final int LAYOUT_DIALOGCHOOSEDOWNLOADLANGUAGE = 25;
    private static final int LAYOUT_DIALOGCHOOSEDOWNLOADLOCATION = 26;
    private static final int LAYOUT_DIALOGCHOOSEPLAYER = 28;
    private static final int LAYOUT_DIALOGCHOOSEPLAYERENGINE = 29;
    private static final int LAYOUT_DIALOGCHOOSEPLAYSPEED = 27;
    private static final int LAYOUT_DIALOGCHOOSESEASON = 30;
    private static final int LAYOUT_DIALOGCONFIRMSWITCHDOWNLOADPATH = 31;
    private static final int LAYOUT_DIALOGDOWNLOADRESOLUTION = 32;
    private static final int LAYOUT_DIALOGDOWNLOADTOUSBHINT = 33;
    private static final int LAYOUT_DIALOGNOENOUGHSPACE = 34;
    private static final int LAYOUT_DIALOGOPENSUBTITLE = 35;
    private static final int LAYOUT_DIALOGPREVIEWSUBTITLE = 36;
    private static final int LAYOUT_DIALOGSMARTDOWNLOADSETTING = 37;
    private static final int LAYOUT_DIALOGSUBTITLECOLOR = 38;
    private static final int LAYOUT_DIALOGTRANSLATEPROGRESS = 39;
    private static final int LAYOUT_DIALOGWATCHPLANPROGRES = 40;
    private static final int LAYOUT_DIALOGYOUTUBEPLAY = 41;
    private static final int LAYOUT_DOWNLOADEMPTYLAYOUT = 42;
    private static final int LAYOUT_DOWNLOADINGSTATUSVIEW = 43;
    private static final int LAYOUT_DOWNLOADINGWHITESTATUSVIEW = 44;
    private static final int LAYOUT_FRAGMENTAUDIODELAY = 45;
    private static final int LAYOUT_FRAGMENTAUDIOTRACK = 46;
    private static final int LAYOUT_FRAGMENTCHOOSEDOWNLOADSTORAGE = 47;
    private static final int LAYOUT_FRAGMENTCHOOSELOGINWAY = 48;
    private static final int LAYOUT_FRAGMENTCHOOSEPLAY = 49;
    private static final int LAYOUT_FRAGMENTCHOOSESEASON = 50;
    private static final int LAYOUT_FRAGMENTCODELOGIN = 51;
    private static final int LAYOUT_FRAGMENTDOWNLOAD = 52;
    private static final int LAYOUT_FRAGMENTDOWNLOADVIDEOQUALITY = 53;
    private static final int LAYOUT_FRAGMENTFILTER = 54;
    private static final int LAYOUT_FRAGMENTFILTERFAVORITE = 55;
    private static final int LAYOUT_FRAGMENTHOME = 56;
    private static final int LAYOUT_FRAGMENTLOGIN = 57;
    private static final int LAYOUT_FRAGMENTLOGINEXPIRE = 58;
    private static final int LAYOUT_FRAGMENTLOWQUALITYHINT = 59;
    private static final int LAYOUT_FRAGMENTMOVIES = 60;
    private static final int LAYOUT_FRAGMENTOPENSUBTITLE = 61;
    private static final int LAYOUT_FRAGMENTPLAYEREPISODELIST = 62;
    private static final int LAYOUT_FRAGMENTRELEASEDHINT = 63;
    private static final int LAYOUT_FRAGMENTSEARCHVIDEORESULT = 64;
    private static final int LAYOUT_FRAGMENTSETTING = 65;
    private static final int LAYOUT_FRAGMENTSPECIALFILTER = 66;
    private static final int LAYOUT_FRAGMENTSUBTITLE = 67;
    private static final int LAYOUT_FRAGMENTSWITCHLANGUAGE = 68;
    private static final int LAYOUT_FRAGMENTTESTNETSPEED = 69;
    private static final int LAYOUT_FRAGMENTTIMEINCORRECTHINT = 70;
    private static final int LAYOUT_FRAGMENTTVGUIDE = 71;
    private static final int LAYOUT_FRAGMENTUSERINFO = 72;
    private static final int LAYOUT_FRAGMENTVIDEOQUALITY = 73;
    private static final int LAYOUT_FRAGMENTVIPCHARGEGUIDE = 74;
    private static final int LAYOUT_FRAGMENTWATCHPLAN = 75;
    private static final int LAYOUT_FRAGMENTWEBVIEWDIALOG = 76;
    private static final int LAYOUT_GRIDLISTDIALOG = 77;
    private static final int LAYOUT_LAYOUTDETAILMETAROW = 78;
    private static final int LAYOUT_LOADERRORLAYOUT = 79;
    private static final int LAYOUT_SHOWSUBTITLEDIALOG = 80;
    private static final int LAYOUT_SIMPLELISTDIALOG = 81;
    private static final int LAYOUT_USERAVATARVIEW = 82;
    private static final int LAYOUT_VERTICALLISTDIALOG = 83;
    private static final int LAYOUT_VIDEOINFODIALOGLAYOUT = 84;
    private static final int LAYOUT_WATCHPROGRESSTOPLAYOUT = 85;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(LAYOUT_WATCHPROGRESSTOPLAYOUT);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_actor_detail, 1);
        sparseIntArray.put(R.layout.activity_choose_tv_download, 2);
        sparseIntArray.put(R.layout.activity_download, 3);
        sparseIntArray.put(R.layout.activity_downloaded_tv, 4);
        sparseIntArray.put(R.layout.activity_downloading, 5);
        sparseIntArray.put(R.layout.activity_frame_layout, 6);
        sparseIntArray.put(R.layout.activity_list_layout, 7);
        sparseIntArray.put(R.layout.activity_main3, 8);
        sparseIntArray.put(R.layout.activity_movie_detail2, 9);
        sparseIntArray.put(R.layout.activity_play_list_detail, 10);
        sparseIntArray.put(R.layout.activity_screen_manage, 11);
        sparseIntArray.put(R.layout.activity_search_video, 12);
        sparseIntArray.put(R.layout.activity_super_child_mode, 13);
        sparseIntArray.put(R.layout.activity_trans_code, 14);
        sparseIntArray.put(R.layout.activity_translate_subtitle, 15);
        sparseIntArray.put(R.layout.activity_tv_channel_receiver, 16);
        sparseIntArray.put(R.layout.activity_tv_detail2, 17);
        sparseIntArray.put(R.layout.activity_watch_plan, 18);
        sparseIntArray.put(R.layout.close_child_mode_layout, 19);
        sparseIntArray.put(R.layout.dialog_add_to, 20);
        sparseIntArray.put(R.layout.dialog_add_watch_plan, 21);
        sparseIntArray.put(R.layout.dialog_audio_syn, 22);
        sparseIntArray.put(R.layout.dialog_change_volume, 23);
        sparseIntArray.put(R.layout.dialog_check_download_path, 24);
        sparseIntArray.put(R.layout.dialog_choose_download_language, 25);
        sparseIntArray.put(R.layout.dialog_choose_download_location, 26);
        sparseIntArray.put(R.layout.dialog_choose_play_speed, 27);
        sparseIntArray.put(R.layout.dialog_choose_player, 28);
        sparseIntArray.put(R.layout.dialog_choose_player_engine, 29);
        sparseIntArray.put(R.layout.dialog_choose_season, 30);
        sparseIntArray.put(R.layout.dialog_confirm_switch_download_path, 31);
        sparseIntArray.put(R.layout.dialog_download_resolution, 32);
        sparseIntArray.put(R.layout.dialog_download_to_usb_hint, 33);
        sparseIntArray.put(R.layout.dialog_no_enough_space, 34);
        sparseIntArray.put(R.layout.dialog_open_subtitle, 35);
        sparseIntArray.put(R.layout.dialog_preview_subtitle, 36);
        sparseIntArray.put(R.layout.dialog_smart_download_setting, 37);
        sparseIntArray.put(R.layout.dialog_subtitle_color, 38);
        sparseIntArray.put(R.layout.dialog_translate_progress, 39);
        sparseIntArray.put(R.layout.dialog_watch_plan_progres, 40);
        sparseIntArray.put(R.layout.dialog_youtube_play, 41);
        sparseIntArray.put(R.layout.download_empty_layout, 42);
        sparseIntArray.put(R.layout.downloading_status_view, 43);
        sparseIntArray.put(R.layout.downloading_white_status_view, 44);
        sparseIntArray.put(R.layout.fragment_audio_delay, 45);
        sparseIntArray.put(R.layout.fragment_audio_track, 46);
        sparseIntArray.put(R.layout.fragment_choose_download_storage, 47);
        sparseIntArray.put(R.layout.fragment_choose_login_way, 48);
        sparseIntArray.put(R.layout.fragment_choose_play, 49);
        sparseIntArray.put(R.layout.fragment_choose_season, 50);
        sparseIntArray.put(R.layout.fragment_code_login, 51);
        sparseIntArray.put(R.layout.fragment_download, 52);
        sparseIntArray.put(R.layout.fragment_download_video_quality, 53);
        sparseIntArray.put(R.layout.fragment_filter, 54);
        sparseIntArray.put(R.layout.fragment_filter_favorite, 55);
        sparseIntArray.put(R.layout.fragment_home, 56);
        sparseIntArray.put(R.layout.fragment_login, 57);
        sparseIntArray.put(R.layout.fragment_login_expire, 58);
        sparseIntArray.put(R.layout.fragment_low_quality_hint, LAYOUT_FRAGMENTLOWQUALITYHINT);
        sparseIntArray.put(R.layout.fragment_movies, 60);
        sparseIntArray.put(R.layout.fragment_open_subtitle, 61);
        sparseIntArray.put(R.layout.fragment_player_episode_list, LAYOUT_FRAGMENTPLAYEREPISODELIST);
        sparseIntArray.put(R.layout.fragment_released_hint, 63);
        sparseIntArray.put(R.layout.fragment_search_video_result, 64);
        sparseIntArray.put(R.layout.fragment_setting, 65);
        sparseIntArray.put(R.layout.fragment_special_filter, 66);
        sparseIntArray.put(R.layout.fragment_subtitle, 67);
        sparseIntArray.put(R.layout.fragment_switch_language, LAYOUT_FRAGMENTSWITCHLANGUAGE);
        sparseIntArray.put(R.layout.fragment_test_net_speed, LAYOUT_FRAGMENTTESTNETSPEED);
        sparseIntArray.put(R.layout.fragment_time_incorrect_hint, LAYOUT_FRAGMENTTIMEINCORRECTHINT);
        sparseIntArray.put(R.layout.fragment_tv_guide, 71);
        sparseIntArray.put(R.layout.fragment_user_info, LAYOUT_FRAGMENTUSERINFO);
        sparseIntArray.put(R.layout.fragment_video_quality, LAYOUT_FRAGMENTVIDEOQUALITY);
        sparseIntArray.put(R.layout.fragment_vip_charge_guide, LAYOUT_FRAGMENTVIPCHARGEGUIDE);
        sparseIntArray.put(R.layout.fragment_watch_plan, 75);
        sparseIntArray.put(R.layout.fragment_web_view_dialog, LAYOUT_FRAGMENTWEBVIEWDIALOG);
        sparseIntArray.put(R.layout.grid_list_dialog, 77);
        sparseIntArray.put(R.layout.layout_detail_meta_row, 78);
        sparseIntArray.put(R.layout.load_error_layout, 79);
        sparseIntArray.put(R.layout.show_subtitle_dialog, 80);
        sparseIntArray.put(R.layout.simple_list_dialog, LAYOUT_SIMPLELISTDIALOG);
        sparseIntArray.put(R.layout.user_avatar_view, LAYOUT_USERAVATARVIEW);
        sparseIntArray.put(R.layout.vertical_list_dialog, LAYOUT_VERTICALLISTDIALOG);
        sparseIntArray.put(R.layout.video_info_dialog_layout, LAYOUT_VIDEOINFODIALOGLAYOUT);
        sparseIntArray.put(R.layout.watch_progress_top_layout, LAYOUT_WATCHPROGRESSTOPLAYOUT);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 1:
                if ("layout/activity_actor_detail_0".equals(tag)) {
                    return new ActivityActorDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_actor_detail is invalid. Received: " + tag);
            case 2:
                if ("layout/activity_choose_tv_download_0".equals(tag)) {
                    return new ActivityChooseTvDownloadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_choose_tv_download is invalid. Received: " + tag);
            case 3:
                if ("layout/activity_download_0".equals(tag)) {
                    return new ActivityDownloadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_download is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_downloaded_tv_0".equals(tag)) {
                    return new ActivityDownloadedTvBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_downloaded_tv is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_downloading_0".equals(tag)) {
                    return new ActivityDownloadingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_downloading is invalid. Received: " + tag);
            case 6:
                if ("layout/activity_frame_layout_0".equals(tag)) {
                    return new ActivityFrameLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_frame_layout is invalid. Received: " + tag);
            case 7:
                if ("layout/activity_list_layout_0".equals(tag)) {
                    return new ActivityListLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_list_layout is invalid. Received: " + tag);
            case 8:
                if ("layout/activity_main3_0".equals(tag)) {
                    return new ActivityMain3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main3 is invalid. Received: " + tag);
            case 9:
                if ("layout/activity_movie_detail2_0".equals(tag)) {
                    return new ActivityMovieDetail2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_movie_detail2 is invalid. Received: " + tag);
            case 10:
                if ("layout/activity_play_list_detail_0".equals(tag)) {
                    return new ActivityPlayListDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_play_list_detail is invalid. Received: " + tag);
            case 11:
                if ("layout/activity_screen_manage_0".equals(tag)) {
                    return new ActivityScreenManageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_screen_manage is invalid. Received: " + tag);
            case 12:
                if ("layout/activity_search_video_0".equals(tag)) {
                    return new ActivitySearchVideoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_search_video is invalid. Received: " + tag);
            case 13:
                if ("layout/activity_super_child_mode_0".equals(tag)) {
                    return new ActivitySuperChildModeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_super_child_mode is invalid. Received: " + tag);
            case 14:
                if ("layout/activity_trans_code_0".equals(tag)) {
                    return new ActivityTransCodeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_trans_code is invalid. Received: " + tag);
            case 15:
                if ("layout/activity_translate_subtitle_0".equals(tag)) {
                    return new ActivityTranslateSubtitleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_translate_subtitle is invalid. Received: " + tag);
            case 16:
                if ("layout/activity_tv_channel_receiver_0".equals(tag)) {
                    return new ActivityTvChannelReceiverBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_tv_channel_receiver is invalid. Received: " + tag);
            case 17:
                if ("layout/activity_tv_detail2_0".equals(tag)) {
                    return new ActivityTvDetail2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_tv_detail2 is invalid. Received: " + tag);
            case 18:
                if ("layout/activity_watch_plan_0".equals(tag)) {
                    return new ActivityWatchPlanBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_watch_plan is invalid. Received: " + tag);
            case 19:
                if ("layout/close_child_mode_layout_0".equals(tag)) {
                    return new CloseChildModeLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for close_child_mode_layout is invalid. Received: " + tag);
            case 20:
                if ("layout/dialog_add_to_0".equals(tag)) {
                    return new DialogAddToBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_add_to is invalid. Received: " + tag);
            case 21:
                if ("layout/dialog_add_watch_plan_0".equals(tag)) {
                    return new DialogAddWatchPlanBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_add_watch_plan is invalid. Received: " + tag);
            case 22:
                if ("layout/dialog_audio_syn_0".equals(tag)) {
                    return new DialogAudioSynBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_audio_syn is invalid. Received: " + tag);
            case 23:
                if ("layout/dialog_change_volume_0".equals(tag)) {
                    return new DialogChangeVolumeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_change_volume is invalid. Received: " + tag);
            case 24:
                if ("layout/dialog_check_download_path_0".equals(tag)) {
                    return new DialogCheckDownloadPathBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_check_download_path is invalid. Received: " + tag);
            case 25:
                if ("layout/dialog_choose_download_language_0".equals(tag)) {
                    return new DialogChooseDownloadLanguageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_choose_download_language is invalid. Received: " + tag);
            case 26:
                if ("layout/dialog_choose_download_location_0".equals(tag)) {
                    return new DialogChooseDownloadLocationBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_choose_download_location is invalid. Received: " + tag);
            case 27:
                if ("layout/dialog_choose_play_speed_0".equals(tag)) {
                    return new DialogChoosePlaySpeedBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_choose_play_speed is invalid. Received: " + tag);
            case 28:
                if ("layout/dialog_choose_player_0".equals(tag)) {
                    return new DialogChoosePlayerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_choose_player is invalid. Received: " + tag);
            case 29:
                if ("layout/dialog_choose_player_engine_0".equals(tag)) {
                    return new DialogChoosePlayerEngineBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_choose_player_engine is invalid. Received: " + tag);
            case 30:
                if ("layout/dialog_choose_season_0".equals(tag)) {
                    return new DialogChooseSeasonBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_choose_season is invalid. Received: " + tag);
            case 31:
                if ("layout/dialog_confirm_switch_download_path_0".equals(tag)) {
                    return new DialogConfirmSwitchDownloadPathBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_confirm_switch_download_path is invalid. Received: " + tag);
            case 32:
                if ("layout/dialog_download_resolution_0".equals(tag)) {
                    return new DialogDownloadResolutionBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_download_resolution is invalid. Received: " + tag);
            case 33:
                if ("layout/dialog_download_to_usb_hint_0".equals(tag)) {
                    return new DialogDownloadToUsbHintBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_download_to_usb_hint is invalid. Received: " + tag);
            case 34:
                if ("layout/dialog_no_enough_space_0".equals(tag)) {
                    return new DialogNoEnoughSpaceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_no_enough_space is invalid. Received: " + tag);
            case 35:
                if ("layout/dialog_open_subtitle_0".equals(tag)) {
                    return new DialogOpenSubtitleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_open_subtitle is invalid. Received: " + tag);
            case 36:
                if ("layout/dialog_preview_subtitle_0".equals(tag)) {
                    return new DialogPreviewSubtitleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_preview_subtitle is invalid. Received: " + tag);
            case 37:
                if ("layout/dialog_smart_download_setting_0".equals(tag)) {
                    return new DialogSmartDownloadSettingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_smart_download_setting is invalid. Received: " + tag);
            case 38:
                if ("layout/dialog_subtitle_color_0".equals(tag)) {
                    return new DialogSubtitleColorBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_subtitle_color is invalid. Received: " + tag);
            case 39:
                if ("layout/dialog_translate_progress_0".equals(tag)) {
                    return new DialogTranslateProgressBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_translate_progress is invalid. Received: " + tag);
            case 40:
                if ("layout/dialog_watch_plan_progres_0".equals(tag)) {
                    return new DialogWatchPlanProgresBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_watch_plan_progres is invalid. Received: " + tag);
            case 41:
                if ("layout/dialog_youtube_play_0".equals(tag)) {
                    return new DialogYoutubePlayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_youtube_play is invalid. Received: " + tag);
            case 42:
                if ("layout/download_empty_layout_0".equals(tag)) {
                    return new DownloadEmptyLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for download_empty_layout is invalid. Received: " + tag);
            case 43:
                if ("layout/downloading_status_view_0".equals(tag)) {
                    return new DownloadingStatusViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for downloading_status_view is invalid. Received: " + tag);
            case 44:
                if ("layout/downloading_white_status_view_0".equals(tag)) {
                    return new DownloadingWhiteStatusViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for downloading_white_status_view is invalid. Received: " + tag);
            case 45:
                if ("layout/fragment_audio_delay_0".equals(tag)) {
                    return new FragmentAudioDelayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_audio_delay is invalid. Received: " + tag);
            case 46:
                if ("layout/fragment_audio_track_0".equals(tag)) {
                    return new FragmentAudioTrackBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_audio_track is invalid. Received: " + tag);
            case 47:
                if ("layout/fragment_choose_download_storage_0".equals(tag)) {
                    return new FragmentChooseDownloadStorageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_choose_download_storage is invalid. Received: " + tag);
            case 48:
                if ("layout/fragment_choose_login_way_0".equals(tag)) {
                    return new FragmentChooseLoginWayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_choose_login_way is invalid. Received: " + tag);
            case 49:
                if ("layout/fragment_choose_play_0".equals(tag)) {
                    return new FragmentChoosePlayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_choose_play is invalid. Received: " + tag);
            case 50:
                if ("layout/fragment_choose_season_0".equals(tag)) {
                    return new FragmentChooseSeasonBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_choose_season is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 51:
                if ("layout/fragment_code_login_0".equals(tag)) {
                    return new FragmentCodeLoginBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_code_login is invalid. Received: " + tag);
            case 52:
                if ("layout/fragment_download_0".equals(tag)) {
                    return new FragmentDownloadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_download is invalid. Received: " + tag);
            case 53:
                if ("layout/fragment_download_video_quality_0".equals(tag)) {
                    return new FragmentDownloadVideoQualityBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_download_video_quality is invalid. Received: " + tag);
            case 54:
                if ("layout/fragment_filter_0".equals(tag)) {
                    return new FragmentFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_filter is invalid. Received: " + tag);
            case 55:
                if ("layout/fragment_filter_favorite_0".equals(tag)) {
                    return new FragmentFilterFavoriteBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_filter_favorite is invalid. Received: " + tag);
            case 56:
                if ("layout/fragment_home_0".equals(tag)) {
                    return new FragmentHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
            case 57:
                if ("layout/fragment_login_0".equals(tag)) {
                    return new FragmentLoginBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
            case 58:
                if ("layout/fragment_login_expire_0".equals(tag)) {
                    return new FragmentLoginExpireBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_login_expire is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTLOWQUALITYHINT /* 59 */:
                if ("layout/fragment_low_quality_hint_0".equals(tag)) {
                    return new FragmentLowQualityHintBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_low_quality_hint is invalid. Received: " + tag);
            case 60:
                if ("layout/fragment_movies_0".equals(tag)) {
                    return new FragmentMoviesBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_movies is invalid. Received: " + tag);
            case 61:
                if ("layout/fragment_open_subtitle_0".equals(tag)) {
                    return new FragmentOpenSubtitleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_open_subtitle is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTPLAYEREPISODELIST /* 62 */:
                if ("layout/fragment_player_episode_list_0".equals(tag)) {
                    return new FragmentPlayerEpisodeListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_player_episode_list is invalid. Received: " + tag);
            case 63:
                if ("layout/fragment_released_hint_0".equals(tag)) {
                    return new FragmentReleasedHintBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_released_hint is invalid. Received: " + tag);
            case 64:
                if ("layout/fragment_search_video_result_0".equals(tag)) {
                    return new FragmentSearchVideoResultBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_search_video_result is invalid. Received: " + tag);
            case 65:
                if ("layout/fragment_setting_0".equals(tag)) {
                    return new FragmentSettingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_setting is invalid. Received: " + tag);
            case 66:
                if ("layout/fragment_special_filter_0".equals(tag)) {
                    return new FragmentSpecialFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_special_filter is invalid. Received: " + tag);
            case 67:
                if ("layout/fragment_subtitle_0".equals(tag)) {
                    return new FragmentSubtitleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_subtitle is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTSWITCHLANGUAGE /* 68 */:
                if ("layout/fragment_switch_language_0".equals(tag)) {
                    return new FragmentSwitchLanguageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_switch_language is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTTESTNETSPEED /* 69 */:
                if ("layout/fragment_test_net_speed_0".equals(tag)) {
                    return new FragmentTestNetSpeedBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_test_net_speed is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTTIMEINCORRECTHINT /* 70 */:
                if ("layout/fragment_time_incorrect_hint_0".equals(tag)) {
                    return new FragmentTimeIncorrectHintBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_time_incorrect_hint is invalid. Received: " + tag);
            case 71:
                if ("layout/fragment_tv_guide_0".equals(tag)) {
                    return new FragmentTvGuideBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_tv_guide is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTUSERINFO /* 72 */:
                if ("layout/fragment_user_info_0".equals(tag)) {
                    return new FragmentUserInfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_user_info is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTVIDEOQUALITY /* 73 */:
                if ("layout/fragment_video_quality_0".equals(tag)) {
                    return new FragmentVideoQualityBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_video_quality is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTVIPCHARGEGUIDE /* 74 */:
                if ("layout/fragment_vip_charge_guide_0".equals(tag)) {
                    return new FragmentVipChargeGuideBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vip_charge_guide is invalid. Received: " + tag);
            case 75:
                if ("layout/fragment_watch_plan_0".equals(tag)) {
                    return new FragmentWatchPlanBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_watch_plan is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTWEBVIEWDIALOG /* 76 */:
                if ("layout/fragment_web_view_dialog_0".equals(tag)) {
                    return new FragmentWebViewDialogBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_web_view_dialog is invalid. Received: " + tag);
            case 77:
                if ("layout/grid_list_dialog_0".equals(tag)) {
                    return new GridListDialogBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for grid_list_dialog is invalid. Received: " + tag);
            case 78:
                if ("layout/layout_detail_meta_row_0".equals(tag)) {
                    return new LayoutDetailMetaRowBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_detail_meta_row is invalid. Received: " + tag);
            case 79:
                if ("layout/load_error_layout_0".equals(tag)) {
                    return new LoadErrorLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for load_error_layout is invalid. Received: " + tag);
            case 80:
                if ("layout/show_subtitle_dialog_0".equals(tag)) {
                    return new ShowSubtitleDialogBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for show_subtitle_dialog is invalid. Received: " + tag);
            case LAYOUT_SIMPLELISTDIALOG /* 81 */:
                if ("layout/simple_list_dialog_0".equals(tag)) {
                    return new SimpleListDialogBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for simple_list_dialog is invalid. Received: " + tag);
            case LAYOUT_USERAVATARVIEW /* 82 */:
                if ("layout/user_avatar_view_0".equals(tag)) {
                    return new UserAvatarViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for user_avatar_view is invalid. Received: " + tag);
            case LAYOUT_VERTICALLISTDIALOG /* 83 */:
                if ("layout/vertical_list_dialog_0".equals(tag)) {
                    return new VerticalListDialogBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for vertical_list_dialog is invalid. Received: " + tag);
            case LAYOUT_VIDEOINFODIALOGLAYOUT /* 84 */:
                if ("layout/video_info_dialog_layout_0".equals(tag)) {
                    return new VideoInfoDialogLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for video_info_dialog_layout is invalid. Received: " + tag);
            case LAYOUT_WATCHPROGRESSTOPLAYOUT /* 85 */:
                if ("layout/watch_progress_top_layout_0".equals(tag)) {
                    return new WatchProgressTopLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for watch_progress_top_layout is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
        int i = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (i <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        int i2 = (i - 1) / 50;
        if (i2 == 0) {
            return internalGetViewDataBinding0(component, view, i, tag);
        }
        if (i2 != 1) {
            return null;
        }
        return internalGetViewDataBinding1(component, view, i, tag);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
        if (views == null || views.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId) <= 0 || views[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String tag) {
        Integer num;
        if (tag == null || (num = InnerLayoutIdLookup.sKeys.get(tag)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int localId) {
        return InnerBrLookup.sKeys.get(localId);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.chad.library.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(1);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> map = new HashMap<>(DataBinderMapperImpl.LAYOUT_WATCHPROGRESSTOPLAYOUT);
            sKeys = map;
            map.put("layout/activity_actor_detail_0", Integer.valueOf(R.layout.activity_actor_detail));
            map.put("layout/activity_choose_tv_download_0", Integer.valueOf(R.layout.activity_choose_tv_download));
            map.put("layout/activity_download_0", Integer.valueOf(R.layout.activity_download));
            map.put("layout/activity_downloaded_tv_0", Integer.valueOf(R.layout.activity_downloaded_tv));
            map.put("layout/activity_downloading_0", Integer.valueOf(R.layout.activity_downloading));
            map.put("layout/activity_frame_layout_0", Integer.valueOf(R.layout.activity_frame_layout));
            map.put("layout/activity_list_layout_0", Integer.valueOf(R.layout.activity_list_layout));
            map.put("layout/activity_main3_0", Integer.valueOf(R.layout.activity_main3));
            map.put("layout/activity_movie_detail2_0", Integer.valueOf(R.layout.activity_movie_detail2));
            map.put("layout/activity_play_list_detail_0", Integer.valueOf(R.layout.activity_play_list_detail));
            map.put("layout/activity_screen_manage_0", Integer.valueOf(R.layout.activity_screen_manage));
            map.put("layout/activity_search_video_0", Integer.valueOf(R.layout.activity_search_video));
            map.put("layout/activity_super_child_mode_0", Integer.valueOf(R.layout.activity_super_child_mode));
            map.put("layout/activity_trans_code_0", Integer.valueOf(R.layout.activity_trans_code));
            map.put("layout/activity_translate_subtitle_0", Integer.valueOf(R.layout.activity_translate_subtitle));
            map.put("layout/activity_tv_channel_receiver_0", Integer.valueOf(R.layout.activity_tv_channel_receiver));
            map.put("layout/activity_tv_detail2_0", Integer.valueOf(R.layout.activity_tv_detail2));
            map.put("layout/activity_watch_plan_0", Integer.valueOf(R.layout.activity_watch_plan));
            map.put("layout/close_child_mode_layout_0", Integer.valueOf(R.layout.close_child_mode_layout));
            map.put("layout/dialog_add_to_0", Integer.valueOf(R.layout.dialog_add_to));
            map.put("layout/dialog_add_watch_plan_0", Integer.valueOf(R.layout.dialog_add_watch_plan));
            map.put("layout/dialog_audio_syn_0", Integer.valueOf(R.layout.dialog_audio_syn));
            map.put("layout/dialog_change_volume_0", Integer.valueOf(R.layout.dialog_change_volume));
            map.put("layout/dialog_check_download_path_0", Integer.valueOf(R.layout.dialog_check_download_path));
            map.put("layout/dialog_choose_download_language_0", Integer.valueOf(R.layout.dialog_choose_download_language));
            map.put("layout/dialog_choose_download_location_0", Integer.valueOf(R.layout.dialog_choose_download_location));
            map.put("layout/dialog_choose_play_speed_0", Integer.valueOf(R.layout.dialog_choose_play_speed));
            map.put("layout/dialog_choose_player_0", Integer.valueOf(R.layout.dialog_choose_player));
            map.put("layout/dialog_choose_player_engine_0", Integer.valueOf(R.layout.dialog_choose_player_engine));
            map.put("layout/dialog_choose_season_0", Integer.valueOf(R.layout.dialog_choose_season));
            map.put("layout/dialog_confirm_switch_download_path_0", Integer.valueOf(R.layout.dialog_confirm_switch_download_path));
            map.put("layout/dialog_download_resolution_0", Integer.valueOf(R.layout.dialog_download_resolution));
            map.put("layout/dialog_download_to_usb_hint_0", Integer.valueOf(R.layout.dialog_download_to_usb_hint));
            map.put("layout/dialog_no_enough_space_0", Integer.valueOf(R.layout.dialog_no_enough_space));
            map.put("layout/dialog_open_subtitle_0", Integer.valueOf(R.layout.dialog_open_subtitle));
            map.put("layout/dialog_preview_subtitle_0", Integer.valueOf(R.layout.dialog_preview_subtitle));
            map.put("layout/dialog_smart_download_setting_0", Integer.valueOf(R.layout.dialog_smart_download_setting));
            map.put("layout/dialog_subtitle_color_0", Integer.valueOf(R.layout.dialog_subtitle_color));
            map.put("layout/dialog_translate_progress_0", Integer.valueOf(R.layout.dialog_translate_progress));
            map.put("layout/dialog_watch_plan_progres_0", Integer.valueOf(R.layout.dialog_watch_plan_progres));
            map.put("layout/dialog_youtube_play_0", Integer.valueOf(R.layout.dialog_youtube_play));
            map.put("layout/download_empty_layout_0", Integer.valueOf(R.layout.download_empty_layout));
            map.put("layout/downloading_status_view_0", Integer.valueOf(R.layout.downloading_status_view));
            map.put("layout/downloading_white_status_view_0", Integer.valueOf(R.layout.downloading_white_status_view));
            map.put("layout/fragment_audio_delay_0", Integer.valueOf(R.layout.fragment_audio_delay));
            map.put("layout/fragment_audio_track_0", Integer.valueOf(R.layout.fragment_audio_track));
            map.put("layout/fragment_choose_download_storage_0", Integer.valueOf(R.layout.fragment_choose_download_storage));
            map.put("layout/fragment_choose_login_way_0", Integer.valueOf(R.layout.fragment_choose_login_way));
            map.put("layout/fragment_choose_play_0", Integer.valueOf(R.layout.fragment_choose_play));
            map.put("layout/fragment_choose_season_0", Integer.valueOf(R.layout.fragment_choose_season));
            map.put("layout/fragment_code_login_0", Integer.valueOf(R.layout.fragment_code_login));
            map.put("layout/fragment_download_0", Integer.valueOf(R.layout.fragment_download));
            map.put("layout/fragment_download_video_quality_0", Integer.valueOf(R.layout.fragment_download_video_quality));
            map.put("layout/fragment_filter_0", Integer.valueOf(R.layout.fragment_filter));
            map.put("layout/fragment_filter_favorite_0", Integer.valueOf(R.layout.fragment_filter_favorite));
            map.put("layout/fragment_home_0", Integer.valueOf(R.layout.fragment_home));
            map.put("layout/fragment_login_0", Integer.valueOf(R.layout.fragment_login));
            map.put("layout/fragment_login_expire_0", Integer.valueOf(R.layout.fragment_login_expire));
            map.put("layout/fragment_low_quality_hint_0", Integer.valueOf(R.layout.fragment_low_quality_hint));
            map.put("layout/fragment_movies_0", Integer.valueOf(R.layout.fragment_movies));
            map.put("layout/fragment_open_subtitle_0", Integer.valueOf(R.layout.fragment_open_subtitle));
            map.put("layout/fragment_player_episode_list_0", Integer.valueOf(R.layout.fragment_player_episode_list));
            map.put("layout/fragment_released_hint_0", Integer.valueOf(R.layout.fragment_released_hint));
            map.put("layout/fragment_search_video_result_0", Integer.valueOf(R.layout.fragment_search_video_result));
            map.put("layout/fragment_setting_0", Integer.valueOf(R.layout.fragment_setting));
            map.put("layout/fragment_special_filter_0", Integer.valueOf(R.layout.fragment_special_filter));
            map.put("layout/fragment_subtitle_0", Integer.valueOf(R.layout.fragment_subtitle));
            map.put("layout/fragment_switch_language_0", Integer.valueOf(R.layout.fragment_switch_language));
            map.put("layout/fragment_test_net_speed_0", Integer.valueOf(R.layout.fragment_test_net_speed));
            map.put("layout/fragment_time_incorrect_hint_0", Integer.valueOf(R.layout.fragment_time_incorrect_hint));
            map.put("layout/fragment_tv_guide_0", Integer.valueOf(R.layout.fragment_tv_guide));
            map.put("layout/fragment_user_info_0", Integer.valueOf(R.layout.fragment_user_info));
            map.put("layout/fragment_video_quality_0", Integer.valueOf(R.layout.fragment_video_quality));
            map.put("layout/fragment_vip_charge_guide_0", Integer.valueOf(R.layout.fragment_vip_charge_guide));
            map.put("layout/fragment_watch_plan_0", Integer.valueOf(R.layout.fragment_watch_plan));
            map.put("layout/fragment_web_view_dialog_0", Integer.valueOf(R.layout.fragment_web_view_dialog));
            map.put("layout/grid_list_dialog_0", Integer.valueOf(R.layout.grid_list_dialog));
            map.put("layout/layout_detail_meta_row_0", Integer.valueOf(R.layout.layout_detail_meta_row));
            map.put("layout/load_error_layout_0", Integer.valueOf(R.layout.load_error_layout));
            map.put("layout/show_subtitle_dialog_0", Integer.valueOf(R.layout.show_subtitle_dialog));
            map.put("layout/simple_list_dialog_0", Integer.valueOf(R.layout.simple_list_dialog));
            map.put("layout/user_avatar_view_0", Integer.valueOf(R.layout.user_avatar_view));
            map.put("layout/vertical_list_dialog_0", Integer.valueOf(R.layout.vertical_list_dialog));
            map.put("layout/video_info_dialog_layout_0", Integer.valueOf(R.layout.video_info_dialog_layout));
            map.put("layout/watch_progress_top_layout_0", Integer.valueOf(R.layout.watch_progress_top_layout));
        }
    }
}
