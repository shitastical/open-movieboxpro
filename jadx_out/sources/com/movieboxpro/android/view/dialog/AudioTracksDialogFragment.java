package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.AudioTrackModel;
import com.movieboxpro.android.model.AudioTrackResponse;
import com.movieboxpro.android.player.MediaQualityInfo;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.VideoIconUtils;
import com.movieboxpro.android.utils.VlcPlayerManager;
import com.movieboxpro.android.view.fragment.AudioSynDialog;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentAudioTrackBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;

/* JADX INFO: compiled from: AudioTracksDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ,2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002,-B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\tJ\u001a\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001dH\u0002J\u000e\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u0011J\b\u0010'\u001a\u00020\u001dH\u0002J\u001a\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\b\u0010+\u001a\u00020\u001dH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/movieboxpro/android/view/dialog/AudioTracksDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentAudioTrackBinding;", "<init>", "()V", "adapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", "Lcom/movieboxpro/android/model/AudioTrackModel;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/AudioTracksDialogFragment$OnAudioTrackListener;", "lastPos", "", "maxId", "audioTrackList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "id", "", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "boxType", "audioTrackIndex", "mediaQuality", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "onlineTracks", "currAudioId", "media", "Lorg/videolan/libvlc/Media;", "onStart", "", "setListener", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "setDelayText", "text", "getAudioTracks", "getAudioTrackInfo", "Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", FirebaseAnalytics.Param.INDEX, "initData", "Companion", "OnAudioTrackListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AudioTracksDialogFragment extends BaseTransparentDialogFragment<FragmentAudioTrackBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommonBaseAdapter<AudioTrackModel> adapter;
    private int audioTrackIndex;
    private ArrayList<AudioTrackModel> audioTrackList;
    private int boxType;
    private String currAudioId;
    private int episode;
    private String id;
    private int lastPos;
    private OnAudioTrackListener listener;
    private int maxId;
    private Media media;
    private MediaQualityInfo mediaQuality;
    private ArrayList<AudioTrackModel> onlineTracks;
    private int season;

    /* JADX INFO: compiled from: AudioTracksDialogFragment.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0007H&J \u0010\u000e\u001a\u00020\u00032\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012H&J\b\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AudioTracksDialogFragment$OnAudioTrackListener;", "", "onAudioClick", "", "id", "", "url", "", "isAdded", "", "needVip", "audioTrackId", "onAdjustAudioDelay", "ossId", "onOnlineAudios", "audioTracks", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lkotlin/collections/ArrayList;", "addDelay", "subDelay", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnAudioTrackListener {
        void addDelay();

        void onAdjustAudioDelay(String ossId);

        void onAudioClick(int id, String url, boolean isAdded, boolean needVip, String audioTrackId);

        void onOnlineAudios(ArrayList<AudioTrackModel> audioTracks);

        void subDelay();
    }

    public AudioTracksDialogFragment() {
        super(R.layout.fragment_audio_track);
        this.lastPos = -1;
        this.audioTrackList = new ArrayList<>();
        this.season = 1;
        this.episode = 1;
        this.boxType = 1;
        this.audioTrackIndex = 1;
        this.onlineTracks = new ArrayList<>();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.5d);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
    }

    public final void setListener(OnAudioTrackListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private final void initListener() {
        getBinding().ivAudioSyn.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioTracksDialogFragment.initListener$lambda$1(this.f$0, view);
            }
        });
        getBinding().tvTryAgain.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioTracksDialogFragment.initListener$lambda$2(this.f$0, view);
            }
        });
        getBinding().ivDelaySub.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                AudioTracksDialogFragment.initListener$lambda$3(this.f$0, view, z);
            }
        });
        getBinding().ivDelayAdd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                AudioTracksDialogFragment.initListener$lambda$4(this.f$0, view, z);
            }
        });
        getBinding().ivShowTrack.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                AudioTracksDialogFragment.initListener$lambda$5(this.f$0, view, z);
            }
        });
        CommonBaseAdapter<AudioTrackModel> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda9
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AudioTracksDialogFragment.initListener$lambda$7(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getBinding().ivDelayAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioTracksDialogFragment.initListener$lambda$8(this.f$0, view);
            }
        });
        getBinding().ivDelaySub.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioTracksDialogFragment.initListener$lambda$9(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(AudioTracksDialogFragment audioTracksDialogFragment, View view) {
        new AudioSynDialog().show(audioTracksDialogFragment.getChildFragmentManager(), "AudioSynDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(AudioTracksDialogFragment audioTracksDialogFragment, View view) {
        TextView tvTryAgain = audioTracksDialogFragment.getBinding().tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.gone(tvTryAgain);
        audioTracksDialogFragment.getAudioTracks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(AudioTracksDialogFragment audioTracksDialogFragment, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = audioTracksDialogFragment.getBinding().llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.invisible(llAudioTracks);
            audioTracksDialogFragment.getBinding().llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.color_main));
            audioTracksDialogFragment.getBinding().llContainer.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = audioTracksDialogFragment.getBinding().ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.visible(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(AudioTracksDialogFragment audioTracksDialogFragment, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = audioTracksDialogFragment.getBinding().llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.invisible(llAudioTracks);
            audioTracksDialogFragment.getBinding().llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.color_main));
            audioTracksDialogFragment.getBinding().llContainer.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = audioTracksDialogFragment.getBinding().ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.visible(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(AudioTracksDialogFragment audioTracksDialogFragment, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = audioTracksDialogFragment.getBinding().llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.visible(llAudioTracks);
            audioTracksDialogFragment.getBinding().llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = audioTracksDialogFragment.getBinding().ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.gone(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(AudioTracksDialogFragment audioTracksDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<AudioTrackModel> commonBaseAdapter = audioTracksDialogFragment.adapter;
        CommonBaseAdapter<AudioTrackModel> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        AudioTrackModel item = commonBaseAdapter.getItem(i);
        if (item != null) {
            Bundle arguments = audioTracksDialogFragment.getArguments();
            if (arguments != null && !arguments.getBoolean("vlc", false)) {
                String download_url = item.getDownload_url();
                if (download_url == null || StringsKt.isBlank(download_url)) {
                    LinearLayout llDelay = audioTracksDialogFragment.getBinding().llDelay;
                    Intrinsics.checkNotNullExpressionValue(llDelay, "llDelay");
                    CommonExtKt.visible(llDelay);
                    TextView tvAudioHint = audioTracksDialogFragment.getBinding().tvAudioHint;
                    Intrinsics.checkNotNullExpressionValue(tvAudioHint, "tvAudioHint");
                    CommonExtKt.visible(tvAudioHint);
                } else {
                    LinearLayout llDelay2 = audioTracksDialogFragment.getBinding().llDelay;
                    Intrinsics.checkNotNullExpressionValue(llDelay2, "llDelay");
                    CommonExtKt.gone(llDelay2);
                    TextView tvAudioHint2 = audioTracksDialogFragment.getBinding().tvAudioHint;
                    Intrinsics.checkNotNullExpressionValue(tvAudioHint2, "tvAudioHint");
                    CommonExtKt.gone(tvAudioHint2);
                }
            }
            Integer vip_only = item.getVip_only();
            if (vip_only != null && vip_only.intValue() == 1) {
                if (App.getUserData().isvip == 1) {
                    Integer trackId = item.getTrackId();
                    if (trackId != null && trackId.intValue() == 0) {
                        int i2 = audioTracksDialogFragment.maxId + 1;
                        audioTracksDialogFragment.maxId = i2;
                        item.setTrackId(Integer.valueOf(i2));
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    item.setSelect(true);
                    CommonBaseAdapter<AudioTrackModel> commonBaseAdapter3 = audioTracksDialogFragment.adapter;
                    if (commonBaseAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commonBaseAdapter3 = null;
                    }
                    AudioTrackModel itemOrNull = commonBaseAdapter3.getItemOrNull(audioTracksDialogFragment.lastPos);
                    if (itemOrNull != null) {
                        itemOrNull.setSelect(false);
                    }
                    CommonBaseAdapter<AudioTrackModel> commonBaseAdapter4 = audioTracksDialogFragment.adapter;
                    if (commonBaseAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commonBaseAdapter4 = null;
                    }
                    commonBaseAdapter4.notifyItemChanged(i);
                    CommonBaseAdapter<AudioTrackModel> commonBaseAdapter5 = audioTracksDialogFragment.adapter;
                    if (commonBaseAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        commonBaseAdapter2 = commonBaseAdapter5;
                    }
                    commonBaseAdapter2.notifyItemChanged(audioTracksDialogFragment.lastPos);
                    audioTracksDialogFragment.lastPos = i;
                    OnAudioTrackListener onAudioTrackListener = audioTracksDialogFragment.listener;
                    if (onAudioTrackListener != null) {
                        Integer trackId2 = item.getTrackId();
                        int iIntValue = trackId2 != null ? trackId2.intValue() : 0;
                        String download_url2 = item.getDownload_url();
                        Integer vip_only2 = item.getVip_only();
                        onAudioTrackListener.onAudioClick(iIntValue, download_url2, z2, vip_only2 != null && vip_only2.intValue() == 1, item.getOss_fid());
                        return;
                    }
                    return;
                }
                new VipChargeGuideDialog().show(audioTracksDialogFragment.getChildFragmentManager(), "VipChargeGuideDialog");
                return;
            }
            Integer trackId3 = item.getTrackId();
            if (trackId3 != null && trackId3.intValue() == 0) {
                int i3 = audioTracksDialogFragment.maxId + 1;
                audioTracksDialogFragment.maxId = i3;
                item.setTrackId(Integer.valueOf(i3));
                z = false;
            } else {
                z = true;
            }
            item.setSelect(true);
            CommonBaseAdapter<AudioTrackModel> commonBaseAdapter6 = audioTracksDialogFragment.adapter;
            if (commonBaseAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commonBaseAdapter6 = null;
            }
            AudioTrackModel itemOrNull2 = commonBaseAdapter6.getItemOrNull(audioTracksDialogFragment.lastPos);
            if (itemOrNull2 != null) {
                itemOrNull2.setSelect(false);
            }
            CommonBaseAdapter<AudioTrackModel> commonBaseAdapter7 = audioTracksDialogFragment.adapter;
            if (commonBaseAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commonBaseAdapter7 = null;
            }
            commonBaseAdapter7.notifyItemChanged(i);
            CommonBaseAdapter<AudioTrackModel> commonBaseAdapter8 = audioTracksDialogFragment.adapter;
            if (commonBaseAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commonBaseAdapter2 = commonBaseAdapter8;
            }
            commonBaseAdapter2.notifyItemChanged(audioTracksDialogFragment.lastPos);
            audioTracksDialogFragment.lastPos = i;
            OnAudioTrackListener onAudioTrackListener2 = audioTracksDialogFragment.listener;
            if (onAudioTrackListener2 != null) {
                Integer trackId4 = item.getTrackId();
                int iIntValue2 = trackId4 != null ? trackId4.intValue() : 0;
                String download_url3 = item.getDownload_url();
                Integer vip_only3 = item.getVip_only();
                onAudioTrackListener2.onAudioClick(iIntValue2, download_url3, z, vip_only3 != null && vip_only3.intValue() == 1, item.getOss_fid());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$8(AudioTracksDialogFragment audioTracksDialogFragment, View view) {
        OnAudioTrackListener onAudioTrackListener = audioTracksDialogFragment.listener;
        if (onAudioTrackListener != null) {
            onAudioTrackListener.addDelay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$9(AudioTracksDialogFragment audioTracksDialogFragment, View view) {
        OnAudioTrackListener onAudioTrackListener = audioTracksDialogFragment.listener;
        if (onAudioTrackListener != null) {
            onAudioTrackListener.subDelay();
        }
    }

    public final void setDelayText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getBinding().tvDelay.setText(text);
    }

    private final void getAudioTracks() {
        String str;
        String str2;
        String str3;
        int i = this.boxType;
        if (i == 1) {
            str = "Movie_audio";
        } else {
            str = "TV_audio";
        }
        String str4 = str;
        if (i != 1) {
            str2 = "";
        } else {
            str2 = this.id;
        }
        if (i != 2) {
            str3 = "";
        } else {
            str3 = this.id;
        }
        Object objAs = Http.getService().getAudioTracks(str4, App.getUserData().uid_v2, str2, str3, this.season, this.episode).compose(RxUtils.rxTranslate2Bean(AudioTrackResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioTracksDialogFragment.getAudioTracks$lambda$10(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioTracksDialogFragment.getAudioTracks$lambda$11(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioTracksDialogFragment.getAudioTracks$lambda$16(this.f$0, (AudioTrackResponse) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$11(AudioTracksDialogFragment audioTracksDialogFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ProgressBar progressBar = audioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$16(AudioTracksDialogFragment audioTracksDialogFragment, AudioTrackResponse audioTrackResponse) {
        String strReplace$default;
        ProgressBar progressBar = audioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        ArrayList arrayList = new ArrayList();
        List<AudioTrackModel> audio_list = audioTrackResponse.getAudio_list();
        CommonBaseAdapter<AudioTrackModel> commonBaseAdapter = null;
        if (audio_list != null) {
            for (AudioTrackModel audioTrackModel : audio_list) {
                String bitstream = audioTrackModel.getBitstream();
                audioTrackModel.setBitstream((bitstream == null || (strReplace$default = StringsKt.replace$default(bitstream, "kb/s", "", false, 4, (Object) null)) == null) ? null : StringsKt.trim((CharSequence) strReplace$default).toString());
                String fid = audioTrackModel.getFid();
                MediaQualityInfo mediaQualityInfo = audioTracksDialogFragment.mediaQuality;
                if (Intrinsics.areEqual(fid, String.valueOf(mediaQualityInfo != null ? Integer.valueOf(mediaQualityInfo.getFid()) : null))) {
                    arrayList.add(audioTrackModel);
                }
                audioTrackModel.setTrackId(audioTrackModel.getId());
            }
        }
        List<AudioTrackModel> audio_list2 = audioTrackResponse.getAudio_list();
        if (audio_list2 != null) {
            for (AudioTrackModel audioTrackModel2 : audio_list2) {
                String fid2 = audioTrackModel2.getFid();
                MediaQualityInfo mediaQualityInfo2 = audioTracksDialogFragment.mediaQuality;
                if (!Intrinsics.areEqual(fid2, String.valueOf(mediaQualityInfo2 != null ? Integer.valueOf(mediaQualityInfo2.getFid()) : null))) {
                    arrayList.add(audioTrackModel2);
                }
            }
        }
        OnAudioTrackListener onAudioTrackListener = audioTracksDialogFragment.listener;
        if (onAudioTrackListener != null) {
            List<AudioTrackModel> audio_list3 = audioTrackResponse.getAudio_list();
            if (audio_list3 == null) {
                audio_list3 = new ArrayList<>();
            }
            onAudioTrackListener.onOnlineAudios(new ArrayList<>(audio_list3));
        }
        ArrayList arrayList2 = arrayList;
        audioTracksDialogFragment.onlineTracks.addAll(arrayList2);
        audioTracksDialogFragment.audioTrackList.addAll(arrayList2);
        ArrayList<AudioTrackModel> arrayList3 = audioTracksDialogFragment.audioTrackList;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : arrayList3) {
            String video_file_name = ((AudioTrackModel) obj).getVideo_file_name();
            Object obj2 = linkedHashMap.get(video_file_name);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(video_file_name, obj2);
            }
            ((List) obj2).add(obj);
        }
        audioTracksDialogFragment.audioTrackList.clear();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            audioTracksDialogFragment.audioTrackList.addAll((Collection) ((Map.Entry) it.next()).getValue());
        }
        CommonBaseAdapter<AudioTrackModel> commonBaseAdapter2 = audioTracksDialogFragment.adapter;
        if (commonBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter = commonBaseAdapter2;
        }
        commonBaseAdapter.setList(audioTracksDialogFragment.audioTrackList);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$10(AudioTracksDialogFragment audioTracksDialogFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ProgressBar progressBar = audioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        TextView tvTryAgain = audioTracksDialogFragment.getBinding().tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.visible(tvTryAgain);
        audioTracksDialogFragment.getBinding().tvTryAgain.setText("Load failed:" + it.getMessage() + ",tap to try again");
        return Unit.INSTANCE;
    }

    private final IMedia.AudioTrack getAudioTrackInfo(int index, int id) {
        Media media = this.media;
        if (media == null) {
            return null;
        }
        IMedia.Track track = media.getTrack(index);
        if (track instanceof IMedia.AudioTrack) {
            IMedia.AudioTrack audioTrack = (IMedia.AudioTrack) track;
            if (audioTrack.id == id) {
                return audioTrack;
            }
        }
        int trackCount = media.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            IMedia.Track track2 = media.getTrack(i);
            if (track2 instanceof IMedia.AudioTrack) {
                IMedia.AudioTrack audioTrack2 = (IMedia.AudioTrack) track2;
                if (audioTrack2.id == id) {
                    return audioTrack2;
                }
            }
        }
        return null;
    }

    private final void initData() {
        ArrayList<AudioTrackModel> arrayList;
        this.audioTrackList.clear();
        Bundle arguments = getArguments();
        CommonBaseAdapter<AudioTrackModel> commonBaseAdapter = null;
        this.mediaQuality = arguments != null ? (MediaQualityInfo) arguments.getParcelable("quality") : null;
        Bundle arguments2 = getArguments();
        this.id = arguments2 != null ? arguments2.getString("id") : null;
        Bundle arguments3 = getArguments();
        this.currAudioId = arguments3 != null ? arguments3.getString("ossFid") : null;
        Bundle arguments4 = getArguments();
        this.season = arguments4 != null ? arguments4.getInt(DealWatchNextService.SEASON) : 1;
        Bundle arguments5 = getArguments();
        this.episode = arguments5 != null ? arguments5.getInt(DealWatchNextService.EPISODE) : 1;
        Bundle arguments6 = getArguments();
        this.boxType = arguments6 != null ? arguments6.getInt("boxType") : 1;
        Bundle arguments7 = getArguments();
        this.audioTrackIndex = arguments7 != null ? arguments7.getInt("audioId", 0) : 1;
        Bundle arguments8 = getArguments();
        if (arguments8 == null || (arrayList = arguments8.getParcelableArrayList("data")) == null) {
            arrayList = new ArrayList<>();
        }
        this.audioTrackList = arrayList;
        Bundle arguments9 = getArguments();
        ArrayList<AudioTrackModel> parcelableArrayList = arguments9 != null ? arguments9.getParcelableArrayList("onlineTracks") : null;
        Bundle arguments10 = getArguments();
        if (arguments10 != null && arguments10.getBoolean("vlc", false)) {
            Context context = getContext();
            if (context != null) {
                IMedia media = VlcPlayerManager.INSTANCE.get(context).getMediaPlayer().getMedia();
                this.media = media instanceof Media ? (Media) media : null;
            }
            int i = 0;
            for (Object obj : this.audioTrackList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                AudioTrackModel audioTrackModel = (AudioTrackModel) obj;
                Integer trackId = audioTrackModel.getTrackId();
                IMedia.AudioTrack audioTrackInfo = getAudioTrackInfo(i2, trackId != null ? trackId.intValue() : 0);
                if (audioTrackInfo != null) {
                    if (audioTrackInfo.bitrate == 0) {
                        audioTrackModel.setBitstream(null);
                    } else {
                        audioTrackModel.setBitstream(String.valueOf(audioTrackInfo.bitrate / 1000));
                    }
                    audioTrackModel.setChannel(String.valueOf(audioTrackInfo.channels));
                    audioTrackModel.setLang(audioTrackInfo.language);
                    audioTrackModel.setFormat(audioTrackInfo.originalCodec);
                }
                i = i2;
            }
            if (!this.audioTrackList.isEmpty()) {
                Integer trackId2 = ((AudioTrackModel) CollectionsKt.last((List) this.audioTrackList)).getTrackId();
                this.maxId = trackId2 != null ? trackId2.intValue() : 0;
            }
        }
        Bundle arguments11 = getArguments();
        int i3 = arguments11 != null ? arguments11.getInt("audioDelay", 0) : 0;
        if (i3 == 0) {
            getBinding().tvDelay.setText("0s");
        } else {
            TextView textView = getBinding().tvDelay;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf((-i3) / 1000.0f)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            textView.setText(str + CmcdData.STREAMING_FORMAT_SS);
        }
        String str2 = this.currAudioId;
        if (str2 == null || StringsKt.isBlank(str2)) {
            int i4 = 0;
            for (AudioTrackModel audioTrackModel2 : this.audioTrackList) {
                int i5 = i4 + 1;
                Integer trackId3 = audioTrackModel2.getTrackId();
                int i6 = this.audioTrackIndex;
                if (trackId3 != null && trackId3.intValue() == i6) {
                    audioTrackModel2.setSelect(true);
                    this.lastPos = i4;
                    break;
                }
                i4 = i5;
            }
            if (parcelableArrayList != null) {
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    ((AudioTrackModel) it.next()).setSelect(false);
                }
            }
            Bundle arguments12 = getArguments();
            if (arguments12 != null && !arguments12.getBoolean("vlc", false)) {
                LinearLayout llDelay = getBinding().llDelay;
                Intrinsics.checkNotNullExpressionValue(llDelay, "llDelay");
                CommonExtKt.gone(llDelay);
                ImageView ivAudioSyn = getBinding().ivAudioSyn;
                Intrinsics.checkNotNullExpressionValue(ivAudioSyn, "ivAudioSyn");
                CommonExtKt.gone(ivAudioSyn);
            }
            TextView tvAudioHint = getBinding().tvAudioHint;
            Intrinsics.checkNotNullExpressionValue(tvAudioHint, "tvAudioHint");
            CommonExtKt.gone(tvAudioHint);
        } else {
            if (parcelableArrayList != null) {
                Iterator it2 = parcelableArrayList.iterator();
                while (it2.hasNext()) {
                    ((AudioTrackModel) it2.next()).setSelect(false);
                }
            }
            if (parcelableArrayList != null) {
                for (AudioTrackModel audioTrackModel3 : parcelableArrayList) {
                    if (audioTrackModel3.getOss_fid() == this.currAudioId) {
                        audioTrackModel3.setSelect(true);
                    }
                }
            }
            LinearLayout llDelay2 = getBinding().llDelay;
            Intrinsics.checkNotNullExpressionValue(llDelay2, "llDelay");
            CommonExtKt.visible(llDelay2);
            TextView tvAudioHint2 = getBinding().tvAudioHint;
            Intrinsics.checkNotNullExpressionValue(tvAudioHint2, "tvAudioHint");
            CommonExtKt.visible(tvAudioHint2);
        }
        ArrayList arrayList2 = parcelableArrayList;
        if (arrayList2 == null || arrayList2.isEmpty()) {
            getAudioTracks();
        } else {
            ProgressBar progressBar = getBinding().progressBar;
            Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
            CommonExtKt.gone(progressBar);
            this.audioTrackList.addAll(arrayList2);
        }
        ArrayList<AudioTrackModel> arrayList3 = this.audioTrackList;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : arrayList3) {
            String video_file_name = ((AudioTrackModel) obj2).getVideo_file_name();
            Object obj3 = linkedHashMap.get(video_file_name);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                linkedHashMap.put(video_file_name, obj3);
            }
            ((List) obj3).add(obj2);
        }
        this.audioTrackList.clear();
        Iterator it3 = linkedHashMap.entrySet().iterator();
        while (it3.hasNext()) {
            this.audioTrackList.addAll((Collection) ((Map.Entry) it3.next()).getValue());
        }
        this.adapter = new CommonBaseAdapter<AudioTrackModel>(R.layout.adapter_audio_track_item, this.audioTrackList) { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment.initData.7
            {
                ArrayList<AudioTrackModel> arrayList4 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, AudioTrackModel item) {
                int i7;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView2 = (TextView) holder.getView(R.id.tv_size);
                String download_url = item.getDownload_url();
                if (download_url == null || StringsKt.isBlank(download_url)) {
                    CommonExtKt.gone(textView2);
                } else {
                    CommonExtKt.visible(textView2);
                    textView2.setText(FileUtils.byte2FitMemorySize(item.getSize_bytes()));
                }
                ImageView imageView = (ImageView) holder.getView(R.id.iv_selected);
                if (item.getSelect()) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.gone(imageView);
                }
                ImageView imageView2 = (ImageView) holder.getView(R.id.ivLang);
                ImageView imageView3 = (ImageView) holder.getView(R.id.ivAudioCode);
                ImageView imageView4 = (ImageView) holder.getView(R.id.ivChannel);
                TextView textView3 = (TextView) holder.getView(R.id.tvRate);
                ImageView imageView5 = (ImageView) holder.getView(R.id.ivVip);
                TextView textView4 = (TextView) holder.getView(R.id.tvLang);
                Integer vip_only = item.getVip_only();
                if (vip_only != null && vip_only.intValue() == 0) {
                    CommonExtKt.gone(imageView2);
                    CommonExtKt.gone(imageView5);
                } else {
                    CommonExtKt.visible(imageView5);
                }
                String bitstream = item.getBitstream();
                if (bitstream != null && !StringsKt.isBlank(bitstream)) {
                    try {
                        String bitstream2 = item.getBitstream();
                        Intrinsics.checkNotNull(bitstream2);
                        i7 = Integer.parseInt(bitstream2);
                    } catch (Exception unused) {
                        i7 = 0;
                    }
                    if (i7 >= 1014) {
                        CommonExtKt.visible(textView3);
                        textView3.setText(new DecimalFormat(IdManager.DEFAULT_VERSION_NAME).format(Float.valueOf(i7 / 1024.0f)) + "M");
                    } else if (i7 == 0) {
                        CommonExtKt.gone(textView3);
                    } else {
                        textView3.setText(i7 + "K");
                    }
                } else {
                    CommonExtKt.gone(textView3);
                }
                int audioChannelIcon = VideoIconUtils.INSTANCE.getAudioChannelIcon(item.getChannel());
                if (audioChannelIcon == -1) {
                    CommonExtKt.gone(imageView4);
                } else {
                    imageView4.setImageResource(audioChannelIcon);
                }
                int audioCodeIcon = VideoIconUtils.INSTANCE.getAudioCodeIcon(item.getFormat());
                if (audioCodeIcon == -1) {
                    CommonExtKt.gone(imageView3);
                } else {
                    CommonExtKt.visible(imageView3);
                    imageView3.setImageResource(audioCodeIcon);
                }
                int audioLangIcon = VideoIconUtils.INSTANCE.getAudioLangIcon(item.getLang());
                if (audioLangIcon == -1) {
                    CommonExtKt.gone(imageView2);
                    String lang = item.getLang();
                    if (lang == null || StringsKt.isBlank(lang)) {
                        CommonExtKt.gone(textView4);
                        return;
                    } else {
                        CommonExtKt.visible(textView4);
                        textView4.setText(item.getLang());
                        return;
                    }
                }
                CommonExtKt.gone(textView4);
                CommonExtKt.visible(imageView2);
                imageView2.setImageResource(audioLangIcon);
            }
        };
        TvRecyclerView recyclerView = getBinding().recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        CommonBaseAdapter<AudioTrackModel> commonBaseAdapter2 = this.adapter;
        if (commonBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter = commonBaseAdapter2;
        }
        tvRecyclerView.setAdapter(commonBaseAdapter);
        PowerfulStickyDecoration powerfulStickyDecorationBuild = PowerfulStickyDecoration.Builder.init(new PowerGroupListener() { // from class: com.movieboxpro.android.view.dialog.AudioTracksDialogFragment$initData$decoration$1
            @Override // com.gavin.com.library.listener.PowerGroupListener
            public boolean needDrawDecoration(int position) {
                return true;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.gavin.com.library.listener.GroupListener
            public String getGroupName(int position) {
                String video_file_name2;
                CommonBaseAdapter commonBaseAdapter3 = this.this$0.adapter;
                if (commonBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commonBaseAdapter3 = null;
                }
                AudioTrackModel audioTrackModel4 = (AudioTrackModel) commonBaseAdapter3.getItemOrNull(position);
                return (audioTrackModel4 == null || (video_file_name2 = audioTrackModel4.getVideo_file_name()) == null) ? "" : video_file_name2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.gavin.com.library.listener.PowerGroupListener
            public View getGroupView(int position) {
                CommonBaseAdapter commonBaseAdapter3 = null;
                View viewInflate = LayoutInflater.from(this.this$0.getContext()).inflate(R.layout.video_quality_title, (ViewGroup) null, false);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.tvName);
                TextView textView3 = (TextView) viewInflate.findViewById(R.id.tvTime);
                CommonBaseAdapter commonBaseAdapter4 = this.this$0.adapter;
                if (commonBaseAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commonBaseAdapter4 = null;
                }
                AudioTrackModel audioTrackModel4 = (AudioTrackModel) commonBaseAdapter4.getItemOrNull(position);
                if (audioTrackModel4 != null) {
                    AudioTracksDialogFragment audioTracksDialogFragment = this.this$0;
                    Intrinsics.checkNotNull(textView3);
                    CommonExtKt.visible(textView3);
                    if (audioTrackModel4.getTime() == null) {
                        textView3.setText(TimeUtils.formatTime4(audioTrackModel4.getAdd_time() * ((long) 1000)));
                    } else {
                        textView3.setText(audioTrackModel4.getTime());
                    }
                    CommonBaseAdapter commonBaseAdapter5 = audioTracksDialogFragment.adapter;
                    if (commonBaseAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commonBaseAdapter5 = null;
                    }
                    AudioTrackModel audioTrackModel5 = (AudioTrackModel) commonBaseAdapter5.getItemOrNull(position);
                    textView2.setText(audioTrackModel5 != null ? audioTrackModel5.getVideo_file_name() : null);
                }
                CommonBaseAdapter commonBaseAdapter6 = this.this$0.adapter;
                if (commonBaseAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commonBaseAdapter3 = commonBaseAdapter6;
                }
                AudioTrackModel audioTrackModel6 = (AudioTrackModel) commonBaseAdapter3.getItemOrNull(position);
                if (audioTrackModel6 != null) {
                    audioTrackModel6.getTime();
                }
                Intrinsics.checkNotNull(viewInflate);
                return viewInflate;
            }
        }).setGroupBackground(CommonExtKt.colorInt(R.color.color_main_back)).setDivideHeight(0).setGroupHeight((int) ScreenUtils.dpToPx(70.0f)).setCacheEnable(true).setSticky(false).build();
        V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 1);
        if (powerfulStickyDecorationBuild != null) {
            powerfulStickyDecorationBuild.resetSpan(getBinding().recyclerView, v7GridLayoutManager);
        }
        getBinding().recyclerView.setLayoutManager(v7GridLayoutManager);
        TvRecyclerView tvRecyclerView2 = getBinding().recyclerView;
        Intrinsics.checkNotNull(powerfulStickyDecorationBuild);
        tvRecyclerView2.addItemDecoration(powerfulStickyDecorationBuild);
    }

    /* JADX INFO: compiled from: AudioTracksDialogFragment.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0084\u0001\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u001a\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0012J\u007f\u0010\u0004\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u001a\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012¢\u0006\u0002\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AudioTracksDialogFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/AudioTracksDialogFragment;", "currString", "", "mediaQuality", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "tracks", "Ljava/util/ArrayList;", "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;", "Lkotlin/collections/ArrayList;", "onlineAudioTracks", "Lcom/movieboxpro/android/model/AudioTrackModel;", "id", "boxType", "", "pos", "audioDelay", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "", "Ltv/danmaku/ijk/media/player/misc/ITrackInfo;", "audioId", "ossFid", "(Lcom/movieboxpro/android/player/MediaQualityInfo;[Ltv/danmaku/ijk/media/player/misc/ITrackInfo;Ljava/util/ArrayList;Ljava/lang/String;IILjava/lang/String;III)Lcom/movieboxpro/android/view/dialog/AudioTracksDialogFragment;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AudioTracksDialogFragment newInstance(String currString, MediaQualityInfo mediaQuality, ArrayList<MediaPlayer.TrackDescription> tracks, ArrayList<AudioTrackModel> onlineAudioTracks, String id, int boxType, int pos, int audioDelay, int season, int episode) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (MediaPlayer.TrackDescription trackDescription : tracks) {
                if (trackDescription.id != -1) {
                    AudioTrackModel audioTrackModel = new AudioTrackModel(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, 0, 0L, 0L, 536870911, null);
                    audioTrackModel.setName(trackDescription.name);
                    audioTrackModel.setFid(String.valueOf(mediaQuality != null ? Integer.valueOf(mediaQuality.getFid()) : null));
                    audioTrackModel.setVideo_file_name(mediaQuality != null ? mediaQuality.getTitle() : null);
                    audioTrackModel.setTime(mediaQuality != null ? mediaQuality.getTime() : null);
                    audioTrackModel.setTrackId(Integer.valueOf(trackDescription.id));
                    arrayList.add(audioTrackModel);
                }
            }
            AudioTracksDialogFragment audioTracksDialogFragment = new AudioTracksDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("data", arrayList);
            bundle.putParcelableArrayList("onlineTracks", onlineAudioTracks);
            bundle.putString("id", id);
            bundle.putInt("boxType", boxType);
            bundle.putInt("audioId", pos);
            bundle.putInt(DealWatchNextService.SEASON, season);
            bundle.putInt(DealWatchNextService.EPISODE, episode);
            bundle.putParcelable("quality", mediaQuality);
            bundle.putString("ossFid", currString);
            bundle.putInt("audioDelay", audioDelay);
            bundle.putBoolean("vlc", true);
            audioTracksDialogFragment.setArguments(bundle);
            return audioTracksDialogFragment;
        }

        public final AudioTracksDialogFragment newInstance(MediaQualityInfo mediaQuality, ITrackInfo[] tracks, ArrayList<AudioTrackModel> onlineAudioTracks, String id, int boxType, int audioId, String ossFid, int season, int episode, int audioDelay) {
            Intrinsics.checkNotNullParameter(ossFid, "ossFid");
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            if (tracks != null) {
                int length = tracks.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    ITrackInfo iTrackInfo = tracks[i];
                    int i3 = i2 + 1;
                    if (iTrackInfo.getTrackType() == 2 && (iTrackInfo instanceof IjkTrackInfo)) {
                        AudioTrackModel audioTrackModel = new AudioTrackModel(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, 0, 0L, 0L, 536870911, null);
                        IjkTrackInfo ijkTrackInfo = (IjkTrackInfo) iTrackInfo;
                        audioTrackModel.setName(ijkTrackInfo.getLanguage());
                        audioTrackModel.setFid(String.valueOf(mediaQuality != null ? Integer.valueOf(mediaQuality.getFid()) : null));
                        audioTrackModel.setVideo_file_name(mediaQuality != null ? mediaQuality.getTitle() : null);
                        audioTrackModel.setTime(mediaQuality != null ? mediaQuality.getTime() : null);
                        if (ijkTrackInfo.getFormat() instanceof IjkMediaFormat) {
                            audioTrackModel.setBitstream(String.valueOf(ijkTrackInfo.getFormat().getInteger(IjkMediaMeta.IJKM_KEY_BITRATE) / 1000));
                            audioTrackModel.setChannel(String.valueOf(ijkTrackInfo.getFormat().getInteger(IjkMediaMeta.IJKM_KEY_CHANNEL_LAYOUT) - 1));
                            audioTrackModel.setFormat(ijkTrackInfo.getFormat().getString(IjkMediaMeta.IJKM_KEY_CODEC_NAME));
                            audioTrackModel.setLang(ijkTrackInfo.getFormat().getString("language"));
                        }
                        audioTrackModel.setTrackId(Integer.valueOf(i2));
                        arrayList.add(audioTrackModel);
                    }
                    i++;
                    i2 = i3;
                }
            }
            AudioTracksDialogFragment audioTracksDialogFragment = new AudioTracksDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("data", arrayList);
            bundle.putParcelableArrayList("onlineTracks", onlineAudioTracks);
            bundle.putString("id", id);
            bundle.putInt("boxType", boxType);
            bundle.putString("ossFid", ossFid);
            bundle.putInt(DealWatchNextService.SEASON, season);
            bundle.putInt("audioId", audioId);
            bundle.putInt(DealWatchNextService.EPISODE, episode);
            bundle.putParcelable("quality", mediaQuality);
            bundle.putInt("audioDelay", audioDelay);
            bundle.putBoolean("vlc", false);
            audioTracksDialogFragment.setArguments(bundle);
            return audioTracksDialogFragment;
        }
    }
}
