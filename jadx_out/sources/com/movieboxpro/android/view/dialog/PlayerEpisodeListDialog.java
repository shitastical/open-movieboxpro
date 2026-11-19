package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.adapter.PlayerEpisodeAdapter;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.widget.LoadingView;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentPlayerEpisodeListBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PlayerEpisodeListDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 F2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002EFB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010!\u001a\u00020\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\"\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\u0016\u0010#\u001a\u00020\u000e2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rJ\b\u0010$\u001a\u00020\u000eH\u0016J\u001a\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u000eH\u0016J\u0010\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020\u000eH\u0016J\b\u0010/\u001a\u00020\u000eH\u0016J)\u00100\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016¢\u0006\u0002\u00101J\b\u00102\u001a\u00020\u000eH\u0016J\u0016\u00103\u001a\u00020\u000e2\f\u00104\u001a\b\u0012\u0004\u0012\u00020605H\u0016J\u0010\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u0011H\u0016J\b\u00109\u001a\u00020\u000eH\u0002J\b\u0010:\u001a\u00020\u000eH\u0002J\b\u0010;\u001a\u00020\u000eH\u0002J\b\u0010<\u001a\u00020\u000eH\u0002J\u0018\u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020\u00112\u0006\u0010?\u001a\u00020\u0011H\u0002J\u0012\u0010@\u001a\u00020\u000e2\b\b\u0002\u0010A\u001a\u00020\u001aH\u0002J\b\u0010B\u001a\u00020\u000eH\u0002J\b\u0010C\u001a\u00020\u000eH\u0002J\b\u0010D\u001a\u00020\u000eH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentPlayerEpisodeListBinding;", "Lcom/movieboxpro/android/view/dialog/EpisodeListContract$View;", "<init>", "()V", "presenter", "Lcom/movieboxpro/android/view/dialog/EpisodeListPresenter;", "adapter", "Lcom/movieboxpro/android/adapter/PlayerEpisodeAdapter;", "callback", "Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListDialog$OnSelectEpisodeCallback;", "onDialogDismissed", "Lkotlin/Function0;", "", "seasons", "", "", "[Ljava/lang/String;", "years", "currSeason", "", "currYear", "playingSeason", "playingEpisode", "seasonSwitchEnabled", "", "store", "Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListStore;", "pendingSelectPosition", "selectAttempts", "selectPlayingRunnable", "Ljava/lang/Runnable;", "setCallback", "setStore", "setOnDialogDismissed", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "showLoadingView", "hideLoadingView", "showSeasonMeta", "([Ljava/lang/String;[Ljava/lang/String;)V", "showSeasonMetaFailed", "showEpisodes", "list", "", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "showEpisodeLoadFailed", "message", "initFocus", "initList", "initRetry", "initSeasonClick", "onSeasonYearChosen", "season", "year", "reloadEpisodes", "forceNetwork", "scrollToPlayingAndFocus", "selectPlayingEpisode", "retrySelectPlayingEpisode", "OnSelectEpisodeCallback", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayerEpisodeListDialog extends BaseTransparentDialogFragment<FragmentPlayerEpisodeListBinding> implements EpisodeListContract.View {
    private static final String ARG_EPISODE = "episode";
    private static final String ARG_SEASON = "season";
    private static final String ARG_TV_ID = "tvId";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private PlayerEpisodeAdapter adapter;
    private OnSelectEpisodeCallback callback;
    private int currSeason;
    private int currYear;
    private Function0<Unit> onDialogDismissed;
    private int pendingSelectPosition;
    private int playingEpisode;
    private int playingSeason;
    private EpisodeListPresenter presenter;
    private boolean seasonSwitchEnabled;
    private String[] seasons;
    private int selectAttempts;
    private final Runnable selectPlayingRunnable;
    private PlayerEpisodeListStore store;
    private String[] years;

    /* JADX INFO: compiled from: PlayerEpisodeListDialog.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\t\u0012\u00070\u0005¢\u0006\u0002\b\b0\u0007H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListDialog$OnSelectEpisodeCallback;", "", "onSelectEpisode", "", "item", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "seasonEpisodes", "", "Lkotlin/jvm/JvmSuppressWildcards;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnSelectEpisodeCallback {
        void onSelectEpisode(TvDetail.SeasonDetail item, List<TvDetail.SeasonDetail> seasonEpisodes);
    }

    @JvmStatic
    public static final PlayerEpisodeListDialog newInstance(String str, int i, int i2) {
        return INSTANCE.newInstance(str, i, i2);
    }

    public PlayerEpisodeListDialog() {
        super(R.layout.fragment_player_episode_list);
        this.seasons = new String[0];
        this.years = new String[0];
        this.currSeason = 1;
        this.playingSeason = 1;
        this.playingEpisode = 1;
        this.pendingSelectPosition = -1;
        this.selectPlayingRunnable = new Runnable() { // from class: com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PlayerEpisodeListDialog.selectPlayingRunnable$lambda$0(this.f$0);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void selectPlayingRunnable$lambda$0(PlayerEpisodeListDialog playerEpisodeListDialog) {
        if (playerEpisodeListDialog.isAdded()) {
            playerEpisodeListDialog.selectPlayingEpisode();
        }
    }

    public final void setCallback(OnSelectEpisodeCallback callback) {
        this.callback = callback;
    }

    public final void setStore(PlayerEpisodeListStore store) {
        this.store = store;
    }

    public final void setOnDialogDismissed(Function0<Unit> callback) {
        this.onDialogDismissed = callback;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) displayMetrics.widthPixels) * 0.5d);
            attributes.height = (int) (((double) displayMetrics.heightPixels) * 0.8d);
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
        PlayerEpisodeAdapter playerEpisodeAdapter = this.adapter;
        if (playerEpisodeAdapter != null) {
            if (playerEpisodeAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                playerEpisodeAdapter = null;
            }
            if (playerEpisodeAdapter.getData().isEmpty()) {
                return;
            }
            getBinding().recyclerView.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.scrollToPlayingAndFocus();
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String string;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        int i = arguments != null ? arguments.getInt("season") : 1;
        this.currSeason = i;
        this.playingSeason = i;
        Bundle arguments2 = getArguments();
        this.playingEpisode = arguments2 != null ? arguments2.getInt("episode") : 1;
        EpisodeListPresenter episodeListPresenter = new EpisodeListPresenter(this, this.store);
        this.presenter = episodeListPresenter;
        episodeListPresenter.attachView(this);
        initFocus();
        initList();
        initSeasonClick();
        initRetry();
        Bundle arguments3 = getArguments();
        if (arguments3 == null || (string = arguments3.getString(ARG_TV_ID)) == null) {
            return;
        }
        EpisodeListPresenter episodeListPresenter2 = this.presenter;
        if (episodeListPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
            episodeListPresenter2 = null;
        }
        episodeListPresenter2.loadSeasonMeta(string);
        reloadEpisodes$default(this, false, 1, null);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        getBinding().recyclerView.removeCallbacks(this.selectPlayingRunnable);
        hideLoadingView();
        EpisodeListPresenter episodeListPresenter = this.presenter;
        if (episodeListPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
            episodeListPresenter = null;
        }
        episodeListPresenter.detachView();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        Function0<Unit> function0 = this.onDialogDismissed;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, com.movieboxpro.android.view.dialog.EpisodeListContract.View
    public void showLoadingView() {
        TvRecyclerView recyclerView = getBinding().recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.gone(recyclerView);
        LinearLayout llError = getBinding().llError;
        Intrinsics.checkNotNullExpressionValue(llError, "llError");
        CommonExtKt.gone(llError);
        LoadingView progressBar = getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
        getBinding().llSeason.setNextFocusDownId(-1);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, com.movieboxpro.android.view.dialog.EpisodeListContract.View
    public void hideLoadingView() {
        LoadingView progressBar = getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
    }

    @Override // com.movieboxpro.android.view.dialog.EpisodeListContract.View
    public void showSeasonMeta(String[] seasons, String[] years) {
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        Intrinsics.checkNotNullParameter(years, "years");
        this.seasons = seasons;
        this.years = years;
        getBinding().tvSeasons.setText(EpisodeSeasonMapper.INSTANCE.seasonCountLabel(seasons.length));
        this.seasonSwitchEnabled = true;
        getBinding().llSeason.setClickable(true);
        getBinding().llSeason.setFocusable(true);
    }

    @Override // com.movieboxpro.android.view.dialog.EpisodeListContract.View
    public void showSeasonMetaFailed() {
        if (this.seasons.length == 0) {
            if (this.years.length == 0) {
                getBinding().tvSeasons.setText(EpisodeSeasonMapper.INSTANCE.seasonCountLabel(0));
                this.seasonSwitchEnabled = false;
                getBinding().llSeason.setClickable(false);
                getBinding().llSeason.setFocusable(false);
                return;
            }
        }
        getBinding().tvSeasons.setText(EpisodeSeasonMapper.INSTANCE.seasonCountLabel(this.seasons.length));
        this.seasonSwitchEnabled = true;
        getBinding().llSeason.setClickable(true);
        getBinding().llSeason.setFocusable(true);
    }

    @Override // com.movieboxpro.android.view.dialog.EpisodeListContract.View
    public void showEpisodes(List<TvDetail.SeasonDetail> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        LoadingView progressBar = getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        LinearLayout llError = getBinding().llError;
        Intrinsics.checkNotNullExpressionValue(llError, "llError");
        CommonExtKt.gone(llError);
        TvRecyclerView recyclerView = getBinding().recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.visible(recyclerView);
        getBinding().llSeason.setNextFocusDownId(R.id.recyclerView);
        getBinding().tvCurrSeason.setText(EpisodeSeasonMapper.INSTANCE.headerTitle(this.currSeason, this.currYear));
        PlayerEpisodeAdapter playerEpisodeAdapter = this.adapter;
        if (playerEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            playerEpisodeAdapter = null;
        }
        playerEpisodeAdapter.setList(list);
        scrollToPlayingAndFocus();
    }

    @Override // com.movieboxpro.android.view.dialog.EpisodeListContract.View
    public void showEpisodeLoadFailed(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        PlayerEpisodeAdapter playerEpisodeAdapter = this.adapter;
        if (playerEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            playerEpisodeAdapter = null;
        }
        playerEpisodeAdapter.setList(new ArrayList());
        LoadingView progressBar = getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        TvRecyclerView recyclerView = getBinding().recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.gone(recyclerView);
        getBinding().tvError.setText(message);
        LinearLayout llError = getBinding().llError;
        Intrinsics.checkNotNullExpressionValue(llError, "llError");
        CommonExtKt.visible(llError);
        getBinding().llSeason.setNextFocusDownId(R.id.tvRetry);
        getBinding().tvCurrSeason.setText(EpisodeSeasonMapper.INSTANCE.headerTitle(this.currSeason, this.currYear));
        getBinding().tvRetry.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PlayerEpisodeListDialog.showEpisodeLoadFailed$lambda$3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEpisodeLoadFailed$lambda$3(PlayerEpisodeListDialog playerEpisodeListDialog) {
        if (playerEpisodeListDialog.isAdded()) {
            playerEpisodeListDialog.getBinding().tvRetry.requestFocus();
        }
    }

    private final void initFocus() {
        getBinding().llSeason.setClickable(true);
        getBinding().llSeason.setFocusable(true);
        getBinding().llSeason.setNextFocusDownId(R.id.recyclerView);
        getBinding().recyclerView.setNextFocusUpId(R.id.llSeason);
    }

    private final void initList() {
        this.adapter = new PlayerEpisodeAdapter(new ArrayList(), this.playingSeason, this.playingEpisode);
        getBinding().recyclerView.setLayoutManager(new V7LinearLayoutManager(getContext()));
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        PlayerEpisodeAdapter playerEpisodeAdapter = this.adapter;
        if (playerEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            playerEpisodeAdapter = null;
        }
        tvRecyclerView.setAdapter(playerEpisodeAdapter);
        getBinding().recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog.initList.1
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                PlayerEpisodeAdapter playerEpisodeAdapter2 = PlayerEpisodeListDialog.this.adapter;
                PlayerEpisodeAdapter playerEpisodeAdapter3 = null;
                if (playerEpisodeAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    playerEpisodeAdapter2 = null;
                }
                TvDetail.SeasonDetail item = playerEpisodeAdapter2.getItem(position);
                if (item == null) {
                    return;
                }
                if (!EpisodeSeasonMapper.INSTANCE.isUnavailable(item)) {
                    if (!EpisodeSeasonMapper.INSTANCE.isCurrentEpisode(item, PlayerEpisodeListDialog.this.playingSeason, PlayerEpisodeListDialog.this.playingEpisode)) {
                        OnSelectEpisodeCallback onSelectEpisodeCallback = PlayerEpisodeListDialog.this.callback;
                        if (onSelectEpisodeCallback != null) {
                            PlayerEpisodeAdapter playerEpisodeAdapter4 = PlayerEpisodeListDialog.this.adapter;
                            if (playerEpisodeAdapter4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            } else {
                                playerEpisodeAdapter3 = playerEpisodeAdapter4;
                            }
                            onSelectEpisodeCallback.onSelectEpisode(item, new ArrayList(playerEpisodeAdapter3.getData()));
                        }
                        PlayerEpisodeListDialog.this.dismissAllowingStateLoss();
                        return;
                    }
                    PlayerEpisodeListDialog.this.dismissAllowingStateLoss();
                    return;
                }
                ToastUtils.showShort("Not Available", new Object[0]);
            }
        });
    }

    private final void initRetry() {
        getBinding().tvRetry.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.reloadEpisodes(true);
            }
        });
    }

    private final void initSeasonClick() {
        getBinding().llSeason.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerEpisodeListDialog.initSeasonClick$lambda$6(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initSeasonClick$lambda$6(final PlayerEpisodeListDialog playerEpisodeListDialog, View view) {
        ChooseSeasonDialogFragment chooseSeasonDialogFragmentNewInstance = ChooseSeasonDialogFragment.INSTANCE.newInstance(playerEpisodeListDialog.seasons, playerEpisodeListDialog.years);
        int i = playerEpisodeListDialog.currSeason;
        String strValueOf = i == 0 ? "" : String.valueOf(i);
        int i2 = playerEpisodeListDialog.currYear;
        chooseSeasonDialogFragmentNewInstance.setSeasonYear(strValueOf, i2 != 0 ? String.valueOf(i2) : "");
        chooseSeasonDialogFragmentNewInstance.setOnSeasonYearChooseListener(new ChooseSeasonDialogFragment.OnSeasonYearChooseListener() { // from class: com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog$$ExternalSyntheticLambda1
            @Override // com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment.OnSeasonYearChooseListener
            public final void onSeasonYearChoose(String str, String str2) {
                PlayerEpisodeListDialog.initSeasonClick$lambda$6$lambda$5(this.f$0, str, str2);
            }
        });
        chooseSeasonDialogFragmentNewInstance.show(playerEpisodeListDialog.getChildFragmentManager(), "ChooseSeasonDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initSeasonClick$lambda$6$lambda$5(PlayerEpisodeListDialog playerEpisodeListDialog, String season, String year) {
        Intrinsics.checkNotNullParameter(season, "season");
        Intrinsics.checkNotNullParameter(year, "year");
        playerEpisodeListDialog.onSeasonYearChosen(season, year);
    }

    private final void onSeasonYearChosen(String season, String year) {
        try {
            this.currSeason = season.length() == 0 ? 0 : Integer.parseInt(season);
            this.currYear = year.length() == 0 ? 0 : Integer.parseInt(year);
            getBinding().tvCurrSeason.setText(EpisodeSeasonMapper.INSTANCE.headerTitle(this.currSeason, this.currYear));
            getBinding().llSeason.requestFocus();
            reloadEpisodes$default(this, false, 1, null);
        } catch (NumberFormatException unused) {
            ToastUtils.showShort("Load failed:invalid season", new Object[0]);
        }
    }

    static /* synthetic */ void reloadEpisodes$default(PlayerEpisodeListDialog playerEpisodeListDialog, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        playerEpisodeListDialog.reloadEpisodes(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadEpisodes(boolean forceNetwork) {
        String string;
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString(ARG_TV_ID)) == null) {
            return;
        }
        int i = this.currSeason;
        String strValueOf = i == 0 ? "" : String.valueOf(i);
        int i2 = this.currYear;
        String strValueOf2 = i2 != 0 ? String.valueOf(i2) : "";
        EpisodeListPresenter episodeListPresenter = this.presenter;
        if (episodeListPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
            episodeListPresenter = null;
        }
        episodeListPresenter.loadEpisodes(string, strValueOf, strValueOf2, forceNetwork);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scrollToPlayingAndFocus() {
        EpisodeSeasonMapper episodeSeasonMapper = EpisodeSeasonMapper.INSTANCE;
        PlayerEpisodeAdapter playerEpisodeAdapter = this.adapter;
        PlayerEpisodeAdapter playerEpisodeAdapter2 = null;
        if (playerEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            playerEpisodeAdapter = null;
        }
        int iIndexOfEpisode = episodeSeasonMapper.indexOfEpisode(playerEpisodeAdapter.getData(), this.playingSeason, this.playingEpisode);
        PlayerEpisodeAdapter playerEpisodeAdapter3 = this.adapter;
        if (playerEpisodeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            playerEpisodeAdapter2 = playerEpisodeAdapter3;
        }
        if (playerEpisodeAdapter2.getData().isEmpty()) {
            return;
        }
        if (iIndexOfEpisode < 0) {
            iIndexOfEpisode = 0;
        }
        this.pendingSelectPosition = iIndexOfEpisode;
        this.selectAttempts = 0;
        getBinding().recyclerView.removeCallbacks(this.selectPlayingRunnable);
        getBinding().recyclerView.post(this.selectPlayingRunnable);
    }

    private final void selectPlayingEpisode() {
        int height;
        if (isAdded()) {
            final TvRecyclerView recyclerView = getBinding().recyclerView;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
            final int i = this.pendingSelectPosition;
            if (i >= 0) {
                PlayerEpisodeAdapter playerEpisodeAdapter = this.adapter;
                if (playerEpisodeAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    playerEpisodeAdapter = null;
                }
                if (i >= playerEpisodeAdapter.getData().size()) {
                    return;
                }
                if (recyclerView.getVisibility() != 0 || recyclerView.getHeight() <= 0) {
                    retrySelectPlayingEpisode();
                    return;
                }
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                final LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
                if (linearLayoutManager == null) {
                    return;
                }
                View childAt = recyclerView.getChildAt(0);
                if (childAt != null && childAt.getHeight() > 0) {
                    height = (recyclerView.getHeight() - childAt.getHeight()) / 2;
                } else {
                    height = recyclerView.getHeight() / 3;
                }
                linearLayoutManager.scrollToPositionWithOffset(i, RangesKt.coerceAtLeast(height, 0));
                recyclerView.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlayerEpisodeListDialog.selectPlayingEpisode$lambda$7(this.f$0, linearLayoutManager, i, recyclerView);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void selectPlayingEpisode$lambda$7(PlayerEpisodeListDialog playerEpisodeListDialog, LinearLayoutManager linearLayoutManager, int i, TvRecyclerView tvRecyclerView) {
        if (playerEpisodeListDialog.isAdded()) {
            View viewFindViewByPosition = linearLayoutManager.findViewByPosition(i);
            if (viewFindViewByPosition != null) {
                tvRecyclerView.setSelection(i);
                viewFindViewByPosition.requestFocus();
            } else {
                playerEpisodeListDialog.retrySelectPlayingEpisode();
            }
        }
    }

    private final void retrySelectPlayingEpisode() {
        int i = this.selectAttempts;
        if (i >= 20) {
            return;
        }
        this.selectAttempts = i + 1;
        getBinding().recyclerView.removeCallbacks(this.selectPlayingRunnable);
        getBinding().recyclerView.postDelayed(this.selectPlayingRunnable, 50L);
    }

    /* JADX INFO: compiled from: PlayerEpisodeListDialog.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListDialog$Companion;", "", "<init>", "()V", "ARG_TV_ID", "", "ARG_SEASON", "ARG_EPISODE", "newInstance", "Lcom/movieboxpro/android/view/dialog/PlayerEpisodeListDialog;", PlayerEpisodeListDialog.ARG_TV_ID, "season", "", "episode", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final PlayerEpisodeListDialog newInstance(String tvId, int season, int episode) {
            Intrinsics.checkNotNullParameter(tvId, "tvId");
            PlayerEpisodeListDialog playerEpisodeListDialog = new PlayerEpisodeListDialog();
            Bundle bundle = new Bundle();
            bundle.putString(PlayerEpisodeListDialog.ARG_TV_ID, tvId);
            bundle.putInt("season", season);
            bundle.putInt("episode", episode);
            playerEpisodeListDialog.setArguments(bundle);
            return playerEpisodeListDialog;
        }
    }
}
