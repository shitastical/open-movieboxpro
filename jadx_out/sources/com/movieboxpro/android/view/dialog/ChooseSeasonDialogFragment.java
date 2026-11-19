package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.base.CommMultiBaseAdapter;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import com.movieboxpro.android.listener.CommonItemClickListener;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentChooseSeasonBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: ChooseSeasonDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u0000 12\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003012B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011J\b\u0010\u0019\u001a\u00020\u000fH\u0016J\u001a\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u000fH\u0002J\b\u0010 \u001a\u00020\u000fH\u0002J\b\u0010!\u001a\u00020\u000fH\u0002J\u0010\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020$H\u0002J\u0018\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&H\u0002J\u0010\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020+H\u0002J\u0018\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020/H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentChooseSeasonBinding;", "<init>", "()V", "adapter", "Lcom/movieboxpro/android/base/CommMultiBaseAdapter;", "Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment$SeasonYearItem;", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/CommonItemClickListener;", "seasonYearListener", "Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment$OnSeasonYearChooseListener;", "setOnSeasonYearChooseListener", "", "selectSeason", "", "selectYear", "onAttach", "context", "Landroid/content/Context;", "setSeasonYear", DealWatchNextService.SEASON, "year", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initView", "initListener", "initData", "bindVerticalKeyListener", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "findNextFocusablePosition", "", "fromPos", "direction", "setRecyclerViewListener", "recyclerView", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView;", "focusScale", "v", "scale", "", "OnSeasonYearChooseListener", "Companion", "SeasonYearItem", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseSeasonDialogFragment extends BaseTransparentDialogFragment<FragmentChooseSeasonBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SEASON = "ChooseSeason";
    private static final int SPAN_COUNT = 4;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_SEASON = 1;
    private static final int TYPE_YEAR = 2;
    public static final String YEAR = "ChooseYear";
    private CommMultiBaseAdapter<SeasonYearItem> adapter;
    private CommonItemClickListener listener;
    private FocusBorder mFocusBorder;
    private OnSeasonYearChooseListener seasonYearListener;
    private String selectSeason;
    private String selectYear;

    /* JADX INFO: compiled from: ChooseSeasonDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment$OnSeasonYearChooseListener;", "", "onSeasonYearChoose", "", DealWatchNextService.SEASON, "", "year", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnSeasonYearChooseListener {
        void onSeasonYearChoose(String season, String year);
    }

    public ChooseSeasonDialogFragment() {
        super(R.layout.fragment_choose_season);
        this.selectSeason = "";
        this.selectYear = "";
    }

    public final void setOnSeasonYearChooseListener(OnSeasonYearChooseListener listener) {
        this.seasonYearListener = listener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof CommonItemClickListener) {
            this.listener = (CommonItemClickListener) context;
        }
    }

    /* JADX INFO: compiled from: ChooseSeasonDialogFragment.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment$Companion;", "", "<init>", "()V", "SEASON", "", "YEAR", "TYPE_HEADER", "", "TYPE_SEASON", "TYPE_YEAR", "SPAN_COUNT", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment;", "seasons", "", "years", "([Ljava/lang/String;[Ljava/lang/String;)Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseSeasonDialogFragment newInstance(String[] seasons, String[] years) {
            ChooseSeasonDialogFragment chooseSeasonDialogFragment = new ChooseSeasonDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArray(DealWatchNextService.SEASON, seasons);
            bundle.putStringArray("year", years);
            chooseSeasonDialogFragment.setArguments(bundle);
            return chooseSeasonDialogFragment;
        }
    }

    public final void setSeasonYear(String season, String year) {
        Intrinsics.checkNotNullParameter(season, "season");
        Intrinsics.checkNotNullParameter(year, "year");
        this.selectSeason = season;
        this.selectYear = year;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            getResources().getDisplayMetrics();
            attributes.width = -1;
            attributes.height = -1;
            attributes.gravity = 17;
            attributes.dimAmount = 0.95f;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private final void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(getActivity());
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
    }

    private final void initListener() {
        CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter = this.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChooseSeasonDialogFragment.initListener$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(ChooseSeasonDialogFragment chooseSeasonDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter = chooseSeasonDialogFragment.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        SeasonYearItem itemOrNull = commMultiBaseAdapter.getItemOrNull(i);
        if (itemOrNull == null) {
            return;
        }
        int type = itemOrNull.getType();
        if (type == 1) {
            OnSeasonYearChooseListener onSeasonYearChooseListener = chooseSeasonDialogFragment.seasonYearListener;
            if (onSeasonYearChooseListener != null) {
                onSeasonYearChooseListener.onSeasonYearChoose(itemOrNull.getContent(), "");
            }
            CommonItemClickListener commonItemClickListener = chooseSeasonDialogFragment.listener;
            if (commonItemClickListener != null) {
                commonItemClickListener.onItemClicked(itemOrNull.getListIndex(), SEASON);
            }
            chooseSeasonDialogFragment.dismiss();
            return;
        }
        if (type != 2) {
            return;
        }
        OnSeasonYearChooseListener onSeasonYearChooseListener2 = chooseSeasonDialogFragment.seasonYearListener;
        if (onSeasonYearChooseListener2 != null) {
            onSeasonYearChooseListener2.onSeasonYearChoose("", itemOrNull.getContent());
        }
        CommonItemClickListener commonItemClickListener2 = chooseSeasonDialogFragment.listener;
        if (commonItemClickListener2 != null) {
            commonItemClickListener2.onItemClicked(itemOrNull.getListIndex(), YEAR);
        }
        chooseSeasonDialogFragment.dismiss();
    }

    private final void initData() {
        String[] stringArray;
        String[] stringArray2;
        ArrayList arrayList = new ArrayList();
        Bundle arguments = getArguments();
        CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter = null;
        List list = (arguments == null || (stringArray2 = arguments.getStringArray(DealWatchNextService.SEASON)) == null) ? null : ArraysKt.toList(stringArray2);
        arrayList.add(new SeasonYearItem(0, "Season", false, 0, 12, null));
        if (list != null) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = (String) obj;
                Intrinsics.checkNotNull(str);
                arrayList.add(new SeasonYearItem(1, str, Intrinsics.areEqual(this.selectSeason, str), i));
                i = i2;
            }
        }
        Bundle arguments2 = getArguments();
        List list2 = (arguments2 == null || (stringArray = arguments2.getStringArray("year")) == null) ? null : ArraysKt.toList(stringArray);
        List list3 = list2;
        if (list3 != null && !list3.isEmpty()) {
            arrayList.add(new SeasonYearItem(0, "Year", false, 0, 12, null));
            int i3 = 0;
            for (Object obj2 : list2) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str2 = (String) obj2;
                Intrinsics.checkNotNull(str2);
                arrayList.add(new SeasonYearItem(2, str2, Intrinsics.areEqual(this.selectYear, str2), i3));
                i3 = i4;
            }
        }
        CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter2 = new CommMultiBaseAdapter<>(new Function2() { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj3, Object obj4) {
                return ChooseSeasonDialogFragment.initData$lambda$4(this.f$0, (BaseViewHolder) obj3, (ChooseSeasonDialogFragment.SeasonYearItem) obj4);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                return Integer.valueOf(ChooseSeasonDialogFragment.initData$lambda$5((ChooseSeasonDialogFragment.SeasonYearItem) obj3));
            }
        }, CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(0, Integer.valueOf(R.layout.adapter_season_year_header)), TuplesKt.to(1, Integer.valueOf(R.layout.adapter_season_item)), TuplesKt.to(2, Integer.valueOf(R.layout.adapter_season_item))}));
        this.adapter = commMultiBaseAdapter2;
        commMultiBaseAdapter2.setList(arrayList);
        V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 4);
        v7GridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment.initData.5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                CommMultiBaseAdapter commMultiBaseAdapter3 = ChooseSeasonDialogFragment.this.adapter;
                if (commMultiBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter3 = null;
                }
                SeasonYearItem seasonYearItem = (SeasonYearItem) commMultiBaseAdapter3.getItemOrNull(position);
                return (seasonYearItem == null || seasonYearItem.getType() != 0) ? 1 : 4;
            }
        });
        getBinding().recyclerView.setLayoutManager(v7GridLayoutManager);
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter3 = this.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter = commMultiBaseAdapter3;
        }
        tvRecyclerView.setAdapter(commMultiBaseAdapter);
        TvRecyclerView recyclerView = getBinding().recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        setRecyclerViewListener(recyclerView);
        getBinding().recyclerView.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                ChooseSeasonDialogFragment.initData$lambda$8(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$4(ChooseSeasonDialogFragment chooseSeasonDialogFragment, BaseViewHolder helper, SeasonYearItem item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        int type = item.getType();
        if (type == 0) {
            helper.setText(R.id.tvTitle, item.getContent());
            helper.itemView.setFocusable(false);
            helper.itemView.setClickable(false);
        } else if (type == 1) {
            TextView textView = (TextView) helper.getView(R.id.textView);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("SEASON %s", Arrays.copyOf(new Object[]{item.getContent()}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            textView.setText(str);
            textView.setTypeface(null, item.getSelected() ? 1 : 0);
            chooseSeasonDialogFragment.bindVerticalKeyListener(helper);
        } else if (type == 2) {
            TextView textView2 = (TextView) helper.getView(R.id.textView);
            textView2.setText(Intrinsics.areEqual(item.getContent(), "-1") ? "Released" : item.getContent());
            textView2.setTypeface(null, item.getSelected() ? 1 : 0);
            chooseSeasonDialogFragment.bindVerticalKeyListener(helper);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int initData$lambda$5(SeasonYearItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$8(ChooseSeasonDialogFragment chooseSeasonDialogFragment) {
        int i;
        CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter = chooseSeasonDialogFragment.adapter;
        CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        Iterator<SeasonYearItem> it = commMultiBaseAdapter.getData().iterator();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = -1;
            if (!it.hasNext()) {
                i3 = -1;
                break;
            } else if (it.next().getSelected()) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 >= 0) {
            i = i3;
        } else {
            CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter3 = chooseSeasonDialogFragment.adapter;
            if (commMultiBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commMultiBaseAdapter2 = commMultiBaseAdapter3;
            }
            Iterator<SeasonYearItem> it2 = commMultiBaseAdapter2.getData().iterator();
            while (it2.hasNext()) {
                if (it2.next().getType() != 0) {
                    i = i2;
                    break;
                }
                i2++;
            }
        }
        if (i >= 0) {
            chooseSeasonDialogFragment.getBinding().recyclerView.setSelection(i);
        }
    }

    private final void bindVerticalKeyListener(final BaseViewHolder helper) {
        helper.itemView.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return ChooseSeasonDialogFragment.bindVerticalKeyListener$lambda$9(helper, this, view, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindVerticalKeyListener$lambda$9(BaseViewHolder baseViewHolder, ChooseSeasonDialogFragment chooseSeasonDialogFragment, View view, int i, KeyEvent keyEvent) {
        int i2;
        if (keyEvent.getAction() != 0) {
            return false;
        }
        if (i == 19) {
            i2 = 33;
        } else {
            if (i != 20) {
                return false;
            }
            i2 = TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
        }
        int adapterPosition = baseViewHolder.getAdapterPosition();
        if (adapterPosition == -1) {
            return false;
        }
        int iFindNextFocusablePosition = chooseSeasonDialogFragment.findNextFocusablePosition(adapterPosition, i2);
        if (iFindNextFocusablePosition < 0) {
            return true;
        }
        chooseSeasonDialogFragment.getBinding().recyclerView.setSelection(iFindNextFocusablePosition);
        return true;
    }

    private final int findNextFocusablePosition(int fromPos, int direction) {
        CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter = this.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        SeasonYearItem itemOrNull = commMultiBaseAdapter.getItemOrNull(fromPos);
        if (itemOrNull == null || itemOrNull.getType() == 0) {
            return -1;
        }
        int listIndex = itemOrNull.getListIndex() % 4;
        int i = direction == 33 ? -1 : 1;
        while (true) {
            fromPos += i;
            if (fromPos >= 0) {
                CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter2 = this.adapter;
                if (commMultiBaseAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter2 = null;
                }
                if (fromPos >= commMultiBaseAdapter2.getData().size()) {
                    break;
                }
                CommMultiBaseAdapter<SeasonYearItem> commMultiBaseAdapter3 = this.adapter;
                if (commMultiBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter3 = null;
                }
                SeasonYearItem seasonYearItem = commMultiBaseAdapter3.getData().get(fromPos);
                if (seasonYearItem.getType() != 0 && seasonYearItem.getListIndex() % 4 == listIndex) {
                    return fromPos;
                }
            } else {
                break;
            }
        }
        return -1;
    }

    private final void setRecyclerViewListener(TvRecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setSpacingWithMargins(DensityUtils.dp2px(getContext(), 15.0f), DensityUtils.dp2px(getContext(), 15.0f));
        recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment.setRecyclerViewListener.1
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                CommMultiBaseAdapter commMultiBaseAdapter = ChooseSeasonDialogFragment.this.adapter;
                if (commMultiBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter = null;
                }
                SeasonYearItem seasonYearItem = (SeasonYearItem) commMultiBaseAdapter.getItemOrNull(position);
                if (seasonYearItem == null || seasonYearItem.getType() != 0) {
                    ChooseSeasonDialogFragment.this.focusScale(itemView, 1.15f);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ChooseSeasonDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment$SeasonYearItem;", "", "type", "", FirebaseAnalytics.Param.CONTENT, "", "selected", "", "listIndex", "<init>", "(ILjava/lang/String;ZI)V", "getType", "()I", "getContent", "()Ljava/lang/String;", "getSelected", "()Z", "getListIndex", "component1", "component2", "component3", "component4", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class SeasonYearItem {
        private final String content;
        private final int listIndex;
        private final boolean selected;
        private final int type;

        public static /* synthetic */ SeasonYearItem copy$default(SeasonYearItem seasonYearItem, int i, String str, boolean z, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = seasonYearItem.type;
            }
            if ((i3 & 2) != 0) {
                str = seasonYearItem.content;
            }
            if ((i3 & 4) != 0) {
                z = seasonYearItem.selected;
            }
            if ((i3 & 8) != 0) {
                i2 = seasonYearItem.listIndex;
            }
            return seasonYearItem.copy(i, str, z, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getContent() {
            return this.content;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getSelected() {
            return this.selected;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getListIndex() {
            return this.listIndex;
        }

        public final SeasonYearItem copy(int type, String content, boolean selected, int listIndex) {
            Intrinsics.checkNotNullParameter(content, "content");
            return new SeasonYearItem(type, content, selected, listIndex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeasonYearItem)) {
                return false;
            }
            SeasonYearItem seasonYearItem = (SeasonYearItem) other;
            return this.type == seasonYearItem.type && Intrinsics.areEqual(this.content, seasonYearItem.content) && this.selected == seasonYearItem.selected && this.listIndex == seasonYearItem.listIndex;
        }

        public int hashCode() {
            return (((((this.type * 31) + this.content.hashCode()) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.selected)) * 31) + this.listIndex;
        }

        public String toString() {
            return "SeasonYearItem(type=" + this.type + ", content=" + this.content + ", selected=" + this.selected + ", listIndex=" + this.listIndex + ")";
        }

        public SeasonYearItem(int i, String content, boolean z, int i2) {
            Intrinsics.checkNotNullParameter(content, "content");
            this.type = i;
            this.content = content;
            this.selected = z;
            this.listIndex = i2;
        }

        public final int getType() {
            return this.type;
        }

        public /* synthetic */ SeasonYearItem(int i, String str, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? false : z, (i3 & 8) != 0 ? 0 : i2);
        }

        public final String getContent() {
            return this.content;
        }

        public final boolean getSelected() {
            return this.selected;
        }

        public final int getListIndex() {
            return this.listIndex;
        }
    }
}
