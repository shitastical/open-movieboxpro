package com.movieboxpro.android.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.C;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\bn\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bë\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\u001c\b\u0002\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0000\u0018\u0001`\u001a\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020\u001c\u0012\b\b\u0002\u0010#\u001a\u00020$\u0012\b\b\u0002\u0010%\u001a\u00020$¢\u0006\u0004\b&\u0010'J\u000b\u0010q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010y\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010z\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010{\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010|\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010~\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u007f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0081\u0001\u001a\u00020\u0014HÆ\u0003J\n\u0010\u0082\u0001\u001a\u00020\u0014HÆ\u0003J\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u001e\u0010\u0084\u0001\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0000\u0018\u0001`\u001aHÆ\u0003J\u0011\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001cHÆ\u0003¢\u0006\u0002\u0010YJ\u0011\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u001cHÆ\u0003¢\u0006\u0002\u0010YJ\u0011\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u001cHÆ\u0003¢\u0006\u0002\u0010YJ\u0011\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u001cHÆ\u0003¢\u0006\u0002\u0010YJ\u0011\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u001cHÆ\u0003¢\u0006\u0002\u0010YJ\f\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020\u001cHÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020$HÆ\u0003J\n\u0010\u008d\u0001\u001a\u00020$HÆ\u0003Jô\u0002\u0010\u008e\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u001c\b\u0002\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0000\u0018\u0001`\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\"\u001a\u00020\u001c2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020$HÆ\u0001¢\u0006\u0003\u0010\u008f\u0001J\u0007\u0010\u0090\u0001\u001a\u00020\u001cJ\u0016\u0010\u0091\u0001\u001a\u00020\u00142\n\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u0093\u0001HÖ\u0003J\n\u0010\u0094\u0001\u001a\u00020\u001cHÖ\u0001J\n\u0010\u0095\u0001\u001a\u00020\u0003HÖ\u0001J\u001b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u009a\u0001\u001a\u00020\u001cR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010)\"\u0004\b-\u0010+R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010)\"\u0004\b1\u0010+R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010)\"\u0004\b3\u0010+R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010)\"\u0004\b5\u0010+R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010)\"\u0004\b7\u0010+R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010)\"\u0004\b9\u0010+R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010)\"\u0004\b;\u0010+R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010)\"\u0004\b=\u0010+R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010)\"\u0004\b?\u0010+R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010)\"\u0004\bA\u0010+R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010)\"\u0004\bC\u0010+R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010)\"\u0004\bE\u0010+R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010)\"\u0004\bG\u0010+R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010)\"\u0004\bI\u0010+R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001a\u0010\u0015\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010K\"\u0004\bO\u0010MR\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR.\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0000\u0018\u0001`\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\\\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\\\u001a\u0004\b]\u0010Y\"\u0004\b^\u0010[R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\\\u001a\u0004\b_\u0010Y\"\u0004\b`\u0010[R\u001e\u0010\u001f\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\\\u001a\u0004\ba\u0010Y\"\u0004\bb\u0010[R\u001e\u0010 \u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\\\u001a\u0004\bc\u0010Y\"\u0004\bd\u0010[R\u001c\u0010!\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010)\"\u0004\bf\u0010+R\u001a\u0010\"\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\u001a\u0010%\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010l\"\u0004\bp\u0010n¨\u0006\u009b\u0001"}, d2 = {"Lcom/movieboxpro/android/model/AudioTrackModel;", "Landroid/os/Parcelable;", "name", "", "download_url", "bitstream", "frequency", IjkMediaMeta.IJKM_KEY_FORMAT, IDataSource.SCHEME_FILE_TAG, "lang", TvContractCompat.PARAM_CHANNEL, "video_file_name", "oss_fid", "fid", "time", "trackName", "language", "iso3Language", "groupId", "select", "", "autoSelect", "exoAudioTrackInfo", "Landroid/os/Bundle;", "tracks", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "id", "", FirebaseAnalytics.Param.INDEX, "vip_only", "org", "trackId", "hlsTrackId", "type", "size_bytes", "", "add_time", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLandroid/os/Bundle;Ljava/util/ArrayList;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;IJJ)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getDownload_url", "setDownload_url", "getBitstream", "setBitstream", "getFrequency", "setFrequency", "getFormat", "setFormat", "getFile", "setFile", "getLang", "setLang", "getChannel", "setChannel", "getVideo_file_name", "setVideo_file_name", "getOss_fid", "setOss_fid", "getFid", "setFid", "getTime", "setTime", "getTrackName", "setTrackName", "getLanguage", "setLanguage", "getIso3Language", "setIso3Language", "getGroupId", "setGroupId", "getSelect", "()Z", "setSelect", "(Z)V", "getAutoSelect", "setAutoSelect", "getExoAudioTrackInfo", "()Landroid/os/Bundle;", "setExoAudioTrackInfo", "(Landroid/os/Bundle;)V", "getTracks", "()Ljava/util/ArrayList;", "setTracks", "(Ljava/util/ArrayList;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getIndex", "setIndex", "getVip_only", "setVip_only", "getOrg", "setOrg", "getTrackId", "setTrackId", "getHlsTrackId", "setHlsTrackId", "getType", "()I", "setType", "(I)V", "getSize_bytes", "()J", "setSize_bytes", "(J)V", "getAdd_time", "setAdd_time", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLandroid/os/Bundle;Ljava/util/ArrayList;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;IJJ)Lcom/movieboxpro/android/model/AudioTrackModel;", "describeContents", "equals", Video.TypeInfo.OTHER, "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AudioTrackModel implements Parcelable {
    public static final Parcelable.Creator<AudioTrackModel> CREATOR = new Creator();
    private long add_time;
    private boolean autoSelect;
    private String bitstream;
    private String channel;
    private String download_url;
    private Bundle exoAudioTrackInfo;
    private String fid;
    private String file;
    private String format;
    private String frequency;
    private String groupId;
    private String hlsTrackId;
    private Integer id;
    private Integer index;
    private String iso3Language;
    private String lang;
    private String language;
    private String name;
    private Integer org;
    private String oss_fid;
    private boolean select;
    private long size_bytes;
    private String time;
    private Integer trackId;
    private String trackName;
    private ArrayList<AudioTrackModel> tracks;
    private int type;
    private String video_file_name;
    private Integer vip_only;

    /* JADX INFO: compiled from: Bean.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AudioTrackModel> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AudioTrackModel createFromParcel(Parcel parcel) {
            ArrayList arrayList;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            String string5 = parcel.readString();
            String string6 = parcel.readString();
            String string7 = parcel.readString();
            String string8 = parcel.readString();
            String string9 = parcel.readString();
            String string10 = parcel.readString();
            String string11 = parcel.readString();
            String string12 = parcel.readString();
            String string13 = parcel.readString();
            String string14 = parcel.readString();
            String string15 = parcel.readString();
            String string16 = parcel.readString();
            boolean z = parcel.readInt() != 0;
            boolean z2 = parcel.readInt() != 0;
            Bundle bundle = parcel.readBundle(AudioTrackModel.class.getClassLoader());
            if (parcel.readInt() == 0) {
                arrayList = null;
            } else {
                int i = parcel.readInt();
                arrayList = new ArrayList(i);
                int i2 = 0;
                while (i2 != i) {
                    arrayList.add(AudioTrackModel.CREATOR.createFromParcel(parcel));
                    i2++;
                    i = i;
                }
            }
            return new AudioTrackModel(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, z, z2, bundle, arrayList, parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readLong());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AudioTrackModel[] newArray(int i) {
            return new AudioTrackModel[i];
        }
    }

    public AudioTrackModel() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, 0, 0L, 0L, 536870911, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getOss_fid() {
        return this.oss_fid;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getFid() {
        return this.fid;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTime() {
        return this.time;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getTrackName() {
        return this.trackName;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getIso3Language() {
        return this.iso3Language;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getGroupId() {
        return this.groupId;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getSelect() {
        return this.select;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final boolean getAutoSelect() {
        return this.autoSelect;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final Bundle getExoAudioTrackInfo() {
        return this.exoAudioTrackInfo;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDownload_url() {
        return this.download_url;
    }

    public final ArrayList<AudioTrackModel> component20() {
        return this.tracks;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final Integer getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final Integer getVip_only() {
        return this.vip_only;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final Integer getOrg() {
        return this.org;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final Integer getTrackId() {
        return this.trackId;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final String getHlsTrackId() {
        return this.hlsTrackId;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final long getSize_bytes() {
        return this.size_bytes;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final long getAdd_time() {
        return this.add_time;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBitstream() {
        return this.bitstream;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getFrequency() {
        return this.frequency;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFormat() {
        return this.format;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getChannel() {
        return this.channel;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getVideo_file_name() {
        return this.video_file_name;
    }

    public final AudioTrackModel copy(String name, String download_url, String bitstream, String frequency, String format, String file, String lang, String channel, String video_file_name, String oss_fid, String fid, String time, String trackName, String language, String iso3Language, String groupId, boolean select, boolean autoSelect, Bundle exoAudioTrackInfo, ArrayList<AudioTrackModel> tracks, Integer id, Integer index, Integer vip_only, Integer org2, Integer trackId, String hlsTrackId, int type, long size_bytes, long add_time) {
        return new AudioTrackModel(name, download_url, bitstream, frequency, format, file, lang, channel, video_file_name, oss_fid, fid, time, trackName, language, iso3Language, groupId, select, autoSelect, exoAudioTrackInfo, tracks, id, index, vip_only, org2, trackId, hlsTrackId, type, size_bytes, add_time);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioTrackModel)) {
            return false;
        }
        AudioTrackModel audioTrackModel = (AudioTrackModel) other;
        return Intrinsics.areEqual(this.name, audioTrackModel.name) && Intrinsics.areEqual(this.download_url, audioTrackModel.download_url) && Intrinsics.areEqual(this.bitstream, audioTrackModel.bitstream) && Intrinsics.areEqual(this.frequency, audioTrackModel.frequency) && Intrinsics.areEqual(this.format, audioTrackModel.format) && Intrinsics.areEqual(this.file, audioTrackModel.file) && Intrinsics.areEqual(this.lang, audioTrackModel.lang) && Intrinsics.areEqual(this.channel, audioTrackModel.channel) && Intrinsics.areEqual(this.video_file_name, audioTrackModel.video_file_name) && Intrinsics.areEqual(this.oss_fid, audioTrackModel.oss_fid) && Intrinsics.areEqual(this.fid, audioTrackModel.fid) && Intrinsics.areEqual(this.time, audioTrackModel.time) && Intrinsics.areEqual(this.trackName, audioTrackModel.trackName) && Intrinsics.areEqual(this.language, audioTrackModel.language) && Intrinsics.areEqual(this.iso3Language, audioTrackModel.iso3Language) && Intrinsics.areEqual(this.groupId, audioTrackModel.groupId) && this.select == audioTrackModel.select && this.autoSelect == audioTrackModel.autoSelect && Intrinsics.areEqual(this.exoAudioTrackInfo, audioTrackModel.exoAudioTrackInfo) && Intrinsics.areEqual(this.tracks, audioTrackModel.tracks) && Intrinsics.areEqual(this.id, audioTrackModel.id) && Intrinsics.areEqual(this.index, audioTrackModel.index) && Intrinsics.areEqual(this.vip_only, audioTrackModel.vip_only) && Intrinsics.areEqual(this.org, audioTrackModel.org) && Intrinsics.areEqual(this.trackId, audioTrackModel.trackId) && Intrinsics.areEqual(this.hlsTrackId, audioTrackModel.hlsTrackId) && this.type == audioTrackModel.type && this.size_bytes == audioTrackModel.size_bytes && this.add_time == audioTrackModel.add_time;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.download_url;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.bitstream;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.frequency;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.format;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.file;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.lang;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.channel;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.video_file_name;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.oss_fid;
        int iHashCode10 = (iHashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.fid;
        int iHashCode11 = (iHashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.time;
        int iHashCode12 = (iHashCode11 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.trackName;
        int iHashCode13 = (iHashCode12 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.language;
        int iHashCode14 = (iHashCode13 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.iso3Language;
        int iHashCode15 = (iHashCode14 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.groupId;
        int iHashCode16 = (((((iHashCode15 + (str16 == null ? 0 : str16.hashCode())) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.select)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.autoSelect)) * 31;
        Bundle bundle = this.exoAudioTrackInfo;
        int iHashCode17 = (iHashCode16 + (bundle == null ? 0 : bundle.hashCode())) * 31;
        ArrayList<AudioTrackModel> arrayList = this.tracks;
        int iHashCode18 = (iHashCode17 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        Integer num = this.id;
        int iHashCode19 = (iHashCode18 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.index;
        int iHashCode20 = (iHashCode19 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.vip_only;
        int iHashCode21 = (iHashCode20 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.org;
        int iHashCode22 = (iHashCode21 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.trackId;
        int iHashCode23 = (iHashCode22 + (num5 == null ? 0 : num5.hashCode())) * 31;
        String str17 = this.hlsTrackId;
        return ((((((iHashCode23 + (str17 != null ? str17.hashCode() : 0)) * 31) + this.type) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.size_bytes)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.add_time);
    }

    public String toString() {
        return "AudioTrackModel(name=" + this.name + ", download_url=" + this.download_url + ", bitstream=" + this.bitstream + ", frequency=" + this.frequency + ", format=" + this.format + ", file=" + this.file + ", lang=" + this.lang + ", channel=" + this.channel + ", video_file_name=" + this.video_file_name + ", oss_fid=" + this.oss_fid + ", fid=" + this.fid + ", time=" + this.time + ", trackName=" + this.trackName + ", language=" + this.language + ", iso3Language=" + this.iso3Language + ", groupId=" + this.groupId + ", select=" + this.select + ", autoSelect=" + this.autoSelect + ", exoAudioTrackInfo=" + this.exoAudioTrackInfo + ", tracks=" + this.tracks + ", id=" + this.id + ", index=" + this.index + ", vip_only=" + this.vip_only + ", org=" + this.org + ", trackId=" + this.trackId + ", hlsTrackId=" + this.hlsTrackId + ", type=" + this.type + ", size_bytes=" + this.size_bytes + ", add_time=" + this.add_time + ")";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.name);
        dest.writeString(this.download_url);
        dest.writeString(this.bitstream);
        dest.writeString(this.frequency);
        dest.writeString(this.format);
        dest.writeString(this.file);
        dest.writeString(this.lang);
        dest.writeString(this.channel);
        dest.writeString(this.video_file_name);
        dest.writeString(this.oss_fid);
        dest.writeString(this.fid);
        dest.writeString(this.time);
        dest.writeString(this.trackName);
        dest.writeString(this.language);
        dest.writeString(this.iso3Language);
        dest.writeString(this.groupId);
        dest.writeInt(this.select ? 1 : 0);
        dest.writeInt(this.autoSelect ? 1 : 0);
        dest.writeBundle(this.exoAudioTrackInfo);
        ArrayList<AudioTrackModel> arrayList = this.tracks;
        if (arrayList == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(arrayList.size());
            Iterator<AudioTrackModel> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().writeToParcel(dest, flags);
            }
        }
        Integer num = this.id;
        if (num == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num.intValue());
        }
        Integer num2 = this.index;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num2.intValue());
        }
        Integer num3 = this.vip_only;
        if (num3 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num3.intValue());
        }
        Integer num4 = this.org;
        if (num4 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num4.intValue());
        }
        Integer num5 = this.trackId;
        if (num5 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num5.intValue());
        }
        dest.writeString(this.hlsTrackId);
        dest.writeInt(this.type);
        dest.writeLong(this.size_bytes);
        dest.writeLong(this.add_time);
    }

    public AudioTrackModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, boolean z, boolean z2, Bundle bundle, ArrayList<AudioTrackModel> arrayList, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str17, int i, long j, long j2) {
        this.name = str;
        this.download_url = str2;
        this.bitstream = str3;
        this.frequency = str4;
        this.format = str5;
        this.file = str6;
        this.lang = str7;
        this.channel = str8;
        this.video_file_name = str9;
        this.oss_fid = str10;
        this.fid = str11;
        this.time = str12;
        this.trackName = str13;
        this.language = str14;
        this.iso3Language = str15;
        this.groupId = str16;
        this.select = z;
        this.autoSelect = z2;
        this.exoAudioTrackInfo = bundle;
        this.tracks = arrayList;
        this.id = num;
        this.index = num2;
        this.vip_only = num3;
        this.org = num4;
        this.trackId = num5;
        this.hlsTrackId = str17;
        this.type = i;
        this.size_bytes = j;
        this.add_time = j2;
    }

    public /* synthetic */ AudioTrackModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, boolean z, boolean z2, Bundle bundle, ArrayList arrayList, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str17, int i, long j, long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) != 0 ? null : str8, (i2 & 256) != 0 ? null : str9, (i2 & 512) != 0 ? null : str10, (i2 & 1024) != 0 ? null : str11, (i2 & 2048) != 0 ? null : str12, (i2 & 4096) != 0 ? null : str13, (i2 & 8192) != 0 ? null : str14, (i2 & 16384) != 0 ? null : str15, (i2 & 32768) != 0 ? null : str16, (i2 & 65536) != 0 ? false : z, (i2 & 131072) != 0 ? false : z2, (i2 & 262144) != 0 ? null : bundle, (i2 & 524288) != 0 ? null : arrayList, (i2 & 1048576) != 0 ? null : num, (i2 & 2097152) != 0 ? null : num2, (i2 & 4194304) != 0 ? null : num3, (i2 & 8388608) != 0 ? null : num4, (i2 & 16777216) != 0 ? null : num5, (i2 & 33554432) != 0 ? null : str17, (i2 & C.BUFFER_FLAG_NOT_DEPENDED_ON) == 0 ? i : 0, (i2 & 134217728) != 0 ? 0L : j, (i2 & 268435456) == 0 ? j2 : 0L);
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getDownload_url() {
        return this.download_url;
    }

    public final void setDownload_url(String str) {
        this.download_url = str;
    }

    public final String getBitstream() {
        return this.bitstream;
    }

    public final void setBitstream(String str) {
        this.bitstream = str;
    }

    public final String getFrequency() {
        return this.frequency;
    }

    public final void setFrequency(String str) {
        this.frequency = str;
    }

    public final String getFormat() {
        return this.format;
    }

    public final void setFormat(String str) {
        this.format = str;
    }

    public final String getFile() {
        return this.file;
    }

    public final void setFile(String str) {
        this.file = str;
    }

    public final String getLang() {
        return this.lang;
    }

    public final void setLang(String str) {
        this.lang = str;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final void setChannel(String str) {
        this.channel = str;
    }

    public final String getVideo_file_name() {
        return this.video_file_name;
    }

    public final void setVideo_file_name(String str) {
        this.video_file_name = str;
    }

    public final String getOss_fid() {
        return this.oss_fid;
    }

    public final void setOss_fid(String str) {
        this.oss_fid = str;
    }

    public final String getFid() {
        return this.fid;
    }

    public final void setFid(String str) {
        this.fid = str;
    }

    public final String getTime() {
        return this.time;
    }

    public final void setTime(String str) {
        this.time = str;
    }

    public final String getTrackName() {
        return this.trackName;
    }

    public final void setTrackName(String str) {
        this.trackName = str;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final void setLanguage(String str) {
        this.language = str;
    }

    public final String getIso3Language() {
        return this.iso3Language;
    }

    public final void setIso3Language(String str) {
        this.iso3Language = str;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    public final void setGroupId(String str) {
        this.groupId = str;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    public final boolean getAutoSelect() {
        return this.autoSelect;
    }

    public final void setAutoSelect(boolean z) {
        this.autoSelect = z;
    }

    public final Bundle getExoAudioTrackInfo() {
        return this.exoAudioTrackInfo;
    }

    public final void setExoAudioTrackInfo(Bundle bundle) {
        this.exoAudioTrackInfo = bundle;
    }

    public final ArrayList<AudioTrackModel> getTracks() {
        return this.tracks;
    }

    public final void setTracks(ArrayList<AudioTrackModel> arrayList) {
        this.tracks = arrayList;
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final Integer getIndex() {
        return this.index;
    }

    public final void setIndex(Integer num) {
        this.index = num;
    }

    public final Integer getVip_only() {
        return this.vip_only;
    }

    public final void setVip_only(Integer num) {
        this.vip_only = num;
    }

    public final Integer getOrg() {
        return this.org;
    }

    public final void setOrg(Integer num) {
        this.org = num;
    }

    public final Integer getTrackId() {
        return this.trackId;
    }

    public final void setTrackId(Integer num) {
        this.trackId = num;
    }

    public final String getHlsTrackId() {
        return this.hlsTrackId;
    }

    public final void setHlsTrackId(String str) {
        this.hlsTrackId = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final long getSize_bytes() {
        return this.size_bytes;
    }

    public final void setSize_bytes(long j) {
        this.size_bytes = j;
    }

    public final long getAdd_time() {
        return this.add_time;
    }

    public final void setAdd_time(long j) {
        this.add_time = j;
    }
}
