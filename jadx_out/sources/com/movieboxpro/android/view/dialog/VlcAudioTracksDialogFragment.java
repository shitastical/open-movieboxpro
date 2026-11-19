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
import androidx.media3.common.C;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.app.App;
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
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;

/* JADX INFO: compiled from: VlcAudioTracksDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 72\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003678B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\nJ\u001a\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001dH\u0002J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u0007H\u0002J\u000e\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020\u0011J\b\u0010)\u001a\u00020\u001dH\u0002J\"\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u0011H\u0002J\u0012\u0010.\u001a\u00020/2\b\u0010-\u001a\u0004\u0018\u00010\u0011H\u0002J \u00100\u001a\u00020\u001d2\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fH\u0002J0\u00102\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000f2\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fH\u0002J\u0018\u00103\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fH\u0002J\b\u00104\u001a\u00020\u001dH\u0002J\b\u00105\u001a\u00020\u001dH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentAudioTrackBinding;", "<init>", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseDelegateMultiAdapter;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment$OnAudioTrackListener;", "maxId", "", "audioTrackList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "id", "", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "boxType", "audioTrackIndex", "mediaQuality", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "onlineTracks", "currAudioId", "media", "Lorg/videolan/libvlc/Media;", "onStart", "", "setListener", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "handleTrackClick", "item", "setDelayText", "text", "getAudioTracks", "getAudioTrackInfo", "Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", FirebaseAnalytics.Param.INDEX, "name", "parseVlcGroupInfo", "Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment$VlcGroupInfo;", "applyVlcTrackGrouping", "list", "buildVlcGroupedTracks", "buildDisplayList", "refreshDisplayList", "initData", "VlcGroupInfo", "Companion", "OnAudioTrackListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VlcAudioTracksDialogFragment extends BaseTransparentDialogFragment<FragmentAudioTrackBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> adapter;
    private int audioTrackIndex;
    private ArrayList<AudioTrackModel> audioTrackList;
    private int boxType;
    private String currAudioId;
    private int episode;
    private String id;
    private OnAudioTrackListener listener;
    private int maxId;
    private Media media;
    private MediaQualityInfo mediaQuality;
    private ArrayList<AudioTrackModel> onlineTracks;
    private int season;

    /* JADX INFO: compiled from: VlcAudioTracksDialogFragment.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0007H&J \u0010\u000e\u001a\u00020\u00032\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012H&J\b\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment$OnAudioTrackListener;", "", "onAudioClick", "", "id", "", "url", "", "isAdded", "", "needVip", "audioTrackId", "onAdjustAudioDelay", "ossId", "onOnlineAudios", "audioTracks", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lkotlin/collections/ArrayList;", "addDelay", "subDelay", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnAudioTrackListener {
        void addDelay();

        void onAdjustAudioDelay(String ossId);

        void onAudioClick(int id, String url, boolean isAdded, boolean needVip, String audioTrackId);

        void onOnlineAudios(ArrayList<AudioTrackModel> audioTracks);

        void subDelay();
    }

    public VlcAudioTracksDialogFragment() {
        super(R.layout.fragment_audio_track);
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
        getBinding().ivAudioSyn.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VlcAudioTracksDialogFragment.initListener$lambda$1(this.f$0, view);
            }
        });
        getBinding().tvTryAgain.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VlcAudioTracksDialogFragment.initListener$lambda$2(this.f$0, view);
            }
        });
        getBinding().ivDelaySub.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                VlcAudioTracksDialogFragment.initListener$lambda$3(this.f$0, view, z);
            }
        });
        getBinding().ivDelayAdd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                VlcAudioTracksDialogFragment.initListener$lambda$4(this.f$0, view, z);
            }
        });
        getBinding().ivShowTrack.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                VlcAudioTracksDialogFragment.initListener$lambda$5(this.f$0, view, z);
            }
        });
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter = this.adapter;
        if (baseDelegateMultiAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseDelegateMultiAdapter = null;
        }
        baseDelegateMultiAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VlcAudioTracksDialogFragment.initListener$lambda$6(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getBinding().ivDelayAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VlcAudioTracksDialogFragment.initListener$lambda$7(this.f$0, view);
            }
        });
        getBinding().ivDelaySub.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VlcAudioTracksDialogFragment.initListener$lambda$8(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, View view) {
        new AudioSynDialog().show(vlcAudioTracksDialogFragment.getChildFragmentManager(), "AudioSynDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, View view) {
        TextView tvTryAgain = vlcAudioTracksDialogFragment.getBinding().tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.gone(tvTryAgain);
        vlcAudioTracksDialogFragment.getAudioTracks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = vlcAudioTracksDialogFragment.getBinding().llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.invisible(llAudioTracks);
            vlcAudioTracksDialogFragment.getBinding().llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.color_main));
            vlcAudioTracksDialogFragment.getBinding().llContainer.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = vlcAudioTracksDialogFragment.getBinding().ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.visible(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = vlcAudioTracksDialogFragment.getBinding().llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.invisible(llAudioTracks);
            vlcAudioTracksDialogFragment.getBinding().llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.color_main));
            vlcAudioTracksDialogFragment.getBinding().llContainer.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = vlcAudioTracksDialogFragment.getBinding().ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.visible(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = vlcAudioTracksDialogFragment.getBinding().llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.visible(llAudioTracks);
            vlcAudioTracksDialogFragment.getBinding().llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = vlcAudioTracksDialogFragment.getBinding().ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.gone(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        ArrayList<AudioTrackModel> tracks;
        ArrayList<AudioTrackModel> tracks2;
        AudioTrackModel audioTrackModel;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter = vlcAudioTracksDialogFragment.adapter;
        if (baseDelegateMultiAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseDelegateMultiAdapter = null;
        }
        AudioTrackModel item = baseDelegateMultiAdapter.getItem(i);
        if (item == null) {
            return;
        }
        if (item.getType() == 1 && (tracks = item.getTracks()) != null && !tracks.isEmpty() && (tracks2 = item.getTracks()) != null && (audioTrackModel = (AudioTrackModel) CollectionsKt.firstOrNull((List) tracks2)) != null) {
            item = audioTrackModel;
        }
        vlcAudioTracksDialogFragment.handleTrackClick(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, View view) {
        OnAudioTrackListener onAudioTrackListener = vlcAudioTracksDialogFragment.listener;
        if (onAudioTrackListener != null) {
            onAudioTrackListener.addDelay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$8(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, View view) {
        OnAudioTrackListener onAudioTrackListener = vlcAudioTracksDialogFragment.listener;
        if (onAudioTrackListener != null) {
            onAudioTrackListener.subDelay();
        }
    }

    private final void handleTrackClick(AudioTrackModel item) {
        boolean z;
        Integer trackId;
        Integer vip_only = item.getVip_only();
        if (vip_only != null && vip_only.intValue() == 1 && App.getUserData().isvip != 1) {
            new VipChargeGuideDialog().show(getChildFragmentManager(), "VipChargeGuideDialog");
            return;
        }
        if (item.getTrackId() == null || ((trackId = item.getTrackId()) != null && trackId.intValue() == 0)) {
            int i = this.maxId + 1;
            this.maxId = i;
            item.setTrackId(Integer.valueOf(i));
            z = false;
        } else {
            z = true;
        }
        Iterator<T> it = this.audioTrackList.iterator();
        while (it.hasNext()) {
            ((AudioTrackModel) it.next()).setSelect(false);
        }
        item.setSelect(true);
        refreshDisplayList();
        OnAudioTrackListener onAudioTrackListener = this.listener;
        if (onAudioTrackListener != null) {
            Integer trackId2 = item.getTrackId();
            int iIntValue = trackId2 != null ? trackId2.intValue() : 0;
            String download_url = item.getDownload_url();
            Integer vip_only2 = item.getVip_only();
            onAudioTrackListener.onAudioClick(iIntValue, download_url, z, vip_only2 != null && vip_only2.intValue() == 1, item.getOss_fid());
        }
    }

    public final void setDelayText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getBinding().tvDelay.setText(text);
    }

    private final void getAudioTracks() {
        String str;
        String str2;
        int i = this.boxType;
        if (i == 1) {
            str = "Movie_audio";
        } else {
            str = "TV_audio";
        }
        String str3 = str;
        String str4 = i == 1 ? this.id : "";
        if (i != 2) {
            str2 = "";
        } else {
            str2 = this.id;
        }
        Object objAs = Http.getService().getAudioTracks(str3, App.getUserData().uid_v2, str4, str2, this.season, this.episode).compose(RxUtils.rxTranslate2Bean(AudioTrackResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VlcAudioTracksDialogFragment.getAudioTracks$lambda$10(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VlcAudioTracksDialogFragment.getAudioTracks$lambda$11(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VlcAudioTracksDialogFragment.getAudioTracks$lambda$14(this.f$0, (AudioTrackResponse) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$11(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ProgressBar progressBar = vlcAudioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$14(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, AudioTrackResponse audioTrackResponse) {
        String strReplace$default;
        ProgressBar progressBar = vlcAudioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        ArrayList arrayList = new ArrayList();
        List<AudioTrackModel> audio_list = audioTrackResponse.getAudio_list();
        if (audio_list != null) {
            for (AudioTrackModel audioTrackModel : audio_list) {
                String bitstream = audioTrackModel.getBitstream();
                audioTrackModel.setBitstream((bitstream == null || (strReplace$default = StringsKt.replace$default(bitstream, "kb/s", "", false, 4, (Object) null)) == null) ? null : StringsKt.trim((CharSequence) strReplace$default).toString());
                audioTrackModel.setTrackId(audioTrackModel.getId());
                String fid = audioTrackModel.getFid();
                MediaQualityInfo mediaQualityInfo = vlcAudioTracksDialogFragment.mediaQuality;
                if (Intrinsics.areEqual(fid, String.valueOf(mediaQualityInfo != null ? Integer.valueOf(mediaQualityInfo.getFid()) : null))) {
                    arrayList.add(audioTrackModel);
                }
            }
        }
        List<AudioTrackModel> audio_list2 = audioTrackResponse.getAudio_list();
        if (audio_list2 != null) {
            for (AudioTrackModel audioTrackModel2 : audio_list2) {
                String fid2 = audioTrackModel2.getFid();
                MediaQualityInfo mediaQualityInfo2 = vlcAudioTracksDialogFragment.mediaQuality;
                if (!Intrinsics.areEqual(fid2, String.valueOf(mediaQualityInfo2 != null ? Integer.valueOf(mediaQualityInfo2.getFid()) : null))) {
                    arrayList.add(audioTrackModel2);
                }
            }
        }
        OnAudioTrackListener onAudioTrackListener = vlcAudioTracksDialogFragment.listener;
        if (onAudioTrackListener != null) {
            List<AudioTrackModel> audio_list3 = audioTrackResponse.getAudio_list();
            if (audio_list3 == null) {
                audio_list3 = new ArrayList<>();
            }
            onAudioTrackListener.onOnlineAudios(new ArrayList<>(audio_list3));
        }
        vlcAudioTracksDialogFragment.onlineTracks.clear();
        ArrayList arrayList2 = arrayList;
        vlcAudioTracksDialogFragment.onlineTracks.addAll(arrayList2);
        vlcAudioTracksDialogFragment.audioTrackList.addAll(arrayList2);
        vlcAudioTracksDialogFragment.refreshDisplayList();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$10(VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ProgressBar progressBar = vlcAudioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        TextView tvTryAgain = vlcAudioTracksDialogFragment.getBinding().tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.visible(tvTryAgain);
        vlcAudioTracksDialogFragment.getBinding().tvTryAgain.setText("Load failed:" + it.getMessage() + ",tap to try again");
        return Unit.INSTANCE;
    }

    private final IMedia.AudioTrack getAudioTrackInfo(int index, int id, String name) {
        Media media = this.media;
        if (media != null) {
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
        }
        Media media2 = this.media;
        if (media2 != null) {
            int trackCount2 = media2.getTrackCount();
            for (int i2 = 0; i2 < trackCount2; i2++) {
                IMedia.Track track3 = media2.getTrack(i2);
                if (track3 instanceof IMedia.AudioTrack) {
                    String str = name;
                    IMedia.AudioTrack audioTrack3 = (IMedia.AudioTrack) track3;
                    String str2 = audioTrack3.description;
                    if (str2 == null) {
                        str2 = "";
                    }
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) str2, false, 2, (Object) null)) {
                        return audioTrack3;
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: compiled from: VlcAudioTracksDialogFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment$VlcGroupInfo;", "", "langToken", "", "groupId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getLangToken", "()Ljava/lang/String;", "getGroupId", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class VlcGroupInfo {
        private final String groupId;
        private final String langToken;

        public static /* synthetic */ VlcGroupInfo copy$default(VlcGroupInfo vlcGroupInfo, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = vlcGroupInfo.langToken;
            }
            if ((i & 2) != 0) {
                str2 = vlcGroupInfo.groupId;
            }
            return vlcGroupInfo.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLangToken() {
            return this.langToken;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getGroupId() {
            return this.groupId;
        }

        public final VlcGroupInfo copy(String langToken, String groupId) {
            return new VlcGroupInfo(langToken, groupId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VlcGroupInfo)) {
                return false;
            }
            VlcGroupInfo vlcGroupInfo = (VlcGroupInfo) other;
            return Intrinsics.areEqual(this.langToken, vlcGroupInfo.langToken) && Intrinsics.areEqual(this.groupId, vlcGroupInfo.groupId);
        }

        public int hashCode() {
            String str = this.langToken;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.groupId;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "VlcGroupInfo(langToken=" + this.langToken + ", groupId=" + this.groupId + ")";
        }

        public VlcGroupInfo(String str, String str2) {
            this.langToken = str;
            this.groupId = str2;
        }

        public final String getLangToken() {
            return this.langToken;
        }

        public final String getGroupId() {
            return this.groupId;
        }
    }

    private final VlcGroupInfo parseVlcGroupInfo(String name) {
        MatchResult matchResultFind$default;
        List<String> groupValues;
        String lowerCase;
        List<String> groupValues2;
        String str = name;
        String str2 = null;
        if (str == null || StringsKt.isBlank(str)) {
            return new VlcGroupInfo(null, null);
        }
        MatchResult matchResultFind$default2 = Regex.find$default(new Regex("audio_[^\\s]+\\s+([A-Za-z]{2,10})"), str, 0, 2, null);
        String str3 = (matchResultFind$default2 == null || (groupValues2 = matchResultFind$default2.getGroupValues()) == null) ? null : (String) CollectionsKt.getOrNull(groupValues2, 1);
        String str4 = str3;
        String str5 = ((str4 != null && !StringsKt.isBlank(str4)) || (matchResultFind$default = Regex.find$default(new Regex("\\[([^\\]]+)]"), str, 0, 2, null)) == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? null : (String) CollectionsKt.getOrNull(groupValues, 1);
        if (str3 == null) {
            str3 = str5;
        }
        String string = str3 != null ? StringsKt.trim((CharSequence) str3).toString() : null;
        MatchResult matchResultFind$default3 = Regex.find$default(new Regex("#\\d+"), str, 0, 2, null);
        String value = matchResultFind$default3 != null ? matchResultFind$default3.getValue() : null;
        if (string != null) {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        } else {
            lowerCase = null;
        }
        String str6 = lowerCase;
        if (str6 != null && !StringsKt.isBlank(str6)) {
            String str7 = value;
            if (str7 == null || StringsKt.isBlank(str7)) {
                str2 = lowerCase;
            } else {
                str2 = lowerCase + value;
            }
        }
        return new VlcGroupInfo(string, str2);
    }

    private final void applyVlcTrackGrouping(ArrayList<AudioTrackModel> list) {
        String lowerCase;
        for (AudioTrackModel audioTrackModel : list) {
            VlcGroupInfo vlcGroupInfo = parseVlcGroupInfo(audioTrackModel.getName());
            String groupId = vlcGroupInfo.getGroupId();
            if (groupId != null && !StringsKt.isBlank(groupId)) {
                audioTrackModel.setGroupId(vlcGroupInfo.getGroupId());
            }
            String langToken = vlcGroupInfo.getLangToken();
            if (langToken != null) {
                lowerCase = langToken.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            } else {
                lowerCase = null;
            }
            String language = audioTrackModel.getLanguage();
            if (language == null || StringsKt.isBlank(language)) {
                audioTrackModel.setLanguage(lowerCase);
            }
            String lang = audioTrackModel.getLang();
            if (lang == null || StringsKt.isBlank(lang)) {
                audioTrackModel.setLang(lowerCase);
            }
            String language2 = audioTrackModel.getLanguage();
            String iSO3Language = "";
            if (language2 == null) {
                language2 = "";
            }
            try {
                iSO3Language = new Locale(language2).getISO3Language();
            } catch (Exception unused) {
            }
            audioTrackModel.setIso3Language(iSO3Language);
        }
    }

    private final ArrayList<AudioTrackModel> buildVlcGroupedTracks(ArrayList<AudioTrackModel> list) {
        AudioTrackModel audioTrackModelCopy;
        String name;
        ArrayList<AudioTrackModel> arrayList = new ArrayList<>();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            String groupId = ((AudioTrackModel) obj).getGroupId();
            Object obj2 = linkedHashMap.get(groupId);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(groupId, obj2);
            }
            ((List) obj2).add(obj);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            List list2 = (List) entry.getValue();
            String str2 = str;
            if (str2 != null && !StringsKt.isBlank(str2)) {
                boolean z = true;
                if (list2.size() != 1) {
                    AudioTrackModel audioTrackModel = (AudioTrackModel) CollectionsKt.firstOrNull(list2);
                    if (audioTrackModel != null && (name = audioTrackModel.getName()) != null && StringsKt.contains((CharSequence) name, (CharSequence) "ec3", true)) {
                        AudioTrackModel audioTrackModel2 = (AudioTrackModel) list2.get(0);
                        audioTrackModelCopy = audioTrackModel2.copy((536870911 & 1) != 0 ? audioTrackModel2.name : null, (536870911 & 2) != 0 ? audioTrackModel2.download_url : null, (536870911 & 4) != 0 ? audioTrackModel2.bitstream : null, (536870911 & 8) != 0 ? audioTrackModel2.frequency : null, (536870911 & 16) != 0 ? audioTrackModel2.format : null, (536870911 & 32) != 0 ? audioTrackModel2.file : null, (536870911 & 64) != 0 ? audioTrackModel2.lang : null, (536870911 & 128) != 0 ? audioTrackModel2.channel : null, (536870911 & 256) != 0 ? audioTrackModel2.video_file_name : null, (536870911 & 512) != 0 ? audioTrackModel2.oss_fid : null, (536870911 & 1024) != 0 ? audioTrackModel2.fid : null, (536870911 & 2048) != 0 ? audioTrackModel2.time : null, (536870911 & 4096) != 0 ? audioTrackModel2.trackName : null, (536870911 & 8192) != 0 ? audioTrackModel2.language : null, (536870911 & 16384) != 0 ? audioTrackModel2.iso3Language : null, (536870911 & 32768) != 0 ? audioTrackModel2.groupId : null, (536870911 & 65536) != 0 ? audioTrackModel2.select : false, (536870911 & 131072) != 0 ? audioTrackModel2.autoSelect : false, (536870911 & 262144) != 0 ? audioTrackModel2.exoAudioTrackInfo : null, (536870911 & 524288) != 0 ? audioTrackModel2.tracks : null, (536870911 & 1048576) != 0 ? audioTrackModel2.id : null, (536870911 & 2097152) != 0 ? audioTrackModel2.index : null, (536870911 & 4194304) != 0 ? audioTrackModel2.vip_only : null, (536870911 & 8388608) != 0 ? audioTrackModel2.org : null, (536870911 & 16777216) != 0 ? audioTrackModel2.trackId : null, (536870911 & 33554432) != 0 ? audioTrackModel2.hlsTrackId : null, (536870911 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? audioTrackModel2.type : 0, (536870911 & 134217728) != 0 ? audioTrackModel2.size_bytes : 0L, (536870911 & 268435456) != 0 ? audioTrackModel2.add_time : 0L);
                    } else {
                        AudioTrackModel audioTrackModel3 = (AudioTrackModel) CollectionsKt.getOrNull(list2, 1);
                        if (audioTrackModel3 == null || (audioTrackModelCopy = audioTrackModel3.copy((536870911 & 1) != 0 ? audioTrackModel3.name : null, (536870911 & 2) != 0 ? audioTrackModel3.download_url : null, (536870911 & 4) != 0 ? audioTrackModel3.bitstream : null, (536870911 & 8) != 0 ? audioTrackModel3.frequency : null, (536870911 & 16) != 0 ? audioTrackModel3.format : null, (536870911 & 32) != 0 ? audioTrackModel3.file : null, (536870911 & 64) != 0 ? audioTrackModel3.lang : null, (536870911 & 128) != 0 ? audioTrackModel3.channel : null, (536870911 & 256) != 0 ? audioTrackModel3.video_file_name : null, (536870911 & 512) != 0 ? audioTrackModel3.oss_fid : null, (536870911 & 1024) != 0 ? audioTrackModel3.fid : null, (536870911 & 2048) != 0 ? audioTrackModel3.time : null, (536870911 & 4096) != 0 ? audioTrackModel3.trackName : null, (536870911 & 8192) != 0 ? audioTrackModel3.language : null, (536870911 & 16384) != 0 ? audioTrackModel3.iso3Language : null, (536870911 & 32768) != 0 ? audioTrackModel3.groupId : null, (536870911 & 65536) != 0 ? audioTrackModel3.select : false, (536870911 & 131072) != 0 ? audioTrackModel3.autoSelect : false, (536870911 & 262144) != 0 ? audioTrackModel3.exoAudioTrackInfo : null, (536870911 & 524288) != 0 ? audioTrackModel3.tracks : null, (536870911 & 1048576) != 0 ? audioTrackModel3.id : null, (536870911 & 2097152) != 0 ? audioTrackModel3.index : null, (536870911 & 4194304) != 0 ? audioTrackModel3.vip_only : null, (536870911 & 8388608) != 0 ? audioTrackModel3.org : null, (536870911 & 16777216) != 0 ? audioTrackModel3.trackId : null, (536870911 & 33554432) != 0 ? audioTrackModel3.hlsTrackId : null, (536870911 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? audioTrackModel3.type : 0, (536870911 & 134217728) != 0 ? audioTrackModel3.size_bytes : 0L, (536870911 & 268435456) != 0 ? audioTrackModel3.add_time : 0L)) == null) {
                            AudioTrackModel audioTrackModel4 = (AudioTrackModel) list2.get(0);
                            audioTrackModelCopy = audioTrackModel4.copy((536870911 & 1) != 0 ? audioTrackModel4.name : null, (536870911 & 2) != 0 ? audioTrackModel4.download_url : null, (536870911 & 4) != 0 ? audioTrackModel4.bitstream : null, (536870911 & 8) != 0 ? audioTrackModel4.frequency : null, (536870911 & 16) != 0 ? audioTrackModel4.format : null, (536870911 & 32) != 0 ? audioTrackModel4.file : null, (536870911 & 64) != 0 ? audioTrackModel4.lang : null, (536870911 & 128) != 0 ? audioTrackModel4.channel : null, (536870911 & 256) != 0 ? audioTrackModel4.video_file_name : null, (536870911 & 512) != 0 ? audioTrackModel4.oss_fid : null, (536870911 & 1024) != 0 ? audioTrackModel4.fid : null, (536870911 & 2048) != 0 ? audioTrackModel4.time : null, (536870911 & 4096) != 0 ? audioTrackModel4.trackName : null, (536870911 & 8192) != 0 ? audioTrackModel4.language : null, (536870911 & 16384) != 0 ? audioTrackModel4.iso3Language : null, (536870911 & 32768) != 0 ? audioTrackModel4.groupId : null, (536870911 & 65536) != 0 ? audioTrackModel4.select : false, (536870911 & 131072) != 0 ? audioTrackModel4.autoSelect : false, (536870911 & 262144) != 0 ? audioTrackModel4.exoAudioTrackInfo : null, (536870911 & 524288) != 0 ? audioTrackModel4.tracks : null, (536870911 & 1048576) != 0 ? audioTrackModel4.id : null, (536870911 & 2097152) != 0 ? audioTrackModel4.index : null, (536870911 & 4194304) != 0 ? audioTrackModel4.vip_only : null, (536870911 & 8388608) != 0 ? audioTrackModel4.org : null, (536870911 & 16777216) != 0 ? audioTrackModel4.trackId : null, (536870911 & 33554432) != 0 ? audioTrackModel4.hlsTrackId : null, (536870911 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? audioTrackModel4.type : 0, (536870911 & 134217728) != 0 ? audioTrackModel4.size_bytes : 0L, (536870911 & 268435456) != 0 ? audioTrackModel4.add_time : 0L);
                        }
                    }
                    audioTrackModelCopy.setTracks(new ArrayList<>(list2));
                    List list3 = list2;
                    if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                        Iterator it = list3.iterator();
                        do {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                        } while (!((AudioTrackModel) it.next()).getSelect());
                    } else {
                        z = false;
                        break;
                    }
                    audioTrackModelCopy.setSelect(z);
                    arrayList.add(audioTrackModelCopy);
                }
            }
            arrayList.addAll(list2);
        }
        return arrayList;
    }

    private final ArrayList<AudioTrackModel> buildDisplayList() {
        ArrayList<AudioTrackModel> tracks;
        new ArrayList();
        ArrayList<AudioTrackModel> arrayListBuildVlcGroupedTracks = buildVlcGroupedTracks(this.audioTrackList);
        ArrayList<AudioTrackModel> arrayList = new ArrayList<>();
        for (AudioTrackModel audioTrackModel : arrayListBuildVlcGroupedTracks) {
            ArrayList<AudioTrackModel> tracks2 = audioTrackModel.getTracks();
            if (tracks2 == null || tracks2.isEmpty()) {
                arrayList.add(audioTrackModel);
            } else {
                arrayList.add(audioTrackModel);
                if (audioTrackModel.getSelect() && (tracks = audioTrackModel.getTracks()) != null) {
                    int i = 0;
                    for (Object obj : tracks) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        AudioTrackModel audioTrackModel2 = (AudioTrackModel) obj;
                        if (audioTrackModel.getAutoSelect() && i == 0) {
                            audioTrackModel2.setType(1);
                        } else {
                            audioTrackModel2.setType(2);
                        }
                        arrayList.add(audioTrackModel2);
                        i = i2;
                    }
                }
            }
        }
        return arrayList;
    }

    private final void refreshDisplayList() {
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter = this.adapter;
        if (baseDelegateMultiAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseDelegateMultiAdapter = null;
        }
        baseDelegateMultiAdapter.setList(buildDisplayList());
    }

    private final void initData() {
        ArrayList<AudioTrackModel> arrayList;
        Integer numValueOf;
        this.audioTrackList.clear();
        Bundle arguments = getArguments();
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter = null;
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
        Context context = getContext();
        if (context != null) {
            IMedia media = VlcPlayerManager.INSTANCE.get(context).getMediaPlayer().getMedia();
            this.media = media instanceof Media ? (Media) media : null;
        }
        LinearLayout llBestAudio = getBinding().llBestAudio;
        Intrinsics.checkNotNullExpressionValue(llBestAudio, "llBestAudio");
        CommonExtKt.gone(llBestAudio);
        int i = 0;
        for (Object obj : this.audioTrackList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AudioTrackModel audioTrackModel = (AudioTrackModel) obj;
            Integer trackId = audioTrackModel.getTrackId();
            int iIntValue = trackId != null ? trackId.intValue() : 0;
            String name = audioTrackModel.getName();
            if (name == null) {
                name = "";
            }
            IMedia.AudioTrack audioTrackInfo = getAudioTrackInfo(i2, iIntValue, name);
            if (audioTrackInfo != null) {
                if (audioTrackInfo.bitrate == 0) {
                    audioTrackModel.setBitstream(null);
                } else {
                    audioTrackModel.setBitstream(String.valueOf(audioTrackInfo.bitrate / 1000));
                }
                audioTrackModel.setChannel(String.valueOf(audioTrackInfo.channels));
                audioTrackModel.setLanguage(audioTrackInfo.language);
                audioTrackModel.setLang(audioTrackInfo.language);
                audioTrackModel.setFormat(audioTrackInfo.originalCodec);
            }
            i = i2;
        }
        applyVlcTrackGrouping(this.audioTrackList);
        Iterator<T> it = this.audioTrackList.iterator();
        if (it.hasNext()) {
            Integer trackId2 = ((AudioTrackModel) it.next()).getTrackId();
            numValueOf = Integer.valueOf(trackId2 != null ? trackId2.intValue() : 0);
            while (it.hasNext()) {
                Integer trackId3 = ((AudioTrackModel) it.next()).getTrackId();
                Integer numValueOf2 = Integer.valueOf(trackId3 != null ? trackId3.intValue() : 0);
                if (numValueOf.compareTo(numValueOf2) < 0) {
                    numValueOf = numValueOf2;
                }
            }
        } else {
            numValueOf = null;
        }
        Integer num = numValueOf;
        this.maxId = num != null ? num.intValue() : 0;
        Bundle arguments10 = getArguments();
        int i3 = arguments10 != null ? arguments10.getInt("audioDelay", 0) : 0;
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
            for (Object obj2 : this.audioTrackList) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                AudioTrackModel audioTrackModel2 = (AudioTrackModel) obj2;
                Integer trackId4 = audioTrackModel2.getTrackId();
                int i6 = this.audioTrackIndex;
                if (trackId4 != null && trackId4.intValue() == i6) {
                    audioTrackModel2.setSelect(true);
                } else {
                    audioTrackModel2.setSelect(false);
                }
                i4 = i5;
            }
            if (parcelableArrayList != null) {
                Iterator it2 = parcelableArrayList.iterator();
                while (it2.hasNext()) {
                    ((AudioTrackModel) it2.next()).setSelect(false);
                }
            }
            TextView tvAudioHint = getBinding().tvAudioHint;
            Intrinsics.checkNotNullExpressionValue(tvAudioHint, "tvAudioHint");
            CommonExtKt.gone(tvAudioHint);
        } else {
            Iterator<T> it3 = this.audioTrackList.iterator();
            while (it3.hasNext()) {
                ((AudioTrackModel) it3.next()).setSelect(false);
            }
            if (parcelableArrayList != null) {
                Iterator it4 = parcelableArrayList.iterator();
                while (it4.hasNext()) {
                    ((AudioTrackModel) it4.next()).setSelect(false);
                }
            }
            if (parcelableArrayList != null) {
                for (AudioTrackModel audioTrackModel3 : parcelableArrayList) {
                    if (Intrinsics.areEqual(audioTrackModel3.getOss_fid(), this.currAudioId)) {
                        audioTrackModel3.setSelect(true);
                    }
                }
            }
            LinearLayout llDelay = getBinding().llDelay;
            Intrinsics.checkNotNullExpressionValue(llDelay, "llDelay");
            CommonExtKt.visible(llDelay);
            TextView tvAudioHint2 = getBinding().tvAudioHint;
            Intrinsics.checkNotNullExpressionValue(tvAudioHint2, "tvAudioHint");
            CommonExtKt.visible(tvAudioHint2);
        }
        ProgressBar progressBar = getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter2 = new BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder>(buildDisplayList()) { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment.initData.9
            {
                super(arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, AudioTrackModel item) {
                int i7;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                int type = item.getType();
                if (type != 0) {
                    if (type == 1 || type == 2) {
                        LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.linearLayout);
                        if (item.getSelect()) {
                            linearLayout.setBackgroundResource(R.drawable.blue_border_8corner_shape);
                        } else {
                            linearLayout.setBackgroundResource(R.color.transparent);
                        }
                        ImageView imageView = (ImageView) holder.getView(R.id.ivAudioCode);
                        ImageView imageView2 = (ImageView) holder.getView(R.id.ivChannel);
                        TextView textView2 = (TextView) holder.getView(R.id.tvRate);
                        String bitstream = item.getBitstream();
                        if (bitstream != null && !StringsKt.isBlank(bitstream)) {
                            CommonExtKt.visible(textView2);
                            textView2.setText(item.getBitstream());
                        } else {
                            CommonExtKt.gone(textView2);
                        }
                        int audioChannelIcon = VideoIconUtils.INSTANCE.getAudioChannelIcon(item.getChannel());
                        if (audioChannelIcon == -1) {
                            CommonExtKt.gone(imageView2);
                        } else {
                            CommonExtKt.visible(imageView2);
                            imageView2.setImageResource(audioChannelIcon);
                        }
                        int audioCodeIcon = VideoIconUtils.INSTANCE.getAudioCodeIcon(item.getFormat());
                        if (audioCodeIcon == -1) {
                            CommonExtKt.gone(imageView);
                            return;
                        } else {
                            CommonExtKt.visible(imageView);
                            imageView.setImageResource(audioCodeIcon);
                            return;
                        }
                    }
                    return;
                }
                TextView textView3 = (TextView) holder.getView(R.id.tv_size);
                String download_url = item.getDownload_url();
                if (download_url == null || StringsKt.isBlank(download_url)) {
                    CommonExtKt.gone(textView3);
                } else {
                    CommonExtKt.visible(textView3);
                    textView3.setText(FileUtils.byte2FitMemorySize(item.getSize_bytes()));
                }
                ImageView imageView3 = (ImageView) holder.getView(R.id.iv_selected);
                if (item.getSelect()) {
                    CommonExtKt.visible(imageView3);
                } else {
                    CommonExtKt.gone(imageView3);
                }
                ImageView imageView4 = (ImageView) holder.getView(R.id.ivLang);
                ImageView imageView5 = (ImageView) holder.getView(R.id.ivAudioCode);
                ImageView imageView6 = (ImageView) holder.getView(R.id.ivChannel);
                TextView textView4 = (TextView) holder.getView(R.id.tvRate);
                TextView textView5 = (TextView) holder.getView(R.id.tvLang);
                String bitstream2 = item.getBitstream();
                if (bitstream2 != null && !StringsKt.isBlank(bitstream2)) {
                    try {
                        String bitstream3 = item.getBitstream();
                        Intrinsics.checkNotNull(bitstream3);
                        i7 = Integer.parseInt(bitstream3);
                    } catch (Exception unused) {
                        i7 = 0;
                    }
                    if (i7 >= 1014) {
                        CommonExtKt.visible(textView4);
                        textView4.setText(new DecimalFormat(IdManager.DEFAULT_VERSION_NAME).format(Float.valueOf(i7 / 1024.0f)) + "M");
                    } else if (i7 == 0) {
                        CommonExtKt.gone(textView4);
                    } else {
                        CommonExtKt.visible(textView4);
                        textView4.setText(i7 + "K");
                    }
                } else {
                    CommonExtKt.gone(textView4);
                }
                int audioChannelIcon2 = VideoIconUtils.INSTANCE.getAudioChannelIcon(item.getChannel());
                if (audioChannelIcon2 == -1) {
                    CommonExtKt.gone(imageView6);
                } else {
                    CommonExtKt.visible(imageView6);
                    imageView6.setImageResource(audioChannelIcon2);
                }
                int audioCodeIcon2 = VideoIconUtils.INSTANCE.getAudioCodeIcon(item.getFormat());
                if (audioCodeIcon2 == -1) {
                    CommonExtKt.gone(imageView5);
                } else {
                    CommonExtKt.visible(imageView5);
                    imageView5.setImageResource(audioCodeIcon2);
                }
                int audioLangIcon = VideoIconUtils.INSTANCE.getAudioLangIcon(item.getLang());
                if (audioLangIcon == -1) {
                    CommonExtKt.gone(imageView4);
                    String lang = item.getLang();
                    if (lang == null || StringsKt.isBlank(lang)) {
                        CommonExtKt.gone(textView5);
                        return;
                    } else {
                        CommonExtKt.visible(textView5);
                        textView5.setText(item.getLang());
                        return;
                    }
                }
                CommonExtKt.gone(textView5);
                CommonExtKt.visible(imageView4);
                imageView4.setImageResource(audioLangIcon);
            }
        };
        this.adapter = baseDelegateMultiAdapter2;
        baseDelegateMultiAdapter2.setMultiTypeDelegate(new BaseMultiTypeDelegate<AudioTrackModel>() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment.initData.10
            @Override // com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
            public int getItemType(List<? extends AudioTrackModel> data, int position) {
                Intrinsics.checkNotNullParameter(data, "data");
                return data.get(position).getType();
            }
        });
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter3 = this.adapter;
        if (baseDelegateMultiAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseDelegateMultiAdapter3 = null;
        }
        BaseMultiTypeDelegate<AudioTrackModel> multiTypeDelegate = baseDelegateMultiAdapter3.getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate);
        multiTypeDelegate.addItemType(0, R.layout.adapter_audio_track_item);
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter4 = this.adapter;
        if (baseDelegateMultiAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseDelegateMultiAdapter4 = null;
        }
        BaseMultiTypeDelegate<AudioTrackModel> multiTypeDelegate2 = baseDelegateMultiAdapter4.getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate2);
        multiTypeDelegate2.addItemType(1, R.layout.adapter_group_track_child_title_item);
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter5 = this.adapter;
        if (baseDelegateMultiAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseDelegateMultiAdapter5 = null;
        }
        BaseMultiTypeDelegate<AudioTrackModel> multiTypeDelegate3 = baseDelegateMultiAdapter5.getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate3);
        multiTypeDelegate3.addItemType(2, R.layout.adapter_group_track_child_item);
        TvRecyclerView recyclerView = getBinding().recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter6 = this.adapter;
        if (baseDelegateMultiAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseDelegateMultiAdapter = baseDelegateMultiAdapter6;
        }
        tvRecyclerView.setAdapter(baseDelegateMultiAdapter);
        PowerfulStickyDecoration powerfulStickyDecorationBuild = PowerfulStickyDecoration.Builder.init(new PowerGroupListener() { // from class: com.movieboxpro.android.view.dialog.VlcAudioTracksDialogFragment$initData$decoration$1
            @Override // com.gavin.com.library.listener.PowerGroupListener
            public boolean needDrawDecoration(int position) {
                return true;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.gavin.com.library.listener.GroupListener
            public String getGroupName(int position) {
                String video_file_name;
                BaseDelegateMultiAdapter baseDelegateMultiAdapter7 = this.this$0.adapter;
                if (baseDelegateMultiAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseDelegateMultiAdapter7 = null;
                }
                AudioTrackModel audioTrackModel4 = (AudioTrackModel) baseDelegateMultiAdapter7.getItemOrNull(position);
                return (audioTrackModel4 == null || (video_file_name = audioTrackModel4.getVideo_file_name()) == null) ? "" : video_file_name;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.gavin.com.library.listener.PowerGroupListener
            public View getGroupView(int position) {
                BaseDelegateMultiAdapter baseDelegateMultiAdapter7 = null;
                View viewInflate = LayoutInflater.from(this.this$0.getContext()).inflate(R.layout.video_quality_title, (ViewGroup) null, false);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.tvName);
                TextView textView3 = (TextView) viewInflate.findViewById(R.id.tvTime);
                BaseDelegateMultiAdapter baseDelegateMultiAdapter8 = this.this$0.adapter;
                if (baseDelegateMultiAdapter8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    baseDelegateMultiAdapter7 = baseDelegateMultiAdapter8;
                }
                AudioTrackModel audioTrackModel4 = (AudioTrackModel) baseDelegateMultiAdapter7.getItemOrNull(position);
                if (audioTrackModel4 != null) {
                    Intrinsics.checkNotNull(textView3);
                    TextView textView4 = textView3;
                    CommonExtKt.visible(textView4);
                    if (audioTrackModel4.getTime() != null) {
                        textView3.setText(audioTrackModel4.getTime());
                    } else if (audioTrackModel4.getAdd_time() == 0) {
                        CommonExtKt.gone(textView4);
                    } else {
                        textView3.setText(TimeUtils.formatTime4(audioTrackModel4.getAdd_time() * ((long) 1000)));
                    }
                    textView2.setText(audioTrackModel4.getVideo_file_name());
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

    /* JADX INFO: compiled from: VlcAudioTracksDialogFragment.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0084\u0001\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u001a\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0012¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;", "currString", "", "mediaQuality", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "tracks", "Ljava/util/ArrayList;", "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;", "Lkotlin/collections/ArrayList;", "onlineAudioTracks", "Lcom/movieboxpro/android/model/AudioTrackModel;", "id", "boxType", "", "pos", "audioDelay", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VlcAudioTracksDialogFragment newInstance(String currString, MediaQualityInfo mediaQuality, ArrayList<MediaPlayer.TrackDescription> tracks, ArrayList<AudioTrackModel> onlineAudioTracks, String id, int boxType, int pos, int audioDelay, int season, int episode) {
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
            VlcAudioTracksDialogFragment vlcAudioTracksDialogFragment = new VlcAudioTracksDialogFragment();
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
            vlcAudioTracksDialogFragment.setArguments(bundle);
            return vlcAudioTracksDialogFragment;
        }
    }
}
