package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.adorkable.iosdialog.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.FilterModel;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.widget.LayoutManagerItemDecoration;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.databinding.FragmentFilterFavoriteBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FilterFavoriteDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 M2\u00020\u0001:\u0002LMB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u000e\u0010)\u001a\u00020&2\u0006\u0010#\u001a\u00020$J\b\u0010*\u001a\u00020&H\u0016J&\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u001a\u00101\u001a\u00020&2\u0006\u00102\u001a\u00020,2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010:\u001a\u00020&H\u0002J\u0014\u0010;\u001a\u00020&2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00100=J\u001e\u0010>\u001a\u00020&2\u0016\u0010<\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u0007J\b\u0010?\u001a\u00020&H\u0002J!\u0010@\u001a\u00020&2\u0012\u0010A\u001a\n\u0012\u0006\b\u0001\u0012\u00020C0B\"\u00020CH\u0002¢\u0006\u0002\u0010DJ\b\u0010E\u001a\u00020&H\u0003J\u0010\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020\u0006H\u0002J\u0018\u0010H\u001a\u00020&2\u0006\u0010I\u001a\u00020,2\u0006\u0010J\u001a\u00020KH\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0005j\b\u0012\u0004\u0012\u00020\u0010`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a0\u0018X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001a0\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "sortTitles", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "sort", "ratingTitles", "rating", "resolutionTitles", "resolution", "countryList", "Lcom/movieboxpro/android/model/FilterCountry;", "geners", "Lcom/movieboxpro/android/model/common/Gener;", "country", "selectMorePos", "", "currType", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "sortAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/FilterModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "typeAdapter", "yearAdapter", "ratingAdapter", "resolutionAdapter", "countryAdapter", "generAdapter", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentFilterFavoriteBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/FilterFavoriteDialogFragment$OnFilterDoneListener;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setOnListener", "onStart", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "lastSortPos", "lastTypePos", "lastYearPos", "lastGenerPos", "lastRatingPos", "lastResolutionPos", "lastCountryPos", "initListener", "setGener", "list", "", "setCountry", "initData", "scaleListener", "recyclerView", "", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView;", "([Lcom/owen/tvrecyclerview/widget/TvRecyclerView;)V", "initView", "switchTvMovieType", "type", "focusScale", "v", "scale", "", "OnFilterDoneListener", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FilterFavoriteDialogFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DEFAULT_SORT = "released";
    private FragmentFilterFavoriteBinding binding;
    private BaseQuickAdapter<FilterCountry, BaseViewHolder> countryAdapter;
    private BaseQuickAdapter<Gener, BaseViewHolder> generAdapter;
    private int lastCountryPos;
    private int lastSortPos;
    private int lastTypePos;
    private OnFilterDoneListener listener;
    private FocusBorder mFocusBorder;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> ratingAdapter;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> resolutionAdapter;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> sortAdapter;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> typeAdapter;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> yearAdapter;
    private ArrayList<String> sortTitles = new ArrayList<>();
    private ArrayList<String> sort = new ArrayList<>();
    private ArrayList<String> ratingTitles = new ArrayList<>();
    private ArrayList<String> rating = new ArrayList<>();
    private ArrayList<String> resolutionTitles = new ArrayList<>();
    private ArrayList<String> resolution = new ArrayList<>();
    private ArrayList<FilterCountry> countryList = new ArrayList<>();
    private ArrayList<Gener> geners = new ArrayList<>();
    private String country = "";
    private int selectMorePos = -1;
    private String currType = TtmlNode.COMBINE_ALL;
    private int lastYearPos = -1;
    private int lastGenerPos = -1;
    private int lastRatingPos = -1;
    private int lastResolutionPos = -1;

    /* JADX INFO: compiled from: FilterFavoriteDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteDialogFragment$OnFilterDoneListener;", "", "onFilterDone", "", "boxType", "", "sort", "year", "gener", "rating", "quality", "country", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnFilterDoneListener {
        void onFilterDone(String boxType, String sort, String year, String gener, String rating, String quality, String country);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogTopShowStyle);
    }

    public final void setOnListener(OnFilterDoneListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            Context context = getContext();
            window.setBackgroundDrawable(context != null ? ContextCompat.getDrawable(context, android.R.color.transparent) : null);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 48;
            attributes.dimAmount = 0.0f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentFilterFavoriteBinding fragmentFilterFavoriteBindingInflate = FragmentFilterFavoriteBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentFilterFavoriteBindingInflate, "inflate(...)");
        this.binding = fragmentFilterFavoriteBindingInflate;
        if (fragmentFilterFavoriteBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterFavoriteBindingInflate = null;
        }
        return fragmentFilterFavoriteBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private final void initListener() {
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter = this.typeAdapter;
        FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteDialogFragment.initListener$lambda$1(this.f$0, baseQuickAdapter2, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = this.sortAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter2 = null;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                FilterFavoriteDialogFragment.initListener$lambda$2(this.f$0, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = this.ratingAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                FilterFavoriteDialogFragment.initListener$lambda$3(this.f$0, baseQuickAdapter4, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = this.resolutionAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter4 = null;
        }
        baseQuickAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i) {
                FilterFavoriteDialogFragment.initListener$lambda$4(this.f$0, baseQuickAdapter5, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = this.yearAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter6, View view, int i) {
                FilterFavoriteDialogFragment.initListener$lambda$5(this.f$0, baseQuickAdapter6, view, i);
            }
        });
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter6 = this.generAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter6 = null;
        }
        baseQuickAdapter6.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter7, View view, int i) {
                FilterFavoriteDialogFragment.initListener$lambda$6(this.f$0, baseQuickAdapter7, view, i);
            }
        });
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter7 = this.countryAdapter;
        if (baseQuickAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter7 = null;
        }
        baseQuickAdapter7.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter8, View view, int i) {
                FilterFavoriteDialogFragment.initListener$lambda$7(this.f$0, baseQuickAdapter8, view, i);
            }
        });
        FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding2 = this.binding;
        if (fragmentFilterFavoriteBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterFavoriteBinding2 = null;
        }
        fragmentFilterFavoriteBinding2.tvDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterFavoriteDialogFragment.initListener$lambda$8(this.f$0, view);
            }
        });
        FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding3 = this.binding;
        if (fragmentFilterFavoriteBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentFilterFavoriteBinding = fragmentFilterFavoriteBinding3;
        }
        fragmentFilterFavoriteBinding.tvReset.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterFavoriteDialogFragment.initListener$lambda$9(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(FilterFavoriteDialogFragment filterFavoriteDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (filterFavoriteDialogFragment.lastTypePos == i) {
            return;
        }
        filterFavoriteDialogFragment.lastRatingPos = -1;
        filterFavoriteDialogFragment.lastResolutionPos = -1;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = filterFavoriteDialogFragment.typeAdapter;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            baseQuickAdapter2 = null;
        }
        FilterModel itemOrNull = baseQuickAdapter2.getItemOrNull(filterFavoriteDialogFragment.lastTypePos);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterFavoriteDialogFragment.typeAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            baseQuickAdapter4 = null;
        }
        FilterModel item = baseQuickAdapter4.getItem(i);
        if (item != null) {
            item.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterFavoriteDialogFragment.typeAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.notifyItemChanged(i);
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterFavoriteDialogFragment.typeAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
        } else {
            baseQuickAdapter3 = baseQuickAdapter6;
        }
        baseQuickAdapter3.notifyItemChanged(filterFavoriteDialogFragment.lastTypePos);
        filterFavoriteDialogFragment.lastTypePos = i;
        if (i == 0) {
            filterFavoriteDialogFragment.switchTvMovieType(TtmlNode.COMBINE_ALL);
        } else if (i == 1) {
            filterFavoriteDialogFragment.switchTvMovieType(BuildConfig.CHANNEL);
        } else {
            if (i != 2) {
                return;
            }
            filterFavoriteDialogFragment.switchTvMovieType("tv");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(FilterFavoriteDialogFragment filterFavoriteDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (filterFavoriteDialogFragment.lastSortPos == i) {
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = filterFavoriteDialogFragment.sortAdapter;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter2 = null;
        }
        FilterModel itemOrNull = baseQuickAdapter2.getItemOrNull(filterFavoriteDialogFragment.lastSortPos);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterFavoriteDialogFragment.sortAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter4 = null;
        }
        FilterModel item = baseQuickAdapter4.getItem(i);
        if (item != null) {
            item.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterFavoriteDialogFragment.sortAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.notifyItemChanged(i);
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterFavoriteDialogFragment.sortAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
        } else {
            baseQuickAdapter3 = baseQuickAdapter6;
        }
        baseQuickAdapter3.notifyItemChanged(filterFavoriteDialogFragment.lastSortPos);
        filterFavoriteDialogFragment.lastSortPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(FilterFavoriteDialogFragment filterFavoriteDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterFavoriteDialogFragment.lastRatingPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterFavoriteDialogFragment.lastRatingPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterFavoriteDialogFragment.ratingAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterFavoriteDialogFragment.ratingAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterFavoriteDialogFragment.ratingAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterFavoriteDialogFragment.ratingAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterFavoriteDialogFragment.lastRatingPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterFavoriteDialogFragment.ratingAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterFavoriteDialogFragment.lastRatingPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterFavoriteDialogFragment.ratingAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterFavoriteDialogFragment.ratingAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterFavoriteDialogFragment.lastRatingPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(FilterFavoriteDialogFragment filterFavoriteDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterFavoriteDialogFragment.lastResolutionPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterFavoriteDialogFragment.lastResolutionPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterFavoriteDialogFragment.resolutionAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterFavoriteDialogFragment.resolutionAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterFavoriteDialogFragment.resolutionAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterFavoriteDialogFragment.resolutionAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterFavoriteDialogFragment.lastResolutionPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterFavoriteDialogFragment.resolutionAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterFavoriteDialogFragment.lastResolutionPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterFavoriteDialogFragment.resolutionAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterFavoriteDialogFragment.resolutionAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterFavoriteDialogFragment.lastResolutionPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(FilterFavoriteDialogFragment filterFavoriteDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterFavoriteDialogFragment.lastYearPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterFavoriteDialogFragment.lastYearPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterFavoriteDialogFragment.yearAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterFavoriteDialogFragment.yearAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterFavoriteDialogFragment.yearAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterFavoriteDialogFragment.yearAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterFavoriteDialogFragment.lastYearPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterFavoriteDialogFragment.yearAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterFavoriteDialogFragment.lastYearPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterFavoriteDialogFragment.yearAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterFavoriteDialogFragment.yearAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterFavoriteDialogFragment.lastYearPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6(FilterFavoriteDialogFragment filterFavoriteDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterFavoriteDialogFragment.lastGenerPos;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter3 = filterFavoriteDialogFragment.generAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter3 = null;
            }
            Gener item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter4 = filterFavoriteDialogFragment.generAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            filterFavoriteDialogFragment.lastGenerPos = -1;
            return;
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter5 = filterFavoriteDialogFragment.generAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter6 = filterFavoriteDialogFragment.generAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter6 = null;
            }
            Gener itemOrNull = baseQuickAdapter6.getItemOrNull(filterFavoriteDialogFragment.lastGenerPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter7 = filterFavoriteDialogFragment.generAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterFavoriteDialogFragment.lastGenerPos);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter8 = filterFavoriteDialogFragment.generAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter8 = null;
        }
        Gener item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter9 = filterFavoriteDialogFragment.generAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterFavoriteDialogFragment.lastGenerPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(final FilterFavoriteDialogFragment filterFavoriteDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter2 = filterFavoriteDialogFragment.countryAdapter;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter2 = null;
        }
        FilterCountry item = baseQuickAdapter2.getItem(i);
        String country = item.getCountry();
        Intrinsics.checkNotNull(country);
        if (StringsKt.contains$default((CharSequence) country, (CharSequence) "More", false, 2, (Object) null)) {
            ChooseCountryDialog chooseCountryDialogNewInstance = ChooseCountryDialog.INSTANCE.newInstance(filterFavoriteDialogFragment.country);
            chooseCountryDialogNewInstance.setListener(new ArrayList<>(filterFavoriteDialogFragment.countryList), new ChooseCountryDialog.OnLanguageListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$initListener$7$1
                @Override // com.movieboxpro.android.view.dialog.ChooseCountryDialog.OnLanguageListener
                public void onLanguageSelected(String language) {
                    Intrinsics.checkNotNullParameter(language, "language");
                    this.this$0.selectMorePos = i;
                    this.this$0.country = language;
                    int i2 = this.this$0.lastCountryPos;
                    BaseQuickAdapter baseQuickAdapter4 = this.this$0.countryAdapter;
                    BaseQuickAdapter baseQuickAdapter5 = null;
                    if (baseQuickAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter4 = null;
                    }
                    if (i2 != baseQuickAdapter4.getData().size() - 1) {
                        BaseQuickAdapter baseQuickAdapter6 = this.this$0.countryAdapter;
                        if (baseQuickAdapter6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            baseQuickAdapter6 = null;
                        }
                        FilterCountry filterCountry = (FilterCountry) baseQuickAdapter6.getItemOrNull(this.this$0.lastCountryPos);
                        if (filterCountry != null) {
                            filterCountry.setSelect(false);
                        }
                        BaseQuickAdapter baseQuickAdapter7 = this.this$0.countryAdapter;
                        if (baseQuickAdapter7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            baseQuickAdapter7 = null;
                        }
                        baseQuickAdapter7.notifyItemChanged(this.this$0.lastCountryPos);
                    }
                    BaseQuickAdapter baseQuickAdapter8 = this.this$0.countryAdapter;
                    if (baseQuickAdapter8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter8 = null;
                    }
                    BaseQuickAdapter baseQuickAdapter9 = this.this$0.countryAdapter;
                    if (baseQuickAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter9 = null;
                    }
                    ((FilterCountry) baseQuickAdapter8.getItem(baseQuickAdapter9.getData().size() - 1)).setCountry("More(" + this.this$0.country + ")");
                    BaseQuickAdapter baseQuickAdapter10 = this.this$0.countryAdapter;
                    if (baseQuickAdapter10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter10 = null;
                    }
                    BaseQuickAdapter baseQuickAdapter11 = this.this$0.countryAdapter;
                    if (baseQuickAdapter11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter11 = null;
                    }
                    ((FilterCountry) baseQuickAdapter10.getItem(baseQuickAdapter11.getData().size() - 1)).setSelect(true);
                    BaseQuickAdapter baseQuickAdapter12 = this.this$0.countryAdapter;
                    if (baseQuickAdapter12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter12 = null;
                    }
                    BaseQuickAdapter baseQuickAdapter13 = this.this$0.countryAdapter;
                    if (baseQuickAdapter13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                    } else {
                        baseQuickAdapter5 = baseQuickAdapter13;
                    }
                    baseQuickAdapter12.notifyItemChanged(baseQuickAdapter5.getData().size() - 1);
                    this.this$0.lastCountryPos = i;
                }
            });
            chooseCountryDialogNewInstance.show(filterFavoriteDialogFragment.getChildFragmentManager(), "ChooseCountryDialog");
            return;
        }
        if (filterFavoriteDialogFragment.lastCountryPos == i) {
            return;
        }
        item.setSelect(true);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter4 = filterFavoriteDialogFragment.countryAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter4 = null;
        }
        FilterCountry itemOrNull = baseQuickAdapter4.getItemOrNull(filterFavoriteDialogFragment.lastCountryPos);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter5 = filterFavoriteDialogFragment.countryAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.notifyItemChanged(i);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter6 = filterFavoriteDialogFragment.countryAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
        } else {
            baseQuickAdapter3 = baseQuickAdapter6;
        }
        baseQuickAdapter3.notifyItemChanged(filterFavoriteDialogFragment.lastCountryPos);
        filterFavoriteDialogFragment.lastCountryPos = i;
        if (Intrinsics.areEqual(item.getCountry(), "All")) {
            filterFavoriteDialogFragment.country = "";
        } else {
            String country2 = item.getCountry();
            Intrinsics.checkNotNullExpressionValue(country2, "getCountry(...)");
            filterFavoriteDialogFragment.country = country2;
        }
        filterFavoriteDialogFragment.selectMorePos = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$8(FilterFavoriteDialogFragment filterFavoriteDialogFragment, View view) {
        String title;
        String str;
        int i = filterFavoriteDialogFragment.lastTypePos;
        String str2 = "0";
        if (i != 0) {
            if (i == 1) {
                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            } else if (i == 2) {
                str2 = ExifInterface.GPS_MEASUREMENT_2D;
            }
        }
        String str3 = str2;
        OnFilterDoneListener onFilterDoneListener = filterFavoriteDialogFragment.listener;
        if (onFilterDoneListener != null) {
            String str4 = filterFavoriteDialogFragment.sort.get(filterFavoriteDialogFragment.lastSortPos);
            Intrinsics.checkNotNullExpressionValue(str4, "get(...)");
            String str5 = str4;
            String str6 = "";
            if (filterFavoriteDialogFragment.lastYearPos == -1) {
                title = "";
            } else {
                BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter = filterFavoriteDialogFragment.yearAdapter;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                    baseQuickAdapter = null;
                }
                title = baseQuickAdapter.getData().get(filterFavoriteDialogFragment.lastYearPos).getTitle();
            }
            Intrinsics.checkNotNull(title);
            int i2 = filterFavoriteDialogFragment.lastGenerPos;
            String id = i2 == -1 ? "" : filterFavoriteDialogFragment.geners.get(i2).getId();
            Intrinsics.checkNotNull(id);
            int i3 = filterFavoriteDialogFragment.lastRatingPos;
            if (i3 == -1) {
                str = "";
            } else {
                String str7 = filterFavoriteDialogFragment.rating.get(i3);
                Intrinsics.checkNotNullExpressionValue(str7, "get(...)");
                str = str7;
            }
            int i4 = filterFavoriteDialogFragment.lastResolutionPos;
            if (i4 != -1) {
                String str8 = filterFavoriteDialogFragment.resolution.get(i4);
                Intrinsics.checkNotNullExpressionValue(str8, "get(...)");
                str6 = str8;
            }
            onFilterDoneListener.onFilterDone(str3, str5, title, id, str, str6, filterFavoriteDialogFragment.country);
        }
        filterFavoriteDialogFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$9(FilterFavoriteDialogFragment filterFavoriteDialogFragment, View view) {
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter = null;
        if (filterFavoriteDialogFragment.lastSortPos != 0) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = filterFavoriteDialogFragment.sortAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter2 = null;
            }
            FilterModel item = baseQuickAdapter2.getItem(0);
            if (item != null) {
                item.setSelect(true);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterFavoriteDialogFragment.sortAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter3 = null;
            }
            baseQuickAdapter3.notifyItemChanged(0);
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterFavoriteDialogFragment.sortAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter4 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter4.getItemOrNull(filterFavoriteDialogFragment.lastSortPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterFavoriteDialogFragment.sortAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(filterFavoriteDialogFragment.lastSortPos);
            filterFavoriteDialogFragment.lastSortPos = 0;
        }
        if (filterFavoriteDialogFragment.lastRatingPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterFavoriteDialogFragment.ratingAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull2 = baseQuickAdapter6.getItemOrNull(filterFavoriteDialogFragment.lastRatingPos);
            if (itemOrNull2 != null) {
                itemOrNull2.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterFavoriteDialogFragment.ratingAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterFavoriteDialogFragment.lastRatingPos);
            filterFavoriteDialogFragment.lastRatingPos = -1;
        }
        if (filterFavoriteDialogFragment.lastResolutionPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterFavoriteDialogFragment.resolutionAdapter;
            if (baseQuickAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter8 = null;
            }
            FilterModel itemOrNull3 = baseQuickAdapter8.getItemOrNull(filterFavoriteDialogFragment.lastResolutionPos);
            if (itemOrNull3 != null) {
                itemOrNull3.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterFavoriteDialogFragment.resolutionAdapter;
            if (baseQuickAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter9 = null;
            }
            baseQuickAdapter9.notifyItemChanged(filterFavoriteDialogFragment.lastResolutionPos);
            filterFavoriteDialogFragment.lastResolutionPos = -1;
        }
        if (filterFavoriteDialogFragment.lastYearPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter10 = filterFavoriteDialogFragment.yearAdapter;
            if (baseQuickAdapter10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter10 = null;
            }
            FilterModel itemOrNull4 = baseQuickAdapter10.getItemOrNull(filterFavoriteDialogFragment.lastYearPos);
            if (itemOrNull4 != null) {
                itemOrNull4.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter11 = filterFavoriteDialogFragment.yearAdapter;
            if (baseQuickAdapter11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter11 = null;
            }
            baseQuickAdapter11.notifyItemChanged(filterFavoriteDialogFragment.lastYearPos);
            filterFavoriteDialogFragment.lastYearPos = -1;
        }
        if (filterFavoriteDialogFragment.lastGenerPos != -1) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter12 = filterFavoriteDialogFragment.generAdapter;
            if (baseQuickAdapter12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter12 = null;
            }
            Gener itemOrNull5 = baseQuickAdapter12.getItemOrNull(filterFavoriteDialogFragment.lastGenerPos);
            if (itemOrNull5 != null) {
                itemOrNull5.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter13 = filterFavoriteDialogFragment.generAdapter;
            if (baseQuickAdapter13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter13 = null;
            }
            baseQuickAdapter13.notifyItemChanged(filterFavoriteDialogFragment.lastGenerPos);
            filterFavoriteDialogFragment.lastGenerPos = -1;
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter14 = filterFavoriteDialogFragment.countryAdapter;
        if (baseQuickAdapter14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter14 = null;
        }
        FilterCountry itemOrNull6 = baseQuickAdapter14.getItemOrNull(filterFavoriteDialogFragment.lastCountryPos);
        if (itemOrNull6 != null) {
            itemOrNull6.setSelect(false);
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter15 = filterFavoriteDialogFragment.countryAdapter;
        if (baseQuickAdapter15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter15 = null;
        }
        baseQuickAdapter15.notifyItemChanged(filterFavoriteDialogFragment.lastCountryPos);
        filterFavoriteDialogFragment.lastCountryPos = 0;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter16 = filterFavoriteDialogFragment.countryAdapter;
        if (baseQuickAdapter16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter16 = null;
        }
        baseQuickAdapter16.getItem(filterFavoriteDialogFragment.lastCountryPos).setSelect(true);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter17 = filterFavoriteDialogFragment.countryAdapter;
        if (baseQuickAdapter17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter17;
        }
        baseQuickAdapter.notifyItemChanged(filterFavoriteDialogFragment.lastCountryPos);
        filterFavoriteDialogFragment.country = "";
    }

    public final void setGener(List<Gener> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        List<Gener> list2 = list;
        if (!list2.isEmpty()) {
            list.remove(0);
        }
        this.geners.addAll(list2);
    }

    public final void setCountry(ArrayList<FilterCountry> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.countryList.clear();
        this.countryList.addAll(list);
    }

    private final void initData() {
        switchTvMovieType(this.currType);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FilterModel("All", Intrinsics.areEqual(this.currType, TtmlNode.COMBINE_ALL)));
        arrayList.add(new FilterModel("Movies", Intrinsics.areEqual(this.currType, BuildConfig.CHANNEL)));
        arrayList.add(new FilterModel("TV Shows", Intrinsics.areEqual(this.currType, "tv")));
        this.typeAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.initData.1
            {
                super(i, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = this.sortTitles.iterator();
        while (it.hasNext()) {
            arrayList2.add(new FilterModel((String) it.next(), false));
        }
        ((FilterModel) arrayList2.get(this.lastSortPos)).setSelect(true);
        this.sortAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList2, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.initData.3
            {
                super(i, arrayList2);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        ArrayList arrayList3 = new ArrayList();
        Iterator<T> it2 = this.resolutionTitles.iterator();
        while (it2.hasNext()) {
            arrayList3.add(new FilterModel((String) it2.next(), false));
        }
        if (CommonExtKt.checkIndexLegal(this.lastResolutionPos, arrayList3)) {
            ((FilterModel) arrayList3.get(this.lastResolutionPos)).setSelect(true);
        }
        this.resolutionAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList3, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.initData.5
            {
                super(i, arrayList3);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new FilterCountry("All", -1));
        if (this.countryList.size() >= 12) {
            arrayList4.addAll(this.countryList.subList(0, 10));
        }
        if (!StringsKt.isBlank(this.country) && this.selectMorePos != -1) {
            arrayList4.add(new FilterCountry("More(" + this.country + ")", -1));
        } else {
            arrayList4.add(new FilterCountry("More", -1));
        }
        FilterCountry filterCountry = (FilterCountry) CollectionsKt.getOrNull(arrayList4, this.lastCountryPos);
        if (filterCountry != null) {
            filterCountry.setSelect(true);
        }
        this.countryAdapter = new BaseQuickAdapter<FilterCountry, BaseViewHolder>(arrayList4, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.initData.6
            {
                super(i, arrayList4);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterCountry item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getCountry());
            }
        };
        ArrayList arrayList5 = new ArrayList();
        Iterator<T> it3 = this.ratingTitles.iterator();
        while (it3.hasNext()) {
            arrayList5.add(new FilterModel((String) it3.next(), false));
        }
        if (CommonExtKt.checkIndexLegal(this.lastRatingPos, arrayList5)) {
            ((FilterModel) arrayList5.get(this.lastRatingPos)).setSelect(true);
        }
        this.ratingAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList5, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.initData.8
            {
                super(i, arrayList5);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        ArrayList arrayList6 = new ArrayList();
        for (int i = Calendar.getInstance().get(1); 2009 < i; i--) {
            arrayList6.add(new FilterModel(String.valueOf(i)));
        }
        for (int i2 = 1; i2 < 12; i2++) {
            arrayList6.add(new FilterModel((2010 - (i2 * 10)) + "-" + (2010 - ((i2 - 1) * 10))));
        }
        if (CommonExtKt.checkIndexLegal(this.lastYearPos, arrayList6)) {
            ((FilterModel) arrayList6.get(this.lastYearPos)).setSelect(true);
        }
        this.yearAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList6, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.initData.9
            {
                super(i, arrayList6);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        this.generAdapter = new BaseQuickAdapter<Gener, BaseViewHolder>(com.movieboxpro.androidtv.R.layout.adapter_genre_item, this.geners) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.initData.10
            {
                ArrayList<Gener> arrayList7 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, Gener item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                GlideUtils.load(getContext(), item.getImg(), (ImageView) helper.getView(com.movieboxpro.androidtv.R.id.imageView));
                View view = helper.getView(com.movieboxpro.androidtv.R.id.view);
                if (item.isSelect()) {
                    CommonExtKt.visible(view);
                } else {
                    CommonExtKt.gone(view);
                }
            }
        };
        FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding = this.binding;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = null;
        if (fragmentFilterFavoriteBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterFavoriteBinding = null;
        }
        fragmentFilterFavoriteBinding.rvType.setSelectedItemAtCentered(true);
        fragmentFilterFavoriteBinding.rvType.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView = fragmentFilterFavoriteBinding.rvType;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = this.typeAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            baseQuickAdapter2 = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter2);
        fragmentFilterFavoriteBinding.rvSort.setSelectedItemAtCentered(true);
        fragmentFilterFavoriteBinding.rvSort.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView2 = fragmentFilterFavoriteBinding.rvSort;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = this.sortAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter3 = null;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter3);
        fragmentFilterFavoriteBinding.rvRating.setSelectedItemAtCentered(true);
        fragmentFilterFavoriteBinding.rvRating.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView3 = fragmentFilterFavoriteBinding.rvRating;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = this.ratingAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter4 = null;
        }
        tvRecyclerView3.setAdapter(baseQuickAdapter4);
        fragmentFilterFavoriteBinding.rvResolution.setSelectedItemAtCentered(true);
        fragmentFilterFavoriteBinding.rvResolution.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView4 = fragmentFilterFavoriteBinding.rvResolution;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = this.resolutionAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter5 = null;
        }
        tvRecyclerView4.setAdapter(baseQuickAdapter5);
        fragmentFilterFavoriteBinding.rvCountry.setSelectedItemAtCentered(true);
        fragmentFilterFavoriteBinding.rvCountry.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView5 = fragmentFilterFavoriteBinding.rvCountry;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter6 = this.countryAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter6 = null;
        }
        tvRecyclerView5.setAdapter(baseQuickAdapter6);
        fragmentFilterFavoriteBinding.rvYear.setSelectedItemAtCentered(true);
        fragmentFilterFavoriteBinding.rvYear.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView6 = fragmentFilterFavoriteBinding.rvYear;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = this.yearAdapter;
        if (baseQuickAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter7 = null;
        }
        tvRecyclerView6.setAdapter(baseQuickAdapter7);
        fragmentFilterFavoriteBinding.rvGener.setSelectedItemAtCentered(true);
        fragmentFilterFavoriteBinding.rvGener.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView7 = fragmentFilterFavoriteBinding.rvGener;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter8 = this.generAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter8;
        }
        tvRecyclerView7.setAdapter(baseQuickAdapter);
        TvRecyclerView rvSort = fragmentFilterFavoriteBinding.rvSort;
        Intrinsics.checkNotNullExpressionValue(rvSort, "rvSort");
        TvRecyclerView rvRating = fragmentFilterFavoriteBinding.rvRating;
        Intrinsics.checkNotNullExpressionValue(rvRating, "rvRating");
        TvRecyclerView rvResolution = fragmentFilterFavoriteBinding.rvResolution;
        Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
        TvRecyclerView rvYear = fragmentFilterFavoriteBinding.rvYear;
        Intrinsics.checkNotNullExpressionValue(rvYear, "rvYear");
        TvRecyclerView rvGener = fragmentFilterFavoriteBinding.rvGener;
        Intrinsics.checkNotNullExpressionValue(rvGener, "rvGener");
        TvRecyclerView rvType = fragmentFilterFavoriteBinding.rvType;
        Intrinsics.checkNotNullExpressionValue(rvType, "rvType");
        scaleListener(rvSort, rvRating, rvResolution, rvYear, rvGener, rvType);
    }

    private final void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(com.movieboxpro.androidtv.R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(com.movieboxpro.androidtv.R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(getActivity());
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
    }

    private final void switchTvMovieType(String type) {
        this.currType = type;
        int iHashCode = type.hashCode();
        if (iHashCode == 3714) {
            if (type.equals("tv")) {
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding = this.binding;
                if (fragmentFilterFavoriteBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding = null;
                }
                TvRecyclerView rvRating = fragmentFilterFavoriteBinding.rvRating;
                Intrinsics.checkNotNullExpressionValue(rvRating, "rvRating");
                CommonExtKt.visible(rvRating);
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding2 = this.binding;
                if (fragmentFilterFavoriteBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding2 = null;
                }
                TvRecyclerView rvResolution = fragmentFilterFavoriteBinding2.rvResolution;
                Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
                CommonExtKt.visible(rvResolution);
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding3 = this.binding;
                if (fragmentFilterFavoriteBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding3 = null;
                }
                TextView tvRating = fragmentFilterFavoriteBinding3.tvRating;
                Intrinsics.checkNotNullExpressionValue(tvRating, "tvRating");
                CommonExtKt.visible(tvRating);
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding4 = this.binding;
                if (fragmentFilterFavoriteBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding4 = null;
                }
                TextView tvResolution = fragmentFilterFavoriteBinding4.tvResolution;
                Intrinsics.checkNotNullExpressionValue(tvResolution, "tvResolution");
                CommonExtKt.visible(tvResolution);
                this.sortTitles = CollectionsKt.arrayListOf("Default", "Added", "Name", "Released");
                this.sort = CollectionsKt.arrayListOf("", "add_time", "name", "episode_released");
                this.ratingTitles = CollectionsKt.arrayListOf("TV-Y", "TV-Y7", "TV-G", "TV-PG", "TV-14", "TV-MA");
                this.rating = CollectionsKt.arrayListOf("TV-Y", "TV-Y7", "TV-G", "TV-PG", "TV-14", "TV-MA");
                this.resolutionTitles = CollectionsKt.arrayListOf("");
                this.resolution = CollectionsKt.arrayListOf("");
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding5 = this.binding;
                if (fragmentFilterFavoriteBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding5 = null;
                }
                TextView tvResolution2 = fragmentFilterFavoriteBinding5.tvResolution;
                Intrinsics.checkNotNullExpressionValue(tvResolution2, "tvResolution");
                CommonExtKt.gone(tvResolution2);
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding6 = this.binding;
                if (fragmentFilterFavoriteBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding6 = null;
                }
                TvRecyclerView rvResolution2 = fragmentFilterFavoriteBinding6.rvResolution;
                Intrinsics.checkNotNullExpressionValue(rvResolution2, "rvResolution");
                CommonExtKt.gone(rvResolution2);
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding7 = this.binding;
                if (fragmentFilterFavoriteBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding7 = null;
                }
                fragmentFilterFavoriteBinding7.tvRating.setText("TVSHOW RATINGS");
                if (this.ratingAdapter != null) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<T> it = this.ratingTitles.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new FilterModel((String) it.next(), false));
                    }
                    if (CommonExtKt.checkIndexLegal(this.lastRatingPos, arrayList)) {
                        ((FilterModel) arrayList.get(this.lastRatingPos)).setSelect(true);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<T> it2 = this.resolutionTitles.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(new FilterModel((String) it2.next(), false));
                    }
                    if (CommonExtKt.checkIndexLegal(this.lastResolutionPos, arrayList2)) {
                        ((FilterModel) arrayList2.get(this.lastResolutionPos)).setSelect(true);
                    }
                    BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter = this.ratingAdapter;
                    if (baseQuickAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                        baseQuickAdapter = null;
                    }
                    baseQuickAdapter.setList(arrayList);
                    BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = this.resolutionAdapter;
                    if (baseQuickAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                        baseQuickAdapter2 = null;
                    }
                    baseQuickAdapter2.setList(arrayList2);
                    return;
                }
                return;
            }
            return;
        }
        if (iHashCode == 96673) {
            if (type.equals(TtmlNode.COMBINE_ALL)) {
                this.sortTitles = CollectionsKt.arrayListOf("Default", "Added", "Name", "Released");
                this.sort = CollectionsKt.arrayListOf("", "add_time", "name", "released");
                this.ratingTitles = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
                this.rating = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
                this.resolutionTitles = CollectionsKt.arrayListOf("1080P", "4K", "8K", "BLU-RAY");
                this.resolution = CollectionsKt.arrayListOf("1080p", "4k", "8k", "blu-ray");
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding8 = this.binding;
                if (fragmentFilterFavoriteBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding8 = null;
                }
                TvRecyclerView rvRating2 = fragmentFilterFavoriteBinding8.rvRating;
                Intrinsics.checkNotNullExpressionValue(rvRating2, "rvRating");
                CommonExtKt.gone(rvRating2);
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding9 = this.binding;
                if (fragmentFilterFavoriteBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding9 = null;
                }
                TvRecyclerView rvResolution3 = fragmentFilterFavoriteBinding9.rvResolution;
                Intrinsics.checkNotNullExpressionValue(rvResolution3, "rvResolution");
                CommonExtKt.gone(rvResolution3);
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding10 = this.binding;
                if (fragmentFilterFavoriteBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding10 = null;
                }
                TextView tvRating2 = fragmentFilterFavoriteBinding10.tvRating;
                Intrinsics.checkNotNullExpressionValue(tvRating2, "tvRating");
                CommonExtKt.gone(tvRating2);
                FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding11 = this.binding;
                if (fragmentFilterFavoriteBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFilterFavoriteBinding11 = null;
                }
                TextView tvResolution3 = fragmentFilterFavoriteBinding11.tvResolution;
                Intrinsics.checkNotNullExpressionValue(tvResolution3, "tvResolution");
                CommonExtKt.gone(tvResolution3);
                return;
            }
            return;
        }
        if (iHashCode == 104087344 && type.equals(BuildConfig.CHANNEL)) {
            FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding12 = this.binding;
            if (fragmentFilterFavoriteBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterFavoriteBinding12 = null;
            }
            TvRecyclerView rvRating3 = fragmentFilterFavoriteBinding12.rvRating;
            Intrinsics.checkNotNullExpressionValue(rvRating3, "rvRating");
            CommonExtKt.visible(rvRating3);
            FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding13 = this.binding;
            if (fragmentFilterFavoriteBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterFavoriteBinding13 = null;
            }
            TvRecyclerView rvResolution4 = fragmentFilterFavoriteBinding13.rvResolution;
            Intrinsics.checkNotNullExpressionValue(rvResolution4, "rvResolution");
            CommonExtKt.visible(rvResolution4);
            FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding14 = this.binding;
            if (fragmentFilterFavoriteBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterFavoriteBinding14 = null;
            }
            TextView tvRating3 = fragmentFilterFavoriteBinding14.tvRating;
            Intrinsics.checkNotNullExpressionValue(tvRating3, "tvRating");
            CommonExtKt.visible(tvRating3);
            FragmentFilterFavoriteBinding fragmentFilterFavoriteBinding15 = this.binding;
            if (fragmentFilterFavoriteBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterFavoriteBinding15 = null;
            }
            TextView tvResolution4 = fragmentFilterFavoriteBinding15.tvResolution;
            Intrinsics.checkNotNullExpressionValue(tvResolution4, "tvResolution");
            CommonExtKt.visible(tvResolution4);
            this.sortTitles = CollectionsKt.arrayListOf("Default", "Added", "Name", "Released");
            this.sort = CollectionsKt.arrayListOf("", "add_time", "name", "released");
            this.ratingTitles = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
            this.rating = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
            this.resolutionTitles = CollectionsKt.arrayListOf("1080P", "4K", "8K", "BLU-RAY");
            this.resolution = CollectionsKt.arrayListOf("1080p", "4k", "8k", "blu-ray");
            if (this.ratingAdapter != null) {
                ArrayList arrayList3 = new ArrayList();
                Iterator<T> it3 = this.ratingTitles.iterator();
                while (it3.hasNext()) {
                    arrayList3.add(new FilterModel((String) it3.next(), false));
                }
                if (CommonExtKt.checkIndexLegal(this.lastRatingPos, arrayList3)) {
                    ((FilterModel) arrayList3.get(this.lastRatingPos)).setSelect(true);
                }
                ArrayList arrayList4 = new ArrayList();
                Iterator<T> it4 = this.resolutionTitles.iterator();
                while (it4.hasNext()) {
                    arrayList4.add(new FilterModel((String) it4.next(), false));
                }
                if (CommonExtKt.checkIndexLegal(this.lastResolutionPos, arrayList4)) {
                    ((FilterModel) arrayList4.get(this.lastResolutionPos)).setSelect(true);
                }
                BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = this.ratingAdapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                    baseQuickAdapter3 = null;
                }
                baseQuickAdapter3.setList(arrayList3);
                BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = this.resolutionAdapter;
                if (baseQuickAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                    baseQuickAdapter4 = null;
                }
                baseQuickAdapter4.setList(arrayList4);
            }
        }
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

    /* JADX INFO: compiled from: FilterFavoriteDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteDialogFragment$Companion;", "", "<init>", "()V", "DEFAULT_SORT", "", "newInstance", "Lcom/movieboxpro/android/view/dialog/FilterDialogFragment;", "isMovie", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FilterDialogFragment newInstance(boolean isMovie) {
            FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isMovie", isMovie);
            filterDialogFragment.setArguments(bundle);
            return filterDialogFragment;
        }
    }

    private final void scaleListener(TvRecyclerView... recyclerView) {
        for (TvRecyclerView tvRecyclerView : recyclerView) {
            tvRecyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment$scaleListener$1$1
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

                @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
                public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                    Intrinsics.checkNotNullParameter(parent, "parent");
                    Intrinsics.checkNotNullParameter(itemView, "itemView");
                    this.this$0.focusScale(itemView, 1.2f);
                }
            });
        }
    }
}
