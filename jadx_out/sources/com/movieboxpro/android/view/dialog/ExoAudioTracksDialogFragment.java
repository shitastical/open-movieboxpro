package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
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
import androidx.media3.common.Format;
import androidx.media3.common.Tracks;
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
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.AudioTrackModel;
import com.movieboxpro.android.model.AudioTrackResponse;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.OnlineAudioTrack;
import com.movieboxpro.android.player.MediaQualityInfo;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GsonExtensionsKt;
import com.movieboxpro.android.utils.ParameterizedTypeImpl;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.VideoIconUtils;
import com.movieboxpro.android.view.fragment.AudioSynDialog;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentAudioTrackBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.interfaces.IMedia;

/* JADX INFO: compiled from: ExoAudioTracksDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002-.B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\nJ\u001a\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u001eH\u0002J\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u0011J\b\u0010(\u001a\u00020\u001eH\u0002J\u001a\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0002J\b\u0010,\u001a\u00020\u001eH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000fj\b\u0012\u0004\u0012\u00020\u0007`\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ExoAudioTracksDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentAudioTrackBinding;", "<init>", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseDelegateMultiAdapter;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ExoAudioTracksDialogFragment$OnAudioTrackListener;", "lastPos", "", "maxId", "audioTrackList", "Ljava/util/ArrayList;", "id", "", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "boxType", "audioTrackIndex", "mediaQuality", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "onlineTracks", "Lkotlin/collections/ArrayList;", "currAudioId", "media", "Lorg/videolan/libvlc/Media;", "onStart", "", "setListener", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "setDelayText", "text", "getAudioTracks", "getAudioTrackInfo", "Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", FirebaseAnalytics.Param.INDEX, "initData", "Companion", "OnAudioTrackListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ExoAudioTracksDialogFragment extends BaseTransparentDialogFragment<FragmentAudioTrackBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> adapter;
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

    /* JADX INFO: compiled from: ExoAudioTracksDialogFragment.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ExoAudioTracksDialogFragment$OnAudioTrackListener;", "", "onAudioClick", "", "item", "Lcom/movieboxpro/android/model/AudioTrackModel;", "onAdjustAudioDelay", "ossId", "", "onOnlineAudios", "audioTracks", "Ljava/util/ArrayList;", "addDelay", "subDelay", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnAudioTrackListener {
        void addDelay();

        void onAdjustAudioDelay(String ossId);

        void onAudioClick(AudioTrackModel item);

        void onOnlineAudios(ArrayList<AudioTrackModel> audioTracks);

        void subDelay();
    }

    public ExoAudioTracksDialogFragment() {
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
        final FragmentAudioTrackBinding binding = getBinding();
        binding.tvDefaultAudio.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$1(this.f$0, view);
            }
        });
        binding.ivAudioSyn.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$2(this.f$0, view);
            }
        });
        binding.tvTryAgain.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$3(binding, view);
            }
        });
        binding.ivDelaySub.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$4(binding, view, z);
            }
        });
        binding.ivDelayAdd.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$5(binding, view, z);
            }
        });
        binding.ivShowTrack.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$6(binding, view, z);
            }
        });
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter = this.adapter;
        if (baseDelegateMultiAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseDelegateMultiAdapter = null;
        }
        baseDelegateMultiAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda11
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$8(this.f$0, binding, baseQuickAdapter, view, i);
            }
        });
        binding.ivDelayAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$9(this.f$0, view);
            }
        });
        binding.ivDelaySub.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExoAudioTracksDialogFragment.initListener$lambda$11$lambda$10(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$1(ExoAudioTracksDialogFragment exoAudioTracksDialogFragment, View view) {
        SettingManager.INSTANCE.setBestAudio(!SettingManager.INSTANCE.getBestAudio());
        exoAudioTracksDialogFragment.getBinding().tvDefaultAudio.setText(SettingManager.INSTANCE.getBestAudio() ? "ON" : "OFF");
        ToastUtils.showShort("Changes to this setting will take effect after you re-enter the playback page.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$2(ExoAudioTracksDialogFragment exoAudioTracksDialogFragment, View view) {
        new AudioSynDialog().show(exoAudioTracksDialogFragment.getChildFragmentManager(), "AudioSynDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$3(FragmentAudioTrackBinding fragmentAudioTrackBinding, View view) {
        TextView tvTryAgain = fragmentAudioTrackBinding.tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.gone(tvTryAgain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$4(FragmentAudioTrackBinding fragmentAudioTrackBinding, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = fragmentAudioTrackBinding.llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.invisible(llAudioTracks);
            fragmentAudioTrackBinding.llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.color_main));
            fragmentAudioTrackBinding.llContainer.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = fragmentAudioTrackBinding.ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.visible(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$5(FragmentAudioTrackBinding fragmentAudioTrackBinding, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = fragmentAudioTrackBinding.llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.invisible(llAudioTracks);
            fragmentAudioTrackBinding.llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.color_main));
            fragmentAudioTrackBinding.llContainer.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = fragmentAudioTrackBinding.ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.visible(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$6(FragmentAudioTrackBinding fragmentAudioTrackBinding, View view, boolean z) {
        if (z) {
            LinearLayout llAudioTracks = fragmentAudioTrackBinding.llAudioTracks;
            Intrinsics.checkNotNullExpressionValue(llAudioTracks, "llAudioTracks");
            CommonExtKt.visible(llAudioTracks);
            fragmentAudioTrackBinding.llDelay.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            ImageView ivShowTrack = fragmentAudioTrackBinding.ivShowTrack;
            Intrinsics.checkNotNullExpressionValue(ivShowTrack, "ivShowTrack");
            CommonExtKt.gone(ivShowTrack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$8(ExoAudioTracksDialogFragment exoAudioTracksDialogFragment, FragmentAudioTrackBinding fragmentAudioTrackBinding, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter = exoAudioTracksDialogFragment.adapter;
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter2 = null;
        if (baseDelegateMultiAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseDelegateMultiAdapter = null;
        }
        AudioTrackModel item = baseDelegateMultiAdapter.getItem(i);
        if (item != null) {
            Bundle arguments = exoAudioTracksDialogFragment.getArguments();
            if (arguments != null && !arguments.getBoolean("vlc", false)) {
                String download_url = item.getDownload_url();
                if (download_url == null || StringsKt.isBlank(download_url)) {
                    LinearLayout llDelay = fragmentAudioTrackBinding.llDelay;
                    Intrinsics.checkNotNullExpressionValue(llDelay, "llDelay");
                    CommonExtKt.visible(llDelay);
                    TextView tvAudioHint = fragmentAudioTrackBinding.tvAudioHint;
                    Intrinsics.checkNotNullExpressionValue(tvAudioHint, "tvAudioHint");
                    CommonExtKt.visible(tvAudioHint);
                } else {
                    LinearLayout llDelay2 = fragmentAudioTrackBinding.llDelay;
                    Intrinsics.checkNotNullExpressionValue(llDelay2, "llDelay");
                    CommonExtKt.gone(llDelay2);
                    TextView tvAudioHint2 = fragmentAudioTrackBinding.tvAudioHint;
                    Intrinsics.checkNotNullExpressionValue(tvAudioHint2, "tvAudioHint");
                    CommonExtKt.gone(tvAudioHint2);
                }
            }
            Integer vip_only = item.getVip_only();
            if (vip_only != null && vip_only.intValue() == 1) {
                if (App.getUserData().isvip == 1) {
                    Integer trackId = item.getTrackId();
                    if (trackId != null && trackId.intValue() == 0) {
                        int i2 = exoAudioTracksDialogFragment.maxId + 1;
                        exoAudioTracksDialogFragment.maxId = i2;
                        item.setTrackId(Integer.valueOf(i2));
                    }
                    item.setSelect(true);
                    BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter3 = exoAudioTracksDialogFragment.adapter;
                    if (baseDelegateMultiAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        baseDelegateMultiAdapter3 = null;
                    }
                    AudioTrackModel itemOrNull = baseDelegateMultiAdapter3.getItemOrNull(exoAudioTracksDialogFragment.lastPos);
                    if (itemOrNull != null) {
                        itemOrNull.setSelect(false);
                    }
                    BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter4 = exoAudioTracksDialogFragment.adapter;
                    if (baseDelegateMultiAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        baseDelegateMultiAdapter4 = null;
                    }
                    baseDelegateMultiAdapter4.notifyItemChanged(i);
                    BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter5 = exoAudioTracksDialogFragment.adapter;
                    if (baseDelegateMultiAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        baseDelegateMultiAdapter2 = baseDelegateMultiAdapter5;
                    }
                    baseDelegateMultiAdapter2.notifyItemChanged(exoAudioTracksDialogFragment.lastPos);
                    exoAudioTracksDialogFragment.lastPos = i;
                    OnAudioTrackListener onAudioTrackListener = exoAudioTracksDialogFragment.listener;
                    if (onAudioTrackListener != null) {
                        onAudioTrackListener.onAudioClick(item);
                        return;
                    }
                    return;
                }
                new VipChargeGuideDialog().show(exoAudioTracksDialogFragment.getChildFragmentManager(), "VipChargeGuideDialog");
                return;
            }
            Integer trackId2 = item.getTrackId();
            if (trackId2 != null && trackId2.intValue() == 0) {
                int i3 = exoAudioTracksDialogFragment.maxId + 1;
                exoAudioTracksDialogFragment.maxId = i3;
                item.setTrackId(Integer.valueOf(i3));
            }
            item.setSelect(true);
            BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter6 = exoAudioTracksDialogFragment.adapter;
            if (baseDelegateMultiAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseDelegateMultiAdapter6 = null;
            }
            AudioTrackModel itemOrNull2 = baseDelegateMultiAdapter6.getItemOrNull(exoAudioTracksDialogFragment.lastPos);
            if (itemOrNull2 != null) {
                itemOrNull2.setSelect(false);
            }
            BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter7 = exoAudioTracksDialogFragment.adapter;
            if (baseDelegateMultiAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseDelegateMultiAdapter7 = null;
            }
            baseDelegateMultiAdapter7.notifyItemChanged(i);
            BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter8 = exoAudioTracksDialogFragment.adapter;
            if (baseDelegateMultiAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseDelegateMultiAdapter2 = baseDelegateMultiAdapter8;
            }
            baseDelegateMultiAdapter2.notifyItemChanged(exoAudioTracksDialogFragment.lastPos);
            exoAudioTracksDialogFragment.lastPos = i;
            OnAudioTrackListener onAudioTrackListener2 = exoAudioTracksDialogFragment.listener;
            if (onAudioTrackListener2 != null) {
                onAudioTrackListener2.onAudioClick(item);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$9(ExoAudioTracksDialogFragment exoAudioTracksDialogFragment, View view) {
        OnAudioTrackListener onAudioTrackListener = exoAudioTracksDialogFragment.listener;
        if (onAudioTrackListener != null) {
            onAudioTrackListener.addDelay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$10(ExoAudioTracksDialogFragment exoAudioTracksDialogFragment, View view) {
        OnAudioTrackListener onAudioTrackListener = exoAudioTracksDialogFragment.listener;
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
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExoAudioTracksDialogFragment.getAudioTracks$lambda$12(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExoAudioTracksDialogFragment.getAudioTracks$lambda$13(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExoAudioTracksDialogFragment.getAudioTracks$lambda$18(this.f$0, (AudioTrackResponse) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$13(ExoAudioTracksDialogFragment exoAudioTracksDialogFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ProgressBar progressBar = exoAudioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$18(ExoAudioTracksDialogFragment exoAudioTracksDialogFragment, AudioTrackResponse audioTrackResponse) {
        String strReplace$default;
        ProgressBar progressBar = exoAudioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        ArrayList arrayList = new ArrayList();
        List<AudioTrackModel> audio_list = audioTrackResponse.getAudio_list();
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter = null;
        if (audio_list != null) {
            for (AudioTrackModel audioTrackModel : audio_list) {
                String bitstream = audioTrackModel.getBitstream();
                audioTrackModel.setBitstream((bitstream == null || (strReplace$default = StringsKt.replace$default(bitstream, "kb/s", "", false, 4, (Object) null)) == null) ? null : StringsKt.trim((CharSequence) strReplace$default).toString());
                String fid = audioTrackModel.getFid();
                MediaQualityInfo mediaQualityInfo = exoAudioTracksDialogFragment.mediaQuality;
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
                MediaQualityInfo mediaQualityInfo2 = exoAudioTracksDialogFragment.mediaQuality;
                if (!Intrinsics.areEqual(fid2, String.valueOf(mediaQualityInfo2 != null ? Integer.valueOf(mediaQualityInfo2.getFid()) : null))) {
                    arrayList.add(audioTrackModel2);
                }
            }
        }
        OnAudioTrackListener onAudioTrackListener = exoAudioTracksDialogFragment.listener;
        if (onAudioTrackListener != null) {
            List<AudioTrackModel> audio_list3 = audioTrackResponse.getAudio_list();
            if (audio_list3 == null) {
                audio_list3 = new ArrayList<>();
            }
            onAudioTrackListener.onOnlineAudios(new ArrayList<>(audio_list3));
        }
        ArrayList arrayList2 = arrayList;
        exoAudioTracksDialogFragment.onlineTracks.addAll(arrayList2);
        exoAudioTracksDialogFragment.audioTrackList.addAll(arrayList2);
        ArrayList<AudioTrackModel> arrayList3 = exoAudioTracksDialogFragment.audioTrackList;
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
        exoAudioTracksDialogFragment.audioTrackList.clear();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            exoAudioTracksDialogFragment.audioTrackList.addAll((Collection) ((Map.Entry) it.next()).getValue());
        }
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter2 = exoAudioTracksDialogFragment.adapter;
        if (baseDelegateMultiAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseDelegateMultiAdapter = baseDelegateMultiAdapter2;
        }
        baseDelegateMultiAdapter.setList(exoAudioTracksDialogFragment.audioTrackList);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAudioTracks$lambda$12(ExoAudioTracksDialogFragment exoAudioTracksDialogFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ProgressBar progressBar = exoAudioTracksDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        TextView tvTryAgain = exoAudioTracksDialogFragment.getBinding().tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.visible(tvTryAgain);
        exoAudioTracksDialogFragment.getBinding().tvTryAgain.setText("Load failed:" + it.getMessage() + ",tap to try again");
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
        ArrayList<AudioTrackModel> tracks;
        ArrayList<AudioTrackModel> tracks2;
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
        this.audioTrackIndex = arguments7 != null ? arguments7.getInt("groupIndex", 0) : 1;
        Bundle arguments8 = getArguments();
        if (arguments8 == null || (arrayList = arguments8.getParcelableArrayList("data")) == null) {
            arrayList = new ArrayList<>();
        }
        this.audioTrackList = arrayList;
        getBinding().tvDefaultAudio.setText(SettingManager.INSTANCE.getBestAudio() ? "ON" : "OFF");
        for (AudioTrackModel audioTrackModel : this.audioTrackList) {
            String language = audioTrackModel.getLanguage();
            String iSO3Language = "";
            if (language == null) {
                language = "";
            }
            try {
                iSO3Language = new Locale(language).getISO3Language();
            } catch (Exception unused) {
            }
            audioTrackModel.setIso3Language(iSO3Language);
        }
        ArrayList<AudioTrackModel> arrayList2 = this.audioTrackList;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : arrayList2) {
            String groupId = ((AudioTrackModel) obj).getGroupId();
            Object obj2 = linkedHashMap.get(groupId);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(groupId, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList arrayList3 = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            CharSequence charSequence = (CharSequence) entry.getKey();
            if (charSequence == null || StringsKt.isBlank(charSequence)) {
                arrayList3.addAll((Collection) entry.getValue());
            } else if (((List) entry.getValue()).size() == 1) {
                arrayList3.addAll((Collection) entry.getValue());
            } else {
                AudioTrackModel audioTrackModel2 = (AudioTrackModel) ((List) entry.getValue()).get(1);
                AudioTrackModel audioTrackModelCopy = audioTrackModel2.copy((536870911 & 1) != 0 ? audioTrackModel2.name : null, (536870911 & 2) != 0 ? audioTrackModel2.download_url : null, (536870911 & 4) != 0 ? audioTrackModel2.bitstream : null, (536870911 & 8) != 0 ? audioTrackModel2.frequency : null, (536870911 & 16) != 0 ? audioTrackModel2.format : null, (536870911 & 32) != 0 ? audioTrackModel2.file : null, (536870911 & 64) != 0 ? audioTrackModel2.lang : null, (536870911 & 128) != 0 ? audioTrackModel2.channel : null, (536870911 & 256) != 0 ? audioTrackModel2.video_file_name : null, (536870911 & 512) != 0 ? audioTrackModel2.oss_fid : null, (536870911 & 1024) != 0 ? audioTrackModel2.fid : null, (536870911 & 2048) != 0 ? audioTrackModel2.time : null, (536870911 & 4096) != 0 ? audioTrackModel2.trackName : null, (536870911 & 8192) != 0 ? audioTrackModel2.language : null, (536870911 & 16384) != 0 ? audioTrackModel2.iso3Language : null, (536870911 & 32768) != 0 ? audioTrackModel2.groupId : null, (536870911 & 65536) != 0 ? audioTrackModel2.select : false, (536870911 & 131072) != 0 ? audioTrackModel2.autoSelect : false, (536870911 & 262144) != 0 ? audioTrackModel2.exoAudioTrackInfo : null, (536870911 & 524288) != 0 ? audioTrackModel2.tracks : null, (536870911 & 1048576) != 0 ? audioTrackModel2.id : null, (536870911 & 2097152) != 0 ? audioTrackModel2.index : null, (536870911 & 4194304) != 0 ? audioTrackModel2.vip_only : null, (536870911 & 8388608) != 0 ? audioTrackModel2.org : null, (536870911 & 16777216) != 0 ? audioTrackModel2.trackId : null, (536870911 & 33554432) != 0 ? audioTrackModel2.hlsTrackId : null, (536870911 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? audioTrackModel2.type : 0, (536870911 & 134217728) != 0 ? audioTrackModel2.size_bytes : 0L, (536870911 & 268435456) != 0 ? audioTrackModel2.add_time : 0L);
                audioTrackModelCopy.setTracks(new ArrayList<>((Collection) entry.getValue()));
                arrayList3.add(audioTrackModelCopy);
            }
        }
        Bundle arguments9 = getArguments();
        ArrayList<Integer> integerArrayList = arguments9 != null ? arguments9.getIntegerArrayList("trackIndices") : null;
        Bundle arguments10 = getArguments();
        Bundle bundle = arguments10 != null ? arguments10.getBundle("select") : null;
        Tracks.Group groupFromBundle = bundle != null ? Tracks.Group.fromBundle(bundle) : null;
        ArrayList<AudioTrackModel> arrayList4 = arrayList3;
        int i = 0;
        for (Object obj3 : arrayList4) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AudioTrackModel audioTrackModel3 = (AudioTrackModel) obj3;
            Bundle exoAudioTrackInfo = audioTrackModel3.getExoAudioTrackInfo();
            Intrinsics.checkNotNull(exoAudioTrackInfo);
            Tracks.Group groupFromBundle2 = Tracks.Group.fromBundle(exoAudioTrackInfo);
            Intrinsics.checkNotNullExpressionValue(groupFromBundle2, "fromBundle(...)");
            if (Intrinsics.areEqual(groupFromBundle, groupFromBundle2)) {
                audioTrackModel3.setSelect(true);
                ArrayList<AudioTrackModel> tracks3 = audioTrackModel3.getTracks();
                if (tracks3 != null && !tracks3.isEmpty()) {
                    if (integerArrayList != null && (tracks2 = audioTrackModel3.getTracks()) != null && integerArrayList.size() == tracks2.size()) {
                        audioTrackModel3.setAutoSelect(true);
                        ArrayList<AudioTrackModel> tracks4 = audioTrackModel3.getTracks();
                        if (tracks4 != null) {
                            int i3 = 0;
                            for (Object obj4 : tracks4) {
                                int i4 = i3 + 1;
                                if (i3 < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                AudioTrackModel audioTrackModel4 = (AudioTrackModel) obj4;
                                Integer num = integerArrayList.get(i3);
                                audioTrackModel4.setSelect(num != null && num.intValue() == 1);
                                i3 = i4;
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    } else {
                        if (integerArrayList == null || integerArrayList.isEmpty()) {
                            audioTrackModel3.setAutoSelect(true);
                        } else {
                            audioTrackModel3.setAutoSelect(false);
                        }
                        ArrayList<AudioTrackModel> tracks5 = audioTrackModel3.getTracks();
                        if (tracks5 != null) {
                            int i5 = 0;
                            for (Object obj5 : tracks5) {
                                int i6 = i5 + 1;
                                if (i5 < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                ((AudioTrackModel) obj5).setSelect(groupFromBundle.isTrackSelected(i5));
                                i5 = i6;
                            }
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                }
            }
            i = i2;
        }
        Bundle arguments11 = getArguments();
        int i7 = arguments11 != null ? arguments11.getInt("audioDelay", 0) : 0;
        if (i7 == 0) {
            getBinding().tvDelay.setText("0s");
        } else {
            TextView textView = getBinding().tvDelay;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf((-i7) / 1000.0f)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            textView.setText(str + CmcdData.STREAMING_FORMAT_SS);
        }
        ArrayList arrayList5 = new ArrayList();
        for (AudioTrackModel audioTrackModel5 : arrayList4) {
            ArrayList<AudioTrackModel> tracks6 = audioTrackModel5.getTracks();
            if (tracks6 == null || tracks6.isEmpty()) {
                Boolean.valueOf(arrayList5.add(audioTrackModel5));
            } else {
                arrayList5.add(audioTrackModel5);
                if (audioTrackModel5.getSelect() && (tracks = audioTrackModel5.getTracks()) != null) {
                    int i8 = 0;
                    for (Object obj6 : tracks) {
                        int i9 = i8 + 1;
                        if (i8 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        AudioTrackModel audioTrackModel6 = (AudioTrackModel) obj6;
                        if (audioTrackModel5.getAutoSelect() && i8 == 0) {
                            audioTrackModel6.setType(1);
                        } else {
                            audioTrackModel6.setType(2);
                        }
                        arrayList5.add(audioTrackModel6);
                        i8 = i9;
                    }
                    Unit unit3 = Unit.INSTANCE;
                }
                Unit unit4 = Unit.INSTANCE;
            }
        }
        BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder> baseDelegateMultiAdapter2 = new BaseDelegateMultiAdapter<AudioTrackModel, BaseViewHolder>(arrayList5) { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment.initData.5
            {
                super(arrayList5);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, AudioTrackModel item) {
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
                            textView2.setText(item.getBitstream());
                            textView2.setVisibility(0);
                        } else {
                            textView2.setVisibility(8);
                        }
                        int audioChannelIcon = VideoIconUtils.INSTANCE.getAudioChannelIcon(item.getChannel());
                        if (audioChannelIcon == -1) {
                            imageView2.setVisibility(8);
                        } else {
                            imageView2.setVisibility(0);
                            imageView2.setImageResource(audioChannelIcon);
                        }
                        int audioCodeIcon = VideoIconUtils.INSTANCE.getAudioCodeIcon(item.getFormat());
                        if (audioCodeIcon == -1) {
                            imageView.setVisibility(8);
                            return;
                        } else {
                            imageView.setVisibility(0);
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
                    CommonExtKt.visible(textView4);
                    textView4.setText(item.getBitstream());
                } else {
                    CommonExtKt.gone(textView4);
                }
                int audioChannelIcon2 = VideoIconUtils.INSTANCE.getAudioChannelIcon(item.getChannel());
                if (audioChannelIcon2 == -1) {
                    CommonExtKt.gone(imageView6);
                } else {
                    imageView6.setImageResource(audioChannelIcon2);
                }
                int audioCodeIcon2 = VideoIconUtils.INSTANCE.getAudioCodeIcon(item.getFormat());
                if (audioCodeIcon2 == -1) {
                    CommonExtKt.gone(imageView5);
                } else {
                    CommonExtKt.visible(imageView5);
                    imageView5.setImageResource(audioCodeIcon2);
                }
                int audioLangIcon = VideoIconUtils.INSTANCE.getAudioLangIcon(item.getLanguage());
                if (audioLangIcon == -1) {
                    CommonExtKt.gone(imageView4);
                    String language2 = item.getLanguage();
                    if (language2 == null || StringsKt.isBlank(language2)) {
                        CommonExtKt.gone(textView5);
                        return;
                    } else {
                        CommonExtKt.visible(textView5);
                        textView5.setText(item.getLanguage());
                        return;
                    }
                }
                CommonExtKt.gone(textView5);
                CommonExtKt.visible(imageView4);
                imageView4.setImageResource(audioLangIcon);
            }
        };
        this.adapter = baseDelegateMultiAdapter2;
        baseDelegateMultiAdapter2.setMultiTypeDelegate(new BaseMultiTypeDelegate<AudioTrackModel>() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment.initData.6
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
        PowerfulStickyDecoration powerfulStickyDecorationBuild = PowerfulStickyDecoration.Builder.init(new PowerGroupListener() { // from class: com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment$initData$decoration$1
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
                AudioTrackModel audioTrackModel7 = (AudioTrackModel) baseDelegateMultiAdapter7.getItemOrNull(position);
                return (audioTrackModel7 == null || (video_file_name = audioTrackModel7.getVideo_file_name()) == null) ? "" : video_file_name;
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
                    baseDelegateMultiAdapter8 = null;
                }
                AudioTrackModel audioTrackModel7 = (AudioTrackModel) baseDelegateMultiAdapter8.getItemOrNull(position);
                if (audioTrackModel7 != null) {
                    ExoAudioTracksDialogFragment exoAudioTracksDialogFragment = this.this$0;
                    Intrinsics.checkNotNull(textView3);
                    TextView textView4 = textView3;
                    CommonExtKt.visible(textView4);
                    if (audioTrackModel7.getTime() != null) {
                        textView3.setText(audioTrackModel7.getTime());
                    } else if (audioTrackModel7.getAdd_time() == 0) {
                        CommonExtKt.gone(textView4);
                    } else {
                        textView3.setText(TimeUtils.formatTime4(audioTrackModel7.getAdd_time() * ((long) 1000)));
                        CommonExtKt.visible(textView4);
                    }
                    BaseDelegateMultiAdapter baseDelegateMultiAdapter9 = exoAudioTracksDialogFragment.adapter;
                    if (baseDelegateMultiAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        baseDelegateMultiAdapter9 = null;
                    }
                    AudioTrackModel audioTrackModel8 = (AudioTrackModel) baseDelegateMultiAdapter9.getItemOrNull(position);
                    textView2.setText(audioTrackModel8 != null ? audioTrackModel8.getVideo_file_name() : null);
                }
                BaseDelegateMultiAdapter baseDelegateMultiAdapter10 = this.this$0.adapter;
                if (baseDelegateMultiAdapter10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    baseDelegateMultiAdapter7 = baseDelegateMultiAdapter10;
                }
                AudioTrackModel audioTrackModel9 = (AudioTrackModel) baseDelegateMultiAdapter7.getItemOrNull(position);
                if (audioTrackModel9 != null) {
                    audioTrackModel9.getTime();
                }
                Intrinsics.checkNotNull(viewInflate);
                return viewInflate;
            }
        }).setGroupBackground(CommonExtKt.colorInt(R.color.color_main_back)).setDivideHeight(0).setGroupHeight((int) ScreenUtils.dpToPx(70.0f)).setCacheEnable(true).setSticky(false).build();
        V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 1);
        if (powerfulStickyDecorationBuild != null) {
            powerfulStickyDecorationBuild.resetSpan(getBinding().recyclerView, v7GridLayoutManager);
            Unit unit5 = Unit.INSTANCE;
        }
        getBinding().recyclerView.setLayoutManager(v7GridLayoutManager);
        TvRecyclerView tvRecyclerView2 = getBinding().recyclerView;
        Intrinsics.checkNotNull(powerfulStickyDecorationBuild);
        tvRecyclerView2.addItemDecoration(powerfulStickyDecorationBuild);
    }

    /* JADX INFO: compiled from: ExoAudioTracksDialogFragment.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nJ\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0005H\u0002J\u0080\u0001\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n2\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00132\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ExoAudioTracksDialogFragment$Companion;", "", "<init>", "()V", "findTrackInfo", "Lcom/movieboxpro/android/model/OnlineAudioTrack;", "id", "", "label", "onlineTracks", "Ljava/util/ArrayList;", "getTrackId", "audioTrack", "newInstance", "Lcom/movieboxpro/android/view/dialog/ExoAudioTracksDialogFragment;", "currString", "mediaQuality", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "tracks", "Landroidx/media3/common/Tracks$Group;", "hlsAudioTracks", "", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "selectAudioTrack", "trackIndices", "", "boxType", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "hls", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code duplicated, block: B:20:0x006d  */
        /* JADX WARN: Code duplicated, block: B:26:0x0077 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:27:? A[LOOP:0: B:9:0x0045->B:27:?, LOOP_END, SYNTHETIC] */
        public final OnlineAudioTrack findTrackInfo(String id, String label, ArrayList<OnlineAudioTrack> onlineTracks) {
            String strGroup;
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(onlineTracks, "onlineTracks");
            String str = id;
            boolean zContains$default = StringsKt.contains$default((CharSequence) str, (CharSequence) "ORG", false, 2, (Object) null);
            Pattern patternCompile = Pattern.compile("\\[(\\d+)]", 0);
            Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(...)");
            Matcher matcher = patternCompile.matcher(str);
            int i = (!matcher.find() || (strGroup = matcher.group(1)) == null) ? 0 : Integer.parseInt(strGroup);
            for (OnlineAudioTrack onlineAudioTrack : onlineTracks) {
                if (zContains$default == onlineAudioTrack.getOrg() && i == onlineAudioTrack.getMap()) {
                    String channel_layout = onlineAudioTrack.getChannel_layout();
                    if (channel_layout == null) {
                        channel_layout = "";
                    }
                    if (!StringsKt.contains$default((CharSequence) str, (CharSequence) channel_layout, false, 2, (Object) null)) {
                        if (Intrinsics.areEqual(id, onlineAudioTrack.getTrackId())) {
                        }
                    }
                } else if (Intrinsics.areEqual(id, onlineAudioTrack.getTrackId())) {
                }
                return onlineAudioTrack;
            }
            return null;
        }

        private final String getTrackId(OnlineAudioTrack audioTrack) {
            String quality = audioTrack.getQuality();
            List listSplit$default = quality != null ? StringsKt.split$default((CharSequence) quality, new String[]{"_"}, false, 0, 6, (Object) null) : null;
            if (listSplit$default != null && listSplit$default.size() == 2) {
                return listSplit$default.get(0) + "_aac:" + audioTrack.getName();
            }
            if (listSplit$default != null && listSplit$default.size() == 3) {
                String str = (String) listSplit$default.get(2);
                Object obj = listSplit$default.get(0);
                if (Intrinsics.areEqual(str, "eac3")) {
                    str = "ec3";
                }
                return obj + "_" + str + ":" + audioTrack.getName();
            }
            return "";
        }

        /* JADX WARN: Code duplicated, block: B:113:0x030d A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:77:0x02d3  */
        /* JADX WARN: Code duplicated, block: B:78:0x02d8  */
        /* JADX WARN: Code duplicated, block: B:87:0x0306  */
        public final ExoAudioTracksDialogFragment newInstance(String currString, MediaQualityInfo mediaQuality, ArrayList<Tracks.Group> tracks, List<? extends BaseMediaModel.DownloadFile> hlsAudioTracks, Tracks.Group selectAudioTrack, ArrayList<Integer> trackIndices, String id, int boxType, int season, int episode, boolean hls) {
            Iterator it;
            int i;
            String str;
            String filename;
            String str2;
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            List<? extends BaseMediaModel.DownloadFile> listEmptyList = hlsAudioTracks == null ? CollectionsKt.emptyList() : hlsAudioTracks;
            ArrayList arrayList = new ArrayList();
            for (Object obj : listEmptyList) {
                if (Intrinsics.areEqual(((BaseMediaModel.DownloadFile) obj).filename, mediaQuality != null ? mediaQuality.getFilename() : null)) {
                    arrayList.add(obj);
                }
            }
            Object objFromJson = GsonExtensionsKt.getGSON().fromJson(GsonExtensionsKt.getGSON().toJson(arrayList), new ParameterizedTypeImpl(OnlineAudioTrack.class));
            ArrayList arrayList2 = objFromJson instanceof List ? (List) objFromJson : null;
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
            }
            ArrayList<OnlineAudioTrack> arrayList3 = new ArrayList<>(arrayList2);
            ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>();
            ExoAudioTracksDialogFragment exoAudioTracksDialogFragment = new ExoAudioTracksDialogFragment();
            Bundle bundle = new Bundle();
            String str3 = "getFormat(...)";
            if (!arrayList3.isEmpty() && hls) {
                for (OnlineAudioTrack onlineAudioTrack : arrayList3) {
                    Companion companion = ExoAudioTracksDialogFragment.INSTANCE;
                    Intrinsics.checkNotNull(onlineAudioTrack);
                    onlineAudioTrack.setTrackId(companion.getTrackId(onlineAudioTrack));
                }
                for (Tracks.Group group : tracks) {
                    if (group.getMediaTrackGroup().length != 0) {
                        int i2 = group.getMediaTrackGroup().length;
                        for (int i3 = 0; i3 < i2; i3++) {
                            String str4 = group.getMediaTrackGroup().getFormat(i3).id;
                            String str5 = group.getMediaTrackGroup().getFormat(i3).label;
                            AudioTrackModel audioTrackModel = new AudioTrackModel(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, 0, 0L, 0L, 536870911, null);
                            Companion companion2 = ExoAudioTracksDialogFragment.INSTANCE;
                            if (str4 == null) {
                                str4 = "";
                            }
                            if (str5 == null) {
                                str5 = "";
                            }
                            OnlineAudioTrack onlineAudioTrackFindTrackInfo = companion2.findTrackInfo(str4, str5, arrayList3);
                            if (onlineAudioTrackFindTrackInfo != null) {
                                audioTrackModel.setIndex(Integer.valueOf(i3));
                                audioTrackModel.setLanguage(onlineAudioTrackFindTrackInfo.getLang());
                                audioTrackModel.setChannel(onlineAudioTrackFindTrackInfo.getChannel_layout());
                                audioTrackModel.setFormat(onlineAudioTrackFindTrackInfo.getFormat());
                                audioTrackModel.setHlsTrackId(onlineAudioTrackFindTrackInfo.getTrackId());
                                audioTrackModel.setGroupId(group.getMediaTrackGroup().id);
                                audioTrackModel.setBitstream(onlineAudioTrackFindTrackInfo.getBitrate());
                                audioTrackModel.setVideo_file_name(mediaQuality != null ? mediaQuality.getFilename() : null);
                                audioTrackModel.setOrg(Integer.valueOf(onlineAudioTrackFindTrackInfo.getOrg()));
                                audioTrackModel.setFrequency(onlineAudioTrackFindTrackInfo.getSample_rate());
                                if (selectAudioTrack != null) {
                                    audioTrackModel.setExoAudioTrackInfo(group.toBundle());
                                }
                                arrayList4.add(audioTrackModel);
                            } else {
                                AudioTrackModel audioTrackModel2 = new AudioTrackModel(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, 0, 0L, 0L, 536870911, null);
                                audioTrackModel2.setIndex(Integer.valueOf(i3));
                                Format format = group.getMediaTrackGroup().getFormat(i3);
                                Intrinsics.checkNotNullExpressionValue(format, "getFormat(...)");
                                audioTrackModel2.setLanguage(format.language);
                                audioTrackModel2.setChannel(String.valueOf(format.channelCount));
                                audioTrackModel2.setVideo_file_name(mediaQuality != null ? mediaQuality.getFilename() : null);
                                audioTrackModel2.setGroupId(group.getMediaTrackGroup().id);
                                if (selectAudioTrack != null) {
                                    audioTrackModel2.setExoAudioTrackInfo(group.toBundle());
                                }
                                arrayList4.add(audioTrackModel2);
                            }
                        }
                    }
                }
            } else {
                Iterator it2 = tracks.iterator();
                while (it2.hasNext()) {
                    Tracks.Group group2 = (Tracks.Group) it2.next();
                    if (group2.getMediaTrackGroup().length != 0) {
                        int i4 = group2.getMediaTrackGroup().length;
                        int i5 = 0;
                        while (i5 < i4) {
                            AudioTrackModel audioTrackModel3 = new AudioTrackModel(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, 0, 0L, 0L, 536870911, null);
                            audioTrackModel3.setIndex(Integer.valueOf(i5));
                            Format format2 = group2.getMediaTrackGroup().getFormat(i5);
                            Intrinsics.checkNotNullExpressionValue(format2, str3);
                            audioTrackModel3.setFormat(format2.id);
                            String str6 = format2.id;
                            if (str6 != null) {
                                it = it2;
                                i = i4;
                                str = str3;
                                if (StringsKt.contains$default((CharSequence) str6, (CharSequence) "5.1", false, 2, (Object) null) && format2.channelCount >= 6) {
                                    audioTrackModel3.setChannel("5.1");
                                }
                                if (mediaQuality != null) {
                                    filename = mediaQuality.getFilename();
                                } else {
                                    filename = null;
                                }
                                audioTrackModel3.setVideo_file_name(filename);
                                audioTrackModel3.setLanguage(format2.language);
                                str2 = format2.id;
                                if (str2 != null && StringsKt.contains$default((CharSequence) str2, (CharSequence) "audio", false, 2, (Object) null)) {
                                    audioTrackModel3.setFormat(format2.id);
                                }
                                audioTrackModel3.setGroupId(group2.getMediaTrackGroup().id);
                                if (selectAudioTrack != null) {
                                    audioTrackModel3.setExoAudioTrackInfo(group2.toBundle());
                                }
                                arrayList4.add(audioTrackModel3);
                                i5++;
                                it2 = it;
                                i4 = i;
                                str3 = str;
                            } else {
                                it = it2;
                                i = i4;
                                str = str3;
                            }
                            audioTrackModel3.setChannel(String.valueOf(format2.channelCount));
                            if (mediaQuality != null) {
                                filename = mediaQuality.getFilename();
                            } else {
                                filename = null;
                            }
                            audioTrackModel3.setVideo_file_name(filename);
                            audioTrackModel3.setLanguage(format2.language);
                            str2 = format2.id;
                            if (str2 != null) {
                                audioTrackModel3.setFormat(format2.id);
                            }
                            audioTrackModel3.setGroupId(group2.getMediaTrackGroup().id);
                            if (selectAudioTrack != null) {
                                audioTrackModel3.setExoAudioTrackInfo(group2.toBundle());
                            }
                            arrayList4.add(audioTrackModel3);
                            i5++;
                            it2 = it;
                            i4 = i;
                            str3 = str;
                        }
                    }
                    it2 = it2;
                    str3 = str3;
                }
                bundle.putParcelableArrayList("onlineTracks", arrayList3);
            }
            bundle.putParcelableArrayList("data", arrayList4);
            if (selectAudioTrack != null) {
                bundle.putBundle("select", selectAudioTrack.toBundle());
            }
            bundle.putIntegerArrayList("trackIndices", trackIndices);
            bundle.putParcelableArrayList("data", arrayList4);
            bundle.putParcelableArrayList("onlineTracks", arrayList3);
            bundle.putInt("boxType", boxType);
            bundle.putString("ossFid", id);
            bundle.putInt(DealWatchNextService.SEASON, season);
            bundle.putInt(DealWatchNextService.EPISODE, episode);
            bundle.putParcelable("quality", mediaQuality);
            exoAudioTracksDialogFragment.setArguments(bundle);
            return exoAudioTracksDialogFragment;
        }
    }
}
