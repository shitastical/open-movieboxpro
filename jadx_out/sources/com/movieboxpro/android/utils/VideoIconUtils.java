package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VideoIconUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/utils/VideoIconUtils;", "", "<init>", "()V", "getAudioChannelIcon", "", TvContractCompat.PARAM_CHANNEL, "", "getAudioCodeIcon", "code", "getAudioLangIcon", "lang", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VideoIconUtils {
    public static final VideoIconUtils INSTANCE = new VideoIconUtils();

    private VideoIconUtils() {
    }

    public final int getAudioChannelIcon(String channel) {
        String str = channel;
        if (str != null && !StringsKt.isBlank(str)) {
            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) ExifInterface.GPS_MEASUREMENT_2D, false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str, (CharSequence) "stereo", false, 2, (Object) null)) {
                if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "5.1", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str, (CharSequence) "6", false, 2, (Object) null)) {
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "7.1", false, 2, (Object) null)) {
                        return R.mipmap.ic_audio_channel_7_1;
                    }
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "6.1", false, 2, (Object) null)) {
                        return R.mipmap.ic_audio_channel_6_1;
                    }
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "mono", false, 2, (Object) null)) {
                        return R.mipmap.ic_audio_channel_mono;
                    }
                    if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "4.0", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str, (CharSequence) "4", false, 2, (Object) null)) {
                        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "quad", false, 2, (Object) null)) {
                            return R.mipmap.ic_audio_channel_quad;
                        }
                        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "3.0", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str, (CharSequence) ExifInterface.GPS_MEASUREMENT_3D, false, 2, (Object) null)) {
                            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "5.0", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str, (CharSequence) "5", false, 2, (Object) null)) {
                                if (Intrinsics.areEqual(channel, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                    return R.mipmap.ic_audio_channel_mono;
                                }
                            }
                            return R.mipmap.ic_audio_channel_5_0;
                        }
                        return R.mipmap.ic_audio_channel_3_0;
                    }
                    return R.mipmap.ic_audio_channel_4_0;
                }
                return R.mipmap.ic_audio_channel_5_1;
            }
            return R.mipmap.ic_audio_channel_stereo;
        }
        return -1;
    }

    public final int getAudioCodeIcon(String code) {
        String str = code;
        if (str != null && !StringsKt.isBlank(str)) {
            if (StringsKt.contains((CharSequence) str, (CharSequence) HlsSegmentFormat.AAC, true)) {
                return R.mipmap.ic_audio_code_aac;
            }
            if (StringsKt.contains((CharSequence) str, (CharSequence) "eac3", true)) {
                return R.mipmap.ic_audio_code_eac3;
            }
            if (!StringsKt.contains((CharSequence) str, (CharSequence) HlsSegmentFormat.AC3, true) && !StringsKt.contains((CharSequence) str, (CharSequence) "ec3", true)) {
                if (StringsKt.contains((CharSequence) str, (CharSequence) "dts", true)) {
                    return R.mipmap.ic_audio_code_dts;
                }
                if (StringsKt.contains((CharSequence) str, (CharSequence) "eac3", true)) {
                    return R.mipmap.ic_audio_code_eac3;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) HlsSegmentFormat.MP3, false, 2, (Object) null)) {
                    return R.mipmap.ic_audio_code_mp3;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "flac", false, 2, (Object) null)) {
                    return R.mipmap.ic_audio_code_flac;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "opus", false, 2, (Object) null)) {
                    return R.mipmap.ic_audio_code_opus;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "truehd", false, 2, (Object) null)) {
                    return R.mipmap.ic_audio_code_turehd;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "vorbis", false, 2, (Object) null)) {
                    return R.mipmap.ic_audio_code_vorbis;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "wmav2", false, 2, (Object) null)) {
                    return R.mipmap.ic_audio_code_wmav2;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "pcm_s24le", false, 2, (Object) null)) {
                    return R.mipmap.ic_audio_code_pcm_s24le;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "mp2", false, 2, (Object) null)) {
                    return R.mipmap.ic_audio_code_mp2;
                }
            }
            return R.mipmap.ic_audio_code_ac3;
        }
        return -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int getAudioLangIcon(String lang) {
        String str = lang;
        if (str != null && !StringsKt.isBlank(str) && lang != null) {
            switch (lang.hashCode()) {
                case -899451350:
                    if (lang.equals("slovak")) {
                        return R.mipmap.ic_audio_lang_slovak;
                    }
                    break;
                case -176239783:
                    if (lang.equals("Romanian")) {
                        return R.mipmap.ic_audio_lang_romanian;
                    }
                    break;
                case 3241:
                    if (lang.equals("en")) {
                        return R.mipmap.ic_audio_lang_en;
                    }
                    break;
                case 3276:
                    if (lang.equals("fr")) {
                        return R.mipmap.ic_audio_lang_fr;
                    }
                    break;
                case 3371:
                    if (lang.equals("it")) {
                        return R.mipmap.ic_audio_lang_it;
                    }
                    break;
                case 96663:
                    if (lang.equals("alb")) {
                        return R.mipmap.ic_audio_lang_alb;
                    }
                    break;
                case 96848:
                    if (lang.equals("ara")) {
                        return R.mipmap.ic_audio_lang_ara;
                    }
                    break;
                case 97419:
                    if (lang.equals("ben")) {
                        return R.mipmap.ic_audio_lang_ben;
                    }
                    break;
                case 97734:
                    if (lang.equals("bos")) {
                        return R.mipmap.ic_audio_lang_bos;
                    }
                    break;
                case 97913:
                    if (lang.equals("bul")) {
                        return R.mipmap.ic_audio_lang_bul;
                    }
                    break;
                case 97919:
                    if (lang.equals("bur")) {
                        return R.mipmap.ic_audio_lang_bur;
                    }
                    break;
                case 98468:
                    if (lang.equals("chi")) {
                        return R.mipmap.ic_audio_lang_chi;
                    }
                    break;
                case 99022:
                    if (lang.equals("cze")) {
                        return R.mipmap.ic_audio_lang_cze;
                    }
                    break;
                case 99217:
                    if (lang.equals("dan")) {
                        return R.mipmap.ic_audio_lang_dan;
                    }
                    break;
                case 99348:
                    if (lang.equals("deu")) {
                        return R.mipmap.ic_audio_lang_deu;
                    }
                    break;
                case 99843:
                    if (lang.equals("dut")) {
                        return R.mipmap.ic_audio_lang_dut;
                    }
                    break;
                case 100574:
                    if (lang.equals("eng")) {
                        return R.mipmap.ic_audio_lang_eng;
                    }
                    break;
                case 100742:
                    if (lang.equals("est")) {
                        return R.mipmap.ic_audio_lang_est;
                    }
                    break;
                case 101387:
                    if (lang.equals("fin")) {
                        return R.mipmap.ic_audio_lang_fin;
                    }
                    break;
                case 101653:
                    if (lang.equals("fra")) {
                        return R.mipmap.ic_audio_lang_fra;
                    }
                    break;
                case 101657:
                    if (lang.equals("fre")) {
                        return R.mipmap.ic_audio_lang_fre;
                    }
                    break;
                case 102228:
                    if (lang.equals("ger")) {
                        return R.mipmap.ic_audio_lang_ger;
                    }
                    break;
                case 102618:
                    if (lang.equals("gre")) {
                        return R.mipmap.ic_audio_lang_gre;
                    }
                    break;
                case 103173:
                    if (lang.equals("heb")) {
                        return R.mipmap.ic_audio_lang_heb;
                    }
                    break;
                case 103309:
                    if (lang.equals("hin")) {
                        return R.mipmap.ic_audio_lang_hin;
                    }
                    break;
                case 103596:
                    if (lang.equals("hrv")) {
                        return R.mipmap.ic_audio_lang_hrv;
                    }
                    break;
                case 103681:
                    if (lang.equals("hun")) {
                        return R.mipmap.ic_audio_lang_hun;
                    }
                    break;
                case 104075:
                    if (lang.equals("ice")) {
                        return R.mipmap.ic_audio_lang_ice;
                    }
                    break;
                case 104415:
                    if (lang.equals("ind")) {
                        return R.mipmap.ic_audio_lang_ind;
                    }
                    break;
                case 104578:
                    if (lang.equals("isl")) {
                        return R.mipmap.ic_audio_lang_isl;
                    }
                    break;
                case 104598:
                    if (lang.equals("ita")) {
                        return R.mipmap.ic_audio_lang_ita;
                    }
                    break;
                case 105448:
                    if (lang.equals("jpn")) {
                        return R.mipmap.ic_audio_lang_jpn;
                    }
                    break;
                case 106382:
                    if (lang.equals("kor")) {
                        return R.mipmap.ic_audio_lang_kor;
                    }
                    break;
                case 106911:
                    if (lang.equals("lat")) {
                        return R.mipmap.ic_audio_lang_lat;
                    }
                    break;
                case 107864:
                    if (lang.equals("mal")) {
                        return R.mipmap.ic_audio_lang_mal;
                    }
                    break;
                case 107866:
                    if (lang.equals("man")) {
                        return R.mipmap.ic_audio_lang_man;
                    }
                    break;
                case 107870:
                    if (lang.equals("mar")) {
                        return R.mipmap.ic_audio_lang_mar;
                    }
                    break;
                case 108300:
                    if (lang.equals("mon")) {
                        return R.mipmap.ic_audio_lang_mon;
                    }
                    break;
                case 108960:
                    if (lang.equals("new")) {
                        return R.mipmap.ic_audio_lang_new;
                    }
                    break;
                case 109158:
                    if (lang.equals("nld")) {
                        return R.mipmap.ic_audio_lang_nld;
                    }
                    break;
                case 109265:
                    if (lang.equals(CmcdConfiguration.KEY_NEXT_OBJECT_REQUEST)) {
                        return R.mipmap.ic_audio_lang_nor;
                    }
                    break;
                case 109386:
                    if (lang.equals("nso")) {
                        return R.mipmap.ic_audio_lang_nso;
                    }
                    break;
                case 110749:
                    if (lang.equals("pan")) {
                        return R.mipmap.ic_audio_lang_pan;
                    }
                    break;
                case 110877:
                    if (lang.equals("per")) {
                        return R.mipmap.ic_audio_lang_per;
                    }
                    break;
                case 111181:
                    if (lang.equals("pol")) {
                        return R.mipmap.ic_audio_lang_pol;
                    }
                    break;
                case 111187:
                    if (lang.equals("por")) {
                        return R.mipmap.ic_audio_lang_por;
                    }
                    break;
                case 113105:
                    if (lang.equals("ron")) {
                        return R.mipmap.ic_audio_lang_ron;
                    }
                    break;
                case 113296:
                    if (lang.equals("rus")) {
                        return R.mipmap.ic_audio_lang_rus;
                    }
                    break;
                case 113974:
                    if (lang.equals("slo")) {
                        return R.mipmap.ic_audio_lang_slo;
                    }
                    break;
                case 114084:
                    if (lang.equals("spa")) {
                        return R.mipmap.ic_audio_lang_spa;
                    }
                    break;
                case 114301:
                    if (lang.equals("swa")) {
                        return R.mipmap.ic_audio_lang_swa;
                    }
                    break;
                case 114305:
                    if (lang.equals("swe")) {
                        return R.mipmap.ic_audio_lang_swe;
                    }
                    break;
                case 114588:
                    if (lang.equals("tai")) {
                        return R.mipmap.ic_audio_lang_tai;
                    }
                    break;
                case 114797:
                    if (lang.equals("tha")) {
                        return R.mipmap.ic_audio_lang_tha;
                    }
                    break;
                case 115217:
                    if (lang.equals("tur")) {
                        return R.mipmap.ic_audio_lang_tur;
                    }
                    break;
                case 116078:
                    if (lang.equals("urk")) {
                        return R.mipmap.ic_audio_lang_urk;
                    }
                    break;
                case 116754:
                    if (lang.equals("vie")) {
                        return R.mipmap.ic_audio_lang_vie;
                    }
                    break;
                case 95163315:
                    if (lang.equals("czech")) {
                        return R.mipmap.ic_audio_lang_czech;
                    }
                    break;
            }
        }
        return -1;
    }
}
